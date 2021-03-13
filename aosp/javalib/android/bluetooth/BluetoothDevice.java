package android.bluetooth;

import android.annotation.SystemApi;
import android.app.PropertyInvalidatedCache;
import android.content.Context;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

public final class BluetoothDevice implements Parcelable {
  @SystemApi
  public static final int ACCESS_ALLOWED = 1;
  
  @SystemApi
  public static final int ACCESS_REJECTED = 2;
  
  @SystemApi
  public static final int ACCESS_UNKNOWN = 0;
  
  public static final String ACTION_ACL_CONNECTED = "android.bluetooth.device.action.ACL_CONNECTED";
  
  public static final String ACTION_ACL_DISCONNECTED = "android.bluetooth.device.action.ACL_DISCONNECTED";
  
  public static final String ACTION_ACL_DISCONNECT_REQUESTED = "android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED";
  
  public static final String ACTION_ALIAS_CHANGED = "android.bluetooth.device.action.ALIAS_CHANGED";
  
  public static final String ACTION_BATTERY_LEVEL_CHANGED = "android.bluetooth.device.action.BATTERY_LEVEL_CHANGED";
  
  public static final String ACTION_BOND_STATE_CHANGED = "android.bluetooth.device.action.BOND_STATE_CHANGED";
  
  public static final String ACTION_CLASS_CHANGED = "android.bluetooth.device.action.CLASS_CHANGED";
  
  public static final String ACTION_CONNECTION_ACCESS_CANCEL = "android.bluetooth.device.action.CONNECTION_ACCESS_CANCEL";
  
  public static final String ACTION_CONNECTION_ACCESS_REPLY = "android.bluetooth.device.action.CONNECTION_ACCESS_REPLY";
  
  public static final String ACTION_CONNECTION_ACCESS_REQUEST = "android.bluetooth.device.action.CONNECTION_ACCESS_REQUEST";
  
  public static final String ACTION_FOUND = "android.bluetooth.device.action.FOUND";
  
  public static final String ACTION_MAS_INSTANCE = "android.bluetooth.device.action.MAS_INSTANCE";
  
  public static final String ACTION_NAME_CHANGED = "android.bluetooth.device.action.NAME_CHANGED";
  
  public static final String ACTION_NAME_FAILED = "android.bluetooth.device.action.NAME_FAILED";
  
  public static final String ACTION_PAIRING_CANCEL = "android.bluetooth.device.action.PAIRING_CANCEL";
  
  public static final String ACTION_PAIRING_REQUEST = "android.bluetooth.device.action.PAIRING_REQUEST";
  
  public static final String ACTION_SDP_RECORD = "android.bluetooth.device.action.SDP_RECORD";
  
  @SystemApi
  public static final String ACTION_SILENCE_MODE_CHANGED = "android.bluetooth.device.action.SILENCE_MODE_CHANGED";
  
  public static final String ACTION_UUID = "android.bluetooth.device.action.UUID";
  
  public static final int BATTERY_LEVEL_BLUETOOTH_OFF = -100;
  
  public static final int BATTERY_LEVEL_UNKNOWN = -1;
  
  private static final String BLUETOOTH_BONDING_CACHE_PROPERTY = "cache_key.bluetooth.get_bond_state";
  
  public static final int BOND_BONDED = 12;
  
  public static final int BOND_BONDING = 11;
  
  public static final int BOND_NONE = 10;
  
  public static final int BOND_SUCCESS = 0;
  
  public static final int CONNECTION_ACCESS_NO = 2;
  
  public static final int CONNECTION_ACCESS_YES = 1;
  
  private static final int CONNECTION_STATE_CONNECTED = 1;
  
  private static final int CONNECTION_STATE_DISCONNECTED = 0;
  
  private static final int CONNECTION_STATE_ENCRYPTED_BREDR = 2;
  
  private static final int CONNECTION_STATE_ENCRYPTED_LE = 4;
  
  public static final Parcelable.Creator<BluetoothDevice> CREATOR;
  
  private static final boolean DBG = false;
  
  public static final int DEVICE_TYPE_CLASSIC = 1;
  
  public static final int DEVICE_TYPE_DUAL = 3;
  
  public static final int DEVICE_TYPE_LE = 2;
  
  public static final int DEVICE_TYPE_UNKNOWN = 0;
  
  public static final int ERROR = -2147483648;
  
  public static final String EXTRA_ACCESS_REQUEST_TYPE = "android.bluetooth.device.extra.ACCESS_REQUEST_TYPE";
  
  public static final String EXTRA_ALWAYS_ALLOWED = "android.bluetooth.device.extra.ALWAYS_ALLOWED";
  
  public static final String EXTRA_BATTERY_LEVEL = "android.bluetooth.device.extra.BATTERY_LEVEL";
  
  public static final String EXTRA_BOND_STATE = "android.bluetooth.device.extra.BOND_STATE";
  
  public static final String EXTRA_CLASS = "android.bluetooth.device.extra.CLASS";
  
  public static final String EXTRA_CLASS_NAME = "android.bluetooth.device.extra.CLASS_NAME";
  
  public static final String EXTRA_CONNECTION_ACCESS_RESULT = "android.bluetooth.device.extra.CONNECTION_ACCESS_RESULT";
  
  public static final String EXTRA_DEVICE = "android.bluetooth.device.extra.DEVICE";
  
  public static final String EXTRA_MAS_INSTANCE = "android.bluetooth.device.extra.MAS_INSTANCE";
  
  public static final String EXTRA_NAME = "android.bluetooth.device.extra.NAME";
  
  public static final String EXTRA_PACKAGE_NAME = "android.bluetooth.device.extra.PACKAGE_NAME";
  
  public static final String EXTRA_PAIRING_KEY = "android.bluetooth.device.extra.PAIRING_KEY";
  
  public static final String EXTRA_PAIRING_VARIANT = "android.bluetooth.device.extra.PAIRING_VARIANT";
  
  public static final String EXTRA_PREVIOUS_BOND_STATE = "android.bluetooth.device.extra.PREVIOUS_BOND_STATE";
  
  public static final String EXTRA_REASON = "android.bluetooth.device.extra.REASON";
  
  public static final String EXTRA_RSSI = "android.bluetooth.device.extra.RSSI";
  
  public static final String EXTRA_SDP_RECORD = "android.bluetooth.device.extra.SDP_RECORD";
  
  public static final String EXTRA_SDP_SEARCH_STATUS = "android.bluetooth.device.extra.SDP_SEARCH_STATUS";
  
  public static final String EXTRA_UUID = "android.bluetooth.device.extra.UUID";
  
  @SystemApi
  public static final int METADATA_COMPANION_APP = 4;
  
  @SystemApi
  public static final int METADATA_ENHANCED_SETTINGS_UI_URI = 16;
  
  @SystemApi
  public static final int METADATA_HARDWARE_VERSION = 3;
  
  @SystemApi
  public static final int METADATA_IS_UNTETHERED_HEADSET = 6;
  
  @SystemApi
  public static final int METADATA_MAIN_ICON = 5;
  
  @SystemApi
  public static final int METADATA_MANUFACTURER_NAME = 0;
  
  @SystemApi
  public static final int METADATA_MAX_LENGTH = 2048;
  
  @SystemApi
  public static final int METADATA_MODEL_NAME = 1;
  
  @SystemApi
  public static final int METADATA_SOFTWARE_VERSION = 2;
  
  @SystemApi
  public static final int METADATA_UNTETHERED_CASE_BATTERY = 12;
  
  @SystemApi
  public static final int METADATA_UNTETHERED_CASE_CHARGING = 15;
  
  @SystemApi
  public static final int METADATA_UNTETHERED_CASE_ICON = 9;
  
  @SystemApi
  public static final int METADATA_UNTETHERED_LEFT_BATTERY = 10;
  
  @SystemApi
  public static final int METADATA_UNTETHERED_LEFT_CHARGING = 13;
  
  @SystemApi
  public static final int METADATA_UNTETHERED_LEFT_ICON = 7;
  
  @SystemApi
  public static final int METADATA_UNTETHERED_RIGHT_BATTERY = 11;
  
  @SystemApi
  public static final int METADATA_UNTETHERED_RIGHT_CHARGING = 14;
  
  @SystemApi
  public static final int METADATA_UNTETHERED_RIGHT_ICON = 8;
  
  public static final int PAIRING_VARIANT_CONSENT = 3;
  
  public static final int PAIRING_VARIANT_DISPLAY_PASSKEY = 4;
  
  public static final int PAIRING_VARIANT_DISPLAY_PIN = 5;
  
  public static final int PAIRING_VARIANT_OOB_CONSENT = 6;
  
  public static final int PAIRING_VARIANT_PASSKEY = 1;
  
  public static final int PAIRING_VARIANT_PASSKEY_CONFIRMATION = 2;
  
  public static final int PAIRING_VARIANT_PIN = 0;
  
  public static final int PAIRING_VARIANT_PIN_16_DIGITS = 7;
  
  public static final int PHY_LE_1M = 1;
  
  public static final int PHY_LE_1M_MASK = 1;
  
  public static final int PHY_LE_2M = 2;
  
  public static final int PHY_LE_2M_MASK = 2;
  
  public static final int PHY_LE_CODED = 3;
  
  public static final int PHY_LE_CODED_MASK = 4;
  
  public static final int PHY_OPTION_NO_PREFERRED = 0;
  
  public static final int PHY_OPTION_S2 = 1;
  
  public static final int PHY_OPTION_S8 = 2;
  
  public static final int REQUEST_TYPE_MESSAGE_ACCESS = 3;
  
  public static final int REQUEST_TYPE_PHONEBOOK_ACCESS = 2;
  
  public static final int REQUEST_TYPE_PROFILE_CONNECTION = 1;
  
  public static final int REQUEST_TYPE_SIM_ACCESS = 4;
  
  private static final String TAG = "BluetoothDevice";
  
  public static final int TRANSPORT_AUTO = 0;
  
  public static final int TRANSPORT_BREDR = 1;
  
  public static final int TRANSPORT_LE = 2;
  
  public static final int UNBOND_REASON_AUTH_CANCELED = 3;
  
  public static final int UNBOND_REASON_AUTH_FAILED = 1;
  
  public static final int UNBOND_REASON_AUTH_REJECTED = 2;
  
  public static final int UNBOND_REASON_AUTH_TIMEOUT = 6;
  
  public static final int UNBOND_REASON_DISCOVERY_IN_PROGRESS = 5;
  
  public static final int UNBOND_REASON_REMOTE_AUTH_CANCELED = 8;
  
  public static final int UNBOND_REASON_REMOTE_DEVICE_DOWN = 4;
  
  public static final int UNBOND_REASON_REMOVED = 9;
  
  public static final int UNBOND_REASON_REPEATED_ATTEMPTS = 7;
  
  private static volatile IBluetooth sService;
  
  static IBluetoothManagerCallback sStateChangeCallback = new IBluetoothManagerCallback.Stub() {
      public void onBluetoothServiceDown() throws RemoteException {
        // Byte code:
        //   0: ldc android/bluetooth/BluetoothDevice
        //   2: monitorenter
        //   3: aconst_null
        //   4: invokestatic access$002 : (Landroid/bluetooth/IBluetooth;)Landroid/bluetooth/IBluetooth;
        //   7: pop
        //   8: ldc android/bluetooth/BluetoothDevice
        //   10: monitorexit
        //   11: return
        //   12: astore_1
        //   13: ldc android/bluetooth/BluetoothDevice
        //   15: monitorexit
        //   16: aload_1
        //   17: athrow
        // Exception table:
        //   from	to	target	type
        //   3	11	12	finally
        //   13	16	12	finally
      }
      
      public void onBluetoothServiceUp(IBluetooth param1IBluetooth) throws RemoteException {
        // Byte code:
        //   0: ldc android/bluetooth/BluetoothDevice
        //   2: monitorenter
        //   3: invokestatic access$000 : ()Landroid/bluetooth/IBluetooth;
        //   6: ifnonnull -> 14
        //   9: aload_1
        //   10: invokestatic access$002 : (Landroid/bluetooth/IBluetooth;)Landroid/bluetooth/IBluetooth;
        //   13: pop
        //   14: ldc android/bluetooth/BluetoothDevice
        //   16: monitorexit
        //   17: return
        //   18: astore_1
        //   19: ldc android/bluetooth/BluetoothDevice
        //   21: monitorexit
        //   22: aload_1
        //   23: athrow
        // Exception table:
        //   from	to	target	type
        //   3	14	18	finally
        //   14	17	18	finally
        //   19	22	18	finally
      }
      
      public void onBrEdrDown() {}
    };
  
  private final String mAddress;
  
  private final PropertyInvalidatedCache<BluetoothDevice, Integer> mBluetoothBondCache = new PropertyInvalidatedCache<BluetoothDevice, Integer>(8, "cache_key.bluetooth.get_bond_state") {
      protected Integer recompute(BluetoothDevice param1BluetoothDevice) {
        try {
          int i = BluetoothDevice.sService.getBondState(param1BluetoothDevice);
          return Integer.valueOf(i);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowAsRuntimeException();
        } 
      }
    };
  
  static {
    CREATOR = new Parcelable.Creator<BluetoothDevice>() {
        public BluetoothDevice createFromParcel(Parcel param1Parcel) {
          return new BluetoothDevice(param1Parcel.readString());
        }
        
        public BluetoothDevice[] newArray(int param1Int) {
          return new BluetoothDevice[param1Int];
        }
      };
  }
  
  BluetoothDevice(String paramString) {
    getService();
    if (BluetoothAdapter.checkBluetoothAddress(paramString)) {
      this.mAddress = paramString;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(" is not a valid Bluetooth address");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static byte[] convertPinToBytes(String paramString) {
    if (paramString == null)
      return null; 
    try {
      byte[] arrayOfByte = paramString.getBytes("UTF-8");
      return (arrayOfByte.length <= 0 || arrayOfByte.length > 16) ? null : arrayOfByte;
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      Log.e("BluetoothDevice", "UTF-8 not supported?!?");
      return null;
    } 
  }
  
  static IBluetooth getService() {
    // Byte code:
    //   0: ldc android/bluetooth/BluetoothDevice
    //   2: monitorenter
    //   3: getstatic android/bluetooth/BluetoothDevice.sService : Landroid/bluetooth/IBluetooth;
    //   6: ifnonnull -> 21
    //   9: invokestatic getDefaultAdapter : ()Landroid/bluetooth/BluetoothAdapter;
    //   12: getstatic android/bluetooth/BluetoothDevice.sStateChangeCallback : Landroid/bluetooth/IBluetoothManagerCallback;
    //   15: invokevirtual getBluetoothService : (Landroid/bluetooth/IBluetoothManagerCallback;)Landroid/bluetooth/IBluetooth;
    //   18: putstatic android/bluetooth/BluetoothDevice.sService : Landroid/bluetooth/IBluetooth;
    //   21: ldc android/bluetooth/BluetoothDevice
    //   23: monitorexit
    //   24: getstatic android/bluetooth/BluetoothDevice.sService : Landroid/bluetooth/IBluetooth;
    //   27: areturn
    //   28: astore_0
    //   29: ldc android/bluetooth/BluetoothDevice
    //   31: monitorexit
    //   32: aload_0
    //   33: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	28	finally
    //   21	24	28	finally
    //   29	32	28	finally
  }
  
  public static void invalidateBluetoothGetBondStateCache() {
    PropertyInvalidatedCache.invalidateCache("cache_key.bluetooth.get_bond_state");
  }
  
  @SystemApi
  public boolean cancelBondProcess() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot cancel Remote Device bond");
      return false;
    } 
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("cancelBondProcess() for device ");
      stringBuilder.append(getAddress());
      stringBuilder.append(" called by pid: ");
      stringBuilder.append(Process.myPid());
      stringBuilder.append(" tid: ");
      stringBuilder.append(Process.myTid());
      Log.i("BluetoothDevice", stringBuilder.toString());
      return iBluetooth.cancelBondProcess(this);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean cancelPairing() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot cancel pairing");
      return false;
    } 
    try {
      return iBluetooth.cancelBondProcess(this);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public BluetoothGatt connectGatt(Context paramContext, boolean paramBoolean, BluetoothGattCallback paramBluetoothGattCallback) {
    return connectGatt(paramContext, paramBoolean, paramBluetoothGattCallback, 0);
  }
  
  public BluetoothGatt connectGatt(Context paramContext, boolean paramBoolean, BluetoothGattCallback paramBluetoothGattCallback, int paramInt) {
    return connectGatt(paramContext, paramBoolean, paramBluetoothGattCallback, paramInt, 1);
  }
  
  public BluetoothGatt connectGatt(Context paramContext, boolean paramBoolean, BluetoothGattCallback paramBluetoothGattCallback, int paramInt1, int paramInt2) {
    return connectGatt(paramContext, paramBoolean, paramBluetoothGattCallback, paramInt1, paramInt2, null);
  }
  
  public BluetoothGatt connectGatt(Context paramContext, boolean paramBoolean, BluetoothGattCallback paramBluetoothGattCallback, int paramInt1, int paramInt2, Handler paramHandler) {
    return connectGatt(paramContext, paramBoolean, paramBluetoothGattCallback, paramInt1, false, paramInt2, paramHandler);
  }
  
  public BluetoothGatt connectGatt(Context paramContext, boolean paramBoolean1, BluetoothGattCallback paramBluetoothGattCallback, int paramInt1, boolean paramBoolean2, int paramInt2, Handler paramHandler) {
    if (paramBluetoothGattCallback != null) {
      IBluetoothManager iBluetoothManager = BluetoothAdapter.getDefaultAdapter().getBluetoothManager();
      try {
        IBluetoothGatt iBluetoothGatt = iBluetoothManager.getBluetoothGatt();
        if (iBluetoothGatt == null)
          return null; 
        BluetoothGatt bluetoothGatt = new BluetoothGatt();
        this(iBluetoothGatt, this, paramInt1, paramBoolean2, paramInt2);
        try {
          bluetoothGatt.connect(Boolean.valueOf(paramBoolean1), paramBluetoothGattCallback, paramHandler);
          return bluetoothGatt;
        } catch (RemoteException null) {}
      } catch (RemoteException remoteException) {}
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return null;
    } 
    throw new NullPointerException("callback is null");
  }
  
  public boolean createBond() {
    return createBond(0);
  }
  
  public boolean createBond(int paramInt) {
    return createBondOutOfBand(paramInt, null);
  }
  
  public boolean createBondOutOfBand(int paramInt, OobData paramOobData) {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.w("BluetoothDevice", "BT not enabled, createBondOutOfBand failed");
      return false;
    } 
    try {
      return iBluetooth.createBond(this, paramInt, paramOobData);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public BluetoothSocket createInsecureL2capChannel(int paramInt) throws IOException {
    if (isBluetoothEnabled())
      return new BluetoothSocket(4, -1, false, false, this, paramInt, null); 
    Log.e("BluetoothDevice", "createInsecureL2capChannel: Bluetooth is not enabled");
    throw new IOException();
  }
  
  public BluetoothSocket createInsecureL2capSocket(int paramInt) throws IOException {
    return new BluetoothSocket(3, -1, false, false, this, paramInt, null);
  }
  
  public BluetoothSocket createInsecureRfcommSocket(int paramInt) throws IOException {
    if (isBluetoothEnabled())
      return new BluetoothSocket(1, -1, false, false, this, paramInt, null); 
    Log.e("BluetoothDevice", "Bluetooth is not enabled");
    throw new IOException();
  }
  
  public BluetoothSocket createInsecureRfcommSocketToServiceRecord(UUID paramUUID) throws IOException {
    if (isBluetoothEnabled())
      return new BluetoothSocket(1, -1, false, false, this, -1, new ParcelUuid(paramUUID)); 
    Log.e("BluetoothDevice", "Bluetooth is not enabled");
    throw new IOException();
  }
  
  public BluetoothSocket createL2capChannel(int paramInt) throws IOException {
    if (isBluetoothEnabled())
      return new BluetoothSocket(4, -1, true, true, this, paramInt, null); 
    Log.e("BluetoothDevice", "createL2capChannel: Bluetooth is not enabled");
    throw new IOException();
  }
  
  public BluetoothSocket createL2capSocket(int paramInt) throws IOException {
    return new BluetoothSocket(3, -1, true, true, this, paramInt, null);
  }
  
  public BluetoothSocket createRfcommSocket(int paramInt) throws IOException {
    if (isBluetoothEnabled())
      return new BluetoothSocket(1, -1, true, true, this, paramInt, null); 
    Log.e("BluetoothDevice", "Bluetooth is not enabled");
    throw new IOException();
  }
  
  public BluetoothSocket createRfcommSocketToServiceRecord(UUID paramUUID) throws IOException {
    if (isBluetoothEnabled())
      return new BluetoothSocket(1, -1, true, true, this, -1, new ParcelUuid(paramUUID)); 
    Log.e("BluetoothDevice", "Bluetooth is not enabled");
    throw new IOException();
  }
  
  public BluetoothSocket createScoSocket() throws IOException {
    if (isBluetoothEnabled())
      return new BluetoothSocket(2, -1, true, true, this, -1, null); 
    Log.e("BluetoothDevice", "Bluetooth is not enabled");
    throw new IOException();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void disableBluetoothGetBondStateCache() {
    this.mBluetoothBondCache.disableLocal();
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof BluetoothDevice) ? this.mAddress.equals(((BluetoothDevice)paramObject).getAddress()) : false;
  }
  
  public boolean fetchUuidsWithSdp() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null || !isBluetoothEnabled()) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot fetchUuidsWithSdp");
      return false;
    } 
    try {
      return iBluetooth.fetchRemoteUuids(this);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public String getAddress() {
    return this.mAddress;
  }
  
  public String getAlias() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot get Remote Device Alias");
      return null;
    } 
    try {
      String str = iBluetooth.getRemoteAlias(this);
      return (str == null) ? getName() : str;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return null;
    } 
  }
  
  public int getBatteryLevel() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "Bluetooth disabled. Cannot get remote device battery level");
      return -100;
    } 
    try {
      return iBluetooth.getBatteryLevel(this);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return -1;
    } 
  }
  
  public BluetoothClass getBluetoothClass() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot get Bluetooth Class");
      return null;
    } 
    try {
      int i = iBluetooth.getRemoteClass(this);
      return (i == -16777216) ? null : new BluetoothClass(i);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return null;
    } 
  }
  
  public int getBondState() {
    if (sService == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot get bond state");
      return 10;
    } 
    try {
      return ((Integer)this.mBluetoothBondCache.query(this)).intValue();
    } catch (RuntimeException runtimeException) {
      if (runtimeException.getCause() instanceof RemoteException) {
        Log.e("BluetoothDevice", "", runtimeException);
        return 10;
      } 
      throw runtimeException;
    } 
  }
  
  public int getMessageAccessPermission() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null)
      return 0; 
    try {
      return iBluetooth.getMessageAccessPermission(this);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return 0;
    } 
  }
  
  @SystemApi
  public byte[] getMetadata(int paramInt) {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "Bluetooth is not enabled. Cannot get metadata");
      return null;
    } 
    try {
      return iBluetooth.getMetadata(this, paramInt);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "getMetadata fail", (Throwable)remoteException);
      return null;
    } 
  }
  
  public String getName() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot get Remote Device name");
      return null;
    } 
    try {
      String str = iBluetooth.getRemoteName(this);
      return (str != null) ? str.replaceAll("[\\t\\n\\r]+", " ") : null;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return null;
    } 
  }
  
  public int getPhonebookAccessPermission() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null)
      return 0; 
    try {
      return iBluetooth.getPhonebookAccessPermission(this);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return 0;
    } 
  }
  
  @SystemApi
  public int getSimAccessPermission() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null)
      return 0; 
    try {
      return iBluetooth.getSimAccessPermission(this);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return 0;
    } 
  }
  
  public int getType() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot get Remote Device type");
      return 0;
    } 
    try {
      return iBluetooth.getRemoteType(this);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return 0;
    } 
  }
  
  public ParcelUuid[] getUuids() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null || !isBluetoothEnabled()) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot get remote device Uuids");
      return null;
    } 
    try {
      return iBluetooth.getRemoteUuids(this);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return null;
    } 
  }
  
  public int hashCode() {
    return this.mAddress.hashCode();
  }
  
  boolean isBluetoothEnabled() {
    boolean bool1 = false;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    boolean bool2 = bool1;
    if (bluetoothAdapter != null) {
      bool2 = bool1;
      if (bluetoothAdapter.isEnabled())
        bool2 = true; 
    } 
    return bool2;
  }
  
  public boolean isBondingInitiatedLocally() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.w("BluetoothDevice", "BT not enabled, isBondingInitiatedLocally failed");
      return false;
    } 
    try {
      return iBluetooth.isBondingInitiatedLocally(this);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  @SystemApi
  public boolean isConnected() {
    IBluetooth iBluetooth = sService;
    boolean bool = false;
    if (iBluetooth == null)
      return false; 
    try {
      int i = iBluetooth.getConnectionState(this);
      if (i != 0)
        bool = true; 
      return bool;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  @SystemApi
  public boolean isEncrypted() {
    IBluetooth iBluetooth = sService;
    boolean bool = false;
    if (iBluetooth == null)
      return false; 
    try {
      int i = iBluetooth.getConnectionState(this);
      if (i > 1)
        bool = true; 
      return bool;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  @SystemApi
  public boolean isInSilenceMode() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth != null)
      try {
        return iBluetooth.getSilenceMode(this);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothDevice", "isInSilenceMode fail", (Throwable)remoteException);
        return false;
      }  
    throw new IllegalStateException("Bluetooth is not turned ON");
  }
  
  @SystemApi
  public boolean removeBond() {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot remove Remote Device bond");
      return false;
    } 
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("removeBond() for device ");
      stringBuilder.append(getAddress());
      stringBuilder.append(" called by pid: ");
      stringBuilder.append(Process.myPid());
      stringBuilder.append(" tid: ");
      stringBuilder.append(Process.myTid());
      Log.i("BluetoothDevice", stringBuilder.toString());
      return iBluetooth.removeBond(this);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean sdpSearch(ParcelUuid paramParcelUuid) {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot query remote device sdp records");
      return false;
    } 
    try {
      return iBluetooth.sdpSearch(this, paramParcelUuid);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean setAlias(String paramString) {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot set Remote Device name");
      return false;
    } 
    try {
      return iBluetooth.setRemoteAlias(this, paramString);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean setDeviceOutOfBandData(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    return false;
  }
  
  @SystemApi
  public boolean setMessageAccessPermission(int paramInt) {
    if (paramInt == 1 || paramInt == 2 || paramInt == 0) {
      IBluetooth iBluetooth = sService;
      if (iBluetooth == null)
        return false; 
      try {
        return iBluetooth.setMessageAccessPermission(this, paramInt);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothDevice", "", (Throwable)remoteException);
        return false;
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramInt);
    stringBuilder.append("is not a valid AccessPermission value");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  @SystemApi
  public boolean setMetadata(int paramInt, byte[] paramArrayOfbyte) {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "Bluetooth is not enabled. Cannot set metadata");
      return false;
    } 
    if (paramArrayOfbyte.length <= 2048)
      try {
        return iBluetooth.setMetadata(this, paramInt, paramArrayOfbyte);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothDevice", "setMetadata fail", (Throwable)remoteException);
        return false;
      }  
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("value length is ");
    stringBuilder.append(remoteException.length);
    stringBuilder.append(", should not over ");
    stringBuilder.append(2048);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean setPairingConfirmation(boolean paramBoolean) {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot set pairing confirmation");
      return false;
    } 
    try {
      return iBluetooth.setPairingConfirmation(this, paramBoolean);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  @SystemApi
  public boolean setPhonebookAccessPermission(int paramInt) {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null)
      return false; 
    try {
      return iBluetooth.setPhonebookAccessPermission(this, paramInt);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean setPin(String paramString) {
    byte[] arrayOfByte = convertPinToBytes(paramString);
    return (arrayOfByte == null) ? false : setPin(arrayOfByte);
  }
  
  public boolean setPin(byte[] paramArrayOfbyte) {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null) {
      Log.e("BluetoothDevice", "BT not enabled. Cannot set Remote Device pin");
      return false;
    } 
    try {
      return iBluetooth.setPin(this, true, paramArrayOfbyte.length, paramArrayOfbyte);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  @SystemApi
  public boolean setSilenceMode(boolean paramBoolean) {
    IBluetooth iBluetooth = sService;
    if (iBluetooth != null)
      try {
        return iBluetooth.setSilenceMode(this, paramBoolean);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothDevice", "setSilenceMode fail", (Throwable)remoteException);
        return false;
      }  
    throw new IllegalStateException("Bluetooth is not turned ON");
  }
  
  @SystemApi
  public boolean setSimAccessPermission(int paramInt) {
    IBluetooth iBluetooth = sService;
    if (iBluetooth == null)
      return false; 
    try {
      return iBluetooth.setSimAccessPermission(this, paramInt);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothDevice", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public String toString() {
    return this.mAddress;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mAddress);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AccessPermission {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */