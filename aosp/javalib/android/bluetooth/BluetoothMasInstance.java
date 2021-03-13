package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public final class BluetoothMasInstance implements Parcelable {
  public static final Parcelable.Creator<BluetoothMasInstance> CREATOR = new Parcelable.Creator<BluetoothMasInstance>() {
      public BluetoothMasInstance createFromParcel(Parcel param1Parcel) {
        return new BluetoothMasInstance(param1Parcel.readInt(), param1Parcel.readString(), param1Parcel.readInt(), param1Parcel.readInt());
      }
      
      public BluetoothMasInstance[] newArray(int param1Int) {
        return new BluetoothMasInstance[param1Int];
      }
    };
  
  private final int mChannel;
  
  private final int mId;
  
  private final int mMsgTypes;
  
  private final String mName;
  
  public BluetoothMasInstance(int paramInt1, String paramString, int paramInt2, int paramInt3) {
    this.mId = paramInt1;
    this.mName = paramString;
    this.mChannel = paramInt2;
    this.mMsgTypes = paramInt3;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof BluetoothMasInstance;
    boolean bool1 = false;
    if (bool) {
      if (this.mId == ((BluetoothMasInstance)paramObject).mId)
        bool1 = true; 
      return bool1;
    } 
    return false;
  }
  
  public int getChannel() {
    return this.mChannel;
  }
  
  public int getId() {
    return this.mId;
  }
  
  public int getMsgTypes() {
    return this.mMsgTypes;
  }
  
  public String getName() {
    return this.mName;
  }
  
  public int hashCode() {
    return this.mId + (this.mChannel << 8) + (this.mMsgTypes << 16);
  }
  
  public boolean msgSupported(int paramInt) {
    boolean bool;
    if ((this.mMsgTypes & paramInt) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(Integer.toString(this.mId));
    stringBuilder.append(":");
    stringBuilder.append(this.mName);
    stringBuilder.append(":");
    stringBuilder.append(this.mChannel);
    stringBuilder.append(":");
    stringBuilder.append(Integer.toHexString(this.mMsgTypes));
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mId);
    paramParcel.writeString(this.mName);
    paramParcel.writeInt(this.mChannel);
    paramParcel.writeInt(this.mMsgTypes);
  }
  
  public static final class MessageType {
    public static final int EMAIL = 1;
    
    public static final int MMS = 8;
    
    public static final int SMS_CDMA = 4;
    
    public static final int SMS_GSM = 2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothMasInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */