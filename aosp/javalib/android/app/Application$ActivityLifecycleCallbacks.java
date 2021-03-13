package android.app;

import android.os.Bundle;

public interface ActivityLifecycleCallbacks {
  void onActivityCreated(Activity paramActivity, Bundle paramBundle);
  
  void onActivityDestroyed(Activity paramActivity);
  
  void onActivityPaused(Activity paramActivity);
  
  default void onActivityPostCreated(Activity paramActivity, Bundle paramBundle) {}
  
  default void onActivityPostDestroyed(Activity paramActivity) {}
  
  default void onActivityPostPaused(Activity paramActivity) {}
  
  default void onActivityPostResumed(Activity paramActivity) {}
  
  default void onActivityPostSaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  default void onActivityPostStarted(Activity paramActivity) {}
  
  default void onActivityPostStopped(Activity paramActivity) {}
  
  default void onActivityPreCreated(Activity paramActivity, Bundle paramBundle) {}
  
  default void onActivityPreDestroyed(Activity paramActivity) {}
  
  default void onActivityPrePaused(Activity paramActivity) {}
  
  default void onActivityPreResumed(Activity paramActivity) {}
  
  default void onActivityPreSaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  default void onActivityPreStarted(Activity paramActivity) {}
  
  default void onActivityPreStopped(Activity paramActivity) {}
  
  void onActivityResumed(Activity paramActivity);
  
  void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle);
  
  void onActivityStarted(Activity paramActivity);
  
  void onActivityStopped(Activity paramActivity);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Application$ActivityLifecycleCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */