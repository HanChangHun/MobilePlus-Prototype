package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class InstallationFileParcel implements Parcelable {
  public static final Parcelable.Creator<InstallationFileParcel> CREATOR = new Parcelable.Creator<InstallationFileParcel>() {
      public InstallationFileParcel createFromParcel(Parcel param1Parcel) {
        InstallationFileParcel installationFileParcel = new InstallationFileParcel();
        installationFileParcel.readFromParcel(param1Parcel);
        return installationFileParcel;
      }
      
      public InstallationFileParcel[] newArray(int param1Int) {
        return new InstallationFileParcel[param1Int];
      }
    };
  
  public int location;
  
  public byte[] metadata;
  
  public String name;
  
  public byte[] signature;
  
  public long size;
  
  public int describeContents() {
    return 0;
  }
  
  public final void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.dataPosition();
    int j = paramParcel.readInt();
    if (j < 0)
      return; 
    try {
      this.name = paramParcel.readString();
      int k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.location = paramParcel.readInt();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.size = paramParcel.readLong();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.metadata = paramParcel.createByteArray();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.signature = paramParcel.createByteArray();
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
    paramParcel.writeString(this.name);
    paramParcel.writeInt(this.location);
    paramParcel.writeLong(this.size);
    paramParcel.writeByteArray(this.metadata);
    paramParcel.writeByteArray(this.signature);
    int i = paramParcel.dataPosition();
    paramParcel.setDataPosition(paramInt);
    paramParcel.writeInt(i - paramInt);
    paramParcel.setDataPosition(i);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstallationFileParcel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */