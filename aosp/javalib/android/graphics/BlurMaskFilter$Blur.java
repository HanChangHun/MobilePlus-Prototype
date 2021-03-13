package android.graphics;

public enum Blur {
  INNER,
  NORMAL(0),
  OUTER(0),
  SOLID(1);
  
  final int native_int;
  
  static {
    OUTER = new Blur("OUTER", 2, 2);
    Blur blur = new Blur("INNER", 3, 3);
    INNER = blur;
    $VALUES = new Blur[] { NORMAL, SOLID, OUTER, blur };
  }
  
  Blur(int paramInt1) {
    this.native_int = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/BlurMaskFilter$Blur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */