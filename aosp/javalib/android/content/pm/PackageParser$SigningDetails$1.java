package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageParser.SigningDetails> {
  public PackageParser.SigningDetails createFromParcel(Parcel paramParcel) {
    return paramParcel.readBoolean() ? PackageParser.SigningDetails.UNKNOWN : new PackageParser.SigningDetails(paramParcel);
  }
  
  public PackageParser.SigningDetails[] newArray(int paramInt) {
    return new PackageParser.SigningDetails[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$SigningDetails$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */