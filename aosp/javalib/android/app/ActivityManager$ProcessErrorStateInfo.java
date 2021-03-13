package android.app;

import android.os.Parcel;
import android.os.Parcelable;

public class ProcessErrorStateInfo implements Parcelable {
  public static final int CRASHED = 1;
  
  public static final Parcelable.Creator<ProcessErrorStateInfo> CREATOR = new Parcelable.Creator<ProcessErrorStateInfo>() {
      public ActivityManager.ProcessErrorStateInfo createFromParcel(Parcel param2Parcel) {
        return new ActivityManager.ProcessErrorStateInfo(param2Parcel);
      }
      
      public ActivityManager.ProcessErrorStateInfo[] newArray(int param2Int) {
        return new ActivityManager.ProcessErrorStateInfo[param2Int];
      }
    };
  
  public static final int NOT_RESPONDING = 2;
  
  public static final int NO_ERROR = 0;
  
  public int condition;
  
  public byte[] crashData = null;
  
  public String longMsg;
  
  public int pid;
  
  public String processName;
  
  public String shortMsg;
  
  public String stackTrace;
  
  public String tag;
  
  public int uid;
  
  public ProcessErrorStateInfo() {}
  
  private ProcessErrorStateInfo(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.condition = paramParcel.readInt();
    this.processName = paramParcel.readString();
    this.pid = paramParcel.readInt();
    this.uid = paramParcel.readInt();
    this.tag = paramParcel.readString();
    this.shortMsg = paramParcel.readString();
    this.longMsg = paramParcel.readString();
    this.stackTrace = paramParcel.readString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.condition);
    paramParcel.writeString(this.processName);
    paramParcel.writeInt(this.pid);
    paramParcel.writeInt(this.uid);
    paramParcel.writeString(this.tag);
    paramParcel.writeString(this.shortMsg);
    paramParcel.writeString(this.longMsg);
    paramParcel.writeString(this.stackTrace);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$ProcessErrorStateInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */