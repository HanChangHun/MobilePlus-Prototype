package android.app.prediction;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

@SystemApi
public final class AppPredictionSessionId implements Parcelable {
  public static final Parcelable.Creator<AppPredictionSessionId> CREATOR = new Parcelable.Creator<AppPredictionSessionId>() {
      public AppPredictionSessionId createFromParcel(Parcel param1Parcel) {
        return new AppPredictionSessionId(param1Parcel);
      }
      
      public AppPredictionSessionId[] newArray(int param1Int) {
        return new AppPredictionSessionId[param1Int];
      }
    };
  
  private final String mId;
  
  private final int mUserId;
  
  private AppPredictionSessionId(Parcel paramParcel) {
    this.mId = paramParcel.readString();
    this.mUserId = paramParcel.readInt();
  }
  
  public AppPredictionSessionId(String paramString, int paramInt) {
    this.mId = paramString;
    this.mUserId = paramInt;
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
    boolean bool = clazz.equals(object);
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (this.mId.equals(((AppPredictionSessionId)paramObject).mId)) {
      bool = bool1;
      if (this.mUserId == ((AppPredictionSessionId)paramObject).mUserId)
        bool = true; 
    } 
    return bool;
  }
  
  public int getUserId() {
    return this.mUserId;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mId, Integer.valueOf(this.mUserId) });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mId);
    stringBuilder.append(",");
    stringBuilder.append(this.mUserId);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mId);
    paramParcel.writeInt(this.mUserId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppPredictionSessionId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */