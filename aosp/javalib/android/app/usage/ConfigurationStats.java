package android.app.usage;

import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;

public final class ConfigurationStats implements Parcelable {
  public static final Parcelable.Creator<ConfigurationStats> CREATOR = new Parcelable.Creator<ConfigurationStats>() {
      public ConfigurationStats createFromParcel(Parcel param1Parcel) {
        ConfigurationStats configurationStats = new ConfigurationStats();
        if (param1Parcel.readInt() != 0)
          configurationStats.mConfiguration = (Configuration)Configuration.CREATOR.createFromParcel(param1Parcel); 
        configurationStats.mBeginTimeStamp = param1Parcel.readLong();
        configurationStats.mEndTimeStamp = param1Parcel.readLong();
        configurationStats.mLastTimeActive = param1Parcel.readLong();
        configurationStats.mTotalTimeActive = param1Parcel.readLong();
        configurationStats.mActivationCount = param1Parcel.readInt();
        return configurationStats;
      }
      
      public ConfigurationStats[] newArray(int param1Int) {
        return new ConfigurationStats[param1Int];
      }
    };
  
  public int mActivationCount;
  
  public long mBeginTimeStamp;
  
  public Configuration mConfiguration;
  
  public long mEndTimeStamp;
  
  public long mLastTimeActive;
  
  public long mTotalTimeActive;
  
  public ConfigurationStats() {}
  
  public ConfigurationStats(ConfigurationStats paramConfigurationStats) {
    this.mConfiguration = paramConfigurationStats.mConfiguration;
    this.mBeginTimeStamp = paramConfigurationStats.mBeginTimeStamp;
    this.mEndTimeStamp = paramConfigurationStats.mEndTimeStamp;
    this.mLastTimeActive = paramConfigurationStats.mLastTimeActive;
    this.mTotalTimeActive = paramConfigurationStats.mTotalTimeActive;
    this.mActivationCount = paramConfigurationStats.mActivationCount;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getActivationCount() {
    return this.mActivationCount;
  }
  
  public Configuration getConfiguration() {
    return this.mConfiguration;
  }
  
  public long getFirstTimeStamp() {
    return this.mBeginTimeStamp;
  }
  
  public long getLastTimeActive() {
    return this.mLastTimeActive;
  }
  
  public long getLastTimeStamp() {
    return this.mEndTimeStamp;
  }
  
  public long getTotalTimeActive() {
    return this.mTotalTimeActive;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mConfiguration != null) {
      paramParcel.writeInt(1);
      this.mConfiguration.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeLong(this.mBeginTimeStamp);
    paramParcel.writeLong(this.mEndTimeStamp);
    paramParcel.writeLong(this.mLastTimeActive);
    paramParcel.writeLong(this.mTotalTimeActive);
    paramParcel.writeInt(this.mActivationCount);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/ConfigurationStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */