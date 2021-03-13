package android.bluetooth.le;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.IBluetoothGatt;
import android.bluetooth.IBluetoothManager;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.util.Log;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class BluetoothLeAdvertiser {
  private static final int FLAGS_FIELD_BYTES = 3;
  
  private static final int MANUFACTURER_SPECIFIC_DATA_LENGTH = 2;
  
  private static final int MAX_ADVERTISING_DATA_BYTES = 1650;
  
  private static final int MAX_LEGACY_ADVERTISING_DATA_BYTES = 31;
  
  private static final int OVERHEAD_BYTES_PER_FIELD = 2;
  
  private static final String TAG = "BluetoothLeAdvertiser";
  
  private final Map<Integer, AdvertisingSet> mAdvertisingSets = Collections.synchronizedMap(new HashMap<>());
  
  private BluetoothAdapter mBluetoothAdapter;
  
  private final IBluetoothManager mBluetoothManager;
  
  private final Map<AdvertisingSetCallback, IAdvertisingSetCallback> mCallbackWrappers = Collections.synchronizedMap(new HashMap<>());
  
  private final Handler mHandler;
  
  private final Map<AdvertiseCallback, AdvertisingSetCallback> mLegacyAdvertisers = new HashMap<>();
  
  public BluetoothLeAdvertiser(IBluetoothManager paramIBluetoothManager) {
    this.mBluetoothManager = paramIBluetoothManager;
    this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    this.mHandler = new Handler(Looper.getMainLooper());
  }
  
  private int byteLength(byte[] paramArrayOfbyte) {
    int i;
    if (paramArrayOfbyte == null) {
      i = 0;
    } else {
      i = paramArrayOfbyte.length;
    } 
    return i;
  }
  
  private void postStartFailure(final AdvertiseCallback callback, final int error) {
    this.mHandler.post(new Runnable() {
          public void run() {
            callback.onStartFailure(error);
          }
        });
  }
  
  private void postStartSetFailure(Handler paramHandler, final AdvertisingSetCallback callback, final int error) {
    paramHandler.post(new Runnable() {
          public void run() {
            callback.onAdvertisingSetStarted(null, 0, error);
          }
        });
  }
  
  private void postStartSuccess(final AdvertiseCallback callback, final AdvertiseSettings settings) {
    this.mHandler.post(new Runnable() {
          public void run() {
            callback.onStartSuccess(settings);
          }
        });
  }
  
  private int totalBytes(AdvertiseData paramAdvertiseData, boolean paramBoolean) {
    int i = 0;
    if (paramAdvertiseData == null)
      return 0; 
    if (paramBoolean)
      i = 3; 
    int j = i;
    if (paramAdvertiseData.getServiceUuids() != null) {
      byte b1 = 0;
      byte b2 = 0;
      byte b3 = 0;
      for (ParcelUuid parcelUuid : paramAdvertiseData.getServiceUuids()) {
        if (BluetoothUuid.is16BitUuid(parcelUuid)) {
          b1++;
          continue;
        } 
        if (BluetoothUuid.is32BitUuid(parcelUuid)) {
          b2++;
          continue;
        } 
        b3++;
      } 
      j = i;
      if (b1 != 0)
        j = i + b1 * 2 + 2; 
      i = j;
      if (b2 != 0)
        i = j + b2 * 4 + 2; 
      j = i;
      if (b3 != 0)
        j = i + b3 * 16 + 2; 
    } 
    Iterator<ParcelUuid> iterator = paramAdvertiseData.getServiceData().keySet().iterator();
    for (i = j; iterator.hasNext(); i += (BluetoothUuid.uuidToBytes(parcelUuid)).length + 2 + byteLength(paramAdvertiseData.getServiceData().get(parcelUuid)))
      ParcelUuid parcelUuid = iterator.next(); 
    for (j = 0; j < paramAdvertiseData.getManufacturerSpecificData().size(); j++)
      i += byteLength((byte[])paramAdvertiseData.getManufacturerSpecificData().valueAt(j)) + 4; 
    j = i;
    if (paramAdvertiseData.getIncludeTxPowerLevel())
      j = i + 3; 
    i = j;
    if (paramAdvertiseData.getIncludeDeviceName()) {
      i = j;
      if (this.mBluetoothAdapter.getName() != null)
        i = j + this.mBluetoothAdapter.getName().length() + 2; 
    } 
    return i;
  }
  
  public void cleanup() {
    this.mLegacyAdvertisers.clear();
    this.mCallbackWrappers.clear();
    this.mAdvertisingSets.clear();
  }
  
  public void startAdvertising(AdvertiseSettings paramAdvertiseSettings, AdvertiseData paramAdvertiseData, AdvertiseCallback paramAdvertiseCallback) {
    startAdvertising(paramAdvertiseSettings, paramAdvertiseData, null, paramAdvertiseCallback);
  }
  
  public void startAdvertising(AdvertiseSettings paramAdvertiseSettings, AdvertiseData paramAdvertiseData1, AdvertiseData paramAdvertiseData2, AdvertiseCallback paramAdvertiseCallback) {
    Map<AdvertiseCallback, AdvertisingSetCallback> map = this.mLegacyAdvertisers;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[ObjectType{android/bluetooth/le/AdvertiseCallback}, ObjectType{android/bluetooth/le/AdvertisingSetCallback}]>}, name=null} */
    try {
      BluetoothLeUtils.checkAdapterStateOn(this.mBluetoothAdapter);
      if (paramAdvertiseCallback != null) {
        boolean bool = paramAdvertiseSettings.isConnectable();
        try {
          int i = totalBytes(paramAdvertiseData1, bool);
          int j = 1;
          if (i <= 31) {
            try {
              if (totalBytes(paramAdvertiseData2, false) <= 31) {
                if (this.mLegacyAdvertisers.containsKey(paramAdvertiseCallback)) {
                  postStartFailure(paramAdvertiseCallback, 3);
                  /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[ObjectType{android/bluetooth/le/AdvertiseCallback}, ObjectType{android/bluetooth/le/AdvertisingSetCallback}]>}, name=null} */
                  return;
                } 
                AdvertisingSetParameters.Builder builder = new AdvertisingSetParameters.Builder();
                this();
                builder.setLegacyMode(true);
                builder.setConnectable(bool);
                builder.setScannable(true);
                if (paramAdvertiseSettings.getMode() == 0) {
                  builder.setInterval(1600);
                } else if (paramAdvertiseSettings.getMode() == 1) {
                  builder.setInterval(400);
                } else if (paramAdvertiseSettings.getMode() == 2) {
                  builder.setInterval(160);
                } 
                if (paramAdvertiseSettings.getTxPowerLevel() == 0) {
                  builder.setTxPowerLevel(-21);
                } else if (paramAdvertiseSettings.getTxPowerLevel() == 1) {
                  builder.setTxPowerLevel(-15);
                } else if (paramAdvertiseSettings.getTxPowerLevel() == 2) {
                  builder.setTxPowerLevel(-7);
                } else if (paramAdvertiseSettings.getTxPowerLevel() == 3) {
                  builder.setTxPowerLevel(1);
                } 
                i = paramAdvertiseSettings.getTimeout();
                if (i > 0) {
                  if (i >= 10)
                    j = i / 10; 
                } else {
                  j = 0;
                } 
                AdvertisingSetCallback advertisingSetCallback = wrapOldCallback(paramAdvertiseCallback, paramAdvertiseSettings);
                this.mLegacyAdvertisers.put(paramAdvertiseCallback, advertisingSetCallback);
                startAdvertisingSet(builder.build(), paramAdvertiseData1, paramAdvertiseData2, null, null, j, 0, advertisingSetCallback);
                /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[ObjectType{android/bluetooth/le/AdvertiseCallback}, ObjectType{android/bluetooth/le/AdvertisingSetCallback}]>}, name=null} */
                return;
              } 
              postStartFailure(paramAdvertiseCallback, 1);
              /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[ObjectType{android/bluetooth/le/AdvertiseCallback}, ObjectType{android/bluetooth/le/AdvertisingSetCallback}]>}, name=null} */
              return;
            } finally {}
            /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[ObjectType{android/bluetooth/le/AdvertiseCallback}, ObjectType{android/bluetooth/le/AdvertisingSetCallback}]>}, name=null} */
            throw paramAdvertiseSettings;
          } 
          postStartFailure(paramAdvertiseCallback, 1);
          /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[ObjectType{android/bluetooth/le/AdvertiseCallback}, ObjectType{android/bluetooth/le/AdvertisingSetCallback}]>}, name=null} */
          return;
        } finally {}
      } else {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        this("callback cannot be null");
        throw illegalArgumentException;
      } 
    } finally {}
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[ObjectType{android/bluetooth/le/AdvertiseCallback}, ObjectType{android/bluetooth/le/AdvertisingSetCallback}]>}, name=null} */
    throw paramAdvertiseSettings;
  }
  
  public void startAdvertisingSet(AdvertisingSetParameters paramAdvertisingSetParameters, AdvertiseData paramAdvertiseData1, AdvertiseData paramAdvertiseData2, PeriodicAdvertisingParameters paramPeriodicAdvertisingParameters, AdvertiseData paramAdvertiseData3, int paramInt1, int paramInt2, AdvertisingSetCallback paramAdvertisingSetCallback) {
    startAdvertisingSet(paramAdvertisingSetParameters, paramAdvertiseData1, paramAdvertiseData2, paramPeriodicAdvertisingParameters, paramAdvertiseData3, paramInt1, paramInt2, paramAdvertisingSetCallback, new Handler(Looper.getMainLooper()));
  }
  
  public void startAdvertisingSet(AdvertisingSetParameters paramAdvertisingSetParameters, AdvertiseData paramAdvertiseData1, AdvertiseData paramAdvertiseData2, PeriodicAdvertisingParameters paramPeriodicAdvertisingParameters, AdvertiseData paramAdvertiseData3, int paramInt1, int paramInt2, AdvertisingSetCallback paramAdvertisingSetCallback, Handler paramHandler) {
    BluetoothLeUtils.checkAdapterStateOn(this.mBluetoothAdapter);
    if (paramAdvertisingSetCallback != null) {
      boolean bool = paramAdvertisingSetParameters.isConnectable();
      if (paramAdvertisingSetParameters.isLegacy()) {
        if (totalBytes(paramAdvertiseData1, bool) <= 31) {
          if (totalBytes(paramAdvertiseData2, false) > 31)
            throw new IllegalArgumentException("Legacy scan response data too big"); 
        } else {
          throw new IllegalArgumentException("Legacy advertising data too big");
        } 
      } else {
        boolean bool1 = this.mBluetoothAdapter.isLeCodedPhySupported();
        boolean bool2 = this.mBluetoothAdapter.isLe2MPhySupported();
        int i = paramAdvertisingSetParameters.getPrimaryPhy();
        int j = paramAdvertisingSetParameters.getSecondaryPhy();
        if (i != 3 || bool1) {
          if ((j != 3 || bool1) && (j != 2 || bool2)) {
            i = this.mBluetoothAdapter.getLeMaximumAdvertisingDataLength();
            if (totalBytes(paramAdvertiseData1, bool) <= i) {
              if (totalBytes(paramAdvertiseData2, false) <= i) {
                if (totalBytes(paramAdvertiseData3, false) <= i) {
                  bool2 = this.mBluetoothAdapter.isLePeriodicAdvertisingSupported();
                  if (paramPeriodicAdvertisingParameters != null && !bool2)
                    throw new IllegalArgumentException("Controller does not support LE Periodic Advertising"); 
                } else {
                  throw new IllegalArgumentException("Periodic advertising data too big");
                } 
              } else {
                throw new IllegalArgumentException("Scan response data too big");
              } 
            } else {
              throw new IllegalArgumentException("Advertising data too big");
            } 
          } else {
            throw new IllegalArgumentException("Unsupported secondary PHY selected");
          } 
        } else {
          throw new IllegalArgumentException("Unsupported primary PHY selected");
        } 
      } 
      if (paramInt2 >= 0 && paramInt2 <= 255) {
        if (paramInt2 == 0 || this.mBluetoothAdapter.isLePeriodicAdvertisingSupported()) {
          if (paramInt1 >= 0 && paramInt1 <= 65535)
            try {
              IBluetoothGatt iBluetoothGatt = this.mBluetoothManager.getBluetoothGatt();
              if (iBluetoothGatt == null) {
                Log.e("BluetoothLeAdvertiser", "Bluetooth GATT is null");
                postStartSetFailure(paramHandler, paramAdvertisingSetCallback, 4);
                return;
              } 
              IAdvertisingSetCallback iAdvertisingSetCallback = wrap(paramAdvertisingSetCallback, paramHandler);
              if (this.mCallbackWrappers.putIfAbsent(paramAdvertisingSetCallback, iAdvertisingSetCallback) == null)
                try {
                  iBluetoothGatt.startAdvertisingSet(paramAdvertisingSetParameters, paramAdvertiseData1, paramAdvertiseData2, paramPeriodicAdvertisingParameters, paramAdvertiseData3, paramInt1, paramInt2, iAdvertisingSetCallback);
                  return;
                } catch (RemoteException remoteException) {
                  Log.e("BluetoothLeAdvertiser", "Failed to start advertising set - ", (Throwable)remoteException);
                  postStartSetFailure(paramHandler, paramAdvertisingSetCallback, 4);
                  return;
                }  
              throw new IllegalArgumentException("callback instance already associated with advertising");
            } catch (RemoteException remoteException) {
              Log.e("BluetoothLeAdvertiser", "Failed to get Bluetooth GATT - ", (Throwable)remoteException);
              postStartSetFailure(paramHandler, paramAdvertisingSetCallback, 4);
              return;
            }  
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("duration out of range: ");
          stringBuilder1.append(paramInt1);
          throw new IllegalArgumentException(stringBuilder1.toString());
        } 
        throw new IllegalArgumentException("Can't use maxExtendedAdvertisingEvents with controller that don't support LE Extended Advertising");
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("maxExtendedAdvertisingEvents out of range: ");
      stringBuilder.append(paramInt2);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    throw new IllegalArgumentException("callback cannot be null");
  }
  
  public void startAdvertisingSet(AdvertisingSetParameters paramAdvertisingSetParameters, AdvertiseData paramAdvertiseData1, AdvertiseData paramAdvertiseData2, PeriodicAdvertisingParameters paramPeriodicAdvertisingParameters, AdvertiseData paramAdvertiseData3, AdvertisingSetCallback paramAdvertisingSetCallback) {
    startAdvertisingSet(paramAdvertisingSetParameters, paramAdvertiseData1, paramAdvertiseData2, paramPeriodicAdvertisingParameters, paramAdvertiseData3, 0, 0, paramAdvertisingSetCallback, new Handler(Looper.getMainLooper()));
  }
  
  public void startAdvertisingSet(AdvertisingSetParameters paramAdvertisingSetParameters, AdvertiseData paramAdvertiseData1, AdvertiseData paramAdvertiseData2, PeriodicAdvertisingParameters paramPeriodicAdvertisingParameters, AdvertiseData paramAdvertiseData3, AdvertisingSetCallback paramAdvertisingSetCallback, Handler paramHandler) {
    startAdvertisingSet(paramAdvertisingSetParameters, paramAdvertiseData1, paramAdvertiseData2, paramPeriodicAdvertisingParameters, paramAdvertiseData3, 0, 0, paramAdvertisingSetCallback, paramHandler);
  }
  
  public void stopAdvertising(AdvertiseCallback paramAdvertiseCallback) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLegacyAdvertisers : Ljava/util/Map;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_1
    //   8: ifnull -> 55
    //   11: aload_0
    //   12: getfield mLegacyAdvertisers : Ljava/util/Map;
    //   15: aload_1
    //   16: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   21: checkcast android/bluetooth/le/AdvertisingSetCallback
    //   24: astore_3
    //   25: aload_3
    //   26: ifnonnull -> 32
    //   29: aload_2
    //   30: monitorexit
    //   31: return
    //   32: aload_0
    //   33: aload_3
    //   34: invokevirtual stopAdvertisingSet : (Landroid/bluetooth/le/AdvertisingSetCallback;)V
    //   37: aload_0
    //   38: getfield mLegacyAdvertisers : Ljava/util/Map;
    //   41: aload_1
    //   42: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   47: pop
    //   48: aload_2
    //   49: monitorexit
    //   50: return
    //   51: astore_1
    //   52: goto -> 68
    //   55: new java/lang/IllegalArgumentException
    //   58: astore_1
    //   59: aload_1
    //   60: ldc_w 'callback cannot be null'
    //   63: invokespecial <init> : (Ljava/lang/String;)V
    //   66: aload_1
    //   67: athrow
    //   68: aload_2
    //   69: monitorexit
    //   70: aload_1
    //   71: athrow
    // Exception table:
    //   from	to	target	type
    //   11	25	51	finally
    //   29	31	51	finally
    //   32	48	51	finally
    //   48	50	51	finally
    //   55	68	51	finally
    //   68	70	51	finally
  }
  
  public void stopAdvertisingSet(AdvertisingSetCallback paramAdvertisingSetCallback) {
    if (paramAdvertisingSetCallback != null) {
      IAdvertisingSetCallback iAdvertisingSetCallback = this.mCallbackWrappers.remove(paramAdvertisingSetCallback);
      if (iAdvertisingSetCallback == null)
        return; 
      try {
        this.mBluetoothManager.getBluetoothGatt().stopAdvertisingSet(iAdvertisingSetCallback);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothLeAdvertiser", "Failed to stop advertising - ", (Throwable)remoteException);
      } 
      return;
    } 
    throw new IllegalArgumentException("callback cannot be null");
  }
  
  IAdvertisingSetCallback wrap(final AdvertisingSetCallback callback, final Handler handler) {
    return new IAdvertisingSetCallback.Stub() {
        public void onAdvertisingDataSet(final int advertiserId, final int status) {
          handler.post(new Runnable() {
                public void run() {
                  AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.this.mAdvertisingSets.get(Integer.valueOf(advertiserId));
                  callback.onAdvertisingDataSet(advertisingSet, status);
                }
              });
        }
        
        public void onAdvertisingEnabled(final int advertiserId, final boolean enabled, final int status) {
          handler.post(new Runnable() {
                public void run() {
                  AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.this.mAdvertisingSets.get(Integer.valueOf(advertiserId));
                  callback.onAdvertisingEnabled(advertisingSet, enabled, status);
                }
              });
        }
        
        public void onAdvertisingParametersUpdated(final int advertiserId, final int txPower, final int status) {
          handler.post(new Runnable() {
                public void run() {
                  AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.this.mAdvertisingSets.get(Integer.valueOf(advertiserId));
                  callback.onAdvertisingParametersUpdated(advertisingSet, txPower, status);
                }
              });
        }
        
        public void onAdvertisingSetStarted(final int advertiserId, final int txPower, final int status) {
          handler.post(new Runnable() {
                public void run() {
                  if (status != 0) {
                    callback.onAdvertisingSetStarted(null, 0, status);
                    BluetoothLeAdvertiser.this.mCallbackWrappers.remove(callback);
                    return;
                  } 
                  AdvertisingSet advertisingSet = new AdvertisingSet(advertiserId, BluetoothLeAdvertiser.this.mBluetoothManager);
                  BluetoothLeAdvertiser.this.mAdvertisingSets.put(Integer.valueOf(advertiserId), advertisingSet);
                  callback.onAdvertisingSetStarted(advertisingSet, txPower, status);
                }
              });
        }
        
        public void onAdvertisingSetStopped(final int advertiserId) {
          handler.post(new Runnable() {
                public void run() {
                  AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.this.mAdvertisingSets.get(Integer.valueOf(advertiserId));
                  callback.onAdvertisingSetStopped(advertisingSet);
                  BluetoothLeAdvertiser.this.mAdvertisingSets.remove(Integer.valueOf(advertiserId));
                  BluetoothLeAdvertiser.this.mCallbackWrappers.remove(callback);
                }
              });
        }
        
        public void onOwnAddressRead(final int advertiserId, final int addressType, final String address) {
          handler.post(new Runnable() {
                public void run() {
                  AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.this.mAdvertisingSets.get(Integer.valueOf(advertiserId));
                  callback.onOwnAddressRead(advertisingSet, addressType, address);
                }
              });
        }
        
        public void onPeriodicAdvertisingDataSet(final int advertiserId, final int status) {
          handler.post(new Runnable() {
                public void run() {
                  AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.this.mAdvertisingSets.get(Integer.valueOf(advertiserId));
                  callback.onPeriodicAdvertisingDataSet(advertisingSet, status);
                }
              });
        }
        
        public void onPeriodicAdvertisingEnabled(final int advertiserId, final boolean enable, final int status) {
          handler.post(new Runnable() {
                public void run() {
                  AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.this.mAdvertisingSets.get(Integer.valueOf(advertiserId));
                  callback.onPeriodicAdvertisingEnabled(advertisingSet, enable, status);
                }
              });
        }
        
        public void onPeriodicAdvertisingParametersUpdated(final int advertiserId, final int status) {
          handler.post(new Runnable() {
                public void run() {
                  AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.this.mAdvertisingSets.get(Integer.valueOf(advertiserId));
                  callback.onPeriodicAdvertisingParametersUpdated(advertisingSet, status);
                }
              });
        }
        
        public void onScanResponseDataSet(final int advertiserId, final int status) {
          handler.post(new Runnable() {
                public void run() {
                  AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.this.mAdvertisingSets.get(Integer.valueOf(advertiserId));
                  callback.onScanResponseDataSet(advertisingSet, status);
                }
              });
        }
      };
  }
  
  AdvertisingSetCallback wrapOldCallback(final AdvertiseCallback callback, final AdvertiseSettings settings) {
    return new AdvertisingSetCallback() {
        public void onAdvertisingEnabled(AdvertisingSet param1AdvertisingSet, boolean param1Boolean, int param1Int) {
          if (param1Boolean) {
            Log.e("BluetoothLeAdvertiser", "Legacy advertiser should be only disabled on timeout, but was enabled!");
            return;
          } 
          BluetoothLeAdvertiser.this.stopAdvertising(callback);
        }
        
        public void onAdvertisingSetStarted(AdvertisingSet param1AdvertisingSet, int param1Int1, int param1Int2) {
          if (param1Int2 != 0) {
            BluetoothLeAdvertiser.this.postStartFailure(callback, param1Int2);
            return;
          } 
          BluetoothLeAdvertiser.this.postStartSuccess(callback, settings);
        }
      };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/BluetoothLeAdvertiser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */