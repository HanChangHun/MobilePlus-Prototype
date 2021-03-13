package android.app.timezone;

import android.content.Context;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public final class RulesManager {
  public static final String ACTION_RULES_UPDATE_OPERATION = "com.android.intent.action.timezone.RULES_UPDATE_OPERATION";
  
  private static final boolean DEBUG = false;
  
  public static final int ERROR_OPERATION_IN_PROGRESS = 1;
  
  public static final int ERROR_UNKNOWN_FAILURE = 2;
  
  public static final String EXTRA_OPERATION_STAGED = "staged";
  
  public static final int SUCCESS = 0;
  
  private static final String TAG = "timezone.RulesManager";
  
  private final Context mContext;
  
  private final IRulesManager mIRulesManager;
  
  public RulesManager(Context paramContext) {
    this.mContext = paramContext;
    this.mIRulesManager = IRulesManager.Stub.asInterface(ServiceManager.getService("timezone"));
  }
  
  static void logDebug(String paramString) {}
  
  public RulesState getRulesState() {
    try {
      logDebug("mIRulesManager.getRulesState()");
      RulesState rulesState = this.mIRulesManager.getRulesState();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("mIRulesManager.getRulesState() returned ");
      stringBuilder.append(rulesState);
      logDebug(stringBuilder.toString());
      return rulesState;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int requestInstall(ParcelFileDescriptor paramParcelFileDescriptor, byte[] paramArrayOfbyte, Callback paramCallback) throws IOException {
    CallbackWrapper callbackWrapper = new CallbackWrapper(this.mContext, paramCallback);
    try {
      logDebug("mIRulesManager.requestInstall()");
      return this.mIRulesManager.requestInstall(paramParcelFileDescriptor, paramArrayOfbyte, callbackWrapper);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void requestNothing(byte[] paramArrayOfbyte, boolean paramBoolean) {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("mIRulesManager.requestNothing() with token=");
      stringBuilder.append(Arrays.toString(paramArrayOfbyte));
      logDebug(stringBuilder.toString());
      this.mIRulesManager.requestNothing(paramArrayOfbyte, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int requestUninstall(byte[] paramArrayOfbyte, Callback paramCallback) {
    CallbackWrapper callbackWrapper = new CallbackWrapper(this.mContext, paramCallback);
    try {
      logDebug("mIRulesManager.requestUninstall()");
      return this.mIRulesManager.requestUninstall(paramArrayOfbyte, callbackWrapper);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private class CallbackWrapper extends ICallback.Stub {
    final Callback mCallback;
    
    final Handler mHandler;
    
    CallbackWrapper(Context param1Context, Callback param1Callback) {
      this.mCallback = param1Callback;
      this.mHandler = new Handler(param1Context.getMainLooper());
    }
    
    public void onFinished(int param1Int) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("mCallback.onFinished(status), status=");
      stringBuilder.append(param1Int);
      RulesManager.logDebug(stringBuilder.toString());
      this.mHandler.post(new _$$Lambda$RulesManager$CallbackWrapper$t7a48uTTxaRuSo3YBKxBIbPQznY(this, param1Int));
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ResultCode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/RulesManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */