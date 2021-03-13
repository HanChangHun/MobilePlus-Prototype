package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableBoolean implements MarshalQueryable<Boolean> {
  public Marshaler<Boolean> createMarshaler(TypeReference<Boolean> paramTypeReference, int paramInt) {
    return new MarshalerBoolean(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<Boolean> paramTypeReference, int paramInt) {
    boolean bool;
    if ((Boolean.class.equals(paramTypeReference.getType()) || boolean.class.equals(paramTypeReference.getType())) && paramInt == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private class MarshalerBoolean extends Marshaler<Boolean> {
    protected MarshalerBoolean(TypeReference<Boolean> param1TypeReference, int param1Int) {
      super(MarshalQueryableBoolean.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 1;
    }
    
    public void marshal(Boolean param1Boolean, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.put((byte)param1Boolean.booleanValue());
    }
    
    public Boolean unmarshal(ByteBuffer param1ByteBuffer) {
      boolean bool;
      if (param1ByteBuffer.get() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return Boolean.valueOf(bool);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */