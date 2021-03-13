package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.util.Log;

class null implements Camera.FaceDetectionListener {
  public void onFaceDetection(Camera.Face[] paramArrayOfFace, Camera paramCamera) {
    int i;
    if (paramArrayOfFace == null) {
      i = 0;
    } else {
      i = paramArrayOfFace.length;
    } 
    synchronized (LegacyFaceDetectMapper.access$000(LegacyFaceDetectMapper.this)) {
      if (LegacyFaceDetectMapper.access$100(LegacyFaceDetectMapper.this)) {
        LegacyFaceDetectMapper.access$202(LegacyFaceDetectMapper.this, paramArrayOfFace);
      } else if (i > 0) {
        Log.d(LegacyFaceDetectMapper.access$300(), "onFaceDetection - Ignored some incoming faces sinceface detection was disabled");
      } 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyFaceDetectMapper$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */