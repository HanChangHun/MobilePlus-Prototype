package android.hardware.radio;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IRadioService {
  public ICloseHandle addAnnouncementListener(int[] paramArrayOfint, IAnnouncementListener paramIAnnouncementListener) throws RemoteException {
    return null;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public List<RadioManager.ModuleProperties> listModules() throws RemoteException {
    return null;
  }
  
  public ITuner openTuner(int paramInt, RadioManager.BandConfig paramBandConfig, boolean paramBoolean, ITunerCallback paramITunerCallback) throws RemoteException {
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/IRadioService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */