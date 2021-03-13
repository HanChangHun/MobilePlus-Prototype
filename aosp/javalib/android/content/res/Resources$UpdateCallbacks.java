package android.content.res;

import android.content.res.loader.ResourcesLoader;
import java.util.List;

public interface UpdateCallbacks extends ResourcesLoader.UpdateCallbacks {
  void onLoadersChanged(Resources paramResources, List<ResourcesLoader> paramList);
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/Resources$UpdateCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */