package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.BlackLevelPattern;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableBlackLevelPattern implements MarshalQueryable<BlackLevelPattern> {
  private static final int SIZE = 16;
  
  public Marshaler<BlackLevelPattern> createMarshaler(TypeReference<BlackLevelPattern> paramTypeReference, int paramInt) {
    return new MarshalerBlackLevelPattern(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<BlackLevelPattern> paramTypeReference, int paramInt) {
    boolean bool = true;
    if (paramInt != 1 || !BlackLevelPattern.class.equals(paramTypeReference.getType()))
      bool = false; 
    return bool;
  }
  
  private class MarshalerBlackLevelPattern extends Marshaler<BlackLevelPattern> {
    protected MarshalerBlackLevelPattern(TypeReference<BlackLevelPattern> param1TypeReference, int param1Int) {
      super(MarshalQueryableBlackLevelPattern.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 16;
    }
    
    public void marshal(BlackLevelPattern param1BlackLevelPattern, ByteBuffer param1ByteBuffer) {
      for (byte b = 0; b < 2; b++) {
        for (byte b1 = 0; b1 < 2; b1++)
          param1ByteBuffer.putInt(param1BlackLevelPattern.getOffsetForIndex(b1, b)); 
      } 
    }
    
    public BlackLevelPattern unmarshal(ByteBuffer param1ByteBuffer) {
      int[] arrayOfInt = new int[4];
      for (byte b = 0; b < 4; b++)
        arrayOfInt[b] = param1ByteBuffer.getInt(); 
      return new BlackLevelPattern(arrayOfInt);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableBlackLevelPattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */