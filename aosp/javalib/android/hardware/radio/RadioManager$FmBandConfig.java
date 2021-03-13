package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

public class FmBandConfig extends RadioManager.BandConfig {
  public static final Parcelable.Creator<FmBandConfig> CREATOR = new Parcelable.Creator<FmBandConfig>() {
      public RadioManager.FmBandConfig createFromParcel(Parcel param2Parcel) {
        return new RadioManager.FmBandConfig(param2Parcel);
      }
      
      public RadioManager.FmBandConfig[] newArray(int param2Int) {
        return new RadioManager.FmBandConfig[param2Int];
      }
    };
  
  private final boolean mAf;
  
  private final boolean mEa;
  
  private final boolean mRds;
  
  private final boolean mStereo;
  
  private final boolean mTa;
  
  FmBandConfig(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5) {
    super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    this.mStereo = paramBoolean1;
    this.mRds = paramBoolean2;
    this.mTa = paramBoolean3;
    this.mAf = paramBoolean4;
    this.mEa = paramBoolean5;
  }
  
  public FmBandConfig(RadioManager.FmBandDescriptor paramFmBandDescriptor) {
    super(paramFmBandDescriptor);
    this.mStereo = paramFmBandDescriptor.isStereoSupported();
    this.mRds = paramFmBandDescriptor.isRdsSupported();
    this.mTa = paramFmBandDescriptor.isTaSupported();
    this.mAf = paramFmBandDescriptor.isAfSupported();
    this.mEa = paramFmBandDescriptor.isEaSupported();
  }
  
  private FmBandConfig(Parcel paramParcel) {
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
    if (!(paramObject instanceof FmBandConfig))
      return false; 
    paramObject = paramObject;
    return (this.mStereo != ((FmBandConfig)paramObject).mStereo) ? false : ((this.mRds != ((FmBandConfig)paramObject).mRds) ? false : ((this.mTa != ((FmBandConfig)paramObject).mTa) ? false : ((this.mAf != ((FmBandConfig)paramObject).mAf) ? false : (!(this.mEa != ((FmBandConfig)paramObject).mEa)))));
  }
  
  public boolean getAf() {
    return this.mAf;
  }
  
  public boolean getEa() {
    return this.mEa;
  }
  
  public boolean getRds() {
    return this.mRds;
  }
  
  public boolean getStereo() {
    return this.mStereo;
  }
  
  public boolean getTa() {
    return this.mTa;
  }
  
  public int hashCode() {
    return ((((super.hashCode() * 31 + this.mStereo) * 31 + this.mRds) * 31 + this.mTa) * 31 + this.mAf) * 31 + this.mEa;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("FmBandConfig [");
    stringBuilder.append(super.toString());
    stringBuilder.append(", mStereo=");
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
  
  public static class Builder {
    private boolean mAf;
    
    private final RadioManager.BandDescriptor mDescriptor;
    
    private boolean mEa;
    
    private boolean mRds;
    
    private boolean mStereo;
    
    private boolean mTa;
    
    public Builder(RadioManager.FmBandConfig param2FmBandConfig) {
      this.mDescriptor = new RadioManager.BandDescriptor(param2FmBandConfig.getRegion(), param2FmBandConfig.getType(), param2FmBandConfig.getLowerLimit(), param2FmBandConfig.getUpperLimit(), param2FmBandConfig.getSpacing());
      this.mStereo = param2FmBandConfig.getStereo();
      this.mRds = param2FmBandConfig.getRds();
      this.mTa = param2FmBandConfig.getTa();
      this.mAf = param2FmBandConfig.getAf();
      this.mEa = param2FmBandConfig.getEa();
    }
    
    public Builder(RadioManager.FmBandDescriptor param2FmBandDescriptor) {
      this.mDescriptor = new RadioManager.BandDescriptor(param2FmBandDescriptor.getRegion(), param2FmBandDescriptor.getType(), param2FmBandDescriptor.getLowerLimit(), param2FmBandDescriptor.getUpperLimit(), param2FmBandDescriptor.getSpacing());
      this.mStereo = param2FmBandDescriptor.isStereoSupported();
      this.mRds = param2FmBandDescriptor.isRdsSupported();
      this.mTa = param2FmBandDescriptor.isTaSupported();
      this.mAf = param2FmBandDescriptor.isAfSupported();
      this.mEa = param2FmBandDescriptor.isEaSupported();
    }
    
    public RadioManager.FmBandConfig build() {
      return new RadioManager.FmBandConfig(this.mDescriptor.getRegion(), this.mDescriptor.getType(), this.mDescriptor.getLowerLimit(), this.mDescriptor.getUpperLimit(), this.mDescriptor.getSpacing(), this.mStereo, this.mRds, this.mTa, this.mAf, this.mEa);
    }
    
    public Builder setAf(boolean param2Boolean) {
      this.mAf = param2Boolean;
      return this;
    }
    
    public Builder setEa(boolean param2Boolean) {
      this.mEa = param2Boolean;
      return this;
    }
    
    public Builder setRds(boolean param2Boolean) {
      this.mRds = param2Boolean;
      return this;
    }
    
    public Builder setStereo(boolean param2Boolean) {
      this.mStereo = param2Boolean;
      return this;
    }
    
    public Builder setTa(boolean param2Boolean) {
      this.mTa = param2Boolean;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$FmBandConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */