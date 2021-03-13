package android.graphics;

public enum VertexMode {
  TRIANGLES(0),
  TRIANGLE_FAN(0),
  TRIANGLE_STRIP(1);
  
  public final int nativeInt;
  
  static {
    VertexMode vertexMode = new VertexMode("TRIANGLE_FAN", 2, 2);
    TRIANGLE_FAN = vertexMode;
    $VALUES = new VertexMode[] { TRIANGLES, TRIANGLE_STRIP, vertexMode };
  }
  
  VertexMode(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Canvas$VertexMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */