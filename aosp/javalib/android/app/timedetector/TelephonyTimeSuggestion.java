package android.app.timedetector;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.TimestampedValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class TelephonyTimeSuggestion implements Parcelable {
  public static final Parcelable.Creator<TelephonyTimeSuggestion> CREATOR = new Parcelable.Creator<TelephonyTimeSuggestion>() {
      public TelephonyTimeSuggestion createFromParcel(Parcel param1Parcel) {
        return TelephonyTimeSuggestion.createFromParcel(param1Parcel);
      }
      
      public TelephonyTimeSuggestion[] newArray(int param1Int) {
        return new TelephonyTimeSuggestion[param1Int];
      }
    };
  
  private ArrayList<String> mDebugInfo;
  
  private final int mSlotIndex;
  
  private final TimestampedValue<Long> mUtcTime;
  
  private TelephonyTimeSuggestion(Builder paramBuilder) {
    this.mSlotIndex = paramBuilder.mSlotIndex;
    this.mUtcTime = paramBuilder.mUtcTime;
    if (paramBuilder.mDebugInfo != null) {
      ArrayList arrayList = new ArrayList(paramBuilder.mDebugInfo);
    } else {
      paramBuilder = null;
    } 
    this.mDebugInfo = (ArrayList<String>)paramBuilder;
  }
  
  private static TelephonyTimeSuggestion createFromParcel(Parcel paramParcel) {
    TelephonyTimeSuggestion telephonyTimeSuggestion = (new Builder(paramParcel.readInt())).setUtcTime((TimestampedValue<Long>)paramParcel.readParcelable(null)).build();
    ArrayList<String> arrayList = paramParcel.readArrayList(null);
    if (arrayList != null)
      telephonyTimeSuggestion.addDebugInfo(arrayList); 
    return telephonyTimeSuggestion;
  }
  
  public void addDebugInfo(String paramString) {
    if (this.mDebugInfo == null)
      this.mDebugInfo = new ArrayList<>(); 
    this.mDebugInfo.add(paramString);
  }
  
  public void addDebugInfo(List<String> paramList) {
    if (this.mDebugInfo == null)
      this.mDebugInfo = new ArrayList<>(paramList.size()); 
    this.mDebugInfo.addAll(paramList);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mSlotIndex != ((TelephonyTimeSuggestion)paramObject).mSlotIndex || !Objects.equals(this.mUtcTime, ((TelephonyTimeSuggestion)paramObject).mUtcTime))
      bool = false; 
    return bool;
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
  
  public int getSlotIndex() {
    return this.mSlotIndex;
  }
  
  public TimestampedValue<Long> getUtcTime() {
    return this.mUtcTime;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.mSlotIndex), this.mUtcTime });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("TelephonyTimeSuggestion{mSlotIndex='");
    stringBuilder.append(this.mSlotIndex);
    stringBuilder.append('\'');
    stringBuilder.append(", mUtcTime=");
    stringBuilder.append(this.mUtcTime);
    stringBuilder.append(", mDebugInfo=");
    stringBuilder.append(this.mDebugInfo);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mSlotIndex);
    paramParcel.writeParcelable((Parcelable)this.mUtcTime, 0);
    paramParcel.writeList(this.mDebugInfo);
  }
  
  public static final class Builder {
    private List<String> mDebugInfo;
    
    private final int mSlotIndex;
    
    private TimestampedValue<Long> mUtcTime;
    
    public Builder(int param1Int) {
      this.mSlotIndex = param1Int;
    }
    
    public Builder addDebugInfo(String param1String) {
      if (this.mDebugInfo == null)
        this.mDebugInfo = new ArrayList<>(); 
      this.mDebugInfo.add(param1String);
      return this;
    }
    
    public TelephonyTimeSuggestion build() {
      return new TelephonyTimeSuggestion(this);
    }
    
    public Builder setUtcTime(TimestampedValue<Long> param1TimestampedValue) {
      if (param1TimestampedValue != null)
        Objects.requireNonNull((Long)param1TimestampedValue.getValue()); 
      this.mUtcTime = param1TimestampedValue;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timedetector/TelephonyTimeSuggestion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */