package android.graphics;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;
import libcore.util.NativeAllocationRegistry;

public class Rgb extends ColorSpace {
  private final DoubleUnaryOperator mClampedEotf;
  
  private final DoubleUnaryOperator mClampedOetf;
  
  private final DoubleUnaryOperator mEotf;
  
  private final float[] mInverseTransform;
  
  private final boolean mIsSrgb;
  
  private final boolean mIsWideGamut;
  
  private final float mMax;
  
  private final float mMin;
  
  private final long mNativePtr;
  
  private final DoubleUnaryOperator mOetf;
  
  private final float[] mPrimaries;
  
  private final TransferParameters mTransferParameters;
  
  private final float[] mTransform;
  
  private final float[] mWhitePoint;
  
  private Rgb(Rgb paramRgb, float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    this(paramRgb.getName(), paramRgb.mPrimaries, paramArrayOffloat2, paramArrayOffloat1, paramRgb.mOetf, paramRgb.mEotf, paramRgb.mMin, paramRgb.mMax, paramRgb.mTransferParameters, -1);
  }
  
  public Rgb(String paramString, float[] paramArrayOffloat, double paramDouble) {
    this(paramString, computePrimaries(paramArrayOffloat), computeWhitePoint(paramArrayOffloat), paramDouble, 0.0F, 1.0F, -1);
  }
  
  public Rgb(String paramString, float[] paramArrayOffloat, TransferParameters paramTransferParameters) {
    this(paramString, arrayOfFloat1, arrayOfFloat2, paramArrayOffloat, paramTransferParameters, -1);
  }
  
  public Rgb(String paramString, float[] paramArrayOffloat, DoubleUnaryOperator paramDoubleUnaryOperator1, DoubleUnaryOperator paramDoubleUnaryOperator2) {
    this(paramString, computePrimaries(paramArrayOffloat), computeWhitePoint(paramArrayOffloat), (float[])null, paramDoubleUnaryOperator1, paramDoubleUnaryOperator2, 0.0F, 1.0F, (TransferParameters)null, -1);
  }
  
  public Rgb(String paramString, float[] paramArrayOffloat1, float[] paramArrayOffloat2, double paramDouble) {
    this(paramString, paramArrayOffloat1, paramArrayOffloat2, paramDouble, 0.0F, 1.0F, -1);
  }
  
  private Rgb(String paramString, float[] paramArrayOffloat1, float[] paramArrayOffloat2, double paramDouble, float paramFloat1, float paramFloat2, int paramInt) {
    this(paramString, paramArrayOffloat1, paramArrayOffloat2, (float[])null, doubleUnaryOperator1, doubleUnaryOperator2, paramFloat1, paramFloat2, new TransferParameters(1.0D, 0.0D, 0.0D, 0.0D, paramDouble), paramInt);
  }
  
  public Rgb(String paramString, float[] paramArrayOffloat1, float[] paramArrayOffloat2, TransferParameters paramTransferParameters) {
    this(paramString, paramArrayOffloat1, paramArrayOffloat2, (float[])null, paramTransferParameters, -1);
  }
  
  public Rgb(String paramString, float[] paramArrayOffloat1, float[] paramArrayOffloat2, DoubleUnaryOperator paramDoubleUnaryOperator1, DoubleUnaryOperator paramDoubleUnaryOperator2, float paramFloat1, float paramFloat2) {
    this(paramString, paramArrayOffloat1, paramArrayOffloat2, (float[])null, paramDoubleUnaryOperator1, paramDoubleUnaryOperator2, paramFloat1, paramFloat2, (TransferParameters)null, -1);
  }
  
  private Rgb(String paramString, float[] paramArrayOffloat1, float[] paramArrayOffloat2, float[] paramArrayOffloat3, TransferParameters paramTransferParameters, int paramInt) {
    this(paramString, paramArrayOffloat1, paramArrayOffloat2, paramArrayOffloat3, _$$Lambda$ColorSpace$Rgb$V_0lmM2WEpxGBDV_1G1wvvidn7Y, _$$Lambda$ColorSpace$Rgb$iMkODTKa3_8kPZUnZZerD2Lv_yo, 0.0F, 1.0F, paramTransferParameters, paramInt);
  }
  
  private Rgb(String paramString, float[] paramArrayOffloat1, float[] paramArrayOffloat2, float[] paramArrayOffloat3, DoubleUnaryOperator paramDoubleUnaryOperator1, DoubleUnaryOperator paramDoubleUnaryOperator2, float paramFloat1, float paramFloat2, TransferParameters paramTransferParameters, int paramInt) {
    super(paramString, ColorSpace.Model.RGB, paramInt);
    if (paramArrayOffloat1 != null && (paramArrayOffloat1.length == 6 || paramArrayOffloat1.length == 9)) {
      if (paramArrayOffloat2 != null && (paramArrayOffloat2.length == 2 || paramArrayOffloat2.length == 3)) {
        if (paramDoubleUnaryOperator1 != null && paramDoubleUnaryOperator2 != null) {
          if (paramFloat1 < paramFloat2) {
            this.mWhitePoint = xyWhitePoint(paramArrayOffloat2);
            float[] arrayOfFloat = xyPrimaries(paramArrayOffloat1);
            this.mPrimaries = arrayOfFloat;
            if (paramArrayOffloat3 == null) {
              this.mTransform = computeXYZMatrix(arrayOfFloat, this.mWhitePoint);
            } else if (paramArrayOffloat3.length == 9) {
              this.mTransform = paramArrayOffloat3;
            } else {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("Transform must have 9 entries! Has ");
              stringBuilder1.append(paramArrayOffloat3.length);
              throw new IllegalArgumentException(stringBuilder1.toString());
            } 
            this.mInverseTransform = ColorSpace.access$1200(this.mTransform);
            this.mOetf = paramDoubleUnaryOperator1;
            this.mEotf = paramDoubleUnaryOperator2;
            this.mMin = paramFloat1;
            this.mMax = paramFloat2;
            _$$Lambda$ColorSpace$Rgb$8EkhO2jIf14tuA3BvrmYJMa7YXM _$$Lambda$ColorSpace$Rgb$8EkhO2jIf14tuA3BvrmYJMa7YXM = new _$$Lambda$ColorSpace$Rgb$8EkhO2jIf14tuA3BvrmYJMa7YXM(this);
            this.mClampedOetf = paramDoubleUnaryOperator1.andThen(_$$Lambda$ColorSpace$Rgb$8EkhO2jIf14tuA3BvrmYJMa7YXM);
            this.mClampedEotf = _$$Lambda$ColorSpace$Rgb$8EkhO2jIf14tuA3BvrmYJMa7YXM.andThen(paramDoubleUnaryOperator2);
            this.mTransferParameters = paramTransferParameters;
            this.mIsWideGamut = isWideGamut(this.mPrimaries, paramFloat1, paramFloat2);
            this.mIsSrgb = isSrgb(this.mPrimaries, this.mWhitePoint, paramDoubleUnaryOperator1, paramDoubleUnaryOperator2, paramFloat1, paramFloat2, paramInt);
            if (this.mTransferParameters != null) {
              paramArrayOffloat1 = this.mWhitePoint;
              if (paramArrayOffloat1 != null) {
                float[] arrayOfFloat1 = this.mTransform;
                if (arrayOfFloat1 != null) {
                  arrayOfFloat1 = ColorSpace.access$1300(paramArrayOffloat1, arrayOfFloat1);
                  this.mNativePtr = nativeCreate((float)this.mTransferParameters.a, (float)this.mTransferParameters.b, (float)this.mTransferParameters.c, (float)this.mTransferParameters.d, (float)this.mTransferParameters.e, (float)this.mTransferParameters.f, (float)this.mTransferParameters.g, arrayOfFloat1);
                  NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativePtr);
                } else {
                  StringBuilder stringBuilder1 = new StringBuilder();
                  stringBuilder1.append("ColorSpace (");
                  stringBuilder1.append(this);
                  stringBuilder1.append(") cannot create native object! mWhitePoint: ");
                  stringBuilder1.append(this.mWhitePoint);
                  stringBuilder1.append(" mTransform: ");
                  stringBuilder1.append(this.mTransform);
                  throw new IllegalStateException(stringBuilder1.toString());
                } 
              } else {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("ColorSpace (");
                stringBuilder1.append(this);
                stringBuilder1.append(") cannot create native object! mWhitePoint: ");
                stringBuilder1.append(this.mWhitePoint);
                stringBuilder1.append(" mTransform: ");
                stringBuilder1.append(this.mTransform);
                throw new IllegalStateException(stringBuilder1.toString());
              } 
            } else {
              this.mNativePtr = 0L;
            } 
            return;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Invalid range: min=");
          stringBuilder.append(paramFloat1);
          stringBuilder.append(", max=");
          stringBuilder.append(paramFloat2);
          stringBuilder.append("; min must be strictly < max");
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
        throw new IllegalArgumentException("The transfer functions of a color space cannot be null");
      } 
      throw new IllegalArgumentException("The color space's white point must be defined as an array of 2 floats in xyY or 3 float in XYZ");
    } 
    throw new IllegalArgumentException("The color space's primaries must be defined as an array of 6 floats in xyY or 9 floats in XYZ");
  }
  
  private static float area(float[] paramArrayOffloat) {
    float f1 = paramArrayOffloat[0];
    float f2 = paramArrayOffloat[1];
    float f3 = paramArrayOffloat[2];
    float f4 = paramArrayOffloat[3];
    float f5 = paramArrayOffloat[4];
    float f6 = paramArrayOffloat[5];
    f1 = 0.5F * (f1 * f4 + f2 * f5 + f3 * f6 - f4 * f5 - f2 * f3 - f1 * f6);
    if (f1 < 0.0F)
      f1 = -f1; 
    return f1;
  }
  
  private double clamp(double paramDouble) {
    float f = this.mMin;
    if (paramDouble < f)
      return f; 
    f = this.mMax;
    return (paramDouble > f) ? f : paramDouble;
  }
  
  private static boolean compare(double paramDouble, DoubleUnaryOperator paramDoubleUnaryOperator1, DoubleUnaryOperator paramDoubleUnaryOperator2) {
    boolean bool;
    if (Math.abs(paramDoubleUnaryOperator1.applyAsDouble(paramDouble) - paramDoubleUnaryOperator2.applyAsDouble(paramDouble)) <= 0.001D) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static float[] computePrimaries(float[] paramArrayOffloat) {
    float[] arrayOfFloat1 = ColorSpace.access$1500(paramArrayOffloat, new float[] { 1.0F, 0.0F, 0.0F });
    float[] arrayOfFloat2 = ColorSpace.access$1500(paramArrayOffloat, new float[] { 0.0F, 1.0F, 0.0F });
    paramArrayOffloat = ColorSpace.access$1500(paramArrayOffloat, new float[] { 0.0F, 0.0F, 1.0F });
    float f1 = arrayOfFloat1[0] + arrayOfFloat1[1] + arrayOfFloat1[2];
    float f2 = arrayOfFloat2[0] + arrayOfFloat2[1] + arrayOfFloat2[2];
    float f3 = paramArrayOffloat[0] + paramArrayOffloat[1] + paramArrayOffloat[2];
    return new float[] { arrayOfFloat1[0] / f1, arrayOfFloat1[1] / f1, arrayOfFloat2[0] / f2, arrayOfFloat2[1] / f2, paramArrayOffloat[0] / f3, paramArrayOffloat[1] / f3 };
  }
  
  private static float[] computeWhitePoint(float[] paramArrayOffloat) {
    paramArrayOffloat = ColorSpace.access$1500(paramArrayOffloat, new float[] { 1.0F, 1.0F, 1.0F });
    float f = paramArrayOffloat[0] + paramArrayOffloat[1] + paramArrayOffloat[2];
    return new float[] { paramArrayOffloat[0] / f, paramArrayOffloat[1] / f };
  }
  
  private static float[] computeXYZMatrix(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    float f1 = paramArrayOffloat1[0];
    float f2 = paramArrayOffloat1[1];
    float f3 = paramArrayOffloat1[2];
    float f4 = paramArrayOffloat1[3];
    float f5 = paramArrayOffloat1[4];
    float f6 = paramArrayOffloat1[5];
    float f7 = paramArrayOffloat2[0];
    float f8 = paramArrayOffloat2[1];
    float f9 = (1.0F - f1) / f2;
    float f10 = (1.0F - f3) / f4;
    float f11 = (1.0F - f5) / f6;
    float f12 = (1.0F - f7) / f8;
    float f13 = f1 / f2;
    float f14 = f3 / f4;
    float f15 = f5 / f6;
    f8 = f7 / f8;
    f10 = ((f12 - f9) * (f14 - f13) - (f8 - f13) * (f10 - f9)) / ((f11 - f9) * (f14 - f13) - (f15 - f13) * (f10 - f9));
    f13 = (f8 - f13 - (f15 - f13) * f10) / (f14 - f13);
    f15 = 1.0F - f13 - f10;
    f14 = f15 / f2;
    f9 = f13 / f4;
    f11 = f10 / f6;
    return new float[] { f14 * f1, f15, (1.0F - f1 - f2) * f14, f9 * f3, f13, (1.0F - f3 - f4) * f9, f11 * f5, f10, (1.0F - f5 - f6) * f11 };
  }
  
  private static boolean contains(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    float[] arrayOfFloat = new float[6];
    arrayOfFloat[0] = paramArrayOffloat1[0] - paramArrayOffloat2[0];
    arrayOfFloat[1] = paramArrayOffloat1[1] - paramArrayOffloat2[1];
    arrayOfFloat[2] = paramArrayOffloat1[2] - paramArrayOffloat2[2];
    arrayOfFloat[3] = paramArrayOffloat1[3] - paramArrayOffloat2[3];
    arrayOfFloat[4] = paramArrayOffloat1[4] - paramArrayOffloat2[4];
    arrayOfFloat[5] = paramArrayOffloat1[5] - paramArrayOffloat2[5];
    return (cross(arrayOfFloat[0], arrayOfFloat[1], paramArrayOffloat2[0] - paramArrayOffloat2[4], paramArrayOffloat2[1] - paramArrayOffloat2[5]) < 0.0F || cross(paramArrayOffloat2[0] - paramArrayOffloat2[2], paramArrayOffloat2[1] - paramArrayOffloat2[3], arrayOfFloat[0], arrayOfFloat[1]) < 0.0F) ? false : ((cross(arrayOfFloat[2], arrayOfFloat[3], paramArrayOffloat2[2] - paramArrayOffloat2[0], paramArrayOffloat2[3] - paramArrayOffloat2[1]) < 0.0F || cross(paramArrayOffloat2[2] - paramArrayOffloat2[4], paramArrayOffloat2[3] - paramArrayOffloat2[5], arrayOfFloat[2], arrayOfFloat[3]) < 0.0F) ? false : (!(cross(arrayOfFloat[4], arrayOfFloat[5], paramArrayOffloat2[4] - paramArrayOffloat2[2], paramArrayOffloat2[5] - paramArrayOffloat2[3]) < 0.0F || cross(paramArrayOffloat2[4] - paramArrayOffloat2[0], paramArrayOffloat2[5] - paramArrayOffloat2[1], arrayOfFloat[4], arrayOfFloat[5]) < 0.0F)));
  }
  
  private static float cross(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    return paramFloat1 * paramFloat4 - paramFloat2 * paramFloat3;
  }
  
  private static boolean isGray(float[] paramArrayOffloat) {
    int i = paramArrayOffloat.length;
    boolean bool = true;
    if (i != 9 || paramArrayOffloat[1] != 0.0F || paramArrayOffloat[2] != 0.0F || paramArrayOffloat[3] != 0.0F || paramArrayOffloat[5] != 0.0F || paramArrayOffloat[6] != 0.0F || paramArrayOffloat[7] != 0.0F)
      bool = false; 
    return bool;
  }
  
  private static boolean isSrgb(float[] paramArrayOffloat1, float[] paramArrayOffloat2, DoubleUnaryOperator paramDoubleUnaryOperator1, DoubleUnaryOperator paramDoubleUnaryOperator2, float paramFloat1, float paramFloat2, int paramInt) {
    if (paramInt == 0)
      return true; 
    if (!ColorSpace.access$1700(paramArrayOffloat1, ColorSpace.access$1600()))
      return false; 
    if (!ColorSpace.access$1700(paramArrayOffloat2, ILLUMINANT_D65))
      return false; 
    if (paramFloat1 != 0.0F)
      return false; 
    if (paramFloat2 != 1.0F)
      return false; 
    Rgb rgb = (Rgb)get(ColorSpace.Named.SRGB);
    double d;
    for (d = 0.0D; d <= 1.0D; d += 0.00392156862745098D) {
      if (!compare(d, paramDoubleUnaryOperator1, rgb.mOetf))
        return false; 
      if (!compare(d, paramDoubleUnaryOperator2, rgb.mEotf))
        return false; 
    } 
    return true;
  }
  
  private static boolean isWideGamut(float[] paramArrayOffloat, float paramFloat1, float paramFloat2) {
    boolean bool;
    if ((area(paramArrayOffloat) / area(ColorSpace.access$1800()) > 0.9F && contains(paramArrayOffloat, ColorSpace.access$1600())) || (paramFloat1 < 0.0F && paramFloat2 > 1.0F)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static native long nativeCreate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float[] paramArrayOffloat);
  
  private static native long nativeGetNativeFinalizer();
  
  private static float[] xyPrimaries(float[] paramArrayOffloat) {
    float[] arrayOfFloat = new float[6];
    if (paramArrayOffloat.length == 9) {
      float f = paramArrayOffloat[0] + paramArrayOffloat[1] + paramArrayOffloat[2];
      arrayOfFloat[0] = paramArrayOffloat[0] / f;
      arrayOfFloat[1] = paramArrayOffloat[1] / f;
      f = paramArrayOffloat[3] + paramArrayOffloat[4] + paramArrayOffloat[5];
      arrayOfFloat[2] = paramArrayOffloat[3] / f;
      arrayOfFloat[3] = paramArrayOffloat[4] / f;
      f = paramArrayOffloat[6] + paramArrayOffloat[7] + paramArrayOffloat[8];
      arrayOfFloat[4] = paramArrayOffloat[6] / f;
      arrayOfFloat[5] = paramArrayOffloat[7] / f;
    } else {
      System.arraycopy(paramArrayOffloat, 0, arrayOfFloat, 0, 6);
    } 
    return arrayOfFloat;
  }
  
  private static float[] xyWhitePoint(float[] paramArrayOffloat) {
    float[] arrayOfFloat = new float[2];
    if (paramArrayOffloat.length == 3) {
      float f = paramArrayOffloat[0] + paramArrayOffloat[1] + paramArrayOffloat[2];
      arrayOfFloat[0] = paramArrayOffloat[0] / f;
      arrayOfFloat[1] = paramArrayOffloat[1] / f;
    } else {
      System.arraycopy(paramArrayOffloat, 0, arrayOfFloat, 0, 2);
    } 
    return arrayOfFloat;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (!super.equals(paramObject))
      return false; 
    paramObject = paramObject;
    if (Float.compare(((Rgb)paramObject).mMin, this.mMin) != 0)
      return false; 
    if (Float.compare(((Rgb)paramObject).mMax, this.mMax) != 0)
      return false; 
    if (!Arrays.equals(this.mWhitePoint, ((Rgb)paramObject).mWhitePoint))
      return false; 
    if (!Arrays.equals(this.mPrimaries, ((Rgb)paramObject).mPrimaries))
      return false; 
    TransferParameters transferParameters = this.mTransferParameters;
    return (transferParameters != null) ? transferParameters.equals(((Rgb)paramObject).mTransferParameters) : ((((Rgb)paramObject).mTransferParameters == null) ? true : (!this.mOetf.equals(((Rgb)paramObject).mOetf) ? false : this.mEotf.equals(((Rgb)paramObject).mEotf)));
  }
  
  public float[] fromLinear(float paramFloat1, float paramFloat2, float paramFloat3) {
    return fromLinear(new float[] { paramFloat1, paramFloat2, paramFloat3 });
  }
  
  public float[] fromLinear(float[] paramArrayOffloat) {
    paramArrayOffloat[0] = (float)this.mClampedOetf.applyAsDouble(paramArrayOffloat[0]);
    paramArrayOffloat[1] = (float)this.mClampedOetf.applyAsDouble(paramArrayOffloat[1]);
    paramArrayOffloat[2] = (float)this.mClampedOetf.applyAsDouble(paramArrayOffloat[2]);
    return paramArrayOffloat;
  }
  
  public float[] fromXyz(float[] paramArrayOffloat) {
    ColorSpace.access$1500(this.mInverseTransform, paramArrayOffloat);
    paramArrayOffloat[0] = (float)this.mClampedOetf.applyAsDouble(paramArrayOffloat[0]);
    paramArrayOffloat[1] = (float)this.mClampedOetf.applyAsDouble(paramArrayOffloat[1]);
    paramArrayOffloat[2] = (float)this.mClampedOetf.applyAsDouble(paramArrayOffloat[2]);
    return paramArrayOffloat;
  }
  
  public DoubleUnaryOperator getEotf() {
    return this.mClampedEotf;
  }
  
  public float[] getInverseTransform() {
    float[] arrayOfFloat = this.mInverseTransform;
    return Arrays.copyOf(arrayOfFloat, arrayOfFloat.length);
  }
  
  public float[] getInverseTransform(float[] paramArrayOffloat) {
    float[] arrayOfFloat = this.mInverseTransform;
    System.arraycopy(arrayOfFloat, 0, paramArrayOffloat, 0, arrayOfFloat.length);
    return paramArrayOffloat;
  }
  
  public float getMaxValue(int paramInt) {
    return this.mMax;
  }
  
  public float getMinValue(int paramInt) {
    return this.mMin;
  }
  
  long getNativeInstance() {
    long l = this.mNativePtr;
    if (l != 0L)
      return l; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ColorSpace must use an ICC parametric transfer function! used ");
    stringBuilder.append(this);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public DoubleUnaryOperator getOetf() {
    return this.mClampedOetf;
  }
  
  public float[] getPrimaries() {
    float[] arrayOfFloat = this.mPrimaries;
    return Arrays.copyOf(arrayOfFloat, arrayOfFloat.length);
  }
  
  public float[] getPrimaries(float[] paramArrayOffloat) {
    float[] arrayOfFloat = this.mPrimaries;
    System.arraycopy(arrayOfFloat, 0, paramArrayOffloat, 0, arrayOfFloat.length);
    return paramArrayOffloat;
  }
  
  public TransferParameters getTransferParameters() {
    return this.mTransferParameters;
  }
  
  public float[] getTransform() {
    float[] arrayOfFloat = this.mTransform;
    return Arrays.copyOf(arrayOfFloat, arrayOfFloat.length);
  }
  
  public float[] getTransform(float[] paramArrayOffloat) {
    float[] arrayOfFloat = this.mTransform;
    System.arraycopy(arrayOfFloat, 0, paramArrayOffloat, 0, arrayOfFloat.length);
    return paramArrayOffloat;
  }
  
  public float[] getWhitePoint() {
    float[] arrayOfFloat = this.mWhitePoint;
    return Arrays.copyOf(arrayOfFloat, arrayOfFloat.length);
  }
  
  public float[] getWhitePoint(float[] paramArrayOffloat) {
    float[] arrayOfFloat = this.mWhitePoint;
    paramArrayOffloat[0] = arrayOfFloat[0];
    paramArrayOffloat[1] = arrayOfFloat[1];
    return paramArrayOffloat;
  }
  
  public int hashCode() {
    int i = super.hashCode();
    int j = Arrays.hashCode(this.mWhitePoint);
    int k = Arrays.hashCode(this.mPrimaries);
    float f = this.mMin;
    int m = 0;
    if (f != 0.0F) {
      n = Float.floatToIntBits(f);
    } else {
      n = 0;
    } 
    f = this.mMax;
    if (f != 0.0F) {
      i1 = Float.floatToIntBits(f);
    } else {
      i1 = 0;
    } 
    TransferParameters transferParameters = this.mTransferParameters;
    if (transferParameters != null)
      m = transferParameters.hashCode(); 
    int i1 = ((((i * 31 + j) * 31 + k) * 31 + n) * 31 + i1) * 31 + m;
    int n = i1;
    if (this.mTransferParameters == null)
      n = (i1 * 31 + this.mOetf.hashCode()) * 31 + this.mEotf.hashCode(); 
    return n;
  }
  
  public boolean isSrgb() {
    return this.mIsSrgb;
  }
  
  public boolean isWideGamut() {
    return this.mIsWideGamut;
  }
  
  public float[] toLinear(float paramFloat1, float paramFloat2, float paramFloat3) {
    return toLinear(new float[] { paramFloat1, paramFloat2, paramFloat3 });
  }
  
  public float[] toLinear(float[] paramArrayOffloat) {
    paramArrayOffloat[0] = (float)this.mClampedEotf.applyAsDouble(paramArrayOffloat[0]);
    paramArrayOffloat[1] = (float)this.mClampedEotf.applyAsDouble(paramArrayOffloat[1]);
    paramArrayOffloat[2] = (float)this.mClampedEotf.applyAsDouble(paramArrayOffloat[2]);
    return paramArrayOffloat;
  }
  
  public float[] toXyz(float[] paramArrayOffloat) {
    paramArrayOffloat[0] = (float)this.mClampedEotf.applyAsDouble(paramArrayOffloat[0]);
    paramArrayOffloat[1] = (float)this.mClampedEotf.applyAsDouble(paramArrayOffloat[1]);
    paramArrayOffloat[2] = (float)this.mClampedEotf.applyAsDouble(paramArrayOffloat[2]);
    return ColorSpace.access$1500(this.mTransform, paramArrayOffloat);
  }
  
  private static class NoImagePreloadHolder {
    public static final NativeAllocationRegistry sRegistry = new NativeAllocationRegistry(ColorSpace.Rgb.class.getClassLoader(), ColorSpace.Rgb.nativeGetNativeFinalizer(), 0L);
  }
  
  public static class TransferParameters {
    public final double a;
    
    public final double b;
    
    public final double c;
    
    public final double d;
    
    public final double e;
    
    public final double f;
    
    public final double g;
    
    public TransferParameters(double param2Double1, double param2Double2, double param2Double3, double param2Double4, double param2Double5) {
      this(param2Double1, param2Double2, param2Double3, param2Double4, 0.0D, 0.0D, param2Double5);
    }
    
    public TransferParameters(double param2Double1, double param2Double2, double param2Double3, double param2Double4, double param2Double5, double param2Double6, double param2Double7) {
      if (!Double.isNaN(param2Double1) && !Double.isNaN(param2Double2) && !Double.isNaN(param2Double3) && !Double.isNaN(param2Double4) && !Double.isNaN(param2Double5) && !Double.isNaN(param2Double6) && !Double.isNaN(param2Double7)) {
        if (param2Double4 >= 0.0D && param2Double4 <= (Math.ulp(1.0F) + 1.0F)) {
          if (param2Double4 != 0.0D || (param2Double1 != 0.0D && param2Double7 != 0.0D)) {
            if (param2Double4 < 1.0D || param2Double3 != 0.0D) {
              if ((param2Double1 != 0.0D && param2Double7 != 0.0D) || param2Double3 != 0.0D) {
                if (param2Double3 >= 0.0D) {
                  if (param2Double1 >= 0.0D && param2Double7 >= 0.0D) {
                    this.a = param2Double1;
                    this.b = param2Double2;
                    this.c = param2Double3;
                    this.d = param2Double4;
                    this.e = param2Double5;
                    this.f = param2Double6;
                    this.g = param2Double7;
                    return;
                  } 
                  throw new IllegalArgumentException("The transfer function must be positive or increasing");
                } 
                throw new IllegalArgumentException("The transfer function must be increasing");
              } 
              throw new IllegalArgumentException("Parameter a or g is zero, and c is zero, the transfer function is constant");
            } 
            throw new IllegalArgumentException("Parameter c is zero, the transfer function is constant");
          } 
          throw new IllegalArgumentException("Parameter a or g is zero, the transfer function is constant");
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Parameter d must be in the range [0..1], was ");
        stringBuilder.append(param2Double4);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      throw new IllegalArgumentException("Parameters cannot be NaN");
    }
    
    public boolean equals(Object param2Object) {
      boolean bool = true;
      if (this == param2Object)
        return true; 
      if (param2Object == null || getClass() != param2Object.getClass())
        return false; 
      param2Object = param2Object;
      if (Double.compare(((TransferParameters)param2Object).a, this.a) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param2Object).b, this.b) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param2Object).c, this.c) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param2Object).d, this.d) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param2Object).e, this.e) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param2Object).f, this.f) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param2Object).g, this.g) != 0)
        bool = false; 
      return bool;
    }
    
    public int hashCode() {
      long l = Double.doubleToLongBits(this.a);
      int i = (int)(l >>> 32L ^ l);
      l = Double.doubleToLongBits(this.b);
      int j = (int)(l >>> 32L ^ l);
      l = Double.doubleToLongBits(this.c);
      int k = (int)(l >>> 32L ^ l);
      l = Double.doubleToLongBits(this.d);
      int m = (int)(l >>> 32L ^ l);
      l = Double.doubleToLongBits(this.e);
      int n = (int)(l >>> 32L ^ l);
      l = Double.doubleToLongBits(this.f);
      int i1 = (int)(l >>> 32L ^ l);
      l = Double.doubleToLongBits(this.g);
      return (((((i * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + (int)(l >>> 32L ^ l);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$Rgb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */