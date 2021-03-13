package android.app;

import android.os.ServiceManager;

abstract class CachedServiceFetcher<T> implements SystemServiceRegistry.ServiceFetcher<T> {
  private final int mCacheIndex = SystemServiceRegistry.access$008();
  
  public abstract T createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException;
  
  public final T getService(ContextImpl paramContextImpl) {
    // Byte code:
    //   0: aload_1
    //   1: getfield mServiceCache : [Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_1
    //   6: getfield mServiceInitializationStateArray : [I
    //   9: astore_3
    //   10: iconst_0
    //   11: istore #4
    //   13: iconst_0
    //   14: istore #5
    //   16: aload_2
    //   17: monitorenter
    //   18: aload_2
    //   19: aload_0
    //   20: getfield mCacheIndex : I
    //   23: aaload
    //   24: astore #6
    //   26: aload #6
    //   28: ifnonnull -> 254
    //   31: aload_3
    //   32: aload_0
    //   33: getfield mCacheIndex : I
    //   36: iaload
    //   37: iconst_3
    //   38: if_icmpne -> 44
    //   41: goto -> 254
    //   44: aload_3
    //   45: aload_0
    //   46: getfield mCacheIndex : I
    //   49: iaload
    //   50: iconst_2
    //   51: if_icmpne -> 61
    //   54: aload_3
    //   55: aload_0
    //   56: getfield mCacheIndex : I
    //   59: iconst_0
    //   60: iastore
    //   61: aload_3
    //   62: aload_0
    //   63: getfield mCacheIndex : I
    //   66: iaload
    //   67: ifne -> 80
    //   70: iconst_1
    //   71: istore #5
    //   73: aload_3
    //   74: aload_0
    //   75: getfield mCacheIndex : I
    //   78: iconst_1
    //   79: iastore
    //   80: aload_2
    //   81: monitorexit
    //   82: iload #5
    //   84: ifeq -> 197
    //   87: aconst_null
    //   88: astore #6
    //   90: aload_0
    //   91: aload_1
    //   92: invokevirtual createService : (Landroid/app/ContextImpl;)Ljava/lang/Object;
    //   95: astore_1
    //   96: aload_2
    //   97: monitorenter
    //   98: aload_2
    //   99: aload_0
    //   100: getfield mCacheIndex : I
    //   103: aload_1
    //   104: aastore
    //   105: aload_3
    //   106: aload_0
    //   107: getfield mCacheIndex : I
    //   110: iconst_2
    //   111: iastore
    //   112: aload_2
    //   113: invokevirtual notifyAll : ()V
    //   116: aload_2
    //   117: monitorexit
    //   118: goto -> 160
    //   121: astore_1
    //   122: aload_2
    //   123: monitorexit
    //   124: aload_1
    //   125: athrow
    //   126: astore_1
    //   127: goto -> 168
    //   130: astore_1
    //   131: aload_1
    //   132: invokestatic onServiceNotFound : (Landroid/os/ServiceManager$ServiceNotFoundException;)V
    //   135: aload_2
    //   136: monitorenter
    //   137: aload_2
    //   138: aload_0
    //   139: getfield mCacheIndex : I
    //   142: aconst_null
    //   143: aastore
    //   144: aload_3
    //   145: aload_0
    //   146: getfield mCacheIndex : I
    //   149: iconst_3
    //   150: iastore
    //   151: aload_2
    //   152: invokevirtual notifyAll : ()V
    //   155: aload_2
    //   156: monitorexit
    //   157: aload #6
    //   159: astore_1
    //   160: goto -> 259
    //   163: astore_1
    //   164: aload_2
    //   165: monitorexit
    //   166: aload_1
    //   167: athrow
    //   168: aload_2
    //   169: monitorenter
    //   170: aload_2
    //   171: aload_0
    //   172: getfield mCacheIndex : I
    //   175: aconst_null
    //   176: aastore
    //   177: aload_3
    //   178: aload_0
    //   179: getfield mCacheIndex : I
    //   182: iconst_3
    //   183: iastore
    //   184: aload_2
    //   185: invokevirtual notifyAll : ()V
    //   188: aload_2
    //   189: monitorexit
    //   190: aload_1
    //   191: athrow
    //   192: astore_1
    //   193: aload_2
    //   194: monitorexit
    //   195: aload_1
    //   196: athrow
    //   197: aload_2
    //   198: monitorenter
    //   199: aload_3
    //   200: aload_0
    //   201: getfield mCacheIndex : I
    //   204: iaload
    //   205: istore #5
    //   207: iload #5
    //   209: iconst_2
    //   210: if_icmpge -> 244
    //   213: iload #4
    //   215: invokestatic interrupted : ()Z
    //   218: ior
    //   219: istore #4
    //   221: aload_2
    //   222: invokevirtual wait : ()V
    //   225: goto -> 199
    //   228: astore #6
    //   230: ldc 'SystemServiceRegistry'
    //   232: ldc 'getService() interrupted'
    //   234: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   237: pop
    //   238: iconst_1
    //   239: istore #4
    //   241: goto -> 225
    //   244: aload_2
    //   245: monitorexit
    //   246: goto -> 13
    //   249: astore_1
    //   250: aload_2
    //   251: monitorexit
    //   252: aload_1
    //   253: athrow
    //   254: aload #6
    //   256: astore_1
    //   257: aload_2
    //   258: monitorexit
    //   259: iload #4
    //   261: ifeq -> 270
    //   264: invokestatic currentThread : ()Ljava/lang/Thread;
    //   267: invokevirtual interrupt : ()V
    //   270: aload_1
    //   271: areturn
    //   272: astore_1
    //   273: aload_2
    //   274: monitorexit
    //   275: aload_1
    //   276: athrow
    // Exception table:
    //   from	to	target	type
    //   18	26	272	finally
    //   31	41	272	finally
    //   44	61	272	finally
    //   61	70	272	finally
    //   73	80	272	finally
    //   80	82	272	finally
    //   90	96	130	android/os/ServiceManager$ServiceNotFoundException
    //   90	96	126	finally
    //   98	118	121	finally
    //   122	124	121	finally
    //   131	135	126	finally
    //   137	157	163	finally
    //   164	166	163	finally
    //   170	190	192	finally
    //   193	195	192	finally
    //   199	207	249	finally
    //   213	225	228	java/lang/InterruptedException
    //   213	225	249	finally
    //   230	238	249	finally
    //   244	246	249	finally
    //   250	252	249	finally
    //   257	259	272	finally
    //   273	275	272	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$CachedServiceFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */