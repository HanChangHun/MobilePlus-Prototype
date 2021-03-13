package android.bluetooth;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;

public abstract class BluetoothProfileConnector<T> {
  private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {
      public void onBluetoothStateChange(boolean param1Boolean) {
        if (param1Boolean) {
          BluetoothProfileConnector.this.doBind();
        } else {
          BluetoothProfileConnector.this.doUnbind();
        } 
      }
    };
  
  private final ServiceConnection mConnection = new ServiceConnection() {
      public void onServiceConnected(ComponentName param1ComponentName, IBinder param1IBinder) {
        BluetoothProfileConnector.this.logDebug("Proxy object connected");
        BluetoothProfileConnector bluetoothProfileConnector = BluetoothProfileConnector.this;
        BluetoothProfileConnector.access$302(bluetoothProfileConnector, bluetoothProfileConnector.getServiceInterface(param1IBinder));
        if (BluetoothProfileConnector.this.mServiceListener != null)
          BluetoothProfileConnector.this.mServiceListener.onServiceConnected(BluetoothProfileConnector.this.mProfileId, BluetoothProfileConnector.this.mProfileProxy); 
      }
      
      public void onServiceDisconnected(ComponentName param1ComponentName) {
        BluetoothProfileConnector.this.logDebug("Proxy object disconnected");
        BluetoothProfileConnector.this.doUnbind();
        if (BluetoothProfileConnector.this.mServiceListener != null)
          BluetoothProfileConnector.this.mServiceListener.onServiceDisconnected(BluetoothProfileConnector.this.mProfileId); 
      }
    };
  
  private Context mContext;
  
  private final int mProfileId;
  
  private final String mProfileName;
  
  private final BluetoothProfile mProfileProxy;
  
  private volatile T mService;
  
  private BluetoothProfile.ServiceListener mServiceListener;
  
  private final String mServiceName;
  
  BluetoothProfileConnector(BluetoothProfile paramBluetoothProfile, int paramInt, String paramString1, String paramString2) {
    this.mProfileId = paramInt;
    this.mProfileProxy = paramBluetoothProfile;
    this.mProfileName = paramString1;
    this.mServiceName = paramString2;
  }
  
  private boolean doBind() {
    synchronized (this.mConnection) {
      if (this.mService == null) {
        logDebug("Binding service...");
        try {
          Intent intent = new Intent();
          this(this.mServiceName);
          ComponentName componentName = intent.resolveSystemService(this.mContext.getPackageManager(), 0);
          intent.setComponent(componentName);
          if (componentName == null || !this.mContext.bindServiceAsUser(intent, this.mConnection, 0, UserHandle.CURRENT_OR_SELF)) {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("Could not bind to Bluetooth Service with ");
            stringBuilder.append(intent);
            logError(stringBuilder.toString());
            return false;
          } 
        } catch (SecurityException securityException) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Failed to bind service. ");
          stringBuilder.append(securityException);
          logError(stringBuilder.toString());
          return false;
        } 
      } 
      return true;
    } 
  }
  
  private void doUnbind() {
    synchronized (this.mConnection) {
      if (this.mService != null) {
        logDebug("Unbinding service...");
        try {
          this.mContext.unbindService(this.mConnection);
          this.mService = null;
        } catch (IllegalArgumentException illegalArgumentException) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Unable to unbind service: ");
          stringBuilder.append(illegalArgumentException);
          logError(stringBuilder.toString());
          this.mService = null;
        } finally {
          Exception exception;
        } 
      } 
      return;
    } 
  }
  
  private void logDebug(String paramString) {
    Log.d(this.mProfileName, paramString);
  }
  
  private void logError(String paramString) {
    Log.e(this.mProfileName, paramString);
  }
  
  void connect(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mContext = paramContext;
    this.mServiceListener = paramServiceListener;
    IBluetoothManager iBluetoothManager = BluetoothAdapter.getDefaultAdapter().getBluetoothManager();
    if (iBluetoothManager != null)
      try {
        iBluetoothManager.registerStateChangeCallback(this.mBluetoothStateChangeCallback);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to register state change callback. ");
        stringBuilder.append(remoteException);
        logError(stringBuilder.toString());
      }  
    doBind();
  }
  
  void disconnect() {
    this.mServiceListener = null;
    IBluetoothManager iBluetoothManager = BluetoothAdapter.getDefaultAdapter().getBluetoothManager();
    if (iBluetoothManager != null)
      try {
        iBluetoothManager.unregisterStateChangeCallback(this.mBluetoothStateChangeCallback);
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to unregister state change callback");
        stringBuilder.append(remoteException);
        logError(stringBuilder.toString());
      }  
    doUnbind();
  }
  
  T getService() {
    return this.mService;
  }
  
  public abstract T getServiceInterface(IBinder paramIBinder);
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothProfileConnector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */