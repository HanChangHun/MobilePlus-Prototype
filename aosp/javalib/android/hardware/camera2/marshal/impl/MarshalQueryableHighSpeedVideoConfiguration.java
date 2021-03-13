package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.HighSpeedVideoConfiguration;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableHighSpeedVideoConfiguration implements MarshalQueryable<HighSpeedVideoConfiguration> {
  private static final int SIZE = 20;
  
  public Marshaler<HighSpeedVideoConfiguration> createMarshaler(TypeReference<HighSpeedVideoConfiguration> paramTypeReference, int paramInt) {
    return new MarshalerHighSpeedVideoConfiguration(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<HighSpeedVideoConfiguration> paramTypeReference, int paramInt) {
    boolean bool = true;
    if (paramInt != 1 || !paramTypeReference.getType().equals(HighSpeedVideoConfiguration.class))
      bool = false; 
    return bool;
  }
  
  private class MarshalerHighSpeedVideoConfiguration extends Marshaler<HighSpeedVideoConfiguration> {
    protected MarshalerHighSpeedVideoConfiguration(TypeReference<HighSpeedVideoConfiguration> param1TypeReference, int param1Int) {
      super(MarshalQueryableHighSpeedVideoConfiguration.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 20;
    }
    
    public void marshal(HighSpeedVideoConfiguration param1HighSpeedVideoConfiguration, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putInt(param1HighSpeedVideoConfiguration.getWidth());
      param1ByteBuffer.putInt(param1HighSpeedVideoConfiguration.getHeight());
      param1ByteBuffer.putInt(param1HighSpeedVideoConfiguration.getFpsMin());
      param1ByteBuffer.putInt(param1HighSpeedVideoConfiguration.getFpsMax());
      param1ByteBuffer.putInt(param1HighSpeedVideoConfiguration.getBatchSizeMax());
    }
    
    public HighSpeedVideoConfiguration unmarshal(ByteBuffer param1ByteBuffer) {
      return new HighSpeedVideoConfiguration(param1ByteBuffer.getInt(), param1ByteBuffer.getInt(), param1ByteBuffer.getInt(), param1ByteBuffer.getInt(), param1ByteBuffer.getInt());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableHighSpeedVideoConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */