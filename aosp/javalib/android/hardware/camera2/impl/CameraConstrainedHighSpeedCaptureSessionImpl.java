package android.hardware.camera2.impl;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraConstrainedHighSpeedCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraOfflineSession;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.utils.SurfaceUtils;
import android.os.ConditionVariable;
import android.os.Handler;
import android.util.Range;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public class CameraConstrainedHighSpeedCaptureSessionImpl extends CameraConstrainedHighSpeedCaptureSession implements CameraCaptureSessionCore {
  private final CameraCharacteristics mCharacteristics;
  
  private final ConditionVariable mInitialized = new ConditionVariable();
  
  private final CameraCaptureSessionImpl mSessionImpl;
  
  CameraConstrainedHighSpeedCaptureSessionImpl(int paramInt, CameraCaptureSession.StateCallback paramStateCallback, Executor paramExecutor1, CameraDeviceImpl paramCameraDeviceImpl, Executor paramExecutor2, boolean paramBoolean, CameraCharacteristics paramCameraCharacteristics) {
    this.mCharacteristics = paramCameraCharacteristics;
    this.mSessionImpl = new CameraCaptureSessionImpl(paramInt, null, new WrapperCallback(paramStateCallback), paramExecutor1, paramCameraDeviceImpl, paramExecutor2, paramBoolean);
    this.mInitialized.open();
  }
  
  private boolean isConstrainedHighSpeedRequestList(List<CaptureRequest> paramList) {
    Preconditions.checkCollectionNotEmpty(paramList, "High speed request list");
    Iterator<CaptureRequest> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      if (!((CaptureRequest)iterator.next()).isPartOfCRequestList())
        return false; 
    } 
    return true;
  }
  
  public void abortCaptures() throws CameraAccessException {
    this.mSessionImpl.abortCaptures();
  }
  
  public int capture(CaptureRequest paramCaptureRequest, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    throw new UnsupportedOperationException("Constrained high speed session doesn't support this method");
  }
  
  public int captureBurst(List<CaptureRequest> paramList, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    if (isConstrainedHighSpeedRequestList(paramList))
      return this.mSessionImpl.captureBurst(paramList, paramCaptureCallback, paramHandler); 
    throw new IllegalArgumentException("Only request lists created by createHighSpeedRequestList() can be submitted to a constrained high speed capture session");
  }
  
  public int captureBurstRequests(List<CaptureRequest> paramList, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    if (isConstrainedHighSpeedRequestList(paramList))
      return this.mSessionImpl.captureBurstRequests(paramList, paramExecutor, paramCaptureCallback); 
    throw new IllegalArgumentException("Only request lists created by createHighSpeedRequestList() can be submitted to a constrained high speed capture session");
  }
  
  public int captureSingleRequest(CaptureRequest paramCaptureRequest, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Constrained high speed session doesn't support this method");
  }
  
  public void close() {
    this.mSessionImpl.close();
  }
  
  public void closeWithoutDraining() {
    throw new UnsupportedOperationException("Constrained high speed session doesn't support this method");
  }
  
  public List<CaptureRequest> createHighSpeedRequestList(CaptureRequest paramCaptureRequest) throws CameraAccessException {
    if (paramCaptureRequest != null) {
      CaptureRequest.Builder builder1;
      Collection collection = paramCaptureRequest.getTargets();
      Range range = (Range)paramCaptureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
      SurfaceUtils.checkConstrainedHighSpeedSurfaces(collection, range, (StreamConfigurationMap)this.mCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP));
      int i = ((Integer)range.getUpper()).intValue() / 30;
      ArrayList<CaptureRequest> arrayList = new ArrayList();
      CaptureRequest.Builder builder2 = new CaptureRequest.Builder(new CameraMetadataNative(paramCaptureRequest.getNativeCopy()), false, -1, paramCaptureRequest.getLogicalCameraId(), null);
      builder2.setTag(paramCaptureRequest.getTag());
      Iterator<Surface> iterator = collection.iterator();
      Surface surface = iterator.next();
      if (collection.size() == 1 && SurfaceUtils.isSurfaceForHwVideoEncoder(surface)) {
        builder2.set(CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(1));
      } else {
        builder2.set(CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(3));
      } 
      builder2.setPartOfCHSRequestList(true);
      CaptureRequest.Builder builder3 = null;
      if (collection.size() == 2) {
        builder3 = new CaptureRequest.Builder(new CameraMetadataNative(paramCaptureRequest.getNativeCopy()), false, -1, paramCaptureRequest.getLogicalCameraId(), null);
        builder3.setTag(paramCaptureRequest.getTag());
        builder3.set(CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(3));
        builder3.addTarget(surface);
        Surface surface2 = iterator.next();
        builder3.addTarget(surface2);
        builder3.setPartOfCHSRequestList(true);
        Surface surface1 = surface;
        if (!SurfaceUtils.isSurfaceForHwVideoEncoder(surface))
          surface1 = surface2; 
        builder2.addTarget(surface1);
        builder1 = builder3;
      } else {
        builder2.addTarget(surface);
        builder1 = builder3;
      } 
      for (byte b = 0; b < i; b++) {
        if (b == 0 && builder1 != null) {
          arrayList.add(builder1.build());
        } else {
          arrayList.add(builder2.build());
        } 
      } 
      return Collections.unmodifiableList(arrayList);
    } 
    throw new IllegalArgumentException("Input capture request must not be null");
  }
  
  public void finalizeOutputConfigurations(List<OutputConfiguration> paramList) throws CameraAccessException {
    this.mSessionImpl.finalizeOutputConfigurations(paramList);
  }
  
  public CameraDevice getDevice() {
    return this.mSessionImpl.getDevice();
  }
  
  public CameraDeviceImpl.StateCallbackKK getDeviceStateCallback() {
    return this.mSessionImpl.getDeviceStateCallback();
  }
  
  public Surface getInputSurface() {
    return null;
  }
  
  public boolean isAborting() {
    return this.mSessionImpl.isAborting();
  }
  
  public boolean isReprocessable() {
    return false;
  }
  
  public void prepare(int paramInt, Surface paramSurface) throws CameraAccessException {
    this.mSessionImpl.prepare(paramInt, paramSurface);
  }
  
  public void prepare(Surface paramSurface) throws CameraAccessException {
    this.mSessionImpl.prepare(paramSurface);
  }
  
  public void replaceSessionClose() {
    this.mSessionImpl.replaceSessionClose();
  }
  
  public int setRepeatingBurst(List<CaptureRequest> paramList, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    if (isConstrainedHighSpeedRequestList(paramList))
      return this.mSessionImpl.setRepeatingBurst(paramList, paramCaptureCallback, paramHandler); 
    throw new IllegalArgumentException("Only request lists created by createHighSpeedRequestList() can be submitted to a constrained high speed capture session");
  }
  
  public int setRepeatingBurstRequests(List<CaptureRequest> paramList, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    if (isConstrainedHighSpeedRequestList(paramList))
      return this.mSessionImpl.setRepeatingBurstRequests(paramList, paramExecutor, paramCaptureCallback); 
    throw new IllegalArgumentException("Only request lists created by createHighSpeedRequestList() can be submitted to a constrained high speed capture session");
  }
  
  public int setRepeatingRequest(CaptureRequest paramCaptureRequest, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    throw new UnsupportedOperationException("Constrained high speed session doesn't support this method");
  }
  
  public int setSingleRepeatingRequest(CaptureRequest paramCaptureRequest, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Constrained high speed session doesn't support this method");
  }
  
  public void stopRepeating() throws CameraAccessException {
    this.mSessionImpl.stopRepeating();
  }
  
  public boolean supportsOfflineProcessing(Surface paramSurface) {
    throw new UnsupportedOperationException("Constrained high speed session doesn't support offline mode");
  }
  
  public CameraOfflineSession switchToOffline(Collection<Surface> paramCollection, Executor paramExecutor, CameraOfflineSession.CameraOfflineSessionCallback paramCameraOfflineSessionCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Constrained high speed session doesn't support this method");
  }
  
  public void tearDown(Surface paramSurface) throws CameraAccessException {
    this.mSessionImpl.tearDown(paramSurface);
  }
  
  public void updateOutputConfiguration(OutputConfiguration paramOutputConfiguration) throws CameraAccessException {
    throw new UnsupportedOperationException("Constrained high speed session doesn't support this method");
  }
  
  private class WrapperCallback extends CameraCaptureSession.StateCallback {
    private final CameraCaptureSession.StateCallback mCallback;
    
    public WrapperCallback(CameraCaptureSession.StateCallback param1StateCallback) {
      this.mCallback = param1StateCallback;
    }
    
    public void onActive(CameraCaptureSession param1CameraCaptureSession) {
      this.mCallback.onActive((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
    }
    
    public void onCaptureQueueEmpty(CameraCaptureSession param1CameraCaptureSession) {
      this.mCallback.onCaptureQueueEmpty((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
    }
    
    public void onClosed(CameraCaptureSession param1CameraCaptureSession) {
      this.mCallback.onClosed((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
    }
    
    public void onConfigureFailed(CameraCaptureSession param1CameraCaptureSession) {
      CameraConstrainedHighSpeedCaptureSessionImpl.this.mInitialized.block();
      this.mCallback.onConfigureFailed((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
    }
    
    public void onConfigured(CameraCaptureSession param1CameraCaptureSession) {
      CameraConstrainedHighSpeedCaptureSessionImpl.this.mInitialized.block();
      this.mCallback.onConfigured((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
    }
    
    public void onReady(CameraCaptureSession param1CameraCaptureSession) {
      this.mCallback.onReady((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this);
    }
    
    public void onSurfacePrepared(CameraCaptureSession param1CameraCaptureSession, Surface param1Surface) {
      this.mCallback.onSurfacePrepared((CameraCaptureSession)CameraConstrainedHighSpeedCaptureSessionImpl.this, param1Surface);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraConstrainedHighSpeedCaptureSessionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */