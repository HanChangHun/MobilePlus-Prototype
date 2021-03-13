package android.apex;

import android.os.Parcel;
import android.os.Parcelable;

public class ApexInfoList implements Parcelable {
  public static final Parcelable.Creator<ApexInfoList> CREATOR = new Parcelable.Creator<ApexInfoList>() {
      public ApexInfoList createFromParcel(Parcel param1Parcel) {
        ApexInfoList apexInfoList = new ApexInfoList();
        apexInfoList.readFromParcel(param1Parcel);
        return apexInfoList;
      }
      
      public ApexInfoList[] newArray(int param1Int) {
        return new ApexInfoList[param1Int];
      }
    };
  
  public ApexInfo[] apexInfos;
  
  public int describeContents() {
    return 0;
  }
  
  public final void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.dataPosition();
    int j = paramParcel.readInt();
    if (j < 0)
      return; 
    try {
      this.apexInfos = (ApexInfo[])paramParcel.createTypedArray(ApexInfo.CREATOR);
      int k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      return;
    } finally {
      paramParcel.setDataPosition(i + j);
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = paramParcel.dataPosition();
    paramParcel.writeInt(0);
    paramParcel.writeTypedArray((Parcelable[])this.apexInfos, 0);
    int i = paramParcel.dataPosition();
    paramParcel.setDataPosition(paramInt);
    paramParcel.writeInt(i - paramInt);
    paramParcel.setDataPosition(i);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/apex/ApexInfoList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */