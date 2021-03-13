package android.bluetooth;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class BluetoothPbapClient implements BluetoothProfile {
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED";
  
  private static final boolean DBG = false;
  
  public static final int RESULT_CANCELED = 2;
  
  public static final int RESULT_FAILURE = 0;
  
  public static final int RESULT_SUCCESS = 1;
  
  public static final int STATE_ERROR = -1;
  
  private static final String TAG = "BluetoothPbapClient";
  
  private static final boolean VDBG = false;
  
  private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
  
  private final BluetoothProfileConnector<IBluetoothPbapClient> mProfileConnector = new BluetoothProfileConnector<IBluetoothPbapClient>(this, 17, "BluetoothPbapClient", IBluetoothPbapClient.class.getName()) {
      public IBluetoothPbapClient getServiceInterface(IBinder param1IBinder) {
        return IBluetoothPbapClient.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothPbapClient(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mProfileConnector.connect(paramContext, paramServiceListener);
  }
  
  private IBluetoothPbapClient getService() {
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
    Log.d("BluetoothPbapClient", paramString);
  }
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mProfileConnector : Landroid/bluetooth/BluetoothProfileConnector;
    //   6: invokevirtual disconnect : ()V
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	12	finally
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) {
    IBluetoothPbapClient iBluetoothPbapClient = getService();
    if (iBluetoothPbapClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothPbapClient.connect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
        return false;
      }  
    if (iBluetoothPbapClient == null)
      Log.w("BluetoothPbapClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    IBluetoothPbapClient iBluetoothPbapClient = getService();
    if (iBluetoothPbapClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        iBluetoothPbapClient.disconnect(paramBluetoothDevice);
        return true;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
        return false;
      }  
    if (iBluetoothPbapClient == null)
      Log.w("BluetoothPbapClient", "Proxy not attached to service"); 
    return false;
  }
  
  protected void finalize() throws Throwable {
    try {
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public List<BluetoothDevice> getConnectedDevices() {
    IBluetoothPbapClient iBluetoothPbapClient = getService();
    if (iBluetoothPbapClient != null && isEnabled())
      try {
        return iBluetoothPbapClient.getConnectedDevices();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (remoteException == null)
      Log.w("BluetoothPbapClient", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  @SystemApi
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) {
    IBluetoothPbapClient iBluetoothPbapClient = getService();
    if (iBluetoothPbapClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothPbapClient.getConnectionPolicy(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothPbapClient == null)
      Log.w("BluetoothPbapClient", "Proxy not attached to service"); 
    return 0;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    IBluetoothPbapClient iBluetoothPbapClient = getService();
    if (iBluetoothPbapClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothPbapClient.getConnectionState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothPbapClient == null)
      Log.w("BluetoothPbapClient", "Proxy not attached to service"); 
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    IBluetoothPbapClient iBluetoothPbapClient = getService();
    if (iBluetoothPbapClient != null && isEnabled())
      try {
        return iBluetoothPbapClient.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (iBluetoothPbapClient == null)
      Log.w("BluetoothPbapClient", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) {
    return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(paramBluetoothDevice));
  }
  
  @SystemApi
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) {
    IBluetoothPbapClient iBluetoothPbapClient = getService();
    if (iBluetoothPbapClient != null && isEnabled() && isValidDevice(paramBluetoothDevice)) {
      if (paramInt != 0 && paramInt != 100)
        return false; 
      try {
        return iBluetoothPbapClient.setConnectionPolicy(paramBluetoothDevice, paramInt);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothPbapClient", Log.getStackTraceString(new Throwable()));
        return false;
      } 
    } 
    if (iBluetoothPbapClient == null)
      Log.w("BluetoothPbapClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean setPriority(BluetoothDevice paramBluetoothDevice, int paramInt) {
    return setConnectionPolicy(paramBluetoothDevice, BluetoothAdapter.priorityToConnectionPolicy(paramInt));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothPbapClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */