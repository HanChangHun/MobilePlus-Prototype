package android.graphics;

public enum Direction {
  CCW,
  CW(0);
  
  final int nativeInt;
  
  static {
    Direction direction = new Direction("CCW", 1, 1);
    CCW = direction;
    $VALUES = new Direction[] { CW, direction };
  }
  
  Direction(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Path$Direction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */