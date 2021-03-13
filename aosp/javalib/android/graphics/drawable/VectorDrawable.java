package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.ComplexColor;
import android.content.res.GradientColor;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.IntProperty;
import android.util.Log;
import android.util.PathParser;
import android.util.Property;
import android.util.Xml;
import com.android.internal.R;
import com.android.internal.util.VirtualRefBasePtr;
import dalvik.annotation.optimization.FastNative;
import dalvik.system.VMRuntime;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawable extends Drawable {
  private static final String LOGTAG = VectorDrawable.class.getSimpleName();
  
  private static final String SHAPE_CLIP_PATH = "clip-path";
  
  private static final String SHAPE_GROUP = "group";
  
  private static final String SHAPE_PATH = "path";
  
  private static final String SHAPE_VECTOR = "vector";
  
  private BlendModeColorFilter mBlendModeColorFilter;
  
  private ColorFilter mColorFilter;
  
  private boolean mDpiScaledDirty = true;
  
  private int mDpiScaledHeight = 0;
  
  private Insets mDpiScaledInsets = Insets.NONE;
  
  private int mDpiScaledWidth = 0;
  
  private boolean mMutated;
  
  private int mTargetDensity;
  
  private PorterDuffColorFilter mTintFilter;
  
  private final Rect mTmpBounds = new Rect();
  
  private VectorDrawableState mVectorState;
  
  public VectorDrawable() {
    this(new VectorDrawableState(null), (Resources)null);
  }
  
  private VectorDrawable(VectorDrawableState paramVectorDrawableState, Resources paramResources) {
    this.mVectorState = paramVectorDrawableState;
    updateLocalState(paramResources);
  }
  
  public static VectorDrawable create(Resources paramResources, int paramInt) {
    try {
      XmlResourceParser xmlResourceParser = paramResources.getXml(paramInt);
      AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)xmlResourceParser);
      while (true) {
        paramInt = xmlResourceParser.next();
        if (paramInt != 2 && paramInt != 1)
          continue; 
        break;
      } 
      if (paramInt == 2) {
        VectorDrawable vectorDrawable = new VectorDrawable();
        this();
        vectorDrawable.inflate(paramResources, (XmlPullParser)xmlResourceParser, attributeSet);
        return vectorDrawable;
      } 
      XmlPullParserException xmlPullParserException = new XmlPullParserException();
      this("No start tag found");
      throw xmlPullParserException;
    } catch (XmlPullParserException xmlPullParserException) {
      Log.e(LOGTAG, "parser error", (Throwable)xmlPullParserException);
    } catch (IOException iOException) {
      Log.e(LOGTAG, "parser error", iOException);
    } 
    return null;
  }
  
  private void inflateChildElements(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    // Byte code:
    //   0: aload_0
    //   1: getfield mVectorState : Landroid/graphics/drawable/VectorDrawable$VectorDrawableState;
    //   4: astore #5
    //   6: iconst_1
    //   7: istore #6
    //   9: new java/util/Stack
    //   12: dup
    //   13: invokespecial <init> : ()V
    //   16: astore #7
    //   18: aload #7
    //   20: aload #5
    //   22: getfield mRootGroup : Landroid/graphics/drawable/VectorDrawable$VGroup;
    //   25: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   28: pop
    //   29: aload_2
    //   30: invokeinterface getEventType : ()I
    //   35: istore #8
    //   37: aload_2
    //   38: invokeinterface getDepth : ()I
    //   43: istore #9
    //   45: iload #8
    //   47: iconst_1
    //   48: if_icmpeq -> 403
    //   51: aload_2
    //   52: invokeinterface getDepth : ()I
    //   57: iload #9
    //   59: iconst_1
    //   60: iadd
    //   61: if_icmpge -> 70
    //   64: iload #8
    //   66: iconst_3
    //   67: if_icmpeq -> 403
    //   70: iload #8
    //   72: iconst_2
    //   73: if_icmpne -> 350
    //   76: aload_2
    //   77: invokeinterface getName : ()Ljava/lang/String;
    //   82: astore #10
    //   84: aload #7
    //   86: invokevirtual peek : ()Ljava/lang/Object;
    //   89: checkcast android/graphics/drawable/VectorDrawable$VGroup
    //   92: astore #11
    //   94: ldc 'path'
    //   96: aload #10
    //   98: invokevirtual equals : (Ljava/lang/Object;)Z
    //   101: ifeq -> 175
    //   104: new android/graphics/drawable/VectorDrawable$VFullPath
    //   107: dup
    //   108: invokespecial <init> : ()V
    //   111: astore #10
    //   113: aload #10
    //   115: aload_1
    //   116: aload_3
    //   117: aload #4
    //   119: invokevirtual inflate : (Landroid/content/res/Resources;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)V
    //   122: aload #11
    //   124: aload #10
    //   126: invokevirtual addChild : (Landroid/graphics/drawable/VectorDrawable$VObject;)V
    //   129: aload #10
    //   131: invokevirtual getPathName : ()Ljava/lang/String;
    //   134: ifnull -> 153
    //   137: aload #5
    //   139: getfield mVGTargetsMap : Landroid/util/ArrayMap;
    //   142: aload #10
    //   144: invokevirtual getPathName : ()Ljava/lang/String;
    //   147: aload #10
    //   149: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   152: pop
    //   153: iconst_0
    //   154: istore #12
    //   156: aload #5
    //   158: aload #5
    //   160: getfield mChangingConfigurations : I
    //   163: aload #10
    //   165: getfield mChangingConfigurations : I
    //   168: ior
    //   169: putfield mChangingConfigurations : I
    //   172: goto -> 347
    //   175: ldc 'clip-path'
    //   177: aload #10
    //   179: invokevirtual equals : (Ljava/lang/Object;)Z
    //   182: ifeq -> 253
    //   185: new android/graphics/drawable/VectorDrawable$VClipPath
    //   188: dup
    //   189: invokespecial <init> : ()V
    //   192: astore #10
    //   194: aload #10
    //   196: aload_1
    //   197: aload_3
    //   198: aload #4
    //   200: invokevirtual inflate : (Landroid/content/res/Resources;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)V
    //   203: aload #11
    //   205: aload #10
    //   207: invokevirtual addChild : (Landroid/graphics/drawable/VectorDrawable$VObject;)V
    //   210: aload #10
    //   212: invokevirtual getPathName : ()Ljava/lang/String;
    //   215: ifnull -> 234
    //   218: aload #5
    //   220: getfield mVGTargetsMap : Landroid/util/ArrayMap;
    //   223: aload #10
    //   225: invokevirtual getPathName : ()Ljava/lang/String;
    //   228: aload #10
    //   230: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   233: pop
    //   234: aload #5
    //   236: aload #5
    //   238: getfield mChangingConfigurations : I
    //   241: aload #10
    //   243: getfield mChangingConfigurations : I
    //   246: ior
    //   247: putfield mChangingConfigurations : I
    //   250: goto -> 343
    //   253: ldc 'group'
    //   255: aload #10
    //   257: invokevirtual equals : (Ljava/lang/Object;)Z
    //   260: ifeq -> 343
    //   263: new android/graphics/drawable/VectorDrawable$VGroup
    //   266: dup
    //   267: invokespecial <init> : ()V
    //   270: astore #10
    //   272: aload #10
    //   274: aload_1
    //   275: aload_3
    //   276: aload #4
    //   278: invokevirtual inflate : (Landroid/content/res/Resources;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)V
    //   281: aload #11
    //   283: aload #10
    //   285: invokevirtual addChild : (Landroid/graphics/drawable/VectorDrawable$VObject;)V
    //   288: aload #7
    //   290: aload #10
    //   292: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   295: pop
    //   296: aload #10
    //   298: invokevirtual getGroupName : ()Ljava/lang/String;
    //   301: ifnull -> 320
    //   304: aload #5
    //   306: getfield mVGTargetsMap : Landroid/util/ArrayMap;
    //   309: aload #10
    //   311: invokevirtual getGroupName : ()Ljava/lang/String;
    //   314: aload #10
    //   316: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   319: pop
    //   320: aload #5
    //   322: aload #5
    //   324: getfield mChangingConfigurations : I
    //   327: aload #10
    //   329: invokestatic access$100 : (Landroid/graphics/drawable/VectorDrawable$VGroup;)I
    //   332: ior
    //   333: putfield mChangingConfigurations : I
    //   336: iload #6
    //   338: istore #12
    //   340: goto -> 347
    //   343: iload #6
    //   345: istore #12
    //   347: goto -> 388
    //   350: iload #6
    //   352: istore #12
    //   354: iload #8
    //   356: iconst_3
    //   357: if_icmpne -> 347
    //   360: iload #6
    //   362: istore #12
    //   364: ldc 'group'
    //   366: aload_2
    //   367: invokeinterface getName : ()Ljava/lang/String;
    //   372: invokevirtual equals : (Ljava/lang/Object;)Z
    //   375: ifeq -> 388
    //   378: aload #7
    //   380: invokevirtual pop : ()Ljava/lang/Object;
    //   383: pop
    //   384: iload #6
    //   386: istore #12
    //   388: aload_2
    //   389: invokeinterface next : ()I
    //   394: istore #8
    //   396: iload #12
    //   398: istore #6
    //   400: goto -> 45
    //   403: iload #6
    //   405: ifeq -> 480
    //   408: new java/lang/StringBuffer
    //   411: dup
    //   412: invokespecial <init> : ()V
    //   415: astore_2
    //   416: aload_2
    //   417: invokevirtual length : ()I
    //   420: ifle -> 431
    //   423: aload_2
    //   424: ldc_w ' or '
    //   427: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   430: pop
    //   431: aload_2
    //   432: ldc 'path'
    //   434: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   437: pop
    //   438: new java/lang/StringBuilder
    //   441: dup
    //   442: invokespecial <init> : ()V
    //   445: astore_1
    //   446: aload_1
    //   447: ldc_w 'no '
    //   450: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: pop
    //   454: aload_1
    //   455: aload_2
    //   456: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   459: pop
    //   460: aload_1
    //   461: ldc_w ' defined'
    //   464: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   467: pop
    //   468: new org/xmlpull/v1/XmlPullParserException
    //   471: dup
    //   472: aload_1
    //   473: invokevirtual toString : ()Ljava/lang/String;
    //   476: invokespecial <init> : (Ljava/lang/String;)V
    //   479: athrow
    //   480: return
  }
  
  @FastNative
  private static native void nAddChild(long paramLong1, long paramLong2);
  
  @FastNative
  private static native long nCreateClipPath();
  
  @FastNative
  private static native long nCreateClipPath(long paramLong);
  
  @FastNative
  private static native long nCreateFullPath();
  
  @FastNative
  private static native long nCreateFullPath(long paramLong);
  
  @FastNative
  private static native long nCreateGroup();
  
  @FastNative
  private static native long nCreateGroup(long paramLong);
  
  @FastNative
  private static native long nCreateTree(long paramLong);
  
  @FastNative
  private static native long nCreateTreeFromCopy(long paramLong1, long paramLong2);
  
  private static native int nDraw(long paramLong1, long paramLong2, long paramLong3, Rect paramRect, boolean paramBoolean1, boolean paramBoolean2);
  
  @FastNative
  private static native float nGetFillAlpha(long paramLong);
  
  @FastNative
  private static native int nGetFillColor(long paramLong);
  
  private static native boolean nGetFullPathProperties(long paramLong, byte[] paramArrayOfbyte, int paramInt);
  
  private static native boolean nGetGroupProperties(long paramLong, float[] paramArrayOffloat, int paramInt);
  
  @FastNative
  private static native float nGetPivotX(long paramLong);
  
  @FastNative
  private static native float nGetPivotY(long paramLong);
  
  @FastNative
  private static native float nGetRootAlpha(long paramLong);
  
  @FastNative
  private static native float nGetRotation(long paramLong);
  
  @FastNative
  private static native float nGetScaleX(long paramLong);
  
  @FastNative
  private static native float nGetScaleY(long paramLong);
  
  @FastNative
  private static native float nGetStrokeAlpha(long paramLong);
  
  @FastNative
  private static native int nGetStrokeColor(long paramLong);
  
  @FastNative
  private static native float nGetStrokeWidth(long paramLong);
  
  @FastNative
  private static native float nGetTranslateX(long paramLong);
  
  @FastNative
  private static native float nGetTranslateY(long paramLong);
  
  @FastNative
  private static native float nGetTrimPathEnd(long paramLong);
  
  @FastNative
  private static native float nGetTrimPathOffset(long paramLong);
  
  @FastNative
  private static native float nGetTrimPathStart(long paramLong);
  
  @FastNative
  private static native void nSetAllowCaching(long paramLong, boolean paramBoolean);
  
  @FastNative
  private static native void nSetAntiAlias(long paramLong, boolean paramBoolean);
  
  @FastNative
  private static native void nSetFillAlpha(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetFillColor(long paramLong, int paramInt);
  
  private static native void nSetName(long paramLong, String paramString);
  
  @FastNative
  private static native void nSetPathData(long paramLong1, long paramLong2);
  
  private static native void nSetPathString(long paramLong, String paramString, int paramInt);
  
  @FastNative
  private static native void nSetPivotX(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetPivotY(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetRendererViewportSize(long paramLong, float paramFloat1, float paramFloat2);
  
  @FastNative
  private static native boolean nSetRootAlpha(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetRotation(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetScaleX(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetScaleY(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetStrokeAlpha(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetStrokeColor(long paramLong, int paramInt);
  
  @FastNative
  private static native void nSetStrokeWidth(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetTranslateX(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetTranslateY(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetTrimPathEnd(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetTrimPathOffset(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nSetTrimPathStart(long paramLong, float paramFloat);
  
  @FastNative
  private static native void nUpdateFullPathFillGradient(long paramLong1, long paramLong2);
  
  @FastNative
  private static native void nUpdateFullPathProperties(long paramLong, float paramFloat1, int paramInt1, float paramFloat2, int paramInt2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, int paramInt3, int paramInt4, int paramInt5);
  
  @FastNative
  private static native void nUpdateFullPathStrokeGradient(long paramLong1, long paramLong2);
  
  @FastNative
  private static native void nUpdateGroupProperties(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7);
  
  private boolean needMirroring() {
    boolean bool = isAutoMirrored();
    boolean bool1 = true;
    if (!bool || getLayoutDirection() != 1)
      bool1 = false; 
    return bool1;
  }
  
  private void updateColorFilters(BlendMode paramBlendMode, ColorStateList paramColorStateList) {
    PorterDuff.Mode mode = BlendMode.blendModeToPorterDuffMode(paramBlendMode);
    this.mTintFilter = updateTintFilter(this.mTintFilter, paramColorStateList, mode);
    this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, paramColorStateList, paramBlendMode);
  }
  
  private void updateLocalState(Resources paramResources) {
    int i = Drawable.resolveDensity(paramResources, this.mVectorState.mDensity);
    if (this.mTargetDensity != i) {
      this.mTargetDensity = i;
      this.mDpiScaledDirty = true;
    } 
    updateColorFilters(this.mVectorState.mBlendMode, this.mVectorState.mTint);
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) throws XmlPullParserException {
    String str;
    VectorDrawableState vectorDrawableState = this.mVectorState;
    vectorDrawableState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    vectorDrawableState.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    int i = paramTypedArray.getInt(6, -1);
    if (i != -1)
      vectorDrawableState.mBlendMode = Drawable.parseBlendMode(i, BlendMode.SRC_IN); 
    ColorStateList colorStateList = paramTypedArray.getColorStateList(1);
    if (colorStateList != null)
      vectorDrawableState.mTint = colorStateList; 
    vectorDrawableState.mAutoMirrored = paramTypedArray.getBoolean(5, vectorDrawableState.mAutoMirrored);
    vectorDrawableState.setViewportSize(paramTypedArray.getFloat(7, vectorDrawableState.mViewportWidth), paramTypedArray.getFloat(8, vectorDrawableState.mViewportHeight));
    if (vectorDrawableState.mViewportWidth > 0.0F) {
      if (vectorDrawableState.mViewportHeight > 0.0F) {
        vectorDrawableState.mBaseWidth = paramTypedArray.getDimensionPixelSize(3, vectorDrawableState.mBaseWidth);
        vectorDrawableState.mBaseHeight = paramTypedArray.getDimensionPixelSize(2, vectorDrawableState.mBaseHeight);
        if (vectorDrawableState.mBaseWidth > 0) {
          if (vectorDrawableState.mBaseHeight > 0) {
            vectorDrawableState.mOpticalInsets = Insets.of(paramTypedArray.getDimensionPixelOffset(9, vectorDrawableState.mOpticalInsets.left), paramTypedArray.getDimensionPixelOffset(10, vectorDrawableState.mOpticalInsets.top), paramTypedArray.getDimensionPixelOffset(11, vectorDrawableState.mOpticalInsets.right), paramTypedArray.getDimensionPixelOffset(12, vectorDrawableState.mOpticalInsets.bottom));
            vectorDrawableState.setAlpha(paramTypedArray.getFloat(4, vectorDrawableState.getAlpha()));
            str = paramTypedArray.getString(0);
            if (str != null) {
              vectorDrawableState.mRootName = str;
              vectorDrawableState.mVGTargetsMap.put(str, vectorDrawableState);
            } 
            return;
          } 
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str.getPositionDescription());
          stringBuilder3.append("<vector> tag requires height > 0");
          throw new XmlPullParserException(stringBuilder3.toString());
        } 
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str.getPositionDescription());
        stringBuilder2.append("<vector> tag requires width > 0");
        throw new XmlPullParserException(stringBuilder2.toString());
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str.getPositionDescription());
      stringBuilder1.append("<vector> tag requires viewportHeight > 0");
      throw new XmlPullParserException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str.getPositionDescription());
    stringBuilder.append("<vector> tag requires viewportWidth > 0");
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    VectorDrawableState vectorDrawableState1 = this.mVectorState;
    if (vectorDrawableState1 == null)
      return; 
    boolean bool = this.mVectorState.setDensity(Drawable.resolveDensity(paramTheme.getResources(), 0));
    this.mDpiScaledDirty |= bool;
    if (vectorDrawableState1.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(vectorDrawableState1.mThemeAttrs, R.styleable.VectorDrawable);
      try {
        vectorDrawableState1.mCacheDirty = true;
        updateStateFromTypedArray(typedArray);
        typedArray.recycle();
        this.mDpiScaledDirty = true;
      } catch (XmlPullParserException xmlPullParserException) {
        RuntimeException runtimeException = new RuntimeException();
        this((Throwable)xmlPullParserException);
        throw runtimeException;
      } finally {}
    } 
    if (((VectorDrawableState)xmlPullParserException).mTint != null && ((VectorDrawableState)xmlPullParserException).mTint.canApplyTheme())
      ((VectorDrawableState)xmlPullParserException).mTint = ((VectorDrawableState)xmlPullParserException).mTint.obtainForTheme(paramTheme); 
    VectorDrawableState vectorDrawableState2 = this.mVectorState;
    if (vectorDrawableState2 != null && vectorDrawableState2.canApplyTheme())
      this.mVectorState.applyTheme(paramTheme); 
    updateLocalState(paramTheme.getResources());
  }
  
  public boolean canApplyTheme() {
    boolean bool;
    VectorDrawableState vectorDrawableState = this.mVectorState;
    if ((vectorDrawableState != null && vectorDrawableState.canApplyTheme()) || super.canApplyTheme()) {
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
  
  void computeVectorSize() {
    Insets insets = this.mVectorState.mOpticalInsets;
    int i = this.mVectorState.mDensity;
    int j = this.mTargetDensity;
    if (j != i) {
      this.mDpiScaledWidth = Drawable.scaleFromDensity(this.mVectorState.mBaseWidth, i, j, true);
      this.mDpiScaledHeight = Drawable.scaleFromDensity(this.mVectorState.mBaseHeight, i, j, true);
      int k = Drawable.scaleFromDensity(insets.left, i, j, false);
      int m = Drawable.scaleFromDensity(insets.right, i, j, false);
      this.mDpiScaledInsets = Insets.of(k, Drawable.scaleFromDensity(insets.top, i, j, false), m, Drawable.scaleFromDensity(insets.bottom, i, j, false));
    } else {
      this.mDpiScaledWidth = this.mVectorState.mBaseWidth;
      this.mDpiScaledHeight = this.mVectorState.mBaseHeight;
      this.mDpiScaledInsets = insets;
    } 
    this.mDpiScaledDirty = false;
  }
  
  public void draw(Canvas paramCanvas) {
    BlendModeColorFilter blendModeColorFilter;
    long l;
    int j;
    copyBounds(this.mTmpBounds);
    if (this.mTmpBounds.width() <= 0 || this.mTmpBounds.height() <= 0)
      return; 
    ColorFilter colorFilter = this.mColorFilter;
    if (colorFilter == null)
      blendModeColorFilter = this.mBlendModeColorFilter; 
    if (blendModeColorFilter == null) {
      l = 0L;
    } else {
      l = blendModeColorFilter.getNativeInstance();
    } 
    boolean bool = this.mVectorState.canReuseCache();
    int i = nDraw(this.mVectorState.getNativeRenderer(), paramCanvas.getNativeCanvasWrapper(), l, this.mTmpBounds, needMirroring(), bool);
    if (i == 0)
      return; 
    if (paramCanvas.isHardwareAccelerated()) {
      j = (i - this.mVectorState.mLastHWCachePixelCount) * 4;
      this.mVectorState.mLastHWCachePixelCount = i;
    } else {
      j = (i - this.mVectorState.mLastSWCachePixelCount) * 4;
      this.mVectorState.mLastSWCachePixelCount = i;
    } 
    if (j > 0) {
      VMRuntime.getRuntime().registerNativeAllocation(j);
    } else if (j < 0) {
      VMRuntime.getRuntime().registerNativeFree(-j);
    } 
  }
  
  public int getAlpha() {
    return (int)(this.mVectorState.getAlpha() * 255.0F);
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations();
  }
  
  public ColorFilter getColorFilter() {
    return this.mColorFilter;
  }
  
  public Drawable.ConstantState getConstantState() {
    this.mVectorState.mChangingConfigurations = getChangingConfigurations();
    return this.mVectorState;
  }
  
  public int getIntrinsicHeight() {
    if (this.mDpiScaledDirty)
      computeVectorSize(); 
    return this.mDpiScaledHeight;
  }
  
  public int getIntrinsicWidth() {
    if (this.mDpiScaledDirty)
      computeVectorSize(); 
    return this.mDpiScaledWidth;
  }
  
  public long getNativeTree() {
    return this.mVectorState.getNativeRenderer();
  }
  
  public int getOpacity() {
    byte b;
    if (getAlpha() == 0) {
      b = -2;
    } else {
      b = -3;
    } 
    return b;
  }
  
  public Insets getOpticalInsets() {
    if (this.mDpiScaledDirty)
      computeVectorSize(); 
    return this.mDpiScaledInsets;
  }
  
  public float getPixelSize() {
    VectorDrawableState vectorDrawableState = this.mVectorState;
    if (vectorDrawableState == null || vectorDrawableState.mBaseWidth == 0 || this.mVectorState.mBaseHeight == 0 || this.mVectorState.mViewportHeight == 0.0F || this.mVectorState.mViewportWidth == 0.0F)
      return 1.0F; 
    float f1 = this.mVectorState.mBaseWidth;
    float f2 = this.mVectorState.mBaseHeight;
    float f3 = this.mVectorState.mViewportWidth;
    float f4 = this.mVectorState.mViewportHeight;
    return Math.min(f3 / f1, f4 / f2);
  }
  
  Object getTargetByName(String paramString) {
    return this.mVectorState.mVGTargetsMap.get(paramString);
  }
  
  public boolean hasFocusStateSpecified() {
    boolean bool;
    VectorDrawableState vectorDrawableState = this.mVectorState;
    if (vectorDrawableState != null && vectorDrawableState.hasFocusStateSpecified()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    try {
      Trace.traceBegin(8192L, "VectorDrawable#inflate");
      if (this.mVectorState.mRootGroup != null || this.mVectorState.mNativeTree != null) {
        if (this.mVectorState.mRootGroup != null) {
          VMRuntime.getRuntime().registerNativeFree(this.mVectorState.mRootGroup.getNativeSize());
          this.mVectorState.mRootGroup.setTree(null);
        } 
        VectorDrawableState vectorDrawableState1 = this.mVectorState;
        VGroup vGroup = new VGroup();
        this();
        vectorDrawableState1.mRootGroup = vGroup;
        if (this.mVectorState.mNativeTree != null) {
          VMRuntime.getRuntime().registerNativeFree(316);
          this.mVectorState.mNativeTree.release();
        } 
        this.mVectorState.createNativeTree(this.mVectorState.mRootGroup);
      } 
      VectorDrawableState vectorDrawableState = this.mVectorState;
      vectorDrawableState.setDensity(Drawable.resolveDensity(paramResources, 0));
      TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.VectorDrawable);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
      this.mDpiScaledDirty = true;
      vectorDrawableState.mCacheDirty = true;
      inflateChildElements(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      vectorDrawableState.onTreeConstructionFinished();
      updateLocalState(paramResources);
      return;
    } finally {
      Trace.traceEnd(8192L);
    } 
  }
  
  public boolean isAutoMirrored() {
    return this.mVectorState.mAutoMirrored;
  }
  
  public boolean isStateful() {
    if (!super.isStateful()) {
      VectorDrawableState vectorDrawableState = this.mVectorState;
      return (vectorDrawableState != null && vectorDrawableState.isStateful());
    } 
    return true;
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mVectorState = new VectorDrawableState(this.mVectorState);
      this.mMutated = true;
    } 
    return this;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    boolean bool1 = false;
    if (isStateful())
      mutate(); 
    VectorDrawableState vectorDrawableState = this.mVectorState;
    if (vectorDrawableState.onStateChange(paramArrayOfint)) {
      bool1 = true;
      vectorDrawableState.mCacheDirty = true;
    } 
    boolean bool2 = bool1;
    if (vectorDrawableState.mTint != null) {
      bool2 = bool1;
      if (vectorDrawableState.mBlendMode != null) {
        updateColorFilters(vectorDrawableState.mBlendMode, vectorDrawableState.mTint);
        bool2 = true;
      } 
    } 
    return bool2;
  }
  
  void setAllowCaching(boolean paramBoolean) {
    nSetAllowCaching(this.mVectorState.getNativeRenderer(), paramBoolean);
  }
  
  public void setAlpha(int paramInt) {
    if (this.mVectorState.setAlpha(paramInt / 255.0F))
      invalidateSelf(); 
  }
  
  public void setAntiAlias(boolean paramBoolean) {
    nSetAntiAlias(this.mVectorState.mNativeTree.get(), paramBoolean);
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    if (this.mVectorState.mAutoMirrored != paramBoolean) {
      this.mVectorState.mAutoMirrored = paramBoolean;
      invalidateSelf();
    } 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    this.mColorFilter = paramColorFilter;
    invalidateSelf();
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    VectorDrawableState vectorDrawableState = this.mVectorState;
    if (vectorDrawableState.mBlendMode != paramBlendMode) {
      vectorDrawableState.mBlendMode = paramBlendMode;
      updateColorFilters(vectorDrawableState.mBlendMode, vectorDrawableState.mTint);
      invalidateSelf();
    } 
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    VectorDrawableState vectorDrawableState = this.mVectorState;
    if (vectorDrawableState.mTint != paramColorStateList) {
      vectorDrawableState.mTint = paramColorStateList;
      updateColorFilters(this.mVectorState.mBlendMode, paramColorStateList);
      invalidateSelf();
    } 
  }
  
  private static class VClipPath extends VPath {
    private static final int NATIVE_ALLOCATION_SIZE = 120;
    
    private final long mNativePtr = VectorDrawable.nCreateClipPath();
    
    public VClipPath() {}
    
    public VClipPath(VClipPath param1VClipPath) {
      super(param1VClipPath);
    }
    
    private void updateStateFromTypedArray(TypedArray param1TypedArray) {
      this.mChangingConfigurations |= param1TypedArray.getChangingConfigurations();
      String str2 = param1TypedArray.getString(0);
      if (str2 != null) {
        this.mPathName = str2;
        VectorDrawable.nSetName(this.mNativePtr, this.mPathName);
      } 
      String str1 = param1TypedArray.getString(1);
      if (str1 != null) {
        this.mPathData = new PathParser.PathData(str1);
        VectorDrawable.nSetPathString(this.mNativePtr, str1, str1.length());
      } 
    }
    
    public void applyTheme(Resources.Theme param1Theme) {}
    
    public boolean canApplyTheme() {
      return false;
    }
    
    public long getNativePtr() {
      return this.mNativePtr;
    }
    
    int getNativeSize() {
      return 120;
    }
    
    public boolean hasFocusStateSpecified() {
      return false;
    }
    
    public void inflate(Resources param1Resources, AttributeSet param1AttributeSet, Resources.Theme param1Theme) {
      TypedArray typedArray = Drawable.obtainAttributes(param1Resources, param1Theme, param1AttributeSet, R.styleable.VectorDrawableClipPath);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
    }
    
    public boolean isStateful() {
      return false;
    }
    
    public boolean onStateChange(int[] param1ArrayOfint) {
      return false;
    }
  }
  
  static class VFullPath extends VPath {
    private static final Property<VFullPath, Float> FILL_ALPHA;
    
    private static final int FILL_ALPHA_INDEX = 4;
    
    private static final Property<VFullPath, Integer> FILL_COLOR;
    
    private static final int FILL_COLOR_INDEX = 3;
    
    private static final int FILL_TYPE_INDEX = 11;
    
    private static final int NATIVE_ALLOCATION_SIZE = 264;
    
    private static final Property<VFullPath, Float> STROKE_ALPHA;
    
    private static final int STROKE_ALPHA_INDEX = 2;
    
    private static final Property<VFullPath, Integer> STROKE_COLOR;
    
    private static final int STROKE_COLOR_INDEX = 1;
    
    private static final int STROKE_LINE_CAP_INDEX = 8;
    
    private static final int STROKE_LINE_JOIN_INDEX = 9;
    
    private static final int STROKE_MITER_LIMIT_INDEX = 10;
    
    private static final Property<VFullPath, Float> STROKE_WIDTH = (Property)new FloatProperty<VFullPath>("strokeWidth") {
        public Float get(VectorDrawable.VFullPath param2VFullPath) {
          return Float.valueOf(param2VFullPath.getStrokeWidth());
        }
        
        public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
          param2VFullPath.setStrokeWidth(param2Float);
        }
      };
    
    private static final int STROKE_WIDTH_INDEX = 0;
    
    private static final int TOTAL_PROPERTY_COUNT = 12;
    
    private static final Property<VFullPath, Float> TRIM_PATH_END;
    
    private static final int TRIM_PATH_END_INDEX = 6;
    
    private static final Property<VFullPath, Float> TRIM_PATH_OFFSET;
    
    private static final int TRIM_PATH_OFFSET_INDEX = 7;
    
    private static final Property<VFullPath, Float> TRIM_PATH_START;
    
    private static final int TRIM_PATH_START_INDEX = 5;
    
    private static final HashMap<String, Integer> sPropertyIndexMap = new HashMap<String, Integer>() {
      
      };
    
    private static final HashMap<String, Property> sPropertyMap;
    
    ComplexColor mFillColors = null;
    
    private final long mNativePtr = VectorDrawable.nCreateFullPath();
    
    private byte[] mPropertyData;
    
    ComplexColor mStrokeColors = null;
    
    private int[] mThemeAttrs;
    
    static {
      STROKE_COLOR = (Property)new IntProperty<VFullPath>("strokeColor") {
          public Integer get(VectorDrawable.VFullPath param2VFullPath) {
            return Integer.valueOf(param2VFullPath.getStrokeColor());
          }
          
          public void setValue(VectorDrawable.VFullPath param2VFullPath, int param2Int) {
            param2VFullPath.setStrokeColor(param2Int);
          }
        };
      STROKE_ALPHA = (Property)new FloatProperty<VFullPath>("strokeAlpha") {
          public Float get(VectorDrawable.VFullPath param2VFullPath) {
            return Float.valueOf(param2VFullPath.getStrokeAlpha());
          }
          
          public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
            param2VFullPath.setStrokeAlpha(param2Float);
          }
        };
      FILL_COLOR = (Property)new IntProperty<VFullPath>("fillColor") {
          public Integer get(VectorDrawable.VFullPath param2VFullPath) {
            return Integer.valueOf(param2VFullPath.getFillColor());
          }
          
          public void setValue(VectorDrawable.VFullPath param2VFullPath, int param2Int) {
            param2VFullPath.setFillColor(param2Int);
          }
        };
      FILL_ALPHA = (Property)new FloatProperty<VFullPath>("fillAlpha") {
          public Float get(VectorDrawable.VFullPath param2VFullPath) {
            return Float.valueOf(param2VFullPath.getFillAlpha());
          }
          
          public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
            param2VFullPath.setFillAlpha(param2Float);
          }
        };
      TRIM_PATH_START = (Property)new FloatProperty<VFullPath>("trimPathStart") {
          public Float get(VectorDrawable.VFullPath param2VFullPath) {
            return Float.valueOf(param2VFullPath.getTrimPathStart());
          }
          
          public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
            param2VFullPath.setTrimPathStart(param2Float);
          }
        };
      TRIM_PATH_END = (Property)new FloatProperty<VFullPath>("trimPathEnd") {
          public Float get(VectorDrawable.VFullPath param2VFullPath) {
            return Float.valueOf(param2VFullPath.getTrimPathEnd());
          }
          
          public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
            param2VFullPath.setTrimPathEnd(param2Float);
          }
        };
      TRIM_PATH_OFFSET = (Property)new FloatProperty<VFullPath>("trimPathOffset") {
          public Float get(VectorDrawable.VFullPath param2VFullPath) {
            return Float.valueOf(param2VFullPath.getTrimPathOffset());
          }
          
          public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
            param2VFullPath.setTrimPathOffset(param2Float);
          }
        };
      sPropertyMap = new HashMap<String, Property>() {
        
        };
    }
    
    public VFullPath() {}
    
    public VFullPath(VFullPath param1VFullPath) {
      super(param1VFullPath);
      this.mThemeAttrs = param1VFullPath.mThemeAttrs;
      this.mStrokeColors = param1VFullPath.mStrokeColors;
      this.mFillColors = param1VFullPath.mFillColors;
    }
    
    private boolean canComplexColorApplyTheme(ComplexColor param1ComplexColor) {
      boolean bool;
      if (param1ComplexColor != null && param1ComplexColor.canApplyTheme()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private void updateStateFromTypedArray(TypedArray param1TypedArray) {
      if (this.mPropertyData == null)
        this.mPropertyData = new byte[48]; 
      if (VectorDrawable.nGetFullPathProperties(this.mNativePtr, this.mPropertyData, 48)) {
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(this.mPropertyData);
        byteBuffer1.order(ByteOrder.nativeOrder());
        float f1 = byteBuffer1.getFloat(0);
        int i = byteBuffer1.getInt(4);
        float f2 = byteBuffer1.getFloat(8);
        int j = byteBuffer1.getInt(12);
        float f3 = byteBuffer1.getFloat(16);
        float f4 = byteBuffer1.getFloat(20);
        float f5 = byteBuffer1.getFloat(24);
        float f6 = byteBuffer1.getFloat(28);
        int k = byteBuffer1.getInt(32);
        int m = byteBuffer1.getInt(36);
        float f7 = byteBuffer1.getFloat(40);
        int n = byteBuffer1.getInt(44);
        Shader shader = null;
        byteBuffer1 = null;
        ByteBuffer byteBuffer2 = null;
        ByteBuffer byteBuffer3 = null;
        this.mChangingConfigurations |= param1TypedArray.getChangingConfigurations();
        this.mThemeAttrs = param1TypedArray.extractThemeAttrs();
        String str = param1TypedArray.getString(0);
        if (str != null) {
          this.mPathName = str;
          VectorDrawable.nSetName(this.mNativePtr, this.mPathName);
        } 
        str = param1TypedArray.getString(2);
        if (str != null) {
          this.mPathData = new PathParser.PathData(str);
          VectorDrawable.nSetPathString(this.mNativePtr, str, str.length());
        } 
        ComplexColor complexColor = param1TypedArray.getComplexColor(1);
        if (complexColor != null) {
          Shader shader1;
          if (complexColor instanceof GradientColor) {
            this.mFillColors = complexColor;
            shader1 = ((GradientColor)complexColor).getShader();
          } else if (complexColor.isStateful() || complexColor.canApplyTheme()) {
            this.mFillColors = complexColor;
          } else {
            this.mFillColors = null;
          } 
          j = complexColor.getDefaultColor();
          shader = shader1;
        } 
        complexColor = param1TypedArray.getComplexColor(3);
        if (complexColor != null) {
          if (complexColor instanceof GradientColor) {
            this.mStrokeColors = complexColor;
            Shader shader1 = ((GradientColor)complexColor).getShader();
          } else if (complexColor.isStateful() || complexColor.canApplyTheme()) {
            this.mStrokeColors = complexColor;
            byteBuffer1 = byteBuffer3;
          } else {
            this.mStrokeColors = null;
            byteBuffer1 = byteBuffer3;
          } 
          i = complexColor.getDefaultColor();
        } else {
          byteBuffer1 = byteBuffer2;
        } 
        long l1 = this.mNativePtr;
        long l2 = 0L;
        if (shader != null) {
          l3 = shader.getNativeInstance();
        } else {
          l3 = 0L;
        } 
        VectorDrawable.nUpdateFullPathFillGradient(l1, l3);
        l1 = this.mNativePtr;
        long l3 = l2;
        if (byteBuffer1 != null)
          l3 = byteBuffer1.getNativeInstance(); 
        VectorDrawable.nUpdateFullPathStrokeGradient(l1, l3);
        f3 = param1TypedArray.getFloat(12, f3);
        k = param1TypedArray.getInt(8, k);
        m = param1TypedArray.getInt(9, m);
        f7 = param1TypedArray.getFloat(10, f7);
        f2 = param1TypedArray.getFloat(11, f2);
        f1 = param1TypedArray.getFloat(4, f1);
        f5 = param1TypedArray.getFloat(6, f5);
        f6 = param1TypedArray.getFloat(7, f6);
        f4 = param1TypedArray.getFloat(5, f4);
        n = param1TypedArray.getInt(13, n);
        VectorDrawable.nUpdateFullPathProperties(this.mNativePtr, f1, i, f2, j, f3, f4, f5, f6, f7, k, m, n);
        return;
      } 
      throw new RuntimeException("Error: inconsistent property count");
    }
    
    public void applyTheme(Resources.Theme param1Theme) {
      int[] arrayOfInt = this.mThemeAttrs;
      if (arrayOfInt != null) {
        TypedArray typedArray = param1Theme.resolveAttributes(arrayOfInt, R.styleable.VectorDrawablePath);
        updateStateFromTypedArray(typedArray);
        typedArray.recycle();
      } 
      boolean bool1 = canComplexColorApplyTheme(this.mFillColors);
      boolean bool2 = canComplexColorApplyTheme(this.mStrokeColors);
      if (bool1) {
        ComplexColor complexColor = this.mFillColors.obtainForTheme(param1Theme);
        this.mFillColors = complexColor;
        if (complexColor instanceof GradientColor) {
          VectorDrawable.nUpdateFullPathFillGradient(this.mNativePtr, ((GradientColor)complexColor).getShader().getNativeInstance());
        } else if (complexColor instanceof ColorStateList) {
          VectorDrawable.nSetFillColor(this.mNativePtr, complexColor.getDefaultColor());
        } 
      } 
      if (bool2) {
        ComplexColor complexColor = this.mStrokeColors.obtainForTheme(param1Theme);
        this.mStrokeColors = complexColor;
        if (complexColor instanceof GradientColor) {
          VectorDrawable.nUpdateFullPathStrokeGradient(this.mNativePtr, ((GradientColor)complexColor).getShader().getNativeInstance());
        } else if (complexColor instanceof ColorStateList) {
          VectorDrawable.nSetStrokeColor(this.mNativePtr, complexColor.getDefaultColor());
        } 
      } 
    }
    
    public boolean canApplyTheme() {
      if (this.mThemeAttrs != null)
        return true; 
      boolean bool1 = canComplexColorApplyTheme(this.mFillColors);
      boolean bool2 = canComplexColorApplyTheme(this.mStrokeColors);
      return (bool1 || bool2);
    }
    
    float getFillAlpha() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetFillAlpha(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    int getFillColor() {
      boolean bool;
      if (isTreeValid()) {
        bool = VectorDrawable.nGetFillColor(this.mNativePtr);
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public long getNativePtr() {
      return this.mNativePtr;
    }
    
    int getNativeSize() {
      return 264;
    }
    
    Property getProperty(String param1String) {
      Property property = super.getProperty(param1String);
      return (property != null) ? property : (sPropertyMap.containsKey(param1String) ? sPropertyMap.get(param1String) : null);
    }
    
    int getPropertyIndex(String param1String) {
      return !sPropertyIndexMap.containsKey(param1String) ? -1 : ((Integer)sPropertyIndexMap.get(param1String)).intValue();
    }
    
    float getStrokeAlpha() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetStrokeAlpha(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    int getStrokeColor() {
      boolean bool;
      if (isTreeValid()) {
        bool = VectorDrawable.nGetStrokeColor(this.mNativePtr);
      } else {
        bool = false;
      } 
      return bool;
    }
    
    float getStrokeWidth() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetStrokeWidth(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    float getTrimPathEnd() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetTrimPathEnd(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    float getTrimPathOffset() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetTrimPathOffset(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    float getTrimPathStart() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetTrimPathStart(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    public boolean hasFocusStateSpecified() {
      ComplexColor complexColor = this.mStrokeColors;
      if (complexColor != null && complexColor instanceof ColorStateList && ((ColorStateList)complexColor).hasFocusStateSpecified()) {
        complexColor = this.mFillColors;
        if (complexColor != null && complexColor instanceof ColorStateList && ((ColorStateList)complexColor).hasFocusStateSpecified())
          return true; 
      } 
      return false;
    }
    
    public void inflate(Resources param1Resources, AttributeSet param1AttributeSet, Resources.Theme param1Theme) {
      TypedArray typedArray = Drawable.obtainAttributes(param1Resources, param1Theme, param1AttributeSet, R.styleable.VectorDrawablePath);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
    }
    
    public boolean isStateful() {
      return (this.mStrokeColors != null || this.mFillColors != null);
    }
    
    public boolean onStateChange(int[] param1ArrayOfint) {
      int i = 0;
      ComplexColor complexColor = this.mStrokeColors;
      boolean bool = true;
      int j = i;
      if (complexColor != null) {
        j = i;
        if (complexColor instanceof ColorStateList) {
          boolean bool1;
          int k = getStrokeColor();
          int m = ((ColorStateList)this.mStrokeColors).getColorForState(param1ArrayOfint, k);
          if (k != m) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          i = false | bool1;
          j = i;
          if (k != m) {
            VectorDrawable.nSetStrokeColor(this.mNativePtr, m);
            j = i;
          } 
        } 
      } 
      complexColor = this.mFillColors;
      i = j;
      if (complexColor != null) {
        i = j;
        if (complexColor instanceof ColorStateList) {
          byte b;
          int k = getFillColor();
          int m = ((ColorStateList)this.mFillColors).getColorForState(param1ArrayOfint, k);
          if (k != m) {
            b = bool;
          } else {
            b = 0;
          } 
          j |= b;
          i = j;
          if (k != m) {
            VectorDrawable.nSetFillColor(this.mNativePtr, m);
            i = j;
          } 
        } 
      } 
      return i;
    }
    
    void setFillAlpha(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetFillAlpha(this.mNativePtr, param1Float); 
    }
    
    void setFillColor(int param1Int) {
      this.mFillColors = null;
      if (isTreeValid())
        VectorDrawable.nSetFillColor(this.mNativePtr, param1Int); 
    }
    
    void setStrokeAlpha(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetStrokeAlpha(this.mNativePtr, param1Float); 
    }
    
    void setStrokeColor(int param1Int) {
      this.mStrokeColors = null;
      if (isTreeValid())
        VectorDrawable.nSetStrokeColor(this.mNativePtr, param1Int); 
    }
    
    void setStrokeWidth(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetStrokeWidth(this.mNativePtr, param1Float); 
    }
    
    void setTrimPathEnd(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetTrimPathEnd(this.mNativePtr, param1Float); 
    }
    
    void setTrimPathOffset(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetTrimPathOffset(this.mNativePtr, param1Float); 
    }
    
    void setTrimPathStart(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetTrimPathStart(this.mNativePtr, param1Float); 
    }
  }
  
  class null extends HashMap<String, Integer> {
    null() {
      put("strokeWidth", Integer.valueOf(0));
      put("strokeColor", Integer.valueOf(1));
      put("strokeAlpha", Integer.valueOf(2));
      put("fillColor", Integer.valueOf(3));
      put("fillAlpha", Integer.valueOf(4));
      put("trimPathStart", Integer.valueOf(5));
      put("trimPathEnd", Integer.valueOf(6));
      put("trimPathOffset", Integer.valueOf(7));
    }
  }
  
  class null extends HashMap<String, Property> {
    null() {
      put("strokeWidth", VectorDrawable.VFullPath.STROKE_WIDTH);
      put("strokeColor", VectorDrawable.VFullPath.STROKE_COLOR);
      put("strokeAlpha", VectorDrawable.VFullPath.STROKE_ALPHA);
      put("fillColor", VectorDrawable.VFullPath.FILL_COLOR);
      put("fillAlpha", VectorDrawable.VFullPath.FILL_ALPHA);
      put("trimPathStart", VectorDrawable.VFullPath.TRIM_PATH_START);
      put("trimPathEnd", VectorDrawable.VFullPath.TRIM_PATH_END);
      put("trimPathOffset", VectorDrawable.VFullPath.TRIM_PATH_OFFSET);
    }
  }
  
  class null extends FloatProperty<VFullPath> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VFullPath param1VFullPath) {
      return Float.valueOf(param1VFullPath.getStrokeWidth());
    }
    
    public void setValue(VectorDrawable.VFullPath param1VFullPath, float param1Float) {
      param1VFullPath.setStrokeWidth(param1Float);
    }
  }
  
  class null extends IntProperty<VFullPath> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Integer get(VectorDrawable.VFullPath param1VFullPath) {
      return Integer.valueOf(param1VFullPath.getStrokeColor());
    }
    
    public void setValue(VectorDrawable.VFullPath param1VFullPath, int param1Int) {
      param1VFullPath.setStrokeColor(param1Int);
    }
  }
  
  class null extends FloatProperty<VFullPath> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VFullPath param1VFullPath) {
      return Float.valueOf(param1VFullPath.getStrokeAlpha());
    }
    
    public void setValue(VectorDrawable.VFullPath param1VFullPath, float param1Float) {
      param1VFullPath.setStrokeAlpha(param1Float);
    }
  }
  
  class null extends IntProperty<VFullPath> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Integer get(VectorDrawable.VFullPath param1VFullPath) {
      return Integer.valueOf(param1VFullPath.getFillColor());
    }
    
    public void setValue(VectorDrawable.VFullPath param1VFullPath, int param1Int) {
      param1VFullPath.setFillColor(param1Int);
    }
  }
  
  class null extends FloatProperty<VFullPath> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VFullPath param1VFullPath) {
      return Float.valueOf(param1VFullPath.getFillAlpha());
    }
    
    public void setValue(VectorDrawable.VFullPath param1VFullPath, float param1Float) {
      param1VFullPath.setFillAlpha(param1Float);
    }
  }
  
  class null extends FloatProperty<VFullPath> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VFullPath param1VFullPath) {
      return Float.valueOf(param1VFullPath.getTrimPathStart());
    }
    
    public void setValue(VectorDrawable.VFullPath param1VFullPath, float param1Float) {
      param1VFullPath.setTrimPathStart(param1Float);
    }
  }
  
  class null extends FloatProperty<VFullPath> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VFullPath param1VFullPath) {
      return Float.valueOf(param1VFullPath.getTrimPathEnd());
    }
    
    public void setValue(VectorDrawable.VFullPath param1VFullPath, float param1Float) {
      param1VFullPath.setTrimPathEnd(param1Float);
    }
  }
  
  class null extends FloatProperty<VFullPath> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VFullPath param1VFullPath) {
      return Float.valueOf(param1VFullPath.getTrimPathOffset());
    }
    
    public void setValue(VectorDrawable.VFullPath param1VFullPath, float param1Float) {
      param1VFullPath.setTrimPathOffset(param1Float);
    }
  }
  
  static class VGroup extends VObject {
    private static final int NATIVE_ALLOCATION_SIZE = 100;
    
    private static final Property<VGroup, Float> PIVOT_X;
    
    private static final int PIVOT_X_INDEX = 1;
    
    private static final Property<VGroup, Float> PIVOT_Y;
    
    private static final int PIVOT_Y_INDEX = 2;
    
    private static final Property<VGroup, Float> ROTATION;
    
    private static final int ROTATION_INDEX = 0;
    
    private static final Property<VGroup, Float> SCALE_X;
    
    private static final int SCALE_X_INDEX = 3;
    
    private static final Property<VGroup, Float> SCALE_Y;
    
    private static final int SCALE_Y_INDEX = 4;
    
    private static final int TRANSFORM_PROPERTY_COUNT = 7;
    
    private static final Property<VGroup, Float> TRANSLATE_X = (Property)new FloatProperty<VGroup>("translateX") {
        public Float get(VectorDrawable.VGroup param2VGroup) {
          return Float.valueOf(param2VGroup.getTranslateX());
        }
        
        public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
          param2VGroup.setTranslateX(param2Float);
        }
      };
    
    private static final int TRANSLATE_X_INDEX = 5;
    
    private static final Property<VGroup, Float> TRANSLATE_Y = (Property)new FloatProperty<VGroup>("translateY") {
        public Float get(VectorDrawable.VGroup param2VGroup) {
          return Float.valueOf(param2VGroup.getTranslateY());
        }
        
        public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
          param2VGroup.setTranslateY(param2Float);
        }
      };
    
    private static final int TRANSLATE_Y_INDEX = 6;
    
    private static final HashMap<String, Integer> sPropertyIndexMap = new HashMap<String, Integer>() {
      
      };
    
    private static final HashMap<String, Property> sPropertyMap;
    
    private int mChangingConfigurations;
    
    private final ArrayList<VectorDrawable.VObject> mChildren = new ArrayList<>();
    
    private String mGroupName = null;
    
    private boolean mIsStateful;
    
    private final long mNativePtr;
    
    private int[] mThemeAttrs;
    
    private float[] mTransform;
    
    static {
      SCALE_X = (Property)new FloatProperty<VGroup>("scaleX") {
          public Float get(VectorDrawable.VGroup param2VGroup) {
            return Float.valueOf(param2VGroup.getScaleX());
          }
          
          public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
            param2VGroup.setScaleX(param2Float);
          }
        };
      SCALE_Y = (Property)new FloatProperty<VGroup>("scaleY") {
          public Float get(VectorDrawable.VGroup param2VGroup) {
            return Float.valueOf(param2VGroup.getScaleY());
          }
          
          public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
            param2VGroup.setScaleY(param2Float);
          }
        };
      PIVOT_X = (Property)new FloatProperty<VGroup>("pivotX") {
          public Float get(VectorDrawable.VGroup param2VGroup) {
            return Float.valueOf(param2VGroup.getPivotX());
          }
          
          public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
            param2VGroup.setPivotX(param2Float);
          }
        };
      PIVOT_Y = (Property)new FloatProperty<VGroup>("pivotY") {
          public Float get(VectorDrawable.VGroup param2VGroup) {
            return Float.valueOf(param2VGroup.getPivotY());
          }
          
          public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
            param2VGroup.setPivotY(param2Float);
          }
        };
      ROTATION = (Property)new FloatProperty<VGroup>("rotation") {
          public Float get(VectorDrawable.VGroup param2VGroup) {
            return Float.valueOf(param2VGroup.getRotation());
          }
          
          public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
            param2VGroup.setRotation(param2Float);
          }
        };
      sPropertyMap = new HashMap<String, Property>() {
        
        };
    }
    
    public VGroup() {
      this.mNativePtr = VectorDrawable.nCreateGroup();
    }
    
    public VGroup(VGroup param1VGroup, ArrayMap<String, Object> param1ArrayMap) {
      this.mIsStateful = param1VGroup.mIsStateful;
      this.mThemeAttrs = param1VGroup.mThemeAttrs;
      String str = param1VGroup.mGroupName;
      this.mGroupName = str;
      this.mChangingConfigurations = param1VGroup.mChangingConfigurations;
      if (str != null)
        param1ArrayMap.put(str, this); 
      this.mNativePtr = VectorDrawable.nCreateGroup(param1VGroup.mNativePtr);
      ArrayList<VectorDrawable.VObject> arrayList = param1VGroup.mChildren;
      for (byte b = 0; b < arrayList.size(); b++) {
        VectorDrawable.VObject vObject = arrayList.get(b);
        if (vObject instanceof VGroup) {
          addChild(new VGroup((VGroup)vObject, param1ArrayMap));
        } else {
          if (vObject instanceof VectorDrawable.VFullPath) {
            vObject = new VectorDrawable.VFullPath((VectorDrawable.VFullPath)vObject);
          } else if (vObject instanceof VectorDrawable.VClipPath) {
            vObject = new VectorDrawable.VClipPath((VectorDrawable.VClipPath)vObject);
          } else {
            throw new IllegalStateException("Unknown object in the tree!");
          } 
          addChild(vObject);
          if (((VectorDrawable.VPath)vObject).mPathName != null)
            param1ArrayMap.put(((VectorDrawable.VPath)vObject).mPathName, vObject); 
        } 
      } 
    }
    
    static int getPropertyIndex(String param1String) {
      return sPropertyIndexMap.containsKey(param1String) ? ((Integer)sPropertyIndexMap.get(param1String)).intValue() : -1;
    }
    
    public void addChild(VectorDrawable.VObject param1VObject) {
      VectorDrawable.nAddChild(this.mNativePtr, param1VObject.getNativePtr());
      this.mChildren.add(param1VObject);
      this.mIsStateful |= param1VObject.isStateful();
    }
    
    public void applyTheme(Resources.Theme param1Theme) {
      int[] arrayOfInt = this.mThemeAttrs;
      if (arrayOfInt != null) {
        TypedArray typedArray = param1Theme.resolveAttributes(arrayOfInt, R.styleable.VectorDrawableGroup);
        updateStateFromTypedArray(typedArray);
        typedArray.recycle();
      } 
      ArrayList<VectorDrawable.VObject> arrayList = this.mChildren;
      byte b = 0;
      int i = arrayList.size();
      while (b < i) {
        VectorDrawable.VObject vObject = arrayList.get(b);
        if (vObject.canApplyTheme()) {
          vObject.applyTheme(param1Theme);
          this.mIsStateful |= vObject.isStateful();
        } 
        b++;
      } 
    }
    
    public boolean canApplyTheme() {
      if (this.mThemeAttrs != null)
        return true; 
      ArrayList<VectorDrawable.VObject> arrayList = this.mChildren;
      byte b = 0;
      int i = arrayList.size();
      while (b < i) {
        if (((VectorDrawable.VObject)arrayList.get(b)).canApplyTheme())
          return true; 
        b++;
      } 
      return false;
    }
    
    public String getGroupName() {
      return this.mGroupName;
    }
    
    public long getNativePtr() {
      return this.mNativePtr;
    }
    
    int getNativeSize() {
      int i = 100;
      for (byte b = 0; b < this.mChildren.size(); b++)
        i += ((VectorDrawable.VObject)this.mChildren.get(b)).getNativeSize(); 
      return i;
    }
    
    public float getPivotX() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetPivotX(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    public float getPivotY() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetPivotY(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    Property getProperty(String param1String) {
      return sPropertyMap.containsKey(param1String) ? sPropertyMap.get(param1String) : null;
    }
    
    public float getRotation() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetRotation(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    public float getScaleX() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetScaleX(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    public float getScaleY() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetScaleY(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    public float getTranslateX() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetTranslateX(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    public float getTranslateY() {
      float f;
      if (isTreeValid()) {
        f = VectorDrawable.nGetTranslateY(this.mNativePtr);
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    public boolean hasFocusStateSpecified() {
      boolean bool = false;
      ArrayList<VectorDrawable.VObject> arrayList = this.mChildren;
      byte b = 0;
      int i = arrayList.size();
      while (b < i) {
        VectorDrawable.VObject vObject = arrayList.get(b);
        boolean bool1 = bool;
        if (vObject.isStateful())
          bool1 = bool | vObject.hasFocusStateSpecified(); 
        b++;
        bool = bool1;
      } 
      return bool;
    }
    
    public void inflate(Resources param1Resources, AttributeSet param1AttributeSet, Resources.Theme param1Theme) {
      TypedArray typedArray = Drawable.obtainAttributes(param1Resources, param1Theme, param1AttributeSet, R.styleable.VectorDrawableGroup);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
    }
    
    public boolean isStateful() {
      return this.mIsStateful;
    }
    
    public boolean onStateChange(int[] param1ArrayOfint) {
      boolean bool = false;
      ArrayList<VectorDrawable.VObject> arrayList = this.mChildren;
      byte b = 0;
      int i = arrayList.size();
      while (b < i) {
        VectorDrawable.VObject vObject = arrayList.get(b);
        boolean bool1 = bool;
        if (vObject.isStateful())
          bool1 = bool | vObject.onStateChange(param1ArrayOfint); 
        b++;
        bool = bool1;
      } 
      return bool;
    }
    
    public void setPivotX(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetPivotX(this.mNativePtr, param1Float); 
    }
    
    public void setPivotY(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetPivotY(this.mNativePtr, param1Float); 
    }
    
    public void setRotation(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetRotation(this.mNativePtr, param1Float); 
    }
    
    public void setScaleX(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetScaleX(this.mNativePtr, param1Float); 
    }
    
    public void setScaleY(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetScaleY(this.mNativePtr, param1Float); 
    }
    
    public void setTranslateX(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetTranslateX(this.mNativePtr, param1Float); 
    }
    
    public void setTranslateY(float param1Float) {
      if (isTreeValid())
        VectorDrawable.nSetTranslateY(this.mNativePtr, param1Float); 
    }
    
    public void setTree(VirtualRefBasePtr param1VirtualRefBasePtr) {
      super.setTree(param1VirtualRefBasePtr);
      for (byte b = 0; b < this.mChildren.size(); b++)
        ((VectorDrawable.VObject)this.mChildren.get(b)).setTree(param1VirtualRefBasePtr); 
    }
    
    void updateStateFromTypedArray(TypedArray param1TypedArray) {
      this.mChangingConfigurations |= param1TypedArray.getChangingConfigurations();
      this.mThemeAttrs = param1TypedArray.extractThemeAttrs();
      if (this.mTransform == null)
        this.mTransform = new float[7]; 
      if (VectorDrawable.nGetGroupProperties(this.mNativePtr, this.mTransform, 7)) {
        float f1 = param1TypedArray.getFloat(5, this.mTransform[0]);
        float f2 = param1TypedArray.getFloat(1, this.mTransform[1]);
        float f3 = param1TypedArray.getFloat(2, this.mTransform[2]);
        float f4 = param1TypedArray.getFloat(3, this.mTransform[3]);
        float f5 = param1TypedArray.getFloat(4, this.mTransform[4]);
        float f6 = param1TypedArray.getFloat(6, this.mTransform[5]);
        float f7 = param1TypedArray.getFloat(7, this.mTransform[6]);
        String str = param1TypedArray.getString(0);
        if (str != null) {
          this.mGroupName = str;
          VectorDrawable.nSetName(this.mNativePtr, str);
        } 
        VectorDrawable.nUpdateGroupProperties(this.mNativePtr, f1, f2, f3, f4, f5, f6, f7);
        return;
      } 
      throw new RuntimeException("Error: inconsistent property count");
    }
  }
  
  class null extends HashMap<String, Integer> {
    null() {
      put("translateX", Integer.valueOf(5));
      put("translateY", Integer.valueOf(6));
      put("scaleX", Integer.valueOf(3));
      put("scaleY", Integer.valueOf(4));
      put("pivotX", Integer.valueOf(1));
      put("pivotY", Integer.valueOf(2));
      put("rotation", Integer.valueOf(0));
    }
  }
  
  class null extends FloatProperty<VGroup> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VGroup param1VGroup) {
      return Float.valueOf(param1VGroup.getTranslateX());
    }
    
    public void setValue(VectorDrawable.VGroup param1VGroup, float param1Float) {
      param1VGroup.setTranslateX(param1Float);
    }
  }
  
  class null extends FloatProperty<VGroup> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VGroup param1VGroup) {
      return Float.valueOf(param1VGroup.getTranslateY());
    }
    
    public void setValue(VectorDrawable.VGroup param1VGroup, float param1Float) {
      param1VGroup.setTranslateY(param1Float);
    }
  }
  
  class null extends FloatProperty<VGroup> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VGroup param1VGroup) {
      return Float.valueOf(param1VGroup.getScaleX());
    }
    
    public void setValue(VectorDrawable.VGroup param1VGroup, float param1Float) {
      param1VGroup.setScaleX(param1Float);
    }
  }
  
  class null extends FloatProperty<VGroup> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VGroup param1VGroup) {
      return Float.valueOf(param1VGroup.getScaleY());
    }
    
    public void setValue(VectorDrawable.VGroup param1VGroup, float param1Float) {
      param1VGroup.setScaleY(param1Float);
    }
  }
  
  class null extends FloatProperty<VGroup> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VGroup param1VGroup) {
      return Float.valueOf(param1VGroup.getPivotX());
    }
    
    public void setValue(VectorDrawable.VGroup param1VGroup, float param1Float) {
      param1VGroup.setPivotX(param1Float);
    }
  }
  
  class null extends FloatProperty<VGroup> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VGroup param1VGroup) {
      return Float.valueOf(param1VGroup.getPivotY());
    }
    
    public void setValue(VectorDrawable.VGroup param1VGroup, float param1Float) {
      param1VGroup.setPivotY(param1Float);
    }
  }
  
  class null extends FloatProperty<VGroup> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VGroup param1VGroup) {
      return Float.valueOf(param1VGroup.getRotation());
    }
    
    public void setValue(VectorDrawable.VGroup param1VGroup, float param1Float) {
      param1VGroup.setRotation(param1Float);
    }
  }
  
  class null extends HashMap<String, Property> {
    null() {
      put("translateX", VectorDrawable.VGroup.TRANSLATE_X);
      put("translateY", VectorDrawable.VGroup.TRANSLATE_Y);
      put("scaleX", VectorDrawable.VGroup.SCALE_X);
      put("scaleY", VectorDrawable.VGroup.SCALE_Y);
      put("pivotX", VectorDrawable.VGroup.PIVOT_X);
      put("pivotY", VectorDrawable.VGroup.PIVOT_Y);
      put("rotation", VectorDrawable.VGroup.ROTATION);
    }
  }
  
  static abstract class VObject {
    VirtualRefBasePtr mTreePtr = null;
    
    abstract void applyTheme(Resources.Theme param1Theme);
    
    abstract boolean canApplyTheme();
    
    abstract long getNativePtr();
    
    abstract int getNativeSize();
    
    abstract Property getProperty(String param1String);
    
    abstract boolean hasFocusStateSpecified();
    
    abstract void inflate(Resources param1Resources, AttributeSet param1AttributeSet, Resources.Theme param1Theme);
    
    abstract boolean isStateful();
    
    boolean isTreeValid() {
      boolean bool;
      VirtualRefBasePtr virtualRefBasePtr = this.mTreePtr;
      if (virtualRefBasePtr != null && virtualRefBasePtr.get() != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    abstract boolean onStateChange(int[] param1ArrayOfint);
    
    void setTree(VirtualRefBasePtr param1VirtualRefBasePtr) {
      this.mTreePtr = param1VirtualRefBasePtr;
    }
  }
  
  static abstract class VPath extends VObject {
    private static final Property<VPath, PathParser.PathData> PATH_DATA = new Property<VPath, PathParser.PathData>(PathParser.PathData.class, "pathData") {
        public PathParser.PathData get(VectorDrawable.VPath param2VPath) {
          return param2VPath.getPathData();
        }
        
        public void set(VectorDrawable.VPath param2VPath, PathParser.PathData param2PathData) {
          param2VPath.setPathData(param2PathData);
        }
      };
    
    int mChangingConfigurations;
    
    protected PathParser.PathData mPathData;
    
    String mPathName;
    
    public VPath() {
      this.mPathData = null;
    }
    
    public VPath(VPath param1VPath) {
      PathParser.PathData pathData2 = null;
      this.mPathData = null;
      this.mPathName = param1VPath.mPathName;
      this.mChangingConfigurations = param1VPath.mChangingConfigurations;
      PathParser.PathData pathData1 = param1VPath.mPathData;
      if (pathData1 == null) {
        pathData1 = pathData2;
      } else {
        pathData1 = new PathParser.PathData(pathData1);
      } 
      this.mPathData = pathData1;
    }
    
    public PathParser.PathData getPathData() {
      return this.mPathData;
    }
    
    public String getPathName() {
      return this.mPathName;
    }
    
    Property getProperty(String param1String) {
      return PATH_DATA.getName().equals(param1String) ? PATH_DATA : null;
    }
    
    public void setPathData(PathParser.PathData param1PathData) {
      this.mPathData.setPathData(param1PathData);
      if (isTreeValid())
        VectorDrawable.nSetPathData(getNativePtr(), this.mPathData.getNativePtr()); 
    }
  }
  
  class null extends Property<VPath, PathParser.PathData> {
    null(VectorDrawable this$0, String param1String) {
      super((Class)this$0, param1String);
    }
    
    public PathParser.PathData get(VectorDrawable.VPath param1VPath) {
      return param1VPath.getPathData();
    }
    
    public void set(VectorDrawable.VPath param1VPath, PathParser.PathData param1PathData) {
      param1VPath.setPathData(param1PathData);
    }
  }
  
  static class VectorDrawableState extends Drawable.ConstantState {
    static final Property<VectorDrawableState, Float> ALPHA = (Property)new FloatProperty<VectorDrawableState>("alpha") {
        public Float get(VectorDrawable.VectorDrawableState param2VectorDrawableState) {
          return Float.valueOf(param2VectorDrawableState.getAlpha());
        }
        
        public void setValue(VectorDrawable.VectorDrawableState param2VectorDrawableState, float param2Float) {
          param2VectorDrawableState.setAlpha(param2Float);
        }
      };
    
    private static final int NATIVE_ALLOCATION_SIZE = 316;
    
    private int mAllocationOfAllNodes = 0;
    
    boolean mAutoMirrored;
    
    int mBaseHeight = 0;
    
    int mBaseWidth = 0;
    
    BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
    
    boolean mCacheDirty;
    
    boolean mCachedAutoMirrored;
    
    BlendMode mCachedBlendMode;
    
    int[] mCachedThemeAttrs;
    
    ColorStateList mCachedTint;
    
    int mChangingConfigurations;
    
    int mDensity = 160;
    
    int mLastHWCachePixelCount = 0;
    
    int mLastSWCachePixelCount = 0;
    
    VirtualRefBasePtr mNativeTree = null;
    
    Insets mOpticalInsets = Insets.NONE;
    
    VectorDrawable.VGroup mRootGroup;
    
    String mRootName = null;
    
    int[] mThemeAttrs;
    
    ColorStateList mTint = null;
    
    final ArrayMap<String, Object> mVGTargetsMap = new ArrayMap();
    
    float mViewportHeight = 0.0F;
    
    float mViewportWidth = 0.0F;
    
    public VectorDrawableState(VectorDrawableState param1VectorDrawableState) {
      if (param1VectorDrawableState != null) {
        this.mThemeAttrs = param1VectorDrawableState.mThemeAttrs;
        this.mChangingConfigurations = param1VectorDrawableState.mChangingConfigurations;
        this.mTint = param1VectorDrawableState.mTint;
        this.mBlendMode = param1VectorDrawableState.mBlendMode;
        this.mAutoMirrored = param1VectorDrawableState.mAutoMirrored;
        VectorDrawable.VGroup vGroup = new VectorDrawable.VGroup(param1VectorDrawableState.mRootGroup, this.mVGTargetsMap);
        this.mRootGroup = vGroup;
        createNativeTreeFromCopy(param1VectorDrawableState, vGroup);
        this.mBaseWidth = param1VectorDrawableState.mBaseWidth;
        this.mBaseHeight = param1VectorDrawableState.mBaseHeight;
        setViewportSize(param1VectorDrawableState.mViewportWidth, param1VectorDrawableState.mViewportHeight);
        this.mOpticalInsets = param1VectorDrawableState.mOpticalInsets;
        this.mRootName = param1VectorDrawableState.mRootName;
        this.mDensity = param1VectorDrawableState.mDensity;
        String str = param1VectorDrawableState.mRootName;
        if (str != null)
          this.mVGTargetsMap.put(str, this); 
      } else {
        VectorDrawable.VGroup vGroup = new VectorDrawable.VGroup();
        this.mRootGroup = vGroup;
        createNativeTree(vGroup);
      } 
      onTreeConstructionFinished();
    }
    
    private void applyDensityScaling(int param1Int1, int param1Int2) {
      this.mBaseWidth = Drawable.scaleFromDensity(this.mBaseWidth, param1Int1, param1Int2, true);
      this.mBaseHeight = Drawable.scaleFromDensity(this.mBaseHeight, param1Int1, param1Int2, true);
      this.mOpticalInsets = Insets.of(Drawable.scaleFromDensity(this.mOpticalInsets.left, param1Int1, param1Int2, false), Drawable.scaleFromDensity(this.mOpticalInsets.top, param1Int1, param1Int2, false), Drawable.scaleFromDensity(this.mOpticalInsets.right, param1Int1, param1Int2, false), Drawable.scaleFromDensity(this.mOpticalInsets.bottom, param1Int1, param1Int2, false));
    }
    
    private void createNativeTree(VectorDrawable.VGroup param1VGroup) {
      this.mNativeTree = new VirtualRefBasePtr(VectorDrawable.nCreateTree(param1VGroup.mNativePtr));
      VMRuntime.getRuntime().registerNativeAllocation(316);
    }
    
    private void createNativeTreeFromCopy(VectorDrawableState param1VectorDrawableState, VectorDrawable.VGroup param1VGroup) {
      this.mNativeTree = new VirtualRefBasePtr(VectorDrawable.nCreateTreeFromCopy(param1VectorDrawableState.mNativeTree.get(), param1VGroup.mNativePtr));
      VMRuntime.getRuntime().registerNativeAllocation(316);
    }
    
    public void applyTheme(Resources.Theme param1Theme) {
      this.mRootGroup.applyTheme(param1Theme);
    }
    
    public boolean canApplyTheme() {
      if (this.mThemeAttrs == null) {
        VectorDrawable.VGroup vGroup = this.mRootGroup;
        if (vGroup == null || !vGroup.canApplyTheme()) {
          ColorStateList colorStateList = this.mTint;
          return ((colorStateList != null && colorStateList.canApplyTheme()) || super.canApplyTheme());
        } 
      } 
      return true;
    }
    
    public boolean canReuseCache() {
      if (!this.mCacheDirty && this.mCachedThemeAttrs == this.mThemeAttrs && this.mCachedTint == this.mTint && this.mCachedBlendMode == this.mBlendMode && this.mCachedAutoMirrored == this.mAutoMirrored)
        return true; 
      updateCacheStates();
      return false;
    }
    
    public void finalize() throws Throwable {
      super.finalize();
      int i = this.mLastHWCachePixelCount;
      int j = this.mLastSWCachePixelCount;
      VMRuntime.getRuntime().registerNativeFree(this.mAllocationOfAllNodes + 316 + i * 4 + j * 4);
    }
    
    public float getAlpha() {
      return VectorDrawable.nGetRootAlpha(this.mNativeTree.get());
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
    
    long getNativeRenderer() {
      VirtualRefBasePtr virtualRefBasePtr = this.mNativeTree;
      return (virtualRefBasePtr == null) ? 0L : virtualRefBasePtr.get();
    }
    
    Property getProperty(String param1String) {
      return ALPHA.getName().equals(param1String) ? ALPHA : null;
    }
    
    public boolean hasFocusStateSpecified() {
      ColorStateList colorStateList = this.mTint;
      if (colorStateList == null || !colorStateList.hasFocusStateSpecified()) {
        VectorDrawable.VGroup vGroup = this.mRootGroup;
        return (vGroup != null && vGroup.hasFocusStateSpecified());
      } 
      return true;
    }
    
    public boolean isStateful() {
      ColorStateList colorStateList = this.mTint;
      if (colorStateList == null || !colorStateList.isStateful()) {
        VectorDrawable.VGroup vGroup = this.mRootGroup;
        return (vGroup != null && vGroup.isStateful());
      } 
      return true;
    }
    
    public Drawable newDrawable() {
      return new VectorDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new VectorDrawable(this, param1Resources);
    }
    
    public boolean onStateChange(int[] param1ArrayOfint) {
      return this.mRootGroup.onStateChange(param1ArrayOfint);
    }
    
    void onTreeConstructionFinished() {
      this.mRootGroup.setTree(this.mNativeTree);
      this.mAllocationOfAllNodes = this.mRootGroup.getNativeSize();
      VMRuntime.getRuntime().registerNativeAllocation(this.mAllocationOfAllNodes);
    }
    
    public boolean setAlpha(float param1Float) {
      return VectorDrawable.nSetRootAlpha(this.mNativeTree.get(), param1Float);
    }
    
    public final boolean setDensity(int param1Int) {
      if (this.mDensity != param1Int) {
        int i = this.mDensity;
        this.mDensity = param1Int;
        applyDensityScaling(i, param1Int);
        return true;
      } 
      return false;
    }
    
    void setViewportSize(float param1Float1, float param1Float2) {
      this.mViewportWidth = param1Float1;
      this.mViewportHeight = param1Float2;
      VectorDrawable.nSetRendererViewportSize(getNativeRenderer(), param1Float1, param1Float2);
    }
    
    public void updateCacheStates() {
      this.mCachedThemeAttrs = this.mThemeAttrs;
      this.mCachedTint = this.mTint;
      this.mCachedBlendMode = this.mBlendMode;
      this.mCachedAutoMirrored = this.mAutoMirrored;
      this.mCacheDirty = false;
    }
  }
  
  class null extends FloatProperty<VectorDrawableState> {
    null(VectorDrawable this$0) {
      super((String)this$0);
    }
    
    public Float get(VectorDrawable.VectorDrawableState param1VectorDrawableState) {
      return Float.valueOf(param1VectorDrawableState.getAlpha());
    }
    
    public void setValue(VectorDrawable.VectorDrawableState param1VectorDrawableState, float param1Float) {
      param1VectorDrawableState.setAlpha(param1Float);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */