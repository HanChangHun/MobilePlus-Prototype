package android.bluetooth.le;

import android.bluetooth.IBluetoothGatt;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.WorkSource;
import android.util.Log;
import java.util.List;

class BleScanCallbackWrapper extends IScannerCallback.Stub {
  private static final int REGISTRATION_CALLBACK_TIMEOUT_MILLIS = 2000;
  
  private IBluetoothGatt mBluetoothGatt;
  
  private final List<ScanFilter> mFilters;
  
  private List<List<ResultStorageDescriptor>> mResultStorages;
  
  private final ScanCallback mScanCallback;
  
  private int mScannerId;
  
  private ScanSettings mSettings;
  
  private final WorkSource mWorkSource;
  
  public BleScanCallbackWrapper(IBluetoothGatt paramIBluetoothGatt, List<ScanFilter> paramList, ScanSettings paramScanSettings, WorkSource paramWorkSource, ScanCallback paramScanCallback, List<List<ResultStorageDescriptor>> paramList1) {
    this.mBluetoothGatt = paramIBluetoothGatt;
    this.mFilters = paramList;
    this.mSettings = paramScanSettings;
    this.mWorkSource = paramWorkSource;
    this.mScanCallback = paramScanCallback;
    this.mScannerId = 0;
    this.mResultStorages = paramList1;
  }
  
  void flushPendingBatchResults() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mScannerId : I
    //   6: ifgt -> 46
    //   9: new java/lang/StringBuilder
    //   12: astore_1
    //   13: aload_1
    //   14: invokespecial <init> : ()V
    //   17: aload_1
    //   18: ldc 'Error state, mLeHandle: '
    //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload_1
    //   25: aload_0
    //   26: getfield mScannerId : I
    //   29: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: ldc 'BluetoothLeScanner'
    //   35: aload_1
    //   36: invokevirtual toString : ()Ljava/lang/String;
    //   39: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   42: pop
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: aload_0
    //   47: getfield mBluetoothGatt : Landroid/bluetooth/IBluetoothGatt;
    //   50: aload_0
    //   51: getfield mScannerId : I
    //   54: invokeinterface flushPendingBatchResults : (I)V
    //   59: goto -> 72
    //   62: astore_1
    //   63: ldc 'BluetoothLeScanner'
    //   65: ldc 'Failed to get pending scan results'
    //   67: aload_1
    //   68: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   71: pop
    //   72: aload_0
    //   73: monitorexit
    //   74: return
    //   75: astore_1
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_1
    //   79: athrow
    // Exception table:
    //   from	to	target	type
    //   2	45	75	finally
    //   46	59	62	android/os/RemoteException
    //   46	59	75	finally
    //   63	72	75	finally
    //   72	74	75	finally
    //   76	78	75	finally
  }
  
  public void onBatchScanResults(final List<ScanResult> results) {
    (new Handler(Looper.getMainLooper())).post(new Runnable() {
          public void run() {
            BluetoothLeScanner.BleScanCallbackWrapper.this.mScanCallback.onBatchScanResults(results);
          }
        });
  }
  
  public void onFoundOrLost(boolean paramBoolean, ScanResult paramScanResult) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mScannerId : I
    //   6: ifgt -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: monitorexit
    //   14: new android/os/Handler
    //   17: dup
    //   18: invokestatic getMainLooper : ()Landroid/os/Looper;
    //   21: invokespecial <init> : (Landroid/os/Looper;)V
    //   24: new android/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper$3
    //   27: dup
    //   28: aload_0
    //   29: iload_1
    //   30: aload_2
    //   31: invokespecial <init> : (Landroid/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper;ZLandroid/bluetooth/le/ScanResult;)V
    //   34: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   37: pop
    //   38: return
    //   39: astore_2
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_2
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	39	finally
    //   12	14	39	finally
    //   40	42	39	finally
  }
  
  public void onScanManagerErrorCallback(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mScannerId : I
    //   6: ifgt -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_0
    //   15: getfield this$0 : Landroid/bluetooth/le/BluetoothLeScanner;
    //   18: aload_0
    //   19: getfield mScanCallback : Landroid/bluetooth/le/ScanCallback;
    //   22: iload_1
    //   23: invokestatic access$000 : (Landroid/bluetooth/le/BluetoothLeScanner;Landroid/bluetooth/le/ScanCallback;I)V
    //   26: return
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
    //   12	14	27	finally
    //   28	30	27	finally
  }
  
  public void onScanResult(ScanResult paramScanResult) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mScannerId : I
    //   6: ifgt -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: monitorexit
    //   14: new android/os/Handler
    //   17: dup
    //   18: invokestatic getMainLooper : ()Landroid/os/Looper;
    //   21: invokespecial <init> : (Landroid/os/Looper;)V
    //   24: new android/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper$1
    //   27: dup
    //   28: aload_0
    //   29: aload_1
    //   30: invokespecial <init> : (Landroid/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper;Landroid/bluetooth/le/ScanResult;)V
    //   33: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   36: pop
    //   37: return
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	38	finally
    //   12	14	38	finally
    //   39	41	38	finally
  }
  
  public void onScannerRegistered(int paramInt1, int paramInt2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onScannerRegistered() - status=");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" scannerId=");
    stringBuilder.append(paramInt2);
    stringBuilder.append(" mScannerId=");
    stringBuilder.append(this.mScannerId);
    Log.d("BluetoothLeScanner", stringBuilder.toString());
    /* monitor enter ThisExpression{InnerObjectType{ObjectType{android/bluetooth/le/BluetoothLeScanner}.Landroid/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper;}} */
    if (paramInt1 == 0) {
      try {
        if (this.mScannerId == -1) {
          this.mBluetoothGatt.unregisterScanner(paramInt2);
        } else {
          this.mScannerId = paramInt2;
          this.mBluetoothGatt.startScan(paramInt2, this.mSettings, this.mFilters, this.mResultStorages, BluetoothLeScanner.access$200(BluetoothLeScanner.this), BluetoothLeScanner.access$300(BluetoothLeScanner.this));
        } 
      } catch (RemoteException remoteException) {
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("fail to start le scan: ");
        stringBuilder.append(remoteException);
        Log.e("BluetoothLeScanner", stringBuilder.toString());
        this.mScannerId = -1;
      } finally {}
    } else if (paramInt1 == 6) {
      this.mScannerId = -2;
    } else {
      this.mScannerId = -1;
    } 
    notifyAll();
    /* monitor exit ThisExpression{InnerObjectType{ObjectType{android/bluetooth/le/BluetoothLeScanner}.Landroid/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper;}} */
  }
  
  public void startRegistration() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mScannerId : I
    //   6: iconst_m1
    //   7: if_icmpeq -> 137
    //   10: aload_0
    //   11: getfield mScannerId : I
    //   14: istore_1
    //   15: iload_1
    //   16: bipush #-2
    //   18: if_icmpne -> 24
    //   21: goto -> 137
    //   24: aload_0
    //   25: getfield mBluetoothGatt : Landroid/bluetooth/IBluetoothGatt;
    //   28: aload_0
    //   29: aload_0
    //   30: getfield mWorkSource : Landroid/os/WorkSource;
    //   33: invokeinterface registerScanner : (Landroid/bluetooth/le/IScannerCallback;Landroid/os/WorkSource;)V
    //   38: aload_0
    //   39: ldc2_w 2000
    //   42: invokevirtual wait : (J)V
    //   45: goto -> 70
    //   48: astore_2
    //   49: ldc 'BluetoothLeScanner'
    //   51: ldc 'application registeration exception'
    //   53: aload_2
    //   54: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   57: pop
    //   58: aload_0
    //   59: getfield this$0 : Landroid/bluetooth/le/BluetoothLeScanner;
    //   62: aload_0
    //   63: getfield mScanCallback : Landroid/bluetooth/le/ScanCallback;
    //   66: iconst_3
    //   67: invokestatic access$000 : (Landroid/bluetooth/le/BluetoothLeScanner;Landroid/bluetooth/le/ScanCallback;I)V
    //   70: aload_0
    //   71: getfield mScannerId : I
    //   74: ifle -> 98
    //   77: aload_0
    //   78: getfield this$0 : Landroid/bluetooth/le/BluetoothLeScanner;
    //   81: invokestatic access$100 : (Landroid/bluetooth/le/BluetoothLeScanner;)Ljava/util/Map;
    //   84: aload_0
    //   85: getfield mScanCallback : Landroid/bluetooth/le/ScanCallback;
    //   88: aload_0
    //   89: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   94: pop
    //   95: goto -> 134
    //   98: aload_0
    //   99: getfield mScannerId : I
    //   102: ifne -> 110
    //   105: aload_0
    //   106: iconst_m1
    //   107: putfield mScannerId : I
    //   110: aload_0
    //   111: getfield mScannerId : I
    //   114: bipush #-2
    //   116: if_icmpne -> 122
    //   119: aload_0
    //   120: monitorexit
    //   121: return
    //   122: aload_0
    //   123: getfield this$0 : Landroid/bluetooth/le/BluetoothLeScanner;
    //   126: aload_0
    //   127: getfield mScanCallback : Landroid/bluetooth/le/ScanCallback;
    //   130: iconst_2
    //   131: invokestatic access$000 : (Landroid/bluetooth/le/BluetoothLeScanner;Landroid/bluetooth/le/ScanCallback;I)V
    //   134: aload_0
    //   135: monitorexit
    //   136: return
    //   137: aload_0
    //   138: monitorexit
    //   139: return
    //   140: astore_2
    //   141: aload_0
    //   142: monitorexit
    //   143: aload_2
    //   144: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	140	finally
    //   24	45	48	java/lang/InterruptedException
    //   24	45	48	android/os/RemoteException
    //   24	45	140	finally
    //   49	70	140	finally
    //   70	95	140	finally
    //   98	110	140	finally
    //   110	121	140	finally
    //   122	134	140	finally
    //   134	136	140	finally
    //   137	139	140	finally
    //   141	143	140	finally
  }
  
  public void stopLeScan() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mScannerId : I
    //   6: ifgt -> 46
    //   9: new java/lang/StringBuilder
    //   12: astore_1
    //   13: aload_1
    //   14: invokespecial <init> : ()V
    //   17: aload_1
    //   18: ldc 'Error state, mLeHandle: '
    //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload_1
    //   25: aload_0
    //   26: getfield mScannerId : I
    //   29: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: ldc 'BluetoothLeScanner'
    //   35: aload_1
    //   36: invokevirtual toString : ()Ljava/lang/String;
    //   39: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   42: pop
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: aload_0
    //   47: getfield mBluetoothGatt : Landroid/bluetooth/IBluetoothGatt;
    //   50: aload_0
    //   51: getfield mScannerId : I
    //   54: invokeinterface stopScan : (I)V
    //   59: aload_0
    //   60: getfield mBluetoothGatt : Landroid/bluetooth/IBluetoothGatt;
    //   63: aload_0
    //   64: getfield mScannerId : I
    //   67: invokeinterface unregisterScanner : (I)V
    //   72: goto -> 85
    //   75: astore_1
    //   76: ldc 'BluetoothLeScanner'
    //   78: ldc 'Failed to stop scan and unregister'
    //   80: aload_1
    //   81: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   84: pop
    //   85: aload_0
    //   86: iconst_m1
    //   87: putfield mScannerId : I
    //   90: aload_0
    //   91: monitorexit
    //   92: return
    //   93: astore_1
    //   94: aload_0
    //   95: monitorexit
    //   96: aload_1
    //   97: athrow
    // Exception table:
    //   from	to	target	type
    //   2	45	93	finally
    //   46	72	75	android/os/RemoteException
    //   46	72	93	finally
    //   76	85	93	finally
    //   85	92	93	finally
    //   94	96	93	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */