package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.PublicKey;

public class VerifierInfo implements Parcelable {
  public static final Parcelable.Creator<VerifierInfo> CREATOR = new Parcelable.Creator<VerifierInfo>() {
      public VerifierInfo createFromParcel(Parcel param1Parcel) {
        return new VerifierInfo(param1Parcel);
      }
      
      public VerifierInfo[] newArray(int param1Int) {
        return new VerifierInfo[param1Int];
      }
    };
  
  public final String packageName;
  
  public final PublicKey publicKey;
  
  private VerifierInfo(Parcel paramParcel) {
    this.packageName = paramParcel.readString();
    this.publicKey = (PublicKey)paramParcel.readSerializable();
  }
  
  public VerifierInfo(String paramString, PublicKey paramPublicKey) {
    if (paramString != null && paramString.length() != 0) {
      if (paramPublicKey != null) {
        this.packageName = paramString;
        this.publicKey = paramPublicKey;
        return;
      } 
      throw new IllegalArgumentException("publicKey must not be null");
    } 
    throw new IllegalArgumentException("packageName must not be null or empty");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.packageName);
    paramParcel.writeSerializable(this.publicKey);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/VerifierInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */