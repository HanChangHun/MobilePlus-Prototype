package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.StreamConfigurationDuration;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerStreamConfigurationDuration extends Marshaler<StreamConfigurationDuration> {
  protected MarshalerStreamConfigurationDuration(TypeReference<StreamConfigurationDuration> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableStreamConfigurationDuration, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 32;
  }
  
  public void marshal(StreamConfigurationDuration paramStreamConfigurationDuration, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putLong(paramStreamConfigurationDuration.getFormat() & 0xFFFFFFFFL);
    paramByteBuffer.putLong(paramStreamConfigurationDuration.getWidth());
    paramByteBuffer.putLong(paramStreamConfigurationDuration.getHeight());
    paramByteBuffer.putLong(paramStreamConfigurationDuration.getDuration());
  }
  
  public StreamConfigurationDuration unmarshal(ByteBuffer paramByteBuffer) {
    return new StreamConfigurationDuration((int)paramByteBuffer.getLong(), (int)paramByteBuffer.getLong(), (int)paramByteBuffer.getLong(), paramByteBuffer.getLong());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableStreamConfigurationDuration$MarshalerStreamConfigurationDuration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */