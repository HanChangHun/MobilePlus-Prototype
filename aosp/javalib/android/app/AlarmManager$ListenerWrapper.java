package android.app;

import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

final class ListenerWrapper extends IAlarmListener.Stub implements Runnable {
  IAlarmCompleteListener mCompletion;
  
  Handler mHandler;
  
  final AlarmManager.OnAlarmListener mListener;
  
  public ListenerWrapper(AlarmManager.OnAlarmListener paramOnAlarmListener) {
    this.mListener = paramOnAlarmListener;
  }
  
  public void cancel() {
    try {
      AlarmManager.access$000(AlarmManager.this).remove(null, this);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void doAlarm(IAlarmCompleteListener paramIAlarmCompleteListener) {
    this.mCompletion = paramIAlarmCompleteListener;
    this.mHandler.post(this);
  }
  
  public void run() {
    try {
      this.mListener.onAlarm();
      return;
    } finally {
      try {
        this.mCompletion.alarmComplete((IBinder)this);
      } catch (Exception exception) {
        Log.e("AlarmManager", "Unable to report completion to Alarm Manager!", exception);
      } 
    } 
  }
  
  public void setHandler(Handler paramHandler) {
    this.mHandler = paramHandler;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AlarmManager$ListenerWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */