package android.app;

import android.os.RemoteException;
import android.view.Window;

class null implements Window.WindowControllerCallback {
  public void enterPictureInPictureModeIfPossible() {
    if (Activity.this.mActivityInfo.supportsPictureInPicture())
      Activity.this.enterPictureInPictureMode(); 
  }
  
  public boolean isTaskRoot() {
    boolean bool = false;
    try {
      int i = ActivityTaskManager.getService().getTaskForActivity(Activity.access$000(Activity.this), true);
      if (i >= 0)
        bool = true; 
      return bool;
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public void toggleFreeformWindowingMode() throws RemoteException {
    ActivityTaskManager.getService().toggleFreeformWindowingMode(Activity.access$000(Activity.this));
  }
  
  public void updateNavigationBarColor(int paramInt) {
    Activity.access$100(Activity.this).setNavigationBarColor(paramInt);
    Activity activity = Activity.this;
    activity.setTaskDescription(Activity.access$100(activity));
  }
  
  public void updateStatusBarColor(int paramInt) {
    Activity.access$100(Activity.this).setStatusBarColor(paramInt);
    Activity activity = Activity.this;
    activity.setTaskDescription(Activity.access$100(activity));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Activity$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */