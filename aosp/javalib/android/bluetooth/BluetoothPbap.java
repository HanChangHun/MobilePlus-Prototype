package android.bluetooth;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SystemApi
public class BluetoothPbap implements BluetoothProfile {
  @SystemApi
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.pbap.profile.action.CONNECTION_STATE_CHANGED";
  
  private static final boolean DBG = false;
  
  public static final int RESULT_CANCELED = 2;
  
  public static final int RESULT_FAILURE = 0;
  
  public static final int RESULT_SUCCESS = 1;
  
  private static final String TAG = "BluetoothPbap";
  
  private BluetoothAdapter mAdapter;
  
  private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {
      public void onBluetoothStateChange(boolean param1Boolean) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onBluetoothStateChange: up=");
        stringBuilder.append(param1Boolean);
        BluetoothPbap.log(stringBuilder.toString());
        if (!param1Boolean) {
          BluetoothPbap.this.doUnbind();
        } else {
          BluetoothPbap.this.doBind();
        } 
      }
    };
  
  private final ServiceConnection mConnection = new ServiceConnection() {
      public void onServiceConnected(ComponentName param1ComponentName, IBinder param1IBinder) {
        BluetoothPbap.log("Proxy object connected");
        BluetoothPbap.access$202(BluetoothPbap.this, IBluetoothPbap.Stub.asInterface(param1IBinder));
        if (BluetoothPbap.this.mServiceListener != null)
          BluetoothPbap.this.mServiceListener.onServiceConnected(6, BluetoothPbap.this); 
      }
      
      public void onServiceDisconnected(ComponentName param1ComponentName) {
        BluetoothPbap.log("Proxy object disconnected");
        BluetoothPbap.this.doUnbind();
        if (BluetoothPbap.this.mServiceListener != null)
          BluetoothPbap.this.mServiceListener.onServiceDisconnected(6); 
      }
    };
  
  private final Context mContext;
  
  private volatile IBluetoothPbap mService;
  
  private BluetoothProfile.ServiceListener mServiceListener;
  
  public BluetoothPbap(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mContext = paramContext;
    this.mServiceListener = paramServiceListener;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    this.mAdapter = bluetoothAdapter;
    IBluetoothManager iBluetoothManager = bluetoothAdapter.getBluetoothManager();
    if (iBluetoothManager != null)
      try {
        iBluetoothManager.registerStateChangeCallback(this.mBluetoothStateChangeCallback);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothPbap", "", (Throwable)remoteException);
      }  
    doBind();
  }
  
  private void doUnbind() {
    synchronized (this.mConnection) {
      if (this.mService != null) {
        log("Unbinding service...");
        try {
          this.mContext.unbindService(this.mConnection);
          this.mService = null;
        } catch (IllegalArgumentException illegalArgumentException) {
          Log.e("BluetoothPbap", "", illegalArgumentException);
          this.mService = null;
        } finally {
          Exception exception;
        } 
      } 
      return;
    } 
  }
  
  private boolean isEnabled() {
    return (this.mAdapter.getState() == 12);
  }
  
  private boolean isValidDevice(BluetoothDevice paramBluetoothDevice) {
    return (paramBluetoothDevice == null) ? false : (BluetoothAdapter.checkBluetoothAddress(paramBluetoothDevice.getAddress()));
  }
  
  private static void log(String paramString) {}
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mAdapter : Landroid/bluetooth/BluetoothAdapter;
    //   6: invokevirtual getBluetoothManager : ()Landroid/bluetooth/IBluetoothManager;
    //   9: astore_1
    //   10: aload_1
    //   11: ifnull -> 37
    //   14: aload_1
    //   15: aload_0
    //   16: getfield mBluetoothStateChangeCallback : Landroid/bluetooth/IBluetoothStateChangeCallback;
    //   19: invokeinterface unregisterStateChangeCallback : (Landroid/bluetooth/IBluetoothStateChangeCallback;)V
    //   24: goto -> 37
    //   27: astore_1
    //   28: ldc 'BluetoothPbap'
    //   30: ldc ''
    //   32: aload_1
    //   33: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   36: pop
    //   37: aload_0
    //   38: invokespecial doUnbind : ()V
    //   41: aload_0
    //   42: aconst_null
    //   43: putfield mServiceListener : Landroid/bluetooth/BluetoothProfile$ServiceListener;
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	49	finally
    //   14	24	27	android/os/RemoteException
    //   14	24	49	finally
    //   28	37	49	finally
    //   37	46	49	finally
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    log("disconnect()");
    IBluetoothPbap iBluetoothPbap = this.mService;
    if (iBluetoothPbap == null) {
      Log.w("BluetoothPbap", "Proxy not attached to service");
      return false;
    } 
    try {
      iBluetoothPbap.disconnect(paramBluetoothDevice);
      return true;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothPbap", remoteException.toString());
      return false;
    } 
  }
  
  boolean doBind() {
    Exception exception;
    ServiceConnection serviceConnection = this.mConnection;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{android/content/ServiceConnection}, name=null} */
    try {
      if (this.mService == null) {
        log("Binding service...");
        Intent intent = new Intent();
        this(IBluetoothPbap.class.getName());
        ComponentName componentName = intent.resolveSystemService(this.mContext.getPackageManager(), 0);
        intent.setComponent(componentName);
        if (componentName == null || !this.mContext.bindServiceAsUser(intent, this.mConnection, 0, UserHandle.CURRENT_OR_SELF)) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Could not bind to Bluetooth Pbap Service with ");
          stringBuilder.append(intent);
          Log.e("BluetoothPbap", stringBuilder.toString());
          /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/content/ServiceConnection}, name=null} */
          return false;
        } 
      } 
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/content/ServiceConnection}, name=null} */
      return true;
    } catch (SecurityException null) {
      Log.e("BluetoothPbap", "", exception);
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/content/ServiceConnection}, name=null} */
      return false;
    } finally {}
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/content/ServiceConnection}, name=null} */
    throw exception;
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
    log("getConnectedDevices()");
    IBluetoothPbap iBluetoothPbap = this.mService;
    if (iBluetoothPbap == null) {
      Log.w("BluetoothPbap", "Proxy not attached to service");
      return new ArrayList<>();
    } 
    try {
      return iBluetoothPbap.getConnectedDevices();
    } catch (RemoteException remoteException) {
      Log.e("BluetoothPbap", remoteException.toString());
      return new ArrayList<>();
    } 
  }
  
  @SystemApi
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("getConnectionState: device=");
    stringBuilder.append(paramBluetoothDevice);
    log(stringBuilder.toString());
    try {
      IBluetoothPbap iBluetoothPbap = this.mService;
      if (iBluetoothPbap != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return iBluetoothPbap.getConnectionState(paramBluetoothDevice); 
      if (iBluetoothPbap == null)
        Log.w("BluetoothPbap", "Proxy not attached to service"); 
      return 0;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothPbap", remoteException.toString());
      return 0;
    } 
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("getDevicesMatchingConnectionStates: states=");
    stringBuilder.append(Arrays.toString(paramArrayOfint));
    log(stringBuilder.toString());
    IBluetoothPbap iBluetoothPbap = this.mService;
    if (iBluetoothPbap == null) {
      Log.w("BluetoothPbap", "Proxy not attached to service");
      return new ArrayList<>();
    } 
    try {
      return iBluetoothPbap.getDevicesMatchingConnectionStates(paramArrayOfint);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothPbap", remoteException.toString());
      return new ArrayList<>();
    } 
  }
  
  @SystemApi
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) {
    try {
      IBluetoothPbap iBluetoothPbap = this.mService;
      if (iBluetoothPbap != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        return (paramInt != 0 && paramInt != 100) ? false : iBluetoothPbap.setConnectionPolicy(paramBluetoothDevice, paramInt); 
      if (iBluetoothPbap == null)
        Log.w("BluetoothPbap", "Proxy not attached to service"); 
      return false;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Stack:");
      stringBuilder.append(Log.getStackTraceString(new Throwable()));
      Log.e("BluetoothPbap", stringBuilder.toString());
      return false;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothPbap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */