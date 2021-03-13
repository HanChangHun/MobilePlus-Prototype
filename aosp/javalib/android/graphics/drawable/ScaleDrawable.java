package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ScaleDrawable extends DrawableWrapper {
  private static final int MAX_LEVEL = 10000;
  
  private ScaleState mState;
  
  private final Rect mTmpRect = new Rect();
  
  ScaleDrawable() {
    this(new ScaleState(null, null), (Resources)null);
  }
  
  public ScaleDrawable(Drawable paramDrawable, int paramInt, float paramFloat1, float paramFloat2) {
    this(new ScaleState(null, null), (Resources)null);
    this.mState.mGravity = paramInt;
    this.mState.mScaleWidth = paramFloat1;
    this.mState.mScaleHeight = paramFloat2;
    setDrawable(paramDrawable);
  }
  
  private ScaleDrawable(ScaleState paramScaleState, Resources paramResources) {
    super(paramScaleState, paramResources);
    this.mState = paramScaleState;
    updateLocalState();
  }
  
  private static float getPercent(TypedArray paramTypedArray, int paramInt, float paramFloat) {
    int i = paramTypedArray.getType(paramInt);
    if (i == 6 || i == 0)
      return paramTypedArray.getFraction(paramInt, 1, 1, paramFloat); 
    String str = paramTypedArray.getString(paramInt);
    return (str != null && str.endsWith("%")) ? (Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0F) : paramFloat;
  }
  
  private void updateLocalState() {
    setLevel(this.mState.mInitialLevel);
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    ScaleState scaleState = this.mState;
    if (scaleState == null)
      return; 
    scaleState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    ScaleState.access$002(scaleState, paramTypedArray.extractThemeAttrs());
    scaleState.mScaleWidth = getPercent(paramTypedArray, 1, scaleState.mScaleWidth);
    scaleState.mScaleHeight = getPercent(paramTypedArray, 2, scaleState.mScaleHeight);
    scaleState.mGravity = paramTypedArray.getInt(3, scaleState.mGravity);
    scaleState.mUseIntrinsicSizeAsMin = paramTypedArray.getBoolean(4, scaleState.mUseIntrinsicSizeAsMin);
    scaleState.mInitialLevel = paramTypedArray.getInt(5, scaleState.mInitialLevel);
  }
  
  private void verifyRequiredAttributes(TypedArray paramTypedArray) throws XmlPullParserException {
    if (getDrawable() != null || (this.mState.mThemeAttrs != null && this.mState.mThemeAttrs[0] != 0))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramTypedArray.getPositionDescription());
    stringBuilder.append(": <scale> tag requires a 'drawable' attribute or child tag defining a drawable");
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    ScaleState scaleState = this.mState;
    if (scaleState == null)
      return; 
    if (scaleState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(scaleState.mThemeAttrs, R.styleable.ScaleDrawable);
      try {
        updateStateFromTypedArray(typedArray);
        verifyRequiredAttributes(typedArray);
        typedArray.recycle();
      } catch (XmlPullParserException xmlPullParserException) {
        rethrowAsRuntimeException((Exception)xmlPullParserException);
        typedArray.recycle();
      } finally {}
    } 
    updateLocalState();
  }
  
  public void draw(Canvas paramCanvas) {
    Drawable drawable = getDrawable();
    if (drawable != null && drawable.getLevel() != 0)
      drawable.draw(paramCanvas); 
  }
  
  public int getOpacity() {
    Drawable drawable = getDrawable();
    if (drawable.getLevel() == 0)
      return -2; 
    int i = drawable.getOpacity();
    return (i == -1 && drawable.getLevel() < 10000) ? -3 : i;
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.ScaleDrawable);
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    updateStateFromTypedArray(typedArray);
    verifyRequiredAttributes(typedArray);
    typedArray.recycle();
    updateLocalState();
  }
  
  DrawableWrapper.DrawableWrapperState mutateConstantState() {
    ScaleState scaleState = new ScaleState(this.mState, null);
    this.mState = scaleState;
    return scaleState;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    int m;
    Drawable drawable = getDrawable();
    Rect rect = this.mTmpRect;
    boolean bool = this.mState.mUseIntrinsicSizeAsMin;
    int i = getLevel();
    int j = paramRect.width();
    float f = this.mState.mScaleWidth;
    int k = 0;
    if (f > 0.0F) {
      if (bool) {
        m = drawable.getIntrinsicWidth();
      } else {
        m = 0;
      } 
      m = j - (int)(((j - m) * (10000 - i)) * this.mState.mScaleWidth / 10000.0F);
    } else {
      m = j;
    } 
    j = paramRect.height();
    if (this.mState.mScaleHeight > 0.0F) {
      if (bool)
        k = drawable.getIntrinsicHeight(); 
      k = j - (int)(((j - k) * (10000 - i)) * this.mState.mScaleHeight / 10000.0F);
    } else {
      k = j;
    } 
    j = getLayoutDirection();
    Gravity.apply(this.mState.mGravity, m, k, paramRect, rect, j);
    if (m > 0 && k > 0)
      drawable.setBounds(rect.left, rect.top, rect.right, rect.bottom); 
  }
  
  protected boolean onLevelChange(int paramInt) {
    super.onLevelChange(paramInt);
    onBoundsChange(getBounds());
    invalidateSelf();
    return true;
  }
  
  static final class ScaleState extends DrawableWrapper.DrawableWrapperState {
    private static final float DO_NOT_SCALE = -1.0F;
    
    int mGravity = 3;
    
    int mInitialLevel = 0;
    
    float mScaleHeight = -1.0F;
    
    float mScaleWidth = -1.0F;
    
    private int[] mThemeAttrs;
    
    boolean mUseIntrinsicSizeAsMin = false;
    
    ScaleState(ScaleState param1ScaleState, Resources param1Resources) {
      super(param1ScaleState, param1Resources);
      if (param1ScaleState != null) {
        this.mScaleWidth = param1ScaleState.mScaleWidth;
        this.mScaleHeight = param1ScaleState.mScaleHeight;
        this.mGravity = param1ScaleState.mGravity;
        this.mUseIntrinsicSizeAsMin = param1ScaleState.mUseIntrinsicSizeAsMin;
        this.mInitialLevel = param1ScaleState.mInitialLevel;
      } 
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new ScaleDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/ScaleDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */