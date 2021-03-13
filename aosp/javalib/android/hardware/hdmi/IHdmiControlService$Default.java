package android.hardware.hdmi;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IHdmiControlService {
  public void addDeviceEventListener(IHdmiDeviceEventListener paramIHdmiDeviceEventListener) throws RemoteException {}
  
  public void addHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener paramIHdmiCecVolumeControlFeatureListener) throws RemoteException {}
  
  public void addHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener paramIHdmiControlStatusChangeListener) throws RemoteException {}
  
  public void addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener paramIHdmiMhlVendorCommandListener) throws RemoteException {}
  
  public void addHotplugEventListener(IHdmiHotplugEventListener paramIHdmiHotplugEventListener) throws RemoteException {}
  
  public void addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener paramIHdmiSystemAudioModeChangeListener) throws RemoteException {}
  
  public void addVendorCommandListener(IHdmiVendorCommandListener paramIHdmiVendorCommandListener, int paramInt) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void askRemoteDeviceToBecomeActiveSource(int paramInt) throws RemoteException {}
  
  public boolean canChangeSystemAudioMode() throws RemoteException {
    return false;
  }
  
  public void clearTimerRecording(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void deviceSelect(int paramInt, IHdmiControlCallback paramIHdmiControlCallback) throws RemoteException {}
  
  public HdmiDeviceInfo getActiveSource() throws RemoteException {
    return null;
  }
  
  public List<HdmiDeviceInfo> getDeviceList() throws RemoteException {
    return null;
  }
  
  public List<HdmiDeviceInfo> getInputDevices() throws RemoteException {
    return null;
  }
  
  public int getPhysicalAddress() throws RemoteException {
    return 0;
  }
  
  public List<HdmiPortInfo> getPortInfo() throws RemoteException {
    return null;
  }
  
  public int[] getSupportedTypes() throws RemoteException {
    return null;
  }
  
  public boolean getSystemAudioMode() throws RemoteException {
    return false;
  }
  
  public boolean isHdmiCecVolumeControlEnabled() throws RemoteException {
    return false;
  }
  
  public void oneTouchPlay(IHdmiControlCallback paramIHdmiControlCallback) throws RemoteException {}
  
  public void portSelect(int paramInt, IHdmiControlCallback paramIHdmiControlCallback) throws RemoteException {}
  
  public void powerOffRemoteDevice(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void powerOnRemoteDevice(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void queryDisplayStatus(IHdmiControlCallback paramIHdmiControlCallback) throws RemoteException {}
  
  public void removeHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener paramIHdmiCecVolumeControlFeatureListener) throws RemoteException {}
  
  public void removeHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener paramIHdmiControlStatusChangeListener) throws RemoteException {}
  
  public void removeHotplugEventListener(IHdmiHotplugEventListener paramIHdmiHotplugEventListener) throws RemoteException {}
  
  public void removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener paramIHdmiSystemAudioModeChangeListener) throws RemoteException {}
  
  public void reportAudioStatus(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) throws RemoteException {}
  
  public void sendKeyEvent(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {}
  
  public void sendMhlVendorCommand(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void sendStandby(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void sendVendorCommand(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, boolean paramBoolean) throws RemoteException {}
  
  public void sendVolumeKeyEvent(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {}
  
  public void setArcMode(boolean paramBoolean) throws RemoteException {}
  
  public void setHdmiCecVolumeControlEnabled(boolean paramBoolean) throws RemoteException {}
  
  public void setHdmiRecordListener(IHdmiRecordListener paramIHdmiRecordListener) throws RemoteException {}
  
  public void setInputChangeListener(IHdmiInputChangeListener paramIHdmiInputChangeListener) throws RemoteException {}
  
  public void setProhibitMode(boolean paramBoolean) throws RemoteException {}
  
  public void setStandbyMode(boolean paramBoolean) throws RemoteException {}
  
  public void setSystemAudioMode(boolean paramBoolean, IHdmiControlCallback paramIHdmiControlCallback) throws RemoteException {}
  
  public void setSystemAudioModeOnForAudioOnlySource() throws RemoteException {}
  
  public void setSystemAudioMute(boolean paramBoolean) throws RemoteException {}
  
  public void setSystemAudioVolume(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void startOneTouchRecord(int paramInt, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void startTimerRecording(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void stopOneTouchRecord(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiControlService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */