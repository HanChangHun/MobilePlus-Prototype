package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.RecommendedStreamConfiguration;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableRecommendedStreamConfiguration implements MarshalQueryable<RecommendedStreamConfiguration> {
  private static final int SIZE = 20;
  
  public Marshaler<RecommendedStreamConfiguration> createMarshaler(TypeReference<RecommendedStreamConfiguration> paramTypeReference, int paramInt) {
    return new MarshalerRecommendedStreamConfiguration(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<RecommendedStreamConfiguration> paramTypeReference, int paramInt) {
    boolean bool = true;
    if (paramInt != 1 || !paramTypeReference.getType().equals(RecommendedStreamConfiguration.class))
      bool = false; 
    return bool;
  }
  
  private class MarshalerRecommendedStreamConfiguration extends Marshaler<RecommendedStreamConfiguration> {
    protected MarshalerRecommendedStreamConfiguration(TypeReference<RecommendedStreamConfiguration> param1TypeReference, int param1Int) {
      super(MarshalQueryableRecommendedStreamConfiguration.this, param1TypeReference, param1Int);
    }
    
    public int getNativeSize() {
      return 20;
    }
    
    public void marshal(RecommendedStreamConfiguration param1RecommendedStreamConfiguration, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putInt(param1RecommendedStreamConfiguration.getWidth());
      param1ByteBuffer.putInt(param1RecommendedStreamConfiguration.getHeight());
      param1ByteBuffer.putInt(param1RecommendedStreamConfiguration.getFormat());
      param1ByteBuffer.putInt(param1RecommendedStreamConfiguration.isInput());
      param1ByteBuffer.putInt(param1RecommendedStreamConfiguration.getUsecaseBitmap());
    }
    
    public RecommendedStreamConfiguration unmarshal(ByteBuffer param1ByteBuffer) {
      boolean bool;
      int i = param1ByteBuffer.getInt();
      int j = param1ByteBuffer.getInt();
      int k = param1ByteBuffer.getInt();
      if (param1ByteBuffer.getInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return new RecommendedStreamConfiguration(k, i, j, bool, param1ByteBuffer.getInt());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableRecommendedStreamConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */