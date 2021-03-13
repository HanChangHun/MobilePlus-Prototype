package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.ReprocessFormatsMap;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerReprocessFormatsMap extends Marshaler<ReprocessFormatsMap> {
  protected MarshalerReprocessFormatsMap(TypeReference<ReprocessFormatsMap> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableReprocessFormatsMap, paramTypeReference, paramInt);
  }
  
  public int calculateMarshalSize(ReprocessFormatsMap paramReprocessFormatsMap) {
    int i = 0;
    int[] arrayOfInt = paramReprocessFormatsMap.getInputs();
    int j = arrayOfInt.length;
    for (byte b = 0; b < j; b++)
      i = i + 1 + 1 + (paramReprocessFormatsMap.getOutputs(arrayOfInt[b])).length; 
    return i * 4;
  }
  
  public int getNativeSize() {
    return NATIVE_SIZE_DYNAMIC;
  }
  
  public void marshal(ReprocessFormatsMap paramReprocessFormatsMap, ByteBuffer paramByteBuffer) {
    for (int i : StreamConfigurationMap.imageFormatToInternal(paramReprocessFormatsMap.getInputs())) {
      paramByteBuffer.putInt(i);
      int[] arrayOfInt = StreamConfigurationMap.imageFormatToInternal(paramReprocessFormatsMap.getOutputs(i));
      paramByteBuffer.putInt(arrayOfInt.length);
      int j = arrayOfInt.length;
      for (i = 0; i < j; i++)
        paramByteBuffer.putInt(arrayOfInt[i]); 
    } 
  }
  
  public ReprocessFormatsMap unmarshal(ByteBuffer paramByteBuffer) {
    int i = paramByteBuffer.remaining() / 4;
    if (paramByteBuffer.remaining() % 4 == 0) {
      int[] arrayOfInt = new int[i];
      paramByteBuffer.asIntBuffer().get(arrayOfInt);
      return new ReprocessFormatsMap(arrayOfInt);
    } 
    throw new AssertionError("ReprocessFormatsMap was not TYPE_INT32");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableReprocessFormatsMap$MarshalerReprocessFormatsMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */