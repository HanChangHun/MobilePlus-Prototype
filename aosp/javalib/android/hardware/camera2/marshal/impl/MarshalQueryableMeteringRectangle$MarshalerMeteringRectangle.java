package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerMeteringRectangle extends Marshaler<MeteringRectangle> {
  protected MarshalerMeteringRectangle(TypeReference<MeteringRectangle> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableMeteringRectangle, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 20;
  }
  
  public void marshal(MeteringRectangle paramMeteringRectangle, ByteBuffer paramByteBuffer) {
    int i = paramMeteringRectangle.getX();
    int j = paramMeteringRectangle.getY();
    int k = paramMeteringRectangle.getWidth();
    int m = paramMeteringRectangle.getHeight();
    int n = paramMeteringRectangle.getMeteringWeight();
    paramByteBuffer.putInt(i);
    paramByteBuffer.putInt(j);
    paramByteBuffer.putInt(k + i);
    paramByteBuffer.putInt(m + j);
    paramByteBuffer.putInt(n);
  }
  
  public MeteringRectangle unmarshal(ByteBuffer paramByteBuffer) {
    int i = paramByteBuffer.getInt();
    int j = paramByteBuffer.getInt();
    return new MeteringRectangle(i, j, paramByteBuffer.getInt() - i, paramByteBuffer.getInt() - j, paramByteBuffer.getInt());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableMeteringRectangle$MarshalerMeteringRectangle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */