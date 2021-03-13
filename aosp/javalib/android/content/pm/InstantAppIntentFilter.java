package android.content.pm;

import android.annotation.SystemApi;
import android.content.IntentFilter;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

@SystemApi
public final class InstantAppIntentFilter implements Parcelable {
  public static final Parcelable.Creator<InstantAppIntentFilter> CREATOR = new Parcelable.Creator<InstantAppIntentFilter>() {
      public InstantAppIntentFilter createFromParcel(Parcel param1Parcel) {
        return new InstantAppIntentFilter(param1Parcel);
      }
      
      public InstantAppIntentFilter[] newArray(int param1Int) {
        return new InstantAppIntentFilter[param1Int];
      }
    };
  
  private final List<IntentFilter> mFilters = new ArrayList<>();
  
  private final String mSplitName;
  
  InstantAppIntentFilter(Parcel paramParcel) {
    this.mSplitName = paramParcel.readString();
    paramParcel.readList(this.mFilters, null);
  }
  
  public InstantAppIntentFilter(String paramString, List<IntentFilter> paramList) {
    if (paramList != null && paramList.size() != 0) {
      this.mSplitName = paramString;
      this.mFilters.addAll(paramList);
      return;
    } 
    throw new IllegalArgumentException();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public List<IntentFilter> getFilters() {
    return this.mFilters;
  }
  
  public String getSplitName() {
    return this.mSplitName;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mSplitName);
    paramParcel.writeList(this.mFilters);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstantAppIntentFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */