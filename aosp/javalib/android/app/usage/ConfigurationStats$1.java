package android.app.usage;

import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ConfigurationStats> {
  public ConfigurationStats createFromParcel(Parcel paramParcel) {
    ConfigurationStats configurationStats = new ConfigurationStats();
    if (paramParcel.readInt() != 0)
      configurationStats.mConfiguration = (Configuration)Configuration.CREATOR.createFromParcel(paramParcel); 
    configurationStats.mBeginTimeStamp = paramParcel.readLong();
    configurationStats.mEndTimeStamp = paramParcel.readLong();
    configurationStats.mLastTimeActive = paramParcel.readLong();
    configurationStats.mTotalTimeActive = paramParcel.readLong();
    configurationStats.mActivationCount = paramParcel.readInt();
    return configurationStats;
  }
  
  public ConfigurationStats[] newArray(int paramInt) {
    return new ConfigurationStats[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/ConfigurationStats$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */