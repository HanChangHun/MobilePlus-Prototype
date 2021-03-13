package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.HighSpeedVideoConfiguration;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerHighSpeedVideoConfiguration extends Marshaler<HighSpeedVideoConfiguration> {
  protected MarshalerHighSpeedVideoConfiguration(TypeReference<HighSpeedVideoConfiguration> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableHighSpeedVideoConfiguration, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 20;
  }
  
  public void marshal(HighSpeedVideoConfiguration paramHighSpeedVideoConfiguration, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putInt(paramHighSpeedVideoConfiguration.getWidth());
    paramByteBuffer.putInt(paramHighSpeedVideoConfiguration.getHeight());
    paramByteBuffer.putInt(paramHighSpeedVideoConfiguration.getFpsMin());
    paramByteBuffer.putInt(paramHighSpeedVideoConfiguration.getFpsMax());
    paramByteBuffer.putInt(paramHighSpeedVideoConfiguration.getBatchSizeMax());
  }
  
  public HighSpeedVideoConfiguration unmarshal(ByteBuffer paramByteBuffer) {
    return new HighSpeedVideoConfiguration(paramByteBuffer.getInt(), paramByteBuffer.getInt(), paramByteBuffer.getInt(), paramByteBuffer.getInt(), paramByteBuffer.getInt());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableHighSpeedVideoConfiguration$MarshalerHighSpeedVideoConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */