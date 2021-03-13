package android.hardware.camera2.legacy;

import android.os.Handler;
import android.os.Message;

class null implements Handler.Callback {
  private boolean mCleanup = false;
  
  private final LegacyResultMapper mMapper = new LegacyResultMapper();
  
  public boolean handleMessage(Message paramMessage) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mCleanup : Z
    //   4: ifeq -> 9
    //   7: iconst_1
    //   8: ireturn
    //   9: lconst_0
    //   10: lstore_2
    //   11: aload_1
    //   12: getfield what : I
    //   15: istore #4
    //   17: iload #4
    //   19: iconst_m1
    //   20: if_icmpeq -> 1648
    //   23: iload #4
    //   25: iconst_1
    //   26: if_icmpeq -> 1465
    //   29: iload #4
    //   31: iconst_2
    //   32: if_icmpeq -> 236
    //   35: iload #4
    //   37: iconst_3
    //   38: if_icmpne -> 188
    //   41: aload_0
    //   42: iconst_1
    //   43: putfield mCleanup : Z
    //   46: aload_0
    //   47: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   50: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   53: ldc2_w 4000
    //   56: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
    //   59: invokevirtual waitForEmpty : (JLjava/util/concurrent/TimeUnit;)Z
    //   62: ifne -> 88
    //   65: aload_0
    //   66: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   69: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   72: ldc 'Timed out while queueing cleanup request.'
    //   74: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   77: pop
    //   78: aload_0
    //   79: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   82: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   85: invokevirtual failAll : ()V
    //   88: goto -> 117
    //   91: astore_1
    //   92: aload_0
    //   93: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   96: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   99: ldc 'Interrupted while waiting for requests to complete: '
    //   101: aload_1
    //   102: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   105: pop
    //   106: aload_0
    //   107: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   110: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   113: iconst_1
    //   114: invokevirtual setError : (I)V
    //   117: aload_0
    //   118: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   121: invokestatic access$500 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/GLThreadManager;
    //   124: ifnull -> 146
    //   127: aload_0
    //   128: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   131: invokestatic access$500 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/GLThreadManager;
    //   134: invokevirtual quit : ()V
    //   137: aload_0
    //   138: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   141: aconst_null
    //   142: invokestatic access$502 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/camera2/legacy/GLThreadManager;)Landroid/hardware/camera2/legacy/GLThreadManager;
    //   145: pop
    //   146: aload_0
    //   147: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   150: invokestatic access$1900 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)V
    //   153: aload_0
    //   154: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   157: invokestatic access$1300 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera;
    //   160: ifnull -> 185
    //   163: aload_0
    //   164: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   167: invokestatic access$1300 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera;
    //   170: invokevirtual release : ()V
    //   173: aload_0
    //   174: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   177: aconst_null
    //   178: invokestatic access$1302 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/Camera;)Landroid/hardware/Camera;
    //   181: pop
    //   182: goto -> 1648
    //   185: goto -> 1648
    //   188: new java/lang/StringBuilder
    //   191: dup
    //   192: invokespecial <init> : ()V
    //   195: astore #5
    //   197: aload #5
    //   199: ldc 'Unhandled message '
    //   201: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: pop
    //   205: aload #5
    //   207: aload_1
    //   208: getfield what : I
    //   211: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   214: pop
    //   215: aload #5
    //   217: ldc ' on RequestThread.'
    //   219: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: pop
    //   223: new java/lang/AssertionError
    //   226: dup
    //   227: aload #5
    //   229: invokevirtual toString : ()Ljava/lang/String;
    //   232: invokespecial <init> : (Ljava/lang/Object;)V
    //   235: athrow
    //   236: aload_0
    //   237: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   240: invokestatic access$700 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/RequestHandlerThread;
    //   243: invokevirtual getHandler : ()Landroid/os/Handler;
    //   246: astore #5
    //   248: iconst_0
    //   249: istore #6
    //   251: aload_0
    //   252: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   255: invokestatic access$800 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/RequestQueue;
    //   258: invokevirtual getNext : ()Landroid/hardware/camera2/legacy/RequestQueue$RequestQueueEntry;
    //   261: astore #7
    //   263: aload #7
    //   265: astore_1
    //   266: aload #7
    //   268: ifnonnull -> 398
    //   271: aload_0
    //   272: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   275: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   278: ldc2_w 4000
    //   281: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
    //   284: invokevirtual waitForEmpty : (JLjava/util/concurrent/TimeUnit;)Z
    //   287: ifne -> 313
    //   290: aload_0
    //   291: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   294: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   297: ldc 'Timed out while waiting for prior requests to complete.'
    //   299: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   302: pop
    //   303: aload_0
    //   304: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   307: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   310: invokevirtual failAll : ()V
    //   313: aload_0
    //   314: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   317: invokestatic access$900 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/Object;
    //   320: astore #7
    //   322: aload #7
    //   324: monitorenter
    //   325: aload_0
    //   326: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   329: invokestatic access$800 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/RequestQueue;
    //   332: invokevirtual getNext : ()Landroid/hardware/camera2/legacy/RequestQueue$RequestQueueEntry;
    //   335: astore_1
    //   336: aload_1
    //   337: ifnonnull -> 357
    //   340: aload_0
    //   341: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   344: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   347: invokevirtual setIdle : ()Z
    //   350: pop
    //   351: aload #7
    //   353: monitorexit
    //   354: goto -> 1648
    //   357: aload #7
    //   359: monitorexit
    //   360: goto -> 398
    //   363: astore_1
    //   364: aload #7
    //   366: monitorexit
    //   367: aload_1
    //   368: athrow
    //   369: astore_1
    //   370: aload_0
    //   371: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   374: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   377: ldc 'Interrupted while waiting for requests to complete: '
    //   379: aload_1
    //   380: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   383: pop
    //   384: aload_0
    //   385: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   388: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   391: iconst_1
    //   392: invokevirtual setError : (I)V
    //   395: goto -> 1648
    //   398: aload_1
    //   399: ifnull -> 426
    //   402: aload #5
    //   404: iconst_2
    //   405: invokevirtual sendEmptyMessage : (I)Z
    //   408: pop
    //   409: aload_1
    //   410: invokevirtual isQueueEmpty : ()Z
    //   413: ifeq -> 426
    //   416: aload_0
    //   417: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   420: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   423: invokevirtual setRequestQueueEmpty : ()V
    //   426: aload_1
    //   427: invokevirtual getBurstHolder : ()Landroid/hardware/camera2/legacy/BurstHolder;
    //   430: astore #7
    //   432: aload #7
    //   434: aload_1
    //   435: invokevirtual getFrameNumber : ()Ljava/lang/Long;
    //   438: invokevirtual longValue : ()J
    //   441: invokevirtual produceRequestHolders : (J)Ljava/util/List;
    //   444: invokeinterface iterator : ()Ljava/util/Iterator;
    //   449: astore #8
    //   451: aload #5
    //   453: astore_1
    //   454: aload #8
    //   456: invokeinterface hasNext : ()Z
    //   461: ifeq -> 1353
    //   464: aload #8
    //   466: invokeinterface next : ()Ljava/lang/Object;
    //   471: checkcast android/hardware/camera2/legacy/RequestHolder
    //   474: astore #5
    //   476: aload #5
    //   478: invokevirtual getRequest : ()Landroid/hardware/camera2/CaptureRequest;
    //   481: astore #9
    //   483: iconst_0
    //   484: istore #4
    //   486: aload_0
    //   487: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   490: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
    //   493: ifnull -> 520
    //   496: aload_0
    //   497: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   500: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
    //   503: getfield captureRequest : Landroid/hardware/camera2/CaptureRequest;
    //   506: aload #9
    //   508: if_acmpeq -> 514
    //   511: goto -> 520
    //   514: iconst_0
    //   515: istore #4
    //   517: goto -> 670
    //   520: aload_0
    //   521: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   524: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
    //   527: invokevirtual getPreviewSize : ()Landroid/hardware/Camera$Size;
    //   530: invokestatic convertSize : (Landroid/hardware/Camera$Size;)Landroid/util/Size;
    //   533: astore #10
    //   535: new android/hardware/camera2/legacy/LegacyRequest
    //   538: dup
    //   539: aload_0
    //   540: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   543: invokestatic access$1200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/CameraCharacteristics;
    //   546: aload #9
    //   548: aload #10
    //   550: aload_0
    //   551: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   554: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
    //   557: invokespecial <init> : (Landroid/hardware/camera2/CameraCharacteristics;Landroid/hardware/camera2/CaptureRequest;Landroid/util/Size;Landroid/hardware/Camera$Parameters;)V
    //   560: astore #10
    //   562: aload #10
    //   564: invokestatic convertRequestMetadata : (Landroid/hardware/camera2/legacy/LegacyRequest;)V
    //   567: aload_0
    //   568: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   571: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
    //   574: aload #10
    //   576: getfield parameters : Landroid/hardware/Camera$Parameters;
    //   579: invokevirtual same : (Landroid/hardware/Camera$Parameters;)Z
    //   582: ifne -> 660
    //   585: aload_0
    //   586: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   589: invokestatic access$1300 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera;
    //   592: aload #10
    //   594: getfield parameters : Landroid/hardware/Camera$Parameters;
    //   597: invokevirtual setParameters : (Landroid/hardware/Camera$Parameters;)V
    //   600: iconst_1
    //   601: istore #4
    //   603: aload_0
    //   604: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   607: aload #10
    //   609: getfield parameters : Landroid/hardware/Camera$Parameters;
    //   612: invokestatic access$1102 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/Camera$Parameters;)Landroid/hardware/Camera$Parameters;
    //   615: pop
    //   616: goto -> 660
    //   619: astore #9
    //   621: aload_0
    //   622: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   625: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   628: ldc_w 'Exception while setting camera parameters: '
    //   631: aload #9
    //   633: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   636: pop
    //   637: aload #5
    //   639: invokevirtual failRequest : ()V
    //   642: aload_0
    //   643: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   646: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   649: aload #5
    //   651: lconst_0
    //   652: iconst_3
    //   653: invokevirtual setCaptureStart : (Landroid/hardware/camera2/legacy/RequestHolder;JI)Z
    //   656: pop
    //   657: goto -> 752
    //   660: aload_0
    //   661: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   664: aload #10
    //   666: invokestatic access$1002 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/camera2/legacy/LegacyRequest;)Landroid/hardware/camera2/legacy/LegacyRequest;
    //   669: pop
    //   670: aload_0
    //   671: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   674: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   677: astore #10
    //   679: aload_0
    //   680: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   683: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
    //   686: astore #11
    //   688: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
    //   691: astore #12
    //   693: aload #10
    //   695: aload #5
    //   697: aload #11
    //   699: ldc2_w 4000
    //   702: aload #12
    //   704: invokevirtual queueRequest : (Landroid/hardware/camera2/legacy/RequestHolder;Landroid/hardware/camera2/legacy/LegacyRequest;JLjava/util/concurrent/TimeUnit;)Z
    //   707: istore #13
    //   709: iload #13
    //   711: ifne -> 767
    //   714: aload_0
    //   715: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   718: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   721: ldc_w 'Timed out while queueing capture request.'
    //   724: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   727: pop
    //   728: aload #5
    //   730: invokevirtual failRequest : ()V
    //   733: aload_0
    //   734: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   737: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   740: astore #9
    //   742: aload #9
    //   744: aload #5
    //   746: lconst_0
    //   747: iconst_3
    //   748: invokevirtual setCaptureStart : (Landroid/hardware/camera2/legacy/RequestHolder;JI)Z
    //   751: pop
    //   752: goto -> 454
    //   755: astore_1
    //   756: goto -> 1264
    //   759: astore_1
    //   760: goto -> 1294
    //   763: astore_1
    //   764: goto -> 1324
    //   767: aload #5
    //   769: invokevirtual hasPreviewTargets : ()Z
    //   772: istore #13
    //   774: iload #13
    //   776: ifeq -> 803
    //   779: aload_0
    //   780: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   783: aload #5
    //   785: invokestatic access$1400 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/camera2/legacy/RequestHolder;)V
    //   788: goto -> 803
    //   791: astore_1
    //   792: goto -> 1264
    //   795: astore_1
    //   796: goto -> 1294
    //   799: astore_1
    //   800: goto -> 1324
    //   803: aload #5
    //   805: invokevirtual hasJpegTargets : ()Z
    //   808: istore #13
    //   810: iload #13
    //   812: ifeq -> 880
    //   815: aload_0
    //   816: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   819: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   822: ldc2_w 1000
    //   825: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
    //   828: invokevirtual waitForPreviewsEmpty : (JLjava/util/concurrent/TimeUnit;)Z
    //   831: ifne -> 861
    //   834: aload_0
    //   835: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   838: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   841: ldc_w 'Timed out while waiting for preview requests to complete.'
    //   844: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   847: pop
    //   848: aload_0
    //   849: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   852: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   855: invokevirtual failNextPreview : ()V
    //   858: goto -> 815
    //   861: aload_0
    //   862: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   865: invokestatic access$400 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/os/ConditionVariable;
    //   868: invokevirtual close : ()V
    //   871: aload_0
    //   872: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   875: aload #5
    //   877: invokestatic access$1500 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/camera2/legacy/RequestHolder;)V
    //   880: aload_0
    //   881: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   884: invokestatic access$1600 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyFaceDetectMapper;
    //   887: aload #9
    //   889: aload_0
    //   890: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   893: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
    //   896: invokevirtual processFaceDetectMode : (Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/Camera$Parameters;)V
    //   899: aload_0
    //   900: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   903: invokestatic access$1700 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyFocusStateMapper;
    //   906: aload #9
    //   908: aload_0
    //   909: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   912: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
    //   915: invokevirtual processRequestTriggers : (Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/Camera$Parameters;)V
    //   918: aload #5
    //   920: invokevirtual hasJpegTargets : ()Z
    //   923: istore #13
    //   925: iload #13
    //   927: ifeq -> 979
    //   930: aload_0
    //   931: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   934: aload #5
    //   936: invokestatic access$1800 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/camera2/legacy/RequestHolder;)V
    //   939: aload_0
    //   940: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   943: invokestatic access$400 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/os/ConditionVariable;
    //   946: ldc2_w 4000
    //   949: invokevirtual block : (J)Z
    //   952: ifne -> 979
    //   955: aload_0
    //   956: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   959: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   962: ldc_w 'Hit timeout for jpeg callback!'
    //   965: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   968: pop
    //   969: aload_0
    //   970: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   973: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   976: invokevirtual failNextJpeg : ()V
    //   979: iload #4
    //   981: ifeq -> 1052
    //   984: aload_0
    //   985: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   988: aload_0
    //   989: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   992: invokestatic access$1300 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera;
    //   995: invokevirtual getParameters : ()Landroid/hardware/Camera$Parameters;
    //   998: invokestatic access$1102 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/Camera$Parameters;)Landroid/hardware/Camera$Parameters;
    //   1001: pop
    //   1002: aload_0
    //   1003: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1006: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
    //   1009: aload_0
    //   1010: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1013: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
    //   1016: invokevirtual setParameters : (Landroid/hardware/Camera$Parameters;)V
    //   1019: goto -> 1052
    //   1022: astore_1
    //   1023: aload_0
    //   1024: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1027: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   1030: ldc_w 'Received device exception: '
    //   1033: aload_1
    //   1034: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1037: pop
    //   1038: aload_0
    //   1039: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1042: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   1045: iconst_1
    //   1046: invokevirtual setError : (I)V
    //   1049: goto -> 1353
    //   1052: new android/util/MutableLong
    //   1055: dup
    //   1056: lconst_0
    //   1057: invokespecial <init> : (J)V
    //   1060: astore #9
    //   1062: aload_0
    //   1063: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1066: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   1069: aload #5
    //   1071: ldc2_w 4000
    //   1074: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
    //   1077: aload #9
    //   1079: invokevirtual waitForRequestCompleted : (Landroid/hardware/camera2/legacy/RequestHolder;JLjava/util/concurrent/TimeUnit;Landroid/util/MutableLong;)Z
    //   1082: istore #13
    //   1084: iload #13
    //   1086: ifne -> 1120
    //   1089: aload_0
    //   1090: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1093: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   1096: ldc_w 'Timed out while waiting for request to complete.'
    //   1099: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   1102: pop
    //   1103: aload_0
    //   1104: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1107: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   1110: invokevirtual failAll : ()V
    //   1113: goto -> 1120
    //   1116: astore_1
    //   1117: goto -> 1210
    //   1120: aload_0
    //   1121: getfield mMapper : Landroid/hardware/camera2/legacy/LegacyResultMapper;
    //   1124: aload_0
    //   1125: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1128: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
    //   1131: aload #9
    //   1133: getfield value : J
    //   1136: invokevirtual cachedConvertResultMetadata : (Landroid/hardware/camera2/legacy/LegacyRequest;J)Landroid/hardware/camera2/impl/CameraMetadataNative;
    //   1139: astore #9
    //   1141: aload_0
    //   1142: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1145: invokestatic access$1700 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyFocusStateMapper;
    //   1148: aload #9
    //   1150: invokevirtual mapResultTriggers : (Landroid/hardware/camera2/impl/CameraMetadataNative;)V
    //   1153: aload_0
    //   1154: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1157: invokestatic access$1600 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyFaceDetectMapper;
    //   1160: aload #9
    //   1162: aload_0
    //   1163: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1166: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
    //   1169: invokevirtual mapResultFaces : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/legacy/LegacyRequest;)V
    //   1172: aload #5
    //   1174: invokevirtual requestFailed : ()Z
    //   1177: ifne -> 1195
    //   1180: aload_0
    //   1181: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1184: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   1187: aload #5
    //   1189: aload #9
    //   1191: invokevirtual setCaptureResult : (Landroid/hardware/camera2/legacy/RequestHolder;Landroid/hardware/camera2/impl/CameraMetadataNative;)Z
    //   1194: pop
    //   1195: aload #5
    //   1197: invokevirtual isOutputAbandoned : ()Z
    //   1200: ifeq -> 1206
    //   1203: iconst_1
    //   1204: istore #6
    //   1206: goto -> 454
    //   1209: astore_1
    //   1210: aload_0
    //   1211: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1214: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   1217: ldc_w 'Interrupted waiting for request completion: '
    //   1220: aload_1
    //   1221: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1224: pop
    //   1225: aload_0
    //   1226: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1229: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   1232: iconst_1
    //   1233: invokevirtual setError : (I)V
    //   1236: goto -> 1353
    //   1239: astore_1
    //   1240: goto -> 1264
    //   1243: astore_1
    //   1244: goto -> 1294
    //   1247: astore_1
    //   1248: goto -> 1324
    //   1251: astore_1
    //   1252: goto -> 1264
    //   1255: astore_1
    //   1256: goto -> 1294
    //   1259: astore_1
    //   1260: goto -> 1324
    //   1263: astore_1
    //   1264: aload_0
    //   1265: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1268: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   1271: ldc_w 'Received device exception during capture call: '
    //   1274: aload_1
    //   1275: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1278: pop
    //   1279: aload_0
    //   1280: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1283: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   1286: iconst_1
    //   1287: invokevirtual setError : (I)V
    //   1290: goto -> 1353
    //   1293: astore_1
    //   1294: aload_0
    //   1295: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1298: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   1301: ldc_w 'Interrupted during capture: '
    //   1304: aload_1
    //   1305: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1308: pop
    //   1309: aload_0
    //   1310: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1313: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   1316: iconst_1
    //   1317: invokevirtual setError : (I)V
    //   1320: goto -> 1353
    //   1323: astore_1
    //   1324: aload_0
    //   1325: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1328: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   1331: ldc_w 'Received device exception during capture call: '
    //   1334: aload_1
    //   1335: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1338: pop
    //   1339: aload_0
    //   1340: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1343: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   1346: iconst_1
    //   1347: invokevirtual setError : (I)V
    //   1350: goto -> 1353
    //   1353: iload #6
    //   1355: ifeq -> 1462
    //   1358: aload #7
    //   1360: invokevirtual isRepeating : ()Z
    //   1363: ifeq -> 1462
    //   1366: aload_0
    //   1367: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1370: aload #7
    //   1372: invokevirtual getRequestId : ()I
    //   1375: invokevirtual cancelRepeating : (I)J
    //   1378: lstore_2
    //   1379: lload_2
    //   1380: ldc2_w -1
    //   1383: lcmp
    //   1384: ifeq -> 1406
    //   1387: aload_0
    //   1388: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1391: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   1394: lload_2
    //   1395: aload #7
    //   1397: invokevirtual getRequestId : ()I
    //   1400: invokevirtual setRepeatingRequestError : (JI)V
    //   1403: goto -> 1459
    //   1406: aload_0
    //   1407: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1410: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   1413: astore #5
    //   1415: new java/lang/StringBuilder
    //   1418: dup
    //   1419: invokespecial <init> : ()V
    //   1422: astore_1
    //   1423: aload_1
    //   1424: ldc_w 'Repeating request id: '
    //   1427: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1430: pop
    //   1431: aload_1
    //   1432: aload #7
    //   1434: invokevirtual getRequestId : ()I
    //   1437: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1440: pop
    //   1441: aload_1
    //   1442: ldc_w ' already canceled!'
    //   1445: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1448: pop
    //   1449: aload #5
    //   1451: aload_1
    //   1452: invokevirtual toString : ()Ljava/lang/String;
    //   1455: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   1458: pop
    //   1459: goto -> 1648
    //   1462: goto -> 1648
    //   1465: aload_1
    //   1466: getfield obj : Ljava/lang/Object;
    //   1469: checkcast android/hardware/camera2/legacy/RequestThreadManager$ConfigureHolder
    //   1472: astore #7
    //   1474: aload #7
    //   1476: getfield surfaces : Ljava/util/Collection;
    //   1479: ifnull -> 1497
    //   1482: aload #7
    //   1484: getfield surfaces : Ljava/util/Collection;
    //   1487: invokeinterface size : ()I
    //   1492: istore #4
    //   1494: goto -> 1500
    //   1497: iconst_0
    //   1498: istore #4
    //   1500: aload_0
    //   1501: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1504: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   1507: astore_1
    //   1508: new java/lang/StringBuilder
    //   1511: dup
    //   1512: invokespecial <init> : ()V
    //   1515: astore #5
    //   1517: aload #5
    //   1519: ldc_w 'Configure outputs: '
    //   1522: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1525: pop
    //   1526: aload #5
    //   1528: iload #4
    //   1530: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1533: pop
    //   1534: aload #5
    //   1536: ldc_w ' surfaces configured.'
    //   1539: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1542: pop
    //   1543: aload_1
    //   1544: aload #5
    //   1546: invokevirtual toString : ()Ljava/lang/String;
    //   1549: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   1552: pop
    //   1553: aload_0
    //   1554: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1557: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   1560: ldc2_w 4000
    //   1563: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
    //   1566: invokevirtual waitForEmpty : (JLjava/util/concurrent/TimeUnit;)Z
    //   1569: ifne -> 1596
    //   1572: aload_0
    //   1573: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1576: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   1579: ldc_w 'Timed out while queueing configure request.'
    //   1582: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   1585: pop
    //   1586: aload_0
    //   1587: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1590: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
    //   1593: invokevirtual failAll : ()V
    //   1596: aload_0
    //   1597: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1600: aload #7
    //   1602: getfield surfaces : Ljava/util/Collection;
    //   1605: invokestatic access$600 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Ljava/util/Collection;)V
    //   1608: aload #7
    //   1610: getfield condition : Landroid/os/ConditionVariable;
    //   1613: invokevirtual open : ()V
    //   1616: goto -> 1648
    //   1619: astore_1
    //   1620: aload_0
    //   1621: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1624: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
    //   1627: ldc_w 'Interrupted while waiting for requests to complete.'
    //   1630: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   1633: pop
    //   1634: aload_0
    //   1635: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   1638: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   1641: iconst_1
    //   1642: invokevirtual setError : (I)V
    //   1645: goto -> 1648
    //   1648: iconst_1
    //   1649: ireturn
    // Exception table:
    //   from	to	target	type
    //   46	88	91	java/lang/InterruptedException
    //   271	313	369	java/lang/InterruptedException
    //   325	336	363	finally
    //   340	354	363	finally
    //   357	360	363	finally
    //   364	367	363	finally
    //   585	600	619	java/lang/RuntimeException
    //   670	693	1323	java/io/IOException
    //   670	693	1293	java/lang/InterruptedException
    //   670	693	1263	java/lang/RuntimeException
    //   693	709	1259	java/io/IOException
    //   693	709	1255	java/lang/InterruptedException
    //   693	709	1251	java/lang/RuntimeException
    //   714	742	763	java/io/IOException
    //   714	742	759	java/lang/InterruptedException
    //   714	742	755	java/lang/RuntimeException
    //   742	752	799	java/io/IOException
    //   742	752	795	java/lang/InterruptedException
    //   742	752	791	java/lang/RuntimeException
    //   767	774	1247	java/io/IOException
    //   767	774	1243	java/lang/InterruptedException
    //   767	774	1239	java/lang/RuntimeException
    //   779	788	799	java/io/IOException
    //   779	788	795	java/lang/InterruptedException
    //   779	788	791	java/lang/RuntimeException
    //   803	810	1247	java/io/IOException
    //   803	810	1243	java/lang/InterruptedException
    //   803	810	1239	java/lang/RuntimeException
    //   815	858	799	java/io/IOException
    //   815	858	795	java/lang/InterruptedException
    //   815	858	791	java/lang/RuntimeException
    //   861	880	799	java/io/IOException
    //   861	880	795	java/lang/InterruptedException
    //   861	880	791	java/lang/RuntimeException
    //   880	925	1247	java/io/IOException
    //   880	925	1243	java/lang/InterruptedException
    //   880	925	1239	java/lang/RuntimeException
    //   930	979	799	java/io/IOException
    //   930	979	795	java/lang/InterruptedException
    //   930	979	791	java/lang/RuntimeException
    //   984	1002	1022	java/lang/RuntimeException
    //   1062	1084	1209	java/lang/InterruptedException
    //   1089	1113	1116	java/lang/InterruptedException
    //   1553	1596	1619	java/lang/InterruptedException
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestThreadManager$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */