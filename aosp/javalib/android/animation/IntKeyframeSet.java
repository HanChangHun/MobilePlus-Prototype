package android.animation;

import java.util.List;

class IntKeyframeSet extends KeyframeSet implements Keyframes.IntKeyframes {
  public IntKeyframeSet(Keyframe.IntKeyframe... paramVarArgs) {
    super((Keyframe[])paramVarArgs);
  }
  
  public IntKeyframeSet clone() {
    List<Keyframe> list = this.mKeyframes;
    int i = this.mKeyframes.size();
    Keyframe.IntKeyframe[] arrayOfIntKeyframe = new Keyframe.IntKeyframe[i];
    for (byte b = 0; b < i; b++)
      arrayOfIntKeyframe[b] = (Keyframe.IntKeyframe)((Keyframe)list.get(b)).clone(); 
    return new IntKeyframeSet(arrayOfIntKeyframe);
  }
  
  public int getIntValue(float paramFloat) {
    if (paramFloat <= 0.0F) {
      Keyframe.IntKeyframe intKeyframe1 = (Keyframe.IntKeyframe)this.mKeyframes.get(0);
      Keyframe.IntKeyframe intKeyframe2 = (Keyframe.IntKeyframe)this.mKeyframes.get(1);
      int j = intKeyframe1.getIntValue();
      int k = intKeyframe2.getIntValue();
      float f1 = intKeyframe1.getFraction();
      float f2 = intKeyframe2.getFraction();
      TimeInterpolator timeInterpolator = intKeyframe2.getInterpolator();
      float f3 = paramFloat;
      if (timeInterpolator != null)
        f3 = timeInterpolator.getInterpolation(paramFloat); 
      paramFloat = (f3 - f1) / (f2 - f1);
      if (this.mEvaluator == null) {
        j = (int)((k - j) * paramFloat) + j;
      } else {
        j = ((Number)this.mEvaluator.evaluate(paramFloat, Integer.valueOf(j), Integer.valueOf(k))).intValue();
      } 
      return j;
    } 
    if (paramFloat >= 1.0F) {
      Keyframe.IntKeyframe intKeyframe2 = (Keyframe.IntKeyframe)this.mKeyframes.get(this.mNumKeyframes - 2);
      Keyframe.IntKeyframe intKeyframe1 = (Keyframe.IntKeyframe)this.mKeyframes.get(this.mNumKeyframes - 1);
      int j = intKeyframe2.getIntValue();
      int k = intKeyframe1.getIntValue();
      float f1 = intKeyframe2.getFraction();
      float f2 = intKeyframe1.getFraction();
      TimeInterpolator timeInterpolator = intKeyframe1.getInterpolator();
      float f3 = paramFloat;
      if (timeInterpolator != null)
        f3 = timeInterpolator.getInterpolation(paramFloat); 
      paramFloat = (f3 - f1) / (f2 - f1);
      if (this.mEvaluator == null) {
        j = (int)((k - j) * paramFloat) + j;
      } else {
        j = ((Number)this.mEvaluator.evaluate(paramFloat, Integer.valueOf(j), Integer.valueOf(k))).intValue();
      } 
      return j;
    } 
    Keyframe.IntKeyframe intKeyframe = (Keyframe.IntKeyframe)this.mKeyframes.get(0);
    for (int i = 1; i < this.mNumKeyframes; i++) {
      Keyframe.IntKeyframe intKeyframe1 = (Keyframe.IntKeyframe)this.mKeyframes.get(i);
      if (paramFloat < intKeyframe1.getFraction()) {
        TimeInterpolator timeInterpolator = intKeyframe1.getInterpolator();
        float f = (paramFloat - intKeyframe.getFraction()) / (intKeyframe1.getFraction() - intKeyframe.getFraction());
        i = intKeyframe.getIntValue();
        int j = intKeyframe1.getIntValue();
        paramFloat = f;
        if (timeInterpolator != null)
          paramFloat = timeInterpolator.getInterpolation(f); 
        if (this.mEvaluator == null) {
          i = (int)((j - i) * paramFloat) + i;
        } else {
          i = ((Number)this.mEvaluator.evaluate(paramFloat, Integer.valueOf(i), Integer.valueOf(j))).intValue();
        } 
        return i;
      } 
      intKeyframe = intKeyframe1;
    } 
    return ((Number)((Keyframe)this.mKeyframes.get(this.mNumKeyframes - 1)).getValue()).intValue();
  }
  
  public Class getType() {
    return Integer.class;
  }
  
  public Object getValue(float paramFloat) {
    return Integer.valueOf(getIntValue(paramFloat));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/IntKeyframeSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */