package android.bluetooth;

import android.os.RemoteException;

class null extends IBluetoothManagerCallback.Stub {
  public void onBluetoothServiceDown() throws RemoteException {
    // Byte code:
    //   0: ldc android/bluetooth/BluetoothDevice
    //   2: monitorenter
    //   3: aconst_null
    //   4: invokestatic access$002 : (Landroid/bluetooth/IBluetooth;)Landroid/bluetooth/IBluetooth;
    //   7: pop
    //   8: ldc android/bluetooth/BluetoothDevice
    //   10: monitorexit
    //   11: return
    //   12: astore_1
    //   13: ldc android/bluetooth/BluetoothDevice
    //   15: monitorexit
    //   16: aload_1
    //   17: athrow
    // Exception table:
    //   from	to	target	type
    //   3	11	12	finally
    //   13	16	12	finally
  }
  
  public void onBluetoothServiceUp(IBluetooth paramIBluetooth) throws RemoteException {
    // Byte code:
    //   0: ldc android/bluetooth/BluetoothDevice
    //   2: monitorenter
    //   3: invokestatic access$000 : ()Landroid/bluetooth/IBluetooth;
    //   6: ifnonnull -> 14
    //   9: aload_1
    //   10: invokestatic access$002 : (Landroid/bluetooth/IBluetooth;)Landroid/bluetooth/IBluetooth;
    //   13: pop
    //   14: ldc android/bluetooth/BluetoothDevice
    //   16: monitorexit
    //   17: return
    //   18: astore_1
    //   19: ldc android/bluetooth/BluetoothDevice
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   3	14	18	finally
    //   14	17	18	finally
    //   19	22	18	finally
  }
  
  public void onBrEdrDown() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothDevice$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */