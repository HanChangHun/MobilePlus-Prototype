package android.app;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadSystemException;
import android.os.FileUtils;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManagerGlobal;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import libcore.io.IoUtils;

public class WallpaperManager {
  public static final String ACTION_CHANGE_LIVE_WALLPAPER = "android.service.wallpaper.CHANGE_LIVE_WALLPAPER";
  
  public static final String ACTION_CROP_AND_SET_WALLPAPER = "android.service.wallpaper.CROP_AND_SET_WALLPAPER";
  
  public static final String ACTION_LIVE_WALLPAPER_CHOOSER = "android.service.wallpaper.LIVE_WALLPAPER_CHOOSER";
  
  public static final String COMMAND_DROP = "android.home.drop";
  
  public static final String COMMAND_REAPPLY = "android.wallpaper.reapply";
  
  public static final String COMMAND_SECONDARY_TAP = "android.wallpaper.secondaryTap";
  
  public static final String COMMAND_TAP = "android.wallpaper.tap";
  
  private static boolean DEBUG = false;
  
  public static final String EXTRA_LIVE_WALLPAPER_COMPONENT = "android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT";
  
  public static final String EXTRA_NEW_WALLPAPER_ID = "android.service.wallpaper.extra.ID";
  
  public static final int FLAG_LOCK = 2;
  
  public static final int FLAG_SYSTEM = 1;
  
  private static final String PROP_LOCK_WALLPAPER = "ro.config.lock_wallpaper";
  
  private static final String PROP_WALLPAPER = "ro.config.wallpaper";
  
  private static final String PROP_WALLPAPER_COMPONENT = "ro.config.wallpaper_component";
  
  private static String TAG = "WallpaperManager";
  
  public static final String WALLPAPER_PREVIEW_META_DATA = "android.wallpaper.preview";
  
  private static Globals sGlobals;
  
  private static final Object sSync;
  
  private final ColorManagementProxy mCmProxy;
  
  private final Context mContext;
  
  private float mWallpaperXStep;
  
  private float mWallpaperYStep;
  
  private final boolean mWcgEnabled;
  
  static {
    DEBUG = false;
    sSync = new Object[0];
  }
  
  WallpaperManager() {
    this.mWallpaperXStep = -1.0F;
    this.mWallpaperYStep = -1.0F;
    this.mContext = null;
    this.mCmProxy = null;
    this.mWcgEnabled = false;
  }
  
  WallpaperManager(IWallpaperManager paramIWallpaperManager, Context paramContext, Handler paramHandler) {
    boolean bool;
    this.mWallpaperXStep = -1.0F;
    this.mWallpaperYStep = -1.0F;
    this.mContext = paramContext;
    if (paramIWallpaperManager != null)
      initGlobals(paramIWallpaperManager, paramContext.getMainLooper()); 
    if (paramContext.getResources().getConfiguration().isScreenWideColorGamut() && paramContext.getResources().getBoolean(17891455)) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mWcgEnabled = bool;
    this.mCmProxy = new ColorManagementProxy(paramContext);
  }
  
  private void copyStreamToWallpaperFile(InputStream paramInputStream, FileOutputStream paramFileOutputStream) throws IOException {
    FileUtils.copy(paramInputStream, paramFileOutputStream);
  }
  
  public static ComponentName getDefaultWallpaperComponent(Context paramContext) {
    ComponentName componentName1 = null;
    String str = SystemProperties.get("ro.config.wallpaper_component");
    if (!TextUtils.isEmpty(str))
      componentName1 = ComponentName.unflattenFromString(str); 
    ComponentName componentName2 = componentName1;
    if (componentName1 == null) {
      String str1 = paramContext.getString(17040040);
      componentName2 = componentName1;
      if (!TextUtils.isEmpty(str1))
        componentName2 = ComponentName.unflattenFromString(str1); 
    } 
    componentName1 = componentName2;
    if (componentName2 != null)
      try {
        paramContext.getPackageManager().getPackageInfo(componentName2.getPackageName(), 786432);
        componentName1 = componentName2;
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        componentName1 = null;
      }  
    return componentName1;
  }
  
  public static WallpaperManager getInstance(Context paramContext) {
    return (WallpaperManager)paramContext.getSystemService("wallpaper");
  }
  
  private static RectF getMaxCropRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2) {
    RectF rectF = new RectF();
    if (paramInt1 / paramInt2 > paramInt3 / paramInt4) {
      rectF.top = 0.0F;
      rectF.bottom = paramInt2;
      paramFloat2 = paramInt3 * paramInt2 / paramInt4;
      rectF.left = (paramInt1 - paramFloat2) * paramFloat1;
      rectF.right = rectF.left + paramFloat2;
    } else {
      rectF.left = 0.0F;
      rectF.right = paramInt1;
      paramFloat1 = paramInt4 * paramInt1 / paramInt3;
      rectF.top = (paramInt2 - paramFloat1) * paramFloat2;
      rectF.bottom = rectF.top + paramFloat1;
    } 
    return rectF;
  }
  
  static void initGlobals(IWallpaperManager paramIWallpaperManager, Looper paramLooper) {
    synchronized (sSync) {
      if (sGlobals == null) {
        Globals globals = new Globals();
        this(paramIWallpaperManager, paramLooper);
        sGlobals = globals;
      } 
      return;
    } 
  }
  
  public static InputStream openDefaultWallpaper(Context paramContext, int paramInt) {
    if (paramInt == 2)
      return null; 
    String str = SystemProperties.get("ro.config.wallpaper");
    if (!TextUtils.isEmpty(str)) {
      File file = new File(str);
      if (file.exists())
        try {
          return new FileInputStream(file);
        } catch (IOException iOException) {} 
    } 
    try {
      return paramContext.getResources().openRawResource(17302154);
    } catch (android.content.res.Resources.NotFoundException notFoundException) {
      return null;
    } 
  }
  
  private final void validateRect(Rect paramRect) {
    if (paramRect == null || !paramRect.isEmpty())
      return; 
    throw new IllegalArgumentException("visibleCrop rectangle must be valid and non-empty");
  }
  
  public void addOnColorsChangedListener(OnColorsChangedListener paramOnColorsChangedListener, Handler paramHandler) {
    addOnColorsChangedListener(paramOnColorsChangedListener, paramHandler, this.mContext.getUserId());
  }
  
  public void addOnColorsChangedListener(OnColorsChangedListener paramOnColorsChangedListener, Handler paramHandler, int paramInt) {
    sGlobals.addOnColorsChangedListener(paramOnColorsChangedListener, paramHandler, paramInt, this.mContext.getDisplayId());
  }
  
  public void clear() throws IOException {
    setStream(openDefaultWallpaper(this.mContext, 1), null, false);
  }
  
  public void clear(int paramInt) throws IOException {
    if ((paramInt & 0x1) != 0)
      clear(); 
    if ((paramInt & 0x2) != 0)
      clearWallpaper(2, this.mContext.getUserId()); 
  }
  
  public void clearWallpaper() {
    clearWallpaper(2, this.mContext.getUserId());
    clearWallpaper(1, this.mContext.getUserId());
  }
  
  @SystemApi
  public void clearWallpaper(int paramInt1, int paramInt2) {
    if (sGlobals.mService != null)
      try {
        sGlobals.mService.clearWallpaper(this.mContext.getOpPackageName(), paramInt1, paramInt2);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public void clearWallpaperOffsets(IBinder paramIBinder) {
    try {
      WindowManagerGlobal.getWindowSession().setWallpaperPosition(paramIBinder, -1.0F, -1.0F, -1.0F, -1.0F);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void forgetLoadedWallpaper() {
    sGlobals.forgetLoadedWallpaper();
  }
  
  public Bitmap getBitmap() {
    return getBitmap(false);
  }
  
  public Bitmap getBitmap(boolean paramBoolean) {
    return getBitmapAsUser(this.mContext.getUserId(), paramBoolean);
  }
  
  public Bitmap getBitmapAsUser(int paramInt, boolean paramBoolean) {
    ColorManagementProxy colorManagementProxy = getColorManagementProxy();
    return sGlobals.peekWallpaperBitmap(this.mContext, true, 1, paramInt, paramBoolean, colorManagementProxy);
  }
  
  public Drawable getBuiltInDrawable() {
    return getBuiltInDrawable(0, 0, false, 0.0F, 0.0F, 1);
  }
  
  public Drawable getBuiltInDrawable(int paramInt) {
    return getBuiltInDrawable(0, 0, false, 0.0F, 0.0F, paramInt);
  }
  
  public Drawable getBuiltInDrawable(int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat1, float paramFloat2) {
    return getBuiltInDrawable(paramInt1, paramInt2, paramBoolean, paramFloat1, paramFloat2, 1);
  }
  
  public Drawable getBuiltInDrawable(int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat1, float paramFloat2, int paramInt3) {
    if (sGlobals.mService != null) {
      if (paramInt3 == 1 || paramInt3 == 2) {
        String str;
        Resources resources = this.mContext.getResources();
        paramFloat1 = Math.max(0.0F, Math.min(1.0F, paramFloat1));
        paramFloat2 = Math.max(0.0F, Math.min(1.0F, paramFloat2));
        InputStream inputStream = openDefaultWallpaper(this.mContext, paramInt3);
        if (inputStream == null) {
          if (DEBUG) {
            str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("default wallpaper stream ");
            stringBuilder.append(paramInt3);
            stringBuilder.append(" is null");
            Log.w(str, stringBuilder.toString());
          } 
          return null;
        } 
        BufferedInputStream bufferedInputStream = new BufferedInputStream((InputStream)str);
        if (paramInt1 <= 0 || paramInt2 <= 0)
          return (Drawable)new BitmapDrawable(resources, BitmapFactory.decodeStream(bufferedInputStream, null, null)); 
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(bufferedInputStream, null, options);
        if (options.outWidth != 0 && options.outHeight != 0) {
          Bitmap bitmap;
          BitmapRegionDecoder bitmapRegionDecoder;
          int i = options.outWidth;
          int j = options.outHeight;
          BufferedInputStream bufferedInputStream1 = new BufferedInputStream(openDefaultWallpaper(this.mContext, paramInt3));
          paramInt1 = Math.min(i, paramInt1);
          int k = Math.min(j, paramInt2);
          if (paramBoolean) {
            rectF = getMaxCropRect(i, j, paramInt1, k, paramFloat1, paramFloat2);
          } else {
            paramInt2 = paramInt1;
            paramFloat1 = (i - paramInt2) * paramFloat1;
            float f = paramInt2;
            paramFloat2 = (j - k) * paramFloat2;
            rectF = new RectF(paramFloat1, paramFloat2, f + paramFloat1, k + paramFloat2);
          } 
          Rect rect = new Rect();
          rectF.roundOut(rect);
          if (rect.width() <= 0 || rect.height() <= 0) {
            Log.w(TAG, "crop has bad values for full size image");
            return null;
          } 
          paramInt2 = Math.min(rect.width() / paramInt1, rect.height() / k);
          options = null;
          try {
            BitmapRegionDecoder bitmapRegionDecoder1 = BitmapRegionDecoder.newInstance(bufferedInputStream1, true);
            bitmapRegionDecoder = bitmapRegionDecoder1;
          } catch (IOException iOException) {
            Log.w(TAG, "cannot open region decoder for default wallpaper");
          } 
          RectF rectF = null;
          if (bitmapRegionDecoder != null) {
            BitmapFactory.Options options1 = new BitmapFactory.Options();
            if (paramInt2 > 1)
              options1.inSampleSize = paramInt2; 
            bitmap = bitmapRegionDecoder.decodeRegion(rect, options1);
            bitmapRegionDecoder.recycle();
          } 
          if (bitmap == null) {
            bufferedInputStream1 = new BufferedInputStream(openDefaultWallpaper(this.mContext, paramInt3));
            BitmapFactory.Options options1 = new BitmapFactory.Options();
            if (paramInt2 > 1)
              options1.inSampleSize = paramInt2; 
            Bitmap bitmap1 = BitmapFactory.decodeStream(bufferedInputStream1, null, options1);
            if (bitmap1 != null)
              bitmap = Bitmap.createBitmap(bitmap1, rect.left, rect.top, rect.width(), rect.height()); 
          } 
          if (bitmap == null) {
            Log.w(TAG, "cannot decode default wallpaper");
            return null;
          } 
          if (paramInt1 > 0 && k > 0 && (bitmap.getWidth() != paramInt1 || bitmap.getHeight() != k)) {
            Matrix matrix = new Matrix();
            RectF rectF2 = new RectF(0.0F, 0.0F, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF1 = new RectF(0.0F, 0.0F, paramInt1, k);
            matrix.setRectToRect(rectF2, rectF1, Matrix.ScaleToFit.FILL);
            Bitmap bitmap1 = Bitmap.createBitmap((int)rectF1.width(), (int)rectF1.height(), Bitmap.Config.ARGB_8888);
            if (bitmap1 != null) {
              Canvas canvas = new Canvas(bitmap1);
              Paint paint = new Paint();
              paint.setFilterBitmap(true);
              canvas.drawBitmap(bitmap, matrix, paint);
              bitmap = bitmap1;
            } 
          } 
          return (Drawable)new BitmapDrawable(resources, bitmap);
        } 
        Log.e(TAG, "default wallpaper dimensions are 0");
        return null;
      } 
      throw new IllegalArgumentException("Must request exactly one kind of wallpaper");
    } 
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public ColorManagementProxy getColorManagementProxy() {
    return this.mCmProxy;
  }
  
  public Intent getCropAndSetWallpaperIntent(Uri paramUri) {
    if (paramUri != null) {
      if ("content".equals(paramUri.getScheme())) {
        PackageManager packageManager = this.mContext.getPackageManager();
        Intent intent = new Intent("android.service.wallpaper.CROP_AND_SET_WALLPAPER", paramUri);
        intent.addFlags(1);
        ResolveInfo resolveInfo = packageManager.resolveActivity((new Intent("android.intent.action.MAIN")).addCategory("android.intent.category.HOME"), 65536);
        if (resolveInfo != null) {
          intent.setPackage(resolveInfo.activityInfo.packageName);
          if (packageManager.queryIntentActivities(intent, 0).size() > 0)
            return intent; 
        } 
        intent.setPackage(this.mContext.getString(17039960));
        if (packageManager.queryIntentActivities(intent, 0).size() > 0)
          return intent; 
        throw new IllegalArgumentException("Cannot use passed URI to set wallpaper; check that the type returned by ContentProvider matches image/*");
      } 
      throw new IllegalArgumentException("Image URI must be of the content scheme type");
    } 
    throw new IllegalArgumentException("Image URI must not be null");
  }
  
  public int getDesiredMinimumHeight() {
    if (sGlobals.mService != null)
      try {
        return sGlobals.mService.getHeightHint(this.mContext.getDisplayId());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public int getDesiredMinimumWidth() {
    if (sGlobals.mService != null)
      try {
        return sGlobals.mService.getWidthHint(this.mContext.getDisplayId());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public Drawable getDrawable() {
    ColorManagementProxy colorManagementProxy = getColorManagementProxy();
    Bitmap bitmap = sGlobals.peekWallpaperBitmap(this.mContext, true, 1, colorManagementProxy);
    if (bitmap != null) {
      BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), bitmap);
      bitmapDrawable.setDither(false);
      return (Drawable)bitmapDrawable;
    } 
    return null;
  }
  
  public Drawable getFastDrawable() {
    ColorManagementProxy colorManagementProxy = getColorManagementProxy();
    Bitmap bitmap = sGlobals.peekWallpaperBitmap(this.mContext, true, 1, colorManagementProxy);
    return (bitmap != null) ? new FastBitmapDrawable(bitmap) : null;
  }
  
  public IWallpaperManager getIWallpaperManager() {
    return sGlobals.mService;
  }
  
  public WallpaperColors getWallpaperColors(int paramInt) {
    return getWallpaperColors(paramInt, this.mContext.getUserId());
  }
  
  public WallpaperColors getWallpaperColors(int paramInt1, int paramInt2) {
    return sGlobals.getWallpaperColors(paramInt1, paramInt2, this.mContext.getDisplayId());
  }
  
  public ParcelFileDescriptor getWallpaperFile(int paramInt) {
    return getWallpaperFile(paramInt, this.mContext.getUserId());
  }
  
  public ParcelFileDescriptor getWallpaperFile(int paramInt1, int paramInt2) {
    if (paramInt1 == 1 || paramInt1 == 2) {
      if (sGlobals.mService != null)
        try {
          Bundle bundle = new Bundle();
          this();
          return sGlobals.mService.getWallpaperWithFeature(this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), null, paramInt1, bundle, paramInt2);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } catch (SecurityException securityException) {
          if ((this.mContext.getApplicationInfo()).targetSdkVersion < 27) {
            Log.w(TAG, "No permission to access wallpaper, suppressing exception to avoid crashing legacy app.");
            return null;
          } 
          throw securityException;
        }  
      Log.w(TAG, "WallpaperService not running");
      throw new RuntimeException(new DeadSystemException());
    } 
    throw new IllegalArgumentException("Must request exactly one kind of wallpaper");
  }
  
  public int getWallpaperId(int paramInt) {
    return getWallpaperIdForUser(paramInt, this.mContext.getUserId());
  }
  
  public int getWallpaperIdForUser(int paramInt1, int paramInt2) {
    try {
      if (sGlobals.mService != null)
        return sGlobals.mService.getWallpaperIdForUser(paramInt1, paramInt2); 
      Log.w(TAG, "WallpaperService not running");
      RuntimeException runtimeException = new RuntimeException();
      DeadSystemException deadSystemException = new DeadSystemException();
      this();
      this((Throwable)deadSystemException);
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public WallpaperInfo getWallpaperInfo() {
    return getWallpaperInfo(this.mContext.getUserId());
  }
  
  public WallpaperInfo getWallpaperInfo(int paramInt) {
    try {
      if (sGlobals.mService != null)
        return sGlobals.mService.getWallpaperInfo(paramInt); 
      Log.w(TAG, "WallpaperService not running");
      RuntimeException runtimeException = new RuntimeException();
      DeadSystemException deadSystemException = new DeadSystemException();
      this();
      this((Throwable)deadSystemException);
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean hasResourceWallpaper(int paramInt) {
    if (sGlobals.mService != null)
      try {
        Resources resources = this.mContext.getResources();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("res:");
        stringBuilder.append(resources.getResourceName(paramInt));
        String str = stringBuilder.toString();
        return sGlobals.mService.hasNamedWallpaper(str);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public boolean isSetWallpaperAllowed() {
    if (sGlobals.mService != null)
      try {
        return sGlobals.mService.isSetWallpaperAllowed(this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public boolean isWallpaperBackupEligible(int paramInt) {
    if (sGlobals.mService != null)
      try {
        return sGlobals.mService.isWallpaperBackupEligible(paramInt, this.mContext.getUserId());
      } catch (RemoteException remoteException) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Exception querying wallpaper backup eligibility: ");
        stringBuilder.append(remoteException.getMessage());
        Log.e(str, stringBuilder.toString());
        return false;
      }  
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public boolean isWallpaperSupported() {
    if (sGlobals.mService != null)
      try {
        return sGlobals.mService.isWallpaperSupported(this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public Drawable peekDrawable() {
    ColorManagementProxy colorManagementProxy = getColorManagementProxy();
    Bitmap bitmap = sGlobals.peekWallpaperBitmap(this.mContext, false, 1, colorManagementProxy);
    if (bitmap != null) {
      BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), bitmap);
      bitmapDrawable.setDither(false);
      return (Drawable)bitmapDrawable;
    } 
    return null;
  }
  
  public Drawable peekFastDrawable() {
    ColorManagementProxy colorManagementProxy = getColorManagementProxy();
    Bitmap bitmap = sGlobals.peekWallpaperBitmap(this.mContext, false, 1, colorManagementProxy);
    return (bitmap != null) ? new FastBitmapDrawable(bitmap) : null;
  }
  
  public void removeOnColorsChangedListener(OnColorsChangedListener paramOnColorsChangedListener) {
    removeOnColorsChangedListener(paramOnColorsChangedListener, this.mContext.getUserId());
  }
  
  public void removeOnColorsChangedListener(OnColorsChangedListener paramOnColorsChangedListener, int paramInt) {
    sGlobals.removeOnColorsChangedListener(paramOnColorsChangedListener, paramInt, this.mContext.getDisplayId());
  }
  
  public void sendWallpaperCommand(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle) {
    try {
      WindowManagerGlobal.getWindowSession().sendWallpaperCommand(paramIBinder, paramString, paramInt1, paramInt2, paramInt3, paramBundle, false);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int setBitmap(Bitmap paramBitmap, Rect paramRect, boolean paramBoolean) throws IOException {
    return setBitmap(paramBitmap, paramRect, paramBoolean, 3);
  }
  
  public int setBitmap(Bitmap paramBitmap, Rect paramRect, boolean paramBoolean, int paramInt) throws IOException {
    return setBitmap(paramBitmap, paramRect, paramBoolean, paramInt, this.mContext.getUserId());
  }
  
  public int setBitmap(Bitmap paramBitmap, Rect paramRect, boolean paramBoolean, int paramInt1, int paramInt2) throws IOException {
    validateRect(paramRect);
    if (sGlobals.mService != null) {
      Bundle bundle = new Bundle();
      WallpaperSetCompletion wallpaperSetCompletion = new WallpaperSetCompletion();
      try {
        ParcelFileDescriptor parcelFileDescriptor = sGlobals.mService.setWallpaper(null, this.mContext.getOpPackageName(), paramRect, paramBoolean, bundle, paramInt1, wallpaperSetCompletion, paramInt2);
        if (parcelFileDescriptor != null) {
          ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
          Rect rect = null;
          paramRect = rect;
          try {
            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream2 = new ParcelFileDescriptor.AutoCloseOutputStream();
            paramRect = rect;
            this(parcelFileDescriptor);
            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream1 = autoCloseOutputStream2;
            autoCloseOutputStream = autoCloseOutputStream1;
            paramBitmap.compress(Bitmap.CompressFormat.PNG, 90, (OutputStream)autoCloseOutputStream1);
            autoCloseOutputStream = autoCloseOutputStream1;
            autoCloseOutputStream1.close();
            autoCloseOutputStream = autoCloseOutputStream1;
            wallpaperSetCompletion.waitForCompletion();
          } finally {
            IoUtils.closeQuietly((AutoCloseable)autoCloseOutputStream);
          } 
        } 
        return bundle.getInt("android.service.wallpaper.extra.ID", 0);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public void setBitmap(Bitmap paramBitmap) throws IOException {
    setBitmap(paramBitmap, null, true);
  }
  
  @SystemApi
  public void setDisplayOffset(IBinder paramIBinder, int paramInt1, int paramInt2) {
    try {
      WindowManagerGlobal.getWindowSession().setWallpaperDisplayOffset(paramIBinder, paramInt1, paramInt2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setDisplayPadding(Rect paramRect) {
    try {
      if (sGlobals.mService != null) {
        sGlobals.mService.setDisplayPadding(paramRect, this.mContext.getOpPackageName(), this.mContext.getDisplayId());
        return;
      } 
      Log.w(TAG, "WallpaperService not running");
      RuntimeException runtimeException = new RuntimeException();
      DeadSystemException deadSystemException = new DeadSystemException();
      this();
      this((Throwable)deadSystemException);
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean setLockWallpaperCallback(IWallpaperManagerCallback paramIWallpaperManagerCallback) {
    if (sGlobals.mService != null)
      try {
        return sGlobals.mService.setLockWallpaperCallback(paramIWallpaperManagerCallback);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public int setResource(int paramInt1, int paramInt2) throws IOException {
    if (sGlobals.mService != null) {
      Bundle bundle = new Bundle();
      WallpaperSetCompletion wallpaperSetCompletion = new WallpaperSetCompletion();
      try {
        Resources resources = this.mContext.getResources();
        IWallpaperManager iWallpaperManager = sGlobals.mService;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("res:");
        stringBuilder.append(resources.getResourceName(paramInt1));
        ParcelFileDescriptor parcelFileDescriptor = iWallpaperManager.setWallpaper(stringBuilder.toString(), this.mContext.getOpPackageName(), null, false, bundle, paramInt2, wallpaperSetCompletion, this.mContext.getUserId());
        if (parcelFileDescriptor != null) {
          ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
          StringBuilder stringBuilder1 = null;
          stringBuilder = stringBuilder1;
          try {
            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream1 = new ParcelFileDescriptor.AutoCloseOutputStream();
            stringBuilder = stringBuilder1;
            this(parcelFileDescriptor);
            autoCloseOutputStream = autoCloseOutputStream1;
            copyStreamToWallpaperFile(resources.openRawResource(paramInt1), (FileOutputStream)autoCloseOutputStream1);
            autoCloseOutputStream = autoCloseOutputStream1;
            autoCloseOutputStream1.close();
            autoCloseOutputStream = autoCloseOutputStream1;
            wallpaperSetCompletion.waitForCompletion();
          } finally {
            IoUtils.closeQuietly((AutoCloseable)autoCloseOutputStream);
          } 
        } 
        return bundle.getInt("android.service.wallpaper.extra.ID", 0);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public void setResource(int paramInt) throws IOException {
    setResource(paramInt, 3);
  }
  
  public int setStream(InputStream paramInputStream, Rect paramRect, boolean paramBoolean) throws IOException {
    return setStream(paramInputStream, paramRect, paramBoolean, 3);
  }
  
  public int setStream(InputStream paramInputStream, Rect paramRect, boolean paramBoolean, int paramInt) throws IOException {
    validateRect(paramRect);
    if (sGlobals.mService != null) {
      Bundle bundle = new Bundle();
      WallpaperSetCompletion wallpaperSetCompletion = new WallpaperSetCompletion();
      try {
        ParcelFileDescriptor parcelFileDescriptor = sGlobals.mService.setWallpaper(null, this.mContext.getOpPackageName(), paramRect, paramBoolean, bundle, paramInt, wallpaperSetCompletion, this.mContext.getUserId());
        if (parcelFileDescriptor != null) {
          ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
          Rect rect = null;
          paramRect = rect;
          try {
            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream1 = new ParcelFileDescriptor.AutoCloseOutputStream();
            paramRect = rect;
            this(parcelFileDescriptor);
            autoCloseOutputStream = autoCloseOutputStream1;
            copyStreamToWallpaperFile(paramInputStream, (FileOutputStream)autoCloseOutputStream1);
            autoCloseOutputStream = autoCloseOutputStream1;
            autoCloseOutputStream1.close();
            autoCloseOutputStream = autoCloseOutputStream1;
            wallpaperSetCompletion.waitForCompletion();
          } finally {
            IoUtils.closeQuietly((AutoCloseable)autoCloseOutputStream);
          } 
        } 
        return bundle.getInt("android.service.wallpaper.extra.ID", 0);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public void setStream(InputStream paramInputStream) throws IOException {
    setStream(paramInputStream, null, true);
  }
  
  @SystemApi
  public boolean setWallpaperComponent(ComponentName paramComponentName) {
    return setWallpaperComponent(paramComponentName, this.mContext.getUserId());
  }
  
  public boolean setWallpaperComponent(ComponentName paramComponentName, int paramInt) {
    if (sGlobals.mService != null)
      try {
        sGlobals.mService.setWallpaperComponentChecked(paramComponentName, this.mContext.getOpPackageName(), paramInt);
        return true;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Log.w(TAG, "WallpaperService not running");
    throw new RuntimeException(new DeadSystemException());
  }
  
  public void setWallpaperOffsetSteps(float paramFloat1, float paramFloat2) {
    this.mWallpaperXStep = paramFloat1;
    this.mWallpaperYStep = paramFloat2;
  }
  
  public void setWallpaperOffsets(IBinder paramIBinder, float paramFloat1, float paramFloat2) {
    try {
      WindowManagerGlobal.getWindowSession().setWallpaperPosition(paramIBinder, paramFloat1, paramFloat2, this.mWallpaperXStep, this.mWallpaperYStep);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setWallpaperZoomOut(IBinder paramIBinder, float paramFloat) {
    if (paramFloat >= 0.0F && paramFloat <= 1.0F)
      try {
        WindowManagerGlobal.getWindowSession().setWallpaperZoomOut(paramIBinder, paramFloat);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("zoom must be between 0 and one: ");
    stringBuilder.append(paramFloat);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean shouldEnableWideColorGamut() {
    return this.mWcgEnabled;
  }
  
  public void suggestDesiredDimensions(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: ldc_w 'sys.max_texture_size'
    //   3: iconst_0
    //   4: invokestatic getInt : (Ljava/lang/String;I)I
    //   7: istore_3
    //   8: goto -> 20
    //   11: astore #4
    //   13: goto -> 167
    //   16: astore #4
    //   18: iconst_0
    //   19: istore_3
    //   20: iload_1
    //   21: istore #5
    //   23: iload_2
    //   24: istore #6
    //   26: iload_3
    //   27: ifle -> 93
    //   30: iload_1
    //   31: iload_3
    //   32: if_icmpgt -> 46
    //   35: iload_1
    //   36: istore #5
    //   38: iload_2
    //   39: istore #6
    //   41: iload_2
    //   42: iload_3
    //   43: if_icmple -> 93
    //   46: iload_2
    //   47: i2f
    //   48: iload_1
    //   49: i2f
    //   50: fdiv
    //   51: fstore #7
    //   53: iload_1
    //   54: iload_2
    //   55: if_icmple -> 77
    //   58: iload_3
    //   59: i2f
    //   60: fload #7
    //   62: fmul
    //   63: f2d
    //   64: ldc2_w 0.5
    //   67: dadd
    //   68: d2i
    //   69: istore #6
    //   71: iload_3
    //   72: istore #5
    //   74: goto -> 93
    //   77: iload_3
    //   78: i2f
    //   79: fload #7
    //   81: fdiv
    //   82: f2d
    //   83: ldc2_w 0.5
    //   86: dadd
    //   87: d2i
    //   88: istore #5
    //   90: iload_3
    //   91: istore #6
    //   93: getstatic android/app/WallpaperManager.sGlobals : Landroid/app/WallpaperManager$Globals;
    //   96: invokestatic access$200 : (Landroid/app/WallpaperManager$Globals;)Landroid/app/IWallpaperManager;
    //   99: ifnull -> 132
    //   102: getstatic android/app/WallpaperManager.sGlobals : Landroid/app/WallpaperManager$Globals;
    //   105: invokestatic access$200 : (Landroid/app/WallpaperManager$Globals;)Landroid/app/IWallpaperManager;
    //   108: iload #5
    //   110: iload #6
    //   112: aload_0
    //   113: getfield mContext : Landroid/content/Context;
    //   116: invokevirtual getOpPackageName : ()Ljava/lang/String;
    //   119: aload_0
    //   120: getfield mContext : Landroid/content/Context;
    //   123: invokevirtual getDisplayId : ()I
    //   126: invokeinterface setDimensionHints : (IILjava/lang/String;I)V
    //   131: return
    //   132: getstatic android/app/WallpaperManager.TAG : Ljava/lang/String;
    //   135: ldc_w 'WallpaperService not running'
    //   138: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   141: pop
    //   142: new java/lang/RuntimeException
    //   145: astore #8
    //   147: new android/os/DeadSystemException
    //   150: astore #4
    //   152: aload #4
    //   154: invokespecial <init> : ()V
    //   157: aload #8
    //   159: aload #4
    //   161: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   164: aload #8
    //   166: athrow
    //   167: aload #4
    //   169: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   172: athrow
    // Exception table:
    //   from	to	target	type
    //   0	8	16	java/lang/Exception
    //   0	8	11	android/os/RemoteException
    //   93	131	11	android/os/RemoteException
    //   132	167	11	android/os/RemoteException
  }
  
  public boolean wallpaperSupportsWcg(int paramInt) {
    boolean bool = shouldEnableWideColorGamut();
    boolean bool1 = false;
    if (!bool)
      return false; 
    ColorManagementProxy colorManagementProxy = getColorManagementProxy();
    Bitmap bitmap = sGlobals.peekWallpaperBitmap(this.mContext, false, paramInt, colorManagementProxy);
    if (bitmap != null && bitmap.getColorSpace() != null && bitmap.getColorSpace() != ColorSpace.get(ColorSpace.Named.SRGB) && colorManagementProxy.isSupportedColorSpace(bitmap.getColorSpace()))
      bool1 = true; 
    return bool1;
  }
  
  public static class ColorManagementProxy {
    private final Set<ColorSpace> mSupportedColorSpaces = new HashSet<>();
    
    public ColorManagementProxy(Context param1Context) {
      Display display = param1Context.getDisplayNoVerify();
      if (display != null)
        this.mSupportedColorSpaces.addAll(Arrays.asList(display.getSupportedWideColorGamut())); 
    }
    
    void doColorManagement(ImageDecoder param1ImageDecoder, ImageDecoder.ImageInfo param1ImageInfo) {
      if (!isSupportedColorSpace(param1ImageInfo.getColorSpace())) {
        param1ImageDecoder.setTargetColorSpace(ColorSpace.get(ColorSpace.Named.SRGB));
        String str = WallpaperManager.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Not supported color space: ");
        stringBuilder.append(param1ImageInfo.getColorSpace());
        Log.w(str, stringBuilder.toString());
      } 
    }
    
    public Set<ColorSpace> getSupportedColorSpaces() {
      return this.mSupportedColorSpaces;
    }
    
    boolean isSupportedColorSpace(ColorSpace param1ColorSpace) {
      boolean bool;
      if (param1ColorSpace != null && (param1ColorSpace == ColorSpace.get(ColorSpace.Named.SRGB) || getSupportedColorSpaces().contains(param1ColorSpace))) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
  }
  
  static class FastBitmapDrawable extends Drawable {
    private final Bitmap mBitmap;
    
    private int mDrawLeft;
    
    private int mDrawTop;
    
    private final int mHeight;
    
    private final Paint mPaint;
    
    private final int mWidth;
    
    private FastBitmapDrawable(Bitmap param1Bitmap) {
      this.mBitmap = param1Bitmap;
      this.mWidth = param1Bitmap.getWidth();
      int i = param1Bitmap.getHeight();
      this.mHeight = i;
      setBounds(0, 0, this.mWidth, i);
      Paint paint = new Paint();
      this.mPaint = paint;
      paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff.Mode.SRC));
    }
    
    public void draw(Canvas param1Canvas) {
      param1Canvas.drawBitmap(this.mBitmap, this.mDrawLeft, this.mDrawTop, this.mPaint);
    }
    
    public int getIntrinsicHeight() {
      return this.mHeight;
    }
    
    public int getIntrinsicWidth() {
      return this.mWidth;
    }
    
    public int getMinimumHeight() {
      return this.mHeight;
    }
    
    public int getMinimumWidth() {
      return this.mWidth;
    }
    
    public int getOpacity() {
      return -1;
    }
    
    public void setAlpha(int param1Int) {
      throw new UnsupportedOperationException("Not supported with this drawable");
    }
    
    public void setBounds(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      this.mDrawLeft = (param1Int3 - param1Int1 - this.mWidth) / 2 + param1Int1;
      this.mDrawTop = (param1Int4 - param1Int2 - this.mHeight) / 2 + param1Int2;
    }
    
    public void setColorFilter(ColorFilter param1ColorFilter) {
      throw new UnsupportedOperationException("Not supported with this drawable");
    }
    
    public void setDither(boolean param1Boolean) {
      throw new UnsupportedOperationException("Not supported with this drawable");
    }
    
    public void setFilterBitmap(boolean param1Boolean) {
      throw new UnsupportedOperationException("Not supported with this drawable");
    }
  }
  
  private static class Globals extends IWallpaperManagerCallback.Stub {
    private Bitmap mCachedWallpaper;
    
    private int mCachedWallpaperUserId;
    
    private boolean mColorCallbackRegistered;
    
    private final ArrayList<Pair<WallpaperManager.OnColorsChangedListener, Handler>> mColorListeners = new ArrayList<>();
    
    private Bitmap mDefaultWallpaper;
    
    private Handler mMainLooperHandler;
    
    private final IWallpaperManager mService;
    
    Globals(IWallpaperManager param1IWallpaperManager, Looper param1Looper) {
      this.mService = param1IWallpaperManager;
      this.mMainLooperHandler = new Handler(param1Looper);
      forgetLoadedWallpaper();
    }
    
    private Bitmap getCurrentWallpaperLocked(Context param1Context, int param1Int, boolean param1Boolean, WallpaperManager.ColorManagementProxy param1ColorManagementProxy) {
      if (this.mService == null) {
        Log.w(WallpaperManager.TAG, "WallpaperService not running");
        return null;
      } 
      try {
        Bundle bundle = new Bundle();
        this();
        ParcelFileDescriptor parcelFileDescriptor = this.mService.getWallpaperWithFeature(param1Context.getOpPackageName(), param1Context.getAttributionTag(), this, 1, bundle, param1Int);
        if (parcelFileDescriptor != null)
          try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream();
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream();
            this(parcelFileDescriptor);
            this((InputStream)autoCloseInputStream);
            try {
              ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
              this();
              while (true) {
                param1Int = bufferedInputStream.read();
                if (param1Int != -1) {
                  byteArrayOutputStream.write(param1Int);
                  continue;
                } 
                ImageDecoder.Source source = ImageDecoder.createSource(byteArrayOutputStream.toByteArray());
                _$$Lambda$WallpaperManager$Globals$alsWlyseh5qEzEBKjJOHGd0GnHk _$$Lambda$WallpaperManager$Globals$alsWlyseh5qEzEBKjJOHGd0GnHk = new _$$Lambda$WallpaperManager$Globals$alsWlyseh5qEzEBKjJOHGd0GnHk();
                this(param1Boolean, param1ColorManagementProxy);
                return ImageDecoder.decodeBitmap(source, _$$Lambda$WallpaperManager$Globals$alsWlyseh5qEzEBKjJOHGd0GnHk);
              } 
            } finally {
              try {
                bufferedInputStream.close();
              } finally {
                bufferedInputStream = null;
              } 
            } 
          } catch (OutOfMemoryError|IOException outOfMemoryError) {
            Log.w(WallpaperManager.TAG, "Can't decode file", outOfMemoryError);
          }  
        return null;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    private Bitmap getDefaultWallpaper(Context param1Context, int param1Int) {
      InputStream inputStream = WallpaperManager.openDefaultWallpaper(param1Context, param1Int);
      if (inputStream != null)
        try {
          BitmapFactory.Options options = new BitmapFactory.Options();
          this();
          Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
          IoUtils.closeQuietly(inputStream);
          return bitmap;
        } catch (OutOfMemoryError outOfMemoryError) {
          Log.w(WallpaperManager.TAG, "Can't decode stream", outOfMemoryError);
          IoUtils.closeQuietly(inputStream);
        } finally {
          Exception exception;
        }  
      return null;
    }
    
    public void addOnColorsChangedListener(WallpaperManager.OnColorsChangedListener param1OnColorsChangedListener, Handler param1Handler, int param1Int1, int param1Int2) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mColorCallbackRegistered : Z
      //   6: istore #5
      //   8: iload #5
      //   10: ifne -> 47
      //   13: aload_0
      //   14: getfield mService : Landroid/app/IWallpaperManager;
      //   17: aload_0
      //   18: iload_3
      //   19: iload #4
      //   21: invokeinterface registerWallpaperColorsCallback : (Landroid/app/IWallpaperManagerCallback;II)V
      //   26: aload_0
      //   27: iconst_1
      //   28: putfield mColorCallbackRegistered : Z
      //   31: goto -> 47
      //   34: astore #6
      //   36: invokestatic access$000 : ()Ljava/lang/String;
      //   39: ldc 'Can't register for color updates'
      //   41: aload #6
      //   43: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   46: pop
      //   47: aload_0
      //   48: getfield mColorListeners : Ljava/util/ArrayList;
      //   51: astore #6
      //   53: new android/util/Pair
      //   56: astore #7
      //   58: aload #7
      //   60: aload_1
      //   61: aload_2
      //   62: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
      //   65: aload #6
      //   67: aload #7
      //   69: invokevirtual add : (Ljava/lang/Object;)Z
      //   72: pop
      //   73: aload_0
      //   74: monitorexit
      //   75: return
      //   76: astore_1
      //   77: aload_0
      //   78: monitorexit
      //   79: aload_1
      //   80: athrow
      // Exception table:
      //   from	to	target	type
      //   2	8	76	finally
      //   13	31	34	android/os/RemoteException
      //   13	31	76	finally
      //   36	47	76	finally
      //   47	75	76	finally
      //   77	79	76	finally
    }
    
    void forgetLoadedWallpaper() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: aconst_null
      //   4: putfield mCachedWallpaper : Landroid/graphics/Bitmap;
      //   7: aload_0
      //   8: iconst_0
      //   9: putfield mCachedWallpaperUserId : I
      //   12: aload_0
      //   13: aconst_null
      //   14: putfield mDefaultWallpaper : Landroid/graphics/Bitmap;
      //   17: aload_0
      //   18: monitorexit
      //   19: return
      //   20: astore_1
      //   21: aload_0
      //   22: monitorexit
      //   23: aload_1
      //   24: athrow
      // Exception table:
      //   from	to	target	type
      //   2	19	20	finally
      //   21	23	20	finally
    }
    
    WallpaperColors getWallpaperColors(int param1Int1, int param1Int2, int param1Int3) {
      if (param1Int1 == 2 || param1Int1 == 1)
        try {
          return this.mService.getWallpaperColors(param1Int1, param1Int2, param1Int3);
        } catch (RemoteException remoteException) {
          return null;
        }  
      throw new IllegalArgumentException("Must request colors for exactly one kind of wallpaper");
    }
    
    public void onWallpaperChanged() {
      forgetLoadedWallpaper();
    }
    
    public void onWallpaperColorsChanged(WallpaperColors param1WallpaperColors, int param1Int1, int param1Int2) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mColorListeners : Ljava/util/ArrayList;
      //   6: invokevirtual iterator : ()Ljava/util/Iterator;
      //   9: astore #4
      //   11: aload #4
      //   13: invokeinterface hasNext : ()Z
      //   18: ifeq -> 87
      //   21: aload #4
      //   23: invokeinterface next : ()Ljava/lang/Object;
      //   28: checkcast android/util/Pair
      //   31: astore #5
      //   33: aload #5
      //   35: getfield second : Ljava/lang/Object;
      //   38: checkcast android/os/Handler
      //   41: astore #6
      //   43: aload #5
      //   45: getfield second : Ljava/lang/Object;
      //   48: ifnonnull -> 60
      //   51: aload_0
      //   52: getfield mMainLooperHandler : Landroid/os/Handler;
      //   55: astore #6
      //   57: goto -> 60
      //   60: new android/app/_$$Lambda$WallpaperManager$Globals$1AcnQUORvPlCjJoNqdxfQT4o4Nw
      //   63: astore #7
      //   65: aload #7
      //   67: aload_0
      //   68: aload #5
      //   70: aload_1
      //   71: iload_2
      //   72: iload_3
      //   73: invokespecial <init> : (Landroid/app/WallpaperManager$Globals;Landroid/util/Pair;Landroid/app/WallpaperColors;II)V
      //   76: aload #6
      //   78: aload #7
      //   80: invokevirtual post : (Ljava/lang/Runnable;)Z
      //   83: pop
      //   84: goto -> 11
      //   87: aload_0
      //   88: monitorexit
      //   89: return
      //   90: astore_1
      //   91: aload_0
      //   92: monitorexit
      //   93: aload_1
      //   94: athrow
      // Exception table:
      //   from	to	target	type
      //   2	11	90	finally
      //   11	57	90	finally
      //   60	84	90	finally
      //   87	89	90	finally
      //   91	93	90	finally
    }
    
    public Bitmap peekWallpaperBitmap(Context param1Context, boolean param1Boolean1, int param1Int1, int param1Int2, boolean param1Boolean2, WallpaperManager.ColorManagementProxy param1ColorManagementProxy) {
      // Byte code:
      //   0: aload_0
      //   1: getfield mService : Landroid/app/IWallpaperManager;
      //   4: astore #7
      //   6: aload #7
      //   8: ifnull -> 40
      //   11: aload #7
      //   13: aload_1
      //   14: invokevirtual getOpPackageName : ()Ljava/lang/String;
      //   17: invokeinterface isWallpaperSupported : (Ljava/lang/String;)Z
      //   22: istore #8
      //   24: iload #8
      //   26: ifne -> 31
      //   29: aconst_null
      //   30: areturn
      //   31: goto -> 40
      //   34: astore_1
      //   35: aload_1
      //   36: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
      //   39: athrow
      //   40: aload_0
      //   41: monitorenter
      //   42: aload_0
      //   43: getfield mCachedWallpaper : Landroid/graphics/Bitmap;
      //   46: ifnull -> 77
      //   49: aload_0
      //   50: getfield mCachedWallpaperUserId : I
      //   53: iload #4
      //   55: if_icmpne -> 77
      //   58: aload_0
      //   59: getfield mCachedWallpaper : Landroid/graphics/Bitmap;
      //   62: invokevirtual isRecycled : ()Z
      //   65: ifne -> 77
      //   68: aload_0
      //   69: getfield mCachedWallpaper : Landroid/graphics/Bitmap;
      //   72: astore_1
      //   73: aload_0
      //   74: monitorexit
      //   75: aload_1
      //   76: areturn
      //   77: aload_0
      //   78: aconst_null
      //   79: putfield mCachedWallpaper : Landroid/graphics/Bitmap;
      //   82: aload_0
      //   83: iconst_0
      //   84: putfield mCachedWallpaperUserId : I
      //   87: aload_0
      //   88: aload_0
      //   89: aload_1
      //   90: iload #4
      //   92: iload #5
      //   94: aload #6
      //   96: invokespecial getCurrentWallpaperLocked : (Landroid/content/Context;IZLandroid/app/WallpaperManager$ColorManagementProxy;)Landroid/graphics/Bitmap;
      //   99: putfield mCachedWallpaper : Landroid/graphics/Bitmap;
      //   102: aload_0
      //   103: iload #4
      //   105: putfield mCachedWallpaperUserId : I
      //   108: goto -> 186
      //   111: astore #6
      //   113: aload_1
      //   114: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
      //   117: getfield targetSdkVersion : I
      //   120: bipush #27
      //   122: if_icmpge -> 138
      //   125: invokestatic access$000 : ()Ljava/lang/String;
      //   128: ldc_w 'No permission to access wallpaper, suppressing exception to avoid crashing legacy app.'
      //   131: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
      //   134: pop
      //   135: goto -> 186
      //   138: aload #6
      //   140: athrow
      //   141: astore #9
      //   143: invokestatic access$000 : ()Ljava/lang/String;
      //   146: astore #7
      //   148: new java/lang/StringBuilder
      //   151: astore #6
      //   153: aload #6
      //   155: invokespecial <init> : ()V
      //   158: aload #6
      //   160: ldc_w 'Out of memory loading the current wallpaper: '
      //   163: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   166: pop
      //   167: aload #6
      //   169: aload #9
      //   171: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   174: pop
      //   175: aload #7
      //   177: aload #6
      //   179: invokevirtual toString : ()Ljava/lang/String;
      //   182: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
      //   185: pop
      //   186: aload_0
      //   187: getfield mCachedWallpaper : Landroid/graphics/Bitmap;
      //   190: ifnull -> 202
      //   193: aload_0
      //   194: getfield mCachedWallpaper : Landroid/graphics/Bitmap;
      //   197: astore_1
      //   198: aload_0
      //   199: monitorexit
      //   200: aload_1
      //   201: areturn
      //   202: aload_0
      //   203: monitorexit
      //   204: iload_2
      //   205: ifeq -> 252
      //   208: aload_0
      //   209: getfield mDefaultWallpaper : Landroid/graphics/Bitmap;
      //   212: astore #7
      //   214: aload #7
      //   216: astore #6
      //   218: aload #7
      //   220: ifnonnull -> 249
      //   223: aload_0
      //   224: aload_1
      //   225: iload_3
      //   226: invokespecial getDefaultWallpaper : (Landroid/content/Context;I)Landroid/graphics/Bitmap;
      //   229: astore #6
      //   231: aload_0
      //   232: monitorenter
      //   233: aload_0
      //   234: aload #6
      //   236: putfield mDefaultWallpaper : Landroid/graphics/Bitmap;
      //   239: aload_0
      //   240: monitorexit
      //   241: goto -> 249
      //   244: astore_1
      //   245: aload_0
      //   246: monitorexit
      //   247: aload_1
      //   248: athrow
      //   249: aload #6
      //   251: areturn
      //   252: aconst_null
      //   253: areturn
      //   254: astore_1
      //   255: aload_0
      //   256: monitorexit
      //   257: aload_1
      //   258: athrow
      // Exception table:
      //   from	to	target	type
      //   11	24	34	android/os/RemoteException
      //   42	75	254	finally
      //   77	87	254	finally
      //   87	108	141	java/lang/OutOfMemoryError
      //   87	108	111	java/lang/SecurityException
      //   87	108	254	finally
      //   113	135	254	finally
      //   138	141	254	finally
      //   143	186	254	finally
      //   186	200	254	finally
      //   202	204	254	finally
      //   233	241	244	finally
      //   245	247	244	finally
      //   255	257	254	finally
    }
    
    public Bitmap peekWallpaperBitmap(Context param1Context, boolean param1Boolean, int param1Int, WallpaperManager.ColorManagementProxy param1ColorManagementProxy) {
      return peekWallpaperBitmap(param1Context, param1Boolean, param1Int, param1Context.getUserId(), false, param1ColorManagementProxy);
    }
    
    public void removeOnColorsChangedListener(WallpaperManager.OnColorsChangedListener param1OnColorsChangedListener, int param1Int1, int param1Int2) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mColorListeners : Ljava/util/ArrayList;
      //   6: astore #4
      //   8: new android/app/_$$Lambda$WallpaperManager$Globals$2yG7V1sbMECCnlFTLyjKWKqNoYI
      //   11: astore #5
      //   13: aload #5
      //   15: aload_1
      //   16: invokespecial <init> : (Landroid/app/WallpaperManager$OnColorsChangedListener;)V
      //   19: aload #4
      //   21: aload #5
      //   23: invokevirtual removeIf : (Ljava/util/function/Predicate;)Z
      //   26: pop
      //   27: aload_0
      //   28: getfield mColorListeners : Ljava/util/ArrayList;
      //   31: invokevirtual size : ()I
      //   34: ifne -> 76
      //   37: aload_0
      //   38: getfield mColorCallbackRegistered : Z
      //   41: ifeq -> 76
      //   44: aload_0
      //   45: iconst_0
      //   46: putfield mColorCallbackRegistered : Z
      //   49: aload_0
      //   50: getfield mService : Landroid/app/IWallpaperManager;
      //   53: aload_0
      //   54: iload_2
      //   55: iload_3
      //   56: invokeinterface unregisterWallpaperColorsCallback : (Landroid/app/IWallpaperManagerCallback;II)V
      //   61: goto -> 76
      //   64: astore_1
      //   65: invokestatic access$000 : ()Ljava/lang/String;
      //   68: ldc_w 'Can't unregister color updates'
      //   71: aload_1
      //   72: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   75: pop
      //   76: aload_0
      //   77: monitorexit
      //   78: return
      //   79: astore_1
      //   80: aload_0
      //   81: monitorexit
      //   82: aload_1
      //   83: athrow
      // Exception table:
      //   from	to	target	type
      //   2	49	79	finally
      //   49	61	64	android/os/RemoteException
      //   49	61	79	finally
      //   65	76	79	finally
      //   76	78	79	finally
      //   80	82	79	finally
    }
  }
  
  public static interface OnColorsChangedListener {
    void onColorsChanged(WallpaperColors param1WallpaperColors, int param1Int);
    
    default void onColorsChanged(WallpaperColors param1WallpaperColors, int param1Int1, int param1Int2) {
      onColorsChanged(param1WallpaperColors, param1Int1);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SetWallpaperFlags {}
  
  private class WallpaperSetCompletion extends IWallpaperManagerCallback.Stub {
    final CountDownLatch mLatch = new CountDownLatch(1);
    
    public void onWallpaperChanged() throws RemoteException {
      this.mLatch.countDown();
    }
    
    public void onWallpaperColorsChanged(WallpaperColors param1WallpaperColors, int param1Int1, int param1Int2) throws RemoteException {
      WallpaperManager.sGlobals.onWallpaperColorsChanged(param1WallpaperColors, param1Int1, param1Int2);
    }
    
    public void waitForCompletion() {
      try {
        this.mLatch.await(30L, TimeUnit.SECONDS);
      } catch (InterruptedException interruptedException) {}
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WallpaperManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */