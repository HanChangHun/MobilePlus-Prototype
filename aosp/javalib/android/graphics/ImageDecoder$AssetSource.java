package android.graphics;

import android.content.res.AssetManager;
import java.io.IOException;

class AssetSource extends ImageDecoder.Source {
  private final AssetManager mAssets;
  
  private final String mFileName;
  
  AssetSource(AssetManager paramAssetManager, String paramString) {
    this.mAssets = paramAssetManager;
    this.mFileName = paramString;
  }
  
  public ImageDecoder createImageDecoder(boolean paramBoolean) throws IOException {
    return ImageDecoder.access$500((AssetManager.AssetInputStream)this.mAssets.open(this.mFileName), paramBoolean, this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$AssetSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */