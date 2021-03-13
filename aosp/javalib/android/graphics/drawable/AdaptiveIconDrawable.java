package android.graphics.drawable;

import android.app.ActivityThread;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.PathParser;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AdaptiveIconDrawable extends Drawable implements Drawable.Callback {
  private static final int BACKGROUND_ID = 0;
  
  private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667F;
  
  private static final float EXTRA_INSET_PERCENTAGE = 0.25F;
  
  private static final int FOREGROUND_ID = 1;
  
  public static final float MASK_SIZE = 100.0F;
  
  private static final float SAFEZONE_SCALE = 0.9166667F;
  
  private static Path sMask;
  
  private final Canvas mCanvas;
  
  private boolean mChildRequestedInvalidation;
  
  private Rect mHotspotBounds;
  
  LayerState mLayerState;
  
  private Bitmap mLayersBitmap;
  
  private Shader mLayersShader;
  
  private final Path mMask;
  
  private final Matrix mMaskMatrix;
  
  private final Path mMaskScaleOnly;
  
  private boolean mMutated;
  
  private Paint mPaint;
  
  private boolean mSuspendChildInvalidation;
  
  private final Rect mTmpOutRect;
  
  private final Region mTransparentRegion;
  
  AdaptiveIconDrawable() {
    this((LayerState)null, (Resources)null);
  }
  
  AdaptiveIconDrawable(LayerState paramLayerState, Resources paramResources) {
    Resources resources;
    this.mTmpOutRect = new Rect();
    this.mPaint = new Paint(7);
    this.mLayerState = createConstantState(paramLayerState, paramResources);
    if (ActivityThread.currentActivityThread() == null) {
      resources = Resources.getSystem();
    } else {
      resources = ActivityThread.currentActivityThread().getApplication().getResources();
    } 
    sMask = PathParser.createPathFromPathData(resources.getString(17039904));
    this.mMask = new Path(sMask);
    this.mMaskScaleOnly = new Path(this.mMask);
    this.mMaskMatrix = new Matrix();
    this.mCanvas = new Canvas();
    this.mTransparentRegion = new Region();
  }
  
  public AdaptiveIconDrawable(Drawable paramDrawable1, Drawable paramDrawable2) {
    this((LayerState)null, (Resources)null);
    if (paramDrawable1 != null)
      addLayer(0, createChildDrawable(paramDrawable1)); 
    if (paramDrawable2 != null)
      addLayer(1, createChildDrawable(paramDrawable2)); 
  }
  
  private void addLayer(int paramInt, ChildDrawable paramChildDrawable) {
    this.mLayerState.mChildren[paramInt] = paramChildDrawable;
    this.mLayerState.invalidateCache();
  }
  
  private ChildDrawable createChildDrawable(Drawable paramDrawable) {
    ChildDrawable childDrawable = new ChildDrawable(this.mLayerState.mDensity);
    childDrawable.mDrawable = paramDrawable;
    childDrawable.mDrawable.setCallback(this);
    LayerState layerState = this.mLayerState;
    layerState.mChildrenChangingConfigurations |= childDrawable.mDrawable.getChangingConfigurations();
    return childDrawable;
  }
  
  public static float getExtraInsetFraction() {
    return 0.25F;
  }
  
  public static float getExtraInsetPercentage() {
    return 0.25F;
  }
  
  private int getMaxIntrinsicHeight() {
    int i = -1;
    byte b = 0;
    while (true) {
      LayerState layerState = this.mLayerState;
      if (b < 2) {
        int j;
        ChildDrawable childDrawable = layerState.mChildren[b];
        if (childDrawable.mDrawable == null) {
          j = i;
        } else {
          int k = childDrawable.mDrawable.getIntrinsicHeight();
          j = i;
          if (k > i)
            j = k; 
        } 
        b++;
        i = j;
        continue;
      } 
      return i;
    } 
  }
  
  private int getMaxIntrinsicWidth() {
    int i = -1;
    byte b = 0;
    while (true) {
      LayerState layerState = this.mLayerState;
      if (b < 2) {
        int j;
        ChildDrawable childDrawable = layerState.mChildren[b];
        if (childDrawable.mDrawable == null) {
          j = i;
        } else {
          int k = childDrawable.mDrawable.getIntrinsicWidth();
          j = i;
          if (k > i)
            j = k; 
        } 
        b++;
        i = j;
        continue;
      } 
      return i;
    } 
  }
  
  private void inflateLayers(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    LayerState layerState = this.mLayerState;
    int i = paramXmlPullParser.getDepth() + 1;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j != 2 || k > i)
            continue; 
          String str = paramXmlPullParser.getName();
          if (str.equals("background")) {
            j = 0;
          } else if (str.equals("foreground")) {
            j = 1;
          } else {
            continue;
          } 
          ChildDrawable childDrawable = new ChildDrawable(layerState.mDensity);
          TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AdaptiveIconDrawableLayer);
          updateLayerFromTypedArray(childDrawable, typedArray);
          typedArray.recycle();
          if (childDrawable.mDrawable == null && childDrawable.mThemeAttrs == null)
            while (true) {
              k = paramXmlPullParser.next();
              if (k == 4)
                continue; 
              if (k == 2) {
                childDrawable.mDrawable = Drawable.createFromXmlInnerForDensity(paramResources, paramXmlPullParser, paramAttributeSet, this.mLayerState.mSrcDensityOverride, paramTheme);
                childDrawable.mDrawable.setCallback(this);
                layerState.mChildrenChangingConfigurations |= childDrawable.mDrawable.getChangingConfigurations();
                break;
              } 
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(paramXmlPullParser.getPositionDescription());
              stringBuilder.append(": <foreground> or <background> tag requires a 'drawable'attribute or child tag defining a drawable");
              throw new XmlPullParserException(stringBuilder.toString());
            }  
          addLayer(j, childDrawable);
          continue;
        } 
      } 
      break;
    } 
  }
  
  private void resumeChildInvalidation() {
    this.mSuspendChildInvalidation = false;
    if (this.mChildRequestedInvalidation) {
      this.mChildRequestedInvalidation = false;
      invalidateSelf();
    } 
  }
  
  private void suspendChildInvalidation() {
    this.mSuspendChildInvalidation = true;
  }
  
  private void updateLayerBounds(Rect paramRect) {
    if (paramRect.isEmpty())
      return; 
    try {
      suspendChildInvalidation();
      updateLayerBoundsInternal(paramRect);
      updateMaskBoundsInternal(paramRect);
      return;
    } finally {
      resumeChildInvalidation();
    } 
  }
  
  private void updateLayerBoundsInternal(Rect paramRect) {
    int i = paramRect.width() / 2;
    int j = paramRect.height() / 2;
    for (byte b = 0; b < 2; b++) {
      ChildDrawable childDrawable = this.mLayerState.mChildren[b];
      if (childDrawable != null) {
        Drawable drawable = childDrawable.mDrawable;
        if (drawable != null) {
          int k = (int)(paramRect.width() / 1.3333334F);
          int m = (int)(paramRect.height() / 1.3333334F);
          Rect rect = this.mTmpOutRect;
          rect.set(i - k, j - m, i + k, j + m);
          drawable.setBounds(rect);
        } 
      } 
    } 
  }
  
  private void updateLayerFromTypedArray(ChildDrawable paramChildDrawable, TypedArray paramTypedArray) {
    LayerState layerState = this.mLayerState;
    layerState.mChildrenChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    paramChildDrawable.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    Drawable drawable = paramTypedArray.getDrawableForDensity(0, layerState.mSrcDensityOverride);
    if (drawable != null) {
      if (paramChildDrawable.mDrawable != null)
        paramChildDrawable.mDrawable.setCallback(null); 
      paramChildDrawable.mDrawable = drawable;
      paramChildDrawable.mDrawable.setCallback(this);
      layerState.mChildrenChangingConfigurations |= paramChildDrawable.mDrawable.getChangingConfigurations();
    } 
  }
  
  private void updateMaskBoundsInternal(Rect paramRect) {
    this.mMaskMatrix.setScale(paramRect.width() / 100.0F, paramRect.height() / 100.0F);
    sMask.transform(this.mMaskMatrix, this.mMaskScaleOnly);
    this.mMaskMatrix.postTranslate(paramRect.left, paramRect.top);
    sMask.transform(this.mMaskMatrix, this.mMask);
    Bitmap bitmap = this.mLayersBitmap;
    if (bitmap == null || bitmap.getWidth() != paramRect.width() || this.mLayersBitmap.getHeight() != paramRect.height())
      this.mLayersBitmap = Bitmap.createBitmap(paramRect.width(), paramRect.height(), Bitmap.Config.ARGB_8888); 
    this.mPaint.setShader(null);
    this.mTransparentRegion.setEmpty();
    this.mLayersShader = null;
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    LayerState layerState = this.mLayerState;
    if (layerState == null)
      return; 
    int i = Drawable.resolveDensity(paramTheme.getResources(), 0);
    layerState.setDensity(i);
    ChildDrawable[] arrayOfChildDrawable = layerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      ChildDrawable childDrawable = arrayOfChildDrawable[b];
      childDrawable.setDensity(i);
      if (childDrawable.mThemeAttrs != null) {
        TypedArray typedArray = paramTheme.resolveAttributes(childDrawable.mThemeAttrs, R.styleable.AdaptiveIconDrawableLayer);
        updateLayerFromTypedArray(childDrawable, typedArray);
        typedArray.recycle();
      } 
      Drawable drawable = childDrawable.mDrawable;
      if (drawable != null && drawable.canApplyTheme()) {
        drawable.applyTheme(paramTheme);
        layerState.mChildrenChangingConfigurations |= drawable.getChangingConfigurations();
      } 
    } 
  }
  
  public boolean canApplyTheme() {
    boolean bool;
    LayerState layerState = this.mLayerState;
    if ((layerState != null && layerState.canApplyTheme()) || super.canApplyTheme()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void clearMutated() {
    super.clearMutated();
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.clearMutated(); 
    } 
    this.mMutated = false;
  }
  
  LayerState createConstantState(LayerState paramLayerState, Resources paramResources) {
    return new LayerState(paramLayerState, this, paramResources);
  }
  
  public void draw(Canvas paramCanvas) {
    Bitmap bitmap = this.mLayersBitmap;
    if (bitmap == null)
      return; 
    if (this.mLayersShader == null) {
      this.mCanvas.setBitmap(bitmap);
      this.mCanvas.drawColor(-16777216);
      byte b = 0;
      while (true) {
        LayerState layerState = this.mLayerState;
        if (b < 2) {
          if (layerState.mChildren[b] != null) {
            Drawable drawable = (this.mLayerState.mChildren[b]).mDrawable;
            if (drawable != null)
              drawable.draw(this.mCanvas); 
          } 
          b++;
          continue;
        } 
        BitmapShader bitmapShader = new BitmapShader(this.mLayersBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.mLayersShader = (Shader)bitmapShader;
        this.mPaint.setShader((Shader)bitmapShader);
        break;
      } 
    } 
    if (this.mMaskScaleOnly != null) {
      Rect rect = getBounds();
      paramCanvas.translate(rect.left, rect.top);
      paramCanvas.drawPath(this.mMaskScaleOnly, this.mPaint);
      paramCanvas.translate(-rect.left, -rect.top);
    } 
  }
  
  public int getAlpha() {
    return this.mPaint.getAlpha();
  }
  
  public Drawable getBackground() {
    return (this.mLayerState.mChildren[0]).mDrawable;
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mLayerState.getChangingConfigurations();
  }
  
  public Drawable.ConstantState getConstantState() {
    if (this.mLayerState.canConstantState()) {
      this.mLayerState.mChangingConfigurations = getChangingConfigurations();
      return this.mLayerState;
    } 
    return null;
  }
  
  public Drawable getForeground() {
    return (this.mLayerState.mChildren[1]).mDrawable;
  }
  
  public void getHotspotBounds(Rect paramRect) {
    Rect rect = this.mHotspotBounds;
    if (rect != null) {
      paramRect.set(rect);
    } else {
      super.getHotspotBounds(paramRect);
    } 
  }
  
  public Path getIconMask() {
    return this.mMask;
  }
  
  public int getIntrinsicHeight() {
    return (int)(getMaxIntrinsicHeight() * 0.6666667F);
  }
  
  public int getIntrinsicWidth() {
    return (int)(getMaxIntrinsicWidth() * 0.6666667F);
  }
  
  public int getOpacity() {
    return -3;
  }
  
  public void getOutline(Outline paramOutline) {
    paramOutline.setPath(this.mMask);
  }
  
  public Region getSafeZone() {
    this.mMaskMatrix.reset();
    this.mMaskMatrix.setScale(0.9166667F, 0.9166667F, getBounds().centerX(), getBounds().centerY());
    Path path = new Path();
    this.mMask.transform(this.mMaskMatrix, path);
    Region region = new Region(getBounds());
    region.setPath(path, region);
    return region;
  }
  
  public int getSourceDrawableResId() {
    int i;
    LayerState layerState = this.mLayerState;
    if (layerState == null) {
      i = 0;
    } else {
      i = layerState.mSourceDrawableId;
    } 
    return i;
  }
  
  public Region getTransparentRegion() {
    if (this.mTransparentRegion.isEmpty()) {
      this.mMask.toggleInverseFillType();
      this.mTransparentRegion.set(getBounds());
      Region region = this.mTransparentRegion;
      region.setPath(this.mMask, region);
      this.mMask.toggleInverseFillType();
    } 
    return this.mTransparentRegion;
  }
  
  public boolean hasFocusStateSpecified() {
    return this.mLayerState.hasFocusStateSpecified();
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    LayerState layerState = this.mLayerState;
    if (layerState == null)
      return; 
    int i = Drawable.resolveDensity(paramResources, 0);
    layerState.setDensity(i);
    layerState.mSrcDensityOverride = this.mSrcDensityOverride;
    layerState.mSourceDrawableId = Resources.getAttributeSetSourceResId(paramAttributeSet);
    ChildDrawable[] arrayOfChildDrawable = layerState.mChildren;
    for (byte b = 0; b < layerState.mChildren.length; b++)
      arrayOfChildDrawable[b].setDensity(i); 
    inflateLayers(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
  }
  
  public void invalidateDrawable(Drawable paramDrawable) {
    if (this.mSuspendChildInvalidation) {
      this.mChildRequestedInvalidation = true;
    } else {
      invalidateSelf();
    } 
  }
  
  public void invalidateSelf() {
    this.mLayersShader = null;
    super.invalidateSelf();
  }
  
  public boolean isAutoMirrored() {
    return this.mLayerState.mAutoMirrored;
  }
  
  public boolean isProjected() {
    if (super.isProjected())
      return true; 
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      if ((arrayOfChildDrawable[b]).mDrawable != null && (arrayOfChildDrawable[b]).mDrawable.isProjected())
        return true; 
    } 
    return false;
  }
  
  public boolean isStateful() {
    return this.mLayerState.isStateful();
  }
  
  public void jumpToCurrentState() {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.jumpToCurrentState(); 
    } 
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mLayerState = createConstantState(this.mLayerState, (Resources)null);
      byte b = 0;
      while (true) {
        LayerState layerState = this.mLayerState;
        if (b < 2) {
          Drawable drawable = (layerState.mChildren[b]).mDrawable;
          if (drawable != null)
            drawable.mutate(); 
          b++;
          continue;
        } 
        this.mMutated = true;
        break;
      } 
    } 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    if (paramRect.isEmpty())
      return; 
    updateLayerBounds(paramRect);
  }
  
  protected boolean onLevelChange(int paramInt) {
    boolean bool = false;
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    byte b = 0;
    while (b < 2) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      boolean bool1 = bool;
      if (drawable != null) {
        bool1 = bool;
        if (drawable.setLevel(paramInt))
          bool1 = true; 
      } 
      b++;
      bool = bool1;
    } 
    if (bool)
      updateLayerBounds(getBounds()); 
    return bool;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    boolean bool = false;
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    byte b = 0;
    while (b < 2) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      boolean bool1 = bool;
      if (drawable != null) {
        bool1 = bool;
        if (drawable.isStateful()) {
          bool1 = bool;
          if (drawable.setState(paramArrayOfint))
            bool1 = true; 
        } 
      } 
      b++;
      bool = bool1;
    } 
    if (bool)
      updateLayerBounds(getBounds()); 
    return bool;
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong) {
    scheduleSelf(paramRunnable, paramLong);
  }
  
  public void setAlpha(int paramInt) {
    this.mPaint.setAlpha(paramInt);
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    LayerState.access$002(this.mLayerState, paramBoolean);
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setAutoMirrored(paramBoolean); 
    } 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setColorFilter(paramColorFilter); 
    } 
  }
  
  public void setDither(boolean paramBoolean) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setDither(paramBoolean); 
    } 
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setHotspot(paramFloat1, paramFloat2); 
    } 
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4); 
    } 
    Rect rect = this.mHotspotBounds;
    if (rect == null) {
      this.mHotspotBounds = new Rect(paramInt1, paramInt2, paramInt3, paramInt4);
    } else {
      rect.set(paramInt1, paramInt2, paramInt3, paramInt4);
    } 
  }
  
  public void setOpacity(int paramInt) {
    this.mLayerState.mOpacityOverride = paramInt;
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setTintBlendMode(paramBlendMode); 
    } 
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setTintList(paramColorStateList); 
    } 
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < 2; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setVisible(paramBoolean1, paramBoolean2); 
    } 
    return bool;
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable) {
    unscheduleSelf(paramRunnable);
  }
  
  static class ChildDrawable {
    public int mDensity;
    
    public Drawable mDrawable;
    
    public int[] mThemeAttrs;
    
    ChildDrawable(int param1Int) {
      this.mDensity = 160;
      this.mDensity = param1Int;
    }
    
    ChildDrawable(ChildDrawable param1ChildDrawable, AdaptiveIconDrawable param1AdaptiveIconDrawable, Resources param1Resources) {
      Drawable drawable2;
      this.mDensity = 160;
      Drawable drawable1 = param1ChildDrawable.mDrawable;
      if (drawable1 != null) {
        Drawable.ConstantState constantState = drawable1.getConstantState();
        if (constantState == null) {
          drawable2 = drawable1;
        } else if (param1Resources != null) {
          drawable2 = drawable2.newDrawable(param1Resources);
        } else {
          drawable2 = drawable2.newDrawable();
        } 
        drawable2.setCallback(param1AdaptiveIconDrawable);
        drawable2.setBounds(drawable1.getBounds());
        drawable2.setLevel(drawable1.getLevel());
      } else {
        drawable2 = null;
      } 
      this.mDrawable = drawable2;
      this.mThemeAttrs = param1ChildDrawable.mThemeAttrs;
      this.mDensity = Drawable.resolveDensity(param1Resources, param1ChildDrawable.mDensity);
    }
    
    public boolean canApplyTheme() {
      if (this.mThemeAttrs == null) {
        Drawable drawable = this.mDrawable;
        return (drawable != null && drawable.canApplyTheme());
      } 
      return true;
    }
    
    public final void setDensity(int param1Int) {
      if (this.mDensity != param1Int)
        this.mDensity = param1Int; 
    }
  }
  
  static class LayerState extends Drawable.ConstantState {
    static final int N_CHILDREN = 2;
    
    private boolean mAutoMirrored;
    
    int mChangingConfigurations;
    
    private boolean mCheckedOpacity;
    
    private boolean mCheckedStateful;
    
    AdaptiveIconDrawable.ChildDrawable[] mChildren;
    
    int mChildrenChangingConfigurations;
    
    int mDensity;
    
    private boolean mIsStateful;
    
    private int mOpacity;
    
    int mOpacityOverride;
    
    int mSourceDrawableId;
    
    int mSrcDensityOverride;
    
    private int[] mThemeAttrs;
    
    LayerState(LayerState param1LayerState, AdaptiveIconDrawable param1AdaptiveIconDrawable, Resources param1Resources) {
      int i = 0;
      this.mSrcDensityOverride = 0;
      this.mOpacityOverride = 0;
      this.mSourceDrawableId = 0;
      this.mAutoMirrored = false;
      if (param1LayerState != null)
        i = param1LayerState.mDensity; 
      this.mDensity = Drawable.resolveDensity(param1Resources, i);
      this.mChildren = new AdaptiveIconDrawable.ChildDrawable[2];
      if (param1LayerState != null) {
        AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = param1LayerState.mChildren;
        this.mChangingConfigurations = param1LayerState.mChangingConfigurations;
        this.mChildrenChangingConfigurations = param1LayerState.mChildrenChangingConfigurations;
        this.mSourceDrawableId = param1LayerState.mSourceDrawableId;
        for (i = 0; i < 2; i++) {
          AdaptiveIconDrawable.ChildDrawable childDrawable = arrayOfChildDrawable[i];
          this.mChildren[i] = new AdaptiveIconDrawable.ChildDrawable(childDrawable, param1AdaptiveIconDrawable, param1Resources);
        } 
        this.mCheckedOpacity = param1LayerState.mCheckedOpacity;
        this.mOpacity = param1LayerState.mOpacity;
        this.mCheckedStateful = param1LayerState.mCheckedStateful;
        this.mIsStateful = param1LayerState.mIsStateful;
        this.mAutoMirrored = param1LayerState.mAutoMirrored;
        this.mThemeAttrs = param1LayerState.mThemeAttrs;
        this.mOpacityOverride = param1LayerState.mOpacityOverride;
        this.mSrcDensityOverride = param1LayerState.mSrcDensityOverride;
      } else {
        for (i = 0; i < 2; i++)
          this.mChildren[i] = new AdaptiveIconDrawable.ChildDrawable(this.mDensity); 
      } 
    }
    
    public boolean canApplyTheme() {
      if (this.mThemeAttrs != null || super.canApplyTheme())
        return true; 
      AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
      for (byte b = 0; b < 2; b++) {
        if (arrayOfChildDrawable[b].canApplyTheme())
          return true; 
      } 
      return false;
    }
    
    public final boolean canConstantState() {
      AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
      for (byte b = 0; b < 2; b++) {
        Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
        if (drawable != null && drawable.getConstantState() == null)
          return false; 
      } 
      return true;
    }
    
    public int getChangingConfigurations() {
      return this.mChangingConfigurations | this.mChildrenChangingConfigurations;
    }
    
    public final int getOpacity() {
      int k;
      if (this.mCheckedOpacity)
        return this.mOpacity; 
      AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
      int i = -1;
      int j = 0;
      while (true) {
        k = i;
        if (j < 2) {
          if ((arrayOfChildDrawable[j]).mDrawable != null) {
            k = j;
            break;
          } 
          j++;
          continue;
        } 
        break;
      } 
      if (k >= 0) {
        j = (arrayOfChildDrawable[k]).mDrawable.getOpacity();
      } else {
        j = -2;
      } 
      k++;
      for (i = j; k < 2; i = j) {
        Drawable drawable = (arrayOfChildDrawable[k]).mDrawable;
        j = i;
        if (drawable != null)
          j = Drawable.resolveOpacity(i, drawable.getOpacity()); 
        k++;
      } 
      this.mOpacity = i;
      this.mCheckedOpacity = true;
      return i;
    }
    
    public final boolean hasFocusStateSpecified() {
      AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
      for (byte b = 0; b < 2; b++) {
        Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
        if (drawable != null && drawable.hasFocusStateSpecified())
          return true; 
      } 
      return false;
    }
    
    public void invalidateCache() {
      this.mCheckedOpacity = false;
      this.mCheckedStateful = false;
    }
    
    public final boolean isStateful() {
      boolean bool2;
      if (this.mCheckedStateful)
        return this.mIsStateful; 
      AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
      boolean bool1 = false;
      byte b = 0;
      while (true) {
        bool2 = bool1;
        if (b < 2) {
          Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
          if (drawable != null && drawable.isStateful()) {
            bool2 = true;
            break;
          } 
          b++;
          continue;
        } 
        break;
      } 
      this.mIsStateful = bool2;
      this.mCheckedStateful = true;
      return bool2;
    }
    
    public Drawable newDrawable() {
      return new AdaptiveIconDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new AdaptiveIconDrawable(this, param1Resources);
    }
    
    public final void setDensity(int param1Int) {
      if (this.mDensity != param1Int)
        this.mDensity = param1Int; 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AdaptiveIconDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */