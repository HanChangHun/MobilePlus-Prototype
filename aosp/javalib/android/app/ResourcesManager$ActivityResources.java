package android.app;

import android.content.res.Configuration;
import android.content.res.Resources;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

class ActivityResources {
  public final ArrayList<WeakReference<Resources>> activityResources = new ArrayList<>();
  
  final ReferenceQueue<Resources> activityResourcesQueue = new ReferenceQueue<>();
  
  public final Configuration overrideConfig = new Configuration();
  
  private ActivityResources() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ResourcesManager$ActivityResources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */