package android.app.contentsuggestions;

import android.annotation.SystemApi;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class ContentSelection implements Parcelable {
  public static final Parcelable.Creator<ContentSelection> CREATOR = new Parcelable.Creator<ContentSelection>() {
      public ContentSelection createFromParcel(Parcel param1Parcel) {
        return new ContentSelection(param1Parcel.readString(), param1Parcel.readBundle());
      }
      
      public ContentSelection[] newArray(int param1Int) {
        return new ContentSelection[param1Int];
      }
    };
  
  private final Bundle mExtras;
  
  private final String mSelectionId;
  
  public ContentSelection(String paramString, Bundle paramBundle) {
    this.mSelectionId = paramString;
    this.mExtras = paramBundle;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public String getId() {
    return this.mSelectionId;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mSelectionId);
    paramParcel.writeBundle(this.mExtras);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ContentSelection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */