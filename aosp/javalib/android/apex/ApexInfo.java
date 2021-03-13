package android.apex;

import android.os.Parcel;
import android.os.Parcelable;

public class ApexInfo implements Parcelable {
  public static final Parcelable.Creator<ApexInfo> CREATOR = new Parcelable.Creator<ApexInfo>() {
      public ApexInfo createFromParcel(Parcel param1Parcel) {
        ApexInfo apexInfo = new ApexInfo();
        apexInfo.readFromParcel(param1Parcel);
        return apexInfo;
      }
      
      public ApexInfo[] newArray(int param1Int) {
        return new ApexInfo[param1Int];
      }
    };
  
  public boolean isActive;
  
  public boolean isFactory;
  
  public String moduleName;
  
  public String modulePath;
  
  public String preinstalledModulePath;
  
  public long versionCode;
  
  public String versionName;
  
  public int describeContents() {
    return 0;
  }
  
  public final void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.dataPosition();
    int j = paramParcel.readInt();
    if (j < 0)
      return; 
    try {
      boolean bool2;
      this.moduleName = paramParcel.readString();
      int k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.modulePath = paramParcel.readString();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.preinstalledModulePath = paramParcel.readString();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.versionCode = paramParcel.readLong();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.versionName = paramParcel.readString();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      k = paramParcel.readInt();
      boolean bool1 = true;
      if (k != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.isFactory = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.isActive = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      return;
    } finally {
      paramParcel.setDataPosition(i + j);
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = paramParcel.dataPosition();
    paramParcel.writeInt(0);
    paramParcel.writeString(this.moduleName);
    paramParcel.writeString(this.modulePath);
    paramParcel.writeString(this.preinstalledModulePath);
    paramParcel.writeLong(this.versionCode);
    paramParcel.writeString(this.versionName);
    paramParcel.writeInt(this.isFactory);
    paramParcel.writeInt(this.isActive);
    paramInt = paramParcel.dataPosition();
    paramParcel.setDataPosition(i);
    paramParcel.writeInt(paramInt - i);
    paramParcel.setDataPosition(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/apex/ApexInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */