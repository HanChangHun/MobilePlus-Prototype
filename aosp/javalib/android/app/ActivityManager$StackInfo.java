package android.app;

import android.content.ComponentName;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.window.WindowContainerToken;

public class StackInfo implements Parcelable {
  public static final Parcelable.Creator<StackInfo> CREATOR = new Parcelable.Creator<StackInfo>() {
      public ActivityManager.StackInfo createFromParcel(Parcel param2Parcel) {
        return new ActivityManager.StackInfo(param2Parcel);
      }
      
      public ActivityManager.StackInfo[] newArray(int param2Int) {
        return new ActivityManager.StackInfo[param2Int];
      }
    };
  
  public Rect bounds = new Rect();
  
  public final Configuration configuration = new Configuration();
  
  public int displayId;
  
  @Deprecated
  public int position;
  
  public int stackId;
  
  public WindowContainerToken stackToken;
  
  public Rect[] taskBounds;
  
  public int[] taskIds;
  
  public String[] taskNames;
  
  public int[] taskUserIds;
  
  public ComponentName topActivity;
  
  public int userId;
  
  public boolean visible;
  
  public StackInfo() {}
  
  private StackInfo(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    boolean bool;
    this.stackId = paramParcel.readInt();
    this.bounds = new Rect(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt());
    this.taskIds = paramParcel.createIntArray();
    this.taskNames = paramParcel.createStringArray();
    int i = paramParcel.readInt();
    if (i > 0) {
      this.taskBounds = new Rect[i];
      for (byte b = 0; b < i; b++) {
        this.taskBounds[b] = new Rect();
        this.taskBounds[b].set(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt());
      } 
    } else {
      this.taskBounds = null;
    } 
    this.taskUserIds = paramParcel.createIntArray();
    this.displayId = paramParcel.readInt();
    this.userId = paramParcel.readInt();
    if (paramParcel.readInt() > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.visible = bool;
    this.position = paramParcel.readInt();
    this.stackToken = (WindowContainerToken)WindowContainerToken.CREATOR.createFromParcel(paramParcel);
    if (paramParcel.readInt() > 0)
      this.topActivity = ComponentName.readFromParcel(paramParcel); 
    this.configuration.readFromParcel(paramParcel);
  }
  
  public String toString() {
    return toString("");
  }
  
  public String toString(String paramString) {
    StringBuilder stringBuilder1 = new StringBuilder(256);
    stringBuilder1.append(paramString);
    stringBuilder1.append("Stack id=");
    stringBuilder1.append(this.stackId);
    stringBuilder1.append(" bounds=");
    stringBuilder1.append(this.bounds.toShortString());
    stringBuilder1.append(" displayId=");
    stringBuilder1.append(this.displayId);
    stringBuilder1.append(" userId=");
    stringBuilder1.append(this.userId);
    stringBuilder1.append("\n");
    stringBuilder1.append(" configuration=");
    stringBuilder1.append(this.configuration);
    stringBuilder1.append("\n");
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString);
    stringBuilder2.append("  ");
    paramString = stringBuilder2.toString();
    for (byte b = 0; b < this.taskIds.length; b++) {
      stringBuilder1.append(paramString);
      stringBuilder1.append("taskId=");
      stringBuilder1.append(this.taskIds[b]);
      stringBuilder1.append(": ");
      stringBuilder1.append(this.taskNames[b]);
      if (this.taskBounds != null) {
        stringBuilder1.append(" bounds=");
        stringBuilder1.append(this.taskBounds[b].toShortString());
      } 
      stringBuilder1.append(" userId=");
      stringBuilder1.append(this.taskUserIds[b]);
      stringBuilder1.append(" visible=");
      stringBuilder1.append(this.visible);
      if (this.topActivity != null) {
        stringBuilder1.append(" topActivity=");
        stringBuilder1.append(this.topActivity);
      } 
      stringBuilder1.append("\n");
    } 
    return stringBuilder1.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i;
    paramParcel.writeInt(this.stackId);
    paramParcel.writeInt(this.bounds.left);
    paramParcel.writeInt(this.bounds.top);
    paramParcel.writeInt(this.bounds.right);
    paramParcel.writeInt(this.bounds.bottom);
    paramParcel.writeIntArray(this.taskIds);
    paramParcel.writeStringArray(this.taskNames);
    Rect[] arrayOfRect = this.taskBounds;
    if (arrayOfRect == null) {
      i = 0;
    } else {
      i = arrayOfRect.length;
    } 
    paramParcel.writeInt(i);
    for (byte b = 0; b < i; b++) {
      paramParcel.writeInt((this.taskBounds[b]).left);
      paramParcel.writeInt((this.taskBounds[b]).top);
      paramParcel.writeInt((this.taskBounds[b]).right);
      paramParcel.writeInt((this.taskBounds[b]).bottom);
    } 
    paramParcel.writeIntArray(this.taskUserIds);
    paramParcel.writeInt(this.displayId);
    paramParcel.writeInt(this.userId);
    paramParcel.writeInt(this.visible);
    paramParcel.writeInt(this.position);
    this.stackToken.writeToParcel(paramParcel, 0);
    if (this.topActivity != null) {
      paramParcel.writeInt(1);
      this.topActivity.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    this.configuration.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$StackInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */