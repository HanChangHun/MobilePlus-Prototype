package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.util.Log;

class null implements Camera.ErrorCallback {
  public void onError(int paramInt, Camera paramCamera) {
    if (paramInt != 2) {
      if (paramInt != 3) {
        String str = RequestThreadManager.access$100(RequestThreadManager.this);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Received error ");
        stringBuilder.append(paramInt);
        stringBuilder.append(" from the Camera1 ErrorCallback");
        Log.e(str, stringBuilder.toString());
        RequestThreadManager.access$000(RequestThreadManager.this).setError(1);
      } else {
        RequestThreadManager.this.flush();
        RequestThreadManager.access$000(RequestThreadManager.this).setError(6);
      } 
    } else {
      RequestThreadManager.this.flush();
      RequestThreadManager.access$000(RequestThreadManager.this).setError(0);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestThreadManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */