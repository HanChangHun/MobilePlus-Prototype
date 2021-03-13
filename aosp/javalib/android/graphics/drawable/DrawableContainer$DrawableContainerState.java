package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.BlendMode;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.util.SparseArray;

public abstract class DrawableContainerState extends Drawable.ConstantState {
  boolean mAutoMirrored;
  
  BlendMode mBlendMode;
  
  boolean mCanConstantState;
  
  int mChangingConfigurations;
  
  boolean mCheckedConstantSize;
  
  boolean mCheckedConstantState;
  
  boolean mCheckedOpacity;
  
  boolean mCheckedPadding;
  
  boolean mCheckedStateful;
  
  int mChildrenChangingConfigurations;
  
  ColorFilter mColorFilter;
  
  int mConstantHeight;
  
  int mConstantMinimumHeight;
  
  int mConstantMinimumWidth;
  
  Rect mConstantPadding;
  
  boolean mConstantSize = false;
  
  int mConstantWidth;
  
  int mDensity = 160;
  
  boolean mDither = true;
  
  SparseArray<Drawable.ConstantState> mDrawableFutures;
  
  Drawable[] mDrawables;
  
  int mEnterFadeDuration = 0;
  
  int mExitFadeDuration = 0;
  
  boolean mHasColorFilter;
  
  boolean mHasTintList;
  
  boolean mHasTintMode;
  
  int mLayoutDirection;
  
  boolean mMutated;
  
  int mNumChildren;
  
  int mOpacity;
  
  final DrawableContainer mOwner;
  
  Resources mSourceRes;
  
  boolean mStateful;
  
  ColorStateList mTintList;
  
  boolean mVariablePadding = false;
  
  protected DrawableContainerState(DrawableContainerState paramDrawableContainerState, DrawableContainer paramDrawableContainer, Resources paramResources) {
    this.mOwner = paramDrawableContainer;
    if (paramResources != null) {
      Resources resources = paramResources;
    } else if (paramDrawableContainerState != null) {
      Resources resources = paramDrawableContainerState.mSourceRes;
    } else {
      paramDrawableContainer = null;
    } 
    this.mSourceRes = (Resources)paramDrawableContainer;
    if (paramDrawableContainerState != null) {
      i = paramDrawableContainerState.mDensity;
    } else {
      i = 0;
    } 
    int i = Drawable.resolveDensity(paramResources, i);
    this.mDensity = i;
    if (paramDrawableContainerState != null) {
      this.mChangingConfigurations = paramDrawableContainerState.mChangingConfigurations;
      this.mChildrenChangingConfigurations = paramDrawableContainerState.mChildrenChangingConfigurations;
      this.mCheckedConstantState = true;
      this.mCanConstantState = true;
      this.mVariablePadding = paramDrawableContainerState.mVariablePadding;
      this.mConstantSize = paramDrawableContainerState.mConstantSize;
      this.mDither = paramDrawableContainerState.mDither;
      this.mMutated = paramDrawableContainerState.mMutated;
      this.mLayoutDirection = paramDrawableContainerState.mLayoutDirection;
      this.mEnterFadeDuration = paramDrawableContainerState.mEnterFadeDuration;
      this.mExitFadeDuration = paramDrawableContainerState.mExitFadeDuration;
      this.mAutoMirrored = paramDrawableContainerState.mAutoMirrored;
      this.mColorFilter = paramDrawableContainerState.mColorFilter;
      this.mHasColorFilter = paramDrawableContainerState.mHasColorFilter;
      this.mTintList = paramDrawableContainerState.mTintList;
      this.mBlendMode = paramDrawableContainerState.mBlendMode;
      this.mHasTintList = paramDrawableContainerState.mHasTintList;
      this.mHasTintMode = paramDrawableContainerState.mHasTintMode;
      if (paramDrawableContainerState.mDensity == i) {
        if (paramDrawableContainerState.mCheckedPadding) {
          this.mConstantPadding = new Rect(paramDrawableContainerState.mConstantPadding);
          this.mCheckedPadding = true;
        } 
        if (paramDrawableContainerState.mCheckedConstantSize) {
          this.mConstantWidth = paramDrawableContainerState.mConstantWidth;
          this.mConstantHeight = paramDrawableContainerState.mConstantHeight;
          this.mConstantMinimumWidth = paramDrawableContainerState.mConstantMinimumWidth;
          this.mConstantMinimumHeight = paramDrawableContainerState.mConstantMinimumHeight;
          this.mCheckedConstantSize = true;
        } 
      } 
      if (paramDrawableContainerState.mCheckedOpacity) {
        this.mOpacity = paramDrawableContainerState.mOpacity;
        this.mCheckedOpacity = true;
      } 
      if (paramDrawableContainerState.mCheckedStateful) {
        this.mStateful = paramDrawableContainerState.mStateful;
        this.mCheckedStateful = true;
      } 
      Drawable[] arrayOfDrawable = paramDrawableContainerState.mDrawables;
      this.mDrawables = new Drawable[arrayOfDrawable.length];
      this.mNumChildren = paramDrawableContainerState.mNumChildren;
      SparseArray<Drawable.ConstantState> sparseArray = paramDrawableContainerState.mDrawableFutures;
      if (sparseArray != null) {
        this.mDrawableFutures = sparseArray.clone();
      } else {
        this.mDrawableFutures = new SparseArray(this.mNumChildren);
      } 
      int j = this.mNumChildren;
      for (i = 0; i < j; i++) {
        if (arrayOfDrawable[i] != null) {
          Drawable.ConstantState constantState = arrayOfDrawable[i].getConstantState();
          if (constantState != null) {
            this.mDrawableFutures.put(i, constantState);
          } else {
            this.mDrawables[i] = arrayOfDrawable[i];
          } 
        } 
      } 
    } else {
      this.mDrawables = new Drawable[10];
      this.mNumChildren = 0;
    } 
  }
  
  private void createAllFutures() {
    SparseArray<Drawable.ConstantState> sparseArray = this.mDrawableFutures;
    if (sparseArray != null) {
      int i = sparseArray.size();
      for (byte b = 0; b < i; b++) {
        int j = this.mDrawableFutures.keyAt(b);
        Drawable.ConstantState constantState = (Drawable.ConstantState)this.mDrawableFutures.valueAt(b);
        this.mDrawables[j] = prepareDrawable(constantState.newDrawable(this.mSourceRes));
      } 
      this.mDrawableFutures = null;
    } 
  }
  
  private void mutate() {
    int i = this.mNumChildren;
    Drawable[] arrayOfDrawable = this.mDrawables;
    for (byte b = 0; b < i; b++) {
      if (arrayOfDrawable[b] != null)
        arrayOfDrawable[b].mutate(); 
    } 
    this.mMutated = true;
  }
  
  private Drawable prepareDrawable(Drawable paramDrawable) {
    paramDrawable.setLayoutDirection(this.mLayoutDirection);
    paramDrawable = paramDrawable.mutate();
    paramDrawable.setCallback(this.mOwner);
    return paramDrawable;
  }
  
  public final int addChild(Drawable paramDrawable) {
    int i = this.mNumChildren;
    if (i >= this.mDrawables.length)
      growArray(i, i + 10); 
    paramDrawable.mutate();
    paramDrawable.setVisible(false, true);
    paramDrawable.setCallback(this.mOwner);
    this.mDrawables[i] = paramDrawable;
    this.mNumChildren++;
    this.mChildrenChangingConfigurations |= paramDrawable.getChangingConfigurations();
    invalidateCache();
    this.mConstantPadding = null;
    this.mCheckedPadding = false;
    this.mCheckedConstantSize = false;
    this.mCheckedConstantState = false;
    return i;
  }
  
  final void applyTheme(Resources.Theme paramTheme) {
    if (paramTheme != null) {
      createAllFutures();
      int i = this.mNumChildren;
      Drawable[] arrayOfDrawable = this.mDrawables;
      for (byte b = 0; b < i; b++) {
        if (arrayOfDrawable[b] != null && arrayOfDrawable[b].canApplyTheme()) {
          arrayOfDrawable[b].applyTheme(paramTheme);
          this.mChildrenChangingConfigurations |= arrayOfDrawable[b].getChangingConfigurations();
        } 
      } 
      updateDensity(paramTheme.getResources());
    } 
  }
  
  public boolean canApplyTheme() {
    int i = this.mNumChildren;
    Drawable[] arrayOfDrawable = this.mDrawables;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = arrayOfDrawable[b];
      if (drawable != null) {
        if (drawable.canApplyTheme())
          return true; 
      } else {
        Drawable.ConstantState constantState = (Drawable.ConstantState)this.mDrawableFutures.get(b);
        if (constantState != null && constantState.canApplyTheme())
          return true; 
      } 
    } 
    return false;
  }
  
  public boolean canConstantState() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCheckedConstantState : Z
    //   6: ifeq -> 18
    //   9: aload_0
    //   10: getfield mCanConstantState : Z
    //   13: istore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: iload_1
    //   17: ireturn
    //   18: aload_0
    //   19: invokespecial createAllFutures : ()V
    //   22: aload_0
    //   23: iconst_1
    //   24: putfield mCheckedConstantState : Z
    //   27: aload_0
    //   28: getfield mNumChildren : I
    //   31: istore_2
    //   32: aload_0
    //   33: getfield mDrawables : [Landroid/graphics/drawable/Drawable;
    //   36: astore_3
    //   37: iconst_0
    //   38: istore #4
    //   40: iload #4
    //   42: iload_2
    //   43: if_icmpge -> 71
    //   46: aload_3
    //   47: iload #4
    //   49: aaload
    //   50: invokevirtual getConstantState : ()Landroid/graphics/drawable/Drawable$ConstantState;
    //   53: ifnonnull -> 65
    //   56: aload_0
    //   57: iconst_0
    //   58: putfield mCanConstantState : Z
    //   61: aload_0
    //   62: monitorexit
    //   63: iconst_0
    //   64: ireturn
    //   65: iinc #4, 1
    //   68: goto -> 40
    //   71: aload_0
    //   72: iconst_1
    //   73: putfield mCanConstantState : Z
    //   76: aload_0
    //   77: monitorexit
    //   78: iconst_1
    //   79: ireturn
    //   80: astore_3
    //   81: aload_0
    //   82: monitorexit
    //   83: aload_3
    //   84: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	80	finally
    //   18	37	80	finally
    //   46	61	80	finally
    //   71	76	80	finally
  }
  
  final void clearMutated() {
    int i = this.mNumChildren;
    Drawable[] arrayOfDrawable = this.mDrawables;
    for (byte b = 0; b < i; b++) {
      if (arrayOfDrawable[b] != null)
        arrayOfDrawable[b].clearMutated(); 
    } 
    this.mMutated = false;
  }
  
  protected void computeConstantSize() {
    this.mCheckedConstantSize = true;
    createAllFutures();
    int i = this.mNumChildren;
    Drawable[] arrayOfDrawable = this.mDrawables;
    this.mConstantHeight = -1;
    this.mConstantWidth = -1;
    this.mConstantMinimumHeight = 0;
    this.mConstantMinimumWidth = 0;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = arrayOfDrawable[b];
      int j = drawable.getIntrinsicWidth();
      if (j > this.mConstantWidth)
        this.mConstantWidth = j; 
      j = drawable.getIntrinsicHeight();
      if (j > this.mConstantHeight)
        this.mConstantHeight = j; 
      j = drawable.getMinimumWidth();
      if (j > this.mConstantMinimumWidth)
        this.mConstantMinimumWidth = j; 
      j = drawable.getMinimumHeight();
      if (j > this.mConstantMinimumHeight)
        this.mConstantMinimumHeight = j; 
    } 
  }
  
  final int getCapacity() {
    return this.mDrawables.length;
  }
  
  public int getChangingConfigurations() {
    return this.mChangingConfigurations | this.mChildrenChangingConfigurations;
  }
  
  public final Drawable getChild(int paramInt) {
    Drawable drawable = this.mDrawables[paramInt];
    if (drawable != null)
      return drawable; 
    SparseArray<Drawable.ConstantState> sparseArray = this.mDrawableFutures;
    if (sparseArray != null) {
      int i = sparseArray.indexOfKey(paramInt);
      if (i >= 0) {
        Drawable drawable1 = prepareDrawable(((Drawable.ConstantState)this.mDrawableFutures.valueAt(i)).newDrawable(this.mSourceRes));
        this.mDrawables[paramInt] = drawable1;
        this.mDrawableFutures.removeAt(i);
        if (this.mDrawableFutures.size() == 0)
          this.mDrawableFutures = null; 
        return drawable1;
      } 
    } 
    return null;
  }
  
  public final int getChildCount() {
    return this.mNumChildren;
  }
  
  public final Drawable[] getChildren() {
    createAllFutures();
    return this.mDrawables;
  }
  
  public final int getConstantHeight() {
    if (!this.mCheckedConstantSize)
      computeConstantSize(); 
    return this.mConstantHeight;
  }
  
  public final int getConstantMinimumHeight() {
    if (!this.mCheckedConstantSize)
      computeConstantSize(); 
    return this.mConstantMinimumHeight;
  }
  
  public final int getConstantMinimumWidth() {
    if (!this.mCheckedConstantSize)
      computeConstantSize(); 
    return this.mConstantMinimumWidth;
  }
  
  public final Rect getConstantPadding() {
    if (this.mVariablePadding)
      return null; 
    if (this.mConstantPadding != null || this.mCheckedPadding)
      return this.mConstantPadding; 
    createAllFutures();
    Rect rect1 = null;
    Rect rect2 = new Rect();
    int i = this.mNumChildren;
    Drawable[] arrayOfDrawable = this.mDrawables;
    byte b = 0;
    while (b < i) {
      Rect rect = rect1;
      if (arrayOfDrawable[b].getPadding(rect2)) {
        Rect rect3 = rect1;
        if (rect1 == null)
          rect3 = new Rect(0, 0, 0, 0); 
        if (rect2.left > rect3.left)
          rect3.left = rect2.left; 
        if (rect2.top > rect3.top)
          rect3.top = rect2.top; 
        if (rect2.right > rect3.right)
          rect3.right = rect2.right; 
        rect = rect3;
        if (rect2.bottom > rect3.bottom) {
          rect3.bottom = rect2.bottom;
          rect = rect3;
        } 
      } 
      b++;
      rect1 = rect;
    } 
    this.mCheckedPadding = true;
    this.mConstantPadding = rect1;
    return rect1;
  }
  
  public final int getConstantWidth() {
    if (!this.mCheckedConstantSize)
      computeConstantSize(); 
    return this.mConstantWidth;
  }
  
  public final int getEnterFadeDuration() {
    return this.mEnterFadeDuration;
  }
  
  public final int getExitFadeDuration() {
    return this.mExitFadeDuration;
  }
  
  public final int getOpacity() {
    int j;
    if (this.mCheckedOpacity)
      return this.mOpacity; 
    createAllFutures();
    int i = this.mNumChildren;
    Drawable[] arrayOfDrawable = this.mDrawables;
    if (i > 0) {
      j = arrayOfDrawable[0].getOpacity();
    } else {
      j = -2;
    } 
    for (byte b = 1; b < i; b++)
      j = Drawable.resolveOpacity(j, arrayOfDrawable[b].getOpacity()); 
    this.mOpacity = j;
    this.mCheckedOpacity = true;
    return j;
  }
  
  public void growArray(int paramInt1, int paramInt2) {
    Drawable[] arrayOfDrawable = new Drawable[paramInt2];
    System.arraycopy(this.mDrawables, 0, arrayOfDrawable, 0, paramInt1);
    this.mDrawables = arrayOfDrawable;
  }
  
  void invalidateCache() {
    this.mCheckedOpacity = false;
    this.mCheckedStateful = false;
  }
  
  public final boolean isConstantSize() {
    return this.mConstantSize;
  }
  
  public final boolean isStateful() {
    boolean bool2;
    if (this.mCheckedStateful)
      return this.mStateful; 
    createAllFutures();
    int i = this.mNumChildren;
    Drawable[] arrayOfDrawable = this.mDrawables;
    boolean bool1 = false;
    byte b = 0;
    while (true) {
      bool2 = bool1;
      if (b < i) {
        if (arrayOfDrawable[b].isStateful()) {
          bool2 = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    this.mStateful = bool2;
    this.mCheckedStateful = true;
    return bool2;
  }
  
  public final void setConstantSize(boolean paramBoolean) {
    this.mConstantSize = paramBoolean;
  }
  
  public final void setEnterFadeDuration(int paramInt) {
    this.mEnterFadeDuration = paramInt;
  }
  
  public final void setExitFadeDuration(int paramInt) {
    this.mExitFadeDuration = paramInt;
  }
  
  final boolean setLayoutDirection(int paramInt1, int paramInt2) {
    boolean bool = false;
    int i = this.mNumChildren;
    Drawable[] arrayOfDrawable = this.mDrawables;
    byte b = 0;
    while (b < i) {
      boolean bool1 = bool;
      if (arrayOfDrawable[b] != null) {
        boolean bool2 = arrayOfDrawable[b].setLayoutDirection(paramInt1);
        bool1 = bool;
        if (b == paramInt2)
          bool1 = bool2; 
      } 
      b++;
      bool = bool1;
    } 
    this.mLayoutDirection = paramInt1;
    return bool;
  }
  
  public final void setVariablePadding(boolean paramBoolean) {
    this.mVariablePadding = paramBoolean;
  }
  
  final void updateDensity(Resources paramResources) {
    if (paramResources != null) {
      this.mSourceRes = paramResources;
      int i = Drawable.resolveDensity(paramResources, this.mDensity);
      int j = this.mDensity;
      this.mDensity = i;
      if (j != i) {
        this.mCheckedConstantSize = false;
        this.mCheckedPadding = false;
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/DrawableContainer$DrawableContainerState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */