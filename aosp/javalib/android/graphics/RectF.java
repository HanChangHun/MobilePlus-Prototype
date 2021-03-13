package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.FastMath;
import java.io.PrintWriter;

public class RectF implements Parcelable {
  public static final Parcelable.Creator<RectF> CREATOR = new Parcelable.Creator<RectF>() {
      public RectF createFromParcel(Parcel param1Parcel) {
        RectF rectF = new RectF();
        rectF.readFromParcel(param1Parcel);
        return rectF;
      }
      
      public RectF[] newArray(int param1Int) {
        return new RectF[param1Int];
      }
    };
  
  public float bottom;
  
  public float left;
  
  public float right;
  
  public float top;
  
  public RectF() {}
  
  public RectF(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    this.left = paramFloat1;
    this.top = paramFloat2;
    this.right = paramFloat3;
    this.bottom = paramFloat4;
  }
  
  public RectF(Rect paramRect) {
    if (paramRect == null) {
      this.bottom = 0.0F;
      this.right = 0.0F;
      this.top = 0.0F;
      this.left = 0.0F;
    } else {
      this.left = paramRect.left;
      this.top = paramRect.top;
      this.right = paramRect.right;
      this.bottom = paramRect.bottom;
    } 
  }
  
  public RectF(RectF paramRectF) {
    if (paramRectF == null) {
      this.bottom = 0.0F;
      this.right = 0.0F;
      this.top = 0.0F;
      this.left = 0.0F;
    } else {
      this.left = paramRectF.left;
      this.top = paramRectF.top;
      this.right = paramRectF.right;
      this.bottom = paramRectF.bottom;
    } 
  }
  
  public static boolean intersects(RectF paramRectF1, RectF paramRectF2) {
    boolean bool;
    if (paramRectF1.left < paramRectF2.right && paramRectF2.left < paramRectF1.right && paramRectF1.top < paramRectF2.bottom && paramRectF2.top < paramRectF1.bottom) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final float centerX() {
    return (this.left + this.right) * 0.5F;
  }
  
  public final float centerY() {
    return (this.top + this.bottom) * 0.5F;
  }
  
  public boolean contains(float paramFloat1, float paramFloat2) {
    float f1 = this.left;
    float f2 = this.right;
    if (f1 < f2) {
      float f3 = this.top;
      float f4 = this.bottom;
      if (f3 < f4 && paramFloat1 >= f1 && paramFloat1 < f2 && paramFloat2 >= f3 && paramFloat2 < f4)
        return true; 
    } 
    return false;
  }
  
  public boolean contains(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    float f1 = this.left;
    float f2 = this.right;
    if (f1 < f2) {
      float f3 = this.top;
      float f4 = this.bottom;
      if (f3 < f4 && f1 <= paramFloat1 && f3 <= paramFloat2 && f2 >= paramFloat3 && f4 >= paramFloat4)
        return true; 
    } 
    return false;
  }
  
  public boolean contains(RectF paramRectF) {
    float f1 = this.left;
    float f2 = this.right;
    if (f1 < f2) {
      float f3 = this.top;
      float f4 = this.bottom;
      if (f3 < f4 && f1 <= paramRectF.left && f3 <= paramRectF.top && f2 >= paramRectF.right && f4 >= paramRectF.bottom)
        return true; 
    } 
    return false;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.left != ((RectF)paramObject).left || this.top != ((RectF)paramObject).top || this.right != ((RectF)paramObject).right || this.bottom != ((RectF)paramObject).bottom)
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    byte b1;
    byte b2;
    byte b3;
    float f = this.left;
    int i = 0;
    if (f != 0.0F) {
      b1 = Float.floatToIntBits(f);
    } else {
      b1 = 0;
    } 
    f = this.top;
    if (f != 0.0F) {
      b2 = Float.floatToIntBits(f);
    } else {
      b2 = 0;
    } 
    f = this.right;
    if (f != 0.0F) {
      b3 = Float.floatToIntBits(f);
    } else {
      b3 = 0;
    } 
    f = this.bottom;
    if (f != 0.0F)
      i = Float.floatToIntBits(f); 
    return ((b1 * 31 + b2) * 31 + b3) * 31 + i;
  }
  
  public final float height() {
    return this.bottom - this.top;
  }
  
  public void inset(float paramFloat1, float paramFloat2) {
    this.left += paramFloat1;
    this.top += paramFloat2;
    this.right -= paramFloat1;
    this.bottom -= paramFloat2;
  }
  
  public boolean intersect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    float f = this.left;
    if (f < paramFloat3 && paramFloat1 < this.right && this.top < paramFloat4 && paramFloat2 < this.bottom) {
      if (f < paramFloat1)
        this.left = paramFloat1; 
      if (this.top < paramFloat2)
        this.top = paramFloat2; 
      if (this.right > paramFloat3)
        this.right = paramFloat3; 
      if (this.bottom > paramFloat4)
        this.bottom = paramFloat4; 
      return true;
    } 
    return false;
  }
  
  public boolean intersect(RectF paramRectF) {
    return intersect(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom);
  }
  
  public boolean intersects(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    boolean bool;
    if (this.left < paramFloat3 && paramFloat1 < this.right && this.top < paramFloat4 && paramFloat2 < this.bottom) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isEmpty() {
    return (this.left >= this.right || this.top >= this.bottom);
  }
  
  public void offset(float paramFloat1, float paramFloat2) {
    this.left += paramFloat1;
    this.top += paramFloat2;
    this.right += paramFloat1;
    this.bottom += paramFloat2;
  }
  
  public void offsetTo(float paramFloat1, float paramFloat2) {
    this.right += paramFloat1 - this.left;
    this.bottom += paramFloat2 - this.top;
    this.left = paramFloat1;
    this.top = paramFloat2;
  }
  
  public void printShortString(PrintWriter paramPrintWriter) {
    paramPrintWriter.print('[');
    paramPrintWriter.print(this.left);
    paramPrintWriter.print(',');
    paramPrintWriter.print(this.top);
    paramPrintWriter.print("][");
    paramPrintWriter.print(this.right);
    paramPrintWriter.print(',');
    paramPrintWriter.print(this.bottom);
    paramPrintWriter.print(']');
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.left = paramParcel.readFloat();
    this.top = paramParcel.readFloat();
    this.right = paramParcel.readFloat();
    this.bottom = paramParcel.readFloat();
  }
  
  public void round(Rect paramRect) {
    paramRect.set(FastMath.round(this.left), FastMath.round(this.top), FastMath.round(this.right), FastMath.round(this.bottom));
  }
  
  public void roundOut(Rect paramRect) {
    paramRect.set((int)Math.floor(this.left), (int)Math.floor(this.top), (int)Math.ceil(this.right), (int)Math.ceil(this.bottom));
  }
  
  public void scale(float paramFloat) {
    if (paramFloat != 1.0F) {
      this.left *= paramFloat;
      this.top *= paramFloat;
      this.right *= paramFloat;
      this.bottom *= paramFloat;
    } 
  }
  
  public void set(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    this.left = paramFloat1;
    this.top = paramFloat2;
    this.right = paramFloat3;
    this.bottom = paramFloat4;
  }
  
  public void set(Rect paramRect) {
    this.left = paramRect.left;
    this.top = paramRect.top;
    this.right = paramRect.right;
    this.bottom = paramRect.bottom;
  }
  
  public void set(RectF paramRectF) {
    this.left = paramRectF.left;
    this.top = paramRectF.top;
    this.right = paramRectF.right;
    this.bottom = paramRectF.bottom;
  }
  
  public void setEmpty() {
    this.bottom = 0.0F;
    this.top = 0.0F;
    this.right = 0.0F;
    this.left = 0.0F;
  }
  
  public boolean setIntersect(RectF paramRectF1, RectF paramRectF2) {
    float f = paramRectF1.left;
    if (f < paramRectF2.right) {
      float f1 = paramRectF2.left;
      if (f1 < paramRectF1.right && paramRectF1.top < paramRectF2.bottom && paramRectF2.top < paramRectF1.bottom) {
        this.left = Math.max(f, f1);
        this.top = Math.max(paramRectF1.top, paramRectF2.top);
        this.right = Math.min(paramRectF1.right, paramRectF2.right);
        this.bottom = Math.min(paramRectF1.bottom, paramRectF2.bottom);
        return true;
      } 
    } 
    return false;
  }
  
  public void sort() {
    float f1 = this.left;
    float f2 = this.right;
    if (f1 > f2) {
      f1 = this.left;
      this.left = f2;
      this.right = f1;
    } 
    f1 = this.top;
    f2 = this.bottom;
    if (f1 > f2) {
      f1 = this.top;
      this.top = f2;
      this.bottom = f1;
    } 
  }
  
  public String toShortString() {
    return toShortString(new StringBuilder(32));
  }
  
  public String toShortString(StringBuilder paramStringBuilder) {
    paramStringBuilder.setLength(0);
    paramStringBuilder.append('[');
    paramStringBuilder.append(this.left);
    paramStringBuilder.append(',');
    paramStringBuilder.append(this.top);
    paramStringBuilder.append("][");
    paramStringBuilder.append(this.right);
    paramStringBuilder.append(',');
    paramStringBuilder.append(this.bottom);
    paramStringBuilder.append(']');
    return paramStringBuilder.toString();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("RectF(");
    stringBuilder.append(this.left);
    stringBuilder.append(", ");
    stringBuilder.append(this.top);
    stringBuilder.append(", ");
    stringBuilder.append(this.right);
    stringBuilder.append(", ");
    stringBuilder.append(this.bottom);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void union(float paramFloat1, float paramFloat2) {
    if (paramFloat1 < this.left) {
      this.left = paramFloat1;
    } else if (paramFloat1 > this.right) {
      this.right = paramFloat1;
    } 
    if (paramFloat2 < this.top) {
      this.top = paramFloat2;
    } else if (paramFloat2 > this.bottom) {
      this.bottom = paramFloat2;
    } 
  }
  
  public void union(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    if (paramFloat1 < paramFloat3 && paramFloat2 < paramFloat4) {
      float f = this.left;
      if (f < this.right && this.top < this.bottom) {
        if (f > paramFloat1)
          this.left = paramFloat1; 
        if (this.top > paramFloat2)
          this.top = paramFloat2; 
        if (this.right < paramFloat3)
          this.right = paramFloat3; 
        if (this.bottom < paramFloat4)
          this.bottom = paramFloat4; 
      } else {
        this.left = paramFloat1;
        this.top = paramFloat2;
        this.right = paramFloat3;
        this.bottom = paramFloat4;
      } 
    } 
  }
  
  public void union(RectF paramRectF) {
    union(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom);
  }
  
  public final float width() {
    return this.right - this.left;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeFloat(this.left);
    paramParcel.writeFloat(this.top);
    paramParcel.writeFloat(this.right);
    paramParcel.writeFloat(this.bottom);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/RectF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */