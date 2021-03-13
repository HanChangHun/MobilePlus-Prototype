package android.animation;

public class FloatArrayEvaluator implements TypeEvaluator<float[]> {
  private float[] mArray;
  
  public FloatArrayEvaluator() {}
  
  public FloatArrayEvaluator(float[] paramArrayOffloat) {
    this.mArray = paramArrayOffloat;
  }
  
  public float[] evaluate(float paramFloat, float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    float[] arrayOfFloat1 = this.mArray;
    float[] arrayOfFloat2 = arrayOfFloat1;
    if (arrayOfFloat1 == null)
      arrayOfFloat2 = new float[paramArrayOffloat1.length]; 
    for (byte b = 0; b < arrayOfFloat2.length; b++) {
      float f = paramArrayOffloat1[b];
      arrayOfFloat2[b] = (paramArrayOffloat2[b] - f) * paramFloat + f;
    } 
    return arrayOfFloat2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/FloatArrayEvaluator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */