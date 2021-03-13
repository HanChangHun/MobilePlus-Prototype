package android.app;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class GrantedUriPermission implements Parcelable {
  public static final Parcelable.Creator<GrantedUriPermission> CREATOR = new Parcelable.Creator<GrantedUriPermission>() {
      public GrantedUriPermission createFromParcel(Parcel param1Parcel) {
        return new GrantedUriPermission(param1Parcel);
      }
      
      public GrantedUriPermission[] newArray(int param1Int) {
        return new GrantedUriPermission[param1Int];
      }
    };
  
  public final String packageName;
  
  public final Uri uri;
  
  public GrantedUriPermission(Uri paramUri, String paramString) {
    this.uri = paramUri;
    this.packageName = paramString;
  }
  
  private GrantedUriPermission(Parcel paramParcel) {
    this.uri = (Uri)paramParcel.readParcelable(null);
    this.packageName = paramParcel.readString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.packageName);
    stringBuilder.append(":");
    stringBuilder.append(this.uri);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeParcelable((Parcelable)this.uri, paramInt);
    paramParcel.writeString(this.packageName);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/GrantedUriPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */