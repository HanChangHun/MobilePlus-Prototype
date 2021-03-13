package android.content.res;

import android.content.res.loader.ResourcesLoader;
import com.android.internal.util.Preconditions;
import java.util.List;

public class AssetManagerUpdateHandler implements Resources.UpdateCallbacks {
  public void onLoaderUpdated(ResourcesLoader paramResourcesLoader) {
    ResourcesImpl resourcesImpl = Resources.access$000(Resources.this);
    AssetManager assetManager = resourcesImpl.getAssets();
    if (assetManager.getLoaders().contains(paramResourcesLoader)) {
      resourcesImpl.clearAllCaches();
      assetManager.setLoaders(assetManager.getLoaders());
    } 
  }
  
  public void onLoadersChanged(Resources paramResources, List<ResourcesLoader> paramList) {
    boolean bool;
    if (Resources.this == paramResources) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool);
    ResourcesImpl resourcesImpl = Resources.access$000(Resources.this);
    resourcesImpl.clearAllCaches();
    resourcesImpl.getAssets().setLoaders(paramList);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/Resources$AssetManagerUpdateHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */