package android.graphics;

public class Options {
  public Bitmap inBitmap;
  
  public int inDensity;
  
  public boolean inDither;
  
  @Deprecated
  public boolean inInputShareable;
  
  public boolean inJustDecodeBounds;
  
  public boolean inMutable;
  
  @Deprecated
  public boolean inPreferQualityOverSpeed;
  
  public ColorSpace inPreferredColorSpace = null;
  
  public Bitmap.Config inPreferredConfig = Bitmap.Config.ARGB_8888;
  
  public boolean inPremultiplied = true;
  
  @Deprecated
  public boolean inPurgeable;
  
  public int inSampleSize;
  
  public boolean inScaled = true;
  
  public int inScreenDensity;
  
  public int inTargetDensity;
  
  public byte[] inTempStorage;
  
  @Deprecated
  public boolean mCancel;
  
  public ColorSpace outColorSpace;
  
  public Bitmap.Config outConfig;
  
  public int outHeight;
  
  public String outMimeType;
  
  public int outWidth;
  
  static long nativeColorSpace(Options paramOptions) {
    if (paramOptions != null) {
      ColorSpace colorSpace = paramOptions.inPreferredColorSpace;
      if (colorSpace != null)
        return colorSpace.getNativeInstance(); 
    } 
    return 0L;
  }
  
  static long nativeInBitmap(Options paramOptions) {
    if (paramOptions != null) {
      Bitmap bitmap = paramOptions.inBitmap;
      if (bitmap != null)
        return bitmap.getNativeInstance(); 
    } 
    return 0L;
  }
  
  static void validate(Options paramOptions) {
    if (paramOptions == null)
      return; 
    Bitmap bitmap = paramOptions.inBitmap;
    if (bitmap != null)
      if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
        if (paramOptions.inBitmap.isRecycled())
          throw new IllegalArgumentException("Cannot reuse a recycled Bitmap"); 
      } else {
        throw new IllegalArgumentException("Bitmaps with Config.HARDWARE are always immutable");
      }  
    if (!paramOptions.inMutable || paramOptions.inPreferredConfig != Bitmap.Config.HARDWARE) {
      ColorSpace colorSpace = paramOptions.inPreferredColorSpace;
      if (colorSpace != null)
        if (colorSpace instanceof ColorSpace.Rgb) {
          if (((ColorSpace.Rgb)colorSpace).getTransferParameters() == null)
            throw new IllegalArgumentException("The destination color space must use an ICC parametric transfer function"); 
        } else {
          throw new IllegalArgumentException("The destination color space must use the RGB color model");
        }  
      return;
    } 
    throw new IllegalArgumentException("Bitmaps with Config.HARDWARE cannot be decoded into - they are immutable");
  }
  
  @Deprecated
  public void requestCancelDecode() {
    this.mCancel = true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/BitmapFactory$Options.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */