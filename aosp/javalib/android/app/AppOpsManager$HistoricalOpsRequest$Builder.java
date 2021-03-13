package android.app;

import android.annotation.SystemApi;
import com.android.internal.util.Preconditions;
import java.util.List;

@SystemApi
public final class Builder {
  private String mAttributionTag;
  
  private final long mBeginTimeMillis;
  
  private final long mEndTimeMillis;
  
  private int mFilter;
  
  private int mFlags;
  
  private List<String> mOpNames;
  
  private String mPackageName;
  
  private int mUid;
  
  public Builder(long paramLong1, long paramLong2) {
    boolean bool;
    this.mUid = -1;
    this.mFlags = 31;
    if (paramLong1 >= 0L && paramLong1 < paramLong2) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "beginTimeMillis must be non negative and lesser than endTimeMillis");
    this.mBeginTimeMillis = paramLong1;
    this.mEndTimeMillis = paramLong2;
  }
  
  public AppOpsManager.HistoricalOpsRequest build() {
    return new AppOpsManager.HistoricalOpsRequest(this.mUid, this.mPackageName, this.mAttributionTag, this.mOpNames, this.mFilter, this.mBeginTimeMillis, this.mEndTimeMillis, this.mFlags, null);
  }
  
  public Builder setAttributionTag(String paramString) {
    this.mAttributionTag = paramString;
    this.mFilter |= 0x4;
    return this;
  }
  
  public Builder setFlags(int paramInt) {
    Preconditions.checkFlagsArgument(paramInt, 31);
    this.mFlags = paramInt;
    return this;
  }
  
  public Builder setOpNames(List<String> paramList) {
    if (paramList != null) {
      int i = paramList.size();
      for (byte b = 0; b < i; b++) {
        boolean bool;
        if (AppOpsManager.strOpToOp(paramList.get(b)) != -1) {
          bool = true;
        } else {
          bool = false;
        } 
        Preconditions.checkArgument(bool);
      } 
    } 
    this.mOpNames = paramList;
    if (paramList == null) {
      this.mFilter &= 0xFFFFFFF7;
    } else {
      this.mFilter |= 0x8;
    } 
    return this;
  }
  
  public Builder setPackageName(String paramString) {
    this.mPackageName = paramString;
    if (paramString == null) {
      this.mFilter &= 0xFFFFFFFD;
    } else {
      this.mFilter |= 0x2;
    } 
    return this;
  }
  
  public Builder setUid(int paramInt) {
    boolean bool;
    if (paramInt == -1 || paramInt >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "uid must be -1 or non negative");
    this.mUid = paramInt;
    if (paramInt == -1) {
      this.mFilter &= 0xFFFFFFFE;
    } else {
      this.mFilter = 0x1 | this.mFilter;
    } 
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$HistoricalOpsRequest$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */