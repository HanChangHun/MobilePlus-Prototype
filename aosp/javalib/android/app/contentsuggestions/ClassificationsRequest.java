package android.app.contentsuggestions;

import android.annotation.SystemApi;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

@SystemApi
public final class ClassificationsRequest implements Parcelable {
  public static final Parcelable.Creator<ClassificationsRequest> CREATOR = new Parcelable.Creator<ClassificationsRequest>() {
      public ClassificationsRequest createFromParcel(Parcel param1Parcel) {
        return new ClassificationsRequest(param1Parcel.createTypedArrayList(ContentSelection.CREATOR), param1Parcel.readBundle());
      }
      
      public ClassificationsRequest[] newArray(int param1Int) {
        return new ClassificationsRequest[param1Int];
      }
    };
  
  private final Bundle mExtras;
  
  private final List<ContentSelection> mSelections;
  
  private ClassificationsRequest(List<ContentSelection> paramList, Bundle paramBundle) {
    this.mSelections = paramList;
    this.mExtras = paramBundle;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Bundle getExtras() {
    Bundle bundle1 = this.mExtras;
    Bundle bundle2 = bundle1;
    if (bundle1 == null)
      bundle2 = new Bundle(); 
    return bundle2;
  }
  
  public List<ContentSelection> getSelections() {
    return this.mSelections;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedList(this.mSelections);
    paramParcel.writeBundle(this.mExtras);
  }
  
  @SystemApi
  public static final class Builder {
    private Bundle mExtras;
    
    private final List<ContentSelection> mSelections;
    
    public Builder(List<ContentSelection> param1List) {
      this.mSelections = param1List;
    }
    
    public ClassificationsRequest build() {
      return new ClassificationsRequest(this.mSelections, this.mExtras);
    }
    
    public Builder setExtras(Bundle param1Bundle) {
      this.mExtras = param1Bundle;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ClassificationsRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */