package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;

class null implements Camera.PictureCallback {
  public void onPictureTaken(byte[] paramArrayOfbyte, Camera paramCamera) {
    Log.i(RequestThreadManager.access$100(RequestThreadManager.this), "Received jpeg.");
    Pair<RequestHolder, Long> pair = RequestThreadManager.access$200(RequestThreadManager.this).jpegProduced();
    if (pair == null || pair.first == null) {
      Log.e(RequestThreadManager.access$100(RequestThreadManager.this), "Dropping jpeg frame.");
      return;
    } 
    RequestHolder requestHolder = (RequestHolder)pair.first;
    long l = ((Long)pair.second).longValue();
    for (Surface surface : requestHolder.getHolderTargets()) {
      try {
        if (LegacyCameraDevice.containsSurfaceId(surface, RequestThreadManager.access$300(RequestThreadManager.this))) {
          Log.i(RequestThreadManager.access$100(RequestThreadManager.this), "Producing jpeg buffer...");
          int i = paramArrayOfbyte.length;
          int j = LegacyCameraDevice.nativeGetJpegFooterSize();
          LegacyCameraDevice.setNextTimestamp(surface, l);
          LegacyCameraDevice.setSurfaceFormat(surface, 1);
          i = (int)Math.ceil(Math.sqrt((i + j + 3 & 0xFFFFFFFC))) + 15 & 0xFFFFFFF0;
          LegacyCameraDevice.setSurfaceDimens(surface, i, i);
          LegacyCameraDevice.produceFrame(surface, paramArrayOfbyte, i, i, 33);
        } 
      } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
        Log.w(RequestThreadManager.access$100(RequestThreadManager.this), "Surface abandoned, dropping frame. ", (Throwable)bufferQueueAbandonedException);
      } 
    } 
    RequestThreadManager.access$400(RequestThreadManager.this).open();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestThreadManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */