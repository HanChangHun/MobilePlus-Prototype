package android.hardware.fingerprint;

public abstract class RemovalCallback {
  public void onRemovalError(Fingerprint paramFingerprint, int paramInt, CharSequence paramCharSequence) {}
  
  public void onRemovalSucceeded(Fingerprint paramFingerprint, int paramInt) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/FingerprintManager$RemovalCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */