package android.graphics;

public enum BlendMode {
  CLEAR(0),
  COLOR(0),
  COLOR_BURN(0),
  COLOR_DODGE(0),
  DARKEN(0),
  DIFFERENCE(0),
  DST(0),
  DST_ATOP(0),
  DST_IN(0),
  DST_OUT(0),
  DST_OVER(0),
  EXCLUSION(0),
  HARD_LIGHT(0),
  HUE(0),
  LIGHTEN(0),
  LUMINOSITY(0),
  MODULATE(0),
  MULTIPLY(0),
  OVERLAY(0),
  PLUS(0),
  SATURATION(0),
  SCREEN(0),
  SOFT_LIGHT(0),
  SRC(1),
  SRC_ATOP(1),
  SRC_IN(1),
  SRC_OUT(1),
  SRC_OVER(1),
  XOR(1);
  
  private static final BlendMode[] BLEND_MODES;
  
  private final Xfermode mXfermode;
  
  static {
    DST = new BlendMode("DST", 2, 2);
    SRC_OVER = new BlendMode("SRC_OVER", 3, 3);
    DST_OVER = new BlendMode("DST_OVER", 4, 4);
    SRC_IN = new BlendMode("SRC_IN", 5, 5);
    DST_IN = new BlendMode("DST_IN", 6, 6);
    SRC_OUT = new BlendMode("SRC_OUT", 7, 7);
    DST_OUT = new BlendMode("DST_OUT", 8, 8);
    SRC_ATOP = new BlendMode("SRC_ATOP", 9, 9);
    DST_ATOP = new BlendMode("DST_ATOP", 10, 10);
    XOR = new BlendMode("XOR", 11, 11);
    PLUS = new BlendMode("PLUS", 12, 12);
    MODULATE = new BlendMode("MODULATE", 13, 13);
    SCREEN = new BlendMode("SCREEN", 14, 14);
    OVERLAY = new BlendMode("OVERLAY", 15, 15);
    DARKEN = new BlendMode("DARKEN", 16, 16);
    LIGHTEN = new BlendMode("LIGHTEN", 17, 17);
    COLOR_DODGE = new BlendMode("COLOR_DODGE", 18, 18);
    COLOR_BURN = new BlendMode("COLOR_BURN", 19, 19);
    HARD_LIGHT = new BlendMode("HARD_LIGHT", 20, 20);
    SOFT_LIGHT = new BlendMode("SOFT_LIGHT", 21, 21);
    DIFFERENCE = new BlendMode("DIFFERENCE", 22, 22);
    EXCLUSION = new BlendMode("EXCLUSION", 23, 23);
    MULTIPLY = new BlendMode("MULTIPLY", 24, 24);
    HUE = new BlendMode("HUE", 25, 25);
    SATURATION = new BlendMode("SATURATION", 26, 26);
    COLOR = new BlendMode("COLOR", 27, 27);
    BlendMode blendMode = new BlendMode("LUMINOSITY", 28, 28);
    LUMINOSITY = blendMode;
    $VALUES = new BlendMode[] { 
        CLEAR, SRC, DST, SRC_OVER, DST_OVER, SRC_IN, DST_IN, SRC_OUT, DST_OUT, SRC_ATOP, 
        DST_ATOP, XOR, PLUS, MODULATE, SCREEN, OVERLAY, DARKEN, LIGHTEN, COLOR_DODGE, COLOR_BURN, 
        HARD_LIGHT, SOFT_LIGHT, DIFFERENCE, EXCLUSION, MULTIPLY, HUE, SATURATION, COLOR, blendMode };
    BLEND_MODES = values();
  }
  
  BlendMode(int paramInt1) {
    Xfermode xfermode = new Xfermode();
    this.mXfermode = xfermode;
    xfermode.porterDuffMode = paramInt1;
  }
  
  public static PorterDuff.Mode blendModeToPorterDuffMode(BlendMode paramBlendMode) {
    if (paramBlendMode != null) {
      switch (paramBlendMode) {
        default:
          return null;
        case null:
          return PorterDuff.Mode.OVERLAY;
        case null:
          return PorterDuff.Mode.ADD;
        case null:
          return PorterDuff.Mode.SCREEN;
        case null:
          return PorterDuff.Mode.MULTIPLY;
        case null:
          return PorterDuff.Mode.LIGHTEN;
        case null:
          return PorterDuff.Mode.DARKEN;
        case null:
          return PorterDuff.Mode.XOR;
        case null:
          return PorterDuff.Mode.DST_ATOP;
        case null:
          return PorterDuff.Mode.SRC_ATOP;
        case null:
          return PorterDuff.Mode.DST_OUT;
        case null:
          return PorterDuff.Mode.SRC_OUT;
        case null:
          return PorterDuff.Mode.DST_IN;
        case null:
          return PorterDuff.Mode.SRC_IN;
        case null:
          return PorterDuff.Mode.DST_OVER;
        case null:
          return PorterDuff.Mode.SRC_OVER;
        case null:
          return PorterDuff.Mode.DST;
        case null:
          return PorterDuff.Mode.SRC;
        case null:
          break;
      } 
      return PorterDuff.Mode.CLEAR;
    } 
    return null;
  }
  
  public static BlendMode fromValue(int paramInt) {
    for (BlendMode blendMode : BLEND_MODES) {
      if (blendMode.mXfermode.porterDuffMode == paramInt)
        return blendMode; 
    } 
    return null;
  }
  
  public static int toValue(BlendMode paramBlendMode) {
    return (paramBlendMode.getXfermode()).porterDuffMode;
  }
  
  public Xfermode getXfermode() {
    return this.mXfermode;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/BlendMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */