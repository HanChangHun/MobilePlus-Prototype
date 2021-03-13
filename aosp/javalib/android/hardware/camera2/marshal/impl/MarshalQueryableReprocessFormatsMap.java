package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.ReprocessFormatsMap;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableReprocessFormatsMap implements MarshalQueryable<ReprocessFormatsMap> {
  public Marshaler<ReprocessFormatsMap> createMarshaler(TypeReference<ReprocessFormatsMap> paramTypeReference, int paramInt) {
    return new MarshalerReprocessFormatsMap(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<ReprocessFormatsMap> paramTypeReference, int paramInt) {
    boolean bool = true;
    if (paramInt != 1 || !paramTypeReference.getType().equals(ReprocessFormatsMap.class))
      bool = false; 
    return bool;
  }
  
  private class MarshalerReprocessFormatsMap extends Marshaler<ReprocessFormatsMap> {
    protected MarshalerReprocessFormatsMap(TypeReference<ReprocessFormatsMap> param1TypeReference, int param1Int) {
      super(MarshalQueryableReprocessFormatsMap.this, param1TypeReference, param1Int);
    }
    
    public int calculateMarshalSize(ReprocessFormatsMap param1ReprocessFormatsMap) {
      int i = 0;
      int[] arrayOfInt = param1ReprocessFormatsMap.getInputs();
      int j = arrayOfInt.length;
      for (byte b = 0; b < j; b++)
        i = i + 1 + 1 + (param1ReprocessFormatsMap.getOutputs(arrayOfInt[b])).length; 
      return i * 4;
    }
    
    public int getNativeSize() {
      return NATIVE_SIZE_DYNAMIC;
    }
    
    public void marshal(ReprocessFormatsMap param1ReprocessFormatsMap, ByteBuffer param1ByteBuffer) {
      for (int i : StreamConfigurationMap.imageFormatToInternal(param1ReprocessFormatsMap.getInputs())) {
        param1ByteBuffer.putInt(i);
        int[] arrayOfInt = StreamConfigurationMap.imageFormatToInternal(param1ReprocessFormatsMap.getOutputs(i));
        param1ByteBuffer.putInt(arrayOfInt.length);
        int j = arrayOfInt.length;
        for (i = 0; i < j; i++)
          param1ByteBuffer.putInt(arrayOfInt[i]); 
      } 
    }
    
    public ReprocessFormatsMap unmarshal(ByteBuffer param1ByteBuffer) {
      int i = param1ByteBuffer.remaining() / 4;
      if (param1ByteBuffer.remaining() % 4 == 0) {
        int[] arrayOfInt = new int[i];
        param1ByteBuffer.asIntBuffer().get(arrayOfInt);
        return new ReprocessFormatsMap(arrayOfInt);
      } 
      throw new AssertionError("ReprocessFormatsMap was not TYPE_INT32");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableReprocessFormatsMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */