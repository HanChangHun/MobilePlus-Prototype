package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import java.util.UUID;

public final class BluetoothHeadsetClientCall implements Parcelable {
  public static final int CALL_STATE_ACTIVE = 0;
  
  public static final int CALL_STATE_ALERTING = 3;
  
  public static final int CALL_STATE_DIALING = 2;
  
  public static final int CALL_STATE_HELD = 1;
  
  public static final int CALL_STATE_HELD_BY_RESPONSE_AND_HOLD = 6;
  
  public static final int CALL_STATE_INCOMING = 4;
  
  public static final int CALL_STATE_TERMINATED = 7;
  
  public static final int CALL_STATE_WAITING = 5;
  
  public static final Parcelable.Creator<BluetoothHeadsetClientCall> CREATOR = new Parcelable.Creator<BluetoothHeadsetClientCall>() {
      public BluetoothHeadsetClientCall createFromParcel(Parcel param1Parcel) {
        boolean bool1;
        boolean bool2;
        boolean bool3;
        BluetoothDevice bluetoothDevice = (BluetoothDevice)param1Parcel.readParcelable(null);
        int i = param1Parcel.readInt();
        UUID uUID = UUID.fromString(param1Parcel.readString());
        int j = param1Parcel.readInt();
        String str = param1Parcel.readString();
        if (param1Parcel.readInt() == 1) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        if (param1Parcel.readInt() == 1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        if (param1Parcel.readInt() == 1) {
          bool3 = true;
        } else {
          bool3 = false;
        } 
        return new BluetoothHeadsetClientCall(bluetoothDevice, i, uUID, j, str, bool1, bool2, bool3);
      }
      
      public BluetoothHeadsetClientCall[] newArray(int param1Int) {
        return new BluetoothHeadsetClientCall[param1Int];
      }
    };
  
  private final long mCreationElapsedMilli;
  
  private final BluetoothDevice mDevice;
  
  private final int mId;
  
  private final boolean mInBandRing;
  
  private boolean mMultiParty;
  
  private String mNumber;
  
  private final boolean mOutgoing;
  
  private int mState;
  
  private final UUID mUUID;
  
  public BluetoothHeadsetClientCall(BluetoothDevice paramBluetoothDevice, int paramInt1, int paramInt2, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    this(paramBluetoothDevice, paramInt1, UUID.randomUUID(), paramInt2, paramString, paramBoolean1, paramBoolean2, paramBoolean3);
  }
  
  public BluetoothHeadsetClientCall(BluetoothDevice paramBluetoothDevice, int paramInt1, UUID paramUUID, int paramInt2, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    this.mDevice = paramBluetoothDevice;
    this.mId = paramInt1;
    this.mUUID = paramUUID;
    this.mState = paramInt2;
    if (paramString == null)
      paramString = ""; 
    this.mNumber = paramString;
    this.mMultiParty = paramBoolean1;
    this.mOutgoing = paramBoolean2;
    this.mInBandRing = paramBoolean3;
    this.mCreationElapsedMilli = SystemClock.elapsedRealtime();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getCreationElapsedMilli() {
    return this.mCreationElapsedMilli;
  }
  
  public BluetoothDevice getDevice() {
    return this.mDevice;
  }
  
  public int getId() {
    return this.mId;
  }
  
  public String getNumber() {
    return this.mNumber;
  }
  
  public int getState() {
    return this.mState;
  }
  
  public UUID getUUID() {
    return this.mUUID;
  }
  
  public boolean isInBandRing() {
    return this.mInBandRing;
  }
  
  public boolean isMultiParty() {
    return this.mMultiParty;
  }
  
  public boolean isOutgoing() {
    return this.mOutgoing;
  }
  
  public void setMultiParty(boolean paramBoolean) {
    this.mMultiParty = paramBoolean;
  }
  
  public void setNumber(String paramString) {
    this.mNumber = paramString;
  }
  
  public void setState(int paramInt) {
    this.mState = paramInt;
  }
  
  public String toString() {
    return toString(false);
  }
  
  public String toString(boolean paramBoolean) {
    Integer integer2;
    Integer integer1;
    StringBuilder stringBuilder = new StringBuilder("BluetoothHeadsetClientCall{mDevice: ");
    BluetoothDevice bluetoothDevice = this.mDevice;
    if (!paramBoolean)
      integer2 = Integer.valueOf(bluetoothDevice.hashCode()); 
    stringBuilder.append(integer2);
    stringBuilder.append(", mId: ");
    stringBuilder.append(this.mId);
    stringBuilder.append(", mUUID: ");
    stringBuilder.append(this.mUUID);
    stringBuilder.append(", mState: ");
    int i = this.mState;
    switch (i) {
      default:
        stringBuilder.append(i);
        break;
      case 7:
        stringBuilder.append("TERMINATED");
        break;
      case 6:
        stringBuilder.append("HELD_BY_RESPONSE_AND_HOLD");
        break;
      case 5:
        stringBuilder.append("WAITING");
        break;
      case 4:
        stringBuilder.append("INCOMING");
        break;
      case 3:
        stringBuilder.append("ALERTING");
        break;
      case 2:
        stringBuilder.append("DIALING");
        break;
      case 1:
        stringBuilder.append("HELD");
        break;
      case 0:
        stringBuilder.append("ACTIVE");
        break;
    } 
    stringBuilder.append(", mNumber: ");
    String str = this.mNumber;
    if (!paramBoolean)
      integer1 = Integer.valueOf(str.hashCode()); 
    stringBuilder.append(integer1);
    stringBuilder.append(", mMultiParty: ");
    stringBuilder.append(this.mMultiParty);
    stringBuilder.append(", mOutgoing: ");
    stringBuilder.append(this.mOutgoing);
    stringBuilder.append(", mInBandRing: ");
    stringBuilder.append(this.mInBandRing);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeParcelable(this.mDevice, 0);
    paramParcel.writeInt(this.mId);
    paramParcel.writeString(this.mUUID.toString());
    paramParcel.writeInt(this.mState);
    paramParcel.writeString(this.mNumber);
    paramParcel.writeInt(this.mMultiParty);
    paramParcel.writeInt(this.mOutgoing);
    paramParcel.writeInt(this.mInBandRing);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHeadsetClientCall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */