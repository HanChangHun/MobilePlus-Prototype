package android.animation;

import java.util.ArrayList;
import java.util.List;

abstract class SimpleKeyframes implements Keyframes {
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
    return PathKeyframes.access$000();
  }
  
  public void setEvaluator(TypeEvaluator paramTypeEvaluator) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PathKeyframes$SimpleKeyframes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */