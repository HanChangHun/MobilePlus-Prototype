package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableNativeByteToInteger implements MarshalQueryable<Integer> {
  private static final int UINT8_MASK = 255;
  
  public Marshaler<Integer> createMarshaler(TypeReference<Integer> paramTypeReference, int paramInt) {
    return new MarshalerNativeByteToInteger(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<Integer> paramTypeReference, int paramInt) {
    boolean bool;
    if ((Integer.class.equals(paramTypeReference.getType()) || int.class.equals(paramTypeReference.getType())) && paramInt == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private class MarshalerNativeByteToInteger extends Marshaler<Integer> {
    protected MarshalerNativeByteToInteger(TypeReference<Integer> param1TypeReference, int param1Int) {
      super(MarshalQueryableNativeByteToInteger.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 1;
    }
    
    public void marshal(Integer param1Integer, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.put((byte)param1Integer.intValue());
    }
    
    public Integer unmarshal(ByteBuffer param1ByteBuffer) {
      return Integer.valueOf(param1ByteBuffer.get() & 0xFF);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableNativeByteToInteger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */