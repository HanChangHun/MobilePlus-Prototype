package android.graphics;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import libcore.util.NativeAllocationRegistry;

public abstract class ColorSpace {
  private static final float[] GRAY_PRIMARIES;
  
  public static final float[] ILLUMINANT_A = new float[] { 0.44757F, 0.40745F };
  
  public static final float[] ILLUMINANT_B = new float[] { 0.34842F, 0.35161F };
  
  public static final float[] ILLUMINANT_C = new float[] { 0.31006F, 0.31616F };
  
  public static final float[] ILLUMINANT_D50 = new float[] { 0.34567F, 0.3585F };
  
  private static final float[] ILLUMINANT_D50_XYZ;
  
  public static final float[] ILLUMINANT_D55 = new float[] { 0.33242F, 0.34743F };
  
  public static final float[] ILLUMINANT_D60 = new float[] { 0.32168F, 0.33767F };
  
  public static final float[] ILLUMINANT_D65 = new float[] { 0.31271F, 0.32902F };
  
  public static final float[] ILLUMINANT_D75 = new float[] { 0.29902F, 0.31485F };
  
  public static final float[] ILLUMINANT_E = new float[] { 0.33333F, 0.33333F };
  
  public static final int MAX_ID = 63;
  
  public static final int MIN_ID = -1;
  
  private static final float[] NTSC_1953_PRIMARIES;
  
  private static final float[] SRGB_PRIMARIES = new float[] { 0.64F, 0.33F, 0.3F, 0.6F, 0.15F, 0.06F };
  
  private static final Rgb.TransferParameters SRGB_TRANSFER_PARAMETERS;
  
  private static final ColorSpace[] sNamedColorSpaces;
  
  private final int mId;
  
  private final Model mModel;
  
  private final String mName;
  
  static {
    NTSC_1953_PRIMARIES = new float[] { 0.67F, 0.33F, 0.21F, 0.71F, 0.14F, 0.08F };
    GRAY_PRIMARIES = new float[] { 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F };
    ILLUMINANT_D50_XYZ = new float[] { 0.964212F, 1.0F, 0.825188F };
    SRGB_TRANSFER_PARAMETERS = new Rgb.TransferParameters(0.9478672985781991D, 0.05213270142180095D, 0.07739938080495357D, 0.04045D, 2.4D);
    ColorSpace[] arrayOfColorSpace4 = new ColorSpace[(Named.values()).length];
    sNamedColorSpaces = arrayOfColorSpace4;
    arrayOfColorSpace4[Named.SRGB.ordinal()] = new Rgb("sRGB IEC61966-2.1", SRGB_PRIMARIES, ILLUMINANT_D65, null, SRGB_TRANSFER_PARAMETERS, Named.SRGB.ordinal());
    sNamedColorSpaces[Named.LINEAR_SRGB.ordinal()] = new Rgb("sRGB IEC61966-2.1 (Linear)", SRGB_PRIMARIES, ILLUMINANT_D65, 1.0D, 0.0F, 1.0F, Named.LINEAR_SRGB.ordinal());
    sNamedColorSpaces[Named.EXTENDED_SRGB.ordinal()] = new Rgb("scRGB-nl IEC 61966-2-2:2003", SRGB_PRIMARIES, ILLUMINANT_D65, null, (DoubleUnaryOperator)_$$Lambda$ColorSpace$BNp_1CyCzsQzfE_Ads9uc4rJDfw.INSTANCE, (DoubleUnaryOperator)_$$Lambda$ColorSpace$S2rlqJvkXGTpUF6mZhvkElds8JE.INSTANCE, -0.799F, 2.399F, SRGB_TRANSFER_PARAMETERS, Named.EXTENDED_SRGB.ordinal());
    sNamedColorSpaces[Named.LINEAR_EXTENDED_SRGB.ordinal()] = new Rgb("scRGB IEC 61966-2-2:2003", SRGB_PRIMARIES, ILLUMINANT_D65, 1.0D, -0.5F, 7.499F, Named.LINEAR_EXTENDED_SRGB.ordinal());
    ColorSpace[] arrayOfColorSpace5 = sNamedColorSpaces;
    int i = Named.BT709.ordinal();
    float[] arrayOfFloat5 = ILLUMINANT_D65;
    Rgb.TransferParameters transferParameters1 = new Rgb.TransferParameters(0.9099181073703367D, 0.09008189262966333D, 0.2222222222222222D, 0.081D, 2.2222222222222223D);
    int j = Named.BT709.ordinal();
    arrayOfColorSpace5[i] = new Rgb("Rec. ITU-R BT.709-5", new float[] { 0.64F, 0.33F, 0.3F, 0.6F, 0.15F, 0.06F }, arrayOfFloat5, null, transferParameters1, j);
    ColorSpace[] arrayOfColorSpace3 = sNamedColorSpaces;
    j = Named.BT2020.ordinal();
    arrayOfFloat5 = ILLUMINANT_D65;
    Rgb.TransferParameters transferParameters2 = new Rgb.TransferParameters(0.9096697898662786D, 0.09033021013372146D, 0.2222222222222222D, 0.08145D, 2.2222222222222223D);
    i = Named.BT2020.ordinal();
    arrayOfColorSpace3[j] = new Rgb("Rec. ITU-R BT.2020-1", new float[] { 0.708F, 0.292F, 0.17F, 0.797F, 0.131F, 0.046F }, arrayOfFloat5, null, transferParameters2, i);
    arrayOfColorSpace3 = sNamedColorSpaces;
    i = Named.DCI_P3.ordinal();
    j = Named.DCI_P3.ordinal();
    arrayOfColorSpace3[i] = new Rgb("SMPTE RP 431-2-2007 DCI (P3)", new float[] { 0.68F, 0.32F, 0.265F, 0.69F, 0.15F, 0.06F }, new float[] { 0.314F, 0.351F }, 2.6D, 0.0F, 1.0F, j);
    ColorSpace[] arrayOfColorSpace7 = sNamedColorSpaces;
    i = Named.DISPLAY_P3.ordinal();
    float[] arrayOfFloat2 = ILLUMINANT_D65;
    transferParameters2 = SRGB_TRANSFER_PARAMETERS;
    j = Named.DISPLAY_P3.ordinal();
    arrayOfColorSpace7[i] = new Rgb("Display P3", new float[] { 0.68F, 0.32F, 0.265F, 0.69F, 0.15F, 0.06F }, arrayOfFloat2, null, transferParameters2, j);
    sNamedColorSpaces[Named.NTSC_1953.ordinal()] = new Rgb("NTSC (1953)", NTSC_1953_PRIMARIES, ILLUMINANT_C, null, new Rgb.TransferParameters(0.9099181073703367D, 0.09008189262966333D, 0.2222222222222222D, 0.081D, 2.2222222222222223D), Named.NTSC_1953.ordinal());
    ColorSpace[] arrayOfColorSpace2 = sNamedColorSpaces;
    j = Named.SMPTE_C.ordinal();
    float[] arrayOfFloat4 = ILLUMINANT_D65;
    transferParameters2 = new Rgb.TransferParameters(0.9099181073703367D, 0.09008189262966333D, 0.2222222222222222D, 0.081D, 2.2222222222222223D);
    i = Named.SMPTE_C.ordinal();
    arrayOfColorSpace2[j] = new Rgb("SMPTE-C RGB", new float[] { 0.63F, 0.34F, 0.31F, 0.595F, 0.155F, 0.07F }, arrayOfFloat4, null, transferParameters2, i);
    arrayOfColorSpace2 = sNamedColorSpaces;
    i = Named.ADOBE_RGB.ordinal();
    arrayOfFloat4 = ILLUMINANT_D65;
    j = Named.ADOBE_RGB.ordinal();
    arrayOfColorSpace2[i] = new Rgb("Adobe RGB (1998)", new float[] { 0.64F, 0.33F, 0.21F, 0.71F, 0.15F, 0.06F }, arrayOfFloat4, 2.2D, 0.0F, 1.0F, j);
    ColorSpace[] arrayOfColorSpace6 = sNamedColorSpaces;
    i = Named.PRO_PHOTO_RGB.ordinal();
    float[] arrayOfFloat1 = ILLUMINANT_D50;
    transferParameters2 = new Rgb.TransferParameters(1.0D, 0.0D, 0.0625D, 0.031248D, 1.8D);
    j = Named.PRO_PHOTO_RGB.ordinal();
    arrayOfColorSpace6[i] = new Rgb("ROMM RGB ISO 22028-2:2013", new float[] { 0.7347F, 0.2653F, 0.1596F, 0.8404F, 0.0366F, 1.0E-4F }, arrayOfFloat1, null, transferParameters2, j);
    ColorSpace[] arrayOfColorSpace1 = sNamedColorSpaces;
    j = Named.ACES.ordinal();
    float[] arrayOfFloat3 = ILLUMINANT_D60;
    i = Named.ACES.ordinal();
    arrayOfColorSpace1[j] = new Rgb("SMPTE ST 2065-1:2012 ACES", new float[] { 0.7347F, 0.2653F, 0.0F, 1.0F, 1.0E-4F, -0.077F }, arrayOfFloat3, 1.0D, -65504.0F, 65504.0F, i);
    arrayOfColorSpace1 = sNamedColorSpaces;
    i = Named.ACESCG.ordinal();
    arrayOfFloat3 = ILLUMINANT_D60;
    j = Named.ACESCG.ordinal();
    arrayOfColorSpace1[i] = new Rgb("Academy S-2014-004 ACEScg", new float[] { 0.713F, 0.293F, 0.165F, 0.83F, 0.128F, 0.044F }, arrayOfFloat3, 1.0D, -65504.0F, 65504.0F, j);
    sNamedColorSpaces[Named.CIE_XYZ.ordinal()] = new Xyz("Generic XYZ", Named.CIE_XYZ.ordinal());
    sNamedColorSpaces[Named.CIE_LAB.ordinal()] = new Lab("Generic L*a*b*", Named.CIE_LAB.ordinal());
  }
  
  ColorSpace(String paramString, Model paramModel, int paramInt) {
    if (paramString != null && paramString.length() >= 1) {
      if (paramModel != null) {
        if (paramInt >= -1 && paramInt <= 63) {
          this.mName = paramString;
          this.mModel = paramModel;
          this.mId = paramInt;
          return;
        } 
        throw new IllegalArgumentException("The id must be between -1 and 63");
      } 
      throw new IllegalArgumentException("A color space must have a model");
    } 
    throw new IllegalArgumentException("The name of a color space cannot be null and must contain at least 1 character");
  }
  
  private static double absRcpResponse(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6) {
    double d;
    if (paramDouble1 < 0.0D) {
      d = -paramDouble1;
    } else {
      d = paramDouble1;
    } 
    return Math.copySign(rcpResponse(d, paramDouble2, paramDouble3, paramDouble4, paramDouble5, paramDouble6), paramDouble1);
  }
  
  private static double absResponse(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6) {
    double d;
    if (paramDouble1 < 0.0D) {
      d = -paramDouble1;
    } else {
      d = paramDouble1;
    } 
    return Math.copySign(response(d, paramDouble2, paramDouble3, paramDouble4, paramDouble5, paramDouble6), paramDouble1);
  }
  
  public static ColorSpace adapt(ColorSpace paramColorSpace, float[] paramArrayOffloat) {
    return adapt(paramColorSpace, paramArrayOffloat, Adaptation.BRADFORD);
  }
  
  public static ColorSpace adapt(ColorSpace paramColorSpace, float[] paramArrayOffloat, Adaptation paramAdaptation) {
    float[] arrayOfFloat;
    if (paramColorSpace.getModel() == Model.RGB) {
      Rgb rgb = (Rgb)paramColorSpace;
      if (compare(rgb.mWhitePoint, paramArrayOffloat))
        return paramColorSpace; 
      if (paramArrayOffloat.length == 3) {
        arrayOfFloat = Arrays.copyOf(paramArrayOffloat, 3);
      } else {
        arrayOfFloat = xyYToXyz(paramArrayOffloat);
      } 
      return new Rgb(rgb, mul3x3(chromaticAdaptation(paramAdaptation.mTransform, xyYToXyz(rgb.getWhitePoint()), arrayOfFloat), rgb.mTransform), paramArrayOffloat);
    } 
    return (ColorSpace)arrayOfFloat;
  }
  
  private static float[] adaptToIlluminantD50(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    float[] arrayOfFloat = ILLUMINANT_D50;
    if (compare(paramArrayOffloat1, arrayOfFloat))
      return paramArrayOffloat2; 
    arrayOfFloat = xyYToXyz(arrayOfFloat);
    return mul3x3(chromaticAdaptation(Adaptation.BRADFORD.mTransform, xyYToXyz(paramArrayOffloat1), arrayOfFloat), paramArrayOffloat2);
  }
  
  public static float[] cctToIlluminantdXyz(int paramInt) {
    if (paramInt >= 1) {
      float f1 = 1.0F / paramInt;
      float f2 = f1 * f1;
      if (paramInt <= 7000.0F) {
        f1 = 99.11F * f1 + 0.244063F + 2967800.0F * f2 - 4.6070001E9F * f2 * f1;
      } else {
        f1 = 247.48F * f1 + 0.23704F + 1901800.0F * f2 - 2.0064E9F * f2 * f1;
      } 
      return xyYToXyz(new float[] { f1, -3.0F * f1 * f1 + 2.87F * f1 - 0.275F });
    } 
    throw new IllegalArgumentException("Temperature must be greater than 0");
  }
  
  public static float[] cctToXyz(int paramInt) {
    if (paramInt >= 1) {
      float f1 = 1000.0F / paramInt;
      float f2 = f1 * f1;
      if (paramInt <= 4000.0F) {
        f2 = 0.8776956F * f1 + 0.17991F - 0.2343589F * f2 - 0.2661239F * f2 * f1;
      } else {
        f2 = 0.2226347F * f1 + 0.24039F + 2.1070378F * f2 - 3.025847F * f2 * f1;
      } 
      f1 = f2 * f2;
      if (paramInt <= 2222.0F) {
        f1 = 2.1855583F * f2 - 0.20219684F - 1.3481102F * f1 - 1.1063814F * f1 * f2;
      } else if (paramInt <= 4000.0F) {
        f1 = 2.09137F * f2 - 0.16748866F - 1.3741859F * f1 - 0.9549476F * f1 * f2;
      } else {
        f1 = 3.7511299F * f2 - 0.37001482F - 5.873387F * f1 + 3.081758F * f1 * f2;
      } 
      return xyYToXyz(new float[] { f2, f1 });
    } 
    throw new IllegalArgumentException("Temperature must be greater than 0");
  }
  
  public static float[] chromaticAdaptation(Adaptation paramAdaptation, float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    if (paramArrayOffloat1.length == 3) {
      paramArrayOffloat1 = Arrays.copyOf(paramArrayOffloat1, 3);
    } else {
      paramArrayOffloat1 = xyYToXyz(paramArrayOffloat1);
    } 
    if (paramArrayOffloat2.length == 3) {
      paramArrayOffloat2 = Arrays.copyOf(paramArrayOffloat2, 3);
    } else {
      paramArrayOffloat2 = xyYToXyz(paramArrayOffloat2);
    } 
    return compare(paramArrayOffloat1, paramArrayOffloat2) ? new float[] { 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F } : chromaticAdaptation(paramAdaptation.mTransform, paramArrayOffloat1, paramArrayOffloat2);
  }
  
  private static float[] chromaticAdaptation(float[] paramArrayOffloat1, float[] paramArrayOffloat2, float[] paramArrayOffloat3) {
    paramArrayOffloat2 = mul3x3Float3(paramArrayOffloat1, paramArrayOffloat2);
    paramArrayOffloat3 = mul3x3Float3(paramArrayOffloat1, paramArrayOffloat3);
    float f1 = paramArrayOffloat3[0] / paramArrayOffloat2[0];
    float f2 = paramArrayOffloat3[1] / paramArrayOffloat2[1];
    float f3 = paramArrayOffloat3[2] / paramArrayOffloat2[2];
    return mul3x3(inverse3x3(paramArrayOffloat1), mul3x3Diag(new float[] { f1, f2, f3 }, paramArrayOffloat1));
  }
  
  private static boolean compare(Rgb.TransferParameters paramTransferParameters1, Rgb.TransferParameters paramTransferParameters2) {
    boolean bool = true;
    if (paramTransferParameters1 == null && paramTransferParameters2 == null)
      return true; 
    if (paramTransferParameters1 == null || paramTransferParameters2 == null || Math.abs(paramTransferParameters1.a - paramTransferParameters2.a) >= 0.001D || Math.abs(paramTransferParameters1.b - paramTransferParameters2.b) >= 0.001D || Math.abs(paramTransferParameters1.c - paramTransferParameters2.c) >= 0.001D || Math.abs(paramTransferParameters1.d - paramTransferParameters2.d) >= 0.002D || Math.abs(paramTransferParameters1.e - paramTransferParameters2.e) >= 0.001D || Math.abs(paramTransferParameters1.f - paramTransferParameters2.f) >= 0.001D || Math.abs(paramTransferParameters1.g - paramTransferParameters2.g) >= 0.001D)
      bool = false; 
    return bool;
  }
  
  private static boolean compare(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    if (paramArrayOffloat1 == paramArrayOffloat2)
      return true; 
    for (byte b = 0; b < paramArrayOffloat1.length; b++) {
      if (Float.compare(paramArrayOffloat1[b], paramArrayOffloat2[b]) != 0 && Math.abs(paramArrayOffloat1[b] - paramArrayOffloat2[b]) > 0.001F)
        return false; 
    } 
    return true;
  }
  
  public static Connector connect(ColorSpace paramColorSpace) {
    return connect(paramColorSpace, RenderIntent.PERCEPTUAL);
  }
  
  public static Connector connect(ColorSpace paramColorSpace, RenderIntent paramRenderIntent) {
    return paramColorSpace.isSrgb() ? Connector.identity(paramColorSpace) : ((paramColorSpace.getModel() == Model.RGB) ? new Connector.Rgb((Rgb)paramColorSpace, (Rgb)get(Named.SRGB), paramRenderIntent) : new Connector(paramColorSpace, get(Named.SRGB), paramRenderIntent));
  }
  
  public static Connector connect(ColorSpace paramColorSpace1, ColorSpace paramColorSpace2) {
    return connect(paramColorSpace1, paramColorSpace2, RenderIntent.PERCEPTUAL);
  }
  
  public static Connector connect(ColorSpace paramColorSpace1, ColorSpace paramColorSpace2, RenderIntent paramRenderIntent) {
    return paramColorSpace1.equals(paramColorSpace2) ? Connector.identity(paramColorSpace1) : ((paramColorSpace1.getModel() == Model.RGB && paramColorSpace2.getModel() == Model.RGB) ? new Connector.Rgb((Rgb)paramColorSpace1, (Rgb)paramColorSpace2, paramRenderIntent) : new Connector(paramColorSpace1, paramColorSpace2, paramRenderIntent));
  }
  
  public static Renderer createRenderer() {
    return new Renderer();
  }
  
  static ColorSpace get(int paramInt) {
    if (paramInt >= 0) {
      ColorSpace[] arrayOfColorSpace = sNamedColorSpaces;
      if (paramInt < arrayOfColorSpace.length)
        return arrayOfColorSpace[paramInt]; 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid ID, must be in the range [0..");
    stringBuilder.append(sNamedColorSpaces.length);
    stringBuilder.append(")");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static ColorSpace get(Named paramNamed) {
    return sNamedColorSpaces[paramNamed.ordinal()];
  }
  
  private static float[] inverse3x3(float[] paramArrayOffloat) {
    float f1 = paramArrayOffloat[0];
    float f2 = paramArrayOffloat[3];
    float f3 = paramArrayOffloat[6];
    float f4 = paramArrayOffloat[1];
    float f5 = paramArrayOffloat[4];
    float f6 = paramArrayOffloat[7];
    float f7 = paramArrayOffloat[2];
    float f8 = paramArrayOffloat[5];
    float f9 = paramArrayOffloat[8];
    float f10 = f5 * f9 - f6 * f8;
    float f11 = f6 * f7 - f4 * f9;
    float f12 = f4 * f8 - f5 * f7;
    float f13 = f1 * f10 + f2 * f11 + f3 * f12;
    paramArrayOffloat = new float[paramArrayOffloat.length];
    paramArrayOffloat[0] = f10 / f13;
    paramArrayOffloat[1] = f11 / f13;
    paramArrayOffloat[2] = f12 / f13;
    paramArrayOffloat[3] = (f3 * f8 - f2 * f9) / f13;
    paramArrayOffloat[4] = (f1 * f9 - f3 * f7) / f13;
    paramArrayOffloat[5] = (f2 * f7 - f1 * f8) / f13;
    paramArrayOffloat[6] = (f2 * f6 - f3 * f5) / f13;
    paramArrayOffloat[7] = (f3 * f4 - f1 * f6) / f13;
    paramArrayOffloat[8] = (f1 * f5 - f2 * f4) / f13;
    return paramArrayOffloat;
  }
  
  public static ColorSpace match(float[] paramArrayOffloat, Rgb.TransferParameters paramTransferParameters) {
    for (ColorSpace colorSpace : sNamedColorSpaces) {
      if (colorSpace.getModel() == Model.RGB) {
        Rgb rgb = (Rgb)adapt(colorSpace, ILLUMINANT_D50_XYZ);
        if (compare(paramArrayOffloat, rgb.mTransform) && compare(paramTransferParameters, rgb.mTransferParameters))
          return colorSpace; 
      } 
    } 
    return null;
  }
  
  public static float[] mul3x3(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    return new float[] { paramArrayOffloat1[0] * paramArrayOffloat2[0] + paramArrayOffloat1[3] * paramArrayOffloat2[1] + paramArrayOffloat1[6] * paramArrayOffloat2[2], paramArrayOffloat1[1] * paramArrayOffloat2[0] + paramArrayOffloat1[4] * paramArrayOffloat2[1] + paramArrayOffloat1[7] * paramArrayOffloat2[2], paramArrayOffloat1[2] * paramArrayOffloat2[0] + paramArrayOffloat1[5] * paramArrayOffloat2[1] + paramArrayOffloat1[8] * paramArrayOffloat2[2], paramArrayOffloat1[0] * paramArrayOffloat2[3] + paramArrayOffloat1[3] * paramArrayOffloat2[4] + paramArrayOffloat1[6] * paramArrayOffloat2[5], paramArrayOffloat1[1] * paramArrayOffloat2[3] + paramArrayOffloat1[4] * paramArrayOffloat2[4] + paramArrayOffloat1[7] * paramArrayOffloat2[5], paramArrayOffloat1[2] * paramArrayOffloat2[3] + paramArrayOffloat1[5] * paramArrayOffloat2[4] + paramArrayOffloat1[8] * paramArrayOffloat2[5], paramArrayOffloat1[0] * paramArrayOffloat2[6] + paramArrayOffloat1[3] * paramArrayOffloat2[7] + paramArrayOffloat1[6] * paramArrayOffloat2[8], paramArrayOffloat1[1] * paramArrayOffloat2[6] + paramArrayOffloat1[4] * paramArrayOffloat2[7] + paramArrayOffloat1[7] * paramArrayOffloat2[8], paramArrayOffloat1[2] * paramArrayOffloat2[6] + paramArrayOffloat1[5] * paramArrayOffloat2[7] + paramArrayOffloat1[8] * paramArrayOffloat2[8] };
  }
  
  private static float[] mul3x3Diag(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    return new float[] { paramArrayOffloat1[0] * paramArrayOffloat2[0], paramArrayOffloat1[1] * paramArrayOffloat2[1], paramArrayOffloat1[2] * paramArrayOffloat2[2], paramArrayOffloat1[0] * paramArrayOffloat2[3], paramArrayOffloat1[1] * paramArrayOffloat2[4], paramArrayOffloat1[2] * paramArrayOffloat2[5], paramArrayOffloat1[0] * paramArrayOffloat2[6], paramArrayOffloat1[1] * paramArrayOffloat2[7], paramArrayOffloat1[2] * paramArrayOffloat2[8] };
  }
  
  private static float[] mul3x3Float3(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    float f1 = paramArrayOffloat2[0];
    float f2 = paramArrayOffloat2[1];
    float f3 = paramArrayOffloat2[2];
    paramArrayOffloat2[0] = paramArrayOffloat1[0] * f1 + paramArrayOffloat1[3] * f2 + paramArrayOffloat1[6] * f3;
    paramArrayOffloat2[1] = paramArrayOffloat1[1] * f1 + paramArrayOffloat1[4] * f2 + paramArrayOffloat1[7] * f3;
    paramArrayOffloat2[2] = paramArrayOffloat1[2] * f1 + paramArrayOffloat1[5] * f2 + paramArrayOffloat1[8] * f3;
    return paramArrayOffloat2;
  }
  
  private static double rcpResponse(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6) {
    if (paramDouble1 >= paramDouble5 * paramDouble4) {
      paramDouble1 = (Math.pow(paramDouble1, 1.0D / paramDouble6) - paramDouble3) / paramDouble2;
    } else {
      paramDouble1 /= paramDouble4;
    } 
    return paramDouble1;
  }
  
  private static double rcpResponse(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8) {
    if (paramDouble1 >= paramDouble5 * paramDouble4) {
      paramDouble1 = (Math.pow(paramDouble1 - paramDouble6, 1.0D / paramDouble8) - paramDouble3) / paramDouble2;
    } else {
      paramDouble1 = (paramDouble1 - paramDouble7) / paramDouble4;
    } 
    return paramDouble1;
  }
  
  private static double response(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6) {
    if (paramDouble1 >= paramDouble5) {
      paramDouble1 = Math.pow(paramDouble2 * paramDouble1 + paramDouble3, paramDouble6);
    } else {
      paramDouble1 = paramDouble4 * paramDouble1;
    } 
    return paramDouble1;
  }
  
  private static double response(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8) {
    if (paramDouble1 >= paramDouble5) {
      paramDouble1 = Math.pow(paramDouble2 * paramDouble1 + paramDouble3, paramDouble8) + paramDouble6;
    } else {
      paramDouble1 = paramDouble4 * paramDouble1 + paramDouble7;
    } 
    return paramDouble1;
  }
  
  private static void xyYToUv(float[] paramArrayOffloat) {
    for (byte b = 0; b < paramArrayOffloat.length; b += 2) {
      float f1 = paramArrayOffloat[b];
      float f2 = paramArrayOffloat[b + 1];
      float f3 = -2.0F * f1 + 12.0F * f2 + 3.0F;
      f1 = 4.0F * f1 / f3;
      f2 = 9.0F * f2 / f3;
      paramArrayOffloat[b] = f1;
      paramArrayOffloat[b + 1] = f2;
    } 
  }
  
  private static float[] xyYToXyz(float[] paramArrayOffloat) {
    return new float[] { paramArrayOffloat[0] / paramArrayOffloat[1], 1.0F, (1.0F - paramArrayOffloat[0] - paramArrayOffloat[1]) / paramArrayOffloat[1] };
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mId != ((ColorSpace)paramObject).mId)
      return false; 
    if (!this.mName.equals(((ColorSpace)paramObject).mName))
      return false; 
    if (this.mModel != ((ColorSpace)paramObject).mModel)
      bool = false; 
    return bool;
  }
  
  public float[] fromXyz(float paramFloat1, float paramFloat2, float paramFloat3) {
    float[] arrayOfFloat = new float[this.mModel.getComponentCount()];
    arrayOfFloat[0] = paramFloat1;
    arrayOfFloat[1] = paramFloat2;
    arrayOfFloat[2] = paramFloat3;
    return fromXyz(arrayOfFloat);
  }
  
  public abstract float[] fromXyz(float[] paramArrayOffloat);
  
  public int getComponentCount() {
    return this.mModel.getComponentCount();
  }
  
  public int getId() {
    return this.mId;
  }
  
  public abstract float getMaxValue(int paramInt);
  
  public abstract float getMinValue(int paramInt);
  
  public Model getModel() {
    return this.mModel;
  }
  
  public String getName() {
    return this.mName;
  }
  
  long getNativeInstance() {
    throw new IllegalArgumentException("colorSpace must be an RGB color space");
  }
  
  public int hashCode() {
    return (this.mName.hashCode() * 31 + this.mModel.hashCode()) * 31 + this.mId;
  }
  
  public boolean isSrgb() {
    return false;
  }
  
  public abstract boolean isWideGamut();
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mName);
    stringBuilder.append(" (id=");
    stringBuilder.append(this.mId);
    stringBuilder.append(", model=");
    stringBuilder.append(this.mModel);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public float[] toXyz(float paramFloat1, float paramFloat2, float paramFloat3) {
    return toXyz(new float[] { paramFloat1, paramFloat2, paramFloat3 });
  }
  
  public abstract float[] toXyz(float[] paramArrayOffloat);
  
  public enum Adaptation {
    BRADFORD((String)new float[] { 0.8951F, -0.7502F, 0.0389F, 0.2664F, 1.7135F, -0.0685F, -0.1614F, 0.0367F, 1.0296F }),
    CIECAT02((String)new float[] { 0.8951F, -0.7502F, 0.0389F, 0.2664F, 1.7135F, -0.0685F, -0.1614F, 0.0367F, 1.0296F }),
    VON_KRIES((String)new float[] { 0.40024F, -0.2263F, 0.0F, 0.7076F, 1.16532F, 0.0F, -0.08081F, 0.0457F, 0.91822F });
    
    final float[] mTransform;
    
    static {
      Adaptation adaptation = new Adaptation("CIECAT02", 2, new float[] { 0.7328F, -0.7036F, 0.003F, 0.4296F, 1.6975F, 0.0136F, -0.1624F, 0.0061F, 0.9834F });
      CIECAT02 = adaptation;
      $VALUES = new Adaptation[] { BRADFORD, VON_KRIES, adaptation };
    }
    
    Adaptation(float[] param1ArrayOffloat) {
      this.mTransform = param1ArrayOffloat;
    }
  }
  
  public static class Connector {
    private final ColorSpace mDestination;
    
    private final ColorSpace.RenderIntent mIntent;
    
    private final ColorSpace mSource;
    
    private final float[] mTransform;
    
    private final ColorSpace mTransformDestination;
    
    private final ColorSpace mTransformSource;
    
    Connector(ColorSpace param1ColorSpace1, ColorSpace param1ColorSpace2, ColorSpace.RenderIntent param1RenderIntent) {
      this(param1ColorSpace1, param1ColorSpace2, colorSpace1, colorSpace2, param1RenderIntent, computeTransform(param1ColorSpace1, param1ColorSpace2, param1RenderIntent));
    }
    
    private Connector(ColorSpace param1ColorSpace1, ColorSpace param1ColorSpace2, ColorSpace param1ColorSpace3, ColorSpace param1ColorSpace4, ColorSpace.RenderIntent param1RenderIntent, float[] param1ArrayOffloat) {
      this.mSource = param1ColorSpace1;
      this.mDestination = param1ColorSpace2;
      this.mTransformSource = param1ColorSpace3;
      this.mTransformDestination = param1ColorSpace4;
      this.mIntent = param1RenderIntent;
      this.mTransform = param1ArrayOffloat;
    }
    
    private static float[] computeTransform(ColorSpace param1ColorSpace1, ColorSpace param1ColorSpace2, ColorSpace.RenderIntent param1RenderIntent) {
      boolean bool1;
      boolean bool2;
      if (param1RenderIntent != ColorSpace.RenderIntent.ABSOLUTE)
        return null; 
      if (param1ColorSpace1.getModel() == ColorSpace.Model.RGB) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (param1ColorSpace2.getModel() == ColorSpace.Model.RGB) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      if (bool1 && bool2)
        return null; 
      if (bool1 || bool2) {
        float[] arrayOfFloat1;
        float[] arrayOfFloat2;
        if (!bool1)
          param1ColorSpace1 = param1ColorSpace2; 
        param1ColorSpace2 = param1ColorSpace1;
        if (bool1) {
          arrayOfFloat1 = ColorSpace.xyYToXyz(((ColorSpace.Rgb)param1ColorSpace2).mWhitePoint);
        } else {
          arrayOfFloat1 = ColorSpace.ILLUMINANT_D50_XYZ;
        } 
        if (bool2) {
          arrayOfFloat2 = ColorSpace.xyYToXyz(((ColorSpace.Rgb)param1ColorSpace2).mWhitePoint);
        } else {
          arrayOfFloat2 = ColorSpace.ILLUMINANT_D50_XYZ;
        } 
        return new float[] { arrayOfFloat1[0] / arrayOfFloat2[0], arrayOfFloat1[1] / arrayOfFloat2[1], arrayOfFloat1[2] / arrayOfFloat2[2] };
      } 
      return null;
    }
    
    static Connector identity(ColorSpace param1ColorSpace) {
      return new Connector(param1ColorSpace, param1ColorSpace, ColorSpace.RenderIntent.RELATIVE) {
          public float[] transform(float[] param2ArrayOffloat) {
            return param2ArrayOffloat;
          }
        };
    }
    
    public ColorSpace getDestination() {
      return this.mDestination;
    }
    
    public ColorSpace.RenderIntent getRenderIntent() {
      return this.mIntent;
    }
    
    public ColorSpace getSource() {
      return this.mSource;
    }
    
    public float[] transform(float param1Float1, float param1Float2, float param1Float3) {
      return transform(new float[] { param1Float1, param1Float2, param1Float3 });
    }
    
    public float[] transform(float[] param1ArrayOffloat) {
      float[] arrayOfFloat = this.mTransformSource.toXyz(param1ArrayOffloat);
      param1ArrayOffloat = this.mTransform;
      if (param1ArrayOffloat != null) {
        arrayOfFloat[0] = arrayOfFloat[0] * param1ArrayOffloat[0];
        arrayOfFloat[1] = arrayOfFloat[1] * param1ArrayOffloat[1];
        arrayOfFloat[2] = arrayOfFloat[2] * param1ArrayOffloat[2];
      } 
      return this.mTransformDestination.fromXyz(arrayOfFloat);
    }
    
    private static class Rgb extends Connector {
      private final ColorSpace.Rgb mDestination;
      
      private final ColorSpace.Rgb mSource;
      
      private final float[] mTransform;
      
      Rgb(ColorSpace.Rgb param2Rgb1, ColorSpace.Rgb param2Rgb2, ColorSpace.RenderIntent param2RenderIntent) {
        super(param2Rgb1, param2Rgb2, param2Rgb1, param2Rgb2, param2RenderIntent, null);
        this.mSource = param2Rgb1;
        this.mDestination = param2Rgb2;
        this.mTransform = computeTransform(param2Rgb1, param2Rgb2, param2RenderIntent);
      }
      
      private static float[] computeTransform(ColorSpace.Rgb param2Rgb1, ColorSpace.Rgb param2Rgb2, ColorSpace.RenderIntent param2RenderIntent) {
        if (ColorSpace.compare(param2Rgb1.mWhitePoint, param2Rgb2.mWhitePoint))
          return ColorSpace.mul3x3(param2Rgb2.mInverseTransform, param2Rgb1.mTransform); 
        float[] arrayOfFloat3 = param2Rgb1.mTransform;
        float[] arrayOfFloat4 = param2Rgb2.mInverseTransform;
        float[] arrayOfFloat5 = ColorSpace.xyYToXyz(param2Rgb1.mWhitePoint);
        float[] arrayOfFloat6 = ColorSpace.xyYToXyz(param2Rgb2.mWhitePoint);
        if (!ColorSpace.compare(param2Rgb1.mWhitePoint, ColorSpace.ILLUMINANT_D50))
          arrayOfFloat3 = ColorSpace.mul3x3(ColorSpace.chromaticAdaptation(ColorSpace.Adaptation.BRADFORD.mTransform, arrayOfFloat5, Arrays.copyOf(ColorSpace.ILLUMINANT_D50_XYZ, 3)), param2Rgb1.mTransform); 
        float[] arrayOfFloat1 = arrayOfFloat4;
        if (!ColorSpace.compare(param2Rgb2.mWhitePoint, ColorSpace.ILLUMINANT_D50))
          arrayOfFloat1 = ColorSpace.inverse3x3(ColorSpace.mul3x3(ColorSpace.chromaticAdaptation(ColorSpace.Adaptation.BRADFORD.mTransform, arrayOfFloat6, Arrays.copyOf(ColorSpace.ILLUMINANT_D50_XYZ, 3)), param2Rgb2.mTransform)); 
        float[] arrayOfFloat2 = arrayOfFloat3;
        if (param2RenderIntent == ColorSpace.RenderIntent.ABSOLUTE)
          arrayOfFloat2 = ColorSpace.mul3x3Diag(new float[] { arrayOfFloat5[0] / arrayOfFloat6[0], arrayOfFloat5[1] / arrayOfFloat6[1], arrayOfFloat5[2] / arrayOfFloat6[2] }, arrayOfFloat3); 
        return ColorSpace.mul3x3(arrayOfFloat1, arrayOfFloat2);
      }
      
      public float[] transform(float[] param2ArrayOffloat) {
        param2ArrayOffloat[0] = (float)this.mSource.mClampedEotf.applyAsDouble(param2ArrayOffloat[0]);
        param2ArrayOffloat[1] = (float)this.mSource.mClampedEotf.applyAsDouble(param2ArrayOffloat[1]);
        param2ArrayOffloat[2] = (float)this.mSource.mClampedEotf.applyAsDouble(param2ArrayOffloat[2]);
        ColorSpace.mul3x3Float3(this.mTransform, param2ArrayOffloat);
        param2ArrayOffloat[0] = (float)this.mDestination.mClampedOetf.applyAsDouble(param2ArrayOffloat[0]);
        param2ArrayOffloat[1] = (float)this.mDestination.mClampedOetf.applyAsDouble(param2ArrayOffloat[1]);
        param2ArrayOffloat[2] = (float)this.mDestination.mClampedOetf.applyAsDouble(param2ArrayOffloat[2]);
        return param2ArrayOffloat;
      }
    }
  }
  
  class null extends Connector {
    null(ColorSpace this$0, ColorSpace param1ColorSpace1, ColorSpace.RenderIntent param1RenderIntent) {
      super(this$0, param1ColorSpace1, param1RenderIntent);
    }
    
    public float[] transform(float[] param1ArrayOffloat) {
      return param1ArrayOffloat;
    }
  }
  
  private static class Rgb extends Connector {
    private final ColorSpace.Rgb mDestination;
    
    private final ColorSpace.Rgb mSource;
    
    private final float[] mTransform;
    
    Rgb(ColorSpace.Rgb param1Rgb1, ColorSpace.Rgb param1Rgb2, ColorSpace.RenderIntent param1RenderIntent) {
      super(param1Rgb1, param1Rgb2, param1Rgb1, param1Rgb2, param1RenderIntent, null);
      this.mSource = param1Rgb1;
      this.mDestination = param1Rgb2;
      this.mTransform = computeTransform(param1Rgb1, param1Rgb2, param1RenderIntent);
    }
    
    private static float[] computeTransform(ColorSpace.Rgb param1Rgb1, ColorSpace.Rgb param1Rgb2, ColorSpace.RenderIntent param1RenderIntent) {
      if (ColorSpace.compare(param1Rgb1.mWhitePoint, param1Rgb2.mWhitePoint))
        return ColorSpace.mul3x3(param1Rgb2.mInverseTransform, param1Rgb1.mTransform); 
      float[] arrayOfFloat3 = param1Rgb1.mTransform;
      float[] arrayOfFloat4 = param1Rgb2.mInverseTransform;
      float[] arrayOfFloat5 = ColorSpace.xyYToXyz(param1Rgb1.mWhitePoint);
      float[] arrayOfFloat6 = ColorSpace.xyYToXyz(param1Rgb2.mWhitePoint);
      if (!ColorSpace.compare(param1Rgb1.mWhitePoint, ColorSpace.ILLUMINANT_D50))
        arrayOfFloat3 = ColorSpace.mul3x3(ColorSpace.chromaticAdaptation(ColorSpace.Adaptation.BRADFORD.mTransform, arrayOfFloat5, Arrays.copyOf(ColorSpace.ILLUMINANT_D50_XYZ, 3)), param1Rgb1.mTransform); 
      float[] arrayOfFloat1 = arrayOfFloat4;
      if (!ColorSpace.compare(param1Rgb2.mWhitePoint, ColorSpace.ILLUMINANT_D50))
        arrayOfFloat1 = ColorSpace.inverse3x3(ColorSpace.mul3x3(ColorSpace.chromaticAdaptation(ColorSpace.Adaptation.BRADFORD.mTransform, arrayOfFloat6, Arrays.copyOf(ColorSpace.ILLUMINANT_D50_XYZ, 3)), param1Rgb2.mTransform)); 
      float[] arrayOfFloat2 = arrayOfFloat3;
      if (param1RenderIntent == ColorSpace.RenderIntent.ABSOLUTE)
        arrayOfFloat2 = ColorSpace.mul3x3Diag(new float[] { arrayOfFloat5[0] / arrayOfFloat6[0], arrayOfFloat5[1] / arrayOfFloat6[1], arrayOfFloat5[2] / arrayOfFloat6[2] }, arrayOfFloat3); 
      return ColorSpace.mul3x3(arrayOfFloat1, arrayOfFloat2);
    }
    
    public float[] transform(float[] param1ArrayOffloat) {
      param1ArrayOffloat[0] = (float)this.mSource.mClampedEotf.applyAsDouble(param1ArrayOffloat[0]);
      param1ArrayOffloat[1] = (float)this.mSource.mClampedEotf.applyAsDouble(param1ArrayOffloat[1]);
      param1ArrayOffloat[2] = (float)this.mSource.mClampedEotf.applyAsDouble(param1ArrayOffloat[2]);
      ColorSpace.mul3x3Float3(this.mTransform, param1ArrayOffloat);
      param1ArrayOffloat[0] = (float)this.mDestination.mClampedOetf.applyAsDouble(param1ArrayOffloat[0]);
      param1ArrayOffloat[1] = (float)this.mDestination.mClampedOetf.applyAsDouble(param1ArrayOffloat[1]);
      param1ArrayOffloat[2] = (float)this.mDestination.mClampedOetf.applyAsDouble(param1ArrayOffloat[2]);
      return param1ArrayOffloat;
    }
  }
  
  private static final class Lab extends ColorSpace {
    private static final float A = 0.008856452F;
    
    private static final float B = 7.787037F;
    
    private static final float C = 0.13793103F;
    
    private static final float D = 0.20689656F;
    
    private Lab(String param1String, int param1Int) {
      super(param1String, ColorSpace.Model.LAB, param1Int);
    }
    
    private static float clamp(float param1Float1, float param1Float2, float param1Float3) {
      if (param1Float1 < param1Float2) {
        param1Float1 = param1Float2;
      } else if (param1Float1 > param1Float3) {
        param1Float1 = param1Float3;
      } 
      return param1Float1;
    }
    
    public float[] fromXyz(float[] param1ArrayOffloat) {
      float f1 = param1ArrayOffloat[0] / ColorSpace.ILLUMINANT_D50_XYZ[0];
      float f2 = param1ArrayOffloat[1] / ColorSpace.ILLUMINANT_D50_XYZ[1];
      float f3 = param1ArrayOffloat[2] / ColorSpace.ILLUMINANT_D50_XYZ[2];
      if (f1 > 0.008856452F) {
        f1 = (float)Math.pow(f1, 0.3333333333333333D);
      } else {
        f1 = f1 * 7.787037F + 0.13793103F;
      } 
      if (f2 > 0.008856452F) {
        f2 = (float)Math.pow(f2, 0.3333333333333333D);
      } else {
        f2 = f2 * 7.787037F + 0.13793103F;
      } 
      if (f3 > 0.008856452F) {
        f3 = (float)Math.pow(f3, 0.3333333333333333D);
      } else {
        f3 = 7.787037F * f3 + 0.13793103F;
      } 
      param1ArrayOffloat[0] = clamp(116.0F * f2 - 16.0F, 0.0F, 100.0F);
      param1ArrayOffloat[1] = clamp((f1 - f2) * 500.0F, -128.0F, 128.0F);
      param1ArrayOffloat[2] = clamp((f2 - f3) * 200.0F, -128.0F, 128.0F);
      return param1ArrayOffloat;
    }
    
    public float getMaxValue(int param1Int) {
      float f;
      if (param1Int == 0) {
        f = 100.0F;
      } else {
        f = 128.0F;
      } 
      return f;
    }
    
    public float getMinValue(int param1Int) {
      float f;
      if (param1Int == 0) {
        f = 0.0F;
      } else {
        f = -128.0F;
      } 
      return f;
    }
    
    public boolean isWideGamut() {
      return true;
    }
    
    public float[] toXyz(float[] param1ArrayOffloat) {
      param1ArrayOffloat[0] = clamp(param1ArrayOffloat[0], 0.0F, 100.0F);
      param1ArrayOffloat[1] = clamp(param1ArrayOffloat[1], -128.0F, 128.0F);
      param1ArrayOffloat[2] = clamp(param1ArrayOffloat[2], -128.0F, 128.0F);
      float f1 = (param1ArrayOffloat[0] + 16.0F) / 116.0F;
      float f2 = param1ArrayOffloat[1] * 0.002F + f1;
      float f3 = f1 - param1ArrayOffloat[2] * 0.005F;
      if (f2 > 0.20689656F) {
        f2 = f2 * f2 * f2;
      } else {
        f2 = (f2 - 0.13793103F) * 0.12841855F;
      } 
      if (f1 > 0.20689656F) {
        f1 = f1 * f1 * f1;
      } else {
        f1 = (f1 - 0.13793103F) * 0.12841855F;
      } 
      if (f3 > 0.20689656F) {
        f3 = f3 * f3 * f3;
      } else {
        f3 = (f3 - 0.13793103F) * 0.12841855F;
      } 
      param1ArrayOffloat[0] = ColorSpace.ILLUMINANT_D50_XYZ[0] * f2;
      param1ArrayOffloat[1] = ColorSpace.ILLUMINANT_D50_XYZ[1] * f1;
      param1ArrayOffloat[2] = ColorSpace.ILLUMINANT_D50_XYZ[2] * f3;
      return param1ArrayOffloat;
    }
  }
  
  public enum Model {
    RGB(3),
    CMYK((String)new float[] { 0.40024F, -0.2263F, 0.0F, 0.7076F, 1.16532F, 0.0F, -0.08081F, 0.0457F, 0.91822F }),
    LAB((String)new float[] { 0.40024F, -0.2263F, 0.0F, 0.7076F, 1.16532F, 0.0F, -0.08081F, 0.0457F, 0.91822F }),
    XYZ(3);
    
    private final int mComponentCount;
    
    static {
      Model model = new Model("CMYK", 3, 4);
      CMYK = model;
      $VALUES = new Model[] { RGB, XYZ, LAB, model };
    }
    
    Model(int param1Int1) {
      this.mComponentCount = param1Int1;
    }
    
    public int getComponentCount() {
      return this.mComponentCount;
    }
  }
  
  public enum Named {
    SRGB,
    ACES(3),
    ACESCG(3),
    ADOBE_RGB(3),
    BT2020(3),
    BT709(3),
    CIE_LAB(3),
    CIE_XYZ(3),
    DCI_P3(3),
    DISPLAY_P3(3),
    EXTENDED_SRGB(3),
    LINEAR_EXTENDED_SRGB(3),
    LINEAR_SRGB(3),
    NTSC_1953(3),
    PRO_PHOTO_RGB(3),
    SMPTE_C(3);
    
    static {
      EXTENDED_SRGB = new Named("EXTENDED_SRGB", 2);
      LINEAR_EXTENDED_SRGB = new Named("LINEAR_EXTENDED_SRGB", 3);
      BT709 = new Named("BT709", 4);
      BT2020 = new Named("BT2020", 5);
      DCI_P3 = new Named("DCI_P3", 6);
      DISPLAY_P3 = new Named("DISPLAY_P3", 7);
      NTSC_1953 = new Named("NTSC_1953", 8);
      SMPTE_C = new Named("SMPTE_C", 9);
      ADOBE_RGB = new Named("ADOBE_RGB", 10);
      PRO_PHOTO_RGB = new Named("PRO_PHOTO_RGB", 11);
      ACES = new Named("ACES", 12);
      ACESCG = new Named("ACESCG", 13);
      CIE_XYZ = new Named("CIE_XYZ", 14);
      Named named = new Named("CIE_LAB", 15);
      CIE_LAB = named;
      $VALUES = new Named[] { 
          SRGB, LINEAR_SRGB, EXTENDED_SRGB, LINEAR_EXTENDED_SRGB, BT709, BT2020, DCI_P3, DISPLAY_P3, NTSC_1953, SMPTE_C, 
          ADOBE_RGB, PRO_PHOTO_RGB, ACES, ACESCG, CIE_XYZ, named };
    }
  }
  
  public enum RenderIntent {
    ABSOLUTE, PERCEPTUAL, RELATIVE, SATURATION;
    
    static {
      RenderIntent renderIntent = new RenderIntent("ABSOLUTE", 3);
      ABSOLUTE = renderIntent;
      $VALUES = new RenderIntent[] { PERCEPTUAL, RELATIVE, SATURATION, renderIntent };
    }
  }
  
  public static class Renderer {
    private static final int CHROMATICITY_RESOLUTION = 32;
    
    private static final int NATIVE_SIZE = 1440;
    
    private static final double ONE_THIRD = 0.3333333333333333D;
    
    private static final float[] SPECTRUM_LOCUS_X = new float[] { 
        0.175596F, 0.172787F, 0.170806F, 0.170085F, 0.160343F, 0.146958F, 0.139149F, 0.133536F, 0.126688F, 0.11583F, 
        0.109616F, 0.099146F, 0.09131F, 0.07813F, 0.068717F, 0.054675F, 0.040763F, 0.027497F, 0.01627F, 0.008169F, 
        0.004876F, 0.003983F, 0.003859F, 0.004646F, 0.007988F, 0.01387F, 0.022244F, 0.027273F, 0.03282F, 0.038851F, 
        0.045327F, 0.052175F, 0.059323F, 0.066713F, 0.074299F, 0.089937F, 0.114155F, 0.138695F, 0.154714F, 0.192865F, 
        0.229607F, 0.26576F, 0.301588F, 0.337346F, 0.373083F, 0.408717F, 0.444043F, 0.478755F, 0.512467F, 0.544767F, 
        0.575132F, 0.602914F, 0.627018F, 0.648215F, 0.665746F, 0.680061F, 0.691487F, 0.700589F, 0.707901F, 0.714015F, 
        0.719017F, 0.723016F, 0.734674F, 0.717203F, 0.699732F, 0.68226F, 0.664789F, 0.647318F, 0.629847F, 0.612376F, 
        0.594905F, 0.577433F, 0.559962F, 0.542491F, 0.52502F, 0.507549F, 0.490077F, 0.472606F, 0.455135F, 0.437664F, 
        0.420193F, 0.402721F, 0.38525F, 0.367779F, 0.350308F, 0.332837F, 0.315366F, 0.297894F, 0.280423F, 0.262952F, 
        0.245481F, 0.22801F, 0.210538F, 0.193067F, 0.175596F };
    
    private static final float[] SPECTRUM_LOCUS_Y = new float[] { 
        0.005295F, 0.0048F, 0.005472F, 0.005976F, 0.014496F, 0.026643F, 0.035211F, 0.042704F, 0.053441F, 0.073601F, 
        0.086866F, 0.112037F, 0.132737F, 0.170464F, 0.200773F, 0.254155F, 0.317049F, 0.387997F, 0.463035F, 0.538504F, 
        0.587196F, 0.610526F, 0.654897F, 0.67597F, 0.715407F, 0.750246F, 0.779682F, 0.792153F, 0.802971F, 0.812059F, 
        0.81943F, 0.8252F, 0.82946F, 0.832306F, 0.833833F, 0.833316F, 0.826231F, 0.814796F, 0.805884F, 0.781648F, 
        0.754347F, 0.724342F, 0.692326F, 0.658867F, 0.62447F, 0.589626F, 0.554734F, 0.520222F, 0.486611F, 0.454454F, 
        0.424252F, 0.396516F, 0.37251F, 0.351413F, 0.334028F, 0.319765F, 0.308359F, 0.299317F, 0.292044F, 0.285945F, 
        0.280951F, 0.276964F, 0.265326F, 0.2572F, 0.249074F, 0.240948F, 0.232822F, 0.224696F, 0.21657F, 0.208444F, 
        0.200318F, 0.192192F, 0.184066F, 0.17594F, 0.167814F, 0.159688F, 0.151562F, 0.143436F, 0.135311F, 0.127185F, 
        0.119059F, 0.110933F, 0.102807F, 0.094681F, 0.086555F, 0.078429F, 0.070303F, 0.062177F, 0.054051F, 0.045925F, 
        0.037799F, 0.029673F, 0.021547F, 0.013421F, 0.005295F };
    
    private static final float UCS_SCALE = 1.5F;
    
    private boolean mClip = false;
    
    private final List<Pair<ColorSpace, Integer>> mColorSpaces = new ArrayList<>(2);
    
    private final List<Point> mPoints = new ArrayList<>(0);
    
    private boolean mShowWhitePoint = true;
    
    private int mSize = 1024;
    
    private boolean mUcs = false;
    
    private Renderer() {}
    
    private static void computeChromaticityMesh(float[] param1ArrayOffloat, int[] param1ArrayOfint) {
      ColorSpace colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
      float[] arrayOfFloat = new float[3];
      int i = 0;
      int j = 0;
      int k = 0;
      while (true) {
        float[] arrayOfFloat1 = SPECTRUM_LOCUS_X;
        if (k < arrayOfFloat1.length) {
          int m = k % (arrayOfFloat1.length - 1) + 1;
          float f1 = (float)Math.atan2(SPECTRUM_LOCUS_Y[k] - 0.3333333333333333D, arrayOfFloat1[k] - 0.3333333333333333D);
          float f2 = (float)Math.atan2(SPECTRUM_LOCUS_Y[m] - 0.3333333333333333D, SPECTRUM_LOCUS_X[m] - 0.3333333333333333D);
          float f3 = (float)Math.pow(sqr(SPECTRUM_LOCUS_X[k] - 0.3333333333333333D) + sqr(SPECTRUM_LOCUS_Y[k] - 0.3333333333333333D), 0.5D);
          float f4 = (float)Math.pow(sqr(SPECTRUM_LOCUS_X[m] - 0.3333333333333333D) + sqr(SPECTRUM_LOCUS_Y[m] - 0.3333333333333333D), 0.5D);
          byte b = 1;
          int n = k;
          k = j;
          while (b <= 32) {
            float f5 = b / 32.0F;
            float f6 = (b - 1) / 32.0F;
            double d1 = f3 * Math.cos(f1);
            double d2 = f3 * Math.sin(f1);
            double d3 = f4 * Math.cos(f2);
            double d4 = f4 * Math.sin(f2);
            float f7 = (float)(f5 * d1 + 0.3333333333333333D);
            float f8 = (float)(f5 * d2 + 0.3333333333333333D);
            float f9 = (float)(f6 * d1 + 0.3333333333333333D);
            float f10 = (float)(f6 * d2 + 0.3333333333333333D);
            float f11 = (float)(f6 * d3 + 0.3333333333333333D);
            f6 = (float)(f6 * d4 + 0.3333333333333333D);
            float f12 = (float)(f5 * d3 + 0.3333333333333333D);
            f5 = (float)(f5 * d4 + 0.3333333333333333D);
            param1ArrayOfint[k] = computeColor(arrayOfFloat, f7, f8, 1.0F - f7 - f8, colorSpace);
            param1ArrayOfint[k + 1] = computeColor(arrayOfFloat, f9, f10, 1.0F - f9 - f10, colorSpace);
            param1ArrayOfint[k + 2] = computeColor(arrayOfFloat, f11, f6, 1.0F - f11 - f6, colorSpace);
            param1ArrayOfint[k + 3] = param1ArrayOfint[k];
            param1ArrayOfint[k + 4] = param1ArrayOfint[k + 2];
            param1ArrayOfint[k + 5] = computeColor(arrayOfFloat, f12, f5, 1.0F - f12 - f5, colorSpace);
            j = i + 1;
            param1ArrayOffloat[i] = f7;
            i = j + 1;
            param1ArrayOffloat[j] = f8;
            int i1 = i + 1;
            param1ArrayOffloat[i] = f9;
            j = i1 + 1;
            param1ArrayOffloat[i1] = f10;
            i = j + 1;
            param1ArrayOffloat[j] = f11;
            j = i + 1;
            param1ArrayOffloat[i] = f6;
            i1 = j + 1;
            param1ArrayOffloat[j] = f7;
            i = i1 + 1;
            param1ArrayOffloat[i1] = f8;
            i1 = i + 1;
            param1ArrayOffloat[i] = f11;
            j = i1 + 1;
            param1ArrayOffloat[i1] = f6;
            i = j + 1;
            param1ArrayOffloat[j] = f12;
            param1ArrayOffloat[i] = f5;
            b++;
            k += 6;
            i++;
          } 
          m = n + 1;
          j = k;
          k = m;
          continue;
        } 
        break;
      } 
    }
    
    private static int computeColor(float[] param1ArrayOffloat, float param1Float1, float param1Float2, float param1Float3, ColorSpace param1ColorSpace) {
      param1ArrayOffloat[0] = param1Float1;
      param1ArrayOffloat[1] = param1Float2;
      param1ArrayOffloat[2] = param1Float3;
      param1ColorSpace.fromXyz(param1ArrayOffloat);
      return ((int)(param1ArrayOffloat[0] * 255.0F) & 0xFF) << 16 | 0xFF000000 | ((int)(param1ArrayOffloat[1] * 255.0F) & 0xFF) << 8 | (int)(param1ArrayOffloat[2] * 255.0F) & 0xFF;
    }
    
    private void drawBox(Canvas param1Canvas, int param1Int1, int param1Int2, Paint param1Paint, Path param1Path) {
      byte b1;
      float f;
      if (this.mUcs) {
        b1 = 7;
        f = 1.5F;
      } else {
        b1 = 10;
        f = 1.0F;
      } 
      param1Paint.setStyle(Paint.Style.STROKE);
      param1Paint.setStrokeWidth(2.0F);
      param1Paint.setColor(-4144960);
      byte b2;
      for (b2 = 1; b2 < b1 - 1; b2++) {
        float f1 = b2 / 10.0F;
        float f2 = param1Int1 * f1 * f;
        f1 = param1Int2 - param1Int2 * f1 * f;
        param1Canvas.drawLine(0.0F, f1, param1Int1 * 0.9F, f1, param1Paint);
        param1Canvas.drawLine(f2, param1Int2, f2, param1Int2 * 0.1F, param1Paint);
      } 
      param1Paint.setStrokeWidth(4.0F);
      param1Paint.setColor(-16777216);
      for (b2 = 1; b2 < b1 - 1; b2++) {
        float f1 = b2 / 10.0F;
        float f2 = param1Int1 * f1 * f;
        f1 = param1Int2 - param1Int2 * f1 * f;
        param1Canvas.drawLine(0.0F, f1, param1Int1 / 100.0F, f1, param1Paint);
        param1Canvas.drawLine(f2, param1Int2, f2, param1Int2 - param1Int2 / 100.0F, param1Paint);
      } 
      param1Paint.setStyle(Paint.Style.FILL);
      param1Paint.setTextSize(36.0F);
      param1Paint.setTypeface(Typeface.create("sans-serif-light", 0));
      Rect rect = new Rect();
      for (b2 = 1; b2 < b1 - 1; b2++) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0.");
        stringBuilder.append(b2);
        String str = stringBuilder.toString();
        param1Paint.getTextBounds(str, 0, str.length(), rect);
        float f1 = b2 / 10.0F;
        float f3 = param1Int1;
        float f4 = param1Int2;
        float f2 = param1Int2;
        param1Canvas.drawText(str, param1Int1 * -0.05F + 10.0F, rect.height() / 2.0F + f4 - f2 * f1 * f, param1Paint);
        param1Canvas.drawText(str, f3 * f1 * f - rect.width() / 2.0F, (rect.height() + param1Int2 + 16), param1Paint);
      } 
      param1Paint.setStyle(Paint.Style.STROKE);
      param1Path.moveTo(0.0F, param1Int2);
      param1Path.lineTo(param1Int1 * 0.9F, param1Int2);
      param1Path.lineTo(param1Int1 * 0.9F, param1Int2 * 0.1F);
      param1Path.lineTo(0.0F, param1Int2 * 0.1F);
      param1Path.close();
      param1Canvas.drawPath(param1Path, param1Paint);
    }
    
    private void drawGamuts(Canvas param1Canvas, int param1Int1, int param1Int2, Paint param1Paint, Path param1Path, float[] param1ArrayOffloat1, float[] param1ArrayOffloat2) {
      if (this.mUcs) {
        f = 1.5F;
      } else {
        f = 1.0F;
      } 
      float f = 4.0F / f;
      for (Pair<ColorSpace, Integer> pair : this.mColorSpaces) {
        ColorSpace colorSpace = (ColorSpace)pair.first;
        int i = ((Integer)pair.second).intValue();
        if (colorSpace.getModel() != ColorSpace.Model.RGB)
          continue; 
        ColorSpace.Rgb rgb = (ColorSpace.Rgb)colorSpace;
        getPrimaries(rgb, param1ArrayOffloat1, this.mUcs);
        param1Path.rewind();
        param1Path.moveTo(param1Int1 * param1ArrayOffloat1[0], param1Int2 - param1Int2 * param1ArrayOffloat1[1]);
        param1Path.lineTo(param1Int1 * param1ArrayOffloat1[2], param1Int2 - param1Int2 * param1ArrayOffloat1[3]);
        param1Path.lineTo(param1Int1 * param1ArrayOffloat1[4], param1Int2 - param1Int2 * param1ArrayOffloat1[5]);
        param1Path.close();
        param1Paint.setStyle(Paint.Style.STROKE);
        param1Paint.setColor(i);
        param1Canvas.drawPath(param1Path, param1Paint);
        if (this.mShowWhitePoint) {
          rgb.getWhitePoint(param1ArrayOffloat2);
          if (this.mUcs)
            ColorSpace.xyYToUv(param1ArrayOffloat2); 
          param1Paint.setStyle(Paint.Style.FILL);
          param1Paint.setColor(i);
          param1Canvas.drawCircle(param1Int1 * param1ArrayOffloat2[0], param1Int2 - param1Int2 * param1ArrayOffloat2[1], f, param1Paint);
        } 
      } 
    }
    
    private void drawLocus(Canvas param1Canvas, int param1Int1, int param1Int2, Paint param1Paint, Path param1Path, float[] param1ArrayOffloat) {
      float f;
      float[] arrayOfFloat = new float[SPECTRUM_LOCUS_X.length * 32 * 6 * 2];
      int[] arrayOfInt = new int[arrayOfFloat.length];
      computeChromaticityMesh(arrayOfFloat, arrayOfInt);
      if (this.mUcs)
        ColorSpace.xyYToUv(arrayOfFloat); 
      for (byte b = 0; b < arrayOfFloat.length; b += 2) {
        arrayOfFloat[b] = arrayOfFloat[b] * param1Int1;
        arrayOfFloat[b + 1] = param1Int2 - arrayOfFloat[b + 1] * param1Int2;
      } 
      if (this.mClip && this.mColorSpaces.size() > 0) {
        Iterator<Pair<ColorSpace, Integer>> iterator = this.mColorSpaces.iterator();
        while (iterator.hasNext()) {
          ColorSpace colorSpace = (ColorSpace)((Pair)iterator.next()).first;
          if (colorSpace.getModel() != ColorSpace.Model.RGB)
            continue; 
          getPrimaries((ColorSpace.Rgb)colorSpace, param1ArrayOffloat, this.mUcs);
        } 
        param1Path.rewind();
        param1Path.moveTo(param1Int1 * param1ArrayOffloat[0], param1Int2 - param1Int2 * param1ArrayOffloat[1]);
        param1Path.lineTo(param1Int1 * param1ArrayOffloat[2], param1Int2 - param1Int2 * param1ArrayOffloat[3]);
        param1Path.lineTo(param1Int1 * param1ArrayOffloat[4], param1Int2 - param1Int2 * param1ArrayOffloat[5]);
        param1Path.close();
        int[] arrayOfInt1 = new int[arrayOfInt.length];
        Arrays.fill(arrayOfInt1, -9671572);
        param1Canvas.drawVertices(Canvas.VertexMode.TRIANGLES, arrayOfFloat.length, arrayOfFloat, 0, null, 0, arrayOfInt1, 0, null, 0, 0, param1Paint);
        param1Canvas.save();
        param1Canvas.clipPath(param1Path);
        param1Canvas.drawVertices(Canvas.VertexMode.TRIANGLES, arrayOfFloat.length, arrayOfFloat, 0, null, 0, arrayOfInt, 0, null, 0, 0, param1Paint);
        param1Canvas.restore();
      } else {
        param1Canvas.drawVertices(Canvas.VertexMode.TRIANGLES, arrayOfFloat.length, arrayOfFloat, 0, null, 0, arrayOfInt, 0, null, 0, 0, param1Paint);
      } 
      Path path = param1Path;
      param1Int2 = 372;
      param1Path.reset();
      path.moveTo(arrayOfFloat[372], arrayOfFloat[372 + 1]);
      for (param1Int1 = 2; param1Int1 < SPECTRUM_LOCUS_X.length; param1Int1++) {
        param1Int2 += 384;
        path.lineTo(arrayOfFloat[param1Int2], arrayOfFloat[param1Int2 + 1]);
      } 
      param1Path.close();
      if (this.mUcs) {
        f = 1.5F;
      } else {
        f = 1.0F;
      } 
      param1Paint.setStrokeWidth(4.0F / f);
      param1Paint.setStyle(Paint.Style.STROKE);
      param1Paint.setColor(-16777216);
      param1Canvas.drawPath(path, param1Paint);
    }
    
    private void drawPoints(Canvas param1Canvas, int param1Int1, int param1Int2, Paint param1Paint) {
      param1Paint.setStyle(Paint.Style.FILL);
      if (this.mUcs) {
        f = 1.5F;
      } else {
        f = 1.0F;
      } 
      float f = 4.0F / f;
      float[] arrayOfFloat1 = new float[3];
      float[] arrayOfFloat2 = new float[2];
      for (Point point : this.mPoints) {
        arrayOfFloat1[0] = point.mRgb[0];
        arrayOfFloat1[1] = point.mRgb[1];
        arrayOfFloat1[2] = point.mRgb[2];
        point.mColorSpace.toXyz(arrayOfFloat1);
        param1Paint.setColor(point.mColor);
        float f1 = arrayOfFloat1[0] + arrayOfFloat1[1] + arrayOfFloat1[2];
        arrayOfFloat2[0] = arrayOfFloat1[0] / f1;
        arrayOfFloat2[1] = arrayOfFloat1[1] / f1;
        if (this.mUcs)
          ColorSpace.xyYToUv(arrayOfFloat2); 
        param1Canvas.drawCircle(param1Int1 * arrayOfFloat2[0], param1Int2 - param1Int2 * arrayOfFloat2[1], f, param1Paint);
      } 
    }
    
    private static void getPrimaries(ColorSpace.Rgb param1Rgb, float[] param1ArrayOffloat, boolean param1Boolean) {
      if (param1Rgb.equals(ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB)) || param1Rgb.equals(ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB))) {
        param1ArrayOffloat[0] = 1.41F;
        param1ArrayOffloat[1] = 0.33F;
        param1ArrayOffloat[2] = 0.27F;
        param1ArrayOffloat[3] = 1.24F;
        param1ArrayOffloat[4] = -0.23F;
        param1ArrayOffloat[5] = -0.57F;
      } else {
        param1Rgb.getPrimaries(param1ArrayOffloat);
      } 
      if (param1Boolean)
        ColorSpace.xyYToUv(param1ArrayOffloat); 
    }
    
    private void setTransform(Canvas param1Canvas, int param1Int1, int param1Int2, float[] param1ArrayOffloat) {
      float f1;
      RectF rectF = new RectF();
      Iterator<Pair<ColorSpace, Integer>> iterator = this.mColorSpaces.iterator();
      while (iterator.hasNext()) {
        ColorSpace colorSpace = (ColorSpace)((Pair)iterator.next()).first;
        if (colorSpace.getModel() != ColorSpace.Model.RGB)
          continue; 
        getPrimaries((ColorSpace.Rgb)colorSpace, param1ArrayOffloat, this.mUcs);
        rectF.left = Math.min(rectF.left, param1ArrayOffloat[4]);
        rectF.top = Math.min(rectF.top, param1ArrayOffloat[5]);
        rectF.right = Math.max(rectF.right, param1ArrayOffloat[0]);
        rectF.bottom = Math.max(rectF.bottom, param1ArrayOffloat[3]);
      } 
      if (this.mUcs) {
        f1 = 0.6F;
      } else {
        f1 = 0.9F;
      } 
      rectF.left = Math.min(0.0F, rectF.left);
      rectF.top = Math.min(0.0F, rectF.top);
      rectF.right = Math.max(f1, rectF.right);
      rectF.bottom = Math.max(f1, rectF.bottom);
      float f2 = Math.min(f1 / rectF.width(), f1 / rectF.height());
      int i = this.mSize;
      param1Canvas.scale(i / 1440.0F, i / 1440.0F);
      param1Canvas.scale(f2, f2);
      param1Canvas.translate((rectF.width() - f1) * param1Int1 / 2.0F, (rectF.height() - f1) * param1Int2 / 2.0F);
      param1Canvas.translate(param1Int1 * 0.05F, param1Int2 * -0.05F);
    }
    
    private void setUcsTransform(Canvas param1Canvas, int param1Int) {
      if (this.mUcs) {
        param1Canvas.translate(0.0F, param1Int - param1Int * 1.5F);
        param1Canvas.scale(1.5F, 1.5F);
      } 
    }
    
    private static double sqr(double param1Double) {
      return param1Double * param1Double;
    }
    
    public Renderer add(ColorSpace param1ColorSpace, float param1Float1, float param1Float2, float param1Float3, int param1Int) {
      this.mPoints.add(new Point(param1ColorSpace, new float[] { param1Float1, param1Float2, param1Float3 }, param1Int));
      return this;
    }
    
    public Renderer add(ColorSpace param1ColorSpace, int param1Int) {
      this.mColorSpaces.add(new Pair(param1ColorSpace, Integer.valueOf(param1Int)));
      return this;
    }
    
    public Renderer clip(boolean param1Boolean) {
      this.mClip = param1Boolean;
      return this;
    }
    
    public Bitmap render() {
      Paint paint = new Paint(1);
      int i = this.mSize;
      Bitmap bitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
      Canvas canvas = new Canvas(bitmap);
      float[] arrayOfFloat1 = new float[6];
      float[] arrayOfFloat2 = new float[2];
      Path path = new Path();
      setTransform(canvas, 1440, 1440, arrayOfFloat1);
      drawBox(canvas, 1440, 1440, paint, path);
      setUcsTransform(canvas, 1440);
      drawLocus(canvas, 1440, 1440, paint, path, arrayOfFloat1);
      drawGamuts(canvas, 1440, 1440, paint, path, arrayOfFloat1, arrayOfFloat2);
      drawPoints(canvas, 1440, 1440, paint);
      return bitmap;
    }
    
    public Renderer showWhitePoint(boolean param1Boolean) {
      this.mShowWhitePoint = param1Boolean;
      return this;
    }
    
    public Renderer size(int param1Int) {
      this.mSize = Math.max(128, param1Int);
      return this;
    }
    
    public Renderer uniformChromaticityScale(boolean param1Boolean) {
      this.mUcs = param1Boolean;
      return this;
    }
    
    private static class Point {
      final int mColor;
      
      final ColorSpace mColorSpace;
      
      final float[] mRgb;
      
      Point(ColorSpace param2ColorSpace, float[] param2ArrayOffloat, int param2Int) {
        this.mColorSpace = param2ColorSpace;
        this.mRgb = param2ArrayOffloat;
        this.mColor = param2Int;
      }
    }
  }
  
  private static class Point {
    final int mColor;
    
    final ColorSpace mColorSpace;
    
    final float[] mRgb;
    
    Point(ColorSpace param1ColorSpace, float[] param1ArrayOffloat, int param1Int) {
      this.mColorSpace = param1ColorSpace;
      this.mRgb = param1ArrayOffloat;
      this.mColor = param1Int;
    }
  }
  
  public static class Rgb extends ColorSpace {
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
    
    private Rgb(Rgb param1Rgb, float[] param1ArrayOffloat1, float[] param1ArrayOffloat2) {
      this(param1Rgb.getName(), param1Rgb.mPrimaries, param1ArrayOffloat2, param1ArrayOffloat1, param1Rgb.mOetf, param1Rgb.mEotf, param1Rgb.mMin, param1Rgb.mMax, param1Rgb.mTransferParameters, -1);
    }
    
    public Rgb(String param1String, float[] param1ArrayOffloat, double param1Double) {
      this(param1String, computePrimaries(param1ArrayOffloat), computeWhitePoint(param1ArrayOffloat), param1Double, 0.0F, 1.0F, -1);
    }
    
    public Rgb(String param1String, float[] param1ArrayOffloat, TransferParameters param1TransferParameters) {
      this(param1String, arrayOfFloat1, arrayOfFloat2, param1ArrayOffloat, param1TransferParameters, -1);
    }
    
    public Rgb(String param1String, float[] param1ArrayOffloat, DoubleUnaryOperator param1DoubleUnaryOperator1, DoubleUnaryOperator param1DoubleUnaryOperator2) {
      this(param1String, computePrimaries(param1ArrayOffloat), computeWhitePoint(param1ArrayOffloat), (float[])null, param1DoubleUnaryOperator1, param1DoubleUnaryOperator2, 0.0F, 1.0F, (TransferParameters)null, -1);
    }
    
    public Rgb(String param1String, float[] param1ArrayOffloat1, float[] param1ArrayOffloat2, double param1Double) {
      this(param1String, param1ArrayOffloat1, param1ArrayOffloat2, param1Double, 0.0F, 1.0F, -1);
    }
    
    private Rgb(String param1String, float[] param1ArrayOffloat1, float[] param1ArrayOffloat2, double param1Double, float param1Float1, float param1Float2, int param1Int) {
      this(param1String, param1ArrayOffloat1, param1ArrayOffloat2, (float[])null, doubleUnaryOperator1, doubleUnaryOperator2, param1Float1, param1Float2, new TransferParameters(1.0D, 0.0D, 0.0D, 0.0D, param1Double), param1Int);
    }
    
    public Rgb(String param1String, float[] param1ArrayOffloat1, float[] param1ArrayOffloat2, TransferParameters param1TransferParameters) {
      this(param1String, param1ArrayOffloat1, param1ArrayOffloat2, (float[])null, param1TransferParameters, -1);
    }
    
    public Rgb(String param1String, float[] param1ArrayOffloat1, float[] param1ArrayOffloat2, DoubleUnaryOperator param1DoubleUnaryOperator1, DoubleUnaryOperator param1DoubleUnaryOperator2, float param1Float1, float param1Float2) {
      this(param1String, param1ArrayOffloat1, param1ArrayOffloat2, (float[])null, param1DoubleUnaryOperator1, param1DoubleUnaryOperator2, param1Float1, param1Float2, (TransferParameters)null, -1);
    }
    
    private Rgb(String param1String, float[] param1ArrayOffloat1, float[] param1ArrayOffloat2, float[] param1ArrayOffloat3, TransferParameters param1TransferParameters, int param1Int) {
      this(param1String, param1ArrayOffloat1, param1ArrayOffloat2, param1ArrayOffloat3, _$$Lambda$ColorSpace$Rgb$V_0lmM2WEpxGBDV_1G1wvvidn7Y, _$$Lambda$ColorSpace$Rgb$iMkODTKa3_8kPZUnZZerD2Lv_yo, 0.0F, 1.0F, param1TransferParameters, param1Int);
    }
    
    private Rgb(String param1String, float[] param1ArrayOffloat1, float[] param1ArrayOffloat2, float[] param1ArrayOffloat3, DoubleUnaryOperator param1DoubleUnaryOperator1, DoubleUnaryOperator param1DoubleUnaryOperator2, float param1Float1, float param1Float2, TransferParameters param1TransferParameters, int param1Int) {
      super(param1String, ColorSpace.Model.RGB, param1Int);
      if (param1ArrayOffloat1 != null && (param1ArrayOffloat1.length == 6 || param1ArrayOffloat1.length == 9)) {
        if (param1ArrayOffloat2 != null && (param1ArrayOffloat2.length == 2 || param1ArrayOffloat2.length == 3)) {
          if (param1DoubleUnaryOperator1 != null && param1DoubleUnaryOperator2 != null) {
            if (param1Float1 < param1Float2) {
              this.mWhitePoint = xyWhitePoint(param1ArrayOffloat2);
              float[] arrayOfFloat = xyPrimaries(param1ArrayOffloat1);
              this.mPrimaries = arrayOfFloat;
              if (param1ArrayOffloat3 == null) {
                this.mTransform = computeXYZMatrix(arrayOfFloat, this.mWhitePoint);
              } else if (param1ArrayOffloat3.length == 9) {
                this.mTransform = param1ArrayOffloat3;
              } else {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Transform must have 9 entries! Has ");
                stringBuilder1.append(param1ArrayOffloat3.length);
                throw new IllegalArgumentException(stringBuilder1.toString());
              } 
              this.mInverseTransform = ColorSpace.inverse3x3(this.mTransform);
              this.mOetf = param1DoubleUnaryOperator1;
              this.mEotf = param1DoubleUnaryOperator2;
              this.mMin = param1Float1;
              this.mMax = param1Float2;
              _$$Lambda$ColorSpace$Rgb$8EkhO2jIf14tuA3BvrmYJMa7YXM _$$Lambda$ColorSpace$Rgb$8EkhO2jIf14tuA3BvrmYJMa7YXM = new _$$Lambda$ColorSpace$Rgb$8EkhO2jIf14tuA3BvrmYJMa7YXM(this);
              this.mClampedOetf = param1DoubleUnaryOperator1.andThen(_$$Lambda$ColorSpace$Rgb$8EkhO2jIf14tuA3BvrmYJMa7YXM);
              this.mClampedEotf = _$$Lambda$ColorSpace$Rgb$8EkhO2jIf14tuA3BvrmYJMa7YXM.andThen(param1DoubleUnaryOperator2);
              this.mTransferParameters = param1TransferParameters;
              this.mIsWideGamut = isWideGamut(this.mPrimaries, param1Float1, param1Float2);
              this.mIsSrgb = isSrgb(this.mPrimaries, this.mWhitePoint, param1DoubleUnaryOperator1, param1DoubleUnaryOperator2, param1Float1, param1Float2, param1Int);
              if (this.mTransferParameters != null) {
                param1ArrayOffloat1 = this.mWhitePoint;
                if (param1ArrayOffloat1 != null) {
                  float[] arrayOfFloat1 = this.mTransform;
                  if (arrayOfFloat1 != null) {
                    arrayOfFloat1 = ColorSpace.adaptToIlluminantD50(param1ArrayOffloat1, arrayOfFloat1);
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
            stringBuilder.append(param1Float1);
            stringBuilder.append(", max=");
            stringBuilder.append(param1Float2);
            stringBuilder.append("; min must be strictly < max");
            throw new IllegalArgumentException(stringBuilder.toString());
          } 
          throw new IllegalArgumentException("The transfer functions of a color space cannot be null");
        } 
        throw new IllegalArgumentException("The color space's white point must be defined as an array of 2 floats in xyY or 3 float in XYZ");
      } 
      throw new IllegalArgumentException("The color space's primaries must be defined as an array of 6 floats in xyY or 9 floats in XYZ");
    }
    
    private static float area(float[] param1ArrayOffloat) {
      float f1 = param1ArrayOffloat[0];
      float f2 = param1ArrayOffloat[1];
      float f3 = param1ArrayOffloat[2];
      float f4 = param1ArrayOffloat[3];
      float f5 = param1ArrayOffloat[4];
      float f6 = param1ArrayOffloat[5];
      f1 = 0.5F * (f1 * f4 + f2 * f5 + f3 * f6 - f4 * f5 - f2 * f3 - f1 * f6);
      if (f1 < 0.0F)
        f1 = -f1; 
      return f1;
    }
    
    private double clamp(double param1Double) {
      float f = this.mMin;
      if (param1Double < f)
        return f; 
      f = this.mMax;
      return (param1Double > f) ? f : param1Double;
    }
    
    private static boolean compare(double param1Double, DoubleUnaryOperator param1DoubleUnaryOperator1, DoubleUnaryOperator param1DoubleUnaryOperator2) {
      boolean bool;
      if (Math.abs(param1DoubleUnaryOperator1.applyAsDouble(param1Double) - param1DoubleUnaryOperator2.applyAsDouble(param1Double)) <= 0.001D) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private static float[] computePrimaries(float[] param1ArrayOffloat) {
      float[] arrayOfFloat1 = ColorSpace.mul3x3Float3(param1ArrayOffloat, new float[] { 1.0F, 0.0F, 0.0F });
      float[] arrayOfFloat2 = ColorSpace.mul3x3Float3(param1ArrayOffloat, new float[] { 0.0F, 1.0F, 0.0F });
      param1ArrayOffloat = ColorSpace.mul3x3Float3(param1ArrayOffloat, new float[] { 0.0F, 0.0F, 1.0F });
      float f1 = arrayOfFloat1[0] + arrayOfFloat1[1] + arrayOfFloat1[2];
      float f2 = arrayOfFloat2[0] + arrayOfFloat2[1] + arrayOfFloat2[2];
      float f3 = param1ArrayOffloat[0] + param1ArrayOffloat[1] + param1ArrayOffloat[2];
      return new float[] { arrayOfFloat1[0] / f1, arrayOfFloat1[1] / f1, arrayOfFloat2[0] / f2, arrayOfFloat2[1] / f2, param1ArrayOffloat[0] / f3, param1ArrayOffloat[1] / f3 };
    }
    
    private static float[] computeWhitePoint(float[] param1ArrayOffloat) {
      param1ArrayOffloat = ColorSpace.mul3x3Float3(param1ArrayOffloat, new float[] { 1.0F, 1.0F, 1.0F });
      float f = param1ArrayOffloat[0] + param1ArrayOffloat[1] + param1ArrayOffloat[2];
      return new float[] { param1ArrayOffloat[0] / f, param1ArrayOffloat[1] / f };
    }
    
    private static float[] computeXYZMatrix(float[] param1ArrayOffloat1, float[] param1ArrayOffloat2) {
      float f1 = param1ArrayOffloat1[0];
      float f2 = param1ArrayOffloat1[1];
      float f3 = param1ArrayOffloat1[2];
      float f4 = param1ArrayOffloat1[3];
      float f5 = param1ArrayOffloat1[4];
      float f6 = param1ArrayOffloat1[5];
      float f7 = param1ArrayOffloat2[0];
      float f8 = param1ArrayOffloat2[1];
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
    
    private static boolean contains(float[] param1ArrayOffloat1, float[] param1ArrayOffloat2) {
      float[] arrayOfFloat = new float[6];
      arrayOfFloat[0] = param1ArrayOffloat1[0] - param1ArrayOffloat2[0];
      arrayOfFloat[1] = param1ArrayOffloat1[1] - param1ArrayOffloat2[1];
      arrayOfFloat[2] = param1ArrayOffloat1[2] - param1ArrayOffloat2[2];
      arrayOfFloat[3] = param1ArrayOffloat1[3] - param1ArrayOffloat2[3];
      arrayOfFloat[4] = param1ArrayOffloat1[4] - param1ArrayOffloat2[4];
      arrayOfFloat[5] = param1ArrayOffloat1[5] - param1ArrayOffloat2[5];
      return (cross(arrayOfFloat[0], arrayOfFloat[1], param1ArrayOffloat2[0] - param1ArrayOffloat2[4], param1ArrayOffloat2[1] - param1ArrayOffloat2[5]) < 0.0F || cross(param1ArrayOffloat2[0] - param1ArrayOffloat2[2], param1ArrayOffloat2[1] - param1ArrayOffloat2[3], arrayOfFloat[0], arrayOfFloat[1]) < 0.0F) ? false : ((cross(arrayOfFloat[2], arrayOfFloat[3], param1ArrayOffloat2[2] - param1ArrayOffloat2[0], param1ArrayOffloat2[3] - param1ArrayOffloat2[1]) < 0.0F || cross(param1ArrayOffloat2[2] - param1ArrayOffloat2[4], param1ArrayOffloat2[3] - param1ArrayOffloat2[5], arrayOfFloat[2], arrayOfFloat[3]) < 0.0F) ? false : (!(cross(arrayOfFloat[4], arrayOfFloat[5], param1ArrayOffloat2[4] - param1ArrayOffloat2[2], param1ArrayOffloat2[5] - param1ArrayOffloat2[3]) < 0.0F || cross(param1ArrayOffloat2[4] - param1ArrayOffloat2[0], param1ArrayOffloat2[5] - param1ArrayOffloat2[1], arrayOfFloat[4], arrayOfFloat[5]) < 0.0F)));
    }
    
    private static float cross(float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
      return param1Float1 * param1Float4 - param1Float2 * param1Float3;
    }
    
    private static boolean isGray(float[] param1ArrayOffloat) {
      int i = param1ArrayOffloat.length;
      boolean bool = true;
      if (i != 9 || param1ArrayOffloat[1] != 0.0F || param1ArrayOffloat[2] != 0.0F || param1ArrayOffloat[3] != 0.0F || param1ArrayOffloat[5] != 0.0F || param1ArrayOffloat[6] != 0.0F || param1ArrayOffloat[7] != 0.0F)
        bool = false; 
      return bool;
    }
    
    private static boolean isSrgb(float[] param1ArrayOffloat1, float[] param1ArrayOffloat2, DoubleUnaryOperator param1DoubleUnaryOperator1, DoubleUnaryOperator param1DoubleUnaryOperator2, float param1Float1, float param1Float2, int param1Int) {
      if (param1Int == 0)
        return true; 
      if (!ColorSpace.compare(param1ArrayOffloat1, ColorSpace.SRGB_PRIMARIES))
        return false; 
      if (!ColorSpace.compare(param1ArrayOffloat2, ILLUMINANT_D65))
        return false; 
      if (param1Float1 != 0.0F)
        return false; 
      if (param1Float2 != 1.0F)
        return false; 
      Rgb rgb = (Rgb)get(ColorSpace.Named.SRGB);
      double d;
      for (d = 0.0D; d <= 1.0D; d += 0.00392156862745098D) {
        if (!compare(d, param1DoubleUnaryOperator1, rgb.mOetf))
          return false; 
        if (!compare(d, param1DoubleUnaryOperator2, rgb.mEotf))
          return false; 
      } 
      return true;
    }
    
    private static boolean isWideGamut(float[] param1ArrayOffloat, float param1Float1, float param1Float2) {
      boolean bool;
      if ((area(param1ArrayOffloat) / area(ColorSpace.NTSC_1953_PRIMARIES) > 0.9F && contains(param1ArrayOffloat, ColorSpace.SRGB_PRIMARIES)) || (param1Float1 < 0.0F && param1Float2 > 1.0F)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private static native long nativeCreate(float param1Float1, float param1Float2, float param1Float3, float param1Float4, float param1Float5, float param1Float6, float param1Float7, float[] param1ArrayOffloat);
    
    private static native long nativeGetNativeFinalizer();
    
    private static float[] xyPrimaries(float[] param1ArrayOffloat) {
      float[] arrayOfFloat = new float[6];
      if (param1ArrayOffloat.length == 9) {
        float f = param1ArrayOffloat[0] + param1ArrayOffloat[1] + param1ArrayOffloat[2];
        arrayOfFloat[0] = param1ArrayOffloat[0] / f;
        arrayOfFloat[1] = param1ArrayOffloat[1] / f;
        f = param1ArrayOffloat[3] + param1ArrayOffloat[4] + param1ArrayOffloat[5];
        arrayOfFloat[2] = param1ArrayOffloat[3] / f;
        arrayOfFloat[3] = param1ArrayOffloat[4] / f;
        f = param1ArrayOffloat[6] + param1ArrayOffloat[7] + param1ArrayOffloat[8];
        arrayOfFloat[4] = param1ArrayOffloat[6] / f;
        arrayOfFloat[5] = param1ArrayOffloat[7] / f;
      } else {
        System.arraycopy(param1ArrayOffloat, 0, arrayOfFloat, 0, 6);
      } 
      return arrayOfFloat;
    }
    
    private static float[] xyWhitePoint(float[] param1ArrayOffloat) {
      float[] arrayOfFloat = new float[2];
      if (param1ArrayOffloat.length == 3) {
        float f = param1ArrayOffloat[0] + param1ArrayOffloat[1] + param1ArrayOffloat[2];
        arrayOfFloat[0] = param1ArrayOffloat[0] / f;
        arrayOfFloat[1] = param1ArrayOffloat[1] / f;
      } else {
        System.arraycopy(param1ArrayOffloat, 0, arrayOfFloat, 0, 2);
      } 
      return arrayOfFloat;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      if (!super.equals(param1Object))
        return false; 
      param1Object = param1Object;
      if (Float.compare(((Rgb)param1Object).mMin, this.mMin) != 0)
        return false; 
      if (Float.compare(((Rgb)param1Object).mMax, this.mMax) != 0)
        return false; 
      if (!Arrays.equals(this.mWhitePoint, ((Rgb)param1Object).mWhitePoint))
        return false; 
      if (!Arrays.equals(this.mPrimaries, ((Rgb)param1Object).mPrimaries))
        return false; 
      TransferParameters transferParameters = this.mTransferParameters;
      return (transferParameters != null) ? transferParameters.equals(((Rgb)param1Object).mTransferParameters) : ((((Rgb)param1Object).mTransferParameters == null) ? true : (!this.mOetf.equals(((Rgb)param1Object).mOetf) ? false : this.mEotf.equals(((Rgb)param1Object).mEotf)));
    }
    
    public float[] fromLinear(float param1Float1, float param1Float2, float param1Float3) {
      return fromLinear(new float[] { param1Float1, param1Float2, param1Float3 });
    }
    
    public float[] fromLinear(float[] param1ArrayOffloat) {
      param1ArrayOffloat[0] = (float)this.mClampedOetf.applyAsDouble(param1ArrayOffloat[0]);
      param1ArrayOffloat[1] = (float)this.mClampedOetf.applyAsDouble(param1ArrayOffloat[1]);
      param1ArrayOffloat[2] = (float)this.mClampedOetf.applyAsDouble(param1ArrayOffloat[2]);
      return param1ArrayOffloat;
    }
    
    public float[] fromXyz(float[] param1ArrayOffloat) {
      ColorSpace.mul3x3Float3(this.mInverseTransform, param1ArrayOffloat);
      param1ArrayOffloat[0] = (float)this.mClampedOetf.applyAsDouble(param1ArrayOffloat[0]);
      param1ArrayOffloat[1] = (float)this.mClampedOetf.applyAsDouble(param1ArrayOffloat[1]);
      param1ArrayOffloat[2] = (float)this.mClampedOetf.applyAsDouble(param1ArrayOffloat[2]);
      return param1ArrayOffloat;
    }
    
    public DoubleUnaryOperator getEotf() {
      return this.mClampedEotf;
    }
    
    public float[] getInverseTransform() {
      float[] arrayOfFloat = this.mInverseTransform;
      return Arrays.copyOf(arrayOfFloat, arrayOfFloat.length);
    }
    
    public float[] getInverseTransform(float[] param1ArrayOffloat) {
      float[] arrayOfFloat = this.mInverseTransform;
      System.arraycopy(arrayOfFloat, 0, param1ArrayOffloat, 0, arrayOfFloat.length);
      return param1ArrayOffloat;
    }
    
    public float getMaxValue(int param1Int) {
      return this.mMax;
    }
    
    public float getMinValue(int param1Int) {
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
    
    public float[] getPrimaries(float[] param1ArrayOffloat) {
      float[] arrayOfFloat = this.mPrimaries;
      System.arraycopy(arrayOfFloat, 0, param1ArrayOffloat, 0, arrayOfFloat.length);
      return param1ArrayOffloat;
    }
    
    public TransferParameters getTransferParameters() {
      return this.mTransferParameters;
    }
    
    public float[] getTransform() {
      float[] arrayOfFloat = this.mTransform;
      return Arrays.copyOf(arrayOfFloat, arrayOfFloat.length);
    }
    
    public float[] getTransform(float[] param1ArrayOffloat) {
      float[] arrayOfFloat = this.mTransform;
      System.arraycopy(arrayOfFloat, 0, param1ArrayOffloat, 0, arrayOfFloat.length);
      return param1ArrayOffloat;
    }
    
    public float[] getWhitePoint() {
      float[] arrayOfFloat = this.mWhitePoint;
      return Arrays.copyOf(arrayOfFloat, arrayOfFloat.length);
    }
    
    public float[] getWhitePoint(float[] param1ArrayOffloat) {
      float[] arrayOfFloat = this.mWhitePoint;
      param1ArrayOffloat[0] = arrayOfFloat[0];
      param1ArrayOffloat[1] = arrayOfFloat[1];
      return param1ArrayOffloat;
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
    
    public float[] toLinear(float param1Float1, float param1Float2, float param1Float3) {
      return toLinear(new float[] { param1Float1, param1Float2, param1Float3 });
    }
    
    public float[] toLinear(float[] param1ArrayOffloat) {
      param1ArrayOffloat[0] = (float)this.mClampedEotf.applyAsDouble(param1ArrayOffloat[0]);
      param1ArrayOffloat[1] = (float)this.mClampedEotf.applyAsDouble(param1ArrayOffloat[1]);
      param1ArrayOffloat[2] = (float)this.mClampedEotf.applyAsDouble(param1ArrayOffloat[2]);
      return param1ArrayOffloat;
    }
    
    public float[] toXyz(float[] param1ArrayOffloat) {
      param1ArrayOffloat[0] = (float)this.mClampedEotf.applyAsDouble(param1ArrayOffloat[0]);
      param1ArrayOffloat[1] = (float)this.mClampedEotf.applyAsDouble(param1ArrayOffloat[1]);
      param1ArrayOffloat[2] = (float)this.mClampedEotf.applyAsDouble(param1ArrayOffloat[2]);
      return ColorSpace.mul3x3Float3(this.mTransform, param1ArrayOffloat);
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
    
    public TransferParameters(double param1Double1, double param1Double2, double param1Double3, double param1Double4, double param1Double5) {
      this(param1Double1, param1Double2, param1Double3, param1Double4, 0.0D, 0.0D, param1Double5);
    }
    
    public TransferParameters(double param1Double1, double param1Double2, double param1Double3, double param1Double4, double param1Double5, double param1Double6, double param1Double7) {
      if (!Double.isNaN(param1Double1) && !Double.isNaN(param1Double2) && !Double.isNaN(param1Double3) && !Double.isNaN(param1Double4) && !Double.isNaN(param1Double5) && !Double.isNaN(param1Double6) && !Double.isNaN(param1Double7)) {
        if (param1Double4 >= 0.0D && param1Double4 <= (Math.ulp(1.0F) + 1.0F)) {
          if (param1Double4 != 0.0D || (param1Double1 != 0.0D && param1Double7 != 0.0D)) {
            if (param1Double4 < 1.0D || param1Double3 != 0.0D) {
              if ((param1Double1 != 0.0D && param1Double7 != 0.0D) || param1Double3 != 0.0D) {
                if (param1Double3 >= 0.0D) {
                  if (param1Double1 >= 0.0D && param1Double7 >= 0.0D) {
                    this.a = param1Double1;
                    this.b = param1Double2;
                    this.c = param1Double3;
                    this.d = param1Double4;
                    this.e = param1Double5;
                    this.f = param1Double6;
                    this.g = param1Double7;
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
        stringBuilder.append(param1Double4);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      throw new IllegalArgumentException("Parameters cannot be NaN");
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if (Double.compare(((TransferParameters)param1Object).a, this.a) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param1Object).b, this.b) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param1Object).c, this.c) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param1Object).d, this.d) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param1Object).e, this.e) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param1Object).f, this.f) != 0)
        return false; 
      if (Double.compare(((TransferParameters)param1Object).g, this.g) != 0)
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
  
  private static final class Xyz extends ColorSpace {
    private Xyz(String param1String, int param1Int) {
      super(param1String, ColorSpace.Model.XYZ, param1Int);
    }
    
    private static float clamp(float param1Float) {
      float f = -2.0F;
      if (param1Float < -2.0F) {
        param1Float = f;
      } else if (param1Float > 2.0F) {
        param1Float = 2.0F;
      } 
      return param1Float;
    }
    
    public float[] fromXyz(float[] param1ArrayOffloat) {
      param1ArrayOffloat[0] = clamp(param1ArrayOffloat[0]);
      param1ArrayOffloat[1] = clamp(param1ArrayOffloat[1]);
      param1ArrayOffloat[2] = clamp(param1ArrayOffloat[2]);
      return param1ArrayOffloat;
    }
    
    public float getMaxValue(int param1Int) {
      return 2.0F;
    }
    
    public float getMinValue(int param1Int) {
      return -2.0F;
    }
    
    public boolean isWideGamut() {
      return true;
    }
    
    public float[] toXyz(float[] param1ArrayOffloat) {
      param1ArrayOffloat[0] = clamp(param1ArrayOffloat[0]);
      param1ArrayOffloat[1] = clamp(param1ArrayOffloat[1]);
      param1ArrayOffloat[2] = clamp(param1ArrayOffloat[2]);
      return param1ArrayOffloat;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */