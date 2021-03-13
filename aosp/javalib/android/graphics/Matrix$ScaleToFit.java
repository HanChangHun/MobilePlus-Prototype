package android.graphics;

public enum ScaleToFit {
  CENTER,
  END,
  FILL(0),
  START(1);
  
  final int nativeInt;
  
  static {
    CENTER = new ScaleToFit("CENTER", 2, 2);
    ScaleToFit scaleToFit = new ScaleToFit("END", 3, 3);
    END = scaleToFit;
    $VALUES = new ScaleToFit[] { FILL, START, CENTER, scaleToFit };
  }
  
  ScaleToFit(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Matrix$ScaleToFit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */