package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerBoolean extends Marshaler<Boolean> {
  protected MarshalerBoolean(TypeReference<Boolean> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableBoolean, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 1;
  }
  
  public void marshal(Boolean paramBoolean, ByteBuffer paramByteBuffer) {
    paramByteBuffer.put((byte)paramBoolean.booleanValue());
  }
  
  public Boolean unmarshal(ByteBuffer paramByteBuffer) {
    boolean bool;
    if (paramByteBuffer.get() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return Boolean.valueOf(bool);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableBoolean$MarshalerBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */