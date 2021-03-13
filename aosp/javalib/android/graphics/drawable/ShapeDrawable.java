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
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.util.Log;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ShapeDrawable extends Drawable {
  private BlendModeColorFilter mBlendModeColorFilter;
  
  private boolean mMutated;
  
  private ShapeState mShapeState;
  
  public ShapeDrawable() {
    this(new ShapeState(), (Resources)null);
  }
  
  private ShapeDrawable(ShapeState paramShapeState, Resources paramResources) {
    this.mShapeState = paramShapeState;
    updateLocalState();
  }
  
  public ShapeDrawable(Shape paramShape) {
    this(new ShapeState(), (Resources)null);
    this.mShapeState.mShape = paramShape;
  }
  
  private static int modulateAlpha(int paramInt1, int paramInt2) {
    return paramInt1 * ((paramInt2 >>> 7) + paramInt2) >>> 8;
  }
  
  private void updateLocalState() {
    this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, this.mShapeState.mTint, this.mShapeState.mBlendMode);
  }
  
  private void updateShape() {
    if (this.mShapeState.mShape != null) {
      Rect rect = getBounds();
      int i = rect.width();
      int j = rect.height();
      this.mShapeState.mShape.resize(i, j);
      if (this.mShapeState.mShaderFactory != null)
        this.mShapeState.mPaint.setShader(this.mShapeState.mShaderFactory.resize(i, j)); 
    } 
    invalidateSelf();
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    ShapeState shapeState = this.mShapeState;
    Paint paint = shapeState.mPaint;
    shapeState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    shapeState.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    paint.setColor(paramTypedArray.getColor(4, paint.getColor()));
    paint.setDither(paramTypedArray.getBoolean(0, paint.isDither()));
    shapeState.mIntrinsicWidth = (int)paramTypedArray.getDimension(3, shapeState.mIntrinsicWidth);
    shapeState.mIntrinsicHeight = (int)paramTypedArray.getDimension(2, shapeState.mIntrinsicHeight);
    int i = paramTypedArray.getInt(5, -1);
    if (i != -1)
      shapeState.mBlendMode = Drawable.parseBlendMode(i, BlendMode.SRC_IN); 
    ColorStateList colorStateList = paramTypedArray.getColorStateList(1);
    if (colorStateList != null)
      shapeState.mTint = colorStateList; 
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    ShapeState shapeState = this.mShapeState;
    if (shapeState == null)
      return; 
    if (shapeState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(shapeState.mThemeAttrs, R.styleable.ShapeDrawable);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
    } 
    if (shapeState.mTint != null && shapeState.mTint.canApplyTheme())
      shapeState.mTint = shapeState.mTint.obtainForTheme(paramTheme); 
    updateLocalState();
  }
  
  public void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  public void draw(Canvas paramCanvas) {
    Rect rect = getBounds();
    ShapeState shapeState = this.mShapeState;
    Paint paint = shapeState.mPaint;
    int i = paint.getAlpha();
    paint.setAlpha(modulateAlpha(i, shapeState.mAlpha));
    if (paint.getAlpha() != 0 || paint.getXfermode() != null || paint.hasShadowLayer()) {
      boolean bool;
      if (this.mBlendModeColorFilter != null && paint.getColorFilter() == null) {
        paint.setColorFilter((ColorFilter)this.mBlendModeColorFilter);
        bool = true;
      } else {
        bool = false;
      } 
      if (shapeState.mShape != null) {
        int j = paramCanvas.save();
        paramCanvas.translate(rect.left, rect.top);
        onDraw(shapeState.mShape, paramCanvas, paint);
        paramCanvas.restoreToCount(j);
      } else {
        paramCanvas.drawRect(rect, paint);
      } 
      if (bool)
        paint.setColorFilter(null); 
    } 
    paint.setAlpha(i);
  }
  
  public int getAlpha() {
    return this.mShapeState.mAlpha;
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mShapeState.getChangingConfigurations();
  }
  
  public Drawable.ConstantState getConstantState() {
    this.mShapeState.mChangingConfigurations = getChangingConfigurations();
    return this.mShapeState;
  }
  
  public int getIntrinsicHeight() {
    return this.mShapeState.mIntrinsicHeight;
  }
  
  public int getIntrinsicWidth() {
    return this.mShapeState.mIntrinsicWidth;
  }
  
  public int getOpacity() {
    if (this.mShapeState.mShape == null) {
      Paint paint = this.mShapeState.mPaint;
      if (paint.getXfermode() == null) {
        int i = paint.getAlpha();
        if (i == 0)
          return -2; 
        if (i == 255)
          return -1; 
      } 
    } 
    return -3;
  }
  
  public void getOutline(Outline paramOutline) {
    if (this.mShapeState.mShape != null) {
      this.mShapeState.mShape.getOutline(paramOutline);
      paramOutline.setAlpha(getAlpha() / 255.0F);
    } 
  }
  
  public boolean getPadding(Rect paramRect) {
    if (this.mShapeState.mPadding != null) {
      paramRect.set(this.mShapeState.mPadding);
      return true;
    } 
    return super.getPadding(paramRect);
  }
  
  public Paint getPaint() {
    return this.mShapeState.mPaint;
  }
  
  public ShaderFactory getShaderFactory() {
    return this.mShapeState.mShaderFactory;
  }
  
  public Shape getShape() {
    return this.mShapeState.mShape;
  }
  
  public boolean hasFocusStateSpecified() {
    boolean bool;
    if (this.mShapeState.mTint != null && this.mShapeState.mTint.hasFocusStateSpecified()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.ShapeDrawable);
    updateStateFromTypedArray(typedArray);
    typedArray.recycle();
    int i = paramXmlPullParser.getDepth();
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1 && (j != 3 || paramXmlPullParser.getDepth() > i)) {
        if (j != 2)
          continue; 
        String str = paramXmlPullParser.getName();
        if (!inflateTag(str, paramResources, paramXmlPullParser, paramAttributeSet)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown element: ");
          stringBuilder.append(str);
          stringBuilder.append(" for ShapeDrawable ");
          stringBuilder.append(this);
          Log.w("drawable", stringBuilder.toString());
        } 
        continue;
      } 
      break;
    } 
    updateLocalState();
  }
  
  protected boolean inflateTag(String paramString, Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) {
    if ("padding".equals(paramString)) {
      TypedArray typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.ShapeDrawablePadding);
      setPadding(typedArray.getDimensionPixelOffset(0, 0), typedArray.getDimensionPixelOffset(1, 0), typedArray.getDimensionPixelOffset(2, 0), typedArray.getDimensionPixelOffset(3, 0));
      typedArray.recycle();
      return true;
    } 
    return false;
  }
  
  public boolean isStateful() {
    ShapeState shapeState = this.mShapeState;
    return (super.isStateful() || (shapeState.mTint != null && shapeState.mTint.isStateful()));
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mShapeState = new ShapeState(this.mShapeState);
      updateLocalState();
      this.mMutated = true;
    } 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    super.onBoundsChange(paramRect);
    updateShape();
  }
  
  protected void onDraw(Shape paramShape, Canvas paramCanvas, Paint paramPaint) {
    paramShape.draw(paramCanvas, paramPaint);
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    ShapeState shapeState = this.mShapeState;
    if (shapeState.mTint != null && shapeState.mBlendMode != null) {
      this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, shapeState.mTint, shapeState.mBlendMode);
      return true;
    } 
    return false;
  }
  
  public void setAlpha(int paramInt) {
    this.mShapeState.mAlpha = paramInt;
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    this.mShapeState.mPaint.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setDither(boolean paramBoolean) {
    this.mShapeState.mPaint.setDither(paramBoolean);
    invalidateSelf();
  }
  
  public void setIntrinsicHeight(int paramInt) {
    this.mShapeState.mIntrinsicHeight = paramInt;
    invalidateSelf();
  }
  
  public void setIntrinsicWidth(int paramInt) {
    this.mShapeState.mIntrinsicWidth = paramInt;
    invalidateSelf();
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if ((paramInt1 | paramInt2 | paramInt3 | paramInt4) == 0) {
      this.mShapeState.mPadding = null;
    } else {
      if (this.mShapeState.mPadding == null)
        this.mShapeState.mPadding = new Rect(); 
      this.mShapeState.mPadding.set(paramInt1, paramInt2, paramInt3, paramInt4);
    } 
    invalidateSelf();
  }
  
  public void setPadding(Rect paramRect) {
    if (paramRect == null) {
      this.mShapeState.mPadding = null;
    } else {
      if (this.mShapeState.mPadding == null)
        this.mShapeState.mPadding = new Rect(); 
      this.mShapeState.mPadding.set(paramRect);
    } 
    invalidateSelf();
  }
  
  public void setShaderFactory(ShaderFactory paramShaderFactory) {
    this.mShapeState.mShaderFactory = paramShaderFactory;
  }
  
  public void setShape(Shape paramShape) {
    this.mShapeState.mShape = paramShape;
    updateShape();
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    this.mShapeState.mBlendMode = paramBlendMode;
    this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, this.mShapeState.mTint, paramBlendMode);
    invalidateSelf();
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    this.mShapeState.mTint = paramColorStateList;
    this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, paramColorStateList, this.mShapeState.mBlendMode);
    invalidateSelf();
  }
  
  public void setXfermode(Xfermode paramXfermode) {
    this.mShapeState.mPaint.setXfermode(paramXfermode);
    invalidateSelf();
  }
  
  public static abstract class ShaderFactory {
    public abstract Shader resize(int param1Int1, int param1Int2);
  }
  
  static final class ShapeState extends Drawable.ConstantState {
    int mAlpha = 255;
    
    BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
    
    int mChangingConfigurations;
    
    int mIntrinsicHeight;
    
    int mIntrinsicWidth;
    
    Rect mPadding;
    
    final Paint mPaint;
    
    ShapeDrawable.ShaderFactory mShaderFactory;
    
    Shape mShape;
    
    int[] mThemeAttrs;
    
    ColorStateList mTint;
    
    ShapeState() {
      this.mPaint = new Paint(1);
    }
    
    ShapeState(ShapeState param1ShapeState) {
      this.mChangingConfigurations = param1ShapeState.mChangingConfigurations;
      this.mPaint = new Paint(param1ShapeState.mPaint);
      this.mThemeAttrs = param1ShapeState.mThemeAttrs;
      Shape shape = param1ShapeState.mShape;
      if (shape != null)
        try {
          this.mShape = shape.clone();
        } catch (CloneNotSupportedException cloneNotSupportedException) {
          this.mShape = param1ShapeState.mShape;
        }  
      this.mTint = param1ShapeState.mTint;
      this.mBlendMode = param1ShapeState.mBlendMode;
      if (param1ShapeState.mPadding != null)
        this.mPadding = new Rect(param1ShapeState.mPadding); 
      this.mIntrinsicWidth = param1ShapeState.mIntrinsicWidth;
      this.mIntrinsicHeight = param1ShapeState.mIntrinsicHeight;
      this.mAlpha = param1ShapeState.mAlpha;
      this.mShaderFactory = param1ShapeState.mShaderFactory;
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
      return new ShapeDrawable(new ShapeState(this), null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new ShapeDrawable(new ShapeState(this), param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/ShapeDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */