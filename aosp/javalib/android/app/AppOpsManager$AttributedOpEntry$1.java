package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppOpsManager.AttributedOpEntry> {
  public AppOpsManager.AttributedOpEntry createFromParcel(Parcel paramParcel) {
    return new AppOpsManager.AttributedOpEntry(paramParcel);
  }
  
  public AppOpsManager.AttributedOpEntry[] newArray(int paramInt) {
    return new AppOpsManager.AttributedOpEntry[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$AttributedOpEntry$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */