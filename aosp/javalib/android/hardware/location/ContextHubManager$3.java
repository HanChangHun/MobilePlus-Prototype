package android.hardware.location;

import java.util.concurrent.Executor;

class null extends IContextHubClientCallback.Stub {
  public void onHubReset() {
    executor.execute(new _$$Lambda$ContextHubManager$3$kLhhBRChCeue1LKohd5lK_lfKTU(callback, client));
  }
  
  public void onMessageFromNanoApp(NanoAppMessage paramNanoAppMessage) {
    executor.execute(new _$$Lambda$ContextHubManager$3$U9x_HK_GdADIEQ3mS5mDWMNWMu8(callback, client, paramNanoAppMessage));
  }
  
  public void onNanoAppAborted(long paramLong, int paramInt) {
    executor.execute(new _$$Lambda$ContextHubManager$3$hASoxw9hzmd9l2NpC91O5tXLzxU(callback, client, paramLong, paramInt));
  }
  
  public void onNanoAppDisabled(long paramLong) {
    executor.execute(new _$$Lambda$ContextHubManager$3$On2Q5Obzm4_zLY0UP3Xs4E3P_V0(callback, client, paramLong));
  }
  
  public void onNanoAppEnabled(long paramLong) {
    executor.execute(new _$$Lambda$ContextHubManager$3$8oeFzBAC_VuH1d32Kod8BVn0Os8(callback, client, paramLong));
  }
  
  public void onNanoAppLoaded(long paramLong) {
    executor.execute(new _$$Lambda$ContextHubManager$3$5yx25kUuvL9qy3uBcIzI3sQQoL8(callback, client, paramLong));
  }
  
  public void onNanoAppUnloaded(long paramLong) {
    executor.execute(new _$$Lambda$ContextHubManager$3$KgVQePwT_QpjU9EQTp2L3LsHE5Y(callback, client, paramLong));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubManager$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */