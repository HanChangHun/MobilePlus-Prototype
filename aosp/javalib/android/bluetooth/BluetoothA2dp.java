package android.bluetooth;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public final class BluetoothA2dp implements BluetoothProfile {
  public static final String ACTION_ACTIVE_DEVICE_CHANGED = "android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED";
  
  public static final String ACTION_AVRCP_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.AVRCP_CONNECTION_STATE_CHANGED";
  
  public static final String ACTION_CODEC_CONFIG_CHANGED = "android.bluetooth.a2dp.profile.action.CODEC_CONFIG_CHANGED";
  
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED";
  
  public static final String ACTION_PLAYING_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED";
  
  private static final boolean DBG = true;
  
  @SystemApi
  public static final int OPTIONAL_CODECS_NOT_SUPPORTED = 0;
  
  @SystemApi
  public static final int OPTIONAL_CODECS_PREF_DISABLED = 0;
  
  @SystemApi
  public static final int OPTIONAL_CODECS_PREF_ENABLED = 1;
  
  @SystemApi
  public static final int OPTIONAL_CODECS_PREF_UNKNOWN = -1;
  
  @SystemApi
  public static final int OPTIONAL_CODECS_SUPPORTED = 1;
  
  @SystemApi
  public static final int OPTIONAL_CODECS_SUPPORT_UNKNOWN = -1;
  
  public static final int STATE_NOT_PLAYING = 11;
  
  public static final int STATE_PLAYING = 10;
  
  private static final String TAG = "BluetoothA2dp";
  
  private static final boolean VDBG = false;
  
  private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
  
  private final BluetoothProfileConnector<IBluetoothA2dp> mProfileConnector = new BluetoothProfileConnector<IBluetoothA2dp>(this, 2, "BluetoothA2dp", IBluetoothA2dp.class.getName()) {
      public IBluetoothA2dp getServiceInterface(IBinder param1IBinder) {
        return IBluetoothA2dp.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothA2dp(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mProfileConnector.connect(paramContext, paramServiceListener);
  }
  
  private void enableDisableOptionalCodecs(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) {
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled())
        if (paramBoolean) {
          iBluetoothA2dp.enableOptionalCodecs(paramBluetoothDevice);
        } else {
          iBluetoothA2dp.disableOptionalCodecs(paramBluetoothDevice);
        }  
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothA2dp", "Error talking to BT service in enableDisableOptionalCodecs()", (Throwable)remoteException);
      return;
    } 
  }
  
  private IBluetoothA2dp getService() {
    return this.mProfileConnector.getService();
  }
  
  private boolean isEnabled() {
    return (this.mAdapter.getState() == 12);
  }
  
  private boolean isValidDevice(BluetoothDevice paramBluetoothDevice) {
    return (paramBluetoothDevice == null) ? false : (BluetoothAdapter.checkBluetoothAddress(paramBluetoothDevice.getAddress()));
  }
  
  private static void log(String paramString) {
    Log.d("BluetoothA2dp", paramString);
  }
  
  public static String stateToString(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt != 10) {
              if (paramInt != 11) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<unknown state ");
                stringBuilder.append(paramInt);
                stringBuilder.append(">");
                return stringBuilder.toString();
              } 
              return "not playing";
            } 
            return "playing";
          } 
          return "disconnecting";
        } 
        return "connected";
      } 
      return "connecting";
    } 
    return "disconnected";
  }
  
  private void verifyDeviceNotNull(BluetoothDevice paramBluetoothDevice, String paramString) {
    if (paramBluetoothDevice != null)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(": device param is null");
    Log.e("BluetoothA2dp", stringBuilder.toString());
    throw new IllegalArgumentException("Device cannot be null");
  }
  
  void close() {
    this.mProfileConnector.disconnect();
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("connect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return iBluetoothA2dp.connect(paramBluetoothDevice); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return false;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Stack:");
      stringBuilder1.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothA2dp", stringBuilder1.toString());
      return false;
    } 
  }
  
  public void disableOptionalCodecs(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("disableOptionalCodecs(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    Log.d("BluetoothA2dp", stringBuilder.toString());
    verifyDeviceNotNull(paramBluetoothDevice, "disableOptionalCodecs");
    enableDisableOptionalCodecs(paramBluetoothDevice, false);
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("disconnect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return iBluetoothA2dp.disconnect(paramBluetoothDevice); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return false;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Stack:");
      stringBuilder1.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothA2dp", stringBuilder1.toString());
      return false;
    } 
  }
  
  public void enableOptionalCodecs(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("enableOptionalCodecs(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    Log.d("BluetoothA2dp", stringBuilder.toString());
    verifyDeviceNotNull(paramBluetoothDevice, "enableOptionalCodecs");
    enableDisableOptionalCodecs(paramBluetoothDevice, true);
  }
  
  public void finalize() {}
  
  public BluetoothDevice getActiveDevice() {
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled())
        return iBluetoothA2dp.getActiveDevice(); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return null;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Stack:");
      stringBuilder.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothA2dp", stringBuilder.toString());
      return null;
    } 
  }
  
  public BluetoothCodecStatus getCodecStatus(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("getCodecStatus(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    Log.d("BluetoothA2dp", stringBuilder.toString());
    verifyDeviceNotNull(paramBluetoothDevice, "getCodecStatus");
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled())
        return iBluetoothA2dp.getCodecStatus(paramBluetoothDevice); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return null;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothA2dp", "Error talking to BT service in getCodecStatus()", (Throwable)remoteException);
      return null;
    } 
  }
  
  public List<BluetoothDevice> getConnectedDevices() {
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled())
        return iBluetoothA2dp.getConnectedDevices(); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return new ArrayList();
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Stack:");
      stringBuilder.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothA2dp", stringBuilder.toString());
      return new ArrayList<>();
    } 
  }
  
  @SystemApi
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) {
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return iBluetoothA2dp.getConnectionPolicy(paramBluetoothDevice); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return 0;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Stack:");
      stringBuilder.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothA2dp", stringBuilder.toString());
      return 0;
    } 
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return iBluetoothA2dp.getConnectionState(paramBluetoothDevice); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return 0;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Stack:");
      stringBuilder.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothA2dp", stringBuilder.toString());
      return 0;
    } 
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled())
        return iBluetoothA2dp.getDevicesMatchingConnectionStates(paramArrayOfint); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return new ArrayList();
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Stack:");
      stringBuilder.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothA2dp", stringBuilder.toString());
      return new ArrayList<>();
    } 
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) {
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return BluetoothAdapter.connectionPolicyToPriority(iBluetoothA2dp.getPriority(paramBluetoothDevice)); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return 0;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Stack:");
      stringBuilder.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothA2dp", stringBuilder.toString());
      return 0;
    } 
  }
  
  public boolean isA2dpPlaying(BluetoothDevice paramBluetoothDevice) {
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return iBluetoothA2dp.isA2dpPlaying(paramBluetoothDevice); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return false;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Stack:");
      stringBuilder.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothA2dp", stringBuilder.toString());
      return false;
    } 
  }
  
  public boolean isAvrcpAbsoluteVolumeSupported() {
    Log.d("BluetoothA2dp", "isAvrcpAbsoluteVolumeSupported");
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled())
        return iBluetoothA2dp.isAvrcpAbsoluteVolumeSupported(); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return false;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothA2dp", "Error talking to BT service in isAvrcpAbsoluteVolumeSupported()", (Throwable)remoteException);
      return false;
    } 
  }
  
  public int isOptionalCodecsEnabled(BluetoothDevice paramBluetoothDevice) {
    verifyDeviceNotNull(paramBluetoothDevice, "isOptionalCodecsEnabled");
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return iBluetoothA2dp.getOptionalCodecsEnabled(paramBluetoothDevice); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return -1;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothA2dp", "Error talking to BT service in getOptionalCodecsEnabled()", (Throwable)remoteException);
      return -1;
    } 
  }
  
  public int isOptionalCodecsSupported(BluetoothDevice paramBluetoothDevice) {
    verifyDeviceNotNull(paramBluetoothDevice, "isOptionalCodecsSupported");
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return iBluetoothA2dp.supportsOptionalCodecs(paramBluetoothDevice); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return -1;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothA2dp", "Error talking to BT service in supportsOptionalCodecs()", (Throwable)remoteException);
      return -1;
    } 
  }
  
  public boolean setActiveDevice(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setActiveDevice(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled() && (paramBluetoothDevice == null || isValidDevice(paramBluetoothDevice)))
        return iBluetoothA2dp.setActiveDevice(paramBluetoothDevice); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return false;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Stack:");
      stringBuilder1.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothA2dp", stringBuilder1.toString());
      return false;
    } 
  }
  
  public void setAvrcpAbsoluteVolume(int paramInt) {
    Log.d("BluetoothA2dp", "setAvrcpAbsoluteVolume");
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled())
        iBluetoothA2dp.setAvrcpAbsoluteVolume(paramInt); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothA2dp", "Error talking to BT service in setAvrcpAbsoluteVolume()", (Throwable)remoteException);
    } 
  }
  
  public void setCodecConfigPreference(BluetoothDevice paramBluetoothDevice, BluetoothCodecConfig paramBluetoothCodecConfig) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setCodecConfigPreference(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    Log.d("BluetoothA2dp", stringBuilder.toString());
    verifyDeviceNotNull(paramBluetoothDevice, "setCodecConfigPreference");
    if (paramBluetoothCodecConfig != null)
      try {
        IBluetoothA2dp iBluetoothA2dp = getService();
        if (iBluetoothA2dp != null && isEnabled())
          iBluetoothA2dp.setCodecConfigPreference(paramBluetoothDevice, paramBluetoothCodecConfig); 
        if (iBluetoothA2dp == null)
          Log.w("BluetoothA2dp", "Proxy not attached to service"); 
        return;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothA2dp", "Error talking to BT service in setCodecConfigPreference()", (Throwable)remoteException);
        return;
      }  
    Log.e("BluetoothA2dp", "setCodecConfigPreference: Codec config can't be null");
    throw new IllegalArgumentException("codecConfig cannot be null");
  }
  
  @SystemApi
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setConnectionPolicy(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(", ");
    stringBuilder.append(paramInt);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    try {
      IBluetoothA2dp iBluetoothA2dp = getService();
      if (iBluetoothA2dp != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return (paramInt != 0 && paramInt != 100) ? false : iBluetoothA2dp.setConnectionPolicy(paramBluetoothDevice, paramInt); 
      if (iBluetoothA2dp == null)
        Log.w("BluetoothA2dp", "Proxy not attached to service"); 
      return false;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Stack:");
      stringBuilder1.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothA2dp", stringBuilder1.toString());
      return false;
    } 
  }
  
  public void setOptionalCodecsEnabled(BluetoothDevice paramBluetoothDevice, int paramInt) {
    StringBuilder stringBuilder;
    verifyDeviceNotNull(paramBluetoothDevice, "setOptionalCodecsEnabled");
    if (paramInt != -1 && paramInt != 0 && paramInt != 1)
      try {
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Invalid value passed to setOptionalCodecsEnabled: ");
        stringBuilder.append(paramInt);
        Log.e("BluetoothA2dp", stringBuilder.toString());
        return;
      } catch (RemoteException remoteException) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothA2dp", stringBuilder.toString());
        return;
      }  
    IBluetoothA2dp iBluetoothA2dp = getService();
    if (iBluetoothA2dp != null && isEnabled() && isValidDevice((BluetoothDevice)stringBuilder))
      iBluetoothA2dp.setOptionalCodecsEnabled((BluetoothDevice)stringBuilder, paramInt); 
    if (iBluetoothA2dp == null)
      Log.w("BluetoothA2dp", "Proxy not attached to service"); 
  }
  
  public boolean setPriority(BluetoothDevice paramBluetoothDevice, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setPriority(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(", ");
    stringBuilder.append(paramInt);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    return setConnectionPolicy(paramBluetoothDevice, BluetoothAdapter.priorityToConnectionPolicy(paramInt));
  }
  
  public boolean shouldSendVolumeKeys(BluetoothDevice paramBluetoothDevice) {
    if (isEnabled() && isValidDevice(paramBluetoothDevice)) {
      ParcelUuid[] arrayOfParcelUuid = paramBluetoothDevice.getUuids();
      if (arrayOfParcelUuid == null)
        return false; 
      int i = arrayOfParcelUuid.length;
      for (byte b = 0; b < i; b++) {
        if (arrayOfParcelUuid[b].equals(BluetoothUuid.AVRCP_TARGET))
          return true; 
      } 
    } 
    return false;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface OptionalCodecsPreferenceStatus {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface OptionalCodecsSupportStatus {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothA2dp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */