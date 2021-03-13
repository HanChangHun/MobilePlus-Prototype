package android.hardware.camera2.legacy;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.internal.util.Preconditions;

class null implements Handler.Callback {
  private boolean mCleanup = false;
  
  private boolean mConfigured = false;
  
  private boolean mDroppingFrames = false;
  
  public boolean handleMessage(Message paramMessage) {
    if (this.mCleanup)
      return true; 
    try {
      int i = paramMessage.what;
      if (i != -1)
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              if (i != 4) {
                if (i != 5) {
                  String str = GLThreadManager.access$200(GLThreadManager.this);
                  StringBuilder stringBuilder = new StringBuilder();
                  this();
                  stringBuilder.append("Unhandled message ");
                  stringBuilder.append(paramMessage.what);
                  stringBuilder.append(" on GLThread.");
                  Log.e(str, stringBuilder.toString());
                } else {
                  this.mDroppingFrames = false;
                } 
              } else {
                this.mDroppingFrames = true;
              } 
            } else {
              GLThreadManager.access$000(GLThreadManager.this).cleanupEGLContext();
              this.mCleanup = true;
              this.mConfigured = false;
            } 
          } else if (this.mDroppingFrames) {
            Log.w(GLThreadManager.access$200(GLThreadManager.this), "Ignoring frame.");
          } else {
            if (!this.mConfigured)
              Log.e(GLThreadManager.access$200(GLThreadManager.this), "Dropping frame, EGL context not configured!"); 
            GLThreadManager.access$000(GLThreadManager.this).drawIntoSurfaces(GLThreadManager.access$100(GLThreadManager.this));
          } 
        } else {
          GLThreadManager.ConfigureHolder configureHolder = (GLThreadManager.ConfigureHolder)paramMessage.obj;
          GLThreadManager.access$000(GLThreadManager.this).cleanupEGLContext();
          GLThreadManager.access$000(GLThreadManager.this).configureSurfaces(configureHolder.surfaces);
          GLThreadManager.access$102(GLThreadManager.this, (CaptureCollector)Preconditions.checkNotNull(configureHolder.collector));
          configureHolder.condition.open();
          this.mConfigured = true;
        }  
    } catch (Exception exception) {
      Log.e(GLThreadManager.access$200(GLThreadManager.this), "Received exception on GL render thread: ", exception);
      GLThreadManager.access$300(GLThreadManager.this).setError(1);
    } 
    return true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/GLThreadManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */