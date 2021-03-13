package android.app;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class ServiceStartArgs implements Parcelable {
  public static final Parcelable.Creator<ServiceStartArgs> CREATOR = new Parcelable.Creator<ServiceStartArgs>() {
      public ServiceStartArgs createFromParcel(Parcel param1Parcel) {
        return new ServiceStartArgs(param1Parcel);
      }
      
      public ServiceStartArgs[] newArray(int param1Int) {
        return new ServiceStartArgs[param1Int];
      }
    };
  
  public final Intent args;
  
  public final int flags;
  
  public final int startId;
  
  public final boolean taskRemoved;
  
  public ServiceStartArgs(Parcel paramParcel) {
    boolean bool;
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.taskRemoved = bool;
    this.startId = paramParcel.readInt();
    this.flags = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      this.args = (Intent)Intent.CREATOR.createFromParcel(paramParcel);
    } else {
      this.args = null;
    } 
  }
  
  public ServiceStartArgs(boolean paramBoolean, int paramInt1, int paramInt2, Intent paramIntent) {
    this.taskRemoved = paramBoolean;
    this.startId = paramInt1;
    this.flags = paramInt2;
    this.args = paramIntent;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ServiceStartArgs{taskRemoved=");
    stringBuilder.append(this.taskRemoved);
    stringBuilder.append(", startId=");
    stringBuilder.append(this.startId);
    stringBuilder.append(", flags=0x");
    stringBuilder.append(Integer.toHexString(this.flags));
    stringBuilder.append(", args=");
    stringBuilder.append(this.args);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.taskRemoved);
    paramParcel.writeInt(this.startId);
    paramParcel.writeInt(paramInt);
    if (this.args != null) {
      paramParcel.writeInt(1);
      this.args.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ServiceStartArgs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */