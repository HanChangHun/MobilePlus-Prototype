package android.content.pm;

import android.content.ComponentName;

public final class ActivityPresentationInfo {
  public final ComponentName componentName;
  
  public final int displayId;
  
  public final int taskId;
  
  public ActivityPresentationInfo(int paramInt1, int paramInt2, ComponentName paramComponentName) {
    this.taskId = paramInt1;
    this.displayId = paramInt2;
    this.componentName = paramComponentName;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ActivityPresentationInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */