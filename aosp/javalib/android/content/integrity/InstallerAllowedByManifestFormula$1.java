package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<InstallerAllowedByManifestFormula> {
  public InstallerAllowedByManifestFormula createFromParcel(Parcel paramParcel) {
    return new InstallerAllowedByManifestFormula(paramParcel, null);
  }
  
  public InstallerAllowedByManifestFormula[] newArray(int paramInt) {
    return new InstallerAllowedByManifestFormula[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/InstallerAllowedByManifestFormula$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */