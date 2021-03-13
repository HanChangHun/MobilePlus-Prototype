package android.app;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class RunningTaskInfo extends TaskInfo implements Parcelable {
  public static final Parcelable.Creator<RunningTaskInfo> CREATOR = new Parcelable.Creator<RunningTaskInfo>() {
      public ActivityManager.RunningTaskInfo createFromParcel(Parcel param2Parcel) {
        return new ActivityManager.RunningTaskInfo(param2Parcel);
      }
      
      public ActivityManager.RunningTaskInfo[] newArray(int param2Int) {
        return new ActivityManager.RunningTaskInfo[param2Int];
      }
    };
  
  @Deprecated
  public CharSequence description;
  
  @Deprecated
  public int id;
  
  @Deprecated
  public int numRunning;
  
  @Deprecated
  public Bitmap thumbnail;
  
  public RunningTaskInfo() {}
  
  private RunningTaskInfo(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.id = paramParcel.readInt();
    super.readFromParcel(paramParcel);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.id);
    super.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$RunningTaskInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */