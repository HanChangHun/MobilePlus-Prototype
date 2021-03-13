package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraDeviceUser;
import android.hardware.camera2.ICameraOfflineSession;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.impl.PhysicalCaptureResultInfo;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.system.OsConstants;
import android.util.Log;
import android.util.Size;
import android.util.SparseArray;
import android.view.Surface;
import java.util.List;

public class CameraDeviceUserShim implements ICameraDeviceUser {
  private static final boolean DEBUG = false;
  
  private static final int OPEN_CAMERA_TIMEOUT_MS = 5000;
  
  private static final String TAG = "CameraDeviceUserShim";
  
  private final CameraCallbackThread mCameraCallbacks;
  
  private final CameraCharacteristics mCameraCharacteristics;
  
  private final CameraLooper mCameraInit;
  
  private final Object mConfigureLock = new Object();
  
  private boolean mConfiguring;
  
  private final LegacyCameraDevice mLegacyDevice;
  
  private int mSurfaceIdCounter;
  
  private final SparseArray<Surface> mSurfaces;
  
  protected CameraDeviceUserShim(int paramInt, LegacyCameraDevice paramLegacyCameraDevice, CameraCharacteristics paramCameraCharacteristics, CameraLooper paramCameraLooper, CameraCallbackThread paramCameraCallbackThread) {
    this.mLegacyDevice = paramLegacyCameraDevice;
    this.mConfiguring = false;
    this.mSurfaces = new SparseArray();
    this.mCameraCharacteristics = paramCameraCharacteristics;
    this.mCameraInit = paramCameraLooper;
    this.mCameraCallbacks = paramCameraCallbackThread;
    this.mSurfaceIdCounter = 0;
  }
  
  public static CameraDeviceUserShim connectBinderShim(ICameraDeviceCallbacks paramICameraDeviceCallbacks, int paramInt, Size paramSize) {
    CameraLooper cameraLooper = new CameraLooper(paramInt);
    CameraCallbackThread cameraCallbackThread = new CameraCallbackThread(paramICameraDeviceCallbacks);
    int i = cameraLooper.waitForOpen(5000);
    Camera camera = cameraLooper.getCamera();
    LegacyExceptionUtils.throwOnServiceError(i);
    camera.disableShutterSound();
    Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
    Camera.getCameraInfo(paramInt, cameraInfo);
    try {
      Camera.Parameters parameters = camera.getParameters();
      CameraCharacteristics cameraCharacteristics = LegacyMetadataMapper.createCharacteristics(parameters, cameraInfo, paramInt, paramSize);
      return new CameraDeviceUserShim(paramInt, new LegacyCameraDevice(paramInt, camera, cameraCharacteristics, cameraCallbackThread), cameraCharacteristics, cameraLooper, cameraCallbackThread);
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to get initial parameters: ");
      stringBuilder.append(runtimeException.getMessage());
      throw new ServiceSpecificException(10, stringBuilder.toString());
    } 
  }
  
  private static int translateErrorsFromCamera1(int paramInt) {
    return (paramInt == -OsConstants.EACCES) ? 1 : paramInt;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public void beginConfigure() {
    if (!this.mLegacyDevice.isClosed())
      synchronized (this.mConfigureLock) {
        if (!this.mConfiguring) {
          this.mConfiguring = true;
          return;
        } 
        Log.e("CameraDeviceUserShim", "Cannot begin configure, configuration change already in progress.");
        ServiceSpecificException serviceSpecificException = new ServiceSpecificException();
        this(10, "Cannot begin configure, configuration change already in progress.");
        throw serviceSpecificException;
      }  
    Log.e("CameraDeviceUserShim", "Cannot begin configure, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot begin configure, device has been closed.");
  }
  
  public long cancelRequest(int paramInt) {
    if (!this.mLegacyDevice.isClosed())
      synchronized (this.mConfigureLock) {
        if (!this.mConfiguring)
          return this.mLegacyDevice.cancelRequest(paramInt); 
        Log.e("CameraDeviceUserShim", "Cannot cancel request, configuration change in progress.");
        ServiceSpecificException serviceSpecificException = new ServiceSpecificException();
        this(10, "Cannot cancel request, configuration change in progress.");
        throw serviceSpecificException;
      }  
    Log.e("CameraDeviceUserShim", "Cannot cancel request, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot cancel request, device has been closed.");
  }
  
  public CameraMetadataNative createDefaultRequest(int paramInt) {
    if (!this.mLegacyDevice.isClosed())
      try {
        return LegacyMetadataMapper.createRequestTemplate(this.mCameraCharacteristics, paramInt);
      } catch (IllegalArgumentException illegalArgumentException) {
        Log.e("CameraDeviceUserShim", "createDefaultRequest - invalid templateId specified");
        throw new ServiceSpecificException(3, "createDefaultRequest - invalid templateId specified");
      }  
    Log.e("CameraDeviceUserShim", "Cannot create default request, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot create default request, device has been closed.");
  }
  
  public int createInputStream(int paramInt1, int paramInt2, int paramInt3) {
    Log.e("CameraDeviceUserShim", "Creating input stream is not supported on legacy devices");
    throw new ServiceSpecificException(10, "Creating input stream is not supported on legacy devices");
  }
  
  public int createStream(OutputConfiguration paramOutputConfiguration) {
    if (!this.mLegacyDevice.isClosed())
      synchronized (this.mConfigureLock) {
        if (this.mConfiguring) {
          if (paramOutputConfiguration.getRotation() == 0) {
            int i = this.mSurfaceIdCounter + 1;
            this.mSurfaceIdCounter = i;
            this.mSurfaces.put(i, paramOutputConfiguration.getSurface());
            return i;
          } 
          Log.e("CameraDeviceUserShim", "Cannot create stream, stream rotation is not supported.");
          ServiceSpecificException serviceSpecificException1 = new ServiceSpecificException();
          this(3, "Cannot create stream, stream rotation is not supported.");
          throw serviceSpecificException1;
        } 
        Log.e("CameraDeviceUserShim", "Cannot create stream, beginConfigure hasn't been called yet.");
        ServiceSpecificException serviceSpecificException = new ServiceSpecificException();
        this(10, "Cannot create stream, beginConfigure hasn't been called yet.");
        throw serviceSpecificException;
      }  
    Log.e("CameraDeviceUserShim", "Cannot create stream, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot create stream, device has been closed.");
  }
  
  public void deleteStream(int paramInt) {
    if (!this.mLegacyDevice.isClosed())
      synchronized (this.mConfigureLock) {
        if (this.mConfiguring) {
          int i = this.mSurfaces.indexOfKey(paramInt);
          if (i >= 0) {
            this.mSurfaces.removeAt(i);
            return;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Cannot delete stream, stream id ");
          stringBuilder.append(paramInt);
          stringBuilder.append(" doesn't exist.");
          String str = stringBuilder.toString();
          Log.e("CameraDeviceUserShim", str);
          ServiceSpecificException serviceSpecificException1 = new ServiceSpecificException();
          this(3, str);
          throw serviceSpecificException1;
        } 
        Log.e("CameraDeviceUserShim", "Cannot delete stream, no configuration change in progress.");
        ServiceSpecificException serviceSpecificException = new ServiceSpecificException();
        this(10, "Cannot delete stream, no configuration change in progress.");
        throw serviceSpecificException;
      }  
    Log.e("CameraDeviceUserShim", "Cannot delete stream, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot delete stream, device has been closed.");
  }
  
  public void disconnect() {
    if (this.mLegacyDevice.isClosed())
      Log.w("CameraDeviceUserShim", "Cannot disconnect, device has already been closed."); 
    try {
      this.mLegacyDevice.close();
      return;
    } finally {
      this.mCameraInit.close();
      this.mCameraCallbacks.close();
    } 
  }
  
  public int[] endConfigure(int paramInt, CameraMetadataNative paramCameraMetadataNative) {
    if (!this.mLegacyDevice.isClosed()) {
      if (paramInt == 0) {
        paramCameraMetadataNative = null;
        synchronized (this.mConfigureLock) {
          if (this.mConfiguring) {
            SparseArray<Surface> sparseArray;
            if (this.mSurfaces != null)
              sparseArray = this.mSurfaces.clone(); 
            this.mConfiguring = false;
            this.mLegacyDevice.configureOutputs(sparseArray);
            return new int[0];
          } 
          Log.e("CameraDeviceUserShim", "Cannot end configure, no configuration change in progress.");
          ServiceSpecificException serviceSpecificException = new ServiceSpecificException();
          this(10, "Cannot end configure, no configuration change in progress.");
          throw serviceSpecificException;
        } 
      } 
      Log.e("CameraDeviceUserShim", "LEGACY devices do not support this operating mode");
      synchronized (this.mConfigureLock) {
        this.mConfiguring = false;
        throw new ServiceSpecificException(3, "LEGACY devices do not support this operating mode");
      } 
    } 
    Log.e("CameraDeviceUserShim", "Cannot end configure, device has been closed.");
    synchronized (this.mConfigureLock) {
      this.mConfiguring = false;
      throw new ServiceSpecificException(4, "Cannot end configure, device has been closed.");
    } 
  }
  
  public void finalizeOutputConfigurations(int paramInt, OutputConfiguration paramOutputConfiguration) {
    Log.e("CameraDeviceUserShim", "Finalizing output configuration is not supported on legacy devices");
    throw new ServiceSpecificException(10, "Finalizing output configuration is not supported on legacy devices");
  }
  
  public long flush() {
    if (!this.mLegacyDevice.isClosed())
      synchronized (this.mConfigureLock) {
        if (!this.mConfiguring)
          return this.mLegacyDevice.flush(); 
        Log.e("CameraDeviceUserShim", "Cannot flush, configuration change in progress.");
        ServiceSpecificException serviceSpecificException = new ServiceSpecificException();
        this(10, "Cannot flush, configuration change in progress.");
        throw serviceSpecificException;
      }  
    Log.e("CameraDeviceUserShim", "Cannot flush, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot flush, device has been closed.");
  }
  
  public CameraMetadataNative getCameraInfo() {
    Log.e("CameraDeviceUserShim", "getCameraInfo unimplemented.");
    return null;
  }
  
  public int getGlobalAudioRestriction() {
    if (!this.mLegacyDevice.isClosed())
      return this.mLegacyDevice.getAudioRestriction(); 
    Log.e("CameraDeviceUserShim", "Cannot set camera audio restriction, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot set camera audio restriction, device has been closed.");
  }
  
  public Surface getInputSurface() {
    Log.e("CameraDeviceUserShim", "Getting input surface is not supported on legacy devices");
    throw new ServiceSpecificException(10, "Getting input surface is not supported on legacy devices");
  }
  
  public boolean isSessionConfigurationSupported(SessionConfiguration paramSessionConfiguration) {
    int i = paramSessionConfiguration.getSessionType();
    boolean bool = false;
    if (i != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Session type: ");
      stringBuilder.append(paramSessionConfiguration.getSessionType());
      stringBuilder.append(" is different from  regular. Legacy devices support only regular session types!");
      Log.e("CameraDeviceUserShim", stringBuilder.toString());
      return false;
    } 
    if (paramSessionConfiguration.getInputConfiguration() != null) {
      Log.e("CameraDeviceUserShim", "Input configuration present, legacy devices do not support this feature!");
      return false;
    } 
    List list = paramSessionConfiguration.getOutputConfigurations();
    if (list.isEmpty()) {
      Log.e("CameraDeviceUserShim", "Empty output configuration list!");
      return false;
    } 
    SparseArray<Surface> sparseArray = new SparseArray(list.size());
    i = 0;
    for (OutputConfiguration outputConfiguration : list) {
      list = outputConfiguration.getSurfaces();
      if (list.isEmpty() || list.size() > 1) {
        Log.e("CameraDeviceUserShim", "Legacy devices do not support deferred or shared surfaces!");
        return false;
      } 
      sparseArray.put(i, outputConfiguration.getSurface());
      i++;
    } 
    if (this.mLegacyDevice.configureOutputs(sparseArray, true) == 0)
      bool = true; 
    return bool;
  }
  
  public void prepare(int paramInt) {
    if (!this.mLegacyDevice.isClosed()) {
      this.mCameraCallbacks.onPrepared(paramInt);
      return;
    } 
    Log.e("CameraDeviceUserShim", "Cannot prepare stream, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot prepare stream, device has been closed.");
  }
  
  public void prepare2(int paramInt1, int paramInt2) {
    prepare(paramInt2);
  }
  
  public void setCameraAudioRestriction(int paramInt) {
    if (!this.mLegacyDevice.isClosed()) {
      this.mLegacyDevice.setAudioRestriction(paramInt);
      return;
    } 
    Log.e("CameraDeviceUserShim", "Cannot set camera audio restriction, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot set camera audio restriction, device has been closed.");
  }
  
  public SubmitInfo submitRequest(CaptureRequest paramCaptureRequest, boolean paramBoolean) {
    if (!this.mLegacyDevice.isClosed())
      synchronized (this.mConfigureLock) {
        if (!this.mConfiguring)
          return this.mLegacyDevice.submitRequest(paramCaptureRequest, paramBoolean); 
        Log.e("CameraDeviceUserShim", "Cannot submit request, configuration change in progress.");
        ServiceSpecificException serviceSpecificException = new ServiceSpecificException();
        this(10, "Cannot submit request, configuration change in progress.");
        throw serviceSpecificException;
      }  
    Log.e("CameraDeviceUserShim", "Cannot submit request, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot submit request, device has been closed.");
  }
  
  public SubmitInfo submitRequestList(CaptureRequest[] paramArrayOfCaptureRequest, boolean paramBoolean) {
    if (!this.mLegacyDevice.isClosed())
      synchronized (this.mConfigureLock) {
        if (!this.mConfiguring)
          return this.mLegacyDevice.submitRequestList(paramArrayOfCaptureRequest, paramBoolean); 
        Log.e("CameraDeviceUserShim", "Cannot submit request, configuration change in progress.");
        ServiceSpecificException serviceSpecificException = new ServiceSpecificException();
        this(10, "Cannot submit request, configuration change in progress.");
        throw serviceSpecificException;
      }  
    Log.e("CameraDeviceUserShim", "Cannot submit request list, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot submit request list, device has been closed.");
  }
  
  public ICameraOfflineSession switchToOffline(ICameraDeviceCallbacks paramICameraDeviceCallbacks, int[] paramArrayOfint) {
    throw new UnsupportedOperationException("Legacy device does not support offline mode");
  }
  
  public void tearDown(int paramInt) {
    if (!this.mLegacyDevice.isClosed())
      return; 
    Log.e("CameraDeviceUserShim", "Cannot tear down stream, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot tear down stream, device has been closed.");
  }
  
  public void updateOutputConfiguration(int paramInt, OutputConfiguration paramOutputConfiguration) {}
  
  public void waitUntilIdle() throws RemoteException {
    if (!this.mLegacyDevice.isClosed())
      synchronized (this.mConfigureLock) {
        if (!this.mConfiguring) {
          this.mLegacyDevice.waitUntilIdle();
          return;
        } 
        Log.e("CameraDeviceUserShim", "Cannot wait until idle, configuration change in progress.");
        ServiceSpecificException serviceSpecificException = new ServiceSpecificException();
        this(10, "Cannot wait until idle, configuration change in progress.");
        throw serviceSpecificException;
      }  
    Log.e("CameraDeviceUserShim", "Cannot wait until idle, device has been closed.");
    throw new ServiceSpecificException(4, "Cannot wait until idle, device has been closed.");
  }
  
  private static class CameraCallbackThread implements ICameraDeviceCallbacks {
    private static final int CAMERA_ERROR = 0;
    
    private static final int CAMERA_IDLE = 1;
    
    private static final int CAPTURE_STARTED = 2;
    
    private static final int PREPARED = 4;
    
    private static final int REPEATING_REQUEST_ERROR = 5;
    
    private static final int REQUEST_QUEUE_EMPTY = 6;
    
    private static final int RESULT_RECEIVED = 3;
    
    private final ICameraDeviceCallbacks mCallbacks;
    
    private Handler mHandler;
    
    private final HandlerThread mHandlerThread;
    
    public CameraCallbackThread(ICameraDeviceCallbacks param1ICameraDeviceCallbacks) {
      this.mCallbacks = param1ICameraDeviceCallbacks;
      HandlerThread handlerThread = new HandlerThread("LegacyCameraCallback");
      this.mHandlerThread = handlerThread;
      handlerThread.start();
    }
    
    private Handler getHandler() {
      if (this.mHandler == null)
        this.mHandler = new CallbackHandler(this.mHandlerThread.getLooper()); 
      return this.mHandler;
    }
    
    public IBinder asBinder() {
      return null;
    }
    
    public void close() {
      this.mHandlerThread.quitSafely();
    }
    
    public void onCaptureStarted(CaptureResultExtras param1CaptureResultExtras, long param1Long) {
      Message message = getHandler().obtainMessage(2, (int)(param1Long & 0xFFFFFFFFL), (int)(0xFFFFFFFFL & param1Long >> 32L), param1CaptureResultExtras);
      getHandler().sendMessage(message);
    }
    
    public void onDeviceError(int param1Int, CaptureResultExtras param1CaptureResultExtras) {
      Message message = getHandler().obtainMessage(0, param1Int, 0, param1CaptureResultExtras);
      getHandler().sendMessage(message);
    }
    
    public void onDeviceIdle() {
      Message message = getHandler().obtainMessage(1);
      getHandler().sendMessage(message);
    }
    
    public void onPrepared(int param1Int) {
      Message message = getHandler().obtainMessage(4, param1Int, 0);
      getHandler().sendMessage(message);
    }
    
    public void onRepeatingRequestError(long param1Long, int param1Int) {
      Message message = getHandler().obtainMessage(5, new Object[] { Long.valueOf(param1Long), Integer.valueOf(param1Int) });
      getHandler().sendMessage(message);
    }
    
    public void onRequestQueueEmpty() {
      Message message = getHandler().obtainMessage(6, 0, 0);
      getHandler().sendMessage(message);
    }
    
    public void onResultReceived(CameraMetadataNative param1CameraMetadataNative, CaptureResultExtras param1CaptureResultExtras, PhysicalCaptureResultInfo[] param1ArrayOfPhysicalCaptureResultInfo) {
      Message message = getHandler().obtainMessage(3, new Object[] { param1CameraMetadataNative, param1CaptureResultExtras });
      getHandler().sendMessage(message);
    }
    
    private class CallbackHandler extends Handler {
      public CallbackHandler(Looper param2Looper) {
        super(param2Looper);
      }
      
      public void handleMessage(Message param2Message) {
        try {
          IllegalArgumentException illegalArgumentException;
          Object[] arrayOfObject1;
          CaptureResultExtras captureResultExtras1;
          StringBuilder stringBuilder;
          Object[] arrayOfObject2;
          CameraMetadataNative cameraMetadataNative;
          long l1;
          long l2;
          switch (param2Message.what) {
            default:
              illegalArgumentException = new IllegalArgumentException();
              stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("Unknown callback message ");
              stringBuilder.append(param2Message.what);
              this(stringBuilder.toString());
              throw illegalArgumentException;
            case 6:
              CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onRequestQueueEmpty();
              return;
            case 5:
              arrayOfObject2 = (Object[])param2Message.obj;
              l1 = ((Long)arrayOfObject2[0]).longValue();
              i = ((Integer)arrayOfObject2[1]).intValue();
              CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onRepeatingRequestError(l1, i);
              return;
            case 4:
              i = param2Message.arg1;
              CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onPrepared(i);
              return;
            case 3:
              arrayOfObject1 = (Object[])param2Message.obj;
              cameraMetadataNative = (CameraMetadataNative)arrayOfObject1[0];
              captureResultExtras1 = (CaptureResultExtras)arrayOfObject1[1];
              CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onResultReceived(cameraMetadataNative, captureResultExtras1, new PhysicalCaptureResultInfo[0]);
              return;
            case 2:
              l2 = param2Message.arg2;
              l1 = param2Message.arg1;
              captureResultExtras2 = (CaptureResultExtras)param2Message.obj;
              CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onCaptureStarted(captureResultExtras2, (l2 & 0xFFFFFFFFL) << 32L | 0xFFFFFFFFL & l1);
              return;
            case 1:
              CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onDeviceIdle();
              return;
            case 0:
              break;
          } 
          int i = param2Message.arg1;
          CaptureResultExtras captureResultExtras2 = (CaptureResultExtras)param2Message.obj;
          CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onDeviceError(i, captureResultExtras2);
          return;
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Received remote exception during camera callback ");
          stringBuilder.append(param2Message.what);
          throw new IllegalStateException(stringBuilder.toString(), remoteException);
        } 
      }
    }
  }
  
  private class CallbackHandler extends Handler {
    public CallbackHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      try {
        IllegalArgumentException illegalArgumentException;
        Object[] arrayOfObject1;
        CaptureResultExtras captureResultExtras1;
        StringBuilder stringBuilder;
        Object[] arrayOfObject2;
        CameraMetadataNative cameraMetadataNative;
        long l1;
        long l2;
        switch (param1Message.what) {
          default:
            illegalArgumentException = new IllegalArgumentException();
            stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("Unknown callback message ");
            stringBuilder.append(param1Message.what);
            this(stringBuilder.toString());
            throw illegalArgumentException;
          case 6:
            this.this$0.mCallbacks.onRequestQueueEmpty();
            return;
          case 5:
            arrayOfObject2 = (Object[])param1Message.obj;
            l1 = ((Long)arrayOfObject2[0]).longValue();
            i = ((Integer)arrayOfObject2[1]).intValue();
            this.this$0.mCallbacks.onRepeatingRequestError(l1, i);
            return;
          case 4:
            i = param1Message.arg1;
            this.this$0.mCallbacks.onPrepared(i);
            return;
          case 3:
            arrayOfObject1 = (Object[])param1Message.obj;
            cameraMetadataNative = (CameraMetadataNative)arrayOfObject1[0];
            captureResultExtras1 = (CaptureResultExtras)arrayOfObject1[1];
            this.this$0.mCallbacks.onResultReceived(cameraMetadataNative, captureResultExtras1, new PhysicalCaptureResultInfo[0]);
            return;
          case 2:
            l2 = param1Message.arg2;
            l1 = param1Message.arg1;
            captureResultExtras2 = (CaptureResultExtras)param1Message.obj;
            this.this$0.mCallbacks.onCaptureStarted(captureResultExtras2, (l2 & 0xFFFFFFFFL) << 32L | 0xFFFFFFFFL & l1);
            return;
          case 1:
            this.this$0.mCallbacks.onDeviceIdle();
            return;
          case 0:
            break;
        } 
        int i = param1Message.arg1;
        CaptureResultExtras captureResultExtras2 = (CaptureResultExtras)param1Message.obj;
        this.this$0.mCallbacks.onDeviceError(i, captureResultExtras2);
        return;
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Received remote exception during camera callback ");
        stringBuilder.append(param1Message.what);
        throw new IllegalStateException(stringBuilder.toString(), remoteException);
      } 
    }
  }
  
  private static class CameraLooper implements Runnable, AutoCloseable {
    private final Camera mCamera = Camera.openUninitialized();
    
    private final int mCameraId;
    
    private volatile int mInitErrors;
    
    private Looper mLooper;
    
    private final ConditionVariable mStartDone = new ConditionVariable();
    
    private final Thread mThread;
    
    public CameraLooper(int param1Int) {
      this.mCameraId = param1Int;
      Thread thread = new Thread(this, "LegacyCameraLooper");
      this.mThread = thread;
      thread.start();
    }
    
    public void close() {
      Looper looper = this.mLooper;
      if (looper == null)
        return; 
      looper.quitSafely();
      try {
        this.mThread.join();
        this.mLooper = null;
        return;
      } catch (InterruptedException interruptedException) {
        throw new AssertionError(interruptedException);
      } 
    }
    
    public Camera getCamera() {
      return this.mCamera;
    }
    
    public void run() {
      Looper.prepare();
      this.mLooper = Looper.myLooper();
      this.mInitErrors = this.mCamera.cameraInitUnspecified(this.mCameraId);
      this.mStartDone.open();
      Looper.loop();
    }
    
    public int waitForOpen(int param1Int) {
      if (this.mStartDone.block(param1Int))
        return this.mInitErrors; 
      Log.e("CameraDeviceUserShim", "waitForOpen - Camera failed to open after timeout of 5000 ms");
      try {
        this.mCamera.release();
      } catch (RuntimeException runtimeException) {
        Log.e("CameraDeviceUserShim", "connectBinderShim - Failed to release camera after timeout ", runtimeException);
      } 
      throw new ServiceSpecificException(10);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/CameraDeviceUserShim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */