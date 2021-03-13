package android.graphics;

public enum Align {
  CENTER,
  LEFT(0),
  RIGHT(0);
  
  final int nativeInt;
  
  static {
    CENTER = new Align("CENTER", 1, 1);
    Align align = new Align("RIGHT", 2, 2);
    RIGHT = align;
    $VALUES = new Align[] { LEFT, CENTER, align };
  }
  
  Align(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Paint$Align.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */