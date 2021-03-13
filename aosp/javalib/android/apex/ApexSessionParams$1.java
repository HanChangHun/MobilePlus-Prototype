package android.apex;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ApexSessionParams> {
  public ApexSessionParams createFromParcel(Parcel paramParcel) {
    ApexSessionParams apexSessionParams = new ApexSessionParams();
    apexSessionParams.readFromParcel(paramParcel);
    return apexSessionParams;
  }
  
  public ApexSessionParams[] newArray(int paramInt) {
    return new ApexSessionParams[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/apex/ApexSessionParams$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */