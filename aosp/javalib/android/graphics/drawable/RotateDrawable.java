package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.MathUtils;
import android.util.TypedValue;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RotateDrawable extends DrawableWrapper {
  private static final int MAX_LEVEL = 10000;
  
  private RotateState mState;
  
  public RotateDrawable() {
    this(new RotateState(null, null), (Resources)null);
  }
  
  private RotateDrawable(RotateState paramRotateState, Resources paramResources) {
    super(paramRotateState, paramResources);
    this.mState = paramRotateState;
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    RotateState rotateState = this.mState;
    if (rotateState == null)
      return; 
    rotateState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    RotateState.access$002(rotateState, paramTypedArray.extractThemeAttrs());
    boolean bool = paramTypedArray.hasValue(4);
    boolean bool1 = true;
    if (bool) {
      float f;
      TypedValue typedValue = paramTypedArray.peekValue(4);
      if (typedValue.type == 6) {
        bool = true;
      } else {
        bool = false;
      } 
      rotateState.mPivotXRel = bool;
      if (rotateState.mPivotXRel) {
        f = typedValue.getFraction(1.0F, 1.0F);
      } else {
        f = typedValue.getFloat();
      } 
      rotateState.mPivotX = f;
    } 
    if (paramTypedArray.hasValue(5)) {
      float f;
      TypedValue typedValue = paramTypedArray.peekValue(5);
      if (typedValue.type == 6) {
        bool = bool1;
      } else {
        bool = false;
      } 
      rotateState.mPivotYRel = bool;
      if (rotateState.mPivotYRel) {
        f = typedValue.getFraction(1.0F, 1.0F);
      } else {
        f = typedValue.getFloat();
      } 
      rotateState.mPivotY = f;
    } 
    rotateState.mFromDegrees = paramTypedArray.getFloat(2, rotateState.mFromDegrees);
    rotateState.mToDegrees = paramTypedArray.getFloat(3, rotateState.mToDegrees);
    rotateState.mCurrentDegrees = rotateState.mFromDegrees;
  }
  
  private void verifyRequiredAttributes(TypedArray paramTypedArray) throws XmlPullParserException {
    if (getDrawable() != null || (this.mState.mThemeAttrs != null && this.mState.mThemeAttrs[1] != 0))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramTypedArray.getPositionDescription());
    stringBuilder.append(": <rotate> tag requires a 'drawable' attribute or child tag defining a drawable");
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    RotateState rotateState = this.mState;
    if (rotateState == null)
      return; 
    if (rotateState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(rotateState.mThemeAttrs, R.styleable.RotateDrawable);
      try {
        updateStateFromTypedArray(typedArray);
        verifyRequiredAttributes(typedArray);
        typedArray.recycle();
      } catch (XmlPullParserException xmlPullParserException) {
        rethrowAsRuntimeException((Exception)xmlPullParserException);
        typedArray.recycle();
      } finally {}
    } 
  }
  
  public void draw(Canvas paramCanvas) {
    float f1;
    float f2;
    Drawable drawable = getDrawable();
    Rect rect = drawable.getBounds();
    int i = rect.right;
    int j = rect.left;
    int k = rect.bottom;
    int m = rect.top;
    RotateState rotateState = this.mState;
    if (rotateState.mPivotXRel) {
      f1 = (i - j) * rotateState.mPivotX;
    } else {
      f1 = rotateState.mPivotX;
    } 
    if (rotateState.mPivotYRel) {
      f2 = (k - m) * rotateState.mPivotY;
    } else {
      f2 = rotateState.mPivotY;
    } 
    k = paramCanvas.save();
    paramCanvas.rotate(rotateState.mCurrentDegrees, rect.left + f1, rect.top + f2);
    drawable.draw(paramCanvas);
    paramCanvas.restoreToCount(k);
  }
  
  public float getFromDegrees() {
    return this.mState.mFromDegrees;
  }
  
  public float getPivotX() {
    return this.mState.mPivotX;
  }
  
  public float getPivotY() {
    return this.mState.mPivotY;
  }
  
  public float getToDegrees() {
    return this.mState.mToDegrees;
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.RotateDrawable);
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    updateStateFromTypedArray(typedArray);
    verifyRequiredAttributes(typedArray);
    typedArray.recycle();
  }
  
  public boolean isPivotXRelative() {
    return this.mState.mPivotXRel;
  }
  
  public boolean isPivotYRelative() {
    return this.mState.mPivotYRel;
  }
  
  DrawableWrapper.DrawableWrapperState mutateConstantState() {
    RotateState rotateState = new RotateState(this.mState, null);
    this.mState = rotateState;
    return rotateState;
  }
  
  protected boolean onLevelChange(int paramInt) {
    super.onLevelChange(paramInt);
    float f = paramInt / 10000.0F;
    f = MathUtils.lerp(this.mState.mFromDegrees, this.mState.mToDegrees, f);
    this.mState.mCurrentDegrees = f;
    invalidateSelf();
    return true;
  }
  
  public void setFromDegrees(float paramFloat) {
    if (this.mState.mFromDegrees != paramFloat) {
      this.mState.mFromDegrees = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setPivotX(float paramFloat) {
    if (this.mState.mPivotX != paramFloat) {
      this.mState.mPivotX = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setPivotXRelative(boolean paramBoolean) {
    if (this.mState.mPivotXRel != paramBoolean) {
      this.mState.mPivotXRel = paramBoolean;
      invalidateSelf();
    } 
  }
  
  public void setPivotY(float paramFloat) {
    if (this.mState.mPivotY != paramFloat) {
      this.mState.mPivotY = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setPivotYRelative(boolean paramBoolean) {
    if (this.mState.mPivotYRel != paramBoolean) {
      this.mState.mPivotYRel = paramBoolean;
      invalidateSelf();
    } 
  }
  
  public void setToDegrees(float paramFloat) {
    if (this.mState.mToDegrees != paramFloat) {
      this.mState.mToDegrees = paramFloat;
      invalidateSelf();
    } 
  }
  
  static final class RotateState extends DrawableWrapper.DrawableWrapperState {
    float mCurrentDegrees = 0.0F;
    
    float mFromDegrees = 0.0F;
    
    float mPivotX = 0.5F;
    
    boolean mPivotXRel = true;
    
    float mPivotY = 0.5F;
    
    boolean mPivotYRel = true;
    
    private int[] mThemeAttrs;
    
    float mToDegrees = 360.0F;
    
    RotateState(RotateState param1RotateState, Resources param1Resources) {
      super(param1RotateState, param1Resources);
      if (param1RotateState != null) {
        this.mPivotXRel = param1RotateState.mPivotXRel;
        this.mPivotX = param1RotateState.mPivotX;
        this.mPivotYRel = param1RotateState.mPivotYRel;
        this.mPivotY = param1RotateState.mPivotY;
        this.mFromDegrees = param1RotateState.mFromDegrees;
        this.mToDegrees = param1RotateState.mToDegrees;
        this.mCurrentDegrees = param1RotateState.mCurrentDegrees;
      } 
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new RotateDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/RotateDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */