package android.graphics;

public enum TileMode {
  CLAMP(0),
  MIRROR(0),
  REPEAT(1);
  
  final int nativeInt;
  
  static {
    TileMode tileMode = new TileMode("MIRROR", 2, 2);
    MIRROR = tileMode;
    $VALUES = new TileMode[] { CLAMP, REPEAT, tileMode };
  }
  
  TileMode(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Shader$TileMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */