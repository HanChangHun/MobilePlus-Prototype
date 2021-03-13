package android.animation;

import java.util.List;

class FloatKeyframeSet extends KeyframeSet implements Keyframes.FloatKeyframes {
  public FloatKeyframeSet(Keyframe.FloatKeyframe... paramVarArgs) {
    super((Keyframe[])paramVarArgs);
  }
  
  public FloatKeyframeSet clone() {
    List<Keyframe> list = this.mKeyframes;
    int i = this.mKeyframes.size();
    Keyframe.FloatKeyframe[] arrayOfFloatKeyframe = new Keyframe.FloatKeyframe[i];
    for (byte b = 0; b < i; b++)
      arrayOfFloatKeyframe[b] = (Keyframe.FloatKeyframe)((Keyframe)list.get(b)).clone(); 
    return new FloatKeyframeSet(arrayOfFloatKeyframe);
  }
  
  public float getFloatValue(float paramFloat) {
    if (paramFloat <= 0.0F) {
      Keyframe.FloatKeyframe floatKeyframe1 = (Keyframe.FloatKeyframe)this.mKeyframes.get(0);
      Keyframe.FloatKeyframe floatKeyframe2 = (Keyframe.FloatKeyframe)this.mKeyframes.get(1);
      float f1 = floatKeyframe1.getFloatValue();
      float f2 = floatKeyframe2.getFloatValue();
      float f3 = floatKeyframe1.getFraction();
      float f4 = floatKeyframe2.getFraction();
      TimeInterpolator timeInterpolator = floatKeyframe2.getInterpolator();
      float f5 = paramFloat;
      if (timeInterpolator != null)
        f5 = timeInterpolator.getInterpolation(paramFloat); 
      paramFloat = (f5 - f3) / (f4 - f3);
      if (this.mEvaluator == null) {
        paramFloat = (f2 - f1) * paramFloat + f1;
      } else {
        paramFloat = ((Number)this.mEvaluator.evaluate(paramFloat, Float.valueOf(f1), Float.valueOf(f2))).floatValue();
      } 
      return paramFloat;
    } 
    if (paramFloat >= 1.0F) {
      Keyframe.FloatKeyframe floatKeyframe1 = (Keyframe.FloatKeyframe)this.mKeyframes.get(this.mNumKeyframes - 2);
      Keyframe.FloatKeyframe floatKeyframe2 = (Keyframe.FloatKeyframe)this.mKeyframes.get(this.mNumKeyframes - 1);
      float f1 = floatKeyframe1.getFloatValue();
      float f2 = floatKeyframe2.getFloatValue();
      float f3 = floatKeyframe1.getFraction();
      float f4 = floatKeyframe2.getFraction();
      TimeInterpolator timeInterpolator = floatKeyframe2.getInterpolator();
      float f5 = paramFloat;
      if (timeInterpolator != null)
        f5 = timeInterpolator.getInterpolation(paramFloat); 
      paramFloat = (f5 - f3) / (f4 - f3);
      if (this.mEvaluator == null) {
        paramFloat = (f2 - f1) * paramFloat + f1;
      } else {
        paramFloat = ((Number)this.mEvaluator.evaluate(paramFloat, Float.valueOf(f1), Float.valueOf(f2))).floatValue();
      } 
      return paramFloat;
    } 
    Keyframe.FloatKeyframe floatKeyframe = (Keyframe.FloatKeyframe)this.mKeyframes.get(0);
    for (byte b = 1; b < this.mNumKeyframes; b++) {
      Keyframe.FloatKeyframe floatKeyframe1 = (Keyframe.FloatKeyframe)this.mKeyframes.get(b);
      if (paramFloat < floatKeyframe1.getFraction()) {
        TimeInterpolator timeInterpolator = floatKeyframe1.getInterpolator();
        float f3 = (paramFloat - floatKeyframe.getFraction()) / (floatKeyframe1.getFraction() - floatKeyframe.getFraction());
        float f2 = floatKeyframe.getFloatValue();
        float f1 = floatKeyframe1.getFloatValue();
        paramFloat = f3;
        if (timeInterpolator != null)
          paramFloat = timeInterpolator.getInterpolation(f3); 
        if (this.mEvaluator == null) {
          paramFloat = (f1 - f2) * paramFloat + f2;
        } else {
          paramFloat = ((Number)this.mEvaluator.evaluate(paramFloat, Float.valueOf(f2), Float.valueOf(f1))).floatValue();
        } 
        return paramFloat;
      } 
      floatKeyframe = floatKeyframe1;
    } 
    return ((Number)((Keyframe)this.mKeyframes.get(this.mNumKeyframes - 1)).getValue()).floatValue();
  }
  
  public Class getType() {
    return Float.class;
  }
  
  public Object getValue(float paramFloat) {
    return Float.valueOf(getFloatValue(paramFloat));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/FloatKeyframeSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */