package android.graphics;

public enum Join {
  BEVEL,
  MITER(0),
  ROUND(1);
  
  final int nativeInt;
  
  static {
    Join join = new Join("BEVEL", 2, 2);
    BEVEL = join;
    $VALUES = new Join[] { MITER, ROUND, join };
  }
  
  Join(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Paint$Join.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */