package android.animation;

public class IntEvaluator implements TypeEvaluator<Integer> {
  public Integer evaluate(float paramFloat, Integer paramInteger1, Integer paramInteger2) {
    int i = paramInteger1.intValue();
    return Integer.valueOf((int)(i + (paramInteger2.intValue() - i) * paramFloat));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/IntEvaluator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */