package android.graphics;

import android.content.res.AssetManager;
import android.content.res.FontResourcesParser;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.graphics.fonts.FontVariationAxis;
import android.graphics.fonts.SystemFonts;
import android.os.ParcelFileDescriptor;
import android.text.FontConfig;
import android.util.LongSparseArray;
import android.util.LruCache;
import android.util.SparseArray;
import com.android.internal.util.Preconditions;
import dalvik.annotation.optimization.CriticalNative;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import libcore.util.NativeAllocationRegistry;

public class Typeface {
  public static final int BOLD = 1;
  
  public static final int BOLD_ITALIC = 3;
  
  public static final Typeface DEFAULT;
  
  public static final Typeface DEFAULT_BOLD;
  
  private static final String DEFAULT_FAMILY = "sans-serif";
  
  private static final int[] EMPTY_AXES;
  
  public static final int ITALIC = 2;
  
  public static final Typeface MONOSPACE;
  
  public static final int NORMAL = 0;
  
  public static final int RESOLVE_BY_FONT_TABLE = -1;
  
  public static final Typeface SANS_SERIF;
  
  public static final Typeface SERIF;
  
  private static final int STYLE_ITALIC = 1;
  
  public static final int STYLE_MASK = 3;
  
  private static final int STYLE_NORMAL = 0;
  
  private static String TAG = "Typeface";
  
  static Typeface sDefaultTypeface;
  
  static Typeface[] sDefaults;
  
  private static final Object sDynamicCacheLock;
  
  private static final LruCache<String, Typeface> sDynamicTypefaceCache;
  
  private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(Typeface.class.getClassLoader(), nativeGetReleaseFunc());
  
  private static final Object sStyledCacheLock;
  
  private static final LongSparseArray<SparseArray<Typeface>> sStyledTypefaceCache = new LongSparseArray(3);
  
  @Deprecated
  static final Map<String, FontFamily[]> sSystemFallbackMap;
  
  static final Map<String, Typeface> sSystemFontMap;
  
  private static final Object sWeightCacheLock;
  
  private static final LongSparseArray<SparseArray<Typeface>> sWeightTypefaceCache;
  
  private int mStyle = 0;
  
  private int[] mSupportedAxes;
  
  private int mWeight = 0;
  
  public long native_instance;
  
  static {
    sStyledCacheLock = new Object();
    sWeightTypefaceCache = new LongSparseArray(3);
    sWeightCacheLock = new Object();
    sDynamicTypefaceCache = new LruCache(16);
    sDynamicCacheLock = new Object();
    sSystemFallbackMap = Collections.emptyMap();
    byte b = 0;
    EMPTY_AXES = new int[0];
    HashMap<Object, Object> hashMap = new HashMap<>();
    initSystemDefaultTypefaces((Map)hashMap, SystemFonts.getRawSystemFallbackMap(), SystemFonts.getAliases());
    Map<Object, Object> map = Collections.unmodifiableMap(hashMap);
    sSystemFontMap = (Map)map;
    if (map.containsKey("sans-serif"))
      setDefault(sSystemFontMap.get("sans-serif")); 
    String str = (String)null;
    DEFAULT = create(str, 0);
    DEFAULT_BOLD = create(str, 1);
    SANS_SERIF = create("sans-serif", 0);
    SERIF = create("serif", 0);
    MONOSPACE = create("monospace", 0);
    sDefaults = new Typeface[] { DEFAULT, DEFAULT_BOLD, create(str, 2), create(str, 3) };
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "serif";
    arrayOfString[1] = "sans-serif";
    arrayOfString[2] = "cursive";
    arrayOfString[3] = "fantasy";
    arrayOfString[4] = "monospace";
    arrayOfString[5] = "system-ui";
    int i = arrayOfString.length;
    while (b < i) {
      String str1 = arrayOfString[b];
      registerGenericFamilyNative(str1, (Typeface)hashMap.get(str1));
      b++;
    } 
  }
  
  private Typeface(long paramLong) {
    if (paramLong != 0L) {
      this.native_instance = paramLong;
      sRegistry.registerNativeAllocation(this, paramLong);
      this.mStyle = nativeGetStyle(paramLong);
      this.mWeight = nativeGetWeight(paramLong);
      return;
    } 
    throw new RuntimeException("native typeface cannot be made");
  }
  
  public static Typeface create(Typeface paramTypeface, int paramInt) {
    int i = paramInt;
    if ((paramInt & 0xFFFFFFFC) != 0)
      i = 0; 
    Typeface typeface = paramTypeface;
    if (paramTypeface == null)
      typeface = sDefaultTypeface; 
    if (typeface.mStyle == i)
      return typeface; 
    long l = typeface.native_instance;
    synchronized (sStyledCacheLock) {
      SparseArray sparseArray = (SparseArray)sStyledTypefaceCache.get(l);
      if (sparseArray == null) {
        sparseArray = new SparseArray();
        this(4);
        sStyledTypefaceCache.put(l, sparseArray);
      } else {
        Typeface typeface2 = (Typeface)sparseArray.get(i);
        if (typeface2 != null)
          return typeface2; 
      } 
      Typeface typeface1 = new Typeface();
      this(nativeCreateFromTypeface(l, i));
      sparseArray.put(i, typeface1);
      return typeface1;
    } 
  }
  
  public static Typeface create(Typeface paramTypeface, int paramInt, boolean paramBoolean) {
    Preconditions.checkArgumentInRange(paramInt, 0, 1000, "weight");
    Typeface typeface = paramTypeface;
    if (paramTypeface == null)
      typeface = sDefaultTypeface; 
    return createWeightStyle(typeface, paramInt, paramBoolean);
  }
  
  public static Typeface create(String paramString, int paramInt) {
    return create(getSystemDefaultTypeface(paramString), paramInt);
  }
  
  public static Typeface createFromAsset(AssetManager paramAssetManager, String paramString) {
    Preconditions.checkNotNull(paramString);
    Preconditions.checkNotNull(paramAssetManager);
    Typeface typeface = (new Builder(paramAssetManager, paramString)).build();
    if (typeface != null)
      return typeface; 
    try {
      InputStream inputStream = paramAssetManager.open(paramString);
      if (inputStream != null)
        inputStream.close(); 
      return DEFAULT;
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Font asset not found ");
      stringBuilder.append(paramString);
      throw new RuntimeException(stringBuilder.toString());
    } 
  }
  
  @Deprecated
  private static Typeface createFromFamilies(FontFamily[] paramArrayOfFontFamily) {
    long[] arrayOfLong = new long[paramArrayOfFontFamily.length];
    for (byte b = 0; b < paramArrayOfFontFamily.length; b++)
      arrayOfLong[b] = (paramArrayOfFontFamily[b]).mNativePtr; 
    return new Typeface(nativeCreateFromArray(arrayOfLong, -1, -1));
  }
  
  private static Typeface createFromFamilies(FontFamily[] paramArrayOfFontFamily) {
    long[] arrayOfLong = new long[paramArrayOfFontFamily.length];
    for (byte b = 0; b < paramArrayOfFontFamily.length; b++)
      arrayOfLong[b] = paramArrayOfFontFamily[b].getNativePtr(); 
    return new Typeface(nativeCreateFromArray(arrayOfLong, -1, -1));
  }
  
  @Deprecated
  private static Typeface createFromFamiliesWithDefault(FontFamily[] paramArrayOfFontFamily, int paramInt1, int paramInt2) {
    return createFromFamiliesWithDefault(paramArrayOfFontFamily, "sans-serif", paramInt1, paramInt2);
  }
  
  @Deprecated
  private static Typeface createFromFamiliesWithDefault(FontFamily[] paramArrayOfFontFamily, String paramString, int paramInt1, int paramInt2) {
    FontFamily[] arrayOfFontFamily = SystemFonts.getSystemFallback(paramString);
    long[] arrayOfLong = new long[paramArrayOfFontFamily.length + arrayOfFontFamily.length];
    byte b;
    for (b = 0; b < paramArrayOfFontFamily.length; b++)
      arrayOfLong[b] = (paramArrayOfFontFamily[b]).mNativePtr; 
    for (b = 0; b < arrayOfFontFamily.length; b++)
      arrayOfLong[paramArrayOfFontFamily.length + b] = arrayOfFontFamily[b].getNativePtr(); 
    return new Typeface(nativeCreateFromArray(arrayOfLong, paramInt1, paramInt2));
  }
  
  public static Typeface createFromFile(File paramFile) {
    Typeface typeface = (new Builder(paramFile)).build();
    if (typeface != null)
      return typeface; 
    if (paramFile.exists())
      return DEFAULT; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Font asset not found ");
    stringBuilder.append(paramFile.getAbsolutePath());
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public static Typeface createFromFile(String paramString) {
    Preconditions.checkNotNull(paramString);
    return createFromFile(new File(paramString));
  }
  
  public static Typeface createFromResources(FontResourcesParser.FamilyResourceEntry paramFamilyResourceEntry, AssetManager paramAssetManager, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: instanceof android/content/res/FontResourcesParser$ProviderResourceEntry
    //   4: ifeq -> 159
    //   7: aload_0
    //   8: checkcast android/content/res/FontResourcesParser$ProviderResourceEntry
    //   11: astore_3
    //   12: aload_3
    //   13: invokevirtual getCerts : ()Ljava/util/List;
    //   16: astore_0
    //   17: new java/util/ArrayList
    //   20: dup
    //   21: invokespecial <init> : ()V
    //   24: astore #4
    //   26: aload_0
    //   27: ifnull -> 121
    //   30: iconst_0
    //   31: istore #5
    //   33: iload #5
    //   35: aload_0
    //   36: invokeinterface size : ()I
    //   41: if_icmpge -> 121
    //   44: aload_0
    //   45: iload #5
    //   47: invokeinterface get : (I)Ljava/lang/Object;
    //   52: checkcast java/util/List
    //   55: astore_1
    //   56: new java/util/ArrayList
    //   59: dup
    //   60: invokespecial <init> : ()V
    //   63: astore_2
    //   64: iconst_0
    //   65: istore #6
    //   67: iload #6
    //   69: aload_1
    //   70: invokeinterface size : ()I
    //   75: if_icmpge -> 106
    //   78: aload_2
    //   79: aload_1
    //   80: iload #6
    //   82: invokeinterface get : (I)Ljava/lang/Object;
    //   87: checkcast java/lang/String
    //   90: iconst_0
    //   91: invokestatic decode : (Ljava/lang/String;I)[B
    //   94: invokeinterface add : (Ljava/lang/Object;)Z
    //   99: pop
    //   100: iinc #6, 1
    //   103: goto -> 67
    //   106: aload #4
    //   108: aload_2
    //   109: invokeinterface add : (Ljava/lang/Object;)Z
    //   114: pop
    //   115: iinc #5, 1
    //   118: goto -> 33
    //   121: new android/provider/FontRequest
    //   124: dup
    //   125: aload_3
    //   126: invokevirtual getAuthority : ()Ljava/lang/String;
    //   129: aload_3
    //   130: invokevirtual getPackage : ()Ljava/lang/String;
    //   133: aload_3
    //   134: invokevirtual getQuery : ()Ljava/lang/String;
    //   137: aload #4
    //   139: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V
    //   142: invokestatic getFontSync : (Landroid/provider/FontRequest;)Landroid/graphics/Typeface;
    //   145: astore_0
    //   146: aload_0
    //   147: ifnonnull -> 157
    //   150: getstatic android/graphics/Typeface.DEFAULT : Landroid/graphics/Typeface;
    //   153: astore_0
    //   154: goto -> 157
    //   157: aload_0
    //   158: areturn
    //   159: aload_1
    //   160: aload_2
    //   161: invokestatic findFromCache : (Landroid/content/res/AssetManager;Ljava/lang/String;)Landroid/graphics/Typeface;
    //   164: astore_3
    //   165: aload_3
    //   166: ifnull -> 171
    //   169: aload_3
    //   170: areturn
    //   171: aload_0
    //   172: checkcast android/content/res/FontResourcesParser$FontFamilyFilesResourceEntry
    //   175: astore_3
    //   176: aconst_null
    //   177: astore_0
    //   178: aload_3
    //   179: invokevirtual getEntries : ()[Landroid/content/res/FontResourcesParser$FontFileResourceEntry;
    //   182: astore_3
    //   183: aload_3
    //   184: arraylength
    //   185: istore #7
    //   187: iconst_0
    //   188: istore #5
    //   190: iload #5
    //   192: iload #7
    //   194: if_icmpge -> 336
    //   197: aload_3
    //   198: iload #5
    //   200: aaload
    //   201: astore #4
    //   203: new android/graphics/fonts/Font$Builder
    //   206: astore #8
    //   208: aload #8
    //   210: aload_1
    //   211: aload #4
    //   213: invokevirtual getFileName : ()Ljava/lang/String;
    //   216: iconst_0
    //   217: iconst_0
    //   218: invokespecial <init> : (Landroid/content/res/AssetManager;Ljava/lang/String;ZI)V
    //   221: aload #8
    //   223: aload #4
    //   225: invokevirtual getTtcIndex : ()I
    //   228: invokevirtual setTtcIndex : (I)Landroid/graphics/fonts/Font$Builder;
    //   231: aload #4
    //   233: invokevirtual getVariationSettings : ()Ljava/lang/String;
    //   236: invokevirtual setFontVariationSettings : (Ljava/lang/String;)Landroid/graphics/fonts/Font$Builder;
    //   239: astore #8
    //   241: aload #4
    //   243: invokevirtual getWeight : ()I
    //   246: iconst_m1
    //   247: if_icmpeq -> 261
    //   250: aload #8
    //   252: aload #4
    //   254: invokevirtual getWeight : ()I
    //   257: invokevirtual setWeight : (I)Landroid/graphics/fonts/Font$Builder;
    //   260: pop
    //   261: aload #4
    //   263: invokevirtual getItalic : ()I
    //   266: iconst_m1
    //   267: if_icmpeq -> 300
    //   270: aload #4
    //   272: invokevirtual getItalic : ()I
    //   275: istore #9
    //   277: iconst_1
    //   278: istore #6
    //   280: iload #9
    //   282: iconst_1
    //   283: if_icmpne -> 289
    //   286: goto -> 292
    //   289: iconst_0
    //   290: istore #6
    //   292: aload #8
    //   294: iload #6
    //   296: invokevirtual setSlant : (I)Landroid/graphics/fonts/Font$Builder;
    //   299: pop
    //   300: aload_0
    //   301: ifnonnull -> 320
    //   304: new android/graphics/fonts/FontFamily$Builder
    //   307: dup
    //   308: aload #8
    //   310: invokevirtual build : ()Landroid/graphics/fonts/Font;
    //   313: invokespecial <init> : (Landroid/graphics/fonts/Font;)V
    //   316: astore_0
    //   317: goto -> 330
    //   320: aload_0
    //   321: aload #8
    //   323: invokevirtual build : ()Landroid/graphics/fonts/Font;
    //   326: invokevirtual addFont : (Landroid/graphics/fonts/Font;)Landroid/graphics/fonts/FontFamily$Builder;
    //   329: pop
    //   330: iinc #5, 1
    //   333: goto -> 190
    //   336: aload_0
    //   337: ifnonnull -> 344
    //   340: getstatic android/graphics/Typeface.DEFAULT : Landroid/graphics/Typeface;
    //   343: areturn
    //   344: aload_0
    //   345: invokevirtual build : ()Landroid/graphics/fonts/FontFamily;
    //   348: astore #4
    //   350: new android/graphics/fonts/FontStyle
    //   353: astore #8
    //   355: aload #8
    //   357: sipush #400
    //   360: iconst_0
    //   361: invokespecial <init> : (II)V
    //   364: aload #4
    //   366: iconst_0
    //   367: invokevirtual getFont : (I)Landroid/graphics/fonts/Font;
    //   370: astore_0
    //   371: aload #8
    //   373: aload_0
    //   374: invokevirtual getStyle : ()Landroid/graphics/fonts/FontStyle;
    //   377: invokevirtual getMatchScore : (Landroid/graphics/fonts/FontStyle;)I
    //   380: istore #7
    //   382: iconst_1
    //   383: istore #5
    //   385: iload #5
    //   387: aload #4
    //   389: invokevirtual getSize : ()I
    //   392: if_icmpge -> 441
    //   395: aload #4
    //   397: iload #5
    //   399: invokevirtual getFont : (I)Landroid/graphics/fonts/Font;
    //   402: astore_3
    //   403: aload #8
    //   405: aload_3
    //   406: invokevirtual getStyle : ()Landroid/graphics/fonts/FontStyle;
    //   409: invokevirtual getMatchScore : (Landroid/graphics/fonts/FontStyle;)I
    //   412: istore #9
    //   414: iload #7
    //   416: istore #6
    //   418: iload #9
    //   420: iload #7
    //   422: if_icmpge -> 431
    //   425: aload_3
    //   426: astore_0
    //   427: iload #9
    //   429: istore #6
    //   431: iinc #5, 1
    //   434: iload #6
    //   436: istore #7
    //   438: goto -> 385
    //   441: new android/graphics/Typeface$CustomFallbackBuilder
    //   444: astore_3
    //   445: aload_3
    //   446: aload #4
    //   448: invokespecial <init> : (Landroid/graphics/fonts/FontFamily;)V
    //   451: aload_3
    //   452: aload_0
    //   453: invokevirtual getStyle : ()Landroid/graphics/fonts/FontStyle;
    //   456: invokevirtual setStyle : (Landroid/graphics/fonts/FontStyle;)Landroid/graphics/Typeface$CustomFallbackBuilder;
    //   459: invokevirtual build : ()Landroid/graphics/Typeface;
    //   462: astore_0
    //   463: goto -> 471
    //   466: astore_0
    //   467: getstatic android/graphics/Typeface.DEFAULT : Landroid/graphics/Typeface;
    //   470: astore_0
    //   471: getstatic android/graphics/Typeface.sDynamicCacheLock : Ljava/lang/Object;
    //   474: astore_3
    //   475: aload_3
    //   476: monitorenter
    //   477: aload_1
    //   478: aload_2
    //   479: iconst_0
    //   480: aconst_null
    //   481: iconst_m1
    //   482: iconst_m1
    //   483: ldc 'sans-serif'
    //   485: invokestatic access$000 : (Landroid/content/res/AssetManager;Ljava/lang/String;I[Landroid/graphics/fonts/FontVariationAxis;IILjava/lang/String;)Ljava/lang/String;
    //   488: astore_1
    //   489: getstatic android/graphics/Typeface.sDynamicTypefaceCache : Landroid/util/LruCache;
    //   492: aload_1
    //   493: aload_0
    //   494: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   497: pop
    //   498: aload_3
    //   499: monitorexit
    //   500: aload_0
    //   501: areturn
    //   502: astore_0
    //   503: aload_3
    //   504: monitorexit
    //   505: aload_0
    //   506: athrow
    //   507: astore_0
    //   508: aconst_null
    //   509: areturn
    // Exception table:
    //   from	to	target	type
    //   178	187	507	java/lang/IllegalArgumentException
    //   178	187	466	java/io/IOException
    //   203	261	507	java/lang/IllegalArgumentException
    //   203	261	466	java/io/IOException
    //   261	277	507	java/lang/IllegalArgumentException
    //   261	277	466	java/io/IOException
    //   292	300	507	java/lang/IllegalArgumentException
    //   292	300	466	java/io/IOException
    //   304	317	507	java/lang/IllegalArgumentException
    //   304	317	466	java/io/IOException
    //   320	330	507	java/lang/IllegalArgumentException
    //   320	330	466	java/io/IOException
    //   340	344	507	java/lang/IllegalArgumentException
    //   340	344	466	java/io/IOException
    //   344	382	507	java/lang/IllegalArgumentException
    //   344	382	466	java/io/IOException
    //   385	414	507	java/lang/IllegalArgumentException
    //   385	414	466	java/io/IOException
    //   441	463	507	java/lang/IllegalArgumentException
    //   441	463	466	java/io/IOException
    //   477	498	502	finally
    //   498	500	502	finally
    //   503	505	502	finally
  }
  
  public static Typeface createFromTypefaceWithVariation(Typeface paramTypeface, List<FontVariationAxis> paramList) {
    if (paramTypeface == null)
      paramTypeface = DEFAULT; 
    return new Typeface(nativeCreateFromTypefaceWithVariation(paramTypeface.native_instance, paramList));
  }
  
  private static String createProviderUid(String paramString1, String paramString2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("provider:");
    stringBuilder.append(paramString1);
    stringBuilder.append("-");
    stringBuilder.append(paramString2);
    return stringBuilder.toString();
  }
  
  private static Typeface createWeightStyle(Typeface paramTypeface, int paramInt, boolean paramBoolean) {
    int i = paramInt << 1 | paramBoolean;
    synchronized (sWeightCacheLock) {
      SparseArray sparseArray = (SparseArray)sWeightTypefaceCache.get(paramTypeface.native_instance);
      if (sparseArray == null) {
        sparseArray = new SparseArray();
        this(4);
        sWeightTypefaceCache.put(paramTypeface.native_instance, sparseArray);
      } else {
        Typeface typeface1 = (Typeface)sparseArray.get(i);
        if (typeface1 != null)
          return typeface1; 
      } 
      Typeface typeface = new Typeface();
      this(nativeCreateFromTypefaceWithExactStyle(paramTypeface.native_instance, paramInt, paramBoolean));
      sparseArray.put(i, typeface);
      return typeface;
    } 
  }
  
  public static Typeface defaultFromStyle(int paramInt) {
    return sDefaults[paramInt];
  }
  
  public static Typeface findFromCache(AssetManager paramAssetManager, String paramString) {
    synchronized (sDynamicCacheLock) {
      String str = Builder.createAssetUid(paramAssetManager, paramString, 0, null, -1, -1, "sans-serif");
      Typeface typeface = (Typeface)sDynamicTypefaceCache.get(str);
      if (typeface != null)
        return typeface; 
      return null;
    } 
  }
  
  private static Typeface getSystemDefaultTypeface(String paramString) {
    Typeface typeface = sSystemFontMap.get(paramString);
    if (typeface == null)
      typeface = DEFAULT; 
    return typeface;
  }
  
  public static void initSystemDefaultTypefaces(Map<String, Typeface> paramMap, Map<String, FontFamily[]> paramMap1, FontConfig.Alias[] paramArrayOfAlias) {
    for (Map.Entry<String, FontFamily> entry : paramMap1.entrySet())
      paramMap.put((String)entry.getKey(), createFromFamilies((FontFamily[])entry.getValue())); 
    int i = paramArrayOfAlias.length;
    for (byte b = 0; b < i; b++) {
      FontConfig.Alias alias = paramArrayOfAlias[b];
      if (!paramMap.containsKey(alias.getName())) {
        Typeface typeface = paramMap.get(alias.getToName());
        if (typeface != null) {
          int j = alias.getWeight();
          if (j != 400)
            typeface = new Typeface(nativeCreateWeightAlias(typeface.native_instance, j)); 
          paramMap.put(alias.getName(), typeface);
        } 
      } 
    } 
  }
  
  private static native long nativeCreateFromArray(long[] paramArrayOflong, int paramInt1, int paramInt2);
  
  private static native long nativeCreateFromTypeface(long paramLong, int paramInt);
  
  private static native long nativeCreateFromTypefaceWithExactStyle(long paramLong, int paramInt, boolean paramBoolean);
  
  private static native long nativeCreateFromTypefaceWithVariation(long paramLong, List<FontVariationAxis> paramList);
  
  private static native long nativeCreateWeightAlias(long paramLong, int paramInt);
  
  @CriticalNative
  private static native long nativeGetReleaseFunc();
  
  @CriticalNative
  private static native int nativeGetStyle(long paramLong);
  
  private static native int[] nativeGetSupportedAxes(long paramLong);
  
  @CriticalNative
  private static native int nativeGetWeight(long paramLong);
  
  private static native void nativeRegisterGenericFamily(String paramString, long paramLong);
  
  @CriticalNative
  private static native void nativeSetDefault(long paramLong);
  
  private static void registerGenericFamilyNative(String paramString, Typeface paramTypeface) {
    if (paramTypeface != null)
      nativeRegisterGenericFamily(paramString, paramTypeface.native_instance); 
  }
  
  private static void setDefault(Typeface paramTypeface) {
    sDefaultTypeface = paramTypeface;
    nativeSetDefault(paramTypeface.native_instance);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mStyle != ((Typeface)paramObject).mStyle || this.native_instance != ((Typeface)paramObject).native_instance)
      bool = false; 
    return bool;
  }
  
  public int getStyle() {
    return this.mStyle;
  }
  
  public int getWeight() {
    return this.mWeight;
  }
  
  public int hashCode() {
    long l = this.native_instance;
    return (17 * 31 + (int)(l ^ l >>> 32L)) * 31 + this.mStyle;
  }
  
  public final boolean isBold() {
    int i = this.mStyle;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public final boolean isItalic() {
    boolean bool;
    if ((this.mStyle & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isSupportedAxes(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mSupportedAxes : [I
    //   4: ifnonnull -> 50
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield mSupportedAxes : [I
    //   13: ifnonnull -> 40
    //   16: aload_0
    //   17: getfield native_instance : J
    //   20: invokestatic nativeGetSupportedAxes : (J)[I
    //   23: astore_2
    //   24: aload_0
    //   25: aload_2
    //   26: putfield mSupportedAxes : [I
    //   29: aload_2
    //   30: ifnonnull -> 40
    //   33: aload_0
    //   34: getstatic android/graphics/Typeface.EMPTY_AXES : [I
    //   37: putfield mSupportedAxes : [I
    //   40: aload_0
    //   41: monitorexit
    //   42: goto -> 50
    //   45: astore_2
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_2
    //   49: athrow
    //   50: aload_0
    //   51: getfield mSupportedAxes : [I
    //   54: iload_1
    //   55: invokestatic binarySearch : ([II)I
    //   58: iflt -> 66
    //   61: iconst_1
    //   62: istore_3
    //   63: goto -> 68
    //   66: iconst_0
    //   67: istore_3
    //   68: iload_3
    //   69: ireturn
    // Exception table:
    //   from	to	target	type
    //   9	29	45	finally
    //   33	40	45	finally
    //   40	42	45	finally
    //   46	48	45	finally
  }
  
  public static final class Builder {
    public static final int BOLD_WEIGHT = 700;
    
    public static final int NORMAL_WEIGHT = 400;
    
    private final AssetManager mAssetManager;
    
    private String mFallbackFamilyName;
    
    private final Font.Builder mFontBuilder;
    
    private int mItalic = -1;
    
    private final String mPath;
    
    private int mWeight = -1;
    
    public Builder(AssetManager param1AssetManager, String param1String) {
      this(param1AssetManager, param1String, true, 0);
    }
    
    public Builder(AssetManager param1AssetManager, String param1String, boolean param1Boolean, int param1Int) {
      this.mFontBuilder = new Font.Builder(param1AssetManager, param1String, param1Boolean, param1Int);
      this.mAssetManager = param1AssetManager;
      this.mPath = param1String;
    }
    
    public Builder(File param1File) {
      this.mFontBuilder = new Font.Builder(param1File);
      this.mAssetManager = null;
      this.mPath = null;
    }
    
    public Builder(FileDescriptor param1FileDescriptor) {
      try {
        Font.Builder builder2 = new Font.Builder();
        this(ParcelFileDescriptor.dup(param1FileDescriptor));
        Font.Builder builder1 = builder2;
      } catch (IOException iOException) {
        iOException = null;
      } 
      this.mFontBuilder = (Font.Builder)iOException;
      this.mAssetManager = null;
      this.mPath = null;
    }
    
    public Builder(String param1String) {
      this.mFontBuilder = new Font.Builder(new File(param1String));
      this.mAssetManager = null;
      this.mPath = null;
    }
    
    private static String createAssetUid(AssetManager param1AssetManager, String param1String1, int param1Int1, FontVariationAxis[] param1ArrayOfFontVariationAxis, int param1Int2, int param1Int3, String param1String2) {
      SparseArray sparseArray = param1AssetManager.getAssignedPackageIdentifiers();
      StringBuilder stringBuilder = new StringBuilder();
      int i = sparseArray.size();
      for (byte b = 0; b < i; b++) {
        stringBuilder.append((String)sparseArray.valueAt(b));
        stringBuilder.append("-");
      } 
      stringBuilder.append(param1String1);
      stringBuilder.append("-");
      stringBuilder.append(Integer.toString(param1Int1));
      stringBuilder.append("-");
      stringBuilder.append(Integer.toString(param1Int2));
      stringBuilder.append("-");
      stringBuilder.append(Integer.toString(param1Int3));
      stringBuilder.append("--");
      stringBuilder.append(param1String2);
      stringBuilder.append("--");
      if (param1ArrayOfFontVariationAxis != null) {
        param1Int2 = param1ArrayOfFontVariationAxis.length;
        for (param1Int1 = 0; param1Int1 < param1Int2; param1Int1++) {
          FontVariationAxis fontVariationAxis = param1ArrayOfFontVariationAxis[param1Int1];
          stringBuilder.append(fontVariationAxis.getTag());
          stringBuilder.append("-");
          stringBuilder.append(Float.toString(fontVariationAxis.getStyleValue()));
        } 
      } 
      return stringBuilder.toString();
    }
    
    private Typeface resolveFallbackTypeface() {
      String str = this.mFallbackFamilyName;
      if (str == null)
        return null; 
      Typeface typeface = Typeface.getSystemDefaultTypeface(str);
      if (this.mWeight == -1 && this.mItalic == -1)
        return typeface; 
      int i = this.mWeight;
      int j = i;
      if (i == -1)
        j = typeface.mWeight; 
      i = this.mItalic;
      boolean bool = false;
      if ((i == -1) ? ((typeface.mStyle & 0x2) != 0) : (i == 1))
        bool = true; 
      return Typeface.createWeightStyle(typeface, j, bool);
    }
    
    public Typeface build() {
      Font.Builder builder = this.mFontBuilder;
      if (builder == null)
        return resolveFallbackTypeface(); 
      try {
        String str;
        int i;
        int j;
        Font font = builder.build();
        if (this.mAssetManager == null) {
          builder = null;
        } else {
          AssetManager assetManager = this.mAssetManager;
          String str1 = this.mPath;
          i = font.getTtcIndex();
          FontVariationAxis[] arrayOfFontVariationAxis = font.getAxes();
          j = this.mWeight;
          int k = this.mItalic;
          if (this.mFallbackFamilyName == null) {
            str = "sans-serif";
          } else {
            str = this.mFallbackFamilyName;
          } 
          str = createAssetUid(assetManager, str1, i, arrayOfFontVariationAxis, j, k, str);
        } 
        if (str != null)
          synchronized (Typeface.sDynamicCacheLock) {
            Typeface typeface1 = (Typeface)Typeface.sDynamicTypefaceCache.get(str);
            if (typeface1 != null)
              return typeface1; 
          }  
        FontFamily.Builder builder1 = new FontFamily.Builder();
        this(font);
        FontFamily fontFamily = builder1.build();
        if (this.mWeight == -1) {
          i = font.getStyle().getWeight();
        } else {
          i = this.mWeight;
        } 
        if (this.mItalic == -1) {
          j = font.getStyle().getSlant();
        } else {
          j = this.mItalic;
        } 
        Typeface.CustomFallbackBuilder customFallbackBuilder = new Typeface.CustomFallbackBuilder();
        this(fontFamily);
        FontStyle fontStyle = new FontStyle();
        this(i, j);
        customFallbackBuilder = customFallbackBuilder.setStyle(fontStyle);
        if (this.mFallbackFamilyName != null)
          customFallbackBuilder.setSystemFallback(this.mFallbackFamilyName); 
        Typeface typeface = customFallbackBuilder.build();
        if (str != null)
          synchronized (Typeface.sDynamicCacheLock) {
            Typeface.sDynamicTypefaceCache.put(str, typeface);
          }  
        return typeface;
      } catch (IOException|IllegalArgumentException iOException) {
        return resolveFallbackTypeface();
      } 
    }
    
    public Builder setFallback(String param1String) {
      this.mFallbackFamilyName = param1String;
      return this;
    }
    
    public Builder setFontVariationSettings(String param1String) {
      this.mFontBuilder.setFontVariationSettings(param1String);
      return this;
    }
    
    public Builder setFontVariationSettings(FontVariationAxis[] param1ArrayOfFontVariationAxis) {
      this.mFontBuilder.setFontVariationSettings(param1ArrayOfFontVariationAxis);
      return this;
    }
    
    public Builder setItalic(boolean param1Boolean) {
      this.mItalic = param1Boolean;
      this.mFontBuilder.setSlant(param1Boolean);
      return this;
    }
    
    public Builder setTtcIndex(int param1Int) {
      this.mFontBuilder.setTtcIndex(param1Int);
      return this;
    }
    
    public Builder setWeight(int param1Int) {
      this.mWeight = param1Int;
      this.mFontBuilder.setWeight(param1Int);
      return this;
    }
  }
  
  public static final class CustomFallbackBuilder {
    private static final int MAX_CUSTOM_FALLBACK = 64;
    
    private String mFallbackName = null;
    
    private final ArrayList<FontFamily> mFamilies = new ArrayList<>();
    
    private FontStyle mStyle;
    
    public CustomFallbackBuilder(FontFamily param1FontFamily) {
      Preconditions.checkNotNull(param1FontFamily);
      this.mFamilies.add(param1FontFamily);
    }
    
    public static int getMaxCustomFallbackCount() {
      return 64;
    }
    
    public CustomFallbackBuilder addCustomFallback(FontFamily param1FontFamily) {
      boolean bool;
      Preconditions.checkNotNull(param1FontFamily);
      if (this.mFamilies.size() < getMaxCustomFallbackCount()) {
        bool = true;
      } else {
        bool = false;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Custom fallback limit exceeded(");
      stringBuilder.append(getMaxCustomFallbackCount());
      stringBuilder.append(")");
      Preconditions.checkArgument(bool, stringBuilder.toString());
      this.mFamilies.add(param1FontFamily);
      return this;
    }
    
    public Typeface build() {
      int i = this.mFamilies.size();
      FontFamily[] arrayOfFontFamily = SystemFonts.getSystemFallback(this.mFallbackName);
      long[] arrayOfLong = new long[arrayOfFontFamily.length + i];
      int j;
      for (j = 0; j < i; j++)
        arrayOfLong[j] = ((FontFamily)this.mFamilies.get(j)).getNativePtr(); 
      for (j = 0; j < arrayOfFontFamily.length; j++)
        arrayOfLong[j + i] = arrayOfFontFamily[j].getNativePtr(); 
      FontStyle fontStyle = this.mStyle;
      if (fontStyle == null) {
        j = 400;
      } else {
        j = fontStyle.getWeight();
      } 
      fontStyle = this.mStyle;
      if (fontStyle == null || fontStyle.getSlant() == 0) {
        i = 0;
        return new Typeface(Typeface.nativeCreateFromArray(arrayOfLong, j, i));
      } 
      i = 1;
      return new Typeface(Typeface.nativeCreateFromArray(arrayOfLong, j, i));
    }
    
    public CustomFallbackBuilder setStyle(FontStyle param1FontStyle) {
      this.mStyle = param1FontStyle;
      return this;
    }
    
    public CustomFallbackBuilder setSystemFallback(String param1String) {
      Preconditions.checkNotNull(param1String);
      this.mFallbackName = param1String;
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Style {}
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Typeface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */