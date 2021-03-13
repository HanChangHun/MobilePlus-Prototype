package android.app;

import android.os.Parcel;
import android.util.Printer;

public class RunningServiceInfo {
  public long durationMillis;
  
  public String serviceDetails;
  
  public RunningServiceInfo() {}
  
  public RunningServiceInfo(Parcel paramParcel) {
    this.durationMillis = paramParcel.readLong();
    this.serviceDetails = paramParcel.readString();
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("durationMillis: ");
    stringBuilder.append(this.durationMillis);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("serviceDetails: ");
    stringBuilder.append(this.serviceDetails);
    paramPrinter.println(stringBuilder.toString());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.durationMillis);
    paramParcel.writeString(this.serviceDetails);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationErrorReport$RunningServiceInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */