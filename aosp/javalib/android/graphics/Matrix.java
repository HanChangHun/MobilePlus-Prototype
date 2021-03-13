package android.graphics;

import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;
import java.io.PrintWriter;
import libcore.util.NativeAllocationRegistry;

public class Matrix {
  public static final Matrix IDENTITY_MATRIX = new Matrix() {
      void oops() {
        throw new IllegalStateException("Matrix can not be modified");
      }
      
      public boolean postConcat(Matrix param1Matrix) {
        oops();
        return false;
      }
      
      public boolean postRotate(float param1Float) {
        oops();
        return false;
      }
      
      public boolean postRotate(float param1Float1, float param1Float2, float param1Float3) {
        oops();
        return false;
      }
      
      public boolean postScale(float param1Float1, float param1Float2) {
        oops();
        return false;
      }
      
      public boolean postScale(float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
        oops();
        return false;
      }
      
      public boolean postSkew(float param1Float1, float param1Float2) {
        oops();
        return false;
      }
      
      public boolean postSkew(float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
        oops();
        return false;
      }
      
      public boolean postTranslate(float param1Float1, float param1Float2) {
        oops();
        return false;
      }
      
      public boolean preConcat(Matrix param1Matrix) {
        oops();
        return false;
      }
      
      public boolean preRotate(float param1Float) {
        oops();
        return false;
      }
      
      public boolean preRotate(float param1Float1, float param1Float2, float param1Float3) {
        oops();
        return false;
      }
      
      public boolean preScale(float param1Float1, float param1Float2) {
        oops();
        return false;
      }
      
      public boolean preScale(float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
        oops();
        return false;
      }
      
      public boolean preSkew(float param1Float1, float param1Float2) {
        oops();
        return false;
      }
      
      public boolean preSkew(float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
        oops();
        return false;
      }
      
      public boolean preTranslate(float param1Float1, float param1Float2) {
        oops();
        return false;
      }
      
      public void reset() {
        oops();
      }
      
      public void set(Matrix param1Matrix) {
        oops();
      }
      
      public boolean setConcat(Matrix param1Matrix1, Matrix param1Matrix2) {
        oops();
        return false;
      }
      
      public boolean setPolyToPoly(float[] param1ArrayOffloat1, int param1Int1, float[] param1ArrayOffloat2, int param1Int2, int param1Int3) {
        oops();
        return false;
      }
      
      public boolean setRectToRect(RectF param1RectF1, RectF param1RectF2, Matrix.ScaleToFit param1ScaleToFit) {
        oops();
        return false;
      }
      
      public void setRotate(float param1Float) {
        oops();
      }
      
      public void setRotate(float param1Float1, float param1Float2, float param1Float3) {
        oops();
      }
      
      public void setScale(float param1Float1, float param1Float2) {
        oops();
      }
      
      public void setScale(float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
        oops();
      }
      
      public void setSinCos(float param1Float1, float param1Float2) {
        oops();
      }
      
      public void setSinCos(float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
        oops();
      }
      
      public void setSkew(float param1Float1, float param1Float2) {
        oops();
      }
      
      public void setSkew(float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
        oops();
      }
      
      public void setTranslate(float param1Float1, float param1Float2) {
        oops();
      }
      
      public void setValues(float[] param1ArrayOffloat) {
        oops();
      }
    };
  
  public static final int MPERSP_0 = 6;
  
  public static final int MPERSP_1 = 7;
  
  public static final int MPERSP_2 = 8;
  
  public static final int MSCALE_X = 0;
  
  public static final int MSCALE_Y = 4;
  
  public static final int MSKEW_X = 1;
  
  public static final int MSKEW_Y = 3;
  
  public static final int MTRANS_X = 2;
  
  public static final int MTRANS_Y = 5;
  
  public final long native_instance;
  
  public Matrix() {
    this.native_instance = nCreate(0L);
    NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.native_instance);
  }
  
  public Matrix(Matrix paramMatrix) {
    long l;
    if (paramMatrix != null) {
      l = paramMatrix.native_instance;
    } else {
      l = 0L;
    } 
    this.native_instance = nCreate(l);
    NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.native_instance);
  }
  
  private static void checkPointArrays(float[] paramArrayOffloat1, int paramInt1, float[] paramArrayOffloat2, int paramInt2, int paramInt3) {
    int i = (paramInt3 << 1) + paramInt1;
    int j = (paramInt3 << 1) + paramInt2;
    if ((paramInt3 | paramInt1 | paramInt2 | i | j) >= 0 && i <= paramArrayOffloat1.length && j <= paramArrayOffloat2.length)
      return; 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  private static native long nCreate(long paramLong);
  
  @CriticalNative
  private static native boolean nEquals(long paramLong1, long paramLong2);
  
  private static native long nGetNativeFinalizer();
  
  @FastNative
  private static native void nGetValues(long paramLong, float[] paramArrayOffloat);
  
  @CriticalNative
  private static native boolean nInvert(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native boolean nIsAffine(long paramLong);
  
  @CriticalNative
  private static native boolean nIsIdentity(long paramLong);
  
  @FastNative
  private static native void nMapPoints(long paramLong, float[] paramArrayOffloat1, int paramInt1, float[] paramArrayOffloat2, int paramInt2, int paramInt3, boolean paramBoolean);
  
  @CriticalNative
  private static native float nMapRadius(long paramLong, float paramFloat);
  
  @FastNative
  private static native boolean nMapRect(long paramLong, RectF paramRectF1, RectF paramRectF2);
  
  @CriticalNative
  private static native void nPostConcat(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nPostRotate(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native void nPostRotate(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3);
  
  @CriticalNative
  private static native void nPostScale(long paramLong, float paramFloat1, float paramFloat2);
  
  @CriticalNative
  private static native void nPostScale(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  @CriticalNative
  private static native void nPostSkew(long paramLong, float paramFloat1, float paramFloat2);
  
  @CriticalNative
  private static native void nPostSkew(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  @CriticalNative
  private static native void nPostTranslate(long paramLong, float paramFloat1, float paramFloat2);
  
  @CriticalNative
  private static native void nPreConcat(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nPreRotate(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native void nPreRotate(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3);
  
  @CriticalNative
  private static native void nPreScale(long paramLong, float paramFloat1, float paramFloat2);
  
  @CriticalNative
  private static native void nPreScale(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  @CriticalNative
  private static native void nPreSkew(long paramLong, float paramFloat1, float paramFloat2);
  
  @CriticalNative
  private static native void nPreSkew(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  @CriticalNative
  private static native void nPreTranslate(long paramLong, float paramFloat1, float paramFloat2);
  
  @CriticalNative
  private static native boolean nRectStaysRect(long paramLong);
  
  @CriticalNative
  private static native void nReset(long paramLong);
  
  @CriticalNative
  private static native void nSet(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nSetConcat(long paramLong1, long paramLong2, long paramLong3);
  
  @FastNative
  private static native boolean nSetPolyToPoly(long paramLong, float[] paramArrayOffloat1, int paramInt1, float[] paramArrayOffloat2, int paramInt2, int paramInt3);
  
  @FastNative
  private static native boolean nSetRectToRect(long paramLong, RectF paramRectF1, RectF paramRectF2, int paramInt);
  
  @CriticalNative
  private static native void nSetRotate(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native void nSetRotate(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3);
  
  @CriticalNative
  private static native void nSetScale(long paramLong, float paramFloat1, float paramFloat2);
  
  @CriticalNative
  private static native void nSetScale(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  @CriticalNative
  private static native void nSetSinCos(long paramLong, float paramFloat1, float paramFloat2);
  
  @CriticalNative
  private static native void nSetSinCos(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  @CriticalNative
  private static native void nSetSkew(long paramLong, float paramFloat1, float paramFloat2);
  
  @CriticalNative
  private static native void nSetSkew(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  @CriticalNative
  private static native void nSetTranslate(long paramLong, float paramFloat1, float paramFloat2);
  
  @FastNative
  private static native void nSetValues(long paramLong, float[] paramArrayOffloat);
  
  public boolean equals(Object paramObject) {
    return !(paramObject instanceof Matrix) ? false : nEquals(this.native_instance, ((Matrix)paramObject).native_instance);
  }
  
  public void getValues(float[] paramArrayOffloat) {
    if (paramArrayOffloat.length >= 9) {
      nGetValues(this.native_instance, paramArrayOffloat);
      return;
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public int hashCode() {
    return 44;
  }
  
  public boolean invert(Matrix paramMatrix) {
    return nInvert(this.native_instance, paramMatrix.native_instance);
  }
  
  public boolean isAffine() {
    return nIsAffine(this.native_instance);
  }
  
  public boolean isIdentity() {
    return nIsIdentity(this.native_instance);
  }
  
  public void mapPoints(float[] paramArrayOffloat) {
    mapPoints(paramArrayOffloat, 0, paramArrayOffloat, 0, paramArrayOffloat.length >> 1);
  }
  
  public void mapPoints(float[] paramArrayOffloat1, int paramInt1, float[] paramArrayOffloat2, int paramInt2, int paramInt3) {
    checkPointArrays(paramArrayOffloat2, paramInt2, paramArrayOffloat1, paramInt1, paramInt3);
    nMapPoints(this.native_instance, paramArrayOffloat1, paramInt1, paramArrayOffloat2, paramInt2, paramInt3, true);
  }
  
  public void mapPoints(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    if (paramArrayOffloat1.length == paramArrayOffloat2.length) {
      mapPoints(paramArrayOffloat1, 0, paramArrayOffloat2, 0, paramArrayOffloat1.length >> 1);
      return;
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public float mapRadius(float paramFloat) {
    return nMapRadius(this.native_instance, paramFloat);
  }
  
  public boolean mapRect(RectF paramRectF) {
    return mapRect(paramRectF, paramRectF);
  }
  
  public boolean mapRect(RectF paramRectF1, RectF paramRectF2) {
    if (paramRectF1 != null && paramRectF2 != null)
      return nMapRect(this.native_instance, paramRectF1, paramRectF2); 
    throw null;
  }
  
  public void mapVectors(float[] paramArrayOffloat) {
    mapVectors(paramArrayOffloat, 0, paramArrayOffloat, 0, paramArrayOffloat.length >> 1);
  }
  
  public void mapVectors(float[] paramArrayOffloat1, int paramInt1, float[] paramArrayOffloat2, int paramInt2, int paramInt3) {
    checkPointArrays(paramArrayOffloat2, paramInt2, paramArrayOffloat1, paramInt1, paramInt3);
    nMapPoints(this.native_instance, paramArrayOffloat1, paramInt1, paramArrayOffloat2, paramInt2, paramInt3, false);
  }
  
  public void mapVectors(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    if (paramArrayOffloat1.length == paramArrayOffloat2.length) {
      mapVectors(paramArrayOffloat1, 0, paramArrayOffloat2, 0, paramArrayOffloat1.length >> 1);
      return;
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public final long ni() {
    return this.native_instance;
  }
  
  public boolean postConcat(Matrix paramMatrix) {
    nPostConcat(this.native_instance, paramMatrix.native_instance);
    return true;
  }
  
  public boolean postRotate(float paramFloat) {
    nPostRotate(this.native_instance, paramFloat);
    return true;
  }
  
  public boolean postRotate(float paramFloat1, float paramFloat2, float paramFloat3) {
    nPostRotate(this.native_instance, paramFloat1, paramFloat2, paramFloat3);
    return true;
  }
  
  public boolean postScale(float paramFloat1, float paramFloat2) {
    nPostScale(this.native_instance, paramFloat1, paramFloat2);
    return true;
  }
  
  public boolean postScale(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    nPostScale(this.native_instance, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    return true;
  }
  
  public boolean postSkew(float paramFloat1, float paramFloat2) {
    nPostSkew(this.native_instance, paramFloat1, paramFloat2);
    return true;
  }
  
  public boolean postSkew(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    nPostSkew(this.native_instance, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    return true;
  }
  
  public boolean postTranslate(float paramFloat1, float paramFloat2) {
    nPostTranslate(this.native_instance, paramFloat1, paramFloat2);
    return true;
  }
  
  public boolean preConcat(Matrix paramMatrix) {
    nPreConcat(this.native_instance, paramMatrix.native_instance);
    return true;
  }
  
  public boolean preRotate(float paramFloat) {
    nPreRotate(this.native_instance, paramFloat);
    return true;
  }
  
  public boolean preRotate(float paramFloat1, float paramFloat2, float paramFloat3) {
    nPreRotate(this.native_instance, paramFloat1, paramFloat2, paramFloat3);
    return true;
  }
  
  public boolean preScale(float paramFloat1, float paramFloat2) {
    nPreScale(this.native_instance, paramFloat1, paramFloat2);
    return true;
  }
  
  public boolean preScale(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    nPreScale(this.native_instance, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    return true;
  }
  
  public boolean preSkew(float paramFloat1, float paramFloat2) {
    nPreSkew(this.native_instance, paramFloat1, paramFloat2);
    return true;
  }
  
  public boolean preSkew(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    nPreSkew(this.native_instance, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    return true;
  }
  
  public boolean preTranslate(float paramFloat1, float paramFloat2) {
    nPreTranslate(this.native_instance, paramFloat1, paramFloat2);
    return true;
  }
  
  public void printShortString(PrintWriter paramPrintWriter) {
    float[] arrayOfFloat = new float[9];
    getValues(arrayOfFloat);
    paramPrintWriter.print('[');
    paramPrintWriter.print(arrayOfFloat[0]);
    paramPrintWriter.print(", ");
    paramPrintWriter.print(arrayOfFloat[1]);
    paramPrintWriter.print(", ");
    paramPrintWriter.print(arrayOfFloat[2]);
    paramPrintWriter.print("][");
    paramPrintWriter.print(arrayOfFloat[3]);
    paramPrintWriter.print(", ");
    paramPrintWriter.print(arrayOfFloat[4]);
    paramPrintWriter.print(", ");
    paramPrintWriter.print(arrayOfFloat[5]);
    paramPrintWriter.print("][");
    paramPrintWriter.print(arrayOfFloat[6]);
    paramPrintWriter.print(", ");
    paramPrintWriter.print(arrayOfFloat[7]);
    paramPrintWriter.print(", ");
    paramPrintWriter.print(arrayOfFloat[8]);
    paramPrintWriter.print(']');
  }
  
  public boolean rectStaysRect() {
    return nRectStaysRect(this.native_instance);
  }
  
  public void reset() {
    nReset(this.native_instance);
  }
  
  public void set(Matrix paramMatrix) {
    if (paramMatrix == null) {
      reset();
    } else {
      nSet(this.native_instance, paramMatrix.native_instance);
    } 
  }
  
  public boolean setConcat(Matrix paramMatrix1, Matrix paramMatrix2) {
    nSetConcat(this.native_instance, paramMatrix1.native_instance, paramMatrix2.native_instance);
    return true;
  }
  
  public boolean setPolyToPoly(float[] paramArrayOffloat1, int paramInt1, float[] paramArrayOffloat2, int paramInt2, int paramInt3) {
    if (paramInt3 <= 4) {
      checkPointArrays(paramArrayOffloat1, paramInt1, paramArrayOffloat2, paramInt2, paramInt3);
      return nSetPolyToPoly(this.native_instance, paramArrayOffloat1, paramInt1, paramArrayOffloat2, paramInt2, paramInt3);
    } 
    throw new IllegalArgumentException();
  }
  
  public boolean setRectToRect(RectF paramRectF1, RectF paramRectF2, ScaleToFit paramScaleToFit) {
    if (paramRectF2 != null && paramRectF1 != null)
      return nSetRectToRect(this.native_instance, paramRectF1, paramRectF2, paramScaleToFit.nativeInt); 
    throw null;
  }
  
  public void setRotate(float paramFloat) {
    nSetRotate(this.native_instance, paramFloat);
  }
  
  public void setRotate(float paramFloat1, float paramFloat2, float paramFloat3) {
    nSetRotate(this.native_instance, paramFloat1, paramFloat2, paramFloat3);
  }
  
  public void setScale(float paramFloat1, float paramFloat2) {
    nSetScale(this.native_instance, paramFloat1, paramFloat2);
  }
  
  public void setScale(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    nSetScale(this.native_instance, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public void setSinCos(float paramFloat1, float paramFloat2) {
    nSetSinCos(this.native_instance, paramFloat1, paramFloat2);
  }
  
  public void setSinCos(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    nSetSinCos(this.native_instance, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public void setSkew(float paramFloat1, float paramFloat2) {
    nSetSkew(this.native_instance, paramFloat1, paramFloat2);
  }
  
  public void setSkew(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    nSetSkew(this.native_instance, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public void setTranslate(float paramFloat1, float paramFloat2) {
    nSetTranslate(this.native_instance, paramFloat1, paramFloat2);
  }
  
  public void setValues(float[] paramArrayOffloat) {
    if (paramArrayOffloat.length >= 9) {
      nSetValues(this.native_instance, paramArrayOffloat);
      return;
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public String toShortString() {
    StringBuilder stringBuilder = new StringBuilder(64);
    toShortString(stringBuilder);
    return stringBuilder.toString();
  }
  
  public void toShortString(StringBuilder paramStringBuilder) {
    float[] arrayOfFloat = new float[9];
    getValues(arrayOfFloat);
    paramStringBuilder.append('[');
    paramStringBuilder.append(arrayOfFloat[0]);
    paramStringBuilder.append(", ");
    paramStringBuilder.append(arrayOfFloat[1]);
    paramStringBuilder.append(", ");
    paramStringBuilder.append(arrayOfFloat[2]);
    paramStringBuilder.append("][");
    paramStringBuilder.append(arrayOfFloat[3]);
    paramStringBuilder.append(", ");
    paramStringBuilder.append(arrayOfFloat[4]);
    paramStringBuilder.append(", ");
    paramStringBuilder.append(arrayOfFloat[5]);
    paramStringBuilder.append("][");
    paramStringBuilder.append(arrayOfFloat[6]);
    paramStringBuilder.append(", ");
    paramStringBuilder.append(arrayOfFloat[7]);
    paramStringBuilder.append(", ");
    paramStringBuilder.append(arrayOfFloat[8]);
    paramStringBuilder.append(']');
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(64);
    stringBuilder.append("Matrix{");
    toShortString(stringBuilder);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  private static class NoImagePreloadHolder {
    public static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(Matrix.class.getClassLoader(), Matrix.nGetNativeFinalizer());
  }
  
  public enum ScaleToFit {
    CENTER,
    END,
    FILL(0),
    START(1);
    
    final int nativeInt;
    
    static {
      ScaleToFit scaleToFit = new ScaleToFit("END", 3, 3);
      END = scaleToFit;
      $VALUES = new ScaleToFit[] { FILL, START, CENTER, scaleToFit };
    }
    
    ScaleToFit(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Matrix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */