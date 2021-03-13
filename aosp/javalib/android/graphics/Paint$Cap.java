package android.graphics;

public enum Cap {
  BUTT(0),
  ROUND(1),
  SQUARE(1);
  
  final int nativeInt;
  
  static {
    Cap cap = new Cap("SQUARE", 2, 2);
    SQUARE = cap;
    $VALUES = new Cap[] { BUTT, ROUND, cap };
  }
  
  Cap(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Paint$Cap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */