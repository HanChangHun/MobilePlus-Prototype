package android.app;

import android.content.res.ApkAssets;
import android.util.ArrayMap;
import java.io.IOException;

class ApkAssetsSupplier {
  final ArrayMap<ResourcesManager.ApkKey, ApkAssets> mLocalCache = new ArrayMap();
  
  private ApkAssetsSupplier() {}
  
  ApkAssets load(ResourcesManager.ApkKey paramApkKey) throws IOException {
    ApkAssets apkAssets1 = (ApkAssets)this.mLocalCache.get(paramApkKey);
    ApkAssets apkAssets2 = apkAssets1;
    if (apkAssets1 == null) {
      apkAssets2 = ResourcesManager.access$000(ResourcesManager.this, paramApkKey);
      this.mLocalCache.put(paramApkKey, apkAssets2);
    } 
    return apkAssets2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ResourcesManager$ApkAssetsSupplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */