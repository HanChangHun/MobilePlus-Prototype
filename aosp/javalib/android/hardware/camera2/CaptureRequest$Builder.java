package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.view.Surface;
import java.util.Set;

public final class Builder {
  private final CaptureRequest mRequest;
  
  public Builder(CameraMetadataNative paramCameraMetadataNative, boolean paramBoolean, int paramInt, String paramString, Set<String> paramSet) {
    this.mRequest = new CaptureRequest(paramCameraMetadataNative, paramBoolean, paramInt, paramString, paramSet, null);
  }
  
  public void addTarget(Surface paramSurface) {
    CaptureRequest.access$300(this.mRequest).add(paramSurface);
  }
  
  public CaptureRequest build() {
    return new CaptureRequest(this.mRequest, null);
  }
  
  public <T> T get(CaptureRequest.Key<T> paramKey) {
    return (T)CaptureRequest.access$400(this.mRequest).get(paramKey);
  }
  
  public <T> T getPhysicalCameraKey(CaptureRequest.Key<T> paramKey, String paramString) {
    if (CaptureRequest.access$500(this.mRequest).containsKey(paramString))
      return (T)((CameraMetadataNative)CaptureRequest.access$500(this.mRequest).get(paramString)).get(paramKey); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Physical camera id: ");
    stringBuilder.append(paramString);
    stringBuilder.append(" is not valid!");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean isEmpty() {
    return CaptureRequest.access$400(this.mRequest).isEmpty();
  }
  
  public void removeTarget(Surface paramSurface) {
    CaptureRequest.access$300(this.mRequest).remove(paramSurface);
  }
  
  public <T> void set(CaptureRequest.Key<T> paramKey, T paramT) {
    CaptureRequest.access$400(this.mRequest).set(paramKey, paramT);
  }
  
  public void setPartOfCHSRequestList(boolean paramBoolean) {
    CaptureRequest.access$702(this.mRequest, paramBoolean);
  }
  
  public <T> Builder setPhysicalCameraKey(CaptureRequest.Key<T> paramKey, T paramT, String paramString) {
    if (CaptureRequest.access$500(this.mRequest).containsKey(paramString)) {
      ((CameraMetadataNative)CaptureRequest.access$500(this.mRequest).get(paramString)).set(paramKey, paramT);
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Physical camera id: ");
    stringBuilder.append(paramString);
    stringBuilder.append(" is not valid!");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setTag(Object paramObject) {
    CaptureRequest.access$602(this.mRequest, paramObject);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CaptureRequest$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */