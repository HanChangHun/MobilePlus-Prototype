package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

public class BandDescriptor implements Parcelable {
  public static final Parcelable.Creator<BandDescriptor> CREATOR = new Parcelable.Creator<BandDescriptor>() {
      public RadioManager.BandDescriptor createFromParcel(Parcel param2Parcel) {
        StringBuilder stringBuilder;
        int i = RadioManager.BandDescriptor.lookupTypeFromParcel(param2Parcel);
        if (i != 0)
          if (i != 1 && i != 2) {
            if (i != 3) {
              stringBuilder = new StringBuilder();
              stringBuilder.append("Unsupported band: ");
              stringBuilder.append(i);
              throw new IllegalArgumentException(stringBuilder.toString());
            } 
          } else {
            return new RadioManager.FmBandDescriptor((Parcel)stringBuilder, null);
          }  
        return new RadioManager.AmBandDescriptor((Parcel)stringBuilder, null);
      }
      
      public RadioManager.BandDescriptor[] newArray(int param2Int) {
        return new RadioManager.BandDescriptor[param2Int];
      }
    };
  
  private final int mLowerLimit;
  
  private final int mRegion;
  
  private final int mSpacing;
  
  private final int mType;
  
  private final int mUpperLimit;
  
  BandDescriptor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt2 == 0 || paramInt2 == 1 || paramInt2 == 2 || paramInt2 == 3) {
      this.mRegion = paramInt1;
      this.mType = paramInt2;
      this.mLowerLimit = paramInt3;
      this.mUpperLimit = paramInt4;
      this.mSpacing = paramInt5;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unsupported band: ");
    stringBuilder.append(paramInt2);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private BandDescriptor(Parcel paramParcel) {
    this.mRegion = paramParcel.readInt();
    this.mType = paramParcel.readInt();
    this.mLowerLimit = paramParcel.readInt();
    this.mUpperLimit = paramParcel.readInt();
    this.mSpacing = paramParcel.readInt();
  }
  
  private static int lookupTypeFromParcel(Parcel paramParcel) {
    int i = paramParcel.dataPosition();
    paramParcel.readInt();
    int j = paramParcel.readInt();
    paramParcel.setDataPosition(i);
    return j;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof BandDescriptor))
      return false; 
    paramObject = paramObject;
    return (this.mRegion != paramObject.getRegion()) ? false : ((this.mType != paramObject.getType()) ? false : ((this.mLowerLimit != paramObject.getLowerLimit()) ? false : ((this.mUpperLimit != paramObject.getUpperLimit()) ? false : (!(this.mSpacing != paramObject.getSpacing())))));
  }
  
  public int getLowerLimit() {
    return this.mLowerLimit;
  }
  
  public int getRegion() {
    return this.mRegion;
  }
  
  public int getSpacing() {
    return this.mSpacing;
  }
  
  public int getType() {
    return this.mType;
  }
  
  public int getUpperLimit() {
    return this.mUpperLimit;
  }
  
  public int hashCode() {
    return ((((1 * 31 + this.mRegion) * 31 + this.mType) * 31 + this.mLowerLimit) * 31 + this.mUpperLimit) * 31 + this.mSpacing;
  }
  
  public boolean isAmBand() {
    int i = this.mType;
    return (i == 0 || i == 3);
  }
  
  public boolean isFmBand() {
    int i = this.mType;
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (i != 1)
      if (i == 2) {
        bool2 = bool1;
      } else {
        bool2 = false;
      }  
    return bool2;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("BandDescriptor [mRegion=");
    stringBuilder.append(this.mRegion);
    stringBuilder.append(", mType=");
    stringBuilder.append(this.mType);
    stringBuilder.append(", mLowerLimit=");
    stringBuilder.append(this.mLowerLimit);
    stringBuilder.append(", mUpperLimit=");
    stringBuilder.append(this.mUpperLimit);
    stringBuilder.append(", mSpacing=");
    stringBuilder.append(this.mSpacing);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mRegion);
    paramParcel.writeInt(this.mType);
    paramParcel.writeInt(this.mLowerLimit);
    paramParcel.writeInt(this.mUpperLimit);
    paramParcel.writeInt(this.mSpacing);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$BandDescriptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */