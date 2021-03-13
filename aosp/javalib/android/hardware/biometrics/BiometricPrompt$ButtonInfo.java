package android.hardware.biometrics;

import android.content.DialogInterface;
import java.util.concurrent.Executor;

class ButtonInfo {
  Executor executor;
  
  DialogInterface.OnClickListener listener;
  
  ButtonInfo(Executor paramExecutor, DialogInterface.OnClickListener paramOnClickListener) {
    this.executor = paramExecutor;
    this.listener = paramOnClickListener;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricPrompt$ButtonInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */