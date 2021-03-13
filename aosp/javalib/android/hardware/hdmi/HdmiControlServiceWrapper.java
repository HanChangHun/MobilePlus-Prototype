package android.hardware.hdmi;

import java.util.List;

public final class HdmiControlServiceWrapper {
  public static final int DEVICE_PURE_CEC_SWITCH = 6;
  
  private List<HdmiPortInfo> mInfoList = null;
  
  private final IHdmiControlService mInterface = new IHdmiControlService.Stub() {
      public void addDeviceEventListener(IHdmiDeviceEventListener param1IHdmiDeviceEventListener) {
        HdmiControlServiceWrapper.this.addDeviceEventListener(param1IHdmiDeviceEventListener);
      }
      
      public void addHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener param1IHdmiCecVolumeControlFeatureListener) {
        HdmiControlServiceWrapper.this.addHdmiCecVolumeControlFeatureListener(param1IHdmiCecVolumeControlFeatureListener);
      }
      
      public void addHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener param1IHdmiControlStatusChangeListener) {
        HdmiControlServiceWrapper.this.addHdmiControlStatusChangeListener(param1IHdmiControlStatusChangeListener);
      }
      
      public void addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener param1IHdmiMhlVendorCommandListener) {
        HdmiControlServiceWrapper.this.addHdmiMhlVendorCommandListener(param1IHdmiMhlVendorCommandListener);
      }
      
      public void addHotplugEventListener(IHdmiHotplugEventListener param1IHdmiHotplugEventListener) {
        HdmiControlServiceWrapper.this.addHotplugEventListener(param1IHdmiHotplugEventListener);
      }
      
      public void addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener param1IHdmiSystemAudioModeChangeListener) {
        HdmiControlServiceWrapper.this.addSystemAudioModeChangeListener(param1IHdmiSystemAudioModeChangeListener);
      }
      
      public void addVendorCommandListener(IHdmiVendorCommandListener param1IHdmiVendorCommandListener, int param1Int) {
        HdmiControlServiceWrapper.this.addVendorCommandListener(param1IHdmiVendorCommandListener, param1Int);
      }
      
      public void askRemoteDeviceToBecomeActiveSource(int param1Int) {
        HdmiControlServiceWrapper.this.askRemoteDeviceToBecomeActiveSource(param1Int);
      }
      
      public boolean canChangeSystemAudioMode() {
        return HdmiControlServiceWrapper.this.canChangeSystemAudioMode();
      }
      
      public void clearTimerRecording(int param1Int1, int param1Int2, byte[] param1ArrayOfbyte) {
        HdmiControlServiceWrapper.this.clearTimerRecording(param1Int1, param1Int2, param1ArrayOfbyte);
      }
      
      public void deviceSelect(int param1Int, IHdmiControlCallback param1IHdmiControlCallback) {
        HdmiControlServiceWrapper.this.deviceSelect(param1Int, param1IHdmiControlCallback);
      }
      
      public HdmiDeviceInfo getActiveSource() {
        return HdmiControlServiceWrapper.this.getActiveSource();
      }
      
      public List<HdmiDeviceInfo> getDeviceList() {
        return HdmiControlServiceWrapper.this.getDeviceList();
      }
      
      public List<HdmiDeviceInfo> getInputDevices() {
        return HdmiControlServiceWrapper.this.getInputDevices();
      }
      
      public int getPhysicalAddress() {
        return HdmiControlServiceWrapper.this.getPhysicalAddress();
      }
      
      public List<HdmiPortInfo> getPortInfo() {
        return HdmiControlServiceWrapper.this.getPortInfo();
      }
      
      public int[] getSupportedTypes() {
        return HdmiControlServiceWrapper.this.getSupportedTypes();
      }
      
      public boolean getSystemAudioMode() {
        return HdmiControlServiceWrapper.this.getSystemAudioMode();
      }
      
      public boolean isHdmiCecVolumeControlEnabled() {
        return HdmiControlServiceWrapper.this.isHdmiCecVolumeControlEnabled();
      }
      
      public void oneTouchPlay(IHdmiControlCallback param1IHdmiControlCallback) {
        HdmiControlServiceWrapper.this.oneTouchPlay(param1IHdmiControlCallback);
      }
      
      public void portSelect(int param1Int, IHdmiControlCallback param1IHdmiControlCallback) {
        HdmiControlServiceWrapper.this.portSelect(param1Int, param1IHdmiControlCallback);
      }
      
      public void powerOffRemoteDevice(int param1Int1, int param1Int2) {
        HdmiControlServiceWrapper.this.powerOffRemoteDevice(param1Int1, param1Int2);
      }
      
      public void powerOnRemoteDevice(int param1Int1, int param1Int2) {
        HdmiControlServiceWrapper.this.powerOnRemoteDevice(param1Int1, param1Int2);
      }
      
      public void queryDisplayStatus(IHdmiControlCallback param1IHdmiControlCallback) {
        HdmiControlServiceWrapper.this.queryDisplayStatus(param1IHdmiControlCallback);
      }
      
      public void removeHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener param1IHdmiCecVolumeControlFeatureListener) {
        HdmiControlServiceWrapper.this.removeHdmiCecVolumeControlFeatureListener(param1IHdmiCecVolumeControlFeatureListener);
      }
      
      public void removeHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener param1IHdmiControlStatusChangeListener) {
        HdmiControlServiceWrapper.this.removeHdmiControlStatusChangeListener(param1IHdmiControlStatusChangeListener);
      }
      
      public void removeHotplugEventListener(IHdmiHotplugEventListener param1IHdmiHotplugEventListener) {
        HdmiControlServiceWrapper.this.removeHotplugEventListener(param1IHdmiHotplugEventListener);
      }
      
      public void removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener param1IHdmiSystemAudioModeChangeListener) {
        HdmiControlServiceWrapper.this.removeSystemAudioModeChangeListener(param1IHdmiSystemAudioModeChangeListener);
      }
      
      public void reportAudioStatus(int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean) {
        HdmiControlServiceWrapper.this.reportAudioStatus(param1Int1, param1Int2, param1Int3, param1Boolean);
      }
      
      public void sendKeyEvent(int param1Int1, int param1Int2, boolean param1Boolean) {
        HdmiControlServiceWrapper.this.sendKeyEvent(param1Int1, param1Int2, param1Boolean);
      }
      
      public void sendMhlVendorCommand(int param1Int1, int param1Int2, int param1Int3, byte[] param1ArrayOfbyte) {
        HdmiControlServiceWrapper.this.sendMhlVendorCommand(param1Int1, param1Int2, param1Int3, param1ArrayOfbyte);
      }
      
      public void sendStandby(int param1Int1, int param1Int2) {
        HdmiControlServiceWrapper.this.sendStandby(param1Int1, param1Int2);
      }
      
      public void sendVendorCommand(int param1Int1, int param1Int2, byte[] param1ArrayOfbyte, boolean param1Boolean) {
        HdmiControlServiceWrapper.this.sendVendorCommand(param1Int1, param1Int2, param1ArrayOfbyte, param1Boolean);
      }
      
      public void sendVolumeKeyEvent(int param1Int1, int param1Int2, boolean param1Boolean) {
        HdmiControlServiceWrapper.this.sendVolumeKeyEvent(param1Int1, param1Int2, param1Boolean);
      }
      
      public void setArcMode(boolean param1Boolean) {
        HdmiControlServiceWrapper.this.setArcMode(param1Boolean);
      }
      
      public void setHdmiCecVolumeControlEnabled(boolean param1Boolean) {
        HdmiControlServiceWrapper.this.setHdmiCecVolumeControlEnabled(param1Boolean);
      }
      
      public void setHdmiRecordListener(IHdmiRecordListener param1IHdmiRecordListener) {
        HdmiControlServiceWrapper.this.setHdmiRecordListener(param1IHdmiRecordListener);
      }
      
      public void setInputChangeListener(IHdmiInputChangeListener param1IHdmiInputChangeListener) {
        HdmiControlServiceWrapper.this.setInputChangeListener(param1IHdmiInputChangeListener);
      }
      
      public void setProhibitMode(boolean param1Boolean) {
        HdmiControlServiceWrapper.this.setProhibitMode(param1Boolean);
      }
      
      public void setStandbyMode(boolean param1Boolean) {
        HdmiControlServiceWrapper.this.setStandbyMode(param1Boolean);
      }
      
      public void setSystemAudioMode(boolean param1Boolean, IHdmiControlCallback param1IHdmiControlCallback) {
        HdmiControlServiceWrapper.this.setSystemAudioMode(param1Boolean, param1IHdmiControlCallback);
      }
      
      public void setSystemAudioModeOnForAudioOnlySource() {
        HdmiControlServiceWrapper.this.setSystemAudioModeOnForAudioOnlySource();
      }
      
      public void setSystemAudioMute(boolean param1Boolean) {
        HdmiControlServiceWrapper.this.setSystemAudioMute(param1Boolean);
      }
      
      public void setSystemAudioVolume(int param1Int1, int param1Int2, int param1Int3) {
        HdmiControlServiceWrapper.this.setSystemAudioVolume(param1Int1, param1Int2, param1Int3);
      }
      
      public void startOneTouchRecord(int param1Int, byte[] param1ArrayOfbyte) {
        HdmiControlServiceWrapper.this.startOneTouchRecord(param1Int, param1ArrayOfbyte);
      }
      
      public void startTimerRecording(int param1Int1, int param1Int2, byte[] param1ArrayOfbyte) {
        HdmiControlServiceWrapper.this.startTimerRecording(param1Int1, param1Int2, param1ArrayOfbyte);
      }
      
      public void stopOneTouchRecord(int param1Int) {
        HdmiControlServiceWrapper.this.stopOneTouchRecord(param1Int);
      }
    };
  
  private int[] mTypes = null;
  
  public void addDeviceEventListener(IHdmiDeviceEventListener paramIHdmiDeviceEventListener) {}
  
  public void addHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener paramIHdmiCecVolumeControlFeatureListener) {}
  
  public void addHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener paramIHdmiControlStatusChangeListener) {}
  
  public void addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener paramIHdmiMhlVendorCommandListener) {}
  
  public void addHotplugEventListener(IHdmiHotplugEventListener paramIHdmiHotplugEventListener) {}
  
  public void addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener paramIHdmiSystemAudioModeChangeListener) {}
  
  public void addVendorCommandListener(IHdmiVendorCommandListener paramIHdmiVendorCommandListener, int paramInt) {}
  
  public void askRemoteDeviceToBecomeActiveSource(int paramInt) {}
  
  public boolean canChangeSystemAudioMode() {
    return true;
  }
  
  public void clearTimerRecording(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) {}
  
  public HdmiControlManager createHdmiControlManager() {
    return new HdmiControlManager(this.mInterface);
  }
  
  public void deviceSelect(int paramInt, IHdmiControlCallback paramIHdmiControlCallback) {}
  
  public HdmiDeviceInfo getActiveSource() {
    return null;
  }
  
  public List<HdmiDeviceInfo> getDeviceList() {
    return null;
  }
  
  public List<HdmiDeviceInfo> getInputDevices() {
    return null;
  }
  
  public int getPhysicalAddress() {
    return 65535;
  }
  
  public List<HdmiPortInfo> getPortInfo() {
    return this.mInfoList;
  }
  
  public int[] getSupportedTypes() {
    return this.mTypes;
  }
  
  public boolean getSystemAudioMode() {
    return true;
  }
  
  public boolean isHdmiCecVolumeControlEnabled() {
    return true;
  }
  
  public void oneTouchPlay(IHdmiControlCallback paramIHdmiControlCallback) {}
  
  public void portSelect(int paramInt, IHdmiControlCallback paramIHdmiControlCallback) {}
  
  public void powerOffRemoteDevice(int paramInt1, int paramInt2) {}
  
  public void powerOnRemoteDevice(int paramInt1, int paramInt2) {}
  
  public void queryDisplayStatus(IHdmiControlCallback paramIHdmiControlCallback) {}
  
  public void removeHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener paramIHdmiCecVolumeControlFeatureListener) {}
  
  public void removeHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener paramIHdmiControlStatusChangeListener) {}
  
  public void removeHotplugEventListener(IHdmiHotplugEventListener paramIHdmiHotplugEventListener) {}
  
  public void removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener paramIHdmiSystemAudioModeChangeListener) {}
  
  public void reportAudioStatus(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {}
  
  public void sendKeyEvent(int paramInt1, int paramInt2, boolean paramBoolean) {}
  
  public void sendMhlVendorCommand(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) {}
  
  public void sendStandby(int paramInt1, int paramInt2) {}
  
  public void sendVendorCommand(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, boolean paramBoolean) {}
  
  public void sendVolumeKeyEvent(int paramInt1, int paramInt2, boolean paramBoolean) {}
  
  public void setArcMode(boolean paramBoolean) {}
  
  public void setDeviceTypes(int[] paramArrayOfint) {
    this.mTypes = paramArrayOfint;
  }
  
  public void setHdmiCecVolumeControlEnabled(boolean paramBoolean) {}
  
  public void setHdmiRecordListener(IHdmiRecordListener paramIHdmiRecordListener) {}
  
  public void setInputChangeListener(IHdmiInputChangeListener paramIHdmiInputChangeListener) {}
  
  public void setPortInfo(List<HdmiPortInfo> paramList) {
    this.mInfoList = paramList;
  }
  
  public void setProhibitMode(boolean paramBoolean) {}
  
  public void setStandbyMode(boolean paramBoolean) {}
  
  public void setSystemAudioMode(boolean paramBoolean, IHdmiControlCallback paramIHdmiControlCallback) {}
  
  public void setSystemAudioModeOnForAudioOnlySource() {}
  
  public void setSystemAudioMute(boolean paramBoolean) {}
  
  public void setSystemAudioVolume(int paramInt1, int paramInt2, int paramInt3) {}
  
  public void startOneTouchRecord(int paramInt, byte[] paramArrayOfbyte) {}
  
  public void startTimerRecording(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) {}
  
  public void stopOneTouchRecord(int paramInt) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiControlServiceWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */