package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.BlackLevelPattern;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerBlackLevelPattern extends Marshaler<BlackLevelPattern> {
  protected MarshalerBlackLevelPattern(TypeReference<BlackLevelPattern> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableBlackLevelPattern, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 16;
  }
  
  public void marshal(BlackLevelPattern paramBlackLevelPattern, ByteBuffer paramByteBuffer) {
    for (byte b = 0; b < 2; b++) {
      for (byte b1 = 0; b1 < 2; b1++)
        paramByteBuffer.putInt(paramBlackLevelPattern.getOffsetForIndex(b1, b)); 
    } 
  }
  
  public BlackLevelPattern unmarshal(ByteBuffer paramByteBuffer) {
    int[] arrayOfInt = new int[4];
    for (byte b = 0; b < 4; b++)
      arrayOfInt[b] = paramByteBuffer.getInt(); 
    return new BlackLevelPattern(arrayOfInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableBlackLevelPattern$MarshalerBlackLevelPattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */