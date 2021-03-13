package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import libcore.util.EmptyArray;

@SystemApi
@Deprecated
public class NanoAppInstanceInfo implements Parcelable {
  public static final Parcelable.Creator<NanoAppInstanceInfo> CREATOR = new Parcelable.Creator<NanoAppInstanceInfo>() {
      public NanoAppInstanceInfo createFromParcel(Parcel param1Parcel) {
        return new NanoAppInstanceInfo(param1Parcel);
      }
      
      public NanoAppInstanceInfo[] newArray(int param1Int) {
        return new NanoAppInstanceInfo[param1Int];
      }
    };
  
  private long mAppId;
  
  private int mAppVersion;
  
  private int mContexthubId;
  
  private int mHandle;
  
  private String mName = "Unknown";
  
  private int mNeededExecMemBytes = 0;
  
  private int mNeededReadMemBytes = 0;
  
  private int[] mNeededSensors = EmptyArray.INT;
  
  private int mNeededWriteMemBytes = 0;
  
  private int[] mOutputEvents = EmptyArray.INT;
  
  private String mPublisher = "Unknown";
  
  public NanoAppInstanceInfo() {}
  
  public NanoAppInstanceInfo(int paramInt1, long paramLong, int paramInt2, int paramInt3) {
    this.mHandle = paramInt1;
    this.mAppId = paramLong;
    this.mAppVersion = paramInt2;
    this.mContexthubId = paramInt3;
  }
  
  private NanoAppInstanceInfo(Parcel paramParcel) {
    this.mPublisher = paramParcel.readString();
    this.mName = paramParcel.readString();
    this.mHandle = paramParcel.readInt();
    this.mAppId = paramParcel.readLong();
    this.mAppVersion = paramParcel.readInt();
    this.mContexthubId = paramParcel.readInt();
    this.mNeededReadMemBytes = paramParcel.readInt();
    this.mNeededWriteMemBytes = paramParcel.readInt();
    this.mNeededExecMemBytes = paramParcel.readInt();
    int[] arrayOfInt = new int[paramParcel.readInt()];
    this.mNeededSensors = arrayOfInt;
    paramParcel.readIntArray(arrayOfInt);
    arrayOfInt = new int[paramParcel.readInt()];
    this.mOutputEvents = arrayOfInt;
    paramParcel.readIntArray(arrayOfInt);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getAppId() {
    return this.mAppId;
  }
  
  public int getAppVersion() {
    return this.mAppVersion;
  }
  
  public int getContexthubId() {
    return this.mContexthubId;
  }
  
  public int getHandle() {
    return this.mHandle;
  }
  
  public String getName() {
    return this.mName;
  }
  
  public int getNeededExecMemBytes() {
    return this.mNeededExecMemBytes;
  }
  
  public int getNeededReadMemBytes() {
    return this.mNeededReadMemBytes;
  }
  
  public int[] getNeededSensors() {
    return this.mNeededSensors;
  }
  
  public int getNeededWriteMemBytes() {
    return this.mNeededWriteMemBytes;
  }
  
  public int[] getOutputEvents() {
    return this.mOutputEvents;
  }
  
  public String getPublisher() {
    return this.mPublisher;
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("handle : ");
    stringBuilder1.append(this.mHandle);
    String str2 = stringBuilder1.toString();
    stringBuilder1 = new StringBuilder();
    stringBuilder1.append(str2);
    stringBuilder1.append(", Id : 0x");
    stringBuilder1.append(Long.toHexString(this.mAppId));
    String str1 = stringBuilder1.toString();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str1);
    stringBuilder2.append(", Version : 0x");
    stringBuilder2.append(Integer.toHexString(this.mAppVersion));
    return stringBuilder2.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mPublisher);
    paramParcel.writeString(this.mName);
    paramParcel.writeInt(this.mHandle);
    paramParcel.writeLong(this.mAppId);
    paramParcel.writeInt(this.mAppVersion);
    paramParcel.writeInt(this.mContexthubId);
    paramParcel.writeInt(this.mNeededReadMemBytes);
    paramParcel.writeInt(this.mNeededWriteMemBytes);
    paramParcel.writeInt(this.mNeededExecMemBytes);
    paramParcel.writeInt(this.mNeededSensors.length);
    paramParcel.writeIntArray(this.mNeededSensors);
    paramParcel.writeInt(this.mOutputEvents.length);
    paramParcel.writeIntArray(this.mOutputEvents);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoAppInstanceInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */