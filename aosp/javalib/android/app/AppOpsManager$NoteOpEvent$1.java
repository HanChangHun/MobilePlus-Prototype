package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppOpsManager.NoteOpEvent> {
  public AppOpsManager.NoteOpEvent createFromParcel(Parcel paramParcel) {
    return new AppOpsManager.NoteOpEvent(paramParcel);
  }
  
  public AppOpsManager.NoteOpEvent[] newArray(int paramInt) {
    return new AppOpsManager.NoteOpEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$NoteOpEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */