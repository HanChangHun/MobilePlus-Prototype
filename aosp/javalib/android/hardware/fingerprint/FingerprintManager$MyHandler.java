package android.hardware.fingerprint;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class MyHandler extends Handler {
  private MyHandler(Context paramContext) {
    super(paramContext.getMainLooper());
  }
  
  private MyHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    FingerprintManager fingerprintManager;
    Fingerprint fingerprint;
    int i;
    int j;
    boolean bool;
    switch (paramMessage.what) {
      default:
        return;
      case 107:
        FingerprintManager.access$1400(FingerprintManager.this, paramMessage.arg1, ((Boolean)paramMessage.obj).booleanValue());
      case 106:
        FingerprintManager.access$1300(FingerprintManager.this, ((Long)paramMessage.obj).longValue(), paramMessage.arg1, paramMessage.arg2);
      case 105:
        FingerprintManager.access$1200(FingerprintManager.this, (Fingerprint)paramMessage.obj, paramMessage.arg1);
      case 104:
        FingerprintManager.access$1100(FingerprintManager.this, ((Long)paramMessage.obj).longValue(), paramMessage.arg1, paramMessage.arg2);
      case 103:
        FingerprintManager.access$1000(FingerprintManager.this);
      case 102:
        fingerprintManager = FingerprintManager.this;
        fingerprint = (Fingerprint)paramMessage.obj;
        i = paramMessage.arg1;
        j = paramMessage.arg2;
        bool = true;
        if (j != 1)
          bool = false; 
        FingerprintManager.access$900(fingerprintManager, fingerprint, i, bool);
      case 101:
        FingerprintManager.access$800(FingerprintManager.this, ((Long)paramMessage.obj).longValue(), paramMessage.arg1, paramMessage.arg2);
      case 100:
        break;
    } 
    FingerprintManager.access$700(FingerprintManager.this, (Fingerprint)paramMessage.obj, paramMessage.arg1);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/FingerprintManager$MyHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */