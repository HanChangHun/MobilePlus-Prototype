package android.graphics;

public class PorterDuff {
  public static Mode intToMode(int paramInt) {
    switch (paramInt) {
      default:
        return Mode.CLEAR;
      case 17:
        return Mode.LIGHTEN;
      case 16:
        return Mode.DARKEN;
      case 15:
        return Mode.OVERLAY;
      case 14:
        return Mode.SCREEN;
      case 13:
        return Mode.MULTIPLY;
      case 12:
        return Mode.ADD;
      case 11:
        return Mode.XOR;
      case 10:
        return Mode.DST_ATOP;
      case 9:
        return Mode.SRC_ATOP;
      case 8:
        return Mode.DST_OUT;
      case 7:
        return Mode.SRC_OUT;
      case 6:
        return Mode.DST_IN;
      case 5:
        return Mode.SRC_IN;
      case 4:
        return Mode.DST_OVER;
      case 3:
        return Mode.SRC_OVER;
      case 2:
        return Mode.DST;
      case 1:
        break;
    } 
    return Mode.SRC;
  }
  
  public static int modeToInt(Mode paramMode) {
    return paramMode.nativeInt;
  }
  
  public enum Mode {
    ADD,
    CLEAR(0),
    DARKEN(0),
    DST(0),
    DST_ATOP(0),
    DST_IN(0),
    DST_OUT(0),
    DST_OVER(0),
    LIGHTEN(0),
    MULTIPLY(0),
    OVERLAY(0),
    SCREEN(0),
    SRC(1),
    SRC_ATOP(1),
    SRC_IN(1),
    SRC_OUT(1),
    SRC_OVER(1),
    XOR(1);
    
    public final int nativeInt;
    
    static {
      DST_OVER = new Mode("DST_OVER", 4, 4);
      SRC_IN = new Mode("SRC_IN", 5, 5);
      DST_IN = new Mode("DST_IN", 6, 6);
      SRC_OUT = new Mode("SRC_OUT", 7, 7);
      DST_OUT = new Mode("DST_OUT", 8, 8);
      SRC_ATOP = new Mode("SRC_ATOP", 9, 9);
      DST_ATOP = new Mode("DST_ATOP", 10, 10);
      XOR = new Mode("XOR", 11, 11);
      DARKEN = new Mode("DARKEN", 12, 16);
      LIGHTEN = new Mode("LIGHTEN", 13, 17);
      MULTIPLY = new Mode("MULTIPLY", 14, 13);
      SCREEN = new Mode("SCREEN", 15, 14);
      ADD = new Mode("ADD", 16, 12);
      Mode mode = new Mode("OVERLAY", 17, 15);
      OVERLAY = mode;
      $VALUES = new Mode[] { 
          CLEAR, SRC, DST, SRC_OVER, DST_OVER, SRC_IN, DST_IN, SRC_OUT, DST_OUT, SRC_ATOP, 
          DST_ATOP, XOR, DARKEN, LIGHTEN, MULTIPLY, SCREEN, ADD, mode };
    }
    
    Mode(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PorterDuff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */