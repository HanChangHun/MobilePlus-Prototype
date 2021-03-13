package android.graphics;

public class TableMaskFilter extends MaskFilter {
  private TableMaskFilter(long paramLong) {
    this.native_instance = paramLong;
  }
  
  public TableMaskFilter(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte.length >= 256) {
      this.native_instance = nativeNewTable(paramArrayOfbyte);
      return;
    } 
    throw new RuntimeException("table.length must be >= 256");
  }
  
  public static TableMaskFilter CreateClipTable(int paramInt1, int paramInt2) {
    return new TableMaskFilter(nativeNewClip(paramInt1, paramInt2));
  }
  
  public static TableMaskFilter CreateGammaTable(float paramFloat) {
    return new TableMaskFilter(nativeNewGamma(paramFloat));
  }
  
  private static native long nativeNewClip(int paramInt1, int paramInt2);
  
  private static native long nativeNewGamma(float paramFloat);
  
  private static native long nativeNewTable(byte[] paramArrayOfbyte);
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/TableMaskFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */