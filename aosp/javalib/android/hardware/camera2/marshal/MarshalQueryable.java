package android.hardware.camera2.marshal;

import android.hardware.camera2.utils.TypeReference;

public interface MarshalQueryable<T> {
  Marshaler<T> createMarshaler(TypeReference<T> paramTypeReference, int paramInt);
  
  boolean isTypeMappingSupported(TypeReference<T> paramTypeReference, int paramInt);
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/MarshalQueryable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */