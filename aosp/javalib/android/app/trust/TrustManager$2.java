package android.app.trust;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class null extends Handler {
  null(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    boolean bool1 = false;
    boolean bool2 = false;
    if (i != 1) {
      if (i != 2) {
        if (i == 3) {
          CharSequence charSequence = paramMessage.peekData().getCharSequence("message");
          ((TrustManager.TrustListener)paramMessage.obj).onTrustError(charSequence);
        } 
      } else {
        TrustManager.TrustListener trustListener = (TrustManager.TrustListener)paramMessage.obj;
        if (paramMessage.arg1 != 0)
          bool2 = true; 
        trustListener.onTrustManagedChanged(bool2, paramMessage.arg2);
      } 
    } else {
      if (paramMessage.peekData() != null) {
        i = paramMessage.peekData().getInt("initiatedByUser");
      } else {
        i = 0;
      } 
      TrustManager.TrustListener trustListener = (TrustManager.TrustListener)paramMessage.obj;
      bool2 = bool1;
      if (paramMessage.arg1 != 0)
        bool2 = true; 
      trustListener.onTrustChanged(bool2, paramMessage.arg2, i);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/TrustManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */