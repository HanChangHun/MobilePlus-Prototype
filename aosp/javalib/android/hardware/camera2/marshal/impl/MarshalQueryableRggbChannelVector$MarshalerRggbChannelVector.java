package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.RggbChannelVector;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerRggbChannelVector extends Marshaler<RggbChannelVector> {
  protected MarshalerRggbChannelVector(TypeReference<RggbChannelVector> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableRggbChannelVector, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 16;
  }
  
  public void marshal(RggbChannelVector paramRggbChannelVector, ByteBuffer paramByteBuffer) {
    for (byte b = 0; b < 4; b++)
      paramByteBuffer.putFloat(paramRggbChannelVector.getComponent(b)); 
  }
  
  public RggbChannelVector unmarshal(ByteBuffer paramByteBuffer) {
    return new RggbChannelVector(paramByteBuffer.getFloat(), paramByteBuffer.getFloat(), paramByteBuffer.getFloat(), paramByteBuffer.getFloat());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableRggbChannelVector$MarshalerRggbChannelVector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */