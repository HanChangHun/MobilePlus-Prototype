package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.PatternMatcher;

public class PathPermission extends PatternMatcher {
  public static final Parcelable.Creator<PathPermission> CREATOR = new Parcelable.Creator<PathPermission>() {
      public PathPermission createFromParcel(Parcel param1Parcel) {
        return new PathPermission(param1Parcel);
      }
      
      public PathPermission[] newArray(int param1Int) {
        return new PathPermission[param1Int];
      }
    };
  
  private final String mReadPermission;
  
  private final String mWritePermission;
  
  public PathPermission(Parcel paramParcel) {
    super(paramParcel);
    this.mReadPermission = paramParcel.readString();
    this.mWritePermission = paramParcel.readString();
  }
  
  public PathPermission(String paramString1, int paramInt, String paramString2, String paramString3) {
    super(paramString1, paramInt);
    this.mReadPermission = paramString2;
    this.mWritePermission = paramString3;
  }
  
  public String getReadPermission() {
    return this.mReadPermission;
  }
  
  public String getWritePermission() {
    return this.mWritePermission;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.mReadPermission);
    paramParcel.writeString(this.mWritePermission);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PathPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */