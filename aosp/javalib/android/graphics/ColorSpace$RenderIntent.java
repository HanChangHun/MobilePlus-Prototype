package android.graphics;

public enum RenderIntent {
  ABSOLUTE, PERCEPTUAL, RELATIVE, SATURATION;
  
  static {
    RenderIntent renderIntent = new RenderIntent("ABSOLUTE", 3);
    ABSOLUTE = renderIntent;
    $VALUES = new RenderIntent[] { PERCEPTUAL, RELATIVE, SATURATION, renderIntent };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$RenderIntent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */