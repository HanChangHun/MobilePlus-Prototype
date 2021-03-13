package android.app;

import android.content.IIntentReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

class FinishedDispatcher extends IIntentReceiver.Stub implements Runnable {
  private static Handler sDefaultSystemHandler;
  
  private final Handler mHandler;
  
  private Intent mIntent;
  
  private final PendingIntent mPendingIntent;
  
  private int mResultCode;
  
  private String mResultData;
  
  private Bundle mResultExtras;
  
  private final PendingIntent.OnFinished mWho;
  
  FinishedDispatcher(PendingIntent paramPendingIntent, PendingIntent.OnFinished paramOnFinished, Handler paramHandler) {
    this.mPendingIntent = paramPendingIntent;
    this.mWho = paramOnFinished;
    if (paramHandler == null && ActivityThread.isSystem()) {
      if (sDefaultSystemHandler == null)
        sDefaultSystemHandler = new Handler(Looper.getMainLooper()); 
      this.mHandler = sDefaultSystemHandler;
    } else {
      this.mHandler = paramHandler;
    } 
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
    this.mWho.onSendFinished(this.mPendingIntent, this.mIntent, this.mResultCode, this.mResultData, this.mResultExtras);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PendingIntent$FinishedDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */