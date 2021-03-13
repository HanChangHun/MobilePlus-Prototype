package android.gesture;

class Instance {
  private static final float[] ORIENTATIONS = new float[] { 0.0F, 0.7853982F, 1.5707964F, 2.3561945F, 3.1415927F, 0.0F, -0.7853982F, -1.5707964F, -2.3561945F, -3.1415927F };
  
  private static final int PATCH_SAMPLE_SIZE = 16;
  
  private static final int SEQUENCE_SAMPLE_SIZE = 16;
  
  final long id;
  
  final String label;
  
  final float[] vector;
  
  private Instance(long paramLong, float[] paramArrayOffloat, String paramString) {
    this.id = paramLong;
    this.vector = paramArrayOffloat;
    this.label = paramString;
  }
  
  static Instance createInstance(int paramInt1, int paramInt2, Gesture paramGesture, String paramString) {
    Instance instance;
    if (paramInt1 == 2) {
      float[] arrayOfFloat = temporalSampler(paramInt2, paramGesture);
      instance = new Instance(paramGesture.getID(), arrayOfFloat, paramString);
      instance.normalize();
    } else {
      float[] arrayOfFloat = spatialSampler((Gesture)instance);
      instance = new Instance(instance.getID(), arrayOfFloat, paramString);
    } 
    return instance;
  }
  
  private void normalize() {
    float[] arrayOfFloat = this.vector;
    float f = 0.0F;
    int i = arrayOfFloat.length;
    byte b;
    for (b = 0; b < i; b++)
      f += arrayOfFloat[b] * arrayOfFloat[b]; 
    f = (float)Math.sqrt(f);
    for (b = 0; b < i; b++)
      arrayOfFloat[b] = arrayOfFloat[b] / f; 
  }
  
  private static float[] spatialSampler(Gesture paramGesture) {
    return GestureUtils.spatialSampling(paramGesture, 16, false);
  }
  
  private static float[] temporalSampler(int paramInt, Gesture paramGesture) {
    float[] arrayOfFloat1 = GestureUtils.temporalSampling(paramGesture.getStrokes().get(0), 16);
    float[] arrayOfFloat2 = GestureUtils.computeCentroid(arrayOfFloat1);
    float f1 = (float)Math.atan2((arrayOfFloat1[1] - arrayOfFloat2[1]), (arrayOfFloat1[0] - arrayOfFloat2[0]));
    float f2 = -f1;
    float f3 = f2;
    if (paramInt != 1) {
      int i = ORIENTATIONS.length;
      paramInt = 0;
      while (true) {
        f3 = f2;
        if (paramInt < i) {
          float f = ORIENTATIONS[paramInt] - f1;
          f3 = f2;
          if (Math.abs(f) < Math.abs(f2))
            f3 = f; 
          paramInt++;
          f2 = f3;
          continue;
        } 
        break;
      } 
    } 
    GestureUtils.translate(arrayOfFloat1, -arrayOfFloat2[0], -arrayOfFloat2[1]);
    GestureUtils.rotate(arrayOfFloat1, f3);
    return arrayOfFloat1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/Instance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */