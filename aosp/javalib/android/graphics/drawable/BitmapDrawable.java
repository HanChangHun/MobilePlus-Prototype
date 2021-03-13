package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ImageDecoder;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import com.android.internal.R;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class BitmapDrawable extends Drawable {
  private static final int DEFAULT_PAINT_FLAGS = 6;
  
  private static final int TILE_MODE_CLAMP = 0;
  
  private static final int TILE_MODE_DISABLED = -1;
  
  private static final int TILE_MODE_MIRROR = 2;
  
  private static final int TILE_MODE_REPEAT = 1;
  
  private static final int TILE_MODE_UNDEFINED = -2;
  
  private int mBitmapHeight;
  
  private BitmapState mBitmapState;
  
  private int mBitmapWidth;
  
  private BlendModeColorFilter mBlendModeFilter;
  
  private final Rect mDstRect;
  
  private boolean mDstRectAndInsetsDirty;
  
  private Matrix mMirrorMatrix;
  
  private boolean mMutated;
  
  private Insets mOpticalInsets;
  
  private int mTargetDensity;
  
  @Deprecated
  public BitmapDrawable() {
    this.mDstRect = new Rect();
    this.mTargetDensity = 160;
    this.mDstRectAndInsetsDirty = true;
    this.mOpticalInsets = Insets.NONE;
    init(new BitmapState((Bitmap)null), (Resources)null);
  }
  
  @Deprecated
  public BitmapDrawable(Resources paramResources) {
    this.mDstRect = new Rect();
    this.mTargetDensity = 160;
    this.mDstRectAndInsetsDirty = true;
    this.mOpticalInsets = Insets.NONE;
    init(new BitmapState((Bitmap)null), paramResources);
  }
  
  public BitmapDrawable(Resources paramResources, Bitmap paramBitmap) {
    this.mDstRect = new Rect();
    this.mTargetDensity = 160;
    this.mDstRectAndInsetsDirty = true;
    this.mOpticalInsets = Insets.NONE;
    init(new BitmapState(paramBitmap), paramResources);
  }
  
  public BitmapDrawable(Resources paramResources, InputStream paramInputStream) {
    StringBuilder stringBuilder;
    this.mDstRect = new Rect();
    this.mTargetDensity = 160;
    this.mDstRectAndInsetsDirty = true;
    this.mOpticalInsets = Insets.NONE;
    try {
      Bitmap bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(paramResources, paramInputStream), (ImageDecoder.OnHeaderDecodedListener)_$$Lambda$BitmapDrawable$T1BUUqQwU4Z6Ve8DJHFuQvYohkY.INSTANCE);
      init(new BitmapState(bitmap), paramResources);
      if (this.mBitmapState.mBitmap == null) {
        stringBuilder = new StringBuilder();
      } else {
        return;
      } 
      stringBuilder.append("BitmapDrawable cannot decode ");
    } catch (Exception exception) {
      init(new BitmapState(null), (Resources)stringBuilder);
      if (this.mBitmapState.mBitmap == null) {
        stringBuilder = new StringBuilder();
      } else {
        return;
      } 
      stringBuilder.append("BitmapDrawable cannot decode ");
    } finally {
      init(new BitmapState(null), (Resources)stringBuilder);
      if (this.mBitmapState.mBitmap == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("BitmapDrawable cannot decode ");
        stringBuilder.append(paramInputStream);
        Log.w("BitmapDrawable", stringBuilder.toString());
      } 
    } 
  }
  
  public BitmapDrawable(Resources paramResources, String paramString) {
    StringBuilder stringBuilder;
    this.mDstRect = new Rect();
    this.mTargetDensity = 160;
    this.mDstRectAndInsetsDirty = true;
    this.mOpticalInsets = Insets.NONE;
    Bitmap bitmap1 = null;
    null = null;
    Bitmap bitmap2 = null;
    Bitmap bitmap3 = bitmap1;
    try {
      FileInputStream fileInputStream = new FileInputStream();
      bitmap2 = null;
      bitmap3 = bitmap1;
    } catch (Exception exception) {
      init(new BitmapState(bitmap3), (Resources)stringBuilder);
      if (this.mBitmapState.mBitmap == null) {
        stringBuilder = new StringBuilder();
      } else {
        return;
      } 
      stringBuilder.append("BitmapDrawable cannot decode ");
    } finally {
      init(new BitmapState(bitmap2), (Resources)stringBuilder);
      if (this.mBitmapState.mBitmap == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("BitmapDrawable cannot decode ");
        stringBuilder.append(paramString);
        Log.w("BitmapDrawable", stringBuilder.toString());
      } 
    } 
  }
  
  @Deprecated
  public BitmapDrawable(Bitmap paramBitmap) {
    this.mDstRect = new Rect();
    this.mTargetDensity = 160;
    this.mDstRectAndInsetsDirty = true;
    this.mOpticalInsets = Insets.NONE;
    init(new BitmapState(paramBitmap), (Resources)null);
  }
  
  private BitmapDrawable(BitmapState paramBitmapState, Resources paramResources) {
    this.mDstRect = new Rect();
    this.mTargetDensity = 160;
    this.mDstRectAndInsetsDirty = true;
    this.mOpticalInsets = Insets.NONE;
    init(paramBitmapState, paramResources);
  }
  
  @Deprecated
  public BitmapDrawable(InputStream paramInputStream) {
    this((Resources)null, paramInputStream);
  }
  
  @Deprecated
  public BitmapDrawable(String paramString) {
    this((Resources)null, paramString);
  }
  
  private void computeBitmapSize() {
    Bitmap bitmap = this.mBitmapState.mBitmap;
    if (bitmap != null) {
      this.mBitmapWidth = bitmap.getScaledWidth(this.mTargetDensity);
      this.mBitmapHeight = bitmap.getScaledHeight(this.mTargetDensity);
    } else {
      this.mBitmapHeight = -1;
      this.mBitmapWidth = -1;
    } 
  }
  
  private Matrix getOrCreateMirrorMatrix() {
    if (this.mMirrorMatrix == null)
      this.mMirrorMatrix = new Matrix(); 
    return this.mMirrorMatrix;
  }
  
  private void init(BitmapState paramBitmapState, Resources paramResources) {
    this.mBitmapState = paramBitmapState;
    updateLocalState(paramResources);
    paramBitmapState = this.mBitmapState;
    if (paramBitmapState != null && paramResources != null)
      paramBitmapState.mTargetDensity = this.mTargetDensity; 
  }
  
  private boolean needMirroring() {
    boolean bool = isAutoMirrored();
    boolean bool1 = true;
    if (!bool || getLayoutDirection() != 1)
      bool1 = false; 
    return bool1;
  }
  
  private static Shader.TileMode parseTileMode(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? null : Shader.TileMode.MIRROR) : Shader.TileMode.REPEAT) : Shader.TileMode.CLAMP;
  }
  
  private void updateDstRectAndInsetsIfDirty() {
    if (this.mDstRectAndInsetsDirty)
      if (this.mBitmapState.mTileModeX == null && this.mBitmapState.mTileModeY == null) {
        Rect rect = getBounds();
        int i = getLayoutDirection();
        Gravity.apply(this.mBitmapState.mGravity, this.mBitmapWidth, this.mBitmapHeight, rect, this.mDstRect, i);
        this.mOpticalInsets = Insets.of(this.mDstRect.left - rect.left, this.mDstRect.top - rect.top, rect.right - this.mDstRect.right, rect.bottom - this.mDstRect.bottom);
      } else {
        copyBounds(this.mDstRect);
        this.mOpticalInsets = Insets.NONE;
      }  
    this.mDstRectAndInsetsDirty = false;
  }
  
  private void updateLocalState(Resources paramResources) {
    this.mTargetDensity = resolveDensity(paramResources, this.mBitmapState.mTargetDensity);
    this.mBlendModeFilter = updateBlendModeFilter(this.mBlendModeFilter, this.mBitmapState.mTint, this.mBitmapState.mBlendMode);
    computeBitmapSize();
  }
  
  private void updateShaderMatrix(Bitmap paramBitmap, Paint paramPaint, Shader paramShader, boolean paramBoolean) {
    boolean bool;
    int i = paramBitmap.getDensity();
    int j = this.mTargetDensity;
    if (i != 0 && i != j) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool || paramBoolean) {
      Matrix matrix = getOrCreateMirrorMatrix();
      matrix.reset();
      if (paramBoolean) {
        matrix.setTranslate((this.mDstRect.right - this.mDstRect.left), 0.0F);
        matrix.setScale(-1.0F, 1.0F);
      } 
      if (bool) {
        float f = j / i;
        matrix.postScale(f, f);
      } 
      paramShader.setLocalMatrix(matrix);
    } else {
      this.mMirrorMatrix = null;
      paramShader.setLocalMatrix(Matrix.IDENTITY_MATRIX);
    } 
    paramPaint.setShader(paramShader);
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray, int paramInt) throws XmlPullParserException {
    boolean bool;
    Resources resources = paramTypedArray.getResources();
    BitmapState bitmapState = this.mBitmapState;
    bitmapState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    bitmapState.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    bitmapState.mSrcDensityOverride = paramInt;
    bitmapState.mTargetDensity = Drawable.resolveDensity(resources, 0);
    int i = paramTypedArray.getResourceId(1, 0);
    if (i != 0) {
      TypedValue typedValue = new TypedValue();
      resources.getValueForDensity(i, paramInt, typedValue, true);
      if (paramInt > 0 && typedValue.density > 0 && typedValue.density != 65535)
        if (typedValue.density == paramInt) {
          typedValue.density = (resources.getDisplayMetrics()).densityDpi;
        } else {
          typedValue.density = typedValue.density * (resources.getDisplayMetrics()).densityDpi / paramInt;
        }  
      paramInt = 0;
      if (typedValue.density == 0) {
        paramInt = 160;
      } else if (typedValue.density != 65535) {
        paramInt = typedValue.density;
      } 
      Bitmap bitmap1 = null;
      Bitmap bitmap2 = bitmap1;
      try {
        InputStream inputStream = resources.openRawResource(i, typedValue);
        try {
          bitmap2 = ImageDecoder.decodeBitmap(ImageDecoder.createSource(resources, inputStream, paramInt), (ImageDecoder.OnHeaderDecodedListener)_$$Lambda$BitmapDrawable$LMqt8JvxZ4giSOIRAtlCKDg39Jw.INSTANCE);
          bitmap1 = bitmap2;
        } finally {
          if (inputStream != null)
            try {
              inputStream.close();
            } finally {
              inputStream = null;
              bitmap2 = bitmap1;
            }  
          bitmap2 = bitmap1;
        } 
      } catch (Exception exception) {}
      if (bitmap2 != null) {
        bitmapState.mBitmap = bitmap2;
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramTypedArray.getPositionDescription());
        stringBuilder.append(": <bitmap> requires a valid 'src' attribute");
        throw new XmlPullParserException(stringBuilder.toString());
      } 
    } 
    if (bitmapState.mBitmap != null) {
      bool = bitmapState.mBitmap.hasMipMap();
    } else {
      bool = false;
    } 
    setMipMap(paramTypedArray.getBoolean(8, bool));
    bitmapState.mAutoMirrored = paramTypedArray.getBoolean(9, bitmapState.mAutoMirrored);
    bitmapState.mBaseAlpha = paramTypedArray.getFloat(7, bitmapState.mBaseAlpha);
    paramInt = paramTypedArray.getInt(10, -1);
    if (paramInt != -1)
      bitmapState.mBlendMode = Drawable.parseBlendMode(paramInt, BlendMode.SRC_IN); 
    ColorStateList colorStateList = paramTypedArray.getColorStateList(5);
    if (colorStateList != null)
      bitmapState.mTint = colorStateList; 
    Paint paint = this.mBitmapState.mPaint;
    paint.setAntiAlias(paramTypedArray.getBoolean(2, paint.isAntiAlias()));
    paint.setFilterBitmap(paramTypedArray.getBoolean(3, paint.isFilterBitmap()));
    paint.setDither(paramTypedArray.getBoolean(4, paint.isDither()));
    setGravity(paramTypedArray.getInt(0, bitmapState.mGravity));
    paramInt = paramTypedArray.getInt(6, -2);
    if (paramInt != -2) {
      Shader.TileMode tileMode = parseTileMode(paramInt);
      setTileModeXY(tileMode, tileMode);
    } 
    paramInt = paramTypedArray.getInt(11, -2);
    if (paramInt != -2)
      setTileModeX(parseTileMode(paramInt)); 
    paramInt = paramTypedArray.getInt(12, -2);
    if (paramInt != -2)
      setTileModeY(parseTileMode(paramInt)); 
  }
  
  private void verifyRequiredAttributes(TypedArray paramTypedArray) throws XmlPullParserException {
    BitmapState bitmapState = this.mBitmapState;
    if (bitmapState.mBitmap != null || (bitmapState.mThemeAttrs != null && bitmapState.mThemeAttrs[1] != 0))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramTypedArray.getPositionDescription());
    stringBuilder.append(": <bitmap> requires a valid 'src' attribute");
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    BitmapState bitmapState = this.mBitmapState;
    if (bitmapState == null)
      return; 
    if (bitmapState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(bitmapState.mThemeAttrs, R.styleable.BitmapDrawable);
      try {
        updateStateFromTypedArray(typedArray, bitmapState.mSrcDensityOverride);
        typedArray.recycle();
      } catch (XmlPullParserException xmlPullParserException) {
        rethrowAsRuntimeException((Exception)xmlPullParserException);
        typedArray.recycle();
      } finally {}
    } 
    if (bitmapState.mTint != null && bitmapState.mTint.canApplyTheme())
      bitmapState.mTint = bitmapState.mTint.obtainForTheme(paramTheme); 
    updateLocalState(paramTheme.getResources());
  }
  
  public boolean canApplyTheme() {
    boolean bool;
    BitmapState bitmapState = this.mBitmapState;
    if (bitmapState != null && bitmapState.canApplyTheme()) {
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
    byte b;
    boolean bool;
    Bitmap bitmap = this.mBitmapState.mBitmap;
    if (bitmap == null)
      return; 
    BitmapState bitmapState = this.mBitmapState;
    Paint paint = bitmapState.mPaint;
    if (bitmapState.mRebuildShader) {
      Shader.TileMode tileMode1 = bitmapState.mTileModeX;
      Shader.TileMode tileMode2 = bitmapState.mTileModeY;
      if (tileMode1 == null && tileMode2 == null) {
        paint.setShader(null);
      } else {
        if (tileMode1 == null)
          tileMode1 = Shader.TileMode.CLAMP; 
        if (tileMode2 == null)
          tileMode2 = Shader.TileMode.CLAMP; 
        paint.setShader((Shader)new BitmapShader(bitmap, tileMode1, tileMode2));
      } 
      bitmapState.mRebuildShader = false;
    } 
    if (bitmapState.mBaseAlpha != 1.0F) {
      Paint paint1 = getPaint();
      b = paint1.getAlpha();
      paint1.setAlpha((int)(b * bitmapState.mBaseAlpha + 0.5F));
    } else {
      b = -1;
    } 
    if (this.mBlendModeFilter != null && paint.getColorFilter() == null) {
      paint.setColorFilter((ColorFilter)this.mBlendModeFilter);
      bool = true;
    } else {
      bool = false;
    } 
    updateDstRectAndInsetsIfDirty();
    Shader shader = paint.getShader();
    boolean bool1 = needMirroring();
    if (shader == null) {
      if (bool1) {
        paramCanvas.save();
        paramCanvas.translate((this.mDstRect.right - this.mDstRect.left), 0.0F);
        paramCanvas.scale(-1.0F, 1.0F);
      } 
      paramCanvas.drawBitmap(bitmap, null, this.mDstRect, paint);
      if (bool1)
        paramCanvas.restore(); 
    } else {
      updateShaderMatrix(bitmap, paint, shader, bool1);
      paramCanvas.drawRect(this.mDstRect, paint);
    } 
    if (bool)
      paint.setColorFilter(null); 
    if (b >= 0)
      paint.setAlpha(b); 
  }
  
  public int getAlpha() {
    return this.mBitmapState.mPaint.getAlpha();
  }
  
  public final Bitmap getBitmap() {
    return this.mBitmapState.mBitmap;
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mBitmapState.getChangingConfigurations();
  }
  
  public ColorFilter getColorFilter() {
    return this.mBitmapState.mPaint.getColorFilter();
  }
  
  public final Drawable.ConstantState getConstantState() {
    BitmapState bitmapState = this.mBitmapState;
    bitmapState.mChangingConfigurations |= getChangingConfigurations();
    return this.mBitmapState;
  }
  
  public int getGravity() {
    return this.mBitmapState.mGravity;
  }
  
  public int getIntrinsicHeight() {
    return this.mBitmapHeight;
  }
  
  public int getIntrinsicWidth() {
    return this.mBitmapWidth;
  }
  
  public int getOpacity() {
    int i = this.mBitmapState.mGravity;
    byte b = -3;
    if (i != 119)
      return -3; 
    Bitmap bitmap = this.mBitmapState.mBitmap;
    if (bitmap != null && !bitmap.hasAlpha() && this.mBitmapState.mPaint.getAlpha() >= 255)
      b = -1; 
    return b;
  }
  
  public Insets getOpticalInsets() {
    updateDstRectAndInsetsIfDirty();
    return this.mOpticalInsets;
  }
  
  public void getOutline(Outline paramOutline) {
    boolean bool;
    float f;
    updateDstRectAndInsetsIfDirty();
    paramOutline.setRect(this.mDstRect);
    if (this.mBitmapState.mBitmap != null && !this.mBitmapState.mBitmap.hasAlpha()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      f = getAlpha() / 255.0F;
    } else {
      f = 0.0F;
    } 
    paramOutline.setAlpha(f);
  }
  
  public final Paint getPaint() {
    return this.mBitmapState.mPaint;
  }
  
  public Shader.TileMode getTileModeX() {
    return this.mBitmapState.mTileModeX;
  }
  
  public Shader.TileMode getTileModeY() {
    return this.mBitmapState.mTileModeY;
  }
  
  public ColorStateList getTint() {
    return this.mBitmapState.mTint;
  }
  
  public PorterDuff.Mode getTintMode() {
    return BlendMode.blendModeToPorterDuffMode(this.mBitmapState.mBlendMode);
  }
  
  public boolean hasAntiAlias() {
    return this.mBitmapState.mPaint.isAntiAlias();
  }
  
  public boolean hasFocusStateSpecified() {
    boolean bool;
    if (this.mBitmapState.mTint != null && this.mBitmapState.mTint.hasFocusStateSpecified()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasMipMap() {
    boolean bool;
    if (this.mBitmapState.mBitmap != null && this.mBitmapState.mBitmap.hasMipMap()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.BitmapDrawable);
    updateStateFromTypedArray(typedArray, this.mSrcDensityOverride);
    verifyRequiredAttributes(typedArray);
    typedArray.recycle();
    updateLocalState(paramResources);
  }
  
  public final boolean isAutoMirrored() {
    return this.mBitmapState.mAutoMirrored;
  }
  
  public boolean isFilterBitmap() {
    return this.mBitmapState.mPaint.isFilterBitmap();
  }
  
  public boolean isStateful() {
    boolean bool;
    if ((this.mBitmapState.mTint != null && this.mBitmapState.mTint.isStateful()) || super.isStateful()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mBitmapState = new BitmapState(this.mBitmapState);
      this.mMutated = true;
    } 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    this.mDstRectAndInsetsDirty = true;
    Bitmap bitmap = this.mBitmapState.mBitmap;
    Shader shader = this.mBitmapState.mPaint.getShader();
    if (bitmap != null && shader != null)
      updateShaderMatrix(bitmap, this.mBitmapState.mPaint, shader, needMirroring()); 
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    BitmapState bitmapState = this.mBitmapState;
    if (bitmapState.mTint != null && bitmapState.mBlendMode != null) {
      this.mBlendModeFilter = updateBlendModeFilter(this.mBlendModeFilter, bitmapState.mTint, bitmapState.mBlendMode);
      return true;
    } 
    return false;
  }
  
  public void setAlpha(int paramInt) {
    if (paramInt != this.mBitmapState.mPaint.getAlpha()) {
      this.mBitmapState.mPaint.setAlpha(paramInt);
      invalidateSelf();
    } 
  }
  
  public void setAntiAlias(boolean paramBoolean) {
    this.mBitmapState.mPaint.setAntiAlias(paramBoolean);
    invalidateSelf();
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    if (this.mBitmapState.mAutoMirrored != paramBoolean) {
      this.mBitmapState.mAutoMirrored = paramBoolean;
      invalidateSelf();
    } 
  }
  
  public void setBitmap(Bitmap paramBitmap) {
    if (this.mBitmapState.mBitmap != paramBitmap) {
      this.mBitmapState.mBitmap = paramBitmap;
      computeBitmapSize();
      invalidateSelf();
    } 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    this.mBitmapState.mPaint.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setDither(boolean paramBoolean) {
    this.mBitmapState.mPaint.setDither(paramBoolean);
    invalidateSelf();
  }
  
  public void setFilterBitmap(boolean paramBoolean) {
    this.mBitmapState.mPaint.setFilterBitmap(paramBoolean);
    invalidateSelf();
  }
  
  public void setGravity(int paramInt) {
    if (this.mBitmapState.mGravity != paramInt) {
      this.mBitmapState.mGravity = paramInt;
      this.mDstRectAndInsetsDirty = true;
      invalidateSelf();
    } 
  }
  
  public void setMipMap(boolean paramBoolean) {
    if (this.mBitmapState.mBitmap != null) {
      this.mBitmapState.mBitmap.setHasMipMap(paramBoolean);
      invalidateSelf();
    } 
  }
  
  public void setTargetDensity(int paramInt) {
    if (this.mTargetDensity != paramInt) {
      if (paramInt == 0)
        paramInt = 160; 
      this.mTargetDensity = paramInt;
      if (this.mBitmapState.mBitmap != null)
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
  
  public void setTileModeX(Shader.TileMode paramTileMode) {
    setTileModeXY(paramTileMode, this.mBitmapState.mTileModeY);
  }
  
  public void setTileModeXY(Shader.TileMode paramTileMode1, Shader.TileMode paramTileMode2) {
    BitmapState bitmapState = this.mBitmapState;
    if (bitmapState.mTileModeX != paramTileMode1 || bitmapState.mTileModeY != paramTileMode2) {
      bitmapState.mTileModeX = paramTileMode1;
      bitmapState.mTileModeY = paramTileMode2;
      bitmapState.mRebuildShader = true;
      this.mDstRectAndInsetsDirty = true;
      invalidateSelf();
    } 
  }
  
  public final void setTileModeY(Shader.TileMode paramTileMode) {
    setTileModeXY(this.mBitmapState.mTileModeX, paramTileMode);
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    BitmapState bitmapState = this.mBitmapState;
    if (bitmapState.mBlendMode != paramBlendMode) {
      bitmapState.mBlendMode = paramBlendMode;
      this.mBlendModeFilter = updateBlendModeFilter(this.mBlendModeFilter, this.mBitmapState.mTint, paramBlendMode);
      invalidateSelf();
    } 
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    BitmapState bitmapState = this.mBitmapState;
    if (bitmapState.mTint != paramColorStateList) {
      bitmapState.mTint = paramColorStateList;
      this.mBlendModeFilter = updateBlendModeFilter(this.mBlendModeFilter, paramColorStateList, this.mBitmapState.mBlendMode);
      invalidateSelf();
    } 
  }
  
  public void setXfermode(Xfermode paramXfermode) {
    this.mBitmapState.mPaint.setXfermode(paramXfermode);
    invalidateSelf();
  }
  
  static final class BitmapState extends Drawable.ConstantState {
    boolean mAutoMirrored = false;
    
    float mBaseAlpha = 1.0F;
    
    Bitmap mBitmap = null;
    
    BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
    
    int mChangingConfigurations;
    
    int mGravity = 119;
    
    final Paint mPaint;
    
    boolean mRebuildShader;
    
    int mSrcDensityOverride = 0;
    
    int mTargetDensity = 160;
    
    int[] mThemeAttrs = null;
    
    Shader.TileMode mTileModeX = null;
    
    Shader.TileMode mTileModeY = null;
    
    ColorStateList mTint = null;
    
    BitmapState(Bitmap param1Bitmap) {
      this.mBitmap = param1Bitmap;
      this.mPaint = new Paint(6);
    }
    
    BitmapState(BitmapState param1BitmapState) {
      this.mBitmap = param1BitmapState.mBitmap;
      this.mTint = param1BitmapState.mTint;
      this.mBlendMode = param1BitmapState.mBlendMode;
      this.mThemeAttrs = param1BitmapState.mThemeAttrs;
      this.mChangingConfigurations = param1BitmapState.mChangingConfigurations;
      this.mGravity = param1BitmapState.mGravity;
      this.mTileModeX = param1BitmapState.mTileModeX;
      this.mTileModeY = param1BitmapState.mTileModeY;
      this.mSrcDensityOverride = param1BitmapState.mSrcDensityOverride;
      this.mTargetDensity = param1BitmapState.mTargetDensity;
      this.mBaseAlpha = param1BitmapState.mBaseAlpha;
      this.mPaint = new Paint(param1BitmapState.mPaint);
      this.mRebuildShader = param1BitmapState.mRebuildShader;
      this.mAutoMirrored = param1BitmapState.mAutoMirrored;
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
      return new BitmapDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new BitmapDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/BitmapDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */