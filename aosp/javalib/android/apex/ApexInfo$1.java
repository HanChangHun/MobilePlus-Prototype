package android.apex;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ApexInfo> {
  public ApexInfo createFromParcel(Parcel paramParcel) {
    ApexInfo apexInfo = new ApexInfo();
    apexInfo.readFromParcel(paramParcel);
    return apexInfo;
  }
  
  public ApexInfo[] newArray(int paramInt) {
    return new ApexInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/apex/ApexInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */