package android.hardware.fingerprint;

class null extends IFingerprintServiceReceiver.Stub {
  public void onAcquired(long paramLong, int paramInt1, int paramInt2) {
    FingerprintManager.access$600(FingerprintManager.this).obtainMessage(101, paramInt1, paramInt2, Long.valueOf(paramLong)).sendToTarget();
  }
  
  public void onAuthenticationFailed(long paramLong) {
    FingerprintManager.access$600(FingerprintManager.this).obtainMessage(103).sendToTarget();
  }
  
  public void onAuthenticationSucceeded(long paramLong, Fingerprint paramFingerprint, int paramInt, boolean paramBoolean) {
    FingerprintManager.access$600(FingerprintManager.this).obtainMessage(102, paramInt, paramBoolean, paramFingerprint).sendToTarget();
  }
  
  public void onEnrollResult(long paramLong, int paramInt1, int paramInt2, int paramInt3) {
    FingerprintManager.access$600(FingerprintManager.this).obtainMessage(100, paramInt3, 0, new Fingerprint(null, paramInt2, paramInt1, paramLong)).sendToTarget();
  }
  
  public void onEnumerated(long paramLong, int paramInt1, int paramInt2, int paramInt3) {
    FingerprintManager.access$600(FingerprintManager.this).obtainMessage(106, paramInt1, paramInt2, Long.valueOf(paramLong)).sendToTarget();
  }
  
  public void onError(long paramLong, int paramInt1, int paramInt2) {
    FingerprintManager.access$600(FingerprintManager.this).obtainMessage(104, paramInt1, paramInt2, Long.valueOf(paramLong)).sendToTarget();
  }
  
  public void onFingerprintDetected(long paramLong, int paramInt, boolean paramBoolean) {
    FingerprintManager.access$600(FingerprintManager.this).obtainMessage(107, paramInt, 0, Boolean.valueOf(paramBoolean)).sendToTarget();
  }
  
  public void onRemoved(long paramLong, int paramInt1, int paramInt2, int paramInt3) {
    FingerprintManager.access$600(FingerprintManager.this).obtainMessage(105, paramInt3, 0, new Fingerprint(null, paramInt2, paramInt1, paramLong)).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/FingerprintManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */