package android.hardware.radio;

import android.annotation.SystemApi;
import android.graphics.Bitmap;
import java.util.List;
import java.util.Map;

@SystemApi
public abstract class RadioTuner {
  public static final int DIRECTION_DOWN = 1;
  
  public static final int DIRECTION_UP = 0;
  
  @Deprecated
  public static final int ERROR_BACKGROUND_SCAN_FAILED = 6;
  
  @Deprecated
  public static final int ERROR_BACKGROUND_SCAN_UNAVAILABLE = 5;
  
  @Deprecated
  public static final int ERROR_CANCELLED = 2;
  
  @Deprecated
  public static final int ERROR_CONFIG = 4;
  
  @Deprecated
  public static final int ERROR_HARDWARE_FAILURE = 0;
  
  @Deprecated
  public static final int ERROR_SCAN_TIMEOUT = 3;
  
  @Deprecated
  public static final int ERROR_SERVER_DIED = 1;
  
  public abstract int cancel();
  
  public abstract void cancelAnnouncement();
  
  public abstract void close();
  
  @Deprecated
  public abstract int getConfiguration(RadioManager.BandConfig[] paramArrayOfBandConfig);
  
  public ProgramList getDynamicProgramList(ProgramList.Filter paramFilter) {
    return null;
  }
  
  public abstract Bitmap getMetadataImage(int paramInt);
  
  public abstract boolean getMute();
  
  public Map<String, String> getParameters(List<String> paramList) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  public abstract int getProgramInformation(RadioManager.ProgramInfo[] paramArrayOfProgramInfo);
  
  @Deprecated
  public abstract List<RadioManager.ProgramInfo> getProgramList(Map<String, String> paramMap);
  
  public abstract boolean hasControl();
  
  @Deprecated
  public abstract boolean isAnalogForced();
  
  @Deprecated
  public abstract boolean isAntennaConnected();
  
  public boolean isConfigFlagSet(int paramInt) {
    throw new UnsupportedOperationException();
  }
  
  public boolean isConfigFlagSupported(int paramInt) {
    return false;
  }
  
  public abstract int scan(int paramInt, boolean paramBoolean);
  
  @Deprecated
  public abstract void setAnalogForced(boolean paramBoolean);
  
  public void setConfigFlag(int paramInt, boolean paramBoolean) {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  public abstract int setConfiguration(RadioManager.BandConfig paramBandConfig);
  
  public abstract int setMute(boolean paramBoolean);
  
  public Map<String, String> setParameters(Map<String, String> paramMap) {
    throw new UnsupportedOperationException();
  }
  
  public abstract boolean startBackgroundScan();
  
  public abstract int step(int paramInt, boolean paramBoolean);
  
  @Deprecated
  public abstract int tune(int paramInt1, int paramInt2);
  
  public abstract void tune(ProgramSelector paramProgramSelector);
  
  public static abstract class Callback {
    public void onAntennaState(boolean param1Boolean) {}
    
    public void onBackgroundScanAvailabilityChange(boolean param1Boolean) {}
    
    public void onBackgroundScanComplete() {}
    
    @Deprecated
    public void onConfigurationChanged(RadioManager.BandConfig param1BandConfig) {}
    
    public void onControlChanged(boolean param1Boolean) {}
    
    public void onEmergencyAnnouncement(boolean param1Boolean) {}
    
    public void onError(int param1Int) {}
    
    @Deprecated
    public void onMetadataChanged(RadioMetadata param1RadioMetadata) {}
    
    public void onParametersUpdated(Map<String, String> param1Map) {}
    
    public void onProgramInfoChanged(RadioManager.ProgramInfo param1ProgramInfo) {}
    
    public void onProgramListChanged() {}
    
    public void onTrafficAnnouncement(boolean param1Boolean) {}
    
    public void onTuneFailed(int param1Int, ProgramSelector param1ProgramSelector) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioTuner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */