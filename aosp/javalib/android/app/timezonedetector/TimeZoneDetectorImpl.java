package android.app.timezonedetector;

import android.os.RemoteException;
import android.os.ServiceManager;

public final class TimeZoneDetectorImpl implements TimeZoneDetector {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "timezonedetector.TimeZoneDetector";
  
  private final ITimeZoneDetectorService mITimeZoneDetectorService = ITimeZoneDetectorService.Stub.asInterface(ServiceManager.getServiceOrThrow("time_zone_detector"));
  
  public void suggestManualTimeZone(ManualTimeZoneSuggestion paramManualTimeZoneSuggestion) {
    try {
      this.mITimeZoneDetectorService.suggestManualTimeZone(paramManualTimeZoneSuggestion);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion paramTelephonyTimeZoneSuggestion) {
    try {
      this.mITimeZoneDetectorService.suggestTelephonyTimeZone(paramTelephonyTimeZoneSuggestion);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezonedetector/TimeZoneDetectorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */