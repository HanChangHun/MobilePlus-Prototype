package android.app;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

@SystemApi
public final class PackageOps implements Parcelable {
  public static final Parcelable.Creator<PackageOps> CREATOR = new Parcelable.Creator<PackageOps>() {
      public AppOpsManager.PackageOps createFromParcel(Parcel param2Parcel) {
        return new AppOpsManager.PackageOps(param2Parcel);
      }
      
      public AppOpsManager.PackageOps[] newArray(int param2Int) {
        return new AppOpsManager.PackageOps[param2Int];
      }
    };
  
  private final List<AppOpsManager.OpEntry> mEntries;
  
  private final String mPackageName;
  
  private final int mUid;
  
  PackageOps(Parcel paramParcel) {
    this.mPackageName = paramParcel.readString();
    this.mUid = paramParcel.readInt();
    this.mEntries = new ArrayList<>();
    int i = paramParcel.readInt();
    for (byte b = 0; b < i; b++)
      this.mEntries.add((AppOpsManager.OpEntry)AppOpsManager.OpEntry.CREATOR.createFromParcel(paramParcel)); 
  }
  
  public PackageOps(String paramString, int paramInt, List<AppOpsManager.OpEntry> paramList) {
    this.mPackageName = paramString;
    this.mUid = paramInt;
    this.mEntries = paramList;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public List<AppOpsManager.OpEntry> getOps() {
    return this.mEntries;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public int getUid() {
    return this.mUid;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeInt(this.mUid);
    paramParcel.writeInt(this.mEntries.size());
    for (byte b = 0; b < this.mEntries.size(); b++)
      ((AppOpsManager.OpEntry)this.mEntries.get(b)).writeToParcel(paramParcel, paramInt); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$PackageOps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */