package android.app;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IAlarmListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void doAlarm(IAlarmCompleteListener paramIAlarmCompleteListener) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAlarmListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */