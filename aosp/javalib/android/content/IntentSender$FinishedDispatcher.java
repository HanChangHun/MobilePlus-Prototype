package android.content;

import android.os.Bundle;
import android.os.Handler;

class FinishedDispatcher extends IIntentReceiver.Stub implements Runnable {
  private final Handler mHandler;
  
  private Intent mIntent;
  
  private final IntentSender mIntentSender;
  
  private int mResultCode;
  
  private String mResultData;
  
  private Bundle mResultExtras;
  
  private final IntentSender.OnFinished mWho;
  
  FinishedDispatcher(IntentSender paramIntentSender, IntentSender.OnFinished paramOnFinished, Handler paramHandler) {
    this.mIntentSender = paramIntentSender;
    this.mWho = paramOnFinished;
    this.mHandler = paramHandler;
  }
  
  public void performReceive(Intent paramIntent, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) {
    this.mIntent = paramIntent;
    this.mResultCode = paramInt1;
    this.mResultData = paramString;
    this.mResultExtras = paramBundle;
    Handler handler = this.mHandler;
    if (handler == null) {
      run();
    } else {
      handler.post(this);
    } 
  }
  
  public void run() {
    this.mWho.onSendFinished(this.mIntentSender, this.mIntent, this.mResultCode, this.mResultData, this.mResultExtras);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IntentSender$FinishedDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */