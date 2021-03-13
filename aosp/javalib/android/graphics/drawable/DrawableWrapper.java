package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Insets;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class DrawableWrapper extends Drawable implements Drawable.Callback {
  private Drawable mDrawable;
  
  private boolean mMutated;
  
  private DrawableWrapperState mState = null;
  
  public DrawableWrapper(Drawable paramDrawable) {
    setDrawable(paramDrawable);
  }
  
  DrawableWrapper(DrawableWrapperState paramDrawableWrapperState, Resources paramResources) {
    updateLocalState(paramResources);
  }
  
  private void inflateChildDrawable(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    Drawable drawable = null;
    int i = paramXmlPullParser.getDepth();
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1 && (j != 3 || paramXmlPullParser.getDepth() > i)) {
        if (j == 2)
          drawable = Drawable.createFromXmlInnerForDensity(paramResources, paramXmlPullParser, paramAttributeSet, this.mState.mSrcDensityOverride, paramTheme); 
        continue;
      } 
      break;
    } 
    if (drawable != null)
      setDrawable(drawable); 
  }
  
  private void updateLocalState(Resources paramResources) {
    DrawableWrapperState drawableWrapperState = this.mState;
    if (drawableWrapperState != null && drawableWrapperState.mDrawableState != null)
      setDrawable(this.mState.mDrawableState.newDrawable(paramResources)); 
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    DrawableWrapperState drawableWrapperState = this.mState;
    if (drawableWrapperState == null)
      return; 
    drawableWrapperState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    DrawableWrapperState.access$002(drawableWrapperState, paramTypedArray.extractThemeAttrs());
    if (paramTypedArray.hasValueOrEmpty(0))
      setDrawable(paramTypedArray.getDrawable(0)); 
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    Drawable drawable = this.mDrawable;
    if (drawable != null && drawable.canApplyTheme())
      this.mDrawable.applyTheme(paramTheme); 
    DrawableWrapperState drawableWrapperState = this.mState;
    if (drawableWrapperState == null)
      return; 
    int i = (paramTheme.getResources().getDisplayMetrics()).densityDpi;
    if (i == 0)
      i = 160; 
    drawableWrapperState.setDensity(i);
    if (drawableWrapperState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(drawableWrapperState.mThemeAttrs, R.styleable.DrawableWrapper);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
    } 
  }
  
  public boolean canApplyTheme() {
    boolean bool;
    DrawableWrapperState drawableWrapperState = this.mState;
    if ((drawableWrapperState != null && drawableWrapperState.canApplyTheme()) || super.canApplyTheme()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void clearMutated() {
    super.clearMutated();
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.clearMutated(); 
    this.mMutated = false;
  }
  
  public void draw(Canvas paramCanvas) {
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.draw(paramCanvas); 
  }
  
  public int getAlpha() {
    char c;
    Drawable drawable = this.mDrawable;
    if (drawable != null) {
      c = drawable.getAlpha();
    } else {
      c = 'ÿ';
    } 
    return c;
  }
  
  public int getChangingConfigurations() {
    byte b;
    int i = super.getChangingConfigurations();
    DrawableWrapperState drawableWrapperState = this.mState;
    if (drawableWrapperState != null) {
      b = drawableWrapperState.getChangingConfigurations();
    } else {
      b = 0;
    } 
    return i | b | this.mDrawable.getChangingConfigurations();
  }
  
  public ColorFilter getColorFilter() {
    Drawable drawable = getDrawable();
    return (drawable != null) ? drawable.getColorFilter() : super.getColorFilter();
  }
  
  public Drawable.ConstantState getConstantState() {
    DrawableWrapperState drawableWrapperState = this.mState;
    if (drawableWrapperState != null && drawableWrapperState.canConstantState()) {
      this.mState.mChangingConfigurations = getChangingConfigurations();
      return this.mState;
    } 
    return null;
  }
  
  public Drawable getDrawable() {
    return this.mDrawable;
  }
  
  public void getHotspotBounds(Rect paramRect) {
    Drawable drawable = this.mDrawable;
    if (drawable != null) {
      drawable.getHotspotBounds(paramRect);
    } else {
      paramRect.set(getBounds());
    } 
  }
  
  public int getIntrinsicHeight() {
    byte b;
    Drawable drawable = this.mDrawable;
    if (drawable != null) {
      b = drawable.getIntrinsicHeight();
    } else {
      b = -1;
    } 
    return b;
  }
  
  public int getIntrinsicWidth() {
    byte b;
    Drawable drawable = this.mDrawable;
    if (drawable != null) {
      b = drawable.getIntrinsicWidth();
    } else {
      b = -1;
    } 
    return b;
  }
  
  public int getOpacity() {
    byte b;
    Drawable drawable = this.mDrawable;
    if (drawable != null) {
      b = drawable.getOpacity();
    } else {
      b = -2;
    } 
    return b;
  }
  
  public Insets getOpticalInsets() {
    Insets insets;
    Drawable drawable = this.mDrawable;
    if (drawable != null) {
      insets = drawable.getOpticalInsets();
    } else {
      insets = Insets.NONE;
    } 
    return insets;
  }
  
  public void getOutline(Outline paramOutline) {
    Drawable drawable = this.mDrawable;
    if (drawable != null) {
      drawable.getOutline(paramOutline);
    } else {
      super.getOutline(paramOutline);
    } 
  }
  
  public boolean getPadding(Rect paramRect) {
    boolean bool;
    Drawable drawable = this.mDrawable;
    if (drawable != null && drawable.getPadding(paramRect)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasFocusStateSpecified() {
    boolean bool;
    Drawable drawable = this.mDrawable;
    if (drawable != null && drawable.hasFocusStateSpecified()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    DrawableWrapperState drawableWrapperState = this.mState;
    if (drawableWrapperState == null)
      return; 
    int i = (paramResources.getDisplayMetrics()).densityDpi;
    if (i == 0)
      i = 160; 
    drawableWrapperState.setDensity(i);
    drawableWrapperState.mSrcDensityOverride = this.mSrcDensityOverride;
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.DrawableWrapper);
    updateStateFromTypedArray(typedArray);
    typedArray.recycle();
    inflateChildDrawable(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
  }
  
  public void invalidateDrawable(Drawable paramDrawable) {
    Drawable.Callback callback = getCallback();
    if (callback != null)
      callback.invalidateDrawable(this); 
  }
  
  public boolean isStateful() {
    boolean bool;
    Drawable drawable = this.mDrawable;
    if (drawable != null && drawable.isStateful()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void jumpToCurrentState() {
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.jumpToCurrentState(); 
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mState = mutateConstantState();
      Drawable drawable = this.mDrawable;
      if (drawable != null)
        drawable.mutate(); 
      DrawableWrapperState drawableWrapperState = this.mState;
      if (drawableWrapperState != null) {
        drawable = this.mDrawable;
        if (drawable != null) {
          Drawable.ConstantState constantState = drawable.getConstantState();
        } else {
          drawable = null;
        } 
        drawableWrapperState.mDrawableState = (Drawable.ConstantState)drawable;
      } 
      this.mMutated = true;
    } 
    return this;
  }
  
  DrawableWrapperState mutateConstantState() {
    return this.mState;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.setBounds(paramRect); 
  }
  
  public boolean onLayoutDirectionChanged(int paramInt) {
    boolean bool;
    Drawable drawable = this.mDrawable;
    if (drawable != null && drawable.setLayoutDirection(paramInt)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  protected boolean onLevelChange(int paramInt) {
    boolean bool;
    Drawable drawable = this.mDrawable;
    if (drawable != null && drawable.setLevel(paramInt)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    Drawable drawable = this.mDrawable;
    if (drawable != null && drawable.isStateful()) {
      boolean bool = this.mDrawable.setState(paramArrayOfint);
      if (bool)
        onBoundsChange(getBounds()); 
      return bool;
    } 
    return false;
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong) {
    Drawable.Callback callback = getCallback();
    if (callback != null)
      callback.scheduleDrawable(this, paramRunnable, paramLong); 
  }
  
  public void setAlpha(int paramInt) {
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.setAlpha(paramInt); 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.setColorFilter(paramColorFilter); 
  }
  
  public void setDrawable(Drawable paramDrawable) {
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.setCallback(null); 
    this.mDrawable = paramDrawable;
    if (paramDrawable != null) {
      paramDrawable.setCallback(this);
      paramDrawable.setVisible(isVisible(), true);
      paramDrawable.setState(getState());
      paramDrawable.setLevel(getLevel());
      paramDrawable.setBounds(getBounds());
      paramDrawable.setLayoutDirection(getLayoutDirection());
      DrawableWrapperState drawableWrapperState = this.mState;
      if (drawableWrapperState != null)
        drawableWrapperState.mDrawableState = paramDrawable.getConstantState(); 
    } 
    invalidateSelf();
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2) {
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.setHotspot(paramFloat1, paramFloat2); 
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4); 
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.setTintBlendMode(paramBlendMode); 
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.setTintList(paramColorStateList); 
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool2;
    boolean bool1 = super.setVisible(paramBoolean1, paramBoolean2);
    Drawable drawable = this.mDrawable;
    if (drawable != null && drawable.setVisible(paramBoolean1, paramBoolean2)) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    return bool1 | bool2;
  }
  
  public void setXfermode(Xfermode paramXfermode) {
    Drawable drawable = this.mDrawable;
    if (drawable != null)
      drawable.setXfermode(paramXfermode); 
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable) {
    Drawable.Callback callback = getCallback();
    if (callback != null)
      callback.unscheduleDrawable(this, paramRunnable); 
  }
  
  static abstract class DrawableWrapperState extends Drawable.ConstantState {
    int mChangingConfigurations;
    
    int mDensity;
    
    Drawable.ConstantState mDrawableState;
    
    int mSrcDensityOverride;
    
    private int[] mThemeAttrs;
    
    DrawableWrapperState(DrawableWrapperState param1DrawableWrapperState, Resources param1Resources) {
      char c2;
      char c1 = ' ';
      this.mDensity = 160;
      this.mSrcDensityOverride = 0;
      if (param1DrawableWrapperState != null) {
        this.mThemeAttrs = param1DrawableWrapperState.mThemeAttrs;
        this.mChangingConfigurations = param1DrawableWrapperState.mChangingConfigurations;
        this.mDrawableState = param1DrawableWrapperState.mDrawableState;
        this.mSrcDensityOverride = param1DrawableWrapperState.mSrcDensityOverride;
      } 
      if (param1Resources != null) {
        c2 = (param1Resources.getDisplayMetrics()).densityDpi;
      } else if (param1DrawableWrapperState != null) {
        c2 = param1DrawableWrapperState.mDensity;
      } else {
        c2 = Character.MIN_VALUE;
      } 
      if (!c2)
        c2 = c1; 
      this.mDensity = c2;
    }
    
    public boolean canApplyTheme() {
      if (this.mThemeAttrs == null) {
        Drawable.ConstantState constantState = this.mDrawableState;
        return ((constantState != null && constantState.canApplyTheme()) || super.canApplyTheme());
      } 
      return true;
    }
    
    public boolean canConstantState() {
      boolean bool;
      if (this.mDrawableState != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getChangingConfigurations() {
      byte b;
      int i = this.mChangingConfigurations;
      Drawable.ConstantState constantState = this.mDrawableState;
      if (constantState != null) {
        b = constantState.getChangingConfigurations();
      } else {
        b = 0;
      } 
      return i | b;
    }
    
    public Drawable newDrawable() {
      return newDrawable(null);
    }
    
    public abstract Drawable newDrawable(Resources param1Resources);
    
    void onDensityChanged(int param1Int1, int param1Int2) {}
    
    public final void setDensity(int param1Int) {
      if (this.mDensity != param1Int) {
        int i = this.mDensity;
        this.mDensity = param1Int;
        onDensityChanged(i, param1Int);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/DrawableWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */