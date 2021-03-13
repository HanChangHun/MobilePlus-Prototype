package android.graphics;

import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;
import libcore.util.NativeAllocationRegistry;

public class Path {
  static final FillType[] sFillTypeArray;
  
  private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(Path.class.getClassLoader(), nGetFinalizer());
  
  public boolean isSimplePath = true;
  
  private Direction mLastDirection = null;
  
  public final long mNativePath;
  
  public Region rects;
  
  static {
    sFillTypeArray = new FillType[] { FillType.WINDING, FillType.EVEN_ODD, FillType.INVERSE_WINDING, FillType.INVERSE_EVEN_ODD };
  }
  
  public Path() {
    long l = nInit();
    this.mNativePath = l;
    sRegistry.registerNativeAllocation(this, l);
  }
  
  public Path(Path paramPath) {
    long l = 0L;
    if (paramPath != null) {
      long l1 = paramPath.mNativePath;
      this.isSimplePath = paramPath.isSimplePath;
      l = l1;
      if (paramPath.rects != null) {
        this.rects = new Region(paramPath.rects);
        l = l1;
      } 
    } 
    l = nInit(l);
    this.mNativePath = l;
    sRegistry.registerNativeAllocation(this, l);
  }
  
  private void detectSimplePath(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Direction paramDirection) {
    if (this.mLastDirection == null)
      this.mLastDirection = paramDirection; 
    if (this.mLastDirection != paramDirection) {
      this.isSimplePath = false;
    } else {
      if (this.rects == null)
        this.rects = new Region(); 
      this.rects.op((int)paramFloat1, (int)paramFloat2, (int)paramFloat3, (int)paramFloat4, Region.Op.UNION);
    } 
  }
  
  private static native void nAddArc(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6);
  
  private static native void nAddCircle(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt);
  
  private static native void nAddOval(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt);
  
  private static native void nAddPath(long paramLong1, long paramLong2);
  
  private static native void nAddPath(long paramLong1, long paramLong2, float paramFloat1, float paramFloat2);
  
  private static native void nAddPath(long paramLong1, long paramLong2, long paramLong3);
  
  private static native void nAddRect(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt);
  
  private static native void nAddRoundRect(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, int paramInt);
  
  private static native void nAddRoundRect(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float[] paramArrayOffloat, int paramInt);
  
  private static native float[] nApproximate(long paramLong, float paramFloat);
  
  private static native void nArcTo(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, boolean paramBoolean);
  
  private static native void nClose(long paramLong);
  
  private static native void nComputeBounds(long paramLong, RectF paramRectF);
  
  private static native void nCubicTo(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6);
  
  @CriticalNative
  private static native int nGetFillType(long paramLong);
  
  private static native long nGetFinalizer();
  
  private static native void nIncReserve(long paramLong, int paramInt);
  
  private static native long nInit();
  
  private static native long nInit(long paramLong);
  
  @CriticalNative
  private static native boolean nIsConvex(long paramLong);
  
  @CriticalNative
  private static native boolean nIsEmpty(long paramLong);
  
  @FastNative
  private static native boolean nIsRect(long paramLong, RectF paramRectF);
  
  private static native void nLineTo(long paramLong, float paramFloat1, float paramFloat2);
  
  private static native void nMoveTo(long paramLong, float paramFloat1, float paramFloat2);
  
  private static native void nOffset(long paramLong, float paramFloat1, float paramFloat2);
  
  private static native boolean nOp(long paramLong1, long paramLong2, int paramInt, long paramLong3);
  
  private static native void nQuadTo(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  private static native void nRCubicTo(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6);
  
  private static native void nRLineTo(long paramLong, float paramFloat1, float paramFloat2);
  
  private static native void nRMoveTo(long paramLong, float paramFloat1, float paramFloat2);
  
  private static native void nRQuadTo(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  @CriticalNative
  private static native void nReset(long paramLong);
  
  @CriticalNative
  private static native void nRewind(long paramLong);
  
  private static native void nSet(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nSetFillType(long paramLong, int paramInt);
  
  private static native void nSetLastPoint(long paramLong, float paramFloat1, float paramFloat2);
  
  private static native void nTransform(long paramLong1, long paramLong2);
  
  private static native void nTransform(long paramLong1, long paramLong2, long paramLong3);
  
  public void addArc(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    this.isSimplePath = false;
    nAddArc(this.mNativePath, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6);
  }
  
  public void addArc(RectF paramRectF, float paramFloat1, float paramFloat2) {
    addArc(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramFloat1, paramFloat2);
  }
  
  public void addCircle(float paramFloat1, float paramFloat2, float paramFloat3, Direction paramDirection) {
    this.isSimplePath = false;
    nAddCircle(this.mNativePath, paramFloat1, paramFloat2, paramFloat3, paramDirection.nativeInt);
  }
  
  public void addOval(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Direction paramDirection) {
    this.isSimplePath = false;
    nAddOval(this.mNativePath, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramDirection.nativeInt);
  }
  
  public void addOval(RectF paramRectF, Direction paramDirection) {
    addOval(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramDirection);
  }
  
  public void addPath(Path paramPath) {
    this.isSimplePath = false;
    nAddPath(this.mNativePath, paramPath.mNativePath);
  }
  
  public void addPath(Path paramPath, float paramFloat1, float paramFloat2) {
    this.isSimplePath = false;
    nAddPath(this.mNativePath, paramPath.mNativePath, paramFloat1, paramFloat2);
  }
  
  public void addPath(Path paramPath, Matrix paramMatrix) {
    if (!paramPath.isSimplePath)
      this.isSimplePath = false; 
    nAddPath(this.mNativePath, paramPath.mNativePath, paramMatrix.native_instance);
  }
  
  public void addRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Direction paramDirection) {
    detectSimplePath(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramDirection);
    nAddRect(this.mNativePath, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramDirection.nativeInt);
  }
  
  public void addRect(RectF paramRectF, Direction paramDirection) {
    addRect(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramDirection);
  }
  
  public void addRoundRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, Direction paramDirection) {
    this.isSimplePath = false;
    nAddRoundRect(this.mNativePath, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramDirection.nativeInt);
  }
  
  public void addRoundRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float[] paramArrayOffloat, Direction paramDirection) {
    if (paramArrayOffloat.length >= 8) {
      this.isSimplePath = false;
      nAddRoundRect(this.mNativePath, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramArrayOffloat, paramDirection.nativeInt);
      return;
    } 
    throw new ArrayIndexOutOfBoundsException("radii[] needs 8 values");
  }
  
  public void addRoundRect(RectF paramRectF, float paramFloat1, float paramFloat2, Direction paramDirection) {
    addRoundRect(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramFloat1, paramFloat2, paramDirection);
  }
  
  public void addRoundRect(RectF paramRectF, float[] paramArrayOffloat, Direction paramDirection) {
    if (paramRectF != null) {
      addRoundRect(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramArrayOffloat, paramDirection);
      return;
    } 
    throw new NullPointerException("need rect parameter");
  }
  
  public float[] approximate(float paramFloat) {
    if (paramFloat >= 0.0F)
      return nApproximate(this.mNativePath, paramFloat); 
    throw new IllegalArgumentException("AcceptableError must be greater than or equal to 0");
  }
  
  public void arcTo(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, boolean paramBoolean) {
    this.isSimplePath = false;
    nArcTo(this.mNativePath, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramBoolean);
  }
  
  public void arcTo(RectF paramRectF, float paramFloat1, float paramFloat2) {
    arcTo(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramFloat1, paramFloat2, false);
  }
  
  public void arcTo(RectF paramRectF, float paramFloat1, float paramFloat2, boolean paramBoolean) {
    arcTo(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramFloat1, paramFloat2, paramBoolean);
  }
  
  public void close() {
    this.isSimplePath = false;
    nClose(this.mNativePath);
  }
  
  public void computeBounds(RectF paramRectF, boolean paramBoolean) {
    nComputeBounds(this.mNativePath, paramRectF);
  }
  
  public void cubicTo(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    this.isSimplePath = false;
    nCubicTo(this.mNativePath, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6);
  }
  
  public FillType getFillType() {
    return sFillTypeArray[nGetFillType(this.mNativePath)];
  }
  
  public void incReserve(int paramInt) {
    nIncReserve(this.mNativePath, paramInt);
  }
  
  @Deprecated
  public boolean isConvex() {
    return nIsConvex(this.mNativePath);
  }
  
  public boolean isEmpty() {
    return nIsEmpty(this.mNativePath);
  }
  
  public boolean isInverseFillType() {
    boolean bool;
    int i = nGetFillType(this.mNativePath);
    if ((FillType.INVERSE_WINDING.nativeInt & i) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isRect(RectF paramRectF) {
    return nIsRect(this.mNativePath, paramRectF);
  }
  
  public void lineTo(float paramFloat1, float paramFloat2) {
    this.isSimplePath = false;
    nLineTo(this.mNativePath, paramFloat1, paramFloat2);
  }
  
  public void moveTo(float paramFloat1, float paramFloat2) {
    nMoveTo(this.mNativePath, paramFloat1, paramFloat2);
  }
  
  final long mutateNI() {
    this.isSimplePath = false;
    return this.mNativePath;
  }
  
  public void offset(float paramFloat1, float paramFloat2) {
    if (this.isSimplePath && this.rects == null)
      return; 
    if (this.isSimplePath && paramFloat1 == Math.rint(paramFloat1) && paramFloat2 == Math.rint(paramFloat2)) {
      this.rects.translate((int)paramFloat1, (int)paramFloat2);
    } else {
      this.isSimplePath = false;
    } 
    nOffset(this.mNativePath, paramFloat1, paramFloat2);
  }
  
  public void offset(float paramFloat1, float paramFloat2, Path paramPath) {
    if (paramPath != null) {
      paramPath.set(this);
    } else {
      paramPath = this;
    } 
    paramPath.offset(paramFloat1, paramFloat2);
  }
  
  public boolean op(Path paramPath, Op paramOp) {
    return op(this, paramPath, paramOp);
  }
  
  public boolean op(Path paramPath1, Path paramPath2, Op paramOp) {
    if (nOp(paramPath1.mNativePath, paramPath2.mNativePath, paramOp.ordinal(), this.mNativePath)) {
      this.isSimplePath = false;
      this.rects = null;
      return true;
    } 
    return false;
  }
  
  public void quadTo(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    this.isSimplePath = false;
    nQuadTo(this.mNativePath, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public void rCubicTo(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    this.isSimplePath = false;
    nRCubicTo(this.mNativePath, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6);
  }
  
  public void rLineTo(float paramFloat1, float paramFloat2) {
    this.isSimplePath = false;
    nRLineTo(this.mNativePath, paramFloat1, paramFloat2);
  }
  
  public void rMoveTo(float paramFloat1, float paramFloat2) {
    nRMoveTo(this.mNativePath, paramFloat1, paramFloat2);
  }
  
  public void rQuadTo(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    this.isSimplePath = false;
    nRQuadTo(this.mNativePath, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public final long readOnlyNI() {
    return this.mNativePath;
  }
  
  public void reset() {
    this.isSimplePath = true;
    this.mLastDirection = null;
    Region region = this.rects;
    if (region != null)
      region.setEmpty(); 
    FillType fillType = getFillType();
    nReset(this.mNativePath);
    setFillType(fillType);
  }
  
  public void rewind() {
    this.isSimplePath = true;
    this.mLastDirection = null;
    Region region = this.rects;
    if (region != null)
      region.setEmpty(); 
    nRewind(this.mNativePath);
  }
  
  public void set(Path paramPath) {
    if (this == paramPath)
      return; 
    this.isSimplePath = paramPath.isSimplePath;
    nSet(this.mNativePath, paramPath.mNativePath);
    if (!this.isSimplePath)
      return; 
    Region region = this.rects;
    if (region != null) {
      Region region1 = paramPath.rects;
      if (region1 != null) {
        region.set(region1);
        return;
      } 
    } 
    region = this.rects;
    if (region != null && paramPath.rects == null) {
      region.setEmpty();
    } else if (paramPath.rects != null) {
      this.rects = new Region(paramPath.rects);
    } 
  }
  
  public void setFillType(FillType paramFillType) {
    nSetFillType(this.mNativePath, paramFillType.nativeInt);
  }
  
  public void setLastPoint(float paramFloat1, float paramFloat2) {
    this.isSimplePath = false;
    nSetLastPoint(this.mNativePath, paramFloat1, paramFloat2);
  }
  
  public void toggleInverseFillType() {
    int i = nGetFillType(this.mNativePath);
    int j = FillType.INVERSE_WINDING.nativeInt;
    nSetFillType(this.mNativePath, i ^ j);
  }
  
  public void transform(Matrix paramMatrix) {
    this.isSimplePath = false;
    nTransform(this.mNativePath, paramMatrix.native_instance);
  }
  
  public void transform(Matrix paramMatrix, Path paramPath) {
    long l = 0L;
    if (paramPath != null) {
      paramPath.isSimplePath = false;
      l = paramPath.mNativePath;
    } 
    nTransform(this.mNativePath, paramMatrix.native_instance, l);
  }
  
  public enum Direction {
    CCW,
    CW(0);
    
    final int nativeInt;
    
    static {
      Direction direction = new Direction("CCW", 1, 1);
      CCW = direction;
      $VALUES = new Direction[] { CW, direction };
    }
    
    Direction(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
  
  public enum FillType {
    EVEN_ODD(0),
    INVERSE_EVEN_ODD(0),
    INVERSE_WINDING(0),
    WINDING(0);
    
    final int nativeInt;
    
    static {
      FillType fillType = new FillType("INVERSE_EVEN_ODD", 3, 3);
      INVERSE_EVEN_ODD = fillType;
      $VALUES = new FillType[] { WINDING, EVEN_ODD, INVERSE_WINDING, fillType };
    }
    
    FillType(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
  
  public enum Op {
    DIFFERENCE, INTERSECT, REVERSE_DIFFERENCE, UNION, XOR;
    
    static {
      Op op = new Op("REVERSE_DIFFERENCE", 4);
      REVERSE_DIFFERENCE = op;
      $VALUES = new Op[] { DIFFERENCE, INTERSECT, UNION, XOR, op };
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Path.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */