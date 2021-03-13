package android.app.trust;

import android.os.Message;

class null extends ITrustListener.Stub {
  public void onTrustChanged(boolean paramBoolean, int paramInt1, int paramInt2) {
    Message message = TrustManager.access$000(TrustManager.this).obtainMessage(1, paramBoolean, paramInt1, trustListener);
    if (paramInt2 != 0)
      message.getData().putInt("initiatedByUser", paramInt2); 
    message.sendToTarget();
  }
  
  public void onTrustError(CharSequence paramCharSequence) {
    Message message = TrustManager.access$000(TrustManager.this).obtainMessage(3);
    message.getData().putCharSequence("message", paramCharSequence);
    message.sendToTarget();
  }
  
  public void onTrustManagedChanged(boolean paramBoolean, int paramInt) {
    TrustManager.access$000(TrustManager.this).obtainMessage(2, paramBoolean, paramInt, trustListener).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/TrustManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */