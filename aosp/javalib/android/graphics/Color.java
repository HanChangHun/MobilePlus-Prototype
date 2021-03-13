package android.graphics;

import android.util.Half;
import com.android.internal.util.XmlUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.function.DoubleUnaryOperator;

public class Color {
  public static final int BLACK = -16777216;
  
  public static final int BLUE = -16776961;
  
  public static final int CYAN = -16711681;
  
  public static final int DKGRAY = -12303292;
  
  public static final int GRAY = -7829368;
  
  public static final int GREEN = -16711936;
  
  public static final int LTGRAY = -3355444;
  
  public static final int MAGENTA = -65281;
  
  public static final int RED = -65536;
  
  public static final int TRANSPARENT = 0;
  
  public static final int WHITE = -1;
  
  public static final int YELLOW = -256;
  
  private static final HashMap<String, Integer> sColorNameMap;
  
  private final ColorSpace mColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
  
  private final float[] mComponents = new float[] { 0.0F, 0.0F, 0.0F, 1.0F };
  
  static {
    HashMap<Object, Object> hashMap = new HashMap<>();
    sColorNameMap = (HashMap)hashMap;
    hashMap.put("black", Integer.valueOf(-16777216));
    HashMap<String, Integer> hashMap1 = sColorNameMap;
    Integer integer1 = Integer.valueOf(-12303292);
    hashMap1.put("darkgray", integer1);
    HashMap<String, Integer> hashMap2 = sColorNameMap;
    Integer integer2 = Integer.valueOf(-7829368);
    hashMap2.put("gray", integer2);
    HashMap<String, Integer> hashMap3 = sColorNameMap;
    Integer integer3 = Integer.valueOf(-3355444);
    hashMap3.put("lightgray", integer3);
    sColorNameMap.put("white", Integer.valueOf(-1));
    sColorNameMap.put("red", Integer.valueOf(-65536));
    HashMap<String, Integer> hashMap4 = sColorNameMap;
    Integer integer4 = Integer.valueOf(-16711936);
    hashMap4.put("green", integer4);
    sColorNameMap.put("blue", Integer.valueOf(-16776961));
    sColorNameMap.put("yellow", Integer.valueOf(-256));
    HashMap<String, Integer> hashMap5 = sColorNameMap;
    Integer integer5 = Integer.valueOf(-16711681);
    hashMap5.put("cyan", integer5);
    hashMap5 = sColorNameMap;
    Integer integer6 = Integer.valueOf(-65281);
    hashMap5.put("magenta", integer6);
    sColorNameMap.put("aqua", integer5);
    sColorNameMap.put("fuchsia", integer6);
    sColorNameMap.put("darkgrey", integer1);
    sColorNameMap.put("grey", integer2);
    sColorNameMap.put("lightgrey", integer3);
    sColorNameMap.put("lime", integer4);
    sColorNameMap.put("maroon", Integer.valueOf(-8388608));
    sColorNameMap.put("navy", Integer.valueOf(-16777088));
    sColorNameMap.put("olive", Integer.valueOf(-8355840));
    sColorNameMap.put("purple", Integer.valueOf(-8388480));
    sColorNameMap.put("silver", Integer.valueOf(-4144960));
    sColorNameMap.put("teal", Integer.valueOf(-16744320));
  }
  
  public Color() {}
  
  private Color(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    this(paramFloat1, paramFloat2, paramFloat3, paramFloat4, ColorSpace.get(ColorSpace.Named.SRGB));
  }
  
  private Color(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, ColorSpace paramColorSpace) {}
  
  private Color(float[] paramArrayOffloat, ColorSpace paramColorSpace) {}
  
  public static int HSVToColor(int paramInt, float[] paramArrayOffloat) {
    if (paramArrayOffloat.length >= 3)
      return nativeHSVToColor(paramInt, paramArrayOffloat); 
    throw new RuntimeException("3 components required for hsv");
  }
  
  public static int HSVToColor(float[] paramArrayOffloat) {
    return HSVToColor(255, paramArrayOffloat);
  }
  
  public static void RGBToHSV(int paramInt1, int paramInt2, int paramInt3, float[] paramArrayOffloat) {
    if (paramArrayOffloat.length >= 3) {
      nativeRGBToHSV(paramInt1, paramInt2, paramInt3, paramArrayOffloat);
      return;
    } 
    throw new RuntimeException("3 components required for hsv");
  }
  
  public static float alpha(long paramLong) {
    return ((0x3FL & paramLong) == 0L) ? ((float)(paramLong >> 56L & 0xFFL) / 255.0F) : ((float)(paramLong >> 6L & 0x3FFL) / 1023.0F);
  }
  
  public static int alpha(int paramInt) {
    return paramInt >>> 24;
  }
  
  public static int argb(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    int i = (int)(paramFloat1 * 255.0F + 0.5F);
    int j = (int)(paramFloat2 * 255.0F + 0.5F);
    int k = (int)(paramFloat3 * 255.0F + 0.5F);
    return (int)(255.0F * paramFloat4 + 0.5F) | i << 24 | j << 16 | k << 8;
  }
  
  public static int argb(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return paramInt1 << 24 | paramInt2 << 16 | paramInt3 << 8 | paramInt4;
  }
  
  public static float blue(long paramLong) {
    return ((0x3FL & paramLong) == 0L) ? ((float)(paramLong >> 32L & 0xFFL) / 255.0F) : Half.toFloat((short)(int)(paramLong >> 16L & 0xFFFFL));
  }
  
  public static int blue(int paramInt) {
    return paramInt & 0xFF;
  }
  
  public static ColorSpace colorSpace(long paramLong) {
    return ColorSpace.get((int)(0x3FL & paramLong));
  }
  
  public static void colorToHSV(int paramInt, float[] paramArrayOffloat) {
    RGBToHSV(paramInt >> 16 & 0xFF, paramInt >> 8 & 0xFF, paramInt & 0xFF, paramArrayOffloat);
  }
  
  public static long convert(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, ColorSpace.Connector paramConnector) {
    float[] arrayOfFloat = paramConnector.transform(paramFloat1, paramFloat2, paramFloat3);
    return pack(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], paramFloat4, paramConnector.getDestination());
  }
  
  public static long convert(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, ColorSpace paramColorSpace1, ColorSpace paramColorSpace2) {
    float[] arrayOfFloat = ColorSpace.connect(paramColorSpace1, paramColorSpace2).transform(paramFloat1, paramFloat2, paramFloat3);
    return pack(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], paramFloat4, paramColorSpace2);
  }
  
  public static long convert(int paramInt, ColorSpace paramColorSpace) {
    return convert((paramInt >> 16 & 0xFF) / 255.0F, (paramInt >> 8 & 0xFF) / 255.0F, (paramInt & 0xFF) / 255.0F, (paramInt >> 24 & 0xFF) / 255.0F, ColorSpace.get(ColorSpace.Named.SRGB), paramColorSpace);
  }
  
  public static long convert(long paramLong, ColorSpace.Connector paramConnector) {
    return convert(red(paramLong), green(paramLong), blue(paramLong), alpha(paramLong), paramConnector);
  }
  
  public static long convert(long paramLong, ColorSpace paramColorSpace) {
    return convert(red(paramLong), green(paramLong), blue(paramLong), alpha(paramLong), colorSpace(paramLong), paramColorSpace);
  }
  
  public static int getHtmlColor(String paramString) {
    Integer integer = sColorNameMap.get(paramString.toLowerCase(Locale.ROOT));
    if (integer != null)
      return integer.intValue(); 
    try {
      return XmlUtils.convertValueToInt(paramString, -1);
    } catch (NumberFormatException numberFormatException) {
      return -1;
    } 
  }
  
  public static float green(long paramLong) {
    return ((0x3FL & paramLong) == 0L) ? ((float)(paramLong >> 40L & 0xFFL) / 255.0F) : Half.toFloat((short)(int)(paramLong >> 32L & 0xFFFFL));
  }
  
  public static int green(int paramInt) {
    return paramInt >> 8 & 0xFF;
  }
  
  public static boolean isInColorSpace(long paramLong, ColorSpace paramColorSpace) {
    boolean bool;
    if ((int)(0x3FL & paramLong) == paramColorSpace.getId()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isSrgb(long paramLong) {
    return colorSpace(paramLong).isSrgb();
  }
  
  public static boolean isWideGamut(long paramLong) {
    return colorSpace(paramLong).isWideGamut();
  }
  
  public static float luminance(int paramInt) {
    DoubleUnaryOperator doubleUnaryOperator = ((ColorSpace.Rgb)ColorSpace.get(ColorSpace.Named.SRGB)).getEotf();
    return (float)(0.2126D * doubleUnaryOperator.applyAsDouble(red(paramInt) / 255.0D) + 0.7152D * doubleUnaryOperator.applyAsDouble(green(paramInt) / 255.0D) + 0.0722D * doubleUnaryOperator.applyAsDouble(blue(paramInt) / 255.0D));
  }
  
  public static float luminance(long paramLong) {
    ColorSpace colorSpace = colorSpace(paramLong);
    if (colorSpace.getModel() == ColorSpace.Model.RGB) {
      DoubleUnaryOperator doubleUnaryOperator = ((ColorSpace.Rgb)colorSpace).getEotf();
      return saturate((float)(0.2126D * doubleUnaryOperator.applyAsDouble(red(paramLong)) + 0.7152D * doubleUnaryOperator.applyAsDouble(green(paramLong)) + 0.0722D * doubleUnaryOperator.applyAsDouble(blue(paramLong))));
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("The specified color must be encoded in an RGB color space. The supplied color space is ");
    stringBuilder.append(colorSpace.getModel());
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static native int nativeHSVToColor(int paramInt, float[] paramArrayOffloat);
  
  private static native void nativeRGBToHSV(int paramInt1, int paramInt2, int paramInt3, float[] paramArrayOffloat);
  
  public static long pack(float paramFloat1, float paramFloat2, float paramFloat3) {
    return pack(paramFloat1, paramFloat2, paramFloat3, 1.0F, ColorSpace.get(ColorSpace.Named.SRGB));
  }
  
  public static long pack(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    return pack(paramFloat1, paramFloat2, paramFloat3, paramFloat4, ColorSpace.get(ColorSpace.Named.SRGB));
  }
  
  public static long pack(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, ColorSpace paramColorSpace) {
    if (paramColorSpace.isSrgb()) {
      int j = (int)(paramFloat4 * 255.0F + 0.5F);
      int k = (int)(paramFloat1 * 255.0F + 0.5F);
      int m = (int)(paramFloat2 * 255.0F + 0.5F);
      return (((int)(255.0F * paramFloat3 + 0.5F) | k << 16 | j << 24 | m << 8) & 0xFFFFFFFFL) << 32L;
    } 
    int i = paramColorSpace.getId();
    if (i != -1) {
      if (paramColorSpace.getComponentCount() <= 3) {
        short s1 = Half.toHalf(paramFloat1);
        short s2 = Half.toHalf(paramFloat2);
        short s3 = Half.toHalf(paramFloat3);
        int j = (int)(Math.max(0.0F, Math.min(paramFloat4, 1.0F)) * 1023.0F + 0.5F);
        long l1 = s1;
        long l2 = s2;
        return (0xFFFFL & s3) << 16L | (l1 & 0xFFFFL) << 48L | (l2 & 0xFFFFL) << 32L | (j & 0x3FFL) << 6L | i & 0x3FL;
      } 
      throw new IllegalArgumentException("The color space must use a color model with at most 3 components");
    } 
    throw new IllegalArgumentException("Unknown color space, please use a color space returned by ColorSpace.get()");
  }
  
  public static long pack(int paramInt) {
    return (paramInt & 0xFFFFFFFFL) << 32L;
  }
  
  public static int parseColor(String paramString) {
    if (paramString.charAt(0) == '#') {
      long l = Long.parseLong(paramString.substring(1), 16);
      if (paramString.length() == 7) {
        l |= 0xFFFFFFFFFF000000L;
      } else if (paramString.length() != 9) {
        throw new IllegalArgumentException("Unknown color");
      } 
      return (int)l;
    } 
    Integer integer = sColorNameMap.get(paramString.toLowerCase(Locale.ROOT));
    if (integer != null)
      return integer.intValue(); 
    throw new IllegalArgumentException("Unknown color");
  }
  
  public static float red(long paramLong) {
    return ((0x3FL & paramLong) == 0L) ? ((float)(paramLong >> 48L & 0xFFL) / 255.0F) : Half.toFloat((short)(int)(paramLong >> 48L & 0xFFFFL));
  }
  
  public static int red(int paramInt) {
    return paramInt >> 16 & 0xFF;
  }
  
  public static int rgb(float paramFloat1, float paramFloat2, float paramFloat3) {
    int i = (int)(paramFloat1 * 255.0F + 0.5F);
    int j = (int)(paramFloat2 * 255.0F + 0.5F);
    return (int)(255.0F * paramFloat3 + 0.5F) | i << 16 | 0xFF000000 | j << 8;
  }
  
  public static int rgb(int paramInt1, int paramInt2, int paramInt3) {
    return paramInt1 << 16 | 0xFF000000 | paramInt2 << 8 | paramInt3;
  }
  
  private static float saturate(float paramFloat) {
    float f = 0.0F;
    if (paramFloat <= 0.0F) {
      paramFloat = f;
    } else if (paramFloat >= 1.0F) {
      paramFloat = 1.0F;
    } 
    return paramFloat;
  }
  
  public static int toArgb(long paramLong) {
    if ((0x3FL & paramLong) == 0L)
      return (int)(paramLong >> 32L); 
    float f1 = red(paramLong);
    float f2 = green(paramLong);
    float f3 = blue(paramLong);
    float f4 = alpha(paramLong);
    float[] arrayOfFloat = ColorSpace.connect(colorSpace(paramLong)).transform(f1, f2, f3);
    int i = (int)(f4 * 255.0F + 0.5F);
    int j = (int)(arrayOfFloat[0] * 255.0F + 0.5F);
    int k = (int)(arrayOfFloat[1] * 255.0F + 0.5F);
    return (int)(arrayOfFloat[2] * 255.0F + 0.5F) | i << 24 | j << 16 | k << 8;
  }
  
  public static Color valueOf(float paramFloat1, float paramFloat2, float paramFloat3) {
    return new Color(paramFloat1, paramFloat2, paramFloat3, 1.0F);
  }
  
  public static Color valueOf(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    return new Color(saturate(paramFloat1), saturate(paramFloat2), saturate(paramFloat3), saturate(paramFloat4));
  }
  
  public static Color valueOf(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, ColorSpace paramColorSpace) {
    if (paramColorSpace.getComponentCount() <= 3)
      return new Color(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramColorSpace); 
    throw new IllegalArgumentException("The specified color space must use a color model with at most 3 color components");
  }
  
  public static Color valueOf(int paramInt) {
    return new Color((paramInt >> 16 & 0xFF) / 255.0F, (paramInt >> 8 & 0xFF) / 255.0F, (paramInt & 0xFF) / 255.0F, (paramInt >> 24 & 0xFF) / 255.0F, ColorSpace.get(ColorSpace.Named.SRGB));
  }
  
  public static Color valueOf(long paramLong) {
    return new Color(red(paramLong), green(paramLong), blue(paramLong), alpha(paramLong), colorSpace(paramLong));
  }
  
  public static Color valueOf(float[] paramArrayOffloat, ColorSpace paramColorSpace) {
    if (paramArrayOffloat.length >= paramColorSpace.getComponentCount() + 1)
      return new Color(Arrays.copyOf(paramArrayOffloat, paramColorSpace.getComponentCount() + 1), paramColorSpace); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Received a component array of length ");
    stringBuilder.append(paramArrayOffloat.length);
    stringBuilder.append(" but the color model requires ");
    stringBuilder.append(paramColorSpace.getComponentCount() + 1);
    stringBuilder.append(" (including alpha)");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public float alpha() {
    float[] arrayOfFloat = this.mComponents;
    return arrayOfFloat[arrayOfFloat.length - 1];
  }
  
  public float blue() {
    return this.mComponents[2];
  }
  
  public Color convert(ColorSpace paramColorSpace) {
    ColorSpace.Connector connector = ColorSpace.connect(this.mColorSpace, paramColorSpace);
    float[] arrayOfFloat1 = new float[4];
    float[] arrayOfFloat2 = this.mComponents;
    arrayOfFloat1[0] = arrayOfFloat2[0];
    arrayOfFloat1[1] = arrayOfFloat2[1];
    arrayOfFloat1[2] = arrayOfFloat2[2];
    arrayOfFloat1[3] = arrayOfFloat2[3];
    connector.transform(arrayOfFloat1);
    return new Color(arrayOfFloat1, paramColorSpace);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    return !Arrays.equals(this.mComponents, ((Color)paramObject).mComponents) ? false : this.mColorSpace.equals(((Color)paramObject).mColorSpace);
  }
  
  public ColorSpace getColorSpace() {
    return this.mColorSpace;
  }
  
  public float getComponent(int paramInt) {
    return this.mComponents[paramInt];
  }
  
  public int getComponentCount() {
    return this.mColorSpace.getComponentCount() + 1;
  }
  
  public float[] getComponents() {
    float[] arrayOfFloat = this.mComponents;
    return Arrays.copyOf(arrayOfFloat, arrayOfFloat.length);
  }
  
  public float[] getComponents(float[] paramArrayOffloat) {
    if (paramArrayOffloat == null) {
      paramArrayOffloat = this.mComponents;
      return Arrays.copyOf(paramArrayOffloat, paramArrayOffloat.length);
    } 
    int i = paramArrayOffloat.length;
    float[] arrayOfFloat = this.mComponents;
    if (i >= arrayOfFloat.length) {
      System.arraycopy(arrayOfFloat, 0, paramArrayOffloat, 0, arrayOfFloat.length);
      return paramArrayOffloat;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("The specified array's length must be at least ");
    stringBuilder.append(this.mComponents.length);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public ColorSpace.Model getModel() {
    return this.mColorSpace.getModel();
  }
  
  public float green() {
    return this.mComponents[1];
  }
  
  public int hashCode() {
    return Arrays.hashCode(this.mComponents) * 31 + this.mColorSpace.hashCode();
  }
  
  public boolean isSrgb() {
    return getColorSpace().isSrgb();
  }
  
  public boolean isWideGamut() {
    return getColorSpace().isWideGamut();
  }
  
  public float luminance() {
    if (this.mColorSpace.getModel() == ColorSpace.Model.RGB) {
      DoubleUnaryOperator doubleUnaryOperator = ((ColorSpace.Rgb)this.mColorSpace).getEotf();
      return saturate((float)(0.2126D * doubleUnaryOperator.applyAsDouble(this.mComponents[0]) + 0.7152D * doubleUnaryOperator.applyAsDouble(this.mComponents[1]) + 0.0722D * doubleUnaryOperator.applyAsDouble(this.mComponents[2])));
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("The specified color must be encoded in an RGB color space. The supplied color space is ");
    stringBuilder.append(this.mColorSpace.getModel());
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public long pack() {
    float[] arrayOfFloat = this.mComponents;
    return pack(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3], this.mColorSpace);
  }
  
  public float red() {
    return this.mComponents[0];
  }
  
  public int toArgb() {
    if (this.mColorSpace.isSrgb()) {
      float[] arrayOfFloat = this.mComponents;
      int i = (int)(arrayOfFloat[3] * 255.0F + 0.5F);
      int j = (int)(arrayOfFloat[0] * 255.0F + 0.5F);
      int k = (int)(arrayOfFloat[1] * 255.0F + 0.5F);
      return (int)(arrayOfFloat[2] * 255.0F + 0.5F) | i << 24 | j << 16 | k << 8;
    } 
    float[] arrayOfFloat1 = new float[4];
    float[] arrayOfFloat2 = this.mComponents;
    arrayOfFloat1[0] = arrayOfFloat2[0];
    arrayOfFloat1[1] = arrayOfFloat2[1];
    arrayOfFloat1[2] = arrayOfFloat2[2];
    arrayOfFloat1[3] = arrayOfFloat2[3];
    ColorSpace.connect(this.mColorSpace).transform(arrayOfFloat1);
    return (int)(arrayOfFloat1[3] * 255.0F + 0.5F) << 24 | (int)(arrayOfFloat1[0] * 255.0F + 0.5F) << 16 | (int)(arrayOfFloat1[1] * 255.0F + 0.5F) << 8 | (int)(arrayOfFloat1[2] * 255.0F + 0.5F);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("Color(");
    float[] arrayOfFloat = this.mComponents;
    int i = arrayOfFloat.length;
    for (byte b = 0; b < i; b++) {
      stringBuilder.append(arrayOfFloat[b]);
      stringBuilder.append(", ");
    } 
    stringBuilder.append(this.mColorSpace.getName());
    stringBuilder.append(')');
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Color.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */