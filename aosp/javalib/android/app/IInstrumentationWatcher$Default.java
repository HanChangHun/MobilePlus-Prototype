package android.app;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IInstrumentationWatcher {
  public IBinder asBinder() {
    return null;
  }
  
  public void instrumentationFinished(ComponentName paramComponentName, int paramInt, Bundle paramBundle) throws RemoteException {}
  
  public void instrumentationStatus(ComponentName paramComponentName, int paramInt, Bundle paramBundle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IInstrumentationWatcher$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */