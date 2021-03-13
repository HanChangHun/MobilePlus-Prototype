package android.graphics.drawable;

class null extends RippleBackground.BackgroundProperty {
  null(String paramString) {
    super(paramString);
  }
  
  public Float get(RippleBackground paramRippleBackground) {
    return Float.valueOf(RippleBackground.access$000(paramRippleBackground));
  }
  
  public void setValue(RippleBackground paramRippleBackground, float paramFloat) {
    RippleBackground.access$002(paramRippleBackground, paramFloat);
    paramRippleBackground.invalidateSelf();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/RippleBackground$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */