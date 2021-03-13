package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Size;
import android.util.proto.ProtoOutputStream;
import java.io.PrintWriter;

public class Point implements Parcelable {
  public static final Parcelable.Creator<Point> CREATOR = new Parcelable.Creator<Point>() {
      public Point createFromParcel(Parcel param1Parcel) {
        Point point = new Point();
        point.readFromParcel(param1Parcel);
        return point;
      }
      
      public Point[] newArray(int param1Int) {
        return new Point[param1Int];
      }
    };
  
  public int x;
  
  public int y;
  
  public Point() {}
  
  public Point(int paramInt1, int paramInt2) {
    this.x = paramInt1;
    this.y = paramInt2;
  }
  
  public Point(Point paramPoint) {
    this.x = paramPoint.x;
    this.y = paramPoint.y;
  }
  
  public static Point convert(Size paramSize) {
    return new Point(paramSize.getWidth(), paramSize.getHeight());
  }
  
  public static Size convert(Point paramPoint) {
    return new Size(paramPoint.x, paramPoint.y);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    paramProtoOutputStream.write(1120986464257L, this.x);
    paramProtoOutputStream.write(1120986464258L, this.y);
    paramProtoOutputStream.end(paramLong);
  }
  
  public final boolean equals(int paramInt1, int paramInt2) {
    boolean bool;
    if (this.x == paramInt1 && this.y == paramInt2) {
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
    return (this.x != ((Point)paramObject).x) ? false : (!(this.y != ((Point)paramObject).y));
  }
  
  public int hashCode() {
    return this.x * 31 + this.y;
  }
  
  public final void negate() {
    this.x = -this.x;
    this.y = -this.y;
  }
  
  public final void offset(int paramInt1, int paramInt2) {
    this.x += paramInt1;
    this.y += paramInt2;
  }
  
  public void printShortString(PrintWriter paramPrintWriter) {
    paramPrintWriter.print("[");
    paramPrintWriter.print(this.x);
    paramPrintWriter.print(",");
    paramPrintWriter.print(this.y);
    paramPrintWriter.print("]");
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.x = paramParcel.readInt();
    this.y = paramParcel.readInt();
  }
  
  public void set(int paramInt1, int paramInt2) {
    this.x = paramInt1;
    this.y = paramInt2;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Point(");
    stringBuilder.append(this.x);
    stringBuilder.append(", ");
    stringBuilder.append(this.y);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.x);
    paramParcel.writeInt(this.y);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Point.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */