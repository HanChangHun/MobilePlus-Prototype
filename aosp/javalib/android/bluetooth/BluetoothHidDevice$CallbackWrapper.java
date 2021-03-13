package android.bluetooth;

import java.util.concurrent.Executor;

class CallbackWrapper extends IBluetoothHidDeviceCallback.Stub {
  private final BluetoothHidDevice.Callback mCallback;
  
  private final Executor mExecutor;
  
  CallbackWrapper(Executor paramExecutor, BluetoothHidDevice.Callback paramCallback) {
    this.mExecutor = paramExecutor;
    this.mCallback = paramCallback;
  }
  
  public void onAppStatusChanged(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) {
    clearCallingIdentity();
    this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$NFluHjT4zTfYBRXClu_2k6mPKFI(this, paramBluetoothDevice, paramBoolean));
  }
  
  public void onConnectionStateChanged(BluetoothDevice paramBluetoothDevice, int paramInt) {
    clearCallingIdentity();
    this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$qtStwQVkGfOs2iJIiePWqJJpi0w(this, paramBluetoothDevice, paramInt));
  }
  
  public void onGetReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, int paramInt) {
    clearCallingIdentity();
    this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$Eyz_qG6mvTlh6a8Bp41ZoEJzQCQ(this, paramBluetoothDevice, paramByte1, paramByte2, paramInt));
  }
  
  public void onInterruptData(BluetoothDevice paramBluetoothDevice, byte paramByte, byte[] paramArrayOfbyte) {
    clearCallingIdentity();
    this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$xW99_tc95OmGApoKnpQ9q1TXb9k(this, paramBluetoothDevice, paramByte, paramArrayOfbyte));
  }
  
  public void onSetProtocol(BluetoothDevice paramBluetoothDevice, byte paramByte) {
    clearCallingIdentity();
    this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$ypkr5GGxsAkGSBiLjIRwg_PzqCM(this, paramBluetoothDevice, paramByte));
  }
  
  public void onSetReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, byte[] paramArrayOfbyte) {
    clearCallingIdentity();
    this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$3bTGVlfKj7Y0SZdifW_Ya2myDKs(this, paramBluetoothDevice, paramByte1, paramByte2, paramArrayOfbyte));
  }
  
  public void onVirtualCableUnplug(BluetoothDevice paramBluetoothDevice) {
    clearCallingIdentity();
    this.mExecutor.execute(new _$$Lambda$BluetoothHidDevice$CallbackWrapper$jiodzbAJAcleQCwlDcBjvDddELM(this, paramBluetoothDevice));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHidDevice$CallbackWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */