package android.hardware.input;

import android.os.Parcel;
import android.os.Parcelable;

public class TouchCalibration implements Parcelable {
  public static final Parcelable.Creator<TouchCalibration> CREATOR;
  
  public static final TouchCalibration IDENTITY = new TouchCalibration();
  
  private final float mXOffset;
  
  private final float mXScale;
  
  private final float mXYMix;
  
  private final float mYOffset;
  
  private final float mYScale;
  
  private final float mYXMix;
  
  static {
    CREATOR = new Parcelable.Creator<TouchCalibration>() {
        public TouchCalibration createFromParcel(Parcel param1Parcel) {
          return new TouchCalibration(param1Parcel);
        }
        
        public TouchCalibration[] newArray(int param1Int) {
          return new TouchCalibration[param1Int];
        }
      };
  }
  
  public TouchCalibration() {
    this(1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F);
  }
  
  public TouchCalibration(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    this.mXScale = paramFloat1;
    this.mXYMix = paramFloat2;
    this.mXOffset = paramFloat3;
    this.mYXMix = paramFloat4;
    this.mYScale = paramFloat5;
    this.mYOffset = paramFloat6;
  }
  
  public TouchCalibration(Parcel paramParcel) {
    this.mXScale = paramParcel.readFloat();
    this.mXYMix = paramParcel.readFloat();
    this.mXOffset = paramParcel.readFloat();
    this.mYXMix = paramParcel.readFloat();
    this.mYScale = paramParcel.readFloat();
    this.mYOffset = paramParcel.readFloat();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (paramObject instanceof TouchCalibration) {
      paramObject = paramObject;
      if (((TouchCalibration)paramObject).mXScale != this.mXScale || ((TouchCalibration)paramObject).mXYMix != this.mXYMix || ((TouchCalibration)paramObject).mXOffset != this.mXOffset || ((TouchCalibration)paramObject).mYXMix != this.mYXMix || ((TouchCalibration)paramObject).mYScale != this.mYScale || ((TouchCalibration)paramObject).mYOffset != this.mYOffset)
        bool = false; 
      return bool;
    } 
    return false;
  }
  
  public float[] getAffineTransform() {
    return new float[] { this.mXScale, this.mXYMix, this.mXOffset, this.mYXMix, this.mYScale, this.mYOffset };
  }
  
  public int hashCode() {
    return Float.floatToIntBits(this.mXScale) ^ Float.floatToIntBits(this.mXYMix) ^ Float.floatToIntBits(this.mXOffset) ^ Float.floatToIntBits(this.mYXMix) ^ Float.floatToIntBits(this.mYScale) ^ Float.floatToIntBits(this.mYOffset);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeFloat(this.mXScale);
    paramParcel.writeFloat(this.mXYMix);
    paramParcel.writeFloat(this.mXOffset);
    paramParcel.writeFloat(this.mYXMix);
    paramParcel.writeFloat(this.mYScale);
    paramParcel.writeFloat(this.mYOffset);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/TouchCalibration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */