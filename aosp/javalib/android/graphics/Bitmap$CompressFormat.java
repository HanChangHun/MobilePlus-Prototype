package android.graphics;

public enum CompressFormat {
  JPEG(0),
  PNG(1),
  WEBP(2),
  WEBP_LOSSLESS(2),
  WEBP_LOSSY(3);
  
  final int nativeInt;
  
  static {
    CompressFormat compressFormat = new CompressFormat("WEBP_LOSSLESS", 4, 4);
    WEBP_LOSSLESS = compressFormat;
    $VALUES = new CompressFormat[] { JPEG, PNG, WEBP, WEBP_LOSSY, compressFormat };
  }
  
  CompressFormat(int paramInt1) {
    this.nativeInt = paramInt1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Bitmap$CompressFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */