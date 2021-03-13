package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ApplicationInfo> {
  public ApplicationInfo createFromParcel(Parcel paramParcel) {
    return (ApplicationInfo)paramParcel.readSquashed((Parcel.SquashReadHelper)_$$Lambda$ApplicationInfo$1$FDtFc_prTtONpy6YSScuAiML69E.INSTANCE);
  }
  
  public ApplicationInfo[] newArray(int paramInt) {
    return new ApplicationInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ApplicationInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */