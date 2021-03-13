package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerColorSpaceTransform extends Marshaler<ColorSpaceTransform> {
  protected MarshalerColorSpaceTransform(TypeReference<ColorSpaceTransform> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableColorSpaceTransform, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 72;
  }
  
  public void marshal(ColorSpaceTransform paramColorSpaceTransform, ByteBuffer paramByteBuffer) {
    int[] arrayOfInt = new int[18];
    paramColorSpaceTransform.copyElements(arrayOfInt, 0);
    for (byte b = 0; b < 18; b++)
      paramByteBuffer.putInt(arrayOfInt[b]); 
  }
  
  public ColorSpaceTransform unmarshal(ByteBuffer paramByteBuffer) {
    int[] arrayOfInt = new int[18];
    for (byte b = 0; b < 18; b++)
      arrayOfInt[b] = paramByteBuffer.getInt(); 
    return new ColorSpaceTransform(arrayOfInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableColorSpaceTransform$MarshalerColorSpaceTransform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */