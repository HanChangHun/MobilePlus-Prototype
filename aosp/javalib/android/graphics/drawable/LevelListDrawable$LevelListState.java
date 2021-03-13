package android.graphics.drawable;

import android.content.res.Resources;

final class LevelListState extends DrawableContainer.DrawableContainerState {
  private int[] mHighs;
  
  private int[] mLows;
  
  LevelListState(LevelListState paramLevelListState, LevelListDrawable paramLevelListDrawable, Resources paramResources) {
    super(paramLevelListState, paramLevelListDrawable, paramResources);
    if (paramLevelListState != null) {
      this.mLows = paramLevelListState.mLows;
      this.mHighs = paramLevelListState.mHighs;
    } else {
      this.mLows = new int[getCapacity()];
      this.mHighs = new int[getCapacity()];
    } 
  }
  
  private void mutate() {
    this.mLows = (int[])this.mLows.clone();
    this.mHighs = (int[])this.mHighs.clone();
  }
  
  public void addLevel(int paramInt1, int paramInt2, Drawable paramDrawable) {
    int i = addChild(paramDrawable);
    this.mLows[i] = paramInt1;
    this.mHighs[i] = paramInt2;
  }
  
  public void growArray(int paramInt1, int paramInt2) {
    super.growArray(paramInt1, paramInt2);
    int[] arrayOfInt = new int[paramInt2];
    System.arraycopy(this.mLows, 0, arrayOfInt, 0, paramInt1);
    this.mLows = arrayOfInt;
    arrayOfInt = new int[paramInt2];
    System.arraycopy(this.mHighs, 0, arrayOfInt, 0, paramInt1);
    this.mHighs = arrayOfInt;
  }
  
  public int indexOfLevel(int paramInt) {
    int[] arrayOfInt1 = this.mLows;
    int[] arrayOfInt2 = this.mHighs;
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      if (paramInt >= arrayOfInt1[b] && paramInt <= arrayOfInt2[b])
        return b; 
    } 
    return -1;
  }
  
  public Drawable newDrawable() {
    return new LevelListDrawable(this, null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new LevelListDrawable(this, paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/LevelListDrawable$LevelListState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */