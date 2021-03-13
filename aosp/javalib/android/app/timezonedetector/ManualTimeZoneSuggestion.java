package android.app.timezonedetector;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ShellCommand;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ManualTimeZoneSuggestion implements Parcelable {
  public static final Parcelable.Creator<ManualTimeZoneSuggestion> CREATOR = new Parcelable.Creator<ManualTimeZoneSuggestion>() {
      public ManualTimeZoneSuggestion createFromParcel(Parcel param1Parcel) {
        return ManualTimeZoneSuggestion.createFromParcel(param1Parcel);
      }
      
      public ManualTimeZoneSuggestion[] newArray(int param1Int) {
        return new ManualTimeZoneSuggestion[param1Int];
      }
    };
  
  private ArrayList<String> mDebugInfo;
  
  private final String mZoneId;
  
  public ManualTimeZoneSuggestion(String paramString) {
    Objects.requireNonNull(paramString);
    this.mZoneId = paramString;
  }
  
  private static ManualTimeZoneSuggestion createFromParcel(Parcel paramParcel) {
    ManualTimeZoneSuggestion manualTimeZoneSuggestion = new ManualTimeZoneSuggestion(paramParcel.readString());
    manualTimeZoneSuggestion.mDebugInfo = paramParcel.readArrayList(null);
    return manualTimeZoneSuggestion;
  }
  
  public static ManualTimeZoneSuggestion parseCommandLineArg(ShellCommand paramShellCommand) {
    String str = null;
    while (true) {
      String str1 = paramShellCommand.getNextArg();
      if (str1 != null) {
        byte b = -1;
        if (str1.hashCode() == 1274807534 && str1.equals("--zone_id"))
          b = 0; 
        if (b == 0) {
          str = paramShellCommand.getNextArgRequired();
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown option: ");
        stringBuilder.append(str1);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      ManualTimeZoneSuggestion manualTimeZoneSuggestion = new ManualTimeZoneSuggestion(str);
      manualTimeZoneSuggestion.addDebugInfo(new String[] { "Command line injection" });
      return manualTimeZoneSuggestion;
    } 
  }
  
  public static void printCommandLineOpts(PrintWriter paramPrintWriter) {
    paramPrintWriter.println("Manual suggestion options:");
    paramPrintWriter.println("  --zone_id <Olson ID>");
    paramPrintWriter.println();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("See ");
    stringBuilder.append(ManualTimeZoneSuggestion.class.getName());
    stringBuilder.append(" for more information");
    paramPrintWriter.println(stringBuilder.toString());
  }
  
  public void addDebugInfo(String... paramVarArgs) {
    if (this.mDebugInfo == null)
      this.mDebugInfo = new ArrayList<>(); 
    this.mDebugInfo.addAll(Arrays.asList(paramVarArgs));
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    return Objects.equals(this.mZoneId, ((ManualTimeZoneSuggestion)paramObject).mZoneId);
  }
  
  public List<String> getDebugInfo() {
    List<?> list = this.mDebugInfo;
    if (list == null) {
      list = Collections.emptyList();
    } else {
      list = Collections.unmodifiableList(list);
    } 
    return (List)list;
  }
  
  public String getZoneId() {
    return this.mZoneId;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mZoneId });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ManualTimeZoneSuggestion{mZoneId=");
    stringBuilder.append(this.mZoneId);
    stringBuilder.append(", mDebugInfo=");
    stringBuilder.append(this.mDebugInfo);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mZoneId);
    paramParcel.writeList(this.mDebugInfo);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezonedetector/ManualTimeZoneSuggestion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */