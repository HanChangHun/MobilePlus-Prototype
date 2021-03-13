package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.Objects;

@SystemApi
@Deprecated
public class NanoApp implements Parcelable {
  public static final Parcelable.Creator<NanoApp> CREATOR = new Parcelable.Creator<NanoApp>() {
      public NanoApp createFromParcel(Parcel param1Parcel) {
        return new NanoApp(param1Parcel);
      }
      
      public NanoApp[] newArray(int param1Int) {
        return new NanoApp[param1Int];
      }
    };
  
  private final String TAG = "NanoApp";
  
  private final String UNKNOWN = "Unknown";
  
  private byte[] mAppBinary;
  
  private long mAppId;
  
  private boolean mAppIdSet;
  
  private int mAppVersion;
  
  private String mName;
  
  private int mNeededExecMemBytes;
  
  private int mNeededReadMemBytes;
  
  private int[] mNeededSensors;
  
  private int mNeededWriteMemBytes;
  
  private int[] mOutputEvents;
  
  private String mPublisher;
  
  public NanoApp() {
    this(0L, (byte[])null);
    this.mAppIdSet = false;
  }
  
  @Deprecated
  public NanoApp(int paramInt, byte[] paramArrayOfbyte) {
    Log.w("NanoApp", "NanoApp(int, byte[]) is deprecated, please use NanoApp(long, byte[]) instead.");
  }
  
  public NanoApp(long paramLong, byte[] paramArrayOfbyte) {
    this.mPublisher = "Unknown";
    this.mName = "Unknown";
    this.mAppId = paramLong;
    this.mAppIdSet = true;
    this.mAppVersion = 0;
    this.mNeededReadMemBytes = 0;
    this.mNeededWriteMemBytes = 0;
    this.mNeededExecMemBytes = 0;
    this.mNeededSensors = new int[0];
    this.mOutputEvents = new int[0];
    this.mAppBinary = paramArrayOfbyte;
  }
  
  private NanoApp(Parcel paramParcel) {
    this.mPublisher = paramParcel.readString();
    this.mName = paramParcel.readString();
    this.mAppId = paramParcel.readLong();
    this.mAppVersion = paramParcel.readInt();
    this.mNeededReadMemBytes = paramParcel.readInt();
    this.mNeededWriteMemBytes = paramParcel.readInt();
    this.mNeededExecMemBytes = paramParcel.readInt();
    int[] arrayOfInt = new int[paramParcel.readInt()];
    this.mNeededSensors = arrayOfInt;
    paramParcel.readIntArray(arrayOfInt);
    arrayOfInt = new int[paramParcel.readInt()];
    this.mOutputEvents = arrayOfInt;
    paramParcel.readIntArray(arrayOfInt);
    byte[] arrayOfByte = new byte[paramParcel.readInt()];
    this.mAppBinary = arrayOfByte;
    paramParcel.readByteArray(arrayOfByte);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public byte[] getAppBinary() {
    return this.mAppBinary;
  }
  
  public long getAppId() {
    return this.mAppId;
  }
  
  public int getAppVersion() {
    return this.mAppVersion;
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
  
  public void setAppBinary(byte[] paramArrayOfbyte) {
    Objects.requireNonNull(paramArrayOfbyte, "appBinary must not be null");
    this.mAppBinary = paramArrayOfbyte;
  }
  
  public void setAppId(long paramLong) {
    this.mAppId = paramLong;
    this.mAppIdSet = true;
  }
  
  public void setAppVersion(int paramInt) {
    this.mAppVersion = paramInt;
  }
  
  public void setName(String paramString) {
    this.mName = paramString;
  }
  
  public void setNeededExecMemBytes(int paramInt) {
    this.mNeededExecMemBytes = paramInt;
  }
  
  public void setNeededReadMemBytes(int paramInt) {
    this.mNeededReadMemBytes = paramInt;
  }
  
  public void setNeededSensors(int[] paramArrayOfint) {
    Objects.requireNonNull(paramArrayOfint, "neededSensors must not be null");
    this.mNeededSensors = paramArrayOfint;
  }
  
  public void setNeededWriteMemBytes(int paramInt) {
    this.mNeededWriteMemBytes = paramInt;
  }
  
  public void setOutputEvents(int[] paramArrayOfint) {
    Objects.requireNonNull(paramArrayOfint, "outputEvents must not be null");
    this.mOutputEvents = paramArrayOfint;
  }
  
  public void setPublisher(String paramString) {
    this.mPublisher = paramString;
  }
  
  public String toString() {
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Id : ");
    stringBuilder2.append(this.mAppId);
    String str1 = stringBuilder2.toString();
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(str1);
    stringBuilder3.append(", Version : ");
    stringBuilder3.append(this.mAppVersion);
    str1 = stringBuilder3.toString();
    stringBuilder3 = new StringBuilder();
    stringBuilder3.append(str1);
    stringBuilder3.append(", Name : ");
    stringBuilder3.append(this.mName);
    String str2 = stringBuilder3.toString();
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(str2);
    stringBuilder1.append(", Publisher : ");
    stringBuilder1.append(this.mPublisher);
    return stringBuilder1.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mAppBinary != null) {
      if (this.mAppIdSet) {
        paramParcel.writeString(this.mPublisher);
        paramParcel.writeString(this.mName);
        paramParcel.writeLong(this.mAppId);
        paramParcel.writeInt(this.mAppVersion);
        paramParcel.writeInt(this.mNeededReadMemBytes);
        paramParcel.writeInt(this.mNeededWriteMemBytes);
        paramParcel.writeInt(this.mNeededExecMemBytes);
        paramParcel.writeInt(this.mNeededSensors.length);
        paramParcel.writeIntArray(this.mNeededSensors);
        paramParcel.writeInt(this.mOutputEvents.length);
        paramParcel.writeIntArray(this.mOutputEvents);
        paramParcel.writeInt(this.mAppBinary.length);
        paramParcel.writeByteArray(this.mAppBinary);
        return;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Must set AppId for nanoapp ");
      stringBuilder1.append(this.mName);
      throw new IllegalStateException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Must set non-null AppBinary for nanoapp ");
    stringBuilder.append(this.mName);
    throw new IllegalStateException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoApp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */