package android.bluetooth.le;

import android.bluetooth.BluetoothAdapter;
import android.os.ParcelUuid;

public final class Builder {
  private String mDeviceAddress;
  
  private String mDeviceName;
  
  private byte[] mManufacturerData;
  
  private byte[] mManufacturerDataMask;
  
  private int mManufacturerId = -1;
  
  private byte[] mServiceData;
  
  private byte[] mServiceDataMask;
  
  private ParcelUuid mServiceDataUuid;
  
  private ParcelUuid mServiceSolicitationUuid;
  
  private ParcelUuid mServiceSolicitationUuidMask;
  
  private ParcelUuid mServiceUuid;
  
  private ParcelUuid mUuidMask;
  
  public ScanFilter build() {
    return new ScanFilter(this.mDeviceName, this.mDeviceAddress, this.mServiceUuid, this.mUuidMask, this.mServiceSolicitationUuid, this.mServiceSolicitationUuidMask, this.mServiceDataUuid, this.mServiceData, this.mServiceDataMask, this.mManufacturerId, this.mManufacturerData, this.mManufacturerDataMask, null);
  }
  
  public Builder setDeviceAddress(String paramString) {
    if (paramString == null || BluetoothAdapter.checkBluetoothAddress(paramString)) {
      this.mDeviceAddress = paramString;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("invalid device address ");
    stringBuilder.append(paramString);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder setDeviceName(String paramString) {
    this.mDeviceName = paramString;
    return this;
  }
  
  public Builder setManufacturerData(int paramInt, byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null || paramInt >= 0) {
      this.mManufacturerId = paramInt;
      this.mManufacturerData = paramArrayOfbyte;
      this.mManufacturerDataMask = null;
      return this;
    } 
    throw new IllegalArgumentException("invalid manufacture id");
  }
  
  public Builder setManufacturerData(int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    if (paramArrayOfbyte1 == null || paramInt >= 0) {
      byte[] arrayOfByte = this.mManufacturerDataMask;
      if (arrayOfByte != null) {
        byte[] arrayOfByte1 = this.mManufacturerData;
        if (arrayOfByte1 != null) {
          if (arrayOfByte1.length != arrayOfByte.length)
            throw new IllegalArgumentException("size mismatch for manufacturerData and manufacturerDataMask"); 
        } else {
          throw new IllegalArgumentException("manufacturerData is null while manufacturerDataMask is not null");
        } 
      } 
      this.mManufacturerId = paramInt;
      this.mManufacturerData = paramArrayOfbyte1;
      this.mManufacturerDataMask = paramArrayOfbyte2;
      return this;
    } 
    throw new IllegalArgumentException("invalid manufacture id");
  }
  
  public Builder setServiceData(ParcelUuid paramParcelUuid, byte[] paramArrayOfbyte) {
    if (paramParcelUuid != null) {
      this.mServiceDataUuid = paramParcelUuid;
      this.mServiceData = paramArrayOfbyte;
      this.mServiceDataMask = null;
      return this;
    } 
    throw new IllegalArgumentException("serviceDataUuid is null");
  }
  
  public Builder setServiceData(ParcelUuid paramParcelUuid, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    if (paramParcelUuid != null) {
      byte[] arrayOfByte = this.mServiceDataMask;
      if (arrayOfByte != null) {
        byte[] arrayOfByte1 = this.mServiceData;
        if (arrayOfByte1 != null) {
          if (arrayOfByte1.length != arrayOfByte.length)
            throw new IllegalArgumentException("size mismatch for service data and service data mask"); 
        } else {
          throw new IllegalArgumentException("serviceData is null while serviceDataMask is not null");
        } 
      } 
      this.mServiceDataUuid = paramParcelUuid;
      this.mServiceData = paramArrayOfbyte1;
      this.mServiceDataMask = paramArrayOfbyte2;
      return this;
    } 
    throw new IllegalArgumentException("serviceDataUuid is null");
  }
  
  public Builder setServiceSolicitationUuid(ParcelUuid paramParcelUuid) {
    this.mServiceSolicitationUuid = paramParcelUuid;
    if (paramParcelUuid == null)
      this.mServiceSolicitationUuidMask = null; 
    return this;
  }
  
  public Builder setServiceSolicitationUuid(ParcelUuid paramParcelUuid1, ParcelUuid paramParcelUuid2) {
    if (paramParcelUuid2 == null || paramParcelUuid1 != null) {
      this.mServiceSolicitationUuid = paramParcelUuid1;
      this.mServiceSolicitationUuidMask = paramParcelUuid2;
      return this;
    } 
    throw new IllegalArgumentException("SolicitationUuid is null while SolicitationUuidMask is not null!");
  }
  
  public Builder setServiceUuid(ParcelUuid paramParcelUuid) {
    this.mServiceUuid = paramParcelUuid;
    this.mUuidMask = null;
    return this;
  }
  
  public Builder setServiceUuid(ParcelUuid paramParcelUuid1, ParcelUuid paramParcelUuid2) {
    if (this.mUuidMask == null || this.mServiceUuid != null) {
      this.mServiceUuid = paramParcelUuid1;
      this.mUuidMask = paramParcelUuid2;
      return this;
    } 
    throw new IllegalArgumentException("uuid is null while uuidMask is not null!");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/ScanFilter$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */