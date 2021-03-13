package android.hardware.biometrics;

import android.os.CancellationSignal;

class OnAuthenticationCancelListener implements CancellationSignal.OnCancelListener {
  private OnAuthenticationCancelListener() {}
  
  public void onCancel() {
    BiometricPrompt.access$100(BiometricPrompt.this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricPrompt$OnAuthenticationCancelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */