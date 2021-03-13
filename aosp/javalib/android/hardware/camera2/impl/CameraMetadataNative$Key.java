package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.utils.TypeReference;

public class Key<T> {
  private final String mFallbackName;
  
  private boolean mHasTag;
  
  private final int mHash;
  
  private final String mName;
  
  private int mTag;
  
  private final Class<T> mType;
  
  private final TypeReference<T> mTypeReference;
  
  private long mVendorId = Long.MAX_VALUE;
  
  public Key(String paramString, TypeReference<T> paramTypeReference) {
    if (paramString != null) {
      if (paramTypeReference != null) {
        this.mName = paramString;
        this.mFallbackName = null;
        this.mType = paramTypeReference.getRawType();
        this.mTypeReference = paramTypeReference;
        this.mHash = this.mName.hashCode() ^ this.mTypeReference.hashCode();
        return;
      } 
      throw new NullPointerException("TypeReference needs to be non-null");
    } 
    throw new NullPointerException("Key needs a valid name");
  }
  
  public Key(String paramString, Class<T> paramClass) {
    if (paramString != null) {
      if (paramClass != null) {
        this.mName = paramString;
        this.mFallbackName = null;
        this.mType = paramClass;
        this.mTypeReference = TypeReference.createSpecializedTypeReference(paramClass);
        this.mHash = this.mName.hashCode() ^ this.mTypeReference.hashCode();
        return;
      } 
      throw new NullPointerException("Type needs to be non-null");
    } 
    throw new NullPointerException("Key needs a valid name");
  }
  
  public Key(String paramString, Class<T> paramClass, long paramLong) {
    if (paramString != null) {
      if (paramClass != null) {
        this.mName = paramString;
        this.mFallbackName = null;
        this.mType = paramClass;
        this.mVendorId = paramLong;
        this.mTypeReference = TypeReference.createSpecializedTypeReference(paramClass);
        this.mHash = this.mName.hashCode() ^ this.mTypeReference.hashCode();
        return;
      } 
      throw new NullPointerException("Type needs to be non-null");
    } 
    throw new NullPointerException("Key needs a valid name");
  }
  
  public Key(String paramString1, String paramString2, Class<T> paramClass) {
    if (paramString1 != null) {
      if (paramClass != null) {
        this.mName = paramString1;
        this.mFallbackName = paramString2;
        this.mType = paramClass;
        this.mTypeReference = TypeReference.createSpecializedTypeReference(paramClass);
        this.mHash = this.mName.hashCode() ^ this.mTypeReference.hashCode();
        return;
      } 
      throw new NullPointerException("Type needs to be non-null");
    } 
    throw new NullPointerException("Key needs a valid name");
  }
  
  public final void cacheTag(int paramInt) {
    this.mHasTag = true;
    this.mTag = paramInt;
  }
  
  public final boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || hashCode() != paramObject.hashCode())
      return false; 
    if (paramObject instanceof CaptureResult.Key) {
      paramObject = ((CaptureResult.Key)paramObject).getNativeKey();
    } else if (paramObject instanceof CaptureRequest.Key) {
      paramObject = ((CaptureRequest.Key)paramObject).getNativeKey();
    } else if (paramObject instanceof CameraCharacteristics.Key) {
      paramObject = ((CameraCharacteristics.Key)paramObject).getNativeKey();
    } else if (paramObject instanceof Key) {
      paramObject = paramObject;
    } else {
      return false;
    } 
    if (!this.mName.equals(((Key)paramObject).mName) || !this.mTypeReference.equals(((Key)paramObject).mTypeReference))
      bool = false; 
    return bool;
  }
  
  public final String getName() {
    return this.mName;
  }
  
  public final int getTag() {
    if (!this.mHasTag) {
      this.mTag = CameraMetadataNative.getTag(this.mName, this.mVendorId);
      this.mHasTag = true;
    } 
    return this.mTag;
  }
  
  public final Class<T> getType() {
    return this.mType;
  }
  
  public final TypeReference<T> getTypeReference() {
    return this.mTypeReference;
  }
  
  public final long getVendorId() {
    return this.mVendorId;
  }
  
  public final boolean hasTag() {
    return this.mHasTag;
  }
  
  public final int hashCode() {
    return this.mHash;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraMetadataNative$Key.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */