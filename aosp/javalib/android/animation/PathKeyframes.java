package android.animation;

import android.graphics.Path;
import android.graphics.PointF;
import java.util.ArrayList;
import java.util.List;

public class PathKeyframes implements Keyframes {
  private static final ArrayList<Keyframe> EMPTY_KEYFRAMES = new ArrayList<>();
  
  private static final int FRACTION_OFFSET = 0;
  
  private static final int NUM_COMPONENTS = 3;
  
  private static final int X_OFFSET = 1;
  
  private static final int Y_OFFSET = 2;
  
  private float[] mKeyframeData;
  
  private PointF mTempPointF = new PointF();
  
  public PathKeyframes(Path paramPath) {
    this(paramPath, 0.5F);
  }
  
  public PathKeyframes(Path paramPath, float paramFloat) {
    if (paramPath != null && !paramPath.isEmpty()) {
      this.mKeyframeData = paramPath.approximate(paramFloat);
      return;
    } 
    throw new IllegalArgumentException("The path must not be null or empty");
  }
  
  private static float interpolate(float paramFloat1, float paramFloat2, float paramFloat3) {
    return (paramFloat3 - paramFloat2) * paramFloat1 + paramFloat2;
  }
  
  private PointF interpolateInRange(float paramFloat, int paramInt1, int paramInt2) {
    paramInt1 *= 3;
    paramInt2 *= 3;
    float[] arrayOfFloat = this.mKeyframeData;
    float f1 = arrayOfFloat[paramInt1 + 0];
    f1 = (paramFloat - f1) / (arrayOfFloat[paramInt2 + 0] - f1);
    float f2 = arrayOfFloat[paramInt1 + 1];
    float f3 = arrayOfFloat[paramInt2 + 1];
    float f4 = arrayOfFloat[paramInt1 + 2];
    paramFloat = arrayOfFloat[paramInt2 + 2];
    f3 = interpolate(f1, f2, f3);
    paramFloat = interpolate(f1, f4, paramFloat);
    this.mTempPointF.set(f3, paramFloat);
    return this.mTempPointF;
  }
  
  private PointF pointForIndex(int paramInt) {
    paramInt *= 3;
    PointF pointF = this.mTempPointF;
    float[] arrayOfFloat = this.mKeyframeData;
    pointF.set(arrayOfFloat[paramInt + 1], arrayOfFloat[paramInt + 2]);
    return this.mTempPointF;
  }
  
  public Keyframes clone() {
    Keyframes keyframes = null;
    try {
      Keyframes keyframes1 = (Keyframes)super.clone();
      keyframes = keyframes1;
    } catch (CloneNotSupportedException cloneNotSupportedException) {}
    return keyframes;
  }
  
  public Keyframes.FloatKeyframes createXFloatKeyframes() {
    return new FloatKeyframesBase() {
        public float getFloatValue(float param1Float) {
          return ((PointF)PathKeyframes.this.getValue(param1Float)).x;
        }
      };
  }
  
  public Keyframes.IntKeyframes createXIntKeyframes() {
    return new IntKeyframesBase() {
        public int getIntValue(float param1Float) {
          return Math.round(((PointF)PathKeyframes.this.getValue(param1Float)).x);
        }
      };
  }
  
  public Keyframes.FloatKeyframes createYFloatKeyframes() {
    return new FloatKeyframesBase() {
        public float getFloatValue(float param1Float) {
          return ((PointF)PathKeyframes.this.getValue(param1Float)).y;
        }
      };
  }
  
  public Keyframes.IntKeyframes createYIntKeyframes() {
    return new IntKeyframesBase() {
        public int getIntValue(float param1Float) {
          return Math.round(((PointF)PathKeyframes.this.getValue(param1Float)).y);
        }
      };
  }
  
  public ArrayList<Keyframe> getKeyframes() {
    return EMPTY_KEYFRAMES;
  }
  
  public Class getType() {
    return PointF.class;
  }
  
  public Object getValue(float paramFloat) {
    int i = this.mKeyframeData.length / 3;
    if (paramFloat < 0.0F)
      return interpolateInRange(paramFloat, 0, 1); 
    if (paramFloat > 1.0F)
      return interpolateInRange(paramFloat, i - 2, i - 1); 
    if (paramFloat == 0.0F)
      return pointForIndex(0); 
    if (paramFloat == 1.0F)
      return pointForIndex(i - 1); 
    int j = 0;
    while (j <= --i) {
      int k = (j + i) / 2;
      float f = this.mKeyframeData[k * 3 + 0];
      if (paramFloat < f) {
        i = k - 1;
        continue;
      } 
      if (paramFloat > f) {
        j = k + 1;
        continue;
      } 
      return pointForIndex(k);
    } 
    return interpolateInRange(paramFloat, i, j);
  }
  
  public void setEvaluator(TypeEvaluator paramTypeEvaluator) {}
  
  static abstract class FloatKeyframesBase extends SimpleKeyframes implements Keyframes.FloatKeyframes {
    public Class getType() {
      return Float.class;
    }
    
    public Object getValue(float param1Float) {
      return Float.valueOf(getFloatValue(param1Float));
    }
  }
  
  static abstract class IntKeyframesBase extends SimpleKeyframes implements Keyframes.IntKeyframes {
    public Class getType() {
      return Integer.class;
    }
    
    public Object getValue(float param1Float) {
      return Integer.valueOf(getIntValue(param1Float));
    }
  }
  
  private static abstract class SimpleKeyframes implements Keyframes {
    private SimpleKeyframes() {}
    
    public Keyframes clone() {
      Keyframes keyframes = null;
      try {
        Keyframes keyframes1 = (Keyframes)super.clone();
        keyframes = keyframes1;
      } catch (CloneNotSupportedException cloneNotSupportedException) {}
      return keyframes;
    }
    
    public ArrayList<Keyframe> getKeyframes() {
      return PathKeyframes.EMPTY_KEYFRAMES;
    }
    
    public void setEvaluator(TypeEvaluator param1TypeEvaluator) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PathKeyframes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */