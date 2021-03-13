package android.hardware.fingerprint;

import android.os.CancellationSignal;

class OnEnrollCancelListener implements CancellationSignal.OnCancelListener {
  private OnEnrollCancelListener() {}
  
  public void onCancel() {
    FingerprintManager.access$000(FingerprintManager.this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/FingerprintManager$OnEnrollCancelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */