package android.animation;

public class FloatEvaluator implements TypeEvaluator<Number> {
  public Float evaluate(float paramFloat, Number paramNumber1, Number paramNumber2) {
    float f = paramNumber1.floatValue();
    return Float.valueOf((paramNumber2.floatValue() - f) * paramFloat + f);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/FloatEvaluator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */