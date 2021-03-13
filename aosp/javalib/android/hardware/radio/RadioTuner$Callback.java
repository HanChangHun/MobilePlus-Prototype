package android.hardware.radio;

import java.util.Map;

public abstract class Callback {
  public void onAntennaState(boolean paramBoolean) {}
  
  public void onBackgroundScanAvailabilityChange(boolean paramBoolean) {}
  
  public void onBackgroundScanComplete() {}
  
  @Deprecated
  public void onConfigurationChanged(RadioManager.BandConfig paramBandConfig) {}
  
  public void onControlChanged(boolean paramBoolean) {}
  
  public void onEmergencyAnnouncement(boolean paramBoolean) {}
  
  public void onError(int paramInt) {}
  
  @Deprecated
  public void onMetadataChanged(RadioMetadata paramRadioMetadata) {}
  
  public void onParametersUpdated(Map<String, String> paramMap) {}
  
  public void onProgramInfoChanged(RadioManager.ProgramInfo paramProgramInfo) {}
  
  public void onProgramListChanged() {}
  
  public void onTrafficAnnouncement(boolean paramBoolean) {}
  
  public void onTuneFailed(int paramInt, ProgramSelector paramProgramSelector) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioTuner$Callback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */