package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<InstallationFileParcel> {
  public InstallationFileParcel createFromParcel(Parcel paramParcel) {
    InstallationFileParcel installationFileParcel = new InstallationFileParcel();
    installationFileParcel.readFromParcel(paramParcel);
    return installationFileParcel;
  }
  
  public InstallationFileParcel[] newArray(int paramInt) {
    return new InstallationFileParcel[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstallationFileParcel$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */