package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public class UidTraffic implements Cloneable, Parcelable {
  public static final Parcelable.Creator<UidTraffic> CREATOR = new Parcelable.Creator<UidTraffic>() {
      public UidTraffic createFromParcel(Parcel param1Parcel) {
        return new UidTraffic(param1Parcel);
      }
      
      public UidTraffic[] newArray(int param1Int) {
        return new UidTraffic[param1Int];
      }
    };
  
  private final int mAppUid;
  
  private long mRxBytes;
  
  private long mTxBytes;
  
  public UidTraffic(int paramInt) {
    this.mAppUid = paramInt;
  }
  
  public UidTraffic(int paramInt, long paramLong1, long paramLong2) {
    this.mAppUid = paramInt;
    this.mRxBytes = paramLong1;
    this.mTxBytes = paramLong2;
  }
  
  UidTraffic(Parcel paramParcel) {
    this.mAppUid = paramParcel.readInt();
    this.mRxBytes = paramParcel.readLong();
    this.mTxBytes = paramParcel.readLong();
  }
  
  public void addRxBytes(long paramLong) {
    this.mRxBytes += paramLong;
  }
  
  public void addTxBytes(long paramLong) {
    this.mTxBytes += paramLong;
  }
  
  public UidTraffic clone() {
    return new UidTraffic(this.mAppUid, this.mRxBytes, this.mTxBytes);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getRxBytes() {
    return this.mRxBytes;
  }
  
  public long getTxBytes() {
    return this.mTxBytes;
  }
  
  public int getUid() {
    return this.mAppUid;
  }
  
  public void setRxBytes(long paramLong) {
    this.mRxBytes = paramLong;
  }
  
  public void setTxBytes(long paramLong) {
    this.mTxBytes = paramLong;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UidTraffic{mAppUid=");
    stringBuilder.append(this.mAppUid);
    stringBuilder.append(", mRxBytes=");
    stringBuilder.append(this.mRxBytes);
    stringBuilder.append(", mTxBytes=");
    stringBuilder.append(this.mTxBytes);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mAppUid);
    paramParcel.writeLong(this.mRxBytes);
    paramParcel.writeLong(this.mTxBytes);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/UidTraffic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */