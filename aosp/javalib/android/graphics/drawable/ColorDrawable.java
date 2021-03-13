package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ColorDrawable extends Drawable {
  private BlendModeColorFilter mBlendModeColorFilter;
  
  @ExportedProperty(deepExport = true, prefix = "state_")
  private ColorState mColorState = new ColorState();
  
  private boolean mMutated;
  
  private final Paint mPaint = new Paint(1);
  
  public ColorDrawable() {}
  
  public ColorDrawable(int paramInt) {
    setColor(paramInt);
  }
  
  private ColorDrawable(ColorState paramColorState, Resources paramResources) {
    updateLocalState(paramResources);
  }
  
  private void updateLocalState(Resources paramResources) {
    this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, this.mColorState.mTint, this.mColorState.mBlendMode);
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    ColorState colorState = this.mColorState;
    colorState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    colorState.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    colorState.mBaseColor = paramTypedArray.getColor(0, colorState.mBaseColor);
    colorState.mUseColor = colorState.mBaseColor;
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    ColorState colorState = this.mColorState;
    if (colorState == null)
      return; 
    if (colorState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(colorState.mThemeAttrs, R.styleable.ColorDrawable);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
    } 
    if (colorState.mTint != null && colorState.mTint.canApplyTheme())
      colorState.mTint = colorState.mTint.obtainForTheme(paramTheme); 
    updateLocalState(paramTheme.getResources());
  }
  
  public boolean canApplyTheme() {
    return (this.mColorState.canApplyTheme() || super.canApplyTheme());
  }
  
  public void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  public void draw(Canvas paramCanvas) {
    ColorFilter colorFilter = this.mPaint.getColorFilter();
    if (this.mColorState.mUseColor >>> 24 != 0 || colorFilter != null || this.mBlendModeColorFilter != null) {
      if (colorFilter == null)
        this.mPaint.setColorFilter((ColorFilter)this.mBlendModeColorFilter); 
      this.mPaint.setColor(this.mColorState.mUseColor);
      paramCanvas.drawRect(getBounds(), this.mPaint);
      this.mPaint.setColorFilter(colorFilter);
    } 
  }
  
  public int getAlpha() {
    return this.mColorState.mUseColor >>> 24;
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mColorState.getChangingConfigurations();
  }
  
  public int getColor() {
    return this.mColorState.mUseColor;
  }
  
  public ColorFilter getColorFilter() {
    return this.mPaint.getColorFilter();
  }
  
  public Drawable.ConstantState getConstantState() {
    return this.mColorState;
  }
  
  public int getOpacity() {
    if (this.mBlendModeColorFilter != null || this.mPaint.getColorFilter() != null)
      return -3; 
    int i = this.mColorState.mUseColor >>> 24;
    return (i != 0) ? ((i != 255) ? -3 : -1) : -2;
  }
  
  public void getOutline(Outline paramOutline) {
    paramOutline.setRect(getBounds());
    paramOutline.setAlpha(getAlpha() / 255.0F);
  }
  
  public Xfermode getXfermode() {
    return this.mPaint.getXfermode();
  }
  
  public boolean hasFocusStateSpecified() {
    boolean bool;
    if (this.mColorState.mTint != null && this.mColorState.mTint.hasFocusStateSpecified()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.ColorDrawable);
    updateStateFromTypedArray(typedArray);
    typedArray.recycle();
    updateLocalState(paramResources);
  }
  
  public boolean isStateful() {
    boolean bool;
    if (this.mColorState.mTint != null && this.mColorState.mTint.isStateful()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mColorState = new ColorState(this.mColorState);
      this.mMutated = true;
    } 
    return this;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    ColorState colorState = this.mColorState;
    if (colorState.mTint != null && colorState.mBlendMode != null) {
      this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, colorState.mTint, colorState.mBlendMode);
      return true;
    } 
    return false;
  }
  
  public void setAlpha(int paramInt) {
    int i = this.mColorState.mBaseColor;
    paramInt = this.mColorState.mBaseColor << 8 >>> 8 | (i >>> 24) * (paramInt + (paramInt >> 7)) >> 8 << 24;
    if (this.mColorState.mUseColor != paramInt) {
      this.mColorState.mUseColor = paramInt;
      invalidateSelf();
    } 
  }
  
  public void setColor(int paramInt) {
    if (this.mColorState.mBaseColor != paramInt || this.mColorState.mUseColor != paramInt) {
      ColorState colorState = this.mColorState;
      colorState.mUseColor = paramInt;
      colorState.mBaseColor = paramInt;
      invalidateSelf();
    } 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    this.mPaint.setColorFilter(paramColorFilter);
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    this.mColorState.mBlendMode = paramBlendMode;
    this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, this.mColorState.mTint, paramBlendMode);
    invalidateSelf();
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    this.mColorState.mTint = paramColorStateList;
    this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, paramColorStateList, this.mColorState.mBlendMode);
    invalidateSelf();
  }
  
  public void setXfermode(Xfermode paramXfermode) {
    this.mPaint.setXfermode(paramXfermode);
    invalidateSelf();
  }
  
  static final class ColorState extends Drawable.ConstantState {
    int mBaseColor;
    
    BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
    
    int mChangingConfigurations;
    
    int[] mThemeAttrs;
    
    ColorStateList mTint = null;
    
    @ExportedProperty
    int mUseColor;
    
    ColorState() {}
    
    ColorState(ColorState param1ColorState) {
      this.mThemeAttrs = param1ColorState.mThemeAttrs;
      this.mBaseColor = param1ColorState.mBaseColor;
      this.mUseColor = param1ColorState.mUseColor;
      this.mChangingConfigurations = param1ColorState.mChangingConfigurations;
      this.mTint = param1ColorState.mTint;
      this.mBlendMode = param1ColorState.mBlendMode;
    }
    
    public boolean canApplyTheme() {
      if (this.mThemeAttrs == null) {
        ColorStateList colorStateList = this.mTint;
        return (colorStateList != null && colorStateList.canApplyTheme());
      } 
      return true;
    }
    
    public int getChangingConfigurations() {
      byte b;
      int i = this.mChangingConfigurations;
      ColorStateList colorStateList = this.mTint;
      if (colorStateList != null) {
        b = colorStateList.getChangingConfigurations();
      } else {
        b = 0;
      } 
      return i | b;
    }
    
    public Drawable newDrawable() {
      return new ColorDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new ColorDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/ColorDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */