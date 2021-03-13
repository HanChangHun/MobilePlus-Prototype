package android.app;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.os.RemoteException;
import android.service.vr.IPersistentVrStateCallbacks;
import android.service.vr.IVrManager;
import android.service.vr.IVrStateCallbacks;
import android.util.ArrayMap;
import java.util.Map;
import java.util.concurrent.Executor;

@SystemApi
public class VrManager {
  private Map<VrStateCallback, CallbackEntry> mCallbackMap = (Map<VrStateCallback, CallbackEntry>)new ArrayMap();
  
  private final IVrManager mService;
  
  public VrManager(IVrManager paramIVrManager) {
    this.mService = paramIVrManager;
  }
  
  public int getVr2dDisplayId() {
    try {
      return this.mService.getVr2dDisplayId();
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
      return -1;
    } 
  }
  
  public boolean isPersistentVrModeEnabled() {
    try {
      return this.mService.getPersistentVrModeEnabled();
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
      return false;
    } 
  }
  
  public boolean isVrModeEnabled() {
    try {
      return this.mService.getVrModeState();
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
      return false;
    } 
  }
  
  public void registerVrStateCallback(Executor paramExecutor, VrStateCallback paramVrStateCallback) {
    if (paramVrStateCallback == null || this.mCallbackMap.containsKey(paramVrStateCallback))
      return; 
    CallbackEntry callbackEntry = new CallbackEntry(paramVrStateCallback, paramExecutor);
    this.mCallbackMap.put(paramVrStateCallback, callbackEntry);
    try {
      this.mService.registerListener(callbackEntry.mStateCallback);
      this.mService.registerPersistentVrStateListener(callbackEntry.mPersistentStateCallback);
    } catch (RemoteException remoteException) {
      try {
        unregisterVrStateCallback(paramVrStateCallback);
      } catch (Exception exception) {
        remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  public void setAndBindVrCompositor(ComponentName paramComponentName) {
    try {
      String str;
      IVrManager iVrManager = this.mService;
      if (paramComponentName == null) {
        paramComponentName = null;
      } else {
        str = paramComponentName.flattenToString();
      } 
      iVrManager.setAndBindCompositor(str);
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setPersistentVrModeEnabled(boolean paramBoolean) {
    try {
      this.mService.setPersistentVrModeEnabled(paramBoolean);
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setStandbyEnabled(boolean paramBoolean) {
    try {
      this.mService.setStandbyEnabled(paramBoolean);
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setVr2dDisplayProperties(Vr2dDisplayProperties paramVr2dDisplayProperties) {
    try {
      this.mService.setVr2dDisplayProperties(paramVr2dDisplayProperties);
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setVrInputMethod(ComponentName paramComponentName) {}
  
  public void unregisterVrStateCallback(VrStateCallback paramVrStateCallback) {
    CallbackEntry callbackEntry = this.mCallbackMap.remove(paramVrStateCallback);
    if (callbackEntry != null) {
      try {
        this.mService.unregisterListener(callbackEntry.mStateCallback);
      } catch (RemoteException remoteException) {}
      try {
        this.mService.unregisterPersistentVrStateListener(callbackEntry.mPersistentStateCallback);
      } catch (RemoteException remoteException) {}
    } 
  }
  
  private static class CallbackEntry {
    final VrStateCallback mCallback;
    
    final Executor mExecutor;
    
    final IPersistentVrStateCallbacks mPersistentStateCallback = (IPersistentVrStateCallbacks)new IPersistentVrStateCallbacks.Stub() {
        public void onPersistentVrStateChanged(boolean param2Boolean) {
          VrManager.CallbackEntry.this.mExecutor.execute(new _$$Lambda$VrManager$CallbackEntry$2$KvHLIXm3_7igcOqTEl46YdjhHMk(this, param2Boolean));
        }
      };
    
    final IVrStateCallbacks mStateCallback = (IVrStateCallbacks)new IVrStateCallbacks.Stub() {
        public void onVrStateChanged(boolean param2Boolean) {
          VrManager.CallbackEntry.this.mExecutor.execute(new _$$Lambda$VrManager$CallbackEntry$1$rgUBVVG1QhelpvAp8W3UQHDHJdU(this, param2Boolean));
        }
      };
    
    CallbackEntry(VrStateCallback param1VrStateCallback, Executor param1Executor) {
      this.mCallback = param1VrStateCallback;
      this.mExecutor = param1Executor;
    }
  }
  
  class null extends IVrStateCallbacks.Stub {
    public void onVrStateChanged(boolean param1Boolean) {
      this.this$0.mExecutor.execute(new _$$Lambda$VrManager$CallbackEntry$1$rgUBVVG1QhelpvAp8W3UQHDHJdU(this, param1Boolean));
    }
  }
  
  class null extends IPersistentVrStateCallbacks.Stub {
    public void onPersistentVrStateChanged(boolean param1Boolean) {
      this.this$0.mExecutor.execute(new _$$Lambda$VrManager$CallbackEntry$2$KvHLIXm3_7igcOqTEl46YdjhHMk(this, param1Boolean));
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VrManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */