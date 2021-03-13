package android.bluetooth;

import android.os.ParcelUuid;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class BluetoothGattServer implements BluetoothProfile {
  private static final int CALLBACK_REG_TIMEOUT = 10000;
  
  private static final boolean DBG = true;
  
  private static final String TAG = "BluetoothGattServer";
  
  private static final boolean VDBG = false;
  
  private BluetoothAdapter mAdapter;
  
  private final IBluetoothGattServerCallback mBluetoothGattServerCallback = new IBluetoothGattServerCallback.Stub() {
      public void onCharacteristicReadRequest(String param1String, int param1Int1, int param1Int2, boolean param1Boolean, int param1Int3) {
        StringBuilder stringBuilder;
        BluetoothDevice bluetoothDevice = BluetoothGattServer.this.mAdapter.getRemoteDevice(param1String);
        BluetoothGattCharacteristic bluetoothGattCharacteristic = BluetoothGattServer.this.getCharacteristicByHandle(param1Int3);
        if (bluetoothGattCharacteristic == null) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("onCharacteristicReadRequest() no char for handle ");
          stringBuilder.append(param1Int3);
          Log.w("BluetoothGattServer", stringBuilder.toString());
          return;
        } 
        try {
          BluetoothGattServer.this.mCallback.onCharacteristicReadRequest(bluetoothDevice, param1Int1, param1Int2, (BluetoothGattCharacteristic)stringBuilder);
        } catch (Exception exception) {
          Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
        } 
      }
      
      public void onCharacteristicWriteRequest(String param1String, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, int param1Int4, byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder;
        BluetoothDevice bluetoothDevice = BluetoothGattServer.this.mAdapter.getRemoteDevice(param1String);
        BluetoothGattCharacteristic bluetoothGattCharacteristic = BluetoothGattServer.this.getCharacteristicByHandle(param1Int4);
        if (bluetoothGattCharacteristic == null) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("onCharacteristicWriteRequest() no char for handle ");
          stringBuilder.append(param1Int4);
          Log.w("BluetoothGattServer", stringBuilder.toString());
          return;
        } 
        try {
          BluetoothGattServer.this.mCallback.onCharacteristicWriteRequest((BluetoothDevice)stringBuilder, param1Int1, bluetoothGattCharacteristic, param1Boolean1, param1Boolean2, param1Int2, param1ArrayOfbyte);
        } catch (Exception exception) {
          Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
        } 
      }
      
      public void onConnectionUpdated(String param1String, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onConnectionUpdated() - Device=");
        stringBuilder.append(param1String);
        stringBuilder.append(" interval=");
        stringBuilder.append(param1Int1);
        stringBuilder.append(" latency=");
        stringBuilder.append(param1Int2);
        stringBuilder.append(" timeout=");
        stringBuilder.append(param1Int3);
        stringBuilder.append(" status=");
        stringBuilder.append(param1Int4);
        Log.d("BluetoothGattServer", stringBuilder.toString());
        BluetoothDevice bluetoothDevice = BluetoothGattServer.this.mAdapter.getRemoteDevice(param1String);
        if (bluetoothDevice == null)
          return; 
        try {
          BluetoothGattServer.this.mCallback.onConnectionUpdated(bluetoothDevice, param1Int1, param1Int2, param1Int3, param1Int4);
        } catch (Exception exception) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unhandled exception: ");
          stringBuilder.append(exception);
          Log.w("BluetoothGattServer", stringBuilder.toString());
        } 
      }
      
      public void onDescriptorReadRequest(String param1String, int param1Int1, int param1Int2, boolean param1Boolean, int param1Int3) {
        StringBuilder stringBuilder;
        BluetoothDevice bluetoothDevice = BluetoothGattServer.this.mAdapter.getRemoteDevice(param1String);
        BluetoothGattDescriptor bluetoothGattDescriptor = BluetoothGattServer.this.getDescriptorByHandle(param1Int3);
        if (bluetoothGattDescriptor == null) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("onDescriptorReadRequest() no desc for handle ");
          stringBuilder.append(param1Int3);
          Log.w("BluetoothGattServer", stringBuilder.toString());
          return;
        } 
        try {
          BluetoothGattServer.this.mCallback.onDescriptorReadRequest(bluetoothDevice, param1Int1, param1Int2, (BluetoothGattDescriptor)stringBuilder);
        } catch (Exception exception) {
          Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
        } 
      }
      
      public void onDescriptorWriteRequest(String param1String, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, int param1Int4, byte[] param1ArrayOfbyte) {
        StringBuilder stringBuilder;
        BluetoothDevice bluetoothDevice = BluetoothGattServer.this.mAdapter.getRemoteDevice(param1String);
        BluetoothGattDescriptor bluetoothGattDescriptor = BluetoothGattServer.this.getDescriptorByHandle(param1Int4);
        if (bluetoothGattDescriptor == null) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("onDescriptorWriteRequest() no desc for handle ");
          stringBuilder.append(param1Int4);
          Log.w("BluetoothGattServer", stringBuilder.toString());
          return;
        } 
        try {
          BluetoothGattServer.this.mCallback.onDescriptorWriteRequest((BluetoothDevice)stringBuilder, param1Int1, bluetoothGattDescriptor, param1Boolean1, param1Boolean2, param1Int2, param1ArrayOfbyte);
        } catch (Exception exception) {
          Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
        } 
      }
      
      public void onExecuteWrite(String param1String, int param1Int, boolean param1Boolean) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onExecuteWrite() - device=");
        stringBuilder.append(param1String);
        stringBuilder.append(", transId=");
        stringBuilder.append(param1Int);
        stringBuilder.append("execWrite=");
        stringBuilder.append(param1Boolean);
        Log.d("BluetoothGattServer", stringBuilder.toString());
        BluetoothDevice bluetoothDevice = BluetoothGattServer.this.mAdapter.getRemoteDevice(param1String);
        if (bluetoothDevice == null)
          return; 
        try {
          BluetoothGattServer.this.mCallback.onExecuteWrite(bluetoothDevice, param1Int, param1Boolean);
        } catch (Exception exception) {
          Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
        } 
      }
      
      public void onMtuChanged(String param1String, int param1Int) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onMtuChanged() - device=");
        stringBuilder.append(param1String);
        stringBuilder.append(", mtu=");
        stringBuilder.append(param1Int);
        Log.d("BluetoothGattServer", stringBuilder.toString());
        BluetoothDevice bluetoothDevice = BluetoothGattServer.this.mAdapter.getRemoteDevice(param1String);
        if (bluetoothDevice == null)
          return; 
        try {
          BluetoothGattServer.this.mCallback.onMtuChanged(bluetoothDevice, param1Int);
        } catch (Exception exception) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Unhandled exception: ");
          stringBuilder1.append(exception);
          Log.w("BluetoothGattServer", stringBuilder1.toString());
        } 
      }
      
      public void onNotificationSent(String param1String, int param1Int) {
        BluetoothDevice bluetoothDevice = BluetoothGattServer.this.mAdapter.getRemoteDevice(param1String);
        if (bluetoothDevice == null)
          return; 
        try {
          BluetoothGattServer.this.mCallback.onNotificationSent(bluetoothDevice, param1Int);
        } catch (Exception exception) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unhandled exception: ");
          stringBuilder.append(exception);
          Log.w("BluetoothGattServer", stringBuilder.toString());
        } 
      }
      
      public void onPhyRead(String param1String, int param1Int1, int param1Int2, int param1Int3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onPhyUpdate() - device=");
        stringBuilder.append(param1String);
        stringBuilder.append(", txPHy=");
        stringBuilder.append(param1Int1);
        stringBuilder.append(", rxPHy=");
        stringBuilder.append(param1Int2);
        Log.d("BluetoothGattServer", stringBuilder.toString());
        BluetoothDevice bluetoothDevice = BluetoothGattServer.this.mAdapter.getRemoteDevice(param1String);
        if (bluetoothDevice == null)
          return; 
        try {
          BluetoothGattServer.this.mCallback.onPhyRead(bluetoothDevice, param1Int1, param1Int2, param1Int3);
        } catch (Exception exception) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Unhandled exception: ");
          stringBuilder1.append(exception);
          Log.w("BluetoothGattServer", stringBuilder1.toString());
        } 
      }
      
      public void onPhyUpdate(String param1String, int param1Int1, int param1Int2, int param1Int3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onPhyUpdate() - device=");
        stringBuilder.append(param1String);
        stringBuilder.append(", txPHy=");
        stringBuilder.append(param1Int1);
        stringBuilder.append(", rxPHy=");
        stringBuilder.append(param1Int2);
        Log.d("BluetoothGattServer", stringBuilder.toString());
        BluetoothDevice bluetoothDevice = BluetoothGattServer.this.mAdapter.getRemoteDevice(param1String);
        if (bluetoothDevice == null)
          return; 
        try {
          BluetoothGattServer.this.mCallback.onPhyUpdate(bluetoothDevice, param1Int1, param1Int2, param1Int3);
        } catch (Exception exception) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unhandled exception: ");
          stringBuilder.append(exception);
          Log.w("BluetoothGattServer", stringBuilder.toString());
        } 
      }
      
      public void onServerConnectionState(int param1Int1, int param1Int2, boolean param1Boolean, String param1String) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onServerConnectionState() - status=");
        stringBuilder.append(param1Int1);
        stringBuilder.append(" serverIf=");
        stringBuilder.append(param1Int2);
        stringBuilder.append(" device=");
        stringBuilder.append(param1String);
        Log.d("BluetoothGattServer", stringBuilder.toString());
        try {
          BluetoothGattServerCallback bluetoothGattServerCallback = BluetoothGattServer.this.mCallback;
          BluetoothDevice bluetoothDevice = BluetoothGattServer.this.mAdapter.getRemoteDevice(param1String);
          if (param1Boolean) {
            param1Int2 = 2;
          } else {
            param1Int2 = 0;
          } 
          bluetoothGattServerCallback.onConnectionStateChange(bluetoothDevice, param1Int1, param1Int2);
        } catch (Exception exception) {
          Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
        } 
      }
      
      public void onServerRegistered(int param1Int1, int param1Int2) {
        null = new StringBuilder();
        null.append("onServerRegistered() - status=");
        null.append(param1Int1);
        null.append(" serverIf=");
        null.append(param1Int2);
        Log.d("BluetoothGattServer", null.toString());
        synchronized (BluetoothGattServer.this.mServerIfLock) {
          if (BluetoothGattServer.this.mCallback != null) {
            BluetoothGattServer.access$202(BluetoothGattServer.this, param1Int2);
            BluetoothGattServer.this.mServerIfLock.notify();
          } else {
            Log.e("BluetoothGattServer", "onServerRegistered: mCallback is null");
          } 
          return;
        } 
      }
      
      public void onServiceAdded(int param1Int, BluetoothGattService param1BluetoothGattService) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onServiceAdded() - handle=");
        stringBuilder.append(param1BluetoothGattService.getInstanceId());
        stringBuilder.append(" uuid=");
        stringBuilder.append(param1BluetoothGattService.getUuid());
        stringBuilder.append(" status=");
        stringBuilder.append(param1Int);
        Log.d("BluetoothGattServer", stringBuilder.toString());
        if (BluetoothGattServer.this.mPendingService == null)
          return; 
        BluetoothGattService bluetoothGattService = BluetoothGattServer.this.mPendingService;
        BluetoothGattServer.access$402(BluetoothGattServer.this, null);
        bluetoothGattService.setInstanceId(param1BluetoothGattService.getInstanceId());
        List<BluetoothGattCharacteristic> list2 = bluetoothGattService.getCharacteristics();
        List<BluetoothGattCharacteristic> list1 = param1BluetoothGattService.getCharacteristics();
        for (byte b = 0; b < list1.size(); b++) {
          BluetoothGattCharacteristic bluetoothGattCharacteristic1 = list2.get(b);
          BluetoothGattCharacteristic bluetoothGattCharacteristic2 = list1.get(b);
          bluetoothGattCharacteristic1.setInstanceId(bluetoothGattCharacteristic2.getInstanceId());
          List<BluetoothGattDescriptor> list3 = bluetoothGattCharacteristic1.getDescriptors();
          List<BluetoothGattDescriptor> list4 = bluetoothGattCharacteristic2.getDescriptors();
          for (byte b1 = 0; b1 < list4.size(); b1++)
            ((BluetoothGattDescriptor)list3.get(b1)).setInstanceId(((BluetoothGattDescriptor)list4.get(b1)).getInstanceId()); 
        } 
        BluetoothGattServer.this.mServices.add(bluetoothGattService);
        try {
          BluetoothGattServer.this.mCallback.onServiceAdded(param1Int, bluetoothGattService);
        } catch (Exception exception) {
          Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
        } 
      }
    };
  
  private BluetoothGattServerCallback mCallback;
  
  private BluetoothGattService mPendingService;
  
  private int mServerIf;
  
  private Object mServerIfLock = new Object();
  
  private IBluetoothGatt mService;
  
  private List<BluetoothGattService> mServices;
  
  private int mTransport;
  
  BluetoothGattServer(IBluetoothGatt paramIBluetoothGatt, int paramInt) {
    this.mService = paramIBluetoothGatt;
    this.mAdapter = BluetoothAdapter.getDefaultAdapter();
    this.mCallback = null;
    this.mServerIf = 0;
    this.mTransport = paramInt;
    this.mServices = new ArrayList<>();
  }
  
  private void unregisterCallback() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("unregisterCallback() - mServerIf=");
    stringBuilder.append(this.mServerIf);
    Log.d("BluetoothGattServer", stringBuilder.toString());
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mServerIf;
      if (i != 0) {
        try {
          this.mCallback = null;
          iBluetoothGatt.unregisterServer(i);
          this.mServerIf = 0;
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGattServer", "", (Throwable)remoteException);
        } 
        return;
      } 
    } 
  }
  
  public boolean addService(BluetoothGattService paramBluetoothGattService) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("addService() - service: ");
    stringBuilder.append(paramBluetoothGattService.getUuid());
    Log.d("BluetoothGattServer", stringBuilder.toString());
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mServerIf;
      if (i != 0) {
        this.mPendingService = paramBluetoothGattService;
        try {
          iBluetoothGatt.addService(i, paramBluetoothGattService);
          return true;
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGattServer", "", (Throwable)remoteException);
          return false;
        } 
      } 
    } 
    return false;
  }
  
  public void cancelConnection(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("cancelConnection() - device: ");
    stringBuilder.append(paramBluetoothDevice.getAddress());
    Log.d("BluetoothGattServer", stringBuilder.toString());
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mServerIf;
      if (i != 0) {
        try {
          iBluetoothGatt.serverDisconnect(i, paramBluetoothDevice.getAddress());
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGattServer", "", (Throwable)remoteException);
        } 
        return;
      } 
    } 
  }
  
  public void clearServices() {
    Log.d("BluetoothGattServer", "clearServices()");
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mServerIf;
      if (i != 0) {
        try {
          iBluetoothGatt.clearServices(i);
          this.mServices.clear();
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGattServer", "", (Throwable)remoteException);
        } 
        return;
      } 
    } 
  }
  
  public void close() {
    Log.d("BluetoothGattServer", "close()");
    unregisterCallback();
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("connect() - device: ");
    stringBuilder.append(paramBluetoothDevice.getAddress());
    stringBuilder.append(", auto: ");
    stringBuilder.append(paramBoolean);
    Log.d("BluetoothGattServer", stringBuilder.toString());
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mServerIf;
      if (i != 0)
        try {
          String str = paramBluetoothDevice.getAddress();
          if (!paramBoolean) {
            paramBoolean = true;
          } else {
            paramBoolean = false;
          } 
          iBluetoothGatt.serverConnect(i, str, paramBoolean, this.mTransport);
          return true;
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGattServer", "", (Throwable)remoteException);
          return false;
        }  
    } 
    return false;
  }
  
  BluetoothGattCharacteristic getCharacteristicByHandle(int paramInt) {
    Iterator<BluetoothGattService> iterator = this.mServices.iterator();
    while (iterator.hasNext()) {
      for (BluetoothGattCharacteristic bluetoothGattCharacteristic : ((BluetoothGattService)iterator.next()).getCharacteristics()) {
        if (bluetoothGattCharacteristic.getInstanceId() == paramInt)
          return bluetoothGattCharacteristic; 
      } 
    } 
    return null;
  }
  
  public List<BluetoothDevice> getConnectedDevices() {
    throw new UnsupportedOperationException("Use BluetoothManager#getConnectedDevices instead.");
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    throw new UnsupportedOperationException("Use BluetoothManager#getConnectionState instead.");
  }
  
  BluetoothGattDescriptor getDescriptorByHandle(int paramInt) {
    Iterator<BluetoothGattService> iterator = this.mServices.iterator();
    while (iterator.hasNext()) {
      Iterator<BluetoothGattCharacteristic> iterator1 = ((BluetoothGattService)iterator.next()).getCharacteristics().iterator();
      while (iterator1.hasNext()) {
        for (BluetoothGattDescriptor bluetoothGattDescriptor : ((BluetoothGattCharacteristic)iterator1.next()).getDescriptors()) {
          if (bluetoothGattDescriptor.getInstanceId() == paramInt)
            return bluetoothGattDescriptor; 
        } 
      } 
    } 
    return null;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    throw new UnsupportedOperationException("Use BluetoothManager#getDevicesMatchingConnectionStates instead.");
  }
  
  public BluetoothGattService getService(UUID paramUUID) {
    for (BluetoothGattService bluetoothGattService : this.mServices) {
      if (bluetoothGattService.getUuid().equals(paramUUID))
        return bluetoothGattService; 
    } 
    return null;
  }
  
  BluetoothGattService getService(UUID paramUUID, int paramInt1, int paramInt2) {
    for (BluetoothGattService bluetoothGattService : this.mServices) {
      if (bluetoothGattService.getType() == paramInt2 && bluetoothGattService.getInstanceId() == paramInt1 && bluetoothGattService.getUuid().equals(paramUUID))
        return bluetoothGattService; 
    } 
    return null;
  }
  
  public List<BluetoothGattService> getServices() {
    return this.mServices;
  }
  
  public boolean notifyCharacteristicChanged(BluetoothDevice paramBluetoothDevice, BluetoothGattCharacteristic paramBluetoothGattCharacteristic, boolean paramBoolean) {
    if (this.mService == null || this.mServerIf == 0)
      return false; 
    if (paramBluetoothGattCharacteristic.getService() == null)
      return false; 
    if (paramBluetoothGattCharacteristic.getValue() != null)
      try {
        this.mService.sendNotification(this.mServerIf, paramBluetoothDevice.getAddress(), paramBluetoothGattCharacteristic.getInstanceId(), paramBoolean, paramBluetoothGattCharacteristic.getValue());
        return true;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothGattServer", "", (Throwable)remoteException);
        return false;
      }  
    throw new IllegalArgumentException("Chracteristic value is empty. Use BluetoothGattCharacteristic#setvalue to update");
  }
  
  public void readPhy(BluetoothDevice paramBluetoothDevice) {
    try {
      this.mService.serverReadPhy(this.mServerIf, paramBluetoothDevice.getAddress());
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGattServer", "", (Throwable)remoteException);
    } 
  }
  
  boolean registerCallback(BluetoothGattServerCallback paramBluetoothGattServerCallback) {
    Log.d("BluetoothGattServer", "registerCallback()");
    if (this.mService == null) {
      Log.e("BluetoothGattServer", "GATT service not available");
      return false;
    } 
    UUID uUID = UUID.randomUUID();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("registerCallback() - UUID=");
    stringBuilder.append(uUID);
    Log.d("BluetoothGattServer", stringBuilder.toString());
    synchronized (this.mServerIfLock) {
      if (this.mCallback != null) {
        Log.e("BluetoothGattServer", "App can register callback only once");
        return false;
      } 
      this.mCallback = paramBluetoothGattServerCallback;
      try {
        IBluetoothGatt iBluetoothGatt = this.mService;
        ParcelUuid parcelUuid = new ParcelUuid();
        this(uUID);
        iBluetoothGatt.registerServer(parcelUuid, this.mBluetoothGattServerCallback);
        try {
          this.mServerIfLock.wait(10000L);
        } catch (InterruptedException interruptedException) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("");
          stringBuilder1.append(interruptedException);
          Log.e("BluetoothGattServer", stringBuilder1.toString());
          this.mCallback = null;
        } 
        if (this.mServerIf == 0) {
          this.mCallback = null;
          return false;
        } 
        return true;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothGattServer", "", (Throwable)remoteException);
        this.mCallback = null;
        return false;
      } 
    } 
  }
  
  public boolean removeService(BluetoothGattService paramBluetoothGattService) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("removeService() - service: ");
    stringBuilder.append(paramBluetoothGattService.getUuid());
    Log.d("BluetoothGattServer", stringBuilder.toString());
    if (this.mService == null || this.mServerIf == 0)
      return false; 
    BluetoothGattService bluetoothGattService = getService(paramBluetoothGattService.getUuid(), paramBluetoothGattService.getInstanceId(), paramBluetoothGattService.getType());
    if (bluetoothGattService == null)
      return false; 
    try {
      this.mService.removeService(this.mServerIf, paramBluetoothGattService.getInstanceId());
      this.mServices.remove(bluetoothGattService);
      return true;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGattServer", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean sendResponse(BluetoothDevice paramBluetoothDevice, int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) {
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mServerIf;
      if (i != 0)
        try {
          iBluetoothGatt.sendResponse(i, paramBluetoothDevice.getAddress(), paramInt1, paramInt2, paramInt3, paramArrayOfbyte);
          return true;
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGattServer", "", (Throwable)remoteException);
          return false;
        }  
    } 
    return false;
  }
  
  public void setPreferredPhy(BluetoothDevice paramBluetoothDevice, int paramInt1, int paramInt2, int paramInt3) {
    try {
      this.mService.serverSetPreferredPhy(this.mServerIf, paramBluetoothDevice.getAddress(), paramInt1, paramInt2, paramInt3);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGattServer", "", (Throwable)remoteException);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothGattServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */