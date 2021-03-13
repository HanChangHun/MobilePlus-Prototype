package android.app;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.WorkSource;

public class Default implements IAlarmManager {
  public IBinder asBinder() {
    return null;
  }
  
  public long currentNetworkTimeMillis() throws RemoteException {
    return 0L;
  }
  
  public AlarmManager.AlarmClockInfo getNextAlarmClock(int paramInt) throws RemoteException {
    return null;
  }
  
  public long getNextWakeFromIdleTime() throws RemoteException {
    return 0L;
  }
  
  public void remove(PendingIntent paramPendingIntent, IAlarmListener paramIAlarmListener) throws RemoteException {}
  
  public void set(String paramString1, int paramInt1, long paramLong1, long paramLong2, long paramLong3, int paramInt2, PendingIntent paramPendingIntent, IAlarmListener paramIAlarmListener, String paramString2, WorkSource paramWorkSource, AlarmManager.AlarmClockInfo paramAlarmClockInfo) throws RemoteException {}
  
  public boolean setTime(long paramLong) throws RemoteException {
    return false;
  }
  
  public void setTimeZone(String paramString) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAlarmManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */