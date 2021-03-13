package android.app.trust;

import android.hardware.biometrics.BiometricSourceType;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.ArrayMap;

public class TrustManager {
  private static final String DATA_FLAGS = "initiatedByUser";
  
  private static final String DATA_MESSAGE = "message";
  
  private static final int MSG_TRUST_CHANGED = 1;
  
  private static final int MSG_TRUST_ERROR = 3;
  
  private static final int MSG_TRUST_MANAGED_CHANGED = 2;
  
  private static final String TAG = "TrustManager";
  
  private final Handler mHandler = new Handler(Looper.getMainLooper()) {
      public void handleMessage(Message param1Message) {
        int i = param1Message.what;
        boolean bool1 = false;
        boolean bool2 = false;
        if (i != 1) {
          if (i != 2) {
            if (i == 3) {
              CharSequence charSequence = param1Message.peekData().getCharSequence("message");
              ((TrustManager.TrustListener)param1Message.obj).onTrustError(charSequence);
            } 
          } else {
            TrustManager.TrustListener trustListener = (TrustManager.TrustListener)param1Message.obj;
            if (param1Message.arg1 != 0)
              bool2 = true; 
            trustListener.onTrustManagedChanged(bool2, param1Message.arg2);
          } 
        } else {
          if (param1Message.peekData() != null) {
            i = param1Message.peekData().getInt("initiatedByUser");
          } else {
            i = 0;
          } 
          TrustManager.TrustListener trustListener = (TrustManager.TrustListener)param1Message.obj;
          bool2 = bool1;
          if (param1Message.arg1 != 0)
            bool2 = true; 
          trustListener.onTrustChanged(bool2, param1Message.arg2, i);
        } 
      }
    };
  
  private final ITrustManager mService;
  
  private final ArrayMap<TrustListener, ITrustListener> mTrustListeners;
  
  public TrustManager(IBinder paramIBinder) {
    this.mService = ITrustManager.Stub.asInterface(paramIBinder);
    this.mTrustListeners = new ArrayMap();
  }
  
  public void clearAllBiometricRecognized(BiometricSourceType paramBiometricSourceType) {
    try {
      this.mService.clearAllBiometricRecognized(paramBiometricSourceType);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isTrustUsuallyManaged(int paramInt) {
    try {
      return this.mService.isTrustUsuallyManaged(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void registerTrustListener(TrustListener paramTrustListener) {
    try {
      ITrustListener.Stub stub = new ITrustListener.Stub() {
          public void onTrustChanged(boolean param1Boolean, int param1Int1, int param1Int2) {
            Message message = TrustManager.this.mHandler.obtainMessage(1, param1Boolean, param1Int1, trustListener);
            if (param1Int2 != 0)
              message.getData().putInt("initiatedByUser", param1Int2); 
            message.sendToTarget();
          }
          
          public void onTrustError(CharSequence param1CharSequence) {
            Message message = TrustManager.this.mHandler.obtainMessage(3);
            message.getData().putCharSequence("message", param1CharSequence);
            message.sendToTarget();
          }
          
          public void onTrustManagedChanged(boolean param1Boolean, int param1Int) {
            TrustManager.this.mHandler.obtainMessage(2, param1Boolean, param1Int, trustListener).sendToTarget();
          }
        };
      super(this, paramTrustListener);
      this.mService.registerTrustListener(stub);
      this.mTrustListeners.put(paramTrustListener, stub);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void reportEnabledTrustAgentsChanged(int paramInt) {
    try {
      this.mService.reportEnabledTrustAgentsChanged(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void reportKeyguardShowingChanged() {
    try {
      this.mService.reportKeyguardShowingChanged();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void reportUnlockAttempt(boolean paramBoolean, int paramInt) {
    try {
      this.mService.reportUnlockAttempt(paramBoolean, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void reportUnlockLockout(int paramInt1, int paramInt2) {
    try {
      this.mService.reportUnlockLockout(paramInt1, paramInt2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setDeviceLockedForUser(int paramInt, boolean paramBoolean) {
    try {
      this.mService.setDeviceLockedForUser(paramInt, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void unlockedByBiometricForUser(int paramInt, BiometricSourceType paramBiometricSourceType) {
    try {
      this.mService.unlockedByBiometricForUser(paramInt, paramBiometricSourceType);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void unregisterTrustListener(TrustListener paramTrustListener) {
    ITrustListener iTrustListener = (ITrustListener)this.mTrustListeners.remove(paramTrustListener);
    if (iTrustListener != null)
      try {
        this.mService.unregisterTrustListener(iTrustListener);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public static interface TrustListener {
    void onTrustChanged(boolean param1Boolean, int param1Int1, int param1Int2);
    
    void onTrustError(CharSequence param1CharSequence);
    
    void onTrustManagedChanged(boolean param1Boolean, int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/TrustManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */