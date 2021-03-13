package android.graphics;

public enum EdgeType {
  AA,
  BW(0);
  
  public final int nativeInt;
  
  static {
    EdgeType edgeType = new EdgeType("AA", 1, 1);
    AA = edgeType;
    $VALUES = new EdgeType[] { BW, edgeType };
  }
  
  EdgeType(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Canvas$EdgeType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */