package android.bluetooth.le;

import android.bluetooth.IBluetoothGatt;
import android.bluetooth.IBluetoothManager;
import android.os.RemoteException;
import android.util.Log;

public final class AdvertisingSet {
  private static final String TAG = "AdvertisingSet";
  
  private int mAdvertiserId;
  
  private final IBluetoothGatt mGatt;
  
  AdvertisingSet(int paramInt, IBluetoothManager paramIBluetoothManager) {
    this.mAdvertiserId = paramInt;
    try {
      this.mGatt = paramIBluetoothManager.getBluetoothGatt();
      return;
    } catch (RemoteException remoteException) {
      Log.e("AdvertisingSet", "Failed to get Bluetooth gatt - ", (Throwable)remoteException);
      throw new IllegalStateException("Failed to get Bluetooth");
    } 
  }
  
  public void enableAdvertising(boolean paramBoolean, int paramInt1, int paramInt2) {
    try {
      this.mGatt.enableAdvertisingSet(this.mAdvertiserId, paramBoolean, paramInt1, paramInt2);
    } catch (RemoteException remoteException) {
      Log.e("AdvertisingSet", "remote exception - ", (Throwable)remoteException);
    } 
  }
  
  public int getAdvertiserId() {
    return this.mAdvertiserId;
  }
  
  public void getOwnAddress() {
    try {
      this.mGatt.getOwnAddress(this.mAdvertiserId);
    } catch (RemoteException remoteException) {
      Log.e("AdvertisingSet", "remote exception - ", (Throwable)remoteException);
    } 
  }
  
  void setAdvertiserId(int paramInt) {
    this.mAdvertiserId = paramInt;
  }
  
  public void setAdvertisingData(AdvertiseData paramAdvertiseData) {
    try {
      this.mGatt.setAdvertisingData(this.mAdvertiserId, paramAdvertiseData);
    } catch (RemoteException remoteException) {
      Log.e("AdvertisingSet", "remote exception - ", (Throwable)remoteException);
    } 
  }
  
  public void setAdvertisingParameters(AdvertisingSetParameters paramAdvertisingSetParameters) {
    try {
      this.mGatt.setAdvertisingParameters(this.mAdvertiserId, paramAdvertisingSetParameters);
    } catch (RemoteException remoteException) {
      Log.e("AdvertisingSet", "remote exception - ", (Throwable)remoteException);
    } 
  }
  
  public void setPeriodicAdvertisingData(AdvertiseData paramAdvertiseData) {
    try {
      this.mGatt.setPeriodicAdvertisingData(this.mAdvertiserId, paramAdvertiseData);
    } catch (RemoteException remoteException) {
      Log.e("AdvertisingSet", "remote exception - ", (Throwable)remoteException);
    } 
  }
  
  public void setPeriodicAdvertisingEnabled(boolean paramBoolean) {
    try {
      this.mGatt.setPeriodicAdvertisingEnable(this.mAdvertiserId, paramBoolean);
    } catch (RemoteException remoteException) {
      Log.e("AdvertisingSet", "remote exception - ", (Throwable)remoteException);
    } 
  }
  
  public void setPeriodicAdvertisingParameters(PeriodicAdvertisingParameters paramPeriodicAdvertisingParameters) {
    try {
      this.mGatt.setPeriodicAdvertisingParameters(this.mAdvertiserId, paramPeriodicAdvertisingParameters);
    } catch (RemoteException remoteException) {
      Log.e("AdvertisingSet", "remote exception - ", (Throwable)remoteException);
    } 
  }
  
  public void setScanResponseData(AdvertiseData paramAdvertiseData) {
    try {
      this.mGatt.setScanResponseData(this.mAdvertiserId, paramAdvertiseData);
    } catch (RemoteException remoteException) {
      Log.e("AdvertisingSet", "remote exception - ", (Throwable)remoteException);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/AdvertisingSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */