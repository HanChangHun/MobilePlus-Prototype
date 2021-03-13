package android.hardware.radio;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.Map;

public class Default implements ITunerCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onAntennaState(boolean paramBoolean) throws RemoteException {}
  
  public void onBackgroundScanAvailabilityChange(boolean paramBoolean) throws RemoteException {}
  
  public void onBackgroundScanComplete() throws RemoteException {}
  
  public void onConfigurationChanged(RadioManager.BandConfig paramBandConfig) throws RemoteException {}
  
  public void onCurrentProgramInfoChanged(RadioManager.ProgramInfo paramProgramInfo) throws RemoteException {}
  
  public void onEmergencyAnnouncement(boolean paramBoolean) throws RemoteException {}
  
  public void onError(int paramInt) throws RemoteException {}
  
  public void onParametersUpdated(Map paramMap) throws RemoteException {}
  
  public void onProgramListChanged() throws RemoteException {}
  
  public void onProgramListUpdated(ProgramList.Chunk paramChunk) throws RemoteException {}
  
  public void onTrafficAnnouncement(boolean paramBoolean) throws RemoteException {}
  
  public void onTuneFailed(int paramInt, ProgramSelector paramProgramSelector) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ITunerCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */