package android.hardware.face;

import android.hardware.biometrics.CryptoObject;

public class AuthenticationResult {
  private CryptoObject mCryptoObject;
  
  private Face mFace;
  
  private boolean mIsStrongBiometric;
  
  private int mUserId;
  
  public AuthenticationResult(CryptoObject paramCryptoObject, Face paramFace, int paramInt, boolean paramBoolean) {
    this.mCryptoObject = paramCryptoObject;
    this.mFace = paramFace;
    this.mUserId = paramInt;
    this.mIsStrongBiometric = paramBoolean;
  }
  
  public CryptoObject getCryptoObject() {
    return this.mCryptoObject;
  }
  
  public Face getFace() {
    return this.mFace;
  }
  
  public int getUserId() {
    return this.mUserId;
  }
  
  public boolean isStrongBiometric() {
    return this.mIsStrongBiometric;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/FaceManager$AuthenticationResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */