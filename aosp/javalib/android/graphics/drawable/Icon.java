package android.graphics.drawable;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;

public final class Icon implements Parcelable {
  public static final Parcelable.Creator<Icon> CREATOR;
  
  static final BlendMode DEFAULT_BLEND_MODE = Drawable.DEFAULT_BLEND_MODE;
  
  public static final int MIN_ASHMEM_ICON_SIZE = 131072;
  
  private static final String TAG = "Icon";
  
  public static final int TYPE_ADAPTIVE_BITMAP = 5;
  
  public static final int TYPE_BITMAP = 1;
  
  public static final int TYPE_DATA = 3;
  
  public static final int TYPE_RESOURCE = 2;
  
  public static final int TYPE_URI = 4;
  
  public static final int TYPE_URI_ADAPTIVE_BITMAP = 6;
  
  private static final int VERSION_STREAM_SERIALIZER = 1;
  
  private BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
  
  private int mInt1;
  
  private int mInt2;
  
  private Object mObj1;
  
  private String mString1;
  
  private ColorStateList mTintList;
  
  private final int mType;
  
  static {
    CREATOR = new Parcelable.Creator<Icon>() {
        public Icon createFromParcel(Parcel param1Parcel) {
          return new Icon(param1Parcel);
        }
        
        public Icon[] newArray(int param1Int) {
          return new Icon[param1Int];
        }
      };
  }
  
  private Icon(int paramInt) {
    this.mType = paramInt;
  }
  
  private Icon(Parcel paramParcel) {
    this(paramParcel.readInt());
    StringBuilder stringBuilder;
    int i;
    byte[] arrayOfByte;
    String str;
    switch (this.mType) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("invalid ");
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" type in parcel: ");
        stringBuilder.append(this.mType);
        throw new RuntimeException(stringBuilder.toString());
      case 4:
      case 6:
        this.mString1 = stringBuilder.readString();
        break;
      case 3:
        i = stringBuilder.readInt();
        arrayOfByte = stringBuilder.readBlob();
        if (i == arrayOfByte.length) {
          this.mInt1 = i;
          this.mObj1 = arrayOfByte;
          break;
        } 
        stringBuilder = new StringBuilder();
        stringBuilder.append("internal unparceling error: blob length (");
        stringBuilder.append(arrayOfByte.length);
        stringBuilder.append(") != expected length (");
        stringBuilder.append(i);
        stringBuilder.append(")");
        throw new RuntimeException(stringBuilder.toString());
      case 2:
        str = stringBuilder.readString();
        i = stringBuilder.readInt();
        this.mString1 = str;
        this.mInt1 = i;
        break;
      case 1:
      case 5:
        this.mObj1 = Bitmap.CREATOR.createFromParcel((Parcel)stringBuilder);
        break;
    } 
    if (stringBuilder.readInt() == 1)
      this.mTintList = (ColorStateList)ColorStateList.CREATOR.createFromParcel((Parcel)stringBuilder); 
    this.mBlendMode = BlendMode.fromValue(stringBuilder.readInt());
  }
  
  public static Icon createFromStream(InputStream paramInputStream) throws IOException {
    paramInputStream = new DataInputStream(paramInputStream);
    if (paramInputStream.readInt() >= 1) {
      int i;
      byte[] arrayOfByte;
      switch (paramInputStream.readByte()) {
        default:
          return null;
        case 6:
          return createWithAdaptiveBitmapContentUri(paramInputStream.readUTF());
        case 5:
          return createWithAdaptiveBitmap(BitmapFactory.decodeStream(paramInputStream));
        case 4:
          return createWithContentUri(paramInputStream.readUTF());
        case 3:
          i = paramInputStream.readInt();
          arrayOfByte = new byte[i];
          paramInputStream.read(arrayOfByte, 0, i);
          return createWithData(arrayOfByte, 0, i);
        case 2:
          return createWithResource(paramInputStream.readUTF(), paramInputStream.readInt());
        case 1:
          break;
      } 
      return createWithBitmap(BitmapFactory.decodeStream(paramInputStream));
    } 
  }
  
  public static Icon createWithAdaptiveBitmap(Bitmap paramBitmap) {
    if (paramBitmap != null) {
      Icon icon = new Icon(5);
      icon.setBitmap(paramBitmap);
      return icon;
    } 
    throw new IllegalArgumentException("Bitmap must not be null.");
  }
  
  public static Icon createWithAdaptiveBitmapContentUri(Uri paramUri) {
    if (paramUri != null)
      return createWithAdaptiveBitmapContentUri(paramUri.toString()); 
    throw new IllegalArgumentException("Uri must not be null.");
  }
  
  public static Icon createWithAdaptiveBitmapContentUri(String paramString) {
    if (paramString != null) {
      Icon icon = new Icon(6);
      icon.mString1 = paramString;
      return icon;
    } 
    throw new IllegalArgumentException("Uri must not be null.");
  }
  
  public static Icon createWithBitmap(Bitmap paramBitmap) {
    if (paramBitmap != null) {
      Icon icon = new Icon(1);
      icon.setBitmap(paramBitmap);
      return icon;
    } 
    throw new IllegalArgumentException("Bitmap must not be null.");
  }
  
  public static Icon createWithContentUri(Uri paramUri) {
    if (paramUri != null)
      return createWithContentUri(paramUri.toString()); 
    throw new IllegalArgumentException("Uri must not be null.");
  }
  
  public static Icon createWithContentUri(String paramString) {
    if (paramString != null) {
      Icon icon = new Icon(4);
      icon.mString1 = paramString;
      return icon;
    } 
    throw new IllegalArgumentException("Uri must not be null.");
  }
  
  public static Icon createWithData(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramArrayOfbyte != null) {
      Icon icon = new Icon(3);
      icon.mObj1 = paramArrayOfbyte;
      icon.mInt1 = paramInt2;
      icon.mInt2 = paramInt1;
      return icon;
    } 
    throw new IllegalArgumentException("Data must not be null.");
  }
  
  public static Icon createWithFilePath(String paramString) {
    if (paramString != null) {
      Icon icon = new Icon(4);
      icon.mString1 = paramString;
      return icon;
    } 
    throw new IllegalArgumentException("Path must not be null.");
  }
  
  public static Icon createWithResource(Context paramContext, int paramInt) {
    if (paramContext != null) {
      Icon icon = new Icon(2);
      icon.mInt1 = paramInt;
      icon.mString1 = paramContext.getPackageName();
      return icon;
    } 
    throw new IllegalArgumentException("Context must not be null.");
  }
  
  public static Icon createWithResource(Resources paramResources, int paramInt) {
    if (paramResources != null) {
      Icon icon = new Icon(2);
      icon.mInt1 = paramInt;
      icon.mString1 = paramResources.getResourcePackageName(paramInt);
      return icon;
    } 
    throw new IllegalArgumentException("Resource must not be null.");
  }
  
  public static Icon createWithResource(String paramString, int paramInt) {
    if (paramString != null) {
      Icon icon = new Icon(2);
      icon.mInt1 = paramInt;
      icon.mString1 = paramString;
      return icon;
    } 
    throw new IllegalArgumentException("Resource package name must not be null.");
  }
  
  private InputStream getUriInputStream(Context paramContext) {
    Uri uri = getUri();
    String str = uri.getScheme();
    if ("content".equals(str) || "file".equals(str)) {
      try {
        return paramContext.getContentResolver().openInputStream(uri);
      } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to load image from URI: ");
        stringBuilder.append(uri);
        Log.w("Icon", stringBuilder.toString(), exception);
      } 
      return null;
    } 
    try {
      File file = new File();
      this(this.mString1);
      return new FileInputStream(file);
    } catch (FileNotFoundException fileNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to load image from path: ");
      stringBuilder.append(uri);
      Log.w("Icon", stringBuilder.toString(), fileNotFoundException);
    } 
    return null;
  }
  
  private Drawable loadDrawableInner(Context paramContext) {
    InputStream inputStream;
    switch (this.mType) {
      default:
        return null;
      case 6:
        inputStream = getUriInputStream(paramContext);
        if (inputStream != null)
          return new AdaptiveIconDrawable(null, new BitmapDrawable(paramContext.getResources(), BitmapFactory.decodeStream(inputStream))); 
      case 5:
        return new AdaptiveIconDrawable(null, new BitmapDrawable(paramContext.getResources(), getBitmap()));
      case 4:
        inputStream = getUriInputStream(paramContext);
        if (inputStream != null)
          return new BitmapDrawable(paramContext.getResources(), BitmapFactory.decodeStream(inputStream)); 
      case 3:
        return new BitmapDrawable(paramContext.getResources(), BitmapFactory.decodeByteArray(getDataBytes(), getDataOffset(), getDataLength()));
      case 2:
        if (getResources() == null) {
          String str2 = getResPackage();
          String str1 = str2;
          if (TextUtils.isEmpty(str2))
            str1 = paramContext.getPackageName(); 
          if ("android".equals(str1)) {
            this.mObj1 = Resources.getSystem();
          } else {
            PackageManager packageManager = paramContext.getPackageManager();
            try {
              ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str1, 8192);
              if (applicationInfo != null) {
                this.mObj1 = packageManager.getResourcesForApplication(applicationInfo);
              } else {
              
              } 
            } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
              Log.e("Icon", String.format("Unable to find pkg=%s for icon %s", new Object[] { str1, this }), (Throwable)nameNotFoundException);
            } 
          } 
        } 
        try {
          return getResources().getDrawable(getResId(), nameNotFoundException.getTheme());
        } catch (RuntimeException runtimeException) {
          Log.e("Icon", String.format("Unable to load resource 0x%08x from pkg=%s", new Object[] { Integer.valueOf(getResId()), getResPackage() }), runtimeException);
        } 
      case 1:
        break;
    } 
    return new BitmapDrawable(runtimeException.getResources(), getBitmap());
  }
  
  public static Bitmap scaleDownIfNecessary(Bitmap paramBitmap, int paramInt1, int paramInt2) {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    if (i <= paramInt1) {
      Bitmap bitmap = paramBitmap;
      if (j > paramInt2) {
        float f1 = Math.min(paramInt1 / i, paramInt2 / j);
        return Bitmap.createScaledBitmap(paramBitmap, Math.max(1, (int)(i * f1)), Math.max(1, (int)(j * f1)), true);
      } 
      return bitmap;
    } 
    float f = Math.min(paramInt1 / i, paramInt2 / j);
    return Bitmap.createScaledBitmap(paramBitmap, Math.max(1, (int)(i * f)), Math.max(1, (int)(j * f)), true);
  }
  
  private void setBitmap(Bitmap paramBitmap) {
    this.mObj1 = paramBitmap;
  }
  
  private static final String typeToString(int paramInt) {
    switch (paramInt) {
      default:
        return "UNKNOWN";
      case 6:
        return "URI_MASKABLE";
      case 5:
        return "BITMAP_MASKABLE";
      case 4:
        return "URI";
      case 3:
        return "DATA";
      case 2:
        return "RESOURCE";
      case 1:
        break;
    } 
    return "BITMAP";
  }
  
  public void convertToAshmem() {
    int i = this.mType;
    if ((i == 1 || i == 5) && getBitmap().isMutable() && getBitmap().getAllocationByteCount() >= 131072)
      setBitmap(getBitmap().createAshmemBitmap()); 
  }
  
  public int describeContents() {
    int i = this.mType;
    boolean bool = true;
    if (i != 1 && i != 5 && i != 3)
      bool = false; 
    return bool;
  }
  
  public Bitmap getBitmap() {
    int i = this.mType;
    if (i == 1 || i == 5)
      return (Bitmap)this.mObj1; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("called getBitmap() on ");
    stringBuilder.append(this);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public byte[] getDataBytes() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mType : I
    //   4: iconst_3
    //   5: if_icmpne -> 27
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield mObj1 : Ljava/lang/Object;
    //   14: checkcast [B
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: areturn
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: athrow
    //   27: new java/lang/StringBuilder
    //   30: dup
    //   31: invokespecial <init> : ()V
    //   34: astore_1
    //   35: aload_1
    //   36: ldc_w 'called getDataBytes() on '
    //   39: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: aload_1
    //   44: aload_0
    //   45: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: new java/lang/IllegalStateException
    //   52: dup
    //   53: aload_1
    //   54: invokevirtual toString : ()Ljava/lang/String;
    //   57: invokespecial <init> : (Ljava/lang/String;)V
    //   60: athrow
    // Exception table:
    //   from	to	target	type
    //   10	20	22	finally
    //   23	25	22	finally
  }
  
  public int getDataLength() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mType : I
    //   4: iconst_3
    //   5: if_icmpne -> 24
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield mInt1 : I
    //   14: istore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: iload_1
    //   18: ireturn
    //   19: astore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: athrow
    //   24: new java/lang/StringBuilder
    //   27: dup
    //   28: invokespecial <init> : ()V
    //   31: astore_2
    //   32: aload_2
    //   33: ldc_w 'called getDataLength() on '
    //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload_2
    //   41: aload_0
    //   42: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: new java/lang/IllegalStateException
    //   49: dup
    //   50: aload_2
    //   51: invokevirtual toString : ()Ljava/lang/String;
    //   54: invokespecial <init> : (Ljava/lang/String;)V
    //   57: athrow
    // Exception table:
    //   from	to	target	type
    //   10	17	19	finally
    //   20	22	19	finally
  }
  
  public int getDataOffset() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mType : I
    //   4: iconst_3
    //   5: if_icmpne -> 24
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield mInt2 : I
    //   14: istore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: iload_1
    //   18: ireturn
    //   19: astore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: athrow
    //   24: new java/lang/StringBuilder
    //   27: dup
    //   28: invokespecial <init> : ()V
    //   31: astore_2
    //   32: aload_2
    //   33: ldc_w 'called getDataOffset() on '
    //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload_2
    //   41: aload_0
    //   42: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: new java/lang/IllegalStateException
    //   49: dup
    //   50: aload_2
    //   51: invokevirtual toString : ()Ljava/lang/String;
    //   54: invokespecial <init> : (Ljava/lang/String;)V
    //   57: athrow
    // Exception table:
    //   from	to	target	type
    //   10	17	19	finally
    //   20	22	19	finally
  }
  
  public int getResId() {
    if (this.mType == 2)
      return this.mInt1; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("called getResId() on ");
    stringBuilder.append(this);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public String getResPackage() {
    if (this.mType == 2)
      return this.mString1; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("called getResPackage() on ");
    stringBuilder.append(this);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public Resources getResources() {
    if (this.mType == 2)
      return (Resources)this.mObj1; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("called getResources() on ");
    stringBuilder.append(this);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public int getType() {
    return this.mType;
  }
  
  public Uri getUri() {
    return Uri.parse(getUriString());
  }
  
  public String getUriString() {
    int i = this.mType;
    if (i == 4 || i == 6)
      return this.mString1; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("called getUriString() on ");
    stringBuilder.append(this);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public boolean hasTint() {
    return (this.mTintList != null || this.mBlendMode != DEFAULT_BLEND_MODE);
  }
  
  public Drawable loadDrawable(Context paramContext) {
    Drawable drawable = loadDrawableInner(paramContext);
    if (drawable != null && (this.mTintList != null || this.mBlendMode != DEFAULT_BLEND_MODE)) {
      drawable.mutate();
      drawable.setTintList(this.mTintList);
      drawable.setTintBlendMode(this.mBlendMode);
    } 
    return drawable;
  }
  
  public Drawable loadDrawableAsUser(Context paramContext, int paramInt) {
    if (this.mType == 2) {
      String str1 = getResPackage();
      String str2 = str1;
      if (TextUtils.isEmpty(str1))
        str2 = paramContext.getPackageName(); 
      if (getResources() == null && !getResPackage().equals("android")) {
        PackageManager packageManager = paramContext.getPackageManager();
        try {
          this.mObj1 = packageManager.getResourcesForApplicationAsUser(str2, paramInt);
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
          Log.e("Icon", String.format("Unable to find pkg=%s user=%d", new Object[] { getResPackage(), Integer.valueOf(paramInt) }), (Throwable)nameNotFoundException);
        } 
      } 
    } 
    return loadDrawable(paramContext);
  }
  
  public void loadDrawableAsync(Context paramContext, OnDrawableLoadedListener paramOnDrawableLoadedListener, Handler paramHandler) {
    (new LoadDrawableTask(paramContext, paramHandler, paramOnDrawableLoadedListener)).runAsync();
  }
  
  public void loadDrawableAsync(Context paramContext, Message paramMessage) {
    if (paramMessage.getTarget() != null) {
      (new LoadDrawableTask(paramContext, paramMessage)).runAsync();
      return;
    } 
    throw new IllegalArgumentException("callback message must have a target handler");
  }
  
  public boolean sameAs(Icon paramIcon) {
    boolean bool1 = true;
    boolean bool2 = true;
    boolean bool3 = true;
    if (paramIcon == this)
      return true; 
    if (this.mType != paramIcon.getType())
      return false; 
    switch (this.mType) {
      default:
        return false;
      case 4:
      case 6:
        return Objects.equals(getUriString(), paramIcon.getUriString());
      case 3:
        if (getDataLength() != paramIcon.getDataLength() || getDataOffset() != paramIcon.getDataOffset() || !Arrays.equals(getDataBytes(), paramIcon.getDataBytes()))
          bool3 = false; 
        return bool3;
      case 2:
        if (getResId() == paramIcon.getResId() && Objects.equals(getResPackage(), paramIcon.getResPackage())) {
          bool3 = bool1;
        } else {
          bool3 = false;
        } 
        return bool3;
      case 1:
      case 5:
        break;
    } 
    if (getBitmap() == paramIcon.getBitmap()) {
      bool3 = bool2;
    } else {
      bool3 = false;
    } 
    return bool3;
  }
  
  public void scaleDownIfNecessary(int paramInt1, int paramInt2) {
    int i = this.mType;
    if (i != 1 && i != 5)
      return; 
    setBitmap(scaleDownIfNecessary(getBitmap(), paramInt1, paramInt2));
  }
  
  public Icon setTint(int paramInt) {
    return setTintList(ColorStateList.valueOf(paramInt));
  }
  
  public Icon setTintBlendMode(BlendMode paramBlendMode) {
    this.mBlendMode = paramBlendMode;
    return this;
  }
  
  public Icon setTintList(ColorStateList paramColorStateList) {
    this.mTintList = paramColorStateList;
    return this;
  }
  
  public Icon setTintMode(PorterDuff.Mode paramMode) {
    this.mBlendMode = BlendMode.fromValue(paramMode.nativeInt);
    return this;
  }
  
  public String toString() {
    StringBuilder stringBuilder = (new StringBuilder("Icon(typ=")).append(typeToString(this.mType));
    switch (this.mType) {
      case 4:
      case 6:
        stringBuilder.append(" uri=");
        stringBuilder.append(getUriString());
        break;
      case 3:
        stringBuilder.append(" len=");
        stringBuilder.append(getDataLength());
        if (getDataOffset() != 0) {
          stringBuilder.append(" off=");
          stringBuilder.append(getDataOffset());
        } 
        break;
      case 2:
        stringBuilder.append(" pkg=");
        stringBuilder.append(getResPackage());
        stringBuilder.append(" id=");
        stringBuilder.append(String.format("0x%08x", new Object[] { Integer.valueOf(getResId()) }));
        break;
      case 1:
      case 5:
        stringBuilder.append(" size=");
        stringBuilder.append(getBitmap().getWidth());
        stringBuilder.append("x");
        stringBuilder.append(getBitmap().getHeight());
        break;
    } 
    if (this.mTintList != null) {
      stringBuilder.append(" tint=");
      String str = "";
      int[] arrayOfInt = this.mTintList.getColors();
      int i = arrayOfInt.length;
      for (byte b = 0; b < i; b++) {
        stringBuilder.append(String.format("%s0x%08x", new Object[] { str, Integer.valueOf(arrayOfInt[b]) }));
        str = "|";
      } 
    } 
    if (this.mBlendMode != DEFAULT_BLEND_MODE) {
      stringBuilder.append(" mode=");
      stringBuilder.append(this.mBlendMode);
    } 
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mType);
    switch (this.mType) {
      case 4:
      case 6:
        paramParcel.writeString(getUriString());
        break;
      case 3:
        paramParcel.writeInt(getDataLength());
        paramParcel.writeBlob(getDataBytes(), getDataOffset(), getDataLength());
        break;
      case 2:
        paramParcel.writeString(getResPackage());
        paramParcel.writeInt(getResId());
        break;
      case 1:
      case 5:
        getBitmap();
        getBitmap().writeToParcel(paramParcel, paramInt);
        break;
    } 
    if (this.mTintList == null) {
      paramParcel.writeInt(0);
    } else {
      paramParcel.writeInt(1);
      this.mTintList.writeToParcel(paramParcel, paramInt);
    } 
    paramParcel.writeInt(BlendMode.toValue(this.mBlendMode));
  }
  
  public void writeToStream(OutputStream paramOutputStream) throws IOException {
    paramOutputStream = new DataOutputStream(paramOutputStream);
    paramOutputStream.writeInt(1);
    paramOutputStream.writeByte(this.mType);
    switch (this.mType) {
      default:
        return;
      case 4:
      case 6:
        paramOutputStream.writeUTF(getUriString());
      case 3:
        paramOutputStream.writeInt(getDataLength());
        paramOutputStream.write(getDataBytes(), getDataOffset(), getDataLength());
      case 2:
        paramOutputStream.writeUTF(getResPackage());
        paramOutputStream.writeInt(getResId());
      case 1:
      case 5:
        break;
    } 
    getBitmap().compress(Bitmap.CompressFormat.PNG, 100, paramOutputStream);
  }
  
  public static @interface IconType {}
  
  private class LoadDrawableTask implements Runnable {
    final Context mContext;
    
    final Message mMessage;
    
    public LoadDrawableTask(Context param1Context, Handler param1Handler, final Icon.OnDrawableLoadedListener listener) {
      this.mContext = param1Context;
      this.mMessage = Message.obtain(param1Handler, new Runnable() {
            public void run() {
              listener.onDrawableLoaded((Drawable)Icon.LoadDrawableTask.this.mMessage.obj);
            }
          });
    }
    
    public LoadDrawableTask(Context param1Context, Message param1Message) {
      this.mContext = param1Context;
      this.mMessage = param1Message;
    }
    
    public void run() {
      this.mMessage.obj = Icon.this.loadDrawable(this.mContext);
      this.mMessage.sendToTarget();
    }
    
    public void runAsync() {
      AsyncTask.THREAD_POOL_EXECUTOR.execute(this);
    }
  }
  
  class null implements Runnable {
    public void run() {
      listener.onDrawableLoaded((Drawable)this.this$1.mMessage.obj);
    }
  }
  
  public static interface OnDrawableLoadedListener {
    void onDrawableLoaded(Drawable param1Drawable);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/Icon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */