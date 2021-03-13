package android.content.res.loader;

import android.content.res.AssetFileDescriptor;

public interface AssetsProvider {
  default AssetFileDescriptor loadAssetFd(String paramString, int paramInt) {
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/loader/AssetsProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */