package android.hardware;

import android.app.ActivityThread;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.IAudioService;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSIllegalArgumentException;
import android.renderscript.RenderScript;
import android.renderscript.Type;
import android.system.OsConstants;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.android.internal.app.IAppOpsCallback;
import com.android.internal.app.IAppOpsService;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@Deprecated
public class Camera {
  public static final String ACTION_NEW_PICTURE = "android.hardware.action.NEW_PICTURE";
  
  public static final String ACTION_NEW_VIDEO = "android.hardware.action.NEW_VIDEO";
  
  public static final int CAMERA_ERROR_DISABLED = 3;
  
  public static final int CAMERA_ERROR_EVICTED = 2;
  
  public static final int CAMERA_ERROR_SERVER_DIED = 100;
  
  public static final int CAMERA_ERROR_UNKNOWN = 1;
  
  private static final int CAMERA_FACE_DETECTION_HW = 0;
  
  private static final int CAMERA_FACE_DETECTION_SW = 1;
  
  public static final int CAMERA_HAL_API_VERSION_1_0 = 256;
  
  private static final int CAMERA_HAL_API_VERSION_NORMAL_CONNECT = -2;
  
  private static final int CAMERA_HAL_API_VERSION_UNSPECIFIED = -1;
  
  private static final int CAMERA_MSG_COMPRESSED_IMAGE = 256;
  
  private static final int CAMERA_MSG_ERROR = 1;
  
  private static final int CAMERA_MSG_FOCUS = 4;
  
  private static final int CAMERA_MSG_FOCUS_MOVE = 2048;
  
  private static final int CAMERA_MSG_POSTVIEW_FRAME = 64;
  
  private static final int CAMERA_MSG_PREVIEW_FRAME = 16;
  
  private static final int CAMERA_MSG_PREVIEW_METADATA = 1024;
  
  private static final int CAMERA_MSG_RAW_IMAGE = 128;
  
  private static final int CAMERA_MSG_RAW_IMAGE_NOTIFY = 512;
  
  private static final int CAMERA_MSG_SHUTTER = 2;
  
  private static final int CAMERA_MSG_VIDEO_FRAME = 32;
  
  private static final int CAMERA_MSG_ZOOM = 8;
  
  private static final int NO_ERROR = 0;
  
  private static final String TAG = "Camera";
  
  private IAppOpsService mAppOps;
  
  private IAppOpsCallback mAppOpsCallback;
  
  private AutoFocusCallback mAutoFocusCallback;
  
  private final Object mAutoFocusCallbackLock = new Object();
  
  private AutoFocusMoveCallback mAutoFocusMoveCallback;
  
  private ErrorCallback mDetailedErrorCallback;
  
  private ErrorCallback mErrorCallback;
  
  private EventHandler mEventHandler;
  
  private boolean mFaceDetectionRunning = false;
  
  private FaceDetectionListener mFaceListener;
  
  private boolean mHasAppOpsPlayAudio = true;
  
  private PictureCallback mJpegCallback;
  
  private long mNativeContext;
  
  private boolean mOneShot;
  
  private PictureCallback mPostviewCallback;
  
  private PreviewCallback mPreviewCallback;
  
  private PictureCallback mRawImageCallback;
  
  private ShutterCallback mShutterCallback;
  
  private boolean mShutterSoundEnabledFromApp = true;
  
  private final Object mShutterSoundLock = new Object();
  
  private boolean mUsingPreviewAllocation;
  
  private boolean mWithBuffer;
  
  private OnZoomChangeListener mZoomListener;
  
  Camera() {}
  
  Camera(int paramInt) {
    paramInt = cameraInitNormal(paramInt);
    if (checkInitErrors(paramInt)) {
      if (paramInt != -OsConstants.EACCES) {
        if (paramInt == -OsConstants.ENODEV)
          throw new RuntimeException("Camera initialization failed"); 
        throw new RuntimeException("Unknown camera error");
      } 
      throw new RuntimeException("Fail to connect to camera service");
    } 
    initAppOps();
  }
  
  private Camera(int paramInt1, int paramInt2) {
    paramInt1 = cameraInitVersion(paramInt1, paramInt2);
    if (checkInitErrors(paramInt1)) {
      if (paramInt1 != -OsConstants.EACCES) {
        if (paramInt1 != -OsConstants.ENODEV) {
          if (paramInt1 != -OsConstants.ENOSYS) {
            if (paramInt1 != -OsConstants.EOPNOTSUPP) {
              if (paramInt1 != -OsConstants.EINVAL) {
                if (paramInt1 != -OsConstants.EBUSY) {
                  if (paramInt1 == -OsConstants.EUSERS)
                    throw new RuntimeException("Camera initialization failed because the max number of camera devices were already opened"); 
                  throw new RuntimeException("Unknown camera error");
                } 
                throw new RuntimeException("Camera initialization failed because the camera device was already opened");
              } 
              throw new RuntimeException("Camera initialization failed because the input arugments are invalid");
            } 
            throw new RuntimeException("Camera initialization failed because the hal version is not supported by this device");
          } 
          throw new RuntimeException("Camera initialization failed because some methods are not implemented");
        } 
        throw new RuntimeException("Camera initialization failed");
      } 
      throw new RuntimeException("Fail to connect to camera service");
    } 
  }
  
  private final native void _addCallbackBuffer(byte[] paramArrayOfbyte, int paramInt);
  
  private final native boolean _enableShutterSound(boolean paramBoolean);
  
  private static native void _getCameraInfo(int paramInt, CameraInfo paramCameraInfo);
  
  private final native void _startFaceDetection(int paramInt);
  
  private final native void _stopFaceDetection();
  
  private final native void _stopPreview();
  
  private final void addCallbackBuffer(byte[] paramArrayOfbyte, int paramInt) {
    if (paramInt == 16 || paramInt == 128) {
      _addCallbackBuffer(paramArrayOfbyte, paramInt);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unsupported message type: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private int cameraInitNormal(int paramInt) {
    return cameraInitVersion(paramInt, -2);
  }
  
  private int cameraInitVersion(int paramInt1, int paramInt2) {
    this.mShutterCallback = null;
    this.mRawImageCallback = null;
    this.mJpegCallback = null;
    this.mPreviewCallback = null;
    this.mPostviewCallback = null;
    this.mUsingPreviewAllocation = false;
    this.mZoomListener = null;
    Looper looper = Looper.myLooper();
    if (looper != null) {
      this.mEventHandler = new EventHandler(this, looper);
    } else {
      looper = Looper.getMainLooper();
      if (looper != null) {
        this.mEventHandler = new EventHandler(this, looper);
      } else {
        this.mEventHandler = null;
      } 
    } 
    return native_setup(new WeakReference<>(this), paramInt1, paramInt2, ActivityThread.currentOpPackageName());
  }
  
  public static boolean checkInitErrors(int paramInt) {
    boolean bool;
    if (paramInt != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private native void enableFocusMoveCallback(int paramInt);
  
  public static void getCameraInfo(int paramInt, CameraInfo paramCameraInfo) {
    _getCameraInfo(paramInt, paramCameraInfo);
    IAudioService iAudioService = IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
    try {
      if (iAudioService.isCameraSoundForced())
        paramCameraInfo.canDisableShutterSound = false; 
    } catch (RemoteException remoteException) {
      Log.e("Camera", "Audio service is unavailable for queries");
    } 
  }
  
  public static Parameters getEmptyParameters() {
    Camera camera = new Camera();
    Objects.requireNonNull(camera);
    return new Parameters();
  }
  
  public static native int getNumberOfCameras();
  
  public static Parameters getParametersCopy(Parameters paramParameters) {
    if (paramParameters != null) {
      Camera camera = paramParameters.getOuter();
      Objects.requireNonNull(camera);
      Parameters parameters = new Parameters();
      parameters.copyFrom(paramParameters);
      return parameters;
    } 
    throw new NullPointerException("parameters must not be null");
  }
  
  private void initAppOps() {
    this.mAppOps = IAppOpsService.Stub.asInterface(ServiceManager.getService("appops"));
    updateAppOpsPlayAudio();
    this.mAppOpsCallback = (IAppOpsCallback)new IAppOpsCallbackWrapper(this);
    try {
      this.mAppOps.startWatchingMode(28, ActivityThread.currentPackageName(), this.mAppOpsCallback);
    } catch (RemoteException remoteException) {
      Log.e("Camera", "Error registering appOps callback", (Throwable)remoteException);
      this.mHasAppOpsPlayAudio = false;
    } 
  }
  
  private final native void native_autoFocus();
  
  private final native void native_cancelAutoFocus();
  
  private final native String native_getParameters();
  
  private final native void native_release();
  
  private final native void native_setParameters(String paramString);
  
  private final native int native_setup(Object paramObject, int paramInt1, int paramInt2, String paramString);
  
  private final native void native_takePicture(int paramInt);
  
  public static Camera open() {
    int i = getNumberOfCameras();
    CameraInfo cameraInfo = new CameraInfo();
    for (byte b = 0; b < i; b++) {
      getCameraInfo(b, cameraInfo);
      if (cameraInfo.facing == 0)
        return new Camera(b); 
    } 
    return null;
  }
  
  public static Camera open(int paramInt) {
    return new Camera(paramInt);
  }
  
  public static Camera openLegacy(int paramInt1, int paramInt2) {
    if (paramInt2 >= 256)
      return new Camera(paramInt1, paramInt2); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid HAL version ");
    stringBuilder.append(paramInt2);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static Camera openUninitialized() {
    return new Camera();
  }
  
  private static void postEventFromNative(Object paramObject1, int paramInt1, int paramInt2, int paramInt3, Object paramObject2) {
    paramObject1 = ((WeakReference<Camera>)paramObject1).get();
    if (paramObject1 == null)
      return; 
    EventHandler eventHandler = ((Camera)paramObject1).mEventHandler;
    if (eventHandler != null) {
      paramObject2 = eventHandler.obtainMessage(paramInt1, paramInt2, paramInt3, paramObject2);
      ((Camera)paramObject1).mEventHandler.sendMessage((Message)paramObject2);
    } 
  }
  
  private void releaseAppOps() {
    try {
      if (this.mAppOps != null)
        this.mAppOps.stopWatchingMode(this.mAppOpsCallback); 
    } catch (Exception exception) {}
  }
  
  private final native void setHasPreviewCallback(boolean paramBoolean1, boolean paramBoolean2);
  
  private final native void setPreviewCallbackSurface(Surface paramSurface);
  
  private void updateAppOpsPlayAudio() {
    synchronized (this.mShutterSoundLock) {
      boolean bool = this.mHasAppOpsPlayAudio;
      int i = 1;
      try {
        boolean bool1;
        if (this.mAppOps != null)
          i = this.mAppOps.checkAudioOperation(28, 13, Process.myUid(), ActivityThread.currentPackageName()); 
        if (i == 0) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        this.mHasAppOpsPlayAudio = bool1;
      } catch (RemoteException remoteException) {
        Log.e("Camera", "AppOpsService check audio operation failed");
        this.mHasAppOpsPlayAudio = false;
      } 
      if (bool != this.mHasAppOpsPlayAudio)
        if (!this.mHasAppOpsPlayAudio) {
          IAudioService iAudioService = IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
          try {
            boolean bool1 = iAudioService.isCameraSoundForced();
            if (bool1)
              return; 
          } catch (RemoteException remoteException) {
            Log.e("Camera", "Audio service is unavailable for queries");
          } 
          _enableShutterSound(false);
        } else {
          enableShutterSound(this.mShutterSoundEnabledFromApp);
        }  
      return;
    } 
  }
  
  public final void addCallbackBuffer(byte[] paramArrayOfbyte) {
    _addCallbackBuffer(paramArrayOfbyte, 16);
  }
  
  public final void addRawImageCallbackBuffer(byte[] paramArrayOfbyte) {
    addCallbackBuffer(paramArrayOfbyte, 128);
  }
  
  public final void autoFocus(AutoFocusCallback paramAutoFocusCallback) {
    synchronized (this.mAutoFocusCallbackLock) {
      this.mAutoFocusCallback = paramAutoFocusCallback;
      native_autoFocus();
      return;
    } 
  }
  
  public int cameraInitUnspecified(int paramInt) {
    return cameraInitVersion(paramInt, -1);
  }
  
  public final void cancelAutoFocus() {
    synchronized (this.mAutoFocusCallbackLock) {
      this.mAutoFocusCallback = null;
      native_cancelAutoFocus();
      this.mEventHandler.removeMessages(4);
      return;
    } 
  }
  
  public final Allocation createPreviewAllocation(RenderScript paramRenderScript, int paramInt) throws RSIllegalArgumentException {
    Size size = getParameters().getPreviewSize();
    Type.Builder builder = new Type.Builder(paramRenderScript, Element.createPixel(paramRenderScript, Element.DataType.UNSIGNED_8, Element.DataKind.PIXEL_YUV));
    builder.setYuvFormat(842094169);
    builder.setX(size.width);
    builder.setY(size.height);
    return Allocation.createTyped(paramRenderScript, builder.create(), paramInt | 0x20);
  }
  
  public final boolean disableShutterSound() {
    return _enableShutterSound(false);
  }
  
  public final boolean enableShutterSound(boolean paramBoolean) {
    boolean bool1 = true;
    boolean bool2 = true;
    IAudioService iAudioService = IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
    try {
      boolean bool = iAudioService.isCameraSoundForced();
      if (bool)
        bool2 = false; 
    } catch (RemoteException remoteException) {
      Log.e("Camera", "Audio service is unavailable for queries");
      bool2 = bool1;
    } 
    if (!paramBoolean && !bool2)
      return false; 
    synchronized (this.mShutterSoundLock) {
      this.mShutterSoundEnabledFromApp = paramBoolean;
      boolean bool = _enableShutterSound(paramBoolean);
      if (paramBoolean && !this.mHasAppOpsPlayAudio) {
        Log.i("Camera", "Shutter sound is not allowed by AppOpsManager");
        if (bool2)
          _enableShutterSound(false); 
      } 
      return bool;
    } 
  }
  
  protected void finalize() {
    release();
  }
  
  public final native int getAudioRestriction();
  
  public Parameters getParameters() {
    Parameters parameters = new Parameters();
    parameters.unflatten(native_getParameters());
    return parameters;
  }
  
  public final native void lock();
  
  public final native boolean previewEnabled();
  
  public final native void reconnect() throws IOException;
  
  public final void release() {
    native_release();
    this.mFaceDetectionRunning = false;
    releaseAppOps();
  }
  
  public final native void setAudioRestriction(int paramInt);
  
  public void setAutoFocusMoveCallback(AutoFocusMoveCallback paramAutoFocusMoveCallback) {
    boolean bool;
    this.mAutoFocusMoveCallback = paramAutoFocusMoveCallback;
    if (paramAutoFocusMoveCallback != null) {
      bool = true;
    } else {
      bool = false;
    } 
    enableFocusMoveCallback(bool);
  }
  
  public final void setDetailedErrorCallback(ErrorCallback paramErrorCallback) {
    this.mDetailedErrorCallback = paramErrorCallback;
  }
  
  public final native void setDisplayOrientation(int paramInt);
  
  public final void setErrorCallback(ErrorCallback paramErrorCallback) {
    this.mErrorCallback = paramErrorCallback;
  }
  
  public final void setFaceDetectionListener(FaceDetectionListener paramFaceDetectionListener) {
    this.mFaceListener = paramFaceDetectionListener;
  }
  
  public final void setOneShotPreviewCallback(PreviewCallback paramPreviewCallback) {
    this.mPreviewCallback = paramPreviewCallback;
    boolean bool = true;
    this.mOneShot = true;
    this.mWithBuffer = false;
    if (paramPreviewCallback != null)
      this.mUsingPreviewAllocation = false; 
    if (paramPreviewCallback == null)
      bool = false; 
    setHasPreviewCallback(bool, false);
  }
  
  public void setParameters(Parameters paramParameters) {
    if (this.mUsingPreviewAllocation) {
      Size size1 = paramParameters.getPreviewSize();
      Size size2 = getParameters().getPreviewSize();
      if (size1.width != size2.width || size1.height != size2.height)
        throw new IllegalStateException("Cannot change preview size while a preview allocation is configured."); 
    } 
    native_setParameters(paramParameters.flatten());
  }
  
  public final void setPreviewCallback(PreviewCallback paramPreviewCallback) {
    boolean bool;
    this.mPreviewCallback = paramPreviewCallback;
    this.mOneShot = false;
    this.mWithBuffer = false;
    if (paramPreviewCallback != null)
      this.mUsingPreviewAllocation = false; 
    if (paramPreviewCallback != null) {
      bool = true;
    } else {
      bool = false;
    } 
    setHasPreviewCallback(bool, false);
  }
  
  public final void setPreviewCallbackAllocation(Allocation paramAllocation) throws IOException {
    Size size1;
    Size size2 = null;
    if (paramAllocation != null) {
      Surface surface;
      size2 = getParameters().getPreviewSize();
      if (size2.width == paramAllocation.getType().getX() && size2.height == paramAllocation.getType().getY()) {
        if ((paramAllocation.getUsage() & 0x20) != 0) {
          if (paramAllocation.getType().getElement().getDataKind() == Element.DataKind.PIXEL_YUV) {
            surface = paramAllocation.getSurface();
            this.mUsingPreviewAllocation = true;
          } else {
            throw new IllegalArgumentException("Allocation is not of a YUV type");
          } 
        } else {
          throw new IllegalArgumentException("Allocation usage does not include USAGE_IO_INPUT");
        } 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Allocation dimensions don't match preview dimensions: Allocation is ");
        stringBuilder.append(surface.getType().getX());
        stringBuilder.append(", ");
        stringBuilder.append(surface.getType().getY());
        stringBuilder.append(". Preview is ");
        stringBuilder.append(size2.width);
        stringBuilder.append(", ");
        stringBuilder.append(size2.height);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
    } else {
      this.mUsingPreviewAllocation = false;
      size1 = size2;
    } 
    setPreviewCallbackSurface((Surface)size1);
  }
  
  public final void setPreviewCallbackWithBuffer(PreviewCallback paramPreviewCallback) {
    this.mPreviewCallback = paramPreviewCallback;
    boolean bool = false;
    this.mOneShot = false;
    this.mWithBuffer = true;
    if (paramPreviewCallback != null)
      this.mUsingPreviewAllocation = false; 
    if (paramPreviewCallback != null)
      bool = true; 
    setHasPreviewCallback(bool, true);
  }
  
  public final void setPreviewDisplay(SurfaceHolder paramSurfaceHolder) throws IOException {
    if (paramSurfaceHolder != null) {
      setPreviewSurface(paramSurfaceHolder.getSurface());
    } else {
      setPreviewSurface((Surface)null);
    } 
  }
  
  public final native void setPreviewSurface(Surface paramSurface) throws IOException;
  
  public final native void setPreviewTexture(SurfaceTexture paramSurfaceTexture) throws IOException;
  
  public final void setZoomChangeListener(OnZoomChangeListener paramOnZoomChangeListener) {
    this.mZoomListener = paramOnZoomChangeListener;
  }
  
  public final void startFaceDetection() {
    if (!this.mFaceDetectionRunning) {
      _startFaceDetection(0);
      this.mFaceDetectionRunning = true;
      return;
    } 
    throw new RuntimeException("Face detection is already running");
  }
  
  public final native void startPreview();
  
  public final native void startSmoothZoom(int paramInt);
  
  public final void stopFaceDetection() {
    _stopFaceDetection();
    this.mFaceDetectionRunning = false;
  }
  
  public final void stopPreview() {
    _stopPreview();
    this.mFaceDetectionRunning = false;
    this.mShutterCallback = null;
    this.mRawImageCallback = null;
    this.mPostviewCallback = null;
    this.mJpegCallback = null;
    synchronized (this.mAutoFocusCallbackLock) {
      this.mAutoFocusCallback = null;
      this.mAutoFocusMoveCallback = null;
      return;
    } 
  }
  
  public final native void stopSmoothZoom();
  
  public final void takePicture(ShutterCallback paramShutterCallback, PictureCallback paramPictureCallback1, PictureCallback paramPictureCallback2) {
    takePicture(paramShutterCallback, paramPictureCallback1, null, paramPictureCallback2);
  }
  
  public final void takePicture(ShutterCallback paramShutterCallback, PictureCallback paramPictureCallback1, PictureCallback paramPictureCallback2, PictureCallback paramPictureCallback3) {
    this.mShutterCallback = paramShutterCallback;
    this.mRawImageCallback = paramPictureCallback1;
    this.mPostviewCallback = paramPictureCallback2;
    this.mJpegCallback = paramPictureCallback3;
    int i = 0;
    if (paramShutterCallback != null)
      i = 0x0 | 0x2; 
    int j = i;
    if (this.mRawImageCallback != null)
      j = i | 0x80; 
    i = j;
    if (this.mPostviewCallback != null)
      i = j | 0x40; 
    j = i;
    if (this.mJpegCallback != null)
      j = i | 0x100; 
    native_takePicture(j);
    this.mFaceDetectionRunning = false;
  }
  
  public final native void unlock();
  
  @Deprecated
  public static class Area {
    public Rect rect;
    
    public int weight;
    
    public Area(Rect param1Rect, int param1Int) {
      this.rect = param1Rect;
      this.weight = param1Int;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = param1Object instanceof Area;
      boolean bool1 = false;
      if (!bool)
        return false; 
      param1Object = param1Object;
      Rect rect = this.rect;
      if (rect == null) {
        if (((Area)param1Object).rect != null)
          return false; 
      } else if (!rect.equals(((Area)param1Object).rect)) {
        return false;
      } 
      if (this.weight == ((Area)param1Object).weight)
        bool1 = true; 
      return bool1;
    }
  }
  
  @Deprecated
  public static interface AutoFocusCallback {
    void onAutoFocus(boolean param1Boolean, Camera param1Camera);
  }
  
  @Deprecated
  public static interface AutoFocusMoveCallback {
    void onAutoFocusMoving(boolean param1Boolean, Camera param1Camera);
  }
  
  @Deprecated
  public static class CameraInfo {
    public static final int CAMERA_FACING_BACK = 0;
    
    public static final int CAMERA_FACING_FRONT = 1;
    
    public boolean canDisableShutterSound;
    
    public int facing;
    
    public int orientation;
  }
  
  @Deprecated
  public static interface ErrorCallback {
    void onError(int param1Int, Camera param1Camera);
  }
  
  private class EventHandler extends Handler {
    private final Camera mCamera;
    
    public EventHandler(Camera param1Camera1, Looper param1Looper) {
      super(param1Looper);
      this.mCamera = param1Camera1;
    }
    
    public void handleMessage(Message param1Message) {
      int i = param1Message.what;
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
                          stringBuilder1.append(param1Message.what);
                          Log.e("Camera", stringBuilder1.toString());
                          return;
                        } 
                        if (Camera.this.mAutoFocusMoveCallback != null) {
                          Camera.AutoFocusMoveCallback autoFocusMoveCallback = Camera.this.mAutoFocusMoveCallback;
                          if (param1Message.arg1 == 0)
                            bool3 = false; 
                          autoFocusMoveCallback.onAutoFocusMoving(bool3, this.mCamera);
                        } 
                        return;
                      } 
                      if (Camera.this.mFaceListener != null)
                        Camera.this.mFaceListener.onFaceDetection((Camera.Face[])param1Message.obj, this.mCamera); 
                      return;
                    } 
                    if (Camera.this.mJpegCallback != null)
                      Camera.this.mJpegCallback.onPictureTaken((byte[])param1Message.obj, this.mCamera); 
                    return;
                  } 
                  if (Camera.this.mRawImageCallback != null)
                    Camera.this.mRawImageCallback.onPictureTaken((byte[])param1Message.obj, this.mCamera); 
                  return;
                } 
                if (Camera.this.mPostviewCallback != null)
                  Camera.this.mPostviewCallback.onPictureTaken((byte[])param1Message.obj, this.mCamera); 
                return;
              } 
              Camera.PreviewCallback previewCallback = Camera.this.mPreviewCallback;
              if (previewCallback != null) {
                if (Camera.this.mOneShot) {
                  Camera.access$302(Camera.this, null);
                } else if (!Camera.this.mWithBuffer) {
                  Camera.this.setHasPreviewCallback(true, false);
                } 
                previewCallback.onPreviewFrame((byte[])param1Message.obj, this.mCamera);
              } 
              return;
            } 
            if (Camera.this.mZoomListener != null) {
              Camera.OnZoomChangeListener onZoomChangeListener = Camera.this.mZoomListener;
              i = param1Message.arg1;
              if (param1Message.arg2 != 0) {
                bool3 = bool1;
              } else {
                bool3 = false;
              } 
              onZoomChangeListener.onZoomChange(i, bool3, this.mCamera);
            } 
            return;
          } 
          synchronized (Camera.this.mAutoFocusCallbackLock) {
            Camera.AutoFocusCallback autoFocusCallback = Camera.this.mAutoFocusCallback;
            if (autoFocusCallback != null) {
              bool3 = bool2;
              if (param1Message.arg1 == 0)
                bool3 = false; 
              autoFocusCallback.onAutoFocus(bool3, this.mCamera);
            } 
            return;
          } 
        } 
        if (Camera.this.mShutterCallback != null)
          Camera.this.mShutterCallback.onShutter(); 
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Error ");
      stringBuilder.append(param1Message.arg1);
      Log.e("Camera", stringBuilder.toString());
      if (Camera.this.mDetailedErrorCallback != null) {
        Camera.this.mDetailedErrorCallback.onError(param1Message.arg1, this.mCamera);
      } else if (Camera.this.mErrorCallback != null) {
        if (param1Message.arg1 == 3) {
          Camera.this.mErrorCallback.onError(2, this.mCamera);
        } else {
          Camera.this.mErrorCallback.onError(param1Message.arg1, this.mCamera);
        } 
      } 
    }
  }
  
  @Deprecated
  public static class Face {
    public int id = -1;
    
    public Point leftEye = null;
    
    public Point mouth = null;
    
    public Rect rect;
    
    public Point rightEye = null;
    
    public int score;
  }
  
  @Deprecated
  public static interface FaceDetectionListener {
    void onFaceDetection(Camera.Face[] param1ArrayOfFace, Camera param1Camera);
  }
  
  private static class IAppOpsCallbackWrapper extends IAppOpsCallback.Stub {
    private final WeakReference<Camera> mWeakCamera;
    
    IAppOpsCallbackWrapper(Camera param1Camera) {
      this.mWeakCamera = new WeakReference<>(param1Camera);
    }
    
    public void opChanged(int param1Int1, int param1Int2, String param1String) {
      if (param1Int1 == 28) {
        Camera camera = this.mWeakCamera.get();
        if (camera != null)
          camera.updateAppOpsPlayAudio(); 
      } 
    }
  }
  
  @Deprecated
  public static interface OnZoomChangeListener {
    void onZoomChange(int param1Int, boolean param1Boolean, Camera param1Camera);
  }
  
  @Deprecated
  public class Parameters {
    public static final String ANTIBANDING_50HZ = "50hz";
    
    public static final String ANTIBANDING_60HZ = "60hz";
    
    public static final String ANTIBANDING_AUTO = "auto";
    
    public static final String ANTIBANDING_OFF = "off";
    
    public static final String EFFECT_AQUA = "aqua";
    
    public static final String EFFECT_BLACKBOARD = "blackboard";
    
    public static final String EFFECT_MONO = "mono";
    
    public static final String EFFECT_NEGATIVE = "negative";
    
    public static final String EFFECT_NONE = "none";
    
    public static final String EFFECT_POSTERIZE = "posterize";
    
    public static final String EFFECT_SEPIA = "sepia";
    
    public static final String EFFECT_SOLARIZE = "solarize";
    
    public static final String EFFECT_WHITEBOARD = "whiteboard";
    
    private static final String FALSE = "false";
    
    public static final String FLASH_MODE_AUTO = "auto";
    
    public static final String FLASH_MODE_OFF = "off";
    
    public static final String FLASH_MODE_ON = "on";
    
    public static final String FLASH_MODE_RED_EYE = "red-eye";
    
    public static final String FLASH_MODE_TORCH = "torch";
    
    public static final int FOCUS_DISTANCE_FAR_INDEX = 2;
    
    public static final int FOCUS_DISTANCE_NEAR_INDEX = 0;
    
    public static final int FOCUS_DISTANCE_OPTIMAL_INDEX = 1;
    
    public static final String FOCUS_MODE_AUTO = "auto";
    
    public static final String FOCUS_MODE_CONTINUOUS_PICTURE = "continuous-picture";
    
    public static final String FOCUS_MODE_CONTINUOUS_VIDEO = "continuous-video";
    
    public static final String FOCUS_MODE_EDOF = "edof";
    
    public static final String FOCUS_MODE_FIXED = "fixed";
    
    public static final String FOCUS_MODE_INFINITY = "infinity";
    
    public static final String FOCUS_MODE_MACRO = "macro";
    
    private static final String KEY_ANTIBANDING = "antibanding";
    
    private static final String KEY_AUTO_EXPOSURE_LOCK = "auto-exposure-lock";
    
    private static final String KEY_AUTO_EXPOSURE_LOCK_SUPPORTED = "auto-exposure-lock-supported";
    
    private static final String KEY_AUTO_WHITEBALANCE_LOCK = "auto-whitebalance-lock";
    
    private static final String KEY_AUTO_WHITEBALANCE_LOCK_SUPPORTED = "auto-whitebalance-lock-supported";
    
    private static final String KEY_EFFECT = "effect";
    
    private static final String KEY_EXPOSURE_COMPENSATION = "exposure-compensation";
    
    private static final String KEY_EXPOSURE_COMPENSATION_STEP = "exposure-compensation-step";
    
    private static final String KEY_FLASH_MODE = "flash-mode";
    
    private static final String KEY_FOCAL_LENGTH = "focal-length";
    
    private static final String KEY_FOCUS_AREAS = "focus-areas";
    
    private static final String KEY_FOCUS_DISTANCES = "focus-distances";
    
    private static final String KEY_FOCUS_MODE = "focus-mode";
    
    private static final String KEY_GPS_ALTITUDE = "gps-altitude";
    
    private static final String KEY_GPS_LATITUDE = "gps-latitude";
    
    private static final String KEY_GPS_LONGITUDE = "gps-longitude";
    
    private static final String KEY_GPS_PROCESSING_METHOD = "gps-processing-method";
    
    private static final String KEY_GPS_TIMESTAMP = "gps-timestamp";
    
    private static final String KEY_HORIZONTAL_VIEW_ANGLE = "horizontal-view-angle";
    
    private static final String KEY_JPEG_QUALITY = "jpeg-quality";
    
    private static final String KEY_JPEG_THUMBNAIL_HEIGHT = "jpeg-thumbnail-height";
    
    private static final String KEY_JPEG_THUMBNAIL_QUALITY = "jpeg-thumbnail-quality";
    
    private static final String KEY_JPEG_THUMBNAIL_SIZE = "jpeg-thumbnail-size";
    
    private static final String KEY_JPEG_THUMBNAIL_WIDTH = "jpeg-thumbnail-width";
    
    private static final String KEY_MAX_EXPOSURE_COMPENSATION = "max-exposure-compensation";
    
    private static final String KEY_MAX_NUM_DETECTED_FACES_HW = "max-num-detected-faces-hw";
    
    private static final String KEY_MAX_NUM_DETECTED_FACES_SW = "max-num-detected-faces-sw";
    
    private static final String KEY_MAX_NUM_FOCUS_AREAS = "max-num-focus-areas";
    
    private static final String KEY_MAX_NUM_METERING_AREAS = "max-num-metering-areas";
    
    private static final String KEY_MAX_ZOOM = "max-zoom";
    
    private static final String KEY_METERING_AREAS = "metering-areas";
    
    private static final String KEY_MIN_EXPOSURE_COMPENSATION = "min-exposure-compensation";
    
    private static final String KEY_PICTURE_FORMAT = "picture-format";
    
    private static final String KEY_PICTURE_SIZE = "picture-size";
    
    private static final String KEY_PREFERRED_PREVIEW_SIZE_FOR_VIDEO = "preferred-preview-size-for-video";
    
    private static final String KEY_PREVIEW_FORMAT = "preview-format";
    
    private static final String KEY_PREVIEW_FPS_RANGE = "preview-fps-range";
    
    private static final String KEY_PREVIEW_FRAME_RATE = "preview-frame-rate";
    
    private static final String KEY_PREVIEW_SIZE = "preview-size";
    
    private static final String KEY_RECORDING_HINT = "recording-hint";
    
    private static final String KEY_ROTATION = "rotation";
    
    private static final String KEY_SCENE_MODE = "scene-mode";
    
    private static final String KEY_SMOOTH_ZOOM_SUPPORTED = "smooth-zoom-supported";
    
    private static final String KEY_VERTICAL_VIEW_ANGLE = "vertical-view-angle";
    
    private static final String KEY_VIDEO_SIZE = "video-size";
    
    private static final String KEY_VIDEO_SNAPSHOT_SUPPORTED = "video-snapshot-supported";
    
    private static final String KEY_VIDEO_STABILIZATION = "video-stabilization";
    
    private static final String KEY_VIDEO_STABILIZATION_SUPPORTED = "video-stabilization-supported";
    
    private static final String KEY_WHITE_BALANCE = "whitebalance";
    
    private static final String KEY_ZOOM = "zoom";
    
    private static final String KEY_ZOOM_RATIOS = "zoom-ratios";
    
    private static final String KEY_ZOOM_SUPPORTED = "zoom-supported";
    
    private static final String PIXEL_FORMAT_BAYER_RGGB = "bayer-rggb";
    
    private static final String PIXEL_FORMAT_JPEG = "jpeg";
    
    private static final String PIXEL_FORMAT_RGB565 = "rgb565";
    
    private static final String PIXEL_FORMAT_YUV420P = "yuv420p";
    
    private static final String PIXEL_FORMAT_YUV420SP = "yuv420sp";
    
    private static final String PIXEL_FORMAT_YUV422I = "yuv422i-yuyv";
    
    private static final String PIXEL_FORMAT_YUV422SP = "yuv422sp";
    
    public static final int PREVIEW_FPS_MAX_INDEX = 1;
    
    public static final int PREVIEW_FPS_MIN_INDEX = 0;
    
    public static final String SCENE_MODE_ACTION = "action";
    
    public static final String SCENE_MODE_AUTO = "auto";
    
    public static final String SCENE_MODE_BARCODE = "barcode";
    
    public static final String SCENE_MODE_BEACH = "beach";
    
    public static final String SCENE_MODE_CANDLELIGHT = "candlelight";
    
    public static final String SCENE_MODE_FIREWORKS = "fireworks";
    
    public static final String SCENE_MODE_HDR = "hdr";
    
    public static final String SCENE_MODE_LANDSCAPE = "landscape";
    
    public static final String SCENE_MODE_NIGHT = "night";
    
    public static final String SCENE_MODE_NIGHT_PORTRAIT = "night-portrait";
    
    public static final String SCENE_MODE_PARTY = "party";
    
    public static final String SCENE_MODE_PORTRAIT = "portrait";
    
    public static final String SCENE_MODE_SNOW = "snow";
    
    public static final String SCENE_MODE_SPORTS = "sports";
    
    public static final String SCENE_MODE_STEADYPHOTO = "steadyphoto";
    
    public static final String SCENE_MODE_SUNSET = "sunset";
    
    public static final String SCENE_MODE_THEATRE = "theatre";
    
    private static final String SUPPORTED_VALUES_SUFFIX = "-values";
    
    private static final String TRUE = "true";
    
    public static final String WHITE_BALANCE_AUTO = "auto";
    
    public static final String WHITE_BALANCE_CLOUDY_DAYLIGHT = "cloudy-daylight";
    
    public static final String WHITE_BALANCE_DAYLIGHT = "daylight";
    
    public static final String WHITE_BALANCE_FLUORESCENT = "fluorescent";
    
    public static final String WHITE_BALANCE_INCANDESCENT = "incandescent";
    
    public static final String WHITE_BALANCE_SHADE = "shade";
    
    public static final String WHITE_BALANCE_TWILIGHT = "twilight";
    
    public static final String WHITE_BALANCE_WARM_FLUORESCENT = "warm-fluorescent";
    
    private final LinkedHashMap<String, String> mMap = new LinkedHashMap<>(64);
    
    private Parameters() {}
    
    private String cameraFormatForPixelFormat(int param1Int) {
      return (param1Int != 4) ? ((param1Int != 20) ? ((param1Int != 256) ? ((param1Int != 842094169) ? ((param1Int != 16) ? ((param1Int != 17) ? null : "yuv420sp") : "yuv422sp") : "yuv420p") : "jpeg") : "yuv422i-yuyv") : "rgb565";
    }
    
    private float getFloat(String param1String, float param1Float) {
      try {
        return Float.parseFloat(this.mMap.get(param1String));
      } catch (NumberFormatException numberFormatException) {
        return param1Float;
      } 
    }
    
    private int getInt(String param1String, int param1Int) {
      try {
        return Integer.parseInt(this.mMap.get(param1String));
      } catch (NumberFormatException numberFormatException) {
        return param1Int;
      } 
    }
    
    private Camera getOuter() {
      return Camera.this;
    }
    
    private int pixelFormatForCameraFormat(String param1String) {
      return (param1String == null) ? 0 : (param1String.equals("yuv422sp") ? 16 : (param1String.equals("yuv420sp") ? 17 : (param1String.equals("yuv422i-yuyv") ? 20 : (param1String.equals("yuv420p") ? 842094169 : (param1String.equals("rgb565") ? 4 : (param1String.equals("jpeg") ? 256 : 0))))));
    }
    
    private void put(String param1String1, String param1String2) {
      this.mMap.remove(param1String1);
      this.mMap.put(param1String1, param1String2);
    }
    
    private boolean same(String param1String1, String param1String2) {
      return (param1String1 == null && param1String2 == null) ? true : ((param1String1 != null && param1String1.equals(param1String2)));
    }
    
    private void set(String param1String, List<Camera.Area> param1List) {
      if (param1List == null) {
        set(param1String, "(0,0,0,0,0)");
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b = 0; b < param1List.size(); b++) {
          Camera.Area area = param1List.get(b);
          Rect rect = area.rect;
          stringBuilder.append('(');
          stringBuilder.append(rect.left);
          stringBuilder.append(',');
          stringBuilder.append(rect.top);
          stringBuilder.append(',');
          stringBuilder.append(rect.right);
          stringBuilder.append(',');
          stringBuilder.append(rect.bottom);
          stringBuilder.append(',');
          stringBuilder.append(area.weight);
          stringBuilder.append(')');
          if (b != param1List.size() - 1)
            stringBuilder.append(','); 
        } 
        set(param1String, stringBuilder.toString());
      } 
    }
    
    private ArrayList<String> split(String param1String) {
      if (param1String == null)
        return null; 
      TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
      simpleStringSplitter.setString(param1String);
      ArrayList<String> arrayList = new ArrayList();
      Iterator<String> iterator = simpleStringSplitter.iterator();
      while (iterator.hasNext())
        arrayList.add(iterator.next()); 
      return arrayList;
    }
    
    private ArrayList<Camera.Area> splitArea(String param1String) {
      if (param1String == null || param1String.charAt(0) != '(' || param1String.charAt(param1String.length() - 1) != ')') {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid area string=");
        stringBuilder.append(param1String);
        Log.e("Camera", stringBuilder.toString());
        return null;
      } 
      ArrayList<Camera.Area> arrayList = new ArrayList();
      int i = 1;
      int[] arrayOfInt = new int[5];
      while (true) {
        int j = param1String.indexOf("),(", i);
        int k = j;
        if (j == -1)
          k = param1String.length() - 1; 
        splitInt(param1String.substring(i, k), arrayOfInt);
        arrayList.add(new Camera.Area(new Rect(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]), arrayOfInt[4]));
        i = k + 3;
        if (k == param1String.length() - 1) {
          if (arrayList.size() == 0)
            return null; 
          if (arrayList.size() == 1) {
            Camera.Area area = arrayList.get(0);
            Rect rect = area.rect;
            if (rect.left == 0 && rect.top == 0 && rect.right == 0 && rect.bottom == 0 && area.weight == 0)
              return null; 
          } 
          return arrayList;
        } 
      } 
    }
    
    private void splitFloat(String param1String, float[] param1ArrayOffloat) {
      if (param1String == null)
        return; 
      TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
      simpleStringSplitter.setString(param1String);
      byte b = 0;
      Iterator iterator = simpleStringSplitter.iterator();
      while (iterator.hasNext()) {
        param1ArrayOffloat[b] = Float.parseFloat((String)iterator.next());
        b++;
      } 
    }
    
    private ArrayList<Integer> splitInt(String param1String) {
      if (param1String == null)
        return null; 
      TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
      simpleStringSplitter.setString(param1String);
      ArrayList<Integer> arrayList = new ArrayList();
      Iterator<String> iterator = simpleStringSplitter.iterator();
      while (iterator.hasNext())
        arrayList.add(Integer.valueOf(Integer.parseInt(iterator.next()))); 
      return (arrayList.size() == 0) ? null : arrayList;
    }
    
    private void splitInt(String param1String, int[] param1ArrayOfint) {
      if (param1String == null)
        return; 
      TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
      simpleStringSplitter.setString(param1String);
      byte b = 0;
      Iterator iterator = simpleStringSplitter.iterator();
      while (iterator.hasNext()) {
        param1ArrayOfint[b] = Integer.parseInt((String)iterator.next());
        b++;
      } 
    }
    
    private ArrayList<int[]> splitRange(String param1String) {
      if (param1String == null || param1String.charAt(0) != '(' || param1String.charAt(param1String.length() - 1) != ')') {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid range list string=");
        stringBuilder.append(param1String);
        Log.e("Camera", stringBuilder.toString());
        return null;
      } 
      ArrayList<int[]> arrayList = new ArrayList();
      int i = 1;
      while (true) {
        int[] arrayOfInt = new int[2];
        int j = param1String.indexOf("),(", i);
        int k = j;
        if (j == -1)
          k = param1String.length() - 1; 
        splitInt(param1String.substring(i, k), arrayOfInt);
        arrayList.add(arrayOfInt);
        i = k + 3;
        if (k == param1String.length() - 1)
          return (arrayList.size() == 0) ? null : arrayList; 
      } 
    }
    
    private ArrayList<Camera.Size> splitSize(String param1String) {
      if (param1String == null)
        return null; 
      TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
      simpleStringSplitter.setString(param1String);
      ArrayList<Camera.Size> arrayList = new ArrayList();
      Iterator<String> iterator = simpleStringSplitter.iterator();
      while (iterator.hasNext()) {
        Camera.Size size = strToSize(iterator.next());
        if (size != null)
          arrayList.add(size); 
      } 
      return (arrayList.size() == 0) ? null : arrayList;
    }
    
    private Camera.Size strToSize(String param1String) {
      if (param1String == null)
        return null; 
      int i = param1String.indexOf('x');
      if (i != -1) {
        String str = param1String.substring(0, i);
        param1String = param1String.substring(i + 1);
        return new Camera.Size(Integer.parseInt(str), Integer.parseInt(param1String));
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid size parameter string=");
      stringBuilder.append(param1String);
      Log.e("Camera", stringBuilder.toString());
      return null;
    }
    
    public void copyFrom(Parameters param1Parameters) {
      if (param1Parameters != null) {
        this.mMap.putAll(param1Parameters.mMap);
        return;
      } 
      throw new NullPointerException("other must not be null");
    }
    
    @Deprecated
    public void dump() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("dump: size=");
      stringBuilder.append(this.mMap.size());
      Log.e("Camera", stringBuilder.toString());
      for (String str : this.mMap.keySet()) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("dump: ");
        stringBuilder1.append(str);
        stringBuilder1.append("=");
        stringBuilder1.append(this.mMap.get(str));
        Log.e("Camera", stringBuilder1.toString());
      } 
    }
    
    public String flatten() {
      StringBuilder stringBuilder = new StringBuilder(128);
      for (String str : this.mMap.keySet()) {
        stringBuilder.append(str);
        stringBuilder.append("=");
        stringBuilder.append(this.mMap.get(str));
        stringBuilder.append(";");
      } 
      stringBuilder.deleteCharAt(stringBuilder.length() - 1);
      return stringBuilder.toString();
    }
    
    public String get(String param1String) {
      return this.mMap.get(param1String);
    }
    
    public String getAntibanding() {
      return get("antibanding");
    }
    
    public boolean getAutoExposureLock() {
      return "true".equals(get("auto-exposure-lock"));
    }
    
    public boolean getAutoWhiteBalanceLock() {
      return "true".equals(get("auto-whitebalance-lock"));
    }
    
    public String getColorEffect() {
      return get("effect");
    }
    
    public int getExposureCompensation() {
      return getInt("exposure-compensation", 0);
    }
    
    public float getExposureCompensationStep() {
      return getFloat("exposure-compensation-step", 0.0F);
    }
    
    public String getFlashMode() {
      return get("flash-mode");
    }
    
    public float getFocalLength() {
      return Float.parseFloat(get("focal-length"));
    }
    
    public List<Camera.Area> getFocusAreas() {
      return splitArea(get("focus-areas"));
    }
    
    public void getFocusDistances(float[] param1ArrayOffloat) {
      if (param1ArrayOffloat != null && param1ArrayOffloat.length == 3) {
        splitFloat(get("focus-distances"), param1ArrayOffloat);
        return;
      } 
      throw new IllegalArgumentException("output must be a float array with three elements.");
    }
    
    public String getFocusMode() {
      return get("focus-mode");
    }
    
    public float getHorizontalViewAngle() {
      return Float.parseFloat(get("horizontal-view-angle"));
    }
    
    public int getInt(String param1String) {
      return Integer.parseInt(this.mMap.get(param1String));
    }
    
    public int getJpegQuality() {
      return getInt("jpeg-quality");
    }
    
    public int getJpegThumbnailQuality() {
      return getInt("jpeg-thumbnail-quality");
    }
    
    public Camera.Size getJpegThumbnailSize() {
      return new Camera.Size(getInt("jpeg-thumbnail-width"), getInt("jpeg-thumbnail-height"));
    }
    
    public int getMaxExposureCompensation() {
      return getInt("max-exposure-compensation", 0);
    }
    
    public int getMaxNumDetectedFaces() {
      return getInt("max-num-detected-faces-hw", 0);
    }
    
    public int getMaxNumFocusAreas() {
      return getInt("max-num-focus-areas", 0);
    }
    
    public int getMaxNumMeteringAreas() {
      return getInt("max-num-metering-areas", 0);
    }
    
    public int getMaxZoom() {
      return getInt("max-zoom", 0);
    }
    
    public List<Camera.Area> getMeteringAreas() {
      return splitArea(get("metering-areas"));
    }
    
    public int getMinExposureCompensation() {
      return getInt("min-exposure-compensation", 0);
    }
    
    public int getPictureFormat() {
      return pixelFormatForCameraFormat(get("picture-format"));
    }
    
    public Camera.Size getPictureSize() {
      return strToSize(get("picture-size"));
    }
    
    public Camera.Size getPreferredPreviewSizeForVideo() {
      return strToSize(get("preferred-preview-size-for-video"));
    }
    
    public int getPreviewFormat() {
      return pixelFormatForCameraFormat(get("preview-format"));
    }
    
    public void getPreviewFpsRange(int[] param1ArrayOfint) {
      if (param1ArrayOfint != null && param1ArrayOfint.length == 2) {
        splitInt(get("preview-fps-range"), param1ArrayOfint);
        return;
      } 
      throw new IllegalArgumentException("range must be an array with two elements.");
    }
    
    @Deprecated
    public int getPreviewFrameRate() {
      return getInt("preview-frame-rate");
    }
    
    public Camera.Size getPreviewSize() {
      return strToSize(get("preview-size"));
    }
    
    public String getSceneMode() {
      return get("scene-mode");
    }
    
    public List<String> getSupportedAntibanding() {
      return split(get("antibanding-values"));
    }
    
    public List<String> getSupportedColorEffects() {
      return split(get("effect-values"));
    }
    
    public List<String> getSupportedFlashModes() {
      return split(get("flash-mode-values"));
    }
    
    public List<String> getSupportedFocusModes() {
      return split(get("focus-mode-values"));
    }
    
    public List<Camera.Size> getSupportedJpegThumbnailSizes() {
      return splitSize(get("jpeg-thumbnail-size-values"));
    }
    
    public List<Integer> getSupportedPictureFormats() {
      String str = get("picture-format-values");
      ArrayList<Integer> arrayList = new ArrayList();
      Iterator<String> iterator = split(str).iterator();
      while (iterator.hasNext()) {
        int i = pixelFormatForCameraFormat(iterator.next());
        if (i == 0)
          continue; 
        arrayList.add(Integer.valueOf(i));
      } 
      return arrayList;
    }
    
    public List<Camera.Size> getSupportedPictureSizes() {
      return splitSize(get("picture-size-values"));
    }
    
    public List<Integer> getSupportedPreviewFormats() {
      String str = get("preview-format-values");
      ArrayList<Integer> arrayList = new ArrayList();
      Iterator<String> iterator = split(str).iterator();
      while (iterator.hasNext()) {
        int i = pixelFormatForCameraFormat(iterator.next());
        if (i == 0)
          continue; 
        arrayList.add(Integer.valueOf(i));
      } 
      return arrayList;
    }
    
    public List<int[]> getSupportedPreviewFpsRange() {
      return (List<int[]>)splitRange(get("preview-fps-range-values"));
    }
    
    @Deprecated
    public List<Integer> getSupportedPreviewFrameRates() {
      return splitInt(get("preview-frame-rate-values"));
    }
    
    public List<Camera.Size> getSupportedPreviewSizes() {
      return splitSize(get("preview-size-values"));
    }
    
    public List<String> getSupportedSceneModes() {
      return split(get("scene-mode-values"));
    }
    
    public List<Camera.Size> getSupportedVideoSizes() {
      return splitSize(get("video-size-values"));
    }
    
    public List<String> getSupportedWhiteBalance() {
      return split(get("whitebalance-values"));
    }
    
    public float getVerticalViewAngle() {
      return Float.parseFloat(get("vertical-view-angle"));
    }
    
    public boolean getVideoStabilization() {
      return "true".equals(get("video-stabilization"));
    }
    
    public String getWhiteBalance() {
      return get("whitebalance");
    }
    
    public int getZoom() {
      return getInt("zoom", 0);
    }
    
    public List<Integer> getZoomRatios() {
      return splitInt(get("zoom-ratios"));
    }
    
    public boolean isAutoExposureLockSupported() {
      return "true".equals(get("auto-exposure-lock-supported"));
    }
    
    public boolean isAutoWhiteBalanceLockSupported() {
      return "true".equals(get("auto-whitebalance-lock-supported"));
    }
    
    public boolean isSmoothZoomSupported() {
      return "true".equals(get("smooth-zoom-supported"));
    }
    
    public boolean isVideoSnapshotSupported() {
      return "true".equals(get("video-snapshot-supported"));
    }
    
    public boolean isVideoStabilizationSupported() {
      return "true".equals(get("video-stabilization-supported"));
    }
    
    public boolean isZoomSupported() {
      return "true".equals(get("zoom-supported"));
    }
    
    public void remove(String param1String) {
      this.mMap.remove(param1String);
    }
    
    public void removeGpsData() {
      remove("gps-latitude");
      remove("gps-longitude");
      remove("gps-altitude");
      remove("gps-timestamp");
      remove("gps-processing-method");
    }
    
    public boolean same(Parameters param1Parameters) {
      boolean bool = true;
      if (this == param1Parameters)
        return true; 
      if (param1Parameters == null || !this.mMap.equals(param1Parameters.mMap))
        bool = false; 
      return bool;
    }
    
    public void set(String param1String, int param1Int) {
      put(param1String, Integer.toString(param1Int));
    }
    
    public void set(String param1String1, String param1String2) {
      StringBuilder stringBuilder1;
      StringBuilder stringBuilder2;
      if (param1String1.indexOf('=') != -1 || param1String1.indexOf(';') != -1 || param1String1.indexOf(false) != -1) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Key \"");
        stringBuilder2.append(param1String1);
        stringBuilder2.append("\" contains invalid character (= or ; or \\0)");
        Log.e("Camera", stringBuilder2.toString());
        return;
      } 
      if (stringBuilder2.indexOf('=') != -1 || stringBuilder2.indexOf(';') != -1 || stringBuilder2.indexOf(false) != -1) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Value \"");
        stringBuilder1.append((String)stringBuilder2);
        stringBuilder1.append("\" contains invalid character (= or ; or \\0)");
        Log.e("Camera", stringBuilder1.toString());
        return;
      } 
      put((String)stringBuilder1, (String)stringBuilder2);
    }
    
    public void setAntibanding(String param1String) {
      set("antibanding", param1String);
    }
    
    public void setAutoExposureLock(boolean param1Boolean) {
      String str;
      if (param1Boolean) {
        str = "true";
      } else {
        str = "false";
      } 
      set("auto-exposure-lock", str);
    }
    
    public void setAutoWhiteBalanceLock(boolean param1Boolean) {
      String str;
      if (param1Boolean) {
        str = "true";
      } else {
        str = "false";
      } 
      set("auto-whitebalance-lock", str);
    }
    
    public void setColorEffect(String param1String) {
      set("effect", param1String);
    }
    
    public void setExposureCompensation(int param1Int) {
      set("exposure-compensation", param1Int);
    }
    
    public void setFlashMode(String param1String) {
      set("flash-mode", param1String);
    }
    
    public void setFocusAreas(List<Camera.Area> param1List) {
      set("focus-areas", param1List);
    }
    
    public void setFocusMode(String param1String) {
      set("focus-mode", param1String);
    }
    
    public void setGpsAltitude(double param1Double) {
      set("gps-altitude", Double.toString(param1Double));
    }
    
    public void setGpsLatitude(double param1Double) {
      set("gps-latitude", Double.toString(param1Double));
    }
    
    public void setGpsLongitude(double param1Double) {
      set("gps-longitude", Double.toString(param1Double));
    }
    
    public void setGpsProcessingMethod(String param1String) {
      set("gps-processing-method", param1String);
    }
    
    public void setGpsTimestamp(long param1Long) {
      set("gps-timestamp", Long.toString(param1Long));
    }
    
    public void setJpegQuality(int param1Int) {
      set("jpeg-quality", param1Int);
    }
    
    public void setJpegThumbnailQuality(int param1Int) {
      set("jpeg-thumbnail-quality", param1Int);
    }
    
    public void setJpegThumbnailSize(int param1Int1, int param1Int2) {
      set("jpeg-thumbnail-width", param1Int1);
      set("jpeg-thumbnail-height", param1Int2);
    }
    
    public void setMeteringAreas(List<Camera.Area> param1List) {
      set("metering-areas", param1List);
    }
    
    public void setPictureFormat(int param1Int) {
      String str = cameraFormatForPixelFormat(param1Int);
      if (str != null) {
        set("picture-format", str);
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid pixel_format=");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public void setPictureSize(int param1Int1, int param1Int2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(Integer.toString(param1Int1));
      stringBuilder.append("x");
      stringBuilder.append(Integer.toString(param1Int2));
      set("picture-size", stringBuilder.toString());
    }
    
    public void setPreviewFormat(int param1Int) {
      String str = cameraFormatForPixelFormat(param1Int);
      if (str != null) {
        set("preview-format", str);
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid pixel_format=");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public void setPreviewFpsRange(int param1Int1, int param1Int2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(param1Int1);
      stringBuilder.append(",");
      stringBuilder.append(param1Int2);
      set("preview-fps-range", stringBuilder.toString());
    }
    
    @Deprecated
    public void setPreviewFrameRate(int param1Int) {
      set("preview-frame-rate", param1Int);
    }
    
    public void setPreviewSize(int param1Int1, int param1Int2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(Integer.toString(param1Int1));
      stringBuilder.append("x");
      stringBuilder.append(Integer.toString(param1Int2));
      set("preview-size", stringBuilder.toString());
    }
    
    public void setRecordingHint(boolean param1Boolean) {
      String str;
      if (param1Boolean) {
        str = "true";
      } else {
        str = "false";
      } 
      set("recording-hint", str);
    }
    
    public void setRotation(int param1Int) {
      if (param1Int == 0 || param1Int == 90 || param1Int == 180 || param1Int == 270) {
        set("rotation", Integer.toString(param1Int));
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid rotation=");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public void setSceneMode(String param1String) {
      set("scene-mode", param1String);
    }
    
    public void setVideoStabilization(boolean param1Boolean) {
      String str;
      if (param1Boolean) {
        str = "true";
      } else {
        str = "false";
      } 
      set("video-stabilization", str);
    }
    
    public void setWhiteBalance(String param1String) {
      if (same(param1String, get("whitebalance")))
        return; 
      set("whitebalance", param1String);
      set("auto-whitebalance-lock", "false");
    }
    
    public void setZoom(int param1Int) {
      set("zoom", param1Int);
    }
    
    public void unflatten(String param1String) {
      this.mMap.clear();
      TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(';');
      simpleStringSplitter.setString(param1String);
      for (String str : simpleStringSplitter) {
        int i = str.indexOf('=');
        if (i == -1)
          continue; 
        param1String = str.substring(0, i);
        str = str.substring(i + 1);
        this.mMap.put(param1String, str);
      } 
    }
  }
  
  @Deprecated
  public static interface PictureCallback {
    void onPictureTaken(byte[] param1ArrayOfbyte, Camera param1Camera);
  }
  
  @Deprecated
  public static interface PreviewCallback {
    void onPreviewFrame(byte[] param1ArrayOfbyte, Camera param1Camera);
  }
  
  @Deprecated
  public static interface ShutterCallback {
    void onShutter();
  }
  
  @Deprecated
  public class Size {
    public int height;
    
    public int width;
    
    public Size(int param1Int1, int param1Int2) {
      this.width = param1Int1;
      this.height = param1Int2;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = param1Object instanceof Size;
      boolean bool1 = false;
      if (!bool)
        return false; 
      param1Object = param1Object;
      bool = bool1;
      if (this.width == ((Size)param1Object).width) {
        bool = bool1;
        if (this.height == ((Size)param1Object).height)
          bool = true; 
      } 
      return bool;
    }
    
    public int hashCode() {
      return this.width * 32713 + this.height;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/Camera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */