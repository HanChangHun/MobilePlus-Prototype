package android.graphics.fonts;

import android.graphics.FontListParser;
import android.text.FontConfig;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

public final class SystemFonts {
  private static final String DEFAULT_FAMILY = "sans-serif";
  
  private static final String TAG = "SystemFonts";
  
  private static final FontConfig.Alias[] sAliases;
  
  private static final List<Font> sAvailableFonts;
  
  private static final Map<String, FontFamily[]> sSystemFallbackMap;
  
  static {
    ArrayMap<String, FontFamily[]> arrayMap = new ArrayMap();
    ArrayList<Font> arrayList = new ArrayList();
    sAliases = buildSystemFallback("/system/etc/fonts.xml", "/system/fonts/", readFontCustomization("/product/etc/fonts_customization.xml", "/product/fonts/"), arrayMap, arrayList);
    sSystemFallbackMap = Collections.unmodifiableMap((Map)arrayMap);
    sAvailableFonts = Collections.unmodifiableList(arrayList);
  }
  
  private static void appendNamedFamily(FontConfig.Family paramFamily, HashMap<String, ByteBuffer> paramHashMap, ArrayMap<String, ArrayList<FontFamily>> paramArrayMap, ArrayList<Font> paramArrayList) {
    String str = paramFamily.getName();
    FontFamily fontFamily = createFontFamily(str, Arrays.asList(paramFamily.getFonts()), paramFamily.getLanguages(), paramFamily.getVariant(), paramHashMap, paramArrayList);
    if (fontFamily == null)
      return; 
    ArrayList<FontFamily> arrayList = new ArrayList();
    arrayList.add(fontFamily);
    paramArrayMap.put(str, arrayList);
  }
  
  public static FontConfig.Alias[] buildSystemFallback(String paramString1, String paramString2, FontCustomizationParser.Result paramResult, ArrayMap<String, FontFamily[]> paramArrayMap, ArrayList<Font> paramArrayList) {
    try {
      FileInputStream fileInputStream = new FileInputStream();
      this(paramString1);
      FontConfig fontConfig = FontListParser.parse(fileInputStream, paramString2);
      HashMap<Object, Object> hashMap = new HashMap<>();
      this();
      FontConfig.Family[] arrayOfFamily = fontConfig.getFamilies();
      ArrayMap<String, ArrayList<FontFamily>> arrayMap = new ArrayMap();
      this();
      int i = arrayOfFamily.length;
      byte b;
      for (b = 0; b < i; b++) {
        FontConfig.Family family = arrayOfFamily[b];
        if (family.getName() != null)
          appendNamedFamily(family, (HashMap)hashMap, arrayMap, paramArrayList); 
      } 
      for (b = 0; b < paramResult.mAdditionalNamedFamilies.size(); b++)
        appendNamedFamily(paramResult.mAdditionalNamedFamilies.get(b), (HashMap)hashMap, arrayMap, paramArrayList); 
      for (b = 0; b < arrayOfFamily.length; b++) {
        FontConfig.Family family = arrayOfFamily[b];
        if (b == 0 || family.getName() == null)
          pushFamilyToFallback(family, arrayMap, (Map)hashMap, paramArrayList); 
      } 
      for (b = 0; b < arrayMap.size(); b++) {
        String str = (String)arrayMap.keyAt(b);
        List list = (List)arrayMap.valueAt(b);
        paramArrayMap.put(str, list.toArray((Object[])new FontFamily[list.size()]));
      } 
      ArrayList<FontConfig.Alias> arrayList = new ArrayList();
      this();
      arrayList.addAll(Arrays.asList(fontConfig.getAliases()));
      arrayList.addAll(paramResult.mAdditionalAliases);
      return arrayList.<FontConfig.Alias>toArray(new FontConfig.Alias[arrayList.size()]);
    } catch (IOException|XmlPullParserException iOException) {
      Log.e("SystemFonts", "Failed initialize system fallbacks.", iOException);
      return (FontConfig.Alias[])ArrayUtils.emptyArray(FontConfig.Alias.class);
    } 
  }
  
  private static FontFamily createFontFamily(String paramString1, List<FontConfig.Font> paramList, String paramString2, int paramInt, Map<String, ByteBuffer> paramMap, ArrayList<Font> paramArrayList) {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface size : ()I
    //   6: istore #6
    //   8: aconst_null
    //   9: astore #7
    //   11: iload #6
    //   13: ifne -> 18
    //   16: aconst_null
    //   17: areturn
    //   18: aconst_null
    //   19: astore_0
    //   20: iconst_0
    //   21: istore #6
    //   23: aload_1
    //   24: invokeinterface size : ()I
    //   29: istore #8
    //   31: iconst_0
    //   32: istore #9
    //   34: iload #6
    //   36: iload #8
    //   38: if_icmpge -> 259
    //   41: aload_1
    //   42: iload #6
    //   44: invokeinterface get : (I)Ljava/lang/Object;
    //   49: checkcast android/text/FontConfig$Font
    //   52: astore #10
    //   54: aload #10
    //   56: invokevirtual getFontName : ()Ljava/lang/String;
    //   59: astore #11
    //   61: aload #4
    //   63: aload #11
    //   65: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   70: checkcast java/nio/ByteBuffer
    //   73: astore #12
    //   75: aload #12
    //   77: astore #13
    //   79: aload #12
    //   81: ifnonnull -> 130
    //   84: aload #4
    //   86: aload #11
    //   88: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   93: ifeq -> 99
    //   96: goto -> 243
    //   99: aload #11
    //   101: invokestatic mmap : (Ljava/lang/String;)Ljava/nio/ByteBuffer;
    //   104: astore #12
    //   106: aload #4
    //   108: aload #11
    //   110: aload #12
    //   112: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   117: pop
    //   118: aload #12
    //   120: astore #13
    //   122: aload #12
    //   124: ifnonnull -> 130
    //   127: goto -> 243
    //   130: new android/graphics/fonts/Font$Builder
    //   133: astore #14
    //   135: new java/io/File
    //   138: astore #12
    //   140: aload #12
    //   142: aload #11
    //   144: invokespecial <init> : (Ljava/lang/String;)V
    //   147: aload #14
    //   149: aload #13
    //   151: aload #12
    //   153: aload_2
    //   154: invokespecial <init> : (Ljava/nio/ByteBuffer;Ljava/io/File;Ljava/lang/String;)V
    //   157: aload #14
    //   159: aload #10
    //   161: invokevirtual getWeight : ()I
    //   164: invokevirtual setWeight : (I)Landroid/graphics/fonts/Font$Builder;
    //   167: astore #13
    //   169: aload #10
    //   171: invokevirtual isItalic : ()Z
    //   174: ifeq -> 183
    //   177: iconst_1
    //   178: istore #9
    //   180: goto -> 183
    //   183: aload #13
    //   185: iload #9
    //   187: invokevirtual setSlant : (I)Landroid/graphics/fonts/Font$Builder;
    //   190: aload #10
    //   192: invokevirtual getTtcIndex : ()I
    //   195: invokevirtual setTtcIndex : (I)Landroid/graphics/fonts/Font$Builder;
    //   198: aload #10
    //   200: invokevirtual getAxes : ()[Landroid/graphics/fonts/FontVariationAxis;
    //   203: invokevirtual setFontVariationSettings : ([Landroid/graphics/fonts/FontVariationAxis;)Landroid/graphics/fonts/Font$Builder;
    //   206: invokevirtual build : ()Landroid/graphics/fonts/Font;
    //   209: astore #13
    //   211: aload #5
    //   213: aload #13
    //   215: invokevirtual add : (Ljava/lang/Object;)Z
    //   218: pop
    //   219: aload_0
    //   220: ifnonnull -> 236
    //   223: new android/graphics/fonts/FontFamily$Builder
    //   226: dup
    //   227: aload #13
    //   229: invokespecial <init> : (Landroid/graphics/fonts/Font;)V
    //   232: astore_0
    //   233: goto -> 243
    //   236: aload_0
    //   237: aload #13
    //   239: invokevirtual addFont : (Landroid/graphics/fonts/Font;)Landroid/graphics/fonts/FontFamily$Builder;
    //   242: pop
    //   243: iinc #6, 1
    //   246: goto -> 23
    //   249: astore_0
    //   250: new java/lang/RuntimeException
    //   253: dup
    //   254: aload_0
    //   255: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   258: athrow
    //   259: aload_0
    //   260: ifnonnull -> 269
    //   263: aload #7
    //   265: astore_0
    //   266: goto -> 277
    //   269: aload_0
    //   270: aload_2
    //   271: iload_3
    //   272: iconst_0
    //   273: invokevirtual build : (Ljava/lang/String;IZ)Landroid/graphics/fonts/FontFamily;
    //   276: astore_0
    //   277: aload_0
    //   278: areturn
    // Exception table:
    //   from	to	target	type
    //   130	177	249	java/io/IOException
    //   183	211	249	java/io/IOException
  }
  
  public static FontConfig.Alias[] getAliases() {
    return sAliases;
  }
  
  public static Set<Font> getAvailableFonts() {
    HashSet<Font> hashSet = new HashSet();
    hashSet.addAll(sAvailableFonts);
    return hashSet;
  }
  
  public static Map<String, FontFamily[]> getRawSystemFallbackMap() {
    return sSystemFallbackMap;
  }
  
  public static FontFamily[] getSystemFallback(String paramString) {
    FontFamily[] arrayOfFontFamily = sSystemFallbackMap.get(paramString);
    if (arrayOfFontFamily == null)
      arrayOfFontFamily = sSystemFallbackMap.get("sans-serif"); 
    return arrayOfFontFamily;
  }
  
  private static ByteBuffer mmap(String paramString) {
    try {
      FileInputStream fileInputStream = new FileInputStream();
      this(paramString);
      try {
        FileChannel fileChannel = fileInputStream.getChannel();
        long l = fileChannel.size();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, l);
      } finally {
        try {
          fileInputStream.close();
        } finally {
          fileInputStream = null;
        } 
      } 
    } catch (IOException iOException) {
      return null;
    } 
  }
  
  private static void pushFamilyToFallback(FontConfig.Family paramFamily, ArrayMap<String, ArrayList<FontFamily>> paramArrayMap, Map<String, ByteBuffer> paramMap, ArrayList<Font> paramArrayList) {
    FontFamily fontFamily;
    String str = paramFamily.getLanguages();
    int i = paramFamily.getVariant();
    ArrayList<FontConfig.Font> arrayList = new ArrayList();
    ArrayMap arrayMap = new ArrayMap();
    for (FontConfig.Font font : paramFamily.getFonts()) {
      String str1 = font.getFallbackFor();
      if (str1 == null) {
        arrayList.add(font);
      } else {
        ArrayList<FontConfig.Font> arrayList1 = (ArrayList)arrayMap.get(str1);
        ArrayList<FontConfig.Font> arrayList2 = arrayList1;
        if (arrayList1 == null) {
          arrayList2 = new ArrayList();
          arrayMap.put(str1, arrayList2);
        } 
        arrayList2.add(font);
      } 
    } 
    if (arrayList.isEmpty()) {
      fontFamily = null;
    } else {
      fontFamily = createFontFamily(paramFamily.getName(), arrayList, str, i, paramMap, paramArrayList);
    } 
    for (byte b = 0; b < paramArrayMap.size(); b++) {
      ArrayList<FontConfig.Font> arrayList1 = (ArrayList)arrayMap.get(paramArrayMap.keyAt(b));
      if (arrayList1 == null) {
        if (fontFamily != null)
          ((ArrayList<FontFamily>)paramArrayMap.valueAt(b)).add(fontFamily); 
      } else {
        FontFamily fontFamily1 = createFontFamily(paramFamily.getName(), arrayList1, str, i, paramMap, paramArrayList);
        if (fontFamily1 != null) {
          ((ArrayList<FontFamily>)paramArrayMap.valueAt(b)).add(fontFamily1);
        } else if (fontFamily != null) {
          ((ArrayList<FontFamily>)paramArrayMap.valueAt(b)).add(fontFamily);
        } 
      } 
    } 
  }
  
  private static FontCustomizationParser.Result readFontCustomization(String paramString1, String paramString2) {
    try {
      FileInputStream fileInputStream = new FileInputStream();
      this(paramString1);
      try {
        return FontCustomizationParser.parse(fileInputStream, paramString2);
      } finally {
        try {
          fileInputStream.close();
        } finally {
          paramString2 = null;
        } 
      } 
    } catch (IOException iOException) {
      return new FontCustomizationParser.Result();
    } catch (XmlPullParserException xmlPullParserException) {
      Log.e("SystemFonts", "Failed to parse font customization XML", (Throwable)xmlPullParserException);
      return new FontCustomizationParser.Result();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/fonts/SystemFonts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */