package android.content.res;

import android.content.res.loader.ResourcesLoader;
import android.util.ArraySet;
import java.util.ArrayList;
import java.util.List;

public class Builder {
  private ArrayList<ResourcesLoader> mLoaders = new ArrayList<>();
  
  private ArrayList<ApkAssets> mUserApkAssets = new ArrayList<>();
  
  public Builder addApkAssets(ApkAssets paramApkAssets) {
    this.mUserApkAssets.add(paramApkAssets);
    return this;
  }
  
  public Builder addLoader(ResourcesLoader paramResourcesLoader) {
    this.mLoaders.add(paramResourcesLoader);
    return this;
  }
  
  public AssetManager build() {
    ResourcesLoader[] arrayOfResourcesLoader;
    ApkAssets[] arrayOfApkAssets1 = AssetManager.getSystem().getApkAssets();
    ArrayList<ApkAssets> arrayList = new ArrayList();
    ArraySet arraySet = new ArraySet();
    int i;
    for (i = this.mLoaders.size() - 1; i >= 0; i--) {
      List<ApkAssets> list = ((ResourcesLoader)this.mLoaders.get(i)).getApkAssets();
      for (int k = list.size() - 1; k >= 0; k--) {
        ApkAssets apkAssets = list.get(k);
        if (arraySet.add(apkAssets))
          arrayList.add(0, apkAssets); 
      } 
    } 
    ApkAssets[] arrayOfApkAssets2 = new ApkAssets[arrayOfApkAssets1.length + this.mUserApkAssets.size() + arrayList.size()];
    System.arraycopy(arrayOfApkAssets1, 0, arrayOfApkAssets2, 0, arrayOfApkAssets1.length);
    i = 0;
    int j = this.mUserApkAssets.size();
    while (i < j) {
      arrayOfApkAssets2[arrayOfApkAssets1.length + i] = this.mUserApkAssets.get(i);
      i++;
    } 
    i = 0;
    j = arrayList.size();
    while (i < j) {
      arrayOfApkAssets2[arrayOfApkAssets1.length + i + this.mUserApkAssets.size()] = arrayList.get(i);
      i++;
    } 
    arrayList = null;
    AssetManager assetManager = new AssetManager(false, null);
    AssetManager.access$102(assetManager, arrayOfApkAssets2);
    AssetManager.access$300(AssetManager.access$200(assetManager), arrayOfApkAssets2, false);
    if (!this.mLoaders.isEmpty())
      arrayOfResourcesLoader = this.mLoaders.<ResourcesLoader>toArray(new ResourcesLoader[0]); 
    AssetManager.access$402(assetManager, arrayOfResourcesLoader);
    return assetManager;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/AssetManager$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */