package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ProgramList.Chunk> {
  public ProgramList.Chunk createFromParcel(Parcel paramParcel) {
    return new ProgramList.Chunk(paramParcel, null);
  }
  
  public ProgramList.Chunk[] newArray(int paramInt) {
    return new ProgramList.Chunk[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ProgramList$Chunk$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */