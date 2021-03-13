package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.util.MathUtils;

public class ColorStateListDrawable extends Drawable implements Drawable.Callback {
  private ColorDrawable mColorDrawable;
  
  private boolean mMutated = false;
  
  private ColorStateListDrawableState mState = new ColorStateListDrawableState();
  
  public ColorStateListDrawable() {
    initializeColorDrawable();
  }
  
  public ColorStateListDrawable(ColorStateList paramColorStateList) {
    initializeColorDrawable();
    setColorStateList(paramColorStateList);
  }
  
  private ColorStateListDrawable(ColorStateListDrawableState paramColorStateListDrawableState) {
    initializeColorDrawable();
  }
  
  private void initializeColorDrawable() {
    ColorDrawable colorDrawable = new ColorDrawable();
    this.mColorDrawable = colorDrawable;
    colorDrawable.setCallback(this);
    if (this.mState.mTint != null)
      this.mColorDrawable.setTintList(this.mState.mTint); 
    if (this.mState.mBlendMode != DEFAULT_BLEND_MODE)
      this.mColorDrawable.setTintBlendMode(this.mState.mBlendMode); 
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    if (this.mState.mColor != null)
      setColorStateList(this.mState.mColor.obtainForTheme(paramTheme)); 
    if (this.mState.mTint != null)
      setTintList(this.mState.mTint.obtainForTheme(paramTheme)); 
  }
  
  public boolean canApplyTheme() {
    return (super.canApplyTheme() || this.mState.canApplyTheme());
  }
  
  public void clearAlpha() {
    this.mState.mAlpha = -1;
    onStateChange(getState());
  }
  
  public void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  public void draw(Canvas paramCanvas) {
    this.mColorDrawable.draw(paramCanvas);
  }
  
  public int getAlpha() {
    return this.mColorDrawable.getAlpha();
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mState.getChangingConfigurations();
  }
  
  public ColorFilter getColorFilter() {
    return this.mColorDrawable.getColorFilter();
  }
  
  public ColorStateList getColorStateList() {
    return (this.mState.mColor == null) ? ColorStateList.valueOf(this.mColorDrawable.getColor()) : this.mState.mColor;
  }
  
  public Drawable.ConstantState getConstantState() {
    ColorStateListDrawableState colorStateListDrawableState = this.mState;
    colorStateListDrawableState.mChangingConfigurations |= getChangingConfigurations() & this.mState.getChangingConfigurations();
    return this.mState;
  }
  
  public Drawable getCurrent() {
    return this.mColorDrawable;
  }
  
  public int getOpacity() {
    return this.mColorDrawable.getOpacity();
  }
  
  public boolean hasFocusStateSpecified() {
    return this.mState.hasFocusStateSpecified();
  }
  
  public void invalidateDrawable(Drawable paramDrawable) {
    if (paramDrawable == this.mColorDrawable && getCallback() != null)
      getCallback().invalidateDrawable(this); 
  }
  
  public boolean isStateful() {
    return this.mState.isStateful();
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mState = new ColorStateListDrawableState(this.mState);
      this.mMutated = true;
    } 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    super.onBoundsChange(paramRect);
    this.mColorDrawable.setBounds(paramRect);
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    if (this.mState.mColor != null) {
      int i = this.mState.mColor.getColorForState(paramArrayOfint, this.mState.mColor.getDefaultColor());
      int j = i;
      if (this.mState.mAlpha != -1)
        j = 0xFFFFFF & i | MathUtils.constrain(this.mState.mAlpha, 0, 255) << 24; 
      if (j != this.mColorDrawable.getColor()) {
        this.mColorDrawable.setColor(j);
        this.mColorDrawable.setState(paramArrayOfint);
        return true;
      } 
      return this.mColorDrawable.setState(paramArrayOfint);
    } 
    return false;
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong) {
    if (paramDrawable == this.mColorDrawable && getCallback() != null)
      getCallback().scheduleDrawable(this, paramRunnable, paramLong); 
  }
  
  public void setAlpha(int paramInt) {
    this.mState.mAlpha = paramInt;
    onStateChange(getState());
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    this.mColorDrawable.setColorFilter(paramColorFilter);
  }
  
  public void setColorStateList(ColorStateList paramColorStateList) {
    this.mState.mColor = paramColorStateList;
    onStateChange(getState());
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    this.mState.mBlendMode = paramBlendMode;
    this.mColorDrawable.setTintBlendMode(paramBlendMode);
    onStateChange(getState());
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    this.mState.mTint = paramColorStateList;
    this.mColorDrawable.setTintList(paramColorStateList);
    onStateChange(getState());
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable) {
    if (paramDrawable == this.mColorDrawable && getCallback() != null)
      getCallback().unscheduleDrawable(this, paramRunnable); 
  }
  
  static final class ColorStateListDrawableState extends Drawable.ConstantState {
    int mAlpha = -1;
    
    BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
    
    int mChangingConfigurations = 0;
    
    ColorStateList mColor = null;
    
    ColorStateList mTint = null;
    
    ColorStateListDrawableState() {}
    
    ColorStateListDrawableState(ColorStateListDrawableState param1ColorStateListDrawableState) {
      this.mColor = param1ColorStateListDrawableState.mColor;
      this.mTint = param1ColorStateListDrawableState.mTint;
      this.mAlpha = param1ColorStateListDrawableState.mAlpha;
      this.mBlendMode = param1ColorStateListDrawableState.mBlendMode;
      this.mChangingConfigurations = param1ColorStateListDrawableState.mChangingConfigurations;
    }
    
    public boolean canApplyTheme() {
      ColorStateList colorStateList = this.mColor;
      if (colorStateList == null || !colorStateList.canApplyTheme()) {
        colorStateList = this.mTint;
        return (colorStateList != null && colorStateList.canApplyTheme());
      } 
      return true;
    }
    
    public int getChangingConfigurations() {
      byte b;
      int i = this.mChangingConfigurations;
      ColorStateList colorStateList = this.mColor;
      int j = 0;
      if (colorStateList != null) {
        b = colorStateList.getChangingConfigurations();
      } else {
        b = 0;
      } 
      colorStateList = this.mTint;
      if (colorStateList != null)
        j = colorStateList.getChangingConfigurations(); 
      return i | b | j;
    }
    
    public boolean hasFocusStateSpecified() {
      ColorStateList colorStateList = this.mColor;
      if (colorStateList == null || !colorStateList.hasFocusStateSpecified()) {
        colorStateList = this.mTint;
        return (colorStateList != null && colorStateList.hasFocusStateSpecified());
      } 
      return true;
    }
    
    public boolean isStateful() {
      ColorStateList colorStateList = this.mColor;
      if (colorStateList == null || !colorStateList.isStateful()) {
        colorStateList = this.mTint;
        return (colorStateList != null && colorStateList.isStateful());
      } 
      return true;
    }
    
    public Drawable newDrawable() {
      return new ColorStateListDrawable(this);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/ColorStateListDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */