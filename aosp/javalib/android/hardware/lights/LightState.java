package android.hardware.lights;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class LightState implements Parcelable {
  public static final Parcelable.Creator<LightState> CREATOR = new Parcelable.Creator<LightState>() {
      public LightState createFromParcel(Parcel param1Parcel) {
        return new LightState(param1Parcel);
      }
      
      public LightState[] newArray(int param1Int) {
        return new LightState[param1Int];
      }
    };
  
  private final int mColor;
  
  public LightState(int paramInt) {
    this.mColor = paramInt;
  }
  
  private LightState(Parcel paramParcel) {
    this.mColor = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getColor() {
    return this.mColor;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mColor);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/LightState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */