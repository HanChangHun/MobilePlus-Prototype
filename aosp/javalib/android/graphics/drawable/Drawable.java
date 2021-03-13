package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ImageDecoder;
import android.graphics.Insets;
import android.graphics.NinePatch;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Xfermode;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import com.android.internal.R;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class Drawable {
  static final BlendMode DEFAULT_BLEND_MODE;
  
  static final PorterDuff.Mode DEFAULT_TINT_MODE;
  
  private static final Rect ZERO_BOUNDS_RECT = new Rect();
  
  private Rect mBounds = ZERO_BOUNDS_RECT;
  
  private WeakReference<Callback> mCallback = null;
  
  private int mChangingConfigurations = 0;
  
  private int mLayoutDirection;
  
  private int mLevel = 0;
  
  private boolean mSetBlendModeInvoked = false;
  
  private boolean mSetTintModeInvoked = false;
  
  protected int mSrcDensityOverride = 0;
  
  private int[] mStateSet = StateSet.WILD_CARD;
  
  private boolean mVisible = true;
  
  static {
    DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    DEFAULT_BLEND_MODE = BlendMode.SRC_IN;
  }
  
  public static Drawable createFromPath(String paramString) {
    if (paramString == null)
      return null; 
    Trace.traceBegin(8192L, paramString);
    try {
      FileInputStream fileInputStream = new FileInputStream();
      this(paramString);
    } catch (IOException iOException) {
      return null;
    } finally {
      Trace.traceEnd(8192L);
    } 
  }
  
  public static Drawable createFromResourceStream(Resources paramResources, TypedValue paramTypedValue, InputStream paramInputStream, String paramString) {
    String str;
    if (paramString != null) {
      str = paramString;
    } else {
      str = "Unknown drawable";
    } 
    Trace.traceBegin(8192L, str);
    try {
      return createFromResourceStream(paramResources, paramTypedValue, paramInputStream, paramString, null);
    } finally {
      Trace.traceEnd(8192L);
    } 
  }
  
  public static Drawable createFromResourceStream(Resources paramResources, TypedValue paramTypedValue, InputStream paramInputStream, String paramString, BitmapFactory.Options paramOptions) {
    if (paramInputStream == null)
      return null; 
    if (paramOptions == null)
      return getBitmapDrawable(paramResources, paramTypedValue, paramInputStream); 
    Rect rect2 = new Rect();
    paramOptions.inScreenDensity = resolveDensity(paramResources, 0);
    Bitmap bitmap = BitmapFactory.decodeResourceStream(paramResources, paramTypedValue, paramInputStream, rect2, paramOptions);
    if (bitmap != null) {
      byte[] arrayOfByte = bitmap.getNinePatchChunk();
      if (arrayOfByte != null) {
        byte[] arrayOfByte1 = arrayOfByte;
        Rect rect3 = rect2;
        if (!NinePatch.isNinePatchChunk(arrayOfByte)) {
          arrayOfByte1 = null;
          rect3 = null;
          Rect rect = new Rect();
          bitmap.getOpticalInsets(rect);
          return drawableFromBitmap(paramResources, bitmap, arrayOfByte1, rect3, rect, paramString);
        } 
        Rect rect4 = new Rect();
        bitmap.getOpticalInsets(rect4);
        return drawableFromBitmap(paramResources, bitmap, arrayOfByte1, rect3, rect4, paramString);
      } 
    } else {
      return null;
    } 
    paramTypedValue = null;
    paramInputStream = null;
    Rect rect1 = new Rect();
    bitmap.getOpticalInsets(rect1);
    return drawableFromBitmap(paramResources, bitmap, (byte[])paramTypedValue, (Rect)paramInputStream, rect1, paramString);
  }
  
  public static Drawable createFromStream(InputStream paramInputStream, String paramString) {
    String str;
    if (paramString != null) {
      str = paramString;
    } else {
      str = "Unknown drawable";
    } 
    Trace.traceBegin(8192L, str);
    try {
      return createFromResourceStream(null, null, paramInputStream, paramString);
    } finally {
      Trace.traceEnd(8192L);
    } 
  }
  
  public static Drawable createFromXml(Resources paramResources, XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    return createFromXml(paramResources, paramXmlPullParser, null);
  }
  
  public static Drawable createFromXml(Resources paramResources, XmlPullParser paramXmlPullParser, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    return createFromXmlForDensity(paramResources, paramXmlPullParser, 0, paramTheme);
  }
  
  public static Drawable createFromXmlForDensity(Resources paramResources, XmlPullParser paramXmlPullParser, int paramInt, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i;
    AttributeSet attributeSet = Xml.asAttributeSet(paramXmlPullParser);
    while (true) {
      i = paramXmlPullParser.next();
      if (i != 2 && i != 1)
        continue; 
      break;
    } 
    if (i == 2) {
      Drawable drawable = createFromXmlInnerForDensity(paramResources, paramXmlPullParser, attributeSet, paramInt, paramTheme);
      if (drawable != null)
        return drawable; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown initial tag: ");
      stringBuilder.append(paramXmlPullParser.getName());
      throw new RuntimeException(stringBuilder.toString());
    } 
    throw new XmlPullParserException("No start tag found");
  }
  
  public static Drawable createFromXmlInner(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws XmlPullParserException, IOException {
    return createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet, null);
  }
  
  public static Drawable createFromXmlInner(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    return createFromXmlInnerForDensity(paramResources, paramXmlPullParser, paramAttributeSet, 0, paramTheme);
  }
  
  static Drawable createFromXmlInnerForDensity(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, int paramInt, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    return paramResources.getDrawableInflater().inflateFromXmlForDensity(paramXmlPullParser.getName(), paramXmlPullParser, paramAttributeSet, paramInt, paramTheme);
  }
  
  private static Drawable drawableFromBitmap(Resources paramResources, Bitmap paramBitmap, byte[] paramArrayOfbyte, Rect paramRect1, Rect paramRect2, String paramString) {
    return (Drawable)((paramArrayOfbyte != null) ? new NinePatchDrawable(paramResources, paramBitmap, paramArrayOfbyte, paramRect1, paramRect2, paramString) : new BitmapDrawable(paramResources, paramBitmap));
  }
  
  private static Drawable getBitmapDrawable(Resources paramResources, TypedValue paramTypedValue, InputStream paramInputStream) {
    if (paramTypedValue != null) {
      int i = 0;
      try {
        if (paramTypedValue.density == 0) {
          i = 160;
        } else if (paramTypedValue.density != 65535) {
          i = paramTypedValue.density;
        } 
        ImageDecoder.Source source1 = ImageDecoder.createSource(paramResources, paramInputStream, i);
        return ImageDecoder.decodeDrawable(source1, (ImageDecoder.OnHeaderDecodedListener)_$$Lambda$Drawable$bbJz2VgQAwkXlE27mR8nPMYacEw.INSTANCE);
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to decode stream: ");
        stringBuilder.append(iOException);
        Log.e("Drawable", stringBuilder.toString());
        return null;
      } 
    } 
    ImageDecoder.Source source = ImageDecoder.createSource((Resources)iOException, paramInputStream);
    return ImageDecoder.decodeDrawable(source, (ImageDecoder.OnHeaderDecodedListener)_$$Lambda$Drawable$bbJz2VgQAwkXlE27mR8nPMYacEw.INSTANCE);
  }
  
  protected static TypedArray obtainAttributes(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, int[] paramArrayOfint) {
    return (paramTheme == null) ? paramResources.obtainAttributes(paramAttributeSet, paramArrayOfint) : paramTheme.obtainStyledAttributes(paramAttributeSet, paramArrayOfint, 0, 0);
  }
  
  public static BlendMode parseBlendMode(int paramInt, BlendMode paramBlendMode) {
    if (paramInt != 3) {
      if (paramInt != 5) {
        if (paramInt != 9) {
          switch (paramInt) {
            default:
              return paramBlendMode;
            case 16:
              return BlendMode.PLUS;
            case 15:
              return BlendMode.SCREEN;
            case 14:
              break;
          } 
          return BlendMode.MODULATE;
        } 
        return BlendMode.SRC_ATOP;
      } 
      return BlendMode.SRC_IN;
    } 
    return BlendMode.SRC_OVER;
  }
  
  public static PorterDuff.Mode parseTintMode(int paramInt, PorterDuff.Mode paramMode) {
    if (paramInt != 3) {
      if (paramInt != 5) {
        if (paramInt != 9) {
          switch (paramInt) {
            default:
              return paramMode;
            case 16:
              return PorterDuff.Mode.ADD;
            case 15:
              return PorterDuff.Mode.SCREEN;
            case 14:
              break;
          } 
          return PorterDuff.Mode.MULTIPLY;
        } 
        return PorterDuff.Mode.SRC_ATOP;
      } 
      return PorterDuff.Mode.SRC_IN;
    } 
    return PorterDuff.Mode.SRC_OVER;
  }
  
  static int resolveDensity(Resources paramResources, int paramInt) {
    if (paramResources != null)
      paramInt = (paramResources.getDisplayMetrics()).densityDpi; 
    if (paramInt == 0)
      paramInt = 160; 
    return paramInt;
  }
  
  public static int resolveOpacity(int paramInt1, int paramInt2) {
    return (paramInt1 == paramInt2) ? paramInt1 : ((paramInt1 == 0 || paramInt2 == 0) ? 0 : ((paramInt1 == -3 || paramInt2 == -3) ? -3 : ((paramInt1 == -2 || paramInt2 == -2) ? -2 : -1)));
  }
  
  static void rethrowAsRuntimeException(Exception paramException) throws RuntimeException {
    paramException = new RuntimeException(paramException);
    paramException.setStackTrace(new StackTraceElement[0]);
    throw paramException;
  }
  
  static float scaleFromDensity(float paramFloat, int paramInt1, int paramInt2) {
    return paramInt2 * paramFloat / paramInt1;
  }
  
  static int scaleFromDensity(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    if (paramInt1 == 0 || paramInt2 == paramInt3)
      return paramInt1; 
    float f = (paramInt1 * paramInt3) / paramInt2;
    if (!paramBoolean)
      return (int)f; 
    paramInt2 = Math.round(f);
    return (paramInt2 != 0) ? paramInt2 : ((paramInt1 > 0) ? 1 : -1);
  }
  
  public void applyTheme(Resources.Theme paramTheme) {}
  
  public boolean canApplyTheme() {
    return false;
  }
  
  public void clearColorFilter() {
    setColorFilter(null);
  }
  
  public void clearMutated() {}
  
  public final Rect copyBounds() {
    return new Rect(this.mBounds);
  }
  
  public final void copyBounds(Rect paramRect) {
    paramRect.set(this.mBounds);
  }
  
  public abstract void draw(Canvas paramCanvas);
  
  public int getAlpha() {
    return 255;
  }
  
  public final Rect getBounds() {
    if (this.mBounds == ZERO_BOUNDS_RECT)
      this.mBounds = new Rect(); 
    return this.mBounds;
  }
  
  public Callback getCallback() {
    WeakReference<Callback> weakReference = this.mCallback;
    if (weakReference != null) {
      Callback callback = weakReference.get();
    } else {
      weakReference = null;
    } 
    return (Callback)weakReference;
  }
  
  public int getChangingConfigurations() {
    return this.mChangingConfigurations;
  }
  
  public ColorFilter getColorFilter() {
    return null;
  }
  
  public ConstantState getConstantState() {
    return null;
  }
  
  public Drawable getCurrent() {
    return this;
  }
  
  public Rect getDirtyBounds() {
    return getBounds();
  }
  
  public void getHotspotBounds(Rect paramRect) {
    paramRect.set(getBounds());
  }
  
  public int getIntrinsicHeight() {
    return -1;
  }
  
  public int getIntrinsicWidth() {
    return -1;
  }
  
  public int getLayoutDirection() {
    return this.mLayoutDirection;
  }
  
  public final int getLevel() {
    return this.mLevel;
  }
  
  public int getMinimumHeight() {
    int i = getIntrinsicHeight();
    if (i <= 0)
      i = 0; 
    return i;
  }
  
  public int getMinimumWidth() {
    int i = getIntrinsicWidth();
    if (i <= 0)
      i = 0; 
    return i;
  }
  
  @Deprecated
  public abstract int getOpacity();
  
  public Insets getOpticalInsets() {
    return Insets.NONE;
  }
  
  public void getOutline(Outline paramOutline) {
    paramOutline.setRect(getBounds());
    paramOutline.setAlpha(0.0F);
  }
  
  public boolean getPadding(Rect paramRect) {
    paramRect.set(0, 0, 0, 0);
    return false;
  }
  
  public int[] getState() {
    return this.mStateSet;
  }
  
  public Region getTransparentRegion() {
    return null;
  }
  
  public boolean hasFocusStateSpecified() {
    return false;
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws XmlPullParserException, IOException {
    inflate(paramResources, paramXmlPullParser, paramAttributeSet, null);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.Drawable);
    this.mVisible = typedArray.getBoolean(0, this.mVisible);
    typedArray.recycle();
  }
  
  void inflateWithAttributes(Resources paramResources, XmlPullParser paramXmlPullParser, TypedArray paramTypedArray, int paramInt) throws XmlPullParserException, IOException {
    this.mVisible = paramTypedArray.getBoolean(paramInt, this.mVisible);
  }
  
  public void invalidateSelf() {
    Callback callback = getCallback();
    if (callback != null)
      callback.invalidateDrawable(this); 
  }
  
  public boolean isAutoMirrored() {
    return false;
  }
  
  public boolean isFilterBitmap() {
    return false;
  }
  
  public boolean isProjected() {
    return false;
  }
  
  public boolean isStateful() {
    return false;
  }
  
  public final boolean isVisible() {
    return this.mVisible;
  }
  
  public void jumpToCurrentState() {}
  
  public Drawable mutate() {
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {}
  
  public boolean onLayoutDirectionChanged(int paramInt) {
    return false;
  }
  
  protected boolean onLevelChange(int paramInt) {
    return false;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    return false;
  }
  
  public void scheduleSelf(Runnable paramRunnable, long paramLong) {
    Callback callback = getCallback();
    if (callback != null)
      callback.scheduleDrawable(this, paramRunnable, paramLong); 
  }
  
  public abstract void setAlpha(int paramInt);
  
  public void setAutoMirrored(boolean paramBoolean) {}
  
  public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    Rect rect1 = this.mBounds;
    Rect rect2 = rect1;
    if (rect1 == ZERO_BOUNDS_RECT) {
      rect2 = new Rect();
      this.mBounds = rect2;
    } 
    if (rect2.left != paramInt1 || rect2.top != paramInt2 || rect2.right != paramInt3 || rect2.bottom != paramInt4) {
      if (!rect2.isEmpty())
        invalidateSelf(); 
      this.mBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
      onBoundsChange(this.mBounds);
    } 
  }
  
  public void setBounds(Rect paramRect) {
    setBounds(paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public final void setCallback(Callback paramCallback) {
    if (paramCallback != null) {
      WeakReference<Callback> weakReference = new WeakReference<>(paramCallback);
    } else {
      paramCallback = null;
    } 
    this.mCallback = (WeakReference<Callback>)paramCallback;
  }
  
  public void setChangingConfigurations(int paramInt) {
    this.mChangingConfigurations = paramInt;
  }
  
  @Deprecated
  public void setColorFilter(int paramInt, PorterDuff.Mode paramMode) {
    if (getColorFilter() instanceof PorterDuffColorFilter) {
      PorterDuffColorFilter porterDuffColorFilter = (PorterDuffColorFilter)getColorFilter();
      if (porterDuffColorFilter.getColor() == paramInt && porterDuffColorFilter.getMode() == paramMode)
        return; 
    } 
    setColorFilter((ColorFilter)new PorterDuffColorFilter(paramInt, paramMode));
  }
  
  public abstract void setColorFilter(ColorFilter paramColorFilter);
  
  @Deprecated
  public void setDither(boolean paramBoolean) {}
  
  public void setFilterBitmap(boolean paramBoolean) {}
  
  public void setHotspot(float paramFloat1, float paramFloat2) {}
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public final boolean setLayoutDirection(int paramInt) {
    if (this.mLayoutDirection != paramInt) {
      this.mLayoutDirection = paramInt;
      return onLayoutDirectionChanged(paramInt);
    } 
    return false;
  }
  
  public final boolean setLevel(int paramInt) {
    if (this.mLevel != paramInt) {
      this.mLevel = paramInt;
      return onLevelChange(paramInt);
    } 
    return false;
  }
  
  final void setSrcDensityOverride(int paramInt) {
    this.mSrcDensityOverride = paramInt;
  }
  
  public boolean setState(int[] paramArrayOfint) {
    if (!Arrays.equals(this.mStateSet, paramArrayOfint)) {
      this.mStateSet = paramArrayOfint;
      return onStateChange(paramArrayOfint);
    } 
    return false;
  }
  
  public void setTint(int paramInt) {
    setTintList(ColorStateList.valueOf(paramInt));
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    if (!this.mSetBlendModeInvoked) {
      this.mSetBlendModeInvoked = true;
      PorterDuff.Mode mode = BlendMode.blendModeToPorterDuffMode(paramBlendMode);
      if (mode == null)
        mode = DEFAULT_TINT_MODE; 
      setTintMode(mode);
      this.mSetBlendModeInvoked = false;
    } 
  }
  
  public void setTintList(ColorStateList paramColorStateList) {}
  
  public void setTintMode(PorterDuff.Mode paramMode) {
    if (!this.mSetTintModeInvoked) {
      BlendMode blendMode;
      this.mSetTintModeInvoked = true;
      if (paramMode != null) {
        blendMode = BlendMode.fromValue(paramMode.nativeInt);
      } else {
        paramMode = null;
      } 
      if (paramMode == null)
        blendMode = DEFAULT_BLEND_MODE; 
      setTintBlendMode(blendMode);
      this.mSetTintModeInvoked = false;
    } 
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    if (this.mVisible != paramBoolean1) {
      paramBoolean2 = true;
    } else {
      paramBoolean2 = false;
    } 
    if (paramBoolean2) {
      this.mVisible = paramBoolean1;
      invalidateSelf();
    } 
    return paramBoolean2;
  }
  
  public void setXfermode(Xfermode paramXfermode) {}
  
  public void unscheduleSelf(Runnable paramRunnable) {
    Callback callback = getCallback();
    if (callback != null)
      callback.unscheduleDrawable(this, paramRunnable); 
  }
  
  BlendModeColorFilter updateBlendModeFilter(BlendModeColorFilter paramBlendModeColorFilter, ColorStateList paramColorStateList, BlendMode paramBlendMode) {
    if (paramColorStateList == null || paramBlendMode == null)
      return null; 
    int i = paramColorStateList.getColorForState(getState(), 0);
    return (paramBlendModeColorFilter == null || paramBlendModeColorFilter.getColor() != i || paramBlendModeColorFilter.getMode() != paramBlendMode) ? new BlendModeColorFilter(i, paramBlendMode) : paramBlendModeColorFilter;
  }
  
  PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter paramPorterDuffColorFilter, ColorStateList paramColorStateList, PorterDuff.Mode paramMode) {
    if (paramColorStateList == null || paramMode == null)
      return null; 
    int i = paramColorStateList.getColorForState(getState(), 0);
    return (paramPorterDuffColorFilter == null || paramPorterDuffColorFilter.getColor() != i || paramPorterDuffColorFilter.getMode() != paramMode) ? new PorterDuffColorFilter(i, paramMode) : paramPorterDuffColorFilter;
  }
  
  public static interface Callback {
    void invalidateDrawable(Drawable param1Drawable);
    
    void scheduleDrawable(Drawable param1Drawable, Runnable param1Runnable, long param1Long);
    
    void unscheduleDrawable(Drawable param1Drawable, Runnable param1Runnable);
  }
  
  public static abstract class ConstantState {
    public boolean canApplyTheme() {
      return false;
    }
    
    public abstract int getChangingConfigurations();
    
    public abstract Drawable newDrawable();
    
    public Drawable newDrawable(Resources param1Resources) {
      return newDrawable();
    }
    
    public Drawable newDrawable(Resources param1Resources, Resources.Theme param1Theme) {
      return newDrawable(param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/Drawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */