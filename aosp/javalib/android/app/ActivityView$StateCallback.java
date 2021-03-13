package android.app;

import android.content.ComponentName;

public abstract class StateCallback {
  public abstract void onActivityViewDestroyed(ActivityView paramActivityView);
  
  public abstract void onActivityViewReady(ActivityView paramActivityView);
  
  public void onBackPressedOnTaskRoot(int paramInt) {}
  
  public void onTaskCreated(int paramInt, ComponentName paramComponentName) {}
  
  public void onTaskMovedToFront(int paramInt) {}
  
  public void onTaskRemovalStarted(int paramInt) {}
  
  public void onTaskVisibilityChanged(int paramInt, boolean paramBoolean) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityView$StateCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */