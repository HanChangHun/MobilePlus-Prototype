package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public final class SigningInfo implements Parcelable {
  public static final Parcelable.Creator<SigningInfo> CREATOR = new Parcelable.Creator<SigningInfo>() {
      public SigningInfo createFromParcel(Parcel param1Parcel) {
        return new SigningInfo(param1Parcel);
      }
      
      public SigningInfo[] newArray(int param1Int) {
        return new SigningInfo[param1Int];
      }
    };
  
  private final PackageParser.SigningDetails mSigningDetails = PackageParser.SigningDetails.UNKNOWN;
  
  public SigningInfo() {}
  
  public SigningInfo(PackageParser.SigningDetails paramSigningDetails) {}
  
  public SigningInfo(SigningInfo paramSigningInfo) {}
  
  private SigningInfo(Parcel paramParcel) {}
  
  public int describeContents() {
    return 0;
  }
  
  public Signature[] getApkContentsSigners() {
    return this.mSigningDetails.signatures;
  }
  
  public Signature[] getSigningCertificateHistory() {
    return hasMultipleSigners() ? null : (!hasPastSigningCertificates() ? this.mSigningDetails.signatures : this.mSigningDetails.pastSigningCertificates);
  }
  
  public boolean hasMultipleSigners() {
    Signature[] arrayOfSignature = this.mSigningDetails.signatures;
    boolean bool = true;
    if (arrayOfSignature == null || this.mSigningDetails.signatures.length <= 1)
      bool = false; 
    return bool;
  }
  
  public boolean hasPastSigningCertificates() {
    boolean bool;
    if (this.mSigningDetails.signatures != null && this.mSigningDetails.pastSigningCertificates != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    this.mSigningDetails.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/SigningInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */