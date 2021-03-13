package android.graphics;

public enum Op {
  DIFFERENCE, INTERSECT, REVERSE_DIFFERENCE, UNION, XOR;
  
  static {
    Op op = new Op("REVERSE_DIFFERENCE", 4);
    REVERSE_DIFFERENCE = op;
    $VALUES = new Op[] { DIFFERENCE, INTERSECT, UNION, XOR, op };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Path$Op.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */