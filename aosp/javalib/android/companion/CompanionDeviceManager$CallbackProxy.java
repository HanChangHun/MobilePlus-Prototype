package android.companion;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import java.util.function.BiConsumer;

class CallbackProxy extends IFindDeviceCallback.Stub implements Application.ActivityLifecycleCallbacks {
  private CompanionDeviceManager.Callback mCallback;
  
  private Handler mHandler;
  
  final Object mLock = new Object();
  
  private AssociationRequest mRequest;
  
  private CallbackProxy(AssociationRequest paramAssociationRequest, CompanionDeviceManager.Callback paramCallback, Handler paramHandler) {
    this.mCallback = paramCallback;
    this.mHandler = paramHandler;
    this.mRequest = paramAssociationRequest;
    CompanionDeviceManager.access$100(paramCompanionDeviceManager).getApplication().registerActivityLifecycleCallbacks(this);
  }
  
  <T> void lockAndPost(BiConsumer<CompanionDeviceManager.Callback, T> paramBiConsumer, T paramT) {
    synchronized (this.mLock) {
      if (this.mHandler != null) {
        Handler handler = this.mHandler;
        _$$Lambda$CompanionDeviceManager$CallbackProxy$gkUVA3m3QgEEk8G84_kcBFARHvo _$$Lambda$CompanionDeviceManager$CallbackProxy$gkUVA3m3QgEEk8G84_kcBFARHvo = new _$$Lambda$CompanionDeviceManager$CallbackProxy$gkUVA3m3QgEEk8G84_kcBFARHvo();
        this(this, paramBiConsumer, paramT);
        handler.post(_$$Lambda$CompanionDeviceManager$CallbackProxy$gkUVA3m3QgEEk8G84_kcBFARHvo);
      } 
      return;
    } 
  }
  
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityDestroyed(Activity paramActivity) {
    synchronized (this.mLock) {
      if (paramActivity != CompanionDeviceManager.access$100(CompanionDeviceManager.this))
        return; 
      try {
        CompanionDeviceManager.access$300(CompanionDeviceManager.this).stopScan(this.mRequest, this, CompanionDeviceManager.access$200(CompanionDeviceManager.this));
      } catch (RemoteException remoteException) {
        remoteException.rethrowFromSystemServer();
      } 
      CompanionDeviceManager.access$100(CompanionDeviceManager.this).getApplication().unregisterActivityLifecycleCallbacks(this);
      this.mCallback = null;
      this.mHandler = null;
      this.mRequest = null;
      return;
    } 
  }
  
  public void onActivityPaused(Activity paramActivity) {}
  
  public void onActivityResumed(Activity paramActivity) {}
  
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityStarted(Activity paramActivity) {}
  
  public void onActivityStopped(Activity paramActivity) {}
  
  public void onFailure(CharSequence paramCharSequence) {
    lockAndPost((BiConsumer<CompanionDeviceManager.Callback, CharSequence>)_$$Lambda$ZUPGnRMz08ZrG1ogNO_2O5Hso3I.INSTANCE, paramCharSequence);
  }
  
  public void onSuccess(PendingIntent paramPendingIntent) {
    lockAndPost((BiConsumer<CompanionDeviceManager.Callback, IntentSender>)_$$Lambda$OThxsns9MAD5QsKURFQAFbt_3qc.INSTANCE, paramPendingIntent.getIntentSender());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/CompanionDeviceManager$CallbackProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */