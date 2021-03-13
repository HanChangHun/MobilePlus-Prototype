package android.hardware.camera2;

import android.hardware.CameraStatus;
import android.hardware.ICameraService;
import android.hardware.ICameraServiceListener;
import android.hardware.camera2.params.SessionConfiguration;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
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

final class CameraManagerGlobal extends ICameraServiceListener.Stub implements IBinder.DeathRecipient {
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
  
  public static boolean cameraStatusesContains(CameraStatus[] paramArrayOfCameraStatus, String paramString) {
    int i = paramArrayOfCameraStatus.length;
    for (byte b = 0; b < i; b++) {
      if ((paramArrayOfCameraStatus[b]).cameraId.equals(paramString))
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
  
  private void handleRecoverableSetupErrors(ServiceSpecificException paramServiceSpecificException) {
    if (paramServiceSpecificException.errorCode == 4) {
      Log.w("CameraManagerGlobal", paramServiceSpecificException.getMessage());
      return;
    } 
    throw new IllegalStateException(paramServiceSpecificException);
  }
  
  private boolean isAvailable(int paramInt) {
    return !(paramInt != 1);
  }
  
  private void onPhysicalCameraStatusChangedLocked(int paramInt, String paramString1, String paramString2) {
    if (!validStatus(paramInt)) {
      Log.e("CameraManagerGlobal", String.format("Ignoring invalid device %s physical device %s status 0x%x", new Object[] { paramString1, paramString2, Integer.valueOf(paramInt) }));
      return;
    } 
    if (!this.mDeviceStatus.containsKey(paramString1) || !isAvailable(((Integer)this.mDeviceStatus.get(paramString1)).intValue()) || !this.mUnavailablePhysicalDevices.containsKey(paramString1)) {
      Log.e("CameraManagerGlobal", String.format("Camera %s is not available. Ignore physical camera status change", new Object[] { paramString1 }));
      return;
    } 
    ArrayList<String> arrayList = (ArrayList)this.mUnavailablePhysicalDevices.get(paramString1);
    if (!isAvailable(paramInt) && !arrayList.contains(paramString2)) {
      arrayList.add(paramString2);
    } else if (isAvailable(paramInt) && arrayList.contains(paramString2)) {
      arrayList.remove(paramString2);
    } else {
      return;
    } 
    int i = this.mCallbackMap.size();
    for (byte b = 0; b < i; b++) {
      Executor executor = (Executor)this.mCallbackMap.valueAt(b);
      postSingleUpdate((CameraManager.AvailabilityCallback)this.mCallbackMap.keyAt(b), executor, paramString1, paramString2, paramInt);
    } 
  }
  
  private void onStatusChangedLocked(int paramInt, String paramString) {
    Integer integer;
    if (!validStatus(paramInt)) {
      Log.e("CameraManagerGlobal", String.format("Ignoring invalid device %s status 0x%x", new Object[] { paramString, Integer.valueOf(paramInt) }));
      return;
    } 
    if (paramInt == 0) {
      integer = (Integer)this.mDeviceStatus.remove(paramString);
      this.mUnavailablePhysicalDevices.remove(paramString);
    } else {
      Integer integer1 = (Integer)this.mDeviceStatus.put(paramString, Integer.valueOf(paramInt));
      integer = integer1;
      if (integer1 == null) {
        this.mUnavailablePhysicalDevices.put(paramString, new ArrayList());
        integer = integer1;
      } 
    } 
    if (integer != null && integer.intValue() == paramInt)
      return; 
    if (integer != null && isAvailable(paramInt) == isAvailable(integer.intValue()))
      return; 
    int i = this.mCallbackMap.size();
    for (byte b = 0; b < i; b++) {
      Executor executor = (Executor)this.mCallbackMap.valueAt(b);
      postSingleUpdate((CameraManager.AvailabilityCallback)this.mCallbackMap.keyAt(b), executor, paramString, null, paramInt);
    } 
  }
  
  private void onTorchStatusChangedLocked(int paramInt, String paramString) {
    if (!validTorchStatus(paramInt)) {
      Log.e("CameraManagerGlobal", String.format("Ignoring invalid device %s torch status 0x%x", new Object[] { paramString, Integer.valueOf(paramInt) }));
      return;
    } 
    Integer integer = (Integer)this.mTorchStatus.put(paramString, Integer.valueOf(paramInt));
    if (integer != null && integer.intValue() == paramInt)
      return; 
    int i = this.mTorchCallbackMap.size();
    for (byte b = 0; b < i; b++) {
      Executor executor = (Executor)this.mTorchCallbackMap.valueAt(b);
      postSingleTorchUpdate((CameraManager.TorchCallback)this.mTorchCallbackMap.keyAt(b), executor, paramString, paramInt);
    } 
  }
  
  private void postSingleAccessPriorityChangeUpdate(CameraManager.AvailabilityCallback paramAvailabilityCallback, Executor paramExecutor) {
    long l = Binder.clearCallingIdentity();
    try {
      Runnable runnable = new Runnable() {
          public void run() {
            callback.onCameraAccessPrioritiesChanged();
          }
        };
      super(this, paramAvailabilityCallback);
      paramExecutor.execute(runnable);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  private void postSingleCameraClosedUpdate(CameraManager.AvailabilityCallback paramAvailabilityCallback, Executor paramExecutor, String paramString) {
    long l = Binder.clearCallingIdentity();
    try {
      Runnable runnable = new Runnable() {
          public void run() {
            callback.onCameraClosed(id);
          }
        };
      super(this, paramAvailabilityCallback, paramString);
      paramExecutor.execute(runnable);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  private void postSingleCameraOpenedUpdate(CameraManager.AvailabilityCallback paramAvailabilityCallback, Executor paramExecutor, String paramString1, String paramString2) {
    long l = Binder.clearCallingIdentity();
    try {
      Runnable runnable = new Runnable() {
          public void run() {
            callback.onCameraOpened(id, packageId);
          }
        };
      super(this, paramAvailabilityCallback, paramString1, paramString2);
      paramExecutor.execute(runnable);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  private void postSingleTorchUpdate(CameraManager.TorchCallback paramTorchCallback, Executor paramExecutor, String paramString, int paramInt) {
    if (paramInt != 1 && paramInt != 2) {
      long l = Binder.clearCallingIdentity();
      try {
        _$$Lambda$CameraManager$CameraManagerGlobal$6Ptxoe4wF_VCkE_pml8t66mklao _$$Lambda$CameraManager$CameraManagerGlobal$6Ptxoe4wF_VCkE_pml8t66mklao = new _$$Lambda$CameraManager$CameraManagerGlobal$6Ptxoe4wF_VCkE_pml8t66mklao();
        this(paramTorchCallback, paramString);
        paramExecutor.execute(_$$Lambda$CameraManager$CameraManagerGlobal$6Ptxoe4wF_VCkE_pml8t66mklao);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } else {
      long l = Binder.clearCallingIdentity();
      try {
        _$$Lambda$CameraManager$CameraManagerGlobal$CONvadOBAEkcHSpx8j61v67qRGM _$$Lambda$CameraManager$CameraManagerGlobal$CONvadOBAEkcHSpx8j61v67qRGM = new _$$Lambda$CameraManager$CameraManagerGlobal$CONvadOBAEkcHSpx8j61v67qRGM();
        this(paramTorchCallback, paramString, paramInt);
        paramExecutor.execute(_$$Lambda$CameraManager$CameraManagerGlobal$CONvadOBAEkcHSpx8j61v67qRGM);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  private void postSingleUpdate(CameraManager.AvailabilityCallback paramAvailabilityCallback, Executor paramExecutor, String paramString1, String paramString2, int paramInt) {
    if (isAvailable(paramInt)) {
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
        super(this, paramString2, paramAvailabilityCallback, paramString1);
        paramExecutor.execute(runnable);
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
        super(this, paramString2, paramAvailabilityCallback, paramString1);
        paramExecutor.execute(runnable);
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
  
  private static void sortCameraIds(String[] paramArrayOfString) {
    Arrays.sort(paramArrayOfString, new Comparator<String>() {
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
  
  private void updateCallbackLocked(CameraManager.AvailabilityCallback paramAvailabilityCallback, Executor paramExecutor) {
    for (byte b = 0; b < this.mDeviceStatus.size(); b++) {
      String str = (String)this.mDeviceStatus.keyAt(b);
      Integer integer = (Integer)this.mDeviceStatus.valueAt(b);
      postSingleUpdate(paramAvailabilityCallback, paramExecutor, str, null, integer.intValue());
      if (isAvailable(integer.intValue()) && this.mUnavailablePhysicalDevices.containsKey(str)) {
        Iterator<String> iterator = ((ArrayList)this.mUnavailablePhysicalDevices.get(str)).iterator();
        while (iterator.hasNext())
          postSingleUpdate(paramAvailabilityCallback, paramExecutor, str, iterator.next(), 0); 
      } 
    } 
  }
  
  private void updateTorchCallbackLocked(CameraManager.TorchCallback paramTorchCallback, Executor paramExecutor) {
    for (byte b = 0; b < this.mTorchStatus.size(); b++)
      postSingleTorchUpdate(paramTorchCallback, paramExecutor, (String)this.mTorchStatus.keyAt(b), ((Integer)this.mTorchStatus.valueAt(b)).intValue()); 
  }
  
  private boolean validStatus(int paramInt) {
    return !(paramInt != -2 && paramInt != 0 && paramInt != 1 && paramInt != 2);
  }
  
  private boolean validTorchStatus(int paramInt) {
    return !(paramInt != 0 && paramInt != 1 && paramInt != 2);
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
  
  public boolean cameraIdHasConcurrentStreamsLocked(String paramString) {
    if (!this.mDeviceStatus.containsKey(paramString)) {
      Log.e("CameraManagerGlobal", "cameraIdHasConcurrentStreamsLocked called on non existing camera id");
      return false;
    } 
    Iterator<Set<String>> iterator = this.mConcurrentCameraIdCombinations.iterator();
    while (iterator.hasNext()) {
      if (((Set)iterator.next()).contains(paramString))
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
  
  public boolean isConcurrentSessionConfigurationSupported(Map<String, SessionConfiguration> paramMap) throws CameraAccessException {
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
  
  public void onCameraClosed(String paramString) {
    synchronized (this.mLock) {
      int i = this.mCallbackMap.size();
      for (byte b = 0; b < i; b++) {
        Executor executor = (Executor)this.mCallbackMap.valueAt(b);
        postSingleCameraClosedUpdate((CameraManager.AvailabilityCallback)this.mCallbackMap.keyAt(b), executor, paramString);
      } 
      return;
    } 
  }
  
  public void onCameraOpened(String paramString1, String paramString2) {
    synchronized (this.mLock) {
      int i = this.mCallbackMap.size();
      for (byte b = 0; b < i; b++) {
        Executor executor = (Executor)this.mCallbackMap.valueAt(b);
        postSingleCameraOpenedUpdate((CameraManager.AvailabilityCallback)this.mCallbackMap.keyAt(b), executor, paramString1, paramString2);
      } 
      return;
    } 
  }
  
  public void onPhysicalCameraStatusChanged(int paramInt, String paramString1, String paramString2) throws RemoteException {
    synchronized (this.mLock) {
      onPhysicalCameraStatusChangedLocked(paramInt, paramString1, paramString2);
      return;
    } 
  }
  
  public void onStatusChanged(int paramInt, String paramString) throws RemoteException {
    synchronized (this.mLock) {
      onStatusChangedLocked(paramInt, paramString);
      return;
    } 
  }
  
  public void onTorchStatusChanged(int paramInt, String paramString) throws RemoteException {
    synchronized (this.mLock) {
      onTorchStatusChangedLocked(paramInt, paramString);
      return;
    } 
  }
  
  public void registerAvailabilityCallback(CameraManager.AvailabilityCallback paramAvailabilityCallback, Executor paramExecutor) {
    synchronized (this.mLock) {
      connectCameraServiceLocked();
      if ((Executor)this.mCallbackMap.put(paramAvailabilityCallback, paramExecutor) == null)
        updateCallbackLocked(paramAvailabilityCallback, paramExecutor); 
      if (this.mCameraService == null)
        scheduleCameraServiceReconnectionLocked(); 
      return;
    } 
  }
  
  public void registerTorchCallback(CameraManager.TorchCallback paramTorchCallback, Executor paramExecutor) {
    synchronized (this.mLock) {
      connectCameraServiceLocked();
      if ((Executor)this.mTorchCallbackMap.put(paramTorchCallback, paramExecutor) == null)
        updateTorchCallbackLocked(paramTorchCallback, paramExecutor); 
      if (this.mCameraService == null)
        scheduleCameraServiceReconnectionLocked(); 
      return;
    } 
  }
  
  public void setTorchMode(String paramString, boolean paramBoolean) throws CameraAccessException {
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
  
  public void unregisterAvailabilityCallback(CameraManager.AvailabilityCallback paramAvailabilityCallback) {
    synchronized (this.mLock) {
      this.mCallbackMap.remove(paramAvailabilityCallback);
      return;
    } 
  }
  
  public void unregisterTorchCallback(CameraManager.TorchCallback paramTorchCallback) {
    synchronized (this.mLock) {
      this.mTorchCallbackMap.remove(paramTorchCallback);
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraManager$CameraManagerGlobal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */