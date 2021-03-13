package android.bluetooth.le;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.IBluetoothGatt;
import android.bluetooth.IBluetoothManager;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import java.util.IdentityHashMap;
import java.util.Map;

public final class PeriodicAdvertisingManager {
  private static final int SKIP_MAX = 499;
  
  private static final int SKIP_MIN = 0;
  
  private static final int SYNC_STARTING = -1;
  
  private static final String TAG = "PeriodicAdvertisingManager";
  
  private static final int TIMEOUT_MAX = 16384;
  
  private static final int TIMEOUT_MIN = 10;
  
  private BluetoothAdapter mBluetoothAdapter;
  
  private final IBluetoothManager mBluetoothManager;
  
  Map<PeriodicAdvertisingCallback, IPeriodicAdvertisingCallback> mCallbackWrappers;
  
  public PeriodicAdvertisingManager(IBluetoothManager paramIBluetoothManager) {
    this.mBluetoothManager = paramIBluetoothManager;
    this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    this.mCallbackWrappers = new IdentityHashMap<>();
  }
  
  private IPeriodicAdvertisingCallback wrap(final PeriodicAdvertisingCallback callback, final Handler handler) {
    return new IPeriodicAdvertisingCallback.Stub() {
        public void onPeriodicAdvertisingReport(final PeriodicAdvertisingReport report) {
          handler.post(new Runnable() {
                public void run() {
                  callback.onPeriodicAdvertisingReport(report);
                }
              });
        }
        
        public void onSyncEstablished(final int syncHandle, final BluetoothDevice device, final int advertisingSid, final int skip, final int timeout, final int status) {
          handler.post(new Runnable() {
                public void run() {
                  callback.onSyncEstablished(syncHandle, device, advertisingSid, skip, timeout, status);
                  if (status != 0)
                    PeriodicAdvertisingManager.this.mCallbackWrappers.remove(callback); 
                }
              });
        }
        
        public void onSyncLost(final int syncHandle) {
          handler.post(new Runnable() {
                public void run() {
                  callback.onSyncLost(syncHandle);
                  PeriodicAdvertisingManager.this.mCallbackWrappers.remove(callback);
                }
              });
        }
      };
  }
  
  public void registerSync(ScanResult paramScanResult, int paramInt1, int paramInt2, PeriodicAdvertisingCallback paramPeriodicAdvertisingCallback) {
    registerSync(paramScanResult, paramInt1, paramInt2, paramPeriodicAdvertisingCallback, null);
  }
  
  public void registerSync(ScanResult paramScanResult, int paramInt1, int paramInt2, PeriodicAdvertisingCallback paramPeriodicAdvertisingCallback, Handler paramHandler) {
    if (paramPeriodicAdvertisingCallback != null) {
      if (paramScanResult != null) {
        if (paramScanResult.getAdvertisingSid() != 255) {
          if (paramInt1 >= 0 && paramInt1 <= 499) {
            if (paramInt2 >= 10 && paramInt2 <= 16384)
              try {
                IBluetoothGatt iBluetoothGatt = this.mBluetoothManager.getBluetoothGatt();
                Handler handler = paramHandler;
                if (paramHandler == null)
                  handler = new Handler(Looper.getMainLooper()); 
                IPeriodicAdvertisingCallback iPeriodicAdvertisingCallback = wrap(paramPeriodicAdvertisingCallback, handler);
                this.mCallbackWrappers.put(paramPeriodicAdvertisingCallback, iPeriodicAdvertisingCallback);
                try {
                  iBluetoothGatt.registerSync(paramScanResult, paramInt1, paramInt2, iPeriodicAdvertisingCallback);
                  return;
                } catch (RemoteException remoteException) {
                  Log.e("PeriodicAdvertisingManager", "Failed to register sync - ", (Throwable)remoteException);
                  return;
                } 
              } catch (RemoteException remoteException1) {
                Log.e("PeriodicAdvertisingManager", "Failed to get Bluetooth gatt - ", (Throwable)remoteException1);
                paramPeriodicAdvertisingCallback.onSyncEstablished(0, remoteException.getDevice(), remoteException.getAdvertisingSid(), paramInt1, paramInt2, 2);
                return;
              }  
            throw new IllegalArgumentException("timeout must be between 10 and 16384");
          } 
          throw new IllegalArgumentException("timeout must be between 10 and 16384");
        } 
        throw new IllegalArgumentException("scanResult must contain a valid sid");
      } 
      throw new IllegalArgumentException("scanResult can't be null");
    } 
    throw new IllegalArgumentException("callback can't be null");
  }
  
  public void unregisterSync(PeriodicAdvertisingCallback paramPeriodicAdvertisingCallback) {
    if (paramPeriodicAdvertisingCallback != null)
      try {
        IBluetoothGatt iBluetoothGatt = this.mBluetoothManager.getBluetoothGatt();
        IPeriodicAdvertisingCallback iPeriodicAdvertisingCallback = this.mCallbackWrappers.remove(paramPeriodicAdvertisingCallback);
        if (iPeriodicAdvertisingCallback != null)
          try {
            iBluetoothGatt.unregisterSync(iPeriodicAdvertisingCallback);
            return;
          } catch (RemoteException remoteException) {
            Log.e("PeriodicAdvertisingManager", "Failed to cancel sync creation - ", (Throwable)remoteException);
            return;
          }  
        throw new IllegalArgumentException("callback was not properly registered");
      } catch (RemoteException remoteException) {
        Log.e("PeriodicAdvertisingManager", "Failed to get Bluetooth gatt - ", (Throwable)remoteException);
        return;
      }  
    throw new IllegalArgumentException("callback can't be null");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/PeriodicAdvertisingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */