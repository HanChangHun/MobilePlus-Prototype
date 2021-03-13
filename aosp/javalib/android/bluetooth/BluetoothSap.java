package android.bluetooth;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class BluetoothSap implements BluetoothProfile {
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.sap.profile.action.CONNECTION_STATE_CHANGED";
  
  private static final boolean DBG = true;
  
  public static final int RESULT_CANCELED = 2;
  
  public static final int RESULT_SUCCESS = 1;
  
  public static final int STATE_ERROR = -1;
  
  private static final String TAG = "BluetoothSap";
  
  private static final boolean VDBG = false;
  
  private BluetoothAdapter mAdapter;
  
  private final BluetoothProfileConnector<IBluetoothSap> mProfileConnector = new BluetoothProfileConnector<IBluetoothSap>(this, 10, "BluetoothSap", IBluetoothSap.class.getName()) {
      public IBluetoothSap getServiceInterface(IBinder param1IBinder) {
        return IBluetoothSap.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothSap(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    Log.d("BluetoothSap", "Create BluetoothSap proxy object");
    this.mAdapter = BluetoothAdapter.getDefaultAdapter();
    this.mProfileConnector.connect(paramContext, paramServiceListener);
  }
  
  private IBluetoothSap getService() {
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
    Log.d("BluetoothSap", paramString);
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
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("connect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")not supported for SAPS");
    log(stringBuilder.toString());
    return false;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("disconnect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothSap iBluetoothSap = getService();
    if (iBluetoothSap != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothSap.disconnect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
        return false;
      }  
    if (iBluetoothSap == null)
      Log.w("BluetoothSap", "Proxy not attached to service"); 
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
  
  public BluetoothDevice getClient() {
    IBluetoothSap iBluetoothSap = getService();
    if (iBluetoothSap != null) {
      try {
        return iBluetoothSap.getClient();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothSap", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothSap", "Proxy not attached to service");
      log(Log.getStackTraceString(new Throwable()));
    } 
    return null;
  }
  
  public List<BluetoothDevice> getConnectedDevices() {
    log("getConnectedDevices()");
    IBluetoothSap iBluetoothSap = getService();
    if (iBluetoothSap != null && isEnabled())
      try {
        return iBluetoothSap.getConnectedDevices();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (remoteException == null)
      Log.w("BluetoothSap", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  @SystemApi
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) {
    IBluetoothSap iBluetoothSap = getService();
    if (iBluetoothSap != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothSap.getConnectionPolicy(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothSap == null)
      Log.w("BluetoothSap", "Proxy not attached to service"); 
    return 0;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("getConnectionState(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothSap iBluetoothSap = getService();
    if (iBluetoothSap != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothSap.getConnectionState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothSap == null)
      Log.w("BluetoothSap", "Proxy not attached to service"); 
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    log("getDevicesMatchingStates()");
    IBluetoothSap iBluetoothSap = getService();
    if (iBluetoothSap != null && isEnabled())
      try {
        return iBluetoothSap.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (iBluetoothSap == null)
      Log.w("BluetoothSap", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) {
    return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(paramBluetoothDevice));
  }
  
  public int getState() {
    IBluetoothSap iBluetoothSap = getService();
    if (iBluetoothSap != null) {
      try {
        return iBluetoothSap.getState();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothSap", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothSap", "Proxy not attached to service");
      log(Log.getStackTraceString(new Throwable()));
    } 
    return -1;
  }
  
  public boolean isConnected(BluetoothDevice paramBluetoothDevice) {
    IBluetoothSap iBluetoothSap = getService();
    if (iBluetoothSap != null) {
      try {
        return iBluetoothSap.isConnected(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothSap", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothSap", "Proxy not attached to service");
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
    IBluetoothSap iBluetoothSap = getService();
    if (iBluetoothSap != null && isEnabled() && isValidDevice(paramBluetoothDevice)) {
      if (paramInt != 0 && paramInt != 100)
        return false; 
      try {
        return iBluetoothSap.setConnectionPolicy(paramBluetoothDevice, paramInt);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothSap", Log.getStackTraceString(new Throwable()));
        return false;
      } 
    } 
    if (iBluetoothSap == null)
      Log.w("BluetoothSap", "Proxy not attached to service"); 
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothSap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */