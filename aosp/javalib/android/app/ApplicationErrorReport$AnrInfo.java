package android.app;

import android.os.Parcel;
import android.util.Printer;

public class AnrInfo {
  public String activity;
  
  public String cause;
  
  public String info;
  
  public AnrInfo() {}
  
  public AnrInfo(Parcel paramParcel) {
    this.activity = paramParcel.readString();
    this.cause = paramParcel.readString();
    this.info = paramParcel.readString();
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("activity: ");
    stringBuilder.append(this.activity);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("cause: ");
    stringBuilder.append(this.cause);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("info: ");
    stringBuilder.append(this.info);
    paramPrinter.println(stringBuilder.toString());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.activity);
    paramParcel.writeString(this.cause);
    paramParcel.writeString(this.info);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationErrorReport$AnrInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */