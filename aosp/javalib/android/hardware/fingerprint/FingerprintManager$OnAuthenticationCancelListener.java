package android.hardware.fingerprint;

import android.hardware.biometrics.CryptoObject;
import android.os.CancellationSignal;

class OnAuthenticationCancelListener implements CancellationSignal.OnCancelListener {
  private CryptoObject mCrypto;
  
  public OnAuthenticationCancelListener(CryptoObject paramCryptoObject) {
    this.mCrypto = paramCryptoObject;
  }
  
  public void onCancel() {
    FingerprintManager.access$100(FingerprintManager.this, this.mCrypto);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/FingerprintManager$OnAuthenticationCancelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */