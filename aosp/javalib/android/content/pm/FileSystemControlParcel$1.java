package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<FileSystemControlParcel> {
  public FileSystemControlParcel createFromParcel(Parcel paramParcel) {
    FileSystemControlParcel fileSystemControlParcel = new FileSystemControlParcel();
    fileSystemControlParcel.readFromParcel(paramParcel);
    return fileSystemControlParcel;
  }
  
  public FileSystemControlParcel[] newArray(int paramInt) {
    return new FileSystemControlParcel[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/FileSystemControlParcel$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */