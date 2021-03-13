package android.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@Deprecated
public class SavedState implements Parcelable {
  public static final Parcelable.ClassLoaderCreator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
      public Fragment.SavedState createFromParcel(Parcel param2Parcel) {
        return new Fragment.SavedState(param2Parcel, null);
      }
      
      public Fragment.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
        return new Fragment.SavedState(param2Parcel, param2ClassLoader);
      }
      
      public Fragment.SavedState[] newArray(int param2Int) {
        return new Fragment.SavedState[param2Int];
      }
    };
  
  final Bundle mState;
  
  SavedState(Bundle paramBundle) {
    this.mState = paramBundle;
  }
  
  SavedState(Parcel paramParcel, ClassLoader paramClassLoader) {
    Bundle bundle = paramParcel.readBundle();
    this.mState = bundle;
    if (paramClassLoader != null && bundle != null)
      bundle.setClassLoader(paramClassLoader); 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeBundle(this.mState);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Fragment$SavedState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */