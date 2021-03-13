package android.hardware.hdmi;

import java.util.List;

class null extends IHdmiControlService.Stub {
  public void addDeviceEventListener(IHdmiDeviceEventListener paramIHdmiDeviceEventListener) {
    HdmiControlServiceWrapper.this.addDeviceEventListener(paramIHdmiDeviceEventListener);
  }
  
  public void addHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener paramIHdmiCecVolumeControlFeatureListener) {
    HdmiControlServiceWrapper.this.addHdmiCecVolumeControlFeatureListener(paramIHdmiCecVolumeControlFeatureListener);
  }
  
  public void addHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener paramIHdmiControlStatusChangeListener) {
    HdmiControlServiceWrapper.this.addHdmiControlStatusChangeListener(paramIHdmiControlStatusChangeListener);
  }
  
  public void addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener paramIHdmiMhlVendorCommandListener) {
    HdmiControlServiceWrapper.this.addHdmiMhlVendorCommandListener(paramIHdmiMhlVendorCommandListener);
  }
  
  public void addHotplugEventListener(IHdmiHotplugEventListener paramIHdmiHotplugEventListener) {
    HdmiControlServiceWrapper.this.addHotplugEventListener(paramIHdmiHotplugEventListener);
  }
  
  public void addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener paramIHdmiSystemAudioModeChangeListener) {
    HdmiControlServiceWrapper.this.addSystemAudioModeChangeListener(paramIHdmiSystemAudioModeChangeListener);
  }
  
  public void addVendorCommandListener(IHdmiVendorCommandListener paramIHdmiVendorCommandListener, int paramInt) {
    HdmiControlServiceWrapper.this.addVendorCommandListener(paramIHdmiVendorCommandListener, paramInt);
  }
  
  public void askRemoteDeviceToBecomeActiveSource(int paramInt) {
    HdmiControlServiceWrapper.this.askRemoteDeviceToBecomeActiveSource(paramInt);
  }
  
  public boolean canChangeSystemAudioMode() {
    return HdmiControlServiceWrapper.this.canChangeSystemAudioMode();
  }
  
  public void clearTimerRecording(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) {
    HdmiControlServiceWrapper.this.clearTimerRecording(paramInt1, paramInt2, paramArrayOfbyte);
  }
  
  public void deviceSelect(int paramInt, IHdmiControlCallback paramIHdmiControlCallback) {
    HdmiControlServiceWrapper.this.deviceSelect(paramInt, paramIHdmiControlCallback);
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
  
  public void oneTouchPlay(IHdmiControlCallback paramIHdmiControlCallback) {
    HdmiControlServiceWrapper.this.oneTouchPlay(paramIHdmiControlCallback);
  }
  
  public void portSelect(int paramInt, IHdmiControlCallback paramIHdmiControlCallback) {
    HdmiControlServiceWrapper.this.portSelect(paramInt, paramIHdmiControlCallback);
  }
  
  public void powerOffRemoteDevice(int paramInt1, int paramInt2) {
    HdmiControlServiceWrapper.this.powerOffRemoteDevice(paramInt1, paramInt2);
  }
  
  public void powerOnRemoteDevice(int paramInt1, int paramInt2) {
    HdmiControlServiceWrapper.this.powerOnRemoteDevice(paramInt1, paramInt2);
  }
  
  public void queryDisplayStatus(IHdmiControlCallback paramIHdmiControlCallback) {
    HdmiControlServiceWrapper.this.queryDisplayStatus(paramIHdmiControlCallback);
  }
  
  public void removeHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener paramIHdmiCecVolumeControlFeatureListener) {
    HdmiControlServiceWrapper.this.removeHdmiCecVolumeControlFeatureListener(paramIHdmiCecVolumeControlFeatureListener);
  }
  
  public void removeHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener paramIHdmiControlStatusChangeListener) {
    HdmiControlServiceWrapper.this.removeHdmiControlStatusChangeListener(paramIHdmiControlStatusChangeListener);
  }
  
  public void removeHotplugEventListener(IHdmiHotplugEventListener paramIHdmiHotplugEventListener) {
    HdmiControlServiceWrapper.this.removeHotplugEventListener(paramIHdmiHotplugEventListener);
  }
  
  public void removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener paramIHdmiSystemAudioModeChangeListener) {
    HdmiControlServiceWrapper.this.removeSystemAudioModeChangeListener(paramIHdmiSystemAudioModeChangeListener);
  }
  
  public void reportAudioStatus(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    HdmiControlServiceWrapper.this.reportAudioStatus(paramInt1, paramInt2, paramInt3, paramBoolean);
  }
  
  public void sendKeyEvent(int paramInt1, int paramInt2, boolean paramBoolean) {
    HdmiControlServiceWrapper.this.sendKeyEvent(paramInt1, paramInt2, paramBoolean);
  }
  
  public void sendMhlVendorCommand(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) {
    HdmiControlServiceWrapper.this.sendMhlVendorCommand(paramInt1, paramInt2, paramInt3, paramArrayOfbyte);
  }
  
  public void sendStandby(int paramInt1, int paramInt2) {
    HdmiControlServiceWrapper.this.sendStandby(paramInt1, paramInt2);
  }
  
  public void sendVendorCommand(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, boolean paramBoolean) {
    HdmiControlServiceWrapper.this.sendVendorCommand(paramInt1, paramInt2, paramArrayOfbyte, paramBoolean);
  }
  
  public void sendVolumeKeyEvent(int paramInt1, int paramInt2, boolean paramBoolean) {
    HdmiControlServiceWrapper.this.sendVolumeKeyEvent(paramInt1, paramInt2, paramBoolean);
  }
  
  public void setArcMode(boolean paramBoolean) {
    HdmiControlServiceWrapper.this.setArcMode(paramBoolean);
  }
  
  public void setHdmiCecVolumeControlEnabled(boolean paramBoolean) {
    HdmiControlServiceWrapper.this.setHdmiCecVolumeControlEnabled(paramBoolean);
  }
  
  public void setHdmiRecordListener(IHdmiRecordListener paramIHdmiRecordListener) {
    HdmiControlServiceWrapper.this.setHdmiRecordListener(paramIHdmiRecordListener);
  }
  
  public void setInputChangeListener(IHdmiInputChangeListener paramIHdmiInputChangeListener) {
    HdmiControlServiceWrapper.this.setInputChangeListener(paramIHdmiInputChangeListener);
  }
  
  public void setProhibitMode(boolean paramBoolean) {
    HdmiControlServiceWrapper.this.setProhibitMode(paramBoolean);
  }
  
  public void setStandbyMode(boolean paramBoolean) {
    HdmiControlServiceWrapper.this.setStandbyMode(paramBoolean);
  }
  
  public void setSystemAudioMode(boolean paramBoolean, IHdmiControlCallback paramIHdmiControlCallback) {
    HdmiControlServiceWrapper.this.setSystemAudioMode(paramBoolean, paramIHdmiControlCallback);
  }
  
  public void setSystemAudioModeOnForAudioOnlySource() {
    HdmiControlServiceWrapper.this.setSystemAudioModeOnForAudioOnlySource();
  }
  
  public void setSystemAudioMute(boolean paramBoolean) {
    HdmiControlServiceWrapper.this.setSystemAudioMute(paramBoolean);
  }
  
  public void setSystemAudioVolume(int paramInt1, int paramInt2, int paramInt3) {
    HdmiControlServiceWrapper.this.setSystemAudioVolume(paramInt1, paramInt2, paramInt3);
  }
  
  public void startOneTouchRecord(int paramInt, byte[] paramArrayOfbyte) {
    HdmiControlServiceWrapper.this.startOneTouchRecord(paramInt, paramArrayOfbyte);
  }
  
  public void startTimerRecording(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) {
    HdmiControlServiceWrapper.this.startTimerRecording(paramInt1, paramInt2, paramArrayOfbyte);
  }
  
  public void stopOneTouchRecord(int paramInt) {
    HdmiControlServiceWrapper.this.stopOneTouchRecord(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiControlServiceWrapper$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */