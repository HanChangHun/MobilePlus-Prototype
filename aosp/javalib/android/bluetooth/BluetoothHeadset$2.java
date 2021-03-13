package android.bluetooth;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

class null extends IBluetoothProfileServiceConnection.Stub {
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    Log.d("BluetoothHeadset", "Proxy object connected");
    BluetoothHeadset.access$202(BluetoothHeadset.this, IBluetoothHeadset.Stub.asInterface(Binder.allowBlocking(paramIBinder)));
    BluetoothHeadset.access$300(BluetoothHeadset.this).sendMessage(BluetoothHeadset.access$300(BluetoothHeadset.this).obtainMessage(100));
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {
    Log.d("BluetoothHeadset", "Proxy object disconnected");
    BluetoothHeadset.access$000(BluetoothHeadset.this);
    BluetoothHeadset.access$300(BluetoothHeadset.this).sendMessage(BluetoothHeadset.access$300(BluetoothHeadset.this).obtainMessage(101));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHeadset$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */