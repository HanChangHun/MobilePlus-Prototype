package android.app;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IActivityController {
  public boolean activityResuming(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean activityStarting(Intent paramIntent, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean appCrashed(String paramString1, int paramInt, String paramString2, String paramString3, long paramLong, String paramString4) throws RemoteException {
    return false;
  }
  
  public int appEarlyNotResponding(String paramString1, int paramInt, String paramString2) throws RemoteException {
    return 0;
  }
  
  public int appNotResponding(String paramString1, int paramInt, String paramString2) throws RemoteException {
    return 0;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public int systemNotResponding(String paramString) throws RemoteException {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityController$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */