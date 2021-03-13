package android.content;

import android.accounts.Account;
import android.os.Bundle;

class SyncThread extends Thread {
  private final Account mAccount;
  
  private final String mAuthority;
  
  private final Bundle mExtras;
  
  private final SyncContext mSyncContext;
  
  private final Account mThreadsKey;
  
  private SyncThread(String paramString1, SyncContext paramSyncContext, String paramString2, Account paramAccount, Bundle paramBundle) {
    super(paramString1);
    this.mSyncContext = paramSyncContext;
    this.mAuthority = paramString2;
    this.mAccount = paramAccount;
    this.mExtras = paramBundle;
    this.mThreadsKey = AbstractThreadedSyncAdapter.access$200(paramAbstractThreadedSyncAdapter, paramAccount);
  }
  
  private boolean isCanceled() {
    return Thread.currentThread().isInterrupted();
  }
  
  public void run() {
    // Byte code:
    //   0: bipush #10
    //   2: invokestatic setThreadPriority : (I)V
    //   5: invokestatic access$100 : ()Z
    //   8: ifeq -> 19
    //   11: ldc 'SyncAdapter'
    //   13: ldc 'Thread started'
    //   15: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   18: pop
    //   19: ldc2_w 128
    //   22: aload_0
    //   23: getfield mAuthority : Ljava/lang/String;
    //   26: invokestatic traceBegin : (JLjava/lang/String;)V
    //   29: new android/content/SyncResult
    //   32: dup
    //   33: invokespecial <init> : ()V
    //   36: astore_1
    //   37: aconst_null
    //   38: astore_2
    //   39: aconst_null
    //   40: astore_3
    //   41: aconst_null
    //   42: astore #4
    //   44: aload #4
    //   46: astore #5
    //   48: aload_0
    //   49: invokespecial isCanceled : ()Z
    //   52: ifeq -> 163
    //   55: aload #4
    //   57: astore #5
    //   59: invokestatic access$100 : ()Z
    //   62: ifeq -> 77
    //   65: aload #4
    //   67: astore #5
    //   69: ldc 'SyncAdapter'
    //   71: ldc 'Already canceled'
    //   73: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   76: pop
    //   77: ldc2_w 128
    //   80: invokestatic traceEnd : (J)V
    //   83: iconst_0
    //   84: ifeq -> 95
    //   87: new java/lang/NullPointerException
    //   90: dup
    //   91: invokespecial <init> : ()V
    //   94: athrow
    //   95: aload_0
    //   96: invokespecial isCanceled : ()Z
    //   99: ifne -> 110
    //   102: aload_0
    //   103: getfield mSyncContext : Landroid/content/SyncContext;
    //   106: aload_1
    //   107: invokevirtual onFinished : (Landroid/content/SyncResult;)V
    //   110: aload_0
    //   111: getfield this$0 : Landroid/content/AbstractThreadedSyncAdapter;
    //   114: invokestatic access$300 : (Landroid/content/AbstractThreadedSyncAdapter;)Ljava/lang/Object;
    //   117: astore #4
    //   119: aload #4
    //   121: monitorenter
    //   122: aload_0
    //   123: getfield this$0 : Landroid/content/AbstractThreadedSyncAdapter;
    //   126: invokestatic access$400 : (Landroid/content/AbstractThreadedSyncAdapter;)Ljava/util/HashMap;
    //   129: aload_0
    //   130: getfield mThreadsKey : Landroid/accounts/Account;
    //   133: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   136: pop
    //   137: aload #4
    //   139: monitorexit
    //   140: invokestatic access$100 : ()Z
    //   143: ifeq -> 154
    //   146: ldc 'SyncAdapter'
    //   148: ldc 'Thread finished'
    //   150: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   153: pop
    //   154: return
    //   155: astore #5
    //   157: aload #4
    //   159: monitorexit
    //   160: aload #5
    //   162: athrow
    //   163: aload #4
    //   165: astore #5
    //   167: invokestatic access$100 : ()Z
    //   170: ifeq -> 185
    //   173: aload #4
    //   175: astore #5
    //   177: ldc 'SyncAdapter'
    //   179: ldc 'Calling onPerformSync...'
    //   181: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   184: pop
    //   185: aload #4
    //   187: astore #5
    //   189: aload_0
    //   190: getfield this$0 : Landroid/content/AbstractThreadedSyncAdapter;
    //   193: invokestatic access$1300 : (Landroid/content/AbstractThreadedSyncAdapter;)Landroid/content/Context;
    //   196: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   199: aload_0
    //   200: getfield mAuthority : Ljava/lang/String;
    //   203: invokevirtual acquireContentProviderClient : (Ljava/lang/String;)Landroid/content/ContentProviderClient;
    //   206: astore #4
    //   208: aload #4
    //   210: ifnull -> 238
    //   213: aload_0
    //   214: getfield this$0 : Landroid/content/AbstractThreadedSyncAdapter;
    //   217: aload_0
    //   218: getfield mAccount : Landroid/accounts/Account;
    //   221: aload_0
    //   222: getfield mExtras : Landroid/os/Bundle;
    //   225: aload_0
    //   226: getfield mAuthority : Ljava/lang/String;
    //   229: aload #4
    //   231: aload_1
    //   232: invokevirtual onPerformSync : (Landroid/accounts/Account;Landroid/os/Bundle;Ljava/lang/String;Landroid/content/ContentProviderClient;Landroid/content/SyncResult;)V
    //   235: goto -> 243
    //   238: aload_1
    //   239: iconst_1
    //   240: putfield databaseError : Z
    //   243: invokestatic access$100 : ()Z
    //   246: ifeq -> 257
    //   249: ldc 'SyncAdapter'
    //   251: ldc 'onPerformSync done'
    //   253: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   256: pop
    //   257: ldc2_w 128
    //   260: invokestatic traceEnd : (J)V
    //   263: aload #4
    //   265: ifnull -> 274
    //   268: aload #4
    //   270: invokevirtual release : ()Z
    //   273: pop
    //   274: aload_0
    //   275: invokespecial isCanceled : ()Z
    //   278: ifne -> 289
    //   281: aload_0
    //   282: getfield mSyncContext : Landroid/content/SyncContext;
    //   285: aload_1
    //   286: invokevirtual onFinished : (Landroid/content/SyncResult;)V
    //   289: aload_0
    //   290: getfield this$0 : Landroid/content/AbstractThreadedSyncAdapter;
    //   293: invokestatic access$300 : (Landroid/content/AbstractThreadedSyncAdapter;)Ljava/lang/Object;
    //   296: astore #5
    //   298: aload #5
    //   300: monitorenter
    //   301: aload_0
    //   302: getfield this$0 : Landroid/content/AbstractThreadedSyncAdapter;
    //   305: invokestatic access$400 : (Landroid/content/AbstractThreadedSyncAdapter;)Ljava/util/HashMap;
    //   308: aload_0
    //   309: getfield mThreadsKey : Landroid/accounts/Account;
    //   312: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   315: pop
    //   316: aload #5
    //   318: monitorexit
    //   319: invokestatic access$100 : ()Z
    //   322: ifeq -> 550
    //   325: ldc 'SyncAdapter'
    //   327: ldc 'Thread finished'
    //   329: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   332: pop
    //   333: goto -> 550
    //   336: astore #4
    //   338: aload #5
    //   340: monitorexit
    //   341: aload #4
    //   343: athrow
    //   344: astore #6
    //   346: aload #4
    //   348: astore #5
    //   350: aload #6
    //   352: astore #4
    //   354: goto -> 559
    //   357: astore #5
    //   359: aload #5
    //   361: astore #6
    //   363: goto -> 381
    //   366: astore #6
    //   368: goto -> 417
    //   371: astore #4
    //   373: goto -> 559
    //   376: astore #6
    //   378: aload_2
    //   379: astore #4
    //   381: aload #4
    //   383: astore #5
    //   385: invokestatic access$100 : ()Z
    //   388: ifeq -> 405
    //   391: aload #4
    //   393: astore #5
    //   395: ldc 'SyncAdapter'
    //   397: ldc 'caught exception'
    //   399: aload #6
    //   401: invokestatic d : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   404: pop
    //   405: aload #4
    //   407: astore #5
    //   409: aload #6
    //   411: athrow
    //   412: astore #6
    //   414: aload_3
    //   415: astore #4
    //   417: aload #4
    //   419: astore #5
    //   421: invokestatic access$100 : ()Z
    //   424: ifeq -> 441
    //   427: aload #4
    //   429: astore #5
    //   431: ldc 'SyncAdapter'
    //   433: ldc 'SecurityException'
    //   435: aload #6
    //   437: invokestatic d : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   440: pop
    //   441: aload #4
    //   443: astore #5
    //   445: aload_0
    //   446: getfield this$0 : Landroid/content/AbstractThreadedSyncAdapter;
    //   449: aload_0
    //   450: getfield mAccount : Landroid/accounts/Account;
    //   453: aload_0
    //   454: getfield mExtras : Landroid/os/Bundle;
    //   457: aload_0
    //   458: getfield mAuthority : Ljava/lang/String;
    //   461: aload_1
    //   462: invokevirtual onSecurityException : (Landroid/accounts/Account;Landroid/os/Bundle;Ljava/lang/String;Landroid/content/SyncResult;)V
    //   465: aload #4
    //   467: astore #5
    //   469: aload_1
    //   470: iconst_1
    //   471: putfield databaseError : Z
    //   474: ldc2_w 128
    //   477: invokestatic traceEnd : (J)V
    //   480: aload #4
    //   482: ifnull -> 491
    //   485: aload #4
    //   487: invokevirtual release : ()Z
    //   490: pop
    //   491: aload_0
    //   492: invokespecial isCanceled : ()Z
    //   495: ifne -> 506
    //   498: aload_0
    //   499: getfield mSyncContext : Landroid/content/SyncContext;
    //   502: aload_1
    //   503: invokevirtual onFinished : (Landroid/content/SyncResult;)V
    //   506: aload_0
    //   507: getfield this$0 : Landroid/content/AbstractThreadedSyncAdapter;
    //   510: invokestatic access$300 : (Landroid/content/AbstractThreadedSyncAdapter;)Ljava/lang/Object;
    //   513: astore #4
    //   515: aload #4
    //   517: monitorenter
    //   518: aload_0
    //   519: getfield this$0 : Landroid/content/AbstractThreadedSyncAdapter;
    //   522: invokestatic access$400 : (Landroid/content/AbstractThreadedSyncAdapter;)Ljava/util/HashMap;
    //   525: aload_0
    //   526: getfield mThreadsKey : Landroid/accounts/Account;
    //   529: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   532: pop
    //   533: aload #4
    //   535: monitorexit
    //   536: invokestatic access$100 : ()Z
    //   539: ifeq -> 550
    //   542: ldc 'SyncAdapter'
    //   544: ldc 'Thread finished'
    //   546: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   549: pop
    //   550: return
    //   551: astore #5
    //   553: aload #4
    //   555: monitorexit
    //   556: aload #5
    //   558: athrow
    //   559: ldc2_w 128
    //   562: invokestatic traceEnd : (J)V
    //   565: aload #5
    //   567: ifnull -> 576
    //   570: aload #5
    //   572: invokevirtual release : ()Z
    //   575: pop
    //   576: aload_0
    //   577: invokespecial isCanceled : ()Z
    //   580: ifne -> 591
    //   583: aload_0
    //   584: getfield mSyncContext : Landroid/content/SyncContext;
    //   587: aload_1
    //   588: invokevirtual onFinished : (Landroid/content/SyncResult;)V
    //   591: aload_0
    //   592: getfield this$0 : Landroid/content/AbstractThreadedSyncAdapter;
    //   595: invokestatic access$300 : (Landroid/content/AbstractThreadedSyncAdapter;)Ljava/lang/Object;
    //   598: astore #5
    //   600: aload #5
    //   602: monitorenter
    //   603: aload_0
    //   604: getfield this$0 : Landroid/content/AbstractThreadedSyncAdapter;
    //   607: invokestatic access$400 : (Landroid/content/AbstractThreadedSyncAdapter;)Ljava/util/HashMap;
    //   610: aload_0
    //   611: getfield mThreadsKey : Landroid/accounts/Account;
    //   614: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   617: pop
    //   618: aload #5
    //   620: monitorexit
    //   621: invokestatic access$100 : ()Z
    //   624: ifeq -> 635
    //   627: ldc 'SyncAdapter'
    //   629: ldc 'Thread finished'
    //   631: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   634: pop
    //   635: aload #4
    //   637: athrow
    //   638: astore #4
    //   640: aload #5
    //   642: monitorexit
    //   643: aload #4
    //   645: athrow
    // Exception table:
    //   from	to	target	type
    //   48	55	412	java/lang/SecurityException
    //   48	55	376	java/lang/RuntimeException
    //   48	55	376	java/lang/Error
    //   48	55	371	finally
    //   59	65	412	java/lang/SecurityException
    //   59	65	376	java/lang/RuntimeException
    //   59	65	376	java/lang/Error
    //   59	65	371	finally
    //   69	77	412	java/lang/SecurityException
    //   69	77	376	java/lang/RuntimeException
    //   69	77	376	java/lang/Error
    //   69	77	371	finally
    //   122	140	155	finally
    //   157	160	155	finally
    //   167	173	412	java/lang/SecurityException
    //   167	173	376	java/lang/RuntimeException
    //   167	173	376	java/lang/Error
    //   167	173	371	finally
    //   177	185	412	java/lang/SecurityException
    //   177	185	376	java/lang/RuntimeException
    //   177	185	376	java/lang/Error
    //   177	185	371	finally
    //   189	208	412	java/lang/SecurityException
    //   189	208	376	java/lang/RuntimeException
    //   189	208	376	java/lang/Error
    //   189	208	371	finally
    //   213	235	366	java/lang/SecurityException
    //   213	235	357	java/lang/RuntimeException
    //   213	235	357	java/lang/Error
    //   213	235	344	finally
    //   238	243	366	java/lang/SecurityException
    //   238	243	357	java/lang/RuntimeException
    //   238	243	357	java/lang/Error
    //   238	243	344	finally
    //   243	257	366	java/lang/SecurityException
    //   243	257	357	java/lang/RuntimeException
    //   243	257	357	java/lang/Error
    //   243	257	344	finally
    //   301	319	336	finally
    //   338	341	336	finally
    //   385	391	371	finally
    //   395	405	371	finally
    //   409	412	371	finally
    //   421	427	371	finally
    //   431	441	371	finally
    //   445	465	371	finally
    //   469	474	371	finally
    //   518	536	551	finally
    //   553	556	551	finally
    //   603	621	638	finally
    //   640	643	638	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/AbstractThreadedSyncAdapter$SyncThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */