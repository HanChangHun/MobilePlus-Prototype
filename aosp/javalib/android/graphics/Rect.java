package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.proto.ProtoInputStream;
import android.util.proto.ProtoOutputStream;
import android.util.proto.WireTypeMismatchException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Rect implements Parcelable {
  public static final Parcelable.Creator<Rect> CREATOR = new Parcelable.Creator<Rect>() {
      public Rect createFromParcel(Parcel param1Parcel) {
        Rect rect = new Rect();
        rect.readFromParcel(param1Parcel);
        return rect;
      }
      
      public Rect[] newArray(int param1Int) {
        return new Rect[param1Int];
      }
    };
  
  public int bottom;
  
  public int left;
  
  public int right;
  
  public int top;
  
  public Rect() {}
  
  public Rect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.left = paramInt1;
    this.top = paramInt2;
    this.right = paramInt3;
    this.bottom = paramInt4;
  }
  
  public Rect(Insets paramInsets) {
    if (paramInsets == null) {
      this.bottom = 0;
      this.right = 0;
      this.top = 0;
      this.left = 0;
    } else {
      this.left = paramInsets.left;
      this.top = paramInsets.top;
      this.right = paramInsets.right;
      this.bottom = paramInsets.bottom;
    } 
  }
  
  public Rect(Rect paramRect) {
    if (paramRect == null) {
      this.bottom = 0;
      this.right = 0;
      this.top = 0;
      this.left = 0;
    } else {
      this.left = paramRect.left;
      this.top = paramRect.top;
      this.right = paramRect.right;
      this.bottom = paramRect.bottom;
    } 
  }
  
  public static Rect copyOrNull(Rect paramRect) {
    if (paramRect == null) {
      paramRect = null;
    } else {
      paramRect = new Rect(paramRect);
    } 
    return paramRect;
  }
  
  public static boolean intersects(Rect paramRect1, Rect paramRect2) {
    boolean bool;
    if (paramRect1.left < paramRect2.right && paramRect2.left < paramRect1.right && paramRect1.top < paramRect2.bottom && paramRect2.top < paramRect1.bottom) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static Rect unflattenFromString(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return null; 
    Matcher matcher = UnflattenHelper.getMatcher(paramString);
    return !matcher.matches() ? null : new Rect(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
  }
  
  public final int centerX() {
    return this.left + this.right >> 1;
  }
  
  public final int centerY() {
    return this.top + this.bottom >> 1;
  }
  
  public boolean contains(int paramInt1, int paramInt2) {
    int i = this.left;
    int j = this.right;
    if (i < j) {
      int k = this.top;
      int m = this.bottom;
      if (k < m && paramInt1 >= i && paramInt1 < j && paramInt2 >= k && paramInt2 < m)
        return true; 
    } 
    return false;
  }
  
  public boolean contains(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = this.left;
    int j = this.right;
    if (i < j) {
      int k = this.top;
      int m = this.bottom;
      if (k < m && i <= paramInt1 && k <= paramInt2 && j >= paramInt3 && m >= paramInt4)
        return true; 
    } 
    return false;
  }
  
  public boolean contains(Rect paramRect) {
    int i = this.left;
    int j = this.right;
    if (i < j) {
      int k = this.top;
      int m = this.bottom;
      if (k < m && i <= paramRect.left && k <= paramRect.top && j >= paramRect.right && m >= paramRect.bottom)
        return true; 
    } 
    return false;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    paramProtoOutputStream.write(1120986464257L, this.left);
    paramProtoOutputStream.write(1120986464258L, this.top);
    paramProtoOutputStream.write(1120986464259L, this.right);
    paramProtoOutputStream.write(1120986464260L, this.bottom);
    paramProtoOutputStream.end(paramLong);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.left != ((Rect)paramObject).left || this.top != ((Rect)paramObject).top || this.right != ((Rect)paramObject).right || this.bottom != ((Rect)paramObject).bottom)
      bool = false; 
    return bool;
  }
  
  public final float exactCenterX() {
    return (this.left + this.right) * 0.5F;
  }
  
  public final float exactCenterY() {
    return (this.top + this.bottom) * 0.5F;
  }
  
  public String flattenToString() {
    StringBuilder stringBuilder = new StringBuilder(32);
    stringBuilder.append(this.left);
    stringBuilder.append(' ');
    stringBuilder.append(this.top);
    stringBuilder.append(' ');
    stringBuilder.append(this.right);
    stringBuilder.append(' ');
    stringBuilder.append(this.bottom);
    return stringBuilder.toString();
  }
  
  public int hashCode() {
    return ((this.left * 31 + this.top) * 31 + this.right) * 31 + this.bottom;
  }
  
  public final int height() {
    return this.bottom - this.top;
  }
  
  public void inset(int paramInt1, int paramInt2) {
    this.left += paramInt1;
    this.top += paramInt2;
    this.right -= paramInt1;
    this.bottom -= paramInt2;
  }
  
  public void inset(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.left += paramInt1;
    this.top += paramInt2;
    this.right -= paramInt3;
    this.bottom -= paramInt4;
  }
  
  public void inset(Insets paramInsets) {
    this.left += paramInsets.left;
    this.top += paramInsets.top;
    this.right -= paramInsets.right;
    this.bottom -= paramInsets.bottom;
  }
  
  public void inset(Rect paramRect) {
    this.left += paramRect.left;
    this.top += paramRect.top;
    this.right -= paramRect.right;
    this.bottom -= paramRect.bottom;
  }
  
  public boolean intersect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = this.left;
    if (i < paramInt3 && paramInt1 < this.right && this.top < paramInt4 && paramInt2 < this.bottom) {
      if (i < paramInt1)
        this.left = paramInt1; 
      if (this.top < paramInt2)
        this.top = paramInt2; 
      if (this.right > paramInt3)
        this.right = paramInt3; 
      if (this.bottom > paramInt4)
        this.bottom = paramInt4; 
      return true;
    } 
    return false;
  }
  
  public boolean intersect(Rect paramRect) {
    return intersect(paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public void intersectUnchecked(Rect paramRect) {
    this.left = Math.max(this.left, paramRect.left);
    this.top = Math.max(this.top, paramRect.top);
    this.right = Math.min(this.right, paramRect.right);
    this.bottom = Math.min(this.bottom, paramRect.bottom);
  }
  
  public boolean intersects(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    boolean bool;
    if (this.left < paramInt3 && paramInt1 < this.right && this.top < paramInt4 && paramInt2 < this.bottom) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isEmpty() {
    return (this.left >= this.right || this.top >= this.bottom);
  }
  
  public void offset(int paramInt1, int paramInt2) {
    this.left += paramInt1;
    this.top += paramInt2;
    this.right += paramInt1;
    this.bottom += paramInt2;
  }
  
  public void offsetTo(int paramInt1, int paramInt2) {
    this.right += paramInt1 - this.left;
    this.bottom += paramInt2 - this.top;
    this.left = paramInt1;
    this.top = paramInt2;
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
    this.left = paramParcel.readInt();
    this.top = paramParcel.readInt();
    this.right = paramParcel.readInt();
    this.bottom = paramParcel.readInt();
  }
  
  public void readFromProto(ProtoInputStream paramProtoInputStream, long paramLong) throws IOException, WireTypeMismatchException {
    paramLong = paramProtoInputStream.start(paramLong);
    try {
      while (paramProtoInputStream.nextField() != -1) {
        int i = paramProtoInputStream.getFieldNumber();
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              if (i != 4)
                continue; 
              this.bottom = paramProtoInputStream.readInt(1120986464260L);
              continue;
            } 
            this.right = paramProtoInputStream.readInt(1120986464259L);
            continue;
          } 
          this.top = paramProtoInputStream.readInt(1120986464258L);
          continue;
        } 
        this.left = paramProtoInputStream.readInt(1120986464257L);
      } 
      return;
    } finally {
      paramProtoInputStream.end(paramLong);
    } 
  }
  
  public void scale(float paramFloat) {
    if (paramFloat != 1.0F) {
      this.left = (int)(this.left * paramFloat + 0.5F);
      this.top = (int)(this.top * paramFloat + 0.5F);
      this.right = (int)(this.right * paramFloat + 0.5F);
      this.bottom = (int)(this.bottom * paramFloat + 0.5F);
    } 
  }
  
  public void set(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.left = paramInt1;
    this.top = paramInt2;
    this.right = paramInt3;
    this.bottom = paramInt4;
  }
  
  public void set(Rect paramRect) {
    this.left = paramRect.left;
    this.top = paramRect.top;
    this.right = paramRect.right;
    this.bottom = paramRect.bottom;
  }
  
  public void setEmpty() {
    this.bottom = 0;
    this.top = 0;
    this.right = 0;
    this.left = 0;
  }
  
  public boolean setIntersect(Rect paramRect1, Rect paramRect2) {
    int i = paramRect1.left;
    if (i < paramRect2.right) {
      int j = paramRect2.left;
      if (j < paramRect1.right && paramRect1.top < paramRect2.bottom && paramRect2.top < paramRect1.bottom) {
        this.left = Math.max(i, j);
        this.top = Math.max(paramRect1.top, paramRect2.top);
        this.right = Math.min(paramRect1.right, paramRect2.right);
        this.bottom = Math.min(paramRect1.bottom, paramRect2.bottom);
        return true;
      } 
    } 
    return false;
  }
  
  public void sort() {
    int i = this.left;
    int j = this.right;
    if (i > j) {
      i = this.left;
      this.left = j;
      this.right = i;
    } 
    i = this.top;
    j = this.bottom;
    if (i > j) {
      i = this.top;
      this.top = j;
      this.bottom = i;
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
    StringBuilder stringBuilder = new StringBuilder(32);
    stringBuilder.append("Rect(");
    stringBuilder.append(this.left);
    stringBuilder.append(", ");
    stringBuilder.append(this.top);
    stringBuilder.append(" - ");
    stringBuilder.append(this.right);
    stringBuilder.append(", ");
    stringBuilder.append(this.bottom);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void union(int paramInt1, int paramInt2) {
    if (paramInt1 < this.left) {
      this.left = paramInt1;
    } else if (paramInt1 > this.right) {
      this.right = paramInt1;
    } 
    if (paramInt2 < this.top) {
      this.top = paramInt2;
    } else if (paramInt2 > this.bottom) {
      this.bottom = paramInt2;
    } 
  }
  
  public void union(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 < paramInt3 && paramInt2 < paramInt4) {
      int i = this.left;
      if (i < this.right && this.top < this.bottom) {
        if (i > paramInt1)
          this.left = paramInt1; 
        if (this.top > paramInt2)
          this.top = paramInt2; 
        if (this.right < paramInt3)
          this.right = paramInt3; 
        if (this.bottom < paramInt4)
          this.bottom = paramInt4; 
      } else {
        this.left = paramInt1;
        this.top = paramInt2;
        this.right = paramInt3;
        this.bottom = paramInt4;
      } 
    } 
  }
  
  public void union(Rect paramRect) {
    union(paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public final int width() {
    return this.right - this.left;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.left);
    paramParcel.writeInt(this.top);
    paramParcel.writeInt(this.right);
    paramParcel.writeInt(this.bottom);
  }
  
  private static final class UnflattenHelper {
    private static final Pattern FLATTENED_PATTERN = Pattern.compile("(-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+)");
    
    static Matcher getMatcher(String param1String) {
      return FLATTENED_PATTERN.matcher(param1String);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Rect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */