package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;

public class AmBandConfig extends RadioManager.BandConfig {
  public static final Parcelable.Creator<AmBandConfig> CREATOR = new Parcelable.Creator<AmBandConfig>() {
      public RadioManager.AmBandConfig createFromParcel(Parcel param2Parcel) {
        return new RadioManager.AmBandConfig(param2Parcel);
      }
      
      public RadioManager.AmBandConfig[] newArray(int param2Int) {
        return new RadioManager.AmBandConfig[param2Int];
      }
    };
  
  private final boolean mStereo;
  
  AmBandConfig(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean) {
    super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    this.mStereo = paramBoolean;
  }
  
  public AmBandConfig(RadioManager.AmBandDescriptor paramAmBandDescriptor) {
    super(paramAmBandDescriptor);
    this.mStereo = paramAmBandDescriptor.isStereoSupported();
  }
  
  private AmBandConfig(Parcel paramParcel) {
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
    if (!(paramObject instanceof AmBandConfig))
      return false; 
    paramObject = paramObject;
    return !(this.mStereo != paramObject.getStereo());
  }
  
  public boolean getStereo() {
    return this.mStereo;
  }
  
  public int hashCode() {
    return super.hashCode() * 31 + this.mStereo;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AmBandConfig [");
    stringBuilder.append(super.toString());
    stringBuilder.append(", mStereo=");
    stringBuilder.append(this.mStereo);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeByte((byte)this.mStereo);
  }
  
  public static class Builder {
    private final RadioManager.BandDescriptor mDescriptor;
    
    private boolean mStereo;
    
    public Builder(RadioManager.AmBandConfig param2AmBandConfig) {
      this.mDescriptor = new RadioManager.BandDescriptor(param2AmBandConfig.getRegion(), param2AmBandConfig.getType(), param2AmBandConfig.getLowerLimit(), param2AmBandConfig.getUpperLimit(), param2AmBandConfig.getSpacing());
      this.mStereo = param2AmBandConfig.getStereo();
    }
    
    public Builder(RadioManager.AmBandDescriptor param2AmBandDescriptor) {
      this.mDescriptor = new RadioManager.BandDescriptor(param2AmBandDescriptor.getRegion(), param2AmBandDescriptor.getType(), param2AmBandDescriptor.getLowerLimit(), param2AmBandDescriptor.getUpperLimit(), param2AmBandDescriptor.getSpacing());
      this.mStereo = param2AmBandDescriptor.isStereoSupported();
    }
    
    public RadioManager.AmBandConfig build() {
      return new RadioManager.AmBandConfig(this.mDescriptor.getRegion(), this.mDescriptor.getType(), this.mDescriptor.getLowerLimit(), this.mDescriptor.getUpperLimit(), this.mDescriptor.getSpacing(), this.mStereo);
    }
    
    public Builder setStereo(boolean param2Boolean) {
      this.mStereo = param2Boolean;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$AmBandConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */