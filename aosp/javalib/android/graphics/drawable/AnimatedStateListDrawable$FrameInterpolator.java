package android.graphics.drawable;

import android.animation.TimeInterpolator;

class FrameInterpolator implements TimeInterpolator {
  private int[] mFrameTimes;
  
  private int mFrames;
  
  private int mTotalDuration;
  
  public FrameInterpolator(AnimationDrawable paramAnimationDrawable, boolean paramBoolean) {
    updateFrames(paramAnimationDrawable, paramBoolean);
  }
  
  public float getInterpolation(float paramFloat) {
    int i = (int)(this.mTotalDuration * paramFloat + 0.5F);
    int j = this.mFrames;
    int[] arrayOfInt = this.mFrameTimes;
    byte b;
    for (b = 0; b < j && i >= arrayOfInt[b]; b++)
      i -= arrayOfInt[b]; 
    if (b < j) {
      paramFloat = i / this.mTotalDuration;
    } else {
      paramFloat = 0.0F;
    } 
    return b / j + paramFloat;
  }
  
  public int getTotalDuration() {
    return this.mTotalDuration;
  }
  
  public int updateFrames(AnimationDrawable paramAnimationDrawable, boolean paramBoolean) {
    int i = paramAnimationDrawable.getNumberOfFrames();
    this.mFrames = i;
    int[] arrayOfInt = this.mFrameTimes;
    if (arrayOfInt == null || arrayOfInt.length < i)
      this.mFrameTimes = new int[i]; 
    arrayOfInt = this.mFrameTimes;
    int j = 0;
    for (byte b = 0; b < i; b++) {
      if (paramBoolean) {
        k = i - b - 1;
      } else {
        k = b;
      } 
      int k = paramAnimationDrawable.getDuration(k);
      arrayOfInt[b] = k;
      j += k;
    } 
    this.mTotalDuration = j;
    return j;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedStateListDrawable$FrameInterpolator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */