package android.hardware.camera2;

import android.hardware.camera2.params.OutputConfiguration;
import android.os.Handler;
import android.view.Surface;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

public abstract class CameraCaptureSession implements AutoCloseable {
  public static final int SESSION_ID_NONE = -1;
  
  public abstract void abortCaptures() throws CameraAccessException;
  
  public abstract int capture(CaptureRequest paramCaptureRequest, CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException;
  
  public abstract int captureBurst(List<CaptureRequest> paramList, CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException;
  
  public int captureBurstRequests(List<CaptureRequest> paramList, Executor paramExecutor, CaptureCallback paramCaptureCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Subclasses must override this method");
  }
  
  public int captureSingleRequest(CaptureRequest paramCaptureRequest, Executor paramExecutor, CaptureCallback paramCaptureCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Subclasses must override this method");
  }
  
  public abstract void close();
  
  public abstract void finalizeOutputConfigurations(List<OutputConfiguration> paramList) throws CameraAccessException;
  
  public abstract CameraDevice getDevice();
  
  public abstract Surface getInputSurface();
  
  public abstract boolean isReprocessable();
  
  public abstract void prepare(int paramInt, Surface paramSurface) throws CameraAccessException;
  
  public abstract void prepare(Surface paramSurface) throws CameraAccessException;
  
  public abstract int setRepeatingBurst(List<CaptureRequest> paramList, CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException;
  
  public int setRepeatingBurstRequests(List<CaptureRequest> paramList, Executor paramExecutor, CaptureCallback paramCaptureCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Subclasses must override this method");
  }
  
  public abstract int setRepeatingRequest(CaptureRequest paramCaptureRequest, CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException;
  
  public int setSingleRepeatingRequest(CaptureRequest paramCaptureRequest, Executor paramExecutor, CaptureCallback paramCaptureCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Subclasses must override this method");
  }
  
  public abstract void stopRepeating() throws CameraAccessException;
  
  public boolean supportsOfflineProcessing(Surface paramSurface) {
    throw new UnsupportedOperationException("Subclasses must override this method");
  }
  
  public CameraOfflineSession switchToOffline(Collection<Surface> paramCollection, Executor paramExecutor, CameraOfflineSession.CameraOfflineSessionCallback paramCameraOfflineSessionCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Subclasses must override this method");
  }
  
  public abstract void tearDown(Surface paramSurface) throws CameraAccessException;
  
  public void updateOutputConfiguration(OutputConfiguration paramOutputConfiguration) throws CameraAccessException {
    throw new UnsupportedOperationException("Subclasses must override this method");
  }
  
  public static abstract class CaptureCallback {
    public static final int NO_FRAMES_CAPTURED = -1;
    
    public void onCaptureBufferLost(CameraCaptureSession param1CameraCaptureSession, CaptureRequest param1CaptureRequest, Surface param1Surface, long param1Long) {}
    
    public void onCaptureCompleted(CameraCaptureSession param1CameraCaptureSession, CaptureRequest param1CaptureRequest, TotalCaptureResult param1TotalCaptureResult) {}
    
    public void onCaptureFailed(CameraCaptureSession param1CameraCaptureSession, CaptureRequest param1CaptureRequest, CaptureFailure param1CaptureFailure) {}
    
    public void onCapturePartial(CameraCaptureSession param1CameraCaptureSession, CaptureRequest param1CaptureRequest, CaptureResult param1CaptureResult) {}
    
    public void onCaptureProgressed(CameraCaptureSession param1CameraCaptureSession, CaptureRequest param1CaptureRequest, CaptureResult param1CaptureResult) {}
    
    public void onCaptureSequenceAborted(CameraCaptureSession param1CameraCaptureSession, int param1Int) {}
    
    public void onCaptureSequenceCompleted(CameraCaptureSession param1CameraCaptureSession, int param1Int, long param1Long) {}
    
    public void onCaptureStarted(CameraCaptureSession param1CameraCaptureSession, CaptureRequest param1CaptureRequest, long param1Long1, long param1Long2) {}
  }
  
  public static abstract class StateCallback {
    public void onActive(CameraCaptureSession param1CameraCaptureSession) {}
    
    public void onCaptureQueueEmpty(CameraCaptureSession param1CameraCaptureSession) {}
    
    public void onClosed(CameraCaptureSession param1CameraCaptureSession) {}
    
    public abstract void onConfigureFailed(CameraCaptureSession param1CameraCaptureSession);
    
    public abstract void onConfigured(CameraCaptureSession param1CameraCaptureSession);
    
    public void onReady(CameraCaptureSession param1CameraCaptureSession) {}
    
    public void onSurfacePrepared(CameraCaptureSession param1CameraCaptureSession, Surface param1Surface) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraCaptureSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */