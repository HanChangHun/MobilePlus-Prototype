package android.bluetooth;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class null extends Handler {
  null(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 100) {
      if (i == 101 && BluetoothHeadset.access$400(BluetoothHeadset.this) != null)
        BluetoothHeadset.access$400(BluetoothHeadset.this).onServiceDisconnected(1); 
    } else if (BluetoothHeadset.access$400(BluetoothHeadset.this) != null) {
      BluetoothHeadset.access$400(BluetoothHeadset.this).onServiceConnected(1, BluetoothHeadset.this);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHeadset$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */