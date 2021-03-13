package android.graphics;

public enum Op {
  DIFFERENCE(0),
  INTERSECT(1),
  REPLACE(1),
  REVERSE_DIFFERENCE(1),
  UNION(2),
  XOR(3);
  
  public final int nativeInt;
  
  static {
    REVERSE_DIFFERENCE = new Op("REVERSE_DIFFERENCE", 4, 4);
    Op op = new Op("REPLACE", 5, 5);
    REPLACE = op;
    $VALUES = new Op[] { DIFFERENCE, INTERSECT, UNION, XOR, REVERSE_DIFFERENCE, op };
  }
  
  Op(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Region$Op.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */