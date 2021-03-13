package android.apex;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ApexSessionInfo> {
  public ApexSessionInfo createFromParcel(Parcel paramParcel) {
    ApexSessionInfo apexSessionInfo = new ApexSessionInfo();
    apexSessionInfo.readFromParcel(paramParcel);
    return apexSessionInfo;
  }
  
  public ApexSessionInfo[] newArray(int paramInt) {
    return new ApexSessionInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/apex/ApexSessionInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */