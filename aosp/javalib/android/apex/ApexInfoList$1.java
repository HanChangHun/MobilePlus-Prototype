package android.apex;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ApexInfoList> {
  public ApexInfoList createFromParcel(Parcel paramParcel) {
    ApexInfoList apexInfoList = new ApexInfoList();
    apexInfoList.readFromParcel(paramParcel);
    return apexInfoList;
  }
  
  public ApexInfoList[] newArray(int paramInt) {
    return new ApexInfoList[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/apex/ApexInfoList$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */