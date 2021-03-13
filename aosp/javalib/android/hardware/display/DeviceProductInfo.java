package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

public final class DeviceProductInfo implements Parcelable {
  public static final Parcelable.Creator<DeviceProductInfo> CREATOR = new Parcelable.Creator<DeviceProductInfo>() {
      public DeviceProductInfo createFromParcel(Parcel param1Parcel) {
        return new DeviceProductInfo(param1Parcel);
      }
      
      public DeviceProductInfo[] newArray(int param1Int) {
        return new DeviceProductInfo[param1Int];
      }
    };
  
  private final ManufactureDate mManufactureDate;
  
  private final String mManufacturerPnpId;
  
  private final Integer mModelYear;
  
  private final String mName;
  
  private final String mProductId;
  
  private final int[] mRelativeAddress;
  
  private DeviceProductInfo(Parcel paramParcel) {
    this.mName = paramParcel.readString();
    this.mManufacturerPnpId = paramParcel.readString();
    this.mProductId = (String)paramParcel.readValue(null);
    this.mModelYear = (Integer)paramParcel.readValue(null);
    this.mManufactureDate = (ManufactureDate)paramParcel.readValue(null);
    this.mRelativeAddress = paramParcel.createIntArray();
  }
  
  public DeviceProductInfo(String paramString1, String paramString2, String paramString3, Integer paramInteger, ManufactureDate paramManufactureDate, int[] paramArrayOfint) {
    this.mName = paramString1;
    this.mManufacturerPnpId = paramString2;
    this.mProductId = paramString3;
    this.mModelYear = paramInteger;
    this.mManufactureDate = paramManufactureDate;
    this.mRelativeAddress = paramArrayOfint;
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
    if (!Objects.equals(this.mName, ((DeviceProductInfo)paramObject).mName) || !Objects.equals(this.mManufacturerPnpId, ((DeviceProductInfo)paramObject).mManufacturerPnpId) || !Objects.equals(this.mProductId, ((DeviceProductInfo)paramObject).mProductId) || !Objects.equals(this.mModelYear, ((DeviceProductInfo)paramObject).mModelYear) || !Objects.equals(this.mManufactureDate, ((DeviceProductInfo)paramObject).mManufactureDate) || !Arrays.equals(this.mRelativeAddress, ((DeviceProductInfo)paramObject).mRelativeAddress))
      bool = false; 
    return bool;
  }
  
  public ManufactureDate getManufactureDate() {
    return this.mManufactureDate;
  }
  
  public String getManufacturerPnpId() {
    return this.mManufacturerPnpId;
  }
  
  public Integer getModelYear() {
    return this.mModelYear;
  }
  
  public String getName() {
    return this.mName;
  }
  
  public String getProductId() {
    return this.mProductId;
  }
  
  public int[] getRelativeAddress() {
    return this.mRelativeAddress;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mName, this.mManufacturerPnpId, this.mProductId, this.mModelYear, this.mManufactureDate, Integer.valueOf(Arrays.hashCode(this.mRelativeAddress)) });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DeviceProductInfo{name=");
    stringBuilder.append(this.mName);
    stringBuilder.append(", manufacturerPnpId=");
    stringBuilder.append(this.mManufacturerPnpId);
    stringBuilder.append(", productId=");
    stringBuilder.append(this.mProductId);
    stringBuilder.append(", modelYear=");
    stringBuilder.append(this.mModelYear);
    stringBuilder.append(", manufactureDate=");
    stringBuilder.append(this.mManufactureDate);
    stringBuilder.append(", relativeAddress=");
    stringBuilder.append(Arrays.toString(this.mRelativeAddress));
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mName);
    paramParcel.writeString(this.mManufacturerPnpId);
    paramParcel.writeValue(this.mProductId);
    paramParcel.writeValue(this.mModelYear);
    paramParcel.writeValue(this.mManufactureDate);
    paramParcel.writeIntArray(this.mRelativeAddress);
  }
  
  public static class ManufactureDate implements Parcelable {
    public static final Parcelable.Creator<ManufactureDate> CREATOR = new Parcelable.Creator<ManufactureDate>() {
        public DeviceProductInfo.ManufactureDate createFromParcel(Parcel param2Parcel) {
          return new DeviceProductInfo.ManufactureDate(param2Parcel);
        }
        
        public DeviceProductInfo.ManufactureDate[] newArray(int param2Int) {
          return new DeviceProductInfo.ManufactureDate[param2Int];
        }
      };
    
    private final Integer mWeek;
    
    private final Integer mYear;
    
    protected ManufactureDate(Parcel param1Parcel) {
      this.mWeek = (Integer)param1Parcel.readValue(null);
      this.mYear = (Integer)param1Parcel.readValue(null);
    }
    
    public ManufactureDate(Integer param1Integer1, Integer param1Integer2) {
      this.mWeek = param1Integer1;
      this.mYear = param1Integer2;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if (!Objects.equals(this.mWeek, ((ManufactureDate)param1Object).mWeek) || !Objects.equals(this.mYear, ((ManufactureDate)param1Object).mYear))
        bool = false; 
      return bool;
    }
    
    public Integer getWeek() {
      return this.mWeek;
    }
    
    public Integer getYear() {
      return this.mYear;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { this.mWeek, this.mYear });
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ManufactureDate{week=");
      stringBuilder.append(this.mWeek);
      stringBuilder.append(", year=");
      stringBuilder.append(this.mYear);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeValue(this.mWeek);
      param1Parcel.writeValue(this.mYear);
    }
  }
  
  class null implements Parcelable.Creator<ManufactureDate> {
    public DeviceProductInfo.ManufactureDate createFromParcel(Parcel param1Parcel) {
      return new DeviceProductInfo.ManufactureDate(param1Parcel);
    }
    
    public DeviceProductInfo.ManufactureDate[] newArray(int param1Int) {
      return new DeviceProductInfo.ManufactureDate[param1Int];
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DeviceProductInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */