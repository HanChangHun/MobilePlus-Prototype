package android.bluetooth;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.CloseGuard;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

@SystemApi
public final class BluetoothMap implements BluetoothProfile, AutoCloseable {
  @SystemApi
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.map.profile.action.CONNECTION_STATE_CHANGED";
  
  private static final boolean DBG = true;
  
  public static final int RESULT_CANCELED = 2;
  
  public static final int RESULT_FAILURE = 0;
  
  public static final int RESULT_SUCCESS = 1;
  
  public static final int STATE_ERROR = -1;
  
  private static final String TAG = "BluetoothMap";
  
  private static final boolean VDBG = false;
  
  private BluetoothAdapter mAdapter;
  
  private CloseGuard mCloseGuard;
  
  private final BluetoothProfileConnector<IBluetoothMap> mProfileConnector = new BluetoothProfileConnector<IBluetoothMap>(this, 9, "BluetoothMap", IBluetoothMap.class.getName()) {
      public IBluetoothMap getServiceInterface(IBinder param1IBinder) {
        return IBluetoothMap.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothMap(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    Log.d("BluetoothMap", "Create BluetoothMap proxy object");
    this.mAdapter = BluetoothAdapter.getDefaultAdapter();
    this.mProfileConnector.connect(paramContext, paramServiceListener);
    CloseGuard closeGuard = new CloseGuard();
    this.mCloseGuard = closeGuard;
    closeGuard.open("close");
  }
  
  public static boolean doesClassMatchSink(BluetoothClass paramBluetoothClass) {
    int i = paramBluetoothClass.getDeviceClass();
    return !(i != 256 && i != 260 && i != 264 && i != 268);
  }
  
  private IBluetoothMap getService() {
    return this.mProfileConnector.getService();
  }
  
  private boolean isEnabled() {
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (bluetoothAdapter != null && bluetoothAdapter.getState() == 12)
      return true; 
    log("Bluetooth is Not enabled");
    return false;
  }
  
  private static boolean isValidDevice(BluetoothDevice paramBluetoothDevice) {
    boolean bool;
    if (paramBluetoothDevice != null && BluetoothAdapter.checkBluetoothAddress(paramBluetoothDevice.getAddress())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static void log(String paramString) {
    Log.d("BluetoothMap", paramString);
  }
  
  @SystemApi
  public void close() {
    this.mProfileConnector.disconnect();
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("connect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")not supported for MAPS");
    log(stringBuilder.toString());
    return false;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("disconnect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothMap iBluetoothMap = getService();
    if (iBluetoothMap != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothMap.disconnect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
        return false;
      }  
    if (iBluetoothMap == null)
      Log.w("BluetoothMap", "Proxy not attached to service"); 
    return false;
  }
  
  protected void finalize() {
    CloseGuard closeGuard = this.mCloseGuard;
    if (closeGuard != null)
      closeGuard.warnIfOpen(); 
    close();
  }
  
  public BluetoothDevice getClient() {
    IBluetoothMap iBluetoothMap = getService();
    if (iBluetoothMap != null) {
      try {
        return iBluetoothMap.getClient();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMap", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothMap", "Proxy not attached to service");
      log(Log.getStackTraceString(new Throwable()));
    } 
    return null;
  }
  
  @SystemApi
  public List<BluetoothDevice> getConnectedDevices() {
    log("getConnectedDevices()");
    IBluetoothMap iBluetoothMap = getService();
    if (iBluetoothMap != null && isEnabled())
      try {
        return iBluetoothMap.getConnectedDevices();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (remoteException == null)
      Log.w("BluetoothMap", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  @SystemApi
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) {
    IBluetoothMap iBluetoothMap = getService();
    if (iBluetoothMap != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothMap.getConnectionPolicy(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothMap == null)
      Log.w("BluetoothMap", "Proxy not attached to service"); 
    return 0;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("getConnectionState(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothMap iBluetoothMap = getService();
    if (iBluetoothMap != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothMap.getConnectionState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothMap == null)
      Log.w("BluetoothMap", "Proxy not attached to service"); 
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    log("getDevicesMatchingStates()");
    IBluetoothMap iBluetoothMap = getService();
    if (iBluetoothMap != null && isEnabled())
      try {
        return iBluetoothMap.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (iBluetoothMap == null)
      Log.w("BluetoothMap", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) {
    return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(paramBluetoothDevice));
  }
  
  public int getState() {
    IBluetoothMap iBluetoothMap = getService();
    if (iBluetoothMap != null) {
      try {
        return iBluetoothMap.getState();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMap", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothMap", "Proxy not attached to service");
      log(Log.getStackTraceString(new Throwable()));
    } 
    return -1;
  }
  
  public boolean isConnected(BluetoothDevice paramBluetoothDevice) {
    IBluetoothMap iBluetoothMap = getService();
    if (iBluetoothMap != null) {
      try {
        return iBluetoothMap.isConnected(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMap", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothMap", "Proxy not attached to service");
      log(Log.getStackTraceString(new Throwable()));
    } 
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
    IBluetoothMap iBluetoothMap = getService();
    if (iBluetoothMap != null && isEnabled() && isValidDevice(paramBluetoothDevice)) {
      if (paramInt != 0 && paramInt != 100)
        return false; 
      try {
        return iBluetoothMap.setConnectionPolicy(paramBluetoothDevice, paramInt);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMap", Log.getStackTraceString(new Throwable()));
        return false;
      } 
    } 
    if (iBluetoothMap == null)
      Log.w("BluetoothMap", "Proxy not attached to service"); 
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
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */