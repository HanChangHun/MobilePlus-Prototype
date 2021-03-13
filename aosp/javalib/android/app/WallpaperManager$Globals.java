package android.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import libcore.io.IoUtils;

class Globals extends IWallpaperManagerCallback.Stub {
  private Bitmap mCachedWallpaper;
  
  private int mCachedWallpaperUserId;
  
  private boolean mColorCallbackRegistered;
  
  private final ArrayList<Pair<WallpaperManager.OnColorsChangedListener, Handler>> mColorListeners = new ArrayList<>();
  
  private Bitmap mDefaultWallpaper;
  
  private Handler mMainLooperHandler;
  
  private final IWallpaperManager mService;
  
  Globals(IWallpaperManager paramIWallpaperManager, Looper paramLooper) {
    this.mService = paramIWallpaperManager;
    this.mMainLooperHandler = new Handler(paramLooper);
    forgetLoadedWallpaper();
  }
  
  private Bitmap getCurrentWallpaperLocked(Context paramContext, int paramInt, boolean paramBoolean, WallpaperManager.ColorManagementProxy paramColorManagementProxy) {
    if (this.mService == null) {
      Log.w(WallpaperManager.access$000(), "WallpaperService not running");
      return null;
    } 
    try {
      Bundle bundle = new Bundle();
      this();
      ParcelFileDescriptor parcelFileDescriptor = this.mService.getWallpaperWithFeature(paramContext.getOpPackageName(), paramContext.getAttributionTag(), this, 1, bundle, paramInt);
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
              paramInt = bufferedInputStream.read();
              if (paramInt != -1) {
                byteArrayOutputStream.write(paramInt);
                continue;
              } 
              ImageDecoder.Source source = ImageDecoder.createSource(byteArrayOutputStream.toByteArray());
              _$$Lambda$WallpaperManager$Globals$alsWlyseh5qEzEBKjJOHGd0GnHk _$$Lambda$WallpaperManager$Globals$alsWlyseh5qEzEBKjJOHGd0GnHk = new _$$Lambda$WallpaperManager$Globals$alsWlyseh5qEzEBKjJOHGd0GnHk();
              this(paramBoolean, paramColorManagementProxy);
              return ImageDecoder.decodeBitmap(source, _$$Lambda$WallpaperManager$Globals$alsWlyseh5qEzEBKjJOHGd0GnHk);
            } 
          } finally {
            try {
              bufferedInputStream.close();
            } finally {
              bufferedInputStream = null;
            } 
          } 
        } catch (OutOfMemoryError|java.io.IOException outOfMemoryError) {
          Log.w(WallpaperManager.access$000(), "Can't decode file", outOfMemoryError);
        }  
      return null;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private Bitmap getDefaultWallpaper(Context paramContext, int paramInt) {
    InputStream inputStream = WallpaperManager.openDefaultWallpaper(paramContext, paramInt);
    if (inputStream != null)
      try {
        BitmapFactory.Options options = new BitmapFactory.Options();
        this();
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
        IoUtils.closeQuietly(inputStream);
        return bitmap;
      } catch (OutOfMemoryError outOfMemoryError) {
        Log.w(WallpaperManager.access$000(), "Can't decode stream", outOfMemoryError);
        IoUtils.closeQuietly(inputStream);
      } finally {
        Exception exception;
      }  
    return null;
  }
  
  public void addOnColorsChangedListener(WallpaperManager.OnColorsChangedListener paramOnColorsChangedListener, Handler paramHandler, int paramInt1, int paramInt2) {
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
  
  WallpaperColors getWallpaperColors(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt1 == 2 || paramInt1 == 1)
      try {
        return this.mService.getWallpaperColors(paramInt1, paramInt2, paramInt3);
      } catch (RemoteException remoteException) {
        return null;
      }  
    throw new IllegalArgumentException("Must request colors for exactly one kind of wallpaper");
  }
  
  public void onWallpaperChanged() {
    forgetLoadedWallpaper();
  }
  
  public void onWallpaperColorsChanged(WallpaperColors paramWallpaperColors, int paramInt1, int paramInt2) {
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
  
  public Bitmap peekWallpaperBitmap(Context paramContext, boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2, WallpaperManager.ColorManagementProxy paramColorManagementProxy) {
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
  
  public Bitmap peekWallpaperBitmap(Context paramContext, boolean paramBoolean, int paramInt, WallpaperManager.ColorManagementProxy paramColorManagementProxy) {
    return peekWallpaperBitmap(paramContext, paramBoolean, paramInt, paramContext.getUserId(), false, paramColorManagementProxy);
  }
  
  public void removeOnColorsChangedListener(WallpaperManager.OnColorsChangedListener paramOnColorsChangedListener, int paramInt1, int paramInt2) {
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


/* Location:              /home/chun/Desktop/temp/!/android/app/WallpaperManager$Globals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */