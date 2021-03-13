package android.app.timedetector;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ITimeDetectorService {
  public IBinder asBinder() {
    return null;
  }
  
  public void suggestManualTime(ManualTimeSuggestion paramManualTimeSuggestion) throws RemoteException {}
  
  public void suggestNetworkTime(NetworkTimeSuggestion paramNetworkTimeSuggestion) throws RemoteException {}
  
  public void suggestTelephonyTime(TelephonyTimeSuggestion paramTelephonyTimeSuggestion) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timedetector/ITimeDetectorService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */