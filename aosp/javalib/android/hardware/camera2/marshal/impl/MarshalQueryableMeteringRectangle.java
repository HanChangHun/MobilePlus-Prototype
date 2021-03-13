package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableMeteringRectangle implements MarshalQueryable<MeteringRectangle> {
  private static final int SIZE = 20;
  
  public Marshaler<MeteringRectangle> createMarshaler(TypeReference<MeteringRectangle> paramTypeReference, int paramInt) {
    return new MarshalerMeteringRectangle(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<MeteringRectangle> paramTypeReference, int paramInt) {
    boolean bool = true;
    if (paramInt != 1 || !MeteringRectangle.class.equals(paramTypeReference.getType()))
      bool = false; 
    return bool;
  }
  
  private class MarshalerMeteringRectangle extends Marshaler<MeteringRectangle> {
    protected MarshalerMeteringRectangle(TypeReference<MeteringRectangle> param1TypeReference, int param1Int) {
      super(MarshalQueryableMeteringRectangle.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 20;
    }
    
    public void marshal(MeteringRectangle param1MeteringRectangle, ByteBuffer param1ByteBuffer) {
      int i = param1MeteringRectangle.getX();
      int j = param1MeteringRectangle.getY();
      int k = param1MeteringRectangle.getWidth();
      int m = param1MeteringRectangle.getHeight();
      int n = param1MeteringRectangle.getMeteringWeight();
      param1ByteBuffer.putInt(i);
      param1ByteBuffer.putInt(j);
      param1ByteBuffer.putInt(k + i);
      param1ByteBuffer.putInt(m + j);
      param1ByteBuffer.putInt(n);
    }
    
    public MeteringRectangle unmarshal(ByteBuffer param1ByteBuffer) {
      int i = param1ByteBuffer.getInt();
      int j = param1ByteBuffer.getInt();
      return new MeteringRectangle(i, j, param1ByteBuffer.getInt() - i, param1ByteBuffer.getInt() - j, param1ByteBuffer.getInt());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableMeteringRectangle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */