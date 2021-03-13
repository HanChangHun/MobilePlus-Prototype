package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.Size;
import java.nio.ByteBuffer;

class MarshalerSize extends Marshaler<Size> {
  protected MarshalerSize(TypeReference<Size> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableSize, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 8;
  }
  
  public void marshal(Size paramSize, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putInt(paramSize.getWidth());
    paramByteBuffer.putInt(paramSize.getHeight());
  }
  
  public Size unmarshal(ByteBuffer paramByteBuffer) {
    return new Size(paramByteBuffer.getInt(), paramByteBuffer.getInt());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableSize$MarshalerSize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */