package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public class ManufactureDate implements Parcelable {
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
  
  protected ManufactureDate(Parcel paramParcel) {
    this.mWeek = (Integer)paramParcel.readValue(null);
    this.mYear = (Integer)paramParcel.readValue(null);
  }
  
  public ManufactureDate(Integer paramInteger1, Integer paramInteger2) {
    this.mWeek = paramInteger1;
    this.mYear = paramInteger2;
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
    if (!Objects.equals(this.mWeek, ((ManufactureDate)paramObject).mWeek) || !Objects.equals(this.mYear, ((ManufactureDate)paramObject).mYear))
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
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeValue(this.mWeek);
    paramParcel.writeValue(this.mYear);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DeviceProductInfo$ManufactureDate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */