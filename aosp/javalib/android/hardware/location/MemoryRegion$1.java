package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<MemoryRegion> {
  public MemoryRegion createFromParcel(Parcel paramParcel) {
    return new MemoryRegion(paramParcel);
  }
  
  public MemoryRegion[] newArray(int paramInt) {
    return new MemoryRegion[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/MemoryRegion$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */