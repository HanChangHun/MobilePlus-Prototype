package android.app;

import android.app.servertransaction.ClientTransaction;
import android.content.AutofillOptions;
import android.content.ComponentName;
import android.content.ContentCaptureOptions;
import android.content.Context;
import android.content.IIntentReceiver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ParceledListSlice;
import android.content.pm.ProviderInfo;
import android.content.pm.ProviderInfoList;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDebug;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Debug;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.StrictMode;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import android.util.Pair;
import android.util.PrintWriterPrinter;
import android.util.Printer;
import android.util.Slog;
import android.util.proto.ProtoOutputStream;
import android.view.Choreographer;
import android.view.ViewDebug;
import android.view.WindowManagerGlobal;
import android.webkit.WebView;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.FastPrintWriter;
import com.android.internal.util.function.HexConsumer;
import com.android.internal.util.function.QuintConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import com.android.internal.util.function.pooled.PooledRunnable;
import com.android.org.conscrypt.OpenSSLSocketImpl;
import dalvik.system.VMDebug;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.BiConsumer;
import libcore.io.IoUtils;
import libcore.net.event.NetworkEventDispatcher;

class ApplicationThread extends IApplicationThread.Stub {
  private static final String DB_INFO_FORMAT = "  %8s %8s %14s %14s  %s";
  
  private ApplicationThread() {}
  
  private void dumpDatabaseInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString, boolean paramBoolean) {
    FastPrintWriter fastPrintWriter = new FastPrintWriter(new FileOutputStream(paramParcelFileDescriptor.getFileDescriptor()));
    SQLiteDebug.dump((Printer)new PrintWriterPrinter((PrintWriter)fastPrintWriter), paramArrayOfString, paramBoolean);
    fastPrintWriter.flush();
  }
  
  private void dumpMemInfo(ProtoOutputStream paramProtoOutputStream, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    String str2;
    long l1 = Debug.getNativeHeapSize() / 1024L;
    long l2 = Debug.getNativeHeapAllocatedSize() / 1024L;
    long l3 = Debug.getNativeHeapFreeSize() / 1024L;
    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long l4 = runtime.totalMemory() / 1024L;
    long l5 = runtime.freeMemory() / 1024L;
    paramBoolean1 = false;
    long[] arrayOfLong = VMDebug.countInstancesOfClasses(new Class[] { ContextImpl.class, Activity.class, WebView.class, OpenSSLSocketImpl.class }, true);
    long l6 = arrayOfLong[0];
    long l7 = arrayOfLong[1];
    long l8 = arrayOfLong[2];
    long l9 = arrayOfLong[3];
    long l10 = ViewDebug.getViewInstanceCount();
    long l11 = ViewDebug.getViewRootImplCount();
    int i = AssetManager.getGlobalAssetCount();
    int j = AssetManager.getGlobalAssetManagerCount();
    int k = Debug.getBinderLocalObjectCount();
    int m = Debug.getBinderProxyObjectCount();
    int n = Debug.getBinderDeathObjectCount();
    long l12 = Parcel.getGlobalAllocSize();
    long l13 = Parcel.getGlobalAllocCount();
    SQLiteDebug.PagerStats pagerStats2 = SQLiteDebug.getDatabaseInfo();
    long l14 = paramProtoOutputStream.start(1146756268033L);
    paramProtoOutputStream.write(1120986464257L, Process.myPid());
    if (ActivityThread.this.mBoundApplication != null) {
      str2 = ActivityThread.this.mBoundApplication.processName;
    } else {
      str2 = "unknown";
    } 
    paramProtoOutputStream.write(1138166333442L, str2);
    ActivityThread.dumpMemInfoTable(paramProtoOutputStream, paramMemoryInfo, paramBoolean2, paramBoolean3, l1, l2, l3, l4, l4 - l5, l5);
    paramProtoOutputStream.end(l14);
    l14 = paramProtoOutputStream.start(1146756268034L);
    paramProtoOutputStream.write(1120986464257L, l10);
    paramProtoOutputStream.write(1120986464258L, l11);
    paramProtoOutputStream.write(1120986464259L, l6);
    paramProtoOutputStream.write(1120986464260L, l7);
    paramProtoOutputStream.write(1120986464261L, i);
    paramProtoOutputStream.write(1120986464262L, j);
    paramProtoOutputStream.write(1120986464263L, k);
    paramProtoOutputStream.write(1120986464264L, m);
    paramProtoOutputStream.write(1112396529673L, l12 / 1024L);
    paramProtoOutputStream.write(1120986464266L, l13);
    paramProtoOutputStream.write(1120986464267L, n);
    paramProtoOutputStream.write(1120986464268L, l9);
    paramProtoOutputStream.write(1120986464269L, l8);
    paramProtoOutputStream.end(l14);
    l8 = paramProtoOutputStream.start(1146756268035L);
    SQLiteDebug.PagerStats pagerStats1 = pagerStats2;
    paramProtoOutputStream.write(1120986464257L, pagerStats1.memoryUsed / 1024);
    paramProtoOutputStream.write(1120986464258L, pagerStats1.pageCacheOverflow / 1024);
    paramProtoOutputStream.write(1120986464259L, pagerStats1.largestMemAlloc / 1024);
    k = pagerStats1.dbStats.size();
    for (n = 0; n < k; n++) {
      SQLiteDebug.DbStats dbStats = pagerStats1.dbStats.get(n);
      l9 = paramProtoOutputStream.start(2246267895812L);
      paramProtoOutputStream.write(1138166333441L, dbStats.dbName);
      paramProtoOutputStream.write(1120986464258L, dbStats.pageSize);
      paramProtoOutputStream.write(1120986464259L, dbStats.dbSize);
      paramProtoOutputStream.write(1120986464260L, dbStats.lookaside);
      paramProtoOutputStream.write(1138166333445L, dbStats.cache);
      paramProtoOutputStream.end(l9);
    } 
    paramProtoOutputStream.end(l8);
    String str1 = AssetManager.getAssetAllocations();
    if (str1 != null)
      paramProtoOutputStream.write(1138166333444L, str1); 
    if (paramBoolean4) {
      if (ActivityThread.this.mBoundApplication == null) {
        k = 0;
      } else {
        k = ActivityThread.this.mBoundApplication.appInfo.flags;
      } 
      if ((k & 0x2) != 0 || Build.IS_DEBUGGABLE)
        paramBoolean1 = true; 
      paramProtoOutputStream.write(1138166333445L, Debug.getUnreachableMemory(100, paramBoolean1));
    } 
  }
  
  private void dumpMemInfo(PrintWriter paramPrintWriter, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5) {
    // Byte code:
    //   0: invokestatic getNativeHeapSize : ()J
    //   3: ldc2_w 1024
    //   6: ldiv
    //   7: lstore #8
    //   9: invokestatic getNativeHeapAllocatedSize : ()J
    //   12: ldc2_w 1024
    //   15: ldiv
    //   16: lstore #10
    //   18: invokestatic getNativeHeapFreeSize : ()J
    //   21: ldc2_w 1024
    //   24: ldiv
    //   25: lstore #12
    //   27: invokestatic getRuntime : ()Ljava/lang/Runtime;
    //   30: astore #14
    //   32: aload #14
    //   34: invokevirtual gc : ()V
    //   37: aload #14
    //   39: invokevirtual totalMemory : ()J
    //   42: ldc2_w 1024
    //   45: ldiv
    //   46: lstore #15
    //   48: aload #14
    //   50: invokevirtual freeMemory : ()J
    //   53: ldc2_w 1024
    //   56: ldiv
    //   57: lstore #17
    //   59: iconst_0
    //   60: istore #19
    //   62: iconst_4
    //   63: anewarray java/lang/Class
    //   66: dup
    //   67: iconst_0
    //   68: ldc android/app/ContextImpl
    //   70: aastore
    //   71: dup
    //   72: iconst_1
    //   73: ldc android/app/Activity
    //   75: aastore
    //   76: dup
    //   77: iconst_2
    //   78: ldc android/webkit/WebView
    //   80: aastore
    //   81: dup
    //   82: iconst_3
    //   83: ldc com/android/org/conscrypt/OpenSSLSocketImpl
    //   85: aastore
    //   86: iconst_1
    //   87: invokestatic countInstancesOfClasses : ([Ljava/lang/Class;Z)[J
    //   90: astore #14
    //   92: aload #14
    //   94: iconst_0
    //   95: laload
    //   96: lstore #20
    //   98: aload #14
    //   100: iconst_1
    //   101: laload
    //   102: lstore #22
    //   104: aload #14
    //   106: iconst_2
    //   107: laload
    //   108: lstore #24
    //   110: aload #14
    //   112: iconst_3
    //   113: laload
    //   114: lstore #26
    //   116: invokestatic getViewInstanceCount : ()J
    //   119: lstore #28
    //   121: invokestatic getViewRootImplCount : ()J
    //   124: lstore #30
    //   126: invokestatic getGlobalAssetCount : ()I
    //   129: istore #32
    //   131: invokestatic getGlobalAssetManagerCount : ()I
    //   134: istore #33
    //   136: invokestatic getBinderLocalObjectCount : ()I
    //   139: istore #34
    //   141: invokestatic getBinderProxyObjectCount : ()I
    //   144: istore #35
    //   146: invokestatic getBinderDeathObjectCount : ()I
    //   149: istore #36
    //   151: invokestatic getGlobalAllocSize : ()J
    //   154: lstore #37
    //   156: invokestatic getGlobalAllocCount : ()J
    //   159: lstore #39
    //   161: invokestatic getDatabaseInfo : ()Landroid/database/sqlite/SQLiteDebug$PagerStats;
    //   164: astore #41
    //   166: invokestatic myPid : ()I
    //   169: istore #42
    //   171: aload_0
    //   172: getfield this$0 : Landroid/app/ActivityThread;
    //   175: getfield mBoundApplication : Landroid/app/ActivityThread$AppBindData;
    //   178: ifnull -> 196
    //   181: aload_0
    //   182: getfield this$0 : Landroid/app/ActivityThread;
    //   185: getfield mBoundApplication : Landroid/app/ActivityThread$AppBindData;
    //   188: getfield processName : Ljava/lang/String;
    //   191: astore #14
    //   193: goto -> 200
    //   196: ldc 'unknown'
    //   198: astore #14
    //   200: aload_1
    //   201: aload_2
    //   202: iload_3
    //   203: iload #4
    //   205: iload #5
    //   207: iload #6
    //   209: iload #42
    //   211: aload #14
    //   213: lload #8
    //   215: lload #10
    //   217: lload #12
    //   219: lload #15
    //   221: lload #15
    //   223: lload #17
    //   225: lsub
    //   226: lload #17
    //   228: invokestatic dumpMemInfoTable : (Ljava/io/PrintWriter;Landroid/os/Debug$MemoryInfo;ZZZZILjava/lang/String;JJJJJJ)V
    //   231: iload_3
    //   232: ifeq -> 554
    //   235: aload_1
    //   236: lload #28
    //   238: invokevirtual print : (J)V
    //   241: aload_1
    //   242: bipush #44
    //   244: invokevirtual print : (C)V
    //   247: aload_1
    //   248: lload #30
    //   250: invokevirtual print : (J)V
    //   253: aload_1
    //   254: bipush #44
    //   256: invokevirtual print : (C)V
    //   259: aload_1
    //   260: lload #20
    //   262: invokevirtual print : (J)V
    //   265: aload_1
    //   266: bipush #44
    //   268: invokevirtual print : (C)V
    //   271: aload_1
    //   272: lload #22
    //   274: invokevirtual print : (J)V
    //   277: aload_1
    //   278: bipush #44
    //   280: invokevirtual print : (C)V
    //   283: aload_1
    //   284: iload #32
    //   286: invokevirtual print : (I)V
    //   289: aload_1
    //   290: bipush #44
    //   292: invokevirtual print : (C)V
    //   295: aload_1
    //   296: iload #33
    //   298: invokevirtual print : (I)V
    //   301: aload_1
    //   302: bipush #44
    //   304: invokevirtual print : (C)V
    //   307: aload_1
    //   308: iload #34
    //   310: invokevirtual print : (I)V
    //   313: aload_1
    //   314: bipush #44
    //   316: invokevirtual print : (C)V
    //   319: aload_1
    //   320: iload #35
    //   322: invokevirtual print : (I)V
    //   325: aload_1
    //   326: bipush #44
    //   328: invokevirtual print : (C)V
    //   331: aload_1
    //   332: iload #36
    //   334: invokevirtual print : (I)V
    //   337: aload_1
    //   338: bipush #44
    //   340: invokevirtual print : (C)V
    //   343: aload_1
    //   344: lload #26
    //   346: invokevirtual print : (J)V
    //   349: aload_1
    //   350: bipush #44
    //   352: invokevirtual print : (C)V
    //   355: aload #41
    //   357: astore_2
    //   358: aload_1
    //   359: aload_2
    //   360: getfield memoryUsed : I
    //   363: sipush #1024
    //   366: idiv
    //   367: invokevirtual print : (I)V
    //   370: aload_1
    //   371: bipush #44
    //   373: invokevirtual print : (C)V
    //   376: aload_1
    //   377: aload_2
    //   378: getfield memoryUsed : I
    //   381: sipush #1024
    //   384: idiv
    //   385: invokevirtual print : (I)V
    //   388: aload_1
    //   389: bipush #44
    //   391: invokevirtual print : (C)V
    //   394: aload_1
    //   395: aload_2
    //   396: getfield pageCacheOverflow : I
    //   399: sipush #1024
    //   402: idiv
    //   403: invokevirtual print : (I)V
    //   406: aload_1
    //   407: bipush #44
    //   409: invokevirtual print : (C)V
    //   412: aload_1
    //   413: aload_2
    //   414: getfield largestMemAlloc : I
    //   417: sipush #1024
    //   420: idiv
    //   421: invokevirtual print : (I)V
    //   424: iconst_0
    //   425: istore #35
    //   427: iload #35
    //   429: aload_2
    //   430: getfield dbStats : Ljava/util/ArrayList;
    //   433: invokevirtual size : ()I
    //   436: if_icmpge -> 549
    //   439: aload_2
    //   440: getfield dbStats : Ljava/util/ArrayList;
    //   443: iload #35
    //   445: invokevirtual get : (I)Ljava/lang/Object;
    //   448: checkcast android/database/sqlite/SQLiteDebug$DbStats
    //   451: astore #14
    //   453: aload_1
    //   454: bipush #44
    //   456: invokevirtual print : (C)V
    //   459: aload_1
    //   460: aload #14
    //   462: getfield dbName : Ljava/lang/String;
    //   465: invokevirtual print : (Ljava/lang/String;)V
    //   468: aload_1
    //   469: bipush #44
    //   471: invokevirtual print : (C)V
    //   474: aload_1
    //   475: aload #14
    //   477: getfield pageSize : J
    //   480: invokevirtual print : (J)V
    //   483: aload_1
    //   484: bipush #44
    //   486: invokevirtual print : (C)V
    //   489: aload_1
    //   490: aload #14
    //   492: getfield dbSize : J
    //   495: invokevirtual print : (J)V
    //   498: aload_1
    //   499: bipush #44
    //   501: invokevirtual print : (C)V
    //   504: aload_1
    //   505: aload #14
    //   507: getfield lookaside : I
    //   510: invokevirtual print : (I)V
    //   513: aload_1
    //   514: bipush #44
    //   516: invokevirtual print : (C)V
    //   519: aload_1
    //   520: aload #14
    //   522: getfield cache : Ljava/lang/String;
    //   525: invokevirtual print : (Ljava/lang/String;)V
    //   528: aload_1
    //   529: bipush #44
    //   531: invokevirtual print : (C)V
    //   534: aload_1
    //   535: aload #14
    //   537: getfield cache : Ljava/lang/String;
    //   540: invokevirtual print : (Ljava/lang/String;)V
    //   543: iinc #35, 1
    //   546: goto -> 427
    //   549: aload_1
    //   550: invokevirtual println : ()V
    //   553: return
    //   554: ldc_w ' '
    //   557: astore_2
    //   558: aload_1
    //   559: ldc_w ' '
    //   562: invokevirtual println : (Ljava/lang/String;)V
    //   565: aload_1
    //   566: ldc_w ' Objects'
    //   569: invokevirtual println : (Ljava/lang/String;)V
    //   572: aload_1
    //   573: ldc_w '%21s %8d %21s %8d'
    //   576: iconst_4
    //   577: anewarray java/lang/Object
    //   580: dup
    //   581: iconst_0
    //   582: ldc_w 'Views:'
    //   585: aastore
    //   586: dup
    //   587: iconst_1
    //   588: lload #28
    //   590: invokestatic valueOf : (J)Ljava/lang/Long;
    //   593: aastore
    //   594: dup
    //   595: iconst_2
    //   596: ldc_w 'ViewRootImpl:'
    //   599: aastore
    //   600: dup
    //   601: iconst_3
    //   602: lload #30
    //   604: invokestatic valueOf : (J)Ljava/lang/Long;
    //   607: aastore
    //   608: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   611: aload_1
    //   612: ldc_w '%21s %8d %21s %8d'
    //   615: iconst_4
    //   616: anewarray java/lang/Object
    //   619: dup
    //   620: iconst_0
    //   621: ldc_w 'AppContexts:'
    //   624: aastore
    //   625: dup
    //   626: iconst_1
    //   627: lload #20
    //   629: invokestatic valueOf : (J)Ljava/lang/Long;
    //   632: aastore
    //   633: dup
    //   634: iconst_2
    //   635: ldc_w 'Activities:'
    //   638: aastore
    //   639: dup
    //   640: iconst_3
    //   641: lload #22
    //   643: invokestatic valueOf : (J)Ljava/lang/Long;
    //   646: aastore
    //   647: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   650: aload_1
    //   651: ldc_w '%21s %8d %21s %8d'
    //   654: iconst_4
    //   655: anewarray java/lang/Object
    //   658: dup
    //   659: iconst_0
    //   660: ldc_w 'Assets:'
    //   663: aastore
    //   664: dup
    //   665: iconst_1
    //   666: iload #32
    //   668: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   671: aastore
    //   672: dup
    //   673: iconst_2
    //   674: ldc_w 'AssetManagers:'
    //   677: aastore
    //   678: dup
    //   679: iconst_3
    //   680: iload #33
    //   682: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   685: aastore
    //   686: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   689: aload_1
    //   690: ldc_w '%21s %8d %21s %8d'
    //   693: iconst_4
    //   694: anewarray java/lang/Object
    //   697: dup
    //   698: iconst_0
    //   699: ldc_w 'Local Binders:'
    //   702: aastore
    //   703: dup
    //   704: iconst_1
    //   705: iload #34
    //   707: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   710: aastore
    //   711: dup
    //   712: iconst_2
    //   713: ldc_w 'Proxy Binders:'
    //   716: aastore
    //   717: dup
    //   718: iconst_3
    //   719: iload #35
    //   721: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   724: aastore
    //   725: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   728: aload_1
    //   729: ldc_w '%21s %8d %21s %8d'
    //   732: iconst_4
    //   733: anewarray java/lang/Object
    //   736: dup
    //   737: iconst_0
    //   738: ldc_w 'Parcel memory:'
    //   741: aastore
    //   742: dup
    //   743: iconst_1
    //   744: lload #37
    //   746: ldc2_w 1024
    //   749: ldiv
    //   750: invokestatic valueOf : (J)Ljava/lang/Long;
    //   753: aastore
    //   754: dup
    //   755: iconst_2
    //   756: ldc_w 'Parcel count:'
    //   759: aastore
    //   760: dup
    //   761: iconst_3
    //   762: lload #39
    //   764: invokestatic valueOf : (J)Ljava/lang/Long;
    //   767: aastore
    //   768: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   771: aload_1
    //   772: ldc_w '%21s %8d %21s %8d'
    //   775: iconst_4
    //   776: anewarray java/lang/Object
    //   779: dup
    //   780: iconst_0
    //   781: ldc_w 'Death Recipients:'
    //   784: aastore
    //   785: dup
    //   786: iconst_1
    //   787: iload #36
    //   789: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   792: aastore
    //   793: dup
    //   794: iconst_2
    //   795: ldc_w 'OpenSSL Sockets:'
    //   798: aastore
    //   799: dup
    //   800: iconst_3
    //   801: lload #26
    //   803: invokestatic valueOf : (J)Ljava/lang/Long;
    //   806: aastore
    //   807: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   810: aload_1
    //   811: ldc_w '%21s %8d'
    //   814: iconst_2
    //   815: anewarray java/lang/Object
    //   818: dup
    //   819: iconst_0
    //   820: ldc_w 'WebViews:'
    //   823: aastore
    //   824: dup
    //   825: iconst_1
    //   826: lload #24
    //   828: invokestatic valueOf : (J)Ljava/lang/Long;
    //   831: aastore
    //   832: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   835: aload_1
    //   836: ldc_w ' '
    //   839: invokevirtual println : (Ljava/lang/String;)V
    //   842: aload_1
    //   843: ldc_w ' SQL'
    //   846: invokevirtual println : (Ljava/lang/String;)V
    //   849: aload_1
    //   850: ldc_w '%21s %8d'
    //   853: iconst_2
    //   854: anewarray java/lang/Object
    //   857: dup
    //   858: iconst_0
    //   859: ldc_w 'MEMORY_USED:'
    //   862: aastore
    //   863: dup
    //   864: iconst_1
    //   865: aload #41
    //   867: getfield memoryUsed : I
    //   870: sipush #1024
    //   873: idiv
    //   874: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   877: aastore
    //   878: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   881: aload_1
    //   882: ldc_w '%21s %8d %21s %8d'
    //   885: iconst_4
    //   886: anewarray java/lang/Object
    //   889: dup
    //   890: iconst_0
    //   891: ldc_w 'PAGECACHE_OVERFLOW:'
    //   894: aastore
    //   895: dup
    //   896: iconst_1
    //   897: aload #41
    //   899: getfield pageCacheOverflow : I
    //   902: sipush #1024
    //   905: idiv
    //   906: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   909: aastore
    //   910: dup
    //   911: iconst_2
    //   912: ldc_w 'MALLOC_SIZE:'
    //   915: aastore
    //   916: dup
    //   917: iconst_3
    //   918: aload #41
    //   920: getfield largestMemAlloc : I
    //   923: sipush #1024
    //   926: idiv
    //   927: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   930: aastore
    //   931: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   934: aload_1
    //   935: ldc_w ' '
    //   938: invokevirtual println : (Ljava/lang/String;)V
    //   941: aload #41
    //   943: getfield dbStats : Ljava/util/ArrayList;
    //   946: invokevirtual size : ()I
    //   949: istore #35
    //   951: iload #35
    //   953: ifle -> 1154
    //   956: aload_1
    //   957: ldc_w ' DATABASES'
    //   960: invokevirtual println : (Ljava/lang/String;)V
    //   963: aload_1
    //   964: ldc '  %8s %8s %14s %14s  %s'
    //   966: iconst_5
    //   967: anewarray java/lang/Object
    //   970: dup
    //   971: iconst_0
    //   972: ldc_w 'pgsz'
    //   975: aastore
    //   976: dup
    //   977: iconst_1
    //   978: ldc_w 'dbsz'
    //   981: aastore
    //   982: dup
    //   983: iconst_2
    //   984: ldc_w 'Lookaside(b)'
    //   987: aastore
    //   988: dup
    //   989: iconst_3
    //   990: ldc_w 'cache'
    //   993: aastore
    //   994: dup
    //   995: iconst_4
    //   996: ldc_w 'Dbname'
    //   999: aastore
    //   1000: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   1003: iconst_0
    //   1004: istore #36
    //   1006: iload #36
    //   1008: iload #35
    //   1010: if_icmpge -> 1151
    //   1013: aload #41
    //   1015: getfield dbStats : Ljava/util/ArrayList;
    //   1018: iload #36
    //   1020: invokevirtual get : (I)Ljava/lang/Object;
    //   1023: checkcast android/database/sqlite/SQLiteDebug$DbStats
    //   1026: astore #43
    //   1028: aload #43
    //   1030: getfield pageSize : J
    //   1033: lconst_0
    //   1034: lcmp
    //   1035: ifle -> 1051
    //   1038: aload #43
    //   1040: getfield pageSize : J
    //   1043: invokestatic valueOf : (J)Ljava/lang/String;
    //   1046: astore #14
    //   1048: goto -> 1054
    //   1051: aload_2
    //   1052: astore #14
    //   1054: aload #43
    //   1056: getfield dbSize : J
    //   1059: lconst_0
    //   1060: lcmp
    //   1061: ifle -> 1077
    //   1064: aload #43
    //   1066: getfield dbSize : J
    //   1069: invokestatic valueOf : (J)Ljava/lang/String;
    //   1072: astore #44
    //   1074: goto -> 1080
    //   1077: aload_2
    //   1078: astore #44
    //   1080: aload #43
    //   1082: getfield lookaside : I
    //   1085: ifle -> 1101
    //   1088: aload #43
    //   1090: getfield lookaside : I
    //   1093: invokestatic valueOf : (I)Ljava/lang/String;
    //   1096: astore #45
    //   1098: goto -> 1104
    //   1101: aload_2
    //   1102: astore #45
    //   1104: aload_1
    //   1105: ldc '  %8s %8s %14s %14s  %s'
    //   1107: iconst_5
    //   1108: anewarray java/lang/Object
    //   1111: dup
    //   1112: iconst_0
    //   1113: aload #14
    //   1115: aastore
    //   1116: dup
    //   1117: iconst_1
    //   1118: aload #44
    //   1120: aastore
    //   1121: dup
    //   1122: iconst_2
    //   1123: aload #45
    //   1125: aastore
    //   1126: dup
    //   1127: iconst_3
    //   1128: aload #43
    //   1130: getfield cache : Ljava/lang/String;
    //   1133: aastore
    //   1134: dup
    //   1135: iconst_4
    //   1136: aload #43
    //   1138: getfield dbName : Ljava/lang/String;
    //   1141: aastore
    //   1142: invokestatic printRow : (Ljava/io/PrintWriter;Ljava/lang/String;[Ljava/lang/Object;)V
    //   1145: iinc #36, 1
    //   1148: goto -> 1006
    //   1151: goto -> 1158
    //   1154: ldc_w ' '
    //   1157: astore_2
    //   1158: invokestatic getAssetAllocations : ()Ljava/lang/String;
    //   1161: astore #14
    //   1163: aload #14
    //   1165: ifnull -> 1189
    //   1168: aload_1
    //   1169: aload_2
    //   1170: invokevirtual println : (Ljava/lang/String;)V
    //   1173: aload_1
    //   1174: ldc_w ' Asset Allocations'
    //   1177: invokevirtual println : (Ljava/lang/String;)V
    //   1180: aload_1
    //   1181: aload #14
    //   1183: invokevirtual print : (Ljava/lang/String;)V
    //   1186: goto -> 1189
    //   1189: iload #7
    //   1191: ifeq -> 1258
    //   1194: aload_0
    //   1195: getfield this$0 : Landroid/app/ActivityThread;
    //   1198: getfield mBoundApplication : Landroid/app/ActivityThread$AppBindData;
    //   1201: ifnull -> 1222
    //   1204: iconst_2
    //   1205: aload_0
    //   1206: getfield this$0 : Landroid/app/ActivityThread;
    //   1209: getfield mBoundApplication : Landroid/app/ActivityThread$AppBindData;
    //   1212: getfield appInfo : Landroid/content/pm/ApplicationInfo;
    //   1215: getfield flags : I
    //   1218: iand
    //   1219: ifne -> 1231
    //   1222: iload #19
    //   1224: istore_3
    //   1225: getstatic android/os/Build.IS_DEBUGGABLE : Z
    //   1228: ifeq -> 1233
    //   1231: iconst_1
    //   1232: istore_3
    //   1233: aload_1
    //   1234: aload_2
    //   1235: invokevirtual println : (Ljava/lang/String;)V
    //   1238: aload_1
    //   1239: ldc_w ' Unreachable memory'
    //   1242: invokevirtual println : (Ljava/lang/String;)V
    //   1245: aload_1
    //   1246: bipush #100
    //   1248: iload_3
    //   1249: invokestatic getUnreachableMemory : (IZ)Ljava/lang/String;
    //   1252: invokevirtual print : (Ljava/lang/String;)V
    //   1255: goto -> 1258
    //   1258: return
  }
  
  private File getDatabasesDir(Context paramContext) {
    return paramContext.getDatabasePath("a").getParentFile();
  }
  
  public void attachAgent(String paramString) {
    ActivityThread.this.sendMessage(155, paramString);
  }
  
  public void attachStartupAgents(String paramString) {
    ActivityThread.this.sendMessage(162, paramString);
  }
  
  public final void bindApplication(String paramString1, ApplicationInfo paramApplicationInfo, ProviderInfoList paramProviderInfoList, ComponentName paramComponentName, ProfilerInfo paramProfilerInfo, Bundle paramBundle1, IInstrumentationWatcher paramIInstrumentationWatcher, IUiAutomationConnection paramIUiAutomationConnection, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo, Map paramMap, Bundle paramBundle2, String paramString2, AutofillOptions paramAutofillOptions, ContentCaptureOptions paramContentCaptureOptions, long[] paramArrayOflong) {
    if (paramMap != null)
      ServiceManager.initServiceCache(paramMap); 
    setCoreSettings(paramBundle2);
    ActivityThread.AppBindData appBindData = new ActivityThread.AppBindData();
    appBindData.processName = paramString1;
    appBindData.appInfo = paramApplicationInfo;
    appBindData.providers = paramProviderInfoList.getList();
    appBindData.instrumentationName = paramComponentName;
    appBindData.instrumentationArgs = paramBundle1;
    appBindData.instrumentationWatcher = paramIInstrumentationWatcher;
    appBindData.instrumentationUiAutomationConnection = paramIUiAutomationConnection;
    appBindData.debugMode = paramInt;
    appBindData.enableBinderTracking = paramBoolean1;
    appBindData.trackAllocation = paramBoolean2;
    appBindData.restrictedBackupMode = paramBoolean3;
    appBindData.persistent = paramBoolean4;
    appBindData.config = paramConfiguration;
    appBindData.compatInfo = paramCompatibilityInfo;
    appBindData.initProfilerInfo = paramProfilerInfo;
    appBindData.buildSerial = paramString2;
    appBindData.autofillOptions = paramAutofillOptions;
    appBindData.contentCaptureOptions = paramContentCaptureOptions;
    appBindData.disabledCompatChanges = paramArrayOflong;
    ActivityThread.this.sendMessage(110, appBindData);
  }
  
  public void clearDnsCache() {
    InetAddress.clearDnsCache();
    NetworkEventDispatcher.getInstance().onNetworkConfigurationChanged();
  }
  
  public void dispatchPackageBroadcast(int paramInt, String[] paramArrayOfString) {
    ActivityThread.access$300(ActivityThread.this, 133, paramArrayOfString, paramInt);
  }
  
  public void dumpActivity(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String paramString, String[] paramArrayOfString) {
    ActivityThread.DumpComponentInfo dumpComponentInfo = new ActivityThread.DumpComponentInfo();
    try {
      dumpComponentInfo.fd = paramParcelFileDescriptor.dup();
      dumpComponentInfo.token = paramIBinder;
      dumpComponentInfo.prefix = paramString;
      dumpComponentInfo.args = paramArrayOfString;
      ActivityThread.access$100(ActivityThread.this, 136, dumpComponentInfo, 0, 0, true);
    } catch (IOException iOException) {
      Slog.w("ActivityThread", "dumpActivity failed", iOException);
    } finally {}
    IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
  }
  
  public void dumpCacheInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString) {
    PropertyInvalidatedCache.dumpCacheInfo(paramParcelFileDescriptor.getFileDescriptor(), paramArrayOfString);
    IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
  }
  
  public void dumpDbInfo(ParcelFileDescriptor paramParcelFileDescriptor, final String[] args) {
    if (ActivityThread.this.mSystemThread) {
      try {
        final ParcelFileDescriptor dup = paramParcelFileDescriptor.dup();
        IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
              public void run() {
                try {
                  ActivityThread.ApplicationThread.this.dumpDatabaseInfo(dup, args, true);
                  return;
                } finally {
                  IoUtils.closeQuietly((AutoCloseable)dup);
                } 
              }
            });
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Could not dup FD ");
        stringBuilder.append(paramParcelFileDescriptor.getFileDescriptor().getInt$());
        Log.w("ActivityThread", stringBuilder.toString());
        IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
        return;
      } finally {}
    } else {
      dumpDatabaseInfo(paramParcelFileDescriptor, args, false);
      IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
    } 
  }
  
  public void dumpGfxInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString) {
    ActivityThread.access$400(ActivityThread.this, paramParcelFileDescriptor.getFileDescriptor());
    WindowManagerGlobal.getInstance().dumpGfxInfo(paramParcelFileDescriptor.getFileDescriptor(), paramArrayOfString);
    IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
  }
  
  public void dumpHeap(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString, ParcelFileDescriptor paramParcelFileDescriptor, RemoteCallback paramRemoteCallback) {
    ActivityThread.DumpHeapData dumpHeapData = new ActivityThread.DumpHeapData();
    dumpHeapData.managed = paramBoolean1;
    dumpHeapData.mallocInfo = paramBoolean2;
    dumpHeapData.runGc = paramBoolean3;
    dumpHeapData.path = paramString;
    try {
      dumpHeapData.fd = paramParcelFileDescriptor.dup();
      IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
      dumpHeapData.finishCallback = paramRemoteCallback;
      ActivityThread.access$100(ActivityThread.this, 135, dumpHeapData, 0, 0, true);
      return;
    } catch (IOException iOException) {
      Slog.e("ActivityThread", "Failed to duplicate heap dump file descriptor", iOException);
      IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
      return;
    } finally {}
    IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
    throw paramString;
  }
  
  public void dumpMemInfo(ParcelFileDescriptor paramParcelFileDescriptor, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String[] paramArrayOfString) {
    FastPrintWriter fastPrintWriter = new FastPrintWriter(new FileOutputStream(paramParcelFileDescriptor.getFileDescriptor()));
    try {
      dumpMemInfo((PrintWriter)fastPrintWriter, paramMemoryInfo, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5);
      return;
    } finally {
      fastPrintWriter.flush();
      IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
    } 
  }
  
  public void dumpMemInfoProto(ParcelFileDescriptor paramParcelFileDescriptor, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, String[] paramArrayOfString) {
    ProtoOutputStream protoOutputStream = new ProtoOutputStream(paramParcelFileDescriptor.getFileDescriptor());
    try {
      dumpMemInfo(protoOutputStream, paramMemoryInfo, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
      return;
    } finally {
      protoOutputStream.flush();
      IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
    } 
  }
  
  public void dumpProvider(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String[] paramArrayOfString) {
    ActivityThread.DumpComponentInfo dumpComponentInfo = new ActivityThread.DumpComponentInfo();
    try {
      dumpComponentInfo.fd = paramParcelFileDescriptor.dup();
      dumpComponentInfo.token = paramIBinder;
      dumpComponentInfo.args = paramArrayOfString;
      ActivityThread.access$100(ActivityThread.this, 141, dumpComponentInfo, 0, 0, true);
    } catch (IOException iOException) {
      Slog.w("ActivityThread", "dumpProvider failed", iOException);
    } finally {}
    IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
  }
  
  public void dumpService(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String[] paramArrayOfString) {
    ActivityThread.DumpComponentInfo dumpComponentInfo = new ActivityThread.DumpComponentInfo();
    try {
      dumpComponentInfo.fd = paramParcelFileDescriptor.dup();
      dumpComponentInfo.token = paramIBinder;
      dumpComponentInfo.args = paramArrayOfString;
      ActivityThread.access$100(ActivityThread.this, 123, dumpComponentInfo, 0, 0, true);
    } catch (IOException iOException) {
      Slog.w("ActivityThread", "dumpService failed", iOException);
    } finally {}
    IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
  }
  
  public void handleTrustStorageUpdate() {
    NetworkSecurityPolicy.getInstance().handleTrustStorageUpdate();
  }
  
  public void notifyCleartextNetwork(byte[] paramArrayOfbyte) {
    if (StrictMode.vmCleartextNetworkEnabled())
      StrictMode.onCleartextNetworkDetected(paramArrayOfbyte); 
  }
  
  public void performDirectAction(IBinder paramIBinder, String paramString, Bundle paramBundle, RemoteCallback paramRemoteCallback1, RemoteCallback paramRemoteCallback2) {
    CancellationSignal cancellationSignal = new CancellationSignal();
    if (paramRemoteCallback1 != null) {
      ActivityThread.SafeCancellationTransport safeCancellationTransport = ActivityThread.access$800(ActivityThread.this, cancellationSignal);
      Bundle bundle = new Bundle();
      bundle.putBinder("key_cancellation_signal", safeCancellationTransport.asBinder());
      paramRemoteCallback1.sendResult(bundle);
    } 
    ActivityThread.this.mH.sendMessage(PooledLambda.obtainMessage((HexConsumer)_$$Lambda$ActivityThread$ApplicationThread$nBC_BR7B9W6ftKAxur3BC53SJYc.INSTANCE, ActivityThread.this, paramIBinder, paramString, paramBundle, cancellationSignal, paramRemoteCallback2));
  }
  
  public void processInBackground() {
    ActivityThread.this.mH.removeMessages(120);
    ActivityThread.this.mH.sendMessage(ActivityThread.this.mH.obtainMessage(120));
  }
  
  public void profilerControl(boolean paramBoolean, ProfilerInfo paramProfilerInfo, int paramInt) {
    ActivityThread.access$200(ActivityThread.this, 127, paramProfilerInfo, paramBoolean, paramInt);
  }
  
  public void requestAssistContextExtras(IBinder paramIBinder1, IBinder paramIBinder2, int paramInt1, int paramInt2, int paramInt3) {
    ActivityThread.RequestAssistContextExtras requestAssistContextExtras = new ActivityThread.RequestAssistContextExtras();
    requestAssistContextExtras.activityToken = paramIBinder1;
    requestAssistContextExtras.requestToken = paramIBinder2;
    requestAssistContextExtras.requestType = paramInt1;
    requestAssistContextExtras.sessionId = paramInt2;
    requestAssistContextExtras.flags = paramInt3;
    ActivityThread.this.sendMessage(143, requestAssistContextExtras);
  }
  
  public void requestDirectActions(IBinder paramIBinder, IVoiceInteractor paramIVoiceInteractor, RemoteCallback paramRemoteCallback1, RemoteCallback paramRemoteCallback2) {
    CancellationSignal cancellationSignal = new CancellationSignal();
    if (paramRemoteCallback1 != null) {
      ActivityThread.SafeCancellationTransport safeCancellationTransport = ActivityThread.access$800(ActivityThread.this, cancellationSignal);
      Bundle bundle = new Bundle();
      bundle.putBinder("key_cancellation_signal", safeCancellationTransport.asBinder());
      paramRemoteCallback1.sendResult(bundle);
    } 
    ActivityThread.this.mH.sendMessage(PooledLambda.obtainMessage((QuintConsumer)_$$Lambda$ActivityThread$ApplicationThread$uR_ee_5oPoxu4U_by7wU55jwtdU.INSTANCE, ActivityThread.this, paramIBinder, paramIVoiceInteractor, cancellationSignal, paramRemoteCallback2));
  }
  
  public final void runIsolatedEntryPoint(String paramString, String[] paramArrayOfString) {
    SomeArgs someArgs = SomeArgs.obtain();
    someArgs.arg1 = paramString;
    someArgs.arg2 = paramArrayOfString;
    ActivityThread.this.sendMessage(158, someArgs);
  }
  
  public void scheduleApplicationInfoChanged(ApplicationInfo paramApplicationInfo) {
    ActivityThread.this.mH.removeMessages(156, paramApplicationInfo);
    ActivityThread.this.sendMessage(156, paramApplicationInfo);
  }
  
  public final void scheduleBindService(IBinder paramIBinder, Intent paramIntent, boolean paramBoolean, int paramInt) {
    ActivityThread.this.updateProcessState(paramInt, false);
    ActivityThread.BindServiceData bindServiceData = new ActivityThread.BindServiceData();
    bindServiceData.token = paramIBinder;
    bindServiceData.intent = paramIntent;
    bindServiceData.rebind = paramBoolean;
    ActivityThread.this.sendMessage(121, bindServiceData);
  }
  
  public void scheduleCrash(String paramString) {
    ActivityThread.this.sendMessage(134, paramString);
  }
  
  public final void scheduleCreateBackupAgent(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt1, int paramInt2) {
    ActivityThread.CreateBackupAgentData createBackupAgentData = new ActivityThread.CreateBackupAgentData();
    createBackupAgentData.appInfo = paramApplicationInfo;
    createBackupAgentData.compatInfo = paramCompatibilityInfo;
    createBackupAgentData.backupMode = paramInt1;
    createBackupAgentData.userId = paramInt2;
    ActivityThread.this.sendMessage(128, createBackupAgentData);
  }
  
  public final void scheduleCreateService(IBinder paramIBinder, ServiceInfo paramServiceInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt) {
    ActivityThread.this.updateProcessState(paramInt, false);
    ActivityThread.CreateServiceData createServiceData = new ActivityThread.CreateServiceData();
    createServiceData.token = paramIBinder;
    createServiceData.info = paramServiceInfo;
    createServiceData.compatInfo = paramCompatibilityInfo;
    ActivityThread.this.sendMessage(114, createServiceData);
  }
  
  public final void scheduleDestroyBackupAgent(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt) {
    ActivityThread.CreateBackupAgentData createBackupAgentData = new ActivityThread.CreateBackupAgentData();
    createBackupAgentData.appInfo = paramApplicationInfo;
    createBackupAgentData.compatInfo = paramCompatibilityInfo;
    createBackupAgentData.userId = paramInt;
    ActivityThread.this.sendMessage(129, createBackupAgentData);
  }
  
  public void scheduleEnterAnimationComplete(IBinder paramIBinder) {
    ActivityThread.this.sendMessage(149, paramIBinder);
  }
  
  public final void scheduleExit() {
    ActivityThread.this.sendMessage(111, (Object)null);
  }
  
  public void scheduleInstallProvider(ProviderInfo paramProviderInfo) {
    ActivityThread.this.sendMessage(145, paramProviderInfo);
  }
  
  public void scheduleLocalVoiceInteractionStarted(IBinder paramIBinder, IVoiceInteractor paramIVoiceInteractor) throws RemoteException {
    SomeArgs someArgs = SomeArgs.obtain();
    someArgs.arg1 = paramIBinder;
    someArgs.arg2 = paramIVoiceInteractor;
    ActivityThread.this.sendMessage(154, someArgs);
  }
  
  public void scheduleLowMemory() {
    ActivityThread.this.sendMessage(124, (Object)null);
  }
  
  public void scheduleOnNewActivityOptions(IBinder paramIBinder, Bundle paramBundle) {
    ActivityThread.this.sendMessage(146, new Pair(paramIBinder, ActivityOptions.fromBundle(paramBundle)));
  }
  
  public final void scheduleReceiver(Intent paramIntent, ActivityInfo paramActivityInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean, int paramInt2, int paramInt3) {
    ActivityThread.this.updateProcessState(paramInt3, false);
    ActivityThread.ReceiverData receiverData = new ActivityThread.ReceiverData(paramIntent, paramInt1, paramString, paramBundle, paramBoolean, false, ActivityThread.this.mAppThread.asBinder(), paramInt2);
    receiverData.info = paramActivityInfo;
    receiverData.compatInfo = paramCompatibilityInfo;
    ActivityThread.this.sendMessage(113, receiverData);
  }
  
  public void scheduleRegisteredReceiver(IIntentReceiver paramIIntentReceiver, Intent paramIntent, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3) throws RemoteException {
    ActivityThread.this.updateProcessState(paramInt3, false);
    paramIIntentReceiver.performReceive(paramIntent, paramInt1, paramString, paramBundle, paramBoolean1, paramBoolean2, paramInt2);
  }
  
  public final void scheduleServiceArgs(IBinder paramIBinder, ParceledListSlice paramParceledListSlice) {
    List<ServiceStartArgs> list = paramParceledListSlice.getList();
    for (byte b = 0; b < list.size(); b++) {
      ServiceStartArgs serviceStartArgs = list.get(b);
      ActivityThread.ServiceArgsData serviceArgsData = new ActivityThread.ServiceArgsData();
      serviceArgsData.token = paramIBinder;
      serviceArgsData.taskRemoved = serviceStartArgs.taskRemoved;
      serviceArgsData.startId = serviceStartArgs.startId;
      serviceArgsData.flags = serviceStartArgs.flags;
      serviceArgsData.args = serviceStartArgs.args;
      ActivityThread.this.sendMessage(115, serviceArgsData);
    } 
  }
  
  public final void scheduleStopService(IBinder paramIBinder) {
    ActivityThread.this.sendMessage(116, paramIBinder);
  }
  
  public final void scheduleSuicide() {
    ActivityThread.this.sendMessage(130, (Object)null);
  }
  
  public void scheduleTransaction(ClientTransaction paramClientTransaction) throws RemoteException {
    ActivityThread.this.scheduleTransaction(paramClientTransaction);
  }
  
  public void scheduleTranslucentConversionComplete(IBinder paramIBinder, boolean paramBoolean) {
    ActivityThread.access$300(ActivityThread.this, 144, paramIBinder, paramBoolean);
  }
  
  public void scheduleTrimMemory(int paramInt) {
    PooledRunnable pooledRunnable = PooledLambda.obtainRunnable((BiConsumer)_$$Lambda$ActivityThread$ApplicationThread$tUGFX7CUhzB4Pg5wFd5yeqOnu38.INSTANCE, ActivityThread.this, Integer.valueOf(paramInt)).recycleOnUse();
    Choreographer choreographer = Choreographer.getMainThreadInstance();
    if (choreographer != null) {
      choreographer.postCallback(4, (Runnable)pooledRunnable, null);
    } else {
      ActivityThread.this.mH.post((Runnable)pooledRunnable);
    } 
  }
  
  public final void scheduleUnbindService(IBinder paramIBinder, Intent paramIntent) {
    ActivityThread.BindServiceData bindServiceData = new ActivityThread.BindServiceData();
    bindServiceData.token = paramIBinder;
    bindServiceData.intent = paramIntent;
    ActivityThread.this.sendMessage(122, bindServiceData);
  }
  
  public void setCoreSettings(Bundle paramBundle) {
    ActivityThread.this.sendMessage(138, paramBundle);
  }
  
  public void setNetworkBlockSeq(long paramLong) {
    synchronized (ActivityThread.access$600(ActivityThread.this)) {
      ActivityThread.access$702(ActivityThread.this, paramLong);
      return;
    } 
  }
  
  public void setProcessState(int paramInt) {
    ActivityThread.this.updateProcessState(paramInt, true);
  }
  
  public void setSchedulingGroup(int paramInt) {
    try {
      Process.setProcessGroup(Process.myPid(), paramInt);
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed setting process group to ");
      stringBuilder.append(paramInt);
      Slog.w("ActivityThread", stringBuilder.toString(), exception);
    } 
  }
  
  public void startBinderTracking() {
    ActivityThread.this.sendMessage(150, (Object)null);
  }
  
  public void stopBinderTrackingAndDump(ParcelFileDescriptor paramParcelFileDescriptor) {
    try {
      ActivityThread.this.sendMessage(151, paramParcelFileDescriptor.dup());
    } catch (IOException iOException) {
    
    } finally {
      IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
    } 
  }
  
  public void unstableProviderDied(IBinder paramIBinder) {
    ActivityThread.this.sendMessage(142, paramIBinder);
  }
  
  public void updateHttpProxy() {
    ContextImpl contextImpl;
    if (ActivityThread.this.getApplication() != null) {
      Application application = ActivityThread.this.getApplication();
    } else {
      contextImpl = ActivityThread.this.getSystemContext();
    } 
    ActivityThread.updateHttpProxy(contextImpl);
  }
  
  public void updatePackageCompatibilityInfo(String paramString, CompatibilityInfo paramCompatibilityInfo) {
    ActivityThread.UpdateCompatibilityData updateCompatibilityData = new ActivityThread.UpdateCompatibilityData();
    updateCompatibilityData.pkg = paramString;
    updateCompatibilityData.info = paramCompatibilityInfo;
    ActivityThread.this.sendMessage(139, updateCompatibilityData);
  }
  
  public final void updateTimePrefs(int paramInt) {
    Boolean bool;
    if (paramInt == 0) {
      bool = Boolean.FALSE;
    } else if (paramInt == 1) {
      bool = Boolean.TRUE;
    } else {
      bool = null;
    } 
    DateFormat.set24HourTimePref(bool);
  }
  
  public void updateTimeZone() {
    TimeZone.setDefault(null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$ApplicationThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */