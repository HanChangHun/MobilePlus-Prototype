package android.graphics;

import android.content.res.Resources;
import java.io.IOException;
import java.io.InputStream;

class InputStreamSource extends ImageDecoder.Source {
  final int mInputDensity;
  
  InputStream mInputStream;
  
  final Resources mResources;
  
  InputStreamSource(Resources paramResources, InputStream paramInputStream, int paramInt) {
    if (paramInputStream != null) {
      this.mResources = paramResources;
      this.mInputStream = paramInputStream;
      this.mInputDensity = paramInt;
      return;
    } 
    throw new IllegalArgumentException("The InputStream cannot be null");
  }
  
  public ImageDecoder createImageDecoder(boolean paramBoolean) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mInputStream : Ljava/io/InputStream;
    //   6: ifnull -> 31
    //   9: aload_0
    //   10: getfield mInputStream : Ljava/io/InputStream;
    //   13: astore_2
    //   14: aload_0
    //   15: aconst_null
    //   16: putfield mInputStream : Ljava/io/InputStream;
    //   19: aload_2
    //   20: iconst_0
    //   21: iload_1
    //   22: aload_0
    //   23: invokestatic access$300 : (Ljava/io/InputStream;ZZLandroid/graphics/ImageDecoder$Source;)Landroid/graphics/ImageDecoder;
    //   26: astore_2
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_2
    //   30: areturn
    //   31: new java/io/IOException
    //   34: astore_2
    //   35: aload_2
    //   36: ldc 'Cannot reuse InputStreamSource'
    //   38: invokespecial <init> : (Ljava/lang/String;)V
    //   41: aload_2
    //   42: athrow
    //   43: astore_2
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_2
    //   47: athrow
    // Exception table:
    //   from	to	target	type
    //   2	29	43	finally
    //   31	43	43	finally
    //   44	46	43	finally
  }
  
  public int getDensity() {
    return this.mInputDensity;
  }
  
  public Resources getResources() {
    return this.mResources;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$InputStreamSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */