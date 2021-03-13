package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class HdmiPortInfo implements Parcelable {
  public static final Parcelable.Creator<HdmiPortInfo> CREATOR = new Parcelable.Creator<HdmiPortInfo>() {
      public HdmiPortInfo createFromParcel(Parcel param1Parcel) {
        boolean bool1;
        boolean bool2;
        boolean bool3;
        int i = param1Parcel.readInt();
        int j = param1Parcel.readInt();
        int k = param1Parcel.readInt();
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
        return new HdmiPortInfo(i, j, k, bool1, bool3, bool2);
      }
      
      public HdmiPortInfo[] newArray(int param1Int) {
        return new HdmiPortInfo[param1Int];
      }
    };
  
  public static final int PORT_INPUT = 0;
  
  public static final int PORT_OUTPUT = 1;
  
  private final int mAddress;
  
  private final boolean mArcSupported;
  
  private final boolean mCecSupported;
  
  private final int mId;
  
  private final boolean mMhlSupported;
  
  private final int mType;
  
  public HdmiPortInfo(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    this.mId = paramInt1;
    this.mType = paramInt2;
    this.mAddress = paramInt3;
    this.mCecSupported = paramBoolean1;
    this.mArcSupported = paramBoolean3;
    this.mMhlSupported = paramBoolean2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof HdmiPortInfo;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (this.mId == ((HdmiPortInfo)paramObject).mId) {
      bool = bool1;
      if (this.mType == ((HdmiPortInfo)paramObject).mType) {
        bool = bool1;
        if (this.mAddress == ((HdmiPortInfo)paramObject).mAddress) {
          bool = bool1;
          if (this.mCecSupported == ((HdmiPortInfo)paramObject).mCecSupported) {
            bool = bool1;
            if (this.mArcSupported == ((HdmiPortInfo)paramObject).mArcSupported) {
              bool = bool1;
              if (this.mMhlSupported == ((HdmiPortInfo)paramObject).mMhlSupported)
                bool = true; 
            } 
          } 
        } 
      } 
    } 
    return bool;
  }
  
  public int getAddress() {
    return this.mAddress;
  }
  
  public int getId() {
    return this.mId;
  }
  
  public int getType() {
    return this.mType;
  }
  
  public int hashCode() {
    return this.mId;
  }
  
  public boolean isArcSupported() {
    return this.mArcSupported;
  }
  
  public boolean isCecSupported() {
    return this.mCecSupported;
  }
  
  public boolean isMhlSupported() {
    return this.mMhlSupported;
  }
  
  public String toString() {
    String str;
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("port_id: ");
    stringBuffer.append(this.mId);
    stringBuffer.append(", ");
    stringBuffer.append("type: ");
    if (this.mType == 0) {
      str = "HDMI_IN";
    } else {
      str = "HDMI_OUT";
    } 
    stringBuffer.append(str);
    stringBuffer.append(", ");
    stringBuffer.append("address: ");
    stringBuffer.append(String.format("0x%04x", new Object[] { Integer.valueOf(this.mAddress) }));
    stringBuffer.append(", ");
    stringBuffer.append("cec: ");
    stringBuffer.append(this.mCecSupported);
    stringBuffer.append(", ");
    stringBuffer.append("arc: ");
    stringBuffer.append(this.mArcSupported);
    stringBuffer.append(", ");
    stringBuffer.append("mhl: ");
    stringBuffer.append(this.mMhlSupported);
    return stringBuffer.toString();
  }
  
  @SystemApi
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mId);
    paramParcel.writeInt(this.mType);
    paramParcel.writeInt(this.mAddress);
    paramParcel.writeInt(this.mCecSupported);
    paramParcel.writeInt(this.mArcSupported);
    paramParcel.writeInt(this.mMhlSupported);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiPortInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */