package android.app;

import android.content.Context;
import android.os.ServiceManager;

abstract class StaticApplicationContextServiceFetcher<T> implements SystemServiceRegistry.ServiceFetcher<T> {
  private T mCachedInstance;
  
  public abstract T createService(Context paramContext) throws ServiceManager.ServiceNotFoundException;
  
  public final T getService(ContextImpl paramContextImpl) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCachedInstance : Ljava/lang/Object;
    //   6: ifnonnull -> 40
    //   9: aload_1
    //   10: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   13: astore_2
    //   14: aload_2
    //   15: ifnull -> 23
    //   18: aload_2
    //   19: astore_1
    //   20: goto -> 23
    //   23: aload_0
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual createService : (Landroid/content/Context;)Ljava/lang/Object;
    //   29: putfield mCachedInstance : Ljava/lang/Object;
    //   32: goto -> 40
    //   35: astore_1
    //   36: aload_1
    //   37: invokestatic onServiceNotFound : (Landroid/os/ServiceManager$ServiceNotFoundException;)V
    //   40: aload_0
    //   41: getfield mCachedInstance : Ljava/lang/Object;
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: areturn
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	49	finally
    //   23	32	35	android/os/ServiceManager$ServiceNotFoundException
    //   23	32	49	finally
    //   36	40	49	finally
    //   40	47	49	finally
    //   50	52	49	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$StaticApplicationContextServiceFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */