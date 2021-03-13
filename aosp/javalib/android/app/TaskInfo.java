package android.app;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.window.WindowContainerToken;

public class TaskInfo {
  private static final String TAG = "TaskInfo";
  
  public ComponentName baseActivity;
  
  public Intent baseIntent;
  
  public final Configuration configuration = new Configuration();
  
  public int displayId;
  
  public boolean isResizeable;
  
  public boolean isRunning;
  
  public long lastActiveTime;
  
  public int numActivities;
  
  public ComponentName origActivity;
  
  public PictureInPictureParams pictureInPictureParams;
  
  public ComponentName realActivity;
  
  public int requestedOrientation;
  
  public int resizeMode;
  
  public int stackId;
  
  public boolean supportsSplitScreenMultiWindow;
  
  public ActivityManager.TaskDescription taskDescription;
  
  public int taskId;
  
  public WindowContainerToken token;
  
  public ComponentName topActivity;
  
  public ActivityInfo topActivityInfo;
  
  public int topActivityType;
  
  public int userId;
  
  TaskInfo() {}
  
  private TaskInfo(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public Configuration getConfiguration() {
    return this.configuration;
  }
  
  public ActivityManager.TaskSnapshot getTaskSnapshot(boolean paramBoolean) {
    try {
      return ActivityManager.getService().getTaskSnapshot(this.taskId, paramBoolean);
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to get task snapshot, taskId=");
      stringBuilder.append(this.taskId);
      Log.e("TaskInfo", stringBuilder.toString(), (Throwable)remoteException);
      return null;
    } 
  }
  
  public WindowContainerToken getToken() {
    return this.token;
  }
  
  void readFromParcel(Parcel paramParcel) {
    Intent intent2;
    this.userId = paramParcel.readInt();
    this.stackId = paramParcel.readInt();
    this.taskId = paramParcel.readInt();
    this.displayId = paramParcel.readInt();
    this.isRunning = paramParcel.readBoolean();
    int i = paramParcel.readInt();
    Intent intent1 = null;
    if (i != 0) {
      intent2 = (Intent)Intent.CREATOR.createFromParcel(paramParcel);
    } else {
      intent2 = null;
    } 
    this.baseIntent = intent2;
    this.baseActivity = ComponentName.readFromParcel(paramParcel);
    this.topActivity = ComponentName.readFromParcel(paramParcel);
    this.origActivity = ComponentName.readFromParcel(paramParcel);
    this.realActivity = ComponentName.readFromParcel(paramParcel);
    this.numActivities = paramParcel.readInt();
    this.lastActiveTime = paramParcel.readLong();
    if (paramParcel.readInt() != 0) {
      ActivityManager.TaskDescription taskDescription = (ActivityManager.TaskDescription)ActivityManager.TaskDescription.CREATOR.createFromParcel(paramParcel);
    } else {
      intent2 = null;
    } 
    this.taskDescription = (ActivityManager.TaskDescription)intent2;
    this.supportsSplitScreenMultiWindow = paramParcel.readBoolean();
    this.resizeMode = paramParcel.readInt();
    this.configuration.readFromParcel(paramParcel);
    this.token = (WindowContainerToken)WindowContainerToken.CREATOR.createFromParcel(paramParcel);
    this.topActivityType = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      PictureInPictureParams pictureInPictureParams = (PictureInPictureParams)PictureInPictureParams.CREATOR.createFromParcel(paramParcel);
    } else {
      intent2 = null;
    } 
    this.pictureInPictureParams = (PictureInPictureParams)intent2;
    if (paramParcel.readInt() != 0) {
      ActivityInfo activityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(paramParcel);
    } else {
      intent2 = intent1;
    } 
    this.topActivityInfo = (ActivityInfo)intent2;
    this.isResizeable = paramParcel.readBoolean();
    this.requestedOrientation = paramParcel.readInt();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("TaskInfo{userId=");
    stringBuilder.append(this.userId);
    stringBuilder.append(" stackId=");
    stringBuilder.append(this.stackId);
    stringBuilder.append(" taskId=");
    stringBuilder.append(this.taskId);
    stringBuilder.append(" displayId=");
    stringBuilder.append(this.displayId);
    stringBuilder.append(" isRunning=");
    stringBuilder.append(this.isRunning);
    stringBuilder.append(" baseIntent=");
    stringBuilder.append(this.baseIntent);
    stringBuilder.append(" baseActivity=");
    stringBuilder.append(this.baseActivity);
    stringBuilder.append(" topActivity=");
    stringBuilder.append(this.topActivity);
    stringBuilder.append(" origActivity=");
    stringBuilder.append(this.origActivity);
    stringBuilder.append(" realActivity=");
    stringBuilder.append(this.realActivity);
    stringBuilder.append(" numActivities=");
    stringBuilder.append(this.numActivities);
    stringBuilder.append(" lastActiveTime=");
    stringBuilder.append(this.lastActiveTime);
    stringBuilder.append(" supportsSplitScreenMultiWindow=");
    stringBuilder.append(this.supportsSplitScreenMultiWindow);
    stringBuilder.append(" resizeMode=");
    stringBuilder.append(this.resizeMode);
    stringBuilder.append(" isResizeable=");
    stringBuilder.append(this.isResizeable);
    stringBuilder.append(" token=");
    stringBuilder.append(this.token);
    stringBuilder.append(" topActivityType=");
    stringBuilder.append(this.topActivityType);
    stringBuilder.append(" pictureInPictureParams=");
    stringBuilder.append(this.pictureInPictureParams);
    stringBuilder.append(" topActivityInfo=");
    stringBuilder.append(this.topActivityInfo);
    stringBuilder.append(" requestedOrientation=");
    stringBuilder.append(this.requestedOrientation);
    return stringBuilder.toString();
  }
  
  void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.userId);
    paramParcel.writeInt(this.stackId);
    paramParcel.writeInt(this.taskId);
    paramParcel.writeInt(this.displayId);
    paramParcel.writeBoolean(this.isRunning);
    if (this.baseIntent != null) {
      paramParcel.writeInt(1);
      this.baseIntent.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    ComponentName.writeToParcel(this.baseActivity, paramParcel);
    ComponentName.writeToParcel(this.topActivity, paramParcel);
    ComponentName.writeToParcel(this.origActivity, paramParcel);
    ComponentName.writeToParcel(this.realActivity, paramParcel);
    paramParcel.writeInt(this.numActivities);
    paramParcel.writeLong(this.lastActiveTime);
    if (this.taskDescription != null) {
      paramParcel.writeInt(1);
      this.taskDescription.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeBoolean(this.supportsSplitScreenMultiWindow);
    paramParcel.writeInt(this.resizeMode);
    this.configuration.writeToParcel(paramParcel, paramInt);
    this.token.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.topActivityType);
    if (this.pictureInPictureParams == null) {
      paramParcel.writeInt(0);
    } else {
      paramParcel.writeInt(1);
      this.pictureInPictureParams.writeToParcel(paramParcel, paramInt);
    } 
    if (this.topActivityInfo == null) {
      paramParcel.writeInt(0);
    } else {
      paramParcel.writeInt(1);
      this.topActivityInfo.writeToParcel(paramParcel, paramInt);
    } 
    paramParcel.writeBoolean(this.isResizeable);
    paramParcel.writeInt(this.requestedOrientation);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/TaskInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */