package android.content.pm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Environment;
import android.os.Handler;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.AtomicFile;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.FastXmlSerializer;
import com.google.android.collect.Maps;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public abstract class RegisteredServicesCache<V> {
  private static final boolean DEBUG = false;
  
  protected static final String REGISTERED_SERVICES_DIR = "registered_services";
  
  private static final String TAG = "PackageManager";
  
  private final String mAttributesName;
  
  public final Context mContext;
  
  private final BroadcastReceiver mExternalReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        RegisteredServicesCache.this.handlePackageEvent(param1Intent, 0);
      }
    };
  
  private Handler mHandler;
  
  private final String mInterfaceName;
  
  private RegisteredServicesCacheListener<V> mListener;
  
  private final String mMetaDataName;
  
  private final BroadcastReceiver mPackageReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        int i = param1Intent.getIntExtra("android.intent.extra.UID", -1);
        if (i != -1)
          RegisteredServicesCache.this.handlePackageEvent(param1Intent, UserHandle.getUserId(i)); 
      }
    };
  
  private final XmlSerializerAndParser<V> mSerializerAndParser;
  
  protected final Object mServicesLock = new Object();
  
  private final BroadcastReceiver mUserRemovedReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        int i = param1Intent.getIntExtra("android.intent.extra.user_handle", -1);
        RegisteredServicesCache.this.onUserRemoved(i);
      }
    };
  
  private final SparseArray<UserServices<V>> mUserServices = new SparseArray(2);
  
  public RegisteredServicesCache(Context paramContext, String paramString1, String paramString2, String paramString3, XmlSerializerAndParser<V> paramXmlSerializerAndParser) {
    this.mContext = paramContext;
    this.mInterfaceName = paramString1;
    this.mMetaDataName = paramString2;
    this.mAttributesName = paramString3;
    this.mSerializerAndParser = paramXmlSerializerAndParser;
    migrateIfNecessaryLocked();
    IntentFilter intentFilter1 = new IntentFilter();
    intentFilter1.addAction("android.intent.action.PACKAGE_ADDED");
    intentFilter1.addAction("android.intent.action.PACKAGE_CHANGED");
    intentFilter1.addAction("android.intent.action.PACKAGE_REMOVED");
    intentFilter1.addDataScheme("package");
    this.mContext.registerReceiverAsUser(this.mPackageReceiver, UserHandle.ALL, intentFilter1, null, null);
    intentFilter1 = new IntentFilter();
    intentFilter1.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
    intentFilter1.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
    this.mContext.registerReceiver(this.mExternalReceiver, intentFilter1);
    IntentFilter intentFilter2 = new IntentFilter();
    intentFilter1.addAction("android.intent.action.USER_REMOVED");
    this.mContext.registerReceiver(this.mUserRemovedReceiver, intentFilter2);
  }
  
  private boolean containsType(ArrayList<ServiceInfo<V>> paramArrayList, V paramV) {
    byte b = 0;
    int i = paramArrayList.size();
    while (b < i) {
      if (((ServiceInfo)paramArrayList.get(b)).type.equals(paramV))
        return true; 
      b++;
    } 
    return false;
  }
  
  private boolean containsTypeAndUid(ArrayList<ServiceInfo<V>> paramArrayList, V paramV, int paramInt) {
    byte b = 0;
    int i = paramArrayList.size();
    while (b < i) {
      ServiceInfo serviceInfo = paramArrayList.get(b);
      if (serviceInfo.type.equals(paramV) && serviceInfo.uid == paramInt)
        return true; 
      b++;
    } 
    return false;
  }
  
  private boolean containsUid(int[] paramArrayOfint, int paramInt) {
    return (paramArrayOfint == null || ArrayUtils.contains(paramArrayOfint, paramInt));
  }
  
  private AtomicFile createFileForUser(int paramInt) {
    File file = getUserSystemDirectory(paramInt);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("registered_services/");
    stringBuilder.append(this.mInterfaceName);
    stringBuilder.append(".xml");
    return new AtomicFile(new File(file, stringBuilder.toString()));
  }
  
  private UserServices<V> findOrCreateUserLocked(int paramInt) {
    return findOrCreateUserLocked(paramInt, true);
  }
  
  private UserServices<V> findOrCreateUserLocked(int paramInt, boolean paramBoolean) {
    UserServices<V> userServices1 = (UserServices)this.mUserServices.get(paramInt);
    UserServices<V> userServices2 = userServices1;
    if (userServices1 == null) {
      UserServices<V> userServices = new UserServices();
      this.mUserServices.put(paramInt, userServices);
      userServices2 = userServices;
      if (paramBoolean) {
        userServices2 = userServices;
        if (this.mSerializerAndParser != null) {
          UserInfo userInfo = getUser(paramInt);
          userServices2 = userServices;
          if (userInfo != null) {
            AtomicFile atomicFile = createFileForUser(userInfo.id);
            userServices2 = userServices;
            if (atomicFile.getBaseFile().exists()) {
              userServices2 = null;
              userServices1 = null;
              try {
                FileInputStream fileInputStream3 = atomicFile.openRead();
                FileInputStream fileInputStream1 = fileInputStream3;
                FileInputStream fileInputStream2 = fileInputStream3;
                readPersistentServicesLocked(fileInputStream3);
                fileInputStream2 = fileInputStream3;
                IoUtils.closeQuietly(fileInputStream2);
                userServices2 = userServices;
              } catch (Exception exception) {
                userServices1 = userServices2;
                StringBuilder stringBuilder = new StringBuilder();
                userServices1 = userServices2;
                this();
                userServices1 = userServices2;
                stringBuilder.append("Error reading persistent services for user ");
                userServices1 = userServices2;
                stringBuilder.append(userInfo.id);
                userServices1 = userServices2;
                Log.w("PackageManager", stringBuilder.toString(), exception);
                IoUtils.closeQuietly((AutoCloseable)userServices2);
                userServices2 = userServices;
              } finally {}
            } 
          } 
        } 
      } 
    } 
    return userServices2;
  }
  
  private void generateServicesMap(int[] paramArrayOfint, int paramInt) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_3
    //   8: aload_0
    //   9: iload_2
    //   10: invokevirtual queryIntentServices : (I)Ljava/util/List;
    //   13: invokeinterface iterator : ()Ljava/util/Iterator;
    //   18: astore #4
    //   20: aload #4
    //   22: invokeinterface hasNext : ()Z
    //   27: ifeq -> 156
    //   30: aload #4
    //   32: invokeinterface next : ()Ljava/lang/Object;
    //   37: checkcast android/content/pm/ResolveInfo
    //   40: astore #5
    //   42: aload_0
    //   43: aload #5
    //   45: invokevirtual parseServiceInfo : (Landroid/content/pm/ResolveInfo;)Landroid/content/pm/RegisteredServicesCache$ServiceInfo;
    //   48: astore #6
    //   50: aload #6
    //   52: ifnonnull -> 99
    //   55: new java/lang/StringBuilder
    //   58: astore #6
    //   60: aload #6
    //   62: invokespecial <init> : ()V
    //   65: aload #6
    //   67: ldc_w 'Unable to load service info '
    //   70: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: pop
    //   74: aload #6
    //   76: aload #5
    //   78: invokevirtual toString : ()Ljava/lang/String;
    //   81: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: ldc 'PackageManager'
    //   87: aload #6
    //   89: invokevirtual toString : ()Ljava/lang/String;
    //   92: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   95: pop
    //   96: goto -> 20
    //   99: aload_3
    //   100: aload #6
    //   102: invokevirtual add : (Ljava/lang/Object;)Z
    //   105: pop
    //   106: goto -> 153
    //   109: astore #6
    //   111: new java/lang/StringBuilder
    //   114: dup
    //   115: invokespecial <init> : ()V
    //   118: astore #7
    //   120: aload #7
    //   122: ldc_w 'Unable to load service info '
    //   125: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: pop
    //   129: aload #7
    //   131: aload #5
    //   133: invokevirtual toString : ()Ljava/lang/String;
    //   136: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: ldc 'PackageManager'
    //   142: aload #7
    //   144: invokevirtual toString : ()Ljava/lang/String;
    //   147: aload #6
    //   149: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   152: pop
    //   153: goto -> 20
    //   156: aload_0
    //   157: getfield mServicesLock : Ljava/lang/Object;
    //   160: astore #4
    //   162: aload #4
    //   164: monitorenter
    //   165: aload_0
    //   166: iload_2
    //   167: invokespecial findOrCreateUserLocked : (I)Landroid/content/pm/RegisteredServicesCache$UserServices;
    //   170: astore #5
    //   172: aload #5
    //   174: getfield services : Ljava/util/Map;
    //   177: ifnonnull -> 186
    //   180: iconst_1
    //   181: istore #8
    //   183: goto -> 189
    //   186: iconst_0
    //   187: istore #8
    //   189: iload #8
    //   191: ifeq -> 202
    //   194: aload #5
    //   196: invokestatic newHashMap : ()Ljava/util/HashMap;
    //   199: putfield services : Ljava/util/Map;
    //   202: new java/lang/StringBuilder
    //   205: invokespecial <init> : ()V
    //   208: iconst_0
    //   209: istore #9
    //   211: aload_3
    //   212: invokevirtual iterator : ()Ljava/util/Iterator;
    //   215: astore #10
    //   217: aload #10
    //   219: invokeinterface hasNext : ()Z
    //   224: ifeq -> 467
    //   227: aload #10
    //   229: invokeinterface next : ()Ljava/lang/Object;
    //   234: checkcast android/content/pm/RegisteredServicesCache$ServiceInfo
    //   237: astore #7
    //   239: aload #5
    //   241: getfield persistentServices : Ljava/util/Map;
    //   244: aload #7
    //   246: getfield type : Ljava/lang/Object;
    //   249: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   254: checkcast java/lang/Integer
    //   257: astore #6
    //   259: aload #6
    //   261: ifnonnull -> 344
    //   264: iconst_1
    //   265: istore #11
    //   267: aload #5
    //   269: getfield services : Ljava/util/Map;
    //   272: aload #7
    //   274: getfield type : Ljava/lang/Object;
    //   277: aload #7
    //   279: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   284: pop
    //   285: aload #5
    //   287: getfield persistentServices : Ljava/util/Map;
    //   290: aload #7
    //   292: getfield type : Ljava/lang/Object;
    //   295: aload #7
    //   297: getfield uid : I
    //   300: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   303: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   308: pop
    //   309: aload #5
    //   311: getfield mPersistentServicesFileDidNotExist : Z
    //   314: ifeq -> 326
    //   317: iload #11
    //   319: istore #9
    //   321: iload #8
    //   323: ifne -> 464
    //   326: aload_0
    //   327: aload #7
    //   329: getfield type : Ljava/lang/Object;
    //   332: iload_2
    //   333: iconst_0
    //   334: invokespecial notifyListener : (Ljava/lang/Object;IZ)V
    //   337: iload #11
    //   339: istore #9
    //   341: goto -> 464
    //   344: aload #6
    //   346: invokevirtual intValue : ()I
    //   349: aload #7
    //   351: getfield uid : I
    //   354: if_icmpne -> 378
    //   357: aload #5
    //   359: getfield services : Ljava/util/Map;
    //   362: aload #7
    //   364: getfield type : Ljava/lang/Object;
    //   367: aload #7
    //   369: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   374: pop
    //   375: goto -> 464
    //   378: aload_0
    //   379: aload #7
    //   381: getfield uid : I
    //   384: invokevirtual inSystemImage : (I)Z
    //   387: ifne -> 408
    //   390: aload_0
    //   391: aload_3
    //   392: aload #7
    //   394: getfield type : Ljava/lang/Object;
    //   397: aload #6
    //   399: invokevirtual intValue : ()I
    //   402: invokespecial containsTypeAndUid : (Ljava/util/ArrayList;Ljava/lang/Object;I)Z
    //   405: ifne -> 464
    //   408: aload #5
    //   410: getfield services : Ljava/util/Map;
    //   413: aload #7
    //   415: getfield type : Ljava/lang/Object;
    //   418: aload #7
    //   420: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   425: pop
    //   426: aload #5
    //   428: getfield persistentServices : Ljava/util/Map;
    //   431: aload #7
    //   433: getfield type : Ljava/lang/Object;
    //   436: aload #7
    //   438: getfield uid : I
    //   441: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   444: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   449: pop
    //   450: aload_0
    //   451: aload #7
    //   453: getfield type : Ljava/lang/Object;
    //   456: iload_2
    //   457: iconst_0
    //   458: invokespecial notifyListener : (Ljava/lang/Object;IZ)V
    //   461: iconst_1
    //   462: istore #9
    //   464: goto -> 217
    //   467: invokestatic newArrayList : ()Ljava/util/ArrayList;
    //   470: astore #6
    //   472: aload #5
    //   474: getfield persistentServices : Ljava/util/Map;
    //   477: invokeinterface keySet : ()Ljava/util/Set;
    //   482: invokeinterface iterator : ()Ljava/util/Iterator;
    //   487: astore #10
    //   489: aload #10
    //   491: invokeinterface hasNext : ()Z
    //   496: ifeq -> 562
    //   499: aload #10
    //   501: invokeinterface next : ()Ljava/lang/Object;
    //   506: astore #7
    //   508: aload_0
    //   509: aload_3
    //   510: aload #7
    //   512: invokespecial containsType : (Ljava/util/ArrayList;Ljava/lang/Object;)Z
    //   515: ifne -> 559
    //   518: aload #5
    //   520: getfield persistentServices : Ljava/util/Map;
    //   523: aload #7
    //   525: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   530: checkcast java/lang/Integer
    //   533: invokevirtual intValue : ()I
    //   536: istore #8
    //   538: aload_0
    //   539: aload_1
    //   540: iload #8
    //   542: invokespecial containsUid : ([II)Z
    //   545: ifeq -> 559
    //   548: aload #6
    //   550: aload #7
    //   552: invokevirtual add : (Ljava/lang/Object;)Z
    //   555: pop
    //   556: goto -> 559
    //   559: goto -> 489
    //   562: aload #6
    //   564: invokevirtual iterator : ()Ljava/util/Iterator;
    //   567: astore_3
    //   568: aload_3
    //   569: invokeinterface hasNext : ()Z
    //   574: ifeq -> 621
    //   577: aload_3
    //   578: invokeinterface next : ()Ljava/lang/Object;
    //   583: astore_1
    //   584: iconst_1
    //   585: istore #9
    //   587: aload #5
    //   589: getfield persistentServices : Ljava/util/Map;
    //   592: aload_1
    //   593: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   598: pop
    //   599: aload #5
    //   601: getfield services : Ljava/util/Map;
    //   604: aload_1
    //   605: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   610: pop
    //   611: aload_0
    //   612: aload_1
    //   613: iload_2
    //   614: iconst_1
    //   615: invokespecial notifyListener : (Ljava/lang/Object;IZ)V
    //   618: goto -> 568
    //   621: iload #9
    //   623: ifeq -> 638
    //   626: aload_0
    //   627: iload_2
    //   628: invokevirtual onServicesChangedLocked : (I)V
    //   631: aload_0
    //   632: aload #5
    //   634: iload_2
    //   635: invokespecial writePersistentServicesLocked : (Landroid/content/pm/RegisteredServicesCache$UserServices;I)V
    //   638: aload #4
    //   640: monitorexit
    //   641: return
    //   642: astore_1
    //   643: aload #4
    //   645: monitorexit
    //   646: aload_1
    //   647: athrow
    //   648: astore_1
    //   649: goto -> 643
    // Exception table:
    //   from	to	target	type
    //   42	50	109	org/xmlpull/v1/XmlPullParserException
    //   42	50	109	java/io/IOException
    //   55	96	109	org/xmlpull/v1/XmlPullParserException
    //   55	96	109	java/io/IOException
    //   99	106	109	org/xmlpull/v1/XmlPullParserException
    //   99	106	109	java/io/IOException
    //   165	180	642	finally
    //   194	202	642	finally
    //   202	208	642	finally
    //   211	217	642	finally
    //   217	259	642	finally
    //   267	317	642	finally
    //   326	337	642	finally
    //   344	375	642	finally
    //   378	390	642	finally
    //   390	408	642	finally
    //   408	461	642	finally
    //   467	489	642	finally
    //   489	538	642	finally
    //   538	556	648	finally
    //   562	568	648	finally
    //   568	584	648	finally
    //   587	618	648	finally
    //   626	638	648	finally
    //   638	641	648	finally
    //   643	646	648	finally
  }
  
  private void handlePackageEvent(Intent paramIntent, int paramInt) {
    int i;
    String str = paramIntent.getAction();
    if ("android.intent.action.PACKAGE_REMOVED".equals(str) || "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(str)) {
      i = 1;
    } else {
      i = 0;
    } 
    boolean bool = paramIntent.getBooleanExtra("android.intent.extra.REPLACING", false);
    if (!i || !bool) {
      int[] arrayOfInt1;
      int[] arrayOfInt2 = null;
      if ("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(str) || "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(str)) {
        arrayOfInt1 = paramIntent.getIntArrayExtra("android.intent.extra.changed_uid_list");
      } else {
        i = arrayOfInt1.getIntExtra("android.intent.extra.UID", -1);
        arrayOfInt1 = arrayOfInt2;
        if (i > 0)
          arrayOfInt1 = new int[] { i }; 
      } 
      generateServicesMap(arrayOfInt1, paramInt);
    } 
  }
  
  private void migrateIfNecessaryLocked() {
    if (this.mSerializerAndParser == null)
      return; 
    File file = new File(new File(getDataDirectory(), "system"), "registered_services");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mInterfaceName);
    stringBuilder.append(".xml");
    AtomicFile atomicFile = new AtomicFile(new File(file, stringBuilder.toString()));
    if (atomicFile.getBaseFile().exists()) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(this.mInterfaceName);
      stringBuilder.append(".xml.migrated");
      File file1 = new File(file, stringBuilder.toString());
      if (!file1.exists()) {
        FileInputStream fileInputStream;
        file = null;
        stringBuilder = null;
        try {
          FileInputStream fileInputStream2 = atomicFile.openRead();
          FileInputStream fileInputStream1 = fileInputStream2;
          fileInputStream = fileInputStream2;
          this.mUserServices.clear();
          fileInputStream1 = fileInputStream2;
          fileInputStream = fileInputStream2;
          readPersistentServicesLocked(fileInputStream2);
          fileInputStream = fileInputStream2;
          IoUtils.closeQuietly(fileInputStream);
        } catch (Exception exception) {
          FileInputStream fileInputStream1 = fileInputStream;
          Log.w("PackageManager", "Error reading persistent services, starting from scratch", exception);
          IoUtils.closeQuietly(fileInputStream);
        } finally {}
        try {
          for (UserInfo userInfo : getUsers()) {
            UserServices<V> userServices = (UserServices)this.mUserServices.get(userInfo.id);
            if (userServices != null)
              writePersistentServicesLocked(userServices, userInfo.id); 
          } 
          file1.createNewFile();
        } catch (Exception exception) {
          Log.w("PackageManager", "Migration failed", exception);
        } 
        this.mUserServices.clear();
      } 
    } 
  }
  
  private void notifyListener(V paramV, int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mListener : Landroid/content/pm/RegisteredServicesCacheListener;
    //   6: astore #4
    //   8: aload_0
    //   9: getfield mHandler : Landroid/os/Handler;
    //   12: astore #5
    //   14: aload_0
    //   15: monitorexit
    //   16: aload #4
    //   18: ifnonnull -> 22
    //   21: return
    //   22: aload #5
    //   24: new android/content/pm/_$$Lambda$RegisteredServicesCache$lDXmLhKoG7lZpIyDOuPYOrjzDYY
    //   27: dup
    //   28: aload #4
    //   30: aload_1
    //   31: iload_2
    //   32: iload_3
    //   33: invokespecial <init> : (Landroid/content/pm/RegisteredServicesCacheListener;Ljava/lang/Object;IZ)V
    //   36: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   39: pop
    //   40: return
    //   41: astore_1
    //   42: aload_0
    //   43: monitorexit
    //   44: aload_1
    //   45: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	41	finally
    //   42	44	41	finally
  }
  
  private void readPersistentServicesLocked(InputStream paramInputStream) throws XmlPullParserException, IOException {
    XmlPullParser xmlPullParser = Xml.newPullParser();
    xmlPullParser.setInput(paramInputStream, StandardCharsets.UTF_8.name());
    int i;
    for (i = xmlPullParser.getEventType(); i != 2 && i != 1; i = xmlPullParser.next());
    if ("services".equals(xmlPullParser.getName())) {
      int j;
      i = xmlPullParser.next();
      do {
        if (i == 2 && xmlPullParser.getDepth() == 2 && "service".equals(xmlPullParser.getName())) {
          paramInputStream = (InputStream)this.mSerializerAndParser.createFromXml(xmlPullParser);
          if (paramInputStream == null)
            break; 
          i = Integer.parseInt(xmlPullParser.getAttributeValue(null, "uid"));
          (findOrCreateUserLocked(UserHandle.getUserId(i), false)).persistentServices.put((V)paramInputStream, Integer.valueOf(i));
        } 
        j = xmlPullParser.next();
        i = j;
      } while (j != 1);
    } 
  }
  
  private void writePersistentServicesLocked(UserServices<V> paramUserServices, int paramInt) {
    if (this.mSerializerAndParser == null)
      return; 
    AtomicFile atomicFile = createFileForUser(paramInt);
    FileOutputStream fileOutputStream = null;
    try {
      FileOutputStream fileOutputStream1 = atomicFile.startWrite();
      fileOutputStream = fileOutputStream1;
      FastXmlSerializer fastXmlSerializer = new FastXmlSerializer();
      fileOutputStream = fileOutputStream1;
      this();
      fileOutputStream = fileOutputStream1;
      fastXmlSerializer.setOutput(fileOutputStream1, StandardCharsets.UTF_8.name());
      fileOutputStream = fileOutputStream1;
      fastXmlSerializer.startDocument(null, Boolean.valueOf(true));
      fileOutputStream = fileOutputStream1;
      fastXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
      fileOutputStream = fileOutputStream1;
      fastXmlSerializer.startTag(null, "services");
      fileOutputStream = fileOutputStream1;
      Iterator<Map.Entry> iterator = paramUserServices.persistentServices.entrySet().iterator();
      while (true) {
        fileOutputStream = fileOutputStream1;
        if (iterator.hasNext()) {
          fileOutputStream = fileOutputStream1;
          Map.Entry entry = iterator.next();
          fileOutputStream = fileOutputStream1;
          fastXmlSerializer.startTag(null, "service");
          fileOutputStream = fileOutputStream1;
          fastXmlSerializer.attribute(null, "uid", Integer.toString(((Integer)entry.getValue()).intValue()));
          fileOutputStream = fileOutputStream1;
          this.mSerializerAndParser.writeAsXml((V)entry.getKey(), (XmlSerializer)fastXmlSerializer);
          fileOutputStream = fileOutputStream1;
          fastXmlSerializer.endTag(null, "service");
          continue;
        } 
        fileOutputStream = fileOutputStream1;
        fastXmlSerializer.endTag(null, "services");
        fileOutputStream = fileOutputStream1;
        fastXmlSerializer.endDocument();
        fileOutputStream = fileOutputStream1;
        atomicFile.finishWrite(fileOutputStream1);
        return;
      } 
    } catch (IOException iOException) {
      Log.w("PackageManager", "Error writing accounts", iOException);
      if (fileOutputStream != null)
        atomicFile.failWrite(fileOutputStream); 
    } 
  }
  
  public void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString, int paramInt) {
    synchronized (this.mServicesLock) {
      UserServices<V> userServices = findOrCreateUserLocked(paramInt);
      if (userServices.services != null) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("RegisteredServicesCache: ");
        stringBuilder.append(userServices.services.size());
        stringBuilder.append(" services");
        paramPrintWriter.println(stringBuilder.toString());
        for (ServiceInfo<V> serviceInfo : userServices.services.values()) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("  ");
          stringBuilder1.append(serviceInfo);
          paramPrintWriter.println(stringBuilder1.toString());
        } 
      } else {
        paramPrintWriter.println("RegisteredServicesCache: services not loaded");
      } 
      return;
    } 
  }
  
  public Collection<ServiceInfo<V>> getAllServices(int paramInt) {
    synchronized (this.mServicesLock) {
      UserServices<V> userServices = findOrCreateUserLocked(paramInt);
      if (userServices.services == null)
        generateServicesMap(null, paramInt); 
      ArrayList<?> arrayList = new ArrayList();
      this(userServices.services.values());
      return (Collection)Collections.unmodifiableCollection(arrayList);
    } 
  }
  
  public boolean getBindInstantServiceAllowed(int paramInt) {
    this.mContext.enforceCallingOrSelfPermission("android.permission.MANAGE_BIND_INSTANT_SERVICE", "getBindInstantServiceAllowed");
    synchronized (this.mServicesLock) {
      return (findOrCreateUserLocked(paramInt)).mBindInstantServiceAllowed;
    } 
  }
  
  protected File getDataDirectory() {
    return Environment.getDataDirectory();
  }
  
  public RegisteredServicesCacheListener<V> getListener() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mListener : Landroid/content/pm/RegisteredServicesCacheListener;
    //   6: astore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: aload_1
    //   10: areturn
    //   11: astore_1
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_1
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	11	finally
    //   12	14	11	finally
  }
  
  protected Map<V, Integer> getPersistentServices(int paramInt) {
    return (findOrCreateUserLocked(paramInt)).persistentServices;
  }
  
  public ServiceInfo<V> getServiceInfo(V paramV, int paramInt) {
    synchronized (this.mServicesLock) {
      UserServices<V> userServices = findOrCreateUserLocked(paramInt);
      if (userServices.services == null)
        generateServicesMap(null, paramInt); 
      return userServices.services.get(paramV);
    } 
  }
  
  protected UserInfo getUser(int paramInt) {
    return UserManager.get(this.mContext).getUserInfo(paramInt);
  }
  
  protected File getUserSystemDirectory(int paramInt) {
    return Environment.getUserSystemDirectory(paramInt);
  }
  
  protected List<UserInfo> getUsers() {
    return UserManager.get(this.mContext).getUsers(true);
  }
  
  protected boolean inSystemImage(int paramInt) {
    String[] arrayOfString = this.mContext.getPackageManager().getPackagesForUid(paramInt);
    if (arrayOfString != null) {
      int i = arrayOfString.length;
      paramInt = 0;
      while (paramInt < i) {
        String str = arrayOfString[paramInt];
        try {
          int j = (this.mContext.getPackageManager().getPackageInfo(str, 0)).applicationInfo.flags;
          if ((j & 0x1) != 0)
            return true; 
          paramInt++;
        } catch (NameNotFoundException nameNotFoundException) {
          return false;
        } 
      } 
    } 
    return false;
  }
  
  public void invalidateCache(int paramInt) {
    synchronized (this.mServicesLock) {
      (findOrCreateUserLocked(paramInt)).services = null;
      onServicesChangedLocked(paramInt);
      return;
    } 
  }
  
  protected void onServicesChangedLocked(int paramInt) {}
  
  protected void onUserRemoved(int paramInt) {
    synchronized (this.mServicesLock) {
      this.mUserServices.remove(paramInt);
      return;
    } 
  }
  
  public abstract V parseServiceAttributes(Resources paramResources, String paramString, AttributeSet paramAttributeSet);
  
  protected ServiceInfo<V> parseServiceInfo(ResolveInfo paramResolveInfo) throws XmlPullParserException, IOException {
    ServiceInfo serviceInfo = paramResolveInfo.serviceInfo;
    ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
    PackageManager packageManager = this.mContext.getPackageManager();
    XmlResourceParser xmlResourceParser1 = null;
    XmlResourceParser xmlResourceParser2 = null;
    try {
      XmlResourceParser xmlResourceParser = serviceInfo.loadXmlMetaData(packageManager, this.mMetaDataName);
      if (xmlResourceParser != null) {
        xmlResourceParser2 = xmlResourceParser;
        xmlResourceParser1 = xmlResourceParser;
        AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)xmlResourceParser);
        while (true) {
          xmlResourceParser2 = xmlResourceParser;
          xmlResourceParser1 = xmlResourceParser;
          int i = xmlResourceParser.next();
          if (i != 1 && i != 2)
            continue; 
          break;
        } 
        xmlResourceParser2 = xmlResourceParser;
        xmlResourceParser1 = xmlResourceParser;
        String str = xmlResourceParser.getName();
        xmlResourceParser2 = xmlResourceParser;
        xmlResourceParser1 = xmlResourceParser;
        if (this.mAttributesName.equals(str)) {
          xmlResourceParser2 = xmlResourceParser;
          xmlResourceParser1 = xmlResourceParser;
          packageManager = (PackageManager)parseServiceAttributes(packageManager.getResourcesForApplication(serviceInfo.applicationInfo), serviceInfo.packageName, attributeSet);
          if (packageManager == null) {
            if (xmlResourceParser != null)
              xmlResourceParser.close(); 
            return null;
          } 
          xmlResourceParser2 = xmlResourceParser;
          xmlResourceParser1 = xmlResourceParser;
          ServiceInfo<PackageManager> serviceInfo1 = new ServiceInfo<>(packageManager, paramResolveInfo.serviceInfo, componentName);
          if (xmlResourceParser != null)
            xmlResourceParser.close(); 
          return (ServiceInfo)serviceInfo1;
        } 
        xmlResourceParser2 = xmlResourceParser;
        xmlResourceParser1 = xmlResourceParser;
        XmlPullParserException xmlPullParserException1 = new XmlPullParserException();
        xmlResourceParser2 = xmlResourceParser;
        xmlResourceParser1 = xmlResourceParser;
        StringBuilder stringBuilder1 = new StringBuilder();
        xmlResourceParser2 = xmlResourceParser;
        xmlResourceParser1 = xmlResourceParser;
        this();
        xmlResourceParser2 = xmlResourceParser;
        xmlResourceParser1 = xmlResourceParser;
        stringBuilder1.append("Meta-data does not start with ");
        xmlResourceParser2 = xmlResourceParser;
        xmlResourceParser1 = xmlResourceParser;
        stringBuilder1.append(this.mAttributesName);
        xmlResourceParser2 = xmlResourceParser;
        xmlResourceParser1 = xmlResourceParser;
        stringBuilder1.append(" tag");
        xmlResourceParser2 = xmlResourceParser;
        xmlResourceParser1 = xmlResourceParser;
        this(stringBuilder1.toString());
        xmlResourceParser2 = xmlResourceParser;
        xmlResourceParser1 = xmlResourceParser;
        throw xmlPullParserException1;
      } 
      xmlResourceParser2 = xmlResourceParser;
      xmlResourceParser1 = xmlResourceParser;
      XmlPullParserException xmlPullParserException = new XmlPullParserException();
      xmlResourceParser2 = xmlResourceParser;
      xmlResourceParser1 = xmlResourceParser;
      StringBuilder stringBuilder = new StringBuilder();
      xmlResourceParser2 = xmlResourceParser;
      xmlResourceParser1 = xmlResourceParser;
      this();
      xmlResourceParser2 = xmlResourceParser;
      xmlResourceParser1 = xmlResourceParser;
      stringBuilder.append("No ");
      xmlResourceParser2 = xmlResourceParser;
      xmlResourceParser1 = xmlResourceParser;
      stringBuilder.append(this.mMetaDataName);
      xmlResourceParser2 = xmlResourceParser;
      xmlResourceParser1 = xmlResourceParser;
      stringBuilder.append(" meta-data");
      xmlResourceParser2 = xmlResourceParser;
      xmlResourceParser1 = xmlResourceParser;
      this(stringBuilder.toString());
      xmlResourceParser2 = xmlResourceParser;
      xmlResourceParser1 = xmlResourceParser;
      throw xmlPullParserException;
    } catch (NameNotFoundException nameNotFoundException) {
      xmlResourceParser2 = xmlResourceParser1;
      XmlPullParserException xmlPullParserException = new XmlPullParserException();
      xmlResourceParser2 = xmlResourceParser1;
      StringBuilder stringBuilder = new StringBuilder();
      xmlResourceParser2 = xmlResourceParser1;
      this();
      xmlResourceParser2 = xmlResourceParser1;
      stringBuilder.append("Unable to load resources for pacakge ");
      xmlResourceParser2 = xmlResourceParser1;
      stringBuilder.append(serviceInfo.packageName);
      xmlResourceParser2 = xmlResourceParser1;
      this(stringBuilder.toString());
      xmlResourceParser2 = xmlResourceParser1;
      throw xmlPullParserException;
    } finally {}
    if (xmlResourceParser2 != null)
      xmlResourceParser2.close(); 
    throw paramResolveInfo;
  }
  
  protected List<ResolveInfo> queryIntentServices(int paramInt) {
    null = this.mContext.getPackageManager();
    int i = 786560;
    synchronized (this.mServicesLock) {
      if ((findOrCreateUserLocked(paramInt)).mBindInstantServiceAllowed)
        i = 0xC0080 | 0x800000; 
      return null.queryIntentServicesAsUser(new Intent(this.mInterfaceName), i, paramInt);
    } 
  }
  
  public void setBindInstantServiceAllowed(int paramInt, boolean paramBoolean) {
    this.mContext.enforceCallingOrSelfPermission("android.permission.MANAGE_BIND_INSTANT_SERVICE", "setBindInstantServiceAllowed");
    synchronized (this.mServicesLock) {
      (findOrCreateUserLocked(paramInt)).mBindInstantServiceAllowed = paramBoolean;
      return;
    } 
  }
  
  public void setListener(RegisteredServicesCacheListener<V> paramRegisteredServicesCacheListener, Handler paramHandler) {
    // Byte code:
    //   0: aload_2
    //   1: astore_3
    //   2: aload_2
    //   3: ifnonnull -> 21
    //   6: new android/os/Handler
    //   9: dup
    //   10: aload_0
    //   11: getfield mContext : Landroid/content/Context;
    //   14: invokevirtual getMainLooper : ()Landroid/os/Looper;
    //   17: invokespecial <init> : (Landroid/os/Looper;)V
    //   20: astore_3
    //   21: aload_0
    //   22: monitorenter
    //   23: aload_0
    //   24: aload_3
    //   25: putfield mHandler : Landroid/os/Handler;
    //   28: aload_0
    //   29: aload_1
    //   30: putfield mListener : Landroid/content/pm/RegisteredServicesCacheListener;
    //   33: aload_0
    //   34: monitorexit
    //   35: return
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    // Exception table:
    //   from	to	target	type
    //   23	35	36	finally
    //   37	39	36	finally
  }
  
  public void updateServices(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mServicesLock : Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: iload_1
    //   9: invokespecial findOrCreateUserLocked : (I)Landroid/content/pm/RegisteredServicesCache$UserServices;
    //   12: astore_3
    //   13: aload_3
    //   14: getfield services : Ljava/util/Map;
    //   17: ifnonnull -> 23
    //   20: aload_2
    //   21: monitorexit
    //   22: return
    //   23: new java/util/ArrayList
    //   26: astore #4
    //   28: aload #4
    //   30: aload_3
    //   31: getfield services : Ljava/util/Map;
    //   34: invokeinterface values : ()Ljava/util/Collection;
    //   39: invokespecial <init> : (Ljava/util/Collection;)V
    //   42: aload_2
    //   43: monitorexit
    //   44: aconst_null
    //   45: astore_2
    //   46: aload #4
    //   48: invokeinterface iterator : ()Ljava/util/Iterator;
    //   53: astore #5
    //   55: aload #5
    //   57: invokeinterface hasNext : ()Z
    //   62: ifeq -> 177
    //   65: aload #5
    //   67: invokeinterface next : ()Ljava/lang/Object;
    //   72: checkcast android/content/pm/RegisteredServicesCache$ServiceInfo
    //   75: astore #6
    //   77: aload #6
    //   79: getfield componentInfo : Landroid/content/pm/ComponentInfo;
    //   82: getfield applicationInfo : Landroid/content/pm/ApplicationInfo;
    //   85: getfield versionCode : I
    //   88: i2l
    //   89: lstore #7
    //   91: aload #6
    //   93: getfield componentInfo : Landroid/content/pm/ComponentInfo;
    //   96: getfield packageName : Ljava/lang/String;
    //   99: astore_3
    //   100: aconst_null
    //   101: astore #4
    //   103: aload_0
    //   104: getfield mContext : Landroid/content/Context;
    //   107: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   110: aload_3
    //   111: iconst_0
    //   112: iload_1
    //   113: invokevirtual getApplicationInfoAsUser : (Ljava/lang/String;II)Landroid/content/pm/ApplicationInfo;
    //   116: astore_3
    //   117: aload_3
    //   118: astore #4
    //   120: goto -> 124
    //   123: astore_3
    //   124: aload #4
    //   126: ifnull -> 143
    //   129: aload_2
    //   130: astore_3
    //   131: aload #4
    //   133: getfield versionCode : I
    //   136: i2l
    //   137: lload #7
    //   139: lcmp
    //   140: ifeq -> 172
    //   143: aload_2
    //   144: astore #4
    //   146: aload_2
    //   147: ifnonnull -> 159
    //   150: new android/util/IntArray
    //   153: dup
    //   154: invokespecial <init> : ()V
    //   157: astore #4
    //   159: aload #4
    //   161: aload #6
    //   163: getfield uid : I
    //   166: invokevirtual add : (I)V
    //   169: aload #4
    //   171: astore_3
    //   172: aload_3
    //   173: astore_2
    //   174: goto -> 55
    //   177: aload_2
    //   178: ifnull -> 197
    //   181: aload_2
    //   182: invokevirtual size : ()I
    //   185: ifle -> 197
    //   188: aload_0
    //   189: aload_2
    //   190: invokevirtual toArray : ()[I
    //   193: iload_1
    //   194: invokespecial generateServicesMap : ([II)V
    //   197: return
    //   198: astore #4
    //   200: aload_2
    //   201: monitorexit
    //   202: aload #4
    //   204: athrow
    // Exception table:
    //   from	to	target	type
    //   7	22	198	finally
    //   23	44	198	finally
    //   103	117	123	android/content/pm/PackageManager$NameNotFoundException
    //   200	202	198	finally
  }
  
  public static class ServiceInfo<V> {
    public final ComponentInfo componentInfo;
    
    public final ComponentName componentName;
    
    public final V type;
    
    public final int uid;
    
    public ServiceInfo(V param1V, ComponentInfo param1ComponentInfo, ComponentName param1ComponentName) {
      byte b;
      this.type = param1V;
      this.componentInfo = param1ComponentInfo;
      this.componentName = param1ComponentName;
      if (param1ComponentInfo != null) {
        b = param1ComponentInfo.applicationInfo.uid;
      } else {
        b = -1;
      } 
      this.uid = b;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ServiceInfo: ");
      stringBuilder.append(this.type);
      stringBuilder.append(", ");
      stringBuilder.append(this.componentName);
      stringBuilder.append(", uid ");
      stringBuilder.append(this.uid);
      return stringBuilder.toString();
    }
  }
  
  private static class UserServices<V> {
    boolean mBindInstantServiceAllowed = false;
    
    boolean mPersistentServicesFileDidNotExist = true;
    
    final Map<V, Integer> persistentServices = Maps.newHashMap();
    
    Map<V, RegisteredServicesCache.ServiceInfo<V>> services = null;
    
    private UserServices() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/RegisteredServicesCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */