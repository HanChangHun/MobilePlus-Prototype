package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public final class ProviderInfoList implements Parcelable {
  public static final Parcelable.Creator<ProviderInfoList> CREATOR = new Parcelable.Creator<ProviderInfoList>() {
      public ProviderInfoList createFromParcel(Parcel param1Parcel) {
        return new ProviderInfoList(param1Parcel);
      }
      
      public ProviderInfoList[] newArray(int param1Int) {
        return new ProviderInfoList[param1Int];
      }
    };
  
  private final List<ProviderInfo> mList;
  
  private ProviderInfoList(Parcel paramParcel) {
    ArrayList<ProviderInfo> arrayList = new ArrayList();
    paramParcel.readTypedList(arrayList, ProviderInfo.CREATOR);
    this.mList = arrayList;
  }
  
  private ProviderInfoList(List<ProviderInfo> paramList) {
    this.mList = paramList;
  }
  
  public static ProviderInfoList fromList(List<ProviderInfo> paramList) {
    return new ProviderInfoList(paramList);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public List<ProviderInfo> getList() {
    return this.mList;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    boolean bool = paramParcel.allowSquashing();
    paramParcel.writeTypedList(this.mList, paramInt);
    paramParcel.restoreAllowSquashing(bool);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ProviderInfoList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */