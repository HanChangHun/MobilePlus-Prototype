package android.content.res;

import android.os.Parcel;
import android.os.Parcelable;

public class ObbInfo implements Parcelable {
  public static final Parcelable.Creator<ObbInfo> CREATOR = new Parcelable.Creator<ObbInfo>() {
      public ObbInfo createFromParcel(Parcel param1Parcel) {
        return new ObbInfo(param1Parcel);
      }
      
      public ObbInfo[] newArray(int param1Int) {
        return new ObbInfo[param1Int];
      }
    };
  
  public static final int OBB_OVERLAY = 1;
  
  public String filename;
  
  public int flags;
  
  public String packageName;
  
  public byte[] salt;
  
  public int version;
  
  ObbInfo() {}
  
  private ObbInfo(Parcel paramParcel) {
    this.filename = paramParcel.readString();
    this.packageName = paramParcel.readString();
    this.version = paramParcel.readInt();
    this.flags = paramParcel.readInt();
    this.salt = paramParcel.createByteArray();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ObbInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" packageName=");
    stringBuilder.append(this.packageName);
    stringBuilder.append(",version=");
    stringBuilder.append(this.version);
    stringBuilder.append(",flags=");
    stringBuilder.append(this.flags);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.filename);
    paramParcel.writeString(this.packageName);
    paramParcel.writeInt(this.version);
    paramParcel.writeInt(this.flags);
    paramParcel.writeByteArray(this.salt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ObbInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */