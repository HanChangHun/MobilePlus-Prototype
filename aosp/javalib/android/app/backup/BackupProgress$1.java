package android.app.backup;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BackupProgress> {
  public BackupProgress createFromParcel(Parcel paramParcel) {
    return new BackupProgress(paramParcel, null);
  }
  
  public BackupProgress[] newArray(int paramInt) {
    return new BackupProgress[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupProgress$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */