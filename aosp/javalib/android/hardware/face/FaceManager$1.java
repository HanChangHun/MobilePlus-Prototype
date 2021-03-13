package android.hardware.face;

import com.android.internal.os.SomeArgs;

class null extends IFaceServiceReceiver.Stub {
  public void onAcquired(long paramLong, int paramInt1, int paramInt2) {
    FaceManager.access$000(FaceManager.this).obtainMessage(101, paramInt1, paramInt2, Long.valueOf(paramLong)).sendToTarget();
  }
  
  public void onAuthenticationFailed(long paramLong) {
    FaceManager.access$000(FaceManager.this).obtainMessage(103).sendToTarget();
  }
  
  public void onAuthenticationSucceeded(long paramLong, Face paramFace, int paramInt, boolean paramBoolean) {
    FaceManager.access$000(FaceManager.this).obtainMessage(102, paramInt, paramBoolean, paramFace).sendToTarget();
  }
  
  public void onEnrollResult(long paramLong, int paramInt1, int paramInt2) {
    FaceManager.access$000(FaceManager.this).obtainMessage(100, paramInt2, 0, new Face(null, paramInt1, paramLong)).sendToTarget();
  }
  
  public void onEnumerated(long paramLong, int paramInt1, int paramInt2) {}
  
  public void onError(long paramLong, int paramInt1, int paramInt2) {
    FaceManager.access$000(FaceManager.this).obtainMessage(104, paramInt1, paramInt2, Long.valueOf(paramLong)).sendToTarget();
  }
  
  public void onFeatureGet(boolean paramBoolean1, int paramInt, boolean paramBoolean2) {
    SomeArgs someArgs = SomeArgs.obtain();
    someArgs.arg1 = Boolean.valueOf(paramBoolean1);
    someArgs.argi1 = paramInt;
    someArgs.arg2 = Boolean.valueOf(paramBoolean2);
    FaceManager.access$000(FaceManager.this).obtainMessage(106, someArgs).sendToTarget();
  }
  
  public void onFeatureSet(boolean paramBoolean, int paramInt) {
    FaceManager.access$000(FaceManager.this).obtainMessage(107, paramInt, 0, Boolean.valueOf(paramBoolean)).sendToTarget();
  }
  
  public void onRemoved(long paramLong, int paramInt1, int paramInt2) {
    FaceManager.access$000(FaceManager.this).obtainMessage(105, paramInt2, 0, new Face(null, paramInt1, paramLong)).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/FaceManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */