package android.bluetooth;

import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.app.PropertyInvalidatedCache;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.PeriodicAdvertisingManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SynchronousResultReceiver;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Pair;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

public final class BluetoothAdapter {
  public static final String ACTION_BLE_ACL_CONNECTED = "android.bluetooth.adapter.action.BLE_ACL_CONNECTED";
  
  public static final String ACTION_BLE_ACL_DISCONNECTED = "android.bluetooth.adapter.action.BLE_ACL_DISCONNECTED";
  
  @SystemApi
  public static final String ACTION_BLE_STATE_CHANGED = "android.bluetooth.adapter.action.BLE_STATE_CHANGED";
  
  public static final String ACTION_BLUETOOTH_ADDRESS_CHANGED = "android.bluetooth.adapter.action.BLUETOOTH_ADDRESS_CHANGED";
  
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED";
  
  public static final String ACTION_DISCOVERY_FINISHED = "android.bluetooth.adapter.action.DISCOVERY_FINISHED";
  
  public static final String ACTION_DISCOVERY_STARTED = "android.bluetooth.adapter.action.DISCOVERY_STARTED";
  
  public static final String ACTION_LOCAL_NAME_CHANGED = "android.bluetooth.adapter.action.LOCAL_NAME_CHANGED";
  
  @SystemApi
  public static final String ACTION_REQUEST_BLE_SCAN_ALWAYS_AVAILABLE = "android.bluetooth.adapter.action.REQUEST_BLE_SCAN_ALWAYS_AVAILABLE";
  
  public static final String ACTION_REQUEST_DISABLE = "android.bluetooth.adapter.action.REQUEST_DISABLE";
  
  public static final String ACTION_REQUEST_DISCOVERABLE = "android.bluetooth.adapter.action.REQUEST_DISCOVERABLE";
  
  public static final String ACTION_REQUEST_ENABLE = "android.bluetooth.adapter.action.REQUEST_ENABLE";
  
  public static final String ACTION_SCAN_MODE_CHANGED = "android.bluetooth.adapter.action.SCAN_MODE_CHANGED";
  
  public static final String ACTION_STATE_CHANGED = "android.bluetooth.adapter.action.STATE_CHANGED";
  
  @SystemApi
  public static final int ACTIVE_DEVICE_ALL = 2;
  
  @SystemApi
  public static final int ACTIVE_DEVICE_AUDIO = 0;
  
  @SystemApi
  public static final int ACTIVE_DEVICE_PHONE_CALL = 1;
  
  private static final int ADDRESS_LENGTH = 17;
  
  private static final String BLUETOOTH_FILTERING_CACHE_PROPERTY = "cache_key.bluetooth.is_offloaded_filtering_supported";
  
  private static final String BLUETOOTH_GET_STATE_CACHE_PROPERTY = "cache_key.bluetooth.get_state";
  
  public static final String BLUETOOTH_MANAGER_SERVICE = "bluetooth_manager";
  
  private static final String BLUETOOTH_PROFILE_CACHE_PROPERTY = "cache_key.bluetooth.get_profile_connection_state";
  
  private static final boolean DBG = true;
  
  public static final String DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00";
  
  public static final int ERROR = -2147483648;
  
  public static final String EXTRA_BLUETOOTH_ADDRESS = "android.bluetooth.adapter.extra.BLUETOOTH_ADDRESS";
  
  public static final String EXTRA_CONNECTION_STATE = "android.bluetooth.adapter.extra.CONNECTION_STATE";
  
  public static final String EXTRA_DISCOVERABLE_DURATION = "android.bluetooth.adapter.extra.DISCOVERABLE_DURATION";
  
  public static final String EXTRA_LOCAL_NAME = "android.bluetooth.adapter.extra.LOCAL_NAME";
  
  public static final String EXTRA_PREVIOUS_CONNECTION_STATE = "android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE";
  
  public static final String EXTRA_PREVIOUS_SCAN_MODE = "android.bluetooth.adapter.extra.PREVIOUS_SCAN_MODE";
  
  public static final String EXTRA_PREVIOUS_STATE = "android.bluetooth.adapter.extra.PREVIOUS_STATE";
  
  public static final String EXTRA_SCAN_MODE = "android.bluetooth.adapter.extra.SCAN_MODE";
  
  public static final String EXTRA_STATE = "android.bluetooth.adapter.extra.STATE";
  
  public static final int IO_CAPABILITY_IN = 2;
  
  public static final int IO_CAPABILITY_IO = 1;
  
  public static final int IO_CAPABILITY_KBDISP = 4;
  
  public static final int IO_CAPABILITY_MAX = 5;
  
  public static final int IO_CAPABILITY_NONE = 3;
  
  public static final int IO_CAPABILITY_OUT = 0;
  
  public static final int IO_CAPABILITY_UNKNOWN = 255;
  
  public static final UUID LE_PSM_CHARACTERISTIC_UUID = UUID.fromString("2d410339-82b6-42aa-b34e-e2e01df8cc1a");
  
  public static final int SCAN_MODE_CONNECTABLE = 21;
  
  public static final int SCAN_MODE_CONNECTABLE_DISCOVERABLE = 23;
  
  public static final int SCAN_MODE_NONE = 20;
  
  public static final int SOCKET_CHANNEL_AUTO_STATIC_NO_SDP = -2;
  
  public static final int STATE_BLE_ON = 15;
  
  public static final int STATE_BLE_TURNING_OFF = 16;
  
  public static final int STATE_BLE_TURNING_ON = 14;
  
  public static final int STATE_CONNECTED = 2;
  
  public static final int STATE_CONNECTING = 1;
  
  public static final int STATE_DISCONNECTED = 0;
  
  public static final int STATE_DISCONNECTING = 3;
  
  public static final int STATE_OFF = 10;
  
  public static final int STATE_ON = 12;
  
  public static final int STATE_TURNING_OFF = 13;
  
  public static final int STATE_TURNING_ON = 11;
  
  private static final String TAG = "BluetoothAdapter";
  
  private static final boolean VDBG = false;
  
  private static BluetoothAdapter sAdapter;
  
  private static BluetoothLeAdvertiser sBluetoothLeAdvertiser;
  
  private static BluetoothLeScanner sBluetoothLeScanner;
  
  private static final IBluetoothMetadataListener sBluetoothMetadataListener;
  
  private static final Map<BluetoothDevice, List<Pair<OnMetadataChangedListener, Executor>>> sMetadataListeners = new HashMap<>();
  
  private static PeriodicAdvertisingManager sPeriodicAdvertisingManager;
  
  private final PropertyInvalidatedCache<Void, Boolean> mBluetoothFilteringCache = new PropertyInvalidatedCache<Void, Boolean>(8, "cache_key.bluetooth.is_offloaded_filtering_supported") {
      protected Boolean recompute(Void param1Void) {
        try {
          BluetoothAdapter.this.mServiceLock.readLock().lock();
          if (BluetoothAdapter.this.mService != null) {
            boolean bool = BluetoothAdapter.this.mService.isOffloadedFilteringSupported();
            BluetoothAdapter.this.mServiceLock.readLock().unlock();
            return Boolean.valueOf(bool);
          } 
        } catch (RemoteException remoteException) {
          Log.e("BluetoothAdapter", "failed to get isOffloadedFilteringSupported, error: ", (Throwable)remoteException);
        } finally {}
        BluetoothAdapter.this.mServiceLock.readLock().unlock();
        return Boolean.valueOf(false);
      }
    };
  
  private final PropertyInvalidatedCache<Void, Integer> mBluetoothGetStateCache = new PropertyInvalidatedCache<Void, Integer>(8, "cache_key.bluetooth.get_state") {
      protected Integer recompute(Void param1Void) {
        try {
          int i = BluetoothAdapter.this.mService.getState();
          return Integer.valueOf(i);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      }
    };
  
  private Context mContext;
  
  private final PropertyInvalidatedCache<Integer, Integer> mGetProfileConnectionStateCache = new PropertyInvalidatedCache<Integer, Integer>(8, "cache_key.bluetooth.get_profile_connection_state") {
      public String queryToString(Integer param1Integer) {
        return String.format("getProfileConnectionState(profile=\"%d\")", new Object[] { param1Integer });
      }
      
      protected Integer recompute(Integer param1Integer) {
        try {
          BluetoothAdapter.this.mServiceLock.readLock().lock();
          if (BluetoothAdapter.this.mService != null) {
            int i = BluetoothAdapter.this.mService.getProfileConnectionState(param1Integer.intValue());
            BluetoothAdapter.this.mServiceLock.readLock().unlock();
            return Integer.valueOf(i);
          } 
        } catch (RemoteException remoteException) {
          Log.e("BluetoothAdapter", "getProfileConnectionState:", (Throwable)remoteException);
        } finally {}
        BluetoothAdapter.this.mServiceLock.readLock().unlock();
        return Integer.valueOf(0);
      }
    };
  
  private final Map<LeScanCallback, ScanCallback> mLeScanClients;
  
  private final Object mLock = new Object();
  
  private final IBluetoothManagerCallback mManagerCallback = new IBluetoothManagerCallback.Stub() {
      public void onBluetoothServiceDown() {
        null = new StringBuilder();
        null.append("onBluetoothServiceDown: ");
        null.append(BluetoothAdapter.this.mService);
        Log.d("BluetoothAdapter", null.toString());
        try {
          BluetoothAdapter.this.mServiceLock.writeLock().lock();
          BluetoothAdapter.access$102(BluetoothAdapter.this, null);
          if (BluetoothAdapter.this.mLeScanClients != null)
            BluetoothAdapter.this.mLeScanClients.clear(); 
          if (BluetoothAdapter.sBluetoothLeAdvertiser != null)
            BluetoothAdapter.sBluetoothLeAdvertiser.cleanup(); 
          if (BluetoothAdapter.sBluetoothLeScanner != null)
            BluetoothAdapter.sBluetoothLeScanner.cleanup(); 
          BluetoothAdapter.this.mServiceLock.writeLock().unlock();
        } finally {
          BluetoothAdapter.this.mServiceLock.writeLock().unlock();
        } 
      }
      
      public void onBluetoothServiceUp(IBluetooth param1IBluetooth) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onBluetoothServiceUp: ");
        stringBuilder.append(param1IBluetooth);
        Log.d("BluetoothAdapter", stringBuilder.toString());
        BluetoothAdapter.this.mServiceLock.writeLock().lock();
        BluetoothAdapter.access$102(BluetoothAdapter.this, param1IBluetooth);
        BluetoothAdapter.this.mServiceLock.writeLock().unlock();
        synchronized (BluetoothAdapter.this.mProxyServiceStateCallbacks) {
          for (IBluetoothManagerCallback iBluetoothManagerCallback : BluetoothAdapter.this.mProxyServiceStateCallbacks) {
            if (iBluetoothManagerCallback != null) {
              try {
                iBluetoothManagerCallback.onBluetoothServiceUp(param1IBluetooth);
              } catch (Exception exception) {
                Log.e("BluetoothAdapter", "", exception);
              } 
              continue;
            } 
            Log.d("BluetoothAdapter", "onBluetoothServiceUp: cb is null!");
          } 
          synchronized (BluetoothAdapter.sMetadataListeners) {
            Map map = BluetoothAdapter.sMetadataListeners;
            _$$Lambda$BluetoothAdapter$5$eKI2JS6EbiGZOGfQ8La27pm0gy0 _$$Lambda$BluetoothAdapter$5$eKI2JS6EbiGZOGfQ8La27pm0gy0 = new _$$Lambda$BluetoothAdapter$5$eKI2JS6EbiGZOGfQ8La27pm0gy0();
            this(this);
            map.forEach(_$$Lambda$BluetoothAdapter$5$eKI2JS6EbiGZOGfQ8La27pm0gy0);
            return;
          } 
        } 
      }
      
      public void onBrEdrDown() {}
    };
  
  private final IBluetoothManager mManagerService;
  
  private final ArrayList<IBluetoothManagerCallback> mProxyServiceStateCallbacks = new ArrayList<>();
  
  private IBluetooth mService;
  
  private final ReentrantReadWriteLock mServiceLock = new ReentrantReadWriteLock();
  
  private final IBinder mToken;
  
  static {
    sBluetoothMetadataListener = new IBluetoothMetadataListener.Stub() {
        public void onMetadataChanged(BluetoothDevice param1BluetoothDevice, int param1Int, byte[] param1ArrayOfbyte) {
          synchronized (BluetoothAdapter.sMetadataListeners) {
            if (BluetoothAdapter.sMetadataListeners.containsKey(param1BluetoothDevice))
              for (Pair pair : BluetoothAdapter.sMetadataListeners.get(param1BluetoothDevice)) {
                BluetoothAdapter.OnMetadataChangedListener onMetadataChangedListener = (BluetoothAdapter.OnMetadataChangedListener)pair.first;
                Executor executor = (Executor)pair.second;
                _$$Lambda$BluetoothAdapter$1$I3p3FVKkxuogQU8eug7PAKoZKZc _$$Lambda$BluetoothAdapter$1$I3p3FVKkxuogQU8eug7PAKoZKZc = new _$$Lambda$BluetoothAdapter$1$I3p3FVKkxuogQU8eug7PAKoZKZc();
                this(onMetadataChangedListener, param1BluetoothDevice, param1Int, param1ArrayOfbyte);
                executor.execute(_$$Lambda$BluetoothAdapter$1$I3p3FVKkxuogQU8eug7PAKoZKZc);
              }  
            return;
          } 
        }
      };
  }
  
  BluetoothAdapter(IBluetoothManager paramIBluetoothManager) {
    if (paramIBluetoothManager != null) {
      try {
        this.mServiceLock.writeLock().lock();
        this.mService = paramIBluetoothManager.registerAdapter(this.mManagerCallback);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      } finally {}
      this.mServiceLock.writeLock().unlock();
      this.mManagerService = paramIBluetoothManager;
      this.mLeScanClients = new HashMap<>();
      this.mToken = (IBinder)new Binder();
      return;
    } 
    throw new IllegalArgumentException("bluetooth manager service is null");
  }
  
  public static boolean checkBluetoothAddress(String paramString) {
    if (paramString == null || paramString.length() != 17)
      return false; 
    for (byte b = 0; b < 17; b++) {
      char c = paramString.charAt(b);
      int i = b % 3;
      if (i != 0 && i != 1) {
        if (i == 2 && c != ':')
          return false; 
      } else if ((c < '0' || c > '9') && (c < 'A' || c > 'F')) {
        return false;
      } 
    } 
    return true;
  }
  
  public static int connectionPolicyToPriority(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 100) ? -1 : 100) : 0;
  }
  
  private BluetoothServerSocket createNewRfcommSocketAndRecord(String paramString, UUID paramUUID, boolean paramBoolean1, boolean paramBoolean2) throws IOException {
    BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(1, paramBoolean1, paramBoolean2, new ParcelUuid(paramUUID));
    bluetoothServerSocket.setServiceName(paramString);
    int i = bluetoothServerSocket.mSocket.bindListen();
    if (i == 0)
      return bluetoothServerSocket; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error: ");
    stringBuilder.append(i);
    throw new IOException(stringBuilder.toString());
  }
  
  private String getAttributionTag() {
    Context context = this.mContext;
    return (context != null) ? context.getAttributionTag() : null;
  }
  
  public static BluetoothAdapter getDefaultAdapter() {
    // Byte code:
    //   0: ldc android/bluetooth/BluetoothAdapter
    //   2: monitorenter
    //   3: getstatic android/bluetooth/BluetoothAdapter.sAdapter : Landroid/bluetooth/BluetoothAdapter;
    //   6: ifnonnull -> 49
    //   9: ldc 'bluetooth_manager'
    //   11: invokestatic getService : (Ljava/lang/String;)Landroid/os/IBinder;
    //   14: astore_0
    //   15: aload_0
    //   16: ifnull -> 40
    //   19: aload_0
    //   20: invokestatic asInterface : (Landroid/os/IBinder;)Landroid/bluetooth/IBluetoothManager;
    //   23: astore_1
    //   24: new android/bluetooth/BluetoothAdapter
    //   27: astore_0
    //   28: aload_0
    //   29: aload_1
    //   30: invokespecial <init> : (Landroid/bluetooth/IBluetoothManager;)V
    //   33: aload_0
    //   34: putstatic android/bluetooth/BluetoothAdapter.sAdapter : Landroid/bluetooth/BluetoothAdapter;
    //   37: goto -> 49
    //   40: ldc 'BluetoothAdapter'
    //   42: ldc_w 'Bluetooth binder is null'
    //   45: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   48: pop
    //   49: getstatic android/bluetooth/BluetoothAdapter.sAdapter : Landroid/bluetooth/BluetoothAdapter;
    //   52: astore_0
    //   53: ldc android/bluetooth/BluetoothAdapter
    //   55: monitorexit
    //   56: aload_0
    //   57: areturn
    //   58: astore_0
    //   59: ldc android/bluetooth/BluetoothAdapter
    //   61: monitorexit
    //   62: aload_0
    //   63: athrow
    // Exception table:
    //   from	to	target	type
    //   3	15	58	finally
    //   19	37	58	finally
    //   40	49	58	finally
    //   49	53	58	finally
  }
  
  private String getOpPackageName() {
    Context context = this.mContext;
    return (context != null) ? context.getOpPackageName() : ActivityThread.currentOpPackageName();
  }
  
  private int getStateInternal() {
    byte b2;
    byte b1 = 10;
    try {
      this.mServiceLock.readLock().lock();
      b2 = b1;
      if (this.mService != null)
        b2 = ((Integer)this.mBluetoothGetStateCache.query(null)).intValue(); 
      this.mServiceLock.readLock().unlock();
    } catch (RuntimeException runtimeException) {
      if (runtimeException.getCause() instanceof RemoteException) {
        Log.e("BluetoothAdapter", "", runtimeException.getCause());
        b2 = b1;
      } else {
        throw runtimeException;
      } 
      this.mServiceLock.readLock().unlock();
    } finally {
      Exception exception;
    } 
    return b2;
  }
  
  public static void invalidateBluetoothGetStateCache() {
    PropertyInvalidatedCache.invalidateCache("cache_key.bluetooth.get_state");
  }
  
  public static void invalidateGetProfileConnectionStateCache() {
    PropertyInvalidatedCache.invalidateCache("cache_key.bluetooth.get_profile_connection_state");
  }
  
  public static void invalidateIsOffloadedFilteringSupportedCache() {
    PropertyInvalidatedCache.invalidateCache("cache_key.bluetooth.is_offloaded_filtering_supported");
  }
  
  private boolean isHearingAidProfileSupported() {
    try {
      return this.mManagerService.isHearingAidProfileSupported();
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "remote expection when calling isHearingAidProfileSupported", (Throwable)remoteException);
      return false;
    } 
  }
  
  public static BluetoothServerSocket listenUsingScoOn() throws IOException {
    BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(2, false, false, -1);
    bluetoothServerSocket.mSocket.bindListen();
    return bluetoothServerSocket;
  }
  
  public static String nameForState(int paramInt) {
    StringBuilder stringBuilder;
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("?!?!? (");
        stringBuilder.append(paramInt);
        stringBuilder.append(")");
        return stringBuilder.toString();
      case 16:
        return "BLE_TURNING_OFF";
      case 15:
        return "BLE_ON";
      case 14:
        return "BLE_TURNING_ON";
      case 13:
        return "TURNING_OFF";
      case 12:
        return "ON";
      case 11:
        return "TURNING_ON";
      case 10:
        break;
    } 
    return "OFF";
  }
  
  public static int priorityToConnectionPolicy(int paramInt) {
    if (paramInt != -1) {
      if (paramInt != 0) {
        if (paramInt != 100) {
          if (paramInt != 1000) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setPriority: Invalid priority: ");
            stringBuilder.append(paramInt);
            Log.e("BluetoothAdapter", stringBuilder.toString());
            return -1;
          } 
          return 100;
        } 
        return 100;
      } 
      return 0;
    } 
    return -1;
  }
  
  private Set<BluetoothDevice> toDeviceSet(BluetoothDevice[] paramArrayOfBluetoothDevice) {
    return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(paramArrayOfBluetoothDevice)));
  }
  
  @SystemApi
  public boolean addOnMetadataChangedListener(BluetoothDevice paramBluetoothDevice, Executor paramExecutor, OnMetadataChangedListener paramOnMetadataChangedListener) {
    Log.d("BluetoothAdapter", "addOnMetadataChangedListener()");
    IBluetooth iBluetooth = this.mService;
    if (iBluetooth == null) {
      Log.e("BluetoothAdapter", "Bluetooth is not enabled. Cannot register metadata listener");
      return false;
    } 
    if (paramOnMetadataChangedListener != null) {
      if (paramBluetoothDevice != null) {
        if (paramExecutor != null)
          synchronized (sMetadataListeners) {
            IllegalArgumentException illegalArgumentException;
            boolean bool3;
            List<Pair<OnMetadataChangedListener, Executor>> list = sMetadataListeners.get(paramBluetoothDevice);
            if (list == null) {
              list = new ArrayList();
              super();
              sMetadataListeners.put(paramBluetoothDevice, list);
            } else {
              Stream<Pair<OnMetadataChangedListener, Executor>> stream = list.stream();
              _$$Lambda$BluetoothAdapter$3qDRCAtPJRu3UbUEFsHnCxkioak _$$Lambda$BluetoothAdapter$3qDRCAtPJRu3UbUEFsHnCxkioak = new _$$Lambda$BluetoothAdapter$3qDRCAtPJRu3UbUEFsHnCxkioak();
              this(paramOnMetadataChangedListener);
              if (stream.anyMatch(_$$Lambda$BluetoothAdapter$3qDRCAtPJRu3UbUEFsHnCxkioak)) {
                illegalArgumentException = new IllegalArgumentException();
                this("listener was already regestered for the device");
                throw illegalArgumentException;
              } 
            } 
            Pair<OnMetadataChangedListener, Executor> pair = new Pair();
            this(paramOnMetadataChangedListener, paramExecutor);
            list.add(pair);
            boolean bool1 = false;
            boolean bool2 = false;
            try {
              Map<BluetoothDevice, List<Pair<OnMetadataChangedListener, Executor>>> map;
              bool3 = iBluetooth.registerMetadataListener(sBluetoothMetadataListener, (BluetoothDevice)illegalArgumentException);
              bool2 = bool3;
              bool3 = bool2;
              if (!bool2) {
                list.remove(pair);
                bool3 = bool2;
                if (list.isEmpty()) {
                  map = sMetadataListeners;
                  bool3 = bool2;
                } else {
                  return bool3;
                } 
              } else {
                return bool3;
              } 
              map.remove(illegalArgumentException);
            } catch (RemoteException remoteException) {
              Map<BluetoothDevice, List<Pair<OnMetadataChangedListener, Executor>>> map;
              Log.e("BluetoothAdapter", "registerMetadataListener fail", (Throwable)remoteException);
              bool3 = bool1;
              if (!false) {
                list.remove(pair);
                bool3 = bool1;
                if (list.isEmpty()) {
                  map = sMetadataListeners;
                  bool3 = bool2;
                } else {
                  return bool3;
                } 
              } else {
                return bool3;
              } 
              map.remove(illegalArgumentException);
            } finally {}
            return bool3;
          }  
        throw new NullPointerException("executor is null");
      } 
      throw new NullPointerException("device is null");
    } 
    throw new NullPointerException("listener is null");
  }
  
  public boolean cancelDiscovery() {
    if (getState() != 12)
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.cancelDiscovery();
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean changeApplicationBluetoothState(boolean paramBoolean, BluetoothStateChangeCallback paramBluetoothStateChangeCallback) {
    return false;
  }
  
  public void closeProfileProxy(int paramInt, BluetoothProfile paramBluetoothProfile) {
    if (paramBluetoothProfile == null)
      return; 
    switch (paramInt) {
      default:
        return;
      case 21:
        ((BluetoothHearingAid)paramBluetoothProfile).close();
      case 19:
        ((BluetoothHidDevice)paramBluetoothProfile).close();
      case 18:
        ((BluetoothMapClient)paramBluetoothProfile).close();
      case 17:
        ((BluetoothPbapClient)paramBluetoothProfile).close();
      case 16:
        ((BluetoothHeadsetClient)paramBluetoothProfile).close();
      case 12:
        ((BluetoothAvrcpController)paramBluetoothProfile).close();
      case 11:
        ((BluetoothA2dpSink)paramBluetoothProfile).close();
      case 10:
        ((BluetoothSap)paramBluetoothProfile).close();
      case 9:
        ((BluetoothMap)paramBluetoothProfile).close();
      case 8:
        ((BluetoothGattServer)paramBluetoothProfile).close();
      case 7:
        ((BluetoothGatt)paramBluetoothProfile).close();
      case 6:
        ((BluetoothPbap)paramBluetoothProfile).close();
      case 5:
        ((BluetoothPan)paramBluetoothProfile).close();
      case 4:
        ((BluetoothHidHost)paramBluetoothProfile).close();
      case 2:
        ((BluetoothA2dp)paramBluetoothProfile).close();
      case 1:
        break;
    } 
    ((BluetoothHeadset)paramBluetoothProfile).close();
  }
  
  public boolean connectAllEnabledProfiles(BluetoothDevice paramBluetoothDevice) {
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.connectAllEnabledProfiles(paramBluetoothDevice);
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {}
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean disable() {
    try {
      return this.mManagerService.disable(ActivityThread.currentPackageName(), true);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean disable(boolean paramBoolean) {
    try {
      return this.mManagerService.disable(ActivityThread.currentPackageName(), paramBoolean);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  @SystemApi
  public boolean disableBLE() {
    if (!isBleScanAlwaysAvailable())
      return false; 
    String str = ActivityThread.currentPackageName();
    try {
      return this.mManagerService.disableBle(str, this.mToken);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public void disableBluetoothGetStateCache() {
    this.mBluetoothGetStateCache.disableLocal();
  }
  
  public void disableGetProfileConnectionStateCache() {
    this.mGetProfileConnectionStateCache.disableLocal();
  }
  
  public void disableIsOffloadedFilteringSupportedCache() {
    this.mBluetoothFilteringCache.disableLocal();
  }
  
  public boolean disconnectAllEnabledProfiles(BluetoothDevice paramBluetoothDevice) {
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.disconnectAllEnabledProfiles(paramBluetoothDevice);
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {}
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean enable() {
    if (isEnabled()) {
      Log.d("BluetoothAdapter", "enable(): BT already enabled!");
      return true;
    } 
    try {
      return this.mManagerService.enable(ActivityThread.currentPackageName());
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  @SystemApi
  public boolean enableBLE() {
    if (!isBleScanAlwaysAvailable())
      return false; 
    String str = ActivityThread.currentPackageName();
    try {
      return this.mManagerService.enableBle(str, this.mToken);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  @SystemApi
  public boolean enableNoAutoConnect() {
    if (isEnabled()) {
      Log.d("BluetoothAdapter", "enableNoAutoConnect(): BT already enabled!");
      return true;
    } 
    try {
      return this.mManagerService.enableNoAutoConnect(ActivityThread.currentPackageName());
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean factoryReset() {
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null && this.mService.factoryReset() && this.mManagerService != null) {
        boolean bool = this.mManagerService.onFactoryReset();
        if (bool) {
          this.mServiceLock.readLock().unlock();
          return true;
        } 
      } 
      Log.e("BluetoothAdapter", "factoryReset(): Setting persist.bluetooth.factoryreset to retry later");
      SystemProperties.set("persist.bluetooth.factoryreset", "true");
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  protected void finalize() throws Throwable {
    try {
      this.mManagerService.unregisterAdapter(this.mManagerCallback);
      super.finalize();
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      super.finalize();
    } finally {
      Exception exception;
    } 
  }
  
  public String getAddress() {
    try {
      return this.mManagerService.getAddress();
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      return null;
    } 
  }
  
  public BluetoothClass getBluetoothClass() {
    if (getState() != 12)
      return null; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        BluetoothClass bluetoothClass = this.mService.getBluetoothClass();
        this.mServiceLock.readLock().unlock();
        return bluetoothClass;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return null;
  }
  
  public BluetoothLeAdvertiser getBluetoothLeAdvertiser() {
    if (!getLeAccess())
      return null; 
    synchronized (this.mLock) {
      if (sBluetoothLeAdvertiser == null) {
        BluetoothLeAdvertiser bluetoothLeAdvertiser = new BluetoothLeAdvertiser();
        this(this.mManagerService);
        sBluetoothLeAdvertiser = bluetoothLeAdvertiser;
      } 
      return sBluetoothLeAdvertiser;
    } 
  }
  
  public BluetoothLeScanner getBluetoothLeScanner() {
    if (!getLeAccess())
      return null; 
    synchronized (this.mLock) {
      if (sBluetoothLeScanner == null) {
        BluetoothLeScanner bluetoothLeScanner = new BluetoothLeScanner();
        this(this.mManagerService, getOpPackageName(), getAttributionTag());
        sBluetoothLeScanner = bluetoothLeScanner;
      } 
      return sBluetoothLeScanner;
    } 
  }
  
  IBluetoothManager getBluetoothManager() {
    return this.mManagerService;
  }
  
  IBluetooth getBluetoothService(IBluetoothManagerCallback paramIBluetoothManagerCallback) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mProxyServiceStateCallbacks : Ljava/util/ArrayList;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_1
    //   8: ifnonnull -> 23
    //   11: ldc 'BluetoothAdapter'
    //   13: ldc_w 'getBluetoothService() called with no BluetoothManagerCallback'
    //   16: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   19: pop
    //   20: goto -> 43
    //   23: aload_0
    //   24: getfield mProxyServiceStateCallbacks : Ljava/util/ArrayList;
    //   27: aload_1
    //   28: invokevirtual contains : (Ljava/lang/Object;)Z
    //   31: ifne -> 43
    //   34: aload_0
    //   35: getfield mProxyServiceStateCallbacks : Ljava/util/ArrayList;
    //   38: aload_1
    //   39: invokevirtual add : (Ljava/lang/Object;)Z
    //   42: pop
    //   43: aload_2
    //   44: monitorexit
    //   45: aload_0
    //   46: getfield mService : Landroid/bluetooth/IBluetooth;
    //   49: areturn
    //   50: astore_1
    //   51: aload_2
    //   52: monitorexit
    //   53: aload_1
    //   54: athrow
    // Exception table:
    //   from	to	target	type
    //   11	20	50	finally
    //   23	43	50	finally
    //   43	45	50	finally
    //   51	53	50	finally
  }
  
  public Set<BluetoothDevice> getBondedDevices() {
    Exception exception;
    if (getState() != 12)
      return toDeviceSet(new BluetoothDevice[0]); 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        Set<BluetoothDevice> set1 = toDeviceSet(this.mService.getBondedDevices());
        this.mServiceLock.readLock().unlock();
        return set1;
      } 
      Set<BluetoothDevice> set = toDeviceSet(new BluetoothDevice[0]);
      this.mServiceLock.readLock().unlock();
      return set;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      this.mServiceLock.readLock().unlock();
      return null;
    } finally {}
    this.mServiceLock.readLock().unlock();
    throw exception;
  }
  
  public int getConnectionState() {
    if (getState() != 12)
      return 0; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        int i = this.mService.getAdapterConnectionState();
        this.mServiceLock.readLock().unlock();
        return i;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "getConnectionState:", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return 0;
  }
  
  @Deprecated
  public BluetoothActivityEnergyInfo getControllerActivityEnergyInfo(int paramInt) {
    SynchronousResultReceiver synchronousResultReceiver = new SynchronousResultReceiver();
    requestControllerActivityEnergyInfo((ResultReceiver)synchronousResultReceiver);
    try {
      SynchronousResultReceiver.Result result = synchronousResultReceiver.awaitResult(1000L);
      if (result.bundle != null)
        return (BluetoothActivityEnergyInfo)result.bundle.getParcelable("controller_activity"); 
    } catch (TimeoutException timeoutException) {
      Log.e("BluetoothAdapter", "getControllerActivityEnergyInfo timed out");
    } 
    return null;
  }
  
  public int getDiscoverableTimeout() {
    if (getState() != 12)
      return -1; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        int i = this.mService.getDiscoverableTimeout();
        this.mServiceLock.readLock().unlock();
        return i;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return -1;
  }
  
  @SystemApi
  public long getDiscoveryEndMillis() {
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        long l = this.mService.getDiscoveryEndMillis();
        this.mServiceLock.readLock().unlock();
        return l;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return -1L;
  }
  
  public int getIoCapability() {
    if (getState() != 12)
      return 255; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        int i = this.mService.getIoCapability();
        this.mServiceLock.readLock().unlock();
        return i;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", remoteException.getMessage(), (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return 255;
  }
  
  boolean getLeAccess() {
    return (getLeState() == 12) ? true : ((getLeState() == 15));
  }
  
  public int getLeIoCapability() {
    if (getState() != 12)
      return 255; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        int i = this.mService.getLeIoCapability();
        this.mServiceLock.readLock().unlock();
        return i;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", remoteException.getMessage(), (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return 255;
  }
  
  public int getLeMaximumAdvertisingDataLength() {
    if (!getLeAccess())
      return 0; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        int i = this.mService.getLeMaximumAdvertisingDataLength();
        this.mServiceLock.readLock().unlock();
        return i;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "failed to get getLeMaximumAdvertisingDataLength, error: ", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return 0;
  }
  
  public int getLeState() {
    return getStateInternal();
  }
  
  public int getMaxConnectedAudioDevices() {
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        int i = this.mService.getMaxConnectedAudioDevices();
        this.mServiceLock.readLock().unlock();
        return i;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "failed to get getMaxConnectedAudioDevices, error: ", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return 1;
  }
  
  public List<BluetoothDevice> getMostRecentlyConnectedDevices() {
    if (getState() != 12)
      return new ArrayList<>(); 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        List<BluetoothDevice> list = this.mService.getMostRecentlyConnectedDevices();
        this.mServiceLock.readLock().unlock();
        return list;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return new ArrayList<>();
  }
  
  public String getName() {
    try {
      return this.mManagerService.getName();
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      return null;
    } 
  }
  
  public PeriodicAdvertisingManager getPeriodicAdvertisingManager() {
    if (!getLeAccess())
      return null; 
    if (!isLePeriodicAdvertisingSupported())
      return null; 
    synchronized (this.mLock) {
      if (sPeriodicAdvertisingManager == null) {
        PeriodicAdvertisingManager periodicAdvertisingManager = new PeriodicAdvertisingManager();
        this(this.mManagerService);
        sPeriodicAdvertisingManager = periodicAdvertisingManager;
      } 
      return sPeriodicAdvertisingManager;
    } 
  }
  
  public int getProfileConnectionState(int paramInt) {
    return (getState() != 12) ? 0 : ((Integer)this.mGetProfileConnectionStateCache.query(new Integer(paramInt))).intValue();
  }
  
  public boolean getProfileProxy(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener, int paramInt) {
    if (paramContext == null || paramServiceListener == null)
      return false; 
    if (paramInt == 1) {
      new BluetoothHeadset(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 2) {
      new BluetoothA2dp(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 11) {
      new BluetoothA2dpSink(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 12) {
      new BluetoothAvrcpController(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 4) {
      new BluetoothHidHost(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 5) {
      new BluetoothPan(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 6) {
      new BluetoothPbap(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 3) {
      Log.e("BluetoothAdapter", "getProfileProxy(): BluetoothHealth is deprecated");
      return false;
    } 
    if (paramInt == 9) {
      new BluetoothMap(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 16) {
      new BluetoothHeadsetClient(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 10) {
      new BluetoothSap(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 17) {
      new BluetoothPbapClient(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 18) {
      new BluetoothMapClient(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 19) {
      new BluetoothHidDevice(paramContext, paramServiceListener);
      return true;
    } 
    if (paramInt == 21) {
      if (isHearingAidProfileSupported()) {
        new BluetoothHearingAid(paramContext, paramServiceListener);
        return true;
      } 
      return false;
    } 
    return false;
  }
  
  public BluetoothDevice getRemoteDevice(String paramString) {
    return new BluetoothDevice(paramString);
  }
  
  public BluetoothDevice getRemoteDevice(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte != null && paramArrayOfbyte.length == 6)
      return new BluetoothDevice(String.format(Locale.US, "%02X:%02X:%02X:%02X:%02X:%02X", new Object[] { Byte.valueOf(paramArrayOfbyte[0]), Byte.valueOf(paramArrayOfbyte[1]), Byte.valueOf(paramArrayOfbyte[2]), Byte.valueOf(paramArrayOfbyte[3]), Byte.valueOf(paramArrayOfbyte[4]), Byte.valueOf(paramArrayOfbyte[5]) })); 
    throw new IllegalArgumentException("Bluetooth address must have 6 bytes");
  }
  
  public int getScanMode() {
    if (getState() != 12)
      return 20; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        int i = this.mService.getScanMode();
        this.mServiceLock.readLock().unlock();
        return i;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return 20;
  }
  
  public int getState() {
    int i = getStateInternal();
    if (i != 15 && i != 14) {
      int j = i;
      return (i == 16) ? 10 : j;
    } 
    return 10;
  }
  
  public List<Integer> getSupportedProfiles() {
    ArrayList<Integer> arrayList = new ArrayList();
    try {
      synchronized (this.mManagerCallback) {
        if (this.mService != null) {
          long l = this.mService.getSupportedProfiles();
          for (byte b = 0; b <= 21; b++) {
            if (((1 << b) & l) != 0L)
              arrayList.add(Integer.valueOf(b)); 
          } 
        } else if (isHearingAidProfileSupported()) {
          arrayList.add(Integer.valueOf(21));
        } 
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "getSupportedProfiles:", (Throwable)remoteException);
    } 
    return arrayList;
  }
  
  public ParcelUuid[] getUuids() {
    if (getState() != 12)
      return null; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        ParcelUuid[] arrayOfParcelUuid = this.mService.getUuids();
        this.mServiceLock.readLock().unlock();
        return arrayOfParcelUuid;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return null;
  }
  
  @SystemApi
  public boolean isBleScanAlwaysAvailable() {
    try {
      return this.mManagerService.isBleScanAlwaysAvailable();
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "remote expection when calling isBleScanAlwaysAvailable", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean isDiscovering() {
    if (getState() != 12)
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.isDiscovering();
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean isEnabled() {
    boolean bool;
    if (getState() == 12) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isHardwareTrackingFiltersAvailable() {
    boolean bool = getLeAccess();
    boolean bool1 = false;
    if (!bool)
      return false; 
    try {
      IBluetoothGatt iBluetoothGatt = this.mManagerService.getBluetoothGatt();
      if (iBluetoothGatt == null)
        return false; 
      int i = iBluetoothGatt.numHwTrackFiltersAvailable();
      if (i != 0)
        bool1 = true; 
      return bool1;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean isLe2MPhySupported() {
    if (!getLeAccess())
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.isLe2MPhySupported();
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "failed to get isExtendedAdvertisingSupported, error: ", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean isLeCodedPhySupported() {
    if (!getLeAccess())
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.isLeCodedPhySupported();
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "failed to get isLeCodedPhySupported, error: ", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  @SystemApi
  public boolean isLeEnabled() {
    int i = getLeState();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("isLeEnabled(): ");
    stringBuilder.append(nameForState(i));
    Log.d("BluetoothAdapter", stringBuilder.toString());
    return (i == 12 || i == 15 || i == 11 || i == 13);
  }
  
  public boolean isLeExtendedAdvertisingSupported() {
    if (!getLeAccess())
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.isLeExtendedAdvertisingSupported();
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "failed to get isLeExtendedAdvertisingSupported, error: ", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean isLePeriodicAdvertisingSupported() {
    if (!getLeAccess())
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.isLePeriodicAdvertisingSupported();
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "failed to get isLePeriodicAdvertisingSupported, error: ", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean isMultipleAdvertisementSupported() {
    if (getState() != 12)
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.isMultiAdvertisementSupported();
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "failed to get isMultipleAdvertisementSupported, error: ", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean isOffloadedFilteringSupported() {
    return !getLeAccess() ? false : ((Boolean)this.mBluetoothFilteringCache.query(null)).booleanValue();
  }
  
  public boolean isOffloadedScanBatchingSupported() {
    if (!getLeAccess())
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.isOffloadedScanBatchingSupported();
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "failed to get isOffloadedScanBatchingSupported, error: ", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public BluetoothServerSocket listenUsingEncryptedRfcommOn(int paramInt) throws IOException {
    BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(1, false, true, paramInt);
    int i = bluetoothServerSocket.mSocket.bindListen();
    if (paramInt == -2)
      bluetoothServerSocket.setChannel(bluetoothServerSocket.mSocket.getPort()); 
    if (i >= 0)
      return bluetoothServerSocket; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error: ");
    stringBuilder.append(i);
    throw new IOException(stringBuilder.toString());
  }
  
  public BluetoothServerSocket listenUsingEncryptedRfcommWithServiceRecord(String paramString, UUID paramUUID) throws IOException {
    return createNewRfcommSocketAndRecord(paramString, paramUUID, false, true);
  }
  
  public BluetoothServerSocket listenUsingInsecureL2capChannel() throws IOException {
    BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(4, false, false, -2, false, false);
    int i = bluetoothServerSocket.mSocket.bindListen();
    if (i == 0) {
      i = bluetoothServerSocket.mSocket.getPort();
      if (i != 0) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("listenUsingInsecureL2capChannel: set assigned PSM to ");
        stringBuilder1.append(i);
        Log.d("BluetoothAdapter", stringBuilder1.toString());
        bluetoothServerSocket.setChannel(i);
        return bluetoothServerSocket;
      } 
      throw new IOException("Error: Unable to assign PSM value");
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error: ");
    stringBuilder.append(i);
    throw new IOException(stringBuilder.toString());
  }
  
  public BluetoothServerSocket listenUsingInsecureL2capOn(int paramInt) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("listenUsingInsecureL2capOn: port=");
    stringBuilder.append(paramInt);
    Log.d("BluetoothAdapter", stringBuilder.toString());
    BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(3, false, false, paramInt, false, false);
    int i = bluetoothServerSocket.mSocket.bindListen();
    if (paramInt == -2) {
      paramInt = bluetoothServerSocket.mSocket.getPort();
      stringBuilder = new StringBuilder();
      stringBuilder.append("listenUsingInsecureL2capOn: set assigned channel to ");
      stringBuilder.append(paramInt);
      Log.d("BluetoothAdapter", stringBuilder.toString());
      bluetoothServerSocket.setChannel(paramInt);
    } 
    if (i == 0)
      return bluetoothServerSocket; 
    stringBuilder = new StringBuilder();
    stringBuilder.append("Error: ");
    stringBuilder.append(i);
    throw new IOException(stringBuilder.toString());
  }
  
  public BluetoothServerSocket listenUsingInsecureRfcommOn(int paramInt) throws IOException {
    BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(1, false, false, paramInt);
    int i = bluetoothServerSocket.mSocket.bindListen();
    if (paramInt == -2)
      bluetoothServerSocket.setChannel(bluetoothServerSocket.mSocket.getPort()); 
    if (i == 0)
      return bluetoothServerSocket; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error: ");
    stringBuilder.append(i);
    throw new IOException(stringBuilder.toString());
  }
  
  public BluetoothServerSocket listenUsingInsecureRfcommWithServiceRecord(String paramString, UUID paramUUID) throws IOException {
    return createNewRfcommSocketAndRecord(paramString, paramUUID, false, false);
  }
  
  public BluetoothServerSocket listenUsingL2capChannel() throws IOException {
    BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(4, true, true, -2, false, false);
    int i = bluetoothServerSocket.mSocket.bindListen();
    if (i == 0) {
      i = bluetoothServerSocket.mSocket.getPort();
      if (i != 0) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("listenUsingL2capChannel: set assigned PSM to ");
        stringBuilder1.append(i);
        Log.d("BluetoothAdapter", stringBuilder1.toString());
        bluetoothServerSocket.setChannel(i);
        return bluetoothServerSocket;
      } 
      throw new IOException("Error: Unable to assign PSM value");
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error: ");
    stringBuilder.append(i);
    throw new IOException(stringBuilder.toString());
  }
  
  public BluetoothServerSocket listenUsingL2capOn(int paramInt) throws IOException {
    return listenUsingL2capOn(paramInt, false, false);
  }
  
  public BluetoothServerSocket listenUsingL2capOn(int paramInt, boolean paramBoolean1, boolean paramBoolean2) throws IOException {
    BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(3, true, true, paramInt, paramBoolean1, paramBoolean2);
    int i = bluetoothServerSocket.mSocket.bindListen();
    if (paramInt == -2) {
      paramInt = bluetoothServerSocket.mSocket.getPort();
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("listenUsingL2capOn: set assigned channel to ");
      stringBuilder1.append(paramInt);
      Log.d("BluetoothAdapter", stringBuilder1.toString());
      bluetoothServerSocket.setChannel(paramInt);
    } 
    if (i == 0)
      return bluetoothServerSocket; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error: ");
    stringBuilder.append(i);
    throw new IOException(stringBuilder.toString());
  }
  
  public BluetoothServerSocket listenUsingRfcommOn(int paramInt) throws IOException {
    return listenUsingRfcommOn(paramInt, false, false);
  }
  
  public BluetoothServerSocket listenUsingRfcommOn(int paramInt, boolean paramBoolean1, boolean paramBoolean2) throws IOException {
    BluetoothServerSocket bluetoothServerSocket = new BluetoothServerSocket(1, true, true, paramInt, paramBoolean1, paramBoolean2);
    int i = bluetoothServerSocket.mSocket.bindListen();
    if (paramInt == -2)
      bluetoothServerSocket.setChannel(bluetoothServerSocket.mSocket.getPort()); 
    if (i == 0)
      return bluetoothServerSocket; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error: ");
    stringBuilder.append(i);
    throw new IOException(stringBuilder.toString());
  }
  
  public BluetoothServerSocket listenUsingRfcommWithServiceRecord(String paramString, UUID paramUUID) throws IOException {
    return createNewRfcommSocketAndRecord(paramString, paramUUID, true, true);
  }
  
  public Pair<byte[], byte[]> readOutOfBandData() {
    return null;
  }
  
  @SystemApi
  public boolean removeActiveDevice(int paramInt) {
    if (paramInt == 0 || paramInt == 1 || paramInt == 2) {
      try {
        this.mServiceLock.readLock().lock();
        if (this.mService != null) {
          boolean bool = this.mService.removeActiveDevice(paramInt);
          this.mServiceLock.readLock().unlock();
          return bool;
        } 
      } catch (RemoteException remoteException) {
        Log.e("BluetoothAdapter", "", (Throwable)remoteException);
      } finally {
        Exception exception;
      } 
      this.mServiceLock.readLock().unlock();
      return false;
    } 
    Log.e("BluetoothAdapter", "Invalid profiles param value in removeActiveDevice");
    throw new IllegalArgumentException("Profiles must be one of BluetoothAdapter.ACTIVE_DEVICE_AUDIO, BluetoothAdapter.ACTIVE_DEVICE_PHONE_CALL, or BluetoothAdapter.ACTIVE_DEVICE_ALL");
  }
  
  @SystemApi
  public boolean removeOnMetadataChangedListener(BluetoothDevice paramBluetoothDevice, OnMetadataChangedListener paramOnMetadataChangedListener) {
    Log.d("BluetoothAdapter", "removeOnMetadataChangedListener()");
    if (paramBluetoothDevice != null) {
      if (paramOnMetadataChangedListener != null)
        synchronized (sMetadataListeners) {
          if (sMetadataListeners.containsKey(paramBluetoothDevice)) {
            List list = sMetadataListeners.get(paramBluetoothDevice);
            _$$Lambda$BluetoothAdapter$dhiyWTssvWZcLytIeq61ARYDHrc _$$Lambda$BluetoothAdapter$dhiyWTssvWZcLytIeq61ARYDHrc = new _$$Lambda$BluetoothAdapter$dhiyWTssvWZcLytIeq61ARYDHrc();
            this(paramOnMetadataChangedListener);
            list.removeIf(_$$Lambda$BluetoothAdapter$dhiyWTssvWZcLytIeq61ARYDHrc);
            if (((List)sMetadataListeners.get(paramBluetoothDevice)).isEmpty()) {
              sMetadataListeners.remove(paramBluetoothDevice);
              IBluetooth iBluetooth = this.mService;
              if (iBluetooth == null)
                return true; 
              try {
                return iBluetooth.unregisterMetadataListener(paramBluetoothDevice);
              } catch (RemoteException remoteException) {
                Log.e("BluetoothAdapter", "unregisterMetadataListener fail", (Throwable)remoteException);
                return false;
              } 
            } 
            return true;
          } 
          IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
          this("device was not registered");
          throw illegalArgumentException;
        }  
      throw new NullPointerException("listener is null");
    } 
    throw new NullPointerException("device is null");
  }
  
  void removeServiceStateCallback(IBluetoothManagerCallback paramIBluetoothManagerCallback) {
    synchronized (this.mProxyServiceStateCallbacks) {
      this.mProxyServiceStateCallbacks.remove(paramIBluetoothManagerCallback);
      return;
    } 
  }
  
  public void requestControllerActivityEnergyInfo(ResultReceiver paramResultReceiver) {
    try {
      this.mServiceLock.readLock().lock();
      ResultReceiver resultReceiver = paramResultReceiver;
      if (this.mService != null) {
        this.mService.requestActivityInfo(paramResultReceiver);
        resultReceiver = null;
      } 
      this.mServiceLock.readLock().unlock();
      if (resultReceiver != null) {
        paramResultReceiver = resultReceiver;
      } else {
        return;
      } 
      paramResultReceiver.send(0, null);
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("getControllerActivityEnergyInfoCallback: ");
      stringBuilder.append(remoteException);
      Log.e("BluetoothAdapter", stringBuilder.toString());
      this.mServiceLock.readLock().unlock();
      if (paramResultReceiver != null)
        paramResultReceiver.send(0, null); 
    } finally {
      Exception exception;
    } 
  }
  
  @SystemApi
  public boolean setActiveDevice(BluetoothDevice paramBluetoothDevice, int paramInt) {
    if (paramBluetoothDevice != null) {
      if (paramInt == 0 || paramInt == 1 || paramInt == 2) {
        try {
          this.mServiceLock.readLock().lock();
          if (this.mService != null) {
            boolean bool = this.mService.setActiveDevice(paramBluetoothDevice, paramInt);
            this.mServiceLock.readLock().unlock();
            return bool;
          } 
        } catch (RemoteException remoteException) {
          Log.e("BluetoothAdapter", "", (Throwable)remoteException);
        } finally {}
        this.mServiceLock.readLock().unlock();
        return false;
      } 
      Log.e("BluetoothAdapter", "Invalid profiles param value in setActiveDevice");
      throw new IllegalArgumentException("Profiles must be one of BluetoothAdapter.ACTIVE_DEVICE_AUDIO, BluetoothAdapter.ACTIVE_DEVICE_PHONE_CALL, or BluetoothAdapter.ACTIVE_DEVICE_ALL");
    } 
    Log.e("BluetoothAdapter", "setActiveDevice: Null device passed as parameter");
    throw new IllegalArgumentException("device cannot be null");
  }
  
  public boolean setBluetoothClass(BluetoothClass paramBluetoothClass) {
    if (getState() != 12)
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.setBluetoothClass(paramBluetoothClass);
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {}
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public void setContext(Context paramContext) {
    this.mContext = paramContext;
  }
  
  public void setDiscoverableTimeout(int paramInt) {
    if (getState() != 12)
      return; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null)
        this.mService.setDiscoverableTimeout(paramInt); 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
  }
  
  public boolean setIoCapability(int paramInt) {
    if (getState() != 12)
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.setIoCapability(paramInt);
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", remoteException.getMessage(), (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean setLeIoCapability(int paramInt) {
    if (getState() != 12)
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.setLeIoCapability(paramInt);
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", remoteException.getMessage(), (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean setName(String paramString) {
    if (getState() != 12)
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.setName(paramString);
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {}
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean setScanMode(int paramInt) {
    if (getState() != 12)
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.setScanMode(paramInt, getDiscoverableTimeout());
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean setScanMode(int paramInt, long paramLong) {
    if (getState() != 12)
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        int i = Math.toIntExact(paramLong / 1000L);
        boolean bool = this.mService.setScanMode(paramInt, i);
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } catch (ArithmeticException arithmeticException) {
      Log.e("BluetoothAdapter", "setScanMode: Duration in seconds outside of the bounds of an int");
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this("Duration not in bounds. In seconds, the durationMillis must be in the range of an int");
      throw illegalArgumentException;
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  public boolean startDiscovery() {
    if (getState() != 12)
      return false; 
    try {
      this.mServiceLock.readLock().lock();
      if (this.mService != null) {
        boolean bool = this.mService.startDiscovery(getOpPackageName(), getAttributionTag());
        this.mServiceLock.readLock().unlock();
        return bool;
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "", (Throwable)remoteException);
    } finally {
      Exception exception;
    } 
    this.mServiceLock.readLock().unlock();
    return false;
  }
  
  @Deprecated
  public boolean startLeScan(LeScanCallback paramLeScanCallback) {
    return startLeScan(null, paramLeScanCallback);
  }
  
  @Deprecated
  public boolean startLeScan(UUID[] paramArrayOfUUID, LeScanCallback paramLeScanCallback) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("startLeScan(): ");
    stringBuilder.append(Arrays.toString((Object[])paramArrayOfUUID));
    Log.d("BluetoothAdapter", stringBuilder.toString());
    if (paramLeScanCallback == null) {
      Log.e("BluetoothAdapter", "startLeScan: null callback");
      return false;
    } 
    BluetoothLeScanner bluetoothLeScanner = getBluetoothLeScanner();
    if (bluetoothLeScanner == null) {
      Log.e("BluetoothAdapter", "startLeScan: cannot get BluetoothLeScanner");
      return false;
    } 
    synchronized (this.mLeScanClients) {
      if (this.mLeScanClients.containsKey(paramLeScanCallback)) {
        Log.e("BluetoothAdapter", "LE Scan has already started");
        return false;
      } 
      try {
        IBluetoothGatt iBluetoothGatt = this.mManagerService.getBluetoothGatt();
        if (iBluetoothGatt == null)
          return false; 
        ScanCallback scanCallback = new ScanCallback() {
            public void onScanResult(int param1Int, ScanResult param1ScanResult) {
              if (param1Int != 1) {
                Log.e("BluetoothAdapter", "LE Scan has already started");
                return;
              } 
              ScanRecord scanRecord = param1ScanResult.getScanRecord();
              if (scanRecord == null)
                return; 
              if (serviceUuids != null) {
                ArrayList<ParcelUuid> arrayList = new ArrayList();
                UUID[] arrayOfUUID = serviceUuids;
                int i = arrayOfUUID.length;
                for (param1Int = 0; param1Int < i; param1Int++)
                  arrayList.add(new ParcelUuid(arrayOfUUID[param1Int])); 
                List list = scanRecord.getServiceUuids();
                if (list == null || !list.containsAll(arrayList)) {
                  Log.d("BluetoothAdapter", "uuids does not match");
                  return;
                } 
              } 
              callback.onLeScan(param1ScanResult.getDevice(), param1ScanResult.getRssi(), scanRecord.getBytes());
            }
          };
        super(this, paramArrayOfUUID, paramLeScanCallback);
        ScanSettings.Builder builder = new ScanSettings.Builder();
        this();
        ScanSettings scanSettings = builder.setCallbackType(1).setScanMode(2).build();
        ArrayList<ScanFilter> arrayList = new ArrayList();
        this();
        if (paramArrayOfUUID != null && paramArrayOfUUID.length > 0) {
          ScanFilter.Builder builder1 = new ScanFilter.Builder();
          this();
          ParcelUuid parcelUuid = new ParcelUuid();
          this(paramArrayOfUUID[0]);
          arrayList.add(builder1.setServiceUuid(parcelUuid).build());
        } 
        bluetoothLeScanner.startScan(arrayList, scanSettings, scanCallback);
        this.mLeScanClients.put(paramLeScanCallback, scanCallback);
        return true;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothAdapter", "", (Throwable)remoteException);
        return false;
      } 
    } 
  }
  
  @Deprecated
  public void stopLeScan(LeScanCallback paramLeScanCallback) {
    Log.d("BluetoothAdapter", "stopLeScan()");
    BluetoothLeScanner bluetoothLeScanner = getBluetoothLeScanner();
    if (bluetoothLeScanner == null)
      return; 
    synchronized (this.mLeScanClients) {
      ScanCallback scanCallback = this.mLeScanClients.remove(paramLeScanCallback);
      if (scanCallback == null) {
        Log.d("BluetoothAdapter", "scan not started yet");
        return;
      } 
      bluetoothLeScanner.stopScan(scanCallback);
      return;
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ActiveDeviceUse {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AdapterState {}
  
  public static interface BluetoothStateChangeCallback {
    void onBluetoothStateChange(boolean param1Boolean);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface IoCapability {}
  
  public static interface LeScanCallback {
    void onLeScan(BluetoothDevice param1BluetoothDevice, int param1Int, byte[] param1ArrayOfbyte);
  }
  
  @SystemApi
  public static interface OnMetadataChangedListener {
    void onMetadataChanged(BluetoothDevice param1BluetoothDevice, int param1Int, byte[] param1ArrayOfbyte);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ScanMode {}
  
  public class StateChangeCallbackWrapper extends IBluetoothStateChangeCallback.Stub {
    private BluetoothAdapter.BluetoothStateChangeCallback mCallback;
    
    StateChangeCallbackWrapper(BluetoothAdapter.BluetoothStateChangeCallback param1BluetoothStateChangeCallback) {
      this.mCallback = param1BluetoothStateChangeCallback;
    }
    
    public void onBluetoothStateChange(boolean param1Boolean) {
      this.mCallback.onBluetoothStateChange(param1Boolean);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */