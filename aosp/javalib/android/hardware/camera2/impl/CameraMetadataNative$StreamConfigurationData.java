package android.hardware.camera2.impl;

import android.hardware.camera2.params.StreamConfiguration;
import android.hardware.camera2.params.StreamConfigurationDuration;

class StreamConfigurationData {
  StreamConfigurationDuration[] minDurationArray = null;
  
  StreamConfigurationDuration[] stallDurationArray = null;
  
  StreamConfiguration[] streamConfigurationArray = null;
  
  private StreamConfigurationData() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraMetadataNative$StreamConfigurationData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */