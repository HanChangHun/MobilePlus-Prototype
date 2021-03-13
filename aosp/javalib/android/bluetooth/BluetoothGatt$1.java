package android.bluetooth;

import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class null extends IBluetoothGattCallback.Stub {
  public void onCharacteristicRead(String paramString, final int status, int paramInt2, final byte[] value) {
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    synchronized (BluetoothGatt.access$1100(BluetoothGatt.this)) {
      BluetoothGatt.access$1202(BluetoothGatt.this, Boolean.valueOf(false));
      if (status == 5 || status == 15) {
        int i = BluetoothGatt.access$1400(BluetoothGatt.this);
        byte b = 2;
        if (i != 2)
          try {
            if (BluetoothGatt.access$1400(BluetoothGatt.this) == 0)
              b = 1; 
            BluetoothGatt.access$1000(BluetoothGatt.this).readCharacteristic(BluetoothGatt.access$000(BluetoothGatt.this), paramString, paramInt2, b);
            BluetoothGatt.access$1408(BluetoothGatt.this);
            return;
          } catch (RemoteException remoteException) {
            Log.e("BluetoothGatt", "", (Throwable)remoteException);
          }  
      } 
      BluetoothGatt.access$1402(BluetoothGatt.this, 0);
      BluetoothGatt bluetoothGatt = BluetoothGatt.this;
      final BluetoothGattCharacteristic characteristic = bluetoothGatt.getCharacteristicById(BluetoothGatt.access$500(bluetoothGatt), paramInt2);
      if (bluetoothGattCharacteristic == null) {
        Log.w("BluetoothGatt", "onCharacteristicRead() failed to find characteristic!");
        return;
      } 
      BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
            public void run() {
              BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
              if (bluetoothGattCallback != null) {
                if (status == 0)
                  characteristic.setValue(value); 
                bluetoothGattCallback.onCharacteristicRead(BluetoothGatt.this, characteristic, status);
              } 
            }
          });
      return;
    } 
  }
  
  public void onCharacteristicWrite(String paramString, final int status, int paramInt2) {
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    synchronized (BluetoothGatt.access$1100(BluetoothGatt.this)) {
      BluetoothGatt.access$1202(BluetoothGatt.this, Boolean.valueOf(false));
      null = BluetoothGatt.this;
      null = null.getCharacteristicById(BluetoothGatt.access$500((BluetoothGatt)null), paramInt2);
      if (null == null)
        return; 
      if (status == 5 || status == 15) {
        int i = BluetoothGatt.access$1400(BluetoothGatt.this);
        byte b = 2;
        if (i != 2)
          try {
            if (BluetoothGatt.access$1400(BluetoothGatt.this) == 0)
              b = 1; 
            BluetoothGatt.access$1000(BluetoothGatt.this).writeCharacteristic(BluetoothGatt.access$000(BluetoothGatt.this), paramString, paramInt2, null.getWriteType(), b, null.getValue());
            BluetoothGatt.access$1408(BluetoothGatt.this);
            return;
          } catch (RemoteException remoteException) {
            Log.e("BluetoothGatt", "", (Throwable)remoteException);
          }  
      } 
      BluetoothGatt.access$1402(BluetoothGatt.this, 0);
      BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
            public void run() {
              BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
              if (bluetoothGattCallback != null)
                bluetoothGattCallback.onCharacteristicWrite(BluetoothGatt.this, characteristic, status); 
            }
          });
      return;
    } 
  }
  
  public void onClientConnectionState(int paramInt1, int paramInt2, boolean paramBoolean, String paramString) {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #5
    //   9: aload #5
    //   11: ldc 'onClientConnectionState() - status='
    //   13: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: pop
    //   17: aload #5
    //   19: iload_1
    //   20: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload #5
    //   26: ldc ' clientIf='
    //   28: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload #5
    //   34: iload_2
    //   35: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload #5
    //   41: ldc ' device='
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: aload #5
    //   49: aload #4
    //   51: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: pop
    //   55: ldc 'BluetoothGatt'
    //   57: aload #5
    //   59: invokevirtual toString : ()Ljava/lang/String;
    //   62: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   65: pop
    //   66: aload #4
    //   68: aload_0
    //   69: getfield this$0 : Landroid/bluetooth/BluetoothGatt;
    //   72: invokestatic access$500 : (Landroid/bluetooth/BluetoothGatt;)Landroid/bluetooth/BluetoothDevice;
    //   75: invokevirtual getAddress : ()Ljava/lang/String;
    //   78: invokevirtual equals : (Ljava/lang/Object;)Z
    //   81: ifne -> 85
    //   84: return
    //   85: iload_3
    //   86: ifeq -> 94
    //   89: iconst_2
    //   90: istore_2
    //   91: goto -> 96
    //   94: iconst_0
    //   95: istore_2
    //   96: aload_0
    //   97: getfield this$0 : Landroid/bluetooth/BluetoothGatt;
    //   100: new android/bluetooth/BluetoothGatt$1$4
    //   103: dup
    //   104: aload_0
    //   105: iload_1
    //   106: iload_2
    //   107: invokespecial <init> : (Landroid/bluetooth/BluetoothGatt$1;II)V
    //   110: invokestatic access$200 : (Landroid/bluetooth/BluetoothGatt;Ljava/lang/Runnable;)V
    //   113: aload_0
    //   114: getfield this$0 : Landroid/bluetooth/BluetoothGatt;
    //   117: invokestatic access$300 : (Landroid/bluetooth/BluetoothGatt;)Ljava/lang/Object;
    //   120: astore #4
    //   122: aload #4
    //   124: monitorenter
    //   125: iload_3
    //   126: ifeq -> 141
    //   129: aload_0
    //   130: getfield this$0 : Landroid/bluetooth/BluetoothGatt;
    //   133: iconst_2
    //   134: invokestatic access$402 : (Landroid/bluetooth/BluetoothGatt;I)I
    //   137: pop
    //   138: goto -> 150
    //   141: aload_0
    //   142: getfield this$0 : Landroid/bluetooth/BluetoothGatt;
    //   145: iconst_0
    //   146: invokestatic access$402 : (Landroid/bluetooth/BluetoothGatt;I)I
    //   149: pop
    //   150: aload #4
    //   152: monitorexit
    //   153: aload_0
    //   154: getfield this$0 : Landroid/bluetooth/BluetoothGatt;
    //   157: invokestatic access$1100 : (Landroid/bluetooth/BluetoothGatt;)Ljava/lang/Object;
    //   160: astore #4
    //   162: aload #4
    //   164: monitorenter
    //   165: aload_0
    //   166: getfield this$0 : Landroid/bluetooth/BluetoothGatt;
    //   169: iconst_0
    //   170: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   173: invokestatic access$1202 : (Landroid/bluetooth/BluetoothGatt;Ljava/lang/Boolean;)Ljava/lang/Boolean;
    //   176: pop
    //   177: aload #4
    //   179: monitorexit
    //   180: return
    //   181: astore #5
    //   183: aload #4
    //   185: monitorexit
    //   186: aload #5
    //   188: athrow
    //   189: astore #5
    //   191: aload #4
    //   193: monitorexit
    //   194: aload #5
    //   196: athrow
    // Exception table:
    //   from	to	target	type
    //   129	138	189	finally
    //   141	150	189	finally
    //   150	153	189	finally
    //   165	180	181	finally
    //   183	186	181	finally
    //   191	194	189	finally
  }
  
  public void onClientRegistered(int paramInt1, int paramInt2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onClientRegistered() - status=");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" clientIf=");
    stringBuilder.append(paramInt2);
    Log.d("BluetoothGatt", stringBuilder.toString());
    BluetoothGatt.access$002(BluetoothGatt.this, paramInt2);
    boolean bool = false;
    if (paramInt1 != 0) {
      BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
            public void run() {
              BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
              if (bluetoothGattCallback != null)
                bluetoothGattCallback.onConnectionStateChange(BluetoothGatt.this, 257, 0); 
            }
          });
      synchronized (BluetoothGatt.access$300(BluetoothGatt.this)) {
        BluetoothGatt.access$402(BluetoothGatt.this, 0);
        return;
      } 
    } 
    try {
      IBluetoothGatt iBluetoothGatt = BluetoothGatt.access$1000(BluetoothGatt.this);
      paramInt1 = BluetoothGatt.access$000(BluetoothGatt.this);
      String str = BluetoothGatt.access$500(BluetoothGatt.this).getAddress();
      if (!BluetoothGatt.access$600(BluetoothGatt.this))
        bool = true; 
      iBluetoothGatt.clientConnect(paramInt1, str, bool, BluetoothGatt.access$700(BluetoothGatt.this), BluetoothGatt.access$800(BluetoothGatt.this), BluetoothGatt.access$900(BluetoothGatt.this));
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGatt", "", (Throwable)remoteException);
    } 
  }
  
  public void onConfigureMTU(String paramString, final int mtu, final int status) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onConfigureMTU() - Device=");
    stringBuilder.append(paramString);
    stringBuilder.append(" mtu=");
    stringBuilder.append(mtu);
    stringBuilder.append(" status=");
    stringBuilder.append(status);
    Log.d("BluetoothGatt", stringBuilder.toString());
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
          public void run() {
            BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
            if (bluetoothGattCallback != null)
              bluetoothGattCallback.onMtuChanged(BluetoothGatt.this, mtu, status); 
          }
        });
  }
  
  public void onConnectionUpdated(String paramString, final int interval, final int latency, final int timeout, final int status) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onConnectionUpdated() - Device=");
    stringBuilder.append(paramString);
    stringBuilder.append(" interval=");
    stringBuilder.append(interval);
    stringBuilder.append(" latency=");
    stringBuilder.append(latency);
    stringBuilder.append(" timeout=");
    stringBuilder.append(timeout);
    stringBuilder.append(" status=");
    stringBuilder.append(status);
    Log.d("BluetoothGatt", stringBuilder.toString());
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
          public void run() {
            BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
            if (bluetoothGattCallback != null)
              bluetoothGattCallback.onConnectionUpdated(BluetoothGatt.this, interval, latency, timeout, status); 
          }
        });
  }
  
  public void onDescriptorRead(String paramString, final int status, int paramInt2, final byte[] value) {
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    synchronized (BluetoothGatt.access$1100(BluetoothGatt.this)) {
      BluetoothGatt.access$1202(BluetoothGatt.this, Boolean.valueOf(false));
      null = BluetoothGatt.this;
      null = null.getDescriptorById(BluetoothGatt.access$500((BluetoothGatt)null), paramInt2);
      if (null == null)
        return; 
      if (status == 5 || status == 15) {
        int i = BluetoothGatt.access$1400(BluetoothGatt.this);
        byte b = 2;
        if (i != 2)
          try {
            if (BluetoothGatt.access$1400(BluetoothGatt.this) == 0)
              b = 1; 
            BluetoothGatt.access$1000(BluetoothGatt.this).readDescriptor(BluetoothGatt.access$000(BluetoothGatt.this), paramString, paramInt2, b);
            BluetoothGatt.access$1408(BluetoothGatt.this);
            return;
          } catch (RemoteException remoteException) {
            Log.e("BluetoothGatt", "", (Throwable)remoteException);
          }  
      } 
      BluetoothGatt.access$1402(BluetoothGatt.this, 0);
      BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
            public void run() {
              BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
              if (bluetoothGattCallback != null) {
                if (status == 0)
                  descriptor.setValue(value); 
                bluetoothGattCallback.onDescriptorRead(BluetoothGatt.this, descriptor, status);
              } 
            }
          });
      return;
    } 
  }
  
  public void onDescriptorWrite(String paramString, final int status, int paramInt2) {
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    synchronized (BluetoothGatt.access$1100(BluetoothGatt.this)) {
      BluetoothGatt.access$1202(BluetoothGatt.this, Boolean.valueOf(false));
      null = BluetoothGatt.this;
      null = null.getDescriptorById(BluetoothGatt.access$500((BluetoothGatt)null), paramInt2);
      if (null == null)
        return; 
      if (status == 5 || status == 15) {
        int i = BluetoothGatt.access$1400(BluetoothGatt.this);
        byte b = 2;
        if (i != 2)
          try {
            if (BluetoothGatt.access$1400(BluetoothGatt.this) == 0)
              b = 1; 
            BluetoothGatt.access$1000(BluetoothGatt.this).writeDescriptor(BluetoothGatt.access$000(BluetoothGatt.this), paramString, paramInt2, b, null.getValue());
            BluetoothGatt.access$1408(BluetoothGatt.this);
            return;
          } catch (RemoteException remoteException) {
            Log.e("BluetoothGatt", "", (Throwable)remoteException);
          }  
      } 
      BluetoothGatt.access$1402(BluetoothGatt.this, 0);
      BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
            public void run() {
              BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
              if (bluetoothGattCallback != null)
                bluetoothGattCallback.onDescriptorWrite(BluetoothGatt.this, descriptor, status); 
            }
          });
      return;
    } 
  }
  
  public void onExecuteWrite(String paramString, final int status) {
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    synchronized (BluetoothGatt.access$1100(BluetoothGatt.this)) {
      BluetoothGatt.access$1202(BluetoothGatt.this, Boolean.valueOf(false));
      BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
            public void run() {
              BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
              if (bluetoothGattCallback != null)
                bluetoothGattCallback.onReliableWriteCompleted(BluetoothGatt.this, status); 
            }
          });
      return;
    } 
  }
  
  public void onNotify(String paramString, int paramInt, final byte[] value) {
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    BluetoothGatt bluetoothGatt = BluetoothGatt.this;
    final BluetoothGattCharacteristic characteristic = bluetoothGatt.getCharacteristicById(BluetoothGatt.access$500(bluetoothGatt), paramInt);
    if (bluetoothGattCharacteristic == null)
      return; 
    BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
          public void run() {
            BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
            if (bluetoothGattCallback != null) {
              characteristic.setValue(value);
              bluetoothGattCallback.onCharacteristicChanged(BluetoothGatt.this, characteristic);
            } 
          }
        });
  }
  
  public void onPhyRead(String paramString, final int txPhy, final int rxPhy, final int status) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onPhyRead() - status=");
    stringBuilder.append(status);
    stringBuilder.append(" address=");
    stringBuilder.append(paramString);
    stringBuilder.append(" txPhy=");
    stringBuilder.append(txPhy);
    stringBuilder.append(" rxPhy=");
    stringBuilder.append(rxPhy);
    Log.d("BluetoothGatt", stringBuilder.toString());
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
          public void run() {
            BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
            if (bluetoothGattCallback != null)
              bluetoothGattCallback.onPhyRead(BluetoothGatt.this, txPhy, rxPhy, status); 
          }
        });
  }
  
  public void onPhyUpdate(String paramString, final int txPhy, final int rxPhy, final int status) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onPhyUpdate() - status=");
    stringBuilder.append(status);
    stringBuilder.append(" address=");
    stringBuilder.append(paramString);
    stringBuilder.append(" txPhy=");
    stringBuilder.append(txPhy);
    stringBuilder.append(" rxPhy=");
    stringBuilder.append(rxPhy);
    Log.d("BluetoothGatt", stringBuilder.toString());
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
          public void run() {
            BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
            if (bluetoothGattCallback != null)
              bluetoothGattCallback.onPhyUpdate(BluetoothGatt.this, txPhy, rxPhy, status); 
          }
        });
  }
  
  public void onReadRemoteRssi(String paramString, final int rssi, final int status) {
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
          public void run() {
            BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
            if (bluetoothGattCallback != null)
              bluetoothGattCallback.onReadRemoteRssi(BluetoothGatt.this, rssi, status); 
          }
        });
  }
  
  public void onSearchComplete(String paramString, List<BluetoothGattService> paramList, final int status) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onSearchComplete() = Device=");
    stringBuilder.append(paramString);
    stringBuilder.append(" Status=");
    stringBuilder.append(status);
    Log.d("BluetoothGatt", stringBuilder.toString());
    if (!paramString.equals(BluetoothGatt.access$500(BluetoothGatt.this).getAddress()))
      return; 
    Iterator<BluetoothGattService> iterator = paramList.iterator();
    while (iterator.hasNext())
      ((BluetoothGattService)iterator.next()).setDevice(BluetoothGatt.access$500(BluetoothGatt.this)); 
    BluetoothGatt.access$1300(BluetoothGatt.this).addAll(paramList);
    for (BluetoothGattService bluetoothGattService : BluetoothGatt.access$1300(BluetoothGatt.this)) {
      ArrayList<BluetoothGattService> arrayList = new ArrayList<>(bluetoothGattService.getIncludedServices());
      bluetoothGattService.getIncludedServices().clear();
      for (BluetoothGattService bluetoothGattService1 : arrayList) {
        BluetoothGatt bluetoothGatt = BluetoothGatt.this;
        BluetoothGattService bluetoothGattService2 = bluetoothGatt.getService(BluetoothGatt.access$500(bluetoothGatt), bluetoothGattService1.getUuid(), bluetoothGattService1.getInstanceId());
        if (bluetoothGattService2 != null) {
          bluetoothGattService.addIncludedService(bluetoothGattService2);
          continue;
        } 
        Log.e("BluetoothGatt", "Broken GATT database: can't find included service.");
      } 
    } 
    BluetoothGatt.access$200(BluetoothGatt.this, new Runnable() {
          public void run() {
            BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(BluetoothGatt.this);
            if (bluetoothGattCallback != null)
              bluetoothGattCallback.onServicesDiscovered(BluetoothGatt.this, status); 
          }
        });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothGatt$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */