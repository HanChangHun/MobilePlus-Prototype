package android.graphics;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Trace;
import android.util.Log;
import android.util.TypedValue;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapFactory {
  private static final int DECODE_BUFFER_SIZE = 16384;
  
  public static Bitmap decodeByteArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return decodeByteArray(paramArrayOfbyte, paramInt1, paramInt2, null);
  }
  
  public static Bitmap decodeByteArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, Options paramOptions) {
    if ((paramInt1 | paramInt2) >= 0 && paramArrayOfbyte.length >= paramInt1 + paramInt2) {
      Options.validate(paramOptions);
      Trace.traceBegin(2L, "decodeBitmap");
      try {
        Bitmap bitmap = nativeDecodeByteArray(paramArrayOfbyte, paramInt1, paramInt2, paramOptions, Options.nativeInBitmap(paramOptions), Options.nativeColorSpace(paramOptions));
        if (bitmap != null || paramOptions == null || paramOptions.inBitmap == null) {
          setDensityFromOptions(bitmap, paramOptions);
          return bitmap;
        } 
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        this("Problem decoding into existing bitmap");
        throw illegalArgumentException;
      } finally {
        Trace.traceEnd(2L);
      } 
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public static Bitmap decodeFile(String paramString) {
    return decodeFile(paramString, null);
  }
  
  public static Bitmap decodeFile(String paramString, Options paramOptions) {
    Bitmap bitmap;
    Options.validate(paramOptions);
    Exception exception = null;
    StringBuilder stringBuilder1 = null;
    StringBuilder stringBuilder2 = null;
    FileInputStream fileInputStream1 = null;
    FileInputStream fileInputStream2 = null;
    FileInputStream fileInputStream3 = fileInputStream2;
    FileInputStream fileInputStream4 = fileInputStream1;
    try {
      FileInputStream fileInputStream6 = new FileInputStream();
      fileInputStream3 = fileInputStream2;
      fileInputStream4 = fileInputStream1;
      this(paramString);
      FileInputStream fileInputStream5 = fileInputStream6;
      fileInputStream3 = fileInputStream5;
      fileInputStream4 = fileInputStream5;
      Bitmap bitmap1 = decodeStream(fileInputStream5, null, paramOptions);
      bitmap = bitmap1;
      bitmap1 = bitmap;
      try {
        fileInputStream5.close();
        Bitmap bitmap2 = bitmap;
      } catch (IOException iOException) {
        Bitmap bitmap2 = bitmap1;
      } 
    } catch (Exception exception1) {
      Bitmap bitmap1 = bitmap;
      StringBuilder stringBuilder = new StringBuilder();
      bitmap1 = bitmap;
      this();
      bitmap1 = bitmap;
      stringBuilder.append("Unable to decode stream: ");
      bitmap1 = bitmap;
      stringBuilder.append(exception1);
      bitmap1 = bitmap;
      Log.e("BitmapFactory", stringBuilder.toString());
      stringBuilder = stringBuilder1;
      if (bitmap != null) {
        exception1 = exception;
        bitmap.close();
        stringBuilder = stringBuilder2;
      } 
    } finally {}
    return (Bitmap)paramString;
  }
  
  public static Bitmap decodeFileDescriptor(FileDescriptor paramFileDescriptor) {
    return decodeFileDescriptor(paramFileDescriptor, null, null);
  }
  
  public static Bitmap decodeFileDescriptor(FileDescriptor paramFileDescriptor, Rect paramRect, Options paramOptions) {
    // Byte code:
    //   0: aload_2
    //   1: invokestatic validate : (Landroid/graphics/BitmapFactory$Options;)V
    //   4: ldc2_w 2
    //   7: ldc 'decodeFileDescriptor'
    //   9: invokestatic traceBegin : (JLjava/lang/String;)V
    //   12: aload_0
    //   13: invokestatic nativeIsSeekable : (Ljava/io/FileDescriptor;)Z
    //   16: ifeq -> 37
    //   19: aload_0
    //   20: aload_1
    //   21: aload_2
    //   22: aload_2
    //   23: invokestatic nativeInBitmap : (Landroid/graphics/BitmapFactory$Options;)J
    //   26: aload_2
    //   27: invokestatic nativeColorSpace : (Landroid/graphics/BitmapFactory$Options;)J
    //   30: invokestatic nativeDecodeFileDescriptor : (Ljava/io/FileDescriptor;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;JJ)Landroid/graphics/Bitmap;
    //   33: astore_0
    //   34: goto -> 61
    //   37: new java/io/FileInputStream
    //   40: astore_3
    //   41: aload_3
    //   42: aload_0
    //   43: invokespecial <init> : (Ljava/io/FileDescriptor;)V
    //   46: aload_3
    //   47: aload_1
    //   48: aload_2
    //   49: invokestatic decodeStreamInternal : (Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   52: astore_0
    //   53: aload_3
    //   54: invokevirtual close : ()V
    //   57: goto -> 61
    //   60: astore_1
    //   61: aload_0
    //   62: ifnonnull -> 91
    //   65: aload_2
    //   66: ifnull -> 91
    //   69: aload_2
    //   70: getfield inBitmap : Landroid/graphics/Bitmap;
    //   73: ifnonnull -> 79
    //   76: goto -> 91
    //   79: new java/lang/IllegalArgumentException
    //   82: astore_0
    //   83: aload_0
    //   84: ldc 'Problem decoding into existing bitmap'
    //   86: invokespecial <init> : (Ljava/lang/String;)V
    //   89: aload_0
    //   90: athrow
    //   91: aload_0
    //   92: aload_2
    //   93: invokestatic setDensityFromOptions : (Landroid/graphics/Bitmap;Landroid/graphics/BitmapFactory$Options;)V
    //   96: ldc2_w 2
    //   99: invokestatic traceEnd : (J)V
    //   102: aload_0
    //   103: areturn
    //   104: astore_0
    //   105: aload_3
    //   106: invokevirtual close : ()V
    //   109: goto -> 116
    //   112: astore_1
    //   113: goto -> 109
    //   116: aload_0
    //   117: athrow
    //   118: astore_0
    //   119: ldc2_w 2
    //   122: invokestatic traceEnd : (J)V
    //   125: aload_0
    //   126: athrow
    // Exception table:
    //   from	to	target	type
    //   12	19	118	finally
    //   19	34	118	finally
    //   37	46	118	finally
    //   46	53	104	finally
    //   53	57	60	finally
    //   69	76	118	finally
    //   79	91	118	finally
    //   91	96	118	finally
    //   105	109	112	finally
    //   116	118	118	finally
  }
  
  public static Bitmap decodeResource(Resources paramResources, int paramInt) {
    return decodeResource(paramResources, paramInt, null);
  }
  
  public static Bitmap decodeResource(Resources paramResources, int paramInt, Options paramOptions) {
    Options.validate(paramOptions);
    Exception exception1 = null;
    Exception exception2 = null;
    InputStream inputStream1 = null;
    InputStream inputStream2 = null;
    InputStream inputStream3 = inputStream2;
    InputStream inputStream4 = inputStream1;
    try {
      TypedValue typedValue = new TypedValue();
      inputStream3 = inputStream2;
      inputStream4 = inputStream1;
      this();
      inputStream3 = inputStream2;
      inputStream4 = inputStream1;
      inputStream2 = paramResources.openRawResource(paramInt, typedValue);
      inputStream3 = inputStream2;
      inputStream4 = inputStream2;
      Bitmap bitmap1 = decodeResourceStream(paramResources, typedValue, inputStream2, null, paramOptions);
      Bitmap bitmap2 = bitmap1;
      bitmap1 = bitmap2;
    } catch (Exception exception) {
      exception = exception1;
    } finally {
      if (inputStream3 != null)
        try {
          inputStream3.close();
        } catch (IOException iOException1) {} 
    } 
    if (paramResources != null || iOException1 == null || ((Options)iOException1).inBitmap == null)
      return (Bitmap)paramResources; 
    throw new IllegalArgumentException("Problem decoding into existing bitmap");
  }
  
  public static Bitmap decodeResourceStream(Resources paramResources, TypedValue paramTypedValue, InputStream paramInputStream, Rect paramRect, Options paramOptions) {
    Options.validate(paramOptions);
    Options options = paramOptions;
    if (paramOptions == null)
      options = new Options(); 
    if (options.inDensity == 0 && paramTypedValue != null) {
      int i = paramTypedValue.density;
      if (i == 0) {
        options.inDensity = 160;
      } else if (i != 65535) {
        options.inDensity = i;
      } 
    } 
    if (options.inTargetDensity == 0 && paramResources != null)
      options.inTargetDensity = (paramResources.getDisplayMetrics()).densityDpi; 
    return decodeStream(paramInputStream, paramRect, options);
  }
  
  public static Bitmap decodeStream(InputStream paramInputStream) {
    return decodeStream(paramInputStream, null, null);
  }
  
  public static Bitmap decodeStream(InputStream paramInputStream, Rect paramRect, Options paramOptions) {
    if (paramInputStream == null)
      return null; 
    Options.validate(paramOptions);
    Trace.traceBegin(2L, "decodeBitmap");
    try {
      Bitmap bitmap;
      if (paramInputStream instanceof AssetManager.AssetInputStream) {
        bitmap = nativeDecodeAsset(((AssetManager.AssetInputStream)paramInputStream).getNativeAsset(), paramRect, paramOptions, Options.nativeInBitmap(paramOptions), Options.nativeColorSpace(paramOptions));
      } else {
        bitmap = decodeStreamInternal((InputStream)bitmap, paramRect, paramOptions);
      } 
      if (bitmap != null || paramOptions == null || paramOptions.inBitmap == null) {
        setDensityFromOptions(bitmap, paramOptions);
        return bitmap;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this("Problem decoding into existing bitmap");
      throw illegalArgumentException;
    } finally {
      Trace.traceEnd(2L);
    } 
  }
  
  private static Bitmap decodeStreamInternal(InputStream paramInputStream, Rect paramRect, Options paramOptions) {
    byte[] arrayOfByte1 = null;
    if (paramOptions != null)
      arrayOfByte1 = paramOptions.inTempStorage; 
    byte[] arrayOfByte2 = arrayOfByte1;
    if (arrayOfByte1 == null)
      arrayOfByte2 = new byte[16384]; 
    return nativeDecodeStream(paramInputStream, arrayOfByte2, paramRect, paramOptions, Options.nativeInBitmap(paramOptions), Options.nativeColorSpace(paramOptions));
  }
  
  private static native Bitmap nativeDecodeAsset(long paramLong1, Rect paramRect, Options paramOptions, long paramLong2, long paramLong3);
  
  private static native Bitmap nativeDecodeByteArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, Options paramOptions, long paramLong1, long paramLong2);
  
  private static native Bitmap nativeDecodeFileDescriptor(FileDescriptor paramFileDescriptor, Rect paramRect, Options paramOptions, long paramLong1, long paramLong2);
  
  private static native Bitmap nativeDecodeStream(InputStream paramInputStream, byte[] paramArrayOfbyte, Rect paramRect, Options paramOptions, long paramLong1, long paramLong2);
  
  private static native boolean nativeIsSeekable(FileDescriptor paramFileDescriptor);
  
  private static void setDensityFromOptions(Bitmap paramBitmap, Options paramOptions) {
    if (paramBitmap == null || paramOptions == null)
      return; 
    int i = paramOptions.inDensity;
    if (i != 0) {
      paramBitmap.setDensity(i);
      int j = paramOptions.inTargetDensity;
      if (j == 0 || i == j || i == paramOptions.inScreenDensity)
        return; 
      byte[] arrayOfByte = paramBitmap.getNinePatchChunk();
      if (arrayOfByte != null && NinePatch.isNinePatchChunk(arrayOfByte)) {
        i = 1;
      } else {
        i = 0;
      } 
      if (paramOptions.inScaled || i != 0)
        paramBitmap.setDensity(j); 
    } else if (paramOptions.inBitmap != null) {
      paramBitmap.setDensity(Bitmap.getDefaultDensity());
    } 
  }
  
  public static class Options {
    public Bitmap inBitmap;
    
    public int inDensity;
    
    public boolean inDither;
    
    @Deprecated
    public boolean inInputShareable;
    
    public boolean inJustDecodeBounds;
    
    public boolean inMutable;
    
    @Deprecated
    public boolean inPreferQualityOverSpeed;
    
    public ColorSpace inPreferredColorSpace = null;
    
    public Bitmap.Config inPreferredConfig = Bitmap.Config.ARGB_8888;
    
    public boolean inPremultiplied = true;
    
    @Deprecated
    public boolean inPurgeable;
    
    public int inSampleSize;
    
    public boolean inScaled = true;
    
    public int inScreenDensity;
    
    public int inTargetDensity;
    
    public byte[] inTempStorage;
    
    @Deprecated
    public boolean mCancel;
    
    public ColorSpace outColorSpace;
    
    public Bitmap.Config outConfig;
    
    public int outHeight;
    
    public String outMimeType;
    
    public int outWidth;
    
    static long nativeColorSpace(Options param1Options) {
      if (param1Options != null) {
        ColorSpace colorSpace = param1Options.inPreferredColorSpace;
        if (colorSpace != null)
          return colorSpace.getNativeInstance(); 
      } 
      return 0L;
    }
    
    static long nativeInBitmap(Options param1Options) {
      if (param1Options != null) {
        Bitmap bitmap = param1Options.inBitmap;
        if (bitmap != null)
          return bitmap.getNativeInstance(); 
      } 
      return 0L;
    }
    
    static void validate(Options param1Options) {
      if (param1Options == null)
        return; 
      Bitmap bitmap = param1Options.inBitmap;
      if (bitmap != null)
        if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
          if (param1Options.inBitmap.isRecycled())
            throw new IllegalArgumentException("Cannot reuse a recycled Bitmap"); 
        } else {
          throw new IllegalArgumentException("Bitmaps with Config.HARDWARE are always immutable");
        }  
      if (!param1Options.inMutable || param1Options.inPreferredConfig != Bitmap.Config.HARDWARE) {
        ColorSpace colorSpace = param1Options.inPreferredColorSpace;
        if (colorSpace != null)
          if (colorSpace instanceof ColorSpace.Rgb) {
            if (((ColorSpace.Rgb)colorSpace).getTransferParameters() == null)
              throw new IllegalArgumentException("The destination color space must use an ICC parametric transfer function"); 
          } else {
            throw new IllegalArgumentException("The destination color space must use the RGB color model");
          }  
        return;
      } 
      throw new IllegalArgumentException("Bitmaps with Config.HARDWARE cannot be decoded into - they are immutable");
    }
    
    @Deprecated
    public void requestCancelDecode() {
      this.mCancel = true;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/BitmapFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */