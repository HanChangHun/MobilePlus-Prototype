package android.app;

import android.content.res.Resources;
import android.content.res.ResourcesImpl;
import android.content.res.ResourcesKey;
import android.content.res.loader.ResourcesLoader;
import android.util.ArrayMap;
import com.android.internal.util.ArrayUtils;
import java.lang.ref.WeakReference;
import java.util.List;

class UpdateHandler implements Resources.UpdateCallbacks {
  private UpdateHandler() {}
  
  public void onLoaderUpdated(ResourcesLoader paramResourcesLoader) {
    synchronized (ResourcesManager.this) {
      ArrayMap arrayMap = new ArrayMap();
      this();
      for (int i = ResourcesManager.access$600(ResourcesManager.this).size() - 1; i >= 0; i--) {
        ResourcesKey resourcesKey = (ResourcesKey)ResourcesManager.access$600(ResourcesManager.this).keyAt(i);
        WeakReference<ResourcesImpl> weakReference = (WeakReference)ResourcesManager.access$600(ResourcesManager.this).valueAt(i);
        if (weakReference != null && weakReference.get() != null && ArrayUtils.contains((Object[])resourcesKey.mLoaders, paramResourcesLoader)) {
          ResourcesManager.access$600(ResourcesManager.this).remove(resourcesKey);
          arrayMap.put(weakReference.get(), resourcesKey);
        } 
      } 
      ResourcesManager.access$700(ResourcesManager.this, arrayMap);
      return;
    } 
  }
  
  public void onLoadersChanged(Resources paramResources, List<ResourcesLoader> paramList) {
    synchronized (ResourcesManager.this) {
      ResourcesKey resourcesKey = ResourcesManager.access$400(ResourcesManager.this, paramResources.getImpl());
      if (resourcesKey != null) {
        ResourcesKey resourcesKey1 = new ResourcesKey();
        this(resourcesKey.mResDir, resourcesKey.mSplitResDirs, resourcesKey.mOverlayDirs, resourcesKey.mLibDirs, resourcesKey.mDisplayId, resourcesKey.mOverrideConfiguration, resourcesKey.mCompatInfo, paramList.<ResourcesLoader>toArray(new ResourcesLoader[0]));
        paramResources.setImpl(ResourcesManager.access$500(ResourcesManager.this, resourcesKey1));
        return;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this("Cannot modify resource loaders of ResourcesImpl not registered with ResourcesManager");
      throw illegalArgumentException;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ResourcesManager$UpdateHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */