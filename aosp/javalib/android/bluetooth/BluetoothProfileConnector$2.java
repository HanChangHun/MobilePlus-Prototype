package android.bluetooth;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

class null implements ServiceConnection {
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    BluetoothProfileConnector.access$200(BluetoothProfileConnector.this, "Proxy object connected");
    BluetoothProfileConnector bluetoothProfileConnector = BluetoothProfileConnector.this;
    BluetoothProfileConnector.access$302(bluetoothProfileConnector, bluetoothProfileConnector.getServiceInterface(paramIBinder));
    if (BluetoothProfileConnector.access$400(BluetoothProfileConnector.this) != null)
      BluetoothProfileConnector.access$400(BluetoothProfileConnector.this).onServiceConnected(BluetoothProfileConnector.access$500(BluetoothProfileConnector.this), BluetoothProfileConnector.access$600(BluetoothProfileConnector.this)); 
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {
    BluetoothProfileConnector.access$200(BluetoothProfileConnector.this, "Proxy object disconnected");
    BluetoothProfileConnector.access$100(BluetoothProfileConnector.this);
    if (BluetoothProfileConnector.access$400(BluetoothProfileConnector.this) != null)
      BluetoothProfileConnector.access$400(BluetoothProfileConnector.this).onServiceDisconnected(BluetoothProfileConnector.access$500(BluetoothProfileConnector.this)); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothProfileConnector$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */