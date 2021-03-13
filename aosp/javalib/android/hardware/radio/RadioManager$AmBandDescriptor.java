package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

public class AmBandDescriptor extends RadioManager.BandDescriptor {
  public static final Parcelable.Creator<AmBandDescriptor> CREATOR = new Parcelable.Creator<AmBandDescriptor>() {
      public RadioManager.AmBandDescriptor createFromParcel(Parcel param2Parcel) {
        return new RadioManager.AmBandDescriptor(param2Parcel);
      }
      
      public RadioManager.AmBandDescriptor[] newArray(int param2Int) {
        return new RadioManager.AmBandDescriptor[param2Int];
      }
    };
  
  private final boolean mStereo;
  
  public AmBandDescriptor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean) {
    super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    this.mStereo = paramBoolean;
  }
  
  private AmBandDescriptor(Parcel paramParcel) {
    super(paramParcel);
    byte b = paramParcel.readByte();
    boolean bool = true;
    if (b != 1)
      bool = false; 
    this.mStereo = bool;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!super.equals(paramObject))
      return false; 
    if (!(paramObject instanceof AmBandDescriptor))
      return false; 
    paramObject = paramObject;
    return !(this.mStereo != paramObject.isStereoSupported());
  }
  
  public int hashCode() {
    return super.hashCode() * 31 + this.mStereo;
  }
  
  public boolean isStereoSupported() {
    return this.mStereo;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AmBandDescriptor [ ");
    stringBuilder.append(super.toString());
    stringBuilder.append(" mStereo=");
    stringBuilder.append(this.mStereo);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeByte((byte)this.mStereo);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$AmBandDescriptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */