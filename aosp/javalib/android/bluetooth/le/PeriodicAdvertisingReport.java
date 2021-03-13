package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class PeriodicAdvertisingReport implements Parcelable {
  public static final Parcelable.Creator<PeriodicAdvertisingReport> CREATOR = new Parcelable.Creator<PeriodicAdvertisingReport>() {
      public PeriodicAdvertisingReport createFromParcel(Parcel param1Parcel) {
        return new PeriodicAdvertisingReport(param1Parcel);
      }
      
      public PeriodicAdvertisingReport[] newArray(int param1Int) {
        return new PeriodicAdvertisingReport[param1Int];
      }
    };
  
  public static final int DATA_COMPLETE = 0;
  
  public static final int DATA_INCOMPLETE_TRUNCATED = 2;
  
  private ScanRecord mData;
  
  private int mDataStatus;
  
  private int mRssi;
  
  private int mSyncHandle;
  
  private long mTimestampNanos;
  
  private int mTxPower;
  
  public PeriodicAdvertisingReport(int paramInt1, int paramInt2, int paramInt3, int paramInt4, ScanRecord paramScanRecord) {
    this.mSyncHandle = paramInt1;
    this.mTxPower = paramInt2;
    this.mRssi = paramInt3;
    this.mDataStatus = paramInt4;
    this.mData = paramScanRecord;
  }
  
  private PeriodicAdvertisingReport(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  private void readFromParcel(Parcel paramParcel) {
    this.mSyncHandle = paramParcel.readInt();
    this.mTxPower = paramParcel.readInt();
    this.mRssi = paramParcel.readInt();
    this.mDataStatus = paramParcel.readInt();
    if (paramParcel.readInt() == 1)
      this.mData = ScanRecord.parseFromBytes(paramParcel.createByteArray()); 
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
    if (this.mSyncHandle != ((PeriodicAdvertisingReport)paramObject).mSyncHandle || this.mTxPower != ((PeriodicAdvertisingReport)paramObject).mTxPower || this.mRssi != ((PeriodicAdvertisingReport)paramObject).mRssi || this.mDataStatus != ((PeriodicAdvertisingReport)paramObject).mDataStatus || !Objects.equals(this.mData, ((PeriodicAdvertisingReport)paramObject).mData) || this.mTimestampNanos != ((PeriodicAdvertisingReport)paramObject).mTimestampNanos)
      bool = false; 
    return bool;
  }
  
  public ScanRecord getData() {
    return this.mData;
  }
  
  public int getDataStatus() {
    return this.mDataStatus;
  }
  
  public int getRssi() {
    return this.mRssi;
  }
  
  public int getSyncHandle() {
    return this.mSyncHandle;
  }
  
  public long getTimestampNanos() {
    return this.mTimestampNanos;
  }
  
  public int getTxPower() {
    return this.mTxPower;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.mSyncHandle), Integer.valueOf(this.mTxPower), Integer.valueOf(this.mRssi), Integer.valueOf(this.mDataStatus), this.mData, Long.valueOf(this.mTimestampNanos) });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PeriodicAdvertisingReport{syncHandle=");
    stringBuilder.append(this.mSyncHandle);
    stringBuilder.append(", txPower=");
    stringBuilder.append(this.mTxPower);
    stringBuilder.append(", rssi=");
    stringBuilder.append(this.mRssi);
    stringBuilder.append(", dataStatus=");
    stringBuilder.append(this.mDataStatus);
    stringBuilder.append(", data=");
    stringBuilder.append(Objects.toString(this.mData));
    stringBuilder.append(", timestampNanos=");
    stringBuilder.append(this.mTimestampNanos);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mSyncHandle);
    paramParcel.writeInt(this.mTxPower);
    paramParcel.writeInt(this.mRssi);
    paramParcel.writeInt(this.mDataStatus);
    if (this.mData != null) {
      paramParcel.writeInt(1);
      paramParcel.writeByteArray(this.mData.getBytes());
    } else {
      paramParcel.writeInt(0);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/PeriodicAdvertisingReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */