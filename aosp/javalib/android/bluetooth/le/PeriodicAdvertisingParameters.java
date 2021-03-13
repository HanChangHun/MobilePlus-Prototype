package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

public final class PeriodicAdvertisingParameters implements Parcelable {
  public static final Parcelable.Creator<PeriodicAdvertisingParameters> CREATOR = new Parcelable.Creator<PeriodicAdvertisingParameters>() {
      public PeriodicAdvertisingParameters createFromParcel(Parcel param1Parcel) {
        return new PeriodicAdvertisingParameters(param1Parcel);
      }
      
      public PeriodicAdvertisingParameters[] newArray(int param1Int) {
        return new PeriodicAdvertisingParameters[param1Int];
      }
    };
  
  private static final int INTERVAL_MAX = 65519;
  
  private static final int INTERVAL_MIN = 80;
  
  private final boolean mIncludeTxPower;
  
  private final int mInterval;
  
  private PeriodicAdvertisingParameters(Parcel paramParcel) {
    boolean bool;
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mIncludeTxPower = bool;
    this.mInterval = paramParcel.readInt();
  }
  
  private PeriodicAdvertisingParameters(boolean paramBoolean, int paramInt) {
    this.mIncludeTxPower = paramBoolean;
    this.mInterval = paramInt;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean getIncludeTxPower() {
    return this.mIncludeTxPower;
  }
  
  public int getInterval() {
    return this.mInterval;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mIncludeTxPower);
    paramParcel.writeInt(this.mInterval);
  }
  
  public static final class Builder {
    private boolean mIncludeTxPower = false;
    
    private int mInterval = 65519;
    
    public PeriodicAdvertisingParameters build() {
      return new PeriodicAdvertisingParameters(this.mIncludeTxPower, this.mInterval);
    }
    
    public Builder setIncludeTxPower(boolean param1Boolean) {
      this.mIncludeTxPower = param1Boolean;
      return this;
    }
    
    public Builder setInterval(int param1Int) {
      if (param1Int >= 80 && param1Int <= 65519) {
        this.mInterval = param1Int;
        return this;
      } 
      throw new IllegalArgumentException("Invalid interval (must be 80-65519)");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/PeriodicAdvertisingParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */