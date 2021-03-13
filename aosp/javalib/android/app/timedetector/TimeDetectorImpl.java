package android.app.timedetector;

import android.os.RemoteException;
import android.os.ServiceManager;

public final class TimeDetectorImpl implements TimeDetector {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "timedetector.TimeDetector";
  
  private final ITimeDetectorService mITimeDetectorService = ITimeDetectorService.Stub.asInterface(ServiceManager.getServiceOrThrow("time_detector"));
  
  public void suggestManualTime(ManualTimeSuggestion paramManualTimeSuggestion) {
    try {
      this.mITimeDetectorService.suggestManualTime(paramManualTimeSuggestion);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void suggestNetworkTime(NetworkTimeSuggestion paramNetworkTimeSuggestion) {
    try {
      this.mITimeDetectorService.suggestNetworkTime(paramNetworkTimeSuggestion);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void suggestTelephonyTime(TelephonyTimeSuggestion paramTelephonyTimeSuggestion) {
    try {
      this.mITimeDetectorService.suggestTelephonyTime(paramTelephonyTimeSuggestion);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timedetector/TimeDetectorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */