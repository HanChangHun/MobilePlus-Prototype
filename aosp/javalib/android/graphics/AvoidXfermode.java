package android.graphics;

@Deprecated
public class AvoidXfermode extends Xfermode {
  public AvoidXfermode(int paramInt1, int paramInt2, Mode paramMode) {
    if (paramInt2 >= 0 && paramInt2 <= 255)
      return; 
    throw new IllegalArgumentException("tolerance must be 0..255");
  }
  
  public enum Mode {
    AVOID(0),
    TARGET(0);
    
    final int nativeInt;
    
    static {
      Mode mode = new Mode("TARGET", 1, 1);
      TARGET = mode;
      $VALUES = new Mode[] { AVOID, mode };
    }
    
    Mode(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/AvoidXfermode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */