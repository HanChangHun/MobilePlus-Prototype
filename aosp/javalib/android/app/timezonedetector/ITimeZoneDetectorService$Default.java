package android.app.timezonedetector;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ITimeZoneDetectorService {
  public IBinder asBinder() {
    return null;
  }
  
  public void suggestManualTimeZone(ManualTimeZoneSuggestion paramManualTimeZoneSuggestion) throws RemoteException {}
  
  public void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion paramTelephonyTimeZoneSuggestion) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezonedetector/ITimeZoneDetectorService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */