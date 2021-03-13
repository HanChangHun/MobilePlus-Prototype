package android.graphics;

public class NinePatch {
  private final Bitmap mBitmap;
  
  public long mNativeChunk;
  
  private Paint mPaint;
  
  private String mSrcName;
  
  public NinePatch(Bitmap paramBitmap, byte[] paramArrayOfbyte) {
    this(paramBitmap, paramArrayOfbyte, null);
  }
  
  public NinePatch(Bitmap paramBitmap, byte[] paramArrayOfbyte, String paramString) {
    this.mBitmap = paramBitmap;
    this.mSrcName = paramString;
    this.mNativeChunk = validateNinePatchChunk(paramArrayOfbyte);
  }
  
  public NinePatch(NinePatch paramNinePatch) {
    this.mBitmap = paramNinePatch.mBitmap;
    this.mSrcName = paramNinePatch.mSrcName;
    if (paramNinePatch.mPaint != null)
      this.mPaint = new Paint(paramNinePatch.mPaint); 
    this.mNativeChunk = paramNinePatch.mNativeChunk;
  }
  
  public static native boolean isNinePatchChunk(byte[] paramArrayOfbyte);
  
  private static native void nativeFinalize(long paramLong);
  
  private static native long nativeGetTransparentRegion(long paramLong1, long paramLong2, Rect paramRect);
  
  private static native long validateNinePatchChunk(byte[] paramArrayOfbyte);
  
  public void draw(Canvas paramCanvas, Rect paramRect) {
    paramCanvas.drawPatch(this, paramRect, this.mPaint);
  }
  
  public void draw(Canvas paramCanvas, Rect paramRect, Paint paramPaint) {
    paramCanvas.drawPatch(this, paramRect, paramPaint);
  }
  
  public void draw(Canvas paramCanvas, RectF paramRectF) {
    paramCanvas.drawPatch(this, paramRectF, this.mPaint);
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mNativeChunk != 0L) {
        nativeFinalize(this.mNativeChunk);
        this.mNativeChunk = 0L;
      } 
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public Bitmap getBitmap() {
    return this.mBitmap;
  }
  
  public int getDensity() {
    return this.mBitmap.mDensity;
  }
  
  public int getHeight() {
    return this.mBitmap.getHeight();
  }
  
  public String getName() {
    return this.mSrcName;
  }
  
  public Paint getPaint() {
    return this.mPaint;
  }
  
  public final Region getTransparentRegion(Rect paramRect) {
    long l = nativeGetTransparentRegion(this.mBitmap.getNativeInstance(), this.mNativeChunk, paramRect);
    if (l != 0L) {
      Region region = new Region(l);
    } else {
      paramRect = null;
    } 
    return (Region)paramRect;
  }
  
  public int getWidth() {
    return this.mBitmap.getWidth();
  }
  
  public final boolean hasAlpha() {
    return this.mBitmap.hasAlpha();
  }
  
  public void setPaint(Paint paramPaint) {
    this.mPaint = paramPaint;
  }
  
  public static class InsetStruct {
    public final Rect opticalRect;
    
    public final float outlineAlpha;
    
    public final float outlineRadius;
    
    public final Rect outlineRect;
    
    InsetStruct(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, int param1Int7, int param1Int8, float param1Float1, int param1Int9, float param1Float2) {
      Rect rect = new Rect(param1Int1, param1Int2, param1Int3, param1Int4);
      this.opticalRect = rect;
      rect.scale(param1Float2);
      this.outlineRect = scaleInsets(param1Int5, param1Int6, param1Int7, param1Int8, param1Float2);
      this.outlineRadius = param1Float1 * param1Float2;
      this.outlineAlpha = param1Int9 / 255.0F;
    }
    
    public static Rect scaleInsets(int param1Int1, int param1Int2, int param1Int3, int param1Int4, float param1Float) {
      if (param1Float == 1.0F)
        return new Rect(param1Int1, param1Int2, param1Int3, param1Int4); 
      Rect rect = new Rect();
      rect.left = (int)Math.ceil((param1Int1 * param1Float));
      rect.top = (int)Math.ceil((param1Int2 * param1Float));
      rect.right = (int)Math.ceil((param1Int3 * param1Float));
      rect.bottom = (int)Math.ceil((param1Int4 * param1Float));
      return rect;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/NinePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */