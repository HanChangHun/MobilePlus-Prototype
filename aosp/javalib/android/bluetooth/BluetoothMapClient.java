package android.bluetooth;

import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class BluetoothMapClient implements BluetoothProfile {
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.mapmce.profile.action.CONNECTION_STATE_CHANGED";
  
  public static final String ACTION_MESSAGE_DELIVERED_SUCCESSFULLY = "android.bluetooth.mapmce.profile.action.MESSAGE_DELIVERED_SUCCESSFULLY";
  
  public static final String ACTION_MESSAGE_RECEIVED = "android.bluetooth.mapmce.profile.action.MESSAGE_RECEIVED";
  
  public static final String ACTION_MESSAGE_SENT_SUCCESSFULLY = "android.bluetooth.mapmce.profile.action.MESSAGE_SENT_SUCCESSFULLY";
  
  private static final boolean DBG = Log.isLoggable("BluetoothMapClient", 3);
  
  public static final String EXTRA_MESSAGE_HANDLE = "android.bluetooth.mapmce.profile.extra.MESSAGE_HANDLE";
  
  public static final String EXTRA_MESSAGE_READ_STATUS = "android.bluetooth.mapmce.profile.extra.MESSAGE_READ_STATUS";
  
  public static final String EXTRA_MESSAGE_TIMESTAMP = "android.bluetooth.mapmce.profile.extra.MESSAGE_TIMESTAMP";
  
  public static final String EXTRA_SENDER_CONTACT_NAME = "android.bluetooth.mapmce.profile.extra.SENDER_CONTACT_NAME";
  
  public static final String EXTRA_SENDER_CONTACT_URI = "android.bluetooth.mapmce.profile.extra.SENDER_CONTACT_URI";
  
  public static final int RESULT_CANCELED = 2;
  
  public static final int RESULT_FAILURE = 0;
  
  public static final int RESULT_SUCCESS = 1;
  
  public static final int STATE_ERROR = -1;
  
  private static final String TAG = "BluetoothMapClient";
  
  private static final int UPLOADING_FEATURE_BITMASK = 8;
  
  private static final boolean VDBG = Log.isLoggable("BluetoothMapClient", 2);
  
  private BluetoothAdapter mAdapter;
  
  private final BluetoothProfileConnector<IBluetoothMapClient> mProfileConnector = new BluetoothProfileConnector<IBluetoothMapClient>(this, 18, "BluetoothMapClient", IBluetoothMapClient.class.getName()) {
      public IBluetoothMapClient getServiceInterface(IBinder param1IBinder) {
        return IBluetoothMapClient.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothMapClient(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    if (DBG)
      Log.d("BluetoothMapClient", "Create BluetoothMapClient proxy object"); 
    this.mAdapter = BluetoothAdapter.getDefaultAdapter();
    this.mProfileConnector.connect(paramContext, paramServiceListener);
  }
  
  private IBluetoothMapClient getService() {
    return this.mProfileConnector.getService();
  }
  
  private boolean isEnabled() {
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (bluetoothAdapter != null && bluetoothAdapter.getState() == 12)
      return true; 
    if (DBG)
      Log.d("BluetoothMapClient", "Bluetooth is Not enabled"); 
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
  
  public void close() {
    this.mProfileConnector.disconnect();
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) {
    if (DBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("connect(");
      stringBuilder.append(paramBluetoothDevice);
      stringBuilder.append(")for MAPS MCE");
      Log.d("BluetoothMapClient", stringBuilder.toString());
    } 
    IBluetoothMapClient iBluetoothMapClient = getService();
    if (iBluetoothMapClient != null) {
      try {
        return iBluetoothMapClient.connect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMapClient", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothMapClient", "Proxy not attached to service");
      if (DBG)
        Log.d("BluetoothMapClient", Log.getStackTraceString(new Throwable())); 
    } 
    return false;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    if (DBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("disconnect(");
      stringBuilder.append(paramBluetoothDevice);
      stringBuilder.append(")");
      Log.d("BluetoothMapClient", stringBuilder.toString());
    } 
    IBluetoothMapClient iBluetoothMapClient = getService();
    if (iBluetoothMapClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothMapClient.disconnect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothMapClient == null)
      Log.w("BluetoothMapClient", "Proxy not attached to service"); 
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
    if (DBG)
      Log.d("BluetoothMapClient", "getConnectedDevices()"); 
    IBluetoothMapClient iBluetoothMapClient = getService();
    if (iBluetoothMapClient != null && isEnabled())
      try {
        return iBluetoothMapClient.getConnectedDevices();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (remoteException == null)
      Log.w("BluetoothMapClient", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  @SystemApi
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) {
    if (VDBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getConnectionPolicy(");
      stringBuilder.append(paramBluetoothDevice);
      stringBuilder.append(")");
      Log.d("BluetoothMapClient", stringBuilder.toString());
    } 
    IBluetoothMapClient iBluetoothMapClient = getService();
    if (iBluetoothMapClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothMapClient.getConnectionPolicy(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothMapClient == null)
      Log.w("BluetoothMapClient", "Proxy not attached to service"); 
    return 0;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    if (DBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getConnectionState(");
      stringBuilder.append(paramBluetoothDevice);
      stringBuilder.append(")");
      Log.d("BluetoothMapClient", stringBuilder.toString());
    } 
    IBluetoothMapClient iBluetoothMapClient = getService();
    if (iBluetoothMapClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothMapClient.getConnectionState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothMapClient == null)
      Log.w("BluetoothMapClient", "Proxy not attached to service"); 
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    if (DBG)
      Log.d("BluetoothMapClient", "getDevicesMatchingStates()"); 
    IBluetoothMapClient iBluetoothMapClient = getService();
    if (iBluetoothMapClient != null && isEnabled())
      try {
        return iBluetoothMapClient.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (iBluetoothMapClient == null)
      Log.w("BluetoothMapClient", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) {
    if (VDBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getPriority(");
      stringBuilder.append(paramBluetoothDevice);
      stringBuilder.append(")");
      Log.d("BluetoothMapClient", stringBuilder.toString());
    } 
    return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(paramBluetoothDevice));
  }
  
  public boolean getUnreadMessages(BluetoothDevice paramBluetoothDevice) {
    if (DBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getUnreadMessages(");
      stringBuilder.append(paramBluetoothDevice);
      stringBuilder.append(")");
      Log.d("BluetoothMapClient", stringBuilder.toString());
    } 
    IBluetoothMapClient iBluetoothMapClient = getService();
    if (iBluetoothMapClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothMapClient.getUnreadMessages(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
        return false;
      }  
    return false;
  }
  
  public boolean isConnected(BluetoothDevice paramBluetoothDevice) {
    if (VDBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("isConnected(");
      stringBuilder.append(paramBluetoothDevice);
      stringBuilder.append(")");
      Log.d("BluetoothMapClient", stringBuilder.toString());
    } 
    IBluetoothMapClient iBluetoothMapClient = getService();
    if (iBluetoothMapClient != null) {
      try {
        return iBluetoothMapClient.isConnected(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMapClient", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothMapClient", "Proxy not attached to service");
      if (DBG)
        Log.d("BluetoothMapClient", Log.getStackTraceString(new Throwable())); 
    } 
    return false;
  }
  
  public boolean isUploadingSupported(BluetoothDevice paramBluetoothDevice) {
    IBluetoothMapClient iBluetoothMapClient = getService();
    boolean bool = false;
    if (iBluetoothMapClient != null)
      try {
        if (isEnabled() && isValidDevice(paramBluetoothDevice)) {
          int i = iBluetoothMapClient.getSupportedFeatures(paramBluetoothDevice);
          if ((i & 0x8) > 0)
            bool = true; 
        } 
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMapClient", remoteException.getMessage());
        return false;
      }  
    return bool;
  }
  
  public boolean sendMessage(BluetoothDevice paramBluetoothDevice, Uri[] paramArrayOfUri, String paramString, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2) {
    if (DBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("sendMessage(");
      stringBuilder.append(paramBluetoothDevice);
      stringBuilder.append(", ");
      stringBuilder.append(paramArrayOfUri);
      stringBuilder.append(", ");
      stringBuilder.append(paramString);
      Log.d("BluetoothMapClient", stringBuilder.toString());
    } 
    IBluetoothMapClient iBluetoothMapClient = getService();
    if (iBluetoothMapClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothMapClient.sendMessage(paramBluetoothDevice, paramArrayOfUri, paramString, paramPendingIntent1, paramPendingIntent2);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
        return false;
      }  
    return false;
  }
  
  @SystemApi
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) {
    if (DBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("setConnectionPolicy(");
      stringBuilder.append(paramBluetoothDevice);
      stringBuilder.append(", ");
      stringBuilder.append(paramInt);
      stringBuilder.append(")");
      Log.d("BluetoothMapClient", stringBuilder.toString());
    } 
    IBluetoothMapClient iBluetoothMapClient = getService();
    if (iBluetoothMapClient != null && isEnabled() && isValidDevice(paramBluetoothDevice)) {
      if (paramInt != 0 && paramInt != 100)
        return false; 
      try {
        return iBluetoothMapClient.setConnectionPolicy(paramBluetoothDevice, paramInt);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothMapClient", Log.getStackTraceString(new Throwable()));
        return false;
      } 
    } 
    if (iBluetoothMapClient == null)
      Log.w("BluetoothMapClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean setPriority(BluetoothDevice paramBluetoothDevice, int paramInt) {
    if (DBG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("setPriority(");
      stringBuilder.append(paramBluetoothDevice);
      stringBuilder.append(", ");
      stringBuilder.append(paramInt);
      stringBuilder.append(")");
      Log.d("BluetoothMapClient", stringBuilder.toString());
    } 
    return setConnectionPolicy(paramBluetoothDevice, BluetoothAdapter.priorityToConnectionPolicy(paramInt));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothMapClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */