package android.graphics;

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
    DST = new Mode("DST", 2, 2);
    SRC_OVER = new Mode("SRC_OVER", 3, 3);
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
  
  Mode(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PorterDuff$Mode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */