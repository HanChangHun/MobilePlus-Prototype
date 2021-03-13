package android.app.admin;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.SurfaceControlViewHost;

class null extends IKeyguardClient.Stub {
  public void onCreateKeyguardSurface(IBinder paramIBinder, IKeyguardCallback paramIKeyguardCallback) {
    DevicePolicyKeyguardService.access$002(DevicePolicyKeyguardService.this, paramIKeyguardCallback);
    DevicePolicyKeyguardService.access$100(DevicePolicyKeyguardService.this).post(new _$$Lambda$DevicePolicyKeyguardService$1$fjXH0Pq_QzHSYdL9qO2BTxQE_IE(this, paramIBinder));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DevicePolicyKeyguardService$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */