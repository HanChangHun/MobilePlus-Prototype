package android.graphics;

public enum Mode {
  AVOID(0),
  TARGET(0);
  
  final int nativeInt;
  
  static {
    Mode mode = new Mode("TARGET", 1, 1);
    TARGET = mode;
    $VALUES = new Mode[] { AVOID, mode };
  }
  
  Mode(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/AvoidXfermode$Mode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */