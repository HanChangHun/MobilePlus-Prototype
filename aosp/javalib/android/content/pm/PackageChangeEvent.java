package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class PackageChangeEvent implements Parcelable {
  public static final Parcelable.Creator<PackageChangeEvent> CREATOR = new Parcelable.Creator<PackageChangeEvent>() {
      public PackageChangeEvent createFromParcel(Parcel param1Parcel) {
        PackageChangeEvent packageChangeEvent = new PackageChangeEvent();
        packageChangeEvent.readFromParcel(param1Parcel);
        return packageChangeEvent;
      }
      
      public PackageChangeEvent[] newArray(int param1Int) {
        return new PackageChangeEvent[param1Int];
      }
    };
  
  public boolean dataRemoved;
  
  public boolean isDeleted;
  
  public long lastUpdateTimeMillis;
  
  public boolean newInstalled;
  
  public String packageName;
  
  public long version;
  
  public int describeContents() {
    return 0;
  }
  
  public final void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.dataPosition();
    int j = paramParcel.readInt();
    if (j < 0)
      return; 
    try {
      boolean bool2;
      this.packageName = paramParcel.readString();
      int k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.version = paramParcel.readLong();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.lastUpdateTimeMillis = paramParcel.readLong();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      k = paramParcel.readInt();
      boolean bool1 = true;
      if (k != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.newInstalled = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.dataRemoved = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.isDeleted = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      return;
    } finally {
      paramParcel.setDataPosition(i + j);
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = paramParcel.dataPosition();
    paramParcel.writeInt(0);
    paramParcel.writeString(this.packageName);
    paramParcel.writeLong(this.version);
    paramParcel.writeLong(this.lastUpdateTimeMillis);
    paramParcel.writeInt(this.newInstalled);
    paramParcel.writeInt(this.dataRemoved);
    paramParcel.writeInt(this.isDeleted);
    int i = paramParcel.dataPosition();
    paramParcel.setDataPosition(paramInt);
    paramParcel.writeInt(i - paramInt);
    paramParcel.setDataPosition(i);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */