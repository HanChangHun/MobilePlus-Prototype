package android.bluetooth;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BluetoothGattService implements Parcelable {
  public static final Parcelable.Creator<BluetoothGattService> CREATOR = new Parcelable.Creator<BluetoothGattService>() {
      public BluetoothGattService createFromParcel(Parcel param1Parcel) {
        return new BluetoothGattService(param1Parcel);
      }
      
      public BluetoothGattService[] newArray(int param1Int) {
        return new BluetoothGattService[param1Int];
      }
    };
  
  public static final int SERVICE_TYPE_PRIMARY = 0;
  
  public static final int SERVICE_TYPE_SECONDARY = 1;
  
  private boolean mAdvertisePreferred;
  
  protected List<BluetoothGattCharacteristic> mCharacteristics;
  
  protected BluetoothDevice mDevice;
  
  protected int mHandles = 0;
  
  protected List<BluetoothGattService> mIncludedServices;
  
  protected int mInstanceId;
  
  protected int mServiceType;
  
  protected UUID mUuid;
  
  BluetoothGattService(BluetoothDevice paramBluetoothDevice, UUID paramUUID, int paramInt1, int paramInt2) {
    this.mDevice = paramBluetoothDevice;
    this.mUuid = paramUUID;
    this.mInstanceId = paramInt1;
    this.mServiceType = paramInt2;
    this.mCharacteristics = new ArrayList<>();
    this.mIncludedServices = new ArrayList<>();
  }
  
  private BluetoothGattService(Parcel paramParcel) {
    this.mUuid = ((ParcelUuid)paramParcel.readParcelable(null)).getUuid();
    this.mInstanceId = paramParcel.readInt();
    this.mServiceType = paramParcel.readInt();
    this.mCharacteristics = new ArrayList<>();
    ArrayList arrayList2 = paramParcel.createTypedArrayList(BluetoothGattCharacteristic.CREATOR);
    if (arrayList2 != null)
      for (BluetoothGattCharacteristic bluetoothGattCharacteristic : arrayList2) {
        bluetoothGattCharacteristic.setService(this);
        this.mCharacteristics.add(bluetoothGattCharacteristic);
      }  
    this.mIncludedServices = new ArrayList<>();
    ArrayList arrayList1 = paramParcel.createTypedArrayList(BluetoothGattIncludedService.CREATOR);
    if (arrayList2 != null)
      for (BluetoothGattIncludedService bluetoothGattIncludedService : arrayList1)
        this.mIncludedServices.add(new BluetoothGattService(null, bluetoothGattIncludedService.getUuid(), bluetoothGattIncludedService.getInstanceId(), bluetoothGattIncludedService.getType()));  
  }
  
  public BluetoothGattService(UUID paramUUID, int paramInt) {
    this.mDevice = null;
    this.mUuid = paramUUID;
    this.mInstanceId = 0;
    this.mServiceType = paramInt;
    this.mCharacteristics = new ArrayList<>();
    this.mIncludedServices = new ArrayList<>();
  }
  
  public BluetoothGattService(UUID paramUUID, int paramInt1, int paramInt2) {
    this.mDevice = null;
    this.mUuid = paramUUID;
    this.mInstanceId = paramInt1;
    this.mServiceType = paramInt2;
    this.mCharacteristics = new ArrayList<>();
    this.mIncludedServices = new ArrayList<>();
  }
  
  public boolean addCharacteristic(BluetoothGattCharacteristic paramBluetoothGattCharacteristic) {
    this.mCharacteristics.add(paramBluetoothGattCharacteristic);
    paramBluetoothGattCharacteristic.setService(this);
    return true;
  }
  
  public void addIncludedService(BluetoothGattService paramBluetoothGattService) {
    this.mIncludedServices.add(paramBluetoothGattService);
  }
  
  public boolean addService(BluetoothGattService paramBluetoothGattService) {
    this.mIncludedServices.add(paramBluetoothGattService);
    return true;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public BluetoothGattCharacteristic getCharacteristic(UUID paramUUID) {
    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : this.mCharacteristics) {
      if (paramUUID.equals(bluetoothGattCharacteristic.getUuid()))
        return bluetoothGattCharacteristic; 
    } 
    return null;
  }
  
  BluetoothGattCharacteristic getCharacteristic(UUID paramUUID, int paramInt) {
    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : this.mCharacteristics) {
      if (paramUUID.equals(bluetoothGattCharacteristic.getUuid()) && bluetoothGattCharacteristic.getInstanceId() == paramInt)
        return bluetoothGattCharacteristic; 
    } 
    return null;
  }
  
  public List<BluetoothGattCharacteristic> getCharacteristics() {
    return this.mCharacteristics;
  }
  
  BluetoothDevice getDevice() {
    return this.mDevice;
  }
  
  int getHandles() {
    return this.mHandles;
  }
  
  public List<BluetoothGattService> getIncludedServices() {
    return this.mIncludedServices;
  }
  
  public int getInstanceId() {
    return this.mInstanceId;
  }
  
  public int getType() {
    return this.mServiceType;
  }
  
  public UUID getUuid() {
    return this.mUuid;
  }
  
  public boolean isAdvertisePreferred() {
    return this.mAdvertisePreferred;
  }
  
  public void setAdvertisePreferred(boolean paramBoolean) {
    this.mAdvertisePreferred = paramBoolean;
  }
  
  void setDevice(BluetoothDevice paramBluetoothDevice) {
    this.mDevice = paramBluetoothDevice;
  }
  
  public void setHandles(int paramInt) {
    this.mHandles = paramInt;
  }
  
  public void setInstanceId(int paramInt) {
    this.mInstanceId = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeParcelable((Parcelable)new ParcelUuid(this.mUuid), 0);
    paramParcel.writeInt(this.mInstanceId);
    paramParcel.writeInt(this.mServiceType);
    paramParcel.writeTypedList(this.mCharacteristics);
    ArrayList<BluetoothGattIncludedService> arrayList = new ArrayList(this.mIncludedServices.size());
    for (BluetoothGattService bluetoothGattService : this.mIncludedServices)
      arrayList.add(new BluetoothGattIncludedService(bluetoothGattService.getUuid(), bluetoothGattService.getInstanceId(), bluetoothGattService.getType())); 
    paramParcel.writeTypedList(arrayList);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothGattService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */