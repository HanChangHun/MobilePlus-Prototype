package android.graphics;

import java.util.Arrays;

public class Connector {
  private final ColorSpace mDestination;
  
  private final ColorSpace.RenderIntent mIntent;
  
  private final ColorSpace mSource;
  
  private final float[] mTransform;
  
  private final ColorSpace mTransformDestination;
  
  private final ColorSpace mTransformSource;
  
  Connector(ColorSpace paramColorSpace1, ColorSpace paramColorSpace2, ColorSpace.RenderIntent paramRenderIntent) {
    this(paramColorSpace1, paramColorSpace2, colorSpace1, colorSpace2, paramRenderIntent, computeTransform(paramColorSpace1, paramColorSpace2, paramRenderIntent));
  }
  
  private Connector(ColorSpace paramColorSpace1, ColorSpace paramColorSpace2, ColorSpace paramColorSpace3, ColorSpace paramColorSpace4, ColorSpace.RenderIntent paramRenderIntent, float[] paramArrayOffloat) {
    this.mSource = paramColorSpace1;
    this.mDestination = paramColorSpace2;
    this.mTransformSource = paramColorSpace3;
    this.mTransformDestination = paramColorSpace4;
    this.mIntent = paramRenderIntent;
    this.mTransform = paramArrayOffloat;
  }
  
  private static float[] computeTransform(ColorSpace paramColorSpace1, ColorSpace paramColorSpace2, ColorSpace.RenderIntent paramRenderIntent) {
    boolean bool1;
    boolean bool2;
    if (paramRenderIntent != ColorSpace.RenderIntent.ABSOLUTE)
      return null; 
    if (paramColorSpace1.getModel() == ColorSpace.Model.RGB) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramColorSpace2.getModel() == ColorSpace.Model.RGB) {
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
        paramColorSpace1 = paramColorSpace2; 
      paramColorSpace2 = paramColorSpace1;
      if (bool1) {
        arrayOfFloat1 = ColorSpace.access$2300(ColorSpace.Rgb.access$000((ColorSpace.Rgb)paramColorSpace2));
      } else {
        arrayOfFloat1 = ColorSpace.access$1000();
      } 
      if (bool2) {
        arrayOfFloat2 = ColorSpace.access$2300(ColorSpace.Rgb.access$000((ColorSpace.Rgb)paramColorSpace2));
      } else {
        arrayOfFloat2 = ColorSpace.access$1000();
      } 
      return new float[] { arrayOfFloat1[0] / arrayOfFloat2[0], arrayOfFloat1[1] / arrayOfFloat2[1], arrayOfFloat1[2] / arrayOfFloat2[2] };
    } 
    return null;
  }
  
  static Connector identity(ColorSpace paramColorSpace) {
    return new Connector(paramColorSpace, paramColorSpace, ColorSpace.RenderIntent.RELATIVE) {
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
  
  public float[] transform(float paramFloat1, float paramFloat2, float paramFloat3) {
    return transform(new float[] { paramFloat1, paramFloat2, paramFloat3 });
  }
  
  public float[] transform(float[] paramArrayOffloat) {
    float[] arrayOfFloat = this.mTransformSource.toXyz(paramArrayOffloat);
    paramArrayOffloat = this.mTransform;
    if (paramArrayOffloat != null) {
      arrayOfFloat[0] = arrayOfFloat[0] * paramArrayOffloat[0];
      arrayOfFloat[1] = arrayOfFloat[1] * paramArrayOffloat[1];
      arrayOfFloat[2] = arrayOfFloat[2] * paramArrayOffloat[2];
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
      if (ColorSpace.access$1700(ColorSpace.Rgb.access$000(param2Rgb1), ColorSpace.Rgb.access$000(param2Rgb2)))
        return ColorSpace.mul3x3(ColorSpace.Rgb.access$2700(param2Rgb2), ColorSpace.Rgb.access$100(param2Rgb1)); 
      float[] arrayOfFloat3 = ColorSpace.Rgb.access$100(param2Rgb1);
      float[] arrayOfFloat4 = ColorSpace.Rgb.access$2700(param2Rgb2);
      float[] arrayOfFloat5 = ColorSpace.access$2300(ColorSpace.Rgb.access$000(param2Rgb1));
      float[] arrayOfFloat6 = ColorSpace.access$2300(ColorSpace.Rgb.access$000(param2Rgb2));
      if (!ColorSpace.access$1700(ColorSpace.Rgb.access$000(param2Rgb1), ColorSpace.ILLUMINANT_D50))
        arrayOfFloat3 = ColorSpace.mul3x3(ColorSpace.access$2800(ColorSpace.Adaptation.BRADFORD.mTransform, arrayOfFloat5, Arrays.copyOf(ColorSpace.access$1000(), 3)), ColorSpace.Rgb.access$100(param2Rgb1)); 
      float[] arrayOfFloat1 = arrayOfFloat4;
      if (!ColorSpace.access$1700(ColorSpace.Rgb.access$000(param2Rgb2), ColorSpace.ILLUMINANT_D50))
        arrayOfFloat1 = ColorSpace.access$1200(ColorSpace.mul3x3(ColorSpace.access$2800(ColorSpace.Adaptation.BRADFORD.mTransform, arrayOfFloat6, Arrays.copyOf(ColorSpace.access$1000(), 3)), ColorSpace.Rgb.access$100(param2Rgb2))); 
      float[] arrayOfFloat2 = arrayOfFloat3;
      if (param2RenderIntent == ColorSpace.RenderIntent.ABSOLUTE)
        arrayOfFloat2 = ColorSpace.access$2900(new float[] { arrayOfFloat5[0] / arrayOfFloat6[0], arrayOfFloat5[1] / arrayOfFloat6[1], arrayOfFloat5[2] / arrayOfFloat6[2] }, arrayOfFloat3); 
      return ColorSpace.mul3x3(arrayOfFloat1, arrayOfFloat2);
    }
    
    public float[] transform(float[] param2ArrayOffloat) {
      param2ArrayOffloat[0] = (float)ColorSpace.Rgb.access$2500(this.mSource).applyAsDouble(param2ArrayOffloat[0]);
      param2ArrayOffloat[1] = (float)ColorSpace.Rgb.access$2500(this.mSource).applyAsDouble(param2ArrayOffloat[1]);
      param2ArrayOffloat[2] = (float)ColorSpace.Rgb.access$2500(this.mSource).applyAsDouble(param2ArrayOffloat[2]);
      ColorSpace.access$1500(this.mTransform, param2ArrayOffloat);
      param2ArrayOffloat[0] = (float)ColorSpace.Rgb.access$2600(this.mDestination).applyAsDouble(param2ArrayOffloat[0]);
      param2ArrayOffloat[1] = (float)ColorSpace.Rgb.access$2600(this.mDestination).applyAsDouble(param2ArrayOffloat[1]);
      param2ArrayOffloat[2] = (float)ColorSpace.Rgb.access$2600(this.mDestination).applyAsDouble(param2ArrayOffloat[2]);
      return param2ArrayOffloat;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$Connector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */