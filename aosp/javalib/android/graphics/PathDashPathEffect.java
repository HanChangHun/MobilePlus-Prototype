package android.graphics;

public class PathDashPathEffect extends PathEffect {
  public PathDashPathEffect(Path paramPath, float paramFloat1, float paramFloat2, Style paramStyle) {
    this.native_instance = nativeCreate(paramPath.readOnlyNI(), paramFloat1, paramFloat2, paramStyle.native_style);
  }
  
  private static native long nativeCreate(long paramLong, float paramFloat1, float paramFloat2, int paramInt);
  
  public enum Style {
    MORPH,
    ROTATE,
    TRANSLATE(0);
    
    int native_style;
    
    static {
      Style style = new Style("MORPH", 2, 2);
      MORPH = style;
      $VALUES = new Style[] { TRANSLATE, ROTATE, style };
    }
    
    Style(int param1Int1) {
      this.native_style = param1Int1;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PathDashPathEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */