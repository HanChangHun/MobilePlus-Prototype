package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pools;

public class Region implements Parcelable {
  public static final Parcelable.Creator<Region> CREATOR;
  
  private static final int MAX_POOL_SIZE = 10;
  
  private static final Pools.SynchronizedPool<Region> sPool = new Pools.SynchronizedPool(10);
  
  public long mNativeRegion;
  
  static {
    CREATOR = new Parcelable.Creator<Region>() {
        public Region createFromParcel(Parcel param1Parcel) {
          long l = Region.nativeCreateFromParcel(param1Parcel);
          if (l != 0L)
            return new Region(l); 
          throw new RuntimeException();
        }
        
        public Region[] newArray(int param1Int) {
          return new Region[param1Int];
        }
      };
  }
  
  public Region() {
    this(nativeConstructor());
  }
  
  public Region(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    long l = nativeConstructor();
    this.mNativeRegion = l;
    nativeSetRect(l, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  Region(long paramLong) {
    if (paramLong != 0L) {
      this.mNativeRegion = paramLong;
      return;
    } 
    throw new RuntimeException();
  }
  
  private Region(long paramLong, int paramInt) {
    this(paramLong);
  }
  
  public Region(Rect paramRect) {
    long l = nativeConstructor();
    this.mNativeRegion = l;
    nativeSetRect(l, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public Region(Region paramRegion) {
    this(nativeConstructor());
    nativeSetRegion(this.mNativeRegion, paramRegion.mNativeRegion);
  }
  
  private static native long nativeConstructor();
  
  private static native long nativeCreateFromParcel(Parcel paramParcel);
  
  private static native void nativeDestructor(long paramLong);
  
  private static native boolean nativeEquals(long paramLong1, long paramLong2);
  
  private static native boolean nativeGetBoundaryPath(long paramLong1, long paramLong2);
  
  private static native boolean nativeGetBounds(long paramLong, Rect paramRect);
  
  private static native boolean nativeOp(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  private static native boolean nativeOp(long paramLong1, long paramLong2, long paramLong3, int paramInt);
  
  private static native boolean nativeOp(long paramLong1, Rect paramRect, long paramLong2, int paramInt);
  
  private static native boolean nativeSetPath(long paramLong1, long paramLong2, long paramLong3);
  
  private static native boolean nativeSetRect(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  private static native void nativeSetRegion(long paramLong1, long paramLong2);
  
  private static native String nativeToString(long paramLong);
  
  private static native boolean nativeWriteToParcel(long paramLong, Parcel paramParcel);
  
  public static Region obtain() {
    Region region = (Region)sPool.acquire();
    if (region == null)
      region = new Region(); 
    return region;
  }
  
  public static Region obtain(Region paramRegion) {
    Region region = obtain();
    region.set(paramRegion);
    return region;
  }
  
  public native boolean contains(int paramInt1, int paramInt2);
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null || !(paramObject instanceof Region))
      return false; 
    paramObject = paramObject;
    return nativeEquals(this.mNativeRegion, ((Region)paramObject).mNativeRegion);
  }
  
  protected void finalize() throws Throwable {
    try {
      nativeDestructor(this.mNativeRegion);
      this.mNativeRegion = 0L;
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public Path getBoundaryPath() {
    Path path = new Path();
    nativeGetBoundaryPath(this.mNativeRegion, path.mutateNI());
    return path;
  }
  
  public boolean getBoundaryPath(Path paramPath) {
    return nativeGetBoundaryPath(this.mNativeRegion, paramPath.mutateNI());
  }
  
  public Rect getBounds() {
    Rect rect = new Rect();
    nativeGetBounds(this.mNativeRegion, rect);
    return rect;
  }
  
  public boolean getBounds(Rect paramRect) {
    if (paramRect != null)
      return nativeGetBounds(this.mNativeRegion, paramRect); 
    throw null;
  }
  
  public native boolean isComplex();
  
  public native boolean isEmpty();
  
  public native boolean isRect();
  
  final long ni() {
    return this.mNativeRegion;
  }
  
  public boolean op(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Op paramOp) {
    return nativeOp(this.mNativeRegion, paramInt1, paramInt2, paramInt3, paramInt4, paramOp.nativeInt);
  }
  
  public boolean op(Rect paramRect, Op paramOp) {
    return nativeOp(this.mNativeRegion, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom, paramOp.nativeInt);
  }
  
  public boolean op(Rect paramRect, Region paramRegion, Op paramOp) {
    return nativeOp(this.mNativeRegion, paramRect, paramRegion.mNativeRegion, paramOp.nativeInt);
  }
  
  public boolean op(Region paramRegion, Op paramOp) {
    return op(this, paramRegion, paramOp);
  }
  
  public boolean op(Region paramRegion1, Region paramRegion2, Op paramOp) {
    return nativeOp(this.mNativeRegion, paramRegion1.mNativeRegion, paramRegion2.mNativeRegion, paramOp.nativeInt);
  }
  
  public native boolean quickContains(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public boolean quickContains(Rect paramRect) {
    return quickContains(paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public native boolean quickReject(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public boolean quickReject(Rect paramRect) {
    return quickReject(paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public native boolean quickReject(Region paramRegion);
  
  public void recycle() {
    setEmpty();
    sPool.release(this);
  }
  
  public void scale(float paramFloat) {
    scale(paramFloat, null);
  }
  
  public native void scale(float paramFloat, Region paramRegion);
  
  public boolean set(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return nativeSetRect(this.mNativeRegion, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean set(Rect paramRect) {
    return nativeSetRect(this.mNativeRegion, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public boolean set(Region paramRegion) {
    nativeSetRegion(this.mNativeRegion, paramRegion.mNativeRegion);
    return true;
  }
  
  public void setEmpty() {
    nativeSetRect(this.mNativeRegion, 0, 0, 0, 0);
  }
  
  public boolean setPath(Path paramPath, Region paramRegion) {
    return nativeSetPath(this.mNativeRegion, paramPath.readOnlyNI(), paramRegion.mNativeRegion);
  }
  
  public String toString() {
    return nativeToString(this.mNativeRegion);
  }
  
  public void translate(int paramInt1, int paramInt2) {
    translate(paramInt1, paramInt2, null);
  }
  
  public native void translate(int paramInt1, int paramInt2, Region paramRegion);
  
  public final boolean union(Rect paramRect) {
    return op(paramRect, Op.UNION);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (nativeWriteToParcel(this.mNativeRegion, paramParcel))
      return; 
    throw new RuntimeException();
  }
  
  public enum Op {
    DIFFERENCE(0),
    INTERSECT(1),
    REPLACE(1),
    REVERSE_DIFFERENCE(1),
    UNION(2),
    XOR(3);
    
    public final int nativeInt;
    
    static {
      Op op = new Op("REPLACE", 5, 5);
      REPLACE = op;
      $VALUES = new Op[] { DIFFERENCE, INTERSECT, UNION, XOR, REVERSE_DIFFERENCE, op };
    }
    
    Op(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Region.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */