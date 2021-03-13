package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.StreamConfiguration;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableStreamConfiguration implements MarshalQueryable<StreamConfiguration> {
  private static final int SIZE = 16;
  
  public Marshaler<StreamConfiguration> createMarshaler(TypeReference<StreamConfiguration> paramTypeReference, int paramInt) {
    return new MarshalerStreamConfiguration(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<StreamConfiguration> paramTypeReference, int paramInt) {
    boolean bool = true;
    if (paramInt != 1 || !paramTypeReference.getType().equals(StreamConfiguration.class))
      bool = false; 
    return bool;
  }
  
  private class MarshalerStreamConfiguration extends Marshaler<StreamConfiguration> {
    protected MarshalerStreamConfiguration(TypeReference<StreamConfiguration> param1TypeReference, int param1Int) {
      super(MarshalQueryableStreamConfiguration.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 16;
    }
    
    public void marshal(StreamConfiguration param1StreamConfiguration, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putInt(param1StreamConfiguration.getFormat());
      param1ByteBuffer.putInt(param1StreamConfiguration.getWidth());
      param1ByteBuffer.putInt(param1StreamConfiguration.getHeight());
      param1ByteBuffer.putInt(param1StreamConfiguration.isInput());
    }
    
    public StreamConfiguration unmarshal(ByteBuffer param1ByteBuffer) {
      boolean bool;
      int i = param1ByteBuffer.getInt();
      int j = param1ByteBuffer.getInt();
      int k = param1ByteBuffer.getInt();
      if (param1ByteBuffer.getInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return new StreamConfiguration(i, j, k, bool);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableStreamConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */