package android.graphics;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.TypedValue;
import java.io.IOException;

public class AssetInputStreamSource extends ImageDecoder.Source {
  private AssetManager.AssetInputStream mAssetInputStream;
  
  private final int mDensity;
  
  private final Resources mResources;
  
  public AssetInputStreamSource(AssetManager.AssetInputStream paramAssetInputStream, Resources paramResources, TypedValue paramTypedValue) {
    this.mAssetInputStream = paramAssetInputStream;
    this.mResources = paramResources;
    if (paramTypedValue.density == 0) {
      this.mDensity = 160;
    } else if (paramTypedValue.density != 65535) {
      this.mDensity = paramTypedValue.density;
    } else {
      this.mDensity = 0;
    } 
  }
  
  public ImageDecoder createImageDecoder(boolean paramBoolean) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mAssetInputStream : Landroid/content/res/AssetManager$AssetInputStream;
    //   6: ifnull -> 30
    //   9: aload_0
    //   10: getfield mAssetInputStream : Landroid/content/res/AssetManager$AssetInputStream;
    //   13: astore_2
    //   14: aload_0
    //   15: aconst_null
    //   16: putfield mAssetInputStream : Landroid/content/res/AssetManager$AssetInputStream;
    //   19: aload_2
    //   20: iload_1
    //   21: aload_0
    //   22: invokestatic access$500 : (Landroid/content/res/AssetManager$AssetInputStream;ZLandroid/graphics/ImageDecoder$Source;)Landroid/graphics/ImageDecoder;
    //   25: astore_2
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_2
    //   29: areturn
    //   30: new java/io/IOException
    //   33: astore_2
    //   34: aload_2
    //   35: ldc 'Cannot reuse AssetInputStreamSource'
    //   37: invokespecial <init> : (Ljava/lang/String;)V
    //   40: aload_2
    //   41: athrow
    //   42: astore_2
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_2
    //   46: athrow
    // Exception table:
    //   from	to	target	type
    //   2	28	42	finally
    //   30	42	42	finally
    //   43	45	42	finally
  }
  
  public int getDensity() {
    return this.mDensity;
  }
  
  public Resources getResources() {
    return this.mResources;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$AssetInputStreamSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */