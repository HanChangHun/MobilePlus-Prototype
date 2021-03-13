package android.bluetooth;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class BluetoothAvrcpController implements BluetoothProfile {
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.avrcp-controller.profile.action.CONNECTION_STATE_CHANGED";
  
  public static final String ACTION_PLAYER_SETTING = "android.bluetooth.avrcp-controller.profile.action.PLAYER_SETTING";
  
  private static final boolean DBG = false;
  
  public static final String EXTRA_PLAYER_SETTING = "android.bluetooth.avrcp-controller.profile.extra.PLAYER_SETTING";
  
  private static final String TAG = "BluetoothAvrcpController";
  
  private static final boolean VDBG = false;
  
  private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
  
  private final BluetoothProfileConnector<IBluetoothAvrcpController> mProfileConnector = new BluetoothProfileConnector<IBluetoothAvrcpController>(this, 12, "BluetoothAvrcpController", IBluetoothAvrcpController.class.getName()) {
      public IBluetoothAvrcpController getServiceInterface(IBinder param1IBinder) {
        return IBluetoothAvrcpController.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothAvrcpController(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mProfileConnector.connect(paramContext, paramServiceListener);
  }
  
  private IBluetoothAvrcpController getService() {
    return this.mProfileConnector.getService();
  }
  
  private boolean isEnabled() {
    boolean bool;
    if (this.mAdapter.getState() == 12) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
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
    Log.d("BluetoothAvrcpController", paramString);
  }
  
  void close() {
    this.mProfileConnector.disconnect();
  }
  
  public void finalize() {
    close();
  }
  
  public List<BluetoothDevice> getConnectedDevices() {
    StringBuilder stringBuilder;
    IBluetoothAvrcpController iBluetoothAvrcpController = getService();
    if (iBluetoothAvrcpController != null && isEnabled())
      try {
        return iBluetoothAvrcpController.getConnectedDevices();
      } catch (RemoteException remoteException) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothAvrcpController", stringBuilder.toString());
        return new ArrayList<>();
      }  
    if (stringBuilder == null)
      Log.w("BluetoothAvrcpController", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    IBluetoothAvrcpController iBluetoothAvrcpController = getService();
    if (iBluetoothAvrcpController != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothAvrcpController.getConnectionState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothAvrcpController", stringBuilder.toString());
        return 0;
      }  
    if (iBluetoothAvrcpController == null)
      Log.w("BluetoothAvrcpController", "Proxy not attached to service"); 
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    IBluetoothAvrcpController iBluetoothAvrcpController = getService();
    if (iBluetoothAvrcpController != null && isEnabled())
      try {
        return iBluetoothAvrcpController.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothAvrcpController", stringBuilder.toString());
        return new ArrayList<>();
      }  
    if (iBluetoothAvrcpController == null)
      Log.w("BluetoothAvrcpController", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  public BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice paramBluetoothDevice) {
    BluetoothAvrcpPlayerSettings bluetoothAvrcpPlayerSettings1 = null;
    IBluetoothAvrcpController iBluetoothAvrcpController = getService();
    BluetoothAvrcpPlayerSettings bluetoothAvrcpPlayerSettings2 = bluetoothAvrcpPlayerSettings1;
    if (iBluetoothAvrcpController != null) {
      bluetoothAvrcpPlayerSettings2 = bluetoothAvrcpPlayerSettings1;
      if (isEnabled())
        try {
          bluetoothAvrcpPlayerSettings2 = iBluetoothAvrcpController.getPlayerSettings(paramBluetoothDevice);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Error talking to BT service in getMetadata() ");
          stringBuilder.append(remoteException);
          Log.e("BluetoothAvrcpController", stringBuilder.toString());
          return null;
        }  
    } 
    return (BluetoothAvrcpPlayerSettings)remoteException;
  }
  
  public void sendGroupNavigationCmd(BluetoothDevice paramBluetoothDevice, int paramInt1, int paramInt2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("sendGroupNavigationCmd dev = ");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(" key ");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" State = ");
    stringBuilder.append(paramInt2);
    Log.d("BluetoothAvrcpController", stringBuilder.toString());
    IBluetoothAvrcpController iBluetoothAvrcpController = getService();
    if (iBluetoothAvrcpController != null && isEnabled())
      try {
        iBluetoothAvrcpController.sendGroupNavigationCmd(paramBluetoothDevice, paramInt1, paramInt2);
        return;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothAvrcpController", "Error talking to BT service in sendGroupNavigationCmd()", (Throwable)remoteException);
        return;
      }  
    if (iBluetoothAvrcpController == null)
      Log.w("BluetoothAvrcpController", "Proxy not attached to service"); 
  }
  
  public boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings paramBluetoothAvrcpPlayerSettings) {
    IBluetoothAvrcpController iBluetoothAvrcpController = getService();
    if (iBluetoothAvrcpController != null && isEnabled())
      try {
        return iBluetoothAvrcpController.setPlayerApplicationSetting(paramBluetoothAvrcpPlayerSettings);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error talking to BT service in setPlayerApplicationSetting() ");
        stringBuilder.append(remoteException);
        Log.e("BluetoothAvrcpController", stringBuilder.toString());
        return false;
      }  
    if (remoteException == null)
      Log.w("BluetoothAvrcpController", "Proxy not attached to service"); 
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAvrcpController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */