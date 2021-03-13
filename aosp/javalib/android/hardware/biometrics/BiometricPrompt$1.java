package android.hardware.biometrics;

import android.hardware.face.FaceManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.RemoteException;

class null extends IBiometricServiceReceiver.Stub {
  public void onAcquired(int paramInt, String paramString) throws RemoteException {
    BiometricPrompt.access$200(BiometricPrompt.this).execute(new _$$Lambda$BiometricPrompt$1$yfG83rs6eJM9CDMAlhftsvdKekY(this, paramInt, paramString));
  }
  
  public void onAuthenticationFailed() throws RemoteException {
    BiometricPrompt.access$200(BiometricPrompt.this).execute(new _$$Lambda$BiometricPrompt$1$AAMJr_dQQ3dkiYxppvTx2AjuvRQ(this));
  }
  
  public void onAuthenticationSucceeded(int paramInt) throws RemoteException {
    BiometricPrompt.access$200(BiometricPrompt.this).execute(new _$$Lambda$BiometricPrompt$1$12VeET6QSFQbES1tShxA0kvzReo(this, paramInt));
  }
  
  public void onDialogDismissed(int paramInt) throws RemoteException {
    if (paramInt == 1) {
      (BiometricPrompt.access$300(BiometricPrompt.this)).executor.execute(new _$$Lambda$BiometricPrompt$1$Kmc1otRcCm0Akw6_6yK5trqAv78(this));
    } else if (paramInt == 2) {
      (BiometricPrompt.access$400(BiometricPrompt.this)).executor.execute(new _$$Lambda$BiometricPrompt$1$G8c_A1luxVwjcfGpdSp4nNPnavM(this));
    } 
  }
  
  public void onError(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    BiometricPrompt.access$200(BiometricPrompt.this).execute(new _$$Lambda$BiometricPrompt$1$bOZpfrHjTYn0bHysdnjuVXTbZSk(this, paramInt1, paramInt2, paramInt3));
  }
  
  public void onSystemEvent(int paramInt) throws RemoteException {
    BiometricPrompt.access$200(BiometricPrompt.this).execute(new _$$Lambda$BiometricPrompt$1$xoRi9oElCVJ5QflEmWoJGQ08mZ8(this, paramInt));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricPrompt$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */