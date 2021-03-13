package android.hardware.fingerprint;

import android.os.CancellationSignal;

class OnFingerprintDetectionCancelListener implements CancellationSignal.OnCancelListener {
  private OnFingerprintDetectionCancelListener() {}
  
  public void onCancel() {
    FingerprintManager.access$200(FingerprintManager.this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/FingerprintManager$OnFingerprintDetectionCancelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */