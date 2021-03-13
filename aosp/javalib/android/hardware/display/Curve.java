package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

public final class Curve implements Parcelable {
  public static final Parcelable.Creator<Curve> CREATOR = new Parcelable.Creator<Curve>() {
      public Curve createFromParcel(Parcel param1Parcel) {
        return new Curve(param1Parcel.createFloatArray(), param1Parcel.createFloatArray());
      }
      
      public Curve[] newArray(int param1Int) {
        return new Curve[param1Int];
      }
    };
  
  private final float[] mX;
  
  private final float[] mY;
  
  public Curve(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    this.mX = paramArrayOffloat1;
    this.mY = paramArrayOffloat2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public float[] getX() {
    return this.mX;
  }
  
  public float[] getY() {
    return this.mY;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("[");
    int i = this.mX.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        stringBuilder.append(", "); 
      stringBuilder.append("(");
      stringBuilder.append(this.mX[b]);
      stringBuilder.append(", ");
      stringBuilder.append(this.mY[b]);
      stringBuilder.append(")");
    } 
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeFloatArray(this.mX);
    paramParcel.writeFloatArray(this.mY);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/Curve.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */