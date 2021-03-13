package android.hardware.face;

import android.os.CancellationSignal;

class OnEnrollCancelListener implements CancellationSignal.OnCancelListener {
  private OnEnrollCancelListener() {}
  
  public void onCancel() {
    FaceManager.access$400(FaceManager.this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/FaceManager$OnEnrollCancelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */