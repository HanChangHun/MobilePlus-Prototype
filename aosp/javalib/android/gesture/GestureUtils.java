package android.gesture;

import android.graphics.RectF;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public final class GestureUtils {
  private static final float NONUNIFORM_SCALE = (float)Math.sqrt(2.0D);
  
  private static final float SCALING_THRESHOLD = 0.26F;
  
  static void closeStream(Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
      } catch (IOException iOException) {
        Log.e("Gestures", "Could not close stream", iOException);
      }  
  }
  
  static float[] computeCentroid(float[] paramArrayOffloat) {
    float f1 = 0.0F;
    float f2 = 0.0F;
    int i = paramArrayOffloat.length;
    for (byte b = 0; b < i; b++) {
      f1 += paramArrayOffloat[b];
      f2 += paramArrayOffloat[++b];
    } 
    return new float[] { f1 * 2.0F / i, 2.0F * f2 / i };
  }
  
  private static float[][] computeCoVariance(float[] paramArrayOffloat) {
    float[][] arrayOfFloat = new float[2][2];
    arrayOfFloat[0][0] = 0.0F;
    arrayOfFloat[0][1] = 0.0F;
    arrayOfFloat[1][0] = 0.0F;
    arrayOfFloat[1][1] = 0.0F;
    int i = paramArrayOffloat.length;
    for (byte b = 0; b < i; b++) {
      float f1 = paramArrayOffloat[b];
      float f2 = paramArrayOffloat[++b];
      float[] arrayOfFloat1 = arrayOfFloat[0];
      arrayOfFloat1[0] = arrayOfFloat1[0] + f1 * f1;
      arrayOfFloat1 = arrayOfFloat[0];
      arrayOfFloat1[1] = arrayOfFloat1[1] + f1 * f2;
      arrayOfFloat[1][0] = arrayOfFloat[0][1];
      arrayOfFloat1 = arrayOfFloat[1];
      arrayOfFloat1[1] = arrayOfFloat1[1] + f2 * f2;
    } 
    paramArrayOffloat = arrayOfFloat[0];
    paramArrayOffloat[0] = paramArrayOffloat[0] / (i / 2);
    paramArrayOffloat = arrayOfFloat[0];
    paramArrayOffloat[1] = paramArrayOffloat[1] / (i / 2);
    paramArrayOffloat = arrayOfFloat[1];
    paramArrayOffloat[0] = paramArrayOffloat[0] / (i / 2);
    paramArrayOffloat = arrayOfFloat[1];
    paramArrayOffloat[1] = paramArrayOffloat[1] / (i / 2);
    return arrayOfFloat;
  }
  
  private static float[] computeOrientation(float[][] paramArrayOffloat) {
    float[] arrayOfFloat = new float[2];
    if (paramArrayOffloat[0][1] == 0.0F || paramArrayOffloat[1][0] == 0.0F) {
      arrayOfFloat[0] = 1.0F;
      arrayOfFloat[1] = 0.0F;
    } 
    float f1 = -paramArrayOffloat[0][0];
    float f2 = paramArrayOffloat[1][1];
    float f3 = paramArrayOffloat[0][0];
    float f4 = paramArrayOffloat[1][1];
    float f5 = paramArrayOffloat[0][1];
    float f6 = paramArrayOffloat[1][0];
    f2 = (f1 - f2) / 2.0F;
    f3 = (float)Math.sqrt(Math.pow(f2, 2.0D) - (f3 * f4 - f5 * f6));
    f6 = -f2 + f3;
    f2 = -f2 - f3;
    if (f6 == f2) {
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = 0.0F;
    } else {
      if (f6 <= f2)
        f6 = f2; 
      arrayOfFloat[0] = 1.0F;
      arrayOfFloat[1] = (f6 - paramArrayOffloat[0][0]) / paramArrayOffloat[0][1];
    } 
    return arrayOfFloat;
  }
  
  public static OrientedBoundingBox computeOrientedBoundingBox(ArrayList<GesturePoint> paramArrayList) {
    int i = paramArrayList.size();
    float[] arrayOfFloat = new float[i * 2];
    for (byte b = 0; b < i; b++) {
      GesturePoint gesturePoint = paramArrayList.get(b);
      int j = b * 2;
      arrayOfFloat[j] = gesturePoint.x;
      arrayOfFloat[j + 1] = gesturePoint.y;
    } 
    return computeOrientedBoundingBox(arrayOfFloat, computeCentroid(arrayOfFloat));
  }
  
  public static OrientedBoundingBox computeOrientedBoundingBox(float[] paramArrayOffloat) {
    int i = paramArrayOffloat.length;
    float[] arrayOfFloat = new float[i];
    for (byte b = 0; b < i; b++)
      arrayOfFloat[b] = paramArrayOffloat[b]; 
    return computeOrientedBoundingBox(arrayOfFloat, computeCentroid(arrayOfFloat));
  }
  
  private static OrientedBoundingBox computeOrientedBoundingBox(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    float f1;
    translate(paramArrayOffloat1, -paramArrayOffloat2[0], -paramArrayOffloat2[1]);
    float[] arrayOfFloat = computeOrientation(computeCoVariance(paramArrayOffloat1));
    if (arrayOfFloat[0] == 0.0F && arrayOfFloat[1] == 0.0F) {
      f1 = -1.5707964F;
    } else {
      f1 = (float)Math.atan2(arrayOfFloat[1], arrayOfFloat[0]);
      rotate(paramArrayOffloat1, -f1);
    } 
    float f2 = Float.MAX_VALUE;
    float f3 = Float.MAX_VALUE;
    float f4 = Float.MIN_VALUE;
    float f5 = Float.MIN_VALUE;
    int i = paramArrayOffloat1.length;
    byte b = 0;
    while (b < i) {
      float f6 = f2;
      if (paramArrayOffloat1[b] < f2)
        f6 = paramArrayOffloat1[b]; 
      float f7 = f4;
      if (paramArrayOffloat1[b] > f4)
        f7 = paramArrayOffloat1[b]; 
      b++;
      f4 = f3;
      if (paramArrayOffloat1[b] < f3)
        f4 = paramArrayOffloat1[b]; 
      float f8 = f5;
      if (paramArrayOffloat1[b] > f5)
        f8 = paramArrayOffloat1[b]; 
      b++;
      f2 = f6;
      f3 = f4;
      f4 = f7;
      f5 = f8;
    } 
    return new OrientedBoundingBox((float)((180.0F * f1) / Math.PI), paramArrayOffloat2[0], paramArrayOffloat2[1], f4 - f2, f5 - f3);
  }
  
  static float computeStraightness(float[] paramArrayOffloat) {
    float f1 = computeTotalLength(paramArrayOffloat);
    float f2 = paramArrayOffloat[2];
    float f3 = paramArrayOffloat[0];
    float f4 = paramArrayOffloat[3];
    float f5 = paramArrayOffloat[1];
    return (float)Math.hypot((f2 - f3), (f4 - f5)) / f1;
  }
  
  static float computeStraightness(float[] paramArrayOffloat, float paramFloat) {
    float f1 = paramArrayOffloat[2];
    float f2 = paramArrayOffloat[0];
    float f3 = paramArrayOffloat[3];
    float f4 = paramArrayOffloat[1];
    return (float)Math.hypot((f1 - f2), (f3 - f4)) / paramFloat;
  }
  
  static float computeTotalLength(float[] paramArrayOffloat) {
    float f = 0.0F;
    int i = paramArrayOffloat.length;
    for (byte b = 0; b < i - 4; b += 2) {
      float f1 = paramArrayOffloat[b + 2];
      float f2 = paramArrayOffloat[b];
      float f3 = paramArrayOffloat[b + 3];
      float f4 = paramArrayOffloat[b + 1];
      f = (float)(f + Math.hypot((f1 - f2), (f3 - f4)));
    } 
    return f;
  }
  
  static float cosineDistance(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    float f = 0.0F;
    int i = paramArrayOffloat1.length;
    for (byte b = 0; b < i; b++)
      f += paramArrayOffloat1[b] * paramArrayOffloat2[b]; 
    return (float)Math.acos(f);
  }
  
  static float minimumCosineDistance(float[] paramArrayOffloat1, float[] paramArrayOffloat2, int paramInt) {
    int i = paramArrayOffloat1.length;
    float f1 = 0.0F;
    float f2 = 0.0F;
    for (byte b = 0; b < i; b += 2) {
      f1 += paramArrayOffloat1[b] * paramArrayOffloat2[b] + paramArrayOffloat1[b + 1] * paramArrayOffloat2[b + 1];
      f2 += paramArrayOffloat1[b] * paramArrayOffloat2[b + 1] - paramArrayOffloat1[b + 1] * paramArrayOffloat2[b];
    } 
    if (f1 != 0.0F) {
      float f = f2 / f1;
      double d1 = Math.atan(f);
      if (paramInt > 2 && Math.abs(d1) >= Math.PI / paramInt)
        return (float)Math.acos(f1); 
      d1 = Math.cos(d1);
      double d2 = f;
      return (float)Math.acos(f1 * d1 + f2 * d2 * d1);
    } 
    return 1.5707964F;
  }
  
  private static void plot(float paramFloat1, float paramFloat2, float[] paramArrayOffloat, int paramInt) {
    float f = 0.0F;
    if (paramFloat1 < 0.0F)
      paramFloat1 = 0.0F; 
    if (paramFloat2 < 0.0F)
      paramFloat2 = f; 
    int i = (int)Math.floor(paramFloat1);
    int j = (int)Math.ceil(paramFloat1);
    int k = (int)Math.floor(paramFloat2);
    int m = (int)Math.ceil(paramFloat2);
    if (paramFloat1 == i && paramFloat2 == k) {
      paramInt = m * paramInt + j;
      if (paramArrayOffloat[paramInt] < 1.0F)
        paramArrayOffloat[paramInt] = 1.0F; 
    } else {
      double d1 = Math.pow((i - paramFloat1), 2.0D);
      double d2 = Math.pow((k - paramFloat2), 2.0D);
      double d3 = Math.pow((j - paramFloat1), 2.0D);
      double d4 = Math.pow((m - paramFloat2), 2.0D);
      float f1 = (float)Math.sqrt(d1 + d2);
      float f2 = (float)Math.sqrt(d3 + d2);
      f = (float)Math.sqrt(d1 + d4);
      paramFloat2 = (float)Math.sqrt(d3 + d4);
      paramFloat1 = f1 + f2 + f + paramFloat2;
      f1 /= paramFloat1;
      int n = k * paramInt + i;
      if (f1 > paramArrayOffloat[n])
        paramArrayOffloat[n] = f1; 
      f2 /= paramFloat1;
      n = k * paramInt + j;
      if (f2 > paramArrayOffloat[n])
        paramArrayOffloat[n] = f2; 
      f /= paramFloat1;
      i = m * paramInt + i;
      if (f > paramArrayOffloat[i])
        paramArrayOffloat[i] = f; 
      paramFloat1 = paramFloat2 / paramFloat1;
      paramInt = m * paramInt + j;
      if (paramFloat1 > paramArrayOffloat[paramInt])
        paramArrayOffloat[paramInt] = paramFloat1; 
    } 
  }
  
  static float[] rotate(float[] paramArrayOffloat, float paramFloat) {
    float f1 = (float)Math.cos(paramFloat);
    float f2 = (float)Math.sin(paramFloat);
    int i = paramArrayOffloat.length;
    for (byte b = 0; b < i; b += 2) {
      float f3 = paramArrayOffloat[b];
      float f4 = paramArrayOffloat[b + 1];
      float f5 = paramArrayOffloat[b];
      paramFloat = paramArrayOffloat[b + 1];
      paramArrayOffloat[b] = f3 * f1 - f4 * f2;
      paramArrayOffloat[b + 1] = f5 * f2 + paramFloat * f1;
    } 
    return paramArrayOffloat;
  }
  
  static float[] scale(float[] paramArrayOffloat, float paramFloat1, float paramFloat2) {
    int i = paramArrayOffloat.length;
    for (byte b = 0; b < i; b += 2) {
      paramArrayOffloat[b] = paramArrayOffloat[b] * paramFloat1;
      int j = b + 1;
      paramArrayOffloat[j] = paramArrayOffloat[j] * paramFloat2;
    } 
    return paramArrayOffloat;
  }
  
  public static float[] spatialSampling(Gesture paramGesture, int paramInt) {
    return spatialSampling(paramGesture, paramInt, false);
  }
  
  public static float[] spatialSampling(Gesture paramGesture, int paramInt, boolean paramBoolean) {
    float f7;
    float f1 = (paramInt - 1);
    float[] arrayOfFloat = new float[paramInt * paramInt];
    Arrays.fill(arrayOfFloat, 0.0F);
    RectF rectF = paramGesture.getBoundingBox();
    float f2 = rectF.width();
    float f3 = rectF.height();
    float f4 = f1 / f2;
    float f5 = f1 / f3;
    if (paramBoolean) {
      if (f4 < f5) {
        f6 = f4;
      } else {
        f6 = f5;
      } 
      f4 = f6;
      f7 = f6;
      f6 = f4;
    } else {
      f7 = f2 / f3;
      f6 = f7;
      if (f7 > 1.0F)
        f6 = 1.0F / f7; 
      if (f6 < 0.26F) {
        if (f4 < f5) {
          f6 = f4;
        } else {
          f6 = f5;
        } 
        f7 = f6;
        f4 = f6;
        f6 = f7;
        f7 = f4;
      } else if (f4 > f5) {
        f7 = NONUNIFORM_SCALE * f5;
        f6 = f4;
        if (f7 < f4)
          f6 = f7; 
        f7 = f5;
      } else {
        float f = NONUNIFORM_SCALE * f4;
        f6 = f4;
        f7 = f5;
        if (f < f5) {
          f7 = f;
          f6 = f4;
        } 
      } 
    } 
    float f9 = -rectF.centerX();
    float f10 = -rectF.centerY();
    float f11 = f1 / 2.0F;
    float f12 = f1 / 2.0F;
    ArrayList<GestureStroke> arrayList = paramGesture.getStrokes();
    int i = arrayList.size();
    byte b = 0;
    float f8 = f6;
    float f6 = f1;
    while (b < i) {
      float[] arrayOfFloat2 = ((GestureStroke)arrayList.get(b)).points;
      int j = arrayOfFloat2.length;
      float[] arrayOfFloat1 = new float[j];
      int k;
      for (k = 0; k < j; k += 2) {
        arrayOfFloat1[k] = (arrayOfFloat2[k] + f9) * f8 + f11;
        arrayOfFloat1[k + 1] = (arrayOfFloat2[k + 1] + f10) * f7 + f12;
      } 
      float f = -1.0F;
      f1 = -1.0F;
      byte b1 = 0;
      k = j;
      while (b1 < k) {
        if (arrayOfFloat1[b1] < 0.0F) {
          f4 = 0.0F;
        } else {
          f4 = arrayOfFloat1[b1];
        } 
        if (arrayOfFloat1[b1 + 1] < 0.0F) {
          f5 = 0.0F;
        } else {
          f5 = arrayOfFloat1[b1 + 1];
        } 
        if (f4 > f6)
          f4 = f6; 
        if (f5 > f6)
          f5 = f6; 
        plot(f4, f5, arrayOfFloat, paramInt);
        if (f != -1.0F) {
          if (f > f4) {
            float f13 = (float)Math.ceil(f4);
            float f14 = (f1 - f5) / (f - f4);
            while (f13 < f) {
              plot(f13, (f13 - f4) * f14 + f5, arrayOfFloat, paramInt);
              f13++;
            } 
          } else {
            arrayOfFloat2 = arrayOfFloat1;
            arrayOfFloat1 = arrayOfFloat2;
            if (f < f4) {
              float f13 = (float)Math.ceil(f);
              float f14 = (f1 - f5) / (f - f4);
              while (true) {
                arrayOfFloat1 = arrayOfFloat2;
                if (f13 < f4) {
                  plot(f13, (f13 - f4) * f14 + f5, arrayOfFloat, paramInt);
                  f13++;
                  continue;
                } 
                break;
              } 
            } 
          } 
          if (f1 > f5) {
            float f13 = (float)Math.ceil(f5);
            float f14 = (f - f4) / (f1 - f5);
            for (f = f13; f < f1; f++)
              plot((f - f5) * f14 + f4, f, arrayOfFloat, paramInt); 
          } else if (f1 < f5) {
            float f13 = (float)Math.ceil(f1);
            f = (f - f4) / (f1 - f5);
            for (f1 = f13; f1 < f5; f1++)
              plot((f1 - f5) * f + f4, f1, arrayOfFloat, paramInt); 
          } 
        } 
        b1 += 2;
        f1 = f5;
        f = f4;
      } 
      b++;
    } 
    return arrayOfFloat;
  }
  
  static float squaredEuclideanDistance(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    float f = 0.0F;
    int i = paramArrayOffloat1.length;
    for (byte b = 0; b < i; b++) {
      float f1 = paramArrayOffloat1[b] - paramArrayOffloat2[b];
      f += f1 * f1;
    } 
    return f / i;
  }
  
  public static float[] temporalSampling(GestureStroke paramGestureStroke, int paramInt) {
    float f1 = paramGestureStroke.length / (paramInt - 1);
    int i = paramInt * 2;
    float[] arrayOfFloat2 = new float[i];
    float f2 = 0.0F;
    float[] arrayOfFloat1 = paramGestureStroke.points;
    float f3 = arrayOfFloat1[0];
    byte b = 1;
    float f4 = arrayOfFloat1[1];
    float f5 = Float.MIN_VALUE;
    float f6 = Float.MIN_VALUE;
    arrayOfFloat2[0] = f3;
    paramInt = 0 + 1;
    arrayOfFloat2[paramInt] = f4;
    int j = paramInt + 1;
    int k = 0;
    int m = arrayOfFloat1.length / 2;
    while (k < m) {
      float f7 = f5;
      paramInt = k;
      if (f5 == Float.MIN_VALUE) {
        paramInt = k + 1;
        if (paramInt >= m)
          break; 
        f7 = arrayOfFloat1[paramInt * 2];
        f6 = arrayOfFloat1[paramInt * 2 + b];
      } 
      f5 = f7 - f3;
      float f8 = f6 - f4;
      float f9 = (float)Math.hypot(f5, f8);
      if (f2 + f9 >= f1) {
        f2 = (f1 - f2) / f9;
        f5 = f2 * f5 + f3;
        f2 = f4 + f2 * f8;
        arrayOfFloat2[j] = f5;
        arrayOfFloat2[++j] = f2;
        j++;
        f4 = f5;
        f3 = 0.0F;
        f5 = f2;
        f2 = f3;
      } else {
        f4 = f7;
        f5 = f6;
        f7 = Float.MIN_VALUE;
        f6 = Float.MIN_VALUE;
        f2 += f9;
      } 
      b = 1;
      f3 = f4;
      f4 = f5;
      f5 = f7;
      k = paramInt;
    } 
    while (j < i) {
      arrayOfFloat2[j] = f3;
      arrayOfFloat2[j + 1] = f4;
      j += 2;
    } 
    return arrayOfFloat2;
  }
  
  static float[] translate(float[] paramArrayOffloat, float paramFloat1, float paramFloat2) {
    int i = paramArrayOffloat.length;
    for (byte b = 0; b < i; b += 2) {
      paramArrayOffloat[b] = paramArrayOffloat[b] + paramFloat1;
      int j = b + 1;
      paramArrayOffloat[j] = paramArrayOffloat[j] + paramFloat2;
    } 
    return paramArrayOffloat;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/GestureUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */