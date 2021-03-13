package android.bluetooth;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class BluetoothHearingAid implements BluetoothProfile {
  public static final String ACTION_ACTIVE_DEVICE_CHANGED = "android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED";
  
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED";
  
  private static final boolean DBG = true;
  
  public static final long HI_SYNC_ID_INVALID = 0L;
  
  public static final int MODE_BINAURAL = 1;
  
  public static final int MODE_MONAURAL = 0;
  
  public static final int SIDE_LEFT = 0;
  
  public static final int SIDE_RIGHT = 1;
  
  private static final String TAG = "BluetoothHearingAid";
  
  private static final boolean VDBG = false;
  
  private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
  
  private final BluetoothProfileConnector<IBluetoothHearingAid> mProfileConnector = new BluetoothProfileConnector<IBluetoothHearingAid>(this, 21, "BluetoothHearingAid", IBluetoothHearingAid.class.getName()) {
      public IBluetoothHearingAid getServiceInterface(IBinder param1IBinder) {
        return IBluetoothHearingAid.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothHearingAid(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mProfileConnector.connect(paramContext, paramServiceListener);
  }
  
  private IBluetoothHearingAid getService() {
    return this.mProfileConnector.getService();
  }
  
  private boolean isEnabled() {
    return (this.mAdapter.getState() == 12);
  }
  
  private boolean isValidDevice(BluetoothDevice paramBluetoothDevice) {
    return (paramBluetoothDevice == null) ? false : (BluetoothAdapter.checkBluetoothAddress(paramBluetoothDevice.getAddress()));
  }
  
  private static void log(String paramString) {
    Log.d("BluetoothHearingAid", paramString);
  }
  
  public static String stateToString(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<unknown state ");
            stringBuilder.append(paramInt);
            stringBuilder.append(">");
            return stringBuilder.toString();
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
    Log.e("BluetoothHearingAid", stringBuilder.toString());
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
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid != null)
      try {
        if (isEnabled() && isValidDevice(paramBluetoothDevice))
          return iBluetoothHearingAid.connect(paramBluetoothDevice); 
      } catch (RemoteException remoteException) {} 
    if (iBluetoothHearingAid == null)
      Log.w("BluetoothHearingAid", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("disconnect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid != null)
      try {
        if (isEnabled() && isValidDevice(paramBluetoothDevice))
          return iBluetoothHearingAid.disconnect(paramBluetoothDevice); 
      } catch (RemoteException remoteException) {} 
    if (iBluetoothHearingAid == null)
      Log.w("BluetoothHearingAid", "Proxy not attached to service"); 
    return false;
  }
  
  public List<BluetoothDevice> getActiveDevices() {
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid != null)
      try {
        if (isEnabled())
          return iBluetoothHearingAid.getActiveDevices(); 
      } catch (RemoteException remoteException) {} 
    if (remoteException == null)
      Log.w("BluetoothHearingAid", "Proxy not attached to service"); 
    return new ArrayList();
  }
  
  public List<BluetoothDevice> getConnectedDevices() {
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid != null)
      try {
        if (isEnabled())
          return iBluetoothHearingAid.getConnectedDevices(); 
      } catch (RemoteException remoteException) {} 
    if (remoteException == null)
      Log.w("BluetoothHearingAid", "Proxy not attached to service"); 
    return new ArrayList();
  }
  
  @SystemApi
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) {
    verifyDeviceNotNull(paramBluetoothDevice, "getConnectionPolicy");
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid != null)
      try {
        if (isEnabled() && isValidDevice(paramBluetoothDevice))
          return iBluetoothHearingAid.getConnectionPolicy(paramBluetoothDevice); 
      } catch (RemoteException remoteException) {} 
    if (iBluetoothHearingAid == null)
      Log.w("BluetoothHearingAid", "Proxy not attached to service"); 
    return 0;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid != null)
      try {
        if (isEnabled() && isValidDevice(paramBluetoothDevice))
          return iBluetoothHearingAid.getConnectionState(paramBluetoothDevice); 
      } catch (RemoteException remoteException) {} 
    if (iBluetoothHearingAid == null)
      Log.w("BluetoothHearingAid", "Proxy not attached to service"); 
    return 0;
  }
  
  public int getDeviceMode(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid != null)
      try {
        if (isEnabled() && isValidDevice(paramBluetoothDevice))
          return iBluetoothHearingAid.getDeviceMode(paramBluetoothDevice); 
      } catch (RemoteException remoteException) {} 
    if (iBluetoothHearingAid == null)
      Log.w("BluetoothHearingAid", "Proxy not attached to service"); 
    return 0;
  }
  
  public int getDeviceSide(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid != null)
      try {
        if (isEnabled() && isValidDevice(paramBluetoothDevice))
          return iBluetoothHearingAid.getDeviceSide(paramBluetoothDevice); 
      } catch (RemoteException remoteException) {} 
    if (iBluetoothHearingAid == null)
      Log.w("BluetoothHearingAid", "Proxy not attached to service"); 
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid != null)
      try {
        if (isEnabled())
          return iBluetoothHearingAid.getDevicesMatchingConnectionStates(paramArrayOfint); 
      } catch (RemoteException remoteException) {} 
    if (iBluetoothHearingAid == null)
      Log.w("BluetoothHearingAid", "Proxy not attached to service"); 
    return new ArrayList();
  }
  
  @SystemApi
  public long getHiSyncId(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder;
    verifyDeviceNotNull(paramBluetoothDevice, "getConnectionPolicy");
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid == null)
      try {
        Log.w("BluetoothHearingAid", "Proxy not attached to service");
        return 0L;
      } catch (RemoteException remoteException) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHearingAid", stringBuilder.toString());
        return 0L;
      }  
    return (!isEnabled() || !isValidDevice((BluetoothDevice)stringBuilder)) ? 0L : iBluetoothHearingAid.getHiSyncId((BluetoothDevice)stringBuilder);
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) {
    return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(paramBluetoothDevice));
  }
  
  public boolean setActiveDevice(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setActiveDevice(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid != null)
      try {
        if (isEnabled() && (paramBluetoothDevice == null || isValidDevice(paramBluetoothDevice))) {
          iBluetoothHearingAid.setActiveDevice(paramBluetoothDevice);
          return true;
        } 
      } catch (RemoteException remoteException) {} 
    if (iBluetoothHearingAid == null)
      Log.w("BluetoothHearingAid", "Proxy not attached to service"); 
    return false;
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
    verifyDeviceNotNull(paramBluetoothDevice, "setConnectionPolicy");
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid != null)
      try {
        if (isEnabled() && isValidDevice(paramBluetoothDevice))
          return (paramInt != 0 && paramInt != 100) ? false : iBluetoothHearingAid.setConnectionPolicy(paramBluetoothDevice, paramInt); 
      } catch (RemoteException remoteException) {} 
    if (iBluetoothHearingAid == null)
      Log.w("BluetoothHearingAid", "Proxy not attached to service"); 
    return false;
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
  
  public void setVolume(int paramInt) {
    StringBuilder stringBuilder1;
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("setVolume(");
    stringBuilder2.append(paramInt);
    stringBuilder2.append(")");
    Log.d("BluetoothHearingAid", stringBuilder2.toString());
    IBluetoothHearingAid iBluetoothHearingAid = getService();
    if (iBluetoothHearingAid == null) {
      try {
        Log.w("BluetoothHearingAid", "Proxy not attached to service");
        return;
      } catch (RemoteException remoteException) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHearingAid", stringBuilder1.toString());
      } 
    } else {
      if (!isEnabled())
        return; 
      stringBuilder1.setVolume(paramInt);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHearingAid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */