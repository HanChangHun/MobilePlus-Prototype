package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

class null implements Parcelable.Creator<FactoryResetProtectionPolicy> {
  public FactoryResetProtectionPolicy createFromParcel(Parcel paramParcel) {
    ArrayList<String> arrayList = new ArrayList();
    int i = paramParcel.readInt();
    for (byte b = 0; b < i; b++)
      arrayList.add(paramParcel.readString()); 
    return new FactoryResetProtectionPolicy(arrayList, paramParcel.readBoolean(), null);
  }
  
  public FactoryResetProtectionPolicy[] newArray(int paramInt) {
    return new FactoryResetProtectionPolicy[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/FactoryResetProtectionPolicy$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */