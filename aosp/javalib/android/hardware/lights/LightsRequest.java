package android.hardware.lights;

import android.annotation.SystemApi;
import android.util.SparseArray;
import com.android.internal.util.Preconditions;

@SystemApi
public final class LightsRequest {
  final int[] mLightIds;
  
  final LightState[] mLightStates;
  
  private LightsRequest(SparseArray<LightState> paramSparseArray) {
    int i = paramSparseArray.size();
    this.mLightIds = new int[i];
    this.mLightStates = new LightState[i];
    for (byte b = 0; b < i; b++) {
      this.mLightIds[b] = paramSparseArray.keyAt(b);
      this.mLightStates[b] = (LightState)paramSparseArray.valueAt(b);
    } 
  }
  
  public static final class Builder {
    private final SparseArray<LightState> mChanges = new SparseArray();
    
    public LightsRequest build() {
      return new LightsRequest(this.mChanges);
    }
    
    public Builder clearLight(Light param1Light) {
      Preconditions.checkNotNull(param1Light);
      this.mChanges.put(param1Light.getId(), null);
      return this;
    }
    
    public Builder setLight(Light param1Light, LightState param1LightState) {
      Preconditions.checkNotNull(param1Light);
      Preconditions.checkNotNull(param1LightState);
      this.mChanges.put(param1Light.getId(), param1LightState);
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/LightsRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */