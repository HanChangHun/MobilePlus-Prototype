package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class BluetoothActivityEnergyInfo implements Parcelable {
  public static final int BT_STACK_STATE_INVALID = 0;
  
  public static final int BT_STACK_STATE_STATE_ACTIVE = 1;
  
  public static final int BT_STACK_STATE_STATE_IDLE = 3;
  
  public static final int BT_STACK_STATE_STATE_SCANNING = 2;
  
  public static final Parcelable.Creator<BluetoothActivityEnergyInfo> CREATOR = new Parcelable.Creator<BluetoothActivityEnergyInfo>() {
      public BluetoothActivityEnergyInfo createFromParcel(Parcel param1Parcel) {
        return new BluetoothActivityEnergyInfo(param1Parcel);
      }
      
      public BluetoothActivityEnergyInfo[] newArray(int param1Int) {
        return new BluetoothActivityEnergyInfo[param1Int];
      }
    };
  
  private int mBluetoothStackState;
  
  private long mControllerEnergyUsed;
  
  private long mControllerIdleTimeMs;
  
  private long mControllerRxTimeMs;
  
  private long mControllerTxTimeMs;
  
  private final long mTimestamp;
  
  private UidTraffic[] mUidTraffic;
  
  public BluetoothActivityEnergyInfo(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4, long paramLong5) {
    this.mTimestamp = paramLong1;
    this.mBluetoothStackState = paramInt;
    this.mControllerTxTimeMs = paramLong2;
    this.mControllerRxTimeMs = paramLong3;
    this.mControllerIdleTimeMs = paramLong4;
    this.mControllerEnergyUsed = paramLong5;
  }
  
  BluetoothActivityEnergyInfo(Parcel paramParcel) {
    this.mTimestamp = paramParcel.readLong();
    this.mBluetoothStackState = paramParcel.readInt();
    this.mControllerTxTimeMs = paramParcel.readLong();
    this.mControllerRxTimeMs = paramParcel.readLong();
    this.mControllerIdleTimeMs = paramParcel.readLong();
    this.mControllerEnergyUsed = paramParcel.readLong();
    this.mUidTraffic = (UidTraffic[])paramParcel.createTypedArray(UidTraffic.CREATOR);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getBluetoothStackState() {
    return this.mBluetoothStackState;
  }
  
  public long getControllerEnergyUsed() {
    return this.mControllerEnergyUsed;
  }
  
  public long getControllerIdleTimeMillis() {
    return this.mControllerIdleTimeMs;
  }
  
  public long getControllerRxTimeMillis() {
    return this.mControllerRxTimeMs;
  }
  
  public long getControllerTxTimeMillis() {
    return this.mControllerTxTimeMs;
  }
  
  public long getTimeStamp() {
    return this.mTimestamp;
  }
  
  public UidTraffic[] getUidTraffic() {
    return this.mUidTraffic;
  }
  
  public boolean isValid() {
    boolean bool;
    if (this.mControllerTxTimeMs >= 0L && this.mControllerRxTimeMs >= 0L && this.mControllerIdleTimeMs >= 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setUidTraffic(UidTraffic[] paramArrayOfUidTraffic) {
    this.mUidTraffic = paramArrayOfUidTraffic;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("BluetoothActivityEnergyInfo{ mTimestamp=");
    stringBuilder.append(this.mTimestamp);
    stringBuilder.append(" mBluetoothStackState=");
    stringBuilder.append(this.mBluetoothStackState);
    stringBuilder.append(" mControllerTxTimeMs=");
    stringBuilder.append(this.mControllerTxTimeMs);
    stringBuilder.append(" mControllerRxTimeMs=");
    stringBuilder.append(this.mControllerRxTimeMs);
    stringBuilder.append(" mControllerIdleTimeMs=");
    stringBuilder.append(this.mControllerIdleTimeMs);
    stringBuilder.append(" mControllerEnergyUsed=");
    stringBuilder.append(this.mControllerEnergyUsed);
    stringBuilder.append(" mUidTraffic=");
    stringBuilder.append(Arrays.toString((Object[])this.mUidTraffic));
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mTimestamp);
    paramParcel.writeInt(this.mBluetoothStackState);
    paramParcel.writeLong(this.mControllerTxTimeMs);
    paramParcel.writeLong(this.mControllerRxTimeMs);
    paramParcel.writeLong(this.mControllerIdleTimeMs);
    paramParcel.writeLong(this.mControllerEnergyUsed);
    paramParcel.writeTypedArray((Parcelable[])this.mUidTraffic, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothActivityEnergyInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */