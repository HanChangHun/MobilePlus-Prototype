package android.bluetooth.le;

import android.annotation.SystemApi;

public final class Builder {
  private int mCallbackType = 1;
  
  private boolean mLegacy = true;
  
  private int mMatchMode = 1;
  
  private int mNumOfMatchesPerFilter = 3;
  
  private int mPhy = 255;
  
  private long mReportDelayMillis = 0L;
  
  private int mScanMode = 0;
  
  private int mScanResultType = 0;
  
  private boolean isValidCallbackType(int paramInt) {
    boolean bool = true;
    if (paramInt == 1 || paramInt == 2 || paramInt == 4)
      return true; 
    if (paramInt != 6)
      bool = false; 
    return bool;
  }
  
  public ScanSettings build() {
    return new ScanSettings(this.mScanMode, this.mCallbackType, this.mScanResultType, this.mReportDelayMillis, this.mMatchMode, this.mNumOfMatchesPerFilter, this.mLegacy, this.mPhy, null);
  }
  
  public Builder setCallbackType(int paramInt) {
    if (isValidCallbackType(paramInt)) {
      this.mCallbackType = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("invalid callback type - ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder setLegacy(boolean paramBoolean) {
    this.mLegacy = paramBoolean;
    return this;
  }
  
  public Builder setMatchMode(int paramInt) {
    if (paramInt >= 1 && paramInt <= 2) {
      this.mMatchMode = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("invalid matchMode ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder setNumOfMatches(int paramInt) {
    if (paramInt >= 1 && paramInt <= 3) {
      this.mNumOfMatchesPerFilter = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("invalid numOfMatches ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder setPhy(int paramInt) {
    this.mPhy = paramInt;
    return this;
  }
  
  public Builder setReportDelay(long paramLong) {
    if (paramLong >= 0L) {
      this.mReportDelayMillis = paramLong;
      return this;
    } 
    throw new IllegalArgumentException("reportDelay must be > 0");
  }
  
  public Builder setScanMode(int paramInt) {
    if (paramInt >= -1 && paramInt <= 2) {
      this.mScanMode = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("invalid scan mode ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  @SystemApi
  public Builder setScanResultType(int paramInt) {
    if (paramInt >= 0 && paramInt <= 1) {
      this.mScanResultType = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("invalid scanResultType - ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/ScanSettings$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */