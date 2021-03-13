package android.animation;

import java.util.List;

public interface Keyframes extends Cloneable {
  Keyframes clone();
  
  List<Keyframe> getKeyframes();
  
  Class getType();
  
  Object getValue(float paramFloat);
  
  void setEvaluator(TypeEvaluator paramTypeEvaluator);
  
  public static interface FloatKeyframes extends Keyframes {
    float getFloatValue(float param1Float);
  }
  
  public static interface IntKeyframes extends Keyframes {
    int getIntValue(float param1Float);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/Keyframes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */