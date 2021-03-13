package android.hardware.face;

import android.hardware.biometrics.CryptoObject;
import android.os.CancellationSignal;

class OnAuthenticationCancelListener implements CancellationSignal.OnCancelListener {
  private CryptoObject mCrypto;
  
  OnAuthenticationCancelListener(CryptoObject paramCryptoObject) {
    this.mCrypto = paramCryptoObject;
  }
  
  public void onCancel() {
    FaceManager.access$500(FaceManager.this, this.mCrypto);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/FaceManager$OnAuthenticationCancelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */