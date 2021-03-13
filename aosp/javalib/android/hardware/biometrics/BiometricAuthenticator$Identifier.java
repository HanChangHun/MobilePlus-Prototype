package android.hardware.biometrics;

import android.os.Parcelable;

public abstract class Identifier implements Parcelable {
  private int mBiometricId;
  
  private long mDeviceId;
  
  private CharSequence mName;
  
  public Identifier() {}
  
  public Identifier(CharSequence paramCharSequence, int paramInt, long paramLong) {
    this.mName = paramCharSequence;
    this.mBiometricId = paramInt;
    this.mDeviceId = paramLong;
  }
  
  public int getBiometricId() {
    return this.mBiometricId;
  }
  
  public long getDeviceId() {
    return this.mDeviceId;
  }
  
  public CharSequence getName() {
    return this.mName;
  }
  
  public void setDeviceId(long paramLong) {
    this.mDeviceId = paramLong;
  }
  
  public void setName(CharSequence paramCharSequence) {
    this.mName = paramCharSequence;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricAuthenticator$Identifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */