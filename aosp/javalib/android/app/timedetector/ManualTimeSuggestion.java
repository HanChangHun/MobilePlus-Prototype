package android.app.timedetector;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.TimestampedValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ManualTimeSuggestion implements Parcelable {
  public static final Parcelable.Creator<ManualTimeSuggestion> CREATOR = new Parcelable.Creator<ManualTimeSuggestion>() {
      public ManualTimeSuggestion createFromParcel(Parcel param1Parcel) {
        return ManualTimeSuggestion.createFromParcel(param1Parcel);
      }
      
      public ManualTimeSuggestion[] newArray(int param1Int) {
        return new ManualTimeSuggestion[param1Int];
      }
    };
  
  private ArrayList<String> mDebugInfo;
  
  private final TimestampedValue<Long> mUtcTime;
  
  public ManualTimeSuggestion(TimestampedValue<Long> paramTimestampedValue) {
    Objects.requireNonNull(paramTimestampedValue);
    this.mUtcTime = paramTimestampedValue;
    Objects.requireNonNull((Long)paramTimestampedValue.getValue());
  }
  
  private static ManualTimeSuggestion createFromParcel(Parcel paramParcel) {
    ManualTimeSuggestion manualTimeSuggestion = new ManualTimeSuggestion((TimestampedValue<Long>)paramParcel.readParcelable(null));
    manualTimeSuggestion.mDebugInfo = paramParcel.readArrayList(null);
    return manualTimeSuggestion;
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
    return Objects.equals(this.mUtcTime, ((ManualTimeSuggestion)paramObject).mUtcTime);
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
  
  public TimestampedValue<Long> getUtcTime() {
    return this.mUtcTime;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mUtcTime });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ManualTimeSuggestion{mUtcTime=");
    stringBuilder.append(this.mUtcTime);
    stringBuilder.append(", mDebugInfo=");
    stringBuilder.append(this.mDebugInfo);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeParcelable((Parcelable)this.mUtcTime, 0);
    paramParcel.writeList(this.mDebugInfo);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timedetector/ManualTimeSuggestion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */