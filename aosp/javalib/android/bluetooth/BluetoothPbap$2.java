package android.bluetooth;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

class null implements ServiceConnection {
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    BluetoothPbap.access$000("Proxy object connected");
    BluetoothPbap.access$202(BluetoothPbap.this, IBluetoothPbap.Stub.asInterface(paramIBinder));
    if (BluetoothPbap.access$300(BluetoothPbap.this) != null)
      BluetoothPbap.access$300(BluetoothPbap.this).onServiceConnected(6, BluetoothPbap.this); 
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {
    BluetoothPbap.access$000("Proxy object disconnected");
    BluetoothPbap.access$100(BluetoothPbap.this);
    if (BluetoothPbap.access$300(BluetoothPbap.this) != null)
      BluetoothPbap.access$300(BluetoothPbap.this).onServiceDisconnected(6); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothPbap$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */