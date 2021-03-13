package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class HdmiHotplugEvent implements Parcelable {
  public static final Parcelable.Creator<HdmiHotplugEvent> CREATOR = new Parcelable.Creator<HdmiHotplugEvent>() {
      public HdmiHotplugEvent createFromParcel(Parcel param1Parcel) {
        int i = param1Parcel.readInt();
        byte b = param1Parcel.readByte();
        boolean bool = true;
        if (b != 1)
          bool = false; 
        return new HdmiHotplugEvent(i, bool);
      }
      
      public HdmiHotplugEvent[] newArray(int param1Int) {
        return new HdmiHotplugEvent[param1Int];
      }
    };
  
  private final boolean mConnected;
  
  private final int mPort;
  
  public HdmiHotplugEvent(int paramInt, boolean paramBoolean) {
    this.mPort = paramInt;
    this.mConnected = paramBoolean;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getPort() {
    return this.mPort;
  }
  
  public boolean isConnected() {
    return this.mConnected;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mPort);
    paramParcel.writeByte((byte)this.mConnected);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiHotplugEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */