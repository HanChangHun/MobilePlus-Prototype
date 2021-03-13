package android.bluetooth.le;

import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.IBluetoothGatt;
import android.bluetooth.IBluetoothManager;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.WorkSource;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class BluetoothLeScanner {
  private static final boolean DBG = true;
  
  public static final String EXTRA_CALLBACK_TYPE = "android.bluetooth.le.extra.CALLBACK_TYPE";
  
  public static final String EXTRA_ERROR_CODE = "android.bluetooth.le.extra.ERROR_CODE";
  
  public static final String EXTRA_LIST_SCAN_RESULT = "android.bluetooth.le.extra.LIST_SCAN_RESULT";
  
  private static final String TAG = "BluetoothLeScanner";
  
  private static final boolean VDBG = false;
  
  private BluetoothAdapter mBluetoothAdapter;
  
  private final IBluetoothManager mBluetoothManager;
  
  private final String mFeatureId;
  
  private final Handler mHandler;
  
  private final Map<ScanCallback, BleScanCallbackWrapper> mLeScanClients;
  
  private final String mOpPackageName;
  
  public BluetoothLeScanner(IBluetoothManager paramIBluetoothManager, String paramString1, String paramString2) {
    this.mBluetoothManager = paramIBluetoothManager;
    this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    this.mHandler = new Handler(Looper.getMainLooper());
    this.mLeScanClients = new HashMap<>();
    this.mOpPackageName = paramString1;
    this.mFeatureId = paramString2;
  }
  
  private boolean isHardwareResourcesAvailableForScan(ScanSettings paramScanSettings) {
    int i = paramScanSettings.getCallbackType();
    boolean bool = true;
    if ((i & 0x2) != 0 || (i & 0x4) != 0) {
      if (!this.mBluetoothAdapter.isOffloadedFilteringSupported() || !this.mBluetoothAdapter.isHardwareTrackingFiltersAvailable())
        bool = false; 
      return bool;
    } 
    return true;
  }
  
  private boolean isSettingsAndFilterComboAllowed(ScanSettings paramScanSettings, List<ScanFilter> paramList) {
    if ((paramScanSettings.getCallbackType() & 0x6) != 0) {
      if (paramList == null)
        return false; 
      Iterator<ScanFilter> iterator = paramList.iterator();
      while (iterator.hasNext()) {
        if (((ScanFilter)iterator.next()).isAllFieldsEmpty())
          return false; 
      } 
    } 
    return true;
  }
  
  private boolean isSettingsConfigAllowedForScan(ScanSettings paramScanSettings) {
    return this.mBluetoothAdapter.isOffloadedFilteringSupported() ? true : ((paramScanSettings.getCallbackType() == 1 && paramScanSettings.getReportDelayMillis() == 0L));
  }
  
  private void postCallbackError(final ScanCallback callback, final int errorCode) {
    this.mHandler.post(new Runnable() {
          public void run() {
            callback.onScanFailed(errorCode);
          }
        });
  }
  
  private int postCallbackErrorOrReturn(ScanCallback paramScanCallback, int paramInt) {
    if (paramScanCallback == null)
      return paramInt; 
    postCallbackError(paramScanCallback, paramInt);
    return 0;
  }
  
  private int startScan(List<ScanFilter> paramList, ScanSettings paramScanSettings, WorkSource paramWorkSource, ScanCallback paramScanCallback, PendingIntent paramPendingIntent, List<List<ResultStorageDescriptor>> paramList1) {
    BluetoothLeUtils.checkAdapterStateOn(this.mBluetoothAdapter);
    if (paramScanCallback != null || paramPendingIntent != null) {
      if (paramScanSettings != null) {
        Map<ScanCallback, BleScanCallbackWrapper> map = this.mLeScanClients;
        /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[ObjectType{android/bluetooth/le/ScanCallback}, InnerObjectType{ObjectType{android/bluetooth/le/BluetoothLeScanner}.Landroid/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper;}]>}, name=null} */
        if (paramScanCallback != null) {
          try {
            if (this.mLeScanClients.containsKey(paramScanCallback)) {
              int i = postCallbackErrorOrReturn(paramScanCallback, 1);
              /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[ObjectType{android/bluetooth/le/ScanCallback}, InnerObjectType{ObjectType{android/bluetooth/le/BluetoothLeScanner}.Landroid/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper;}]>}, name=null} */
              return i;
            } 
            try {
              IBluetoothGatt iBluetoothGatt = this.mBluetoothManager.getBluetoothGatt();
            } catch (RemoteException remoteException) {
              remoteException = null;
            } 
          } finally {}
          /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[ObjectType{android/bluetooth/le/ScanCallback}, InnerObjectType{ObjectType{android/bluetooth/le/BluetoothLeScanner}.Landroid/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper;}]>}, name=null} */
          throw paramList;
        } 
      } else {
        throw new IllegalArgumentException("settings is null");
      } 
    } else {
      throw new IllegalArgumentException("callback is null");
    } 
    try {
      IBluetoothGatt iBluetoothGatt = this.mBluetoothManager.getBluetoothGatt();
    } catch (RemoteException remoteException) {
      remoteException = null;
    } 
  }
  
  public void cleanup() {
    this.mLeScanClients.clear();
  }
  
  public void flushPendingScanResults(ScanCallback paramScanCallback) {
    BluetoothLeUtils.checkAdapterStateOn(this.mBluetoothAdapter);
    if (paramScanCallback != null)
      synchronized (this.mLeScanClients) {
        BleScanCallbackWrapper bleScanCallbackWrapper = this.mLeScanClients.get(paramScanCallback);
        if (bleScanCallbackWrapper == null)
          return; 
        bleScanCallbackWrapper.flushPendingBatchResults();
        return;
      }  
    throw new IllegalArgumentException("callback cannot be null!");
  }
  
  public int startScan(List<ScanFilter> paramList, ScanSettings paramScanSettings, PendingIntent paramPendingIntent) {
    if (paramScanSettings == null)
      paramScanSettings = (new ScanSettings.Builder()).build(); 
    return startScan(paramList, paramScanSettings, null, null, paramPendingIntent, null);
  }
  
  public void startScan(ScanCallback paramScanCallback) {
    startScan((List<ScanFilter>)null, (new ScanSettings.Builder()).build(), paramScanCallback);
  }
  
  public void startScan(List<ScanFilter> paramList, ScanSettings paramScanSettings, ScanCallback paramScanCallback) {
    startScan(paramList, paramScanSettings, null, paramScanCallback, null, null);
  }
  
  @SystemApi
  public void startScanFromSource(WorkSource paramWorkSource, ScanCallback paramScanCallback) {
    startScanFromSource(null, (new ScanSettings.Builder()).build(), paramWorkSource, paramScanCallback);
  }
  
  @SystemApi
  public void startScanFromSource(List<ScanFilter> paramList, ScanSettings paramScanSettings, WorkSource paramWorkSource, ScanCallback paramScanCallback) {
    startScan(paramList, paramScanSettings, paramWorkSource, paramScanCallback, null, null);
  }
  
  @SystemApi
  public void startTruncatedScan(List<TruncatedFilter> paramList, ScanSettings paramScanSettings, ScanCallback paramScanCallback) {
    int i = paramList.size();
    ArrayList<ScanFilter> arrayList = new ArrayList(i);
    ArrayList<List<ResultStorageDescriptor>> arrayList1 = new ArrayList(i);
    for (TruncatedFilter truncatedFilter : paramList) {
      arrayList.add(truncatedFilter.getFilter());
      arrayList1.add(truncatedFilter.getStorageDescriptors());
    } 
    startScan(arrayList, paramScanSettings, null, paramScanCallback, null, arrayList1);
  }
  
  public void stopScan(PendingIntent paramPendingIntent) {
    BluetoothLeUtils.checkAdapterStateOn(this.mBluetoothAdapter);
    try {
      this.mBluetoothManager.getBluetoothGatt().stopScanForIntent(paramPendingIntent, this.mOpPackageName);
    } catch (RemoteException remoteException) {}
  }
  
  public void stopScan(ScanCallback paramScanCallback) {
    BluetoothLeUtils.checkAdapterStateOn(this.mBluetoothAdapter);
    synchronized (this.mLeScanClients) {
      BleScanCallbackWrapper bleScanCallbackWrapper = this.mLeScanClients.remove(paramScanCallback);
      if (bleScanCallbackWrapper == null) {
        Log.d("BluetoothLeScanner", "could not find callback wrapper");
        return;
      } 
      bleScanCallbackWrapper.stopLeScan();
      return;
    } 
  }
  
  private class BleScanCallbackWrapper extends IScannerCallback.Stub {
    private static final int REGISTRATION_CALLBACK_TIMEOUT_MILLIS = 2000;
    
    private IBluetoothGatt mBluetoothGatt;
    
    private final List<ScanFilter> mFilters;
    
    private List<List<ResultStorageDescriptor>> mResultStorages;
    
    private final ScanCallback mScanCallback;
    
    private int mScannerId;
    
    private ScanSettings mSettings;
    
    private final WorkSource mWorkSource;
    
    public BleScanCallbackWrapper(IBluetoothGatt param1IBluetoothGatt, List<ScanFilter> param1List, ScanSettings param1ScanSettings, WorkSource param1WorkSource, ScanCallback param1ScanCallback, List<List<ResultStorageDescriptor>> param1List1) {
      this.mBluetoothGatt = param1IBluetoothGatt;
      this.mFilters = param1List;
      this.mSettings = param1ScanSettings;
      this.mWorkSource = param1WorkSource;
      this.mScanCallback = param1ScanCallback;
      this.mScannerId = 0;
      this.mResultStorages = param1List1;
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
    
    public void onFoundOrLost(boolean param1Boolean, ScanResult param1ScanResult) {
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
    
    public void onScanManagerErrorCallback(int param1Int) {
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
    
    public void onScanResult(ScanResult param1ScanResult) {
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
    
    public void onScannerRegistered(int param1Int1, int param1Int2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onScannerRegistered() - status=");
      stringBuilder.append(param1Int1);
      stringBuilder.append(" scannerId=");
      stringBuilder.append(param1Int2);
      stringBuilder.append(" mScannerId=");
      stringBuilder.append(this.mScannerId);
      Log.d("BluetoothLeScanner", stringBuilder.toString());
      /* monitor enter ThisExpression{InnerObjectType{ObjectType{android/bluetooth/le/BluetoothLeScanner}.Landroid/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper;}} */
      if (param1Int1 == 0) {
        try {
          if (this.mScannerId == -1) {
            this.mBluetoothGatt.unregisterScanner(param1Int2);
          } else {
            this.mScannerId = param1Int2;
            this.mBluetoothGatt.startScan(param1Int2, this.mSettings, this.mFilters, this.mResultStorages, BluetoothLeScanner.this.mOpPackageName, BluetoothLeScanner.this.mFeatureId);
          } 
        } catch (RemoteException remoteException) {
          stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("fail to start le scan: ");
          stringBuilder.append(remoteException);
          Log.e("BluetoothLeScanner", stringBuilder.toString());
          this.mScannerId = -1;
        } finally {}
      } else if (param1Int1 == 6) {
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
  
  class null implements Runnable {
    public void run() {
      this.this$1.mScanCallback.onScanResult(1, scanResult);
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$1.mScanCallback.onBatchScanResults(results);
    }
  }
  
  class null implements Runnable {
    public void run() {
      if (onFound) {
        this.this$1.mScanCallback.onScanResult(2, scanResult);
      } else {
        this.this$1.mScanCallback.onScanResult(4, scanResult);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/BluetoothLeScanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */