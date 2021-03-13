package android.app.contentsuggestions;

import android.annotation.SystemApi;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class ContentClassification implements Parcelable {
  public static final Parcelable.Creator<ContentClassification> CREATOR = new Parcelable.Creator<ContentClassification>() {
      public ContentClassification createFromParcel(Parcel param1Parcel) {
        return new ContentClassification(param1Parcel.readString(), param1Parcel.readBundle());
      }
      
      public ContentClassification[] newArray(int param1Int) {
        return new ContentClassification[param1Int];
      }
    };
  
  private final String mClassificationId;
  
  private final Bundle mExtras;
  
  public ContentClassification(String paramString, Bundle paramBundle) {
    this.mClassificationId = paramString;
    this.mExtras = paramBundle;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public String getId() {
    return this.mClassificationId;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mClassificationId);
    paramParcel.writeBundle(this.mExtras);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ContentClassification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */