package android.graphics;

public class PathMeasure {
  public static final int POSITION_MATRIX_FLAG = 1;
  
  public static final int TANGENT_MATRIX_FLAG = 2;
  
  private Path mPath;
  
  private long native_instance;
  
  public PathMeasure() {
    this.mPath = null;
    this.native_instance = native_create(0L, false);
  }
  
  public PathMeasure(Path paramPath, boolean paramBoolean) {
    long l;
    this.mPath = paramPath;
    if (paramPath != null) {
      l = paramPath.readOnlyNI();
    } else {
      l = 0L;
    } 
    this.native_instance = native_create(l, paramBoolean);
  }
  
  private static native long native_create(long paramLong, boolean paramBoolean);
  
  private static native void native_destroy(long paramLong);
  
  private static native float native_getLength(long paramLong);
  
  private static native boolean native_getMatrix(long paramLong1, float paramFloat, long paramLong2, int paramInt);
  
  private static native boolean native_getPosTan(long paramLong, float paramFloat, float[] paramArrayOffloat1, float[] paramArrayOffloat2);
  
  private static native boolean native_getSegment(long paramLong1, float paramFloat1, float paramFloat2, long paramLong2, boolean paramBoolean);
  
  private static native boolean native_isClosed(long paramLong);
  
  private static native boolean native_nextContour(long paramLong);
  
  private static native void native_setPath(long paramLong1, long paramLong2, boolean paramBoolean);
  
  protected void finalize() throws Throwable {
    native_destroy(this.native_instance);
    this.native_instance = 0L;
  }
  
  public float getLength() {
    return native_getLength(this.native_instance);
  }
  
  public boolean getMatrix(float paramFloat, Matrix paramMatrix, int paramInt) {
    return native_getMatrix(this.native_instance, paramFloat, paramMatrix.native_instance, paramInt);
  }
  
  public boolean getPosTan(float paramFloat, float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    if ((paramArrayOffloat1 == null || paramArrayOffloat1.length >= 2) && (paramArrayOffloat2 == null || paramArrayOffloat2.length >= 2))
      return native_getPosTan(this.native_instance, paramFloat, paramArrayOffloat1, paramArrayOffloat2); 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public boolean getSegment(float paramFloat1, float paramFloat2, Path paramPath, boolean paramBoolean) {
    float f1 = getLength();
    float f2 = paramFloat1;
    if (paramFloat1 < 0.0F)
      f2 = 0.0F; 
    paramFloat1 = paramFloat2;
    if (paramFloat2 > f1)
      paramFloat1 = f1; 
    return (f2 >= paramFloat1) ? false : native_getSegment(this.native_instance, f2, paramFloat1, paramPath.mutateNI(), paramBoolean);
  }
  
  public boolean isClosed() {
    return native_isClosed(this.native_instance);
  }
  
  public boolean nextContour() {
    return native_nextContour(this.native_instance);
  }
  
  public void setPath(Path paramPath, boolean paramBoolean) {
    long l2;
    this.mPath = paramPath;
    long l1 = this.native_instance;
    if (paramPath != null) {
      l2 = paramPath.readOnlyNI();
    } else {
      l2 = 0L;
    } 
    native_setPath(l1, l2, paramBoolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PathMeasure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */