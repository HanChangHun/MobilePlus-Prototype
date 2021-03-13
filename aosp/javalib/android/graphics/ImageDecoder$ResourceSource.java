package android.graphics;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.TypedValue;
import java.io.IOException;
import java.io.InputStream;

class ResourceSource extends ImageDecoder.Source {
  private Object mLock = new Object();
  
  int mResDensity;
  
  final int mResId;
  
  final Resources mResources;
  
  ResourceSource(Resources paramResources, int paramInt) {
    this.mResources = paramResources;
    this.mResId = paramInt;
    this.mResDensity = 0;
  }
  
  public ImageDecoder createImageDecoder(boolean paramBoolean) throws IOException {
    null = new TypedValue();
    InputStream inputStream = this.mResources.openRawResource(this.mResId, null);
    synchronized (this.mLock) {
      if (null.density == 0) {
        this.mResDensity = 160;
      } else if (null.density != 65535) {
        this.mResDensity = null.density;
      } 
      return ImageDecoder.access$500((AssetManager.AssetInputStream)inputStream, paramBoolean, this);
    } 
  }
  
  public int getDensity() {
    synchronized (this.mLock) {
      return this.mResDensity;
    } 
  }
  
  public Resources getResources() {
    return this.mResources;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$ResourceSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */