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

public class ClipDrawable extends DrawableWrapper {
  public static final int HORIZONTAL = 1;
  
  private static final int MAX_LEVEL = 10000;
  
  public static final int VERTICAL = 2;
  
  private ClipState mState;
  
  private final Rect mTmpRect = new Rect();
  
  ClipDrawable() {
    this(new ClipState(null, null), (Resources)null);
  }
  
  private ClipDrawable(ClipState paramClipState, Resources paramResources) {
    super(paramClipState, paramResources);
    this.mState = paramClipState;
  }
  
  public ClipDrawable(Drawable paramDrawable, int paramInt1, int paramInt2) {
    this(new ClipState(null, null), (Resources)null);
    this.mState.mGravity = paramInt1;
    this.mState.mOrientation = paramInt2;
    setDrawable(paramDrawable);
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    ClipState clipState = this.mState;
    if (clipState == null)
      return; 
    clipState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    ClipState.access$002(clipState, paramTypedArray.extractThemeAttrs());
    clipState.mOrientation = paramTypedArray.getInt(2, clipState.mOrientation);
    clipState.mGravity = paramTypedArray.getInt(0, clipState.mGravity);
  }
  
  private void verifyRequiredAttributes(TypedArray paramTypedArray) throws XmlPullParserException {
    if (getDrawable() != null || (this.mState.mThemeAttrs != null && this.mState.mThemeAttrs[1] != 0))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramTypedArray.getPositionDescription());
    stringBuilder.append(": <clip> tag requires a 'drawable' attribute or child tag defining a drawable");
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    ClipState clipState = this.mState;
    if (clipState == null)
      return; 
    if (clipState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(clipState.mThemeAttrs, R.styleable.ClipDrawable);
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
    Drawable drawable = getDrawable();
    if (drawable.getLevel() == 0)
      return; 
    Rect rect1 = this.mTmpRect;
    Rect rect2 = getBounds();
    int i = getLevel();
    int j = rect2.width();
    if ((this.mState.mOrientation & 0x1) != 0)
      j -= (j + 0) * (10000 - i) / 10000; 
    int k = rect2.height();
    if ((this.mState.mOrientation & 0x2) != 0)
      k -= (k + 0) * (10000 - i) / 10000; 
    i = getLayoutDirection();
    Gravity.apply(this.mState.mGravity, j, k, rect2, rect1, i);
    if (j > 0 && k > 0) {
      paramCanvas.save();
      paramCanvas.clipRect(rect1);
      drawable.draw(paramCanvas);
      paramCanvas.restore();
    } 
  }
  
  public int getOpacity() {
    Drawable drawable = getDrawable();
    return (drawable.getOpacity() == -2 || drawable.getLevel() == 0) ? -2 : ((getLevel() >= 10000) ? drawable.getOpacity() : -3);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.ClipDrawable);
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    updateStateFromTypedArray(typedArray);
    verifyRequiredAttributes(typedArray);
    typedArray.recycle();
  }
  
  DrawableWrapper.DrawableWrapperState mutateConstantState() {
    ClipState clipState = new ClipState(this.mState, null);
    this.mState = clipState;
    return clipState;
  }
  
  protected boolean onLevelChange(int paramInt) {
    super.onLevelChange(paramInt);
    invalidateSelf();
    return true;
  }
  
  static final class ClipState extends DrawableWrapper.DrawableWrapperState {
    int mGravity = 3;
    
    int mOrientation = 1;
    
    private int[] mThemeAttrs;
    
    ClipState(ClipState param1ClipState, Resources param1Resources) {
      super(param1ClipState, param1Resources);
      if (param1ClipState != null) {
        this.mOrientation = param1ClipState.mOrientation;
        this.mGravity = param1ClipState.mGravity;
      } 
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new ClipDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/ClipDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */