package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

public final class Insets implements Parcelable {
  public static final Parcelable.Creator<Insets> CREATOR;
  
  public static final Insets NONE = new Insets(0, 0, 0, 0);
  
  public final int bottom;
  
  public final int left;
  
  public final int right;
  
  public final int top;
  
  static {
    CREATOR = new Parcelable.Creator<Insets>() {
        public Insets createFromParcel(Parcel param1Parcel) {
          return new Insets(param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt());
        }
        
        public Insets[] newArray(int param1Int) {
          return new Insets[param1Int];
        }
      };
  }
  
  private Insets(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.left = paramInt1;
    this.top = paramInt2;
    this.right = paramInt3;
    this.bottom = paramInt4;
  }
  
  public static Insets add(Insets paramInsets1, Insets paramInsets2) {
    return of(paramInsets1.left + paramInsets2.left, paramInsets1.top + paramInsets2.top, paramInsets1.right + paramInsets2.right, paramInsets1.bottom + paramInsets2.bottom);
  }
  
  public static Insets max(Insets paramInsets1, Insets paramInsets2) {
    return of(Math.max(paramInsets1.left, paramInsets2.left), Math.max(paramInsets1.top, paramInsets2.top), Math.max(paramInsets1.right, paramInsets2.right), Math.max(paramInsets1.bottom, paramInsets2.bottom));
  }
  
  public static Insets min(Insets paramInsets1, Insets paramInsets2) {
    return of(Math.min(paramInsets1.left, paramInsets2.left), Math.min(paramInsets1.top, paramInsets2.top), Math.min(paramInsets1.right, paramInsets2.right), Math.min(paramInsets1.bottom, paramInsets2.bottom));
  }
  
  public static Insets of(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return (paramInt1 == 0 && paramInt2 == 0 && paramInt3 == 0 && paramInt4 == 0) ? NONE : new Insets(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static Insets of(Rect paramRect) {
    Insets insets;
    if (paramRect == null) {
      insets = NONE;
    } else {
      insets = of(((Rect)insets).left, ((Rect)insets).top, ((Rect)insets).right, ((Rect)insets).bottom);
    } 
    return insets;
  }
  
  public static Insets subtract(Insets paramInsets1, Insets paramInsets2) {
    return of(paramInsets1.left - paramInsets2.left, paramInsets1.top - paramInsets2.top, paramInsets1.right - paramInsets2.right, paramInsets1.bottom - paramInsets2.bottom);
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
    return (this.bottom != ((Insets)paramObject).bottom) ? false : ((this.left != ((Insets)paramObject).left) ? false : ((this.right != ((Insets)paramObject).right) ? false : (!(this.top != ((Insets)paramObject).top))));
  }
  
  public int hashCode() {
    return ((this.left * 31 + this.top) * 31 + this.right) * 31 + this.bottom;
  }
  
  public Rect toRect() {
    return new Rect(this.left, this.top, this.right, this.bottom);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Insets{left=");
    stringBuilder.append(this.left);
    stringBuilder.append(", top=");
    stringBuilder.append(this.top);
    stringBuilder.append(", right=");
    stringBuilder.append(this.right);
    stringBuilder.append(", bottom=");
    stringBuilder.append(this.bottom);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.left);
    paramParcel.writeInt(this.top);
    paramParcel.writeInt(this.right);
    paramParcel.writeInt(this.bottom);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Insets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */