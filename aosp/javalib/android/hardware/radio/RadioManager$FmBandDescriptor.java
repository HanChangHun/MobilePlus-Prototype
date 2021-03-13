package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

public class FmBandDescriptor extends RadioManager.BandDescriptor {
  public static final Parcelable.Creator<FmBandDescriptor> CREATOR = new Parcelable.Creator<FmBandDescriptor>() {
      public RadioManager.FmBandDescriptor createFromParcel(Parcel param2Parcel) {
        return new RadioManager.FmBandDescriptor(param2Parcel);
      }
      
      public RadioManager.FmBandDescriptor[] newArray(int param2Int) {
        return new RadioManager.FmBandDescriptor[param2Int];
      }
    };
  
  private final boolean mAf;
  
  private final boolean mEa;
  
  private final boolean mRds;
  
  private final boolean mStereo;
  
  private final boolean mTa;
  
  public FmBandDescriptor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5) {
    super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    this.mStereo = paramBoolean1;
    this.mRds = paramBoolean2;
    this.mTa = paramBoolean3;
    this.mAf = paramBoolean4;
    this.mEa = paramBoolean5;
  }
  
  private FmBandDescriptor(Parcel paramParcel) {
    super(paramParcel);
    byte b = paramParcel.readByte();
    boolean bool1 = false;
    if (b == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mStereo = bool2;
    if (paramParcel.readByte() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mRds = bool2;
    if (paramParcel.readByte() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mTa = bool2;
    if (paramParcel.readByte() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mAf = bool2;
    boolean bool2 = bool1;
    if (paramParcel.readByte() == 1)
      bool2 = true; 
    this.mEa = bool2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!super.equals(paramObject))
      return false; 
    if (!(paramObject instanceof FmBandDescriptor))
      return false; 
    paramObject = paramObject;
    return (this.mStereo != paramObject.isStereoSupported()) ? false : ((this.mRds != paramObject.isRdsSupported()) ? false : ((this.mTa != paramObject.isTaSupported()) ? false : ((this.mAf != paramObject.isAfSupported()) ? false : (!(this.mEa != paramObject.isEaSupported())))));
  }
  
  public int hashCode() {
    return ((((super.hashCode() * 31 + this.mStereo) * 31 + this.mRds) * 31 + this.mTa) * 31 + this.mAf) * 31 + this.mEa;
  }
  
  public boolean isAfSupported() {
    return this.mAf;
  }
  
  public boolean isEaSupported() {
    return this.mEa;
  }
  
  public boolean isRdsSupported() {
    return this.mRds;
  }
  
  public boolean isStereoSupported() {
    return this.mStereo;
  }
  
  public boolean isTaSupported() {
    return this.mTa;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("FmBandDescriptor [ ");
    stringBuilder.append(super.toString());
    stringBuilder.append(" mStereo=");
    stringBuilder.append(this.mStereo);
    stringBuilder.append(", mRds=");
    stringBuilder.append(this.mRds);
    stringBuilder.append(", mTa=");
    stringBuilder.append(this.mTa);
    stringBuilder.append(", mAf=");
    stringBuilder.append(this.mAf);
    stringBuilder.append(", mEa =");
    stringBuilder.append(this.mEa);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeByte((byte)this.mStereo);
    paramParcel.writeByte((byte)this.mRds);
    paramParcel.writeByte((byte)this.mTa);
    paramParcel.writeByte((byte)this.mAf);
    paramParcel.writeByte((byte)this.mEa);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$FmBandDescriptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */