package android.app.admin;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.SurfaceControlViewHost;

@SystemApi
public class DevicePolicyKeyguardService extends Service {
  private static final String TAG = "DevicePolicyKeyguardService";
  
  private IKeyguardCallback mCallback;
  
  private final IKeyguardClient mClient = new IKeyguardClient.Stub() {
      public void onCreateKeyguardSurface(IBinder param1IBinder, IKeyguardCallback param1IKeyguardCallback) {
        DevicePolicyKeyguardService.access$002(DevicePolicyKeyguardService.this, param1IKeyguardCallback);
        DevicePolicyKeyguardService.this.mHandler.post(new _$$Lambda$DevicePolicyKeyguardService$1$fjXH0Pq_QzHSYdL9qO2BTxQE_IE(this, param1IBinder));
      }
    };
  
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  
  public void dismiss() {
    IKeyguardCallback iKeyguardCallback = this.mCallback;
    if (iKeyguardCallback == null) {
      Log.w("DevicePolicyKeyguardService", "KeyguardCallback was unexpectedly null");
      return;
    } 
    try {
      iKeyguardCallback.onDismiss();
    } catch (RemoteException remoteException) {
      Log.e("DevicePolicyKeyguardService", "onDismiss failed", (Throwable)remoteException);
    } 
  }
  
  public final IBinder onBind(Intent paramIntent) {
    return this.mClient.asBinder();
  }
  
  public SurfaceControlViewHost.SurfacePackage onCreateKeyguardSurface(IBinder paramIBinder) {
    return null;
  }
  
  public void onDestroy() {
    this.mHandler.removeCallbacksAndMessages(null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DevicePolicyKeyguardService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */