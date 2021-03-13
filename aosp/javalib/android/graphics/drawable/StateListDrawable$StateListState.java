package android.graphics.drawable;

import android.content.res.Resources;
import android.util.StateSet;

class StateListState extends DrawableContainer.DrawableContainerState {
  int[][] mStateSets;
  
  int[] mThemeAttrs;
  
  StateListState(StateListState paramStateListState, StateListDrawable paramStateListDrawable, Resources paramResources) {
    super(paramStateListState, paramStateListDrawable, paramResources);
    if (paramStateListState != null) {
      this.mThemeAttrs = paramStateListState.mThemeAttrs;
      this.mStateSets = paramStateListState.mStateSets;
    } else {
      this.mThemeAttrs = null;
      this.mStateSets = new int[getCapacity()][];
    } 
  }
  
  int addStateSet(int[] paramArrayOfint, Drawable paramDrawable) {
    int i = addChild(paramDrawable);
    this.mStateSets[i] = paramArrayOfint;
    return i;
  }
  
  public boolean canApplyTheme() {
    return (this.mThemeAttrs != null || super.canApplyTheme());
  }
  
  public void growArray(int paramInt1, int paramInt2) {
    super.growArray(paramInt1, paramInt2);
    int[][] arrayOfInt = new int[paramInt2][];
    System.arraycopy(this.mStateSets, 0, arrayOfInt, 0, paramInt1);
    this.mStateSets = arrayOfInt;
  }
  
  boolean hasFocusStateSpecified() {
    return StateSet.containsAttribute(this.mStateSets, 16842908);
  }
  
  int indexOfStateSet(int[] paramArrayOfint) {
    int[][] arrayOfInt = this.mStateSets;
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      if (StateSet.stateSetMatches(arrayOfInt[b], paramArrayOfint))
        return b; 
    } 
    return -1;
  }
  
  void mutate() {
    int[] arrayOfInt = this.mThemeAttrs;
    if (arrayOfInt != null) {
      arrayOfInt = (int[])arrayOfInt.clone();
    } else {
      arrayOfInt = null;
    } 
    this.mThemeAttrs = arrayOfInt;
    int[][] arrayOfInt1 = this.mStateSets;
    int[][] arrayOfInt2 = new int[arrayOfInt1.length][];
    for (int i = arrayOfInt1.length - 1; i >= 0; i--) {
      arrayOfInt1 = this.mStateSets;
      if (arrayOfInt1[i] != null) {
        int[] arrayOfInt3 = (int[])arrayOfInt1[i].clone();
      } else {
        arrayOfInt1 = null;
      } 
      arrayOfInt2[i] = (int[])arrayOfInt1;
    } 
    this.mStateSets = arrayOfInt2;
  }
  
  public Drawable newDrawable() {
    return new StateListDrawable(this, null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new StateListDrawable(this, paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/StateListDrawable$StateListState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */