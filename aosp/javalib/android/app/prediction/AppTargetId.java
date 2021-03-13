package android.app.prediction;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class AppTargetId implements Parcelable {
  public static final Parcelable.Creator<AppTargetId> CREATOR = new Parcelable.Creator<AppTargetId>() {
      public AppTargetId createFromParcel(Parcel param1Parcel) {
        return new AppTargetId(param1Parcel);
      }
      
      public AppTargetId[] newArray(int param1Int) {
        return new AppTargetId[param1Int];
      }
    };
  
  private final String mId;
  
  private AppTargetId(Parcel paramParcel) {
    this.mId = paramParcel.readString();
  }
  
  @SystemApi
  public AppTargetId(String paramString) {
    this.mId = paramString;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    Object object;
    Class<?> clazz = getClass();
    if (paramObject != null) {
      object = paramObject.getClass();
    } else {
      object = null;
    } 
    if (!clazz.equals(object))
      return false; 
    paramObject = paramObject;
    return this.mId.equals(((AppTargetId)paramObject).mId);
  }
  
  public String getId() {
    return this.mId;
  }
  
  public int hashCode() {
    return this.mId.hashCode();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppTargetId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */