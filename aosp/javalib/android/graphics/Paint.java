package android.graphics;

import android.graphics.fonts.FontVariationAxis;
import android.os.LocaleList;
import android.text.GraphicsOperations;
import android.text.TextUtils;
import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import libcore.util.NativeAllocationRegistry;

public class Paint {
  public static final int ANTI_ALIAS_FLAG = 1;
  
  public static final int AUTO_HINTING_TEXT_FLAG = 2048;
  
  public static final int BIDI_DEFAULT_LTR = 2;
  
  public static final int BIDI_DEFAULT_RTL = 3;
  
  private static final int BIDI_FLAG_MASK = 7;
  
  public static final int BIDI_FORCE_LTR = 4;
  
  public static final int BIDI_FORCE_RTL = 5;
  
  public static final int BIDI_LTR = 0;
  
  private static final int BIDI_MAX_FLAG_VALUE = 5;
  
  public static final int BIDI_RTL = 1;
  
  public static final int CURSOR_AFTER = 0;
  
  public static final int CURSOR_AT = 4;
  
  public static final int CURSOR_AT_OR_AFTER = 1;
  
  public static final int CURSOR_AT_OR_BEFORE = 3;
  
  public static final int CURSOR_BEFORE = 2;
  
  private static final int CURSOR_OPT_MAX_VALUE = 4;
  
  public static final int DEV_KERN_TEXT_FLAG = 256;
  
  public static final int DIRECTION_LTR = 0;
  
  public static final int DIRECTION_RTL = 1;
  
  public static final int DITHER_FLAG = 4;
  
  public static final int EMBEDDED_BITMAP_TEXT_FLAG = 1024;
  
  public static final int END_HYPHEN_EDIT_INSERT_ARMENIAN_HYPHEN = 3;
  
  public static final int END_HYPHEN_EDIT_INSERT_HYPHEN = 2;
  
  public static final int END_HYPHEN_EDIT_INSERT_MAQAF = 4;
  
  public static final int END_HYPHEN_EDIT_INSERT_UCAS_HYPHEN = 5;
  
  public static final int END_HYPHEN_EDIT_INSERT_ZWJ_AND_HYPHEN = 6;
  
  public static final int END_HYPHEN_EDIT_NO_EDIT = 0;
  
  public static final int END_HYPHEN_EDIT_REPLACE_WITH_HYPHEN = 1;
  
  public static final int FAKE_BOLD_TEXT_FLAG = 32;
  
  public static final int FILTER_BITMAP_FLAG = 2;
  
  static final int HIDDEN_DEFAULT_PAINT_FLAGS = 1282;
  
  public static final int HINTING_OFF = 0;
  
  public static final int HINTING_ON = 1;
  
  public static final int LCD_RENDER_TEXT_FLAG = 512;
  
  public static final int LINEAR_TEXT_FLAG = 64;
  
  public static final int START_HYPHEN_EDIT_INSERT_HYPHEN = 1;
  
  public static final int START_HYPHEN_EDIT_INSERT_ZWJ = 2;
  
  public static final int START_HYPHEN_EDIT_NO_EDIT = 0;
  
  public static final int STRIKE_THRU_TEXT_FLAG = 16;
  
  public static final int SUBPIXEL_TEXT_FLAG = 128;
  
  public static final int UNDERLINE_TEXT_FLAG = 8;
  
  public static final int VERTICAL_TEXT_FLAG = 4096;
  
  static final Align[] sAlignArray;
  
  private static final Object sCacheLock = new Object();
  
  static final Cap[] sCapArray;
  
  static final Join[] sJoinArray;
  
  private static final HashMap<String, Integer> sMinikinLocaleListIdCache = new HashMap<>();
  
  static final Style[] sStyleArray = new Style[] { Style.FILL, Style.STROKE, Style.FILL_AND_STROKE };
  
  public int mBidiFlags = 2;
  
  private long mColor;
  
  private ColorFilter mColorFilter;
  
  private float mCompatScaling;
  
  private String mFontFeatureSettings;
  
  private String mFontVariationSettings;
  
  private boolean mHasCompatScaling;
  
  private float mInvCompatScaling;
  
  private LocaleList mLocales;
  
  private MaskFilter mMaskFilter;
  
  private long mNativeColorFilter;
  
  private long mNativePaint = nInit();
  
  private long mNativeShader;
  
  private PathEffect mPathEffect;
  
  private Shader mShader;
  
  private long mShadowLayerColor;
  
  private float mShadowLayerDx;
  
  private float mShadowLayerDy;
  
  private float mShadowLayerRadius;
  
  private Typeface mTypeface;
  
  private Xfermode mXfermode;
  
  static {
    sCapArray = new Cap[] { Cap.BUTT, Cap.ROUND, Cap.SQUARE };
    sJoinArray = new Join[] { Join.MITER, Join.ROUND, Join.BEVEL };
    sAlignArray = new Align[] { Align.LEFT, Align.CENTER, Align.RIGHT };
  }
  
  public Paint() {
    this(0);
  }
  
  public Paint(int paramInt) {
    NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativePaint);
    setFlags(paramInt | 0x502);
    this.mInvCompatScaling = 1.0F;
    this.mCompatScaling = 1.0F;
    setTextLocales(LocaleList.getAdjustedDefault());
    this.mColor = Color.pack(-16777216);
  }
  
  public Paint(Paint paramPaint) {
    NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativePaint);
    setClassVariablesFrom(paramPaint);
  }
  
  private Xfermode installXfermode(Xfermode paramXfermode) {
    int i;
    int j;
    if (paramXfermode != null) {
      i = paramXfermode.porterDuffMode;
    } else {
      i = Xfermode.DEFAULT;
    } 
    Xfermode xfermode = this.mXfermode;
    if (xfermode != null) {
      j = xfermode.porterDuffMode;
    } else {
      j = Xfermode.DEFAULT;
    } 
    if (i != j)
      nSetXfermode(this.mNativePaint, i); 
    this.mXfermode = paramXfermode;
    return paramXfermode;
  }
  
  @CriticalNative
  private static native float nAscent(long paramLong);
  
  private static native int nBreakText(long paramLong, String paramString, boolean paramBoolean, float paramFloat, int paramInt, float[] paramArrayOffloat);
  
  private static native int nBreakText(long paramLong, char[] paramArrayOfchar, int paramInt1, int paramInt2, float paramFloat, int paramInt3, float[] paramArrayOffloat);
  
  @CriticalNative
  private static native float nDescent(long paramLong);
  
  @CriticalNative
  private static native boolean nEqualsForTextMeasurement(long paramLong1, long paramLong2);
  
  private static native void nGetCharArrayBounds(long paramLong, char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, Rect paramRect);
  
  @CriticalNative
  private static native int nGetEndHyphenEdit(long paramLong);
  
  @CriticalNative
  private static native boolean nGetFillPath(long paramLong1, long paramLong2, long paramLong3);
  
  @CriticalNative
  private static native int nGetFlags(long paramLong);
  
  @FastNative
  private static native float nGetFontMetrics(long paramLong, FontMetrics paramFontMetrics);
  
  @FastNative
  private static native int nGetFontMetricsInt(long paramLong, FontMetricsInt paramFontMetricsInt);
  
  @CriticalNative
  private static native int nGetHinting(long paramLong);
  
  @CriticalNative
  private static native float nGetLetterSpacing(long paramLong);
  
  private static native long nGetNativeFinalizer();
  
  private static native int nGetOffsetForAdvance(long paramLong, char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, float paramFloat);
  
  private static native float nGetRunAdvance(long paramLong, char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, int paramInt5);
  
  @CriticalNative
  private static native int nGetStartHyphenEdit(long paramLong);
  
  @CriticalNative
  private static native float nGetStrikeThruPosition(long paramLong);
  
  @CriticalNative
  private static native float nGetStrikeThruThickness(long paramLong);
  
  private static native void nGetStringBounds(long paramLong, String paramString, int paramInt1, int paramInt2, int paramInt3, Rect paramRect);
  
  @CriticalNative
  private static native int nGetStrokeCap(long paramLong);
  
  @CriticalNative
  private static native int nGetStrokeJoin(long paramLong);
  
  @CriticalNative
  private static native float nGetStrokeMiter(long paramLong);
  
  @CriticalNative
  private static native float nGetStrokeWidth(long paramLong);
  
  @CriticalNative
  private static native int nGetStyle(long paramLong);
  
  private static native float nGetTextAdvances(long paramLong, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOffloat, int paramInt6);
  
  private static native float nGetTextAdvances(long paramLong, char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOffloat, int paramInt6);
  
  @CriticalNative
  private static native int nGetTextAlign(long paramLong);
  
  private static native void nGetTextPath(long paramLong1, int paramInt1, String paramString, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, long paramLong2);
  
  private static native void nGetTextPath(long paramLong1, int paramInt1, char[] paramArrayOfchar, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, long paramLong2);
  
  private native int nGetTextRunCursor(long paramLong, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  private native int nGetTextRunCursor(long paramLong, char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  @CriticalNative
  private static native float nGetTextScaleX(long paramLong);
  
  @CriticalNative
  private static native float nGetTextSize(long paramLong);
  
  @CriticalNative
  private static native float nGetTextSkewX(long paramLong);
  
  @CriticalNative
  private static native float nGetUnderlinePosition(long paramLong);
  
  @CriticalNative
  private static native float nGetUnderlineThickness(long paramLong);
  
  @CriticalNative
  private static native float nGetWordSpacing(long paramLong);
  
  private static native boolean nHasGlyph(long paramLong, int paramInt, String paramString);
  
  @CriticalNative
  private static native boolean nHasShadowLayer(long paramLong);
  
  private static native long nInit();
  
  private static native long nInitWithPaint(long paramLong);
  
  @CriticalNative
  private static native boolean nIsElegantTextHeight(long paramLong);
  
  @CriticalNative
  private static native void nReset(long paramLong);
  
  @CriticalNative
  private static native void nSet(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nSetAlpha(long paramLong, int paramInt);
  
  @CriticalNative
  private static native void nSetAntiAlias(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native void nSetColor(long paramLong, int paramInt);
  
  @CriticalNative
  private static native void nSetColor(long paramLong1, long paramLong2, long paramLong3);
  
  @CriticalNative
  private static native long nSetColorFilter(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nSetDither(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native void nSetElegantTextHeight(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native void nSetEndHyphenEdit(long paramLong, int paramInt);
  
  @CriticalNative
  private static native void nSetFakeBoldText(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native void nSetFilterBitmap(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native void nSetFlags(long paramLong, int paramInt);
  
  @FastNative
  private static native void nSetFontFeatureSettings(long paramLong, String paramString);
  
  @CriticalNative
  private static native void nSetHinting(long paramLong, int paramInt);
  
  @CriticalNative
  private static native void nSetLetterSpacing(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native void nSetLinearText(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native long nSetMaskFilter(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native long nSetPathEffect(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native long nSetShader(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nSetShadowLayer(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong2, long paramLong3);
  
  @CriticalNative
  private static native void nSetStartHyphenEdit(long paramLong, int paramInt);
  
  @CriticalNative
  private static native void nSetStrikeThruText(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native void nSetStrokeCap(long paramLong, int paramInt);
  
  @CriticalNative
  private static native void nSetStrokeJoin(long paramLong, int paramInt);
  
  @CriticalNative
  private static native void nSetStrokeMiter(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native void nSetStrokeWidth(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native void nSetStyle(long paramLong, int paramInt);
  
  @CriticalNative
  private static native void nSetSubpixelText(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native void nSetTextAlign(long paramLong, int paramInt);
  
  @FastNative
  private static native int nSetTextLocales(long paramLong, String paramString);
  
  @CriticalNative
  private static native void nSetTextLocalesByMinikinLocaleListId(long paramLong, int paramInt);
  
  @CriticalNative
  private static native void nSetTextScaleX(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native void nSetTextSize(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native void nSetTextSkewX(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native void nSetTypeface(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nSetUnderlineText(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native void nSetWordSpacing(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native void nSetXfermode(long paramLong, int paramInt);
  
  private void setClassVariablesFrom(Paint paramPaint) {
    this.mColor = paramPaint.mColor;
    this.mColorFilter = paramPaint.mColorFilter;
    this.mMaskFilter = paramPaint.mMaskFilter;
    this.mPathEffect = paramPaint.mPathEffect;
    this.mShader = paramPaint.mShader;
    this.mNativeShader = paramPaint.mNativeShader;
    this.mTypeface = paramPaint.mTypeface;
    this.mXfermode = paramPaint.mXfermode;
    this.mHasCompatScaling = paramPaint.mHasCompatScaling;
    this.mCompatScaling = paramPaint.mCompatScaling;
    this.mInvCompatScaling = paramPaint.mInvCompatScaling;
    this.mBidiFlags = paramPaint.mBidiFlags;
    this.mLocales = paramPaint.mLocales;
    this.mFontFeatureSettings = paramPaint.mFontFeatureSettings;
    this.mFontVariationSettings = paramPaint.mFontVariationSettings;
    this.mShadowLayerRadius = paramPaint.mShadowLayerRadius;
    this.mShadowLayerDx = paramPaint.mShadowLayerDx;
    this.mShadowLayerDy = paramPaint.mShadowLayerDy;
    this.mShadowLayerColor = paramPaint.mShadowLayerColor;
  }
  
  private void syncTextLocalesWithMinikin() {
    null = this.mLocales.toLanguageTags();
    synchronized (sCacheLock) {
      Integer integer = sMinikinLocaleListIdCache.get(null);
      if (integer == null) {
        int i = nSetTextLocales(this.mNativePaint, null);
        sMinikinLocaleListIdCache.put(null, Integer.valueOf(i));
        return;
      } 
      nSetTextLocalesByMinikinLocaleListId(this.mNativePaint, integer.intValue());
      return;
    } 
  }
  
  public float ascent() {
    return nAscent(this.mNativePaint);
  }
  
  public int breakText(CharSequence paramCharSequence, int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat, float[] paramArrayOffloat) {
    if (paramCharSequence != null) {
      if ((paramInt1 | paramInt2 | paramInt2 - paramInt1 | paramCharSequence.length() - paramInt2) >= 0) {
        if (paramCharSequence.length() == 0 || paramInt1 == paramInt2)
          return 0; 
        if (paramInt1 == 0 && paramCharSequence instanceof String && paramInt2 == paramCharSequence.length())
          return breakText((String)paramCharSequence, paramBoolean, paramFloat, paramArrayOffloat); 
        char[] arrayOfChar = TemporaryBuffer.obtain(paramInt2 - paramInt1);
        TextUtils.getChars(paramCharSequence, paramInt1, paramInt2, arrayOfChar, 0);
        if (paramBoolean) {
          paramInt1 = breakText(arrayOfChar, 0, paramInt2 - paramInt1, paramFloat, paramArrayOffloat);
        } else {
          paramInt1 = breakText(arrayOfChar, 0, -(paramInt2 - paramInt1), paramFloat, paramArrayOffloat);
        } 
        TemporaryBuffer.recycle(arrayOfChar);
        return paramInt1;
      } 
      throw new IndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public int breakText(String paramString, boolean paramBoolean, float paramFloat, float[] paramArrayOffloat) {
    if (paramString != null) {
      if (paramString.length() == 0)
        return 0; 
      if (!this.mHasCompatScaling)
        return nBreakText(this.mNativePaint, paramString, paramBoolean, paramFloat, this.mBidiFlags, paramArrayOffloat); 
      float f = getTextSize();
      setTextSize(this.mCompatScaling * f);
      int i = nBreakText(this.mNativePaint, paramString, paramBoolean, paramFloat * this.mCompatScaling, this.mBidiFlags, paramArrayOffloat);
      setTextSize(f);
      if (paramArrayOffloat != null)
        paramArrayOffloat[0] = paramArrayOffloat[0] * this.mInvCompatScaling; 
      return i;
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public int breakText(char[] paramArrayOfchar, int paramInt1, int paramInt2, float paramFloat, float[] paramArrayOffloat) {
    if (paramArrayOfchar != null) {
      if (paramInt1 >= 0 && paramArrayOfchar.length - paramInt1 >= Math.abs(paramInt2)) {
        if (paramArrayOfchar.length == 0 || paramInt2 == 0)
          return 0; 
        if (!this.mHasCompatScaling)
          return nBreakText(this.mNativePaint, paramArrayOfchar, paramInt1, paramInt2, paramFloat, this.mBidiFlags, paramArrayOffloat); 
        float f = getTextSize();
        setTextSize(this.mCompatScaling * f);
        paramInt1 = nBreakText(this.mNativePaint, paramArrayOfchar, paramInt1, paramInt2, paramFloat * this.mCompatScaling, this.mBidiFlags, paramArrayOffloat);
        setTextSize(f);
        if (paramArrayOffloat != null)
          paramArrayOffloat[0] = paramArrayOffloat[0] * this.mInvCompatScaling; 
        return paramInt1;
      } 
      throw new ArrayIndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public void clearShadowLayer() {
    setShadowLayer(0.0F, 0.0F, 0.0F, 0);
  }
  
  public float descent() {
    return nDescent(this.mNativePaint);
  }
  
  public boolean equalsForTextMeasurement(Paint paramPaint) {
    return nEqualsForTextMeasurement(this.mNativePaint, paramPaint.mNativePaint);
  }
  
  public int getAlpha() {
    return Math.round(Color.alpha(this.mColor) * 255.0F);
  }
  
  public int getBidiFlags() {
    return this.mBidiFlags;
  }
  
  public BlendMode getBlendMode() {
    Xfermode xfermode = this.mXfermode;
    return (xfermode == null) ? null : BlendMode.fromValue(xfermode.porterDuffMode);
  }
  
  public int getColor() {
    return Color.toArgb(this.mColor);
  }
  
  public ColorFilter getColorFilter() {
    return this.mColorFilter;
  }
  
  public long getColorLong() {
    return this.mColor;
  }
  
  public int getEndHyphenEdit() {
    return nGetEndHyphenEdit(this.mNativePaint);
  }
  
  public boolean getFillPath(Path paramPath1, Path paramPath2) {
    return nGetFillPath(this.mNativePaint, paramPath1.readOnlyNI(), paramPath2.mutateNI());
  }
  
  public int getFlags() {
    return nGetFlags(this.mNativePaint);
  }
  
  public String getFontFeatureSettings() {
    return this.mFontFeatureSettings;
  }
  
  public float getFontMetrics(FontMetrics paramFontMetrics) {
    return nGetFontMetrics(this.mNativePaint, paramFontMetrics);
  }
  
  public FontMetrics getFontMetrics() {
    FontMetrics fontMetrics = new FontMetrics();
    getFontMetrics(fontMetrics);
    return fontMetrics;
  }
  
  public int getFontMetricsInt(FontMetricsInt paramFontMetricsInt) {
    return nGetFontMetricsInt(this.mNativePaint, paramFontMetricsInt);
  }
  
  public FontMetricsInt getFontMetricsInt() {
    FontMetricsInt fontMetricsInt = new FontMetricsInt();
    getFontMetricsInt(fontMetricsInt);
    return fontMetricsInt;
  }
  
  public float getFontSpacing() {
    return getFontMetrics(null);
  }
  
  public String getFontVariationSettings() {
    return this.mFontVariationSettings;
  }
  
  public int getHinting() {
    return nGetHinting(this.mNativePaint);
  }
  
  public float getLetterSpacing() {
    return nGetLetterSpacing(this.mNativePaint);
  }
  
  public MaskFilter getMaskFilter() {
    return this.mMaskFilter;
  }
  
  public long getNativeInstance() {
    long l2;
    Shader shader = this.mShader;
    long l1 = 0L;
    if (shader == null) {
      l2 = 0L;
    } else {
      l2 = shader.getNativeInstance();
    } 
    if (l2 != this.mNativeShader) {
      this.mNativeShader = l2;
      nSetShader(this.mNativePaint, l2);
    } 
    ColorFilter colorFilter = this.mColorFilter;
    if (colorFilter == null) {
      l2 = l1;
    } else {
      l2 = colorFilter.getNativeInstance();
    } 
    if (l2 != this.mNativeColorFilter) {
      this.mNativeColorFilter = l2;
      nSetColorFilter(this.mNativePaint, l2);
    } 
    return this.mNativePaint;
  }
  
  public int getOffsetForAdvance(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, float paramFloat) {
    if (paramCharSequence != null) {
      if ((paramInt3 | paramInt1 | paramInt2 | paramInt4 | paramInt1 - paramInt3 | paramInt2 - paramInt1 | paramInt4 - paramInt2 | paramCharSequence.length() - paramInt4) >= 0) {
        char[] arrayOfChar = TemporaryBuffer.obtain(paramInt4 - paramInt3);
        TextUtils.getChars(paramCharSequence, paramInt3, paramInt4, arrayOfChar, 0);
        paramInt1 = getOffsetForAdvance(arrayOfChar, paramInt1 - paramInt3, paramInt2 - paramInt3, 0, paramInt4 - paramInt3, paramBoolean, paramFloat);
        TemporaryBuffer.recycle(arrayOfChar);
        return paramInt1 + paramInt3;
      } 
      throw new IndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public int getOffsetForAdvance(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, float paramFloat) {
    if (paramArrayOfchar != null) {
      if ((paramInt3 | paramInt1 | paramInt2 | paramInt4 | paramInt1 - paramInt3 | paramInt2 - paramInt1 | paramInt4 - paramInt2 | paramArrayOfchar.length - paramInt4) >= 0)
        return nGetOffsetForAdvance(this.mNativePaint, paramArrayOfchar, paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean, paramFloat); 
      throw new IndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public PathEffect getPathEffect() {
    return this.mPathEffect;
  }
  
  @Deprecated
  public Rasterizer getRasterizer() {
    return null;
  }
  
  public float getRunAdvance(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, int paramInt5) {
    if (paramCharSequence != null) {
      if ((paramInt3 | paramInt1 | paramInt5 | paramInt2 | paramInt4 | paramInt1 - paramInt3 | paramInt5 - paramInt1 | paramInt2 - paramInt5 | paramInt4 - paramInt2 | paramCharSequence.length() - paramInt4) >= 0) {
        if (paramInt2 == paramInt1)
          return 0.0F; 
        char[] arrayOfChar = TemporaryBuffer.obtain(paramInt4 - paramInt3);
        TextUtils.getChars(paramCharSequence, paramInt3, paramInt4, arrayOfChar, 0);
        float f = getRunAdvance(arrayOfChar, paramInt1 - paramInt3, paramInt2 - paramInt3, 0, paramInt4 - paramInt3, paramBoolean, paramInt5 - paramInt3);
        TemporaryBuffer.recycle(arrayOfChar);
        return f;
      } 
      throw new IndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public float getRunAdvance(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, int paramInt5) {
    if (paramArrayOfchar != null) {
      if ((paramInt3 | paramInt1 | paramInt5 | paramInt2 | paramInt4 | paramInt1 - paramInt3 | paramInt5 - paramInt1 | paramInt2 - paramInt5 | paramInt4 - paramInt2 | paramArrayOfchar.length - paramInt4) >= 0)
        return (paramInt2 == paramInt1) ? 0.0F : nGetRunAdvance(this.mNativePaint, paramArrayOfchar, paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean, paramInt5); 
      throw new IndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public Shader getShader() {
    return this.mShader;
  }
  
  public int getShadowLayerColor() {
    return Color.toArgb(this.mShadowLayerColor);
  }
  
  public long getShadowLayerColorLong() {
    return this.mShadowLayerColor;
  }
  
  public float getShadowLayerDx() {
    return this.mShadowLayerDx;
  }
  
  public float getShadowLayerDy() {
    return this.mShadowLayerDy;
  }
  
  public float getShadowLayerRadius() {
    return this.mShadowLayerRadius;
  }
  
  public int getStartHyphenEdit() {
    return nGetStartHyphenEdit(this.mNativePaint);
  }
  
  public float getStrikeThruPosition() {
    return nGetStrikeThruPosition(this.mNativePaint);
  }
  
  public float getStrikeThruThickness() {
    return nGetStrikeThruThickness(this.mNativePaint);
  }
  
  public Cap getStrokeCap() {
    return sCapArray[nGetStrokeCap(this.mNativePaint)];
  }
  
  public Join getStrokeJoin() {
    return sJoinArray[nGetStrokeJoin(this.mNativePaint)];
  }
  
  public float getStrokeMiter() {
    return nGetStrokeMiter(this.mNativePaint);
  }
  
  public float getStrokeWidth() {
    return nGetStrokeWidth(this.mNativePaint);
  }
  
  public Style getStyle() {
    return sStyleArray[nGetStyle(this.mNativePaint)];
  }
  
  public Align getTextAlign() {
    return sAlignArray[nGetTextAlign(this.mNativePaint)];
  }
  
  public void getTextBounds(CharSequence paramCharSequence, int paramInt1, int paramInt2, Rect paramRect) {
    if ((paramInt1 | paramInt2 | paramInt2 - paramInt1 | paramCharSequence.length() - paramInt2) >= 0) {
      if (paramRect != null) {
        char[] arrayOfChar = TemporaryBuffer.obtain(paramInt2 - paramInt1);
        TextUtils.getChars(paramCharSequence, paramInt1, paramInt2, arrayOfChar, 0);
        getTextBounds(arrayOfChar, 0, paramInt2 - paramInt1, paramRect);
        TemporaryBuffer.recycle(arrayOfChar);
        return;
      } 
      throw new NullPointerException("need bounds Rect");
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public void getTextBounds(String paramString, int paramInt1, int paramInt2, Rect paramRect) {
    if ((paramInt1 | paramInt2 | paramInt2 - paramInt1 | paramString.length() - paramInt2) >= 0) {
      if (paramRect != null) {
        nGetStringBounds(this.mNativePaint, paramString, paramInt1, paramInt2, this.mBidiFlags, paramRect);
        return;
      } 
      throw new NullPointerException("need bounds Rect");
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public void getTextBounds(char[] paramArrayOfchar, int paramInt1, int paramInt2, Rect paramRect) {
    if ((paramInt1 | paramInt2) >= 0 && paramInt1 + paramInt2 <= paramArrayOfchar.length) {
      if (paramRect != null) {
        nGetCharArrayBounds(this.mNativePaint, paramArrayOfchar, paramInt1, paramInt2, this.mBidiFlags, paramRect);
        return;
      } 
      throw new NullPointerException("need bounds Rect");
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public Locale getTextLocale() {
    return this.mLocales.get(0);
  }
  
  public LocaleList getTextLocales() {
    return this.mLocales;
  }
  
  public void getTextPath(String paramString, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, Path paramPath) {
    if ((paramInt1 | paramInt2 | paramInt2 - paramInt1 | paramString.length() - paramInt2) >= 0) {
      nGetTextPath(this.mNativePaint, this.mBidiFlags, paramString, paramInt1, paramInt2, paramFloat1, paramFloat2, paramPath.mutateNI());
      return;
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public void getTextPath(char[] paramArrayOfchar, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, Path paramPath) {
    if ((paramInt1 | paramInt2) >= 0 && paramInt1 + paramInt2 <= paramArrayOfchar.length) {
      nGetTextPath(this.mNativePaint, this.mBidiFlags, paramArrayOfchar, paramInt1, paramInt2, paramFloat1, paramFloat2, paramPath.mutateNI());
      return;
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public float getTextRunAdvances(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, float[] paramArrayOffloat, int paramInt5) {
    if (paramArrayOfchar != null) {
      int j;
      int i = paramArrayOfchar.length;
      if (paramArrayOffloat == null) {
        j = 0;
      } else {
        j = paramArrayOffloat.length - paramInt5 + paramInt2;
      } 
      if ((paramInt1 | paramInt2 | paramInt3 | paramInt4 | paramInt5 | paramInt1 - paramInt3 | paramInt4 - paramInt2 | paramInt3 + paramInt4 - paramInt1 + paramInt2 | i - paramInt3 + paramInt4 | j) >= 0) {
        if (paramArrayOfchar.length == 0 || paramInt2 == 0)
          return 0.0F; 
        if (!this.mHasCompatScaling) {
          long l1 = this.mNativePaint;
          if (paramBoolean) {
            j = 5;
          } else {
            j = 4;
          } 
          return nGetTextAdvances(l1, paramArrayOfchar, paramInt1, paramInt2, paramInt3, paramInt4, j, paramArrayOffloat, paramInt5);
        } 
        float f1 = getTextSize();
        setTextSize(this.mCompatScaling * f1);
        long l = this.mNativePaint;
        if (paramBoolean) {
          j = 5;
        } else {
          j = 4;
        } 
        float f2 = nGetTextAdvances(l, paramArrayOfchar, paramInt1, paramInt2, paramInt3, paramInt4, j, paramArrayOffloat, paramInt5);
        setTextSize(f1);
        if (paramArrayOffloat != null)
          for (paramInt1 = paramInt5; paramInt1 < paramInt5 + paramInt2; paramInt1++)
            paramArrayOffloat[paramInt1] = paramArrayOffloat[paramInt1] * this.mInvCompatScaling;  
        return this.mInvCompatScaling * f2;
      } 
      throw new IndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public int getTextRunCursor(CharSequence paramCharSequence, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4) {
    if (paramCharSequence instanceof String || paramCharSequence instanceof android.text.SpannedString || paramCharSequence instanceof android.text.SpannableString)
      return getTextRunCursor(paramCharSequence.toString(), paramInt1, paramInt2, paramBoolean, paramInt3, paramInt4); 
    if (paramCharSequence instanceof GraphicsOperations)
      return ((GraphicsOperations)paramCharSequence).getTextRunCursor(paramInt1, paramInt2, paramBoolean, paramInt3, paramInt4, this); 
    int i = paramInt2 - paramInt1;
    char[] arrayOfChar = TemporaryBuffer.obtain(i);
    TextUtils.getChars(paramCharSequence, paramInt1, paramInt2, arrayOfChar, 0);
    paramInt3 = getTextRunCursor(arrayOfChar, 0, i, paramBoolean, paramInt3 - paramInt1, paramInt4);
    TemporaryBuffer.recycle(arrayOfChar);
    paramInt2 = -1;
    if (paramInt3 == -1) {
      paramInt1 = paramInt2;
    } else {
      paramInt1 = paramInt3 + paramInt1;
    } 
    return paramInt1;
  }
  
  public int getTextRunCursor(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4) {
    if ((paramInt1 | paramInt2 | paramInt3 | paramInt2 - paramInt1 | paramInt3 - paramInt1 | paramInt2 - paramInt3 | paramString.length() - paramInt2 | paramInt4) >= 0 && paramInt4 <= 4) {
      long l = this.mNativePaint;
      return nGetTextRunCursor(l, paramString, paramInt1, paramInt2, paramBoolean, paramInt3, paramInt4);
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public int getTextRunCursor(char[] paramArrayOfchar, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4) {
    int i = paramInt1 + paramInt2;
    if ((paramInt1 | i | paramInt3 | i - paramInt1 | paramInt3 - paramInt1 | i - paramInt3 | paramArrayOfchar.length - i | paramInt4) >= 0 && paramInt4 <= 4) {
      long l = this.mNativePaint;
      return nGetTextRunCursor(l, paramArrayOfchar, paramInt1, paramInt2, paramBoolean, paramInt3, paramInt4);
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public float getTextScaleX() {
    return nGetTextScaleX(this.mNativePaint);
  }
  
  public float getTextSize() {
    return nGetTextSize(this.mNativePaint);
  }
  
  public float getTextSkewX() {
    return nGetTextSkewX(this.mNativePaint);
  }
  
  public int getTextWidths(CharSequence paramCharSequence, int paramInt1, int paramInt2, float[] paramArrayOffloat) {
    if (paramCharSequence != null) {
      if ((paramInt1 | paramInt2 | paramInt2 - paramInt1 | paramCharSequence.length() - paramInt2) >= 0) {
        if (paramInt2 - paramInt1 <= paramArrayOffloat.length) {
          if (paramCharSequence.length() == 0 || paramInt1 == paramInt2)
            return 0; 
          if (paramCharSequence instanceof String)
            return getTextWidths((String)paramCharSequence, paramInt1, paramInt2, paramArrayOffloat); 
          if (paramCharSequence instanceof android.text.SpannedString || paramCharSequence instanceof android.text.SpannableString)
            return getTextWidths(paramCharSequence.toString(), paramInt1, paramInt2, paramArrayOffloat); 
          if (paramCharSequence instanceof GraphicsOperations)
            return ((GraphicsOperations)paramCharSequence).getTextWidths(paramInt1, paramInt2, paramArrayOffloat, this); 
          char[] arrayOfChar = TemporaryBuffer.obtain(paramInt2 - paramInt1);
          TextUtils.getChars(paramCharSequence, paramInt1, paramInt2, arrayOfChar, 0);
          paramInt1 = getTextWidths(arrayOfChar, 0, paramInt2 - paramInt1, paramArrayOffloat);
          TemporaryBuffer.recycle(arrayOfChar);
          return paramInt1;
        } 
        throw new ArrayIndexOutOfBoundsException();
      } 
      throw new IndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public int getTextWidths(String paramString, int paramInt1, int paramInt2, float[] paramArrayOffloat) {
    if (paramString != null) {
      if ((paramInt1 | paramInt2 | paramInt2 - paramInt1 | paramString.length() - paramInt2) >= 0) {
        if (paramInt2 - paramInt1 <= paramArrayOffloat.length) {
          if (paramString.length() == 0 || paramInt1 == paramInt2)
            return 0; 
          if (!this.mHasCompatScaling) {
            nGetTextAdvances(this.mNativePaint, paramString, paramInt1, paramInt2, paramInt1, paramInt2, this.mBidiFlags, paramArrayOffloat, 0);
            return paramInt2 - paramInt1;
          } 
          float f = getTextSize();
          setTextSize(this.mCompatScaling * f);
          nGetTextAdvances(this.mNativePaint, paramString, paramInt1, paramInt2, paramInt1, paramInt2, this.mBidiFlags, paramArrayOffloat, 0);
          setTextSize(f);
          for (byte b = 0; b < paramInt2 - paramInt1; b++)
            paramArrayOffloat[b] = paramArrayOffloat[b] * this.mInvCompatScaling; 
          return paramInt2 - paramInt1;
        } 
        throw new ArrayIndexOutOfBoundsException();
      } 
      throw new IndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public int getTextWidths(String paramString, float[] paramArrayOffloat) {
    return getTextWidths(paramString, 0, paramString.length(), paramArrayOffloat);
  }
  
  public int getTextWidths(char[] paramArrayOfchar, int paramInt1, int paramInt2, float[] paramArrayOffloat) {
    if (paramArrayOfchar != null) {
      if ((paramInt1 | paramInt2) >= 0 && paramInt1 + paramInt2 <= paramArrayOfchar.length && paramInt2 <= paramArrayOffloat.length) {
        if (paramArrayOfchar.length == 0 || paramInt2 == 0)
          return 0; 
        if (!this.mHasCompatScaling) {
          nGetTextAdvances(this.mNativePaint, paramArrayOfchar, paramInt1, paramInt2, paramInt1, paramInt2, this.mBidiFlags, paramArrayOffloat, 0);
          return paramInt2;
        } 
        float f = getTextSize();
        setTextSize(this.mCompatScaling * f);
        nGetTextAdvances(this.mNativePaint, paramArrayOfchar, paramInt1, paramInt2, paramInt1, paramInt2, this.mBidiFlags, paramArrayOffloat, 0);
        setTextSize(f);
        for (paramInt1 = 0; paramInt1 < paramInt2; paramInt1++)
          paramArrayOffloat[paramInt1] = paramArrayOffloat[paramInt1] * this.mInvCompatScaling; 
        return paramInt2;
      } 
      throw new ArrayIndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public Typeface getTypeface() {
    return this.mTypeface;
  }
  
  public float getUnderlinePosition() {
    return nGetUnderlinePosition(this.mNativePaint);
  }
  
  public float getUnderlineThickness() {
    return nGetUnderlineThickness(this.mNativePaint);
  }
  
  public float getWordSpacing() {
    return nGetWordSpacing(this.mNativePaint);
  }
  
  public Xfermode getXfermode() {
    return this.mXfermode;
  }
  
  public boolean hasGlyph(String paramString) {
    return nHasGlyph(this.mNativePaint, this.mBidiFlags, paramString);
  }
  
  public boolean hasShadowLayer() {
    return nHasShadowLayer(this.mNativePaint);
  }
  
  public final boolean isAntiAlias() {
    int i = getFlags();
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public final boolean isDither() {
    boolean bool;
    if ((getFlags() & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isElegantTextHeight() {
    return nIsElegantTextHeight(this.mNativePaint);
  }
  
  public final boolean isFakeBoldText() {
    boolean bool;
    if ((getFlags() & 0x20) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isFilterBitmap() {
    boolean bool;
    if ((getFlags() & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isLinearText() {
    boolean bool;
    if ((getFlags() & 0x40) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isStrikeThruText() {
    boolean bool;
    if ((getFlags() & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isSubpixelText() {
    boolean bool;
    if ((getFlags() & 0x80) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isUnderlineText() {
    boolean bool;
    if ((getFlags() & 0x8) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public float measureText(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    if (paramCharSequence != null) {
      if ((paramInt1 | paramInt2 | paramInt2 - paramInt1 | paramCharSequence.length() - paramInt2) >= 0) {
        if (paramCharSequence.length() == 0 || paramInt1 == paramInt2)
          return 0.0F; 
        if (paramCharSequence instanceof String)
          return measureText((String)paramCharSequence, paramInt1, paramInt2); 
        if (paramCharSequence instanceof android.text.SpannedString || paramCharSequence instanceof android.text.SpannableString)
          return measureText(paramCharSequence.toString(), paramInt1, paramInt2); 
        if (paramCharSequence instanceof GraphicsOperations)
          return ((GraphicsOperations)paramCharSequence).measureText(paramInt1, paramInt2, this); 
        char[] arrayOfChar = TemporaryBuffer.obtain(paramInt2 - paramInt1);
        TextUtils.getChars(paramCharSequence, paramInt1, paramInt2, arrayOfChar, 0);
        float f = measureText(arrayOfChar, 0, paramInt2 - paramInt1);
        TemporaryBuffer.recycle(arrayOfChar);
        return f;
      } 
      throw new IndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public float measureText(String paramString) {
    if (paramString != null)
      return measureText(paramString, 0, paramString.length()); 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public float measureText(String paramString, int paramInt1, int paramInt2) {
    if (paramString != null) {
      if ((paramInt1 | paramInt2 | paramInt2 - paramInt1 | paramString.length() - paramInt2) >= 0) {
        if (paramString.length() == 0 || paramInt1 == paramInt2)
          return 0.0F; 
        if (!this.mHasCompatScaling)
          return (float)Math.ceil(nGetTextAdvances(this.mNativePaint, paramString, paramInt1, paramInt2, paramInt1, paramInt2, this.mBidiFlags, (float[])null, 0)); 
        float f1 = getTextSize();
        setTextSize(this.mCompatScaling * f1);
        float f2 = nGetTextAdvances(this.mNativePaint, paramString, paramInt1, paramInt2, paramInt1, paramInt2, this.mBidiFlags, (float[])null, 0);
        setTextSize(f1);
        return (float)Math.ceil((this.mInvCompatScaling * f2));
      } 
      throw new IndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public float measureText(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (paramArrayOfchar != null) {
      if ((paramInt1 | paramInt2) >= 0 && paramInt1 + paramInt2 <= paramArrayOfchar.length) {
        if (paramArrayOfchar.length == 0 || paramInt2 == 0)
          return 0.0F; 
        if (!this.mHasCompatScaling)
          return (float)Math.ceil(nGetTextAdvances(this.mNativePaint, paramArrayOfchar, paramInt1, paramInt2, paramInt1, paramInt2, this.mBidiFlags, (float[])null, 0)); 
        float f1 = getTextSize();
        setTextSize(this.mCompatScaling * f1);
        float f2 = nGetTextAdvances(this.mNativePaint, paramArrayOfchar, paramInt1, paramInt2, paramInt1, paramInt2, this.mBidiFlags, (float[])null, 0);
        setTextSize(f1);
        return (float)Math.ceil((this.mInvCompatScaling * f2));
      } 
      throw new ArrayIndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("text cannot be null");
  }
  
  public void reset() {
    nReset(this.mNativePaint);
    setFlags(1282);
    this.mColor = Color.pack(-16777216);
    this.mColorFilter = null;
    this.mMaskFilter = null;
    this.mPathEffect = null;
    this.mShader = null;
    this.mNativeShader = 0L;
    this.mTypeface = null;
    this.mXfermode = null;
    this.mHasCompatScaling = false;
    this.mCompatScaling = 1.0F;
    this.mInvCompatScaling = 1.0F;
    this.mBidiFlags = 2;
    setTextLocales(LocaleList.getAdjustedDefault());
    setElegantTextHeight(false);
    this.mFontFeatureSettings = null;
    this.mFontVariationSettings = null;
    this.mShadowLayerRadius = 0.0F;
    this.mShadowLayerDx = 0.0F;
    this.mShadowLayerDy = 0.0F;
    this.mShadowLayerColor = Color.pack(0);
  }
  
  public void set(Paint paramPaint) {
    if (this != paramPaint) {
      nSet(this.mNativePaint, paramPaint.mNativePaint);
      setClassVariablesFrom(paramPaint);
    } 
  }
  
  public void setARGB(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    setColor(paramInt1 << 24 | paramInt2 << 16 | paramInt3 << 8 | paramInt4);
  }
  
  public void setAlpha(int paramInt) {
    ColorSpace colorSpace = Color.colorSpace(this.mColor);
    this.mColor = Color.pack(Color.red(this.mColor), Color.green(this.mColor), Color.blue(this.mColor), paramInt * 0.003921569F, colorSpace);
    nSetAlpha(this.mNativePaint, paramInt);
  }
  
  public void setAntiAlias(boolean paramBoolean) {
    nSetAntiAlias(this.mNativePaint, paramBoolean);
  }
  
  public void setBidiFlags(int paramInt) {
    paramInt &= 0x7;
    if (paramInt <= 5) {
      this.mBidiFlags = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("unknown bidi flag: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setBlendMode(BlendMode paramBlendMode) {
    if (paramBlendMode != null) {
      Xfermode xfermode = paramBlendMode.getXfermode();
    } else {
      paramBlendMode = null;
    } 
    installXfermode((Xfermode)paramBlendMode);
  }
  
  public void setColor(int paramInt) {
    nSetColor(this.mNativePaint, paramInt);
    this.mColor = Color.pack(paramInt);
  }
  
  public void setColor(long paramLong) {
    ColorSpace colorSpace = Color.colorSpace(paramLong);
    nSetColor(this.mNativePaint, colorSpace.getNativeInstance(), paramLong);
    this.mColor = paramLong;
  }
  
  public ColorFilter setColorFilter(ColorFilter paramColorFilter) {
    if (this.mColorFilter != paramColorFilter)
      this.mNativeColorFilter = -1L; 
    this.mColorFilter = paramColorFilter;
    return paramColorFilter;
  }
  
  public void setCompatibilityScaling(float paramFloat) {
    if (paramFloat == 1.0D) {
      this.mHasCompatScaling = false;
      this.mInvCompatScaling = 1.0F;
      this.mCompatScaling = 1.0F;
    } else {
      this.mHasCompatScaling = true;
      this.mCompatScaling = paramFloat;
      this.mInvCompatScaling = 1.0F / paramFloat;
    } 
  }
  
  public void setDither(boolean paramBoolean) {
    nSetDither(this.mNativePaint, paramBoolean);
  }
  
  public void setElegantTextHeight(boolean paramBoolean) {
    nSetElegantTextHeight(this.mNativePaint, paramBoolean);
  }
  
  public void setEndHyphenEdit(int paramInt) {
    nSetEndHyphenEdit(this.mNativePaint, paramInt);
  }
  
  public void setFakeBoldText(boolean paramBoolean) {
    nSetFakeBoldText(this.mNativePaint, paramBoolean);
  }
  
  public void setFilterBitmap(boolean paramBoolean) {
    nSetFilterBitmap(this.mNativePaint, paramBoolean);
  }
  
  public void setFlags(int paramInt) {
    nSetFlags(this.mNativePaint, paramInt);
  }
  
  public void setFontFeatureSettings(String paramString) {
    String str = paramString;
    if (paramString != null) {
      str = paramString;
      if (paramString.equals(""))
        str = null; 
    } 
    if ((str == null && this.mFontFeatureSettings == null) || (str != null && str.equals(this.mFontFeatureSettings)))
      return; 
    this.mFontFeatureSettings = str;
    nSetFontFeatureSettings(this.mNativePaint, str);
  }
  
  public boolean setFontVariationSettings(String paramString) {
    String str = TextUtils.nullIfEmpty(paramString);
    paramString = this.mFontVariationSettings;
    if (str == paramString || (str != null && str.equals(paramString)))
      return true; 
    if (str == null || str.length() == 0) {
      this.mFontVariationSettings = null;
      setTypeface(Typeface.createFromTypefaceWithVariation(this.mTypeface, Collections.emptyList()));
      return true;
    } 
    Typeface typeface2 = this.mTypeface;
    Typeface typeface1 = typeface2;
    if (typeface2 == null)
      typeface1 = Typeface.DEFAULT; 
    FontVariationAxis[] arrayOfFontVariationAxis = FontVariationAxis.fromFontVariationSettings(str);
    ArrayList<FontVariationAxis> arrayList = new ArrayList();
    int i = arrayOfFontVariationAxis.length;
    for (byte b = 0; b < i; b++) {
      FontVariationAxis fontVariationAxis = arrayOfFontVariationAxis[b];
      if (typeface1.isSupportedAxes(fontVariationAxis.getOpenTypeTagValue()))
        arrayList.add(fontVariationAxis); 
    } 
    if (arrayList.isEmpty())
      return false; 
    this.mFontVariationSettings = str;
    setTypeface(Typeface.createFromTypefaceWithVariation(typeface1, arrayList));
    return true;
  }
  
  public void setHinting(int paramInt) {
    nSetHinting(this.mNativePaint, paramInt);
  }
  
  public void setLetterSpacing(float paramFloat) {
    nSetLetterSpacing(this.mNativePaint, paramFloat);
  }
  
  public void setLinearText(boolean paramBoolean) {
    nSetLinearText(this.mNativePaint, paramBoolean);
  }
  
  public MaskFilter setMaskFilter(MaskFilter paramMaskFilter) {
    long l = 0L;
    if (paramMaskFilter != null)
      l = paramMaskFilter.native_instance; 
    nSetMaskFilter(this.mNativePaint, l);
    this.mMaskFilter = paramMaskFilter;
    return paramMaskFilter;
  }
  
  public PathEffect setPathEffect(PathEffect paramPathEffect) {
    long l = 0L;
    if (paramPathEffect != null)
      l = paramPathEffect.native_instance; 
    nSetPathEffect(this.mNativePaint, l);
    this.mPathEffect = paramPathEffect;
    return paramPathEffect;
  }
  
  @Deprecated
  public Rasterizer setRasterizer(Rasterizer paramRasterizer) {
    return paramRasterizer;
  }
  
  public Shader setShader(Shader paramShader) {
    if (this.mShader != paramShader) {
      this.mNativeShader = -1L;
      nSetShader(this.mNativePaint, 0L);
    } 
    this.mShader = paramShader;
    return paramShader;
  }
  
  public void setShadowLayer(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt) {
    setShadowLayer(paramFloat1, paramFloat2, paramFloat3, Color.pack(paramInt));
  }
  
  public void setShadowLayer(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong) {
    ColorSpace colorSpace = Color.colorSpace(paramLong);
    nSetShadowLayer(this.mNativePaint, paramFloat1, paramFloat2, paramFloat3, colorSpace.getNativeInstance(), paramLong);
    this.mShadowLayerRadius = paramFloat1;
    this.mShadowLayerDx = paramFloat2;
    this.mShadowLayerDy = paramFloat3;
    this.mShadowLayerColor = paramLong;
  }
  
  public void setStartHyphenEdit(int paramInt) {
    nSetStartHyphenEdit(this.mNativePaint, paramInt);
  }
  
  public void setStrikeThruText(boolean paramBoolean) {
    nSetStrikeThruText(this.mNativePaint, paramBoolean);
  }
  
  public void setStrokeCap(Cap paramCap) {
    nSetStrokeCap(this.mNativePaint, paramCap.nativeInt);
  }
  
  public void setStrokeJoin(Join paramJoin) {
    nSetStrokeJoin(this.mNativePaint, paramJoin.nativeInt);
  }
  
  public void setStrokeMiter(float paramFloat) {
    nSetStrokeMiter(this.mNativePaint, paramFloat);
  }
  
  public void setStrokeWidth(float paramFloat) {
    nSetStrokeWidth(this.mNativePaint, paramFloat);
  }
  
  public void setStyle(Style paramStyle) {
    nSetStyle(this.mNativePaint, paramStyle.nativeInt);
  }
  
  public void setSubpixelText(boolean paramBoolean) {
    nSetSubpixelText(this.mNativePaint, paramBoolean);
  }
  
  public void setTextAlign(Align paramAlign) {
    nSetTextAlign(this.mNativePaint, paramAlign.nativeInt);
  }
  
  public void setTextLocale(Locale paramLocale) {
    if (paramLocale != null) {
      LocaleList localeList = this.mLocales;
      if (localeList != null && localeList.size() == 1 && paramLocale.equals(this.mLocales.get(0)))
        return; 
      this.mLocales = new LocaleList(new Locale[] { paramLocale });
      syncTextLocalesWithMinikin();
      return;
    } 
    throw new IllegalArgumentException("locale cannot be null");
  }
  
  public void setTextLocales(LocaleList paramLocaleList) {
    if (paramLocaleList != null && !paramLocaleList.isEmpty()) {
      if (paramLocaleList.equals(this.mLocales))
        return; 
      this.mLocales = paramLocaleList;
      syncTextLocalesWithMinikin();
      return;
    } 
    throw new IllegalArgumentException("locales cannot be null or empty");
  }
  
  public void setTextScaleX(float paramFloat) {
    nSetTextScaleX(this.mNativePaint, paramFloat);
  }
  
  public void setTextSize(float paramFloat) {
    nSetTextSize(this.mNativePaint, paramFloat);
  }
  
  public void setTextSkewX(float paramFloat) {
    nSetTextSkewX(this.mNativePaint, paramFloat);
  }
  
  public Typeface setTypeface(Typeface paramTypeface) {
    long l;
    if (paramTypeface == null) {
      l = 0L;
    } else {
      l = paramTypeface.native_instance;
    } 
    nSetTypeface(this.mNativePaint, l);
    this.mTypeface = paramTypeface;
    return paramTypeface;
  }
  
  public void setUnderlineText(boolean paramBoolean) {
    nSetUnderlineText(this.mNativePaint, paramBoolean);
  }
  
  public void setWordSpacing(float paramFloat) {
    nSetWordSpacing(this.mNativePaint, paramFloat);
  }
  
  public Xfermode setXfermode(Xfermode paramXfermode) {
    return installXfermode(paramXfermode);
  }
  
  public enum Align {
    CENTER,
    LEFT(0),
    RIGHT(0);
    
    final int nativeInt;
    
    static {
      Align align = new Align("RIGHT", 2, 2);
      RIGHT = align;
      $VALUES = new Align[] { LEFT, CENTER, align };
    }
    
    Align(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
  
  public enum Cap {
    BUTT(0),
    ROUND(1),
    SQUARE(1);
    
    final int nativeInt;
    
    static {
      Cap cap = new Cap("SQUARE", 2, 2);
      SQUARE = cap;
      $VALUES = new Cap[] { BUTT, ROUND, cap };
    }
    
    Cap(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface CursorOption {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface EndHyphenEdit {}
  
  public static class FontMetrics {
    public float ascent;
    
    public float bottom;
    
    public float descent;
    
    public float leading;
    
    public float top;
  }
  
  public static class FontMetricsInt {
    public int ascent;
    
    public int bottom;
    
    public int descent;
    
    public int leading;
    
    public int top;
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("FontMetricsInt: top=");
      stringBuilder.append(this.top);
      stringBuilder.append(" ascent=");
      stringBuilder.append(this.ascent);
      stringBuilder.append(" descent=");
      stringBuilder.append(this.descent);
      stringBuilder.append(" bottom=");
      stringBuilder.append(this.bottom);
      stringBuilder.append(" leading=");
      stringBuilder.append(this.leading);
      return stringBuilder.toString();
    }
  }
  
  public enum Join {
    MITER(0),
    BEVEL(1),
    ROUND(1);
    
    final int nativeInt;
    
    static {
      Join join = new Join("BEVEL", 2, 2);
      BEVEL = join;
      $VALUES = new Join[] { MITER, ROUND, join };
    }
    
    Join(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
  
  private static class NoImagePreloadHolder {
    public static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(Paint.class.getClassLoader(), Paint.nGetNativeFinalizer());
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface StartHyphenEdit {}
  
  public enum Style {
    FILL(0),
    FILL_AND_STROKE(0),
    STROKE(1);
    
    final int nativeInt;
    
    static {
      Style style = new Style("FILL_AND_STROKE", 2, 2);
      FILL_AND_STROKE = style;
      $VALUES = new Style[] { FILL, STROKE, style };
    }
    
    Style(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Paint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */