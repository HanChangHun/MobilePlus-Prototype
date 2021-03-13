package android.telephony.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.ServiceState;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ApnSetting implements Parcelable {
  private static final Map<Integer, String> APN_TYPE_INT_MAP;
  
  private static final Map<String, Integer> APN_TYPE_STRING_MAP;
  
  public static final int AUTH_TYPE_CHAP = 2;
  
  public static final int AUTH_TYPE_NONE = 0;
  
  public static final int AUTH_TYPE_PAP = 1;
  
  public static final int AUTH_TYPE_PAP_OR_CHAP = 3;
  
  public static final Parcelable.Creator<ApnSetting> CREATOR;
  
  private static final String LOG_TAG = "ApnSetting";
  
  public static final int MVNO_TYPE_GID = 2;
  
  public static final int MVNO_TYPE_ICCID = 3;
  
  public static final int MVNO_TYPE_IMSI = 1;
  
  private static final Map<Integer, String> MVNO_TYPE_INT_MAP;
  
  public static final int MVNO_TYPE_SPN = 0;
  
  private static final Map<String, Integer> MVNO_TYPE_STRING_MAP;
  
  private static final Map<Integer, String> PROTOCOL_INT_MAP;
  
  public static final int PROTOCOL_IP = 0;
  
  public static final int PROTOCOL_IPV4V6 = 2;
  
  public static final int PROTOCOL_IPV6 = 1;
  
  public static final int PROTOCOL_NON_IP = 4;
  
  public static final int PROTOCOL_PPP = 3;
  
  private static final Map<String, Integer> PROTOCOL_STRING_MAP;
  
  public static final int PROTOCOL_UNSTRUCTURED = 5;
  
  public static final int TYPE_ALL = 255;
  
  public static final String TYPE_ALL_STRING = "*";
  
  public static final int TYPE_CBS = 128;
  
  public static final String TYPE_CBS_STRING = "cbs";
  
  public static final int TYPE_DEFAULT = 17;
  
  public static final String TYPE_DEFAULT_STRING = "default";
  
  public static final int TYPE_DUN = 8;
  
  public static final String TYPE_DUN_STRING = "dun";
  
  public static final int TYPE_EMERGENCY = 512;
  
  public static final String TYPE_EMERGENCY_STRING = "emergency";
  
  public static final int TYPE_FOTA = 32;
  
  public static final String TYPE_FOTA_STRING = "fota";
  
  public static final int TYPE_HIPRI = 16;
  
  public static final String TYPE_HIPRI_STRING = "hipri";
  
  public static final int TYPE_IA = 256;
  
  public static final String TYPE_IA_STRING = "ia";
  
  public static final int TYPE_IMS = 64;
  
  public static final String TYPE_IMS_STRING = "ims";
  
  public static final int TYPE_MCX = 1024;
  
  public static final String TYPE_MCX_STRING = "mcx";
  
  public static final int TYPE_MMS = 2;
  
  public static final String TYPE_MMS_STRING = "mms";
  
  public static final int TYPE_NONE = 0;
  
  public static final int TYPE_SUPL = 4;
  
  public static final String TYPE_SUPL_STRING = "supl";
  
  public static final int TYPE_XCAP = 2048;
  
  public static final String TYPE_XCAP_STRING = "xcap";
  
  public static final int UNSET_MTU = 0;
  
  private static final int UNSPECIFIED_INT = -1;
  
  private static final String UNSPECIFIED_STRING = "";
  
  private static final String V2_FORMAT_REGEX = "^\\[ApnSettingV2\\]\\s*";
  
  private static final String V3_FORMAT_REGEX = "^\\[ApnSettingV3\\]\\s*";
  
  private static final String V4_FORMAT_REGEX = "^\\[ApnSettingV4\\]\\s*";
  
  private static final String V5_FORMAT_REGEX = "^\\[ApnSettingV5\\]\\s*";
  
  private static final String V6_FORMAT_REGEX = "^\\[ApnSettingV6\\]\\s*";
  
  private static final String V7_FORMAT_REGEX = "^\\[ApnSettingV7\\]\\s*";
  
  private static final boolean VDBG = false;
  
  private final String mApnName;
  
  private final int mApnSetId;
  
  private final int mApnTypeBitmask;
  
  private final int mAuthType;
  
  private final boolean mCarrierEnabled;
  
  private final int mCarrierId;
  
  private final String mEntryName;
  
  private final int mId;
  
  private final int mMaxConns;
  
  private final int mMaxConnsTime;
  
  private final String mMmsProxyAddress;
  
  private final int mMmsProxyPort;
  
  private final Uri mMmsc;
  
  private final int mMtu;
  
  private final String mMvnoMatchData;
  
  private final int mMvnoType;
  
  private final int mNetworkTypeBitmask;
  
  private final String mOperatorNumeric;
  
  private final String mPassword;
  
  private boolean mPermanentFailed = false;
  
  private final boolean mPersistent;
  
  private final int mProfileId;
  
  private final int mProtocol;
  
  private final String mProxyAddress;
  
  private final int mProxyPort;
  
  private final int mRoamingProtocol;
  
  private final int mSkip464Xlat;
  
  private final String mUser;
  
  private final int mWaitTime;
  
  static {
    ArrayMap<String, Integer> arrayMap1 = new ArrayMap();
    APN_TYPE_STRING_MAP = (Map<String, Integer>)arrayMap1;
    arrayMap1.put("*", Integer.valueOf(255));
    Map<String, Integer> map1 = APN_TYPE_STRING_MAP;
    Integer integer2 = Integer.valueOf(17);
    map1.put("default", integer2);
    Map<String, Integer> map2 = APN_TYPE_STRING_MAP;
    Integer integer1 = Integer.valueOf(2);
    map2.put("mms", integer1);
    Map<String, Integer> map3 = APN_TYPE_STRING_MAP;
    Integer integer3 = Integer.valueOf(4);
    map3.put("supl", integer3);
    Map<String, Integer> map5 = APN_TYPE_STRING_MAP;
    Integer integer5 = Integer.valueOf(8);
    map5.put("dun", integer5);
    Map<String, Integer> map7 = APN_TYPE_STRING_MAP;
    Integer integer7 = Integer.valueOf(16);
    map7.put("hipri", integer7);
    Map<String, Integer> map8 = APN_TYPE_STRING_MAP;
    Integer integer8 = Integer.valueOf(32);
    map8.put("fota", integer8);
    Map<String, Integer> map9 = APN_TYPE_STRING_MAP;
    Integer integer9 = Integer.valueOf(64);
    map9.put("ims", integer9);
    Map<String, Integer> map10 = APN_TYPE_STRING_MAP;
    Integer integer10 = Integer.valueOf(128);
    map10.put("cbs", integer10);
    Map<String, Integer> map11 = APN_TYPE_STRING_MAP;
    Integer integer11 = Integer.valueOf(256);
    map11.put("ia", integer11);
    APN_TYPE_STRING_MAP.put("emergency", Integer.valueOf(512));
    APN_TYPE_STRING_MAP.put("mcx", Integer.valueOf(1024));
    APN_TYPE_STRING_MAP.put("xcap", Integer.valueOf(2048));
    ArrayMap<Integer, String> arrayMap5 = new ArrayMap();
    APN_TYPE_INT_MAP = (Map<Integer, String>)arrayMap5;
    arrayMap5.put(integer2, "default");
    APN_TYPE_INT_MAP.put(integer1, "mms");
    APN_TYPE_INT_MAP.put(integer3, "supl");
    APN_TYPE_INT_MAP.put(integer5, "dun");
    APN_TYPE_INT_MAP.put(integer7, "hipri");
    APN_TYPE_INT_MAP.put(integer8, "fota");
    APN_TYPE_INT_MAP.put(integer9, "ims");
    APN_TYPE_INT_MAP.put(integer10, "cbs");
    APN_TYPE_INT_MAP.put(integer11, "ia");
    APN_TYPE_INT_MAP.put(Integer.valueOf(512), "emergency");
    APN_TYPE_INT_MAP.put(Integer.valueOf(1024), "mcx");
    APN_TYPE_INT_MAP.put(Integer.valueOf(2048), "xcap");
    ArrayMap<String, Integer> arrayMap3 = new ArrayMap();
    PROTOCOL_STRING_MAP = (Map<String, Integer>)arrayMap3;
    integer2 = Integer.valueOf(0);
    arrayMap3.put("IP", integer2);
    Map<String, Integer> map4 = PROTOCOL_STRING_MAP;
    Integer integer4 = Integer.valueOf(1);
    map4.put("IPV6", integer4);
    PROTOCOL_STRING_MAP.put("IPV4V6", integer1);
    Map<String, Integer> map6 = PROTOCOL_STRING_MAP;
    Integer integer6 = Integer.valueOf(3);
    map6.put("PPP", integer6);
    PROTOCOL_STRING_MAP.put("NON-IP", integer3);
    PROTOCOL_STRING_MAP.put("UNSTRUCTURED", Integer.valueOf(5));
    ArrayMap<Integer, String> arrayMap4 = new ArrayMap();
    PROTOCOL_INT_MAP = (Map<Integer, String>)arrayMap4;
    arrayMap4.put(integer2, "IP");
    PROTOCOL_INT_MAP.put(integer4, "IPV6");
    PROTOCOL_INT_MAP.put(integer1, "IPV4V6");
    PROTOCOL_INT_MAP.put(integer6, "PPP");
    PROTOCOL_INT_MAP.put(integer3, "NON-IP");
    PROTOCOL_INT_MAP.put(Integer.valueOf(5), "UNSTRUCTURED");
    ArrayMap<String, Integer> arrayMap2 = new ArrayMap();
    MVNO_TYPE_STRING_MAP = (Map<String, Integer>)arrayMap2;
    arrayMap2.put("spn", integer2);
    MVNO_TYPE_STRING_MAP.put("imsi", integer4);
    MVNO_TYPE_STRING_MAP.put("gid", integer1);
    MVNO_TYPE_STRING_MAP.put("iccid", integer6);
    arrayMap2 = new ArrayMap();
    MVNO_TYPE_INT_MAP = (Map)arrayMap2;
    arrayMap2.put(integer2, "spn");
    MVNO_TYPE_INT_MAP.put(integer4, "imsi");
    MVNO_TYPE_INT_MAP.put(integer1, "gid");
    MVNO_TYPE_INT_MAP.put(integer6, "iccid");
    CREATOR = new Parcelable.Creator<ApnSetting>() {
        public ApnSetting createFromParcel(Parcel param1Parcel) {
          return ApnSetting.readFromParcel(param1Parcel);
        }
        
        public ApnSetting[] newArray(int param1Int) {
          return new ApnSetting[param1Int];
        }
      };
  }
  
  private ApnSetting(Builder paramBuilder) {
    this.mEntryName = paramBuilder.mEntryName;
    this.mApnName = paramBuilder.mApnName;
    this.mProxyAddress = paramBuilder.mProxyAddress;
    this.mProxyPort = paramBuilder.mProxyPort;
    this.mMmsc = paramBuilder.mMmsc;
    this.mMmsProxyAddress = paramBuilder.mMmsProxyAddress;
    this.mMmsProxyPort = paramBuilder.mMmsProxyPort;
    this.mUser = paramBuilder.mUser;
    this.mPassword = paramBuilder.mPassword;
    this.mAuthType = paramBuilder.mAuthType;
    this.mApnTypeBitmask = paramBuilder.mApnTypeBitmask;
    this.mId = paramBuilder.mId;
    this.mOperatorNumeric = paramBuilder.mOperatorNumeric;
    this.mProtocol = paramBuilder.mProtocol;
    this.mRoamingProtocol = paramBuilder.mRoamingProtocol;
    this.mMtu = paramBuilder.mMtu;
    this.mCarrierEnabled = paramBuilder.mCarrierEnabled;
    this.mNetworkTypeBitmask = paramBuilder.mNetworkTypeBitmask;
    this.mProfileId = paramBuilder.mProfileId;
    this.mPersistent = paramBuilder.mModemCognitive;
    this.mMaxConns = paramBuilder.mMaxConns;
    this.mWaitTime = paramBuilder.mWaitTime;
    this.mMaxConnsTime = paramBuilder.mMaxConnsTime;
    this.mMvnoType = paramBuilder.mMvnoType;
    this.mMvnoMatchData = paramBuilder.mMvnoMatchData;
    this.mApnSetId = paramBuilder.mApnSetId;
    this.mCarrierId = paramBuilder.mCarrierId;
    this.mSkip464Xlat = paramBuilder.mSkip464Xlat;
  }
  
  private static Uri UriFromString(String paramString) {
    Uri uri;
    if (TextUtils.isEmpty(paramString)) {
      paramString = null;
    } else {
      uri = Uri.parse(paramString);
    } 
    return uri;
  }
  
  private static String UriToString(Uri paramUri) {
    String str;
    if (paramUri == null) {
      paramUri = null;
    } else {
      str = paramUri.toString();
    } 
    return str;
  }
  
  public static List<ApnSetting> arrayFromString(String paramString) {
    ArrayList<ApnSetting> arrayList = new ArrayList();
    if (TextUtils.isEmpty(paramString))
      return arrayList; 
    String[] arrayOfString = paramString.split("\\s*;\\s*");
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      ApnSetting apnSetting = fromString(arrayOfString[b]);
      if (apnSetting != null)
        arrayList.add(apnSetting); 
    } 
    return arrayList;
  }
  
  public static ApnSetting fromString(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_0
    //   7: ldc_w '^\[ApnSettingV7\]\s*.*'
    //   10: invokevirtual matches : (Ljava/lang/String;)Z
    //   13: ifeq -> 31
    //   16: aload_0
    //   17: ldc '^\[ApnSettingV7\]\s*'
    //   19: ldc ''
    //   21: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   24: astore_0
    //   25: bipush #7
    //   27: istore_1
    //   28: goto -> 154
    //   31: aload_0
    //   32: ldc_w '^\[ApnSettingV6\]\s*.*'
    //   35: invokevirtual matches : (Ljava/lang/String;)Z
    //   38: ifeq -> 56
    //   41: aload_0
    //   42: ldc '^\[ApnSettingV6\]\s*'
    //   44: ldc ''
    //   46: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   49: astore_0
    //   50: bipush #6
    //   52: istore_1
    //   53: goto -> 154
    //   56: aload_0
    //   57: ldc_w '^\[ApnSettingV5\]\s*.*'
    //   60: invokevirtual matches : (Ljava/lang/String;)Z
    //   63: ifeq -> 80
    //   66: aload_0
    //   67: ldc '^\[ApnSettingV5\]\s*'
    //   69: ldc ''
    //   71: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   74: astore_0
    //   75: iconst_5
    //   76: istore_1
    //   77: goto -> 154
    //   80: aload_0
    //   81: ldc_w '^\[ApnSettingV4\]\s*.*'
    //   84: invokevirtual matches : (Ljava/lang/String;)Z
    //   87: ifeq -> 104
    //   90: aload_0
    //   91: ldc '^\[ApnSettingV4\]\s*'
    //   93: ldc ''
    //   95: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   98: astore_0
    //   99: iconst_4
    //   100: istore_1
    //   101: goto -> 154
    //   104: aload_0
    //   105: ldc_w '^\[ApnSettingV3\]\s*.*'
    //   108: invokevirtual matches : (Ljava/lang/String;)Z
    //   111: ifeq -> 128
    //   114: aload_0
    //   115: ldc '^\[ApnSettingV3\]\s*'
    //   117: ldc ''
    //   119: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   122: astore_0
    //   123: iconst_3
    //   124: istore_1
    //   125: goto -> 154
    //   128: aload_0
    //   129: ldc_w '^\[ApnSettingV2\]\s*.*'
    //   132: invokevirtual matches : (Ljava/lang/String;)Z
    //   135: ifeq -> 152
    //   138: aload_0
    //   139: ldc '^\[ApnSettingV2\]\s*'
    //   141: ldc ''
    //   143: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   146: astore_0
    //   147: iconst_2
    //   148: istore_1
    //   149: goto -> 154
    //   152: iconst_1
    //   153: istore_1
    //   154: aload_0
    //   155: ldc_w '\s*,\s*'
    //   158: iconst_m1
    //   159: invokevirtual split : (Ljava/lang/String;I)[Ljava/lang/String;
    //   162: astore_2
    //   163: aload_2
    //   164: arraylength
    //   165: bipush #14
    //   167: if_icmpge -> 172
    //   170: aconst_null
    //   171: areturn
    //   172: aload_2
    //   173: bipush #12
    //   175: aaload
    //   176: invokestatic parseInt : (Ljava/lang/String;)I
    //   179: istore_3
    //   180: goto -> 186
    //   183: astore_0
    //   184: iconst_0
    //   185: istore_3
    //   186: iconst_0
    //   187: istore #4
    //   189: iconst_0
    //   190: istore #5
    //   192: iconst_0
    //   193: istore #6
    //   195: iconst_0
    //   196: istore #7
    //   198: iconst_0
    //   199: istore #8
    //   201: iconst_0
    //   202: istore #9
    //   204: iconst_0
    //   205: istore #10
    //   207: iconst_0
    //   208: istore #11
    //   210: iconst_0
    //   211: istore #12
    //   213: ldc ''
    //   215: astore #13
    //   217: ldc ''
    //   219: astore_0
    //   220: iconst_0
    //   221: istore #14
    //   223: iconst_m1
    //   224: istore #15
    //   226: iload_1
    //   227: iconst_1
    //   228: if_icmpne -> 334
    //   231: aload_2
    //   232: arraylength
    //   233: bipush #13
    //   235: isub
    //   236: anewarray java/lang/String
    //   239: astore #16
    //   241: aload_2
    //   242: bipush #13
    //   244: aload #16
    //   246: iconst_0
    //   247: aload_2
    //   248: arraylength
    //   249: bipush #13
    //   251: isub
    //   252: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   255: getstatic android/telephony/data/ApnSetting.PROTOCOL_INT_MAP : Ljava/util/Map;
    //   258: iconst_0
    //   259: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   262: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   267: checkcast java/lang/String
    //   270: astore_0
    //   271: getstatic android/telephony/data/ApnSetting.PROTOCOL_INT_MAP : Ljava/util/Map;
    //   274: iconst_0
    //   275: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   278: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   283: checkcast java/lang/String
    //   286: astore #13
    //   288: iconst_1
    //   289: istore #6
    //   291: iconst_0
    //   292: istore_1
    //   293: iconst_0
    //   294: istore #17
    //   296: iconst_0
    //   297: istore #4
    //   299: iconst_0
    //   300: istore #9
    //   302: iconst_0
    //   303: istore #10
    //   305: iconst_0
    //   306: istore #5
    //   308: ldc ''
    //   310: astore #18
    //   312: ldc ''
    //   314: astore #19
    //   316: iconst_0
    //   317: istore #11
    //   319: iconst_m1
    //   320: istore #20
    //   322: iconst_m1
    //   323: istore #15
    //   325: iconst_0
    //   326: istore #7
    //   328: iconst_0
    //   329: istore #12
    //   331: goto -> 813
    //   334: aload_2
    //   335: arraylength
    //   336: bipush #18
    //   338: if_icmpge -> 343
    //   341: aconst_null
    //   342: areturn
    //   343: aload_2
    //   344: bipush #13
    //   346: aaload
    //   347: ldc_w '\s*\|\s*'
    //   350: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   353: astore #18
    //   355: aload_2
    //   356: bipush #14
    //   358: aaload
    //   359: astore #16
    //   361: aload_2
    //   362: bipush #15
    //   364: aaload
    //   365: astore #19
    //   367: aload_2
    //   368: bipush #16
    //   370: aaload
    //   371: invokestatic parseBoolean : (Ljava/lang/String;)Z
    //   374: istore #17
    //   376: aload_2
    //   377: bipush #17
    //   379: aaload
    //   380: invokestatic getBitmaskFromString : (Ljava/lang/String;)I
    //   383: istore #20
    //   385: iload #9
    //   387: istore_1
    //   388: iload #11
    //   390: istore #9
    //   392: aload_2
    //   393: arraylength
    //   394: bipush #22
    //   396: if_icmple -> 516
    //   399: aload_2
    //   400: bipush #19
    //   402: aaload
    //   403: invokestatic parseBoolean : (Ljava/lang/String;)Z
    //   406: istore #6
    //   408: iload #5
    //   410: istore #4
    //   412: iload #8
    //   414: istore #7
    //   416: iload #10
    //   418: istore_1
    //   419: aload_2
    //   420: bipush #18
    //   422: aaload
    //   423: invokestatic parseInt : (Ljava/lang/String;)I
    //   426: istore #5
    //   428: iload #5
    //   430: istore #4
    //   432: iload #8
    //   434: istore #7
    //   436: iload #10
    //   438: istore_1
    //   439: aload_2
    //   440: bipush #20
    //   442: aaload
    //   443: invokestatic parseInt : (Ljava/lang/String;)I
    //   446: istore #9
    //   448: iload #5
    //   450: istore #4
    //   452: iload #9
    //   454: istore #7
    //   456: iload #10
    //   458: istore_1
    //   459: aload_2
    //   460: bipush #21
    //   462: aaload
    //   463: invokestatic parseInt : (Ljava/lang/String;)I
    //   466: istore #10
    //   468: iload #5
    //   470: istore #4
    //   472: iload #9
    //   474: istore #7
    //   476: iload #10
    //   478: istore_1
    //   479: aload_2
    //   480: bipush #22
    //   482: aaload
    //   483: invokestatic parseInt : (Ljava/lang/String;)I
    //   486: istore #8
    //   488: iload #8
    //   490: istore #11
    //   492: iload #5
    //   494: istore #4
    //   496: iload #9
    //   498: istore #7
    //   500: iload #10
    //   502: istore_1
    //   503: iload #11
    //   505: istore #9
    //   507: goto -> 516
    //   510: astore #21
    //   512: iload #11
    //   514: istore #9
    //   516: iload #12
    //   518: istore #10
    //   520: aload_2
    //   521: arraylength
    //   522: bipush #23
    //   524: if_icmple -> 545
    //   527: aload_2
    //   528: bipush #23
    //   530: aaload
    //   531: invokestatic parseInt : (Ljava/lang/String;)I
    //   534: istore #10
    //   536: goto -> 545
    //   539: astore #21
    //   541: iload #12
    //   543: istore #10
    //   545: aload_2
    //   546: arraylength
    //   547: bipush #25
    //   549: if_icmple -> 563
    //   552: aload_2
    //   553: bipush #24
    //   555: aaload
    //   556: astore #13
    //   558: aload_2
    //   559: bipush #25
    //   561: aaload
    //   562: astore_0
    //   563: aload_2
    //   564: arraylength
    //   565: bipush #26
    //   567: if_icmple -> 582
    //   570: aload_2
    //   571: bipush #26
    //   573: aaload
    //   574: invokestatic getBitmaskFromString : (Ljava/lang/String;)I
    //   577: istore #12
    //   579: goto -> 585
    //   582: iconst_0
    //   583: istore #12
    //   585: iload #14
    //   587: istore #11
    //   589: aload_2
    //   590: arraylength
    //   591: bipush #27
    //   593: if_icmple -> 605
    //   596: aload_2
    //   597: bipush #27
    //   599: aaload
    //   600: invokestatic parseInt : (Ljava/lang/String;)I
    //   603: istore #11
    //   605: iload #15
    //   607: istore #5
    //   609: aload_2
    //   610: arraylength
    //   611: bipush #28
    //   613: if_icmple -> 625
    //   616: aload_2
    //   617: bipush #28
    //   619: aaload
    //   620: invokestatic parseInt : (Ljava/lang/String;)I
    //   623: istore #5
    //   625: aload_2
    //   626: arraylength
    //   627: bipush #29
    //   629: if_icmple -> 734
    //   632: aload_2
    //   633: bipush #29
    //   635: aaload
    //   636: invokestatic parseInt : (Ljava/lang/String;)I
    //   639: istore #22
    //   641: iload #4
    //   643: istore #23
    //   645: iload #6
    //   647: istore #24
    //   649: iload #7
    //   651: istore #4
    //   653: iload_1
    //   654: istore #15
    //   656: iload #9
    //   658: istore #14
    //   660: iload #10
    //   662: istore #8
    //   664: aload #13
    //   666: astore #21
    //   668: aload_0
    //   669: astore #25
    //   671: iload #5
    //   673: istore #26
    //   675: aload #19
    //   677: astore #13
    //   679: iload #20
    //   681: istore #7
    //   683: iload #17
    //   685: istore #6
    //   687: aload #16
    //   689: astore_0
    //   690: aload #18
    //   692: astore #16
    //   694: iload #23
    //   696: istore_1
    //   697: iload #24
    //   699: istore #17
    //   701: iload #15
    //   703: istore #9
    //   705: iload #14
    //   707: istore #10
    //   709: iload #8
    //   711: istore #5
    //   713: aload #21
    //   715: astore #18
    //   717: aload #25
    //   719: astore #19
    //   721: iload #26
    //   723: istore #20
    //   725: iload #22
    //   727: istore #15
    //   729: goto -> 813
    //   732: astore #21
    //   734: iload #4
    //   736: istore #14
    //   738: iload #6
    //   740: istore #24
    //   742: aload #13
    //   744: astore #21
    //   746: aload_0
    //   747: astore #25
    //   749: iconst_m1
    //   750: istore #15
    //   752: aload #19
    //   754: astore #13
    //   756: iload #20
    //   758: istore #8
    //   760: iload #17
    //   762: istore #6
    //   764: aload #18
    //   766: astore #27
    //   768: aload #16
    //   770: astore_0
    //   771: iload #5
    //   773: istore #20
    //   775: aload #25
    //   777: astore #19
    //   779: aload #21
    //   781: astore #18
    //   783: iload #10
    //   785: istore #5
    //   787: iload #9
    //   789: istore #10
    //   791: iload_1
    //   792: istore #9
    //   794: iload #7
    //   796: istore #4
    //   798: iload #24
    //   800: istore #17
    //   802: iload #14
    //   804: istore_1
    //   805: aload #27
    //   807: astore #16
    //   809: iload #8
    //   811: istore #7
    //   813: iload #12
    //   815: ifne -> 828
    //   818: iload #7
    //   820: invokestatic convertBearerBitmaskToNetworkTypeBitmask : (I)I
    //   823: istore #12
    //   825: goto -> 828
    //   828: new java/lang/StringBuilder
    //   831: dup
    //   832: invokespecial <init> : ()V
    //   835: astore #21
    //   837: aload #21
    //   839: aload_2
    //   840: bipush #10
    //   842: aaload
    //   843: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   846: pop
    //   847: aload #21
    //   849: aload_2
    //   850: bipush #11
    //   852: aaload
    //   853: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   856: pop
    //   857: iconst_m1
    //   858: aload #21
    //   860: invokevirtual toString : ()Ljava/lang/String;
    //   863: aload_2
    //   864: iconst_0
    //   865: aaload
    //   866: aload_2
    //   867: iconst_1
    //   868: aaload
    //   869: aload_2
    //   870: iconst_2
    //   871: aaload
    //   872: aload_2
    //   873: iconst_3
    //   874: aaload
    //   875: invokestatic portFromString : (Ljava/lang/String;)I
    //   878: aload_2
    //   879: bipush #7
    //   881: aaload
    //   882: invokestatic UriFromString : (Ljava/lang/String;)Landroid/net/Uri;
    //   885: aload_2
    //   886: bipush #8
    //   888: aaload
    //   889: aload_2
    //   890: bipush #9
    //   892: aaload
    //   893: invokestatic portFromString : (Ljava/lang/String;)I
    //   896: aload_2
    //   897: iconst_4
    //   898: aaload
    //   899: aload_2
    //   900: iconst_5
    //   901: aaload
    //   902: iload_3
    //   903: ldc_w ','
    //   906: aload #16
    //   908: invokestatic join : (Ljava/lang/CharSequence;[Ljava/lang/Object;)Ljava/lang/String;
    //   911: invokestatic getApnTypesBitmaskFromString : (Ljava/lang/String;)I
    //   914: aload_0
    //   915: invokestatic getProtocolIntFromString : (Ljava/lang/String;)I
    //   918: aload #13
    //   920: invokestatic getProtocolIntFromString : (Ljava/lang/String;)I
    //   923: iload #6
    //   925: iload #12
    //   927: iload_1
    //   928: iload #17
    //   930: iload #4
    //   932: iload #9
    //   934: iload #10
    //   936: iload #5
    //   938: aload #18
    //   940: invokestatic getMvnoTypeIntFromString : (Ljava/lang/String;)I
    //   943: aload #19
    //   945: iload #11
    //   947: iload #20
    //   949: iload #15
    //   951: invokestatic makeApnSetting : (ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILandroid/net/Uri;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;IIIIZIIZIIIIILjava/lang/String;III)Landroid/telephony/data/ApnSetting;
    //   954: areturn
    // Exception table:
    //   from	to	target	type
    //   172	180	183	java/lang/NumberFormatException
    //   419	428	510	java/lang/NumberFormatException
    //   439	448	510	java/lang/NumberFormatException
    //   459	468	510	java/lang/NumberFormatException
    //   479	488	510	java/lang/NumberFormatException
    //   527	536	539	java/lang/NumberFormatException
    //   632	641	732	java/lang/NumberFormatException
  }
  
  public static String getApnTypeString(int paramInt) {
    if (paramInt == 255)
      return "*"; 
    String str = APN_TYPE_INT_MAP.get(Integer.valueOf(paramInt));
    if (str == null)
      str = "Unknown"; 
    return str;
  }
  
  public static int getApnTypesBitmaskFromString(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return 255; 
    int i = 0;
    String[] arrayOfString = paramString.split(",");
    int j = arrayOfString.length;
    byte b = 0;
    while (b < j) {
      String str = arrayOfString[b];
      Integer integer = APN_TYPE_STRING_MAP.get(str.toLowerCase());
      int k = i;
      if (integer != null)
        k = i | integer.intValue(); 
      b++;
      i = k;
    } 
    return i;
  }
  
  public static String getApnTypesStringFromBitmask(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    for (Integer integer : APN_TYPE_INT_MAP.keySet()) {
      if ((integer.intValue() & paramInt) == integer.intValue())
        arrayList.add(APN_TYPE_INT_MAP.get(integer)); 
    } 
    return TextUtils.join(",", arrayList);
  }
  
  public static int getMvnoTypeIntFromString(String paramString) {
    int i;
    if (!TextUtils.isEmpty(paramString))
      paramString = paramString.toLowerCase(); 
    Integer integer = MVNO_TYPE_STRING_MAP.get(paramString);
    if (integer == null) {
      i = -1;
    } else {
      i = integer.intValue();
    } 
    return i;
  }
  
  public static String getMvnoTypeStringFromInt(int paramInt) {
    String str = MVNO_TYPE_INT_MAP.get(Integer.valueOf(paramInt));
    if (str == null)
      str = ""; 
    return str;
  }
  
  public static int getProtocolIntFromString(String paramString) {
    int i;
    Integer integer = PROTOCOL_STRING_MAP.get(paramString);
    if (integer == null) {
      i = -1;
    } else {
      i = integer.intValue();
    } 
    return i;
  }
  
  public static String getProtocolStringFromInt(int paramInt) {
    String str = PROTOCOL_INT_MAP.get(Integer.valueOf(paramInt));
    if (str == null)
      str = ""; 
    return str;
  }
  
  private boolean hasApnType(int paramInt) {
    boolean bool;
    if ((this.mApnTypeBitmask & paramInt) == paramInt) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static InetAddress inetAddressFromString(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return null; 
    try {
      return InetAddress.getByName(paramString);
    } catch (UnknownHostException unknownHostException) {
      Log.e("ApnSetting", "Can't parse InetAddress from string: unknown host.");
      return null;
    } 
  }
  
  public static String inetAddressToString(InetAddress paramInetAddress) {
    if (paramInetAddress == null)
      return null; 
    String str2 = paramInetAddress.toString();
    if (TextUtils.isEmpty(str2))
      return null; 
    String str1 = str2.substring(0, str2.indexOf("/"));
    str2 = str2.substring(str2.indexOf("/") + 1);
    if (TextUtils.isEmpty(str1) && TextUtils.isEmpty(str2))
      return null; 
    if (TextUtils.isEmpty(str1))
      str1 = str2; 
    return str1;
  }
  
  public static ApnSetting makeApnSetting(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2, Uri paramUri, String paramString5, int paramInt3, String paramString6, String paramString7, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean1, int paramInt8, int paramInt9, boolean paramBoolean2, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, String paramString8) {
    return makeApnSetting(paramInt1, paramString1, paramString2, paramString3, paramString4, paramInt2, paramUri, paramString5, paramInt3, paramString6, paramString7, paramInt4, paramInt5, paramInt6, paramInt7, paramBoolean1, paramInt8, paramInt9, paramBoolean2, paramInt10, paramInt11, paramInt12, paramInt13, paramInt14, paramString8, 0, -1, -1);
  }
  
  public static ApnSetting makeApnSetting(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2, Uri paramUri, String paramString5, int paramInt3, String paramString6, String paramString7, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean1, int paramInt8, int paramInt9, boolean paramBoolean2, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, String paramString8, int paramInt15, int paramInt16, int paramInt17) {
    return (new Builder()).setId(paramInt1).setOperatorNumeric(paramString1).setEntryName(paramString2).setApnName(paramString3).setProxyAddress(paramString4).setProxyPort(paramInt2).setMmsc(paramUri).setMmsProxyAddress(paramString5).setMmsProxyPort(paramInt3).setUser(paramString6).setPassword(paramString7).setAuthType(paramInt4).setApnTypeBitmask(paramInt5).setProtocol(paramInt6).setRoamingProtocol(paramInt7).setCarrierEnabled(paramBoolean1).setNetworkTypeBitmask(paramInt8).setProfileId(paramInt9).setModemCognitive(paramBoolean2).setMaxConns(paramInt10).setWaitTime(paramInt11).setMaxConnsTime(paramInt12).setMtu(paramInt13).setMvnoType(paramInt14).setMvnoMatchData(paramString8).setApnSetId(paramInt15).setCarrierId(paramInt16).setSkip464Xlat(paramInt17).buildWithoutCheck();
  }
  
  public static ApnSetting makeApnSetting(Cursor paramCursor) {
    boolean bool1;
    boolean bool2;
    int i = getApnTypesBitmaskFromString(paramCursor.getString(paramCursor.getColumnIndexOrThrow("type")));
    int j = paramCursor.getInt(paramCursor.getColumnIndexOrThrow("network_type_bitmask"));
    if (j == 0) {
      j = paramCursor.getInt(paramCursor.getColumnIndexOrThrow("bearer_bitmask"));
      j = ServiceState.convertBearerBitmaskToNetworkTypeBitmask(j);
    } 
    int k = paramCursor.getInt(paramCursor.getColumnIndexOrThrow("_id"));
    String str1 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("numeric"));
    String str2 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("name"));
    String str3 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("apn"));
    String str4 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("proxy"));
    int m = portFromString(paramCursor.getString(paramCursor.getColumnIndexOrThrow("port")));
    Uri uri = UriFromString(paramCursor.getString(paramCursor.getColumnIndexOrThrow("mmsc")));
    String str5 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("mmsproxy"));
    int n = portFromString(paramCursor.getString(paramCursor.getColumnIndexOrThrow("mmsport")));
    String str6 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("user"));
    String str7 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("password"));
    int i1 = paramCursor.getInt(paramCursor.getColumnIndexOrThrow("authtype"));
    int i2 = getProtocolIntFromString(paramCursor.getString(paramCursor.getColumnIndexOrThrow("protocol")));
    int i3 = getProtocolIntFromString(paramCursor.getString(paramCursor.getColumnIndexOrThrow("roaming_protocol")));
    if (paramCursor.getInt(paramCursor.getColumnIndexOrThrow("carrier_enabled")) == 1) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    int i4 = paramCursor.getInt(paramCursor.getColumnIndexOrThrow("profile_id"));
    if (paramCursor.getInt(paramCursor.getColumnIndexOrThrow("modem_cognitive")) == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    return makeApnSetting(k, str1, str2, str3, str4, m, uri, str5, n, str6, str7, i1, i, i2, i3, bool1, j, i4, bool2, paramCursor.getInt(paramCursor.getColumnIndexOrThrow("max_conns")), paramCursor.getInt(paramCursor.getColumnIndexOrThrow("wait_time")), paramCursor.getInt(paramCursor.getColumnIndexOrThrow("max_conns_time")), paramCursor.getInt(paramCursor.getColumnIndexOrThrow("mtu")), getMvnoTypeIntFromString(paramCursor.getString(paramCursor.getColumnIndexOrThrow("mvno_type"))), paramCursor.getString(paramCursor.getColumnIndexOrThrow("mvno_match_data")), paramCursor.getInt(paramCursor.getColumnIndexOrThrow("apn_set_id")), paramCursor.getInt(paramCursor.getColumnIndexOrThrow("carrier_id")), paramCursor.getInt(paramCursor.getColumnIndexOrThrow("skip_464xlat")));
  }
  
  public static ApnSetting makeApnSetting(ApnSetting paramApnSetting) {
    return makeApnSetting(paramApnSetting.mId, paramApnSetting.mOperatorNumeric, paramApnSetting.mEntryName, paramApnSetting.mApnName, paramApnSetting.mProxyAddress, paramApnSetting.mProxyPort, paramApnSetting.mMmsc, paramApnSetting.mMmsProxyAddress, paramApnSetting.mMmsProxyPort, paramApnSetting.mUser, paramApnSetting.mPassword, paramApnSetting.mAuthType, paramApnSetting.mApnTypeBitmask, paramApnSetting.mProtocol, paramApnSetting.mRoamingProtocol, paramApnSetting.mCarrierEnabled, paramApnSetting.mNetworkTypeBitmask, paramApnSetting.mProfileId, paramApnSetting.mPersistent, paramApnSetting.mMaxConns, paramApnSetting.mWaitTime, paramApnSetting.mMaxConnsTime, paramApnSetting.mMtu, paramApnSetting.mMvnoType, paramApnSetting.mMvnoMatchData, paramApnSetting.mApnSetId, paramApnSetting.mCarrierId, paramApnSetting.mSkip464Xlat);
  }
  
  private String nullToEmpty(String paramString) {
    if (paramString == null)
      paramString = ""; 
    return paramString;
  }
  
  private static int portFromString(String paramString) {
    byte b = -1;
    int i = b;
    if (!TextUtils.isEmpty(paramString))
      try {
        i = Integer.parseInt(paramString);
      } catch (NumberFormatException numberFormatException) {
        Log.e("ApnSetting", "Can't parse port from String");
        i = b;
      }  
    return i;
  }
  
  private static String portToString(int paramInt) {
    String str;
    if (paramInt == -1) {
      str = null;
    } else {
      str = Integer.toString(paramInt);
    } 
    return str;
  }
  
  private static ApnSetting readFromParcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    String str3 = paramParcel.readString();
    String str4 = paramParcel.readString();
    int j = paramParcel.readInt();
    Uri uri = (Uri)paramParcel.readValue(Uri.class.getClassLoader());
    String str5 = paramParcel.readString();
    int k = paramParcel.readInt();
    String str6 = paramParcel.readString();
    String str7 = paramParcel.readString();
    int m = paramParcel.readInt();
    int n = paramParcel.readInt();
    int i1 = paramParcel.readInt();
    int i2 = paramParcel.readInt();
    boolean bool = paramParcel.readBoolean();
    int i3 = paramParcel.readInt();
    return makeApnSetting(i, str1, str2, str3, str4, j, uri, str5, k, str6, str7, m, n, i1, i2, bool, paramParcel.readInt(), 0, false, 0, 0, 0, 0, i3, null, paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt());
  }
  
  private boolean typeSameAny(ApnSetting paramApnSetting1, ApnSetting paramApnSetting2) {
    return ((paramApnSetting1.mApnTypeBitmask & paramApnSetting2.mApnTypeBitmask) != 0);
  }
  
  private boolean xorEquals(Object paramObject1, Object paramObject2) {
    return (paramObject1 == null || paramObject2 == null || paramObject1.equals(paramObject2));
  }
  
  private boolean xorEqualsInt(int paramInt1, int paramInt2) {
    return (paramInt1 == -1 || paramInt2 == -1 || Objects.equals(Integer.valueOf(paramInt1), Integer.valueOf(paramInt2)));
  }
  
  private boolean xorEqualsString(String paramString1, String paramString2) {
    return (TextUtils.isEmpty(paramString1) || TextUtils.isEmpty(paramString2) || paramString1.equals(paramString2));
  }
  
  public boolean canHandleType(int paramInt) {
    return !this.mCarrierEnabled ? false : (hasApnType(paramInt));
  }
  
  public boolean canSupportNetworkType(int paramInt) {
    return (paramInt == 16 && (this.mNetworkTypeBitmask & 0x3L) != 0L) ? true : ServiceState.bitmaskHasTech(this.mNetworkTypeBitmask, paramInt);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof ApnSetting;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    if (this.mEntryName.equals(((ApnSetting)paramObject).mEntryName) && Objects.equals(Integer.valueOf(this.mId), Integer.valueOf(((ApnSetting)paramObject).mId)) && Objects.equals(this.mOperatorNumeric, ((ApnSetting)paramObject).mOperatorNumeric) && Objects.equals(this.mApnName, ((ApnSetting)paramObject).mApnName) && Objects.equals(this.mProxyAddress, ((ApnSetting)paramObject).mProxyAddress) && Objects.equals(this.mMmsc, ((ApnSetting)paramObject).mMmsc) && Objects.equals(this.mMmsProxyAddress, ((ApnSetting)paramObject).mMmsProxyAddress) && Objects.equals(Integer.valueOf(this.mMmsProxyPort), Integer.valueOf(((ApnSetting)paramObject).mMmsProxyPort)) && Objects.equals(Integer.valueOf(this.mProxyPort), Integer.valueOf(((ApnSetting)paramObject).mProxyPort)) && Objects.equals(this.mUser, ((ApnSetting)paramObject).mUser) && Objects.equals(this.mPassword, ((ApnSetting)paramObject).mPassword) && Objects.equals(Integer.valueOf(this.mAuthType), Integer.valueOf(((ApnSetting)paramObject).mAuthType)) && Objects.equals(Integer.valueOf(this.mApnTypeBitmask), Integer.valueOf(((ApnSetting)paramObject).mApnTypeBitmask)) && Objects.equals(Integer.valueOf(this.mProtocol), Integer.valueOf(((ApnSetting)paramObject).mProtocol)) && Objects.equals(Integer.valueOf(this.mRoamingProtocol), Integer.valueOf(((ApnSetting)paramObject).mRoamingProtocol)) && Objects.equals(Boolean.valueOf(this.mCarrierEnabled), Boolean.valueOf(((ApnSetting)paramObject).mCarrierEnabled)) && Objects.equals(Integer.valueOf(this.mProfileId), Integer.valueOf(((ApnSetting)paramObject).mProfileId)) && Objects.equals(Boolean.valueOf(this.mPersistent), Boolean.valueOf(((ApnSetting)paramObject).mPersistent)) && Objects.equals(Integer.valueOf(this.mMaxConns), Integer.valueOf(((ApnSetting)paramObject).mMaxConns)) && Objects.equals(Integer.valueOf(this.mWaitTime), Integer.valueOf(((ApnSetting)paramObject).mWaitTime)) && Objects.equals(Integer.valueOf(this.mMaxConnsTime), Integer.valueOf(((ApnSetting)paramObject).mMaxConnsTime)) && Objects.equals(Integer.valueOf(this.mMtu), Integer.valueOf(((ApnSetting)paramObject).mMtu)) && Objects.equals(Integer.valueOf(this.mMvnoType), Integer.valueOf(((ApnSetting)paramObject).mMvnoType)) && Objects.equals(this.mMvnoMatchData, ((ApnSetting)paramObject).mMvnoMatchData) && Objects.equals(Integer.valueOf(this.mNetworkTypeBitmask), Integer.valueOf(((ApnSetting)paramObject).mNetworkTypeBitmask)) && Objects.equals(Integer.valueOf(this.mApnSetId), Integer.valueOf(((ApnSetting)paramObject).mApnSetId)) && Objects.equals(Integer.valueOf(this.mCarrierId), Integer.valueOf(((ApnSetting)paramObject).mCarrierId)) && Objects.equals(Integer.valueOf(this.mSkip464Xlat), Integer.valueOf(((ApnSetting)paramObject).mSkip464Xlat)))
      bool1 = true; 
    return bool1;
  }
  
  public boolean equals(Object paramObject, boolean paramBoolean) {
    boolean bool = paramObject instanceof ApnSetting;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    if (this.mEntryName.equals(((ApnSetting)paramObject).mEntryName) && Objects.equals(this.mOperatorNumeric, ((ApnSetting)paramObject).mOperatorNumeric) && Objects.equals(this.mApnName, ((ApnSetting)paramObject).mApnName) && Objects.equals(this.mProxyAddress, ((ApnSetting)paramObject).mProxyAddress) && Objects.equals(this.mMmsc, ((ApnSetting)paramObject).mMmsc) && Objects.equals(this.mMmsProxyAddress, ((ApnSetting)paramObject).mMmsProxyAddress) && Objects.equals(Integer.valueOf(this.mMmsProxyPort), Integer.valueOf(((ApnSetting)paramObject).mMmsProxyPort)) && Objects.equals(Integer.valueOf(this.mProxyPort), Integer.valueOf(((ApnSetting)paramObject).mProxyPort)) && Objects.equals(this.mUser, ((ApnSetting)paramObject).mUser) && Objects.equals(this.mPassword, ((ApnSetting)paramObject).mPassword) && Objects.equals(Integer.valueOf(this.mAuthType), Integer.valueOf(((ApnSetting)paramObject).mAuthType)) && Objects.equals(Integer.valueOf(this.mApnTypeBitmask), Integer.valueOf(((ApnSetting)paramObject).mApnTypeBitmask)) && (paramBoolean || Objects.equals(Integer.valueOf(this.mProtocol), Integer.valueOf(((ApnSetting)paramObject).mProtocol))) && (!paramBoolean || Objects.equals(Integer.valueOf(this.mRoamingProtocol), Integer.valueOf(((ApnSetting)paramObject).mRoamingProtocol))) && Objects.equals(Boolean.valueOf(this.mCarrierEnabled), Boolean.valueOf(((ApnSetting)paramObject).mCarrierEnabled)) && Objects.equals(Integer.valueOf(this.mProfileId), Integer.valueOf(((ApnSetting)paramObject).mProfileId)) && Objects.equals(Boolean.valueOf(this.mPersistent), Boolean.valueOf(((ApnSetting)paramObject).mPersistent)) && Objects.equals(Integer.valueOf(this.mMaxConns), Integer.valueOf(((ApnSetting)paramObject).mMaxConns)) && Objects.equals(Integer.valueOf(this.mWaitTime), Integer.valueOf(((ApnSetting)paramObject).mWaitTime)) && Objects.equals(Integer.valueOf(this.mMaxConnsTime), Integer.valueOf(((ApnSetting)paramObject).mMaxConnsTime)) && Objects.equals(Integer.valueOf(this.mMtu), Integer.valueOf(((ApnSetting)paramObject).mMtu)) && Objects.equals(Integer.valueOf(this.mMvnoType), Integer.valueOf(((ApnSetting)paramObject).mMvnoType)) && Objects.equals(this.mMvnoMatchData, ((ApnSetting)paramObject).mMvnoMatchData) && Objects.equals(Integer.valueOf(this.mApnSetId), Integer.valueOf(((ApnSetting)paramObject).mApnSetId)) && Objects.equals(Integer.valueOf(this.mCarrierId), Integer.valueOf(((ApnSetting)paramObject).mCarrierId)) && Objects.equals(Integer.valueOf(this.mSkip464Xlat), Integer.valueOf(((ApnSetting)paramObject).mSkip464Xlat))) {
      paramBoolean = true;
    } else {
      paramBoolean = bool1;
    } 
    return paramBoolean;
  }
  
  public String getApnName() {
    return this.mApnName;
  }
  
  public int getApnSetId() {
    return this.mApnSetId;
  }
  
  public int getApnTypeBitmask() {
    return this.mApnTypeBitmask;
  }
  
  public List<Integer> getApnTypes() {
    ArrayList<Integer> arrayList = new ArrayList();
    for (Integer integer : APN_TYPE_INT_MAP.keySet()) {
      if ((this.mApnTypeBitmask & integer.intValue()) == integer.intValue())
        arrayList.add(integer); 
    } 
    return arrayList;
  }
  
  public int getAuthType() {
    return this.mAuthType;
  }
  
  public int getCarrierId() {
    return this.mCarrierId;
  }
  
  public String getEntryName() {
    return this.mEntryName;
  }
  
  public int getId() {
    return this.mId;
  }
  
  public int getMaxConns() {
    return this.mMaxConns;
  }
  
  public int getMaxConnsTime() {
    return this.mMaxConnsTime;
  }
  
  @Deprecated
  public InetAddress getMmsProxyAddress() {
    return inetAddressFromString(this.mMmsProxyAddress);
  }
  
  public String getMmsProxyAddressAsString() {
    return this.mMmsProxyAddress;
  }
  
  public int getMmsProxyPort() {
    return this.mMmsProxyPort;
  }
  
  public Uri getMmsc() {
    return this.mMmsc;
  }
  
  public int getMtu() {
    return this.mMtu;
  }
  
  public String getMvnoMatchData() {
    return this.mMvnoMatchData;
  }
  
  public int getMvnoType() {
    return this.mMvnoType;
  }
  
  public int getNetworkTypeBitmask() {
    return this.mNetworkTypeBitmask;
  }
  
  public String getOperatorNumeric() {
    return this.mOperatorNumeric;
  }
  
  public String getPassword() {
    return this.mPassword;
  }
  
  public boolean getPermanentFailed() {
    return this.mPermanentFailed;
  }
  
  public int getProfileId() {
    return this.mProfileId;
  }
  
  public int getProtocol() {
    return this.mProtocol;
  }
  
  @Deprecated
  public InetAddress getProxyAddress() {
    return inetAddressFromString(this.mProxyAddress);
  }
  
  public String getProxyAddressAsString() {
    return this.mProxyAddress;
  }
  
  public int getProxyPort() {
    return this.mProxyPort;
  }
  
  public int getRoamingProtocol() {
    return this.mRoamingProtocol;
  }
  
  public int getSkip464Xlat() {
    return this.mSkip464Xlat;
  }
  
  public String getUser() {
    return this.mUser;
  }
  
  public int getWaitTime() {
    return this.mWaitTime;
  }
  
  public boolean hasMvnoParams() {
    boolean bool;
    if (!TextUtils.isEmpty(getMvnoTypeStringFromInt(this.mMvnoType)) && !TextUtils.isEmpty(this.mMvnoMatchData)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isEmergencyApn() {
    return hasApnType(512);
  }
  
  public boolean isEnabled() {
    return this.mCarrierEnabled;
  }
  
  public boolean isPersistent() {
    return this.mPersistent;
  }
  
  public void setPermanentFailed(boolean paramBoolean) {
    this.mPermanentFailed = paramBoolean;
  }
  
  public boolean similar(ApnSetting paramApnSetting) {
    boolean bool;
    if (!canHandleType(8) && !paramApnSetting.canHandleType(8) && Objects.equals(this.mApnName, paramApnSetting.mApnName) && !typeSameAny(this, paramApnSetting) && xorEqualsString(this.mProxyAddress, paramApnSetting.mProxyAddress) && xorEqualsInt(this.mProxyPort, paramApnSetting.mProxyPort) && xorEquals(Integer.valueOf(this.mProtocol), Integer.valueOf(paramApnSetting.mProtocol)) && xorEquals(Integer.valueOf(this.mRoamingProtocol), Integer.valueOf(paramApnSetting.mRoamingProtocol)) && Objects.equals(Boolean.valueOf(this.mCarrierEnabled), Boolean.valueOf(paramApnSetting.mCarrierEnabled)) && Objects.equals(Integer.valueOf(this.mProfileId), Integer.valueOf(paramApnSetting.mProfileId)) && Objects.equals(Integer.valueOf(this.mMvnoType), Integer.valueOf(paramApnSetting.mMvnoType)) && Objects.equals(this.mMvnoMatchData, paramApnSetting.mMvnoMatchData) && xorEquals(this.mMmsc, paramApnSetting.mMmsc) && xorEqualsString(this.mMmsProxyAddress, paramApnSetting.mMmsProxyAddress) && xorEqualsInt(this.mMmsProxyPort, paramApnSetting.mMmsProxyPort) && Objects.equals(Integer.valueOf(this.mNetworkTypeBitmask), Integer.valueOf(paramApnSetting.mNetworkTypeBitmask)) && Objects.equals(Integer.valueOf(this.mApnSetId), Integer.valueOf(paramApnSetting.mApnSetId)) && Objects.equals(Integer.valueOf(this.mCarrierId), Integer.valueOf(paramApnSetting.mCarrierId)) && Objects.equals(Integer.valueOf(this.mSkip464Xlat), Integer.valueOf(paramApnSetting.mSkip464Xlat))) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public ContentValues toContentValues() {
    ContentValues contentValues = new ContentValues();
    contentValues.put("numeric", nullToEmpty(this.mOperatorNumeric));
    contentValues.put("name", nullToEmpty(this.mEntryName));
    contentValues.put("apn", nullToEmpty(this.mApnName));
    contentValues.put("proxy", nullToEmpty(this.mProxyAddress));
    contentValues.put("port", nullToEmpty(portToString(this.mProxyPort)));
    contentValues.put("mmsc", nullToEmpty(UriToString(this.mMmsc)));
    contentValues.put("mmsport", nullToEmpty(portToString(this.mMmsProxyPort)));
    contentValues.put("mmsproxy", nullToEmpty(this.mMmsProxyAddress));
    contentValues.put("user", nullToEmpty(this.mUser));
    contentValues.put("password", nullToEmpty(this.mPassword));
    contentValues.put("authtype", Integer.valueOf(this.mAuthType));
    contentValues.put("type", nullToEmpty(getApnTypesStringFromBitmask(this.mApnTypeBitmask)));
    contentValues.put("protocol", getProtocolStringFromInt(this.mProtocol));
    contentValues.put("roaming_protocol", getProtocolStringFromInt(this.mRoamingProtocol));
    contentValues.put("carrier_enabled", Boolean.valueOf(this.mCarrierEnabled));
    contentValues.put("mvno_type", getMvnoTypeStringFromInt(this.mMvnoType));
    contentValues.put("network_type_bitmask", Integer.valueOf(this.mNetworkTypeBitmask));
    contentValues.put("carrier_id", Integer.valueOf(this.mCarrierId));
    contentValues.put("skip_464xlat", Integer.valueOf(this.mSkip464Xlat));
    return contentValues;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[ApnSettingV7] ");
    stringBuilder.append(this.mEntryName);
    stringBuilder.append(", ");
    stringBuilder.append(this.mId);
    stringBuilder.append(", ");
    stringBuilder.append(this.mOperatorNumeric);
    stringBuilder.append(", ");
    stringBuilder.append(this.mApnName);
    stringBuilder.append(", ");
    stringBuilder.append(this.mProxyAddress);
    stringBuilder.append(", ");
    stringBuilder.append(UriToString(this.mMmsc));
    stringBuilder.append(", ");
    stringBuilder.append(this.mMmsProxyAddress);
    stringBuilder.append(", ");
    stringBuilder.append(portToString(this.mMmsProxyPort));
    stringBuilder.append(", ");
    stringBuilder.append(portToString(this.mProxyPort));
    stringBuilder.append(", ");
    stringBuilder.append(this.mAuthType);
    stringBuilder.append(", ");
    stringBuilder.append(TextUtils.join(" | ", (Object[])getApnTypesStringFromBitmask(this.mApnTypeBitmask).split(",")));
    stringBuilder.append(", ");
    stringBuilder.append(PROTOCOL_INT_MAP.get(Integer.valueOf(this.mProtocol)));
    stringBuilder.append(", ");
    stringBuilder.append(PROTOCOL_INT_MAP.get(Integer.valueOf(this.mRoamingProtocol)));
    stringBuilder.append(", ");
    stringBuilder.append(this.mCarrierEnabled);
    stringBuilder.append(", ");
    stringBuilder.append(this.mProfileId);
    stringBuilder.append(", ");
    stringBuilder.append(this.mPersistent);
    stringBuilder.append(", ");
    stringBuilder.append(this.mMaxConns);
    stringBuilder.append(", ");
    stringBuilder.append(this.mWaitTime);
    stringBuilder.append(", ");
    stringBuilder.append(this.mMaxConnsTime);
    stringBuilder.append(", ");
    stringBuilder.append(this.mMtu);
    stringBuilder.append(", ");
    stringBuilder.append(MVNO_TYPE_INT_MAP.get(Integer.valueOf(this.mMvnoType)));
    stringBuilder.append(", ");
    stringBuilder.append(this.mMvnoMatchData);
    stringBuilder.append(", ");
    stringBuilder.append(this.mPermanentFailed);
    stringBuilder.append(", ");
    stringBuilder.append(this.mNetworkTypeBitmask);
    stringBuilder.append(", ");
    stringBuilder.append(this.mApnSetId);
    stringBuilder.append(", ");
    stringBuilder.append(this.mCarrierId);
    stringBuilder.append(", ");
    stringBuilder.append(this.mSkip464Xlat);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mId);
    paramParcel.writeString(this.mOperatorNumeric);
    paramParcel.writeString(this.mEntryName);
    paramParcel.writeString(this.mApnName);
    paramParcel.writeString(this.mProxyAddress);
    paramParcel.writeInt(this.mProxyPort);
    paramParcel.writeValue(this.mMmsc);
    paramParcel.writeString(this.mMmsProxyAddress);
    paramParcel.writeInt(this.mMmsProxyPort);
    paramParcel.writeString(this.mUser);
    paramParcel.writeString(this.mPassword);
    paramParcel.writeInt(this.mAuthType);
    paramParcel.writeInt(this.mApnTypeBitmask);
    paramParcel.writeInt(this.mProtocol);
    paramParcel.writeInt(this.mRoamingProtocol);
    paramParcel.writeBoolean(this.mCarrierEnabled);
    paramParcel.writeInt(this.mMvnoType);
    paramParcel.writeInt(this.mNetworkTypeBitmask);
    paramParcel.writeInt(this.mApnSetId);
    paramParcel.writeInt(this.mCarrierId);
    paramParcel.writeInt(this.mSkip464Xlat);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AuthType {}
  
  public static class Builder {
    private String mApnName;
    
    private int mApnSetId;
    
    private int mApnTypeBitmask;
    
    private int mAuthType;
    
    private boolean mCarrierEnabled;
    
    private int mCarrierId = -1;
    
    private String mEntryName;
    
    private int mId;
    
    private int mMaxConns;
    
    private int mMaxConnsTime;
    
    private String mMmsProxyAddress;
    
    private int mMmsProxyPort = -1;
    
    private Uri mMmsc;
    
    private boolean mModemCognitive;
    
    private int mMtu;
    
    private String mMvnoMatchData;
    
    private int mMvnoType = -1;
    
    private int mNetworkTypeBitmask;
    
    private String mOperatorNumeric;
    
    private String mPassword;
    
    private int mProfileId;
    
    private int mProtocol = -1;
    
    private String mProxyAddress;
    
    private int mProxyPort = -1;
    
    private int mRoamingProtocol = -1;
    
    private int mSkip464Xlat = -1;
    
    private String mUser;
    
    private int mWaitTime;
    
    private Builder setId(int param1Int) {
      this.mId = param1Int;
      return this;
    }
    
    public ApnSetting build() {
      return ((this.mApnTypeBitmask & 0xFFF) == 0 || TextUtils.isEmpty(this.mApnName) || TextUtils.isEmpty(this.mEntryName)) ? null : new ApnSetting(this);
    }
    
    public ApnSetting buildWithoutCheck() {
      return new ApnSetting(this);
    }
    
    public Builder setApnName(String param1String) {
      this.mApnName = param1String;
      return this;
    }
    
    public Builder setApnSetId(int param1Int) {
      this.mApnSetId = param1Int;
      return this;
    }
    
    public Builder setApnTypeBitmask(int param1Int) {
      this.mApnTypeBitmask = param1Int;
      return this;
    }
    
    public Builder setAuthType(int param1Int) {
      this.mAuthType = param1Int;
      return this;
    }
    
    public Builder setCarrierEnabled(boolean param1Boolean) {
      this.mCarrierEnabled = param1Boolean;
      return this;
    }
    
    public Builder setCarrierId(int param1Int) {
      this.mCarrierId = param1Int;
      return this;
    }
    
    public Builder setEntryName(String param1String) {
      this.mEntryName = param1String;
      return this;
    }
    
    public Builder setMaxConns(int param1Int) {
      this.mMaxConns = param1Int;
      return this;
    }
    
    public Builder setMaxConnsTime(int param1Int) {
      this.mMaxConnsTime = param1Int;
      return this;
    }
    
    public Builder setMmsProxyAddress(String param1String) {
      this.mMmsProxyAddress = param1String;
      return this;
    }
    
    @Deprecated
    public Builder setMmsProxyAddress(InetAddress param1InetAddress) {
      this.mMmsProxyAddress = ApnSetting.inetAddressToString(param1InetAddress);
      return this;
    }
    
    public Builder setMmsProxyPort(int param1Int) {
      this.mMmsProxyPort = param1Int;
      return this;
    }
    
    public Builder setMmsc(Uri param1Uri) {
      this.mMmsc = param1Uri;
      return this;
    }
    
    public Builder setModemCognitive(boolean param1Boolean) {
      this.mModemCognitive = param1Boolean;
      return this;
    }
    
    public Builder setMtu(int param1Int) {
      this.mMtu = param1Int;
      return this;
    }
    
    public Builder setMvnoMatchData(String param1String) {
      this.mMvnoMatchData = param1String;
      return this;
    }
    
    public Builder setMvnoType(int param1Int) {
      this.mMvnoType = param1Int;
      return this;
    }
    
    public Builder setNetworkTypeBitmask(int param1Int) {
      this.mNetworkTypeBitmask = param1Int;
      return this;
    }
    
    public Builder setOperatorNumeric(String param1String) {
      this.mOperatorNumeric = param1String;
      return this;
    }
    
    public Builder setPassword(String param1String) {
      this.mPassword = param1String;
      return this;
    }
    
    public Builder setProfileId(int param1Int) {
      this.mProfileId = param1Int;
      return this;
    }
    
    public Builder setProtocol(int param1Int) {
      this.mProtocol = param1Int;
      return this;
    }
    
    public Builder setProxyAddress(String param1String) {
      this.mProxyAddress = param1String;
      return this;
    }
    
    @Deprecated
    public Builder setProxyAddress(InetAddress param1InetAddress) {
      this.mProxyAddress = ApnSetting.inetAddressToString(param1InetAddress);
      return this;
    }
    
    public Builder setProxyPort(int param1Int) {
      this.mProxyPort = param1Int;
      return this;
    }
    
    public Builder setRoamingProtocol(int param1Int) {
      this.mRoamingProtocol = param1Int;
      return this;
    }
    
    public Builder setSkip464Xlat(int param1Int) {
      this.mSkip464Xlat = param1Int;
      return this;
    }
    
    public Builder setUser(String param1String) {
      this.mUser = param1String;
      return this;
    }
    
    public Builder setWaitTime(int param1Int) {
      this.mWaitTime = param1Int;
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface MvnoType {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ProtocolType {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Skip464XlatStatus {}
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/ApnSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */