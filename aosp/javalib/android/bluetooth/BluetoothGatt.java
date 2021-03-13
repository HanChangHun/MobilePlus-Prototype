package android.bluetooth;

import android.os.Handler;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class BluetoothGatt implements BluetoothProfile {
  static final int AUTHENTICATION_MITM = 2;
  
  static final int AUTHENTICATION_NONE = 0;
  
  static final int AUTHENTICATION_NO_MITM = 1;
  
  private static final int AUTH_RETRY_STATE_IDLE = 0;
  
  private static final int AUTH_RETRY_STATE_MITM = 2;
  
  private static final int AUTH_RETRY_STATE_NO_MITM = 1;
  
  public static final int CONNECTION_PRIORITY_BALANCED = 0;
  
  public static final int CONNECTION_PRIORITY_HIGH = 1;
  
  public static final int CONNECTION_PRIORITY_LOW_POWER = 2;
  
  private static final int CONN_STATE_CLOSED = 4;
  
  private static final int CONN_STATE_CONNECTED = 2;
  
  private static final int CONN_STATE_CONNECTING = 1;
  
  private static final int CONN_STATE_DISCONNECTING = 3;
  
  private static final int CONN_STATE_IDLE = 0;
  
  private static final boolean DBG = true;
  
  public static final int GATT_CONNECTION_CONGESTED = 143;
  
  public static final int GATT_FAILURE = 257;
  
  public static final int GATT_INSUFFICIENT_AUTHENTICATION = 5;
  
  public static final int GATT_INSUFFICIENT_ENCRYPTION = 15;
  
  public static final int GATT_INVALID_ATTRIBUTE_LENGTH = 13;
  
  public static final int GATT_INVALID_OFFSET = 7;
  
  public static final int GATT_READ_NOT_PERMITTED = 2;
  
  public static final int GATT_REQUEST_NOT_SUPPORTED = 6;
  
  public static final int GATT_SUCCESS = 0;
  
  public static final int GATT_WRITE_NOT_PERMITTED = 3;
  
  private static final String TAG = "BluetoothGatt";
  
  private static final boolean VDBG = false;
  
  private int mAuthRetryState;
  
  private boolean mAutoConnect;
  
  private final IBluetoothGattCallback mBluetoothGattCallback = new IBluetoothGattCallback.Stub() {
      public void onCharacteristicRead(String param1String, final int status, int param1Int2, final byte[] value) {
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        synchronized (BluetoothGatt.this.mDeviceBusyLock) {
          BluetoothGatt.access$1202(BluetoothGatt.this, Boolean.valueOf(false));
          if (status == 5 || status == 15) {
            int i = BluetoothGatt.this.mAuthRetryState;
            byte b = 2;
            if (i != 2)
              try {
                if (BluetoothGatt.this.mAuthRetryState == 0)
                  b = 1; 
                BluetoothGatt.this.mService.readCharacteristic(BluetoothGatt.this.mClientIf, param1String, param1Int2, b);
                BluetoothGatt.access$1408(BluetoothGatt.this);
                return;
              } catch (RemoteException remoteException) {
                Log.e("BluetoothGatt", "", (Throwable)remoteException);
              }  
          } 
          BluetoothGatt.access$1402(BluetoothGatt.this, 0);
          BluetoothGatt bluetoothGatt = BluetoothGatt.this;
          final BluetoothGattCharacteristic characteristic = bluetoothGatt.getCharacteristicById(bluetoothGatt.mDevice, param1Int2);
          if (bluetoothGattCharacteristic == null) {
            Log.w("BluetoothGatt", "onCharacteristicRead() failed to find characteristic!");
            return;
          } 
          BluetoothGatt.this.runOrQueueCallback(new Runnable() {
                public void run() {
                  BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
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
      
      public void onCharacteristicWrite(String param1String, final int status, int param1Int2) {
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        synchronized (BluetoothGatt.this.mDeviceBusyLock) {
          BluetoothGatt.access$1202(BluetoothGatt.this, Boolean.valueOf(false));
          null = BluetoothGatt.this;
          null = null.getCharacteristicById(((BluetoothGatt)null).mDevice, param1Int2);
          if (null == null)
            return; 
          if (status == 5 || status == 15) {
            int i = BluetoothGatt.this.mAuthRetryState;
            byte b = 2;
            if (i != 2)
              try {
                if (BluetoothGatt.this.mAuthRetryState == 0)
                  b = 1; 
                BluetoothGatt.this.mService.writeCharacteristic(BluetoothGatt.this.mClientIf, param1String, param1Int2, null.getWriteType(), b, null.getValue());
                BluetoothGatt.access$1408(BluetoothGatt.this);
                return;
              } catch (RemoteException remoteException) {
                Log.e("BluetoothGatt", "", (Throwable)remoteException);
              }  
          } 
          BluetoothGatt.access$1402(BluetoothGatt.this, 0);
          BluetoothGatt.this.runOrQueueCallback(new Runnable() {
                public void run() {
                  BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
                  if (bluetoothGattCallback != null)
                    bluetoothGattCallback.onCharacteristicWrite(BluetoothGatt.this, characteristic, status); 
                }
              });
          return;
        } 
      }
      
      public void onClientConnectionState(int param1Int1, int param1Int2, boolean param1Boolean, String param1String) {
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
      
      public void onClientRegistered(int param1Int1, int param1Int2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onClientRegistered() - status=");
        stringBuilder.append(param1Int1);
        stringBuilder.append(" clientIf=");
        stringBuilder.append(param1Int2);
        Log.d("BluetoothGatt", stringBuilder.toString());
        BluetoothGatt.access$002(BluetoothGatt.this, param1Int2);
        boolean bool = false;
        if (param1Int1 != 0) {
          BluetoothGatt.this.runOrQueueCallback(new Runnable() {
                public void run() {
                  BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
                  if (bluetoothGattCallback != null)
                    bluetoothGattCallback.onConnectionStateChange(BluetoothGatt.this, 257, 0); 
                }
              });
          synchronized (BluetoothGatt.this.mStateLock) {
            BluetoothGatt.access$402(BluetoothGatt.this, 0);
            return;
          } 
        } 
        try {
          IBluetoothGatt iBluetoothGatt = BluetoothGatt.this.mService;
          param1Int1 = BluetoothGatt.this.mClientIf;
          String str = BluetoothGatt.this.mDevice.getAddress();
          if (!BluetoothGatt.this.mAutoConnect)
            bool = true; 
          iBluetoothGatt.clientConnect(param1Int1, str, bool, BluetoothGatt.this.mTransport, BluetoothGatt.this.mOpportunistic, BluetoothGatt.this.mPhy);
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGatt", "", (Throwable)remoteException);
        } 
      }
      
      public void onConfigureMTU(String param1String, final int mtu, final int status) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onConfigureMTU() - Device=");
        stringBuilder.append(param1String);
        stringBuilder.append(" mtu=");
        stringBuilder.append(mtu);
        stringBuilder.append(" status=");
        stringBuilder.append(status);
        Log.d("BluetoothGatt", stringBuilder.toString());
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        BluetoothGatt.this.runOrQueueCallback(new Runnable() {
              public void run() {
                BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
                if (bluetoothGattCallback != null)
                  bluetoothGattCallback.onMtuChanged(BluetoothGatt.this, mtu, status); 
              }
            });
      }
      
      public void onConnectionUpdated(String param1String, final int interval, final int latency, final int timeout, final int status) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onConnectionUpdated() - Device=");
        stringBuilder.append(param1String);
        stringBuilder.append(" interval=");
        stringBuilder.append(interval);
        stringBuilder.append(" latency=");
        stringBuilder.append(latency);
        stringBuilder.append(" timeout=");
        stringBuilder.append(timeout);
        stringBuilder.append(" status=");
        stringBuilder.append(status);
        Log.d("BluetoothGatt", stringBuilder.toString());
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        BluetoothGatt.this.runOrQueueCallback(new Runnable() {
              public void run() {
                BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
                if (bluetoothGattCallback != null)
                  bluetoothGattCallback.onConnectionUpdated(BluetoothGatt.this, interval, latency, timeout, status); 
              }
            });
      }
      
      public void onDescriptorRead(String param1String, final int status, int param1Int2, final byte[] value) {
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        synchronized (BluetoothGatt.this.mDeviceBusyLock) {
          BluetoothGatt.access$1202(BluetoothGatt.this, Boolean.valueOf(false));
          null = BluetoothGatt.this;
          null = null.getDescriptorById(((BluetoothGatt)null).mDevice, param1Int2);
          if (null == null)
            return; 
          if (status == 5 || status == 15) {
            int i = BluetoothGatt.this.mAuthRetryState;
            byte b = 2;
            if (i != 2)
              try {
                if (BluetoothGatt.this.mAuthRetryState == 0)
                  b = 1; 
                BluetoothGatt.this.mService.readDescriptor(BluetoothGatt.this.mClientIf, param1String, param1Int2, b);
                BluetoothGatt.access$1408(BluetoothGatt.this);
                return;
              } catch (RemoteException remoteException) {
                Log.e("BluetoothGatt", "", (Throwable)remoteException);
              }  
          } 
          BluetoothGatt.access$1402(BluetoothGatt.this, 0);
          BluetoothGatt.this.runOrQueueCallback(new Runnable() {
                public void run() {
                  BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
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
      
      public void onDescriptorWrite(String param1String, final int status, int param1Int2) {
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        synchronized (BluetoothGatt.this.mDeviceBusyLock) {
          BluetoothGatt.access$1202(BluetoothGatt.this, Boolean.valueOf(false));
          null = BluetoothGatt.this;
          null = null.getDescriptorById(((BluetoothGatt)null).mDevice, param1Int2);
          if (null == null)
            return; 
          if (status == 5 || status == 15) {
            int i = BluetoothGatt.this.mAuthRetryState;
            byte b = 2;
            if (i != 2)
              try {
                if (BluetoothGatt.this.mAuthRetryState == 0)
                  b = 1; 
                BluetoothGatt.this.mService.writeDescriptor(BluetoothGatt.this.mClientIf, param1String, param1Int2, b, null.getValue());
                BluetoothGatt.access$1408(BluetoothGatt.this);
                return;
              } catch (RemoteException remoteException) {
                Log.e("BluetoothGatt", "", (Throwable)remoteException);
              }  
          } 
          BluetoothGatt.access$1402(BluetoothGatt.this, 0);
          BluetoothGatt.this.runOrQueueCallback(new Runnable() {
                public void run() {
                  BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
                  if (bluetoothGattCallback != null)
                    bluetoothGattCallback.onDescriptorWrite(BluetoothGatt.this, descriptor, status); 
                }
              });
          return;
        } 
      }
      
      public void onExecuteWrite(String param1String, final int status) {
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        synchronized (BluetoothGatt.this.mDeviceBusyLock) {
          BluetoothGatt.access$1202(BluetoothGatt.this, Boolean.valueOf(false));
          BluetoothGatt.this.runOrQueueCallback(new Runnable() {
                public void run() {
                  BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
                  if (bluetoothGattCallback != null)
                    bluetoothGattCallback.onReliableWriteCompleted(BluetoothGatt.this, status); 
                }
              });
          return;
        } 
      }
      
      public void onNotify(String param1String, int param1Int, final byte[] value) {
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        BluetoothGatt bluetoothGatt = BluetoothGatt.this;
        final BluetoothGattCharacteristic characteristic = bluetoothGatt.getCharacteristicById(bluetoothGatt.mDevice, param1Int);
        if (bluetoothGattCharacteristic == null)
          return; 
        BluetoothGatt.this.runOrQueueCallback(new Runnable() {
              public void run() {
                BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
                if (bluetoothGattCallback != null) {
                  characteristic.setValue(value);
                  bluetoothGattCallback.onCharacteristicChanged(BluetoothGatt.this, characteristic);
                } 
              }
            });
      }
      
      public void onPhyRead(String param1String, final int txPhy, final int rxPhy, final int status) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onPhyRead() - status=");
        stringBuilder.append(status);
        stringBuilder.append(" address=");
        stringBuilder.append(param1String);
        stringBuilder.append(" txPhy=");
        stringBuilder.append(txPhy);
        stringBuilder.append(" rxPhy=");
        stringBuilder.append(rxPhy);
        Log.d("BluetoothGatt", stringBuilder.toString());
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        BluetoothGatt.this.runOrQueueCallback(new Runnable() {
              public void run() {
                BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
                if (bluetoothGattCallback != null)
                  bluetoothGattCallback.onPhyRead(BluetoothGatt.this, txPhy, rxPhy, status); 
              }
            });
      }
      
      public void onPhyUpdate(String param1String, final int txPhy, final int rxPhy, final int status) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onPhyUpdate() - status=");
        stringBuilder.append(status);
        stringBuilder.append(" address=");
        stringBuilder.append(param1String);
        stringBuilder.append(" txPhy=");
        stringBuilder.append(txPhy);
        stringBuilder.append(" rxPhy=");
        stringBuilder.append(rxPhy);
        Log.d("BluetoothGatt", stringBuilder.toString());
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        BluetoothGatt.this.runOrQueueCallback(new Runnable() {
              public void run() {
                BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
                if (bluetoothGattCallback != null)
                  bluetoothGattCallback.onPhyUpdate(BluetoothGatt.this, txPhy, rxPhy, status); 
              }
            });
      }
      
      public void onReadRemoteRssi(String param1String, final int rssi, final int status) {
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        BluetoothGatt.this.runOrQueueCallback(new Runnable() {
              public void run() {
                BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
                if (bluetoothGattCallback != null)
                  bluetoothGattCallback.onReadRemoteRssi(BluetoothGatt.this, rssi, status); 
              }
            });
      }
      
      public void onSearchComplete(String param1String, List<BluetoothGattService> param1List, final int status) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onSearchComplete() = Device=");
        stringBuilder.append(param1String);
        stringBuilder.append(" Status=");
        stringBuilder.append(status);
        Log.d("BluetoothGatt", stringBuilder.toString());
        if (!param1String.equals(BluetoothGatt.this.mDevice.getAddress()))
          return; 
        Iterator<BluetoothGattService> iterator = param1List.iterator();
        while (iterator.hasNext())
          ((BluetoothGattService)iterator.next()).setDevice(BluetoothGatt.this.mDevice); 
        BluetoothGatt.this.mServices.addAll(param1List);
        for (BluetoothGattService bluetoothGattService : BluetoothGatt.this.mServices) {
          ArrayList<BluetoothGattService> arrayList = new ArrayList<>(bluetoothGattService.getIncludedServices());
          bluetoothGattService.getIncludedServices().clear();
          for (BluetoothGattService bluetoothGattService1 : arrayList) {
            BluetoothGatt bluetoothGatt = BluetoothGatt.this;
            BluetoothGattService bluetoothGattService2 = bluetoothGatt.getService(bluetoothGatt.mDevice, bluetoothGattService1.getUuid(), bluetoothGattService1.getInstanceId());
            if (bluetoothGattService2 != null) {
              bluetoothGattService.addIncludedService(bluetoothGattService2);
              continue;
            } 
            Log.e("BluetoothGatt", "Broken GATT database: can't find included service.");
          } 
        } 
        BluetoothGatt.this.runOrQueueCallback(new Runnable() {
              public void run() {
                BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.this.mCallback;
                if (bluetoothGattCallback != null)
                  bluetoothGattCallback.onServicesDiscovered(BluetoothGatt.this, status); 
              }
            });
      }
    };
  
  private volatile BluetoothGattCallback mCallback;
  
  private int mClientIf;
  
  private int mConnState;
  
  private BluetoothDevice mDevice;
  
  private Boolean mDeviceBusy = Boolean.valueOf(false);
  
  private final Object mDeviceBusyLock = new Object();
  
  private Handler mHandler;
  
  private boolean mOpportunistic;
  
  private int mPhy;
  
  private IBluetoothGatt mService;
  
  private List<BluetoothGattService> mServices;
  
  private final Object mStateLock = new Object();
  
  private int mTransport;
  
  BluetoothGatt(IBluetoothGatt paramIBluetoothGatt, BluetoothDevice paramBluetoothDevice, int paramInt1, boolean paramBoolean, int paramInt2) {
    this.mService = paramIBluetoothGatt;
    this.mDevice = paramBluetoothDevice;
    this.mTransport = paramInt1;
    this.mPhy = paramInt2;
    this.mOpportunistic = paramBoolean;
    this.mServices = new ArrayList<>();
    this.mConnState = 0;
    this.mAuthRetryState = 0;
  }
  
  private boolean registerApp(BluetoothGattCallback paramBluetoothGattCallback, Handler paramHandler) {
    Log.d("BluetoothGatt", "registerApp()");
    if (this.mService == null)
      return false; 
    this.mCallback = paramBluetoothGattCallback;
    this.mHandler = paramHandler;
    UUID uUID = UUID.randomUUID();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("registerApp() - UUID=");
    stringBuilder.append(uUID);
    Log.d("BluetoothGatt", stringBuilder.toString());
    try {
      IBluetoothGatt iBluetoothGatt = this.mService;
      ParcelUuid parcelUuid = new ParcelUuid();
      this(uUID);
      iBluetoothGatt.registerClient(parcelUuid, this.mBluetoothGattCallback);
      return true;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGatt", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  private void runOrQueueCallback(Runnable paramRunnable) {
    Handler handler = this.mHandler;
    if (handler == null) {
      try {
        paramRunnable.run();
      } catch (Exception exception) {
        Log.w("BluetoothGatt", "Unhandled exception in callback", exception);
      } 
    } else {
      handler.post((Runnable)exception);
    } 
  }
  
  private void unregisterApp() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("unregisterApp() - mClientIf=");
    stringBuilder.append(this.mClientIf);
    Log.d("BluetoothGatt", stringBuilder.toString());
    if (this.mService == null || this.mClientIf == 0)
      return; 
    try {
      this.mCallback = null;
      this.mService.unregisterClient(this.mClientIf);
      this.mClientIf = 0;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGatt", "", (Throwable)remoteException);
    } 
  }
  
  public void abortReliableWrite() {
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mClientIf;
      if (i != 0) {
        try {
          iBluetoothGatt.endReliableWrite(i, this.mDevice.getAddress(), false);
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGatt", "", (Throwable)remoteException);
        } 
        return;
      } 
    } 
  }
  
  @Deprecated
  public void abortReliableWrite(BluetoothDevice paramBluetoothDevice) {
    abortReliableWrite();
  }
  
  public boolean beginReliableWrite() {
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mClientIf;
      if (i != 0)
        try {
          iBluetoothGatt.beginReliableWrite(i, this.mDevice.getAddress());
          return true;
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGatt", "", (Throwable)remoteException);
          return false;
        }  
    } 
    return false;
  }
  
  public void close() {
    Log.d("BluetoothGatt", "close()");
    unregisterApp();
    this.mConnState = 4;
    this.mAuthRetryState = 0;
  }
  
  public boolean connect() {
    try {
      this.mService.clientConnect(this.mClientIf, this.mDevice.getAddress(), false, this.mTransport, this.mOpportunistic, this.mPhy);
      return true;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGatt", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  boolean connect(Boolean paramBoolean, BluetoothGattCallback paramBluetoothGattCallback, Handler paramHandler) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("connect() - device: ");
    stringBuilder.append(this.mDevice.getAddress());
    stringBuilder.append(", auto: ");
    stringBuilder.append(paramBoolean);
    Log.d("BluetoothGatt", stringBuilder.toString());
    synchronized (this.mStateLock) {
      if (this.mConnState == 0) {
        this.mConnState = 1;
        this.mAutoConnect = paramBoolean.booleanValue();
        if (!registerApp(paramBluetoothGattCallback, paramHandler))
          synchronized (this.mStateLock) {
            this.mConnState = 0;
            Log.e("BluetoothGatt", "Failed to register callback");
            return false;
          }  
        return true;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Not idle");
      throw illegalStateException;
    } 
  }
  
  public void disconnect() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("cancelOpen() - device: ");
    stringBuilder.append(this.mDevice.getAddress());
    Log.d("BluetoothGatt", stringBuilder.toString());
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mClientIf;
      if (i != 0) {
        try {
          iBluetoothGatt.clientDisconnect(i, this.mDevice.getAddress());
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGatt", "", (Throwable)remoteException);
        } 
        return;
      } 
    } 
  }
  
  public boolean discoverServiceByUuid(UUID paramUUID) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("discoverServiceByUuid() - device: ");
    stringBuilder.append(this.mDevice.getAddress());
    Log.d("BluetoothGatt", stringBuilder.toString());
    if (this.mService == null || this.mClientIf == 0)
      return false; 
    this.mServices.clear();
    try {
      IBluetoothGatt iBluetoothGatt = this.mService;
      int i = this.mClientIf;
      String str = this.mDevice.getAddress();
      ParcelUuid parcelUuid = new ParcelUuid();
      this(paramUUID);
      iBluetoothGatt.discoverServiceByUuid(i, str, parcelUuid);
      return true;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGatt", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean discoverServices() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("discoverServices() - device: ");
    stringBuilder.append(this.mDevice.getAddress());
    Log.d("BluetoothGatt", stringBuilder.toString());
    if (this.mService == null || this.mClientIf == 0)
      return false; 
    this.mServices.clear();
    try {
      this.mService.discoverServices(this.mClientIf, this.mDevice.getAddress());
      return true;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGatt", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean executeReliableWrite() {
    if (this.mService == null || this.mClientIf == 0)
      return false; 
    synchronized (this.mDeviceBusyLock) {
      if (this.mDeviceBusy.booleanValue())
        return false; 
      this.mDeviceBusy = Boolean.valueOf(true);
      try {
        this.mService.endReliableWrite(this.mClientIf, this.mDevice.getAddress(), true);
        return true;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothGatt", "", (Throwable)remoteException);
        this.mDeviceBusy = Boolean.valueOf(false);
        return false;
      } 
    } 
  }
  
  BluetoothGattCharacteristic getCharacteristicById(BluetoothDevice paramBluetoothDevice, int paramInt) {
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
  
  BluetoothGattDescriptor getDescriptorById(BluetoothDevice paramBluetoothDevice, int paramInt) {
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
  
  public BluetoothDevice getDevice() {
    return this.mDevice;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    throw new UnsupportedOperationException("Use BluetoothManager#getDevicesMatchingConnectionStates instead.");
  }
  
  BluetoothGattService getService(BluetoothDevice paramBluetoothDevice, UUID paramUUID, int paramInt) {
    for (BluetoothGattService bluetoothGattService : this.mServices) {
      if (bluetoothGattService.getDevice().equals(paramBluetoothDevice) && bluetoothGattService.getInstanceId() == paramInt && bluetoothGattService.getUuid().equals(paramUUID))
        return bluetoothGattService; 
    } 
    return null;
  }
  
  public BluetoothGattService getService(UUID paramUUID) {
    for (BluetoothGattService bluetoothGattService : this.mServices) {
      if (bluetoothGattService.getDevice().equals(this.mDevice) && bluetoothGattService.getUuid().equals(paramUUID))
        return bluetoothGattService; 
    } 
    return null;
  }
  
  public List<BluetoothGattService> getServices() {
    ArrayList<BluetoothGattService> arrayList = new ArrayList();
    for (BluetoothGattService bluetoothGattService : this.mServices) {
      if (bluetoothGattService.getDevice().equals(this.mDevice))
        arrayList.add(bluetoothGattService); 
    } 
    return arrayList;
  }
  
  public boolean readCharacteristic(BluetoothGattCharacteristic paramBluetoothGattCharacteristic) {
    if ((paramBluetoothGattCharacteristic.getProperties() & 0x2) == 0)
      return false; 
    if (this.mService == null || this.mClientIf == 0)
      return false; 
    BluetoothGattService bluetoothGattService = paramBluetoothGattCharacteristic.getService();
    if (bluetoothGattService == null)
      return false; 
    BluetoothDevice bluetoothDevice = bluetoothGattService.getDevice();
    if (bluetoothDevice == null)
      return false; 
    synchronized (this.mDeviceBusyLock) {
      if (this.mDeviceBusy.booleanValue())
        return false; 
      this.mDeviceBusy = Boolean.valueOf(true);
      try {
        this.mService.readCharacteristic(this.mClientIf, bluetoothDevice.getAddress(), paramBluetoothGattCharacteristic.getInstanceId(), 0);
        return true;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothGatt", "", (Throwable)remoteException);
        this.mDeviceBusy = Boolean.valueOf(false);
        return false;
      } 
    } 
  }
  
  public boolean readDescriptor(BluetoothGattDescriptor paramBluetoothGattDescriptor) {
    if (this.mService == null || this.mClientIf == 0)
      return false; 
    BluetoothGattCharacteristic bluetoothGattCharacteristic = paramBluetoothGattDescriptor.getCharacteristic();
    if (bluetoothGattCharacteristic == null)
      return false; 
    BluetoothGattService bluetoothGattService = bluetoothGattCharacteristic.getService();
    if (bluetoothGattService == null)
      return false; 
    BluetoothDevice bluetoothDevice = bluetoothGattService.getDevice();
    if (bluetoothDevice == null)
      return false; 
    synchronized (this.mDeviceBusyLock) {
      if (this.mDeviceBusy.booleanValue())
        return false; 
      this.mDeviceBusy = Boolean.valueOf(true);
      try {
        this.mService.readDescriptor(this.mClientIf, bluetoothDevice.getAddress(), paramBluetoothGattDescriptor.getInstanceId(), 0);
        return true;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothGatt", "", (Throwable)remoteException);
        this.mDeviceBusy = Boolean.valueOf(false);
        return false;
      } 
    } 
  }
  
  public void readPhy() {
    try {
      this.mService.clientReadPhy(this.mClientIf, this.mDevice.getAddress());
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGatt", "", (Throwable)remoteException);
    } 
  }
  
  public boolean readRemoteRssi() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("readRssi() - device: ");
    stringBuilder.append(this.mDevice.getAddress());
    Log.d("BluetoothGatt", stringBuilder.toString());
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mClientIf;
      if (i != 0)
        try {
          iBluetoothGatt.readRemoteRssi(i, this.mDevice.getAddress());
          return true;
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGatt", "", (Throwable)remoteException);
          return false;
        }  
    } 
    return false;
  }
  
  public boolean readUsingCharacteristicUuid(UUID paramUUID, int paramInt1, int paramInt2) {
    if (this.mService == null || this.mClientIf == 0)
      return false; 
    synchronized (this.mDeviceBusyLock) {
      if (this.mDeviceBusy.booleanValue())
        return false; 
      this.mDeviceBusy = Boolean.valueOf(true);
      try {
        null = this.mService;
        int i = this.mClientIf;
        String str = this.mDevice.getAddress();
        ParcelUuid parcelUuid = new ParcelUuid();
        this(paramUUID);
        null.readUsingCharacteristicUuid(i, str, parcelUuid, paramInt1, paramInt2, 0);
        return true;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothGatt", "", (Throwable)remoteException);
        this.mDeviceBusy = Boolean.valueOf(false);
        return false;
      } 
    } 
  }
  
  public boolean refresh() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("refresh() - device: ");
    stringBuilder.append(this.mDevice.getAddress());
    Log.d("BluetoothGatt", stringBuilder.toString());
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mClientIf;
      if (i != 0)
        try {
          iBluetoothGatt.refreshDevice(i, this.mDevice.getAddress());
          return true;
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGatt", "", (Throwable)remoteException);
          return false;
        }  
    } 
    return false;
  }
  
  public boolean requestConnectionPriority(int paramInt) {
    if (paramInt >= 0 && paramInt <= 2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("requestConnectionPriority() - params: ");
      stringBuilder.append(paramInt);
      Log.d("BluetoothGatt", stringBuilder.toString());
      IBluetoothGatt iBluetoothGatt = this.mService;
      if (iBluetoothGatt != null) {
        int i = this.mClientIf;
        if (i != 0)
          try {
            iBluetoothGatt.connectionParameterUpdate(i, this.mDevice.getAddress(), paramInt);
            return true;
          } catch (RemoteException remoteException) {
            Log.e("BluetoothGatt", "", (Throwable)remoteException);
            return false;
          }  
      } 
      return false;
    } 
    throw new IllegalArgumentException("connectionPriority not within valid range");
  }
  
  public boolean requestLeConnectionUpdate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("requestLeConnectionUpdate() - min=(");
    stringBuilder.append(paramInt1);
    stringBuilder.append(")");
    stringBuilder.append(paramInt1 * 1.25D);
    stringBuilder.append("msec, max=(");
    stringBuilder.append(paramInt2);
    stringBuilder.append(")");
    stringBuilder.append(paramInt2 * 1.25D);
    stringBuilder.append("msec, latency=");
    stringBuilder.append(paramInt3);
    stringBuilder.append(", timeout=");
    stringBuilder.append(paramInt4);
    stringBuilder.append("msec, min_ce=");
    stringBuilder.append(paramInt5);
    stringBuilder.append(", max_ce=");
    stringBuilder.append(paramInt6);
    Log.d("BluetoothGatt", stringBuilder.toString());
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mClientIf;
      if (i != 0) {
        try {
          String str = this.mDevice.getAddress();
          try {
            iBluetoothGatt.leConnectionUpdate(i, str, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
            return true;
          } catch (RemoteException null) {}
        } catch (RemoteException remoteException) {}
        Log.e("BluetoothGatt", "", (Throwable)remoteException);
        return false;
      } 
    } 
    return false;
  }
  
  public boolean requestMtu(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("configureMTU() - device: ");
    stringBuilder.append(this.mDevice.getAddress());
    stringBuilder.append(" mtu: ");
    stringBuilder.append(paramInt);
    Log.d("BluetoothGatt", stringBuilder.toString());
    IBluetoothGatt iBluetoothGatt = this.mService;
    if (iBluetoothGatt != null) {
      int i = this.mClientIf;
      if (i != 0)
        try {
          iBluetoothGatt.configureMTU(i, this.mDevice.getAddress(), paramInt);
          return true;
        } catch (RemoteException remoteException) {
          Log.e("BluetoothGatt", "", (Throwable)remoteException);
          return false;
        }  
    } 
    return false;
  }
  
  public boolean setCharacteristicNotification(BluetoothGattCharacteristic paramBluetoothGattCharacteristic, boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setCharacteristicNotification() - uuid: ");
    stringBuilder.append(paramBluetoothGattCharacteristic.getUuid());
    stringBuilder.append(" enable: ");
    stringBuilder.append(paramBoolean);
    Log.d("BluetoothGatt", stringBuilder.toString());
    if (this.mService == null || this.mClientIf == 0)
      return false; 
    BluetoothGattService bluetoothGattService = paramBluetoothGattCharacteristic.getService();
    if (bluetoothGattService == null)
      return false; 
    BluetoothDevice bluetoothDevice = bluetoothGattService.getDevice();
    if (bluetoothDevice == null)
      return false; 
    try {
      this.mService.registerForNotification(this.mClientIf, bluetoothDevice.getAddress(), paramBluetoothGattCharacteristic.getInstanceId(), paramBoolean);
      return true;
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGatt", "", (Throwable)remoteException);
      return false;
    } 
  }
  
  public void setPreferredPhy(int paramInt1, int paramInt2, int paramInt3) {
    try {
      this.mService.clientSetPreferredPhy(this.mClientIf, this.mDevice.getAddress(), paramInt1, paramInt2, paramInt3);
    } catch (RemoteException remoteException) {
      Log.e("BluetoothGatt", "", (Throwable)remoteException);
    } 
  }
  
  public boolean writeCharacteristic(BluetoothGattCharacteristic paramBluetoothGattCharacteristic) {
    if ((paramBluetoothGattCharacteristic.getProperties() & 0x8) == 0 && (paramBluetoothGattCharacteristic.getProperties() & 0x4) == 0)
      return false; 
    if (this.mService == null || this.mClientIf == 0 || paramBluetoothGattCharacteristic.getValue() == null)
      return false; 
    BluetoothGattService bluetoothGattService = paramBluetoothGattCharacteristic.getService();
    if (bluetoothGattService == null)
      return false; 
    BluetoothDevice bluetoothDevice = bluetoothGattService.getDevice();
    if (bluetoothDevice == null)
      return false; 
    synchronized (this.mDeviceBusyLock) {
      if (this.mDeviceBusy.booleanValue())
        return false; 
      this.mDeviceBusy = Boolean.valueOf(true);
      try {
        this.mService.writeCharacteristic(this.mClientIf, bluetoothDevice.getAddress(), paramBluetoothGattCharacteristic.getInstanceId(), paramBluetoothGattCharacteristic.getWriteType(), 0, paramBluetoothGattCharacteristic.getValue());
        return true;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothGatt", "", (Throwable)remoteException);
        this.mDeviceBusy = Boolean.valueOf(false);
        return false;
      } 
    } 
  }
  
  public boolean writeDescriptor(BluetoothGattDescriptor paramBluetoothGattDescriptor) {
    if (this.mService == null || this.mClientIf == 0 || paramBluetoothGattDescriptor.getValue() == null)
      return false; 
    BluetoothGattCharacteristic bluetoothGattCharacteristic = paramBluetoothGattDescriptor.getCharacteristic();
    if (bluetoothGattCharacteristic == null)
      return false; 
    BluetoothGattService bluetoothGattService = bluetoothGattCharacteristic.getService();
    if (bluetoothGattService == null)
      return false; 
    BluetoothDevice bluetoothDevice = bluetoothGattService.getDevice();
    if (bluetoothDevice == null)
      return false; 
    synchronized (this.mDeviceBusyLock) {
      if (this.mDeviceBusy.booleanValue())
        return false; 
      this.mDeviceBusy = Boolean.valueOf(true);
      try {
        this.mService.writeDescriptor(this.mClientIf, bluetoothDevice.getAddress(), paramBluetoothGattDescriptor.getInstanceId(), 0, paramBluetoothGattDescriptor.getValue());
        return true;
      } catch (RemoteException remoteException) {
        Log.e("BluetoothGatt", "", (Throwable)remoteException);
        this.mDeviceBusy = Boolean.valueOf(false);
        return false;
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothGatt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */