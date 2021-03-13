package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

public class PointF implements Parcelable {
  public static final Parcelable.Creator<PointF> CREATOR = new Parcelable.Creator<PointF>() {
      public PointF createFromParcel(Parcel param1Parcel) {
        PointF pointF = new PointF();
        pointF.readFromParcel(param1Parcel);
        return pointF;
      }
      
      public PointF[] newArray(int param1Int) {
        return new PointF[param1Int];
      }
    };
  
  public float x;
  
  public float y;
  
  public PointF() {}
  
  public PointF(float paramFloat1, float paramFloat2) {
    this.x = paramFloat1;
    this.y = paramFloat2;
  }
  
  public PointF(Point paramPoint) {
    this.x = paramPoint.x;
    this.y = paramPoint.y;
  }
  
  public PointF(PointF paramPointF) {
    this.x = paramPointF.x;
    this.y = paramPointF.y;
  }
  
  public static float length(float paramFloat1, float paramFloat2) {
    return (float)Math.hypot(paramFloat1, paramFloat2);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public final boolean equals(float paramFloat1, float paramFloat2) {
    boolean bool;
    if (this.x == paramFloat1 && this.y == paramFloat2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    return (Float.compare(((PointF)paramObject).x, this.x) != 0) ? false : (!(Float.compare(((PointF)paramObject).y, this.y) != 0));
  }
  
  public int hashCode() {
    byte b;
    float f = this.x;
    int i = 0;
    if (f != 0.0F) {
      b = Float.floatToIntBits(f);
    } else {
      b = 0;
    } 
    f = this.y;
    if (f != 0.0F)
      i = Float.floatToIntBits(f); 
    return b * 31 + i;
  }
  
  public final float length() {
    return length(this.x, this.y);
  }
  
  public final void negate() {
    this.x = -this.x;
    this.y = -this.y;
  }
  
  public final void offset(float paramFloat1, float paramFloat2) {
    this.x += paramFloat1;
    this.y += paramFloat2;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.x = paramParcel.readFloat();
    this.y = paramParcel.readFloat();
  }
  
  public final void set(float paramFloat1, float paramFloat2) {
    this.x = paramFloat1;
    this.y = paramFloat2;
  }
  
  public final void set(PointF paramPointF) {
    this.x = paramPointF.x;
    this.y = paramPointF.y;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PointF(");
    stringBuilder.append(this.x);
    stringBuilder.append(", ");
    stringBuilder.append(this.y);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeFloat(this.x);
    paramParcel.writeFloat(this.y);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PointF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */