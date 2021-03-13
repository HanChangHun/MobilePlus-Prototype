package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.StreamConfiguration;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerStreamConfiguration extends Marshaler<StreamConfiguration> {
  protected MarshalerStreamConfiguration(TypeReference<StreamConfiguration> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableStreamConfiguration, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 16;
  }
  
  public void marshal(StreamConfiguration paramStreamConfiguration, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putInt(paramStreamConfiguration.getFormat());
    paramByteBuffer.putInt(paramStreamConfiguration.getWidth());
    paramByteBuffer.putInt(paramStreamConfiguration.getHeight());
    paramByteBuffer.putInt(paramStreamConfiguration.isInput());
  }
  
  public StreamConfiguration unmarshal(ByteBuffer paramByteBuffer) {
    boolean bool;
    int i = paramByteBuffer.getInt();
    int j = paramByteBuffer.getInt();
    int k = paramByteBuffer.getInt();
    if (paramByteBuffer.getInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return new StreamConfiguration(i, j, k, bool);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableStreamConfiguration$MarshalerStreamConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */