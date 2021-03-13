package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class ChangedPackages implements Parcelable {
  public static final Parcelable.Creator<ChangedPackages> CREATOR = new Parcelable.Creator<ChangedPackages>() {
      public ChangedPackages createFromParcel(Parcel param1Parcel) {
        return new ChangedPackages(param1Parcel);
      }
      
      public ChangedPackages[] newArray(int param1Int) {
        return new ChangedPackages[param1Int];
      }
    };
  
  private final List<String> mPackageNames;
  
  private final int mSequenceNumber;
  
  public ChangedPackages(int paramInt, List<String> paramList) {
    this.mSequenceNumber = paramInt;
    this.mPackageNames = paramList;
  }
  
  protected ChangedPackages(Parcel paramParcel) {
    this.mSequenceNumber = paramParcel.readInt();
    this.mPackageNames = paramParcel.createStringArrayList();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public List<String> getPackageNames() {
    return this.mPackageNames;
  }
  
  public int getSequenceNumber() {
    return this.mSequenceNumber;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mSequenceNumber);
    paramParcel.writeStringList(this.mPackageNames);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ChangedPackages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */