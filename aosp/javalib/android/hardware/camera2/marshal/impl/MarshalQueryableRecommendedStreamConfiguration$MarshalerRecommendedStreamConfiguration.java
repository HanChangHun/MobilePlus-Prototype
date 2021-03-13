package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.RecommendedStreamConfiguration;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerRecommendedStreamConfiguration extends Marshaler<RecommendedStreamConfiguration> {
  protected MarshalerRecommendedStreamConfiguration(TypeReference<RecommendedStreamConfiguration> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableRecommendedStreamConfiguration, paramTypeReference, paramInt);
  }
  
  public int getNativeSize() {
    return 20;
  }
  
  public void marshal(RecommendedStreamConfiguration paramRecommendedStreamConfiguration, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putInt(paramRecommendedStreamConfiguration.getWidth());
    paramByteBuffer.putInt(paramRecommendedStreamConfiguration.getHeight());
    paramByteBuffer.putInt(paramRecommendedStreamConfiguration.getFormat());
    paramByteBuffer.putInt(paramRecommendedStreamConfiguration.isInput());
    paramByteBuffer.putInt(paramRecommendedStreamConfiguration.getUsecaseBitmap());
  }
  
  public RecommendedStreamConfiguration unmarshal(ByteBuffer paramByteBuffer) {
    boolean bool;
    int i = paramByteBuffer.getInt();
    int j = paramByteBuffer.getInt();
    int k = paramByteBuffer.getInt();
    if (paramByteBuffer.getInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return new RecommendedStreamConfiguration(k, i, j, bool, paramByteBuffer.getInt());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableRecommendedStreamConfiguration$MarshalerRecommendedStreamConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */