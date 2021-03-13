package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.Size;
import java.nio.ByteBuffer;

public class MarshalQueryableSize implements MarshalQueryable<Size> {
  private static final int SIZE = 8;
  
  public Marshaler<Size> createMarshaler(TypeReference<Size> paramTypeReference, int paramInt) {
    return new MarshalerSize(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<Size> paramTypeReference, int paramInt) {
    boolean bool = true;
    if (paramInt != 1 || !Size.class.equals(paramTypeReference.getType()))
      bool = false; 
    return bool;
  }
  
  private class MarshalerSize extends Marshaler<Size> {
    protected MarshalerSize(TypeReference<Size> param1TypeReference, int param1Int) {
      super(MarshalQueryableSize.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 8;
    }
    
    public void marshal(Size param1Size, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putInt(param1Size.getWidth());
      param1ByteBuffer.putInt(param1Size.getHeight());
    }
    
    public Size unmarshal(ByteBuffer param1ByteBuffer) {
      return new Size(param1ByteBuffer.getInt(), param1ByteBuffer.getInt());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableSize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */