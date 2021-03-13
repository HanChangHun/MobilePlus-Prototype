package android.hardware.camera2.legacy;

import android.graphics.SurfaceTexture;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.Collection;

public class GLThreadManager {
  private static final boolean DEBUG = false;
  
  private static final int MSG_ALLOW_FRAMES = 5;
  
  private static final int MSG_CLEANUP = 3;
  
  private static final int MSG_DROP_FRAMES = 4;
  
  private static final int MSG_NEW_CONFIGURATION = 1;
  
  private static final int MSG_NEW_FRAME = 2;
  
  private final String TAG;
  
  private CaptureCollector mCaptureCollector;
  
  private final CameraDeviceState mDeviceState;
  
  private final Handler.Callback mGLHandlerCb = new Handler.Callback() {
      private boolean mCleanup = false;
      
      private boolean mConfigured = false;
      
      private boolean mDroppingFrames = false;
      
      public boolean handleMessage(Message param1Message) {
        if (this.mCleanup)
          return true; 
        try {
          int i = param1Message.what;
          if (i != -1)
            if (i != 1) {
              if (i != 2) {
                if (i != 3) {
                  if (i != 4) {
                    if (i != 5) {
                      String str = GLThreadManager.this.TAG;
                      StringBuilder stringBuilder = new StringBuilder();
                      this();
                      stringBuilder.append("Unhandled message ");
                      stringBuilder.append(param1Message.what);
                      stringBuilder.append(" on GLThread.");
                      Log.e(str, stringBuilder.toString());
                    } else {
                      this.mDroppingFrames = false;
                    } 
                  } else {
                    this.mDroppingFrames = true;
                  } 
                } else {
                  GLThreadManager.this.mTextureRenderer.cleanupEGLContext();
                  this.mCleanup = true;
                  this.mConfigured = false;
                } 
              } else if (this.mDroppingFrames) {
                Log.w(GLThreadManager.this.TAG, "Ignoring frame.");
              } else {
                if (!this.mConfigured)
                  Log.e(GLThreadManager.this.TAG, "Dropping frame, EGL context not configured!"); 
                GLThreadManager.this.mTextureRenderer.drawIntoSurfaces(GLThreadManager.this.mCaptureCollector);
              } 
            } else {
              GLThreadManager.ConfigureHolder configureHolder = (GLThreadManager.ConfigureHolder)param1Message.obj;
              GLThreadManager.this.mTextureRenderer.cleanupEGLContext();
              GLThreadManager.this.mTextureRenderer.configureSurfaces(configureHolder.surfaces);
              GLThreadManager.access$102(GLThreadManager.this, (CaptureCollector)Preconditions.checkNotNull(configureHolder.collector));
              configureHolder.condition.open();
              this.mConfigured = true;
            }  
        } catch (Exception exception) {
          Log.e(GLThreadManager.this.TAG, "Received exception on GL render thread: ", exception);
          GLThreadManager.this.mDeviceState.setError(1);
        } 
        return true;
      }
    };
  
  private final RequestHandlerThread mGLHandlerThread;
  
  private final RequestThreadManager.FpsCounter mPrevCounter = new RequestThreadManager.FpsCounter("GL Preview Producer");
  
  private final SurfaceTextureRenderer mTextureRenderer;
  
  public GLThreadManager(int paramInt1, int paramInt2, CameraDeviceState paramCameraDeviceState) {
    this.mTextureRenderer = new SurfaceTextureRenderer(paramInt2);
    this.TAG = String.format("CameraDeviceGLThread-%d", new Object[] { Integer.valueOf(paramInt1) });
    this.mGLHandlerThread = new RequestHandlerThread(this.TAG, this.mGLHandlerCb);
    this.mDeviceState = paramCameraDeviceState;
  }
  
  public void allowNewFrames() {
    this.mGLHandlerThread.getHandler().sendEmptyMessage(5);
  }
  
  public SurfaceTexture getCurrentSurfaceTexture() {
    return this.mTextureRenderer.getSurfaceTexture();
  }
  
  public void ignoreNewFrames() {
    this.mGLHandlerThread.getHandler().sendEmptyMessage(4);
  }
  
  public void queueNewFrame() {
    Handler handler = this.mGLHandlerThread.getHandler();
    if (!handler.hasMessages(2)) {
      handler.sendMessage(handler.obtainMessage(2));
    } else {
      Log.e(this.TAG, "GLThread dropping frame.  Not consuming frames quickly enough!");
    } 
  }
  
  public void quit() {
    Handler handler = this.mGLHandlerThread.getHandler();
    handler.sendMessageAtFrontOfQueue(handler.obtainMessage(3));
    this.mGLHandlerThread.quitSafely();
    try {
      this.mGLHandlerThread.join();
    } catch (InterruptedException interruptedException) {
      Log.e(this.TAG, String.format("Thread %s (%d) interrupted while quitting.", new Object[] { this.mGLHandlerThread.getName(), Long.valueOf(this.mGLHandlerThread.getId()) }));
    } 
  }
  
  public void setConfigurationAndWait(Collection<Pair<Surface, Size>> paramCollection, CaptureCollector paramCaptureCollector) {
    Preconditions.checkNotNull(paramCaptureCollector, "collector must not be null");
    Handler handler = this.mGLHandlerThread.getHandler();
    ConditionVariable conditionVariable = new ConditionVariable(false);
    handler.sendMessage(handler.obtainMessage(1, 0, 0, new ConfigureHolder(conditionVariable, paramCollection, paramCaptureCollector)));
    conditionVariable.block();
  }
  
  public void start() {
    this.mGLHandlerThread.start();
  }
  
  public void waitUntilIdle() {
    this.mGLHandlerThread.waitUntilIdle();
  }
  
  public void waitUntilStarted() {
    this.mGLHandlerThread.waitUntilStarted();
  }
  
  private static class ConfigureHolder {
    public final CaptureCollector collector;
    
    public final ConditionVariable condition;
    
    public final Collection<Pair<Surface, Size>> surfaces;
    
    public ConfigureHolder(ConditionVariable param1ConditionVariable, Collection<Pair<Surface, Size>> param1Collection, CaptureCollector param1CaptureCollector) {
      this.condition = param1ConditionVariable;
      this.surfaces = param1Collection;
      this.collector = param1CaptureCollector;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/GLThreadManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */