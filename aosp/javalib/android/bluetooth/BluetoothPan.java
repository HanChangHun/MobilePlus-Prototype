package android.bluetooth;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

@SystemApi
public final class BluetoothPan implements BluetoothProfile {
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED";
  
  private static final boolean DBG = true;
  
  public static final String EXTRA_LOCAL_ROLE = "android.bluetooth.pan.extra.LOCAL_ROLE";
  
  public static final int LOCAL_NAP_ROLE = 1;
  
  public static final int LOCAL_PANU_ROLE = 2;
  
  public static final int PAN_CONNECT_FAILED_ALREADY_CONNECTED = 1001;
  
  public static final int PAN_CONNECT_FAILED_ATTEMPT_FAILED = 1002;
  
  public static final int PAN_DISCONNECT_FAILED_NOT_CONNECTED = 1000;
  
  public static final int PAN_OPERATION_GENERIC_FAILURE = 1003;
  
  public static final int PAN_OPERATION_SUCCESS = 1004;
  
  public static final int PAN_ROLE_NONE = 0;
  
  public static final int REMOTE_NAP_ROLE = 1;
  
  public static final int REMOTE_PANU_ROLE = 2;
  
  private static final String TAG = "BluetoothPan";
  
  private static final boolean VDBG = false;
  
  private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
  
  private final Context mContext;
  
  private final BluetoothProfileConnector<IBluetoothPan> mProfileConnector = new BluetoothProfileConnector<IBluetoothPan>(this, 5, "BluetoothPan", IBluetoothPan.class.getName()) {
      public IBluetoothPan getServiceInterface(IBinder param1IBinder) {
        return IBluetoothPan.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothPan(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mContext = paramContext;
    this.mProfileConnector.connect(paramContext, paramServiceListener);
  }
  
  private IBluetoothPan getService() {
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
    Log.d("BluetoothPan", paramString);
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
    IBluetoothPan iBluetoothPan = getService();
    if (iBluetoothPan != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothPan.connect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothPan", stringBuilder1.toString());
        return false;
      }  
    if (iBluetoothPan == null)
      Log.w("BluetoothPan", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("disconnect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothPan iBluetoothPan = getService();
    if (iBluetoothPan != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothPan.disconnect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothPan", stringBuilder1.toString());
        return false;
      }  
    if (iBluetoothPan == null)
      Log.w("BluetoothPan", "Proxy not attached to service"); 
    return false;
  }
  
  protected void finalize() {
    close();
  }
  
  @SystemApi
  public List<BluetoothDevice> getConnectedDevices() {
    StringBuilder stringBuilder;
    IBluetoothPan iBluetoothPan = getService();
    if (iBluetoothPan != null && isEnabled())
      try {
        return iBluetoothPan.getConnectedDevices();
      } catch (RemoteException remoteException) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothPan", stringBuilder.toString());
        return new ArrayList<>();
      }  
    if (stringBuilder == null)
      Log.w("BluetoothPan", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  @SystemApi
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    IBluetoothPan iBluetoothPan = getService();
    if (iBluetoothPan != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothPan.getConnectionState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothPan", stringBuilder.toString());
        return 0;
      }  
    if (iBluetoothPan == null)
      Log.w("BluetoothPan", "Proxy not attached to service"); 
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    IBluetoothPan iBluetoothPan = getService();
    if (iBluetoothPan != null && isEnabled())
      try {
        return iBluetoothPan.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothPan", stringBuilder.toString());
        return new ArrayList<>();
      }  
    if (iBluetoothPan == null)
      Log.w("BluetoothPan", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  @SystemApi
  public boolean isTetheringOn() {
    IBluetoothPan iBluetoothPan = getService();
    if (iBluetoothPan != null && isEnabled())
      try {
        return iBluetoothPan.isTetheringOn();
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack:");
        stringBuilder.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothPan", stringBuilder.toString());
      }  
    return false;
  }
  
  @SystemApi
  public void setBluetoothTethering(boolean paramBoolean) {
    String str = this.mContext.getOpPackageName();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setBluetoothTethering(");
    stringBuilder.append(paramBoolean);
    stringBuilder.append("), calling package:");
    stringBuilder.append(str);
    log(stringBuilder.toString());
    IBluetoothPan iBluetoothPan = getService();
    if (iBluetoothPan != null && isEnabled())
      try {
        iBluetoothPan.setBluetoothTethering(paramBoolean, str);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Stack:");
        stringBuilder1.append(Log.getStackTraceString(new Throwable()));
        Log.e("BluetoothPan", stringBuilder1.toString());
      }  
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
      IBluetoothPan iBluetoothPan = getService();
      if (iBluetoothPan != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return (paramInt != 0 && paramInt != 100) ? false : iBluetoothPan.setConnectionPolicy(paramBluetoothDevice, paramInt); 
      if (iBluetoothPan == null)
        Log.w("BluetoothPan", "Proxy not attached to service"); 
      return false;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Stack:");
      stringBuilder1.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothPan", stringBuilder1.toString());
      return false;
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface LocalPanRole {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface RemotePanRole {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothPan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */