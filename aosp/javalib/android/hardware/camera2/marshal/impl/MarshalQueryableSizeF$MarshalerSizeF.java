package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.SizeF;
import java.nio.ByteBuffer;

class MarshalerSizeF extends Marshaler<SizeF> {
  protected MarshalerSizeF(TypeReference<SizeF> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableSizeF, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 8;
  }
  
  public void marshal(SizeF paramSizeF, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putFloat(paramSizeF.getWidth());
    paramByteBuffer.putFloat(paramSizeF.getHeight());
  }
  
  public SizeF unmarshal(ByteBuffer paramByteBuffer) {
    return new SizeF(paramByteBuffer.getFloat(), paramByteBuffer.getFloat());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableSizeF$MarshalerSizeF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */