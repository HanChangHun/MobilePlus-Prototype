package android.hardware.fingerprint;

public abstract class EnrollmentCallback {
  public void onEnrollmentError(int paramInt, CharSequence paramCharSequence) {}
  
  public void onEnrollmentHelp(int paramInt, CharSequence paramCharSequence) {}
  
  public void onEnrollmentProgress(int paramInt) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/FingerprintManager$EnrollmentCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */