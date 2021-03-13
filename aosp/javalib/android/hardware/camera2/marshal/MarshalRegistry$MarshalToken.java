package android.hardware.camera2.marshal;

import android.hardware.camera2.utils.TypeReference;

class MarshalToken<T> {
  private final int hash;
  
  final int nativeType;
  
  final TypeReference<T> typeReference;
  
  public MarshalToken(TypeReference<T> paramTypeReference, int paramInt) {
    this.typeReference = paramTypeReference;
    this.nativeType = paramInt;
    this.hash = paramTypeReference.hashCode() ^ paramInt;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof MarshalToken;
    boolean bool1 = false;
    if (bool) {
      paramObject = paramObject;
      bool = bool1;
      if (this.typeReference.equals(((MarshalToken)paramObject).typeReference)) {
        bool = bool1;
        if (this.nativeType == ((MarshalToken)paramObject).nativeType)
          bool = true; 
      } 
      return bool;
    } 
    return false;
  }
  
  public int hashCode() {
    return this.hash;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/MarshalRegistry$MarshalToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */