package android.animation;

import android.graphics.Path;
import android.util.Log;
import java.util.Arrays;
import java.util.List;

public class KeyframeSet implements Keyframes {
  TypeEvaluator mEvaluator;
  
  Keyframe mFirstKeyframe;
  
  TimeInterpolator mInterpolator;
  
  List<Keyframe> mKeyframes;
  
  Keyframe mLastKeyframe;
  
  int mNumKeyframes;
  
  public KeyframeSet(Keyframe... paramVarArgs) {
    this.mNumKeyframes = paramVarArgs.length;
    this.mKeyframes = Arrays.asList(paramVarArgs);
    this.mFirstKeyframe = paramVarArgs[0];
    Keyframe keyframe = paramVarArgs[this.mNumKeyframes - 1];
    this.mLastKeyframe = keyframe;
    this.mInterpolator = keyframe.getInterpolator();
  }
  
  public static KeyframeSet ofFloat(float... paramVarArgs) {
    boolean bool1 = false;
    boolean bool2 = false;
    int i = paramVarArgs.length;
    Keyframe.FloatKeyframe[] arrayOfFloatKeyframe = new Keyframe.FloatKeyframe[Math.max(i, 2)];
    if (i == 1) {
      arrayOfFloatKeyframe[0] = (Keyframe.FloatKeyframe)Keyframe.ofFloat(0.0F);
      arrayOfFloatKeyframe[1] = (Keyframe.FloatKeyframe)Keyframe.ofFloat(1.0F, paramVarArgs[0]);
      if (Float.isNaN(paramVarArgs[0]))
        bool1 = true; 
    } else {
      arrayOfFloatKeyframe[0] = (Keyframe.FloatKeyframe)Keyframe.ofFloat(0.0F, paramVarArgs[0]);
      byte b = 1;
      while (true) {
        bool1 = bool2;
        if (b < i) {
          arrayOfFloatKeyframe[b] = (Keyframe.FloatKeyframe)Keyframe.ofFloat(b / (i - 1), paramVarArgs[b]);
          if (Float.isNaN(paramVarArgs[b]))
            bool2 = true; 
          b++;
          continue;
        } 
        break;
      } 
    } 
    if (bool1)
      Log.w("Animator", "Bad value (NaN) in float animator"); 
    return new FloatKeyframeSet(arrayOfFloatKeyframe);
  }
  
  public static KeyframeSet ofInt(int... paramVarArgs) {
    int i = paramVarArgs.length;
    Keyframe.IntKeyframe[] arrayOfIntKeyframe = new Keyframe.IntKeyframe[Math.max(i, 2)];
    if (i == 1) {
      arrayOfIntKeyframe[0] = (Keyframe.IntKeyframe)Keyframe.ofInt(0.0F);
      arrayOfIntKeyframe[1] = (Keyframe.IntKeyframe)Keyframe.ofInt(1.0F, paramVarArgs[0]);
    } else {
      arrayOfIntKeyframe[0] = (Keyframe.IntKeyframe)Keyframe.ofInt(0.0F, paramVarArgs[0]);
      for (byte b = 1; b < i; b++)
        arrayOfIntKeyframe[b] = (Keyframe.IntKeyframe)Keyframe.ofInt(b / (i - 1), paramVarArgs[b]); 
    } 
    return new IntKeyframeSet(arrayOfIntKeyframe);
  }
  
  public static KeyframeSet ofKeyframe(Keyframe... paramVarArgs) {
    int i = paramVarArgs.length;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    byte b;
    for (b = 0; b < i; b++) {
      if (paramVarArgs[b] instanceof Keyframe.FloatKeyframe) {
        bool1 = true;
      } else if (paramVarArgs[b] instanceof Keyframe.IntKeyframe) {
        bool2 = true;
      } else {
        bool3 = true;
      } 
    } 
    if (bool1 && !bool2 && !bool3) {
      Keyframe.FloatKeyframe[] arrayOfFloatKeyframe = new Keyframe.FloatKeyframe[i];
      for (b = 0; b < i; b++)
        arrayOfFloatKeyframe[b] = (Keyframe.FloatKeyframe)paramVarArgs[b]; 
      return new FloatKeyframeSet(arrayOfFloatKeyframe);
    } 
    if (bool2 && !bool1 && !bool3) {
      Keyframe.IntKeyframe[] arrayOfIntKeyframe = new Keyframe.IntKeyframe[i];
      for (b = 0; b < i; b++)
        arrayOfIntKeyframe[b] = (Keyframe.IntKeyframe)paramVarArgs[b]; 
      return new IntKeyframeSet(arrayOfIntKeyframe);
    } 
    return new KeyframeSet(paramVarArgs);
  }
  
  public static KeyframeSet ofObject(Object... paramVarArgs) {
    int i = paramVarArgs.length;
    Keyframe.ObjectKeyframe[] arrayOfObjectKeyframe = new Keyframe.ObjectKeyframe[Math.max(i, 2)];
    if (i == 1) {
      arrayOfObjectKeyframe[0] = (Keyframe.ObjectKeyframe)Keyframe.ofObject(0.0F);
      arrayOfObjectKeyframe[1] = (Keyframe.ObjectKeyframe)Keyframe.ofObject(1.0F, paramVarArgs[0]);
    } else {
      arrayOfObjectKeyframe[0] = (Keyframe.ObjectKeyframe)Keyframe.ofObject(0.0F, paramVarArgs[0]);
      for (byte b = 1; b < i; b++)
        arrayOfObjectKeyframe[b] = (Keyframe.ObjectKeyframe)Keyframe.ofObject(b / (i - 1), paramVarArgs[b]); 
    } 
    return new KeyframeSet((Keyframe[])arrayOfObjectKeyframe);
  }
  
  public static PathKeyframes ofPath(Path paramPath) {
    return new PathKeyframes(paramPath);
  }
  
  public static PathKeyframes ofPath(Path paramPath, float paramFloat) {
    return new PathKeyframes(paramPath, paramFloat);
  }
  
  public KeyframeSet clone() {
    List<Keyframe> list = this.mKeyframes;
    int i = this.mKeyframes.size();
    Keyframe[] arrayOfKeyframe = new Keyframe[i];
    for (byte b = 0; b < i; b++)
      arrayOfKeyframe[b] = ((Keyframe)list.get(b)).clone(); 
    return new KeyframeSet(arrayOfKeyframe);
  }
  
  public List<Keyframe> getKeyframes() {
    return this.mKeyframes;
  }
  
  public Class getType() {
    return this.mFirstKeyframe.getType();
  }
  
  public Object getValue(float paramFloat) {
    int i = this.mNumKeyframes;
    if (i == 2) {
      TimeInterpolator timeInterpolator = this.mInterpolator;
      float f = paramFloat;
      if (timeInterpolator != null)
        f = timeInterpolator.getInterpolation(paramFloat); 
      return this.mEvaluator.evaluate(f, this.mFirstKeyframe.getValue(), this.mLastKeyframe.getValue());
    } 
    if (paramFloat <= 0.0F) {
      Keyframe keyframe1 = this.mKeyframes.get(1);
      TimeInterpolator timeInterpolator = keyframe1.getInterpolator();
      float f = paramFloat;
      if (timeInterpolator != null)
        f = timeInterpolator.getInterpolation(paramFloat); 
      paramFloat = this.mFirstKeyframe.getFraction();
      paramFloat = (f - paramFloat) / (keyframe1.getFraction() - paramFloat);
      return this.mEvaluator.evaluate(paramFloat, this.mFirstKeyframe.getValue(), keyframe1.getValue());
    } 
    if (paramFloat >= 1.0F) {
      Keyframe keyframe1 = this.mKeyframes.get(i - 2);
      TimeInterpolator timeInterpolator = this.mLastKeyframe.getInterpolator();
      float f = paramFloat;
      if (timeInterpolator != null)
        f = timeInterpolator.getInterpolation(paramFloat); 
      paramFloat = keyframe1.getFraction();
      paramFloat = (f - paramFloat) / (this.mLastKeyframe.getFraction() - paramFloat);
      return this.mEvaluator.evaluate(paramFloat, keyframe1.getValue(), this.mLastKeyframe.getValue());
    } 
    Keyframe keyframe = this.mFirstKeyframe;
    for (i = 1; i < this.mNumKeyframes; i++) {
      Keyframe keyframe1 = this.mKeyframes.get(i);
      if (paramFloat < keyframe1.getFraction()) {
        TimeInterpolator timeInterpolator = keyframe1.getInterpolator();
        float f = keyframe.getFraction();
        f = (paramFloat - f) / (keyframe1.getFraction() - f);
        paramFloat = f;
        if (timeInterpolator != null)
          paramFloat = timeInterpolator.getInterpolation(f); 
        return this.mEvaluator.evaluate(paramFloat, keyframe.getValue(), keyframe1.getValue());
      } 
      keyframe = keyframe1;
    } 
    return this.mLastKeyframe.getValue();
  }
  
  public void setEvaluator(TypeEvaluator paramTypeEvaluator) {
    this.mEvaluator = paramTypeEvaluator;
  }
  
  public String toString() {
    String str = " ";
    for (byte b = 0; b < this.mNumKeyframes; b++) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(((Keyframe)this.mKeyframes.get(b)).getValue());
      stringBuilder.append("  ");
      str = stringBuilder.toString();
    } 
    return str;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/KeyframeSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */