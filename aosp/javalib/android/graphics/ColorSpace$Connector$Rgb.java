package android.graphics;

import java.util.Arrays;

class Rgb extends ColorSpace.Connector {
  private final ColorSpace.Rgb mDestination;
  
  private final ColorSpace.Rgb mSource;
  
  private final float[] mTransform;
  
  Rgb(ColorSpace.Rgb paramRgb1, ColorSpace.Rgb paramRgb2, ColorSpace.RenderIntent paramRenderIntent) {
    super(paramRgb1, paramRgb2, paramRgb1, paramRgb2, paramRenderIntent, null);
    this.mSource = paramRgb1;
    this.mDestination = paramRgb2;
    this.mTransform = computeTransform(paramRgb1, paramRgb2, paramRenderIntent);
  }
  
  private static float[] computeTransform(ColorSpace.Rgb paramRgb1, ColorSpace.Rgb paramRgb2, ColorSpace.RenderIntent paramRenderIntent) {
    if (ColorSpace.access$1700(ColorSpace.Rgb.access$000(paramRgb1), ColorSpace.Rgb.access$000(paramRgb2)))
      return ColorSpace.mul3x3(ColorSpace.Rgb.access$2700(paramRgb2), ColorSpace.Rgb.access$100(paramRgb1)); 
    float[] arrayOfFloat3 = ColorSpace.Rgb.access$100(paramRgb1);
    float[] arrayOfFloat4 = ColorSpace.Rgb.access$2700(paramRgb2);
    float[] arrayOfFloat5 = ColorSpace.access$2300(ColorSpace.Rgb.access$000(paramRgb1));
    float[] arrayOfFloat6 = ColorSpace.access$2300(ColorSpace.Rgb.access$000(paramRgb2));
    if (!ColorSpace.access$1700(ColorSpace.Rgb.access$000(paramRgb1), ColorSpace.ILLUMINANT_D50))
      arrayOfFloat3 = ColorSpace.mul3x3(ColorSpace.access$2800(ColorSpace.Adaptation.BRADFORD.mTransform, arrayOfFloat5, Arrays.copyOf(ColorSpace.access$1000(), 3)), ColorSpace.Rgb.access$100(paramRgb1)); 
    float[] arrayOfFloat1 = arrayOfFloat4;
    if (!ColorSpace.access$1700(ColorSpace.Rgb.access$000(paramRgb2), ColorSpace.ILLUMINANT_D50))
      arrayOfFloat1 = ColorSpace.access$1200(ColorSpace.mul3x3(ColorSpace.access$2800(ColorSpace.Adaptation.BRADFORD.mTransform, arrayOfFloat6, Arrays.copyOf(ColorSpace.access$1000(), 3)), ColorSpace.Rgb.access$100(paramRgb2))); 
    float[] arrayOfFloat2 = arrayOfFloat3;
    if (paramRenderIntent == ColorSpace.RenderIntent.ABSOLUTE)
      arrayOfFloat2 = ColorSpace.access$2900(new float[] { arrayOfFloat5[0] / arrayOfFloat6[0], arrayOfFloat5[1] / arrayOfFloat6[1], arrayOfFloat5[2] / arrayOfFloat6[2] }, arrayOfFloat3); 
    return ColorSpace.mul3x3(arrayOfFloat1, arrayOfFloat2);
  }
  
  public float[] transform(float[] paramArrayOffloat) {
    paramArrayOffloat[0] = (float)ColorSpace.Rgb.access$2500(this.mSource).applyAsDouble(paramArrayOffloat[0]);
    paramArrayOffloat[1] = (float)ColorSpace.Rgb.access$2500(this.mSource).applyAsDouble(paramArrayOffloat[1]);
    paramArrayOffloat[2] = (float)ColorSpace.Rgb.access$2500(this.mSource).applyAsDouble(paramArrayOffloat[2]);
    ColorSpace.access$1500(this.mTransform, paramArrayOffloat);
    paramArrayOffloat[0] = (float)ColorSpace.Rgb.access$2600(this.mDestination).applyAsDouble(paramArrayOffloat[0]);
    paramArrayOffloat[1] = (float)ColorSpace.Rgb.access$2600(this.mDestination).applyAsDouble(paramArrayOffloat[1]);
    paramArrayOffloat[2] = (float)ColorSpace.Rgb.access$2600(this.mDestination).applyAsDouble(paramArrayOffloat[2]);
    return paramArrayOffloat;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$Connector$Rgb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */