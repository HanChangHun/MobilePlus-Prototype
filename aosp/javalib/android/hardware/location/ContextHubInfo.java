package android.hardware.location;

import android.annotation.SystemApi;
import android.hardware.contexthub.V1_0.ContextHub;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.proto.ProtoOutputStream;
import java.util.Arrays;

@SystemApi
public class ContextHubInfo implements Parcelable {
  public static final Parcelable.Creator<ContextHubInfo> CREATOR = new Parcelable.Creator<ContextHubInfo>() {
      public ContextHubInfo createFromParcel(Parcel param1Parcel) {
        return new ContextHubInfo(param1Parcel);
      }
      
      public ContextHubInfo[] newArray(int param1Int) {
        return new ContextHubInfo[param1Int];
      }
    };
  
  private byte mChreApiMajorVersion;
  
  private byte mChreApiMinorVersion;
  
  private short mChrePatchVersion;
  
  private long mChrePlatformId;
  
  private int mId;
  
  private int mMaxPacketLengthBytes;
  
  private MemoryRegion[] mMemoryRegions;
  
  private String mName;
  
  private float mPeakMips;
  
  private float mPeakPowerDrawMw;
  
  private int mPlatformVersion;
  
  private float mSleepPowerDrawMw;
  
  private float mStoppedPowerDrawMw;
  
  private int[] mSupportedSensors;
  
  private String mToolchain;
  
  private int mToolchainVersion;
  
  private String mVendor;
  
  public ContextHubInfo() {}
  
  public ContextHubInfo(ContextHub paramContextHub) {
    this.mId = paramContextHub.hubId;
    this.mName = paramContextHub.name;
    this.mVendor = paramContextHub.vendor;
    this.mToolchain = paramContextHub.toolchain;
    this.mPlatformVersion = paramContextHub.platformVersion;
    this.mToolchainVersion = paramContextHub.toolchainVersion;
    this.mPeakMips = paramContextHub.peakMips;
    this.mStoppedPowerDrawMw = paramContextHub.stoppedPowerDrawMw;
    this.mSleepPowerDrawMw = paramContextHub.sleepPowerDrawMw;
    this.mPeakPowerDrawMw = paramContextHub.peakPowerDrawMw;
    this.mMaxPacketLengthBytes = paramContextHub.maxSupportedMsgLen;
    this.mChrePlatformId = paramContextHub.chrePlatformId;
    this.mChreApiMajorVersion = (byte)paramContextHub.chreApiMajorVersion;
    this.mChreApiMinorVersion = (byte)paramContextHub.chreApiMinorVersion;
    this.mChrePatchVersion = (short)paramContextHub.chrePatchVersion;
    this.mSupportedSensors = new int[0];
    this.mMemoryRegions = new MemoryRegion[0];
  }
  
  private ContextHubInfo(Parcel paramParcel) {
    this.mId = paramParcel.readInt();
    this.mName = paramParcel.readString();
    this.mVendor = paramParcel.readString();
    this.mToolchain = paramParcel.readString();
    this.mPlatformVersion = paramParcel.readInt();
    this.mToolchainVersion = paramParcel.readInt();
    this.mPeakMips = paramParcel.readFloat();
    this.mStoppedPowerDrawMw = paramParcel.readFloat();
    this.mSleepPowerDrawMw = paramParcel.readFloat();
    this.mPeakPowerDrawMw = paramParcel.readFloat();
    this.mMaxPacketLengthBytes = paramParcel.readInt();
    this.mChrePlatformId = paramParcel.readLong();
    this.mChreApiMajorVersion = paramParcel.readByte();
    this.mChreApiMinorVersion = paramParcel.readByte();
    this.mChrePatchVersion = (short)(short)paramParcel.readInt();
    int[] arrayOfInt = new int[paramParcel.readInt()];
    this.mSupportedSensors = arrayOfInt;
    paramParcel.readIntArray(arrayOfInt);
    this.mMemoryRegions = (MemoryRegion[])paramParcel.createTypedArray(MemoryRegion.CREATOR);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(ProtoOutputStream paramProtoOutputStream) {
    paramProtoOutputStream.write(1120986464257L, this.mId);
    paramProtoOutputStream.write(1138166333442L, this.mName);
    paramProtoOutputStream.write(1138166333443L, this.mVendor);
    paramProtoOutputStream.write(1138166333444L, this.mToolchain);
    paramProtoOutputStream.write(1120986464261L, this.mPlatformVersion);
    paramProtoOutputStream.write(1120986464262L, getStaticSwVersion());
    paramProtoOutputStream.write(1120986464263L, this.mToolchainVersion);
    paramProtoOutputStream.write(1112396529672L, this.mChrePlatformId);
    paramProtoOutputStream.write(1108101562377L, this.mPeakMips);
    paramProtoOutputStream.write(1108101562378L, this.mStoppedPowerDrawMw);
    paramProtoOutputStream.write(1108101562379L, this.mSleepPowerDrawMw);
    paramProtoOutputStream.write(1108101562380L, this.mPeakPowerDrawMw);
    paramProtoOutputStream.write(1120986464269L, this.mMaxPacketLengthBytes);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool1 = true;
    if (paramObject == this)
      return true; 
    boolean bool2 = false;
    if (paramObject instanceof ContextHubInfo) {
      paramObject = paramObject;
      if (paramObject.getId() == this.mId && paramObject.getName().equals(this.mName) && paramObject.getVendor().equals(this.mVendor) && paramObject.getToolchain().equals(this.mToolchain) && paramObject.getToolchainVersion() == this.mToolchainVersion && paramObject.getStaticSwVersion() == getStaticSwVersion() && paramObject.getChrePlatformId() == this.mChrePlatformId && paramObject.getPeakMips() == this.mPeakMips && paramObject.getStoppedPowerDrawMw() == this.mStoppedPowerDrawMw && paramObject.getSleepPowerDrawMw() == this.mSleepPowerDrawMw && paramObject.getPeakPowerDrawMw() == this.mPeakPowerDrawMw && paramObject.getMaxPacketLengthBytes() == this.mMaxPacketLengthBytes && Arrays.equals(paramObject.getSupportedSensors(), this.mSupportedSensors) && Arrays.equals((Object[])paramObject.getMemoryRegions(), (Object[])this.mMemoryRegions)) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
    } 
    return bool2;
  }
  
  public byte getChreApiMajorVersion() {
    return this.mChreApiMajorVersion;
  }
  
  public byte getChreApiMinorVersion() {
    return this.mChreApiMinorVersion;
  }
  
  public short getChrePatchVersion() {
    return this.mChrePatchVersion;
  }
  
  public long getChrePlatformId() {
    return this.mChrePlatformId;
  }
  
  public int getId() {
    return this.mId;
  }
  
  public int getMaxPacketLengthBytes() {
    return this.mMaxPacketLengthBytes;
  }
  
  public MemoryRegion[] getMemoryRegions() {
    MemoryRegion[] arrayOfMemoryRegion = this.mMemoryRegions;
    return Arrays.<MemoryRegion>copyOf(arrayOfMemoryRegion, arrayOfMemoryRegion.length);
  }
  
  public String getName() {
    return this.mName;
  }
  
  public float getPeakMips() {
    return this.mPeakMips;
  }
  
  public float getPeakPowerDrawMw() {
    return this.mPeakPowerDrawMw;
  }
  
  public int getPlatformVersion() {
    return this.mPlatformVersion;
  }
  
  public float getSleepPowerDrawMw() {
    return this.mSleepPowerDrawMw;
  }
  
  public int getStaticSwVersion() {
    return this.mChreApiMajorVersion << 24 | this.mChreApiMinorVersion << 16 | this.mChrePatchVersion;
  }
  
  public float getStoppedPowerDrawMw() {
    return this.mStoppedPowerDrawMw;
  }
  
  public int[] getSupportedSensors() {
    int[] arrayOfInt = this.mSupportedSensors;
    return Arrays.copyOf(arrayOfInt, arrayOfInt.length);
  }
  
  public String getToolchain() {
    return this.mToolchain;
  }
  
  public int getToolchainVersion() {
    return this.mToolchainVersion;
  }
  
  public String getVendor() {
    return this.mVendor;
  }
  
  public String toString() {
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append("");
    stringBuilder3.append("ID/handle : ");
    stringBuilder3.append(this.mId);
    String str5 = stringBuilder3.toString();
    stringBuilder3 = new StringBuilder();
    stringBuilder3.append(str5);
    stringBuilder3.append(", Name : ");
    stringBuilder3.append(this.mName);
    String str2 = stringBuilder3.toString();
    StringBuilder stringBuilder5 = new StringBuilder();
    stringBuilder5.append(str2);
    stringBuilder5.append("\n\tVendor : ");
    stringBuilder5.append(this.mVendor);
    String str4 = stringBuilder5.toString();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str4);
    stringBuilder2.append(", Toolchain : ");
    stringBuilder2.append(this.mToolchain);
    str4 = stringBuilder2.toString();
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str4);
    stringBuilder2.append(", Toolchain version: 0x");
    stringBuilder2.append(Integer.toHexString(this.mToolchainVersion));
    str4 = stringBuilder2.toString();
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str4);
    stringBuilder2.append("\n\tPlatformVersion : 0x");
    stringBuilder2.append(Integer.toHexString(this.mPlatformVersion));
    str4 = stringBuilder2.toString();
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str4);
    stringBuilder2.append(", SwVersion : ");
    stringBuilder2.append(this.mChreApiMajorVersion);
    stringBuilder2.append(".");
    stringBuilder2.append(this.mChreApiMinorVersion);
    stringBuilder2.append(".");
    stringBuilder2.append(this.mChrePatchVersion);
    str4 = stringBuilder2.toString();
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str4);
    stringBuilder2.append(", CHRE platform ID: 0x");
    stringBuilder2.append(Long.toHexString(this.mChrePlatformId));
    String str1 = stringBuilder2.toString();
    StringBuilder stringBuilder4 = new StringBuilder();
    stringBuilder4.append(str1);
    stringBuilder4.append("\n\tPeakMips : ");
    stringBuilder4.append(this.mPeakMips);
    str1 = stringBuilder4.toString();
    stringBuilder4 = new StringBuilder();
    stringBuilder4.append(str1);
    stringBuilder4.append(", StoppedPowerDraw : ");
    stringBuilder4.append(this.mStoppedPowerDrawMw);
    stringBuilder4.append(" mW");
    str1 = stringBuilder4.toString();
    stringBuilder4 = new StringBuilder();
    stringBuilder4.append(str1);
    stringBuilder4.append(", PeakPowerDraw : ");
    stringBuilder4.append(this.mPeakPowerDrawMw);
    stringBuilder4.append(" mW");
    String str3 = stringBuilder4.toString();
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(str3);
    stringBuilder1.append(", MaxPacketLength : ");
    stringBuilder1.append(this.mMaxPacketLengthBytes);
    stringBuilder1.append(" Bytes");
    return stringBuilder1.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mId);
    paramParcel.writeString(this.mName);
    paramParcel.writeString(this.mVendor);
    paramParcel.writeString(this.mToolchain);
    paramParcel.writeInt(this.mPlatformVersion);
    paramParcel.writeInt(this.mToolchainVersion);
    paramParcel.writeFloat(this.mPeakMips);
    paramParcel.writeFloat(this.mStoppedPowerDrawMw);
    paramParcel.writeFloat(this.mSleepPowerDrawMw);
    paramParcel.writeFloat(this.mPeakPowerDrawMw);
    paramParcel.writeInt(this.mMaxPacketLengthBytes);
    paramParcel.writeLong(this.mChrePlatformId);
    paramParcel.writeByte(this.mChreApiMajorVersion);
    paramParcel.writeByte(this.mChreApiMinorVersion);
    paramParcel.writeInt(this.mChrePatchVersion);
    paramParcel.writeInt(this.mSupportedSensors.length);
    paramParcel.writeIntArray(this.mSupportedSensors);
    paramParcel.writeTypedArray((Parcelable[])this.mMemoryRegions, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */