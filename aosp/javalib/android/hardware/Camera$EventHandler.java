package android.hardware;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

class EventHandler extends Handler {
  private final Camera mCamera;
  
  public EventHandler(Camera paramCamera2, Looper paramLooper) {
    super(paramLooper);
    this.mCamera = paramCamera2;
  }
  
  public void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    boolean bool1 = true;
    boolean bool2 = true;
    boolean bool3 = true;
    if (i != 1) {
      if (i != 2) {
        if (i != 4) {
          if (i != 8) {
            if (i != 16) {
              if (i != 64) {
                if (i != 128) {
                  if (i != 256) {
                    if (i != 1024) {
                      if (i != 2048) {
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("Unknown message type ");
                        stringBuilder1.append(paramMessage.what);
                        Log.e("Camera", stringBuilder1.toString());
                        return;
                      } 
                      if (Camera.access$1400(Camera.this) != null) {
                        Camera.AutoFocusMoveCallback autoFocusMoveCallback = Camera.access$1400(Camera.this);
                        if (paramMessage.arg1 == 0)
                          bool3 = false; 
                        autoFocusMoveCallback.onAutoFocusMoving(bool3, this.mCamera);
                      } 
                      return;
                    } 
                    if (Camera.access$1100(Camera.this) != null)
                      Camera.access$1100(Camera.this).onFaceDetection((Camera.Face[])paramMessage.obj, this.mCamera); 
                    return;
                  } 
                  if (Camera.access$200(Camera.this) != null)
                    Camera.access$200(Camera.this).onPictureTaken((byte[])paramMessage.obj, this.mCamera); 
                  return;
                } 
                if (Camera.access$100(Camera.this) != null)
                  Camera.access$100(Camera.this).onPictureTaken((byte[])paramMessage.obj, this.mCamera); 
                return;
              } 
              if (Camera.access$700(Camera.this) != null)
                Camera.access$700(Camera.this).onPictureTaken((byte[])paramMessage.obj, this.mCamera); 
              return;
            } 
            Camera.PreviewCallback previewCallback = Camera.access$300(Camera.this);
            if (previewCallback != null) {
              if (Camera.access$400(Camera.this)) {
                Camera.access$302(Camera.this, null);
              } else if (!Camera.access$500(Camera.this)) {
                Camera.access$600(Camera.this, true, false);
              } 
              previewCallback.onPreviewFrame((byte[])paramMessage.obj, this.mCamera);
            } 
            return;
          } 
          if (Camera.access$1000(Camera.this) != null) {
            Camera.OnZoomChangeListener onZoomChangeListener = Camera.access$1000(Camera.this);
            i = paramMessage.arg1;
            if (paramMessage.arg2 != 0) {
              bool3 = bool1;
            } else {
              bool3 = false;
            } 
            onZoomChangeListener.onZoomChange(i, bool3, this.mCamera);
          } 
          return;
        } 
        synchronized (Camera.access$800(Camera.this)) {
          Camera.AutoFocusCallback autoFocusCallback = Camera.access$900(Camera.this);
          if (autoFocusCallback != null) {
            bool3 = bool2;
            if (paramMessage.arg1 == 0)
              bool3 = false; 
            autoFocusCallback.onAutoFocus(bool3, this.mCamera);
          } 
          return;
        } 
      } 
      if (Camera.access$000(Camera.this) != null)
        Camera.access$000(Camera.this).onShutter(); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error ");
    stringBuilder.append(paramMessage.arg1);
    Log.e("Camera", stringBuilder.toString());
    if (Camera.access$1200(Camera.this) != null) {
      Camera.access$1200(Camera.this).onError(paramMessage.arg1, this.mCamera);
    } else if (Camera.access$1300(Camera.this) != null) {
      if (paramMessage.arg1 == 3) {
        Camera.access$1300(Camera.this).onError(2, this.mCamera);
      } else {
        Camera.access$1300(Camera.this).onError(paramMessage.arg1, this.mCamera);
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/Camera$EventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */