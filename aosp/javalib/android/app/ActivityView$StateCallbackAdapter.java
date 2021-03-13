package android.app;

import android.content.ComponentName;
import android.window.TaskEmbedder;

final class StateCallbackAdapter implements TaskEmbedder.Listener {
  private final ActivityView.StateCallback mCallback;
  
  private StateCallbackAdapter(ActivityView.StateCallback paramStateCallback) {
    this.mCallback = paramStateCallback;
  }
  
  public void onBackPressedOnTaskRoot(int paramInt) {
    this.mCallback.onBackPressedOnTaskRoot(paramInt);
  }
  
  public void onInitialized() {
    this.mCallback.onActivityViewReady(ActivityView.this);
  }
  
  public void onReleased() {
    this.mCallback.onActivityViewDestroyed(ActivityView.this);
  }
  
  public void onTaskCreated(int paramInt, ComponentName paramComponentName) {
    this.mCallback.onTaskCreated(paramInt, paramComponentName);
  }
  
  public void onTaskMovedToFront(int paramInt) {
    this.mCallback.onTaskMovedToFront(paramInt);
  }
  
  public void onTaskRemovalStarted(int paramInt) {
    this.mCallback.onTaskRemovalStarted(paramInt);
  }
  
  public void onTaskVisibilityChanged(int paramInt, boolean paramBoolean) {
    this.mCallback.onTaskVisibilityChanged(paramInt, paramBoolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityView$StateCallbackAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */