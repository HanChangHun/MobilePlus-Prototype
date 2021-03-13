package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RunningAppProcessInfo implements Parcelable {
  public static final Parcelable.Creator<RunningAppProcessInfo> CREATOR = new Parcelable.Creator<RunningAppProcessInfo>() {
      public ActivityManager.RunningAppProcessInfo createFromParcel(Parcel param2Parcel) {
        return new ActivityManager.RunningAppProcessInfo(param2Parcel);
      }
      
      public ActivityManager.RunningAppProcessInfo[] newArray(int param2Int) {
        return new ActivityManager.RunningAppProcessInfo[param2Int];
      }
    };
  
  public static final int FLAG_CANT_SAVE_STATE = 1;
  
  public static final int FLAG_HAS_ACTIVITIES = 4;
  
  public static final int FLAG_PERSISTENT = 2;
  
  public static final int IMPORTANCE_BACKGROUND = 400;
  
  public static final int IMPORTANCE_CACHED = 400;
  
  public static final int IMPORTANCE_CANT_SAVE_STATE = 350;
  
  public static final int IMPORTANCE_CANT_SAVE_STATE_PRE_26 = 170;
  
  @Deprecated
  public static final int IMPORTANCE_EMPTY = 500;
  
  public static final int IMPORTANCE_FOREGROUND = 100;
  
  public static final int IMPORTANCE_FOREGROUND_SERVICE = 125;
  
  public static final int IMPORTANCE_GONE = 1000;
  
  public static final int IMPORTANCE_PERCEPTIBLE = 230;
  
  public static final int IMPORTANCE_PERCEPTIBLE_PRE_26 = 130;
  
  public static final int IMPORTANCE_SERVICE = 300;
  
  public static final int IMPORTANCE_TOP_SLEEPING = 325;
  
  @Deprecated
  public static final int IMPORTANCE_TOP_SLEEPING_PRE_28 = 150;
  
  public static final int IMPORTANCE_VISIBLE = 200;
  
  public static final int REASON_PROVIDER_IN_USE = 1;
  
  public static final int REASON_SERVICE_IN_USE = 2;
  
  public static final int REASON_UNKNOWN = 0;
  
  public int flags;
  
  public int importance;
  
  public int importanceReasonCode;
  
  public ComponentName importanceReasonComponent;
  
  public int importanceReasonImportance;
  
  public int importanceReasonPid;
  
  public boolean isFocused;
  
  public long lastActivityTime;
  
  public int lastTrimLevel;
  
  public int lru;
  
  public int pid;
  
  public String[] pkgList;
  
  public String processName;
  
  public int processState;
  
  public int uid;
  
  public RunningAppProcessInfo() {
    this.importance = 100;
    this.importanceReasonCode = 0;
    this.processState = 6;
    this.isFocused = false;
    this.lastActivityTime = 0L;
  }
  
  private RunningAppProcessInfo(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public RunningAppProcessInfo(String paramString, int paramInt, String[] paramArrayOfString) {
    this.processName = paramString;
    this.pid = paramInt;
    this.pkgList = paramArrayOfString;
    this.isFocused = false;
    this.lastActivityTime = 0L;
  }
  
  public static int importanceToProcState(int paramInt) {
    return (paramInt == 1000) ? 20 : ((paramInt >= 400) ? 14 : ((paramInt >= 350) ? 13 : ((paramInt >= 325) ? 12 : ((paramInt >= 300) ? 10 : ((paramInt >= 230) ? 8 : ((paramInt >= 200) ? 6 : ((paramInt >= 150) ? 6 : ((paramInt >= 125) ? 4 : 2))))))));
  }
  
  public static int procStateToImportance(int paramInt) {
    return (paramInt == 20) ? 1000 : ((paramInt >= 14) ? 400 : ((paramInt == 13) ? 350 : ((paramInt >= 12) ? 325 : ((paramInt >= 10) ? 300 : ((paramInt >= 8) ? 230 : ((paramInt >= 6) ? 200 : ((paramInt >= 4) ? 125 : 100)))))));
  }
  
  public static int procStateToImportanceForClient(int paramInt, Context paramContext) {
    return procStateToImportanceForTargetSdk(paramInt, (paramContext.getApplicationInfo()).targetSdkVersion);
  }
  
  public static int procStateToImportanceForTargetSdk(int paramInt1, int paramInt2) {
    paramInt1 = procStateToImportance(paramInt1);
    if (paramInt2 < 26)
      if (paramInt1 != 230) {
        if (paramInt1 != 325) {
          if (paramInt1 == 350)
            return 170; 
        } else {
          return 150;
        } 
      } else {
        return 130;
      }  
    return paramInt1;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    boolean bool;
    this.processName = paramParcel.readString();
    this.pid = paramParcel.readInt();
    this.uid = paramParcel.readInt();
    this.pkgList = paramParcel.readStringArray();
    this.flags = paramParcel.readInt();
    this.lastTrimLevel = paramParcel.readInt();
    this.importance = paramParcel.readInt();
    this.lru = paramParcel.readInt();
    this.importanceReasonCode = paramParcel.readInt();
    this.importanceReasonPid = paramParcel.readInt();
    this.importanceReasonComponent = ComponentName.readFromParcel(paramParcel);
    this.importanceReasonImportance = paramParcel.readInt();
    this.processState = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.isFocused = bool;
    this.lastActivityTime = paramParcel.readLong();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.processName);
    paramParcel.writeInt(this.pid);
    paramParcel.writeInt(this.uid);
    paramParcel.writeStringArray(this.pkgList);
    paramParcel.writeInt(this.flags);
    paramParcel.writeInt(this.lastTrimLevel);
    paramParcel.writeInt(this.importance);
    paramParcel.writeInt(this.lru);
    paramParcel.writeInt(this.importanceReasonCode);
    paramParcel.writeInt(this.importanceReasonPid);
    ComponentName.writeToParcel(this.importanceReasonComponent, paramParcel);
    paramParcel.writeInt(this.importanceReasonImportance);
    paramParcel.writeInt(this.processState);
    paramParcel.writeInt(this.isFocused);
    paramParcel.writeLong(this.lastActivityTime);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Importance {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$RunningAppProcessInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */