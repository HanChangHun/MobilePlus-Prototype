package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableColorSpaceTransform implements MarshalQueryable<ColorSpaceTransform> {
  private static final int ELEMENTS_INT32 = 18;
  
  private static final int SIZE = 72;
  
  public Marshaler<ColorSpaceTransform> createMarshaler(TypeReference<ColorSpaceTransform> paramTypeReference, int paramInt) {
    return new MarshalerColorSpaceTransform(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<ColorSpaceTransform> paramTypeReference, int paramInt) {
    boolean bool;
    if (paramInt == 5 && ColorSpaceTransform.class.equals(paramTypeReference.getType())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private class MarshalerColorSpaceTransform extends Marshaler<ColorSpaceTransform> {
    protected MarshalerColorSpaceTransform(TypeReference<ColorSpaceTransform> param1TypeReference, int param1Int) {
      super(MarshalQueryableColorSpaceTransform.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 72;
    }
    
    public void marshal(ColorSpaceTransform param1ColorSpaceTransform, ByteBuffer param1ByteBuffer) {
      int[] arrayOfInt = new int[18];
      param1ColorSpaceTransform.copyElements(arrayOfInt, 0);
      for (byte b = 0; b < 18; b++)
        param1ByteBuffer.putInt(arrayOfInt[b]); 
    }
    
    public ColorSpaceTransform unmarshal(ByteBuffer param1ByteBuffer) {
      int[] arrayOfInt = new int[18];
      for (byte b = 0; b < 18; b++)
        arrayOfInt[b] = param1ByteBuffer.getInt(); 
      return new ColorSpaceTransform(arrayOfInt);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableColorSpaceTransform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */