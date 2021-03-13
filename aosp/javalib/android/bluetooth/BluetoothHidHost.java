package android.bluetooth;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

@SystemApi
public final class BluetoothHidHost implements BluetoothProfile {
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED";
  
  public static final String ACTION_HANDSHAKE = "android.bluetooth.input.profile.action.HANDSHAKE";
  
  public static final String ACTION_IDLE_TIME_CHANGED = "android.bluetooth.input.profile.action.IDLE_TIME_CHANGED";
  
  public static final String ACTION_PROTOCOL_MODE_CHANGED = "android.bluetooth.input.profile.action.PROTOCOL_MODE_CHANGED";
  
  public static final String ACTION_REPORT = "android.bluetooth.input.profile.action.REPORT";
  
  public static final String ACTION_VIRTUAL_UNPLUG_STATUS = "android.bluetooth.input.profile.action.VIRTUAL_UNPLUG_STATUS";
  
  private static final boolean DBG = true;
  
  public static final String EXTRA_IDLE_TIME = "android.bluetooth.BluetoothHidHost.extra.IDLE_TIME";
  
  public static final String EXTRA_PROTOCOL_MODE = "android.bluetooth.BluetoothHidHost.extra.PROTOCOL_MODE";
  
  public static final String EXTRA_REPORT = "android.bluetooth.BluetoothHidHost.extra.REPORT";
  
  public static final String EXTRA_REPORT_BUFFER_SIZE = "android.bluetooth.BluetoothHidHost.extra.REPORT_BUFFER_SIZE";
  
  public static final String EXTRA_REPORT_ID = "android.bluetooth.BluetoothHidHost.extra.REPORT_ID";
  
  public static final String EXTRA_REPORT_TYPE = "android.bluetooth.BluetoothHidHost.extra.REPORT_TYPE";
  
  public static final String EXTRA_STATUS = "android.bluetooth.BluetoothHidHost.extra.STATUS";
  
  public static final String EXTRA_VIRTUAL_UNPLUG_STATUS = "android.bluetooth.BluetoothHidHost.extra.VIRTUAL_UNPLUG_STATUS";
  
  public static final int INPUT_CONNECT_FAILED_ALREADY_CONNECTED = 5001;
  
  public static final int INPUT_CONNECT_FAILED_ATTEMPT_FAILED = 5002;
  
  public static final int INPUT_DISCONNECT_FAILED_NOT_CONNECTED = 5000;
  
  public static final int INPUT_OPERATION_GENERIC_FAILURE = 5003;
  
  public static final int INPUT_OPERATION_SUCCESS = 5004;
  
  public static final int PROTOCOL_BOOT_MODE = 1;
  
  public static final int PROTOCOL_REPORT_MODE = 0;
  
  public static final int PROTOCOL_UNSUPPORTED_MODE = 255;
  
  public static final byte REPORT_TYPE_FEATURE = 3;
  
  public static final byte REPORT_TYPE_INPUT = 1;
  
  public static final byte REPORT_TYPE_OUTPUT = 2;
  
  private static final String TAG = "BluetoothHidHost";
  
  private static final boolean VDBG = false;
  
  public static final int VIRTUAL_UNPLUG_STATUS_FAIL = 1;
  
  public static final int VIRTUAL_UNPLUG_STATUS_SUCCESS = 0;
  
  private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
  
  private final BluetoothProfileConnector<IBluetoothHidHost> mProfileConnector = new BluetoothProfileConnector<IBluetoothHidHost>(this, 4, "BluetoothHidHost", IBluetoothHidHost.class.getName()) {
      public IBluetoothHidHost getServiceInterface(IBinder param1IBinder) {
        return IBluetoothHidHost.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothHidHost(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mProfileConnector.connect(paramContext, paramServiceListener);
  }
  
  private IBluetoothHidHost getService() {
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
    Log.d("BluetoothHidHost", paramString);
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
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHidHost.connect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder1.toString());
        return false;
      }  
    if (iBluetoothHidHost == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("disconnect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHidHost.disconnect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder1.toString());
        return false;
      }  
    if (iBluetoothHidHost == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
    return false;
  }
  
  @SystemApi
  public List<BluetoothDevice> getConnectedDevices() {
    StringBuilder stringBuilder;
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled())
      try {
        return iBluetoothHidHost.getConnectedDevices();
      } catch (RemoteException remoteException) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder.toString());
        return new ArrayList<>();
      }  
    if (stringBuilder == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  @SystemApi
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) {
    if (paramBluetoothDevice != null) {
      IBluetoothHidHost iBluetoothHidHost = getService();
      if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        try {
          return iBluetoothHidHost.getConnectionPolicy(paramBluetoothDevice);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Stack:");
          stringBuilder.append(Log.getStackTraceString(new Throwable()));
          Log.e("BluetoothHidHost", stringBuilder.toString());
          return 0;
        }  
      if (iBluetoothHidHost == null)
        Log.w("BluetoothHidHost", "Proxy not attached to service"); 
      return 0;
    } 
    throw new IllegalArgumentException("device must not be null");
  }
  
  @SystemApi
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    if (paramBluetoothDevice != null) {
      IBluetoothHidHost iBluetoothHidHost = getService();
      if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        try {
          return iBluetoothHidHost.getConnectionState(paramBluetoothDevice);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Stack:");
          stringBuilder.append(Log.getStackTraceString(new Throwable()));
          Log.e("BluetoothHidHost", stringBuilder.toString());
          return 0;
        }  
      if (iBluetoothHidHost == null)
        Log.w("BluetoothHidHost", "Proxy not attached to service"); 
      return 0;
    } 
    throw new IllegalArgumentException("device must not be null");
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled())
      try {
        return iBluetoothHidHost.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder.toString());
        return new ArrayList<>();
      }  
    if (iBluetoothHidHost == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  public boolean getIdleTime(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("getIdletime(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHidHost.getIdleTime(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder1.toString());
        return false;
      }  
    if (iBluetoothHidHost == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
    return false;
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) {
    return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(paramBluetoothDevice));
  }
  
  public boolean getProtocolMode(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHidHost.getProtocolMode(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder.toString());
        return false;
      }  
    if (iBluetoothHidHost == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean getReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, int paramInt) {
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHidHost.getReport(paramBluetoothDevice, paramByte1, paramByte2, paramInt);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder.toString());
        return false;
      }  
    if (iBluetoothHidHost == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean sendData(BluetoothDevice paramBluetoothDevice, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("sendData(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append("), report=");
    stringBuilder.append(paramString);
    log(stringBuilder.toString());
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHidHost.sendData(paramBluetoothDevice, paramString);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder1.toString());
        return false;
      }  
    if (iBluetoothHidHost == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
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
    if (paramBluetoothDevice != null) {
      IBluetoothHidHost iBluetoothHidHost = getService();
      if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice)) {
        if (paramInt != 0 && paramInt != 100)
          return false; 
        try {
          return iBluetoothHidHost.setConnectionPolicy(paramBluetoothDevice, paramInt);
        } catch (RemoteException remoteException) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Stack:");
          stringBuilder1.append(Log.getStackTraceString(new Throwable()));
          Log.e("BluetoothHidHost", stringBuilder1.toString());
          return false;
        } 
      } 
      if (iBluetoothHidHost == null)
        Log.w("BluetoothHidHost", "Proxy not attached to service"); 
      return false;
    } 
    throw new IllegalArgumentException("device must not be null");
  }
  
  public boolean setIdleTime(BluetoothDevice paramBluetoothDevice, byte paramByte) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setIdletime(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append("), idleTime=");
    stringBuilder.append(paramByte);
    log(stringBuilder.toString());
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHidHost.setIdleTime(paramBluetoothDevice, paramByte);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder1.toString());
        return false;
      }  
    if (iBluetoothHidHost == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
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
  
  public boolean setProtocolMode(BluetoothDevice paramBluetoothDevice, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setProtocolMode(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHidHost.setProtocolMode(paramBluetoothDevice, paramInt);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder1.toString());
        return false;
      }  
    if (iBluetoothHidHost == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean setReport(BluetoothDevice paramBluetoothDevice, byte paramByte, String paramString) {
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHidHost.setReport(paramBluetoothDevice, paramByte, paramString);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder.toString());
        return false;
      }  
    if (iBluetoothHidHost == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean virtualUnplug(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("virtualUnplug(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHidHost iBluetoothHidHost = getService();
    if (iBluetoothHidHost != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHidHost.virtualUnplug(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothHidHost", stringBuilder1.toString());
        return false;
      }  
    if (iBluetoothHidHost == null)
      Log.w("BluetoothHidHost", "Proxy not attached to service"); 
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHidHost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */