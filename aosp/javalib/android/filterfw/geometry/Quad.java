package android.filterfw.geometry;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quad {
  public Point p0;
  
  public Point p1;
  
  public Point p2;
  
  public Point p3;
  
  public Quad() {}
  
  public Quad(Point paramPoint1, Point paramPoint2, Point paramPoint3, Point paramPoint4) {
    this.p0 = paramPoint1;
    this.p1 = paramPoint2;
    this.p2 = paramPoint3;
    this.p3 = paramPoint4;
  }
  
  public boolean IsInUnitRange() {
    boolean bool;
    if (this.p0.IsInUnitRange() && this.p1.IsInUnitRange() && this.p2.IsInUnitRange() && this.p3.IsInUnitRange()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Rectangle boundingBox() {
    List<Float> list1 = Arrays.asList(new Float[] { Float.valueOf(this.p0.x), Float.valueOf(this.p1.x), Float.valueOf(this.p2.x), Float.valueOf(this.p3.x) });
    List<Float> list2 = Arrays.asList(new Float[] { Float.valueOf(this.p0.y), Float.valueOf(this.p1.y), Float.valueOf(this.p2.y), Float.valueOf(this.p3.y) });
    float f1 = ((Float)Collections.<Float>min(list1)).floatValue();
    float f2 = ((Float)Collections.<Float>min(list2)).floatValue();
    return new Rectangle(f1, f2, ((Float)Collections.<Float>max(list1)).floatValue() - f1, ((Float)Collections.<Float>max(list2)).floatValue() - f2);
  }
  
  public float getBoundingHeight() {
    List<Float> list = Arrays.asList(new Float[] { Float.valueOf(this.p0.y), Float.valueOf(this.p1.y), Float.valueOf(this.p2.y), Float.valueOf(this.p3.y) });
    return ((Float)Collections.<Float>max(list)).floatValue() - ((Float)Collections.<Float>min(list)).floatValue();
  }
  
  public float getBoundingWidth() {
    List<Float> list = Arrays.asList(new Float[] { Float.valueOf(this.p0.x), Float.valueOf(this.p1.x), Float.valueOf(this.p2.x), Float.valueOf(this.p3.x) });
    return ((Float)Collections.<Float>max(list)).floatValue() - ((Float)Collections.<Float>min(list)).floatValue();
  }
  
  public Quad scaled(float paramFloat) {
    return new Quad(this.p0.times(paramFloat), this.p1.times(paramFloat), this.p2.times(paramFloat), this.p3.times(paramFloat));
  }
  
  public Quad scaled(float paramFloat1, float paramFloat2) {
    return new Quad(this.p0.mult(paramFloat1, paramFloat2), this.p1.mult(paramFloat1, paramFloat2), this.p2.mult(paramFloat1, paramFloat2), this.p3.mult(paramFloat1, paramFloat2));
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(this.p0);
    stringBuilder.append(", ");
    stringBuilder.append(this.p1);
    stringBuilder.append(", ");
    stringBuilder.append(this.p2);
    stringBuilder.append(", ");
    stringBuilder.append(this.p3);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public Quad translated(float paramFloat1, float paramFloat2) {
    return new Quad(this.p0.plus(paramFloat1, paramFloat2), this.p1.plus(paramFloat1, paramFloat2), this.p2.plus(paramFloat1, paramFloat2), this.p3.plus(paramFloat1, paramFloat2));
  }
  
  public Quad translated(Point paramPoint) {
    return new Quad(this.p0.plus(paramPoint), this.p1.plus(paramPoint), this.p2.plus(paramPoint), this.p3.plus(paramPoint));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/geometry/Quad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */