package android.bluetooth.le;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

public final class ScanSettings implements Parcelable {
  public static final int CALLBACK_TYPE_ALL_MATCHES = 1;
  
  public static final int CALLBACK_TYPE_FIRST_MATCH = 2;
  
  public static final int CALLBACK_TYPE_MATCH_LOST = 4;
  
  public static final Parcelable.Creator<ScanSettings> CREATOR = new Parcelable.Creator<ScanSettings>() {
      public ScanSettings createFromParcel(Parcel param1Parcel) {
        return new ScanSettings(param1Parcel);
      }
      
      public ScanSettings[] newArray(int param1Int) {
        return new ScanSettings[param1Int];
      }
    };
  
  public static final int MATCH_MODE_AGGRESSIVE = 1;
  
  public static final int MATCH_MODE_STICKY = 2;
  
  public static final int MATCH_NUM_FEW_ADVERTISEMENT = 2;
  
  public static final int MATCH_NUM_MAX_ADVERTISEMENT = 3;
  
  public static final int MATCH_NUM_ONE_ADVERTISEMENT = 1;
  
  public static final int PHY_LE_ALL_SUPPORTED = 255;
  
  public static final int SCAN_MODE_BALANCED = 1;
  
  public static final int SCAN_MODE_LOW_LATENCY = 2;
  
  public static final int SCAN_MODE_LOW_POWER = 0;
  
  public static final int SCAN_MODE_OPPORTUNISTIC = -1;
  
  @SystemApi
  public static final int SCAN_RESULT_TYPE_ABBREVIATED = 1;
  
  @SystemApi
  public static final int SCAN_RESULT_TYPE_FULL = 0;
  
  private int mCallbackType;
  
  private boolean mLegacy;
  
  private int mMatchMode;
  
  private int mNumOfMatchesPerFilter;
  
  private int mPhy;
  
  private long mReportDelayMillis;
  
  private int mScanMode;
  
  private int mScanResultType;
  
  private ScanSettings(int paramInt1, int paramInt2, int paramInt3, long paramLong, int paramInt4, int paramInt5, boolean paramBoolean, int paramInt6) {
    this.mScanMode = paramInt1;
    this.mCallbackType = paramInt2;
    this.mScanResultType = paramInt3;
    this.mReportDelayMillis = paramLong;
    this.mNumOfMatchesPerFilter = paramInt5;
    this.mMatchMode = paramInt4;
    this.mLegacy = paramBoolean;
    this.mPhy = paramInt6;
  }
  
  private ScanSettings(Parcel paramParcel) {
    boolean bool;
    this.mScanMode = paramParcel.readInt();
    this.mCallbackType = paramParcel.readInt();
    this.mScanResultType = paramParcel.readInt();
    this.mReportDelayMillis = paramParcel.readLong();
    this.mMatchMode = paramParcel.readInt();
    this.mNumOfMatchesPerFilter = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mLegacy = bool;
    this.mPhy = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getCallbackType() {
    return this.mCallbackType;
  }
  
  public boolean getLegacy() {
    return this.mLegacy;
  }
  
  public int getMatchMode() {
    return this.mMatchMode;
  }
  
  public int getNumOfMatches() {
    return this.mNumOfMatchesPerFilter;
  }
  
  public int getPhy() {
    return this.mPhy;
  }
  
  public long getReportDelayMillis() {
    return this.mReportDelayMillis;
  }
  
  public int getScanMode() {
    return this.mScanMode;
  }
  
  public int getScanResultType() {
    return this.mScanResultType;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mScanMode);
    paramParcel.writeInt(this.mCallbackType);
    paramParcel.writeInt(this.mScanResultType);
    paramParcel.writeLong(this.mReportDelayMillis);
    paramParcel.writeInt(this.mMatchMode);
    paramParcel.writeInt(this.mNumOfMatchesPerFilter);
    paramParcel.writeInt(this.mLegacy);
    paramParcel.writeInt(this.mPhy);
  }
  
  public static final class Builder {
    private int mCallbackType = 1;
    
    private boolean mLegacy = true;
    
    private int mMatchMode = 1;
    
    private int mNumOfMatchesPerFilter = 3;
    
    private int mPhy = 255;
    
    private long mReportDelayMillis = 0L;
    
    private int mScanMode = 0;
    
    private int mScanResultType = 0;
    
    private boolean isValidCallbackType(int param1Int) {
      boolean bool = true;
      if (param1Int == 1 || param1Int == 2 || param1Int == 4)
        return true; 
      if (param1Int != 6)
        bool = false; 
      return bool;
    }
    
    public ScanSettings build() {
      return new ScanSettings(this.mScanMode, this.mCallbackType, this.mScanResultType, this.mReportDelayMillis, this.mMatchMode, this.mNumOfMatchesPerFilter, this.mLegacy, this.mPhy);
    }
    
    public Builder setCallbackType(int param1Int) {
      if (isValidCallbackType(param1Int)) {
        this.mCallbackType = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("invalid callback type - ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder setLegacy(boolean param1Boolean) {
      this.mLegacy = param1Boolean;
      return this;
    }
    
    public Builder setMatchMode(int param1Int) {
      if (param1Int >= 1 && param1Int <= 2) {
        this.mMatchMode = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("invalid matchMode ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder setNumOfMatches(int param1Int) {
      if (param1Int >= 1 && param1Int <= 3) {
        this.mNumOfMatchesPerFilter = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("invalid numOfMatches ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder setPhy(int param1Int) {
      this.mPhy = param1Int;
      return this;
    }
    
    public Builder setReportDelay(long param1Long) {
      if (param1Long >= 0L) {
        this.mReportDelayMillis = param1Long;
        return this;
      } 
      throw new IllegalArgumentException("reportDelay must be > 0");
    }
    
    public Builder setScanMode(int param1Int) {
      if (param1Int >= -1 && param1Int <= 2) {
        this.mScanMode = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("invalid scan mode ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    @SystemApi
    public Builder setScanResultType(int param1Int) {
      if (param1Int >= 0 && param1Int <= 1) {
        this.mScanResultType = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("invalid scanResultType - ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/ScanSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */