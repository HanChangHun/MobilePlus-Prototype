package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;

class null extends BluetoothProfileConnector {
  null(BluetoothProfile paramBluetoothProfile, int paramInt, String paramString1, String paramString2) {
    super(paramBluetoothProfile, paramInt, paramString1, paramString2);
  }
  
  public IBluetoothHeadsetClient getServiceInterface(IBinder paramIBinder) {
    return IBluetoothHeadsetClient.Stub.asInterface(Binder.allowBlocking(paramIBinder));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHeadsetClient$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */