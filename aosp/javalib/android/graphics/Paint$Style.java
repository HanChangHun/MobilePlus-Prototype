package android.graphics;

public enum Style {
  FILL(0),
  FILL_AND_STROKE(0),
  STROKE(1);
  
  final int nativeInt;
  
  static {
    Style style = new Style("FILL_AND_STROKE", 2, 2);
    FILL_AND_STROKE = style;
    $VALUES = new Style[] { FILL, STROKE, style };
  }
  
  Style(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Paint$Style.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */