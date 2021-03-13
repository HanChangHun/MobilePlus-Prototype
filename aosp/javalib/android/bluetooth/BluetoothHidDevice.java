package android.bluetooth;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public final class BluetoothHidDevice implements BluetoothProfile {
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED";
  
  private static final boolean DBG = false;
  
  public static final byte ERROR_RSP_INVALID_PARAM = 4;
  
  public static final byte ERROR_RSP_INVALID_RPT_ID = 2;
  
  public static final byte ERROR_RSP_NOT_READY = 1;
  
  public static final byte ERROR_RSP_SUCCESS = 0;
  
  public static final byte ERROR_RSP_UNKNOWN = 14;
  
  public static final byte ERROR_RSP_UNSUPPORTED_REQ = 3;
  
  public static final byte PROTOCOL_BOOT_MODE = 0;
  
  public static final byte PROTOCOL_REPORT_MODE = 1;
  
  public static final byte REPORT_TYPE_FEATURE = 3;
  
  public static final byte REPORT_TYPE_INPUT = 1;
  
  public static final byte REPORT_TYPE_OUTPUT = 2;
  
  public static final byte SUBCLASS1_COMBO = -64;
  
  public static final byte SUBCLASS1_KEYBOARD = 64;
  
  public static final byte SUBCLASS1_MOUSE = -128;
  
  public static final byte SUBCLASS1_NONE = 0;
  
  public static final byte SUBCLASS2_CARD_READER = 6;
  
  public static final byte SUBCLASS2_DIGITIZER_TABLET = 5;
  
  public static final byte SUBCLASS2_GAMEPAD = 2;
  
  public static final byte SUBCLASS2_JOYSTICK = 1;
  
  public static final byte SUBCLASS2_REMOTE_CONTROL = 3;
  
  public static final byte SUBCLASS2_SENSING_DEVICE = 4;
  
  public static final byte SUBCLASS2_UNCATEGORIZED = 0;
  
  private static final String TAG = BluetoothHidDevice.class.getSimpleName();
  
  private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
  
  private final BluetoothProfileConnector<IBluetoothHidDevice> mProfileConnector = new BluetoothProfileConnector<IBluetoothHidDevice>(this, 19, "BluetoothHidDevice", IBluetoothHidDevice.class.getName()) {
      public IBluetoothHidDevice getServiceInterface(IBinder param1IBinder) {
        return IBluetoothHidDevice.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothHidDevice(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mProfileConnector.connect(paramContext, paramServiceListener);
  }
  
  private IBluetoothHidDevice getService() {
    return this.mProfileConnector.getService();
  }
  
  private boolean isEnabled() {
    return (this.mAdapter.getState() == 12);
  }
  
  private boolean isValidDevice(BluetoothDevice paramBluetoothDevice) {
    return (paramBluetoothDevice == null) ? false : (BluetoothAdapter.checkBluetoothAddress(paramBluetoothDevice.getAddress()));
  }
  
  private static void log(String paramString) {}
  
  void close() {
    this.mProfileConnector.disconnect();
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) {
    boolean bool1 = false;
    boolean bool2 = false;
    IBluetoothHidDevice iBluetoothHidDevice = getService();
    if (iBluetoothHidDevice != null) {
      try {
        bool1 = iBluetoothHidDevice.connect(paramBluetoothDevice);
        bool2 = bool1;
      } catch (RemoteException remoteException) {
        Log.e(TAG, remoteException.toString());
      } 
    } else {
      Log.w(TAG, "Proxy not attached to service");
      bool2 = bool1;
    } 
    return bool2;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    boolean bool1 = false;
    boolean bool2 = false;
    IBluetoothHidDevice iBluetoothHidDevice = getService();
    if (iBluetoothHidDevice != null) {
      try {
        bool1 = iBluetoothHidDevice.disconnect(paramBluetoothDevice);
        bool2 = bool1;
      } catch (RemoteException remoteException) {
        Log.e(TAG, remoteException.toString());
      } 
    } else {
      Log.w(TAG, "Proxy not attached to service");
      bool2 = bool1;
    } 
    return bool2;
  }
  
  public List<BluetoothDevice> getConnectedDevices() {
    IBluetoothHidDevice iBluetoothHidDevice = getService();
    if (iBluetoothHidDevice != null) {
      try {
        return iBluetoothHidDevice.getConnectedDevices();
      } catch (RemoteException remoteException) {
        Log.e(TAG, remoteException.toString());
      } 
    } else {
      Log.w(TAG, "Proxy not attached to service");
    } 
    return new ArrayList<>();
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHidDevice iBluetoothHidDevice = getService();
    if (iBluetoothHidDevice != null) {
      try {
        return iBluetoothHidDevice.getConnectionState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e(TAG, remoteException.toString());
      } 
    } else {
      Log.w(TAG, "Proxy not attached to service");
    } 
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    IBluetoothHidDevice iBluetoothHidDevice = getService();
    if (iBluetoothHidDevice != null) {
      try {
        return iBluetoothHidDevice.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        Log.e(TAG, remoteException.toString());
      } 
    } else {
      Log.w(TAG, "Proxy not attached to service");
    } 
    return new ArrayList<>();
  }
  
  public String getUserAppName() {
    IBluetoothHidDevice iBluetoothHidDevice = getService();
    if (iBluetoothHidDevice != null) {
      try {
        return iBluetoothHidDevice.getUserAppName();
      } catch (RemoteException remoteException) {
        Log.e(TAG, remoteException.toString());
      } 
    } else {
      Log.w(TAG, "Proxy not attached to service");
    } 
    return "";
  }
  
  public boolean registerApp(BluetoothHidDeviceAppSdpSettings paramBluetoothHidDeviceAppSdpSettings, BluetoothHidDeviceAppQosSettings paramBluetoothHidDeviceAppQosSettings1, BluetoothHidDeviceAppQosSettings paramBluetoothHidDeviceAppQosSettings2, Executor paramExecutor, Callback paramCallback) {
    boolean bool1 = false;
    boolean bool2 = false;
    if (paramBluetoothHidDeviceAppSdpSettings != null) {
      if (paramExecutor != null) {
        if (paramCallback != null) {
          IBluetoothHidDevice iBluetoothHidDevice = getService();
          if (iBluetoothHidDevice != null) {
            try {
              CallbackWrapper callbackWrapper = new CallbackWrapper();
              this(paramExecutor, paramCallback);
              bool1 = iBluetoothHidDevice.registerApp(paramBluetoothHidDeviceAppSdpSettings, paramBluetoothHidDeviceAppQosSettings1, paramBluetoothHidDeviceAppQosSettings2, callbackWrapper);
              bool2 = bool1;
            } catch (RemoteException remoteException) {
              Log.e(TAG, remoteException.toString());
            } 
          } else {
            Log.w(TAG, "Proxy not attached to service");
            bool2 = bool1;
          } 
          return bool2;
        } 
        throw new IllegalArgumentException("callback parameter cannot be null");
      } 
      throw new IllegalArgumentException("executor parameter cannot be null");
    } 
    throw new IllegalArgumentException("sdp parameter cannot be null");
  }
  
  public boolean replyReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, byte[] paramArrayOfbyte) {
    boolean bool1 = false;
    boolean bool2 = false;
    IBluetoothHidDevice iBluetoothHidDevice = getService();
    if (iBluetoothHidDevice != null) {
      try {
        bool1 = iBluetoothHidDevice.replyReport(paramBluetoothDevice, paramByte1, paramByte2, paramArrayOfbyte);
        bool2 = bool1;
      } catch (RemoteException remoteException) {
        Log.e(TAG, remoteException.toString());
      } 
    } else {
      Log.w(TAG, "Proxy not attached to service");
      bool2 = bool1;
    } 
    return bool2;
  }
  
  public boolean reportError(BluetoothDevice paramBluetoothDevice, byte paramByte) {
    boolean bool1 = false;
    boolean bool2 = false;
    IBluetoothHidDevice iBluetoothHidDevice = getService();
    if (iBluetoothHidDevice != null) {
      try {
        bool1 = iBluetoothHidDevice.reportError(paramBluetoothDevice, paramByte);
        bool2 = bool1;
      } catch (RemoteException remoteException) {
        Log.e(TAG, remoteException.toString());
      } 
    } else {
      Log.w(TAG, "Proxy not attached to service");
      bool2 = bool1;
    } 
    return bool2;
  }
  
  public boolean sendReport(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfbyte) {
    boolean bool1 = false;
    boolean bool2 = false;
    IBluetoothHidDevice iBluetoothHidDevice = getService();
    if (iBluetoothHidDevice != null) {
      try {
        bool1 = iBluetoothHidDevice.sendReport(paramBluetoothDevice, paramInt, paramArrayOfbyte);
        bool2 = bool1;
      } catch (RemoteException remoteException) {
        Log.e(TAG, remoteException.toString());
      } 
    } else {
      Log.w(TAG, "Proxy not attached to service");
      bool2 = bool1;
    } 
    return bool2;
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
      IBluetoothHidDevice iBluetoothHidDevice = getService();
      if (iBluetoothHidDevice != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return (paramInt != 0 && paramInt != 100) ? false : iBluetoothHidDevice.setConnectionPolicy(paramBluetoothDevice, paramInt); 
      if (iBluetoothHidDevice == null)
        Log.w(TAG, "Proxy not attached to service"); 
      return false;
    } catch (RemoteException remoteException) {
      String str = TAG;
      stringBuilder = new StringBuilder();
      stringBuilder.append("Stack:");
      stringBuilder.append(Log.getStackTraceString(new Throwable()));
      Log.e(str, stringBuilder.toString());
      return false;
    } 
  }
  
  public boolean unregisterApp() {
    boolean bool1 = false;
    boolean bool2 = false;
    IBluetoothHidDevice iBluetoothHidDevice = getService();
    if (iBluetoothHidDevice != null) {
      try {
        bool1 = iBluetoothHidDevice.unregisterApp();
        bool2 = bool1;
      } catch (RemoteException remoteException) {
        Log.e(TAG, remoteException.toString());
      } 
    } else {
      Log.w(TAG, "Proxy not attached to service");
      bool2 = bool1;
    } 
    return bool2;
  }
  
  public static abstract class Callback {
    private static final String TAG = "BluetoothHidDevCallback";
    
    public void onAppStatusChanged(BluetoothDevice param1BluetoothDevice, boolean param1Boolean) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onAppStatusChanged: pluggedDevice=");
      stringBuilder.append(param1BluetoothDevice);
      stringBuilder.append(" registered=");
      stringBuilder.append(param1Boolean);
      Log.d("BluetoothHidDevCallback", stringBuilder.toString());
    }
    
    public void onConnectionStateChanged(BluetoothDevice param1BluetoothDevice, int param1Int) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onConnectionStateChanged: device=");
      stringBuilder.append(param1BluetoothDevice);
      stringBuilder.append(" state=");
      stringBuilder.append(param1Int);
      Log.d("BluetoothHidDevCallback", stringBuilder.toString());
    }
    
    public void onGetReport(BluetoothDevice param1BluetoothDevice, byte param1Byte1, byte param1Byte2, int param1Int) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onGetReport: device=");
      stringBuilder.append(param1BluetoothDevice);
      stringBuilder.append(" type=");
      stringBuilder.append(param1Byte1);
      stringBuilder.append(" id=");
      stringBuilder.append(param1Byte2);
      stringBuilder.append(" bufferSize=");
      stringBuilder.append(param1Int);
      Log.d("BluetoothHidDevCallback", stringBuilder.toString());
    }
    
    public void onInterruptData(BluetoothDevice param1BluetoothDevice, byte param1Byte, byte[] param1ArrayOfbyte) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onInterruptData: device=");
      stringBuilder.append(param1BluetoothDevice);
      stringBuilder.append(" reportId=");
      stringBuilder.append(param1Byte);
      Log.d("BluetoothHidDevCallback", stringBuilder.toString());
    }
    
    public void onSetProtocol(BluetoothDevice param1BluetoothDevice, byte param1Byte) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onSetProtocol: device=");
      stringBuilder.append(param1BluetoothDevice);
      stringBuilder.append(" protocol=");
      stringBuilder.append(param1Byte);
      Log.d("BluetoothHidDevCallback", stringBuilder.toString());
    }
    
    public void onSetReport(BluetoothDevice param1BluetoothDevice, byte param1Byte1, byte param1Byte2, byte[] param1ArrayOfbyte) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onSetReport: device=");
      stringBuilder.append(param1BluetoothDevice);
      stringBuilder.append(" type=");
      stringBuilder.append(param1Byte1);
      stringBuilder.append(" id=");
      stringBuilder.append(param1Byte2);
      Log.d("BluetoothHidDevCallback", stringBuilder.toString());
    }
    
    public void onVirtualCableUnplug(BluetoothDevice param1BluetoothDevice) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onVirtualCableUnplug: device=");
      stringBuilder.append(param1BluetoothDevice);
      Log.d("BluetoothHidDevCallback", stringBuilder.toString());
    }
  }
  
  private static class CallbackWrapper extends IBluetoothHidDeviceCallback.Stub {
    private final BluetoothHidDevice.Callback mCallback;
    
    private final Executor mExecutor;
    
    CallbackWrapper(Executor param1Executor, BluetoothHidDevice.Callback param1Callback) {
      this.mExecutor = param1Executor;
      this.mCallback = param1Callback;
    }
    
    public void onAppStatusChanged(BluetoothDevice param1BluetoothDevice, boolean param1Boolean) {
      clearCallingIdentity();
      this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$NFluHjT4zTfYBRXClu_2k6mPKFI(this, param1BluetoothDevice, param1Boolean));
    }
    
    public void onConnectionStateChanged(BluetoothDevice param1BluetoothDevice, int param1Int) {
      clearCallingIdentity();
      this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$qtStwQVkGfOs2iJIiePWqJJpi0w(this, param1BluetoothDevice, param1Int));
    }
    
    public void onGetReport(BluetoothDevice param1BluetoothDevice, byte param1Byte1, byte param1Byte2, int param1Int) {
      clearCallingIdentity();
      this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$Eyz_qG6mvTlh6a8Bp41ZoEJzQCQ(this, param1BluetoothDevice, param1Byte1, param1Byte2, param1Int));
    }
    
    public void onInterruptData(BluetoothDevice param1BluetoothDevice, byte param1Byte, byte[] param1ArrayOfbyte) {
      clearCallingIdentity();
      this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$xW99_tc95OmGApoKnpQ9q1TXb9k(this, param1BluetoothDevice, param1Byte, param1ArrayOfbyte));
    }
    
    public void onSetProtocol(BluetoothDevice param1BluetoothDevice, byte param1Byte) {
      clearCallingIdentity();
      this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$ypkr5GGxsAkGSBiLjIRwg_PzqCM(this, param1BluetoothDevice, param1Byte));
    }
    
    public void onSetReport(BluetoothDevice param1BluetoothDevice, byte param1Byte1, byte param1Byte2, byte[] param1ArrayOfbyte) {
      clearCallingIdentity();
      this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$3bTGVlfKj7Y0SZdifW_Ya2myDKs(this, param1BluetoothDevice, param1Byte1, param1Byte2, param1ArrayOfbyte));
    }
    
    public void onVirtualCableUnplug(BluetoothDevice param1BluetoothDevice) {
      clearCallingIdentity();
      this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$jiodzbAJAcleQCwlDcBjvDddELM(this, param1BluetoothDevice));
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHidDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */