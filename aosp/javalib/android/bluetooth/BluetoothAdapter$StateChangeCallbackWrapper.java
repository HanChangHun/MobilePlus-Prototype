package android.bluetooth;

public class StateChangeCallbackWrapper extends IBluetoothStateChangeCallback.Stub {
  private BluetoothAdapter.BluetoothStateChangeCallback mCallback;
  
  StateChangeCallbackWrapper(BluetoothAdapter.BluetoothStateChangeCallback paramBluetoothStateChangeCallback) {
    this.mCallback = paramBluetoothStateChangeCallback;
  }
  
  public void onBluetoothStateChange(boolean paramBoolean) {
    this.mCallback.onBluetoothStateChange(paramBoolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAdapter$StateChangeCallbackWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */