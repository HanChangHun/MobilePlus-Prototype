package android.hardware.camera2;

import android.content.Context;
import android.hardware.CameraStatus;
import android.hardware.ICameraService;
import android.hardware.ICameraServiceListener;
import android.hardware.camera2.impl.CameraDeviceImpl;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.legacy.LegacyMetadataMapper;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.display.DisplayManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class CameraManager {
  private static final int API_VERSION_1 = 1;
  
  private static final int API_VERSION_2 = 2;
  
  private static final int CAMERA_TYPE_ALL = 1;
  
  private static final int CAMERA_TYPE_BACKWARD_COMPATIBLE = 0;
  
  private static final String TAG = "CameraManager";
  
  private static final int USE_CALLING_UID = -1;
  
  private final boolean DEBUG;
  
  private final Context mContext;
  
  private ArrayList<String> mDeviceIdList;
  
  private final Object mLock;
  
  public CameraManager(Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_0
    //   5: iconst_0
    //   6: putfield DEBUG : Z
    //   9: new java/lang/Object
    //   12: dup
    //   13: invokespecial <init> : ()V
    //   16: astore_2
    //   17: aload_0
    //   18: aload_2
    //   19: putfield mLock : Ljava/lang/Object;
    //   22: aload_2
    //   23: monitorenter
    //   24: aload_0
    //   25: aload_1
    //   26: putfield mContext : Landroid/content/Context;
    //   29: aload_2
    //   30: monitorexit
    //   31: return
    //   32: astore_1
    //   33: aload_2
    //   34: monitorexit
    //   35: aload_1
    //   36: athrow
    // Exception table:
    //   from	to	target	type
    //   24	31	32	finally
    //   33	35	32	finally
  }
  
  private Size getDisplaySize() {
    Size size = new Size(0, 0);
    try {
      Display display = ((DisplayManager)this.mContext.getSystemService("display")).getDisplay(0);
      if (display != null) {
        int i = display.getWidth();
        int j = display.getHeight();
        int k = i;
        int m = j;
        if (j > i) {
          m = i;
          k = display.getHeight();
        } 
        Size size1 = new Size();
        this(k, m);
        size = size1;
      } else {
        Log.e("CameraManager", "Invalid default display!");
      } 
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getDisplaySize Failed. ");
      stringBuilder.append(exception.toString());
      Log.e("CameraManager", stringBuilder.toString());
    } 
    return size;
  }
  
  public static boolean isHiddenPhysicalCamera(String paramString) {
    try {
      ICameraService iCameraService = CameraManagerGlobal.get().getCameraService();
      return (iCameraService == null) ? false : iCameraService.isHiddenPhysicalCamera(paramString);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  private CameraDevice openCameraDeviceUserAsync(String paramString, CameraDevice.StateCallback paramStateCallback, Executor paramExecutor, int paramInt) throws CameraAccessException {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual getCameraCharacteristics : (Ljava/lang/String;)Landroid/hardware/camera2/CameraCharacteristics;
    //   5: astore #5
    //   7: aload_0
    //   8: getfield mLock : Ljava/lang/Object;
    //   11: astore #6
    //   13: aload #6
    //   15: monitorenter
    //   16: aconst_null
    //   17: astore #7
    //   19: new android/hardware/camera2/impl/CameraDeviceImpl
    //   22: astore #8
    //   24: aload #8
    //   26: aload_1
    //   27: aload_2
    //   28: aload_3
    //   29: aload #5
    //   31: aload_0
    //   32: getfield mContext : Landroid/content/Context;
    //   35: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   38: getfield targetSdkVersion : I
    //   41: invokespecial <init> : (Ljava/lang/String;Landroid/hardware/camera2/CameraDevice$StateCallback;Ljava/util/concurrent/Executor;Landroid/hardware/camera2/CameraCharacteristics;I)V
    //   44: aload #8
    //   46: invokevirtual getCallbacks : ()Landroid/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks;
    //   49: astore_2
    //   50: aload_0
    //   51: aload_1
    //   52: invokespecial supportsCamera2ApiLocked : (Ljava/lang/String;)Z
    //   55: ifeq -> 110
    //   58: invokestatic get : ()Landroid/hardware/camera2/CameraManager$CameraManagerGlobal;
    //   61: invokevirtual getCameraService : ()Landroid/hardware/ICameraService;
    //   64: astore_3
    //   65: aload_3
    //   66: ifnull -> 97
    //   69: aload_3
    //   70: aload_2
    //   71: aload_1
    //   72: aload_0
    //   73: getfield mContext : Landroid/content/Context;
    //   76: invokevirtual getOpPackageName : ()Ljava/lang/String;
    //   79: aload_0
    //   80: getfield mContext : Landroid/content/Context;
    //   83: invokevirtual getAttributionTag : ()Ljava/lang/String;
    //   86: iload #4
    //   88: invokeinterface connectDevice : (Landroid/hardware/camera2/ICameraDeviceCallbacks;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)Landroid/hardware/camera2/ICameraDeviceUser;
    //   93: astore_1
    //   94: goto -> 135
    //   97: new android/os/ServiceSpecificException
    //   100: astore_1
    //   101: aload_1
    //   102: iconst_4
    //   103: ldc 'Camera service is currently unavailable'
    //   105: invokespecial <init> : (ILjava/lang/String;)V
    //   108: aload_1
    //   109: athrow
    //   110: aload_1
    //   111: invokestatic parseInt : (Ljava/lang/String;)I
    //   114: istore #4
    //   116: ldc 'CameraManager'
    //   118: ldc 'Using legacy camera HAL.'
    //   120: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   123: pop
    //   124: aload_2
    //   125: iload #4
    //   127: aload_0
    //   128: invokespecial getDisplaySize : ()Landroid/util/Size;
    //   131: invokestatic connectBinderShim : (Landroid/hardware/camera2/ICameraDeviceCallbacks;ILandroid/util/Size;)Landroid/hardware/camera2/legacy/CameraDeviceUserShim;
    //   134: astore_1
    //   135: goto -> 313
    //   138: astore_2
    //   139: new java/lang/IllegalArgumentException
    //   142: astore_3
    //   143: new java/lang/StringBuilder
    //   146: astore_2
    //   147: aload_2
    //   148: invokespecial <init> : ()V
    //   151: aload_2
    //   152: ldc 'Expected cameraId to be numeric, but it was: '
    //   154: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: pop
    //   158: aload_2
    //   159: aload_1
    //   160: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: pop
    //   164: aload_3
    //   165: aload_2
    //   166: invokevirtual toString : ()Ljava/lang/String;
    //   169: invokespecial <init> : (Ljava/lang/String;)V
    //   172: aload_3
    //   173: athrow
    //   174: astore_1
    //   175: goto -> 183
    //   178: astore_1
    //   179: goto -> 211
    //   182: astore_1
    //   183: new android/os/ServiceSpecificException
    //   186: astore_1
    //   187: aload_1
    //   188: iconst_4
    //   189: ldc 'Camera service is currently unavailable'
    //   191: invokespecial <init> : (ILjava/lang/String;)V
    //   194: aload #8
    //   196: aload_1
    //   197: invokevirtual setRemoteFailure : (Landroid/os/ServiceSpecificException;)V
    //   200: aload_1
    //   201: invokestatic throwAsPublicException : (Ljava/lang/Throwable;)V
    //   204: aload #7
    //   206: astore_1
    //   207: goto -> 313
    //   210: astore_1
    //   211: aload_1
    //   212: getfield errorCode : I
    //   215: bipush #9
    //   217: if_icmpeq -> 325
    //   220: aload_1
    //   221: getfield errorCode : I
    //   224: bipush #7
    //   226: if_icmpeq -> 274
    //   229: aload_1
    //   230: getfield errorCode : I
    //   233: bipush #8
    //   235: if_icmpeq -> 274
    //   238: aload_1
    //   239: getfield errorCode : I
    //   242: bipush #6
    //   244: if_icmpeq -> 274
    //   247: aload_1
    //   248: getfield errorCode : I
    //   251: iconst_4
    //   252: if_icmpeq -> 274
    //   255: aload_1
    //   256: getfield errorCode : I
    //   259: bipush #10
    //   261: if_icmpne -> 267
    //   264: goto -> 274
    //   267: aload_1
    //   268: invokestatic throwAsPublicException : (Ljava/lang/Throwable;)V
    //   271: goto -> 310
    //   274: aload #8
    //   276: aload_1
    //   277: invokevirtual setRemoteFailure : (Landroid/os/ServiceSpecificException;)V
    //   280: aload_1
    //   281: getfield errorCode : I
    //   284: bipush #6
    //   286: if_icmpeq -> 306
    //   289: aload_1
    //   290: getfield errorCode : I
    //   293: iconst_4
    //   294: if_icmpeq -> 306
    //   297: aload_1
    //   298: getfield errorCode : I
    //   301: bipush #7
    //   303: if_icmpne -> 310
    //   306: aload_1
    //   307: invokestatic throwAsPublicException : (Ljava/lang/Throwable;)V
    //   310: aload #7
    //   312: astore_1
    //   313: aload #8
    //   315: aload_1
    //   316: invokevirtual setRemoteDevice : (Landroid/hardware/camera2/ICameraDeviceUser;)V
    //   319: aload #6
    //   321: monitorexit
    //   322: aload #8
    //   324: areturn
    //   325: new java/lang/AssertionError
    //   328: astore_1
    //   329: aload_1
    //   330: ldc 'Should've gone down the shim path'
    //   332: invokespecial <init> : (Ljava/lang/Object;)V
    //   335: aload_1
    //   336: athrow
    //   337: astore_1
    //   338: aload #6
    //   340: monitorexit
    //   341: aload_1
    //   342: athrow
    //   343: astore_1
    //   344: goto -> 338
    // Exception table:
    //   from	to	target	type
    //   19	50	337	finally
    //   50	65	210	android/os/ServiceSpecificException
    //   50	65	182	android/os/RemoteException
    //   50	65	337	finally
    //   69	94	210	android/os/ServiceSpecificException
    //   69	94	182	android/os/RemoteException
    //   69	94	337	finally
    //   97	110	210	android/os/ServiceSpecificException
    //   97	110	182	android/os/RemoteException
    //   97	110	337	finally
    //   110	116	138	java/lang/NumberFormatException
    //   110	116	210	android/os/ServiceSpecificException
    //   110	116	182	android/os/RemoteException
    //   110	116	337	finally
    //   116	124	210	android/os/ServiceSpecificException
    //   116	124	182	android/os/RemoteException
    //   116	124	337	finally
    //   124	135	210	android/os/ServiceSpecificException
    //   124	135	182	android/os/RemoteException
    //   124	135	337	finally
    //   139	158	210	android/os/ServiceSpecificException
    //   139	158	182	android/os/RemoteException
    //   139	158	337	finally
    //   158	174	178	android/os/ServiceSpecificException
    //   158	174	174	android/os/RemoteException
    //   158	174	343	finally
    //   183	204	343	finally
    //   211	264	343	finally
    //   267	271	343	finally
    //   274	306	343	finally
    //   306	310	343	finally
    //   313	322	343	finally
    //   325	337	343	finally
    //   338	341	343	finally
  }
  
  private boolean supportsCamera2ApiLocked(String paramString) {
    return supportsCameraApiLocked(paramString, 2);
  }
  
  private boolean supportsCameraApiLocked(String paramString, int paramInt) {
    try {
      ICameraService iCameraService = CameraManagerGlobal.get().getCameraService();
      return (iCameraService == null) ? false : iCameraService.supportsCameraApi(paramString, paramInt);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public static void throwAsPublicException(Throwable paramThrowable) throws CameraAccessException {
    ServiceSpecificException serviceSpecificException;
    if (paramThrowable instanceof ServiceSpecificException) {
      char c;
      serviceSpecificException = (ServiceSpecificException)paramThrowable;
      switch (serviceSpecificException.errorCode) {
        default:
          c = '\003';
          throw new CameraAccessException(c, serviceSpecificException.getMessage(), serviceSpecificException);
        case 9:
          c = 'Ϩ';
          throw new CameraAccessException(c, serviceSpecificException.getMessage(), serviceSpecificException);
        case 8:
          c = '\005';
          throw new CameraAccessException(c, serviceSpecificException.getMessage(), serviceSpecificException);
        case 7:
          c = '\004';
          throw new CameraAccessException(c, serviceSpecificException.getMessage(), serviceSpecificException);
        case 6:
          c = '\001';
          throw new CameraAccessException(c, serviceSpecificException.getMessage(), serviceSpecificException);
        case 4:
          c = '\002';
          throw new CameraAccessException(c, serviceSpecificException.getMessage(), serviceSpecificException);
        case 2:
        case 3:
          throw new IllegalArgumentException(serviceSpecificException.getMessage(), serviceSpecificException);
        case 1:
          break;
      } 
      throw new SecurityException(serviceSpecificException.getMessage(), serviceSpecificException);
    } 
    if (!(serviceSpecificException instanceof android.os.DeadObjectException)) {
      if (!(serviceSpecificException instanceof RemoteException)) {
        if (!(serviceSpecificException instanceof RuntimeException))
          return; 
        throw (RuntimeException)serviceSpecificException;
      } 
      throw new UnsupportedOperationException("An unknown RemoteException was thrown which should never happen.", serviceSpecificException);
    } 
    throw new CameraAccessException(2, "Camera service has died unexpectedly", serviceSpecificException);
  }
  
  public CameraCharacteristics getCameraCharacteristics(String paramString) throws CameraAccessException {
    ServiceSpecificException serviceSpecificException = null;
    if (!CameraManagerGlobal.sCameraServiceDisabled)
      synchronized (this.mLock) {
        ICameraService iCameraService = CameraManagerGlobal.get().getCameraService();
        if (iCameraService != null) {
          CameraAccessException cameraAccessException1;
          try {
            CameraCharacteristics cameraCharacteristics;
            Size size = getDisplaySize();
            if (!isHiddenPhysicalCamera(paramString) && !supportsCamera2ApiLocked(paramString)) {
              int i = Integer.parseInt(paramString);
              cameraCharacteristics = LegacyMetadataMapper.createCharacteristics(iCameraService.getLegacyParameters(i), iCameraService.getCameraInfo(i), i, size);
            } else {
              CameraMetadataNative cameraMetadataNative = iCameraService.getCameraCharacteristics((String)cameraCharacteristics);
              try {
                cameraMetadataNative.setCameraId(Integer.parseInt((String)cameraCharacteristics));
              } catch (NumberFormatException numberFormatException) {
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("Failed to parse camera Id ");
                stringBuilder.append((String)cameraCharacteristics);
                stringBuilder.append(" to integer");
                Log.v("CameraManager", stringBuilder.toString());
              } 
              cameraMetadataNative.setHasMandatoryConcurrentStreams(CameraManagerGlobal.get().cameraIdHasConcurrentStreamsLocked((String)cameraCharacteristics));
              cameraMetadataNative.setDisplaySize(size);
              cameraCharacteristics = new CameraCharacteristics(cameraMetadataNative);
            } 
          } catch (ServiceSpecificException serviceSpecificException1) {
            throwAsPublicException((Throwable)serviceSpecificException1);
            serviceSpecificException1 = serviceSpecificException;
          } catch (RemoteException remoteException) {
            cameraAccessException1 = new CameraAccessException();
            this(2, "Camera service is currently unavailable", (Throwable)remoteException);
            throw cameraAccessException1;
          } 
          return (CameraCharacteristics)cameraAccessException1;
        } 
        CameraAccessException cameraAccessException = new CameraAccessException();
        this(2, "Camera service is currently unavailable");
        throw cameraAccessException;
      }  
    throw new IllegalArgumentException("No cameras available on device");
  }
  
  public String[] getCameraIdList() throws CameraAccessException {
    return CameraManagerGlobal.get().getCameraIdList();
  }
  
  public String[] getCameraIdListNoLazy() throws CameraAccessException {
    return CameraManagerGlobal.get().getCameraIdListNoLazy();
  }
  
  public Set<Set<String>> getConcurrentCameraIds() throws CameraAccessException {
    return CameraManagerGlobal.get().getConcurrentCameraIds();
  }
  
  public boolean isConcurrentSessionConfigurationSupported(Map<String, SessionConfiguration> paramMap) throws CameraAccessException {
    return CameraManagerGlobal.get().isConcurrentSessionConfigurationSupported(paramMap);
  }
  
  public void openCamera(String paramString, CameraDevice.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException {
    openCameraForUid(paramString, paramStateCallback, CameraDeviceImpl.checkAndWrapHandler(paramHandler), -1);
  }
  
  public void openCamera(String paramString, Executor paramExecutor, CameraDevice.StateCallback paramStateCallback) throws CameraAccessException {
    if (paramExecutor != null) {
      openCameraForUid(paramString, paramStateCallback, paramExecutor, -1);
      return;
    } 
    throw new IllegalArgumentException("executor was null");
  }
  
  public void openCameraForUid(String paramString, CameraDevice.StateCallback paramStateCallback, Executor paramExecutor, int paramInt) throws CameraAccessException {
    if (paramString != null) {
      if (paramStateCallback != null) {
        if (!CameraManagerGlobal.sCameraServiceDisabled) {
          openCameraDeviceUserAsync(paramString, paramStateCallback, paramExecutor, paramInt);
          return;
        } 
        throw new IllegalArgumentException("No cameras available on device");
      } 
      throw new IllegalArgumentException("callback was null");
    } 
    throw new IllegalArgumentException("cameraId was null");
  }
  
  public void registerAvailabilityCallback(AvailabilityCallback paramAvailabilityCallback, Handler paramHandler) {
    CameraManagerGlobal.get().registerAvailabilityCallback(paramAvailabilityCallback, CameraDeviceImpl.checkAndWrapHandler(paramHandler));
  }
  
  public void registerAvailabilityCallback(Executor paramExecutor, AvailabilityCallback paramAvailabilityCallback) {
    if (paramExecutor != null) {
      CameraManagerGlobal.get().registerAvailabilityCallback(paramAvailabilityCallback, paramExecutor);
      return;
    } 
    throw new IllegalArgumentException("executor was null");
  }
  
  public void registerTorchCallback(TorchCallback paramTorchCallback, Handler paramHandler) {
    CameraManagerGlobal.get().registerTorchCallback(paramTorchCallback, CameraDeviceImpl.checkAndWrapHandler(paramHandler));
  }
  
  public void registerTorchCallback(Executor paramExecutor, TorchCallback paramTorchCallback) {
    if (paramExecutor != null) {
      CameraManagerGlobal.get().registerTorchCallback(paramTorchCallback, paramExecutor);
      return;
    } 
    throw new IllegalArgumentException("executor was null");
  }
  
  public void setTorchMode(String paramString, boolean paramBoolean) throws CameraAccessException {
    if (!CameraManagerGlobal.sCameraServiceDisabled) {
      CameraManagerGlobal.get().setTorchMode(paramString, paramBoolean);
      return;
    } 
    throw new IllegalArgumentException("No cameras available on device");
  }
  
  public void unregisterAvailabilityCallback(AvailabilityCallback paramAvailabilityCallback) {
    CameraManagerGlobal.get().unregisterAvailabilityCallback(paramAvailabilityCallback);
  }
  
  public void unregisterTorchCallback(TorchCallback paramTorchCallback) {
    CameraManagerGlobal.get().unregisterTorchCallback(paramTorchCallback);
  }
  
  public static abstract class AvailabilityCallback {
    public void onCameraAccessPrioritiesChanged() {}
    
    public void onCameraAvailable(String param1String) {}
    
    public void onCameraClosed(String param1String) {}
    
    public void onCameraOpened(String param1String1, String param1String2) {}
    
    public void onCameraUnavailable(String param1String) {}
    
    public void onPhysicalCameraAvailable(String param1String1, String param1String2) {}
    
    public void onPhysicalCameraUnavailable(String param1String1, String param1String2) {}
  }
  
  private static final class CameraManagerGlobal extends ICameraServiceListener.Stub implements IBinder.DeathRecipient {
    private static final String CAMERA_SERVICE_BINDER_NAME = "media.camera";
    
    private static final String TAG = "CameraManagerGlobal";
    
    private static final CameraManagerGlobal gCameraManager = new CameraManagerGlobal();
    
    public static final boolean sCameraServiceDisabled = SystemProperties.getBoolean("config.disable_cameraservice", false);
    
    private final int CAMERA_SERVICE_RECONNECT_DELAY_MS = 1000;
    
    private final boolean DEBUG = false;
    
    private final ArrayMap<CameraManager.AvailabilityCallback, Executor> mCallbackMap = new ArrayMap();
    
    private ICameraService mCameraService;
    
    private final Set<Set<String>> mConcurrentCameraIdCombinations = (Set<Set<String>>)new ArraySet();
    
    private final ArrayMap<String, Integer> mDeviceStatus = new ArrayMap();
    
    private final Object mLock = new Object();
    
    private final ScheduledExecutorService mScheduler = Executors.newScheduledThreadPool(1);
    
    private final ArrayMap<CameraManager.TorchCallback, Executor> mTorchCallbackMap = new ArrayMap();
    
    private Binder mTorchClientBinder = new Binder();
    
    private final ArrayMap<String, Integer> mTorchStatus = new ArrayMap();
    
    private final ArrayMap<String, ArrayList<String>> mUnavailablePhysicalDevices = new ArrayMap();
    
    public static boolean cameraStatusesContains(CameraStatus[] param1ArrayOfCameraStatus, String param1String) {
      int i = param1ArrayOfCameraStatus.length;
      for (byte b = 0; b < i; b++) {
        if ((param1ArrayOfCameraStatus[b]).cameraId.equals(param1String))
          return true; 
      } 
      return false;
    }
    
    private void connectCameraServiceLocked() {
      // Byte code:
      //   0: aload_0
      //   1: getfield mCameraService : Landroid/hardware/ICameraService;
      //   4: ifnonnull -> 248
      //   7: getstatic android/hardware/camera2/CameraManager$CameraManagerGlobal.sCameraServiceDisabled : Z
      //   10: ifeq -> 16
      //   13: goto -> 248
      //   16: ldc 'CameraManagerGlobal'
      //   18: ldc 'Connecting to camera service'
      //   20: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
      //   23: pop
      //   24: ldc 'media.camera'
      //   26: invokestatic getService : (Ljava/lang/String;)Landroid/os/IBinder;
      //   29: astore_1
      //   30: aload_1
      //   31: ifnonnull -> 35
      //   34: return
      //   35: iconst_0
      //   36: istore_2
      //   37: aload_1
      //   38: aload_0
      //   39: iconst_0
      //   40: invokeinterface linkToDeath : (Landroid/os/IBinder$DeathRecipient;I)V
      //   45: aload_1
      //   46: invokestatic asInterface : (Landroid/os/IBinder;)Landroid/hardware/ICameraService;
      //   49: astore_1
      //   50: invokestatic setupGlobalVendorTagDescriptor : ()V
      //   53: goto -> 62
      //   56: astore_3
      //   57: aload_0
      //   58: aload_3
      //   59: invokespecial handleRecoverableSetupErrors : (Landroid/os/ServiceSpecificException;)V
      //   62: aload_1
      //   63: aload_0
      //   64: invokeinterface addListener : (Landroid/hardware/ICameraServiceListener;)[Landroid/hardware/CameraStatus;
      //   69: astore #4
      //   71: aload #4
      //   73: arraylength
      //   74: istore #5
      //   76: iconst_0
      //   77: istore #6
      //   79: iload #6
      //   81: iload #5
      //   83: if_icmpge -> 162
      //   86: aload #4
      //   88: iload #6
      //   90: aaload
      //   91: astore_3
      //   92: aload_0
      //   93: aload_3
      //   94: getfield status : I
      //   97: aload_3
      //   98: getfield cameraId : Ljava/lang/String;
      //   101: invokespecial onStatusChangedLocked : (ILjava/lang/String;)V
      //   104: aload_3
      //   105: getfield unavailablePhysicalCameras : [Ljava/lang/String;
      //   108: ifnull -> 156
      //   111: aload_3
      //   112: getfield unavailablePhysicalCameras : [Ljava/lang/String;
      //   115: astore #7
      //   117: aload #7
      //   119: arraylength
      //   120: istore #8
      //   122: iconst_0
      //   123: istore #9
      //   125: iload #9
      //   127: iload #8
      //   129: if_icmpge -> 156
      //   132: aload #7
      //   134: iload #9
      //   136: aaload
      //   137: astore #10
      //   139: aload_0
      //   140: iconst_0
      //   141: aload_3
      //   142: getfield cameraId : Ljava/lang/String;
      //   145: aload #10
      //   147: invokespecial onPhysicalCameraStatusChangedLocked : (ILjava/lang/String;Ljava/lang/String;)V
      //   150: iinc #9, 1
      //   153: goto -> 125
      //   156: iinc #6, 1
      //   159: goto -> 79
      //   162: aload_0
      //   163: aload_1
      //   164: putfield mCameraService : Landroid/hardware/ICameraService;
      //   167: goto -> 171
      //   170: astore_3
      //   171: aload_1
      //   172: invokeinterface getConcurrentCameraIds : ()[Landroid/hardware/camera2/utils/ConcurrentCameraIdCombination;
      //   177: astore_3
      //   178: aload_3
      //   179: arraylength
      //   180: istore #9
      //   182: iload_2
      //   183: istore #6
      //   185: iload #6
      //   187: iload #9
      //   189: if_icmpge -> 217
      //   192: aload_3
      //   193: iload #6
      //   195: aaload
      //   196: astore_1
      //   197: aload_0
      //   198: getfield mConcurrentCameraIdCombinations : Ljava/util/Set;
      //   201: aload_1
      //   202: invokevirtual getConcurrentCameraIdCombination : ()Ljava/util/Set;
      //   205: invokeinterface add : (Ljava/lang/Object;)Z
      //   210: pop
      //   211: iinc #6, 1
      //   214: goto -> 185
      //   217: goto -> 221
      //   220: astore_1
      //   221: return
      //   222: astore_1
      //   223: new java/lang/IllegalStateException
      //   226: dup
      //   227: ldc 'Failed to get concurrent camera id combinations'
      //   229: aload_1
      //   230: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
      //   233: athrow
      //   234: astore_1
      //   235: new java/lang/IllegalStateException
      //   238: dup
      //   239: ldc 'Failed to register a camera service listener'
      //   241: aload_1
      //   242: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
      //   245: athrow
      //   246: astore_1
      //   247: return
      //   248: return
      // Exception table:
      //   from	to	target	type
      //   37	45	246	android/os/RemoteException
      //   50	53	56	android/os/ServiceSpecificException
      //   62	76	234	android/os/ServiceSpecificException
      //   62	76	170	android/os/RemoteException
      //   92	122	234	android/os/ServiceSpecificException
      //   92	122	170	android/os/RemoteException
      //   139	150	234	android/os/ServiceSpecificException
      //   139	150	170	android/os/RemoteException
      //   162	167	234	android/os/ServiceSpecificException
      //   162	167	170	android/os/RemoteException
      //   171	182	222	android/os/ServiceSpecificException
      //   171	182	220	android/os/RemoteException
      //   197	211	222	android/os/ServiceSpecificException
      //   197	211	220	android/os/RemoteException
    }
    
    private String[] extractCameraIdListLocked() {
      int i = 0;
      byte b = 0;
      while (b < this.mDeviceStatus.size()) {
        int j = ((Integer)this.mDeviceStatus.valueAt(b)).intValue();
        int k = i;
        if (j != 0)
          if (j == 2) {
            k = i;
          } else {
            k = i + 1;
          }  
        b++;
        i = k;
      } 
      String[] arrayOfString = new String[i];
      i = 0;
      b = 0;
      while (b < this.mDeviceStatus.size()) {
        int j = ((Integer)this.mDeviceStatus.valueAt(b)).intValue();
        int k = i;
        if (j != 0)
          if (j == 2) {
            k = i;
          } else {
            arrayOfString[i] = (String)this.mDeviceStatus.keyAt(b);
            k = i + 1;
          }  
        b++;
        i = k;
      } 
      return arrayOfString;
    }
    
    private Set<Set<String>> extractConcurrentCameraIdListLocked() {
      ArraySet<ArraySet<String>> arraySet = new ArraySet();
      for (Set<String> set : this.mConcurrentCameraIdCombinations) {
        ArraySet<String> arraySet1 = new ArraySet();
        for (String str : set) {
          Integer integer = (Integer)this.mDeviceStatus.get(str);
          if (integer == null || integer.intValue() == 2 || integer.intValue() == 0)
            continue; 
          arraySet1.add(str);
        } 
        arraySet.add(arraySet1);
      } 
      return (Set)arraySet;
    }
    
    public static CameraManagerGlobal get() {
      return gCameraManager;
    }
    
    private void handleRecoverableSetupErrors(ServiceSpecificException param1ServiceSpecificException) {
      if (param1ServiceSpecificException.errorCode == 4) {
        Log.w("CameraManagerGlobal", param1ServiceSpecificException.getMessage());
        return;
      } 
      throw new IllegalStateException(param1ServiceSpecificException);
    }
    
    private boolean isAvailable(int param1Int) {
      return !(param1Int != 1);
    }
    
    private void onPhysicalCameraStatusChangedLocked(int param1Int, String param1String1, String param1String2) {
      if (!validStatus(param1Int)) {
        Log.e("CameraManagerGlobal", String.format("Ignoring invalid device %s physical device %s status 0x%x", new Object[] { param1String1, param1String2, Integer.valueOf(param1Int) }));
        return;
      } 
      if (!this.mDeviceStatus.containsKey(param1String1) || !isAvailable(((Integer)this.mDeviceStatus.get(param1String1)).intValue()) || !this.mUnavailablePhysicalDevices.containsKey(param1String1)) {
        Log.e("CameraManagerGlobal", String.format("Camera %s is not available. Ignore physical camera status change", new Object[] { param1String1 }));
        return;
      } 
      ArrayList<String> arrayList = (ArrayList)this.mUnavailablePhysicalDevices.get(param1String1);
      if (!isAvailable(param1Int) && !arrayList.contains(param1String2)) {
        arrayList.add(param1String2);
      } else if (isAvailable(param1Int) && arrayList.contains(param1String2)) {
        arrayList.remove(param1String2);
      } else {
        return;
      } 
      int i = this.mCallbackMap.size();
      for (byte b = 0; b < i; b++) {
        Executor executor = (Executor)this.mCallbackMap.valueAt(b);
        postSingleUpdate((CameraManager.AvailabilityCallback)this.mCallbackMap.keyAt(b), executor, param1String1, param1String2, param1Int);
      } 
    }
    
    private void onStatusChangedLocked(int param1Int, String param1String) {
      Integer integer;
      if (!validStatus(param1Int)) {
        Log.e("CameraManagerGlobal", String.format("Ignoring invalid device %s status 0x%x", new Object[] { param1String, Integer.valueOf(param1Int) }));
        return;
      } 
      if (param1Int == 0) {
        integer = (Integer)this.mDeviceStatus.remove(param1String);
        this.mUnavailablePhysicalDevices.remove(param1String);
      } else {
        Integer integer1 = (Integer)this.mDeviceStatus.put(param1String, Integer.valueOf(param1Int));
        integer = integer1;
        if (integer1 == null) {
          this.mUnavailablePhysicalDevices.put(param1String, new ArrayList());
          integer = integer1;
        } 
      } 
      if (integer != null && integer.intValue() == param1Int)
        return; 
      if (integer != null && isAvailable(param1Int) == isAvailable(integer.intValue()))
        return; 
      int i = this.mCallbackMap.size();
      for (byte b = 0; b < i; b++) {
        Executor executor = (Executor)this.mCallbackMap.valueAt(b);
        postSingleUpdate((CameraManager.AvailabilityCallback)this.mCallbackMap.keyAt(b), executor, param1String, null, param1Int);
      } 
    }
    
    private void onTorchStatusChangedLocked(int param1Int, String param1String) {
      if (!validTorchStatus(param1Int)) {
        Log.e("CameraManagerGlobal", String.format("Ignoring invalid device %s torch status 0x%x", new Object[] { param1String, Integer.valueOf(param1Int) }));
        return;
      } 
      Integer integer = (Integer)this.mTorchStatus.put(param1String, Integer.valueOf(param1Int));
      if (integer != null && integer.intValue() == param1Int)
        return; 
      int i = this.mTorchCallbackMap.size();
      for (byte b = 0; b < i; b++) {
        Executor executor = (Executor)this.mTorchCallbackMap.valueAt(b);
        postSingleTorchUpdate((CameraManager.TorchCallback)this.mTorchCallbackMap.keyAt(b), executor, param1String, param1Int);
      } 
    }
    
    private void postSingleAccessPriorityChangeUpdate(CameraManager.AvailabilityCallback param1AvailabilityCallback, Executor param1Executor) {
      long l = Binder.clearCallingIdentity();
      try {
        Runnable runnable = new Runnable() {
            public void run() {
              callback.onCameraAccessPrioritiesChanged();
            }
          };
        super(this, param1AvailabilityCallback);
        param1Executor.execute(runnable);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    }
    
    private void postSingleCameraClosedUpdate(CameraManager.AvailabilityCallback param1AvailabilityCallback, Executor param1Executor, String param1String) {
      long l = Binder.clearCallingIdentity();
      try {
        Runnable runnable = new Runnable() {
            public void run() {
              callback.onCameraClosed(id);
            }
          };
        super(this, param1AvailabilityCallback, param1String);
        param1Executor.execute(runnable);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    }
    
    private void postSingleCameraOpenedUpdate(CameraManager.AvailabilityCallback param1AvailabilityCallback, Executor param1Executor, String param1String1, String param1String2) {
      long l = Binder.clearCallingIdentity();
      try {
        Runnable runnable = new Runnable() {
            public void run() {
              callback.onCameraOpened(id, packageId);
            }
          };
        super(this, param1AvailabilityCallback, param1String1, param1String2);
        param1Executor.execute(runnable);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    }
    
    private void postSingleTorchUpdate(CameraManager.TorchCallback param1TorchCallback, Executor param1Executor, String param1String, int param1Int) {
      if (param1Int != 1 && param1Int != 2) {
        long l = Binder.clearCallingIdentity();
        try {
          _$$Lambda$CameraManager$CameraManagerGlobal$6Ptxoe4wF_VCkE_pml8t66mklao _$$Lambda$CameraManager$CameraManagerGlobal$6Ptxoe4wF_VCkE_pml8t66mklao = new _$$Lambda$CameraManager$CameraManagerGlobal$6Ptxoe4wF_VCkE_pml8t66mklao();
          this(param1TorchCallback, param1String);
          param1Executor.execute(_$$Lambda$CameraManager$CameraManagerGlobal$6Ptxoe4wF_VCkE_pml8t66mklao);
        } finally {
          Binder.restoreCallingIdentity(l);
        } 
      } else {
        long l = Binder.clearCallingIdentity();
        try {
          _$$Lambda$CameraManager$CameraManagerGlobal$CONvadOBAEkcHSpx8j61v67qRGM _$$Lambda$CameraManager$CameraManagerGlobal$CONvadOBAEkcHSpx8j61v67qRGM = new _$$Lambda$CameraManager$CameraManagerGlobal$CONvadOBAEkcHSpx8j61v67qRGM();
          this(param1TorchCallback, param1String, param1Int);
          param1Executor.execute(_$$Lambda$CameraManager$CameraManagerGlobal$CONvadOBAEkcHSpx8j61v67qRGM);
          return;
        } finally {
          Binder.restoreCallingIdentity(l);
        } 
      } 
    }
    
    private void postSingleUpdate(CameraManager.AvailabilityCallback param1AvailabilityCallback, Executor param1Executor, String param1String1, String param1String2, int param1Int) {
      if (isAvailable(param1Int)) {
        long l = Binder.clearCallingIdentity();
        try {
          Runnable runnable = new Runnable() {
              public void run() {
                String str = physicalId;
                if (str == null) {
                  callback.onCameraAvailable(id);
                } else {
                  callback.onPhysicalCameraAvailable(id, str);
                } 
              }
            };
          super(this, param1String2, param1AvailabilityCallback, param1String1);
          param1Executor.execute(runnable);
        } finally {
          Binder.restoreCallingIdentity(l);
        } 
      } else {
        long l = Binder.clearCallingIdentity();
        try {
          Runnable runnable = new Runnable() {
              public void run() {
                String str = physicalId;
                if (str == null) {
                  callback.onCameraUnavailable(id);
                } else {
                  callback.onPhysicalCameraUnavailable(id, str);
                } 
              }
            };
          super(this, param1String2, param1AvailabilityCallback, param1String1);
          param1Executor.execute(runnable);
          return;
        } finally {
          Binder.restoreCallingIdentity(l);
        } 
      } 
    }
    
    private void scheduleCameraServiceReconnectionLocked() {
      if (this.mCallbackMap.isEmpty() && this.mTorchCallbackMap.isEmpty())
        return; 
      try {
        ScheduledExecutorService scheduledExecutorService = this.mScheduler;
        _$$Lambda$CameraManager$CameraManagerGlobal$w1y8myi6vgxAcTEs8WArI_NN3R0 _$$Lambda$CameraManager$CameraManagerGlobal$w1y8myi6vgxAcTEs8WArI_NN3R0 = new _$$Lambda$CameraManager$CameraManagerGlobal$w1y8myi6vgxAcTEs8WArI_NN3R0();
        this(this);
        scheduledExecutorService.schedule(_$$Lambda$CameraManager$CameraManagerGlobal$w1y8myi6vgxAcTEs8WArI_NN3R0, 1000L, TimeUnit.MILLISECONDS);
      } catch (RejectedExecutionException rejectedExecutionException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to schedule camera service re-connect: ");
        stringBuilder.append(rejectedExecutionException);
        Log.e("CameraManagerGlobal", stringBuilder.toString());
      } 
    }
    
    private static void sortCameraIds(String[] param1ArrayOfString) {
      Arrays.sort(param1ArrayOfString, new Comparator<String>() {
            public int compare(String param2String1, String param2String2) {
              byte b1;
              byte b2;
              try {
                b1 = Integer.parseInt(param2String1);
              } catch (NumberFormatException numberFormatException) {
                b1 = -1;
              } 
              try {
                b2 = Integer.parseInt(param2String2);
              } catch (NumberFormatException numberFormatException) {
                b2 = -1;
              } 
              return (b1 >= 0 && b2 >= 0) ? (b1 - b2) : ((b1 >= 0) ? -1 : ((b2 >= 0) ? 1 : param2String1.compareTo(param2String2)));
            }
          });
    }
    
    private void updateCallbackLocked(CameraManager.AvailabilityCallback param1AvailabilityCallback, Executor param1Executor) {
      for (byte b = 0; b < this.mDeviceStatus.size(); b++) {
        String str = (String)this.mDeviceStatus.keyAt(b);
        Integer integer = (Integer)this.mDeviceStatus.valueAt(b);
        postSingleUpdate(param1AvailabilityCallback, param1Executor, str, null, integer.intValue());
        if (isAvailable(integer.intValue()) && this.mUnavailablePhysicalDevices.containsKey(str)) {
          Iterator<String> iterator = ((ArrayList)this.mUnavailablePhysicalDevices.get(str)).iterator();
          while (iterator.hasNext())
            postSingleUpdate(param1AvailabilityCallback, param1Executor, str, iterator.next(), 0); 
        } 
      } 
    }
    
    private void updateTorchCallbackLocked(CameraManager.TorchCallback param1TorchCallback, Executor param1Executor) {
      for (byte b = 0; b < this.mTorchStatus.size(); b++)
        postSingleTorchUpdate(param1TorchCallback, param1Executor, (String)this.mTorchStatus.keyAt(b), ((Integer)this.mTorchStatus.valueAt(b)).intValue()); 
    }
    
    private boolean validStatus(int param1Int) {
      return !(param1Int != -2 && param1Int != 0 && param1Int != 1 && param1Int != 2);
    }
    
    private boolean validTorchStatus(int param1Int) {
      return !(param1Int != 0 && param1Int != 1 && param1Int != 2);
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public void binderDied() {
      synchronized (this.mLock) {
        if (this.mCameraService == null)
          return; 
        this.mCameraService = null;
        byte b;
        for (b = 0; b < this.mDeviceStatus.size(); b++)
          onStatusChangedLocked(0, (String)this.mDeviceStatus.keyAt(b)); 
        for (b = 0; b < this.mTorchStatus.size(); b++)
          onTorchStatusChangedLocked(0, (String)this.mTorchStatus.keyAt(b)); 
        this.mConcurrentCameraIdCombinations.clear();
        scheduleCameraServiceReconnectionLocked();
        return;
      } 
    }
    
    public boolean cameraIdHasConcurrentStreamsLocked(String param1String) {
      if (!this.mDeviceStatus.containsKey(param1String)) {
        Log.e("CameraManagerGlobal", "cameraIdHasConcurrentStreamsLocked called on non existing camera id");
        return false;
      } 
      Iterator<Set<String>> iterator = this.mConcurrentCameraIdCombinations.iterator();
      while (iterator.hasNext()) {
        if (((Set)iterator.next()).contains(param1String))
          return true; 
      } 
      return false;
    }
    
    public String[] getCameraIdList() {
      synchronized (this.mLock) {
        connectCameraServiceLocked();
        String[] arrayOfString = extractCameraIdListLocked();
        sortCameraIds(arrayOfString);
        return arrayOfString;
      } 
    }
    
    public String[] getCameraIdListNoLazy() {
      if (sCameraServiceDisabled)
        return new String[0]; 
      ICameraServiceListener.Stub stub = new ICameraServiceListener.Stub() {
          public void onCameraAccessPrioritiesChanged() {}
          
          public void onCameraClosed(String param2String) {}
          
          public void onCameraOpened(String param2String1, String param2String2) {}
          
          public void onPhysicalCameraStatusChanged(int param2Int, String param2String1, String param2String2) throws RemoteException {}
          
          public void onStatusChanged(int param2Int, String param2String) throws RemoteException {}
          
          public void onTorchStatusChanged(int param2Int, String param2String) throws RemoteException {}
        };
      synchronized (this.mLock) {
        connectCameraServiceLocked();
        try {
          CameraStatus[] arrayOfCameraStatus = this.mCameraService.addListener((ICameraServiceListener)stub);
          this.mCameraService.removeListener((ICameraServiceListener)stub);
          int i = arrayOfCameraStatus.length;
          for (byte b = 0; b < i; b++) {
            CameraStatus cameraStatus = arrayOfCameraStatus[b];
            onStatusChangedLocked(cameraStatus.status, cameraStatus.cameraId);
          } 
          Set set = this.mDeviceStatus.keySet();
          ArrayList<String> arrayList = new ArrayList();
          this();
          for (String str : set) {
            if (!cameraStatusesContains(arrayOfCameraStatus, str))
              arrayList.add(str); 
          } 
          Iterator<String> iterator = arrayList.iterator();
          while (iterator.hasNext())
            onStatusChangedLocked(0, iterator.next()); 
          String[] arrayOfString = extractCameraIdListLocked();
          sortCameraIds(arrayOfString);
          return arrayOfString;
        } catch (ServiceSpecificException serviceSpecificException) {
          IllegalStateException illegalStateException = new IllegalStateException();
          this("Failed to register a camera service listener", (Throwable)serviceSpecificException);
          throw illegalStateException;
        } catch (RemoteException remoteException) {
          String[] arrayOfString = extractCameraIdListLocked();
          sortCameraIds(arrayOfString);
          return arrayOfString;
        } 
      } 
    }
    
    public ICameraService getCameraService() {
      synchronized (this.mLock) {
        connectCameraServiceLocked();
        if (this.mCameraService == null && !sCameraServiceDisabled)
          Log.e("CameraManagerGlobal", "Camera service is unavailable"); 
        return this.mCameraService;
      } 
    }
    
    public Set<Set<String>> getConcurrentCameraIds() {
      synchronized (this.mLock) {
        connectCameraServiceLocked();
        return extractConcurrentCameraIdListLocked();
      } 
    }
    
    public boolean isConcurrentSessionConfigurationSupported(Map<String, SessionConfiguration> param1Map) throws CameraAccessException {
      // Byte code:
      //   0: aload_1
      //   1: ifnull -> 234
      //   4: aload_1
      //   5: invokeinterface size : ()I
      //   10: istore_2
      //   11: iload_2
      //   12: ifeq -> 223
      //   15: aload_0
      //   16: getfield mLock : Ljava/lang/Object;
      //   19: astore_3
      //   20: aload_3
      //   21: monitorenter
      //   22: iconst_0
      //   23: istore #4
      //   25: aload_0
      //   26: getfield mConcurrentCameraIdCombinations : Ljava/util/Set;
      //   29: invokeinterface iterator : ()Ljava/util/Iterator;
      //   34: astore #5
      //   36: aload #5
      //   38: invokeinterface hasNext : ()Z
      //   43: ifeq -> 76
      //   46: aload #5
      //   48: invokeinterface next : ()Ljava/lang/Object;
      //   53: checkcast java/util/Set
      //   56: aload_1
      //   57: invokeinterface keySet : ()Ljava/util/Set;
      //   62: invokeinterface containsAll : (Ljava/util/Collection;)Z
      //   67: ifeq -> 73
      //   70: iconst_1
      //   71: istore #4
      //   73: goto -> 36
      //   76: iload #4
      //   78: ifne -> 94
      //   81: ldc 'CameraManagerGlobal'
      //   83: ldc_w 'isConcurrentSessionConfigurationSupported called with a subset ofcamera ids not returned by getConcurrentCameraIds'
      //   86: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
      //   89: pop
      //   90: aload_3
      //   91: monitorexit
      //   92: iconst_0
      //   93: ireturn
      //   94: iload_2
      //   95: anewarray android/hardware/camera2/utils/CameraIdAndSessionConfiguration
      //   98: astore #5
      //   100: iconst_0
      //   101: istore #4
      //   103: aload_1
      //   104: invokeinterface entrySet : ()Ljava/util/Set;
      //   109: invokeinterface iterator : ()Ljava/util/Iterator;
      //   114: astore_1
      //   115: aload_1
      //   116: invokeinterface hasNext : ()Z
      //   121: ifeq -> 173
      //   124: aload_1
      //   125: invokeinterface next : ()Ljava/lang/Object;
      //   130: checkcast java/util/Map$Entry
      //   133: astore #6
      //   135: aload #5
      //   137: iload #4
      //   139: new android/hardware/camera2/utils/CameraIdAndSessionConfiguration
      //   142: dup
      //   143: aload #6
      //   145: invokeinterface getKey : ()Ljava/lang/Object;
      //   150: checkcast java/lang/String
      //   153: aload #6
      //   155: invokeinterface getValue : ()Ljava/lang/Object;
      //   160: checkcast android/hardware/camera2/params/SessionConfiguration
      //   163: invokespecial <init> : (Ljava/lang/String;Landroid/hardware/camera2/params/SessionConfiguration;)V
      //   166: aastore
      //   167: iinc #4, 1
      //   170: goto -> 115
      //   173: aload_0
      //   174: getfield mCameraService : Landroid/hardware/ICameraService;
      //   177: aload #5
      //   179: invokeinterface isConcurrentSessionConfigurationSupported : ([Landroid/hardware/camera2/utils/CameraIdAndSessionConfiguration;)Z
      //   184: istore #7
      //   186: aload_3
      //   187: monitorexit
      //   188: iload #7
      //   190: ireturn
      //   191: astore #5
      //   193: new android/hardware/camera2/CameraAccessException
      //   196: astore_1
      //   197: aload_1
      //   198: iconst_2
      //   199: ldc_w 'Camera service is currently unavailable'
      //   202: aload #5
      //   204: invokespecial <init> : (ILjava/lang/String;Ljava/lang/Throwable;)V
      //   207: aload_1
      //   208: athrow
      //   209: astore_1
      //   210: aload_1
      //   211: invokestatic throwAsPublicException : (Ljava/lang/Throwable;)V
      //   214: aload_3
      //   215: monitorexit
      //   216: iconst_0
      //   217: ireturn
      //   218: astore_1
      //   219: aload_3
      //   220: monitorexit
      //   221: aload_1
      //   222: athrow
      //   223: new java/lang/IllegalArgumentException
      //   226: dup
      //   227: ldc_w 'camera id and session combination is empty'
      //   230: invokespecial <init> : (Ljava/lang/String;)V
      //   233: athrow
      //   234: new java/lang/IllegalArgumentException
      //   237: dup
      //   238: ldc_w 'cameraIdsAndSessionConfigurations was null'
      //   241: invokespecial <init> : (Ljava/lang/String;)V
      //   244: athrow
      // Exception table:
      //   from	to	target	type
      //   25	36	218	finally
      //   36	46	218	finally
      //   46	70	218	finally
      //   81	92	218	finally
      //   94	100	218	finally
      //   103	115	218	finally
      //   115	167	218	finally
      //   173	186	209	android/os/ServiceSpecificException
      //   173	186	191	android/os/RemoteException
      //   173	186	218	finally
      //   186	188	218	finally
      //   193	209	218	finally
      //   210	214	218	finally
      //   214	216	218	finally
      //   219	221	218	finally
    }
    
    public void onCameraAccessPrioritiesChanged() {
      synchronized (this.mLock) {
        int i = this.mCallbackMap.size();
        for (byte b = 0; b < i; b++) {
          Executor executor = (Executor)this.mCallbackMap.valueAt(b);
          postSingleAccessPriorityChangeUpdate((CameraManager.AvailabilityCallback)this.mCallbackMap.keyAt(b), executor);
        } 
        return;
      } 
    }
    
    public void onCameraClosed(String param1String) {
      synchronized (this.mLock) {
        int i = this.mCallbackMap.size();
        for (byte b = 0; b < i; b++) {
          Executor executor = (Executor)this.mCallbackMap.valueAt(b);
          postSingleCameraClosedUpdate((CameraManager.AvailabilityCallback)this.mCallbackMap.keyAt(b), executor, param1String);
        } 
        return;
      } 
    }
    
    public void onCameraOpened(String param1String1, String param1String2) {
      synchronized (this.mLock) {
        int i = this.mCallbackMap.size();
        for (byte b = 0; b < i; b++) {
          Executor executor = (Executor)this.mCallbackMap.valueAt(b);
          postSingleCameraOpenedUpdate((CameraManager.AvailabilityCallback)this.mCallbackMap.keyAt(b), executor, param1String1, param1String2);
        } 
        return;
      } 
    }
    
    public void onPhysicalCameraStatusChanged(int param1Int, String param1String1, String param1String2) throws RemoteException {
      synchronized (this.mLock) {
        onPhysicalCameraStatusChangedLocked(param1Int, param1String1, param1String2);
        return;
      } 
    }
    
    public void onStatusChanged(int param1Int, String param1String) throws RemoteException {
      synchronized (this.mLock) {
        onStatusChangedLocked(param1Int, param1String);
        return;
      } 
    }
    
    public void onTorchStatusChanged(int param1Int, String param1String) throws RemoteException {
      synchronized (this.mLock) {
        onTorchStatusChangedLocked(param1Int, param1String);
        return;
      } 
    }
    
    public void registerAvailabilityCallback(CameraManager.AvailabilityCallback param1AvailabilityCallback, Executor param1Executor) {
      synchronized (this.mLock) {
        connectCameraServiceLocked();
        if ((Executor)this.mCallbackMap.put(param1AvailabilityCallback, param1Executor) == null)
          updateCallbackLocked(param1AvailabilityCallback, param1Executor); 
        if (this.mCameraService == null)
          scheduleCameraServiceReconnectionLocked(); 
        return;
      } 
    }
    
    public void registerTorchCallback(CameraManager.TorchCallback param1TorchCallback, Executor param1Executor) {
      synchronized (this.mLock) {
        connectCameraServiceLocked();
        if ((Executor)this.mTorchCallbackMap.put(param1TorchCallback, param1Executor) == null)
          updateTorchCallbackLocked(param1TorchCallback, param1Executor); 
        if (this.mCameraService == null)
          scheduleCameraServiceReconnectionLocked(); 
        return;
      } 
    }
    
    public void setTorchMode(String param1String, boolean param1Boolean) throws CameraAccessException {
      // Byte code:
      //   0: aload_0
      //   1: getfield mLock : Ljava/lang/Object;
      //   4: astore_3
      //   5: aload_3
      //   6: monitorenter
      //   7: aload_1
      //   8: ifnull -> 82
      //   11: aload_0
      //   12: invokevirtual getCameraService : ()Landroid/hardware/ICameraService;
      //   15: astore #4
      //   17: aload #4
      //   19: ifnull -> 64
      //   22: aload #4
      //   24: aload_1
      //   25: iload_2
      //   26: aload_0
      //   27: getfield mTorchClientBinder : Landroid/os/Binder;
      //   30: invokeinterface setTorchMode : (Ljava/lang/String;ZLandroid/os/IBinder;)V
      //   35: goto -> 61
      //   38: astore_1
      //   39: new android/hardware/camera2/CameraAccessException
      //   42: astore_1
      //   43: aload_1
      //   44: iconst_2
      //   45: ldc_w 'Camera service is currently unavailable'
      //   48: invokespecial <init> : (ILjava/lang/String;)V
      //   51: aload_1
      //   52: athrow
      //   53: astore_1
      //   54: aload_1
      //   55: invokestatic throwAsPublicException : (Ljava/lang/Throwable;)V
      //   58: goto -> 35
      //   61: aload_3
      //   62: monitorexit
      //   63: return
      //   64: new android/hardware/camera2/CameraAccessException
      //   67: astore_1
      //   68: aload_1
      //   69: iconst_2
      //   70: ldc_w 'Camera service is currently unavailable'
      //   73: invokespecial <init> : (ILjava/lang/String;)V
      //   76: aload_1
      //   77: athrow
      //   78: astore_1
      //   79: goto -> 95
      //   82: new java/lang/IllegalArgumentException
      //   85: astore_1
      //   86: aload_1
      //   87: ldc_w 'cameraId was null'
      //   90: invokespecial <init> : (Ljava/lang/String;)V
      //   93: aload_1
      //   94: athrow
      //   95: aload_3
      //   96: monitorexit
      //   97: aload_1
      //   98: athrow
      // Exception table:
      //   from	to	target	type
      //   11	17	78	finally
      //   22	35	53	android/os/ServiceSpecificException
      //   22	35	38	android/os/RemoteException
      //   22	35	78	finally
      //   39	53	78	finally
      //   54	58	78	finally
      //   61	63	78	finally
      //   64	78	78	finally
      //   82	95	78	finally
      //   95	97	78	finally
    }
    
    public void unregisterAvailabilityCallback(CameraManager.AvailabilityCallback param1AvailabilityCallback) {
      synchronized (this.mLock) {
        this.mCallbackMap.remove(param1AvailabilityCallback);
        return;
      } 
    }
    
    public void unregisterTorchCallback(CameraManager.TorchCallback param1TorchCallback) {
      synchronized (this.mLock) {
        this.mTorchCallbackMap.remove(param1TorchCallback);
        return;
      } 
    }
  }
  
  class null implements Comparator<String> {
    public int compare(String param1String1, String param1String2) {
      byte b1;
      byte b2;
      try {
        b1 = Integer.parseInt(param1String1);
      } catch (NumberFormatException numberFormatException) {
        b1 = -1;
      } 
      try {
        b2 = Integer.parseInt(param1String2);
      } catch (NumberFormatException numberFormatException) {
        b2 = -1;
      } 
      return (b1 >= 0 && b2 >= 0) ? (b1 - b2) : ((b1 >= 0) ? -1 : ((b2 >= 0) ? 1 : param1String1.compareTo(param1String2)));
    }
  }
  
  class null extends ICameraServiceListener.Stub {
    public void onCameraAccessPrioritiesChanged() {}
    
    public void onCameraClosed(String param1String) {}
    
    public void onCameraOpened(String param1String1, String param1String2) {}
    
    public void onPhysicalCameraStatusChanged(int param1Int, String param1String1, String param1String2) throws RemoteException {}
    
    public void onStatusChanged(int param1Int, String param1String) throws RemoteException {}
    
    public void onTorchStatusChanged(int param1Int, String param1String) throws RemoteException {}
  }
  
  class null implements Runnable {
    public void run() {
      callback.onCameraAccessPrioritiesChanged();
    }
  }
  
  class null implements Runnable {
    public void run() {
      callback.onCameraOpened(id, packageId);
    }
  }
  
  class null implements Runnable {
    public void run() {
      callback.onCameraClosed(id);
    }
  }
  
  class null implements Runnable {
    public void run() {
      String str = physicalId;
      if (str == null) {
        callback.onCameraAvailable(id);
      } else {
        callback.onPhysicalCameraAvailable(id, str);
      } 
    }
  }
  
  class null implements Runnable {
    public void run() {
      String str = physicalId;
      if (str == null) {
        callback.onCameraUnavailable(id);
      } else {
        callback.onPhysicalCameraUnavailable(id, str);
      } 
    }
  }
  
  public static abstract class TorchCallback {
    public void onTorchModeChanged(String param1String, boolean param1Boolean) {}
    
    public void onTorchModeUnavailable(String param1String) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */