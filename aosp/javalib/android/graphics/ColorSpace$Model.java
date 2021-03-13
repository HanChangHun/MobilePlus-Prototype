package android.graphics;

public enum Model {
  CMYK,
  LAB,
  RGB(3),
  XYZ(3);
  
  private final int mComponentCount;
  
  static {
    LAB = new Model("LAB", 2, 3);
    Model model = new Model("CMYK", 3, 4);
    CMYK = model;
    $VALUES = new Model[] { RGB, XYZ, LAB, model };
  }
  
  Model(int paramInt1) {
    this.mComponentCount = paramInt1;
  }
  
  public int getComponentCount() {
    return this.mComponentCount;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$Model.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */