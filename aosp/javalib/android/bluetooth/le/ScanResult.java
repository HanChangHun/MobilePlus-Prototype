package android.bluetooth.le;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class ScanResult implements Parcelable {
  public static final Parcelable.Creator<ScanResult> CREATOR = new Parcelable.Creator<ScanResult>() {
      public ScanResult createFromParcel(Parcel param1Parcel) {
        return new ScanResult(param1Parcel);
      }
      
      public ScanResult[] newArray(int param1Int) {
        return new ScanResult[param1Int];
      }
    };
  
  public static final int DATA_COMPLETE = 0;
  
  public static final int DATA_TRUNCATED = 2;
  
  private static final int ET_CONNECTABLE_MASK = 1;
  
  private static final int ET_LEGACY_MASK = 16;
  
  public static final int PERIODIC_INTERVAL_NOT_PRESENT = 0;
  
  public static final int PHY_UNUSED = 0;
  
  public static final int SID_NOT_PRESENT = 255;
  
  public static final int TX_POWER_NOT_PRESENT = 127;
  
  private int mAdvertisingSid;
  
  private BluetoothDevice mDevice;
  
  private int mEventType;
  
  private int mPeriodicAdvertisingInterval;
  
  private int mPrimaryPhy;
  
  private int mRssi;
  
  private ScanRecord mScanRecord;
  
  private int mSecondaryPhy;
  
  private long mTimestampNanos;
  
  private int mTxPower;
  
  public ScanResult(BluetoothDevice paramBluetoothDevice, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, ScanRecord paramScanRecord, long paramLong) {
    this.mDevice = paramBluetoothDevice;
    this.mEventType = paramInt1;
    this.mPrimaryPhy = paramInt2;
    this.mSecondaryPhy = paramInt3;
    this.mAdvertisingSid = paramInt4;
    this.mTxPower = paramInt5;
    this.mRssi = paramInt6;
    this.mPeriodicAdvertisingInterval = paramInt7;
    this.mScanRecord = paramScanRecord;
    this.mTimestampNanos = paramLong;
  }
  
  @Deprecated
  public ScanResult(BluetoothDevice paramBluetoothDevice, ScanRecord paramScanRecord, int paramInt, long paramLong) {
    this.mDevice = paramBluetoothDevice;
    this.mScanRecord = paramScanRecord;
    this.mRssi = paramInt;
    this.mTimestampNanos = paramLong;
    this.mEventType = 17;
    this.mPrimaryPhy = 1;
    this.mSecondaryPhy = 0;
    this.mAdvertisingSid = 255;
    this.mTxPower = 127;
    this.mPeriodicAdvertisingInterval = 0;
  }
  
  private ScanResult(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  private void readFromParcel(Parcel paramParcel) {
    if (paramParcel.readInt() == 1)
      this.mDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel); 
    if (paramParcel.readInt() == 1)
      this.mScanRecord = ScanRecord.parseFromBytes(paramParcel.createByteArray()); 
    this.mRssi = paramParcel.readInt();
    this.mTimestampNanos = paramParcel.readLong();
    this.mEventType = paramParcel.readInt();
    this.mPrimaryPhy = paramParcel.readInt();
    this.mSecondaryPhy = paramParcel.readInt();
    this.mAdvertisingSid = paramParcel.readInt();
    this.mTxPower = paramParcel.readInt();
    this.mPeriodicAdvertisingInterval = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (!Objects.equals(this.mDevice, ((ScanResult)paramObject).mDevice) || this.mRssi != ((ScanResult)paramObject).mRssi || !Objects.equals(this.mScanRecord, ((ScanResult)paramObject).mScanRecord) || this.mTimestampNanos != ((ScanResult)paramObject).mTimestampNanos || this.mEventType != ((ScanResult)paramObject).mEventType || this.mPrimaryPhy != ((ScanResult)paramObject).mPrimaryPhy || this.mSecondaryPhy != ((ScanResult)paramObject).mSecondaryPhy || this.mAdvertisingSid != ((ScanResult)paramObject).mAdvertisingSid || this.mTxPower != ((ScanResult)paramObject).mTxPower || this.mPeriodicAdvertisingInterval != ((ScanResult)paramObject).mPeriodicAdvertisingInterval)
      bool = false; 
    return bool;
  }
  
  public int getAdvertisingSid() {
    return this.mAdvertisingSid;
  }
  
  public int getDataStatus() {
    return this.mEventType >> 5 & 0x3;
  }
  
  public BluetoothDevice getDevice() {
    return this.mDevice;
  }
  
  public int getPeriodicAdvertisingInterval() {
    return this.mPeriodicAdvertisingInterval;
  }
  
  public int getPrimaryPhy() {
    return this.mPrimaryPhy;
  }
  
  public int getRssi() {
    return this.mRssi;
  }
  
  public ScanRecord getScanRecord() {
    return this.mScanRecord;
  }
  
  public int getSecondaryPhy() {
    return this.mSecondaryPhy;
  }
  
  public long getTimestampNanos() {
    return this.mTimestampNanos;
  }
  
  public int getTxPower() {
    return this.mTxPower;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mDevice, Integer.valueOf(this.mRssi), this.mScanRecord, Long.valueOf(this.mTimestampNanos), Integer.valueOf(this.mEventType), Integer.valueOf(this.mPrimaryPhy), Integer.valueOf(this.mSecondaryPhy), Integer.valueOf(this.mAdvertisingSid), Integer.valueOf(this.mTxPower), Integer.valueOf(this.mPeriodicAdvertisingInterval) });
  }
  
  public boolean isConnectable() {
    int i = this.mEventType;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public boolean isLegacy() {
    boolean bool;
    if ((this.mEventType & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ScanResult{device=");
    stringBuilder.append(this.mDevice);
    stringBuilder.append(", scanRecord=");
    stringBuilder.append(Objects.toString(this.mScanRecord));
    stringBuilder.append(", rssi=");
    stringBuilder.append(this.mRssi);
    stringBuilder.append(", timestampNanos=");
    stringBuilder.append(this.mTimestampNanos);
    stringBuilder.append(", eventType=");
    stringBuilder.append(this.mEventType);
    stringBuilder.append(", primaryPhy=");
    stringBuilder.append(this.mPrimaryPhy);
    stringBuilder.append(", secondaryPhy=");
    stringBuilder.append(this.mSecondaryPhy);
    stringBuilder.append(", advertisingSid=");
    stringBuilder.append(this.mAdvertisingSid);
    stringBuilder.append(", txPower=");
    stringBuilder.append(this.mTxPower);
    stringBuilder.append(", periodicAdvertisingInterval=");
    stringBuilder.append(this.mPeriodicAdvertisingInterval);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mDevice != null) {
      paramParcel.writeInt(1);
      this.mDevice.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mScanRecord != null) {
      paramParcel.writeInt(1);
      paramParcel.writeByteArray(this.mScanRecord.getBytes());
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.mRssi);
    paramParcel.writeLong(this.mTimestampNanos);
    paramParcel.writeInt(this.mEventType);
    paramParcel.writeInt(this.mPrimaryPhy);
    paramParcel.writeInt(this.mSecondaryPhy);
    paramParcel.writeInt(this.mAdvertisingSid);
    paramParcel.writeInt(this.mTxPower);
    paramParcel.writeInt(this.mPeriodicAdvertisingInterval);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/ScanResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */