package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ImageDecoder;
import android.graphics.Insets;
import android.graphics.NinePatch;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.android.internal.R;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class NinePatchDrawable extends Drawable {
  private static final boolean DEFAULT_DITHER = false;
  
  private int mBitmapHeight = -1;
  
  private int mBitmapWidth = -1;
  
  private BlendModeColorFilter mBlendModeFilter;
  
  private boolean mMutated;
  
  private NinePatchState mNinePatchState = new NinePatchState();
  
  private Insets mOpticalInsets = Insets.NONE;
  
  private Rect mOutlineInsets;
  
  private float mOutlineRadius;
  
  private Rect mPadding;
  
  private Paint mPaint;
  
  private int mTargetDensity = 160;
  
  private Rect mTempRect;
  
  NinePatchDrawable() {}
  
  public NinePatchDrawable(Resources paramResources, Bitmap paramBitmap, byte[] paramArrayOfbyte, Rect paramRect1, Rect paramRect2, String paramString) {
    this(new NinePatchState(new NinePatch(paramBitmap, paramArrayOfbyte, paramString), paramRect1, paramRect2), paramResources);
  }
  
  public NinePatchDrawable(Resources paramResources, Bitmap paramBitmap, byte[] paramArrayOfbyte, Rect paramRect, String paramString) {
    this(new NinePatchState(new NinePatch(paramBitmap, paramArrayOfbyte, paramString), paramRect), paramResources);
  }
  
  public NinePatchDrawable(Resources paramResources, NinePatch paramNinePatch) {
    this(new NinePatchState(paramNinePatch, new Rect()), paramResources);
  }
  
  @Deprecated
  public NinePatchDrawable(Bitmap paramBitmap, byte[] paramArrayOfbyte, Rect paramRect, String paramString) {
    this(new NinePatchState(new NinePatch(paramBitmap, paramArrayOfbyte, paramString), paramRect), (Resources)null);
  }
  
  @Deprecated
  public NinePatchDrawable(NinePatch paramNinePatch) {
    this(new NinePatchState(paramNinePatch, new Rect()), (Resources)null);
  }
  
  private NinePatchDrawable(NinePatchState paramNinePatchState, Resources paramResources) {
    updateLocalState(paramResources);
  }
  
  private void computeBitmapSize() {
    int j;
    NinePatch ninePatch = this.mNinePatchState.mNinePatch;
    if (ninePatch == null)
      return; 
    int i = this.mTargetDensity;
    if (ninePatch.getDensity() == 0) {
      j = i;
    } else {
      j = ninePatch.getDensity();
    } 
    Insets insets = this.mNinePatchState.mOpticalInsets;
    if (insets != Insets.NONE) {
      this.mOpticalInsets = Insets.of(Drawable.scaleFromDensity(insets.left, j, i, true), Drawable.scaleFromDensity(insets.top, j, i, true), Drawable.scaleFromDensity(insets.right, j, i, true), Drawable.scaleFromDensity(insets.bottom, j, i, true));
    } else {
      this.mOpticalInsets = Insets.NONE;
    } 
    Rect rect = this.mNinePatchState.mPadding;
    if (rect != null) {
      if (this.mPadding == null)
        this.mPadding = new Rect(); 
      this.mPadding.left = Drawable.scaleFromDensity(rect.left, j, i, true);
      this.mPadding.top = Drawable.scaleFromDensity(rect.top, j, i, true);
      this.mPadding.right = Drawable.scaleFromDensity(rect.right, j, i, true);
      this.mPadding.bottom = Drawable.scaleFromDensity(rect.bottom, j, i, true);
    } else {
      this.mPadding = null;
    } 
    this.mBitmapHeight = Drawable.scaleFromDensity(ninePatch.getHeight(), j, i, true);
    this.mBitmapWidth = Drawable.scaleFromDensity(ninePatch.getWidth(), j, i, true);
    NinePatch.InsetStruct insetStruct = ninePatch.getBitmap().getNinePatchInsets();
    if (insetStruct != null) {
      Rect rect1 = insetStruct.outlineRect;
      this.mOutlineInsets = NinePatch.InsetStruct.scaleInsets(rect1.left, rect1.top, rect1.right, rect1.bottom, i / j);
      this.mOutlineRadius = Drawable.scaleFromDensity(insetStruct.outlineRadius, j, i);
    } else {
      this.mOutlineInsets = null;
    } 
  }
  
  private boolean needsMirroring() {
    boolean bool = isAutoMirrored();
    boolean bool1 = true;
    if (!bool || getLayoutDirection() != 1)
      bool1 = false; 
    return bool1;
  }
  
  private void updateLocalState(Resources paramResources) {
    NinePatchState ninePatchState = this.mNinePatchState;
    if (ninePatchState.mDither)
      setDither(ninePatchState.mDither); 
    if (paramResources == null && ninePatchState.mNinePatch != null) {
      this.mTargetDensity = ninePatchState.mNinePatch.getDensity();
    } else {
      this.mTargetDensity = Drawable.resolveDensity(paramResources, this.mTargetDensity);
    } 
    this.mBlendModeFilter = updateBlendModeFilter(this.mBlendModeFilter, ninePatchState.mTint, ninePatchState.mBlendMode);
    computeBitmapSize();
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) throws XmlPullParserException {
    Resources resources = paramTypedArray.getResources();
    NinePatchState ninePatchState = this.mNinePatchState;
    ninePatchState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    ninePatchState.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    ninePatchState.mDither = paramTypedArray.getBoolean(1, ninePatchState.mDither);
    int i = paramTypedArray.getResourceId(0, 0);
    if (i != 0) {
      Rect rect1 = new Rect();
      Rect rect2 = new Rect();
      Bitmap bitmap1 = null;
      Bitmap bitmap2 = bitmap1;
      try {
        TypedValue typedValue = new TypedValue();
        bitmap2 = bitmap1;
        this();
        bitmap2 = bitmap1;
        InputStream inputStream = resources.openRawResource(i, typedValue);
        i = 0;
        bitmap2 = bitmap1;
        if (typedValue.density == 0) {
          i = 160;
        } else {
          bitmap2 = bitmap1;
          if (typedValue.density != 65535) {
            bitmap2 = bitmap1;
            i = typedValue.density;
          } 
        } 
        bitmap2 = bitmap1;
        ImageDecoder.Source source = ImageDecoder.createSource(resources, inputStream, i);
        bitmap2 = bitmap1;
        _$$Lambda$NinePatchDrawable$yQvfm7FAkslD5wdGFysjgwt8cLE _$$Lambda$NinePatchDrawable$yQvfm7FAkslD5wdGFysjgwt8cLE = new _$$Lambda$NinePatchDrawable$yQvfm7FAkslD5wdGFysjgwt8cLE();
        bitmap2 = bitmap1;
        this(rect1);
        bitmap2 = bitmap1;
        bitmap1 = ImageDecoder.decodeBitmap(source, _$$Lambda$NinePatchDrawable$yQvfm7FAkslD5wdGFysjgwt8cLE);
        bitmap2 = bitmap1;
        inputStream.close();
        bitmap2 = bitmap1;
      } catch (IOException iOException) {}
      if (bitmap2 != null) {
        if (bitmap2.getNinePatchChunk() != null) {
          bitmap2.getOpticalInsets(rect2);
          ninePatchState.mNinePatch = new NinePatch(bitmap2, bitmap2.getNinePatchChunk());
          ninePatchState.mPadding = rect1;
          ninePatchState.mOpticalInsets = Insets.of(rect2);
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramTypedArray.getPositionDescription());
          stringBuilder.append(": <nine-patch> requires a valid 9-patch source image");
          throw new XmlPullParserException(stringBuilder.toString());
        } 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramTypedArray.getPositionDescription());
        stringBuilder.append(": <nine-patch> requires a valid src attribute");
        throw new XmlPullParserException(stringBuilder.toString());
      } 
    } 
    ninePatchState.mAutoMirrored = paramTypedArray.getBoolean(4, ninePatchState.mAutoMirrored);
    ninePatchState.mBaseAlpha = paramTypedArray.getFloat(3, ninePatchState.mBaseAlpha);
    i = paramTypedArray.getInt(5, -1);
    if (i != -1)
      ninePatchState.mBlendMode = Drawable.parseBlendMode(i, BlendMode.SRC_IN); 
    ColorStateList colorStateList = paramTypedArray.getColorStateList(2);
    if (colorStateList != null)
      ninePatchState.mTint = colorStateList; 
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    NinePatchState ninePatchState = this.mNinePatchState;
    if (ninePatchState == null)
      return; 
    if (ninePatchState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(ninePatchState.mThemeAttrs, R.styleable.NinePatchDrawable);
      try {
        updateStateFromTypedArray(typedArray);
        typedArray.recycle();
      } catch (XmlPullParserException xmlPullParserException) {
        rethrowAsRuntimeException((Exception)xmlPullParserException);
        typedArray.recycle();
      } finally {}
    } 
    if (ninePatchState.mTint != null && ninePatchState.mTint.canApplyTheme())
      ninePatchState.mTint = ninePatchState.mTint.obtainForTheme(paramTheme); 
    updateLocalState(paramTheme.getResources());
  }
  
  public boolean canApplyTheme() {
    boolean bool;
    NinePatchState ninePatchState = this.mNinePatchState;
    if (ninePatchState != null && ninePatchState.canApplyTheme()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  public void draw(Canvas paramCanvas) {
    boolean bool;
    byte b;
    NinePatchState ninePatchState = this.mNinePatchState;
    Rect rect1 = getBounds();
    int i = -1;
    if (this.mBlendModeFilter != null && getPaint().getColorFilter() == null) {
      this.mPaint.setColorFilter((ColorFilter)this.mBlendModeFilter);
      bool = true;
    } else {
      bool = false;
    } 
    if (ninePatchState.mBaseAlpha != 1.0F) {
      b = getPaint().getAlpha();
      this.mPaint.setAlpha((int)(b * ninePatchState.mBaseAlpha + 0.5F));
    } else {
      b = -1;
    } 
    if (paramCanvas.getDensity() == 0 && ninePatchState.mNinePatch.getDensity() != 0) {
      j = 1;
    } else {
      j = 0;
    } 
    Rect rect2 = rect1;
    if (j) {
      if (-1 >= 0) {
        i = -1;
      } else {
        i = paramCanvas.save();
      } 
      float f = this.mTargetDensity / ninePatchState.mNinePatch.getDensity();
      paramCanvas.scale(f, f, rect1.left, rect1.top);
      if (this.mTempRect == null)
        this.mTempRect = new Rect(); 
      rect2 = this.mTempRect;
      rect2.left = rect1.left;
      rect2.top = rect1.top;
      rect2.right = rect1.left + Math.round(rect1.width() / f);
      rect2.bottom = rect1.top + Math.round(rect1.height() / f);
    } 
    int j = i;
    if (needsMirroring()) {
      if (i < 0)
        i = paramCanvas.save(); 
      paramCanvas.scale(-1.0F, 1.0F, (rect2.left + rect2.right) / 2.0F, (rect2.top + rect2.bottom) / 2.0F);
      j = i;
    } 
    ninePatchState.mNinePatch.draw(paramCanvas, rect2, this.mPaint);
    if (j >= 0)
      paramCanvas.restoreToCount(j); 
    if (bool)
      this.mPaint.setColorFilter(null); 
    if (b >= 0)
      this.mPaint.setAlpha(b); 
  }
  
  public int getAlpha() {
    return (this.mPaint == null) ? 255 : getPaint().getAlpha();
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mNinePatchState.getChangingConfigurations();
  }
  
  public Drawable.ConstantState getConstantState() {
    this.mNinePatchState.mChangingConfigurations = getChangingConfigurations();
    return this.mNinePatchState;
  }
  
  public int getIntrinsicHeight() {
    return this.mBitmapHeight;
  }
  
  public int getIntrinsicWidth() {
    return this.mBitmapWidth;
  }
  
  public int getOpacity() {
    if (!this.mNinePatchState.mNinePatch.hasAlpha()) {
      Paint paint = this.mPaint;
      return (paint != null && paint.getAlpha() < 255) ? -3 : -1;
    } 
    return -3;
  }
  
  public Insets getOpticalInsets() {
    Insets insets = this.mOpticalInsets;
    return needsMirroring() ? Insets.of(insets.right, insets.top, insets.left, insets.bottom) : insets;
  }
  
  public void getOutline(Outline paramOutline) {
    Rect rect = getBounds();
    if (rect.isEmpty())
      return; 
    NinePatchState ninePatchState = this.mNinePatchState;
    if (ninePatchState != null && this.mOutlineInsets != null) {
      NinePatch.InsetStruct insetStruct = ninePatchState.mNinePatch.getBitmap().getNinePatchInsets();
      if (insetStruct != null) {
        paramOutline.setRoundRect(rect.left + this.mOutlineInsets.left, rect.top + this.mOutlineInsets.top, rect.right - this.mOutlineInsets.right, rect.bottom - this.mOutlineInsets.bottom, this.mOutlineRadius);
        paramOutline.setAlpha(insetStruct.outlineAlpha * getAlpha() / 255.0F);
        return;
      } 
    } 
    super.getOutline(paramOutline);
  }
  
  public boolean getPadding(Rect paramRect) {
    Rect rect = this.mPadding;
    if (rect != null) {
      boolean bool;
      paramRect.set(rect);
      if ((paramRect.left | paramRect.top | paramRect.right | paramRect.bottom) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
    return super.getPadding(paramRect);
  }
  
  public Paint getPaint() {
    if (this.mPaint == null) {
      Paint paint = new Paint();
      this.mPaint = paint;
      paint.setDither(false);
    } 
    return this.mPaint;
  }
  
  public Region getTransparentRegion() {
    return this.mNinePatchState.mNinePatch.getTransparentRegion(getBounds());
  }
  
  public boolean hasFocusStateSpecified() {
    boolean bool;
    if (this.mNinePatchState.mTint != null && this.mNinePatchState.mTint.hasFocusStateSpecified()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.NinePatchDrawable);
    updateStateFromTypedArray(typedArray);
    typedArray.recycle();
    updateLocalState(paramResources);
  }
  
  public boolean isAutoMirrored() {
    return this.mNinePatchState.mAutoMirrored;
  }
  
  public boolean isFilterBitmap() {
    boolean bool;
    if (this.mPaint != null && getPaint().isFilterBitmap()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStateful() {
    NinePatchState ninePatchState = this.mNinePatchState;
    return (super.isStateful() || (ninePatchState.mTint != null && ninePatchState.mTint.isStateful()));
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mNinePatchState = new NinePatchState(this.mNinePatchState);
      this.mMutated = true;
    } 
    return this;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    NinePatchState ninePatchState = this.mNinePatchState;
    if (ninePatchState.mTint != null && ninePatchState.mBlendMode != null) {
      this.mBlendModeFilter = updateBlendModeFilter(this.mBlendModeFilter, ninePatchState.mTint, ninePatchState.mBlendMode);
      return true;
    } 
    return false;
  }
  
  public void setAlpha(int paramInt) {
    if (this.mPaint == null && paramInt == 255)
      return; 
    getPaint().setAlpha(paramInt);
    invalidateSelf();
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    this.mNinePatchState.mAutoMirrored = paramBoolean;
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    if (this.mPaint == null && paramColorFilter == null)
      return; 
    getPaint().setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setDither(boolean paramBoolean) {
    if (this.mPaint == null && !paramBoolean)
      return; 
    getPaint().setDither(paramBoolean);
    invalidateSelf();
  }
  
  public void setFilterBitmap(boolean paramBoolean) {
    getPaint().setFilterBitmap(paramBoolean);
    invalidateSelf();
  }
  
  public void setTargetDensity(int paramInt) {
    int i = paramInt;
    if (paramInt == 0)
      i = 160; 
    if (this.mTargetDensity != i) {
      this.mTargetDensity = i;
      computeBitmapSize();
      invalidateSelf();
    } 
  }
  
  public void setTargetDensity(Canvas paramCanvas) {
    setTargetDensity(paramCanvas.getDensity());
  }
  
  public void setTargetDensity(DisplayMetrics paramDisplayMetrics) {
    setTargetDensity(paramDisplayMetrics.densityDpi);
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    this.mNinePatchState.mBlendMode = paramBlendMode;
    this.mBlendModeFilter = updateBlendModeFilter(this.mBlendModeFilter, this.mNinePatchState.mTint, paramBlendMode);
    invalidateSelf();
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    this.mNinePatchState.mTint = paramColorStateList;
    this.mBlendModeFilter = updateBlendModeFilter(this.mBlendModeFilter, paramColorStateList, this.mNinePatchState.mBlendMode);
    invalidateSelf();
  }
  
  static final class NinePatchState extends Drawable.ConstantState {
    boolean mAutoMirrored = false;
    
    float mBaseAlpha = 1.0F;
    
    BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
    
    int mChangingConfigurations;
    
    boolean mDither = false;
    
    NinePatch mNinePatch = null;
    
    Insets mOpticalInsets = Insets.NONE;
    
    Rect mPadding = null;
    
    int[] mThemeAttrs;
    
    ColorStateList mTint = null;
    
    NinePatchState() {}
    
    NinePatchState(NinePatch param1NinePatch, Rect param1Rect) {
      this(param1NinePatch, param1Rect, null, false, false);
    }
    
    NinePatchState(NinePatch param1NinePatch, Rect param1Rect1, Rect param1Rect2) {
      this(param1NinePatch, param1Rect1, param1Rect2, false, false);
    }
    
    NinePatchState(NinePatch param1NinePatch, Rect param1Rect1, Rect param1Rect2, boolean param1Boolean1, boolean param1Boolean2) {
      this.mNinePatch = param1NinePatch;
      this.mPadding = param1Rect1;
      this.mOpticalInsets = Insets.of(param1Rect2);
      this.mDither = param1Boolean1;
      this.mAutoMirrored = param1Boolean2;
    }
    
    NinePatchState(NinePatchState param1NinePatchState) {
      this.mChangingConfigurations = param1NinePatchState.mChangingConfigurations;
      this.mNinePatch = param1NinePatchState.mNinePatch;
      this.mTint = param1NinePatchState.mTint;
      this.mBlendMode = param1NinePatchState.mBlendMode;
      this.mPadding = param1NinePatchState.mPadding;
      this.mOpticalInsets = param1NinePatchState.mOpticalInsets;
      this.mBaseAlpha = param1NinePatchState.mBaseAlpha;
      this.mDither = param1NinePatchState.mDither;
      this.mAutoMirrored = param1NinePatchState.mAutoMirrored;
      this.mThemeAttrs = param1NinePatchState.mThemeAttrs;
    }
    
    public boolean canApplyTheme() {
      if (this.mThemeAttrs == null) {
        ColorStateList colorStateList = this.mTint;
        return ((colorStateList != null && colorStateList.canApplyTheme()) || super.canApplyTheme());
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
      return new NinePatchDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new NinePatchDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/NinePatchDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */