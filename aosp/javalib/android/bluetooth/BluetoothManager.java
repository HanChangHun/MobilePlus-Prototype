package android.bluetooth;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class BluetoothManager {
  private static final boolean DBG = false;
  
  private static final String TAG = "BluetoothManager";
  
  private final BluetoothAdapter mAdapter;
  
  public BluetoothManager(Context paramContext) {
    if (paramContext.getAttributionTag() == null) {
      paramContext = paramContext.getApplicationContext();
      if (paramContext != null) {
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
      } else {
        throw new IllegalArgumentException("context not associated with any application (using a mock context?)");
      } 
    } else {
      IBinder iBinder = ServiceManager.getService("bluetooth_manager");
      if (iBinder != null) {
        this.mAdapter = new BluetoothAdapter(IBluetoothManager.Stub.asInterface(iBinder));
      } else {
        Log.e("BluetoothManager", "Bluetooth binder is null");
        this.mAdapter = null;
      } 
    } 
    BluetoothAdapter bluetoothAdapter = this.mAdapter;
    if (bluetoothAdapter != null)
      bluetoothAdapter.setContext(paramContext); 
  }
  
  public BluetoothAdapter getAdapter() {
    return this.mAdapter;
  }
  
  public List<BluetoothDevice> getConnectedDevices(int paramInt) {
    if (paramInt == 7 || paramInt == 8) {
      List<BluetoothDevice> list = new ArrayList();
      try {
        IBluetoothGatt iBluetoothGatt = this.mAdapter.getBluetoothManager().getBluetoothGatt();
        if (iBluetoothGatt == null)
          return list; 
        List<BluetoothDevice> list1 = iBluetoothGatt.getDevicesMatchingConnectionStates(new int[] { 2 });
        list = list1;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothManager", "", (Throwable)remoteException);
      } 
      return list;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Profile not supported: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice, int paramInt) {
    Iterator<BluetoothDevice> iterator = getConnectedDevices(paramInt).iterator();
    while (iterator.hasNext()) {
      if (paramBluetoothDevice.equals(iterator.next()))
        return 2; 
    } 
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int paramInt, int[] paramArrayOfint) {
    if (paramInt == 7 || paramInt == 8) {
      List<BluetoothDevice> list;
      ArrayList<BluetoothDevice> arrayList = new ArrayList();
      try {
        IBluetoothGatt iBluetoothGatt = this.mAdapter.getBluetoothManager().getBluetoothGatt();
        if (iBluetoothGatt == null)
          return arrayList; 
        list = iBluetoothGatt.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothManager", "", (Throwable)remoteException);
        list = arrayList;
      } 
      return list;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Profile not supported: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public BluetoothGattServer openGattServer(Context paramContext, BluetoothGattServerCallback paramBluetoothGattServerCallback) {
    return openGattServer(paramContext, paramBluetoothGattServerCallback, 0);
  }
  
  public BluetoothGattServer openGattServer(Context paramContext, BluetoothGattServerCallback paramBluetoothGattServerCallback, int paramInt) {
    if (paramContext != null && paramBluetoothGattServerCallback != null) {
      paramContext = null;
      try {
        BluetoothGattServer bluetoothGattServer1;
        IBluetoothGatt iBluetoothGatt = this.mAdapter.getBluetoothManager().getBluetoothGatt();
        if (iBluetoothGatt == null) {
          Log.e("BluetoothManager", "Fail to get GATT Server connection");
          return null;
        } 
        BluetoothGattServer bluetoothGattServer2 = new BluetoothGattServer();
        this(iBluetoothGatt, paramInt);
        boolean bool = Boolean.valueOf(bluetoothGattServer2.registerCallback(paramBluetoothGattServerCallback)).booleanValue();
        if (bool)
          bluetoothGattServer1 = bluetoothGattServer2; 
        return bluetoothGattServer1;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothManager", "", (Throwable)remoteException);
        return null;
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("null parameter: ");
    stringBuilder.append(remoteException);
    stringBuilder.append(" ");
    stringBuilder.append(paramBluetoothGattServerCallback);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */