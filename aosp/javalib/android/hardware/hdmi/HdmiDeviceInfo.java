package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public class HdmiDeviceInfo implements Parcelable {
  public static final int ADDR_INTERNAL = 0;
  
  public static final Parcelable.Creator<HdmiDeviceInfo> CREATOR;
  
  public static final int DEVICE_AUDIO_SYSTEM = 5;
  
  public static final int DEVICE_INACTIVE = -1;
  
  public static final int DEVICE_PLAYBACK = 4;
  
  public static final int DEVICE_PURE_CEC_SWITCH = 6;
  
  public static final int DEVICE_RECORDER = 1;
  
  public static final int DEVICE_RESERVED = 2;
  
  public static final int DEVICE_TUNER = 3;
  
  public static final int DEVICE_TV = 0;
  
  public static final int DEVICE_VIDEO_PROCESSOR = 7;
  
  private static final int HDMI_DEVICE_TYPE_CEC = 0;
  
  private static final int HDMI_DEVICE_TYPE_HARDWARE = 2;
  
  private static final int HDMI_DEVICE_TYPE_INACTIVE = 100;
  
  private static final int HDMI_DEVICE_TYPE_MHL = 1;
  
  public static final int ID_INVALID = 65535;
  
  private static final int ID_OFFSET_CEC = 0;
  
  private static final int ID_OFFSET_HARDWARE = 192;
  
  private static final int ID_OFFSET_MHL = 128;
  
  public static final HdmiDeviceInfo INACTIVE_DEVICE = new HdmiDeviceInfo();
  
  public static final int PATH_INTERNAL = 0;
  
  public static final int PATH_INVALID = 65535;
  
  public static final int PORT_INVALID = -1;
  
  private final int mAdopterId;
  
  private final int mDeviceId;
  
  private final int mDevicePowerStatus;
  
  private final int mDeviceType;
  
  private final String mDisplayName;
  
  private final int mHdmiDeviceType = 100;
  
  private final int mId;
  
  private final int mLogicalAddress;
  
  private final int mPhysicalAddress = 65535;
  
  private final int mPortId;
  
  private final int mVendorId;
  
  static {
    CREATOR = new Parcelable.Creator<HdmiDeviceInfo>() {
        public HdmiDeviceInfo createFromParcel(Parcel param1Parcel) {
          int i = param1Parcel.readInt();
          int j = param1Parcel.readInt();
          int k = param1Parcel.readInt();
          if (i != 0) {
            if (i != 1)
              return (i != 2) ? ((i != 100) ? null : HdmiDeviceInfo.INACTIVE_DEVICE) : new HdmiDeviceInfo(j, k); 
            i = param1Parcel.readInt();
            return new HdmiDeviceInfo(j, k, param1Parcel.readInt(), i);
          } 
          int m = param1Parcel.readInt();
          int n = param1Parcel.readInt();
          i = param1Parcel.readInt();
          int i1 = param1Parcel.readInt();
          return new HdmiDeviceInfo(m, j, k, n, i, param1Parcel.readString(), i1);
        }
        
        public HdmiDeviceInfo[] newArray(int param1Int) {
          return new HdmiDeviceInfo[param1Int];
        }
      };
  }
  
  public HdmiDeviceInfo() {
    this.mId = 65535;
    this.mLogicalAddress = -1;
    this.mDeviceType = -1;
    this.mPortId = -1;
    this.mDevicePowerStatus = -1;
    this.mDisplayName = "Inactive";
    this.mVendorId = 0;
    this.mDeviceId = -1;
    this.mAdopterId = -1;
  }
  
  public HdmiDeviceInfo(int paramInt1, int paramInt2) {
    this.mPortId = paramInt2;
    this.mId = idForHardware(paramInt2);
    this.mLogicalAddress = -1;
    this.mDeviceType = 2;
    this.mVendorId = 0;
    this.mDevicePowerStatus = -1;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("HDMI");
    stringBuilder.append(paramInt2);
    this.mDisplayName = stringBuilder.toString();
    this.mDeviceId = -1;
    this.mAdopterId = -1;
  }
  
  public HdmiDeviceInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mPortId = paramInt2;
    this.mId = idForMhlDevice(paramInt2);
    this.mLogicalAddress = -1;
    this.mDeviceType = 2;
    this.mVendorId = 0;
    this.mDevicePowerStatus = -1;
    this.mDisplayName = "Mobile";
    this.mDeviceId = paramInt3;
    this.mAdopterId = paramInt4;
  }
  
  public HdmiDeviceInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString) {
    this(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramString, -1);
  }
  
  public HdmiDeviceInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString, int paramInt6) {
    this.mPortId = paramInt3;
    this.mId = idForCecDevice(paramInt1);
    this.mLogicalAddress = paramInt1;
    this.mDeviceType = paramInt4;
    this.mVendorId = paramInt5;
    this.mDevicePowerStatus = paramInt6;
    this.mDisplayName = paramString;
    this.mDeviceId = -1;
    this.mAdopterId = -1;
  }
  
  public static int idForCecDevice(int paramInt) {
    return paramInt + 0;
  }
  
  public static int idForHardware(int paramInt) {
    return paramInt + 192;
  }
  
  public static int idForMhlDevice(int paramInt) {
    return paramInt + 128;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof HdmiDeviceInfo;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    if (this.mHdmiDeviceType == ((HdmiDeviceInfo)paramObject).mHdmiDeviceType && this.mPhysicalAddress == ((HdmiDeviceInfo)paramObject).mPhysicalAddress && this.mPortId == ((HdmiDeviceInfo)paramObject).mPortId && this.mLogicalAddress == ((HdmiDeviceInfo)paramObject).mLogicalAddress && this.mDeviceType == ((HdmiDeviceInfo)paramObject).mDeviceType && this.mVendorId == ((HdmiDeviceInfo)paramObject).mVendorId && this.mDevicePowerStatus == ((HdmiDeviceInfo)paramObject).mDevicePowerStatus && this.mDisplayName.equals(((HdmiDeviceInfo)paramObject).mDisplayName) && this.mDeviceId == ((HdmiDeviceInfo)paramObject).mDeviceId && this.mAdopterId == ((HdmiDeviceInfo)paramObject).mAdopterId)
      bool1 = true; 
    return bool1;
  }
  
  public int getAdopterId() {
    return this.mAdopterId;
  }
  
  public int getDeviceId() {
    return this.mDeviceId;
  }
  
  public int getDevicePowerStatus() {
    return this.mDevicePowerStatus;
  }
  
  public int getDeviceType() {
    return this.mDeviceType;
  }
  
  public String getDisplayName() {
    return this.mDisplayName;
  }
  
  public int getId() {
    return this.mId;
  }
  
  public int getLogicalAddress() {
    return this.mLogicalAddress;
  }
  
  public int getPhysicalAddress() {
    return this.mPhysicalAddress;
  }
  
  public int getPortId() {
    return this.mPortId;
  }
  
  public int getVendorId() {
    return this.mVendorId;
  }
  
  public boolean isCecDevice() {
    boolean bool;
    if (this.mHdmiDeviceType == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isInactivated() {
    boolean bool;
    if (this.mHdmiDeviceType == 100) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isMhlDevice() {
    int i = this.mHdmiDeviceType;
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public boolean isSourceType() {
    boolean bool = isCecDevice();
    boolean bool1 = false;
    if (bool) {
      int i = this.mDeviceType;
      if (i == 4 || i == 1 || i == 3)
        bool1 = true; 
      return bool1;
    } 
    return isMhlDevice();
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    int i = this.mHdmiDeviceType;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 100)
            return ""; 
          stringBuffer.append("Inactivated: ");
        } else {
          stringBuffer.append("Hardware: ");
        } 
      } else {
        stringBuffer.append("MHL: ");
        stringBuffer.append("device_id: ");
        stringBuffer.append(String.format("0x%04X", new Object[] { Integer.valueOf(this.mDeviceId) }));
        stringBuffer.append(" ");
        stringBuffer.append("adopter_id: ");
        stringBuffer.append(String.format("0x%04X", new Object[] { Integer.valueOf(this.mAdopterId) }));
        stringBuffer.append(" ");
      } 
    } else {
      stringBuffer.append("CEC: ");
      stringBuffer.append("logical_address: ");
      stringBuffer.append(String.format("0x%02X", new Object[] { Integer.valueOf(this.mLogicalAddress) }));
      stringBuffer.append(" ");
      stringBuffer.append("device_type: ");
      stringBuffer.append(this.mDeviceType);
      stringBuffer.append(" ");
      stringBuffer.append("vendor_id: ");
      stringBuffer.append(this.mVendorId);
      stringBuffer.append(" ");
      stringBuffer.append("display_name: ");
      stringBuffer.append(this.mDisplayName);
      stringBuffer.append(" ");
      stringBuffer.append("power_status: ");
      stringBuffer.append(this.mDevicePowerStatus);
      stringBuffer.append(" ");
    } 
    stringBuffer.append("physical_address: ");
    stringBuffer.append(String.format("0x%04X", new Object[] { Integer.valueOf(this.mPhysicalAddress) }));
    stringBuffer.append(" ");
    stringBuffer.append("port_id: ");
    stringBuffer.append(this.mPortId);
    return stringBuffer.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mHdmiDeviceType);
    paramParcel.writeInt(this.mPhysicalAddress);
    paramParcel.writeInt(this.mPortId);
    paramInt = this.mHdmiDeviceType;
    if (paramInt != 0) {
      if (paramInt == 1) {
        paramParcel.writeInt(this.mDeviceId);
        paramParcel.writeInt(this.mAdopterId);
      } 
    } else {
      paramParcel.writeInt(this.mLogicalAddress);
      paramParcel.writeInt(this.mDeviceType);
      paramParcel.writeInt(this.mVendorId);
      paramParcel.writeInt(this.mDevicePowerStatus);
      paramParcel.writeString(this.mDisplayName);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiDeviceInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */