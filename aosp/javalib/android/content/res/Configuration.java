package android.content.res;

import android.app.WindowConfiguration;
import android.os.Build;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.proto.ProtoInputStream;
import android.util.proto.ProtoOutputStream;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class Configuration implements Parcelable, Comparable<Configuration> {
  public static final int ASSETS_SEQ_UNDEFINED = 0;
  
  public static final int COLOR_MODE_HDR_MASK = 12;
  
  public static final int COLOR_MODE_HDR_NO = 4;
  
  public static final int COLOR_MODE_HDR_SHIFT = 2;
  
  public static final int COLOR_MODE_HDR_UNDEFINED = 0;
  
  public static final int COLOR_MODE_HDR_YES = 8;
  
  public static final int COLOR_MODE_UNDEFINED = 0;
  
  public static final int COLOR_MODE_WIDE_COLOR_GAMUT_MASK = 3;
  
  public static final int COLOR_MODE_WIDE_COLOR_GAMUT_NO = 1;
  
  public static final int COLOR_MODE_WIDE_COLOR_GAMUT_UNDEFINED = 0;
  
  public static final int COLOR_MODE_WIDE_COLOR_GAMUT_YES = 2;
  
  public static final Parcelable.Creator<Configuration> CREATOR;
  
  public static final int DENSITY_DPI_ANY = 65534;
  
  public static final int DENSITY_DPI_NONE = 65535;
  
  public static final int DENSITY_DPI_UNDEFINED = 0;
  
  public static final Configuration EMPTY = new Configuration();
  
  public static final int HARDKEYBOARDHIDDEN_NO = 1;
  
  public static final int HARDKEYBOARDHIDDEN_UNDEFINED = 0;
  
  public static final int HARDKEYBOARDHIDDEN_YES = 2;
  
  public static final int KEYBOARDHIDDEN_NO = 1;
  
  public static final int KEYBOARDHIDDEN_SOFT = 3;
  
  public static final int KEYBOARDHIDDEN_UNDEFINED = 0;
  
  public static final int KEYBOARDHIDDEN_YES = 2;
  
  public static final int KEYBOARD_12KEY = 3;
  
  public static final int KEYBOARD_NOKEYS = 1;
  
  public static final int KEYBOARD_QWERTY = 2;
  
  public static final int KEYBOARD_UNDEFINED = 0;
  
  public static final int MNC_ZERO = 65535;
  
  public static final int NATIVE_CONFIG_COLOR_MODE = 65536;
  
  public static final int NATIVE_CONFIG_DENSITY = 256;
  
  public static final int NATIVE_CONFIG_KEYBOARD = 16;
  
  public static final int NATIVE_CONFIG_KEYBOARD_HIDDEN = 32;
  
  public static final int NATIVE_CONFIG_LAYOUTDIR = 16384;
  
  public static final int NATIVE_CONFIG_LOCALE = 4;
  
  public static final int NATIVE_CONFIG_MCC = 1;
  
  public static final int NATIVE_CONFIG_MNC = 2;
  
  public static final int NATIVE_CONFIG_NAVIGATION = 64;
  
  public static final int NATIVE_CONFIG_ORIENTATION = 128;
  
  public static final int NATIVE_CONFIG_SCREEN_LAYOUT = 2048;
  
  public static final int NATIVE_CONFIG_SCREEN_SIZE = 512;
  
  public static final int NATIVE_CONFIG_SMALLEST_SCREEN_SIZE = 8192;
  
  public static final int NATIVE_CONFIG_TOUCHSCREEN = 8;
  
  public static final int NATIVE_CONFIG_UI_MODE = 4096;
  
  public static final int NATIVE_CONFIG_VERSION = 1024;
  
  public static final int NAVIGATIONHIDDEN_NO = 1;
  
  public static final int NAVIGATIONHIDDEN_UNDEFINED = 0;
  
  public static final int NAVIGATIONHIDDEN_YES = 2;
  
  public static final int NAVIGATION_DPAD = 2;
  
  public static final int NAVIGATION_NONAV = 1;
  
  public static final int NAVIGATION_TRACKBALL = 3;
  
  public static final int NAVIGATION_UNDEFINED = 0;
  
  public static final int NAVIGATION_WHEEL = 4;
  
  public static final int ORIENTATION_LANDSCAPE = 2;
  
  public static final int ORIENTATION_PORTRAIT = 1;
  
  @Deprecated
  public static final int ORIENTATION_SQUARE = 3;
  
  public static final int ORIENTATION_UNDEFINED = 0;
  
  public static final int SCREENLAYOUT_COMPAT_NEEDED = 268435456;
  
  public static final int SCREENLAYOUT_LAYOUTDIR_LTR = 64;
  
  public static final int SCREENLAYOUT_LAYOUTDIR_MASK = 192;
  
  public static final int SCREENLAYOUT_LAYOUTDIR_RTL = 128;
  
  public static final int SCREENLAYOUT_LAYOUTDIR_SHIFT = 6;
  
  public static final int SCREENLAYOUT_LAYOUTDIR_UNDEFINED = 0;
  
  public static final int SCREENLAYOUT_LONG_MASK = 48;
  
  public static final int SCREENLAYOUT_LONG_NO = 16;
  
  public static final int SCREENLAYOUT_LONG_UNDEFINED = 0;
  
  public static final int SCREENLAYOUT_LONG_YES = 32;
  
  public static final int SCREENLAYOUT_ROUND_MASK = 768;
  
  public static final int SCREENLAYOUT_ROUND_NO = 256;
  
  public static final int SCREENLAYOUT_ROUND_SHIFT = 8;
  
  public static final int SCREENLAYOUT_ROUND_UNDEFINED = 0;
  
  public static final int SCREENLAYOUT_ROUND_YES = 512;
  
  public static final int SCREENLAYOUT_SIZE_LARGE = 3;
  
  public static final int SCREENLAYOUT_SIZE_MASK = 15;
  
  public static final int SCREENLAYOUT_SIZE_NORMAL = 2;
  
  public static final int SCREENLAYOUT_SIZE_SMALL = 1;
  
  public static final int SCREENLAYOUT_SIZE_UNDEFINED = 0;
  
  public static final int SCREENLAYOUT_SIZE_XLARGE = 4;
  
  public static final int SCREENLAYOUT_UNDEFINED = 0;
  
  public static final int SCREEN_HEIGHT_DP_UNDEFINED = 0;
  
  public static final int SCREEN_WIDTH_DP_UNDEFINED = 0;
  
  public static final int SMALLEST_SCREEN_WIDTH_DP_UNDEFINED = 0;
  
  private static final String TAG = "Configuration";
  
  public static final int TOUCHSCREEN_FINGER = 3;
  
  public static final int TOUCHSCREEN_NOTOUCH = 1;
  
  @Deprecated
  public static final int TOUCHSCREEN_STYLUS = 2;
  
  public static final int TOUCHSCREEN_UNDEFINED = 0;
  
  public static final int UI_MODE_NIGHT_MASK = 48;
  
  public static final int UI_MODE_NIGHT_NO = 16;
  
  public static final int UI_MODE_NIGHT_UNDEFINED = 0;
  
  public static final int UI_MODE_NIGHT_YES = 32;
  
  public static final int UI_MODE_TYPE_APPLIANCE = 5;
  
  public static final int UI_MODE_TYPE_CAR = 3;
  
  public static final int UI_MODE_TYPE_DESK = 2;
  
  public static final int UI_MODE_TYPE_MASK = 15;
  
  public static final int UI_MODE_TYPE_NORMAL = 1;
  
  public static final int UI_MODE_TYPE_TELEVISION = 4;
  
  public static final int UI_MODE_TYPE_UNDEFINED = 0;
  
  public static final int UI_MODE_TYPE_VR_HEADSET = 7;
  
  public static final int UI_MODE_TYPE_WATCH = 6;
  
  private static final String XML_ATTR_APP_BOUNDS = "app_bounds";
  
  private static final String XML_ATTR_COLOR_MODE = "clrMod";
  
  private static final String XML_ATTR_DENSITY = "density";
  
  private static final String XML_ATTR_FONT_SCALE = "fs";
  
  private static final String XML_ATTR_HARD_KEYBOARD_HIDDEN = "hardKeyHid";
  
  private static final String XML_ATTR_KEYBOARD = "key";
  
  private static final String XML_ATTR_KEYBOARD_HIDDEN = "keyHid";
  
  private static final String XML_ATTR_LOCALES = "locales";
  
  private static final String XML_ATTR_MCC = "mcc";
  
  private static final String XML_ATTR_MNC = "mnc";
  
  private static final String XML_ATTR_NAVIGATION = "nav";
  
  private static final String XML_ATTR_NAVIGATION_HIDDEN = "navHid";
  
  private static final String XML_ATTR_ORIENTATION = "ori";
  
  private static final String XML_ATTR_ROTATION = "rot";
  
  private static final String XML_ATTR_SCREEN_HEIGHT = "height";
  
  private static final String XML_ATTR_SCREEN_LAYOUT = "scrLay";
  
  private static final String XML_ATTR_SCREEN_WIDTH = "width";
  
  private static final String XML_ATTR_SMALLEST_WIDTH = "sw";
  
  private static final String XML_ATTR_TOUCHSCREEN = "touch";
  
  private static final String XML_ATTR_UI_MODE = "ui";
  
  public int assetsSeq;
  
  public int colorMode;
  
  public int compatScreenHeightDp;
  
  public int compatScreenWidthDp;
  
  public int compatSmallestScreenWidthDp;
  
  public int densityDpi;
  
  public float fontScale;
  
  public int hardKeyboardHidden;
  
  public int keyboard;
  
  public int keyboardHidden;
  
  @Deprecated
  public Locale locale;
  
  private LocaleList mLocaleList;
  
  public int mcc;
  
  public int mnc;
  
  public int navigation;
  
  public int navigationHidden;
  
  public int orientation;
  
  public int screenHeightDp;
  
  public int screenLayout;
  
  public int screenWidthDp;
  
  public int seq;
  
  public int smallestScreenWidthDp;
  
  public int touchscreen;
  
  public int uiMode;
  
  public boolean userSetLocale;
  
  public final WindowConfiguration windowConfiguration = new WindowConfiguration();
  
  static {
    CREATOR = new Parcelable.Creator<Configuration>() {
        public Configuration createFromParcel(Parcel param1Parcel) {
          return new Configuration(param1Parcel);
        }
        
        public Configuration[] newArray(int param1Int) {
          return new Configuration[param1Int];
        }
      };
  }
  
  public Configuration() {
    unset();
  }
  
  public Configuration(Configuration paramConfiguration) {
    setTo(paramConfiguration);
  }
  
  private Configuration(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public static String configurationDiffToString(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    if ((paramInt & 0x1) != 0)
      arrayList.add("CONFIG_MCC"); 
    if ((paramInt & 0x2) != 0)
      arrayList.add("CONFIG_MNC"); 
    if ((paramInt & 0x4) != 0)
      arrayList.add("CONFIG_LOCALE"); 
    if ((paramInt & 0x8) != 0)
      arrayList.add("CONFIG_TOUCHSCREEN"); 
    if ((paramInt & 0x10) != 0)
      arrayList.add("CONFIG_KEYBOARD"); 
    if ((paramInt & 0x20) != 0)
      arrayList.add("CONFIG_KEYBOARD_HIDDEN"); 
    if ((paramInt & 0x40) != 0)
      arrayList.add("CONFIG_NAVIGATION"); 
    if ((paramInt & 0x80) != 0)
      arrayList.add("CONFIG_ORIENTATION"); 
    if ((paramInt & 0x100) != 0)
      arrayList.add("CONFIG_SCREEN_LAYOUT"); 
    if ((paramInt & 0x4000) != 0)
      arrayList.add("CONFIG_COLOR_MODE"); 
    if ((paramInt & 0x200) != 0)
      arrayList.add("CONFIG_UI_MODE"); 
    if ((paramInt & 0x400) != 0)
      arrayList.add("CONFIG_SCREEN_SIZE"); 
    if ((paramInt & 0x800) != 0)
      arrayList.add("CONFIG_SMALLEST_SCREEN_SIZE"); 
    if ((paramInt & 0x1000) != 0)
      arrayList.add("CONFIG_DENSITY"); 
    if ((paramInt & 0x2000) != 0)
      arrayList.add("CONFIG_LAYOUT_DIRECTION"); 
    if ((0x40000000 & paramInt) != 0)
      arrayList.add("CONFIG_FONT_SCALE"); 
    if ((Integer.MIN_VALUE & paramInt) != 0)
      arrayList.add("CONFIG_ASSETS_PATHS"); 
    if ((0x20000000 & paramInt) != 0)
      arrayList.add("CONFIG_WINDOW_CONFIGURATION"); 
    StringBuilder stringBuilder = new StringBuilder("{");
    paramInt = 0;
    int i = arrayList.size();
    while (paramInt < i) {
      stringBuilder.append(arrayList.get(paramInt));
      if (paramInt != i - 1)
        stringBuilder.append(", "); 
      paramInt++;
    } 
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  private void fixUpLocaleList() {
    // Byte code:
    //   0: aload_0
    //   1: getfield locale : Ljava/util/Locale;
    //   4: ifnonnull -> 17
    //   7: aload_0
    //   8: getfield mLocaleList : Landroid/os/LocaleList;
    //   11: invokevirtual isEmpty : ()Z
    //   14: ifeq -> 41
    //   17: aload_0
    //   18: getfield locale : Ljava/util/Locale;
    //   21: astore_1
    //   22: aload_1
    //   23: ifnull -> 79
    //   26: aload_1
    //   27: aload_0
    //   28: getfield mLocaleList : Landroid/os/LocaleList;
    //   31: iconst_0
    //   32: invokevirtual get : (I)Ljava/util/Locale;
    //   35: invokevirtual equals : (Ljava/lang/Object;)Z
    //   38: ifne -> 79
    //   41: aload_0
    //   42: getfield locale : Ljava/util/Locale;
    //   45: ifnonnull -> 55
    //   48: invokestatic getEmptyLocaleList : ()Landroid/os/LocaleList;
    //   51: astore_1
    //   52: goto -> 74
    //   55: new android/os/LocaleList
    //   58: dup
    //   59: iconst_1
    //   60: anewarray java/util/Locale
    //   63: dup
    //   64: iconst_0
    //   65: aload_0
    //   66: getfield locale : Ljava/util/Locale;
    //   69: aastore
    //   70: invokespecial <init> : ([Ljava/util/Locale;)V
    //   73: astore_1
    //   74: aload_0
    //   75: aload_1
    //   76: putfield mLocaleList : Landroid/os/LocaleList;
    //   79: return
  }
  
  public static Configuration generateDelta(Configuration paramConfiguration1, Configuration paramConfiguration2) {
    Configuration configuration = new Configuration();
    float f1 = paramConfiguration1.fontScale;
    float f2 = paramConfiguration2.fontScale;
    if (f1 != f2)
      configuration.fontScale = f2; 
    int i = paramConfiguration1.mcc;
    int j = paramConfiguration2.mcc;
    if (i != j)
      configuration.mcc = j; 
    i = paramConfiguration1.mnc;
    j = paramConfiguration2.mnc;
    if (i != j)
      configuration.mnc = j; 
    paramConfiguration1.fixUpLocaleList();
    paramConfiguration2.fixUpLocaleList();
    if (!paramConfiguration1.mLocaleList.equals(paramConfiguration2.mLocaleList)) {
      configuration.mLocaleList = paramConfiguration2.mLocaleList;
      configuration.locale = paramConfiguration2.locale;
    } 
    j = paramConfiguration1.touchscreen;
    i = paramConfiguration2.touchscreen;
    if (j != i)
      configuration.touchscreen = i; 
    j = paramConfiguration1.keyboard;
    i = paramConfiguration2.keyboard;
    if (j != i)
      configuration.keyboard = i; 
    j = paramConfiguration1.keyboardHidden;
    i = paramConfiguration2.keyboardHidden;
    if (j != i)
      configuration.keyboardHidden = i; 
    i = paramConfiguration1.navigation;
    j = paramConfiguration2.navigation;
    if (i != j)
      configuration.navigation = j; 
    i = paramConfiguration1.navigationHidden;
    j = paramConfiguration2.navigationHidden;
    if (i != j)
      configuration.navigationHidden = j; 
    i = paramConfiguration1.orientation;
    j = paramConfiguration2.orientation;
    if (i != j)
      configuration.orientation = j; 
    j = paramConfiguration1.screenLayout;
    i = paramConfiguration2.screenLayout;
    if ((j & 0xF) != (i & 0xF))
      configuration.screenLayout |= i & 0xF; 
    i = paramConfiguration1.screenLayout;
    j = paramConfiguration2.screenLayout;
    if ((i & 0xC0) != (j & 0xC0))
      configuration.screenLayout |= j & 0xC0; 
    i = paramConfiguration1.screenLayout;
    j = paramConfiguration2.screenLayout;
    if ((i & 0x30) != (j & 0x30))
      configuration.screenLayout |= j & 0x30; 
    i = paramConfiguration1.screenLayout;
    j = paramConfiguration2.screenLayout;
    if ((i & 0x300) != (j & 0x300))
      configuration.screenLayout |= j & 0x300; 
    j = paramConfiguration1.colorMode;
    i = paramConfiguration2.colorMode;
    if ((j & 0x3) != (i & 0x3))
      configuration.colorMode |= i & 0x3; 
    j = paramConfiguration1.colorMode;
    i = paramConfiguration2.colorMode;
    if ((j & 0xC) != (i & 0xC))
      configuration.colorMode |= i & 0xC; 
    j = paramConfiguration1.uiMode;
    i = paramConfiguration2.uiMode;
    if ((j & 0xF) != (i & 0xF))
      configuration.uiMode |= i & 0xF; 
    j = paramConfiguration1.uiMode;
    i = paramConfiguration2.uiMode;
    if ((j & 0x30) != (i & 0x30))
      configuration.uiMode |= i & 0x30; 
    j = paramConfiguration1.screenWidthDp;
    i = paramConfiguration2.screenWidthDp;
    if (j != i)
      configuration.screenWidthDp = i; 
    j = paramConfiguration1.screenHeightDp;
    i = paramConfiguration2.screenHeightDp;
    if (j != i)
      configuration.screenHeightDp = i; 
    i = paramConfiguration1.smallestScreenWidthDp;
    j = paramConfiguration2.smallestScreenWidthDp;
    if (i != j)
      configuration.smallestScreenWidthDp = j; 
    i = paramConfiguration1.densityDpi;
    j = paramConfiguration2.densityDpi;
    if (i != j)
      configuration.densityDpi = j; 
    i = paramConfiguration1.assetsSeq;
    j = paramConfiguration2.assetsSeq;
    if (i != j)
      configuration.assetsSeq = j; 
    if (!paramConfiguration1.windowConfiguration.equals(paramConfiguration2.windowConfiguration))
      configuration.windowConfiguration.setTo(paramConfiguration2.windowConfiguration); 
    return configuration;
  }
  
  private static int getScreenLayoutNoDirection(int paramInt) {
    return paramInt & 0xFFFFFF3F;
  }
  
  public static String localesToResourceQualifier(LocaleList paramLocaleList) {
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b = 0; b < paramLocaleList.size(); b++) {
      Locale locale = paramLocaleList.get(b);
      int i = locale.getLanguage().length();
      if (i != 0) {
        int j = locale.getScript().length();
        int k = locale.getCountry().length();
        int m = locale.getVariant().length();
        if (stringBuilder.length() != 0)
          stringBuilder.append(","); 
        if (i == 2 && j == 0 && (k == 0 || k == 2) && m == 0) {
          stringBuilder.append(locale.getLanguage());
          if (k == 2) {
            stringBuilder.append("-r");
            stringBuilder.append(locale.getCountry());
          } 
        } else {
          stringBuilder.append("b+");
          stringBuilder.append(locale.getLanguage());
          if (j != 0) {
            stringBuilder.append("+");
            stringBuilder.append(locale.getScript());
          } 
          if (k != 0) {
            stringBuilder.append("+");
            stringBuilder.append(locale.getCountry());
          } 
          if (m != 0) {
            stringBuilder.append("+");
            stringBuilder.append(locale.getVariant());
          } 
        } 
      } 
    } 
    return stringBuilder.toString();
  }
  
  public static boolean needNewResources(int paramInt1, int paramInt2) {
    boolean bool;
    if ((paramInt1 & (Integer.MIN_VALUE | paramInt2 | 0x40000000)) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static void readXmlAttrs(XmlPullParser paramXmlPullParser, Configuration paramConfiguration) throws XmlPullParserException, IOException {
    paramConfiguration.fontScale = Float.intBitsToFloat(XmlUtils.readIntAttribute(paramXmlPullParser, "fs", 0));
    paramConfiguration.mcc = XmlUtils.readIntAttribute(paramXmlPullParser, "mcc", 0);
    paramConfiguration.mnc = XmlUtils.readIntAttribute(paramXmlPullParser, "mnc", 0);
    LocaleList localeList = LocaleList.forLanguageTags(XmlUtils.readStringAttribute(paramXmlPullParser, "locales"));
    paramConfiguration.mLocaleList = localeList;
    paramConfiguration.locale = localeList.get(0);
    paramConfiguration.touchscreen = XmlUtils.readIntAttribute(paramXmlPullParser, "touch", 0);
    paramConfiguration.keyboard = XmlUtils.readIntAttribute(paramXmlPullParser, "key", 0);
    paramConfiguration.keyboardHidden = XmlUtils.readIntAttribute(paramXmlPullParser, "keyHid", 0);
    paramConfiguration.hardKeyboardHidden = XmlUtils.readIntAttribute(paramXmlPullParser, "hardKeyHid", 0);
    paramConfiguration.navigation = XmlUtils.readIntAttribute(paramXmlPullParser, "nav", 0);
    paramConfiguration.navigationHidden = XmlUtils.readIntAttribute(paramXmlPullParser, "navHid", 0);
    paramConfiguration.orientation = XmlUtils.readIntAttribute(paramXmlPullParser, "ori", 0);
    paramConfiguration.screenLayout = XmlUtils.readIntAttribute(paramXmlPullParser, "scrLay", 0);
    paramConfiguration.colorMode = XmlUtils.readIntAttribute(paramXmlPullParser, "clrMod", 0);
    paramConfiguration.uiMode = XmlUtils.readIntAttribute(paramXmlPullParser, "ui", 0);
    paramConfiguration.screenWidthDp = XmlUtils.readIntAttribute(paramXmlPullParser, "width", 0);
    paramConfiguration.screenHeightDp = XmlUtils.readIntAttribute(paramXmlPullParser, "height", 0);
    paramConfiguration.smallestScreenWidthDp = XmlUtils.readIntAttribute(paramXmlPullParser, "sw", 0);
    paramConfiguration.densityDpi = XmlUtils.readIntAttribute(paramXmlPullParser, "density", 0);
  }
  
  public static int reduceScreenLayout(int paramInt1, int paramInt2, int paramInt3) {
    byte b;
    boolean bool;
    if (paramInt2 < 470) {
      b = 1;
      paramInt2 = 0;
      bool = false;
    } else {
      if (paramInt2 >= 960 && paramInt3 >= 720) {
        b = 4;
      } else if (paramInt2 >= 640 && paramInt3 >= 480) {
        b = 3;
      } else {
        b = 2;
      } 
      if (paramInt3 > 321 || paramInt2 > 570) {
        bool = true;
      } else {
        bool = false;
      } 
      if (paramInt2 * 3 / 5 >= paramInt3 - 1) {
        paramInt2 = 1;
      } else {
        paramInt2 = 0;
      } 
    } 
    paramInt3 = paramInt1;
    if (paramInt2 == 0)
      paramInt3 = paramInt1 & 0xFFFFFFCF | 0x10; 
    paramInt1 = paramInt3;
    if (bool)
      paramInt1 = paramInt3 | 0x10000000; 
    paramInt2 = paramInt1;
    if (b < (paramInt1 & 0xF))
      paramInt2 = paramInt1 & 0xFFFFFFF0 | b; 
    return paramInt2;
  }
  
  public static int resetScreenLayout(int paramInt) {
    return 0xEFFFFFC0 & paramInt | 0x24;
  }
  
  public static String resourceQualifierString(Configuration paramConfiguration) {
    return resourceQualifierString(paramConfiguration, null);
  }
  
  public static String resourceQualifierString(Configuration paramConfiguration, DisplayMetrics paramDisplayMetrics) {
    ArrayList<String> arrayList = new ArrayList();
    if (paramConfiguration.mcc != 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("mcc");
      stringBuilder1.append(paramConfiguration.mcc);
      arrayList.add(stringBuilder1.toString());
      if (paramConfiguration.mnc != 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("mnc");
        stringBuilder1.append(paramConfiguration.mnc);
        arrayList.add(stringBuilder1.toString());
      } 
    } 
    if (!paramConfiguration.mLocaleList.isEmpty()) {
      String str = localesToResourceQualifier(paramConfiguration.mLocaleList);
      if (!str.isEmpty())
        arrayList.add(str); 
    } 
    int i = paramConfiguration.screenLayout & 0xC0;
    if (i != 64) {
      if (i == 128)
        arrayList.add("ldrtl"); 
    } else {
      arrayList.add("ldltr");
    } 
    if (paramConfiguration.smallestScreenWidthDp != 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("sw");
      stringBuilder1.append(paramConfiguration.smallestScreenWidthDp);
      stringBuilder1.append("dp");
      arrayList.add(stringBuilder1.toString());
    } 
    if (paramConfiguration.screenWidthDp != 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("w");
      stringBuilder1.append(paramConfiguration.screenWidthDp);
      stringBuilder1.append("dp");
      arrayList.add(stringBuilder1.toString());
    } 
    if (paramConfiguration.screenHeightDp != 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("h");
      stringBuilder1.append(paramConfiguration.screenHeightDp);
      stringBuilder1.append("dp");
      arrayList.add(stringBuilder1.toString());
    } 
    i = paramConfiguration.screenLayout & 0xF;
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i == 4)
            arrayList.add("xlarge"); 
        } else {
          arrayList.add("large");
        } 
      } else {
        arrayList.add("normal");
      } 
    } else {
      arrayList.add("small");
    } 
    i = paramConfiguration.screenLayout & 0x30;
    if (i != 16) {
      if (i == 32)
        arrayList.add("long"); 
    } else {
      arrayList.add("notlong");
    } 
    i = paramConfiguration.screenLayout & 0x300;
    if (i != 256) {
      if (i == 512)
        arrayList.add("round"); 
    } else {
      arrayList.add("notround");
    } 
    i = paramConfiguration.colorMode & 0x3;
    if (i != 1) {
      if (i == 2)
        arrayList.add("widecg"); 
    } else {
      arrayList.add("nowidecg");
    } 
    i = paramConfiguration.colorMode & 0xC;
    if (i != 4) {
      if (i == 8)
        arrayList.add("highdr"); 
    } else {
      arrayList.add("lowdr");
    } 
    i = paramConfiguration.orientation;
    if (i != 1) {
      if (i == 2)
        arrayList.add("land"); 
    } else {
      arrayList.add("port");
    } 
    switch (paramConfiguration.uiMode & 0xF) {
      case 7:
        arrayList.add("vrheadset");
        break;
      case 6:
        arrayList.add("watch");
        break;
      case 5:
        arrayList.add("appliance");
        break;
      case 4:
        arrayList.add("television");
        break;
      case 3:
        arrayList.add("car");
        break;
      case 2:
        arrayList.add("desk");
        break;
    } 
    i = paramConfiguration.uiMode & 0x30;
    if (i != 16) {
      if (i == 32)
        arrayList.add("night"); 
    } else {
      arrayList.add("notnight");
    } 
    i = paramConfiguration.densityDpi;
    if (i != 0)
      if (i != 120) {
        if (i != 160) {
          if (i != 213) {
            if (i != 240) {
              if (i != 320) {
                if (i != 480) {
                  if (i != 640) {
                    StringBuilder stringBuilder1;
                    switch (i) {
                      default:
                        stringBuilder1 = new StringBuilder();
                        stringBuilder1.append(paramConfiguration.densityDpi);
                        stringBuilder1.append("dpi");
                        arrayList.add(stringBuilder1.toString());
                        break;
                      case 65535:
                        arrayList.add("nodpi");
                        break;
                      case 65534:
                        arrayList.add("anydpi");
                        break;
                    } 
                  } else {
                    arrayList.add("xxxhdpi");
                  } 
                } else {
                  arrayList.add("xxhdpi");
                } 
              } else {
                arrayList.add("xhdpi");
              } 
            } else {
              arrayList.add("hdpi");
            } 
          } else {
            arrayList.add("tvdpi");
          } 
        } else {
          arrayList.add("mdpi");
        } 
      } else {
        arrayList.add("ldpi");
      }  
    i = paramConfiguration.touchscreen;
    if (i != 1) {
      if (i == 3)
        arrayList.add("finger"); 
    } else {
      arrayList.add("notouch");
    } 
    i = paramConfiguration.keyboardHidden;
    if (i != 1) {
      if (i != 2) {
        if (i == 3)
          arrayList.add("keyssoft"); 
      } else {
        arrayList.add("keyshidden");
      } 
    } else {
      arrayList.add("keysexposed");
    } 
    i = paramConfiguration.keyboard;
    if (i != 1) {
      if (i != 2) {
        if (i == 3)
          arrayList.add("12key"); 
      } else {
        arrayList.add("qwerty");
      } 
    } else {
      arrayList.add("nokeys");
    } 
    i = paramConfiguration.navigationHidden;
    if (i != 1) {
      if (i == 2)
        arrayList.add("navhidden"); 
    } else {
      arrayList.add("navexposed");
    } 
    i = paramConfiguration.navigation;
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i == 4)
            arrayList.add("wheel"); 
        } else {
          arrayList.add("trackball");
        } 
      } else {
        arrayList.add("dpad");
      } 
    } else {
      arrayList.add("nonav");
    } 
    if (paramDisplayMetrics != null) {
      int j;
      if (paramDisplayMetrics.widthPixels >= paramDisplayMetrics.heightPixels) {
        i = paramDisplayMetrics.widthPixels;
        j = paramDisplayMetrics.heightPixels;
      } else {
        i = paramDisplayMetrics.heightPixels;
        j = paramDisplayMetrics.widthPixels;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(i);
      stringBuilder1.append("x");
      stringBuilder1.append(j);
      arrayList.add(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("v");
    stringBuilder.append(Build.VERSION.RESOURCES_SDK_INT);
    arrayList.add(stringBuilder.toString());
    return TextUtils.join("-", arrayList);
  }
  
  public static String uiModeToString(int paramInt) {
    switch (paramInt) {
      default:
        return Integer.toString(paramInt);
      case 7:
        return "UI_MODE_TYPE_VR_HEADSET";
      case 6:
        return "UI_MODE_TYPE_WATCH";
      case 5:
        return "UI_MODE_TYPE_APPLIANCE";
      case 4:
        return "UI_MODE_TYPE_TELEVISION";
      case 3:
        return "UI_MODE_TYPE_CAR";
      case 2:
        return "UI_MODE_TYPE_DESK";
      case 1:
        return "UI_MODE_TYPE_NORMAL";
      case 0:
        break;
    } 
    return "UI_MODE_TYPE_UNDEFINED";
  }
  
  public void clearLocales() {
    this.mLocaleList = LocaleList.getEmptyLocaleList();
    this.locale = null;
  }
  
  public int compareTo(Configuration paramConfiguration) {
    float f1 = this.fontScale;
    float f2 = paramConfiguration.fontScale;
    if (f1 < f2)
      return -1; 
    if (f1 > f2)
      return 1; 
    int i = this.mcc - paramConfiguration.mcc;
    if (i != 0)
      return i; 
    i = this.mnc - paramConfiguration.mnc;
    if (i != 0)
      return i; 
    fixUpLocaleList();
    paramConfiguration.fixUpLocaleList();
    if (this.mLocaleList.isEmpty()) {
      if (!paramConfiguration.mLocaleList.isEmpty())
        return 1; 
    } else {
      if (paramConfiguration.mLocaleList.isEmpty())
        return -1; 
      int j = Math.min(this.mLocaleList.size(), paramConfiguration.mLocaleList.size());
      for (i = 0; i < j; i++) {
        Locale locale1 = this.mLocaleList.get(i);
        Locale locale2 = paramConfiguration.mLocaleList.get(i);
        int k = locale1.getLanguage().compareTo(locale2.getLanguage());
        if (k != 0)
          return k; 
        k = locale1.getCountry().compareTo(locale2.getCountry());
        if (k != 0)
          return k; 
        k = locale1.getVariant().compareTo(locale2.getVariant());
        if (k != 0)
          return k; 
        k = locale1.toLanguageTag().compareTo(locale2.toLanguageTag());
        if (k != 0)
          return k; 
      } 
      i = this.mLocaleList.size() - paramConfiguration.mLocaleList.size();
      if (i != 0)
        return i; 
    } 
    i = this.touchscreen - paramConfiguration.touchscreen;
    if (i != 0)
      return i; 
    i = this.keyboard - paramConfiguration.keyboard;
    if (i != 0)
      return i; 
    i = this.keyboardHidden - paramConfiguration.keyboardHidden;
    if (i != 0)
      return i; 
    i = this.hardKeyboardHidden - paramConfiguration.hardKeyboardHidden;
    if (i != 0)
      return i; 
    i = this.navigation - paramConfiguration.navigation;
    if (i != 0)
      return i; 
    i = this.navigationHidden - paramConfiguration.navigationHidden;
    if (i != 0)
      return i; 
    i = this.orientation - paramConfiguration.orientation;
    if (i != 0)
      return i; 
    i = this.colorMode - paramConfiguration.colorMode;
    if (i != 0)
      return i; 
    i = this.screenLayout - paramConfiguration.screenLayout;
    if (i != 0)
      return i; 
    i = this.uiMode - paramConfiguration.uiMode;
    if (i != 0)
      return i; 
    i = this.screenWidthDp - paramConfiguration.screenWidthDp;
    if (i != 0)
      return i; 
    i = this.screenHeightDp - paramConfiguration.screenHeightDp;
    if (i != 0)
      return i; 
    i = this.smallestScreenWidthDp - paramConfiguration.smallestScreenWidthDp;
    if (i != 0)
      return i; 
    i = this.densityDpi - paramConfiguration.densityDpi;
    if (i != 0)
      return i; 
    i = this.assetsSeq - paramConfiguration.assetsSeq;
    if (i != 0)
      return i; 
    i = this.windowConfiguration.compareTo(paramConfiguration.windowConfiguration);
    return (i != 0) ? i : i;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int diff(Configuration paramConfiguration) {
    return diff(paramConfiguration, false, false);
  }
  
  public int diff(Configuration paramConfiguration, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: iload_2
    //   4: ifne -> 20
    //   7: iload #4
    //   9: istore #5
    //   11: aload_1
    //   12: getfield fontScale : F
    //   15: fconst_0
    //   16: fcmpl
    //   17: ifle -> 43
    //   20: iload #4
    //   22: istore #5
    //   24: aload_0
    //   25: getfield fontScale : F
    //   28: aload_1
    //   29: getfield fontScale : F
    //   32: fcmpl
    //   33: ifeq -> 43
    //   36: iconst_0
    //   37: ldc_w 1073741824
    //   40: ior
    //   41: istore #5
    //   43: iload_2
    //   44: ifne -> 58
    //   47: iload #5
    //   49: istore #4
    //   51: aload_1
    //   52: getfield mcc : I
    //   55: ifeq -> 79
    //   58: iload #5
    //   60: istore #4
    //   62: aload_0
    //   63: getfield mcc : I
    //   66: aload_1
    //   67: getfield mcc : I
    //   70: if_icmpeq -> 79
    //   73: iload #5
    //   75: iconst_1
    //   76: ior
    //   77: istore #4
    //   79: iload_2
    //   80: ifne -> 94
    //   83: iload #4
    //   85: istore #5
    //   87: aload_1
    //   88: getfield mnc : I
    //   91: ifeq -> 115
    //   94: iload #4
    //   96: istore #5
    //   98: aload_0
    //   99: getfield mnc : I
    //   102: aload_1
    //   103: getfield mnc : I
    //   106: if_icmpeq -> 115
    //   109: iload #4
    //   111: iconst_2
    //   112: ior
    //   113: istore #5
    //   115: aload_0
    //   116: invokespecial fixUpLocaleList : ()V
    //   119: aload_1
    //   120: invokespecial fixUpLocaleList : ()V
    //   123: iload_2
    //   124: ifne -> 141
    //   127: iload #5
    //   129: istore #4
    //   131: aload_1
    //   132: getfield mLocaleList : Landroid/os/LocaleList;
    //   135: invokevirtual isEmpty : ()Z
    //   138: ifne -> 169
    //   141: iload #5
    //   143: istore #4
    //   145: aload_0
    //   146: getfield mLocaleList : Landroid/os/LocaleList;
    //   149: aload_1
    //   150: getfield mLocaleList : Landroid/os/LocaleList;
    //   153: invokevirtual equals : (Ljava/lang/Object;)Z
    //   156: ifne -> 169
    //   159: iload #5
    //   161: iconst_4
    //   162: ior
    //   163: sipush #8192
    //   166: ior
    //   167: istore #4
    //   169: aload_1
    //   170: getfield screenLayout : I
    //   173: sipush #192
    //   176: iand
    //   177: istore #6
    //   179: iload_2
    //   180: ifne -> 192
    //   183: iload #4
    //   185: istore #5
    //   187: iload #6
    //   189: ifeq -> 217
    //   192: iload #4
    //   194: istore #5
    //   196: iload #6
    //   198: aload_0
    //   199: getfield screenLayout : I
    //   202: sipush #192
    //   205: iand
    //   206: if_icmpeq -> 217
    //   209: iload #4
    //   211: sipush #8192
    //   214: ior
    //   215: istore #5
    //   217: iload_2
    //   218: ifne -> 232
    //   221: iload #5
    //   223: istore #4
    //   225: aload_1
    //   226: getfield touchscreen : I
    //   229: ifeq -> 254
    //   232: iload #5
    //   234: istore #4
    //   236: aload_0
    //   237: getfield touchscreen : I
    //   240: aload_1
    //   241: getfield touchscreen : I
    //   244: if_icmpeq -> 254
    //   247: iload #5
    //   249: bipush #8
    //   251: ior
    //   252: istore #4
    //   254: iload_2
    //   255: ifne -> 269
    //   258: iload #4
    //   260: istore #5
    //   262: aload_1
    //   263: getfield keyboard : I
    //   266: ifeq -> 291
    //   269: iload #4
    //   271: istore #5
    //   273: aload_0
    //   274: getfield keyboard : I
    //   277: aload_1
    //   278: getfield keyboard : I
    //   281: if_icmpeq -> 291
    //   284: iload #4
    //   286: bipush #16
    //   288: ior
    //   289: istore #5
    //   291: iload_2
    //   292: ifne -> 306
    //   295: iload #5
    //   297: istore #4
    //   299: aload_1
    //   300: getfield keyboardHidden : I
    //   303: ifeq -> 328
    //   306: iload #5
    //   308: istore #4
    //   310: aload_0
    //   311: getfield keyboardHidden : I
    //   314: aload_1
    //   315: getfield keyboardHidden : I
    //   318: if_icmpeq -> 328
    //   321: iload #5
    //   323: bipush #32
    //   325: ior
    //   326: istore #4
    //   328: iload_2
    //   329: ifne -> 343
    //   332: iload #4
    //   334: istore #6
    //   336: aload_1
    //   337: getfield hardKeyboardHidden : I
    //   340: ifeq -> 365
    //   343: iload #4
    //   345: istore #6
    //   347: aload_0
    //   348: getfield hardKeyboardHidden : I
    //   351: aload_1
    //   352: getfield hardKeyboardHidden : I
    //   355: if_icmpeq -> 365
    //   358: iload #4
    //   360: bipush #32
    //   362: ior
    //   363: istore #6
    //   365: iload_2
    //   366: ifne -> 380
    //   369: iload #6
    //   371: istore #5
    //   373: aload_1
    //   374: getfield navigation : I
    //   377: ifeq -> 402
    //   380: iload #6
    //   382: istore #5
    //   384: aload_0
    //   385: getfield navigation : I
    //   388: aload_1
    //   389: getfield navigation : I
    //   392: if_icmpeq -> 402
    //   395: iload #6
    //   397: bipush #64
    //   399: ior
    //   400: istore #5
    //   402: iload_2
    //   403: ifne -> 417
    //   406: iload #5
    //   408: istore #4
    //   410: aload_1
    //   411: getfield navigationHidden : I
    //   414: ifeq -> 439
    //   417: iload #5
    //   419: istore #4
    //   421: aload_0
    //   422: getfield navigationHidden : I
    //   425: aload_1
    //   426: getfield navigationHidden : I
    //   429: if_icmpeq -> 439
    //   432: iload #5
    //   434: bipush #32
    //   436: ior
    //   437: istore #4
    //   439: iload_2
    //   440: ifne -> 454
    //   443: iload #4
    //   445: istore #5
    //   447: aload_1
    //   448: getfield orientation : I
    //   451: ifeq -> 477
    //   454: iload #4
    //   456: istore #5
    //   458: aload_0
    //   459: getfield orientation : I
    //   462: aload_1
    //   463: getfield orientation : I
    //   466: if_icmpeq -> 477
    //   469: iload #4
    //   471: sipush #128
    //   474: ior
    //   475: istore #5
    //   477: iload_2
    //   478: ifne -> 495
    //   481: iload #5
    //   483: istore #4
    //   485: aload_1
    //   486: getfield screenLayout : I
    //   489: invokestatic getScreenLayoutNoDirection : (I)I
    //   492: ifeq -> 524
    //   495: iload #5
    //   497: istore #4
    //   499: aload_0
    //   500: getfield screenLayout : I
    //   503: invokestatic getScreenLayoutNoDirection : (I)I
    //   506: aload_1
    //   507: getfield screenLayout : I
    //   510: invokestatic getScreenLayoutNoDirection : (I)I
    //   513: if_icmpeq -> 524
    //   516: iload #5
    //   518: sipush #256
    //   521: ior
    //   522: istore #4
    //   524: iload_2
    //   525: ifne -> 542
    //   528: iload #4
    //   530: istore #5
    //   532: aload_1
    //   533: getfield colorMode : I
    //   536: bipush #12
    //   538: iand
    //   539: ifeq -> 571
    //   542: iload #4
    //   544: istore #5
    //   546: aload_0
    //   547: getfield colorMode : I
    //   550: bipush #12
    //   552: iand
    //   553: aload_1
    //   554: getfield colorMode : I
    //   557: bipush #12
    //   559: iand
    //   560: if_icmpeq -> 571
    //   563: iload #4
    //   565: sipush #16384
    //   568: ior
    //   569: istore #5
    //   571: iload_2
    //   572: ifne -> 588
    //   575: iload #5
    //   577: istore #4
    //   579: aload_1
    //   580: getfield colorMode : I
    //   583: iconst_3
    //   584: iand
    //   585: ifeq -> 615
    //   588: iload #5
    //   590: istore #4
    //   592: aload_0
    //   593: getfield colorMode : I
    //   596: iconst_3
    //   597: iand
    //   598: aload_1
    //   599: getfield colorMode : I
    //   602: iconst_3
    //   603: iand
    //   604: if_icmpeq -> 615
    //   607: iload #5
    //   609: sipush #16384
    //   612: ior
    //   613: istore #4
    //   615: iload_2
    //   616: ifne -> 630
    //   619: iload #4
    //   621: istore #5
    //   623: aload_1
    //   624: getfield uiMode : I
    //   627: ifeq -> 653
    //   630: iload #4
    //   632: istore #5
    //   634: aload_0
    //   635: getfield uiMode : I
    //   638: aload_1
    //   639: getfield uiMode : I
    //   642: if_icmpeq -> 653
    //   645: iload #4
    //   647: sipush #512
    //   650: ior
    //   651: istore #5
    //   653: iload_2
    //   654: ifne -> 668
    //   657: iload #5
    //   659: istore #4
    //   661: aload_1
    //   662: getfield screenWidthDp : I
    //   665: ifeq -> 691
    //   668: iload #5
    //   670: istore #4
    //   672: aload_0
    //   673: getfield screenWidthDp : I
    //   676: aload_1
    //   677: getfield screenWidthDp : I
    //   680: if_icmpeq -> 691
    //   683: iload #5
    //   685: sipush #1024
    //   688: ior
    //   689: istore #4
    //   691: iload_2
    //   692: ifne -> 706
    //   695: iload #4
    //   697: istore #5
    //   699: aload_1
    //   700: getfield screenHeightDp : I
    //   703: ifeq -> 729
    //   706: iload #4
    //   708: istore #5
    //   710: aload_0
    //   711: getfield screenHeightDp : I
    //   714: aload_1
    //   715: getfield screenHeightDp : I
    //   718: if_icmpeq -> 729
    //   721: iload #4
    //   723: sipush #1024
    //   726: ior
    //   727: istore #5
    //   729: iload_2
    //   730: ifne -> 744
    //   733: iload #5
    //   735: istore #4
    //   737: aload_1
    //   738: getfield smallestScreenWidthDp : I
    //   741: ifeq -> 767
    //   744: iload #5
    //   746: istore #4
    //   748: aload_0
    //   749: getfield smallestScreenWidthDp : I
    //   752: aload_1
    //   753: getfield smallestScreenWidthDp : I
    //   756: if_icmpeq -> 767
    //   759: iload #5
    //   761: sipush #2048
    //   764: ior
    //   765: istore #4
    //   767: iload_2
    //   768: ifne -> 782
    //   771: iload #4
    //   773: istore #5
    //   775: aload_1
    //   776: getfield densityDpi : I
    //   779: ifeq -> 805
    //   782: iload #4
    //   784: istore #5
    //   786: aload_0
    //   787: getfield densityDpi : I
    //   790: aload_1
    //   791: getfield densityDpi : I
    //   794: if_icmpeq -> 805
    //   797: iload #4
    //   799: sipush #4096
    //   802: ior
    //   803: istore #5
    //   805: iload_2
    //   806: ifne -> 820
    //   809: iload #5
    //   811: istore #4
    //   813: aload_1
    //   814: getfield assetsSeq : I
    //   817: ifeq -> 843
    //   820: iload #5
    //   822: istore #4
    //   824: aload_0
    //   825: getfield assetsSeq : I
    //   828: aload_1
    //   829: getfield assetsSeq : I
    //   832: if_icmpeq -> 843
    //   835: iload #5
    //   837: ldc_w -2147483648
    //   840: ior
    //   841: istore #4
    //   843: iload #4
    //   845: istore #5
    //   847: iload_3
    //   848: ifne -> 880
    //   851: iload #4
    //   853: istore #5
    //   855: aload_0
    //   856: getfield windowConfiguration : Landroid/app/WindowConfiguration;
    //   859: aload_1
    //   860: getfield windowConfiguration : Landroid/app/WindowConfiguration;
    //   863: iload_2
    //   864: invokevirtual diff : (Landroid/app/WindowConfiguration;Z)J
    //   867: lconst_0
    //   868: lcmp
    //   869: ifeq -> 880
    //   872: iload #4
    //   874: ldc_w 536870912
    //   877: ior
    //   878: istore #5
    //   880: iload #5
    //   882: ireturn
  }
  
  public int diffPublicOnly(Configuration paramConfiguration) {
    return diff(paramConfiguration, false, true);
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    dumpDebug(paramProtoOutputStream, paramLong, false, false);
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong, boolean paramBoolean) {
    dumpDebug(paramProtoOutputStream, paramLong, false, paramBoolean);
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong, boolean paramBoolean1, boolean paramBoolean2) {
    paramLong = paramProtoOutputStream.start(paramLong);
    if (!paramBoolean2) {
      paramProtoOutputStream.write(1108101562369L, this.fontScale);
      paramProtoOutputStream.write(1155346202626L, this.mcc);
      paramProtoOutputStream.write(1155346202627L, this.mnc);
      LocaleList localeList = this.mLocaleList;
      if (localeList != null)
        paramProtoOutputStream.write(1138166333460L, localeList.toLanguageTags()); 
      paramProtoOutputStream.write(1155346202629L, this.screenLayout);
      paramProtoOutputStream.write(1155346202630L, this.colorMode);
      paramProtoOutputStream.write(1155346202631L, this.touchscreen);
      paramProtoOutputStream.write(1155346202632L, this.keyboard);
      paramProtoOutputStream.write(1155346202633L, this.keyboardHidden);
      paramProtoOutputStream.write(1155346202634L, this.hardKeyboardHidden);
      paramProtoOutputStream.write(1155346202635L, this.navigation);
      paramProtoOutputStream.write(1155346202636L, this.navigationHidden);
      paramProtoOutputStream.write(1155346202638L, this.uiMode);
      paramProtoOutputStream.write(1155346202641L, this.smallestScreenWidthDp);
      paramProtoOutputStream.write(1155346202642L, this.densityDpi);
      if (!paramBoolean1) {
        WindowConfiguration windowConfiguration = this.windowConfiguration;
        if (windowConfiguration != null)
          windowConfiguration.dumpDebug(paramProtoOutputStream, 1146756268051L); 
      } 
    } 
    paramProtoOutputStream.write(1155346202637L, this.orientation);
    paramProtoOutputStream.write(1155346202639L, this.screenWidthDp);
    paramProtoOutputStream.write(1155346202640L, this.screenHeightDp);
    paramProtoOutputStream.end(paramLong);
  }
  
  public boolean equals(Configuration paramConfiguration) {
    boolean bool = false;
    if (paramConfiguration == null)
      return false; 
    if (paramConfiguration == this)
      return true; 
    if (compareTo(paramConfiguration) == 0)
      bool = true; 
    return bool;
  }
  
  public boolean equals(Object paramObject) {
    try {
      return equals((Configuration)paramObject);
    } catch (ClassCastException classCastException) {
      return false;
    } 
  }
  
  public int getLayoutDirection() {
    boolean bool;
    if ((this.screenLayout & 0xC0) == 128) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public LocaleList getLocales() {
    fixUpLocaleList();
    return this.mLocaleList;
  }
  
  public int hashCode() {
    return ((((((((((((((((((17 * 31 + Float.floatToIntBits(this.fontScale)) * 31 + this.mcc) * 31 + this.mnc) * 31 + this.mLocaleList.hashCode()) * 31 + this.touchscreen) * 31 + this.keyboard) * 31 + this.keyboardHidden) * 31 + this.hardKeyboardHidden) * 31 + this.navigation) * 31 + this.navigationHidden) * 31 + this.orientation) * 31 + this.screenLayout) * 31 + this.colorMode) * 31 + this.uiMode) * 31 + this.screenWidthDp) * 31 + this.screenHeightDp) * 31 + this.smallestScreenWidthDp) * 31 + this.densityDpi) * 31 + this.assetsSeq;
  }
  
  public boolean isLayoutSizeAtLeast(int paramInt) {
    int i = this.screenLayout & 0xF;
    boolean bool = false;
    if (i == 0)
      return false; 
    if (i >= paramInt)
      bool = true; 
    return bool;
  }
  
  public boolean isNightModeActive() {
    boolean bool;
    if ((this.uiMode & 0x30) == 32) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isOtherSeqNewer(Configuration paramConfiguration) {
    boolean bool = false;
    if (paramConfiguration == null)
      return false; 
    int i = paramConfiguration.seq;
    if (i == 0)
      return true; 
    int j = this.seq;
    if (j == 0)
      return true; 
    i -= j;
    if (i > 65536)
      return false; 
    if (i > 0)
      bool = true; 
    return bool;
  }
  
  public boolean isScreenHdr() {
    boolean bool;
    if ((this.colorMode & 0xC) == 8) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isScreenRound() {
    boolean bool;
    if ((this.screenLayout & 0x300) == 512) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isScreenWideColorGamut() {
    boolean bool;
    if ((this.colorMode & 0x3) == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public void makeDefault() {
    setToDefaults();
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.fontScale = paramParcel.readFloat();
    this.mcc = paramParcel.readInt();
    this.mnc = paramParcel.readInt();
    LocaleList localeList = (LocaleList)paramParcel.readParcelable(LocaleList.class.getClassLoader());
    this.mLocaleList = localeList;
    boolean bool = false;
    this.locale = localeList.get(0);
    if (paramParcel.readInt() == 1)
      bool = true; 
    this.userSetLocale = bool;
    this.touchscreen = paramParcel.readInt();
    this.keyboard = paramParcel.readInt();
    this.keyboardHidden = paramParcel.readInt();
    this.hardKeyboardHidden = paramParcel.readInt();
    this.navigation = paramParcel.readInt();
    this.navigationHidden = paramParcel.readInt();
    this.orientation = paramParcel.readInt();
    this.screenLayout = paramParcel.readInt();
    this.colorMode = paramParcel.readInt();
    this.uiMode = paramParcel.readInt();
    this.screenWidthDp = paramParcel.readInt();
    this.screenHeightDp = paramParcel.readInt();
    this.smallestScreenWidthDp = paramParcel.readInt();
    this.densityDpi = paramParcel.readInt();
    this.compatScreenWidthDp = paramParcel.readInt();
    this.compatScreenHeightDp = paramParcel.readInt();
    this.compatSmallestScreenWidthDp = paramParcel.readInt();
    this.windowConfiguration.setTo((WindowConfiguration)paramParcel.readValue(null));
    this.assetsSeq = paramParcel.readInt();
    this.seq = paramParcel.readInt();
  }
  
  public void readFromProto(ProtoInputStream paramProtoInputStream, long paramLong) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: astore #4
    //   3: aload_1
    //   4: astore #5
    //   6: ldc_w ';variant-'
    //   9: astore #6
    //   11: ldc_w ';country-'
    //   14: astore #7
    //   16: ldc_w ''
    //   19: astore #8
    //   21: aload_1
    //   22: lload_2
    //   23: invokevirtual start : (J)J
    //   26: lstore_2
    //   27: new java/util/ArrayList
    //   30: dup
    //   31: invokespecial <init> : ()V
    //   34: astore #9
    //   36: aload_1
    //   37: invokevirtual nextField : ()I
    //   40: istore #10
    //   42: iload #10
    //   44: iconst_m1
    //   45: if_icmpeq -> 1353
    //   48: aload_1
    //   49: invokevirtual getFieldNumber : ()I
    //   52: istore #10
    //   54: iload #10
    //   56: tableswitch default -> 152, 1 -> 1306, 2 -> 1278, 3 -> 1228, 4 -> 441, 5 -> 417, 6 -> 401, 7 -> 385, 8 -> 369, 9 -> 353, 10 -> 337, 11 -> 321, 12 -> 305, 13 -> 289, 14 -> 273, 15 -> 257, 16 -> 241, 17 -> 225, 18 -> 209, 19 -> 193, 20 -> 155
    //   152: goto -> 1342
    //   155: lload_2
    //   156: lstore #11
    //   158: aload #4
    //   160: aload #5
    //   162: ldc2_w 1138166333460
    //   165: invokevirtual readString : (J)Ljava/lang/String;
    //   168: invokestatic forLanguageTags : (Ljava/lang/String;)Landroid/os/LocaleList;
    //   171: invokevirtual setLocales : (Landroid/os/LocaleList;)V
    //   174: goto -> 1342
    //   177: astore #13
    //   179: ldc 'Configuration'
    //   181: ldc_w 'error parsing locale list in configuration.'
    //   184: aload #13
    //   186: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   189: pop
    //   190: goto -> 1342
    //   193: aload #4
    //   195: getfield windowConfiguration : Landroid/app/WindowConfiguration;
    //   198: aload #5
    //   200: ldc2_w 1146756268051
    //   203: invokevirtual readFromProto : (Landroid/util/proto/ProtoInputStream;J)V
    //   206: goto -> 1342
    //   209: aload #4
    //   211: aload #5
    //   213: ldc2_w 1155346202642
    //   216: invokevirtual readInt : (J)I
    //   219: putfield densityDpi : I
    //   222: goto -> 1342
    //   225: aload #4
    //   227: aload #5
    //   229: ldc2_w 1155346202641
    //   232: invokevirtual readInt : (J)I
    //   235: putfield smallestScreenWidthDp : I
    //   238: goto -> 1342
    //   241: aload #4
    //   243: aload #5
    //   245: ldc2_w 1155346202640
    //   248: invokevirtual readInt : (J)I
    //   251: putfield screenHeightDp : I
    //   254: goto -> 1342
    //   257: aload #4
    //   259: aload #5
    //   261: ldc2_w 1155346202639
    //   264: invokevirtual readInt : (J)I
    //   267: putfield screenWidthDp : I
    //   270: goto -> 1342
    //   273: aload #4
    //   275: aload #5
    //   277: ldc2_w 1155346202638
    //   280: invokevirtual readInt : (J)I
    //   283: putfield uiMode : I
    //   286: goto -> 1342
    //   289: aload #4
    //   291: aload #5
    //   293: ldc2_w 1155346202637
    //   296: invokevirtual readInt : (J)I
    //   299: putfield orientation : I
    //   302: goto -> 1342
    //   305: aload #4
    //   307: aload #5
    //   309: ldc2_w 1155346202636
    //   312: invokevirtual readInt : (J)I
    //   315: putfield navigationHidden : I
    //   318: goto -> 1342
    //   321: aload #4
    //   323: aload #5
    //   325: ldc2_w 1155346202635
    //   328: invokevirtual readInt : (J)I
    //   331: putfield navigation : I
    //   334: goto -> 1342
    //   337: aload #4
    //   339: aload #5
    //   341: ldc2_w 1155346202634
    //   344: invokevirtual readInt : (J)I
    //   347: putfield hardKeyboardHidden : I
    //   350: goto -> 1342
    //   353: aload #4
    //   355: aload #5
    //   357: ldc2_w 1155346202633
    //   360: invokevirtual readInt : (J)I
    //   363: putfield keyboardHidden : I
    //   366: goto -> 1342
    //   369: aload #4
    //   371: aload #5
    //   373: ldc2_w 1155346202632
    //   376: invokevirtual readInt : (J)I
    //   379: putfield keyboard : I
    //   382: goto -> 1342
    //   385: aload #4
    //   387: aload #5
    //   389: ldc2_w 1155346202631
    //   392: invokevirtual readInt : (J)I
    //   395: putfield touchscreen : I
    //   398: goto -> 1342
    //   401: aload #4
    //   403: aload #5
    //   405: ldc2_w 1155346202630
    //   408: invokevirtual readInt : (J)I
    //   411: putfield colorMode : I
    //   414: goto -> 1342
    //   417: aload #4
    //   419: aload #5
    //   421: ldc2_w 1155346202629
    //   424: invokevirtual readInt : (J)I
    //   427: putfield screenLayout : I
    //   430: goto -> 1342
    //   433: astore_1
    //   434: aload #4
    //   436: astore #8
    //   438: goto -> 1407
    //   441: aload #5
    //   443: ldc2_w 2246267895812
    //   446: invokevirtual start : (J)J
    //   449: lstore #11
    //   451: aload #8
    //   453: astore #14
    //   455: aload #8
    //   457: astore #15
    //   459: aload #8
    //   461: astore #4
    //   463: aload #8
    //   465: astore #13
    //   467: aload_1
    //   468: invokevirtual nextField : ()I
    //   471: istore #10
    //   473: iload #10
    //   475: iconst_m1
    //   476: if_icmpeq -> 590
    //   479: aload_1
    //   480: invokevirtual getFieldNumber : ()I
    //   483: istore #10
    //   485: iload #10
    //   487: iconst_1
    //   488: if_icmpeq -> 563
    //   491: iload #10
    //   493: iconst_2
    //   494: if_icmpeq -> 546
    //   497: iload #10
    //   499: iconst_3
    //   500: if_icmpeq -> 529
    //   503: iload #10
    //   505: iconst_4
    //   506: if_icmpeq -> 512
    //   509: goto -> 577
    //   512: aload #5
    //   514: ldc2_w 1138166333444
    //   517: invokevirtual readString : (J)Ljava/lang/String;
    //   520: astore #16
    //   522: aload #16
    //   524: astore #13
    //   526: goto -> 577
    //   529: aload #5
    //   531: ldc2_w 1138166333443
    //   534: invokevirtual readString : (J)Ljava/lang/String;
    //   537: astore #16
    //   539: aload #16
    //   541: astore #4
    //   543: goto -> 577
    //   546: aload #5
    //   548: ldc2_w 1138166333442
    //   551: invokevirtual readString : (J)Ljava/lang/String;
    //   554: astore #16
    //   556: aload #16
    //   558: astore #15
    //   560: goto -> 577
    //   563: aload #5
    //   565: ldc2_w 1138166333441
    //   568: invokevirtual readString : (J)Ljava/lang/String;
    //   571: astore #16
    //   573: aload #16
    //   575: astore #14
    //   577: goto -> 467
    //   580: astore #5
    //   582: goto -> 904
    //   585: astore #5
    //   587: goto -> 899
    //   590: aload #5
    //   592: lload #11
    //   594: invokevirtual end : (J)V
    //   597: new java/util/Locale$Builder
    //   600: astore #5
    //   602: aload #5
    //   604: invokespecial <init> : ()V
    //   607: aload #5
    //   609: aload #14
    //   611: invokevirtual setLanguage : (Ljava/lang/String;)Ljava/util/Locale$Builder;
    //   614: astore #5
    //   616: aload #5
    //   618: aload #15
    //   620: invokevirtual setRegion : (Ljava/lang/String;)Ljava/util/Locale$Builder;
    //   623: astore #5
    //   625: aload #5
    //   627: aload #4
    //   629: invokevirtual setVariant : (Ljava/lang/String;)Ljava/util/Locale$Builder;
    //   632: astore #5
    //   634: aload #5
    //   636: aload #13
    //   638: invokevirtual setScript : (Ljava/lang/String;)Ljava/util/Locale$Builder;
    //   641: invokevirtual build : ()Ljava/util/Locale;
    //   644: astore #16
    //   646: aload #9
    //   648: aload #16
    //   650: invokeinterface indexOf : (Ljava/lang/Object;)I
    //   655: istore #10
    //   657: iload #10
    //   659: iconst_m1
    //   660: if_icmpeq -> 731
    //   663: new java/lang/StringBuilder
    //   666: astore #5
    //   668: aload #5
    //   670: invokespecial <init> : ()V
    //   673: aload #5
    //   675: ldc_w 'Repeated locale ('
    //   678: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   681: pop
    //   682: aload #5
    //   684: aload #9
    //   686: iload #10
    //   688: invokeinterface get : (I)Ljava/lang/Object;
    //   693: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   696: pop
    //   697: aload #5
    //   699: ldc_w ') found when trying to add: '
    //   702: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   705: pop
    //   706: aload #5
    //   708: aload #16
    //   710: invokevirtual toString : ()Ljava/lang/String;
    //   713: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   716: pop
    //   717: ldc 'Configuration'
    //   719: aload #5
    //   721: invokevirtual toString : ()Ljava/lang/String;
    //   724: invokestatic wtf : (Ljava/lang/String;Ljava/lang/String;)I
    //   727: pop
    //   728: goto -> 741
    //   731: aload #9
    //   733: aload #16
    //   735: invokeinterface add : (Ljava/lang/Object;)Z
    //   740: pop
    //   741: goto -> 858
    //   744: astore #5
    //   746: goto -> 771
    //   749: astore #5
    //   751: goto -> 771
    //   754: astore #5
    //   756: goto -> 771
    //   759: astore #5
    //   761: goto -> 771
    //   764: astore #5
    //   766: goto -> 771
    //   769: astore #5
    //   771: new java/lang/StringBuilder
    //   774: astore #5
    //   776: aload #5
    //   778: invokespecial <init> : ()V
    //   781: aload #5
    //   783: ldc_w 'readFromProto error building locale with: language-'
    //   786: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   789: pop
    //   790: aload #5
    //   792: aload #14
    //   794: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   797: pop
    //   798: aload #5
    //   800: aload #7
    //   802: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   805: pop
    //   806: aload #5
    //   808: aload #15
    //   810: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   813: pop
    //   814: aload #5
    //   816: aload #6
    //   818: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   821: pop
    //   822: aload #5
    //   824: aload #4
    //   826: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   829: pop
    //   830: aload #5
    //   832: ldc_w ';script-'
    //   835: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   838: pop
    //   839: aload #5
    //   841: aload #13
    //   843: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   846: pop
    //   847: ldc 'Configuration'
    //   849: aload #5
    //   851: invokevirtual toString : ()Ljava/lang/String;
    //   854: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   857: pop
    //   858: aload_0
    //   859: astore #4
    //   861: aload_1
    //   862: astore #5
    //   864: goto -> 1342
    //   867: astore #4
    //   869: aload_0
    //   870: astore #8
    //   872: aload_1
    //   873: astore #5
    //   875: aload #4
    //   877: astore_1
    //   878: goto -> 1339
    //   881: astore #8
    //   883: aload_1
    //   884: astore #5
    //   886: aload #8
    //   888: astore_1
    //   889: goto -> 1222
    //   892: astore #5
    //   894: goto -> 904
    //   897: astore #5
    //   899: aload #5
    //   901: athrow
    //   902: astore #5
    //   904: aload #4
    //   906: astore #8
    //   908: aload_1
    //   909: astore #4
    //   911: aload_1
    //   912: lload #11
    //   914: invokevirtual end : (J)V
    //   917: aload_1
    //   918: astore #4
    //   920: new java/util/Locale$Builder
    //   923: astore #16
    //   925: aload_1
    //   926: astore #4
    //   928: aload #16
    //   930: invokespecial <init> : ()V
    //   933: aload_1
    //   934: astore #4
    //   936: aload #16
    //   938: aload #14
    //   940: invokevirtual setLanguage : (Ljava/lang/String;)Ljava/util/Locale$Builder;
    //   943: aload #15
    //   945: invokevirtual setRegion : (Ljava/lang/String;)Ljava/util/Locale$Builder;
    //   948: aload #8
    //   950: invokevirtual setVariant : (Ljava/lang/String;)Ljava/util/Locale$Builder;
    //   953: aload #13
    //   955: invokevirtual setScript : (Ljava/lang/String;)Ljava/util/Locale$Builder;
    //   958: invokevirtual build : ()Ljava/util/Locale;
    //   961: astore #16
    //   963: aload_1
    //   964: astore #4
    //   966: aload #9
    //   968: aload #16
    //   970: invokeinterface indexOf : (Ljava/lang/Object;)I
    //   975: istore #10
    //   977: iload #10
    //   979: iconst_m1
    //   980: if_icmpeq -> 1072
    //   983: aload_1
    //   984: astore #4
    //   986: new java/lang/StringBuilder
    //   989: astore #17
    //   991: aload_1
    //   992: astore #4
    //   994: aload #17
    //   996: invokespecial <init> : ()V
    //   999: aload_1
    //   1000: astore #4
    //   1002: aload #17
    //   1004: ldc_w 'Repeated locale ('
    //   1007: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1010: pop
    //   1011: aload_1
    //   1012: astore #4
    //   1014: aload #17
    //   1016: aload #9
    //   1018: iload #10
    //   1020: invokeinterface get : (I)Ljava/lang/Object;
    //   1025: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1028: pop
    //   1029: aload_1
    //   1030: astore #4
    //   1032: aload #17
    //   1034: ldc_w ') found when trying to add: '
    //   1037: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1040: pop
    //   1041: aload_1
    //   1042: astore #4
    //   1044: aload #17
    //   1046: aload #16
    //   1048: invokevirtual toString : ()Ljava/lang/String;
    //   1051: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1054: pop
    //   1055: aload_1
    //   1056: astore #4
    //   1058: ldc 'Configuration'
    //   1060: aload #17
    //   1062: invokevirtual toString : ()Ljava/lang/String;
    //   1065: invokestatic wtf : (Ljava/lang/String;Ljava/lang/String;)I
    //   1068: pop
    //   1069: goto -> 1085
    //   1072: aload_1
    //   1073: astore #4
    //   1075: aload #9
    //   1077: aload #16
    //   1079: invokeinterface add : (Ljava/lang/Object;)Z
    //   1084: pop
    //   1085: goto -> 1215
    //   1088: astore #4
    //   1090: goto -> 1095
    //   1093: astore #4
    //   1095: aload_1
    //   1096: astore #4
    //   1098: new java/lang/StringBuilder
    //   1101: astore #16
    //   1103: aload_1
    //   1104: astore #4
    //   1106: aload #16
    //   1108: invokespecial <init> : ()V
    //   1111: aload_1
    //   1112: astore #4
    //   1114: aload #16
    //   1116: ldc_w 'readFromProto error building locale with: language-'
    //   1119: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1122: pop
    //   1123: aload_1
    //   1124: astore #4
    //   1126: aload #16
    //   1128: aload #14
    //   1130: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1133: pop
    //   1134: aload_1
    //   1135: astore #4
    //   1137: aload #16
    //   1139: aload #7
    //   1141: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1144: pop
    //   1145: aload_1
    //   1146: astore #4
    //   1148: aload #16
    //   1150: aload #15
    //   1152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1155: pop
    //   1156: aload_1
    //   1157: astore #4
    //   1159: aload #16
    //   1161: aload #6
    //   1163: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1166: pop
    //   1167: aload_1
    //   1168: astore #4
    //   1170: aload #16
    //   1172: aload #8
    //   1174: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1177: pop
    //   1178: aload_1
    //   1179: astore #4
    //   1181: aload #16
    //   1183: ldc_w ';script-'
    //   1186: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1189: pop
    //   1190: aload_1
    //   1191: astore #4
    //   1193: aload #16
    //   1195: aload #13
    //   1197: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1200: pop
    //   1201: aload_1
    //   1202: astore #4
    //   1204: ldc 'Configuration'
    //   1206: aload #16
    //   1208: invokevirtual toString : ()Ljava/lang/String;
    //   1211: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   1214: pop
    //   1215: aload_1
    //   1216: astore #4
    //   1218: aload #5
    //   1220: athrow
    //   1221: astore_1
    //   1222: aload_0
    //   1223: astore #8
    //   1225: goto -> 1407
    //   1228: aload #5
    //   1230: astore #15
    //   1232: aload #15
    //   1234: astore #4
    //   1236: aload #15
    //   1238: ldc2_w 1155346202627
    //   1241: invokevirtual readInt : (J)I
    //   1244: istore #10
    //   1246: aload_0
    //   1247: astore #4
    //   1249: aload #4
    //   1251: astore #13
    //   1253: aload #4
    //   1255: iload #10
    //   1257: putfield mnc : I
    //   1260: aload #15
    //   1262: astore #5
    //   1264: goto -> 1342
    //   1267: astore_1
    //   1268: aload_0
    //   1269: astore #8
    //   1271: aload #4
    //   1273: astore #5
    //   1275: goto -> 1339
    //   1278: aload #5
    //   1280: astore #15
    //   1282: aload #4
    //   1284: astore #13
    //   1286: aload #4
    //   1288: aload #15
    //   1290: ldc2_w 1155346202626
    //   1293: invokevirtual readInt : (J)I
    //   1296: putfield mcc : I
    //   1299: aload #15
    //   1301: astore #5
    //   1303: goto -> 1342
    //   1306: aload #5
    //   1308: astore #15
    //   1310: aload #4
    //   1312: astore #13
    //   1314: aload #4
    //   1316: aload #15
    //   1318: ldc2_w 1108101562369
    //   1321: invokevirtual readFloat : (J)F
    //   1324: putfield fontScale : F
    //   1327: aload #15
    //   1329: astore #5
    //   1331: goto -> 1342
    //   1334: astore_1
    //   1335: aload #13
    //   1337: astore #8
    //   1339: goto -> 1407
    //   1342: goto -> 36
    //   1345: astore_1
    //   1346: aload #4
    //   1348: astore #8
    //   1350: goto -> 1407
    //   1353: aload #9
    //   1355: invokeinterface size : ()I
    //   1360: ifle -> 1395
    //   1363: aload #4
    //   1365: new android/os/LocaleList
    //   1368: dup
    //   1369: aload #9
    //   1371: aload #9
    //   1373: invokeinterface size : ()I
    //   1378: anewarray java/util/Locale
    //   1381: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   1386: checkcast [Ljava/util/Locale;
    //   1389: invokespecial <init> : ([Ljava/util/Locale;)V
    //   1392: invokevirtual setLocales : (Landroid/os/LocaleList;)V
    //   1395: aload #5
    //   1397: lload_2
    //   1398: invokevirtual end : (J)V
    //   1401: return
    //   1402: astore_1
    //   1403: aload #4
    //   1405: astore #8
    //   1407: aload #9
    //   1409: invokeinterface size : ()I
    //   1414: ifle -> 1449
    //   1417: aload #8
    //   1419: new android/os/LocaleList
    //   1422: dup
    //   1423: aload #9
    //   1425: aload #9
    //   1427: invokeinterface size : ()I
    //   1432: anewarray java/util/Locale
    //   1435: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   1440: checkcast [Ljava/util/Locale;
    //   1443: invokespecial <init> : ([Ljava/util/Locale;)V
    //   1446: invokevirtual setLocales : (Landroid/os/LocaleList;)V
    //   1449: aload #5
    //   1451: lload_2
    //   1452: invokevirtual end : (J)V
    //   1455: aload_1
    //   1456: athrow
    // Exception table:
    //   from	to	target	type
    //   36	42	1402	finally
    //   48	54	1345	finally
    //   158	174	177	java/lang/Exception
    //   158	174	433	finally
    //   179	190	433	finally
    //   193	206	433	finally
    //   209	222	433	finally
    //   225	238	433	finally
    //   241	254	433	finally
    //   257	270	433	finally
    //   273	286	433	finally
    //   289	302	433	finally
    //   305	318	433	finally
    //   321	334	433	finally
    //   337	350	433	finally
    //   353	366	433	finally
    //   369	382	433	finally
    //   385	398	433	finally
    //   401	414	433	finally
    //   417	430	433	finally
    //   441	451	1221	finally
    //   467	473	897	android/util/proto/WireTypeMismatchException
    //   467	473	892	finally
    //   479	485	585	android/util/proto/WireTypeMismatchException
    //   479	485	580	finally
    //   512	522	585	android/util/proto/WireTypeMismatchException
    //   512	522	580	finally
    //   529	539	585	android/util/proto/WireTypeMismatchException
    //   529	539	580	finally
    //   546	556	585	android/util/proto/WireTypeMismatchException
    //   546	556	580	finally
    //   563	573	585	android/util/proto/WireTypeMismatchException
    //   563	573	580	finally
    //   590	597	881	finally
    //   597	607	769	java/util/IllformedLocaleException
    //   597	607	881	finally
    //   607	616	764	java/util/IllformedLocaleException
    //   607	616	881	finally
    //   616	625	759	java/util/IllformedLocaleException
    //   616	625	881	finally
    //   625	634	754	java/util/IllformedLocaleException
    //   625	634	881	finally
    //   634	657	749	java/util/IllformedLocaleException
    //   634	657	867	finally
    //   663	728	744	java/util/IllformedLocaleException
    //   663	728	867	finally
    //   731	741	744	java/util/IllformedLocaleException
    //   731	741	867	finally
    //   771	858	867	finally
    //   899	902	902	finally
    //   911	917	1267	finally
    //   920	925	1093	java/util/IllformedLocaleException
    //   920	925	1267	finally
    //   928	933	1093	java/util/IllformedLocaleException
    //   928	933	1267	finally
    //   936	963	1093	java/util/IllformedLocaleException
    //   936	963	1267	finally
    //   966	977	1093	java/util/IllformedLocaleException
    //   966	977	1267	finally
    //   986	991	1088	java/util/IllformedLocaleException
    //   986	991	1267	finally
    //   994	999	1088	java/util/IllformedLocaleException
    //   994	999	1267	finally
    //   1002	1011	1088	java/util/IllformedLocaleException
    //   1002	1011	1267	finally
    //   1014	1029	1088	java/util/IllformedLocaleException
    //   1014	1029	1267	finally
    //   1032	1041	1088	java/util/IllformedLocaleException
    //   1032	1041	1267	finally
    //   1044	1055	1088	java/util/IllformedLocaleException
    //   1044	1055	1267	finally
    //   1058	1069	1088	java/util/IllformedLocaleException
    //   1058	1069	1267	finally
    //   1075	1085	1088	java/util/IllformedLocaleException
    //   1075	1085	1267	finally
    //   1098	1103	1267	finally
    //   1106	1111	1267	finally
    //   1114	1123	1267	finally
    //   1126	1134	1267	finally
    //   1137	1145	1267	finally
    //   1148	1156	1267	finally
    //   1159	1167	1267	finally
    //   1170	1178	1267	finally
    //   1181	1190	1267	finally
    //   1193	1201	1267	finally
    //   1204	1215	1267	finally
    //   1218	1221	1267	finally
    //   1236	1246	1267	finally
    //   1253	1260	1334	finally
    //   1286	1299	1334	finally
    //   1314	1327	1334	finally
  }
  
  public void setLayoutDirection(Locale paramLocale) {
    int i = TextUtils.getLayoutDirectionFromLocale(paramLocale);
    this.screenLayout = this.screenLayout & 0xFFFFFF3F | i + 1 << 6;
  }
  
  public void setLocale(Locale paramLocale) {
    LocaleList localeList;
    if (paramLocale == null) {
      localeList = LocaleList.getEmptyLocaleList();
    } else {
      localeList = new LocaleList(new Locale[] { (Locale)localeList });
    } 
    setLocales(localeList);
  }
  
  public void setLocales(LocaleList paramLocaleList) {
    if (paramLocaleList == null)
      paramLocaleList = LocaleList.getEmptyLocaleList(); 
    this.mLocaleList = paramLocaleList;
    Locale locale = paramLocaleList.get(0);
    this.locale = locale;
    setLayoutDirection(locale);
  }
  
  public void setTo(Configuration paramConfiguration) {
    this.fontScale = paramConfiguration.fontScale;
    this.mcc = paramConfiguration.mcc;
    this.mnc = paramConfiguration.mnc;
    Locale locale = paramConfiguration.locale;
    if (locale == null) {
      this.locale = null;
    } else if (!locale.equals(this.locale)) {
      this.locale = (Locale)paramConfiguration.locale.clone();
    } 
    paramConfiguration.fixUpLocaleList();
    this.mLocaleList = paramConfiguration.mLocaleList;
    this.userSetLocale = paramConfiguration.userSetLocale;
    this.touchscreen = paramConfiguration.touchscreen;
    this.keyboard = paramConfiguration.keyboard;
    this.keyboardHidden = paramConfiguration.keyboardHidden;
    this.hardKeyboardHidden = paramConfiguration.hardKeyboardHidden;
    this.navigation = paramConfiguration.navigation;
    this.navigationHidden = paramConfiguration.navigationHidden;
    this.orientation = paramConfiguration.orientation;
    this.screenLayout = paramConfiguration.screenLayout;
    this.colorMode = paramConfiguration.colorMode;
    this.uiMode = paramConfiguration.uiMode;
    this.screenWidthDp = paramConfiguration.screenWidthDp;
    this.screenHeightDp = paramConfiguration.screenHeightDp;
    this.smallestScreenWidthDp = paramConfiguration.smallestScreenWidthDp;
    this.densityDpi = paramConfiguration.densityDpi;
    this.compatScreenWidthDp = paramConfiguration.compatScreenWidthDp;
    this.compatScreenHeightDp = paramConfiguration.compatScreenHeightDp;
    this.compatSmallestScreenWidthDp = paramConfiguration.compatSmallestScreenWidthDp;
    this.assetsSeq = paramConfiguration.assetsSeq;
    this.seq = paramConfiguration.seq;
    this.windowConfiguration.setTo(paramConfiguration.windowConfiguration);
  }
  
  public void setTo(Configuration paramConfiguration, int paramInt1, int paramInt2) {
    if ((0x40000000 & paramInt1) != 0)
      this.fontScale = paramConfiguration.fontScale; 
    if ((paramInt1 & 0x1) != 0)
      this.mcc = paramConfiguration.mcc; 
    if ((paramInt1 & 0x2) != 0)
      this.mnc = paramConfiguration.mnc; 
    if ((paramInt1 & 0x4) != 0) {
      LocaleList localeList = paramConfiguration.mLocaleList;
      this.mLocaleList = localeList;
      if (!localeList.isEmpty() && !paramConfiguration.locale.equals(this.locale))
        this.locale = (Locale)paramConfiguration.locale.clone(); 
    } 
    if ((paramInt1 & 0x2000) != 0) {
      int i = paramConfiguration.screenLayout;
      this.screenLayout = this.screenLayout & 0xFFFFFF3F | i & 0xC0;
    } 
    if ((paramInt1 & 0x4) != 0)
      this.userSetLocale = paramConfiguration.userSetLocale; 
    if ((paramInt1 & 0x8) != 0)
      this.touchscreen = paramConfiguration.touchscreen; 
    if ((paramInt1 & 0x10) != 0)
      this.keyboard = paramConfiguration.keyboard; 
    if ((paramInt1 & 0x20) != 0) {
      this.keyboardHidden = paramConfiguration.keyboardHidden;
      this.hardKeyboardHidden = paramConfiguration.hardKeyboardHidden;
      this.navigationHidden = paramConfiguration.navigationHidden;
    } 
    if ((paramInt1 & 0x40) != 0)
      this.navigation = paramConfiguration.navigation; 
    if ((paramInt1 & 0x80) != 0)
      this.orientation = paramConfiguration.orientation; 
    if ((paramInt1 & 0x100) != 0)
      this.screenLayout |= paramConfiguration.screenLayout & 0xFFFFFF3F; 
    if ((paramInt1 & 0x4000) != 0)
      this.colorMode = paramConfiguration.colorMode; 
    if ((paramInt1 & 0x200) != 0)
      this.uiMode = paramConfiguration.uiMode; 
    if ((paramInt1 & 0x400) != 0) {
      this.screenWidthDp = paramConfiguration.screenWidthDp;
      this.screenHeightDp = paramConfiguration.screenHeightDp;
    } 
    if ((paramInt1 & 0x800) != 0)
      this.smallestScreenWidthDp = paramConfiguration.smallestScreenWidthDp; 
    if ((paramInt1 & 0x1000) != 0)
      this.densityDpi = paramConfiguration.densityDpi; 
    if ((Integer.MIN_VALUE & paramInt1) != 0)
      this.assetsSeq = paramConfiguration.assetsSeq; 
    if ((0x20000000 & paramInt1) != 0)
      this.windowConfiguration.setTo(paramConfiguration.windowConfiguration, paramInt2); 
  }
  
  public void setToDefaults() {
    this.fontScale = 1.0F;
    this.mnc = 0;
    this.mcc = 0;
    this.mLocaleList = LocaleList.getEmptyLocaleList();
    this.locale = null;
    this.userSetLocale = false;
    this.touchscreen = 0;
    this.keyboard = 0;
    this.keyboardHidden = 0;
    this.hardKeyboardHidden = 0;
    this.navigation = 0;
    this.navigationHidden = 0;
    this.orientation = 0;
    this.screenLayout = 0;
    this.colorMode = 0;
    this.uiMode = 0;
    this.compatScreenWidthDp = 0;
    this.screenWidthDp = 0;
    this.compatScreenHeightDp = 0;
    this.screenHeightDp = 0;
    this.compatSmallestScreenWidthDp = 0;
    this.smallestScreenWidthDp = 0;
    this.densityDpi = 0;
    this.assetsSeq = 0;
    this.seq = 0;
    this.windowConfiguration.setToDefaults();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("{");
    stringBuilder.append(this.fontScale);
    stringBuilder.append(" ");
    int i = this.mcc;
    if (i != 0) {
      stringBuilder.append(i);
      stringBuilder.append("mcc");
    } else {
      stringBuilder.append("?mcc");
    } 
    i = this.mnc;
    if (i != 0) {
      stringBuilder.append(i);
      stringBuilder.append("mnc");
    } else {
      stringBuilder.append("?mnc");
    } 
    fixUpLocaleList();
    if (!this.mLocaleList.isEmpty()) {
      stringBuilder.append(" ");
      stringBuilder.append(this.mLocaleList);
    } else {
      stringBuilder.append(" ?localeList");
    } 
    i = this.screenLayout & 0xC0;
    if (i != 0) {
      if (i != 64) {
        if (i != 128) {
          stringBuilder.append(" layoutDir=");
          stringBuilder.append(i >> 6);
        } else {
          stringBuilder.append(" ldrtl");
        } 
      } else {
        stringBuilder.append(" ldltr");
      } 
    } else {
      stringBuilder.append(" ?layoutDir");
    } 
    if (this.smallestScreenWidthDp != 0) {
      stringBuilder.append(" sw");
      stringBuilder.append(this.smallestScreenWidthDp);
      stringBuilder.append("dp");
    } else {
      stringBuilder.append(" ?swdp");
    } 
    if (this.screenWidthDp != 0) {
      stringBuilder.append(" w");
      stringBuilder.append(this.screenWidthDp);
      stringBuilder.append("dp");
    } else {
      stringBuilder.append(" ?wdp");
    } 
    if (this.screenHeightDp != 0) {
      stringBuilder.append(" h");
      stringBuilder.append(this.screenHeightDp);
      stringBuilder.append("dp");
    } else {
      stringBuilder.append(" ?hdp");
    } 
    if (this.densityDpi != 0) {
      stringBuilder.append(" ");
      stringBuilder.append(this.densityDpi);
      stringBuilder.append("dpi");
    } else {
      stringBuilder.append(" ?density");
    } 
    i = this.screenLayout & 0xF;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              stringBuilder.append(" layoutSize=");
              stringBuilder.append(this.screenLayout & 0xF);
            } else {
              stringBuilder.append(" xlrg");
            } 
          } else {
            stringBuilder.append(" lrg");
          } 
        } else {
          stringBuilder.append(" nrml");
        } 
      } else {
        stringBuilder.append(" smll");
      } 
    } else {
      stringBuilder.append(" ?lsize");
    } 
    i = this.screenLayout & 0x30;
    if (i != 0) {
      if (i != 16)
        if (i != 32) {
          stringBuilder.append(" layoutLong=");
          stringBuilder.append(this.screenLayout & 0x30);
        } else {
          stringBuilder.append(" long");
        }  
    } else {
      stringBuilder.append(" ?long");
    } 
    i = this.colorMode & 0xC;
    if (i != 0) {
      if (i != 4)
        if (i != 8) {
          stringBuilder.append(" dynamicRange=");
          stringBuilder.append(this.colorMode & 0xC);
        } else {
          stringBuilder.append(" hdr");
        }  
    } else {
      stringBuilder.append(" ?ldr");
    } 
    i = this.colorMode & 0x3;
    if (i != 0) {
      if (i != 1)
        if (i != 2) {
          stringBuilder.append(" wideColorGamut=");
          stringBuilder.append(this.colorMode & 0x3);
        } else {
          stringBuilder.append(" widecg");
        }  
    } else {
      stringBuilder.append(" ?wideColorGamut");
    } 
    i = this.orientation;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          stringBuilder.append(" orien=");
          stringBuilder.append(this.orientation);
        } else {
          stringBuilder.append(" land");
        } 
      } else {
        stringBuilder.append(" port");
      } 
    } else {
      stringBuilder.append(" ?orien");
    } 
    switch (this.uiMode & 0xF) {
      default:
        stringBuilder.append(" uimode=");
        stringBuilder.append(this.uiMode & 0xF);
        break;
      case 7:
        stringBuilder.append(" vrheadset");
        break;
      case 6:
        stringBuilder.append(" watch");
        break;
      case 5:
        stringBuilder.append(" appliance");
        break;
      case 4:
        stringBuilder.append(" television");
        break;
      case 3:
        stringBuilder.append(" car");
        break;
      case 2:
        stringBuilder.append(" desk");
        break;
      case 1:
        break;
      case 0:
        stringBuilder.append(" ?uimode");
        break;
    } 
    i = this.uiMode & 0x30;
    if (i != 0) {
      if (i != 16)
        if (i != 32) {
          stringBuilder.append(" night=");
          stringBuilder.append(this.uiMode & 0x30);
        } else {
          stringBuilder.append(" night");
        }  
    } else {
      stringBuilder.append(" ?night");
    } 
    i = this.touchscreen;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            stringBuilder.append(" touch=");
            stringBuilder.append(this.touchscreen);
          } else {
            stringBuilder.append(" finger");
          } 
        } else {
          stringBuilder.append(" stylus");
        } 
      } else {
        stringBuilder.append(" -touch");
      } 
    } else {
      stringBuilder.append(" ?touch");
    } 
    i = this.keyboard;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            stringBuilder.append(" keys=");
            stringBuilder.append(this.keyboard);
          } else {
            stringBuilder.append(" 12key");
          } 
        } else {
          stringBuilder.append(" qwerty");
        } 
      } else {
        stringBuilder.append(" -keyb");
      } 
    } else {
      stringBuilder.append(" ?keyb");
    } 
    i = this.keyboardHidden;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            stringBuilder.append("/");
            stringBuilder.append(this.keyboardHidden);
          } else {
            stringBuilder.append("/s");
          } 
        } else {
          stringBuilder.append("/h");
        } 
      } else {
        stringBuilder.append("/v");
      } 
    } else {
      stringBuilder.append("/?");
    } 
    i = this.hardKeyboardHidden;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          stringBuilder.append("/");
          stringBuilder.append(this.hardKeyboardHidden);
        } else {
          stringBuilder.append("/h");
        } 
      } else {
        stringBuilder.append("/v");
      } 
    } else {
      stringBuilder.append("/?");
    } 
    i = this.navigation;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              stringBuilder.append(" nav=");
              stringBuilder.append(this.navigation);
            } else {
              stringBuilder.append(" wheel");
            } 
          } else {
            stringBuilder.append(" tball");
          } 
        } else {
          stringBuilder.append(" dpad");
        } 
      } else {
        stringBuilder.append(" -nav");
      } 
    } else {
      stringBuilder.append(" ?nav");
    } 
    i = this.navigationHidden;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          stringBuilder.append("/");
          stringBuilder.append(this.navigationHidden);
        } else {
          stringBuilder.append("/h");
        } 
      } else {
        stringBuilder.append("/v");
      } 
    } else {
      stringBuilder.append("/?");
    } 
    stringBuilder.append(" winConfig=");
    stringBuilder.append(this.windowConfiguration);
    if (this.assetsSeq != 0) {
      stringBuilder.append(" as.");
      stringBuilder.append(this.assetsSeq);
    } 
    if (this.seq != 0) {
      stringBuilder.append(" s.");
      stringBuilder.append(this.seq);
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void unset() {
    setToDefaults();
    this.fontScale = 0.0F;
  }
  
  public int updateFrom(Configuration paramConfiguration) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_1
    //   3: getfield fontScale : F
    //   6: fstore_3
    //   7: iload_2
    //   8: istore #4
    //   10: fload_3
    //   11: fconst_0
    //   12: fcmpl
    //   13: ifle -> 40
    //   16: iload_2
    //   17: istore #4
    //   19: aload_0
    //   20: getfield fontScale : F
    //   23: fload_3
    //   24: fcmpl
    //   25: ifeq -> 40
    //   28: iconst_0
    //   29: ldc_w 1073741824
    //   32: ior
    //   33: istore #4
    //   35: aload_0
    //   36: fload_3
    //   37: putfield fontScale : F
    //   40: aload_1
    //   41: getfield mcc : I
    //   44: istore #5
    //   46: iload #4
    //   48: istore_2
    //   49: iload #5
    //   51: ifeq -> 77
    //   54: iload #4
    //   56: istore_2
    //   57: aload_0
    //   58: getfield mcc : I
    //   61: iload #5
    //   63: if_icmpeq -> 77
    //   66: iload #4
    //   68: iconst_1
    //   69: ior
    //   70: istore_2
    //   71: aload_0
    //   72: iload #5
    //   74: putfield mcc : I
    //   77: aload_1
    //   78: getfield mnc : I
    //   81: istore #5
    //   83: iload_2
    //   84: istore #4
    //   86: iload #5
    //   88: ifeq -> 114
    //   91: iload_2
    //   92: istore #4
    //   94: aload_0
    //   95: getfield mnc : I
    //   98: iload #5
    //   100: if_icmpeq -> 114
    //   103: iload_2
    //   104: iconst_2
    //   105: ior
    //   106: istore #4
    //   108: aload_0
    //   109: iload #5
    //   111: putfield mnc : I
    //   114: aload_0
    //   115: invokespecial fixUpLocaleList : ()V
    //   118: aload_1
    //   119: invokespecial fixUpLocaleList : ()V
    //   122: iload #4
    //   124: istore_2
    //   125: aload_1
    //   126: getfield mLocaleList : Landroid/os/LocaleList;
    //   129: invokevirtual isEmpty : ()Z
    //   132: ifne -> 214
    //   135: iload #4
    //   137: istore_2
    //   138: aload_0
    //   139: getfield mLocaleList : Landroid/os/LocaleList;
    //   142: aload_1
    //   143: getfield mLocaleList : Landroid/os/LocaleList;
    //   146: invokevirtual equals : (Ljava/lang/Object;)Z
    //   149: ifne -> 214
    //   152: iload #4
    //   154: iconst_4
    //   155: ior
    //   156: istore #4
    //   158: aload_0
    //   159: aload_1
    //   160: getfield mLocaleList : Landroid/os/LocaleList;
    //   163: putfield mLocaleList : Landroid/os/LocaleList;
    //   166: iload #4
    //   168: istore_2
    //   169: aload_1
    //   170: getfield locale : Ljava/util/Locale;
    //   173: aload_0
    //   174: getfield locale : Ljava/util/Locale;
    //   177: invokevirtual equals : (Ljava/lang/Object;)Z
    //   180: ifne -> 214
    //   183: aload_1
    //   184: getfield locale : Ljava/util/Locale;
    //   187: invokevirtual clone : ()Ljava/lang/Object;
    //   190: checkcast java/util/Locale
    //   193: astore #6
    //   195: aload_0
    //   196: aload #6
    //   198: putfield locale : Ljava/util/Locale;
    //   201: iload #4
    //   203: sipush #8192
    //   206: ior
    //   207: istore_2
    //   208: aload_0
    //   209: aload #6
    //   211: invokevirtual setLayoutDirection : (Ljava/util/Locale;)V
    //   214: aload_1
    //   215: getfield screenLayout : I
    //   218: sipush #192
    //   221: iand
    //   222: istore #7
    //   224: iload_2
    //   225: istore #4
    //   227: iload #7
    //   229: ifeq -> 272
    //   232: aload_0
    //   233: getfield screenLayout : I
    //   236: istore #5
    //   238: iload_2
    //   239: istore #4
    //   241: iload #7
    //   243: iload #5
    //   245: sipush #192
    //   248: iand
    //   249: if_icmpeq -> 272
    //   252: aload_0
    //   253: iload #5
    //   255: sipush #-193
    //   258: iand
    //   259: iload #7
    //   261: ior
    //   262: putfield screenLayout : I
    //   265: iload_2
    //   266: sipush #8192
    //   269: ior
    //   270: istore #4
    //   272: iload #4
    //   274: istore #5
    //   276: aload_1
    //   277: getfield userSetLocale : Z
    //   280: ifeq -> 312
    //   283: aload_0
    //   284: getfield userSetLocale : Z
    //   287: ifeq -> 301
    //   290: iload #4
    //   292: istore #5
    //   294: iload #4
    //   296: iconst_4
    //   297: iand
    //   298: ifeq -> 312
    //   301: iload #4
    //   303: iconst_4
    //   304: ior
    //   305: istore #5
    //   307: aload_0
    //   308: iconst_1
    //   309: putfield userSetLocale : Z
    //   312: aload_1
    //   313: getfield touchscreen : I
    //   316: istore #4
    //   318: iload #5
    //   320: istore_2
    //   321: iload #4
    //   323: ifeq -> 350
    //   326: iload #5
    //   328: istore_2
    //   329: aload_0
    //   330: getfield touchscreen : I
    //   333: iload #4
    //   335: if_icmpeq -> 350
    //   338: iload #5
    //   340: bipush #8
    //   342: ior
    //   343: istore_2
    //   344: aload_0
    //   345: iload #4
    //   347: putfield touchscreen : I
    //   350: aload_1
    //   351: getfield keyboard : I
    //   354: istore #5
    //   356: iload_2
    //   357: istore #4
    //   359: iload #5
    //   361: ifeq -> 388
    //   364: iload_2
    //   365: istore #4
    //   367: aload_0
    //   368: getfield keyboard : I
    //   371: iload #5
    //   373: if_icmpeq -> 388
    //   376: iload_2
    //   377: bipush #16
    //   379: ior
    //   380: istore #4
    //   382: aload_0
    //   383: iload #5
    //   385: putfield keyboard : I
    //   388: aload_1
    //   389: getfield keyboardHidden : I
    //   392: istore #5
    //   394: iload #4
    //   396: istore_2
    //   397: iload #5
    //   399: ifeq -> 426
    //   402: iload #4
    //   404: istore_2
    //   405: aload_0
    //   406: getfield keyboardHidden : I
    //   409: iload #5
    //   411: if_icmpeq -> 426
    //   414: iload #4
    //   416: bipush #32
    //   418: ior
    //   419: istore_2
    //   420: aload_0
    //   421: iload #5
    //   423: putfield keyboardHidden : I
    //   426: aload_1
    //   427: getfield hardKeyboardHidden : I
    //   430: istore #4
    //   432: iload_2
    //   433: istore #5
    //   435: iload #4
    //   437: ifeq -> 464
    //   440: iload_2
    //   441: istore #5
    //   443: aload_0
    //   444: getfield hardKeyboardHidden : I
    //   447: iload #4
    //   449: if_icmpeq -> 464
    //   452: iload_2
    //   453: bipush #32
    //   455: ior
    //   456: istore #5
    //   458: aload_0
    //   459: iload #4
    //   461: putfield hardKeyboardHidden : I
    //   464: aload_1
    //   465: getfield navigation : I
    //   468: istore_2
    //   469: iload #5
    //   471: istore #4
    //   473: iload_2
    //   474: ifeq -> 501
    //   477: iload #5
    //   479: istore #4
    //   481: aload_0
    //   482: getfield navigation : I
    //   485: iload_2
    //   486: if_icmpeq -> 501
    //   489: iload #5
    //   491: bipush #64
    //   493: ior
    //   494: istore #4
    //   496: aload_0
    //   497: iload_2
    //   498: putfield navigation : I
    //   501: aload_1
    //   502: getfield navigationHidden : I
    //   505: istore #5
    //   507: iload #4
    //   509: istore_2
    //   510: iload #5
    //   512: ifeq -> 539
    //   515: iload #4
    //   517: istore_2
    //   518: aload_0
    //   519: getfield navigationHidden : I
    //   522: iload #5
    //   524: if_icmpeq -> 539
    //   527: iload #4
    //   529: bipush #32
    //   531: ior
    //   532: istore_2
    //   533: aload_0
    //   534: iload #5
    //   536: putfield navigationHidden : I
    //   539: aload_1
    //   540: getfield orientation : I
    //   543: istore #5
    //   545: iload_2
    //   546: istore #4
    //   548: iload #5
    //   550: ifeq -> 578
    //   553: iload_2
    //   554: istore #4
    //   556: aload_0
    //   557: getfield orientation : I
    //   560: iload #5
    //   562: if_icmpeq -> 578
    //   565: iload_2
    //   566: sipush #128
    //   569: ior
    //   570: istore #4
    //   572: aload_0
    //   573: iload #5
    //   575: putfield orientation : I
    //   578: aload_1
    //   579: getfield screenLayout : I
    //   582: istore #7
    //   584: iload #4
    //   586: istore_2
    //   587: iload #7
    //   589: bipush #15
    //   591: iand
    //   592: ifeq -> 639
    //   595: aload_0
    //   596: getfield screenLayout : I
    //   599: istore #5
    //   601: iload #4
    //   603: istore_2
    //   604: iload #7
    //   606: bipush #15
    //   608: iand
    //   609: iload #5
    //   611: bipush #15
    //   613: iand
    //   614: if_icmpeq -> 639
    //   617: iload #4
    //   619: sipush #256
    //   622: ior
    //   623: istore_2
    //   624: aload_0
    //   625: iload #7
    //   627: bipush #15
    //   629: iand
    //   630: iload #5
    //   632: bipush #-16
    //   634: iand
    //   635: ior
    //   636: putfield screenLayout : I
    //   639: aload_1
    //   640: getfield screenLayout : I
    //   643: istore #5
    //   645: iload_2
    //   646: istore #4
    //   648: iload #5
    //   650: bipush #48
    //   652: iand
    //   653: ifeq -> 700
    //   656: aload_0
    //   657: getfield screenLayout : I
    //   660: istore #7
    //   662: iload_2
    //   663: istore #4
    //   665: iload #5
    //   667: bipush #48
    //   669: iand
    //   670: iload #7
    //   672: bipush #48
    //   674: iand
    //   675: if_icmpeq -> 700
    //   678: iload_2
    //   679: sipush #256
    //   682: ior
    //   683: istore #4
    //   685: aload_0
    //   686: iload #5
    //   688: bipush #48
    //   690: iand
    //   691: iload #7
    //   693: bipush #-49
    //   695: iand
    //   696: ior
    //   697: putfield screenLayout : I
    //   700: aload_1
    //   701: getfield screenLayout : I
    //   704: istore #7
    //   706: iload #4
    //   708: istore_2
    //   709: iload #7
    //   711: sipush #768
    //   714: iand
    //   715: ifeq -> 766
    //   718: aload_0
    //   719: getfield screenLayout : I
    //   722: istore #5
    //   724: iload #4
    //   726: istore_2
    //   727: iload #7
    //   729: sipush #768
    //   732: iand
    //   733: iload #5
    //   735: sipush #768
    //   738: iand
    //   739: if_icmpeq -> 766
    //   742: iload #4
    //   744: sipush #256
    //   747: ior
    //   748: istore_2
    //   749: aload_0
    //   750: iload #7
    //   752: sipush #768
    //   755: iand
    //   756: iload #5
    //   758: sipush #-769
    //   761: iand
    //   762: ior
    //   763: putfield screenLayout : I
    //   766: aload_1
    //   767: getfield screenLayout : I
    //   770: istore #7
    //   772: aload_0
    //   773: getfield screenLayout : I
    //   776: istore #5
    //   778: iload_2
    //   779: istore #4
    //   781: iload #7
    //   783: ldc 268435456
    //   785: iand
    //   786: iload #5
    //   788: ldc 268435456
    //   790: iand
    //   791: if_icmpeq -> 825
    //   794: iload_2
    //   795: istore #4
    //   797: iload #7
    //   799: ifeq -> 825
    //   802: iload_2
    //   803: sipush #256
    //   806: ior
    //   807: istore #4
    //   809: aload_0
    //   810: iload #7
    //   812: ldc 268435456
    //   814: iand
    //   815: ldc_w -268435457
    //   818: iload #5
    //   820: iand
    //   821: ior
    //   822: putfield screenLayout : I
    //   825: aload_1
    //   826: getfield colorMode : I
    //   829: istore #7
    //   831: iload #4
    //   833: istore_2
    //   834: iload #7
    //   836: iconst_3
    //   837: iand
    //   838: ifeq -> 882
    //   841: aload_0
    //   842: getfield colorMode : I
    //   845: istore #5
    //   847: iload #4
    //   849: istore_2
    //   850: iload #7
    //   852: iconst_3
    //   853: iand
    //   854: iload #5
    //   856: iconst_3
    //   857: iand
    //   858: if_icmpeq -> 882
    //   861: iload #4
    //   863: sipush #16384
    //   866: ior
    //   867: istore_2
    //   868: aload_0
    //   869: iload #7
    //   871: iconst_3
    //   872: iand
    //   873: iload #5
    //   875: bipush #-4
    //   877: iand
    //   878: ior
    //   879: putfield colorMode : I
    //   882: aload_1
    //   883: getfield colorMode : I
    //   886: istore #7
    //   888: iload_2
    //   889: istore #4
    //   891: iload #7
    //   893: bipush #12
    //   895: iand
    //   896: ifeq -> 943
    //   899: aload_0
    //   900: getfield colorMode : I
    //   903: istore #5
    //   905: iload_2
    //   906: istore #4
    //   908: iload #7
    //   910: bipush #12
    //   912: iand
    //   913: iload #5
    //   915: bipush #12
    //   917: iand
    //   918: if_icmpeq -> 943
    //   921: iload_2
    //   922: sipush #16384
    //   925: ior
    //   926: istore #4
    //   928: aload_0
    //   929: iload #7
    //   931: bipush #12
    //   933: iand
    //   934: iload #5
    //   936: bipush #-13
    //   938: iand
    //   939: ior
    //   940: putfield colorMode : I
    //   943: aload_1
    //   944: getfield uiMode : I
    //   947: istore #5
    //   949: iload #4
    //   951: istore_2
    //   952: iload #5
    //   954: ifeq -> 1041
    //   957: aload_0
    //   958: getfield uiMode : I
    //   961: istore #7
    //   963: iload #4
    //   965: istore_2
    //   966: iload #7
    //   968: iload #5
    //   970: if_icmpeq -> 1041
    //   973: iload #4
    //   975: sipush #512
    //   978: ior
    //   979: istore #4
    //   981: iload #5
    //   983: bipush #15
    //   985: iand
    //   986: ifeq -> 1004
    //   989: aload_0
    //   990: iload #5
    //   992: bipush #15
    //   994: iand
    //   995: iload #7
    //   997: bipush #-16
    //   999: iand
    //   1000: ior
    //   1001: putfield uiMode : I
    //   1004: aload_1
    //   1005: getfield uiMode : I
    //   1008: istore #5
    //   1010: iload #4
    //   1012: istore_2
    //   1013: iload #5
    //   1015: bipush #48
    //   1017: iand
    //   1018: ifeq -> 1041
    //   1021: aload_0
    //   1022: iload #5
    //   1024: bipush #48
    //   1026: iand
    //   1027: aload_0
    //   1028: getfield uiMode : I
    //   1031: bipush #-49
    //   1033: iand
    //   1034: ior
    //   1035: putfield uiMode : I
    //   1038: iload #4
    //   1040: istore_2
    //   1041: aload_1
    //   1042: getfield screenWidthDp : I
    //   1045: istore #5
    //   1047: iload_2
    //   1048: istore #4
    //   1050: iload #5
    //   1052: ifeq -> 1080
    //   1055: iload_2
    //   1056: istore #4
    //   1058: aload_0
    //   1059: getfield screenWidthDp : I
    //   1062: iload #5
    //   1064: if_icmpeq -> 1080
    //   1067: iload_2
    //   1068: sipush #1024
    //   1071: ior
    //   1072: istore #4
    //   1074: aload_0
    //   1075: iload #5
    //   1077: putfield screenWidthDp : I
    //   1080: aload_1
    //   1081: getfield screenHeightDp : I
    //   1084: istore #5
    //   1086: iload #4
    //   1088: istore_2
    //   1089: iload #5
    //   1091: ifeq -> 1119
    //   1094: iload #4
    //   1096: istore_2
    //   1097: aload_0
    //   1098: getfield screenHeightDp : I
    //   1101: iload #5
    //   1103: if_icmpeq -> 1119
    //   1106: iload #4
    //   1108: sipush #1024
    //   1111: ior
    //   1112: istore_2
    //   1113: aload_0
    //   1114: iload #5
    //   1116: putfield screenHeightDp : I
    //   1119: aload_1
    //   1120: getfield smallestScreenWidthDp : I
    //   1123: istore #5
    //   1125: iload_2
    //   1126: istore #4
    //   1128: iload #5
    //   1130: ifeq -> 1158
    //   1133: iload_2
    //   1134: istore #4
    //   1136: aload_0
    //   1137: getfield smallestScreenWidthDp : I
    //   1140: iload #5
    //   1142: if_icmpeq -> 1158
    //   1145: iload_2
    //   1146: sipush #2048
    //   1149: ior
    //   1150: istore #4
    //   1152: aload_0
    //   1153: iload #5
    //   1155: putfield smallestScreenWidthDp : I
    //   1158: aload_1
    //   1159: getfield densityDpi : I
    //   1162: istore #5
    //   1164: iload #4
    //   1166: istore_2
    //   1167: iload #5
    //   1169: ifeq -> 1197
    //   1172: iload #4
    //   1174: istore_2
    //   1175: aload_0
    //   1176: getfield densityDpi : I
    //   1179: iload #5
    //   1181: if_icmpeq -> 1197
    //   1184: iload #4
    //   1186: sipush #4096
    //   1189: ior
    //   1190: istore_2
    //   1191: aload_0
    //   1192: iload #5
    //   1194: putfield densityDpi : I
    //   1197: aload_1
    //   1198: getfield compatScreenWidthDp : I
    //   1201: istore #4
    //   1203: iload #4
    //   1205: ifeq -> 1214
    //   1208: aload_0
    //   1209: iload #4
    //   1211: putfield compatScreenWidthDp : I
    //   1214: aload_1
    //   1215: getfield compatScreenHeightDp : I
    //   1218: istore #4
    //   1220: iload #4
    //   1222: ifeq -> 1231
    //   1225: aload_0
    //   1226: iload #4
    //   1228: putfield compatScreenHeightDp : I
    //   1231: aload_1
    //   1232: getfield compatSmallestScreenWidthDp : I
    //   1235: istore #4
    //   1237: iload #4
    //   1239: ifeq -> 1248
    //   1242: aload_0
    //   1243: iload #4
    //   1245: putfield compatSmallestScreenWidthDp : I
    //   1248: aload_1
    //   1249: getfield assetsSeq : I
    //   1252: istore #5
    //   1254: iload_2
    //   1255: istore #4
    //   1257: iload #5
    //   1259: ifeq -> 1287
    //   1262: iload_2
    //   1263: istore #4
    //   1265: iload #5
    //   1267: aload_0
    //   1268: getfield assetsSeq : I
    //   1271: if_icmpeq -> 1287
    //   1274: iload_2
    //   1275: ldc_w -2147483648
    //   1278: ior
    //   1279: istore #4
    //   1281: aload_0
    //   1282: iload #5
    //   1284: putfield assetsSeq : I
    //   1287: aload_1
    //   1288: getfield seq : I
    //   1291: istore_2
    //   1292: iload_2
    //   1293: ifeq -> 1301
    //   1296: aload_0
    //   1297: iload_2
    //   1298: putfield seq : I
    //   1301: iload #4
    //   1303: istore_2
    //   1304: aload_0
    //   1305: getfield windowConfiguration : Landroid/app/WindowConfiguration;
    //   1308: aload_1
    //   1309: getfield windowConfiguration : Landroid/app/WindowConfiguration;
    //   1312: invokevirtual updateFrom : (Landroid/app/WindowConfiguration;)I
    //   1315: ifeq -> 1325
    //   1318: iload #4
    //   1320: ldc_w 536870912
    //   1323: ior
    //   1324: istore_2
    //   1325: iload_2
    //   1326: ireturn
  }
  
  public void writeResConfigToProto(ProtoOutputStream paramProtoOutputStream, long paramLong, DisplayMetrics paramDisplayMetrics) {
    int i;
    int j;
    if (paramDisplayMetrics.widthPixels >= paramDisplayMetrics.heightPixels) {
      i = paramDisplayMetrics.widthPixels;
      j = paramDisplayMetrics.heightPixels;
    } else {
      i = paramDisplayMetrics.heightPixels;
      j = paramDisplayMetrics.widthPixels;
    } 
    paramLong = paramProtoOutputStream.start(paramLong);
    dumpDebug(paramProtoOutputStream, 1146756268033L);
    paramProtoOutputStream.write(1155346202626L, Build.VERSION.RESOURCES_SDK_INT);
    paramProtoOutputStream.write(1155346202627L, i);
    paramProtoOutputStream.write(1155346202628L, j);
    paramProtoOutputStream.end(paramLong);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeFloat(this.fontScale);
    paramParcel.writeInt(this.mcc);
    paramParcel.writeInt(this.mnc);
    fixUpLocaleList();
    paramParcel.writeParcelable((Parcelable)this.mLocaleList, paramInt);
    if (this.userSetLocale) {
      paramParcel.writeInt(1);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.touchscreen);
    paramParcel.writeInt(this.keyboard);
    paramParcel.writeInt(this.keyboardHidden);
    paramParcel.writeInt(this.hardKeyboardHidden);
    paramParcel.writeInt(this.navigation);
    paramParcel.writeInt(this.navigationHidden);
    paramParcel.writeInt(this.orientation);
    paramParcel.writeInt(this.screenLayout);
    paramParcel.writeInt(this.colorMode);
    paramParcel.writeInt(this.uiMode);
    paramParcel.writeInt(this.screenWidthDp);
    paramParcel.writeInt(this.screenHeightDp);
    paramParcel.writeInt(this.smallestScreenWidthDp);
    paramParcel.writeInt(this.densityDpi);
    paramParcel.writeInt(this.compatScreenWidthDp);
    paramParcel.writeInt(this.compatScreenHeightDp);
    paramParcel.writeInt(this.compatSmallestScreenWidthDp);
    paramParcel.writeValue(this.windowConfiguration);
    paramParcel.writeInt(this.assetsSeq);
    paramParcel.writeInt(this.seq);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface NativeConfig {}
  
  public static @interface Orientation {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/Configuration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */