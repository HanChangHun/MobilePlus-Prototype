package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.RggbChannelVector;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableRggbChannelVector implements MarshalQueryable<RggbChannelVector> {
  private static final int SIZE = 16;
  
  public Marshaler<RggbChannelVector> createMarshaler(TypeReference<RggbChannelVector> paramTypeReference, int paramInt) {
    return new MarshalerRggbChannelVector(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<RggbChannelVector> paramTypeReference, int paramInt) {
    boolean bool;
    if (paramInt == 2 && RggbChannelVector.class.equals(paramTypeReference.getType())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private class MarshalerRggbChannelVector extends Marshaler<RggbChannelVector> {
    protected MarshalerRggbChannelVector(TypeReference<RggbChannelVector> param1TypeReference, int param1Int) {
      super(MarshalQueryableRggbChannelVector.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 16;
    }
    
    public void marshal(RggbChannelVector param1RggbChannelVector, ByteBuffer param1ByteBuffer) {
      for (byte b = 0; b < 4; b++)
        param1ByteBuffer.putFloat(param1RggbChannelVector.getComponent(b)); 
    }
    
    public RggbChannelVector unmarshal(ByteBuffer param1ByteBuffer) {
      return new RggbChannelVector(param1ByteBuffer.getFloat(), param1ByteBuffer.getFloat(), param1ByteBuffer.getFloat(), param1ByteBuffer.getFloat());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableRggbChannelVector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */