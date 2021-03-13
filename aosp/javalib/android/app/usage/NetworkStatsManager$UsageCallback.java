package android.app.usage;

import android.net.DataUsageRequest;

public abstract class UsageCallback {
  private DataUsageRequest request;
  
  public abstract void onThresholdReached(int paramInt, String paramString);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/NetworkStatsManager$UsageCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */