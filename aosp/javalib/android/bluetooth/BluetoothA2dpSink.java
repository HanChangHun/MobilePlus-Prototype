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
public final class BluetoothA2dpSink implements BluetoothProfile {
  @SystemApi
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED";
  
  private static final boolean DBG = true;
  
  private static final String TAG = "BluetoothA2dpSink";
  
  private static final boolean VDBG = false;
  
  private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
  
  private final BluetoothProfileConnector<IBluetoothA2dpSink> mProfileConnector = new BluetoothProfileConnector<IBluetoothA2dpSink>(this, 11, "BluetoothA2dpSink", IBluetoothA2dpSink.class.getName()) {
      public IBluetoothA2dpSink getServiceInterface(IBinder param1IBinder) {
        return IBluetoothA2dpSink.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothA2dpSink(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mProfileConnector.connect(paramContext, paramServiceListener);
  }
  
  private IBluetoothA2dpSink getService() {
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
    Log.d("BluetoothA2dpSink", paramString);
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
  
  void close() {
    this.mProfileConnector.disconnect();
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("connect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothA2dpSink iBluetoothA2dpSink = getService();
    if (iBluetoothA2dpSink != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothA2dpSink.connect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothA2dpSink", stringBuilder1.toString());
        return false;
      }  
    if (iBluetoothA2dpSink == null)
      Log.w("BluetoothA2dpSink", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("disconnect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothA2dpSink iBluetoothA2dpSink = getService();
    if (iBluetoothA2dpSink != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothA2dpSink.disconnect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothA2dpSink", stringBuilder1.toString());
        return false;
      }  
    if (iBluetoothA2dpSink == null)
      Log.w("BluetoothA2dpSink", "Proxy not attached to service"); 
    return false;
  }
  
  public void finalize() {
    close();
  }
  
  public BluetoothAudioConfig getAudioConfig(BluetoothDevice paramBluetoothDevice) {
    IBluetoothA2dpSink iBluetoothA2dpSink = getService();
    if (iBluetoothA2dpSink != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothA2dpSink.getAudioConfig(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothA2dpSink", stringBuilder.toString());
        return null;
      }  
    if (iBluetoothA2dpSink == null)
      Log.w("BluetoothA2dpSink", "Proxy not attached to service"); 
    return null;
  }
  
  public List<BluetoothDevice> getConnectedDevices() {
    StringBuilder stringBuilder;
    IBluetoothA2dpSink iBluetoothA2dpSink = getService();
    if (iBluetoothA2dpSink != null && isEnabled())
      try {
        return iBluetoothA2dpSink.getConnectedDevices();
      } catch (RemoteException remoteException) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothA2dpSink", stringBuilder.toString());
        return new ArrayList<>();
      }  
    if (stringBuilder == null)
      Log.w("BluetoothA2dpSink", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  @SystemApi
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) {
    IBluetoothA2dpSink iBluetoothA2dpSink = getService();
    if (iBluetoothA2dpSink != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothA2dpSink.getConnectionPolicy(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothA2dpSink", stringBuilder.toString());
        return 0;
      }  
    if (iBluetoothA2dpSink == null)
      Log.w("BluetoothA2dpSink", "Proxy not attached to service"); 
    return 0;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    IBluetoothA2dpSink iBluetoothA2dpSink = getService();
    if (iBluetoothA2dpSink != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothA2dpSink.getConnectionState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothA2dpSink", stringBuilder.toString());
        return 0;
      }  
    if (iBluetoothA2dpSink == null)
      Log.w("BluetoothA2dpSink", "Proxy not attached to service"); 
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    IBluetoothA2dpSink iBluetoothA2dpSink = getService();
    if (iBluetoothA2dpSink != null && isEnabled())
      try {
        return iBluetoothA2dpSink.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothA2dpSink", stringBuilder.toString());
        return new ArrayList<>();
      }  
    if (iBluetoothA2dpSink == null)
      Log.w("BluetoothA2dpSink", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) {
    return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(paramBluetoothDevice));
  }
  
  @SystemApi
  public boolean isAudioPlaying(BluetoothDevice paramBluetoothDevice) {
    IBluetoothA2dpSink iBluetoothA2dpSink = getService();
    if (iBluetoothA2dpSink != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothA2dpSink.isA2dpPlaying(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothA2dpSink", stringBuilder.toString());
        return false;
      }  
    if (iBluetoothA2dpSink == null)
      Log.w("BluetoothA2dpSink", "Proxy not attached to service"); 
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
    IBluetoothA2dpSink iBluetoothA2dpSink = getService();
    if (iBluetoothA2dpSink != null && isEnabled() && isValidDevice(paramBluetoothDevice)) {
      if (paramInt != 0 && paramInt != 100)
        return false; 
      try {
        return iBluetoothA2dpSink.setConnectionPolicy(paramBluetoothDevice, paramInt);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothA2dpSink", stringBuilder1.toString());
        return false;
      } 
    } 
    if (iBluetoothA2dpSink == null)
      Log.w("BluetoothA2dpSink", "Proxy not attached to service"); 
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothA2dpSink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */