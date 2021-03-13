package android.animation;

public class IntArrayEvaluator implements TypeEvaluator<int[]> {
  private int[] mArray;
  
  public IntArrayEvaluator() {}
  
  public IntArrayEvaluator(int[] paramArrayOfint) {
    this.mArray = paramArrayOfint;
  }
  
  public int[] evaluate(float paramFloat, int[] paramArrayOfint1, int[] paramArrayOfint2) {
    int[] arrayOfInt1 = this.mArray;
    int[] arrayOfInt2 = arrayOfInt1;
    if (arrayOfInt1 == null)
      arrayOfInt2 = new int[paramArrayOfint1.length]; 
    for (byte b = 0; b < arrayOfInt2.length; b++) {
      int i = paramArrayOfint1[b];
      int j = paramArrayOfint2[b];
      arrayOfInt2[b] = (int)(i + (j - i) * paramFloat);
    } 
    return arrayOfInt2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/IntArrayEvaluator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */