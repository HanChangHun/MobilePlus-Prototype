package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.utils.TypeReference;

public final class Key<T> {
  private final CameraMetadataNative.Key<T> mKey;
  
  Key(CameraMetadataNative.Key<?> paramKey) {
    this.mKey = (CameraMetadataNative.Key)paramKey;
  }
  
  public Key(String paramString, TypeReference<T> paramTypeReference) {
    this.mKey = new CameraMetadataNative.Key(paramString, paramTypeReference);
  }
  
  public Key(String paramString, Class<T> paramClass) {
    this.mKey = new CameraMetadataNative.Key(paramString, paramClass);
  }
  
  public Key(String paramString, Class<T> paramClass, long paramLong) {
    this.mKey = new CameraMetadataNative.Key(paramString, paramClass, paramLong);
  }
  
  public Key(String paramString1, String paramString2, Class<T> paramClass) {
    this.mKey = new CameraMetadataNative.Key(paramString1, paramString2, paramClass);
  }
  
  public final boolean equals(Object paramObject) {
    boolean bool;
    if (paramObject instanceof Key && ((Key)paramObject).mKey.equals(this.mKey)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String getName() {
    return this.mKey.getName();
  }
  
  public CameraMetadataNative.Key<T> getNativeKey() {
    return this.mKey;
  }
  
  public long getVendorId() {
    return this.mKey.getVendorId();
  }
  
  public final int hashCode() {
    return this.mKey.hashCode();
  }
  
  public String toString() {
    return String.format("CaptureResult.Key(%s)", new Object[] { this.mKey.getName() });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CaptureResult$Key.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */