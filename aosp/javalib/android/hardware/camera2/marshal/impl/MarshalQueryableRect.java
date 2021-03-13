package android.hardware.camera2.marshal.impl;

import android.graphics.Rect;
import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableRect implements MarshalQueryable<Rect> {
  private static final int SIZE = 16;
  
  public Marshaler<Rect> createMarshaler(TypeReference<Rect> paramTypeReference, int paramInt) {
    return new MarshalerRect(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<Rect> paramTypeReference, int paramInt) {
    boolean bool = true;
    if (paramInt != 1 || !Rect.class.equals(paramTypeReference.getType()))
      bool = false; 
    return bool;
  }
  
  private class MarshalerRect extends Marshaler<Rect> {
    protected MarshalerRect(TypeReference<Rect> param1TypeReference, int param1Int) {
      super(MarshalQueryableRect.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 16;
    }
    
    public void marshal(Rect param1Rect, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putInt(param1Rect.left);
      param1ByteBuffer.putInt(param1Rect.top);
      param1ByteBuffer.putInt(param1Rect.width());
      param1ByteBuffer.putInt(param1Rect.height());
    }
    
    public Rect unmarshal(ByteBuffer param1ByteBuffer) {
      int i = param1ByteBuffer.getInt();
      int j = param1ByteBuffer.getInt();
      return new Rect(i, j, i + param1ByteBuffer.getInt(), j + param1ByteBuffer.getInt());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableRect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */