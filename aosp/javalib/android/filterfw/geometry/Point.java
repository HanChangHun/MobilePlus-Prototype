package android.filterfw.geometry;

public class Point {
  public float x;
  
  public float y;
  
  public Point() {}
  
  public Point(float paramFloat1, float paramFloat2) {
    this.x = paramFloat1;
    this.y = paramFloat2;
  }
  
  public boolean IsInUnitRange() {
    float f = this.x;
    if (f >= 0.0F && f <= 1.0F) {
      f = this.y;
      if (f >= 0.0F && f <= 1.0F)
        return true; 
    } 
    return false;
  }
  
  public float distanceTo(Point paramPoint) {
    return paramPoint.minus(this).length();
  }
  
  public float length() {
    return (float)Math.hypot(this.x, this.y);
  }
  
  public Point minus(float paramFloat1, float paramFloat2) {
    return new Point(this.x - paramFloat1, this.y - paramFloat2);
  }
  
  public Point minus(Point paramPoint) {
    return minus(paramPoint.x, paramPoint.y);
  }
  
  public Point mult(float paramFloat1, float paramFloat2) {
    return new Point(this.x * paramFloat1, this.y * paramFloat2);
  }
  
  public Point normalize() {
    return scaledTo(1.0F);
  }
  
  public Point plus(float paramFloat1, float paramFloat2) {
    return new Point(this.x + paramFloat1, this.y + paramFloat2);
  }
  
  public Point plus(Point paramPoint) {
    return plus(paramPoint.x, paramPoint.y);
  }
  
  public Point rotated(float paramFloat) {
    return new Point((float)(Math.cos(paramFloat) * this.x - Math.sin(paramFloat) * this.y), (float)(Math.sin(paramFloat) * this.x + Math.cos(paramFloat) * this.y));
  }
  
  public Point rotated90(int paramInt) {
    float f1 = this.x;
    float f2 = this.y;
    byte b = 0;
    while (b < paramInt) {
      float f = -f1;
      b++;
      f1 = f2;
      f2 = f;
    } 
    return new Point(f1, f2);
  }
  
  public Point rotatedAround(Point paramPoint, float paramFloat) {
    return minus(paramPoint).rotated(paramFloat).plus(paramPoint);
  }
  
  public Point scaledTo(float paramFloat) {
    return times(paramFloat / length());
  }
  
  public void set(float paramFloat1, float paramFloat2) {
    this.x = paramFloat1;
    this.y = paramFloat2;
  }
  
  public Point times(float paramFloat) {
    return new Point(this.x * paramFloat, this.y * paramFloat);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("(");
    stringBuilder.append(this.x);
    stringBuilder.append(", ");
    stringBuilder.append(this.y);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/geometry/Point.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */