package android.app;

import android.content.pm.IDexModuleRegisterCallback;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

class DexModuleRegisterCallbackDelegate extends IDexModuleRegisterCallback.Stub implements Handler.Callback {
  private static final int MSG_DEX_MODULE_REGISTERED = 1;
  
  private final PackageManager.DexModuleRegisterCallback callback;
  
  private final Handler mHandler;
  
  DexModuleRegisterCallbackDelegate(PackageManager.DexModuleRegisterCallback paramDexModuleRegisterCallback) {
    this.callback = paramDexModuleRegisterCallback;
    this.mHandler = new Handler(Looper.getMainLooper(), this);
  }
  
  public boolean handleMessage(Message paramMessage) {
    if (paramMessage.what != 1)
      return false; 
    ApplicationPackageManager.DexModuleRegisterResult dexModuleRegisterResult = (ApplicationPackageManager.DexModuleRegisterResult)paramMessage.obj;
    this.callback.onDexModuleRegistered(dexModuleRegisterResult.dexModulePath, dexModuleRegisterResult.success, dexModuleRegisterResult.message);
    return true;
  }
  
  public void onDexModuleRegistered(String paramString1, boolean paramBoolean, String paramString2) throws RemoteException {
    this.mHandler.obtainMessage(1, new ApplicationPackageManager.DexModuleRegisterResult(paramString1, paramBoolean, paramString2, null)).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationPackageManager$DexModuleRegisterCallbackDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */