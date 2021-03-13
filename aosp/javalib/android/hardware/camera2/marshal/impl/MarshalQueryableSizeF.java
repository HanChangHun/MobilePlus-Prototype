package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.SizeF;
import java.nio.ByteBuffer;

public class MarshalQueryableSizeF implements MarshalQueryable<SizeF> {
  private static final int SIZE = 8;
  
  public Marshaler<SizeF> createMarshaler(TypeReference<SizeF> paramTypeReference, int paramInt) {
    return new MarshalerSizeF(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<SizeF> paramTypeReference, int paramInt) {
    boolean bool;
    if (paramInt == 2 && SizeF.class.equals(paramTypeReference.getType())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private class MarshalerSizeF extends Marshaler<SizeF> {
    protected MarshalerSizeF(TypeReference<SizeF> param1TypeReference, int param1Int) {
      super(MarshalQueryableSizeF.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 8;
    }
    
    public void marshal(SizeF param1SizeF, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putFloat(param1SizeF.getWidth());
      param1ByteBuffer.putFloat(param1SizeF.getHeight());
    }
    
    public SizeF unmarshal(ByteBuffer param1ByteBuffer) {
      return new SizeF(param1ByteBuffer.getFloat(), param1ByteBuffer.getFloat());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableSizeF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */