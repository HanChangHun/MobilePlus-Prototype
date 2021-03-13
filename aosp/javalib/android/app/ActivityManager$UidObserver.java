package android.app;

import android.content.Context;

final class UidObserver extends IUidObserver.Stub {
  final Context mContext;
  
  final ActivityManager.OnUidImportanceListener mListener;
  
  UidObserver(ActivityManager.OnUidImportanceListener paramOnUidImportanceListener, Context paramContext) {
    this.mListener = paramOnUidImportanceListener;
    this.mContext = paramContext;
  }
  
  public void onUidActive(int paramInt) {}
  
  public void onUidCachedChanged(int paramInt, boolean paramBoolean) {}
  
  public void onUidGone(int paramInt, boolean paramBoolean) {
    this.mListener.onUidImportance(paramInt, 1000);
  }
  
  public void onUidIdle(int paramInt, boolean paramBoolean) {}
  
  public void onUidStateChanged(int paramInt1, int paramInt2, long paramLong, int paramInt3) {
    this.mListener.onUidImportance(paramInt1, ActivityManager.RunningAppProcessInfo.procStateToImportanceForClient(paramInt2, this.mContext));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$UidObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */