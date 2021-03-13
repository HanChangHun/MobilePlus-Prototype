package android.content.pm.permission;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

class null implements Parcelable.Creator<SplitPermissionInfoParcelable> {
  public SplitPermissionInfoParcelable createFromParcel(Parcel paramParcel) {
    String str = paramParcel.readString();
    ArrayList<String> arrayList = new ArrayList();
    paramParcel.readStringList(arrayList);
    return new SplitPermissionInfoParcelable(str, arrayList, paramParcel.readInt());
  }
  
  public SplitPermissionInfoParcelable[] newArray(int paramInt) {
    return new SplitPermissionInfoParcelable[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/permission/SplitPermissionInfoParcelable$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */