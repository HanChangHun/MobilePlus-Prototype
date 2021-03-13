package android.bluetooth;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.os.ParcelUuid;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class null extends ScanCallback {
  public void onScanResult(int paramInt, ScanResult paramScanResult) {
    if (paramInt != 1) {
      Log.e("BluetoothAdapter", "LE Scan has already started");
      return;
    } 
    ScanRecord scanRecord = paramScanResult.getScanRecord();
    if (scanRecord == null)
      return; 
    if (serviceUuids != null) {
      ArrayList<ParcelUuid> arrayList = new ArrayList();
      UUID[] arrayOfUUID = serviceUuids;
      int i = arrayOfUUID.length;
      for (paramInt = 0; paramInt < i; paramInt++)
        arrayList.add(new ParcelUuid(arrayOfUUID[paramInt])); 
      List list = scanRecord.getServiceUuids();
      if (list == null || !list.containsAll(arrayList)) {
        Log.d("BluetoothAdapter", "uuids does not match");
        return;
      } 
    } 
    callback.onLeScan(paramScanResult.getDevice(), paramScanResult.getRssi(), scanRecord.getBytes());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAdapter$6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */