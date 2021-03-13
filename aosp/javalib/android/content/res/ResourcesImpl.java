package android.content.res;

import android.animation.Animator;
import android.animation.StateListAnimator;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.PluralRules;
import android.os.Build;
import android.os.LocaleList;
import android.os.Process;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.DisplayAdjustments;
import com.android.internal.util.GrowingArrayUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;
import org.xmlpull.v1.XmlPullParserException;

public class ResourcesImpl {
  private static final boolean DEBUG_CONFIG = false;
  
  private static final boolean DEBUG_LOAD = false;
  
  private static final int ID_OTHER = 16777220;
  
  static final String TAG = "Resources";
  
  static final String TAG_PRELOAD = "Resources.preload";
  
  public static final boolean TRACE_FOR_DETAILED_PRELOAD = SystemProperties.getBoolean("debug.trace_resource_preload", false);
  
  private static final boolean TRACE_FOR_MISS_PRELOAD = false;
  
  private static final boolean TRACE_FOR_PRELOAD = false;
  
  private static final int XML_BLOCK_CACHE_SIZE = 4;
  
  private static int sPreloadTracingNumLoadedDrawables;
  
  private static boolean sPreloaded;
  
  private static final LongSparseArray<Drawable.ConstantState> sPreloadedColorDrawables;
  
  private static final LongSparseArray<ConstantState<ComplexColor>> sPreloadedComplexColors;
  
  private static final LongSparseArray<Drawable.ConstantState>[] sPreloadedDrawables;
  
  private static final Object sSync = new Object();
  
  private final Object mAccessLock = new Object();
  
  private final ConfigurationBoundResourceCache<Animator> mAnimatorCache = new ConfigurationBoundResourceCache<>();
  
  final AssetManager mAssets;
  
  private final int[] mCachedXmlBlockCookies = new int[4];
  
  private final String[] mCachedXmlBlockFiles = new String[4];
  
  private final XmlBlock[] mCachedXmlBlocks = new XmlBlock[4];
  
  private final DrawableCache mColorDrawableCache = new DrawableCache();
  
  private final ConfigurationBoundResourceCache<ComplexColor> mComplexColorCache = new ConfigurationBoundResourceCache<>();
  
  private final Configuration mConfiguration = new Configuration();
  
  private final DisplayAdjustments mDisplayAdjustments;
  
  private final DrawableCache mDrawableCache = new DrawableCache();
  
  private int mLastCachedXmlBlockIndex = -1;
  
  private final ThreadLocal<LookupStack> mLookupStack = ThreadLocal.withInitial((Supplier<? extends LookupStack>)_$$Lambda$ResourcesImpl$h3PTRX185BeQl8SVC2_w9arp5Og.INSTANCE);
  
  private final DisplayMetrics mMetrics = new DisplayMetrics();
  
  private PluralRules mPluralRule;
  
  private long mPreloadTracingPreloadStartTime;
  
  private long mPreloadTracingStartBitmapCount;
  
  private long mPreloadTracingStartBitmapSize;
  
  private boolean mPreloading;
  
  private final ConfigurationBoundResourceCache<StateListAnimator> mStateListAnimatorCache = new ConfigurationBoundResourceCache<>();
  
  private final Configuration mTmpConfig = new Configuration();
  
  static {
    sPreloadedColorDrawables = new LongSparseArray();
    sPreloadedComplexColors = new LongSparseArray();
    LongSparseArray[] arrayOfLongSparseArray = new LongSparseArray[2];
    sPreloadedDrawables = (LongSparseArray<Drawable.ConstantState>[])arrayOfLongSparseArray;
    arrayOfLongSparseArray[0] = new LongSparseArray();
    sPreloadedDrawables[1] = new LongSparseArray();
  }
  
  public ResourcesImpl(AssetManager paramAssetManager, DisplayMetrics paramDisplayMetrics, Configuration paramConfiguration, DisplayAdjustments paramDisplayAdjustments) {
    this.mAssets = paramAssetManager;
    this.mMetrics.setToDefaults();
    this.mDisplayAdjustments = paramDisplayAdjustments;
    this.mConfiguration.setToDefaults();
    updateConfiguration(paramConfiguration, paramDisplayMetrics, paramDisplayAdjustments.getCompatibilityInfo());
  }
  
  private static String adjustLanguageTag(String paramString) {
    String str;
    int i = paramString.indexOf('-');
    if (i == -1) {
      String str1 = "";
      str = paramString;
      paramString = str1;
    } else {
      str = paramString.substring(0, i);
      paramString = paramString.substring(i);
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(Locale.adjustLanguageCode(str));
    stringBuilder.append(paramString);
    return stringBuilder.toString();
  }
  
  private static int attrForQuantityCode(String paramString) {
    byte b;
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 3735208:
        if (paramString.equals("zero")) {
          b = 0;
          break;
        } 
      case 3343967:
        if (paramString.equals("many")) {
          b = 4;
          break;
        } 
      case 115276:
        if (paramString.equals("two")) {
          b = 2;
          break;
        } 
      case 110182:
        if (paramString.equals("one")) {
          b = 1;
          break;
        } 
      case 101272:
        if (paramString.equals("few")) {
          b = 3;
          break;
        } 
    } 
    return (b != 0) ? ((b != 1) ? ((b != 2) ? ((b != 3) ? ((b != 4) ? 16777220 : 16777225) : 16777224) : 16777223) : 16777222) : 16777221;
  }
  
  private void cacheDrawable(TypedValue paramTypedValue, boolean paramBoolean1, DrawableCache paramDrawableCache, Resources.Theme paramTheme, boolean paramBoolean2, long paramLong, Drawable paramDrawable) {
    Drawable.ConstantState constantState = paramDrawable.getConstantState();
    if (constantState == null)
      return; 
    if (this.mPreloading) {
      int i = constantState.getChangingConfigurations();
      if (paramBoolean1) {
        if (verifyPreloadConfig(i, 0, paramTypedValue.resourceId, "drawable"))
          sPreloadedColorDrawables.put(paramLong, constantState); 
      } else if (verifyPreloadConfig(i, 8192, paramTypedValue.resourceId, "drawable")) {
        if ((i & 0x2000) == 0) {
          sPreloadedDrawables[0].put(paramLong, constantState);
          sPreloadedDrawables[1].put(paramLong, constantState);
        } else {
          sPreloadedDrawables[this.mConfiguration.getLayoutDirection()].put(paramLong, constantState);
        } 
      } 
    } else {
      synchronized (this.mAccessLock) {
        paramDrawableCache.put(paramLong, paramTheme, constantState, paramBoolean2);
        return;
      } 
    } 
  }
  
  private Drawable decodeImageDrawable(AssetManager.AssetInputStream paramAssetInputStream, Resources paramResources, TypedValue paramTypedValue) {
    ImageDecoder.AssetInputStreamSource assetInputStreamSource = new ImageDecoder.AssetInputStreamSource(paramAssetInputStream, paramResources, paramTypedValue);
    try {
      return ImageDecoder.decodeDrawable((ImageDecoder.Source)assetInputStreamSource, (ImageDecoder.OnHeaderDecodedListener)_$$Lambda$ResourcesImpl$99dm2ENnzo9b0SIUjUj2Kl3pi90.INSTANCE);
    } catch (IOException iOException) {
      return null;
    } 
  }
  
  static int getAttributeSetSourceResId(AttributeSet paramAttributeSet) {
    return (paramAttributeSet == null || !(paramAttributeSet instanceof XmlBlock.Parser)) ? 0 : ((XmlBlock.Parser)paramAttributeSet).getSourceResId();
  }
  
  private ColorStateList getColorStateListFromInt(TypedValue paramTypedValue, long paramLong) {
    ConstantState<ColorStateList> constantState = (ConstantState)sPreloadedComplexColors.get(paramLong);
    if (constantState != null)
      return constantState.newInstance(); 
    ColorStateList colorStateList = ColorStateList.valueOf(paramTypedValue.data);
    if (this.mPreloading && verifyPreloadConfig(paramTypedValue.changingConfigurations, 0, paramTypedValue.resourceId, "color"))
      sPreloadedComplexColors.put(paramLong, colorStateList.getConstantState()); 
    return colorStateList;
  }
  
  private PluralRules getPluralRule() {
    synchronized (sSync) {
      if (this.mPluralRule == null)
        this.mPluralRule = PluralRules.forLocale(this.mConfiguration.getLocales().get(0)); 
      return this.mPluralRule;
    } 
  }
  
  private Drawable loadColorOrXmlDrawable(Resources paramResources, TypedValue paramTypedValue, int paramInt1, int paramInt2, String paramString) {
    try {
      return (Drawable)new ColorStateListDrawable(loadColorStateList(paramResources, paramTypedValue, paramInt1, null));
    } catch (NotFoundException notFoundException) {
      try {
        return loadXmlDrawable(paramResources, paramTypedValue, paramInt1, paramInt2, paramString);
      } catch (Exception exception) {
        throw notFoundException;
      } 
    } 
  }
  
  private ComplexColor loadComplexColorForCookie(Resources paramResources, TypedValue paramTypedValue, int paramInt, Resources.Theme paramTheme) {
    Resources.NotFoundException notFoundException;
    if (paramTypedValue.string != null) {
      String str = paramTypedValue.string.toString();
      TypedValue typedValue = null;
      Trace.traceBegin(8192L, str);
      if (str.endsWith(".xml"))
        try {
          int i;
          XmlResourceParser xmlResourceParser = loadXmlResourceParser(str, paramInt, paramTypedValue.assetCookie, "ComplexColor");
          AttributeSet attributeSet = Xml.asAttributeSet(xmlResourceParser);
          while (true) {
            i = xmlResourceParser.next();
            if (i != 2 && i != 1)
              continue; 
            break;
          } 
          if (i == 2) {
            ColorStateList colorStateList;
            String str1 = xmlResourceParser.getName();
            if (str1.equals("gradient")) {
              GradientColor gradientColor = GradientColor.createFromXmlInner(paramResources, xmlResourceParser, attributeSet, paramTheme);
            } else {
              paramTypedValue = typedValue;
              if (str1.equals("selector"))
                colorStateList = ColorStateList.createFromXmlInner(paramResources, xmlResourceParser, attributeSet, paramTheme); 
            } 
            xmlResourceParser.close();
            Trace.traceEnd(8192L);
            return colorStateList;
          } 
          XmlPullParserException xmlPullParserException = new XmlPullParserException();
          this("No start tag found");
          throw xmlPullParserException;
        } catch (Exception exception) {
          Trace.traceEnd(8192L);
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("File ");
          stringBuilder2.append(str);
          stringBuilder2.append(" from ComplexColor resource ID #0x");
          stringBuilder2.append(Integer.toHexString(paramInt));
          notFoundException = new Resources.NotFoundException(stringBuilder2.toString());
          notFoundException.initCause(exception);
          throw notFoundException;
        }  
      Trace.traceEnd(8192L);
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("File ");
      stringBuilder1.append(str);
      stringBuilder1.append(" from drawable resource ID #0x");
      stringBuilder1.append(Integer.toHexString(paramInt));
      stringBuilder1.append(": .xml extension required");
      throw new Resources.NotFoundException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Can't convert to ComplexColor: type=0x");
    stringBuilder.append(((TypedValue)notFoundException).type);
    throw new UnsupportedOperationException(stringBuilder.toString());
  }
  
  private ComplexColor loadComplexColorFromName(Resources paramResources, Resources.Theme paramTheme, TypedValue paramTypedValue, int paramInt) {
    long l = paramTypedValue.assetCookie << 32L | paramTypedValue.data;
    ConfigurationBoundResourceCache<ComplexColor> configurationBoundResourceCache = this.mComplexColorCache;
    ComplexColor complexColor1 = configurationBoundResourceCache.getInstance(l, paramResources, paramTheme);
    if (complexColor1 != null)
      return complexColor1; 
    ConstantState<ComplexColor> constantState = (ConstantState)sPreloadedComplexColors.get(l);
    if (constantState != null)
      complexColor1 = constantState.newInstance(paramResources, paramTheme); 
    ComplexColor complexColor2 = complexColor1;
    if (complexColor1 == null)
      complexColor2 = loadComplexColorForCookie(paramResources, paramTypedValue, paramInt, paramTheme); 
    if (complexColor2 != null) {
      complexColor2.setBaseChangingConfigurations(paramTypedValue.changingConfigurations);
      if (this.mPreloading) {
        if (verifyPreloadConfig(complexColor2.getChangingConfigurations(), 0, paramTypedValue.resourceId, "color"))
          sPreloadedComplexColors.put(l, complexColor2.getConstantState()); 
      } else {
        configurationBoundResourceCache.put(l, paramTheme, complexColor2.getConstantState());
      } 
    } 
    return complexColor2;
  }
  
  private Drawable loadDrawableForCookie(Resources paramResources, TypedValue paramTypedValue, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_2
    //   1: getfield string : Ljava/lang/CharSequence;
    //   4: ifnull -> 581
    //   7: aload_2
    //   8: getfield string : Ljava/lang/CharSequence;
    //   11: invokeinterface toString : ()Ljava/lang/String;
    //   16: astore #5
    //   18: getstatic android/content/res/ResourcesImpl.TRACE_FOR_DETAILED_PRELOAD : Z
    //   21: ifeq -> 47
    //   24: invokestatic nanoTime : ()J
    //   27: lstore #6
    //   29: getstatic android/graphics/Bitmap.sPreloadTracingNumInstantiatedBitmaps : I
    //   32: istore #8
    //   34: getstatic android/graphics/Bitmap.sPreloadTracingTotalBitmapsSize : J
    //   37: lstore #9
    //   39: getstatic android/content/res/ResourcesImpl.sPreloadTracingNumLoadedDrawables : I
    //   42: istore #11
    //   44: goto -> 59
    //   47: lconst_0
    //   48: lstore #6
    //   50: iconst_0
    //   51: istore #8
    //   53: lconst_0
    //   54: lstore #9
    //   56: iconst_0
    //   57: istore #11
    //   59: ldc2_w 8192
    //   62: aload #5
    //   64: invokestatic traceBegin : (JLjava/lang/String;)V
    //   67: aload_0
    //   68: getfield mLookupStack : Ljava/lang/ThreadLocal;
    //   71: invokevirtual get : ()Ljava/lang/Object;
    //   74: checkcast android/content/res/ResourcesImpl$LookupStack
    //   77: astore #12
    //   79: aload #12
    //   81: iload_3
    //   82: invokevirtual contains : (I)Z
    //   85: ifne -> 497
    //   88: aload #12
    //   90: iload_3
    //   91: invokevirtual push : (I)V
    //   94: aload #5
    //   96: ldc_w '.xml'
    //   99: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   102: istore #13
    //   104: iload #13
    //   106: ifeq -> 174
    //   109: aload_0
    //   110: iload_3
    //   111: invokevirtual getResourceTypeName : (I)Ljava/lang/String;
    //   114: astore #14
    //   116: aload #14
    //   118: ifnull -> 151
    //   121: aload #14
    //   123: ldc_w 'color'
    //   126: invokevirtual equals : (Ljava/lang/Object;)Z
    //   129: istore #13
    //   131: iload #13
    //   133: ifeq -> 151
    //   136: aload_0
    //   137: aload_1
    //   138: aload_2
    //   139: iload_3
    //   140: iload #4
    //   142: aload #5
    //   144: invokespecial loadColorOrXmlDrawable : (Landroid/content/res/Resources;Landroid/util/TypedValue;IILjava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   147: astore_1
    //   148: goto -> 163
    //   151: aload_0
    //   152: aload_1
    //   153: aload_2
    //   154: iload_3
    //   155: iload #4
    //   157: aload #5
    //   159: invokespecial loadXmlDrawable : (Landroid/content/res/Resources;Landroid/util/TypedValue;IILjava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   162: astore_1
    //   163: goto -> 198
    //   166: astore_1
    //   167: goto -> 490
    //   170: astore_1
    //   171: goto -> 490
    //   174: aload_0
    //   175: aload_0
    //   176: getfield mAssets : Landroid/content/res/AssetManager;
    //   179: aload_2
    //   180: getfield assetCookie : I
    //   183: aload #5
    //   185: iconst_2
    //   186: invokevirtual openNonAsset : (ILjava/lang/String;I)Ljava/io/InputStream;
    //   189: checkcast android/content/res/AssetManager$AssetInputStream
    //   192: aload_1
    //   193: aload_2
    //   194: invokespecial decodeImageDrawable : (Landroid/content/res/AssetManager$AssetInputStream;Landroid/content/res/Resources;Landroid/util/TypedValue;)Landroid/graphics/drawable/Drawable;
    //   197: astore_1
    //   198: aload #12
    //   200: invokevirtual pop : ()V
    //   203: ldc2_w 8192
    //   206: invokestatic traceEnd : (J)V
    //   209: getstatic android/content/res/ResourcesImpl.TRACE_FOR_DETAILED_PRELOAD : Z
    //   212: ifeq -> 479
    //   215: iload_3
    //   216: bipush #24
    //   218: iushr
    //   219: iconst_1
    //   220: if_icmpne -> 476
    //   223: aload_0
    //   224: iload_3
    //   225: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   228: astore #12
    //   230: aload #12
    //   232: ifnull -> 473
    //   235: invokestatic nanoTime : ()J
    //   238: lstore #15
    //   240: getstatic android/graphics/Bitmap.sPreloadTracingNumInstantiatedBitmaps : I
    //   243: istore #17
    //   245: getstatic android/graphics/Bitmap.sPreloadTracingTotalBitmapsSize : J
    //   248: lstore #18
    //   250: getstatic android/content/res/ResourcesImpl.sPreloadTracingNumLoadedDrawables : I
    //   253: istore #20
    //   255: iconst_1
    //   256: istore #4
    //   258: iload #20
    //   260: iconst_1
    //   261: iadd
    //   262: putstatic android/content/res/ResourcesImpl.sPreloadTracingNumLoadedDrawables : I
    //   265: invokestatic myUid : ()I
    //   268: ifne -> 274
    //   271: goto -> 277
    //   274: iconst_0
    //   275: istore #4
    //   277: new java/lang/StringBuilder
    //   280: dup
    //   281: invokespecial <init> : ()V
    //   284: astore #14
    //   286: iload #4
    //   288: ifeq -> 298
    //   291: ldc_w 'Preloaded FW drawable #'
    //   294: astore_2
    //   295: goto -> 302
    //   298: ldc_w 'Loaded non-preloaded FW drawable #'
    //   301: astore_2
    //   302: aload #14
    //   304: aload_2
    //   305: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: pop
    //   309: aload #14
    //   311: iload_3
    //   312: invokestatic toHexString : (I)Ljava/lang/String;
    //   315: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: pop
    //   319: aload #14
    //   321: ldc_w ' '
    //   324: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   327: pop
    //   328: aload #14
    //   330: aload #12
    //   332: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   335: pop
    //   336: aload #14
    //   338: ldc_w ' '
    //   341: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: pop
    //   345: aload #14
    //   347: aload #5
    //   349: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: pop
    //   353: aload #14
    //   355: ldc_w ' '
    //   358: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   361: pop
    //   362: aload #14
    //   364: aload_1
    //   365: invokevirtual getClass : ()Ljava/lang/Class;
    //   368: invokevirtual getCanonicalName : ()Ljava/lang/String;
    //   371: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   374: pop
    //   375: aload #14
    //   377: ldc_w ' #nested_drawables= '
    //   380: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   383: pop
    //   384: aload #14
    //   386: iload #20
    //   388: iload #11
    //   390: isub
    //   391: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   394: pop
    //   395: aload #14
    //   397: ldc_w ' #bitmaps= '
    //   400: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: pop
    //   404: aload #14
    //   406: iload #17
    //   408: iload #8
    //   410: isub
    //   411: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   414: pop
    //   415: aload #14
    //   417: ldc_w ' total_bitmap_size= '
    //   420: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: pop
    //   424: aload #14
    //   426: lload #18
    //   428: lload #9
    //   430: lsub
    //   431: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   434: pop
    //   435: aload #14
    //   437: ldc_w ' in[us] '
    //   440: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   443: pop
    //   444: aload #14
    //   446: lload #15
    //   448: lload #6
    //   450: lsub
    //   451: ldc2_w 1000
    //   454: ldiv
    //   455: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   458: pop
    //   459: ldc 'Resources.preload'
    //   461: aload #14
    //   463: invokevirtual toString : ()Ljava/lang/String;
    //   466: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   469: pop
    //   470: goto -> 479
    //   473: goto -> 479
    //   476: goto -> 479
    //   479: aload_1
    //   480: areturn
    //   481: astore_1
    //   482: goto -> 515
    //   485: astore_1
    //   486: goto -> 490
    //   489: astore_1
    //   490: aload #12
    //   492: invokevirtual pop : ()V
    //   495: aload_1
    //   496: athrow
    //   497: new java/lang/Exception
    //   500: astore_1
    //   501: aload_1
    //   502: ldc_w 'Recursive reference in drawable'
    //   505: invokespecial <init> : (Ljava/lang/String;)V
    //   508: aload_1
    //   509: athrow
    //   510: astore_1
    //   511: goto -> 515
    //   514: astore_1
    //   515: ldc2_w 8192
    //   518: invokestatic traceEnd : (J)V
    //   521: new java/lang/StringBuilder
    //   524: dup
    //   525: invokespecial <init> : ()V
    //   528: astore_2
    //   529: aload_2
    //   530: ldc_w 'File '
    //   533: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   536: pop
    //   537: aload_2
    //   538: aload #5
    //   540: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: pop
    //   544: aload_2
    //   545: ldc_w ' from drawable resource ID #0x'
    //   548: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   551: pop
    //   552: aload_2
    //   553: iload_3
    //   554: invokestatic toHexString : (I)Ljava/lang/String;
    //   557: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: pop
    //   561: new android/content/res/Resources$NotFoundException
    //   564: dup
    //   565: aload_2
    //   566: invokevirtual toString : ()Ljava/lang/String;
    //   569: invokespecial <init> : (Ljava/lang/String;)V
    //   572: astore_2
    //   573: aload_2
    //   574: aload_1
    //   575: invokevirtual initCause : (Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   578: pop
    //   579: aload_2
    //   580: athrow
    //   581: new java/lang/StringBuilder
    //   584: dup
    //   585: invokespecial <init> : ()V
    //   588: astore_1
    //   589: aload_1
    //   590: ldc_w 'Resource "'
    //   593: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   596: pop
    //   597: aload_1
    //   598: aload_0
    //   599: iload_3
    //   600: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   603: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   606: pop
    //   607: aload_1
    //   608: ldc_w '" ('
    //   611: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   614: pop
    //   615: aload_1
    //   616: iload_3
    //   617: invokestatic toHexString : (I)Ljava/lang/String;
    //   620: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: pop
    //   624: aload_1
    //   625: ldc_w ') is not a Drawable (color or path): '
    //   628: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   631: pop
    //   632: aload_1
    //   633: aload_2
    //   634: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   637: pop
    //   638: new android/content/res/Resources$NotFoundException
    //   641: dup
    //   642: aload_1
    //   643: invokevirtual toString : ()Ljava/lang/String;
    //   646: invokespecial <init> : (Ljava/lang/String;)V
    //   649: athrow
    // Exception table:
    //   from	to	target	type
    //   79	94	514	java/lang/Exception
    //   79	94	514	java/lang/StackOverflowError
    //   94	104	489	finally
    //   109	116	170	finally
    //   121	131	170	finally
    //   136	148	166	finally
    //   151	163	166	finally
    //   174	198	485	finally
    //   198	203	481	java/lang/Exception
    //   198	203	481	java/lang/StackOverflowError
    //   490	495	510	java/lang/Exception
    //   490	495	510	java/lang/StackOverflowError
    //   495	497	510	java/lang/Exception
    //   495	497	510	java/lang/StackOverflowError
    //   497	510	510	java/lang/Exception
    //   497	510	510	java/lang/StackOverflowError
  }
  
  private Drawable loadXmlDrawable(Resources paramResources, TypedValue paramTypedValue, int paramInt1, int paramInt2, String paramString) throws IOException, XmlPullParserException {
    XmlResourceParser xmlResourceParser = loadXmlResourceParser(paramString, paramInt1, paramTypedValue.assetCookie, "drawable");
    try {
      return Drawable.createFromXmlForDensity(paramResources, xmlResourceParser, paramInt2, null);
    } finally {
      if (xmlResourceParser != null)
        try {
          xmlResourceParser.close();
        } finally {
          xmlResourceParser = null;
        }  
    } 
  }
  
  private boolean verifyPreloadConfig(int paramInt1, int paramInt2, int paramInt3, String paramString) {
    if ((0xBFFFEFFF & paramInt1 & paramInt2) != 0) {
      String str;
      try {
        str = getResourceName(paramInt3);
      } catch (NotFoundException notFoundException) {
        str = "?";
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Preloaded ");
      stringBuilder.append(paramString);
      stringBuilder.append(" resource #0x");
      stringBuilder.append(Integer.toHexString(paramInt3));
      stringBuilder.append(" (");
      stringBuilder.append(str);
      stringBuilder.append(") that varies with configuration!!");
      Log.w("Resources", stringBuilder.toString());
      return false;
    } 
    return true;
  }
  
  public int calcConfigChanges(Configuration paramConfiguration) {
    if (paramConfiguration == null)
      return -1; 
    this.mTmpConfig.setTo(paramConfiguration);
    int i = paramConfiguration.densityDpi;
    int j = i;
    if (i == 0)
      j = this.mMetrics.noncompatDensityDpi; 
    this.mDisplayAdjustments.getCompatibilityInfo().applyToConfiguration(j, this.mTmpConfig);
    if (this.mTmpConfig.getLocales().isEmpty())
      this.mTmpConfig.setLocales(LocaleList.getDefault()); 
    return this.mConfiguration.updateFrom(this.mTmpConfig);
  }
  
  public void clearAllCaches() {
    synchronized (this.mAccessLock) {
      this.mDrawableCache.clear();
      this.mColorDrawableCache.clear();
      this.mComplexColorCache.clear();
      this.mAnimatorCache.clear();
      this.mStateListAnimatorCache.clear();
      flushLayoutCache();
      return;
    } 
  }
  
  void finishPreloading() {
    if (this.mPreloading) {
      if (TRACE_FOR_DETAILED_PRELOAD) {
        long l1 = SystemClock.uptimeMillis();
        long l2 = this.mPreloadTracingPreloadStartTime;
        long l3 = Bitmap.sPreloadTracingTotalBitmapsSize;
        long l4 = this.mPreloadTracingStartBitmapSize;
        long l5 = Bitmap.sPreloadTracingNumInstantiatedBitmaps;
        long l6 = this.mPreloadTracingStartBitmapCount;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Preload finished, ");
        stringBuilder.append(l5 - l6);
        stringBuilder.append(" bitmaps of ");
        stringBuilder.append(l3 - l4);
        stringBuilder.append(" bytes in ");
        stringBuilder.append(l1 - l2);
        stringBuilder.append(" ms");
        Log.d("Resources.preload", stringBuilder.toString());
      } 
      this.mPreloading = false;
      flushLayoutCache();
    } 
  }
  
  public void flushLayoutCache() {
    synchronized (this.mCachedXmlBlocks) {
      Arrays.fill(this.mCachedXmlBlockCookies, 0);
      Arrays.fill((Object[])this.mCachedXmlBlockFiles, (Object)null);
      XmlBlock[] arrayOfXmlBlock = this.mCachedXmlBlocks;
      for (byte b = 0; b < 4; b++) {
        XmlBlock xmlBlock = arrayOfXmlBlock[b];
        if (xmlBlock != null)
          xmlBlock.close(); 
      } 
      Arrays.fill((Object[])arrayOfXmlBlock, (Object)null);
      return;
    } 
  }
  
  ConfigurationBoundResourceCache<Animator> getAnimatorCache() {
    return this.mAnimatorCache;
  }
  
  public AssetManager getAssets() {
    return this.mAssets;
  }
  
  CompatibilityInfo getCompatibilityInfo() {
    return this.mDisplayAdjustments.getCompatibilityInfo();
  }
  
  Configuration getConfiguration() {
    return this.mConfiguration;
  }
  
  public DisplayAdjustments getDisplayAdjustments() {
    return this.mDisplayAdjustments;
  }
  
  DisplayMetrics getDisplayMetrics() {
    return this.mMetrics;
  }
  
  int getIdentifier(String paramString1, String paramString2, String paramString3) {
    if (paramString1 != null)
      try {
        return Integer.parseInt(paramString1);
      } catch (Exception exception) {
        return this.mAssets.getResourceIdentifier(paramString1, paramString2, paramString3);
      }  
    throw new NullPointerException("name is null");
  }
  
  String getLastResourceResolution() throws Resources.NotFoundException {
    String str = this.mAssets.getLastResourceResolution();
    if (str != null)
      return str; 
    throw new Resources.NotFoundException("Associated AssetManager hasn't resolved a resource");
  }
  
  LongSparseArray<Drawable.ConstantState> getPreloadedDrawables() {
    return sPreloadedDrawables[0];
  }
  
  CharSequence getQuantityText(int paramInt1, int paramInt2) throws Resources.NotFoundException {
    PluralRules pluralRules = getPluralRule();
    CharSequence charSequence = this.mAssets.getResourceBagText(paramInt1, attrForQuantityCode(pluralRules.select(paramInt2)));
    if (charSequence != null)
      return charSequence; 
    charSequence = this.mAssets.getResourceBagText(paramInt1, 16777220);
    if (charSequence != null)
      return charSequence; 
    charSequence = new StringBuilder();
    charSequence.append("Plural resource ID #0x");
    charSequence.append(Integer.toHexString(paramInt1));
    charSequence.append(" quantity=");
    charSequence.append(paramInt2);
    charSequence.append(" item=");
    charSequence.append(pluralRules.select(paramInt2));
    throw new Resources.NotFoundException(charSequence.toString());
  }
  
  String getResourceEntryName(int paramInt) throws Resources.NotFoundException {
    String str = this.mAssets.getResourceEntryName(paramInt);
    if (str != null)
      return str; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unable to find resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  String getResourceName(int paramInt) throws Resources.NotFoundException {
    String str = this.mAssets.getResourceName(paramInt);
    if (str != null)
      return str; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unable to find resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  String getResourcePackageName(int paramInt) throws Resources.NotFoundException {
    String str = this.mAssets.getResourcePackageName(paramInt);
    if (str != null)
      return str; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unable to find resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  String getResourceTypeName(int paramInt) throws Resources.NotFoundException {
    String str = this.mAssets.getResourceTypeName(paramInt);
    if (str != null)
      return str; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unable to find resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  Configuration[] getSizeConfigurations() {
    return this.mAssets.getSizeConfigurations();
  }
  
  ConfigurationBoundResourceCache<StateListAnimator> getStateListAnimatorCache() {
    return this.mStateListAnimatorCache;
  }
  
  void getValue(int paramInt, TypedValue paramTypedValue, boolean paramBoolean) throws Resources.NotFoundException {
    if (this.mAssets.getResourceValue(paramInt, 0, paramTypedValue, paramBoolean))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  void getValue(String paramString, TypedValue paramTypedValue, boolean paramBoolean) throws Resources.NotFoundException {
    int i = getIdentifier(paramString, "string", null);
    if (i != 0) {
      getValue(i, paramTypedValue, paramBoolean);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("String resource name ");
    stringBuilder.append(paramString);
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  void getValueForDensity(int paramInt1, int paramInt2, TypedValue paramTypedValue, boolean paramBoolean) throws Resources.NotFoundException {
    if (this.mAssets.getResourceValue(paramInt1, paramInt2, paramTypedValue, paramBoolean))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt1));
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  ColorStateList loadColorStateList(Resources paramResources, TypedValue paramTypedValue, int paramInt, Resources.Theme paramTheme) throws Resources.NotFoundException {
    long l1 = paramTypedValue.assetCookie;
    long l2 = paramTypedValue.data;
    if (paramTypedValue.type >= 28 && paramTypedValue.type <= 31)
      return getColorStateListFromInt(paramTypedValue, l1 << 32L | l2); 
    ComplexColor complexColor = loadComplexColorFromName(paramResources, paramTheme, paramTypedValue, paramInt);
    if (complexColor != null && complexColor instanceof ColorStateList)
      return (ColorStateList)complexColor; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Can't find ColorStateList from drawable resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  ComplexColor loadComplexColor(Resources paramResources, TypedValue paramTypedValue, int paramInt, Resources.Theme paramTheme) {
    long l1 = paramTypedValue.assetCookie;
    long l2 = paramTypedValue.data;
    if (paramTypedValue.type >= 28 && paramTypedValue.type <= 31)
      return getColorStateListFromInt(paramTypedValue, l1 << 32L | l2); 
    String str = paramTypedValue.string.toString();
    if (str.endsWith(".xml"))
      try {
        return loadComplexColorFromName(paramResources, paramTheme, paramTypedValue, paramInt);
      } catch (Exception exception) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("File ");
        stringBuilder1.append(str);
        stringBuilder1.append(" from complex color resource ID #0x");
        stringBuilder1.append(Integer.toHexString(paramInt));
        Resources.NotFoundException notFoundException = new Resources.NotFoundException(stringBuilder1.toString());
        notFoundException.initCause(exception);
        throw notFoundException;
      }  
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("File ");
    stringBuilder.append(str);
    stringBuilder.append(" from drawable resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    stringBuilder.append(": .xml extension required");
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  Drawable loadDrawable(Resources paramResources, TypedValue paramTypedValue, int paramInt1, int paramInt2, Resources.Theme paramTheme) throws Resources.NotFoundException {
    boolean bool;
    if (paramInt2 == 0 || paramTypedValue.density == this.mMetrics.densityDpi) {
      bool = true;
    } else {
      bool = false;
    } 
    if (paramInt2 > 0 && paramTypedValue.density > 0 && paramTypedValue.density != 65535)
      if (paramTypedValue.density == paramInt2) {
        paramTypedValue.density = this.mMetrics.densityDpi;
      } else {
        paramTypedValue.density = paramTypedValue.density * this.mMetrics.densityDpi / paramInt2;
      }  
    try {
      Drawable drawable1;
      DrawableCache drawableCache;
      long l;
      boolean bool1;
      Drawable.ConstantState constantState;
      Drawable drawable2;
      boolean bool2;
      if (paramTypedValue.type >= 28 && paramTypedValue.type <= 31) {
        drawableCache = this.mColorDrawableCache;
        l = paramTypedValue.data;
        bool1 = true;
      } else {
        drawableCache = this.mDrawableCache;
        long l1 = paramTypedValue.assetCookie;
        l = paramTypedValue.data;
        bool1 = false;
        l = l1 << 32L | l;
      } 
      if (!this.mPreloading && bool) {
        drawable2 = drawableCache.getInstance(l, paramResources, paramTheme);
        if (drawable2 != null) {
          drawable2.setChangingConfigurations(paramTypedValue.changingConfigurations);
          return drawable2;
        } 
      } 
      if (bool1) {
        constantState = (Drawable.ConstantState)sPreloadedColorDrawables.get(l);
      } else {
        constantState = (Drawable.ConstantState)sPreloadedDrawables[this.mConfiguration.getLayoutDirection()].get(l);
      } 
      if (constantState != null) {
        if (TRACE_FOR_DETAILED_PRELOAD && paramInt1 >>> 24 == 1 && Process.myUid() != 0) {
          String str = getResourceName(paramInt1);
          if (str != null) {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("Hit preloaded FW drawable #");
            stringBuilder.append(Integer.toHexString(paramInt1));
            stringBuilder.append(" ");
            stringBuilder.append(str);
            Log.d("Resources.preload", stringBuilder.toString());
          } 
        } 
        drawable2 = constantState.newDrawable(paramResources);
      } else if (bool1) {
        ColorDrawable colorDrawable = new ColorDrawable(paramTypedValue.data);
      } else {
        drawable2 = loadDrawableForCookie(paramResources, paramTypedValue, paramInt1, paramInt2);
      } 
      if (drawable2 instanceof android.graphics.drawable.DrawableContainer) {
        paramInt2 = 1;
      } else {
        paramInt2 = 0;
      } 
      if (drawable2 != null && drawable2.canApplyTheme()) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      Drawable drawable3 = drawable2;
      if (bool2) {
        drawable3 = drawable2;
        if (paramTheme != null) {
          drawable3 = drawable2.mutate();
          drawable3.applyTheme(paramTheme);
          drawable3.clearMutated();
        } 
      } 
      if (drawable3 != null) {
        drawable3.setChangingConfigurations(paramTypedValue.changingConfigurations);
        if (bool) {
          try {
            cacheDrawable(paramTypedValue, bool1, drawableCache, paramTheme, bool2, l, drawable3);
            drawable1 = drawable3;
            if (paramInt2 != 0) {
              Drawable.ConstantState constantState1 = drawable3.getConstantState();
              drawable1 = drawable3;
              if (constantState1 != null)
                drawable1 = constantState1.newDrawable(paramResources); 
            } 
          } catch (Exception exception) {}
        } else {
          drawable1 = drawable3;
        } 
      } else {
        drawable1 = drawable3;
      } 
      return drawable1;
    } catch (Exception exception) {
      String str;
      try {
        str = getResourceName(paramInt1);
      } catch (NotFoundException notFoundException) {
        str = "(missing name)";
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Drawable ");
      stringBuilder.append(str);
      stringBuilder.append(" with resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt1));
      exception = new Resources.NotFoundException(stringBuilder.toString(), exception);
      exception.setStackTrace(new StackTraceElement[0]);
      throw exception;
    } 
  }
  
  public Typeface loadFont(Resources paramResources, TypedValue paramTypedValue, int paramInt) {
    StringBuilder stringBuilder2;
    if (paramTypedValue.string != null) {
      String str = paramTypedValue.string.toString();
      if (!str.startsWith("res/"))
        return null; 
      Typeface typeface = Typeface.findFromCache(this.mAssets, str);
      if (typeface != null)
        return typeface; 
      Trace.traceBegin(8192L, str);
      try {
        XmlResourceParser xmlResourceParser;
        if (str.endsWith("xml")) {
          xmlResourceParser = loadXmlResourceParser(str, paramInt, paramTypedValue.assetCookie, "font");
          FontResourcesParser.FamilyResourceEntry familyResourceEntry = FontResourcesParser.parse(xmlResourceParser, paramResources);
          if (familyResourceEntry == null) {
            Trace.traceEnd(8192L);
            return null;
          } 
          Typeface typeface2 = Typeface.createFromResources(familyResourceEntry, this.mAssets, str);
          Trace.traceEnd(8192L);
          return typeface2;
        } 
        Typeface.Builder builder = new Typeface.Builder();
        this(this.mAssets, str, false, ((TypedValue)xmlResourceParser).assetCookie);
        Typeface typeface1 = builder.build();
        Trace.traceEnd(8192L);
        return typeface1;
      } catch (XmlPullParserException xmlPullParserException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Failed to parse xml resource ");
        stringBuilder.append(str);
        Log.e("Resources", stringBuilder.toString(), (Throwable)xmlPullParserException);
      } catch (IOException iOException) {
        stringBuilder2 = new StringBuilder();
        this();
        stringBuilder2.append("Failed to read xml resource ");
        stringBuilder2.append(str);
        Log.e("Resources", stringBuilder2.toString(), iOException);
      } finally {}
      Trace.traceEnd(8192L);
      return null;
    } 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Resource \"");
    stringBuilder1.append(getResourceName(paramInt));
    stringBuilder1.append("\" (");
    stringBuilder1.append(Integer.toHexString(paramInt));
    stringBuilder1.append(") is not a Font: ");
    stringBuilder1.append(stringBuilder2);
    throw new Resources.NotFoundException(stringBuilder1.toString());
  }
  
  XmlResourceParser loadXmlResourceParser(String paramString1, int paramInt1, int paramInt2, String paramString2) throws Resources.NotFoundException {
    // Byte code:
    //   0: iload_2
    //   1: ifeq -> 280
    //   4: aload_0
    //   5: getfield mCachedXmlBlocks : [Landroid/content/res/XmlBlock;
    //   8: astore #5
    //   10: aload #5
    //   12: monitorenter
    //   13: aload_0
    //   14: getfield mCachedXmlBlockCookies : [I
    //   17: astore #6
    //   19: aload_0
    //   20: getfield mCachedXmlBlockFiles : [Ljava/lang/String;
    //   23: astore #7
    //   25: aload_0
    //   26: getfield mCachedXmlBlocks : [Landroid/content/res/XmlBlock;
    //   29: astore #8
    //   31: aload #7
    //   33: arraylength
    //   34: istore #9
    //   36: iconst_0
    //   37: istore #10
    //   39: iload #10
    //   41: iload #9
    //   43: if_icmpge -> 98
    //   46: aload #6
    //   48: iload #10
    //   50: iaload
    //   51: iload_3
    //   52: if_icmpne -> 92
    //   55: aload #7
    //   57: iload #10
    //   59: aaload
    //   60: ifnull -> 92
    //   63: aload #7
    //   65: iload #10
    //   67: aaload
    //   68: aload_1
    //   69: invokevirtual equals : (Ljava/lang/Object;)Z
    //   72: ifeq -> 92
    //   75: aload #8
    //   77: iload #10
    //   79: aaload
    //   80: iload_2
    //   81: invokevirtual newParser : (I)Landroid/content/res/XmlResourceParser;
    //   84: astore #6
    //   86: aload #5
    //   88: monitorexit
    //   89: aload #6
    //   91: areturn
    //   92: iinc #10, 1
    //   95: goto -> 39
    //   98: aload_0
    //   99: getfield mAssets : Landroid/content/res/AssetManager;
    //   102: iload_3
    //   103: aload_1
    //   104: invokevirtual openXmlBlockAsset : (ILjava/lang/String;)Landroid/content/res/XmlBlock;
    //   107: astore #11
    //   109: aload #11
    //   111: ifnull -> 181
    //   114: aload_0
    //   115: getfield mLastCachedXmlBlockIndex : I
    //   118: iconst_1
    //   119: iadd
    //   120: iload #9
    //   122: irem
    //   123: istore #10
    //   125: aload_0
    //   126: iload #10
    //   128: putfield mLastCachedXmlBlockIndex : I
    //   131: aload #8
    //   133: iload #10
    //   135: aaload
    //   136: astore #12
    //   138: aload #12
    //   140: ifnull -> 148
    //   143: aload #12
    //   145: invokevirtual close : ()V
    //   148: aload #6
    //   150: iload #10
    //   152: iload_3
    //   153: iastore
    //   154: aload #7
    //   156: iload #10
    //   158: aload_1
    //   159: aastore
    //   160: aload #8
    //   162: iload #10
    //   164: aload #11
    //   166: aastore
    //   167: aload #11
    //   169: iload_2
    //   170: invokevirtual newParser : (I)Landroid/content/res/XmlResourceParser;
    //   173: astore #6
    //   175: aload #5
    //   177: monitorexit
    //   178: aload #6
    //   180: areturn
    //   181: aload #5
    //   183: monitorexit
    //   184: goto -> 280
    //   187: astore #6
    //   189: aload #5
    //   191: monitorexit
    //   192: aload #6
    //   194: athrow
    //   195: astore #5
    //   197: new java/lang/StringBuilder
    //   200: dup
    //   201: invokespecial <init> : ()V
    //   204: astore #6
    //   206: aload #6
    //   208: ldc_w 'File '
    //   211: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: pop
    //   215: aload #6
    //   217: aload_1
    //   218: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: pop
    //   222: aload #6
    //   224: ldc_w ' from xml type '
    //   227: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: pop
    //   231: aload #6
    //   233: aload #4
    //   235: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: pop
    //   239: aload #6
    //   241: ldc_w ' resource ID #0x'
    //   244: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: pop
    //   248: aload #6
    //   250: iload_2
    //   251: invokestatic toHexString : (I)Ljava/lang/String;
    //   254: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: pop
    //   258: new android/content/res/Resources$NotFoundException
    //   261: dup
    //   262: aload #6
    //   264: invokevirtual toString : ()Ljava/lang/String;
    //   267: invokespecial <init> : (Ljava/lang/String;)V
    //   270: astore_1
    //   271: aload_1
    //   272: aload #5
    //   274: invokevirtual initCause : (Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   277: pop
    //   278: aload_1
    //   279: athrow
    //   280: new java/lang/StringBuilder
    //   283: dup
    //   284: invokespecial <init> : ()V
    //   287: astore #5
    //   289: aload #5
    //   291: ldc_w 'File '
    //   294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: pop
    //   298: aload #5
    //   300: aload_1
    //   301: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: pop
    //   305: aload #5
    //   307: ldc_w ' from xml type '
    //   310: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: pop
    //   314: aload #5
    //   316: aload #4
    //   318: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: pop
    //   322: aload #5
    //   324: ldc_w ' resource ID #0x'
    //   327: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: pop
    //   331: aload #5
    //   333: iload_2
    //   334: invokestatic toHexString : (I)Ljava/lang/String;
    //   337: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: pop
    //   341: new android/content/res/Resources$NotFoundException
    //   344: dup
    //   345: aload #5
    //   347: invokevirtual toString : ()Ljava/lang/String;
    //   350: invokespecial <init> : (Ljava/lang/String;)V
    //   353: athrow
    // Exception table:
    //   from	to	target	type
    //   4	13	195	java/lang/Exception
    //   13	36	187	finally
    //   63	89	187	finally
    //   98	109	187	finally
    //   114	131	187	finally
    //   143	148	187	finally
    //   167	178	187	finally
    //   181	184	187	finally
    //   189	192	187	finally
    //   192	195	195	java/lang/Exception
  }
  
  ThemeImpl newThemeImpl() {
    return new ThemeImpl();
  }
  
  ThemeImpl newThemeImpl(Resources.ThemeKey paramThemeKey) {
    ThemeImpl themeImpl = new ThemeImpl();
    themeImpl.mKey.setTo(paramThemeKey);
    themeImpl.rebase();
    return themeImpl;
  }
  
  InputStream openRawResource(int paramInt, TypedValue paramTypedValue) throws Resources.NotFoundException {
    getValue(paramInt, paramTypedValue, true);
    try {
      return this.mAssets.openNonAsset(paramTypedValue.assetCookie, paramTypedValue.string.toString(), 2);
    } catch (Exception exception) {
      String str;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("File ");
      if (paramTypedValue.string == null) {
        str = "(null)";
      } else {
        str = ((TypedValue)str).string.toString();
      } 
      stringBuilder.append(str);
      stringBuilder.append(" from resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      Resources.NotFoundException notFoundException = new Resources.NotFoundException(stringBuilder.toString());
      notFoundException.initCause(exception);
      throw notFoundException;
    } 
  }
  
  AssetFileDescriptor openRawResourceFd(int paramInt, TypedValue paramTypedValue) throws Resources.NotFoundException {
    getValue(paramInt, paramTypedValue, true);
    try {
      return this.mAssets.openNonAssetFd(paramTypedValue.assetCookie, paramTypedValue.string.toString());
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("File ");
      stringBuilder.append(paramTypedValue.string.toString());
      stringBuilder.append(" from resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      throw new Resources.NotFoundException(stringBuilder.toString(), exception);
    } 
  }
  
  public final void startPreloading() {
    synchronized (sSync) {
      if (!sPreloaded) {
        sPreloaded = true;
        this.mPreloading = true;
        this.mConfiguration.densityDpi = DisplayMetrics.DENSITY_DEVICE;
        updateConfiguration(null, null, null);
        if (TRACE_FOR_DETAILED_PRELOAD) {
          this.mPreloadTracingPreloadStartTime = SystemClock.uptimeMillis();
          this.mPreloadTracingStartBitmapSize = Bitmap.sPreloadTracingTotalBitmapsSize;
          this.mPreloadTracingStartBitmapCount = Bitmap.sPreloadTracingNumInstantiatedBitmaps;
          Log.d("Resources.preload", "Preload starting");
        } 
        return;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Resources already preloaded");
      throw illegalStateException;
    } 
  }
  
  public void updateConfiguration(Configuration paramConfiguration, DisplayMetrics paramDisplayMetrics, CompatibilityInfo paramCompatibilityInfo) {
    Trace.traceBegin(8192L, "ResourcesImpl#updateConfiguration");
    try {
      float f2;
      int j;
      int k;
      int m;
      Object object = this.mAccessLock;
      /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      if (paramCompatibilityInfo != null)
        try {
          this.mDisplayAdjustments.setCompatibilityInfo(paramCompatibilityInfo);
        } finally {} 
      if (paramDisplayMetrics != null)
        this.mMetrics.setTo(paramDisplayMetrics); 
      this.mDisplayAdjustments.getCompatibilityInfo().applyToDisplayMetrics(this.mMetrics);
      int i = calcConfigChanges(paramConfiguration);
      LocaleList localeList = this.mConfiguration.getLocales();
      null = localeList;
      if (localeList.isEmpty()) {
        null = LocaleList.getDefault();
        this.mConfiguration.setLocales(null);
      } 
      if ((i & 0x4) != 0 && null.size() > 1) {
        String[] arrayOfString2 = this.mAssets.getNonSystemLocales();
        String[] arrayOfString1 = arrayOfString2;
        if (LocaleList.isPseudoLocalesOnly(arrayOfString2)) {
          arrayOfString2 = this.mAssets.getLocales();
          arrayOfString1 = arrayOfString2;
          if (LocaleList.isPseudoLocalesOnly(arrayOfString2))
            arrayOfString1 = null; 
        } 
        if (arrayOfString1 != null) {
          Locale locale = null.getFirstMatchWithEnglishSupported(arrayOfString1);
          if (locale != null && locale != null.get(0)) {
            Configuration configuration = this.mConfiguration;
            LocaleList localeList1 = new LocaleList();
            this(locale, null);
            configuration.setLocales(localeList1);
          } 
        } 
      } 
      if (this.mConfiguration.densityDpi != 0) {
        this.mMetrics.densityDpi = this.mConfiguration.densityDpi;
        this.mMetrics.density = this.mConfiguration.densityDpi * 0.00625F;
      } 
      DisplayMetrics displayMetrics = this.mMetrics;
      float f1 = this.mMetrics.density;
      if (this.mConfiguration.fontScale != 0.0F) {
        f2 = this.mConfiguration.fontScale;
      } else {
        f2 = 1.0F;
      } 
      displayMetrics.scaledDensity = f1 * f2;
      if (this.mMetrics.widthPixels >= this.mMetrics.heightPixels) {
        j = this.mMetrics.widthPixels;
        k = this.mMetrics.heightPixels;
      } else {
        j = this.mMetrics.heightPixels;
        k = this.mMetrics.widthPixels;
      } 
      if (this.mConfiguration.keyboardHidden == 1 && this.mConfiguration.hardKeyboardHidden == 2) {
        m = 3;
      } else {
        m = this.mConfiguration.keyboardHidden;
      } 
      this.mAssets.setConfiguration(this.mConfiguration.mcc, this.mConfiguration.mnc, adjustLanguageTag(this.mConfiguration.getLocales().get(0).toLanguageTag()), this.mConfiguration.orientation, this.mConfiguration.touchscreen, this.mConfiguration.densityDpi, this.mConfiguration.keyboard, m, this.mConfiguration.navigation, j, k, this.mConfiguration.smallestScreenWidthDp, this.mConfiguration.screenWidthDp, this.mConfiguration.screenHeightDp, this.mConfiguration.screenLayout, this.mConfiguration.uiMode, this.mConfiguration.colorMode, Build.VERSION.RESOURCES_SDK_INT);
      this.mDrawableCache.onConfigurationChange(i);
      this.mColorDrawableCache.onConfigurationChange(i);
      this.mComplexColorCache.onConfigurationChange(i);
      this.mAnimatorCache.onConfigurationChange(i);
      this.mStateListAnimatorCache.onConfigurationChange(i);
      flushLayoutCache();
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    } finally {
      Trace.traceEnd(8192L);
    } 
  }
  
  private static class LookupStack {
    private int[] mIds = new int[4];
    
    private int mSize = 0;
    
    private LookupStack() {}
    
    public boolean contains(int param1Int) {
      for (byte b = 0; b < this.mSize; b++) {
        if (this.mIds[b] == param1Int)
          return true; 
      } 
      return false;
    }
    
    public void pop() {
      this.mSize--;
    }
    
    public void push(int param1Int) {
      this.mIds = GrowingArrayUtils.append(this.mIds, this.mSize, param1Int);
      this.mSize++;
    }
  }
  
  public class ThemeImpl {
    private final AssetManager mAssets;
    
    private final Resources.ThemeKey mKey = new Resources.ThemeKey();
    
    private final long mTheme;
    
    private int mThemeResId = 0;
    
    ThemeImpl() {
      AssetManager assetManager = ResourcesImpl.this.mAssets;
      this.mAssets = assetManager;
      this.mTheme = assetManager.createTheme();
    }
    
    void applyStyle(int param1Int, boolean param1Boolean) {
      synchronized (this.mKey) {
        this.mAssets.applyStyleToTheme(this.mTheme, param1Int, param1Boolean);
        this.mThemeResId = param1Int;
        this.mKey.append(param1Int, param1Boolean);
        return;
      } 
    }
    
    public void dump(int param1Int, String param1String1, String param1String2) {
      synchronized (this.mKey) {
        this.mAssets.dumpTheme(this.mTheme, param1Int, param1String1, param1String2);
        return;
      } 
    }
    
    protected void finalize() throws Throwable {
      super.finalize();
      this.mAssets.releaseTheme(this.mTheme);
    }
    
    int[] getAllAttributes() {
      return this.mAssets.getStyleAttributes(getAppliedStyleResId());
    }
    
    int getAppliedStyleResId() {
      return this.mThemeResId;
    }
    
    public int[] getAttributeResolutionStack(int param1Int1, int param1Int2, int param1Int3) {
      synchronized (this.mKey) {
        return this.mAssets.getAttributeResolutionStack(this.mTheme, param1Int1, param1Int2, param1Int3);
      } 
    }
    
    int getChangingConfigurations() {
      synchronized (this.mKey) {
        return ActivityInfo.activityInfoConfigNativeToJava(AssetManager.nativeThemeGetChangingConfigurations(this.mTheme));
      } 
    }
    
    Resources.ThemeKey getKey() {
      return this.mKey;
    }
    
    long getNativeTheme() {
      return this.mTheme;
    }
    
    String[] getTheme() {
      synchronized (this.mKey) {
        int i = this.mKey.mCount;
        String[] arrayOfString = new String[i * 2];
        byte b = 0;
        i--;
        while (b < arrayOfString.length) {
          String str;
          int j = this.mKey.mResId[i];
          boolean bool = this.mKey.mForce[i];
          try {
            arrayOfString[b] = ResourcesImpl.this.getResourceName(j);
          } catch (NotFoundException notFoundException) {
            arrayOfString[b] = Integer.toHexString(b);
          } 
          if (bool) {
            str = "forced";
          } else {
            str = "not forced";
          } 
          arrayOfString[b + 1] = str;
          b += 2;
          i--;
        } 
        return arrayOfString;
      } 
    }
    
    TypedArray obtainStyledAttributes(Resources.Theme param1Theme, AttributeSet param1AttributeSet, int[] param1ArrayOfint, int param1Int1, int param1Int2) {
      Resources.ThemeKey themeKey = this.mKey;
      /* monitor enter ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{android/content/res/Resources}.Landroid/content/res/Resources$ThemeKey;}, name=null} */
      try {
        int i = param1ArrayOfint.length;
        TypedArray typedArray = TypedArray.obtain(param1Theme.getResources(), i);
        param1AttributeSet = param1AttributeSet;
        this.mAssets.applyStyle(this.mTheme, param1Int1, param1Int2, (XmlBlock.Parser)param1AttributeSet, param1ArrayOfint, typedArray.mDataAddress, typedArray.mIndicesAddress);
        try {
          typedArray.mTheme = param1Theme;
          typedArray.mXml = (XmlBlock.Parser)param1AttributeSet;
          /* monitor exit ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{android/content/res/Resources}.Landroid/content/res/Resources$ThemeKey;}, name=null} */
          return typedArray;
        } finally {}
      } finally {}
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{android/content/res/Resources}.Landroid/content/res/Resources$ThemeKey;}, name=null} */
      throw param1Theme;
    }
    
    void rebase() {
      synchronized (this.mKey) {
        AssetManager.nativeThemeClear(this.mTheme);
        for (byte b = 0; b < this.mKey.mCount; b++) {
          int i = this.mKey.mResId[b];
          boolean bool = this.mKey.mForce[b];
          this.mAssets.applyStyleToTheme(this.mTheme, i, bool);
        } 
        return;
      } 
    }
    
    boolean resolveAttribute(int param1Int, TypedValue param1TypedValue, boolean param1Boolean) {
      synchronized (this.mKey) {
        param1Boolean = this.mAssets.getThemeValue(this.mTheme, param1Int, param1TypedValue, param1Boolean);
        return param1Boolean;
      } 
    }
    
    TypedArray resolveAttributes(Resources.Theme param1Theme, int[] param1ArrayOfint1, int[] param1ArrayOfint2) {
      synchronized (this.mKey) {
        int i = param1ArrayOfint2.length;
        if (param1ArrayOfint1 != null && i == param1ArrayOfint1.length) {
          TypedArray typedArray = TypedArray.obtain(param1Theme.getResources(), i);
          this.mAssets.resolveAttrs(this.mTheme, 0, 0, param1ArrayOfint1, param1ArrayOfint2, typedArray.mData, typedArray.mIndices);
          typedArray.mTheme = param1Theme;
          typedArray.mXml = null;
          return typedArray;
        } 
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        this("Base attribute values must the same length as attrs");
        throw illegalArgumentException;
      } 
    }
    
    void setTo(ThemeImpl param1ThemeImpl) {
      synchronized (this.mKey) {
        synchronized (param1ThemeImpl.mKey) {
          this.mAssets.setThemeTo(this.mTheme, param1ThemeImpl.mAssets, param1ThemeImpl.mTheme);
          this.mThemeResId = param1ThemeImpl.mThemeResId;
          this.mKey.setTo(param1ThemeImpl.getKey());
          return;
        } 
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ResourcesImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */