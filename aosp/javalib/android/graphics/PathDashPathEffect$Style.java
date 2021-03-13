package android.graphics;

public enum Style {
  MORPH,
  ROTATE,
  TRANSLATE(0);
  
  int native_style;
  
  static {
    ROTATE = new Style("ROTATE", 1, 1);
    Style style = new Style("MORPH", 2, 2);
    MORPH = style;
    $VALUES = new Style[] { TRANSLATE, ROTATE, style };
  }
  
  Style(int paramInt1) {
    this.native_style = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PathDashPathEffect$Style.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */