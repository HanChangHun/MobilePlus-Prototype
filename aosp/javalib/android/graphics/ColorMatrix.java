package android.graphics;

import java.util.Arrays;

public class ColorMatrix {
  private final float[] mArray;
  
  public ColorMatrix() {
    this.mArray = new float[20];
    reset();
  }
  
  public ColorMatrix(ColorMatrix paramColorMatrix) {
    float[] arrayOfFloat = new float[20];
    this.mArray = arrayOfFloat;
    System.arraycopy(paramColorMatrix.mArray, 0, arrayOfFloat, 0, 20);
  }
  
  public ColorMatrix(float[] paramArrayOffloat) {
    float[] arrayOfFloat = new float[20];
    this.mArray = arrayOfFloat;
    System.arraycopy(paramArrayOffloat, 0, arrayOfFloat, 0, 20);
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof ColorMatrix))
      return false; 
    paramObject = ((ColorMatrix)paramObject).mArray;
    for (byte b = 0; b < 20; b++) {
      if (paramObject[b] != this.mArray[b])
        return false; 
    } 
    return true;
  }
  
  public final float[] getArray() {
    return this.mArray;
  }
  
  public void postConcat(ColorMatrix paramColorMatrix) {
    setConcat(paramColorMatrix, this);
  }
  
  public void preConcat(ColorMatrix paramColorMatrix) {
    setConcat(this, paramColorMatrix);
  }
  
  public void reset() {
    float[] arrayOfFloat = this.mArray;
    Arrays.fill(arrayOfFloat, 0.0F);
    arrayOfFloat[18] = 1.0F;
    arrayOfFloat[12] = 1.0F;
    arrayOfFloat[6] = 1.0F;
    arrayOfFloat[0] = 1.0F;
  }
  
  public void set(ColorMatrix paramColorMatrix) {
    System.arraycopy(paramColorMatrix.mArray, 0, this.mArray, 0, 20);
  }
  
  public void set(float[] paramArrayOffloat) {
    System.arraycopy(paramArrayOffloat, 0, this.mArray, 0, 20);
  }
  
  public void setConcat(ColorMatrix paramColorMatrix1, ColorMatrix paramColorMatrix2) {
    float[] arrayOfFloat3;
    if (paramColorMatrix1 == this || paramColorMatrix2 == this) {
      arrayOfFloat3 = new float[20];
    } else {
      arrayOfFloat3 = this.mArray;
    } 
    float[] arrayOfFloat1 = paramColorMatrix1.mArray;
    float[] arrayOfFloat2 = paramColorMatrix2.mArray;
    byte b1 = 0;
    byte b2 = 0;
    while (b2 < 20) {
      byte b = 0;
      while (b < 4) {
        arrayOfFloat3[b1] = arrayOfFloat1[b2 + 0] * arrayOfFloat2[b + 0] + arrayOfFloat1[b2 + 1] * arrayOfFloat2[b + 5] + arrayOfFloat1[b2 + 2] * arrayOfFloat2[b + 10] + arrayOfFloat1[b2 + 3] * arrayOfFloat2[b + 15];
        b++;
        b1++;
      } 
      arrayOfFloat3[b1] = arrayOfFloat1[b2 + 0] * arrayOfFloat2[4] + arrayOfFloat1[b2 + 1] * arrayOfFloat2[9] + arrayOfFloat1[b2 + 2] * arrayOfFloat2[14] + arrayOfFloat1[b2 + 3] * arrayOfFloat2[19] + arrayOfFloat1[b2 + 4];
      b2 += 5;
      b1++;
    } 
    arrayOfFloat1 = this.mArray;
    if (arrayOfFloat3 != arrayOfFloat1)
      System.arraycopy(arrayOfFloat3, 0, arrayOfFloat1, 0, 20); 
  }
  
  public void setRGB2YUV() {
    reset();
    float[] arrayOfFloat = this.mArray;
    arrayOfFloat[0] = 0.299F;
    arrayOfFloat[1] = 0.587F;
    arrayOfFloat[2] = 0.114F;
    arrayOfFloat[5] = -0.16874F;
    arrayOfFloat[6] = -0.33126F;
    arrayOfFloat[7] = 0.5F;
    arrayOfFloat[10] = 0.5F;
    arrayOfFloat[11] = -0.41869F;
    arrayOfFloat[12] = -0.08131F;
  }
  
  public void setRotate(int paramInt, float paramFloat) {
    reset();
    double d = paramFloat * Math.PI / 180.0D;
    float f = (float)Math.cos(d);
    paramFloat = (float)Math.sin(d);
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt == 2) {
          float[] arrayOfFloat = this.mArray;
          arrayOfFloat[6] = f;
          arrayOfFloat[0] = f;
          arrayOfFloat[1] = paramFloat;
          arrayOfFloat[5] = -paramFloat;
        } else {
          throw new RuntimeException();
        } 
      } else {
        float[] arrayOfFloat = this.mArray;
        arrayOfFloat[12] = f;
        arrayOfFloat[0] = f;
        arrayOfFloat[2] = -paramFloat;
        arrayOfFloat[10] = paramFloat;
      } 
    } else {
      float[] arrayOfFloat = this.mArray;
      arrayOfFloat[12] = f;
      arrayOfFloat[6] = f;
      arrayOfFloat[7] = paramFloat;
      arrayOfFloat[11] = -paramFloat;
    } 
  }
  
  public void setSaturation(float paramFloat) {
    reset();
    float[] arrayOfFloat = this.mArray;
    float f1 = 1.0F - paramFloat;
    float f2 = 0.213F * f1;
    float f3 = 0.715F * f1;
    f1 = 0.072F * f1;
    arrayOfFloat[0] = f2 + paramFloat;
    arrayOfFloat[1] = f3;
    arrayOfFloat[2] = f1;
    arrayOfFloat[5] = f2;
    arrayOfFloat[6] = f3 + paramFloat;
    arrayOfFloat[7] = f1;
    arrayOfFloat[10] = f2;
    arrayOfFloat[11] = f3;
    arrayOfFloat[12] = f1 + paramFloat;
  }
  
  public void setScale(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    float[] arrayOfFloat = this.mArray;
    for (byte b = 19; b > 0; b--)
      arrayOfFloat[b] = 0.0F; 
    arrayOfFloat[0] = paramFloat1;
    arrayOfFloat[6] = paramFloat2;
    arrayOfFloat[12] = paramFloat3;
    arrayOfFloat[18] = paramFloat4;
  }
  
  public void setYUV2RGB() {
    reset();
    float[] arrayOfFloat = this.mArray;
    arrayOfFloat[2] = 1.402F;
    arrayOfFloat[5] = 1.0F;
    arrayOfFloat[6] = -0.34414F;
    arrayOfFloat[7] = -0.71414F;
    arrayOfFloat[10] = 1.0F;
    arrayOfFloat[11] = 1.772F;
    arrayOfFloat[12] = 0.0F;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorMatrix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */