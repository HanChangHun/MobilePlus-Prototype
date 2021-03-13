package android.hardware.camera2.marshal.impl;

import android.graphics.Rect;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerRect extends Marshaler<Rect> {
  protected MarshalerRect(TypeReference<Rect> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableRect, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 16;
  }
  
  public void marshal(Rect paramRect, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putInt(paramRect.left);
    paramByteBuffer.putInt(paramRect.top);
    paramByteBuffer.putInt(paramRect.width());
    paramByteBuffer.putInt(paramRect.height());
  }
  
  public Rect unmarshal(ByteBuffer paramByteBuffer) {
    int i = paramByteBuffer.getInt();
    int j = paramByteBuffer.getInt();
    return new Rect(i, j, i + paramByteBuffer.getInt(), j + paramByteBuffer.getInt());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableRect$MarshalerRect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */