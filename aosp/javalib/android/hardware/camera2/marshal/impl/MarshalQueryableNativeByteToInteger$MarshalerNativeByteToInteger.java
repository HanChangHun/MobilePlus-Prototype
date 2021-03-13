package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerNativeByteToInteger extends Marshaler<Integer> {
  protected MarshalerNativeByteToInteger(TypeReference<Integer> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableNativeByteToInteger, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 1;
  }
  
  public void marshal(Integer paramInteger, ByteBuffer paramByteBuffer) {
    paramByteBuffer.put((byte)paramInteger.intValue());
  }
  
  public Integer unmarshal(ByteBuffer paramByteBuffer) {
    return Integer.valueOf(paramByteBuffer.get() & 0xFF);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableNativeByteToInteger$MarshalerNativeByteToInteger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */