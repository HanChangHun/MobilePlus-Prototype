package android.hardware.lights;

import android.util.SparseArray;
import com.android.internal.util.Preconditions;

public final class Builder {
  private final SparseArray<LightState> mChanges = new SparseArray();
  
  public LightsRequest build() {
    return new LightsRequest(this.mChanges, null);
  }
  
  public Builder clearLight(Light paramLight) {
    Preconditions.checkNotNull(paramLight);
    this.mChanges.put(paramLight.getId(), null);
    return this;
  }
  
  public Builder setLight(Light paramLight, LightState paramLightState) {
    Preconditions.checkNotNull(paramLight);
    Preconditions.checkNotNull(paramLightState);
    this.mChanges.put(paramLight.getId(), paramLightState);
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/LightsRequest$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */