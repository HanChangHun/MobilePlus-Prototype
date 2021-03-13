package android.app;

import android.os.Parcel;
import android.os.Parcelable;

public final class ProcessMemoryState implements Parcelable {
  public static final Parcelable.Creator<ProcessMemoryState> CREATOR = new Parcelable.Creator<ProcessMemoryState>() {
      public ProcessMemoryState createFromParcel(Parcel param1Parcel) {
        return new ProcessMemoryState(param1Parcel);
      }
      
      public ProcessMemoryState[] newArray(int param1Int) {
        return new ProcessMemoryState[param1Int];
      }
    };
  
  public final int oomScore;
  
  public final int pid;
  
  public final String processName;
  
  public final int uid;
  
  public ProcessMemoryState(int paramInt1, int paramInt2, String paramString, int paramInt3) {
    this.uid = paramInt1;
    this.pid = paramInt2;
    this.processName = paramString;
    this.oomScore = paramInt3;
  }
  
  private ProcessMemoryState(Parcel paramParcel) {
    this.uid = paramParcel.readInt();
    this.pid = paramParcel.readInt();
    this.processName = paramParcel.readString();
    this.oomScore = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.uid);
    paramParcel.writeInt(this.pid);
    paramParcel.writeString(this.processName);
    paramParcel.writeInt(this.oomScore);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ProcessMemoryState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */