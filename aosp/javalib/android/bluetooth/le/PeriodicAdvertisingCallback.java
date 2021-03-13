package android.bluetooth.le;

import android.bluetooth.BluetoothDevice;

public abstract class PeriodicAdvertisingCallback {
  public static final int SYNC_NO_RESOURCES = 2;
  
  public static final int SYNC_NO_RESPONSE = 1;
  
  public static final int SYNC_SUCCESS = 0;
  
  public void onPeriodicAdvertisingReport(PeriodicAdvertisingReport paramPeriodicAdvertisingReport) {}
  
  public void onSyncEstablished(int paramInt1, BluetoothDevice paramBluetoothDevice, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {}
  
  public void onSyncLost(int paramInt) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/PeriodicAdvertisingCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */