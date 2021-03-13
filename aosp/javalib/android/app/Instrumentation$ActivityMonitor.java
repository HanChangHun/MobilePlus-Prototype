package android.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ActivityMonitor {
  private final boolean mBlock = false;
  
  private final String mClass = null;
  
  int mHits = 0;
  
  private final boolean mIgnoreMatchingSpecificIntents = true;
  
  Activity mLastActivity = null;
  
  private final Instrumentation.ActivityResult mResult = null;
  
  private final IntentFilter mWhich = null;
  
  public ActivityMonitor() {}
  
  public ActivityMonitor(IntentFilter paramIntentFilter, Instrumentation.ActivityResult paramActivityResult, boolean paramBoolean) {}
  
  public ActivityMonitor(String paramString, Instrumentation.ActivityResult paramActivityResult, boolean paramBoolean) {}
  
  public final IntentFilter getFilter() {
    return this.mWhich;
  }
  
  public final int getHits() {
    return this.mHits;
  }
  
  public final Activity getLastActivity() {
    return this.mLastActivity;
  }
  
  public final Instrumentation.ActivityResult getResult() {
    return this.mResult;
  }
  
  final boolean ignoreMatchingSpecificIntents() {
    return this.mIgnoreMatchingSpecificIntents;
  }
  
  public final boolean isBlocking() {
    return this.mBlock;
  }
  
  final boolean match(Context paramContext, Activity paramActivity, Intent paramIntent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mIgnoreMatchingSpecificIntents : Z
    //   4: ifeq -> 9
    //   7: iconst_0
    //   8: ireturn
    //   9: aload_0
    //   10: monitorenter
    //   11: aload_0
    //   12: getfield mWhich : Landroid/content/IntentFilter;
    //   15: ifnull -> 40
    //   18: aload_0
    //   19: getfield mWhich : Landroid/content/IntentFilter;
    //   22: aload_1
    //   23: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   26: aload_3
    //   27: iconst_1
    //   28: ldc 'Instrumentation'
    //   30: invokevirtual match : (Landroid/content/ContentResolver;Landroid/content/Intent;ZLjava/lang/String;)I
    //   33: ifge -> 40
    //   36: aload_0
    //   37: monitorexit
    //   38: iconst_0
    //   39: ireturn
    //   40: aload_0
    //   41: getfield mClass : Ljava/lang/String;
    //   44: ifnull -> 98
    //   47: aconst_null
    //   48: astore_1
    //   49: aload_2
    //   50: ifnull -> 64
    //   53: aload_2
    //   54: invokevirtual getClass : ()Ljava/lang/Class;
    //   57: invokevirtual getName : ()Ljava/lang/String;
    //   60: astore_1
    //   61: goto -> 79
    //   64: aload_3
    //   65: invokevirtual getComponent : ()Landroid/content/ComponentName;
    //   68: ifnull -> 79
    //   71: aload_3
    //   72: invokevirtual getComponent : ()Landroid/content/ComponentName;
    //   75: invokevirtual getClassName : ()Ljava/lang/String;
    //   78: astore_1
    //   79: aload_1
    //   80: ifnull -> 94
    //   83: aload_0
    //   84: getfield mClass : Ljava/lang/String;
    //   87: aload_1
    //   88: invokevirtual equals : (Ljava/lang/Object;)Z
    //   91: ifne -> 98
    //   94: aload_0
    //   95: monitorexit
    //   96: iconst_0
    //   97: ireturn
    //   98: aload_2
    //   99: ifnull -> 111
    //   102: aload_0
    //   103: aload_2
    //   104: putfield mLastActivity : Landroid/app/Activity;
    //   107: aload_0
    //   108: invokevirtual notifyAll : ()V
    //   111: aload_0
    //   112: monitorexit
    //   113: iconst_1
    //   114: ireturn
    //   115: astore_1
    //   116: aload_0
    //   117: monitorexit
    //   118: aload_1
    //   119: athrow
    // Exception table:
    //   from	to	target	type
    //   11	38	115	finally
    //   40	47	115	finally
    //   53	61	115	finally
    //   64	79	115	finally
    //   83	94	115	finally
    //   94	96	115	finally
    //   102	111	115	finally
    //   111	113	115	finally
    //   116	118	115	finally
  }
  
  public Instrumentation.ActivityResult onStartActivity(Intent paramIntent) {
    return null;
  }
  
  public final Activity waitForActivity() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mLastActivity : Landroid/app/Activity;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnonnull -> 22
    //   11: aload_0
    //   12: invokevirtual wait : ()V
    //   15: goto -> 2
    //   18: astore_1
    //   19: goto -> 15
    //   22: aload_0
    //   23: getfield mLastActivity : Landroid/app/Activity;
    //   26: astore_1
    //   27: aload_0
    //   28: aconst_null
    //   29: putfield mLastActivity : Landroid/app/Activity;
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: areturn
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	36	finally
    //   11	15	18	java/lang/InterruptedException
    //   11	15	36	finally
    //   22	34	36	finally
    //   37	39	36	finally
  }
  
  public final Activity waitForActivityWithTimeout(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mLastActivity : Landroid/app/Activity;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnonnull -> 20
    //   11: aload_0
    //   12: lload_1
    //   13: invokevirtual wait : (J)V
    //   16: goto -> 20
    //   19: astore_3
    //   20: aload_0
    //   21: getfield mLastActivity : Landroid/app/Activity;
    //   24: ifnonnull -> 31
    //   27: aload_0
    //   28: monitorexit
    //   29: aconst_null
    //   30: areturn
    //   31: aload_0
    //   32: getfield mLastActivity : Landroid/app/Activity;
    //   35: astore_3
    //   36: aload_0
    //   37: aconst_null
    //   38: putfield mLastActivity : Landroid/app/Activity;
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_3
    //   44: areturn
    //   45: astore_3
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_3
    //   49: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	45	finally
    //   11	16	19	java/lang/InterruptedException
    //   11	16	45	finally
    //   20	29	45	finally
    //   31	43	45	finally
    //   46	48	45	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Instrumentation$ActivityMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */