package android.bluetooth;

import android.util.Log;
import java.util.List;

class null extends IBluetoothGattServerCallback.Stub {
  public void onCharacteristicReadRequest(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) {
    StringBuilder stringBuilder;
    BluetoothDevice bluetoothDevice = BluetoothGattServer.access$300(BluetoothGattServer.this).getRemoteDevice(paramString);
    BluetoothGattCharacteristic bluetoothGattCharacteristic = BluetoothGattServer.this.getCharacteristicByHandle(paramInt3);
    if (bluetoothGattCharacteristic == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("onCharacteristicReadRequest() no char for handle ");
      stringBuilder.append(paramInt3);
      Log.w("BluetoothGattServer", stringBuilder.toString());
      return;
    } 
    try {
      BluetoothGattServer.access$100(BluetoothGattServer.this).onCharacteristicReadRequest(bluetoothDevice, paramInt1, paramInt2, (BluetoothGattCharacteristic)stringBuilder);
    } catch (Exception exception) {
      Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
    } 
  }
  
  public void onCharacteristicWriteRequest(String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4, byte[] paramArrayOfbyte) {
    StringBuilder stringBuilder;
    BluetoothDevice bluetoothDevice = BluetoothGattServer.access$300(BluetoothGattServer.this).getRemoteDevice(paramString);
    BluetoothGattCharacteristic bluetoothGattCharacteristic = BluetoothGattServer.this.getCharacteristicByHandle(paramInt4);
    if (bluetoothGattCharacteristic == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("onCharacteristicWriteRequest() no char for handle ");
      stringBuilder.append(paramInt4);
      Log.w("BluetoothGattServer", stringBuilder.toString());
      return;
    } 
    try {
      BluetoothGattServer.access$100(BluetoothGattServer.this).onCharacteristicWriteRequest((BluetoothDevice)stringBuilder, paramInt1, bluetoothGattCharacteristic, paramBoolean1, paramBoolean2, paramInt2, paramArrayOfbyte);
    } catch (Exception exception) {
      Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
    } 
  }
  
  public void onConnectionUpdated(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onConnectionUpdated() - Device=");
    stringBuilder.append(paramString);
    stringBuilder.append(" interval=");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" latency=");
    stringBuilder.append(paramInt2);
    stringBuilder.append(" timeout=");
    stringBuilder.append(paramInt3);
    stringBuilder.append(" status=");
    stringBuilder.append(paramInt4);
    Log.d("BluetoothGattServer", stringBuilder.toString());
    BluetoothDevice bluetoothDevice = BluetoothGattServer.access$300(BluetoothGattServer.this).getRemoteDevice(paramString);
    if (bluetoothDevice == null)
      return; 
    try {
      BluetoothGattServer.access$100(BluetoothGattServer.this).onConnectionUpdated(bluetoothDevice, paramInt1, paramInt2, paramInt3, paramInt4);
    } catch (Exception exception) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Unhandled exception: ");
      stringBuilder.append(exception);
      Log.w("BluetoothGattServer", stringBuilder.toString());
    } 
  }
  
  public void onDescriptorReadRequest(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) {
    StringBuilder stringBuilder;
    BluetoothDevice bluetoothDevice = BluetoothGattServer.access$300(BluetoothGattServer.this).getRemoteDevice(paramString);
    BluetoothGattDescriptor bluetoothGattDescriptor = BluetoothGattServer.this.getDescriptorByHandle(paramInt3);
    if (bluetoothGattDescriptor == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("onDescriptorReadRequest() no desc for handle ");
      stringBuilder.append(paramInt3);
      Log.w("BluetoothGattServer", stringBuilder.toString());
      return;
    } 
    try {
      BluetoothGattServer.access$100(BluetoothGattServer.this).onDescriptorReadRequest(bluetoothDevice, paramInt1, paramInt2, (BluetoothGattDescriptor)stringBuilder);
    } catch (Exception exception) {
      Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
    } 
  }
  
  public void onDescriptorWriteRequest(String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4, byte[] paramArrayOfbyte) {
    StringBuilder stringBuilder;
    BluetoothDevice bluetoothDevice = BluetoothGattServer.access$300(BluetoothGattServer.this).getRemoteDevice(paramString);
    BluetoothGattDescriptor bluetoothGattDescriptor = BluetoothGattServer.this.getDescriptorByHandle(paramInt4);
    if (bluetoothGattDescriptor == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("onDescriptorWriteRequest() no desc for handle ");
      stringBuilder.append(paramInt4);
      Log.w("BluetoothGattServer", stringBuilder.toString());
      return;
    } 
    try {
      BluetoothGattServer.access$100(BluetoothGattServer.this).onDescriptorWriteRequest((BluetoothDevice)stringBuilder, paramInt1, bluetoothGattDescriptor, paramBoolean1, paramBoolean2, paramInt2, paramArrayOfbyte);
    } catch (Exception exception) {
      Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
    } 
  }
  
  public void onExecuteWrite(String paramString, int paramInt, boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onExecuteWrite() - device=");
    stringBuilder.append(paramString);
    stringBuilder.append(", transId=");
    stringBuilder.append(paramInt);
    stringBuilder.append("execWrite=");
    stringBuilder.append(paramBoolean);
    Log.d("BluetoothGattServer", stringBuilder.toString());
    BluetoothDevice bluetoothDevice = BluetoothGattServer.access$300(BluetoothGattServer.this).getRemoteDevice(paramString);
    if (bluetoothDevice == null)
      return; 
    try {
      BluetoothGattServer.access$100(BluetoothGattServer.this).onExecuteWrite(bluetoothDevice, paramInt, paramBoolean);
    } catch (Exception exception) {
      Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
    } 
  }
  
  public void onMtuChanged(String paramString, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onMtuChanged() - device=");
    stringBuilder.append(paramString);
    stringBuilder.append(", mtu=");
    stringBuilder.append(paramInt);
    Log.d("BluetoothGattServer", stringBuilder.toString());
    BluetoothDevice bluetoothDevice = BluetoothGattServer.access$300(BluetoothGattServer.this).getRemoteDevice(paramString);
    if (bluetoothDevice == null)
      return; 
    try {
      BluetoothGattServer.access$100(BluetoothGattServer.this).onMtuChanged(bluetoothDevice, paramInt);
    } catch (Exception exception) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Unhandled exception: ");
      stringBuilder1.append(exception);
      Log.w("BluetoothGattServer", stringBuilder1.toString());
    } 
  }
  
  public void onNotificationSent(String paramString, int paramInt) {
    BluetoothDevice bluetoothDevice = BluetoothGattServer.access$300(BluetoothGattServer.this).getRemoteDevice(paramString);
    if (bluetoothDevice == null)
      return; 
    try {
      BluetoothGattServer.access$100(BluetoothGattServer.this).onNotificationSent(bluetoothDevice, paramInt);
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unhandled exception: ");
      stringBuilder.append(exception);
      Log.w("BluetoothGattServer", stringBuilder.toString());
    } 
  }
  
  public void onPhyRead(String paramString, int paramInt1, int paramInt2, int paramInt3) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onPhyUpdate() - device=");
    stringBuilder.append(paramString);
    stringBuilder.append(", txPHy=");
    stringBuilder.append(paramInt1);
    stringBuilder.append(", rxPHy=");
    stringBuilder.append(paramInt2);
    Log.d("BluetoothGattServer", stringBuilder.toString());
    BluetoothDevice bluetoothDevice = BluetoothGattServer.access$300(BluetoothGattServer.this).getRemoteDevice(paramString);
    if (bluetoothDevice == null)
      return; 
    try {
      BluetoothGattServer.access$100(BluetoothGattServer.this).onPhyRead(bluetoothDevice, paramInt1, paramInt2, paramInt3);
    } catch (Exception exception) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Unhandled exception: ");
      stringBuilder1.append(exception);
      Log.w("BluetoothGattServer", stringBuilder1.toString());
    } 
  }
  
  public void onPhyUpdate(String paramString, int paramInt1, int paramInt2, int paramInt3) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onPhyUpdate() - device=");
    stringBuilder.append(paramString);
    stringBuilder.append(", txPHy=");
    stringBuilder.append(paramInt1);
    stringBuilder.append(", rxPHy=");
    stringBuilder.append(paramInt2);
    Log.d("BluetoothGattServer", stringBuilder.toString());
    BluetoothDevice bluetoothDevice = BluetoothGattServer.access$300(BluetoothGattServer.this).getRemoteDevice(paramString);
    if (bluetoothDevice == null)
      return; 
    try {
      BluetoothGattServer.access$100(BluetoothGattServer.this).onPhyUpdate(bluetoothDevice, paramInt1, paramInt2, paramInt3);
    } catch (Exception exception) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Unhandled exception: ");
      stringBuilder.append(exception);
      Log.w("BluetoothGattServer", stringBuilder.toString());
    } 
  }
  
  public void onServerConnectionState(int paramInt1, int paramInt2, boolean paramBoolean, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onServerConnectionState() - status=");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" serverIf=");
    stringBuilder.append(paramInt2);
    stringBuilder.append(" device=");
    stringBuilder.append(paramString);
    Log.d("BluetoothGattServer", stringBuilder.toString());
    try {
      BluetoothGattServerCallback bluetoothGattServerCallback = BluetoothGattServer.access$100(BluetoothGattServer.this);
      BluetoothDevice bluetoothDevice = BluetoothGattServer.access$300(BluetoothGattServer.this).getRemoteDevice(paramString);
      if (paramBoolean) {
        paramInt2 = 2;
      } else {
        paramInt2 = 0;
      } 
      bluetoothGattServerCallback.onConnectionStateChange(bluetoothDevice, paramInt1, paramInt2);
    } catch (Exception exception) {
      Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
    } 
  }
  
  public void onServerRegistered(int paramInt1, int paramInt2) {
    null = new StringBuilder();
    null.append("onServerRegistered() - status=");
    null.append(paramInt1);
    null.append(" serverIf=");
    null.append(paramInt2);
    Log.d("BluetoothGattServer", null.toString());
    synchronized (BluetoothGattServer.access$000(BluetoothGattServer.this)) {
      if (BluetoothGattServer.access$100(BluetoothGattServer.this) != null) {
        BluetoothGattServer.access$202(BluetoothGattServer.this, paramInt2);
        BluetoothGattServer.access$000(BluetoothGattServer.this).notify();
      } else {
        Log.e("BluetoothGattServer", "onServerRegistered: mCallback is null");
      } 
      return;
    } 
  }
  
  public void onServiceAdded(int paramInt, BluetoothGattService paramBluetoothGattService) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onServiceAdded() - handle=");
    stringBuilder.append(paramBluetoothGattService.getInstanceId());
    stringBuilder.append(" uuid=");
    stringBuilder.append(paramBluetoothGattService.getUuid());
    stringBuilder.append(" status=");
    stringBuilder.append(paramInt);
    Log.d("BluetoothGattServer", stringBuilder.toString());
    if (BluetoothGattServer.access$400(BluetoothGattServer.this) == null)
      return; 
    BluetoothGattService bluetoothGattService = BluetoothGattServer.access$400(BluetoothGattServer.this);
    BluetoothGattServer.access$402(BluetoothGattServer.this, null);
    bluetoothGattService.setInstanceId(paramBluetoothGattService.getInstanceId());
    List<BluetoothGattCharacteristic> list2 = bluetoothGattService.getCharacteristics();
    List<BluetoothGattCharacteristic> list1 = paramBluetoothGattService.getCharacteristics();
    for (byte b = 0; b < list1.size(); b++) {
      BluetoothGattCharacteristic bluetoothGattCharacteristic1 = list2.get(b);
      BluetoothGattCharacteristic bluetoothGattCharacteristic2 = list1.get(b);
      bluetoothGattCharacteristic1.setInstanceId(bluetoothGattCharacteristic2.getInstanceId());
      List<BluetoothGattDescriptor> list3 = bluetoothGattCharacteristic1.getDescriptors();
      List<BluetoothGattDescriptor> list4 = bluetoothGattCharacteristic2.getDescriptors();
      for (byte b1 = 0; b1 < list4.size(); b1++)
        ((BluetoothGattDescriptor)list3.get(b1)).setInstanceId(((BluetoothGattDescriptor)list4.get(b1)).getInstanceId()); 
    } 
    BluetoothGattServer.access$500(BluetoothGattServer.this).add(bluetoothGattService);
    try {
      BluetoothGattServer.access$100(BluetoothGattServer.this).onServiceAdded(paramInt, bluetoothGattService);
    } catch (Exception exception) {
      Log.w("BluetoothGattServer", "Unhandled exception in callback", exception);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothGattServer$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */