package android.app;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class WaitResult implements Parcelable {
  public static final Parcelable.Creator<WaitResult> CREATOR = new Parcelable.Creator<WaitResult>() {
      public WaitResult createFromParcel(Parcel param1Parcel) {
        return new WaitResult(param1Parcel);
      }
      
      public WaitResult[] newArray(int param1Int) {
        return new WaitResult[param1Int];
      }
    };
  
  public static final int INVALID_DELAY = -1;
  
  public static final int LAUNCH_STATE_COLD = 1;
  
  public static final int LAUNCH_STATE_HOT = 3;
  
  public static final int LAUNCH_STATE_WARM = 2;
  
  public int launchState;
  
  public int result;
  
  public boolean timeout;
  
  public long totalTime;
  
  public ComponentName who;
  
  public WaitResult() {}
  
  private WaitResult(Parcel paramParcel) {
    boolean bool;
    this.result = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.timeout = bool;
    this.who = ComponentName.readFromParcel(paramParcel);
    this.totalTime = paramParcel.readLong();
    this.launchState = paramParcel.readInt();
  }
  
  public static String launchStateToString(int paramInt) {
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 3) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("UNKNOWN (");
          stringBuilder.append(paramInt);
          stringBuilder.append(")");
          return stringBuilder.toString();
        } 
        return "HOT";
      } 
      return "WARM";
    } 
    return "COLD";
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(PrintWriter paramPrintWriter, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("WaitResult:");
    paramPrintWriter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("  result=");
    stringBuilder.append(this.result);
    paramPrintWriter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("  timeout=");
    stringBuilder.append(this.timeout);
    paramPrintWriter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("  who=");
    stringBuilder.append(this.who);
    paramPrintWriter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("  totalTime=");
    stringBuilder.append(this.totalTime);
    paramPrintWriter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("  launchState=");
    stringBuilder.append(this.launchState);
    paramPrintWriter.println(stringBuilder.toString());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.result);
    paramParcel.writeInt(this.timeout);
    ComponentName.writeToParcel(this.who, paramParcel);
    paramParcel.writeLong(this.totalTime);
    paramParcel.writeInt(this.launchState);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface LaunchState {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WaitResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */