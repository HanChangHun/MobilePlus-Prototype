package android.app.usage;

import android.net.DataUsageRequest;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

class CallbackHandler extends Handler {
  private NetworkStatsManager.UsageCallback mCallback;
  
  private final int mNetworkType;
  
  private final String mSubscriberId;
  
  CallbackHandler(Looper paramLooper, int paramInt, String paramString, NetworkStatsManager.UsageCallback paramUsageCallback) {
    super(paramLooper);
    this.mNetworkType = paramInt;
    this.mSubscriberId = paramString;
    this.mCallback = paramUsageCallback;
  }
  
  private static Object getObject(Message paramMessage, String paramString) {
    return paramMessage.getData().getParcelable(paramString);
  }
  
  public void handleMessage(Message paramMessage) {
    DataUsageRequest dataUsageRequest = (DataUsageRequest)getObject(paramMessage, "DataUsageRequest");
    int i = paramMessage.what;
    if (i != 0) {
      if (i == 1)
        this.mCallback = null; 
    } else {
      NetworkStatsManager.UsageCallback usageCallback = this.mCallback;
      if (usageCallback != null) {
        usageCallback.onThresholdReached(this.mNetworkType, this.mSubscriberId);
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("limit reached with released callback for ");
        stringBuilder.append(dataUsageRequest);
        Log.e("NetworkStatsManager", stringBuilder.toString());
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/NetworkStatsManager$CallbackHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */