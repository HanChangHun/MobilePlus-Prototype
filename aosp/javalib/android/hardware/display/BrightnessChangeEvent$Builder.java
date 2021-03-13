package android.hardware.display;

import java.util.Objects;

public class Builder {
  private float mBatteryLevel;
  
  private float mBrightness;
  
  private long mColorSampleDuration;
  
  private int mColorTemperature;
  
  private long[] mColorValueBuckets;
  
  private boolean mIsDefaultBrightnessConfig;
  
  private boolean mIsUserSetBrightness;
  
  private float mLastBrightness;
  
  private long[] mLuxTimestamps;
  
  private float[] mLuxValues;
  
  private boolean mNightMode;
  
  private String mPackageName;
  
  private float mPowerBrightnessFactor;
  
  private long mTimeStamp;
  
  private int mUserId;
  
  public BrightnessChangeEvent build() {
    return new BrightnessChangeEvent(this.mBrightness, this.mTimeStamp, this.mPackageName, this.mUserId, this.mLuxValues, this.mLuxTimestamps, this.mBatteryLevel, this.mPowerBrightnessFactor, this.mNightMode, this.mColorTemperature, this.mLastBrightness, this.mIsDefaultBrightnessConfig, this.mIsUserSetBrightness, this.mColorValueBuckets, this.mColorSampleDuration, null);
  }
  
  public Builder setBatteryLevel(float paramFloat) {
    this.mBatteryLevel = paramFloat;
    return this;
  }
  
  public Builder setBrightness(float paramFloat) {
    this.mBrightness = paramFloat;
    return this;
  }
  
  public Builder setColorTemperature(int paramInt) {
    this.mColorTemperature = paramInt;
    return this;
  }
  
  public Builder setColorValues(long[] paramArrayOflong, long paramLong) {
    Objects.requireNonNull(paramArrayOflong);
    this.mColorValueBuckets = paramArrayOflong;
    this.mColorSampleDuration = paramLong;
    return this;
  }
  
  public Builder setIsDefaultBrightnessConfig(boolean paramBoolean) {
    this.mIsDefaultBrightnessConfig = paramBoolean;
    return this;
  }
  
  public Builder setLastBrightness(float paramFloat) {
    this.mLastBrightness = paramFloat;
    return this;
  }
  
  public Builder setLuxTimestamps(long[] paramArrayOflong) {
    this.mLuxTimestamps = paramArrayOflong;
    return this;
  }
  
  public Builder setLuxValues(float[] paramArrayOffloat) {
    this.mLuxValues = paramArrayOffloat;
    return this;
  }
  
  public Builder setNightMode(boolean paramBoolean) {
    this.mNightMode = paramBoolean;
    return this;
  }
  
  public Builder setPackageName(String paramString) {
    this.mPackageName = paramString;
    return this;
  }
  
  public Builder setPowerBrightnessFactor(float paramFloat) {
    this.mPowerBrightnessFactor = paramFloat;
    return this;
  }
  
  public Builder setTimeStamp(long paramLong) {
    this.mTimeStamp = paramLong;
    return this;
  }
  
  public Builder setUserBrightnessPoint(boolean paramBoolean) {
    this.mIsUserSetBrightness = paramBoolean;
    return this;
  }
  
  public Builder setUserId(int paramInt) {
    this.mUserId = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/BrightnessChangeEvent$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */