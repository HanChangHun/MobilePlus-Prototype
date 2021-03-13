package android.app.timezone;

import android.content.Context;
import android.os.Handler;

class CallbackWrapper extends ICallback.Stub {
  final Callback mCallback;
  
  final Handler mHandler;
  
  CallbackWrapper(Context paramContext, Callback paramCallback) {
    this.mCallback = paramCallback;
    this.mHandler = new Handler(paramContext.getMainLooper());
  }
  
  public void onFinished(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("mCallback.onFinished(status), status=");
    stringBuilder.append(paramInt);
    RulesManager.logDebug(stringBuilder.toString());
    this.mHandler.post(new _$$Lambda$RulesManager$CallbackWrapper$t7a48uTTxaRuSo3YBKxBIbPQznY(this, paramInt));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/RulesManager$CallbackWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */