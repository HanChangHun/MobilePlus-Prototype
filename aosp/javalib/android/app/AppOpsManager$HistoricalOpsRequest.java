package android.app;

import android.annotation.SystemApi;
import com.android.internal.util.Preconditions;
import java.util.List;

@SystemApi
public final class HistoricalOpsRequest {
  private final String mAttributionTag;
  
  private final long mBeginTimeMillis;
  
  private final long mEndTimeMillis;
  
  private final int mFilter;
  
  private final int mFlags;
  
  private final List<String> mOpNames;
  
  private final String mPackageName;
  
  private final int mUid;
  
  private HistoricalOpsRequest(int paramInt1, String paramString1, String paramString2, List<String> paramList, int paramInt2, long paramLong1, long paramLong2, int paramInt3) {
    this.mUid = paramInt1;
    this.mPackageName = paramString1;
    this.mAttributionTag = paramString2;
    this.mOpNames = paramList;
    this.mFilter = paramInt2;
    this.mBeginTimeMillis = paramLong1;
    this.mEndTimeMillis = paramLong2;
    this.mFlags = paramInt3;
  }
  
  @SystemApi
  public static final class Builder {
    private String mAttributionTag;
    
    private final long mBeginTimeMillis;
    
    private final long mEndTimeMillis;
    
    private int mFilter;
    
    private int mFlags;
    
    private List<String> mOpNames;
    
    private String mPackageName;
    
    private int mUid;
    
    public Builder(long param2Long1, long param2Long2) {
      boolean bool;
      this.mUid = -1;
      this.mFlags = 31;
      if (param2Long1 >= 0L && param2Long1 < param2Long2) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, "beginTimeMillis must be non negative and lesser than endTimeMillis");
      this.mBeginTimeMillis = param2Long1;
      this.mEndTimeMillis = param2Long2;
    }
    
    public AppOpsManager.HistoricalOpsRequest build() {
      return new AppOpsManager.HistoricalOpsRequest(this.mUid, this.mPackageName, this.mAttributionTag, this.mOpNames, this.mFilter, this.mBeginTimeMillis, this.mEndTimeMillis, this.mFlags);
    }
    
    public Builder setAttributionTag(String param2String) {
      this.mAttributionTag = param2String;
      this.mFilter |= 0x4;
      return this;
    }
    
    public Builder setFlags(int param2Int) {
      Preconditions.checkFlagsArgument(param2Int, 31);
      this.mFlags = param2Int;
      return this;
    }
    
    public Builder setOpNames(List<String> param2List) {
      if (param2List != null) {
        int i = param2List.size();
        for (byte b = 0; b < i; b++) {
          boolean bool;
          if (AppOpsManager.strOpToOp(param2List.get(b)) != -1) {
            bool = true;
          } else {
            bool = false;
          } 
          Preconditions.checkArgument(bool);
        } 
      } 
      this.mOpNames = param2List;
      if (param2List == null) {
        this.mFilter &= 0xFFFFFFF7;
      } else {
        this.mFilter |= 0x8;
      } 
      return this;
    }
    
    public Builder setPackageName(String param2String) {
      this.mPackageName = param2String;
      if (param2String == null) {
        this.mFilter &= 0xFFFFFFFD;
      } else {
        this.mFilter |= 0x2;
      } 
      return this;
    }
    
    public Builder setUid(int param2Int) {
      boolean bool;
      if (param2Int == -1 || param2Int >= 0) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, "uid must be -1 or non negative");
      this.mUid = param2Int;
      if (param2Int == -1) {
        this.mFilter &= 0xFFFFFFFE;
      } else {
        this.mFilter = 0x1 | this.mFilter;
      } 
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$HistoricalOpsRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */