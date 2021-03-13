package android.bluetooth.le;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import com.android.internal.util.BitUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class ScanFilter implements Parcelable {
  public static final Parcelable.Creator<ScanFilter> CREATOR;
  
  public static final ScanFilter EMPTY = (new Builder()).build();
  
  private final String mDeviceAddress;
  
  private final String mDeviceName;
  
  private final byte[] mManufacturerData;
  
  private final byte[] mManufacturerDataMask;
  
  private final int mManufacturerId;
  
  private final byte[] mServiceData;
  
  private final byte[] mServiceDataMask;
  
  private final ParcelUuid mServiceDataUuid;
  
  private final ParcelUuid mServiceSolicitationUuid;
  
  private final ParcelUuid mServiceSolicitationUuidMask;
  
  private final ParcelUuid mServiceUuid;
  
  private final ParcelUuid mServiceUuidMask;
  
  static {
    CREATOR = new Parcelable.Creator<ScanFilter>() {
        public ScanFilter createFromParcel(Parcel param1Parcel) {
          ScanFilter.Builder builder = new ScanFilter.Builder();
          if (param1Parcel.readInt() == 1)
            builder.setDeviceName(param1Parcel.readString()); 
          if (param1Parcel.readInt() == 1)
            builder.setDeviceAddress(param1Parcel.readString()); 
          if (param1Parcel.readInt() == 1) {
            ParcelUuid parcelUuid = (ParcelUuid)param1Parcel.readParcelable(ParcelUuid.class.getClassLoader());
            builder.setServiceUuid(parcelUuid);
            if (param1Parcel.readInt() == 1)
              builder.setServiceUuid(parcelUuid, (ParcelUuid)param1Parcel.readParcelable(ParcelUuid.class.getClassLoader())); 
          } 
          if (param1Parcel.readInt() == 1) {
            ParcelUuid parcelUuid = (ParcelUuid)param1Parcel.readParcelable(ParcelUuid.class.getClassLoader());
            builder.setServiceSolicitationUuid(parcelUuid);
            if (param1Parcel.readInt() == 1)
              builder.setServiceSolicitationUuid(parcelUuid, (ParcelUuid)param1Parcel.readParcelable(ParcelUuid.class.getClassLoader())); 
          } 
          if (param1Parcel.readInt() == 1) {
            ParcelUuid parcelUuid = (ParcelUuid)param1Parcel.readParcelable(ParcelUuid.class.getClassLoader());
            if (param1Parcel.readInt() == 1) {
              byte[] arrayOfByte = new byte[param1Parcel.readInt()];
              param1Parcel.readByteArray(arrayOfByte);
              if (param1Parcel.readInt() == 0) {
                builder.setServiceData(parcelUuid, arrayOfByte);
              } else {
                byte[] arrayOfByte1 = new byte[param1Parcel.readInt()];
                param1Parcel.readByteArray(arrayOfByte1);
                builder.setServiceData(parcelUuid, arrayOfByte, arrayOfByte1);
              } 
            } 
          } 
          int i = param1Parcel.readInt();
          if (param1Parcel.readInt() == 1) {
            byte[] arrayOfByte = new byte[param1Parcel.readInt()];
            param1Parcel.readByteArray(arrayOfByte);
            if (param1Parcel.readInt() == 0) {
              builder.setManufacturerData(i, arrayOfByte);
            } else {
              byte[] arrayOfByte1 = new byte[param1Parcel.readInt()];
              param1Parcel.readByteArray(arrayOfByte1);
              builder.setManufacturerData(i, arrayOfByte, arrayOfByte1);
            } 
          } 
          return builder.build();
        }
        
        public ScanFilter[] newArray(int param1Int) {
          return new ScanFilter[param1Int];
        }
      };
  }
  
  private ScanFilter(String paramString1, String paramString2, ParcelUuid paramParcelUuid1, ParcelUuid paramParcelUuid2, ParcelUuid paramParcelUuid3, ParcelUuid paramParcelUuid4, ParcelUuid paramParcelUuid5, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4) {
    this.mDeviceName = paramString1;
    this.mServiceUuid = paramParcelUuid1;
    this.mServiceUuidMask = paramParcelUuid2;
    this.mServiceSolicitationUuid = paramParcelUuid3;
    this.mServiceSolicitationUuidMask = paramParcelUuid4;
    this.mDeviceAddress = paramString2;
    this.mServiceDataUuid = paramParcelUuid5;
    this.mServiceData = paramArrayOfbyte1;
    this.mServiceDataMask = paramArrayOfbyte2;
    this.mManufacturerId = paramInt;
    this.mManufacturerData = paramArrayOfbyte3;
    this.mManufacturerDataMask = paramArrayOfbyte4;
  }
  
  private boolean matchesPartialData(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3) {
    if (paramArrayOfbyte3 == null || paramArrayOfbyte3.length < paramArrayOfbyte1.length)
      return false; 
    if (paramArrayOfbyte2 == null) {
      for (byte b1 = 0; b1 < paramArrayOfbyte1.length; b1++) {
        if (paramArrayOfbyte3[b1] != paramArrayOfbyte1[b1])
          return false; 
      } 
      return true;
    } 
    for (byte b = 0; b < paramArrayOfbyte1.length; b++) {
      if ((paramArrayOfbyte2[b] & paramArrayOfbyte3[b]) != (paramArrayOfbyte2[b] & paramArrayOfbyte1[b]))
        return false; 
    } 
    return true;
  }
  
  private static boolean matchesServiceSolicitationUuid(UUID paramUUID1, UUID paramUUID2, UUID paramUUID3) {
    return BitUtils.maskedEquals(paramUUID3, paramUUID1, paramUUID2);
  }
  
  private static boolean matchesServiceSolicitationUuids(ParcelUuid paramParcelUuid1, ParcelUuid paramParcelUuid2, List<ParcelUuid> paramList) {
    if (paramParcelUuid1 == null)
      return true; 
    if (paramList == null)
      return false; 
    for (ParcelUuid parcelUuid : paramList) {
      UUID uUID;
      if (paramParcelUuid2 == null) {
        paramList = null;
      } else {
        uUID = paramParcelUuid2.getUuid();
      } 
      if (matchesServiceUuid(paramParcelUuid1.getUuid(), uUID, parcelUuid.getUuid()))
        return true; 
    } 
    return false;
  }
  
  private static boolean matchesServiceUuid(UUID paramUUID1, UUID paramUUID2, UUID paramUUID3) {
    return BitUtils.maskedEquals(paramUUID3, paramUUID1, paramUUID2);
  }
  
  public static boolean matchesServiceUuids(ParcelUuid paramParcelUuid1, ParcelUuid paramParcelUuid2, List<ParcelUuid> paramList) {
    if (paramParcelUuid1 == null)
      return true; 
    if (paramList == null)
      return false; 
    for (ParcelUuid parcelUuid : paramList) {
      UUID uUID;
      if (paramParcelUuid2 == null) {
        paramList = null;
      } else {
        uUID = paramParcelUuid2.getUuid();
      } 
      if (matchesServiceUuid(paramParcelUuid1.getUuid(), uUID, parcelUuid.getUuid()))
        return true; 
    } 
    return false;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (!Objects.equals(this.mDeviceName, ((ScanFilter)paramObject).mDeviceName) || !Objects.equals(this.mDeviceAddress, ((ScanFilter)paramObject).mDeviceAddress) || this.mManufacturerId != ((ScanFilter)paramObject).mManufacturerId || !Objects.deepEquals(this.mManufacturerData, ((ScanFilter)paramObject).mManufacturerData) || !Objects.deepEquals(this.mManufacturerDataMask, ((ScanFilter)paramObject).mManufacturerDataMask) || !Objects.equals(this.mServiceDataUuid, ((ScanFilter)paramObject).mServiceDataUuid) || !Objects.deepEquals(this.mServiceData, ((ScanFilter)paramObject).mServiceData) || !Objects.deepEquals(this.mServiceDataMask, ((ScanFilter)paramObject).mServiceDataMask) || !Objects.equals(this.mServiceUuid, ((ScanFilter)paramObject).mServiceUuid) || !Objects.equals(this.mServiceUuidMask, ((ScanFilter)paramObject).mServiceUuidMask) || !Objects.equals(this.mServiceSolicitationUuid, ((ScanFilter)paramObject).mServiceSolicitationUuid) || !Objects.equals(this.mServiceSolicitationUuidMask, ((ScanFilter)paramObject).mServiceSolicitationUuidMask))
      bool = false; 
    return bool;
  }
  
  public String getDeviceAddress() {
    return this.mDeviceAddress;
  }
  
  public String getDeviceName() {
    return this.mDeviceName;
  }
  
  public byte[] getManufacturerData() {
    return this.mManufacturerData;
  }
  
  public byte[] getManufacturerDataMask() {
    return this.mManufacturerDataMask;
  }
  
  public int getManufacturerId() {
    return this.mManufacturerId;
  }
  
  public byte[] getServiceData() {
    return this.mServiceData;
  }
  
  public byte[] getServiceDataMask() {
    return this.mServiceDataMask;
  }
  
  public ParcelUuid getServiceDataUuid() {
    return this.mServiceDataUuid;
  }
  
  public ParcelUuid getServiceSolicitationUuid() {
    return this.mServiceSolicitationUuid;
  }
  
  public ParcelUuid getServiceSolicitationUuidMask() {
    return this.mServiceSolicitationUuidMask;
  }
  
  public ParcelUuid getServiceUuid() {
    return this.mServiceUuid;
  }
  
  public ParcelUuid getServiceUuidMask() {
    return this.mServiceUuidMask;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { 
          this.mDeviceName, this.mDeviceAddress, Integer.valueOf(this.mManufacturerId), Integer.valueOf(Arrays.hashCode(this.mManufacturerData)), Integer.valueOf(Arrays.hashCode(this.mManufacturerDataMask)), this.mServiceDataUuid, Integer.valueOf(Arrays.hashCode(this.mServiceData)), Integer.valueOf(Arrays.hashCode(this.mServiceDataMask)), this.mServiceUuid, this.mServiceUuidMask, 
          this.mServiceSolicitationUuid, this.mServiceSolicitationUuidMask });
  }
  
  public boolean isAllFieldsEmpty() {
    return EMPTY.equals(this);
  }
  
  public boolean matches(ScanResult paramScanResult) {
    if (paramScanResult == null)
      return false; 
    BluetoothDevice bluetoothDevice = paramScanResult.getDevice();
    String str2 = this.mDeviceAddress;
    if (str2 != null && (bluetoothDevice == null || !str2.equals(bluetoothDevice.getAddress())))
      return false; 
    ScanRecord scanRecord = paramScanResult.getScanRecord();
    if (scanRecord == null && (this.mDeviceName != null || this.mServiceUuid != null || this.mManufacturerData != null || this.mServiceData != null || this.mServiceSolicitationUuid != null))
      return false; 
    String str1 = this.mDeviceName;
    if (str1 != null && !str1.equals(scanRecord.getDeviceName()))
      return false; 
    ParcelUuid parcelUuid = this.mServiceUuid;
    if (parcelUuid != null && !matchesServiceUuids(parcelUuid, this.mServiceUuidMask, scanRecord.getServiceUuids()))
      return false; 
    parcelUuid = this.mServiceSolicitationUuid;
    if (parcelUuid != null && !matchesServiceSolicitationUuids(parcelUuid, this.mServiceSolicitationUuidMask, scanRecord.getServiceSolicitationUuids()))
      return false; 
    parcelUuid = this.mServiceDataUuid;
    if (parcelUuid != null && !matchesPartialData(this.mServiceData, this.mServiceDataMask, scanRecord.getServiceData(parcelUuid)))
      return false; 
    int i = this.mManufacturerId;
    return !(i >= 0 && !matchesPartialData(this.mManufacturerData, this.mManufacturerDataMask, scanRecord.getManufacturerSpecificData(i)));
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("BluetoothLeScanFilter [mDeviceName=");
    stringBuilder.append(this.mDeviceName);
    stringBuilder.append(", mDeviceAddress=");
    stringBuilder.append(this.mDeviceAddress);
    stringBuilder.append(", mUuid=");
    stringBuilder.append(this.mServiceUuid);
    stringBuilder.append(", mUuidMask=");
    stringBuilder.append(this.mServiceUuidMask);
    stringBuilder.append(", mServiceSolicitationUuid=");
    stringBuilder.append(this.mServiceSolicitationUuid);
    stringBuilder.append(", mServiceSolicitationUuidMask=");
    stringBuilder.append(this.mServiceSolicitationUuidMask);
    stringBuilder.append(", mServiceDataUuid=");
    stringBuilder.append(Objects.toString(this.mServiceDataUuid));
    stringBuilder.append(", mServiceData=");
    stringBuilder.append(Arrays.toString(this.mServiceData));
    stringBuilder.append(", mServiceDataMask=");
    stringBuilder.append(Arrays.toString(this.mServiceDataMask));
    stringBuilder.append(", mManufacturerId=");
    stringBuilder.append(this.mManufacturerId);
    stringBuilder.append(", mManufacturerData=");
    stringBuilder.append(Arrays.toString(this.mManufacturerData));
    stringBuilder.append(", mManufacturerDataMask=");
    stringBuilder.append(Arrays.toString(this.mManufacturerDataMask));
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    boolean bool2;
    String str = this.mDeviceName;
    boolean bool1 = false;
    if (str == null) {
      bool2 = false;
    } else {
      bool2 = true;
    } 
    paramParcel.writeInt(bool2);
    str = this.mDeviceName;
    if (str != null)
      paramParcel.writeString(str); 
    if (this.mDeviceAddress == null) {
      bool2 = false;
    } else {
      bool2 = true;
    } 
    paramParcel.writeInt(bool2);
    str = this.mDeviceAddress;
    if (str != null)
      paramParcel.writeString(str); 
    if (this.mServiceUuid == null) {
      bool2 = false;
    } else {
      bool2 = true;
    } 
    paramParcel.writeInt(bool2);
    ParcelUuid parcelUuid = this.mServiceUuid;
    if (parcelUuid != null) {
      paramParcel.writeParcelable((Parcelable)parcelUuid, paramInt);
      if (this.mServiceUuidMask == null) {
        bool2 = false;
      } else {
        bool2 = true;
      } 
      paramParcel.writeInt(bool2);
      parcelUuid = this.mServiceUuidMask;
      if (parcelUuid != null)
        paramParcel.writeParcelable((Parcelable)parcelUuid, paramInt); 
    } 
    if (this.mServiceSolicitationUuid == null) {
      bool2 = false;
    } else {
      bool2 = true;
    } 
    paramParcel.writeInt(bool2);
    parcelUuid = this.mServiceSolicitationUuid;
    if (parcelUuid != null) {
      paramParcel.writeParcelable((Parcelable)parcelUuid, paramInt);
      if (this.mServiceSolicitationUuidMask == null) {
        bool2 = false;
      } else {
        bool2 = true;
      } 
      paramParcel.writeInt(bool2);
      parcelUuid = this.mServiceSolicitationUuidMask;
      if (parcelUuid != null)
        paramParcel.writeParcelable((Parcelable)parcelUuid, paramInt); 
    } 
    if (this.mServiceDataUuid == null) {
      bool2 = false;
    } else {
      bool2 = true;
    } 
    paramParcel.writeInt(bool2);
    parcelUuid = this.mServiceDataUuid;
    if (parcelUuid != null) {
      paramParcel.writeParcelable((Parcelable)parcelUuid, paramInt);
      if (this.mServiceData == null) {
        paramInt = 0;
      } else {
        paramInt = 1;
      } 
      paramParcel.writeInt(paramInt);
      byte[] arrayOfByte1 = this.mServiceData;
      if (arrayOfByte1 != null) {
        paramParcel.writeInt(arrayOfByte1.length);
        paramParcel.writeByteArray(this.mServiceData);
        if (this.mServiceDataMask == null) {
          paramInt = 0;
        } else {
          paramInt = 1;
        } 
        paramParcel.writeInt(paramInt);
        arrayOfByte1 = this.mServiceDataMask;
        if (arrayOfByte1 != null) {
          paramParcel.writeInt(arrayOfByte1.length);
          paramParcel.writeByteArray(this.mServiceDataMask);
        } 
      } 
    } 
    paramParcel.writeInt(this.mManufacturerId);
    if (this.mManufacturerData == null) {
      paramInt = 0;
    } else {
      paramInt = 1;
    } 
    paramParcel.writeInt(paramInt);
    byte[] arrayOfByte = this.mManufacturerData;
    if (arrayOfByte != null) {
      paramParcel.writeInt(arrayOfByte.length);
      paramParcel.writeByteArray(this.mManufacturerData);
      if (this.mManufacturerDataMask == null) {
        paramInt = bool1;
      } else {
        paramInt = 1;
      } 
      paramParcel.writeInt(paramInt);
      arrayOfByte = this.mManufacturerDataMask;
      if (arrayOfByte != null) {
        paramParcel.writeInt(arrayOfByte.length);
        paramParcel.writeByteArray(this.mManufacturerDataMask);
      } 
    } 
  }
  
  public static final class Builder {
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
      return new ScanFilter(this.mDeviceName, this.mDeviceAddress, this.mServiceUuid, this.mUuidMask, this.mServiceSolicitationUuid, this.mServiceSolicitationUuidMask, this.mServiceDataUuid, this.mServiceData, this.mServiceDataMask, this.mManufacturerId, this.mManufacturerData, this.mManufacturerDataMask);
    }
    
    public Builder setDeviceAddress(String param1String) {
      if (param1String == null || BluetoothAdapter.checkBluetoothAddress(param1String)) {
        this.mDeviceAddress = param1String;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("invalid device address ");
      stringBuilder.append(param1String);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder setDeviceName(String param1String) {
      this.mDeviceName = param1String;
      return this;
    }
    
    public Builder setManufacturerData(int param1Int, byte[] param1ArrayOfbyte) {
      if (param1ArrayOfbyte == null || param1Int >= 0) {
        this.mManufacturerId = param1Int;
        this.mManufacturerData = param1ArrayOfbyte;
        this.mManufacturerDataMask = null;
        return this;
      } 
      throw new IllegalArgumentException("invalid manufacture id");
    }
    
    public Builder setManufacturerData(int param1Int, byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2) {
      if (param1ArrayOfbyte1 == null || param1Int >= 0) {
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
        this.mManufacturerId = param1Int;
        this.mManufacturerData = param1ArrayOfbyte1;
        this.mManufacturerDataMask = param1ArrayOfbyte2;
        return this;
      } 
      throw new IllegalArgumentException("invalid manufacture id");
    }
    
    public Builder setServiceData(ParcelUuid param1ParcelUuid, byte[] param1ArrayOfbyte) {
      if (param1ParcelUuid != null) {
        this.mServiceDataUuid = param1ParcelUuid;
        this.mServiceData = param1ArrayOfbyte;
        this.mServiceDataMask = null;
        return this;
      } 
      throw new IllegalArgumentException("serviceDataUuid is null");
    }
    
    public Builder setServiceData(ParcelUuid param1ParcelUuid, byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2) {
      if (param1ParcelUuid != null) {
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
        this.mServiceDataUuid = param1ParcelUuid;
        this.mServiceData = param1ArrayOfbyte1;
        this.mServiceDataMask = param1ArrayOfbyte2;
        return this;
      } 
      throw new IllegalArgumentException("serviceDataUuid is null");
    }
    
    public Builder setServiceSolicitationUuid(ParcelUuid param1ParcelUuid) {
      this.mServiceSolicitationUuid = param1ParcelUuid;
      if (param1ParcelUuid == null)
        this.mServiceSolicitationUuidMask = null; 
      return this;
    }
    
    public Builder setServiceSolicitationUuid(ParcelUuid param1ParcelUuid1, ParcelUuid param1ParcelUuid2) {
      if (param1ParcelUuid2 == null || param1ParcelUuid1 != null) {
        this.mServiceSolicitationUuid = param1ParcelUuid1;
        this.mServiceSolicitationUuidMask = param1ParcelUuid2;
        return this;
      } 
      throw new IllegalArgumentException("SolicitationUuid is null while SolicitationUuidMask is not null!");
    }
    
    public Builder setServiceUuid(ParcelUuid param1ParcelUuid) {
      this.mServiceUuid = param1ParcelUuid;
      this.mUuidMask = null;
      return this;
    }
    
    public Builder setServiceUuid(ParcelUuid param1ParcelUuid1, ParcelUuid param1ParcelUuid2) {
      if (this.mUuidMask == null || this.mServiceUuid != null) {
        this.mServiceUuid = param1ParcelUuid1;
        this.mUuidMask = param1ParcelUuid2;
        return this;
      } 
      throw new IllegalArgumentException("uuid is null while uuidMask is not null!");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/ScanFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */