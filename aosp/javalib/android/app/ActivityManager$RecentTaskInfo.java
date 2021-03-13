package android.app;

import android.content.pm.ActivityInfo;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.PrintWriter;

public class RecentTaskInfo extends TaskInfo implements Parcelable {
  public static final Parcelable.Creator<RecentTaskInfo> CREATOR = new Parcelable.Creator<RecentTaskInfo>() {
      public ActivityManager.RecentTaskInfo createFromParcel(Parcel param2Parcel) {
        return new ActivityManager.RecentTaskInfo(param2Parcel);
      }
      
      public ActivityManager.RecentTaskInfo[] newArray(int param2Int) {
        return new ActivityManager.RecentTaskInfo[param2Int];
      }
    };
  
  @Deprecated
  public int affiliatedTaskId;
  
  @Deprecated
  public CharSequence description;
  
  @Deprecated
  public int id;
  
  @Deprecated
  public int persistentId;
  
  public RecentTaskInfo() {}
  
  private RecentTaskInfo(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(PrintWriter paramPrintWriter, String paramString) {
    boolean bool2;
    String str = WindowConfiguration.activityTypeToString(this.configuration.windowConfiguration.getActivityType());
    paramString = WindowConfiguration.activityTypeToString(this.configuration.windowConfiguration.getActivityType());
    paramPrintWriter.println();
    paramPrintWriter.print("   ");
    paramPrintWriter.print(" id=");
    paramPrintWriter.print(this.persistentId);
    paramPrintWriter.print(" stackId=");
    paramPrintWriter.print(this.stackId);
    paramPrintWriter.print(" userId=");
    paramPrintWriter.print(this.userId);
    paramPrintWriter.print(" hasTask=");
    int i = this.id;
    boolean bool1 = true;
    if (i != -1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    paramPrintWriter.print(bool2);
    paramPrintWriter.print(" lastActiveTime=");
    paramPrintWriter.println(this.lastActiveTime);
    paramPrintWriter.print("   ");
    paramPrintWriter.print(" baseIntent=");
    paramPrintWriter.println(this.baseIntent);
    if (this.baseActivity != null) {
      paramPrintWriter.print("   ");
      paramPrintWriter.print(" baseActivity=");
      paramPrintWriter.println(this.baseActivity.toShortString());
    } 
    if (this.topActivity != null) {
      paramPrintWriter.print("   ");
      paramPrintWriter.print(" topActivity=");
      paramPrintWriter.println(this.topActivity.toShortString());
    } 
    if (this.origActivity != null) {
      paramPrintWriter.print("   ");
      paramPrintWriter.print(" origActivity=");
      paramPrintWriter.println(this.origActivity.toShortString());
    } 
    if (this.realActivity != null) {
      paramPrintWriter.print("   ");
      paramPrintWriter.print(" realActivity=");
      paramPrintWriter.println(this.realActivity.toShortString());
    } 
    paramPrintWriter.print("   ");
    paramPrintWriter.print(" isExcluded=");
    if ((this.baseIntent.getFlags() & 0x800000) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    paramPrintWriter.print(bool2);
    paramPrintWriter.print(" activityType=");
    paramPrintWriter.print(str);
    paramPrintWriter.print(" windowingMode=");
    paramPrintWriter.print(paramString);
    paramPrintWriter.print(" supportsSplitScreenMultiWindow=");
    paramPrintWriter.println(this.supportsSplitScreenMultiWindow);
    if (this.taskDescription != null) {
      paramPrintWriter.print("   ");
      ActivityManager.TaskDescription taskDescription = this.taskDescription;
      paramPrintWriter.print(" taskDescription {");
      paramPrintWriter.print(" colorBackground=#");
      paramPrintWriter.print(Integer.toHexString(taskDescription.getBackgroundColor()));
      paramPrintWriter.print(" colorPrimary=#");
      paramPrintWriter.print(Integer.toHexString(taskDescription.getPrimaryColor()));
      paramPrintWriter.print(" iconRes=");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(taskDescription.getIconResourcePackage());
      stringBuilder.append("/");
      stringBuilder.append(taskDescription.getIconResource());
      paramPrintWriter.print(stringBuilder.toString());
      paramPrintWriter.print(" iconBitmap=");
      bool2 = bool1;
      if (taskDescription.getIconFilename() == null)
        if (taskDescription.getInMemoryIcon() != null) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
      paramPrintWriter.print(bool2);
      paramPrintWriter.print(" resizeMode=");
      paramPrintWriter.print(ActivityInfo.resizeModeToString(taskDescription.getResizeMode()));
      paramPrintWriter.print(" minWidth=");
      paramPrintWriter.print(taskDescription.getMinWidth());
      paramPrintWriter.print(" minHeight=");
      paramPrintWriter.print(taskDescription.getMinHeight());
      paramPrintWriter.println(" }");
    } 
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.id = paramParcel.readInt();
    this.persistentId = paramParcel.readInt();
    super.readFromParcel(paramParcel);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.id);
    paramParcel.writeInt(this.persistentId);
    super.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$RecentTaskInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */