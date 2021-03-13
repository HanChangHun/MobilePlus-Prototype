package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

public final class ParcelableColorSpace extends ColorSpace implements Parcelable {
  public static final Parcelable.Creator<ParcelableColorSpace> CREATOR = new Parcelable.Creator<ParcelableColorSpace>() {
      public ParcelableColorSpace createFromParcel(Parcel param1Parcel) {
        int i = param1Parcel.readInt();
        return (i == -1) ? new ParcelableColorSpace(new ColorSpace.Rgb(param1Parcel.readString(), param1Parcel.createFloatArray(), param1Parcel.createFloatArray(), new ColorSpace.Rgb.TransferParameters(param1Parcel.readDouble(), param1Parcel.readDouble(), param1Parcel.readDouble(), param1Parcel.readDouble(), param1Parcel.readDouble(), param1Parcel.readDouble(), param1Parcel.readDouble()))) : new ParcelableColorSpace(ColorSpace.get(i));
      }
      
      public ParcelableColorSpace[] newArray(int param1Int) {
        return new ParcelableColorSpace[param1Int];
      }
    };
  
  private final ColorSpace mColorSpace;
  
  public ParcelableColorSpace(ColorSpace paramColorSpace) {
    super(paramColorSpace.getName(), paramColorSpace.getModel(), paramColorSpace.getId());
    this.mColorSpace = paramColorSpace;
    if (paramColorSpace.getId() == -1) {
      paramColorSpace = this.mColorSpace;
      if (paramColorSpace instanceof ColorSpace.Rgb) {
        if (((ColorSpace.Rgb)paramColorSpace).getTransferParameters() == null)
          throw new IllegalArgumentException("ColorSpace must use an ICC parametric transfer function to be parcelable"); 
      } else {
        throw new IllegalArgumentException("Unable to parcel unknown ColorSpaces that are not ColorSpace.Rgb");
      } 
    } 
  }
  
  public static boolean isParcelable(ColorSpace paramColorSpace) {
    if (paramColorSpace.getId() == -1) {
      if (!(paramColorSpace instanceof ColorSpace.Rgb))
        return false; 
      if (((ColorSpace.Rgb)paramColorSpace).getTransferParameters() == null)
        return false; 
    } 
    return true;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    return this.mColorSpace.equals(((ParcelableColorSpace)paramObject).mColorSpace);
  }
  
  public float[] fromXyz(float[] paramArrayOffloat) {
    return this.mColorSpace.fromXyz(paramArrayOffloat);
  }
  
  public ColorSpace getColorSpace() {
    return this.mColorSpace;
  }
  
  public float getMaxValue(int paramInt) {
    return this.mColorSpace.getMaxValue(paramInt);
  }
  
  public float getMinValue(int paramInt) {
    return this.mColorSpace.getMinValue(paramInt);
  }
  
  long getNativeInstance() {
    return this.mColorSpace.getNativeInstance();
  }
  
  public int hashCode() {
    return this.mColorSpace.hashCode();
  }
  
  public boolean isWideGamut() {
    return this.mColorSpace.isWideGamut();
  }
  
  public float[] toXyz(float[] paramArrayOffloat) {
    return this.mColorSpace.toXyz(paramArrayOffloat);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = this.mColorSpace.getId();
    paramParcel.writeInt(paramInt);
    if (paramInt == -1) {
      ColorSpace.Rgb rgb = (ColorSpace.Rgb)this.mColorSpace;
      paramParcel.writeString(rgb.getName());
      paramParcel.writeFloatArray(rgb.getPrimaries());
      paramParcel.writeFloatArray(rgb.getWhitePoint());
      ColorSpace.Rgb.TransferParameters transferParameters = rgb.getTransferParameters();
      paramParcel.writeDouble(transferParameters.a);
      paramParcel.writeDouble(transferParameters.b);
      paramParcel.writeDouble(transferParameters.c);
      paramParcel.writeDouble(transferParameters.d);
      paramParcel.writeDouble(transferParameters.e);
      paramParcel.writeDouble(transferParameters.f);
      paramParcel.writeDouble(transferParameters.g);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ParcelableColorSpace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */