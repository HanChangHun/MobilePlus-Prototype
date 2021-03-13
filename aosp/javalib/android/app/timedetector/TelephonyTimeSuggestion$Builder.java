package android.app.timedetector;

import android.os.TimestampedValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Builder {
  private List<String> mDebugInfo;
  
  private final int mSlotIndex;
  
  private TimestampedValue<Long> mUtcTime;
  
  public Builder(int paramInt) {
    this.mSlotIndex = paramInt;
  }
  
  public Builder addDebugInfo(String paramString) {
    if (this.mDebugInfo == null)
      this.mDebugInfo = new ArrayList<>(); 
    this.mDebugInfo.add(paramString);
    return this;
  }
  
  public TelephonyTimeSuggestion build() {
    return new TelephonyTimeSuggestion(this, null);
  }
  
  public Builder setUtcTime(TimestampedValue<Long> paramTimestampedValue) {
    if (paramTimestampedValue != null)
      Objects.requireNonNull((Long)paramTimestampedValue.getValue()); 
    this.mUtcTime = paramTimestampedValue;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timedetector/TelephonyTimeSuggestion$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */