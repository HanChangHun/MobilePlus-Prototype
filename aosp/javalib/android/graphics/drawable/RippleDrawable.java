package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import com.android.internal.R;
import java.io.IOException;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RippleDrawable extends LayerDrawable {
  private static final int MASK_CONTENT = 1;
  
  private static final int MASK_EXPLICIT = 2;
  
  private static final int MASK_NONE = 0;
  
  private static final int MASK_UNKNOWN = -1;
  
  private static final int MAX_RIPPLES = 10;
  
  public static final int RADIUS_AUTO = -1;
  
  private RippleBackground mBackground;
  
  private int mDensity;
  
  private final Rect mDirtyBounds = new Rect();
  
  private final Rect mDrawingBounds = new Rect();
  
  private RippleForeground[] mExitingRipples;
  
  private int mExitingRipplesCount = 0;
  
  private boolean mForceSoftware;
  
  private boolean mHasPending;
  
  private boolean mHasValidMask;
  
  private final Rect mHotspotBounds = new Rect();
  
  private Drawable mMask;
  
  private Bitmap mMaskBuffer;
  
  private Canvas mMaskCanvas;
  
  private PorterDuffColorFilter mMaskColorFilter;
  
  private Matrix mMaskMatrix;
  
  private BitmapShader mMaskShader;
  
  private boolean mOverrideBounds;
  
  private float mPendingX;
  
  private float mPendingY;
  
  private RippleForeground mRipple;
  
  private boolean mRippleActive;
  
  private Paint mRipplePaint;
  
  private RippleState mState;
  
  private final Rect mTempRect = new Rect();
  
  RippleDrawable() {
    this(new RippleState(null, null, null), (Resources)null);
  }
  
  public RippleDrawable(ColorStateList paramColorStateList, Drawable paramDrawable1, Drawable paramDrawable2) {
    this(new RippleState(null, null, null), (Resources)null);
    if (paramColorStateList != null) {
      if (paramDrawable1 != null)
        addLayer(paramDrawable1, (int[])null, 0, 0, 0, 0, 0); 
      if (paramDrawable2 != null)
        addLayer(paramDrawable2, (int[])null, 16908334, 0, 0, 0, 0); 
      setColor(paramColorStateList);
      ensurePadding();
      refreshPadding();
      updateLocalState();
      return;
    } 
    throw new IllegalArgumentException("RippleDrawable requires a non-null color");
  }
  
  private RippleDrawable(RippleState paramRippleState, Resources paramResources) {
    paramRippleState = new RippleState(paramRippleState, this, paramResources);
    this.mState = paramRippleState;
    this.mLayerState = paramRippleState;
    this.mDensity = Drawable.resolveDensity(paramResources, this.mState.mDensity);
    if (this.mState.mNumChildren > 0) {
      ensurePadding();
      refreshPadding();
    } 
    updateLocalState();
  }
  
  private void cancelExitingRipples() {
    int i = this.mExitingRipplesCount;
    RippleForeground[] arrayOfRippleForeground = this.mExitingRipples;
    for (byte b = 0; b < i; b++)
      arrayOfRippleForeground[b].end(); 
    if (arrayOfRippleForeground != null)
      Arrays.fill((Object[])arrayOfRippleForeground, 0, i, (Object)null); 
    this.mExitingRipplesCount = 0;
    invalidateSelf(false);
  }
  
  private void clearHotspots() {
    RippleForeground rippleForeground = this.mRipple;
    if (rippleForeground != null) {
      rippleForeground.end();
      this.mRipple = null;
      this.mRippleActive = false;
    } 
    RippleBackground rippleBackground = this.mBackground;
    if (rippleBackground != null)
      rippleBackground.setState(false, false, false); 
    cancelExitingRipples();
  }
  
  private void drawBackgroundAndRipples(Canvas paramCanvas) {
    RippleForeground rippleForeground = this.mRipple;
    RippleBackground rippleBackground = this.mBackground;
    int i = this.mExitingRipplesCount;
    if (rippleForeground == null && i <= 0 && (rippleBackground == null || !rippleBackground.isVisible()))
      return; 
    float f1 = this.mHotspotBounds.exactCenterX();
    float f2 = this.mHotspotBounds.exactCenterY();
    paramCanvas.translate(f1, f2);
    Paint paint = getRipplePaint();
    if (rippleBackground != null && rippleBackground.isVisible())
      rippleBackground.draw(paramCanvas, paint); 
    if (i > 0) {
      RippleForeground[] arrayOfRippleForeground = this.mExitingRipples;
      for (byte b = 0; b < i; b++)
        arrayOfRippleForeground[b].draw(paramCanvas, paint); 
    } 
    if (rippleForeground != null)
      rippleForeground.draw(paramCanvas, paint); 
    paramCanvas.translate(-f1, -f2);
  }
  
  private void drawContent(Canvas paramCanvas) {
    LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      if ((arrayOfChildDrawable[b]).mId != 16908334)
        (arrayOfChildDrawable[b]).mDrawable.draw(paramCanvas); 
    } 
  }
  
  private void drawMask(Canvas paramCanvas) {
    this.mMask.draw(paramCanvas);
  }
  
  private int getMaskType() {
    if (this.mRipple == null && this.mExitingRipplesCount <= 0) {
      RippleBackground rippleBackground = this.mBackground;
      if (rippleBackground == null || !rippleBackground.isVisible())
        return -1; 
    } 
    Drawable drawable = this.mMask;
    if (drawable != null)
      return (drawable.getOpacity() == -1) ? 0 : 2; 
    LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      if ((arrayOfChildDrawable[b]).mDrawable.getOpacity() != -1)
        return 1; 
    } 
    return 0;
  }
  
  private boolean isBounded() {
    boolean bool;
    if (getNumberOfLayers() > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void onHotspotBoundsChanged() {
    int i = this.mExitingRipplesCount;
    RippleForeground[] arrayOfRippleForeground = this.mExitingRipples;
    for (byte b = 0; b < i; b++)
      arrayOfRippleForeground[b].onHotspotBoundsChanged(); 
    RippleForeground rippleForeground = this.mRipple;
    if (rippleForeground != null)
      rippleForeground.onHotspotBoundsChanged(); 
    RippleBackground rippleBackground = this.mBackground;
    if (rippleBackground != null)
      rippleBackground.onHotspotBoundsChanged(); 
  }
  
  private void pruneRipples() {
    int i = 0;
    RippleForeground[] arrayOfRippleForeground = this.mExitingRipples;
    int j = this.mExitingRipplesCount;
    int k = 0;
    while (k < j) {
      int m = i;
      if (!arrayOfRippleForeground[k].hasFinishedExit()) {
        arrayOfRippleForeground[i] = arrayOfRippleForeground[k];
        m = i + 1;
      } 
      k++;
      i = m;
    } 
    for (k = i; k < j; k++)
      arrayOfRippleForeground[k] = null; 
    this.mExitingRipplesCount = i;
  }
  
  private void setBackgroundActive(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    if (this.mBackground == null && (paramBoolean1 || paramBoolean2)) {
      RippleBackground rippleBackground1 = new RippleBackground(this, this.mHotspotBounds, isBounded());
      this.mBackground = rippleBackground1;
      rippleBackground1.setup(this.mState.mMaxRadius, this.mDensity);
    } 
    RippleBackground rippleBackground = this.mBackground;
    if (rippleBackground != null)
      rippleBackground.setState(paramBoolean2, paramBoolean1, paramBoolean3); 
  }
  
  private void setRippleActive(boolean paramBoolean) {
    if (this.mRippleActive != paramBoolean) {
      this.mRippleActive = paramBoolean;
      if (paramBoolean) {
        tryRippleEnter();
      } else {
        tryRippleExit();
      } 
    } 
  }
  
  private void tryRippleEnter() {
    if (this.mExitingRipplesCount >= 10)
      return; 
    if (this.mRipple == null) {
      float f1;
      float f2;
      if (this.mHasPending) {
        this.mHasPending = false;
        f1 = this.mPendingX;
        f2 = this.mPendingY;
      } else {
        f1 = this.mHotspotBounds.exactCenterX();
        f2 = this.mHotspotBounds.exactCenterY();
      } 
      this.mRipple = new RippleForeground(this, this.mHotspotBounds, f1, f2, this.mForceSoftware);
    } 
    this.mRipple.setup(this.mState.mMaxRadius, this.mDensity);
    this.mRipple.enter();
  }
  
  private void tryRippleExit() {
    if (this.mRipple != null) {
      if (this.mExitingRipples == null)
        this.mExitingRipples = new RippleForeground[10]; 
      RippleForeground[] arrayOfRippleForeground = this.mExitingRipples;
      int i = this.mExitingRipplesCount;
      this.mExitingRipplesCount = i + 1;
      RippleForeground rippleForeground = this.mRipple;
      arrayOfRippleForeground[i] = rippleForeground;
      rippleForeground.exit();
      this.mRipple = null;
    } 
  }
  
  private void updateLocalState() {
    this.mMask = findDrawableByLayerId(16908334);
  }
  
  private void updateMaskShaderIfNeeded() {
    Bitmap bitmap1;
    if (this.mHasValidMask)
      return; 
    int i = getMaskType();
    if (i == -1)
      return; 
    this.mHasValidMask = true;
    Rect rect = getBounds();
    if (i == 0 || rect.isEmpty()) {
      bitmap1 = this.mMaskBuffer;
      if (bitmap1 != null) {
        bitmap1.recycle();
        this.mMaskBuffer = null;
        this.mMaskShader = null;
        this.mMaskCanvas = null;
      } 
      this.mMaskMatrix = null;
      this.mMaskColorFilter = null;
      return;
    } 
    Bitmap bitmap2 = this.mMaskBuffer;
    if (bitmap2 == null || bitmap2.getWidth() != bitmap1.width() || this.mMaskBuffer.getHeight() != bitmap1.height()) {
      bitmap2 = this.mMaskBuffer;
      if (bitmap2 != null)
        bitmap2.recycle(); 
      bitmap2 = Bitmap.createBitmap(bitmap1.width(), bitmap1.height(), Bitmap.Config.ALPHA_8);
      this.mMaskBuffer = bitmap2;
      this.mMaskShader = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
      this.mMaskCanvas = new Canvas(this.mMaskBuffer);
    } else {
      this.mMaskBuffer.eraseColor(0);
    } 
    Matrix matrix = this.mMaskMatrix;
    if (matrix == null) {
      this.mMaskMatrix = new Matrix();
    } else {
      matrix.reset();
    } 
    if (this.mMaskColorFilter == null)
      this.mMaskColorFilter = new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_IN); 
    int j = ((Rect)bitmap1).left;
    int k = ((Rect)bitmap1).top;
    this.mMaskCanvas.translate(-j, -k);
    if (i == 2) {
      drawMask(this.mMaskCanvas);
    } else if (i == 1) {
      drawContent(this.mMaskCanvas);
    } 
    this.mMaskCanvas.translate(j, k);
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) throws XmlPullParserException {
    RippleState rippleState2 = this.mState;
    rippleState2.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    rippleState2.mTouchThemeAttrs = paramTypedArray.extractThemeAttrs();
    ColorStateList colorStateList = paramTypedArray.getColorStateList(0);
    if (colorStateList != null)
      this.mState.mColor = colorStateList; 
    RippleState rippleState1 = this.mState;
    rippleState1.mMaxRadius = paramTypedArray.getDimensionPixelSize(1, rippleState1.mMaxRadius);
  }
  
  private void verifyRequiredAttributes(TypedArray paramTypedArray) throws XmlPullParserException {
    if (this.mState.mColor != null || (this.mState.mTouchThemeAttrs != null && this.mState.mTouchThemeAttrs[0] != 0))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramTypedArray.getPositionDescription());
    stringBuilder.append(": <ripple> requires a valid color attribute");
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    RippleState rippleState = this.mState;
    if (rippleState == null)
      return; 
    if (rippleState.mTouchThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(rippleState.mTouchThemeAttrs, R.styleable.RippleDrawable);
      try {
        updateStateFromTypedArray(typedArray);
        verifyRequiredAttributes(typedArray);
        typedArray.recycle();
      } catch (XmlPullParserException xmlPullParserException) {
        rethrowAsRuntimeException((Exception)xmlPullParserException);
        typedArray.recycle();
      } finally {}
    } 
    if (rippleState.mColor != null && rippleState.mColor.canApplyTheme())
      rippleState.mColor = rippleState.mColor.obtainForTheme(paramTheme); 
    updateLocalState();
  }
  
  public boolean canApplyTheme() {
    boolean bool;
    RippleState rippleState = this.mState;
    if ((rippleState != null && rippleState.canApplyTheme()) || super.canApplyTheme()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  RippleState createConstantState(LayerDrawable.LayerState paramLayerState, Resources paramResources) {
    return new RippleState(paramLayerState, this, paramResources);
  }
  
  public void draw(Canvas paramCanvas) {
    pruneRipples();
    Rect rect = getDirtyBounds();
    int i = paramCanvas.save(2);
    if (isBounded())
      paramCanvas.clipRect(rect); 
    drawContent(paramCanvas);
    drawBackgroundAndRipples(paramCanvas);
    paramCanvas.restoreToCount(i);
  }
  
  public Drawable.ConstantState getConstantState() {
    return this.mState;
  }
  
  public Rect getDirtyBounds() {
    if (!isBounded()) {
      Rect rect1 = this.mDrawingBounds;
      Rect rect2 = this.mDirtyBounds;
      rect2.set(rect1);
      rect1.setEmpty();
      int i = (int)this.mHotspotBounds.exactCenterX();
      int j = (int)this.mHotspotBounds.exactCenterY();
      Rect rect3 = this.mTempRect;
      RippleForeground[] arrayOfRippleForeground = this.mExitingRipples;
      int k = this.mExitingRipplesCount;
      for (byte b = 0; b < k; b++) {
        arrayOfRippleForeground[b].getBounds(rect3);
        rect3.offset(i, j);
        rect1.union(rect3);
      } 
      RippleBackground rippleBackground = this.mBackground;
      if (rippleBackground != null) {
        rippleBackground.getBounds(rect3);
        rect3.offset(i, j);
        rect1.union(rect3);
      } 
      rect2.union(rect1);
      rect2.union(super.getDirtyBounds());
      return rect2;
    } 
    return getBounds();
  }
  
  public void getHotspotBounds(Rect paramRect) {
    paramRect.set(this.mHotspotBounds);
  }
  
  public int getOpacity() {
    return -3;
  }
  
  public void getOutline(Outline paramOutline) {
    LayerDrawable.LayerState layerState = this.mLayerState;
    LayerDrawable.ChildDrawable[] arrayOfChildDrawable = layerState.mChildren;
    int i = layerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      if ((arrayOfChildDrawable[b]).mId != 16908334) {
        (arrayOfChildDrawable[b]).mDrawable.getOutline(paramOutline);
        if (!paramOutline.isEmpty())
          return; 
      } 
    } 
  }
  
  public int getRadius() {
    return this.mState.mMaxRadius;
  }
  
  Paint getRipplePaint() {
    if (this.mRipplePaint == null) {
      Paint paint1 = new Paint();
      this.mRipplePaint = paint1;
      paint1.setAntiAlias(true);
      this.mRipplePaint.setStyle(Paint.Style.FILL);
    } 
    float f1 = this.mHotspotBounds.exactCenterX();
    float f2 = this.mHotspotBounds.exactCenterY();
    updateMaskShaderIfNeeded();
    if (this.mMaskShader != null) {
      Rect rect = getBounds();
      this.mMaskMatrix.setTranslate(rect.left - f1, rect.top - f2);
      this.mMaskShader.setLocalMatrix(this.mMaskMatrix);
    } 
    int i = this.mState.mColor.getColorForState(getState(), -16777216);
    int j = i;
    if (Color.alpha(i) > 128)
      j = 0xFFFFFF & i | Integer.MIN_VALUE; 
    Paint paint = this.mRipplePaint;
    PorterDuffColorFilter porterDuffColorFilter = this.mMaskColorFilter;
    if (porterDuffColorFilter != null) {
      i = j | 0xFF000000;
      if (porterDuffColorFilter.getColor() != i)
        this.mMaskColorFilter = new PorterDuffColorFilter(i, this.mMaskColorFilter.getMode()); 
      paint.setColor(0xFF000000 & j);
      paint.setColorFilter((ColorFilter)this.mMaskColorFilter);
      paint.setShader((Shader)this.mMaskShader);
    } else {
      paint.setColor(j);
      paint.setColorFilter(null);
      paint.setShader(null);
    } 
    return paint;
  }
  
  public boolean hasFocusStateSpecified() {
    return true;
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.RippleDrawable);
    setPaddingMode(1);
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    updateStateFromTypedArray(typedArray);
    verifyRequiredAttributes(typedArray);
    typedArray.recycle();
    updateLocalState();
  }
  
  public void invalidateSelf() {
    invalidateSelf(true);
  }
  
  void invalidateSelf(boolean paramBoolean) {
    super.invalidateSelf();
    if (paramBoolean)
      this.mHasValidMask = false; 
  }
  
  public boolean isProjected() {
    if (isBounded())
      return false; 
    int i = this.mState.mMaxRadius;
    Rect rect1 = getBounds();
    Rect rect2 = this.mHotspotBounds;
    return !(i != -1 && i <= rect2.width() / 2 && i <= rect2.height() / 2 && (rect1.equals(rect2) || rect1.contains(rect2)));
  }
  
  public boolean isStateful() {
    return true;
  }
  
  public void jumpToCurrentState() {
    super.jumpToCurrentState();
    RippleForeground rippleForeground = this.mRipple;
    if (rippleForeground != null)
      rippleForeground.end(); 
    RippleBackground rippleBackground = this.mBackground;
    if (rippleBackground != null)
      rippleBackground.jumpToFinal(); 
    cancelExitingRipples();
  }
  
  public Drawable mutate() {
    super.mutate();
    this.mState = (RippleState)this.mLayerState;
    this.mMask = findDrawableByLayerId(16908334);
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    super.onBoundsChange(paramRect);
    if (!this.mOverrideBounds) {
      this.mHotspotBounds.set(paramRect);
      onHotspotBoundsChanged();
    } 
    int i = this.mExitingRipplesCount;
    RippleForeground[] arrayOfRippleForeground = this.mExitingRipples;
    for (byte b = 0; b < i; b++)
      arrayOfRippleForeground[b].onBoundsChange(); 
    RippleBackground rippleBackground = this.mBackground;
    if (rippleBackground != null)
      rippleBackground.onBoundsChange(); 
    RippleForeground rippleForeground = this.mRipple;
    if (rippleForeground != null)
      rippleForeground.onBoundsChange(); 
    invalidateSelf();
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    boolean bool = super.onStateChange(paramArrayOfint);
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    int i = paramArrayOfint.length;
    boolean bool5 = false;
    byte b = 0;
    while (b < i) {
      boolean bool7;
      boolean bool8;
      boolean bool9;
      int j = paramArrayOfint[b];
      if (j == 16842910) {
        bool7 = true;
        bool8 = bool2;
        bool9 = bool3;
      } else if (j == 16842908) {
        bool9 = true;
        bool7 = bool1;
        bool8 = bool2;
      } else if (j == 16842919) {
        bool8 = true;
        bool7 = bool1;
        bool9 = bool3;
      } else {
        bool7 = bool1;
        bool8 = bool2;
        bool9 = bool3;
        if (j == 16843623) {
          bool4 = true;
          bool9 = bool3;
          bool8 = bool2;
          bool7 = bool1;
        } 
      } 
      b++;
      bool1 = bool7;
      bool2 = bool8;
      bool3 = bool9;
    } 
    boolean bool6 = bool5;
    if (bool1) {
      bool6 = bool5;
      if (bool2)
        bool6 = true; 
    } 
    setRippleActive(bool6);
    setBackgroundActive(bool4, bool3, bool2);
    return bool;
  }
  
  public void setColor(ColorStateList paramColorStateList) {
    this.mState.mColor = paramColorStateList;
    invalidateSelf(false);
  }
  
  public boolean setDrawableByLayerId(int paramInt, Drawable paramDrawable) {
    if (super.setDrawableByLayerId(paramInt, paramDrawable)) {
      if (paramInt == 16908334) {
        this.mMask = paramDrawable;
        this.mHasValidMask = false;
      } 
      return true;
    } 
    return false;
  }
  
  public void setForceSoftware(boolean paramBoolean) {
    this.mForceSoftware = paramBoolean;
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2) {
    if (this.mRipple == null || this.mBackground == null) {
      this.mPendingX = paramFloat1;
      this.mPendingY = paramFloat2;
      this.mHasPending = true;
    } 
    RippleForeground rippleForeground = this.mRipple;
    if (rippleForeground != null)
      rippleForeground.move(paramFloat1, paramFloat2); 
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mOverrideBounds = true;
    this.mHotspotBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
    onHotspotBoundsChanged();
  }
  
  public void setPaddingMode(int paramInt) {
    super.setPaddingMode(paramInt);
  }
  
  public void setRadius(int paramInt) {
    this.mState.mMaxRadius = paramInt;
    invalidateSelf(false);
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    paramBoolean2 = super.setVisible(paramBoolean1, paramBoolean2);
    if (!paramBoolean1) {
      clearHotspots();
    } else if (paramBoolean2) {
      if (this.mRippleActive)
        tryRippleEnter(); 
      jumpToCurrentState();
    } 
    return paramBoolean2;
  }
  
  static class RippleState extends LayerDrawable.LayerState {
    ColorStateList mColor = ColorStateList.valueOf(-65281);
    
    int mMaxRadius = -1;
    
    int[] mTouchThemeAttrs;
    
    public RippleState(LayerDrawable.LayerState param1LayerState, RippleDrawable param1RippleDrawable, Resources param1Resources) {
      super(param1LayerState, param1RippleDrawable, param1Resources);
      if (param1LayerState != null && param1LayerState instanceof RippleState) {
        RippleState rippleState = (RippleState)param1LayerState;
        this.mTouchThemeAttrs = rippleState.mTouchThemeAttrs;
        this.mColor = rippleState.mColor;
        this.mMaxRadius = rippleState.mMaxRadius;
        if (rippleState.mDensity != this.mDensity)
          applyDensityScaling(param1LayerState.mDensity, this.mDensity); 
      } 
    }
    
    private void applyDensityScaling(int param1Int1, int param1Int2) {
      int i = this.mMaxRadius;
      if (i != -1)
        this.mMaxRadius = Drawable.scaleFromDensity(i, param1Int1, param1Int2, true); 
    }
    
    public boolean canApplyTheme() {
      if (this.mTouchThemeAttrs == null) {
        ColorStateList colorStateList = this.mColor;
        return ((colorStateList != null && colorStateList.canApplyTheme()) || super.canApplyTheme());
      } 
      return true;
    }
    
    public int getChangingConfigurations() {
      byte b;
      int i = super.getChangingConfigurations();
      ColorStateList colorStateList = this.mColor;
      if (colorStateList != null) {
        b = colorStateList.getChangingConfigurations();
      } else {
        b = 0;
      } 
      return i | b;
    }
    
    public Drawable newDrawable() {
      return new RippleDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new RippleDrawable(this, param1Resources);
    }
    
    protected void onDensityChanged(int param1Int1, int param1Int2) {
      super.onDensityChanged(param1Int1, param1Int2);
      applyDensityScaling(param1Int1, param1Int2);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/RippleDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */