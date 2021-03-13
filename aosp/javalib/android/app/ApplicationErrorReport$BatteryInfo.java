package android.app;

import android.os.Parcel;
import android.util.Printer;

public class BatteryInfo {
  public String checkinDetails;
  
  public long durationMicros;
  
  public String usageDetails;
  
  public int usagePercent;
  
  public BatteryInfo() {}
  
  public BatteryInfo(Parcel paramParcel) {
    this.usagePercent = paramParcel.readInt();
    this.durationMicros = paramParcel.readLong();
    this.usageDetails = paramParcel.readString();
    this.checkinDetails = paramParcel.readString();
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("usagePercent: ");
    stringBuilder.append(this.usagePercent);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("durationMicros: ");
    stringBuilder.append(this.durationMicros);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("usageDetails: ");
    stringBuilder.append(this.usageDetails);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("checkinDetails: ");
    stringBuilder.append(this.checkinDetails);
    paramPrinter.println(stringBuilder.toString());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.usagePercent);
    paramParcel.writeLong(this.durationMicros);
    paramParcel.writeString(this.usageDetails);
    paramParcel.writeString(this.checkinDetails);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationErrorReport$BatteryInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */