package android.app.backup;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public class RestoreDescription implements Parcelable {
  public static final Parcelable.Creator<RestoreDescription> CREATOR;
  
  public static final RestoreDescription NO_MORE_PACKAGES = new RestoreDescription("NO_MORE_PACKAGES", 0);
  
  private static final String NO_MORE_PACKAGES_SENTINEL = "NO_MORE_PACKAGES";
  
  public static final int TYPE_FULL_STREAM = 2;
  
  public static final int TYPE_KEY_VALUE = 1;
  
  private final int mDataType;
  
  private final String mPackageName;
  
  static {
    CREATOR = new Parcelable.Creator<RestoreDescription>() {
        public RestoreDescription createFromParcel(Parcel param1Parcel) {
          RestoreDescription restoreDescription = new RestoreDescription(param1Parcel);
          if ("NO_MORE_PACKAGES".equals(restoreDescription.mPackageName))
            restoreDescription = RestoreDescription.NO_MORE_PACKAGES; 
          return restoreDescription;
        }
        
        public RestoreDescription[] newArray(int param1Int) {
          return new RestoreDescription[param1Int];
        }
      };
  }
  
  private RestoreDescription(Parcel paramParcel) {
    this.mPackageName = paramParcel.readString();
    this.mDataType = paramParcel.readInt();
  }
  
  public RestoreDescription(String paramString, int paramInt) {
    this.mPackageName = paramString;
    this.mDataType = paramInt;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getDataType() {
    return this.mDataType;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("RestoreDescription{");
    stringBuilder.append(this.mPackageName);
    stringBuilder.append(" : ");
    if (this.mDataType == 1) {
      str = "KEY_VALUE";
    } else {
      str = "STREAM";
    } 
    stringBuilder.append(str);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeInt(this.mDataType);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/RestoreDescription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */