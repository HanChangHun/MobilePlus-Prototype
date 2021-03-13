package android.hardware.location;

import android.annotation.SystemApi;

@SystemApi
public class ContextHubClientCallback {
  public void onHubReset(ContextHubClient paramContextHubClient) {}
  
  public void onMessageFromNanoApp(ContextHubClient paramContextHubClient, NanoAppMessage paramNanoAppMessage) {}
  
  public void onNanoAppAborted(ContextHubClient paramContextHubClient, long paramLong, int paramInt) {}
  
  public void onNanoAppDisabled(ContextHubClient paramContextHubClient, long paramLong) {}
  
  public void onNanoAppEnabled(ContextHubClient paramContextHubClient, long paramLong) {}
  
  public void onNanoAppLoaded(ContextHubClient paramContextHubClient, long paramLong) {}
  
  public void onNanoAppUnloaded(ContextHubClient paramContextHubClient, long paramLong) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubClientCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */