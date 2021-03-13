package android.hardware.radio;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

public class Default implements ITuner {
  public IBinder asBinder() {
    return null;
  }
  
  public void cancel() throws RemoteException {}
  
  public void cancelAnnouncement() throws RemoteException {}
  
  public void close() throws RemoteException {}
  
  public RadioManager.BandConfig getConfiguration() throws RemoteException {
    return null;
  }
  
  public Bitmap getImage(int paramInt) throws RemoteException {
    return null;
  }
  
  public Map getParameters(List<String> paramList) throws RemoteException {
    return null;
  }
  
  public boolean isClosed() throws RemoteException {
    return false;
  }
  
  public boolean isConfigFlagSet(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isConfigFlagSupported(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isMuted() throws RemoteException {
    return false;
  }
  
  public void scan(boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {}
  
  public void setConfigFlag(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setConfiguration(RadioManager.BandConfig paramBandConfig) throws RemoteException {}
  
  public void setMuted(boolean paramBoolean) throws RemoteException {}
  
  public Map setParameters(Map paramMap) throws RemoteException {
    return null;
  }
  
  public boolean startBackgroundScan() throws RemoteException {
    return false;
  }
  
  public void startProgramListUpdates(ProgramList.Filter paramFilter) throws RemoteException {}
  
  public void step(boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {}
  
  public void stopProgramListUpdates() throws RemoteException {}
  
  public void tune(ProgramSelector paramProgramSelector) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ITuner$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */