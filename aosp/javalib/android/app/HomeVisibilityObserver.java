package android.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.List;

public abstract class HomeVisibilityObserver {
  private ActivityManager mActivityManager;
  
  private Context mContext;
  
  boolean mIsHomeActivityVisible;
  
  IProcessObserver.Stub mObserver = new IProcessObserver.Stub() {
      public void onForegroundActivitiesChanged(int param1Int1, int param1Int2, boolean param1Boolean) {
        param1Boolean = HomeVisibilityObserver.this.isHomeActivityVisible();
        if (HomeVisibilityObserver.this.mIsHomeActivityVisible != param1Boolean) {
          HomeVisibilityObserver.this.mIsHomeActivityVisible = param1Boolean;
          HomeVisibilityObserver homeVisibilityObserver = HomeVisibilityObserver.this;
          homeVisibilityObserver.onHomeVisibilityChanged(homeVisibilityObserver.mIsHomeActivityVisible);
        } 
      }
      
      public void onForegroundServicesChanged(int param1Int1, int param1Int2, int param1Int3) {}
      
      public void onProcessDied(int param1Int1, int param1Int2) {}
    };
  
  private boolean isHomeActivityVisible() {
    List<ActivityManager.RunningTaskInfo> list = this.mActivityManager.getRunningTasks(1);
    if (list == null || list.isEmpty())
      return false; 
    String str = ((ActivityManager.RunningTaskInfo)list.get(0)).topActivity.getPackageName();
    if (str == null)
      return false; 
    Intent intent = new Intent("android.intent.action.MAIN", null);
    intent.addCategory("android.intent.category.HOME");
    ResolveInfo resolveInfo = this.mContext.getPackageManager().resolveActivity(intent, 65536);
    return (resolveInfo != null && str.equals(resolveInfo.activityInfo.packageName));
  }
  
  void init(Context paramContext, ActivityManager paramActivityManager) {
    this.mContext = paramContext;
    this.mActivityManager = paramActivityManager;
    this.mIsHomeActivityVisible = isHomeActivityVisible();
  }
  
  public abstract void onHomeVisibilityChanged(boolean paramBoolean);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/HomeVisibilityObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */