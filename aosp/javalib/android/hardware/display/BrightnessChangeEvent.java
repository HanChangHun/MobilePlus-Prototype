package android.hardware.display;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

@SystemApi
public final class BrightnessChangeEvent implements Parcelable {
  public static final Parcelable.Creator<BrightnessChangeEvent> CREATOR = new Parcelable.Creator<BrightnessChangeEvent>() {
      public BrightnessChangeEvent createFromParcel(Parcel param1Parcel) {
        return new BrightnessChangeEvent(param1Parcel);
      }
      
      public BrightnessChangeEvent[] newArray(int param1Int) {
        return new BrightnessChangeEvent[param1Int];
      }
    };
  
  public final float batteryLevel;
  
  public final float brightness;
  
  public final long colorSampleDuration;
  
  public final int colorTemperature;
  
  public final long[] colorValueBuckets;
  
  public final boolean isDefaultBrightnessConfig;
  
  public final boolean isUserSetBrightness;
  
  public final float lastBrightness;
  
  public final long[] luxTimestamps;
  
  public final float[] luxValues;
  
  public final boolean nightMode;
  
  public final String packageName;
  
  public final float powerBrightnessFactor;
  
  public final long timeStamp;
  
  public final int userId;
  
  private BrightnessChangeEvent(float paramFloat1, long paramLong1, String paramString, int paramInt1, float[] paramArrayOffloat, long[] paramArrayOflong1, float paramFloat2, float paramFloat3, boolean paramBoolean1, int paramInt2, float paramFloat4, boolean paramBoolean2, boolean paramBoolean3, long[] paramArrayOflong2, long paramLong2) {
    this.brightness = paramFloat1;
    this.timeStamp = paramLong1;
    this.packageName = paramString;
    this.userId = paramInt1;
    this.luxValues = paramArrayOffloat;
    this.luxTimestamps = paramArrayOflong1;
    this.batteryLevel = paramFloat2;
    this.powerBrightnessFactor = paramFloat3;
    this.nightMode = paramBoolean1;
    this.colorTemperature = paramInt2;
    this.lastBrightness = paramFloat4;
    this.isDefaultBrightnessConfig = paramBoolean2;
    this.isUserSetBrightness = paramBoolean3;
    this.colorValueBuckets = paramArrayOflong2;
    this.colorSampleDuration = paramLong2;
  }
  
  public BrightnessChangeEvent(BrightnessChangeEvent paramBrightnessChangeEvent, boolean paramBoolean) {
    String str;
    this.brightness = paramBrightnessChangeEvent.brightness;
    this.timeStamp = paramBrightnessChangeEvent.timeStamp;
    if (paramBoolean) {
      str = null;
    } else {
      str = paramBrightnessChangeEvent.packageName;
    } 
    this.packageName = str;
    this.userId = paramBrightnessChangeEvent.userId;
    this.luxValues = paramBrightnessChangeEvent.luxValues;
    this.luxTimestamps = paramBrightnessChangeEvent.luxTimestamps;
    this.batteryLevel = paramBrightnessChangeEvent.batteryLevel;
    this.powerBrightnessFactor = paramBrightnessChangeEvent.powerBrightnessFactor;
    this.nightMode = paramBrightnessChangeEvent.nightMode;
    this.colorTemperature = paramBrightnessChangeEvent.colorTemperature;
    this.lastBrightness = paramBrightnessChangeEvent.lastBrightness;
    this.isDefaultBrightnessConfig = paramBrightnessChangeEvent.isDefaultBrightnessConfig;
    this.isUserSetBrightness = paramBrightnessChangeEvent.isUserSetBrightness;
    this.colorValueBuckets = paramBrightnessChangeEvent.colorValueBuckets;
    this.colorSampleDuration = paramBrightnessChangeEvent.colorSampleDuration;
  }
  
  private BrightnessChangeEvent(Parcel paramParcel) {
    this.brightness = paramParcel.readFloat();
    this.timeStamp = paramParcel.readLong();
    this.packageName = paramParcel.readString();
    this.userId = paramParcel.readInt();
    this.luxValues = paramParcel.createFloatArray();
    this.luxTimestamps = paramParcel.createLongArray();
    this.batteryLevel = paramParcel.readFloat();
    this.powerBrightnessFactor = paramParcel.readFloat();
    this.nightMode = paramParcel.readBoolean();
    this.colorTemperature = paramParcel.readInt();
    this.lastBrightness = paramParcel.readFloat();
    this.isDefaultBrightnessConfig = paramParcel.readBoolean();
    this.isUserSetBrightness = paramParcel.readBoolean();
    this.colorValueBuckets = paramParcel.createLongArray();
    this.colorSampleDuration = paramParcel.readLong();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeFloat(this.brightness);
    paramParcel.writeLong(this.timeStamp);
    paramParcel.writeString(this.packageName);
    paramParcel.writeInt(this.userId);
    paramParcel.writeFloatArray(this.luxValues);
    paramParcel.writeLongArray(this.luxTimestamps);
    paramParcel.writeFloat(this.batteryLevel);
    paramParcel.writeFloat(this.powerBrightnessFactor);
    paramParcel.writeBoolean(this.nightMode);
    paramParcel.writeInt(this.colorTemperature);
    paramParcel.writeFloat(this.lastBrightness);
    paramParcel.writeBoolean(this.isDefaultBrightnessConfig);
    paramParcel.writeBoolean(this.isUserSetBrightness);
    paramParcel.writeLongArray(this.colorValueBuckets);
    paramParcel.writeLong(this.colorSampleDuration);
  }
  
  public static class Builder {
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
      return new BrightnessChangeEvent(this.mBrightness, this.mTimeStamp, this.mPackageName, this.mUserId, this.mLuxValues, this.mLuxTimestamps, this.mBatteryLevel, this.mPowerBrightnessFactor, this.mNightMode, this.mColorTemperature, this.mLastBrightness, this.mIsDefaultBrightnessConfig, this.mIsUserSetBrightness, this.mColorValueBuckets, this.mColorSampleDuration);
    }
    
    public Builder setBatteryLevel(float param1Float) {
      this.mBatteryLevel = param1Float;
      return this;
    }
    
    public Builder setBrightness(float param1Float) {
      this.mBrightness = param1Float;
      return this;
    }
    
    public Builder setColorTemperature(int param1Int) {
      this.mColorTemperature = param1Int;
      return this;
    }
    
    public Builder setColorValues(long[] param1ArrayOflong, long param1Long) {
      Objects.requireNonNull(param1ArrayOflong);
      this.mColorValueBuckets = param1ArrayOflong;
      this.mColorSampleDuration = param1Long;
      return this;
    }
    
    public Builder setIsDefaultBrightnessConfig(boolean param1Boolean) {
      this.mIsDefaultBrightnessConfig = param1Boolean;
      return this;
    }
    
    public Builder setLastBrightness(float param1Float) {
      this.mLastBrightness = param1Float;
      return this;
    }
    
    public Builder setLuxTimestamps(long[] param1ArrayOflong) {
      this.mLuxTimestamps = param1ArrayOflong;
      return this;
    }
    
    public Builder setLuxValues(float[] param1ArrayOffloat) {
      this.mLuxValues = param1ArrayOffloat;
      return this;
    }
    
    public Builder setNightMode(boolean param1Boolean) {
      this.mNightMode = param1Boolean;
      return this;
    }
    
    public Builder setPackageName(String param1String) {
      this.mPackageName = param1String;
      return this;
    }
    
    public Builder setPowerBrightnessFactor(float param1Float) {
      this.mPowerBrightnessFactor = param1Float;
      return this;
    }
    
    public Builder setTimeStamp(long param1Long) {
      this.mTimeStamp = param1Long;
      return this;
    }
    
    public Builder setUserBrightnessPoint(boolean param1Boolean) {
      this.mIsUserSetBrightness = param1Boolean;
      return this;
    }
    
    public Builder setUserId(int param1Int) {
      this.mUserId = param1Int;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/BrightnessChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */