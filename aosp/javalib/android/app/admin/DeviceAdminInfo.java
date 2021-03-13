package android.app.admin;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Printer;
import android.util.SparseArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public final class DeviceAdminInfo implements Parcelable {
  public static final Parcelable.Creator<DeviceAdminInfo> CREATOR;
  
  static final String TAG = "DeviceAdminInfo";
  
  public static final int USES_ENCRYPTED_STORAGE = 7;
  
  public static final int USES_POLICY_DEVICE_OWNER = -2;
  
  public static final int USES_POLICY_DISABLE_CAMERA = 8;
  
  public static final int USES_POLICY_DISABLE_KEYGUARD_FEATURES = 9;
  
  public static final int USES_POLICY_EXPIRE_PASSWORD = 6;
  
  public static final int USES_POLICY_FORCE_LOCK = 3;
  
  public static final int USES_POLICY_LIMIT_PASSWORD = 0;
  
  public static final int USES_POLICY_ORGANIZATION_OWNED_PROFILE_OWNER = -3;
  
  public static final int USES_POLICY_PROFILE_OWNER = -1;
  
  public static final int USES_POLICY_RESET_PASSWORD = 2;
  
  public static final int USES_POLICY_SETS_GLOBAL_PROXY = 5;
  
  public static final int USES_POLICY_WATCH_LOGIN = 1;
  
  public static final int USES_POLICY_WIPE_DATA = 4;
  
  static HashMap<String, Integer> sKnownPolicies;
  
  static ArrayList<PolicyInfo> sPoliciesDisplayOrder = new ArrayList<>();
  
  static SparseArray<PolicyInfo> sRevKnownPolicies;
  
  final ActivityInfo mActivityInfo;
  
  boolean mSupportsTransferOwnership;
  
  int mUsesPolicies;
  
  boolean mVisible;
  
  static {
    sKnownPolicies = new HashMap<>();
    sRevKnownPolicies = new SparseArray();
    sPoliciesDisplayOrder.add(new PolicyInfo(4, "wipe-data", 17041072, 17041061, 17041073, 17041062));
    sPoliciesDisplayOrder.add(new PolicyInfo(2, "reset-password", 17041069, 17041057));
    sPoliciesDisplayOrder.add(new PolicyInfo(0, "limit-password", 17041068, 17041056));
    sPoliciesDisplayOrder.add(new PolicyInfo(1, "watch-login", 17041071, 17041059, 17041071, 17041060));
    sPoliciesDisplayOrder.add(new PolicyInfo(3, "force-lock", 17041067, 17041055));
    sPoliciesDisplayOrder.add(new PolicyInfo(5, "set-global-proxy", 17041070, 17041058));
    sPoliciesDisplayOrder.add(new PolicyInfo(6, "expire-password", 17041066, 17041054));
    sPoliciesDisplayOrder.add(new PolicyInfo(7, "encrypted-storage", 17041065, 17041053));
    sPoliciesDisplayOrder.add(new PolicyInfo(8, "disable-camera", 17041063, 17041051));
    sPoliciesDisplayOrder.add(new PolicyInfo(9, "disable-keyguard-features", 17041064, 17041052));
    for (byte b = 0; b < sPoliciesDisplayOrder.size(); b++) {
      PolicyInfo policyInfo = sPoliciesDisplayOrder.get(b);
      sRevKnownPolicies.put(policyInfo.ident, policyInfo);
      sKnownPolicies.put(policyInfo.tag, Integer.valueOf(policyInfo.ident));
    } 
    CREATOR = new Parcelable.Creator<DeviceAdminInfo>() {
        public DeviceAdminInfo createFromParcel(Parcel param1Parcel) {
          return new DeviceAdminInfo(param1Parcel);
        }
        
        public DeviceAdminInfo[] newArray(int param1Int) {
          return new DeviceAdminInfo[param1Int];
        }
      };
  }
  
  public DeviceAdminInfo(Context paramContext, ActivityInfo paramActivityInfo) throws XmlPullParserException, IOException {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_0
    //   5: aload_2
    //   6: putfield mActivityInfo : Landroid/content/pm/ActivityInfo;
    //   9: aload_1
    //   10: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   13: astore_1
    //   14: aconst_null
    //   15: astore_3
    //   16: aconst_null
    //   17: astore_2
    //   18: aload_0
    //   19: getfield mActivityInfo : Landroid/content/pm/ActivityInfo;
    //   22: aload_1
    //   23: ldc 'android.app.device_admin'
    //   25: invokevirtual loadXmlMetaData : (Landroid/content/pm/PackageManager;Ljava/lang/String;)Landroid/content/res/XmlResourceParser;
    //   28: astore #4
    //   30: aload #4
    //   32: ifnull -> 662
    //   35: aload #4
    //   37: astore_2
    //   38: aload #4
    //   40: astore_3
    //   41: aload_1
    //   42: aload_0
    //   43: getfield mActivityInfo : Landroid/content/pm/ActivityInfo;
    //   46: getfield applicationInfo : Landroid/content/pm/ApplicationInfo;
    //   49: invokevirtual getResourcesForApplication : (Landroid/content/pm/ApplicationInfo;)Landroid/content/res/Resources;
    //   52: astore_1
    //   53: aload #4
    //   55: astore_2
    //   56: aload #4
    //   58: astore_3
    //   59: aload #4
    //   61: invokestatic asAttributeSet : (Lorg/xmlpull/v1/XmlPullParser;)Landroid/util/AttributeSet;
    //   64: astore #5
    //   66: aload #4
    //   68: astore_2
    //   69: aload #4
    //   71: astore_3
    //   72: aload #4
    //   74: invokeinterface next : ()I
    //   79: istore #6
    //   81: iconst_1
    //   82: istore #7
    //   84: iload #6
    //   86: iconst_1
    //   87: if_icmpeq -> 99
    //   90: iload #6
    //   92: iconst_2
    //   93: if_icmpeq -> 99
    //   96: goto -> 66
    //   99: aload #4
    //   101: astore_2
    //   102: aload #4
    //   104: astore_3
    //   105: ldc 'device-admin'
    //   107: aload #4
    //   109: invokeinterface getName : ()Ljava/lang/String;
    //   114: invokevirtual equals : (Ljava/lang/Object;)Z
    //   117: ifeq -> 631
    //   120: aload #4
    //   122: astore_2
    //   123: aload #4
    //   125: astore_3
    //   126: aload_1
    //   127: aload #5
    //   129: getstatic com/android/internal/R$styleable.DeviceAdmin : [I
    //   132: invokevirtual obtainAttributes : (Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
    //   135: astore #5
    //   137: aload #4
    //   139: astore_2
    //   140: aload #4
    //   142: astore_3
    //   143: aload_0
    //   144: aload #5
    //   146: iconst_0
    //   147: iconst_1
    //   148: invokevirtual getBoolean : (IZ)Z
    //   151: putfield mVisible : Z
    //   154: aload #4
    //   156: astore_2
    //   157: aload #4
    //   159: astore_3
    //   160: aload #5
    //   162: invokevirtual recycle : ()V
    //   165: aload #4
    //   167: astore_2
    //   168: aload #4
    //   170: astore_3
    //   171: aload #4
    //   173: invokeinterface getDepth : ()I
    //   178: istore #6
    //   180: aload #4
    //   182: astore_2
    //   183: aload #4
    //   185: astore_3
    //   186: aload #4
    //   188: invokeinterface next : ()I
    //   193: istore #8
    //   195: iload #8
    //   197: iload #7
    //   199: if_icmpeq -> 618
    //   202: iload #8
    //   204: iconst_3
    //   205: if_icmpne -> 226
    //   208: aload #4
    //   210: astore_2
    //   211: aload #4
    //   213: astore_3
    //   214: aload #4
    //   216: invokeinterface getDepth : ()I
    //   221: iload #6
    //   223: if_icmple -> 618
    //   226: iload #8
    //   228: iconst_3
    //   229: if_icmpeq -> 615
    //   232: iload #8
    //   234: iconst_4
    //   235: if_icmpne -> 241
    //   238: goto -> 615
    //   241: aload #4
    //   243: astore_2
    //   244: aload #4
    //   246: astore_3
    //   247: aload #4
    //   249: invokeinterface getName : ()Ljava/lang/String;
    //   254: astore #5
    //   256: aload #4
    //   258: astore_2
    //   259: aload #4
    //   261: astore_3
    //   262: aload #5
    //   264: ldc 'uses-policies'
    //   266: invokevirtual equals : (Ljava/lang/Object;)Z
    //   269: ifeq -> 530
    //   272: aload #4
    //   274: astore_2
    //   275: aload #4
    //   277: astore_3
    //   278: aload #4
    //   280: invokeinterface getDepth : ()I
    //   285: istore #8
    //   287: aload #4
    //   289: astore_2
    //   290: aload #4
    //   292: astore_3
    //   293: aload #4
    //   295: invokeinterface next : ()I
    //   300: istore #9
    //   302: iload #9
    //   304: iload #7
    //   306: if_icmpeq -> 527
    //   309: iload #9
    //   311: iconst_3
    //   312: if_icmpne -> 339
    //   315: aload #4
    //   317: astore_2
    //   318: aload #4
    //   320: astore_3
    //   321: aload #4
    //   323: invokeinterface getDepth : ()I
    //   328: iload #8
    //   330: if_icmple -> 336
    //   333: goto -> 339
    //   336: goto -> 527
    //   339: iload #9
    //   341: iconst_3
    //   342: if_icmpeq -> 521
    //   345: iload #9
    //   347: iconst_4
    //   348: if_icmpne -> 354
    //   351: goto -> 521
    //   354: aload #4
    //   356: astore_2
    //   357: aload #4
    //   359: astore_3
    //   360: aload #4
    //   362: invokeinterface getName : ()Ljava/lang/String;
    //   367: astore #5
    //   369: aload #4
    //   371: astore_2
    //   372: aload #4
    //   374: astore_3
    //   375: getstatic android/app/admin/DeviceAdminInfo.sKnownPolicies : Ljava/util/HashMap;
    //   378: aload #5
    //   380: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   383: checkcast java/lang/Integer
    //   386: astore #10
    //   388: aload #10
    //   390: ifnull -> 419
    //   393: aload #4
    //   395: astore_2
    //   396: aload #4
    //   398: astore_3
    //   399: aload_0
    //   400: aload_0
    //   401: getfield mUsesPolicies : I
    //   404: iload #7
    //   406: aload #10
    //   408: invokevirtual intValue : ()I
    //   411: ishl
    //   412: ior
    //   413: putfield mUsesPolicies : I
    //   416: goto -> 518
    //   419: aload #4
    //   421: astore_2
    //   422: aload #4
    //   424: astore_3
    //   425: new java/lang/StringBuilder
    //   428: astore #10
    //   430: aload #4
    //   432: astore_2
    //   433: aload #4
    //   435: astore_3
    //   436: aload #10
    //   438: invokespecial <init> : ()V
    //   441: aload #4
    //   443: astore_2
    //   444: aload #4
    //   446: astore_3
    //   447: aload #10
    //   449: ldc_w 'Unknown tag under uses-policies of '
    //   452: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   455: pop
    //   456: aload #4
    //   458: astore_2
    //   459: aload #4
    //   461: astore_3
    //   462: aload #10
    //   464: aload_0
    //   465: invokevirtual getComponent : ()Landroid/content/ComponentName;
    //   468: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   471: pop
    //   472: aload #4
    //   474: astore_2
    //   475: aload #4
    //   477: astore_3
    //   478: aload #10
    //   480: ldc_w ': '
    //   483: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: pop
    //   487: aload #4
    //   489: astore_2
    //   490: aload #4
    //   492: astore_3
    //   493: aload #10
    //   495: aload #5
    //   497: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   500: pop
    //   501: aload #4
    //   503: astore_2
    //   504: aload #4
    //   506: astore_3
    //   507: ldc 'DeviceAdminInfo'
    //   509: aload #10
    //   511: invokevirtual toString : ()Ljava/lang/String;
    //   514: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   517: pop
    //   518: goto -> 521
    //   521: iconst_1
    //   522: istore #7
    //   524: goto -> 287
    //   527: goto -> 609
    //   530: aload #4
    //   532: astore_2
    //   533: aload #4
    //   535: astore_3
    //   536: aload #5
    //   538: ldc_w 'support-transfer-ownership'
    //   541: invokevirtual equals : (Ljava/lang/Object;)Z
    //   544: ifeq -> 609
    //   547: aload #4
    //   549: astore_2
    //   550: aload #4
    //   552: astore_3
    //   553: aload #4
    //   555: invokeinterface next : ()I
    //   560: iconst_3
    //   561: if_icmpne -> 578
    //   564: aload #4
    //   566: astore_2
    //   567: aload #4
    //   569: astore_3
    //   570: aload_0
    //   571: iconst_1
    //   572: putfield mSupportsTransferOwnership : Z
    //   575: goto -> 609
    //   578: aload #4
    //   580: astore_2
    //   581: aload #4
    //   583: astore_3
    //   584: new org/xmlpull/v1/XmlPullParserException
    //   587: astore_1
    //   588: aload #4
    //   590: astore_2
    //   591: aload #4
    //   593: astore_3
    //   594: aload_1
    //   595: ldc_w 'support-transfer-ownership tag must be empty.'
    //   598: invokespecial <init> : (Ljava/lang/String;)V
    //   601: aload #4
    //   603: astore_2
    //   604: aload #4
    //   606: astore_3
    //   607: aload_1
    //   608: athrow
    //   609: iconst_1
    //   610: istore #7
    //   612: goto -> 180
    //   615: goto -> 180
    //   618: aload #4
    //   620: ifnull -> 630
    //   623: aload #4
    //   625: invokeinterface close : ()V
    //   630: return
    //   631: aload #4
    //   633: astore_2
    //   634: aload #4
    //   636: astore_3
    //   637: new org/xmlpull/v1/XmlPullParserException
    //   640: astore_1
    //   641: aload #4
    //   643: astore_2
    //   644: aload #4
    //   646: astore_3
    //   647: aload_1
    //   648: ldc_w 'Meta-data does not start with device-admin tag'
    //   651: invokespecial <init> : (Ljava/lang/String;)V
    //   654: aload #4
    //   656: astore_2
    //   657: aload #4
    //   659: astore_3
    //   660: aload_1
    //   661: athrow
    //   662: aload #4
    //   664: astore_2
    //   665: aload #4
    //   667: astore_3
    //   668: new org/xmlpull/v1/XmlPullParserException
    //   671: astore_1
    //   672: aload #4
    //   674: astore_2
    //   675: aload #4
    //   677: astore_3
    //   678: aload_1
    //   679: ldc_w 'No android.app.device_admin meta-data'
    //   682: invokespecial <init> : (Ljava/lang/String;)V
    //   685: aload #4
    //   687: astore_2
    //   688: aload #4
    //   690: astore_3
    //   691: aload_1
    //   692: athrow
    //   693: astore_1
    //   694: goto -> 759
    //   697: astore_1
    //   698: aload_3
    //   699: astore_2
    //   700: new org/xmlpull/v1/XmlPullParserException
    //   703: astore_1
    //   704: aload_3
    //   705: astore_2
    //   706: new java/lang/StringBuilder
    //   709: astore #4
    //   711: aload_3
    //   712: astore_2
    //   713: aload #4
    //   715: invokespecial <init> : ()V
    //   718: aload_3
    //   719: astore_2
    //   720: aload #4
    //   722: ldc_w 'Unable to create context for: '
    //   725: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   728: pop
    //   729: aload_3
    //   730: astore_2
    //   731: aload #4
    //   733: aload_0
    //   734: getfield mActivityInfo : Landroid/content/pm/ActivityInfo;
    //   737: getfield packageName : Ljava/lang/String;
    //   740: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   743: pop
    //   744: aload_3
    //   745: astore_2
    //   746: aload_1
    //   747: aload #4
    //   749: invokevirtual toString : ()Ljava/lang/String;
    //   752: invokespecial <init> : (Ljava/lang/String;)V
    //   755: aload_3
    //   756: astore_2
    //   757: aload_1
    //   758: athrow
    //   759: aload_2
    //   760: ifnull -> 769
    //   763: aload_2
    //   764: invokeinterface close : ()V
    //   769: aload_1
    //   770: athrow
    // Exception table:
    //   from	to	target	type
    //   18	30	697	android/content/pm/PackageManager$NameNotFoundException
    //   18	30	693	finally
    //   41	53	697	android/content/pm/PackageManager$NameNotFoundException
    //   41	53	693	finally
    //   59	66	697	android/content/pm/PackageManager$NameNotFoundException
    //   59	66	693	finally
    //   72	81	697	android/content/pm/PackageManager$NameNotFoundException
    //   72	81	693	finally
    //   105	120	697	android/content/pm/PackageManager$NameNotFoundException
    //   105	120	693	finally
    //   126	137	697	android/content/pm/PackageManager$NameNotFoundException
    //   126	137	693	finally
    //   143	154	697	android/content/pm/PackageManager$NameNotFoundException
    //   143	154	693	finally
    //   160	165	697	android/content/pm/PackageManager$NameNotFoundException
    //   160	165	693	finally
    //   171	180	697	android/content/pm/PackageManager$NameNotFoundException
    //   171	180	693	finally
    //   186	195	697	android/content/pm/PackageManager$NameNotFoundException
    //   186	195	693	finally
    //   214	226	697	android/content/pm/PackageManager$NameNotFoundException
    //   214	226	693	finally
    //   247	256	697	android/content/pm/PackageManager$NameNotFoundException
    //   247	256	693	finally
    //   262	272	697	android/content/pm/PackageManager$NameNotFoundException
    //   262	272	693	finally
    //   278	287	697	android/content/pm/PackageManager$NameNotFoundException
    //   278	287	693	finally
    //   293	302	697	android/content/pm/PackageManager$NameNotFoundException
    //   293	302	693	finally
    //   321	333	697	android/content/pm/PackageManager$NameNotFoundException
    //   321	333	693	finally
    //   360	369	697	android/content/pm/PackageManager$NameNotFoundException
    //   360	369	693	finally
    //   375	388	697	android/content/pm/PackageManager$NameNotFoundException
    //   375	388	693	finally
    //   399	416	697	android/content/pm/PackageManager$NameNotFoundException
    //   399	416	693	finally
    //   425	430	697	android/content/pm/PackageManager$NameNotFoundException
    //   425	430	693	finally
    //   436	441	697	android/content/pm/PackageManager$NameNotFoundException
    //   436	441	693	finally
    //   447	456	697	android/content/pm/PackageManager$NameNotFoundException
    //   447	456	693	finally
    //   462	472	697	android/content/pm/PackageManager$NameNotFoundException
    //   462	472	693	finally
    //   478	487	697	android/content/pm/PackageManager$NameNotFoundException
    //   478	487	693	finally
    //   493	501	697	android/content/pm/PackageManager$NameNotFoundException
    //   493	501	693	finally
    //   507	518	697	android/content/pm/PackageManager$NameNotFoundException
    //   507	518	693	finally
    //   536	547	697	android/content/pm/PackageManager$NameNotFoundException
    //   536	547	693	finally
    //   553	564	697	android/content/pm/PackageManager$NameNotFoundException
    //   553	564	693	finally
    //   570	575	697	android/content/pm/PackageManager$NameNotFoundException
    //   570	575	693	finally
    //   584	588	697	android/content/pm/PackageManager$NameNotFoundException
    //   584	588	693	finally
    //   594	601	697	android/content/pm/PackageManager$NameNotFoundException
    //   594	601	693	finally
    //   607	609	697	android/content/pm/PackageManager$NameNotFoundException
    //   607	609	693	finally
    //   637	641	697	android/content/pm/PackageManager$NameNotFoundException
    //   637	641	693	finally
    //   647	654	697	android/content/pm/PackageManager$NameNotFoundException
    //   647	654	693	finally
    //   660	662	697	android/content/pm/PackageManager$NameNotFoundException
    //   660	662	693	finally
    //   668	672	697	android/content/pm/PackageManager$NameNotFoundException
    //   668	672	693	finally
    //   678	685	697	android/content/pm/PackageManager$NameNotFoundException
    //   678	685	693	finally
    //   691	693	697	android/content/pm/PackageManager$NameNotFoundException
    //   691	693	693	finally
    //   700	704	693	finally
    //   706	711	693	finally
    //   713	718	693	finally
    //   720	729	693	finally
    //   731	744	693	finally
    //   746	755	693	finally
    //   757	759	693	finally
  }
  
  public DeviceAdminInfo(Context paramContext, ResolveInfo paramResolveInfo) throws XmlPullParserException, IOException {
    this(paramContext, paramResolveInfo.activityInfo);
  }
  
  DeviceAdminInfo(Parcel paramParcel) {
    this.mActivityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(paramParcel);
    this.mUsesPolicies = paramParcel.readInt();
    this.mSupportsTransferOwnership = paramParcel.readBoolean();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("Receiver:");
    paramPrinter.println(stringBuilder.toString());
    ActivityInfo activityInfo = this.mActivityInfo;
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("  ");
    activityInfo.dump(paramPrinter, stringBuilder.toString());
  }
  
  public ActivityInfo getActivityInfo() {
    return this.mActivityInfo;
  }
  
  public ComponentName getComponent() {
    return new ComponentName(this.mActivityInfo.packageName, this.mActivityInfo.name);
  }
  
  public String getPackageName() {
    return this.mActivityInfo.packageName;
  }
  
  public String getReceiverName() {
    return this.mActivityInfo.name;
  }
  
  public String getTagForPolicy(int paramInt) {
    return ((PolicyInfo)sRevKnownPolicies.get(paramInt)).tag;
  }
  
  public ArrayList<PolicyInfo> getUsedPolicies() {
    ArrayList<PolicyInfo> arrayList = new ArrayList();
    for (byte b = 0; b < sPoliciesDisplayOrder.size(); b++) {
      PolicyInfo policyInfo = sPoliciesDisplayOrder.get(b);
      if (usesPolicy(policyInfo.ident))
        arrayList.add(policyInfo); 
    } 
    return arrayList;
  }
  
  public boolean isVisible() {
    return this.mVisible;
  }
  
  public CharSequence loadDescription(PackageManager paramPackageManager) throws Resources.NotFoundException {
    if (this.mActivityInfo.descriptionRes != 0)
      return paramPackageManager.getText(this.mActivityInfo.packageName, this.mActivityInfo.descriptionRes, this.mActivityInfo.applicationInfo); 
    throw new Resources.NotFoundException();
  }
  
  public Drawable loadIcon(PackageManager paramPackageManager) {
    return this.mActivityInfo.loadIcon(paramPackageManager);
  }
  
  public CharSequence loadLabel(PackageManager paramPackageManager) {
    return this.mActivityInfo.loadLabel(paramPackageManager);
  }
  
  public void readPoliciesFromXml(XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    this.mUsesPolicies = Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "flags"));
  }
  
  public boolean supportsTransferOwnership() {
    return this.mSupportsTransferOwnership;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DeviceAdminInfo{");
    stringBuilder.append(this.mActivityInfo.name);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public boolean usesPolicy(int paramInt) {
    int i = this.mUsesPolicies;
    boolean bool = true;
    if ((i & 1 << paramInt) == 0)
      bool = false; 
    return bool;
  }
  
  public void writePoliciesToXml(XmlSerializer paramXmlSerializer) throws IllegalArgumentException, IllegalStateException, IOException {
    paramXmlSerializer.attribute(null, "flags", Integer.toString(this.mUsesPolicies));
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    this.mActivityInfo.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.mUsesPolicies);
    paramParcel.writeBoolean(this.mSupportsTransferOwnership);
  }
  
  public static class PolicyInfo {
    public final int description;
    
    public final int descriptionForSecondaryUsers;
    
    public final int ident;
    
    public final int label;
    
    public final int labelForSecondaryUsers;
    
    public final String tag;
    
    public PolicyInfo(int param1Int1, String param1String, int param1Int2, int param1Int3) {
      this(param1Int1, param1String, param1Int2, param1Int3, param1Int2, param1Int3);
    }
    
    public PolicyInfo(int param1Int1, String param1String, int param1Int2, int param1Int3, int param1Int4, int param1Int5) {
      this.ident = param1Int1;
      this.tag = param1String;
      this.label = param1Int2;
      this.description = param1Int3;
      this.labelForSecondaryUsers = param1Int4;
      this.descriptionForSecondaryUsers = param1Int5;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DeviceAdminInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */