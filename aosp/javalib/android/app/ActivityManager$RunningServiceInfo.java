package android.app;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;

public class RunningServiceInfo implements Parcelable {
  public static final Parcelable.Creator<RunningServiceInfo> CREATOR = new Parcelable.Creator<RunningServiceInfo>() {
      public ActivityManager.RunningServiceInfo createFromParcel(Parcel param2Parcel) {
        return new ActivityManager.RunningServiceInfo(param2Parcel);
      }
      
      public ActivityManager.RunningServiceInfo[] newArray(int param2Int) {
        return new ActivityManager.RunningServiceInfo[param2Int];
      }
    };
  
  public static final int FLAG_FOREGROUND = 2;
  
  public static final int FLAG_PERSISTENT_PROCESS = 8;
  
  public static final int FLAG_STARTED = 1;
  
  public static final int FLAG_SYSTEM_PROCESS = 4;
  
  public long activeSince;
  
  public int clientCount;
  
  public int clientLabel;
  
  public String clientPackage;
  
  public int crashCount;
  
  public int flags;
  
  public boolean foreground;
  
  public long lastActivityTime;
  
  public int pid;
  
  public String process;
  
  public long restarting;
  
  public ComponentName service;
  
  public boolean started;
  
  public int uid;
  
  public RunningServiceInfo() {}
  
  private RunningServiceInfo(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    boolean bool2;
    this.service = ComponentName.readFromParcel(paramParcel);
    this.pid = paramParcel.readInt();
    this.uid = paramParcel.readInt();
    this.process = paramParcel.readString();
    int i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.foreground = bool2;
    this.activeSince = paramParcel.readLong();
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.started = bool2;
    this.clientCount = paramParcel.readInt();
    this.crashCount = paramParcel.readInt();
    this.lastActivityTime = paramParcel.readLong();
    this.restarting = paramParcel.readLong();
    this.flags = paramParcel.readInt();
    this.clientPackage = paramParcel.readString();
    this.clientLabel = paramParcel.readInt();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    ComponentName.writeToParcel(this.service, paramParcel);
    paramParcel.writeInt(this.pid);
    paramParcel.writeInt(this.uid);
    paramParcel.writeString(this.process);
    paramParcel.writeInt(this.foreground);
    paramParcel.writeLong(this.activeSince);
    paramParcel.writeInt(this.started);
    paramParcel.writeInt(this.clientCount);
    paramParcel.writeInt(this.crashCount);
    paramParcel.writeLong(this.lastActivityTime);
    paramParcel.writeLong(this.restarting);
    paramParcel.writeInt(this.flags);
    paramParcel.writeString(this.clientPackage);
    paramParcel.writeInt(this.clientLabel);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$RunningServiceInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */