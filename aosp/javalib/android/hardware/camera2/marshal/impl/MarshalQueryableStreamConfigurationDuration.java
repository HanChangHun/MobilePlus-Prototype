package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.StreamConfigurationDuration;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableStreamConfigurationDuration implements MarshalQueryable<StreamConfigurationDuration> {
  private static final long MASK_UNSIGNED_INT = 4294967295L;
  
  private static final int SIZE = 32;
  
  public Marshaler<StreamConfigurationDuration> createMarshaler(TypeReference<StreamConfigurationDuration> paramTypeReference, int paramInt) {
    return new MarshalerStreamConfigurationDuration(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<StreamConfigurationDuration> paramTypeReference, int paramInt) {
    boolean bool;
    if (paramInt == 3 && StreamConfigurationDuration.class.equals(paramTypeReference.getType())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private class MarshalerStreamConfigurationDuration extends Marshaler<StreamConfigurationDuration> {
    protected MarshalerStreamConfigurationDuration(TypeReference<StreamConfigurationDuration> param1TypeReference, int param1Int) {
      super(MarshalQueryableStreamConfigurationDuration.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 32;
    }
    
    public void marshal(StreamConfigurationDuration param1StreamConfigurationDuration, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putLong(param1StreamConfigurationDuration.getFormat() & 0xFFFFFFFFL);
      param1ByteBuffer.putLong(param1StreamConfigurationDuration.getWidth());
      param1ByteBuffer.putLong(param1StreamConfigurationDuration.getHeight());
      param1ByteBuffer.putLong(param1StreamConfigurationDuration.getDuration());
    }
    
    public StreamConfigurationDuration unmarshal(ByteBuffer param1ByteBuffer) {
      return new StreamConfigurationDuration((int)param1ByteBuffer.getLong(), (int)param1ByteBuffer.getLong(), (int)param1ByteBuffer.getLong(), param1ByteBuffer.getLong());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableStreamConfigurationDuration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */