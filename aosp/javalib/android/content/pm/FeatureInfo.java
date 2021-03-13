package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.proto.ProtoOutputStream;

public class FeatureInfo implements Parcelable {
  public static final Parcelable.Creator<FeatureInfo> CREATOR = new Parcelable.Creator<FeatureInfo>() {
      public FeatureInfo createFromParcel(Parcel param1Parcel) {
        return new FeatureInfo(param1Parcel);
      }
      
      public FeatureInfo[] newArray(int param1Int) {
        return new FeatureInfo[param1Int];
      }
    };
  
  public static final int FLAG_REQUIRED = 1;
  
  public static final int GL_ES_VERSION_UNDEFINED = 0;
  
  public int flags;
  
  public String name;
  
  public int reqGlEsVersion;
  
  public int version;
  
  public FeatureInfo() {}
  
  public FeatureInfo(FeatureInfo paramFeatureInfo) {
    this.name = paramFeatureInfo.name;
    this.version = paramFeatureInfo.version;
    this.reqGlEsVersion = paramFeatureInfo.reqGlEsVersion;
    this.flags = paramFeatureInfo.flags;
  }
  
  private FeatureInfo(Parcel paramParcel) {
    this.name = paramParcel.readString8();
    this.version = paramParcel.readInt();
    this.reqGlEsVersion = paramParcel.readInt();
    this.flags = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    String str = this.name;
    if (str != null)
      paramProtoOutputStream.write(1138166333441L, str); 
    paramProtoOutputStream.write(1120986464258L, this.version);
    paramProtoOutputStream.write(1138166333443L, getGlEsVersion());
    paramProtoOutputStream.write(1120986464260L, this.flags);
    paramProtoOutputStream.end(paramLong);
  }
  
  public String getGlEsVersion() {
    int i = this.reqGlEsVersion;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.valueOf((0xFFFF0000 & i) >> 16));
    stringBuilder.append(".");
    stringBuilder.append(String.valueOf(i & 0xFFFF));
    return stringBuilder.toString();
  }
  
  public String toString() {
    if (this.name != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("FeatureInfo{");
      stringBuilder1.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder1.append(" ");
      stringBuilder1.append(this.name);
      stringBuilder1.append(" v=");
      stringBuilder1.append(this.version);
      stringBuilder1.append(" fl=0x");
      stringBuilder1.append(Integer.toHexString(this.flags));
      stringBuilder1.append("}");
      return stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("FeatureInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" glEsVers=");
    stringBuilder.append(getGlEsVersion());
    stringBuilder.append(" fl=0x");
    stringBuilder.append(Integer.toHexString(this.flags));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString8(this.name);
    paramParcel.writeInt(this.version);
    paramParcel.writeInt(this.reqGlEsVersion);
    paramParcel.writeInt(this.flags);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/FeatureInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */