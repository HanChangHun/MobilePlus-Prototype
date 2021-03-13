package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public class BandConfig implements Parcelable {
  public static final Parcelable.Creator<BandConfig> CREATOR = new Parcelable.Creator<BandConfig>() {
      public RadioManager.BandConfig createFromParcel(Parcel param2Parcel) {
        StringBuilder stringBuilder;
        int i = RadioManager.BandDescriptor.access$100(param2Parcel);
        if (i != 0)
          if (i != 1 && i != 2) {
            if (i != 3) {
              stringBuilder = new StringBuilder();
              stringBuilder.append("Unsupported band: ");
              stringBuilder.append(i);
              throw new IllegalArgumentException(stringBuilder.toString());
            } 
          } else {
            return new RadioManager.FmBandConfig((Parcel)stringBuilder, null);
          }  
        return new RadioManager.AmBandConfig((Parcel)stringBuilder, null);
      }
      
      public RadioManager.BandConfig[] newArray(int param2Int) {
        return new RadioManager.BandConfig[param2Int];
      }
    };
  
  final RadioManager.BandDescriptor mDescriptor;
  
  BandConfig(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    this.mDescriptor = new RadioManager.BandDescriptor(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  BandConfig(RadioManager.BandDescriptor paramBandDescriptor) {
    Objects.requireNonNull(paramBandDescriptor);
    this.mDescriptor = paramBandDescriptor;
  }
  
  private BandConfig(Parcel paramParcel) {
    this.mDescriptor = new RadioManager.BandDescriptor(paramParcel, null);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool1;
    boolean bool2;
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof BandConfig))
      return false; 
    paramObject = ((BandConfig)paramObject).getDescriptor();
    if (this.mDescriptor == null) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramObject == null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (bool1 != bool2)
      return false; 
    RadioManager.BandDescriptor bandDescriptor = this.mDescriptor;
    return !(bandDescriptor != null && !bandDescriptor.equals(paramObject));
  }
  
  RadioManager.BandDescriptor getDescriptor() {
    return this.mDescriptor;
  }
  
  public int getLowerLimit() {
    return this.mDescriptor.getLowerLimit();
  }
  
  public int getRegion() {
    return this.mDescriptor.getRegion();
  }
  
  public int getSpacing() {
    return this.mDescriptor.getSpacing();
  }
  
  public int getType() {
    return this.mDescriptor.getType();
  }
  
  public int getUpperLimit() {
    return this.mDescriptor.getUpperLimit();
  }
  
  public int hashCode() {
    return 1 * 31 + this.mDescriptor.hashCode();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("BandConfig [ ");
    stringBuilder.append(this.mDescriptor.toString());
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    this.mDescriptor.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$BandConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */