package android.graphics;

public enum FillType {
  EVEN_ODD,
  INVERSE_EVEN_ODD,
  INVERSE_WINDING,
  WINDING(0);
  
  final int nativeInt;
  
  static {
    EVEN_ODD = new FillType("EVEN_ODD", 1, 1);
    INVERSE_WINDING = new FillType("INVERSE_WINDING", 2, 2);
    FillType fillType = new FillType("INVERSE_EVEN_ODD", 3, 3);
    INVERSE_EVEN_ODD = fillType;
    $VALUES = new FillType[] { WINDING, EVEN_ODD, INVERSE_WINDING, fillType };
  }
  
  FillType(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Path$FillType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */