package android.app.timezonedetector;

import java.util.ArrayList;
import java.util.List;

public final class Builder {
  private List<String> mDebugInfo;
  
  private int mMatchType;
  
  private int mQuality;
  
  private final int mSlotIndex;
  
  private String mZoneId;
  
  public Builder(int paramInt) {
    this.mSlotIndex = paramInt;
  }
  
  public Builder addDebugInfo(String paramString) {
    if (this.mDebugInfo == null)
      this.mDebugInfo = new ArrayList<>(); 
    this.mDebugInfo.add(paramString);
    return this;
  }
  
  public TelephonyTimeZoneSuggestion build() {
    validate();
    return new TelephonyTimeZoneSuggestion(this, null);
  }
  
  public Builder setMatchType(int paramInt) {
    this.mMatchType = paramInt;
    return this;
  }
  
  public Builder setQuality(int paramInt) {
    this.mQuality = paramInt;
    return this;
  }
  
  public Builder setZoneId(String paramString) {
    this.mZoneId = paramString;
    return this;
  }
  
  void validate() {
    int i = this.mQuality;
    int j = this.mMatchType;
    if (this.mZoneId == null) {
      if (i != 0 || j != 0) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid quality or match type for null zone ID. quality=");
        stringBuilder.append(i);
        stringBuilder.append(", matchType=");
        stringBuilder.append(j);
        throw new RuntimeException(stringBuilder.toString());
      } 
    } else {
      boolean bool2;
      boolean bool1 = false;
      if (i == 1 || i == 2 || i == 3) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      if (j == 2 || j == 3 || j == 4 || j == 5)
        bool1 = true; 
      if (!bool2 || !bool1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid quality or match type with zone ID. quality=");
        stringBuilder.append(i);
        stringBuilder.append(", matchType=");
        stringBuilder.append(j);
        throw new RuntimeException(stringBuilder.toString());
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezonedetector/TelephonyTimeZoneSuggestion$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */