package android.app;

import android.os.ServiceManager;

abstract class StaticServiceFetcher<T> implements SystemServiceRegistry.ServiceFetcher<T> {
  private T mCachedInstance;
  
  public abstract T createService() throws ServiceManager.ServiceNotFoundException;
  
  public final T getService(ContextImpl paramContextImpl) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCachedInstance : Ljava/lang/Object;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnonnull -> 27
    //   11: aload_0
    //   12: aload_0
    //   13: invokevirtual createService : ()Ljava/lang/Object;
    //   16: putfield mCachedInstance : Ljava/lang/Object;
    //   19: goto -> 27
    //   22: astore_1
    //   23: aload_1
    //   24: invokestatic onServiceNotFound : (Landroid/os/ServiceManager$ServiceNotFoundException;)V
    //   27: aload_0
    //   28: getfield mCachedInstance : Ljava/lang/Object;
    //   31: astore_1
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
    //   11	19	22	android/os/ServiceManager$ServiceNotFoundException
    //   11	19	36	finally
    //   23	27	36	finally
    //   27	34	36	finally
    //   37	39	36	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$StaticServiceFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */