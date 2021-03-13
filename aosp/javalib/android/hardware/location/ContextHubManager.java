package android.hardware.location;

import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

@SystemApi
public final class ContextHubManager {
  public static final int EVENT_HUB_RESET = 6;
  
  public static final int EVENT_NANOAPP_ABORTED = 4;
  
  public static final int EVENT_NANOAPP_DISABLED = 3;
  
  public static final int EVENT_NANOAPP_ENABLED = 2;
  
  public static final int EVENT_NANOAPP_LOADED = 0;
  
  public static final int EVENT_NANOAPP_MESSAGE = 5;
  
  public static final int EVENT_NANOAPP_UNLOADED = 1;
  
  public static final String EXTRA_CONTEXT_HUB_INFO = "android.hardware.location.extra.CONTEXT_HUB_INFO";
  
  public static final String EXTRA_EVENT_TYPE = "android.hardware.location.extra.EVENT_TYPE";
  
  public static final String EXTRA_MESSAGE = "android.hardware.location.extra.MESSAGE";
  
  public static final String EXTRA_NANOAPP_ABORT_CODE = "android.hardware.location.extra.NANOAPP_ABORT_CODE";
  
  public static final String EXTRA_NANOAPP_ID = "android.hardware.location.extra.NANOAPP_ID";
  
  private static final String TAG = "ContextHubManager";
  
  private Callback mCallback;
  
  private Handler mCallbackHandler;
  
  private final IContextHubCallback.Stub mClientCallback = new IContextHubCallback.Stub() {
      public void onMessageReceipt(int param1Int1, int param1Int2, ContextHubMessage param1ContextHubMessage) {
        synchronized (ContextHubManager.this) {
          if (ContextHubManager.this.mCallback != null) {
            Handler handler = ContextHubManager.this.mCallbackHandler;
            _$$Lambda$ContextHubManager$4$sylEfC1Rx_cxuQRnKuthZXmV8KI _$$Lambda$ContextHubManager$4$sylEfC1Rx_cxuQRnKuthZXmV8KI = new _$$Lambda$ContextHubManager$4$sylEfC1Rx_cxuQRnKuthZXmV8KI();
            this(this, param1Int1, param1Int2, param1ContextHubMessage);
            handler.post(_$$Lambda$ContextHubManager$4$sylEfC1Rx_cxuQRnKuthZXmV8KI);
          } else if (ContextHubManager.this.mLocalCallback != null) {
            ContextHubManager.this.mLocalCallback.onMessageReceipt(param1Int1, param1Int2, param1ContextHubMessage);
          } 
          return;
        } 
      }
    };
  
  @Deprecated
  private ICallback mLocalCallback;
  
  private final Looper mMainLooper;
  
  private final IContextHubService mService;
  
  public ContextHubManager(Context paramContext, Looper paramLooper) throws ServiceManager.ServiceNotFoundException {
    this.mMainLooper = paramLooper;
    IContextHubService iContextHubService = IContextHubService.Stub.asInterface(ServiceManager.getServiceOrThrow("contexthub"));
    this.mService = iContextHubService;
    try {
      iContextHubService.registerCallback(this.mClientCallback);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private IContextHubClientCallback createClientCallback(final ContextHubClient client, final ContextHubClientCallback callback, final Executor executor) {
    return new IContextHubClientCallback.Stub() {
        public void onHubReset() {
          executor.execute(new _$$Lambda$ContextHubManager$3$kLhhBRChCeue1LKohd5lK_lfKTU(callback, client));
        }
        
        public void onMessageFromNanoApp(NanoAppMessage param1NanoAppMessage) {
          executor.execute(new _$$Lambda$ContextHubManager$3$U9x_HK_GdADIEQ3mS5mDWMNWMu8(callback, client, param1NanoAppMessage));
        }
        
        public void onNanoAppAborted(long param1Long, int param1Int) {
          executor.execute(new _$$Lambda$ContextHubManager$3$hASoxw9hzmd9l2NpC91O5tXLzxU(callback, client, param1Long, param1Int));
        }
        
        public void onNanoAppDisabled(long param1Long) {
          executor.execute(new _$$Lambda$ContextHubManager$3$On2Q5Obzm4_zLY0UP3Xs4E3P_V0(callback, client, param1Long));
        }
        
        public void onNanoAppEnabled(long param1Long) {
          executor.execute(new _$$Lambda$ContextHubManager$3$8oeFzBAC_VuH1d32Kod8BVn0Os8(callback, client, param1Long));
        }
        
        public void onNanoAppLoaded(long param1Long) {
          executor.execute(new _$$Lambda$ContextHubManager$3$5yx25kUuvL9qy3uBcIzI3sQQoL8(callback, client, param1Long));
        }
        
        public void onNanoAppUnloaded(long param1Long) {
          executor.execute(new _$$Lambda$ContextHubManager$3$KgVQePwT_QpjU9EQTp2L3LsHE5Y(callback, client, param1Long));
        }
      };
  }
  
  private IContextHubTransactionCallback createQueryCallback(final ContextHubTransaction<List<NanoAppState>> transaction) {
    return new IContextHubTransactionCallback.Stub() {
        public void onQueryResponse(int param1Int, List<NanoAppState> param1List) {
          transaction.setResponse(new ContextHubTransaction.Response<>(param1Int, param1List));
        }
        
        public void onTransactionComplete(int param1Int) {
          Log.e("ContextHubManager", "Received a non-query callback on a query request");
          transaction.setResponse(new ContextHubTransaction.Response(7, null));
        }
      };
  }
  
  private IContextHubTransactionCallback createTransactionCallback(final ContextHubTransaction<Void> transaction) {
    return new IContextHubTransactionCallback.Stub() {
        public void onQueryResponse(int param1Int, List<NanoAppState> param1List) {
          Log.e("ContextHubManager", "Received a query callback on a non-query request");
          transaction.setResponse(new ContextHubTransaction.Response(7, null));
        }
        
        public void onTransactionComplete(int param1Int) {
          transaction.setResponse(new ContextHubTransaction.Response(param1Int, null));
        }
      };
  }
  
  private void invokeOnMessageReceiptCallback(int paramInt1, int paramInt2, ContextHubMessage paramContextHubMessage) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCallback : Landroid/hardware/location/ContextHubManager$Callback;
    //   6: ifnull -> 19
    //   9: aload_0
    //   10: getfield mCallback : Landroid/hardware/location/ContextHubManager$Callback;
    //   13: iload_1
    //   14: iload_2
    //   15: aload_3
    //   16: invokevirtual onMessageReceipt : (IILandroid/hardware/location/ContextHubMessage;)V
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: astore_3
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_3
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	22	finally
  }
  
  public ContextHubClient createClient(ContextHubInfo paramContextHubInfo, PendingIntent paramPendingIntent, long paramLong) {
    Objects.requireNonNull(paramPendingIntent);
    Objects.requireNonNull(paramContextHubInfo);
    ContextHubClient contextHubClient = new ContextHubClient(paramContextHubInfo, true);
    try {
      IContextHubClient iContextHubClient = this.mService.createPendingIntentClient(paramContextHubInfo.getId(), paramPendingIntent, paramLong);
      contextHubClient.setClientProxy(iContextHubClient);
      return contextHubClient;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ContextHubClient createClient(ContextHubInfo paramContextHubInfo, ContextHubClientCallback paramContextHubClientCallback) {
    return createClient(paramContextHubInfo, paramContextHubClientCallback, (Executor)new HandlerExecutor(Handler.getMain()));
  }
  
  public ContextHubClient createClient(ContextHubInfo paramContextHubInfo, ContextHubClientCallback paramContextHubClientCallback, Executor paramExecutor) {
    Objects.requireNonNull(paramContextHubClientCallback, "Callback cannot be null");
    Objects.requireNonNull(paramContextHubInfo, "ContextHubInfo cannot be null");
    Objects.requireNonNull(paramExecutor, "Executor cannot be null");
    ContextHubClient contextHubClient = new ContextHubClient(paramContextHubInfo, false);
    IContextHubClientCallback iContextHubClientCallback = createClientCallback(contextHubClient, paramContextHubClientCallback, paramExecutor);
    try {
      IContextHubClient iContextHubClient = this.mService.createClient(paramContextHubInfo.getId(), iContextHubClientCallback);
      contextHubClient.setClientProxy(iContextHubClient);
      return contextHubClient;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ContextHubTransaction<Void> disableNanoApp(ContextHubInfo paramContextHubInfo, long paramLong) {
    Objects.requireNonNull(paramContextHubInfo, "ContextHubInfo cannot be null");
    ContextHubTransaction<Void> contextHubTransaction = new ContextHubTransaction(3);
    IContextHubTransactionCallback iContextHubTransactionCallback = createTransactionCallback(contextHubTransaction);
    try {
      this.mService.disableNanoApp(paramContextHubInfo.getId(), iContextHubTransactionCallback, paramLong);
      return contextHubTransaction;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ContextHubTransaction<Void> enableNanoApp(ContextHubInfo paramContextHubInfo, long paramLong) {
    Objects.requireNonNull(paramContextHubInfo, "ContextHubInfo cannot be null");
    ContextHubTransaction<Void> contextHubTransaction = new ContextHubTransaction(2);
    IContextHubTransactionCallback iContextHubTransactionCallback = createTransactionCallback(contextHubTransaction);
    try {
      this.mService.enableNanoApp(paramContextHubInfo.getId(), iContextHubTransactionCallback, paramLong);
      return contextHubTransaction;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public int[] findNanoAppOnHub(int paramInt, NanoAppFilter paramNanoAppFilter) {
    try {
      return this.mService.findNanoAppOnHub(paramInt, paramNanoAppFilter);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public int[] getContextHubHandles() {
    try {
      return this.mService.getContextHubHandles();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public ContextHubInfo getContextHubInfo(int paramInt) {
    try {
      return this.mService.getContextHubInfo(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ContextHubInfo> getContextHubs() {
    try {
      return this.mService.getContextHubs();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public NanoAppInstanceInfo getNanoAppInstanceInfo(int paramInt) {
    try {
      return this.mService.getNanoAppInstanceInfo(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public int loadNanoApp(int paramInt, NanoApp paramNanoApp) {
    try {
      return this.mService.loadNanoApp(paramInt, paramNanoApp);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ContextHubTransaction<Void> loadNanoApp(ContextHubInfo paramContextHubInfo, NanoAppBinary paramNanoAppBinary) {
    Objects.requireNonNull(paramContextHubInfo, "ContextHubInfo cannot be null");
    Objects.requireNonNull(paramNanoAppBinary, "NanoAppBinary cannot be null");
    ContextHubTransaction<Void> contextHubTransaction = new ContextHubTransaction(0);
    IContextHubTransactionCallback iContextHubTransactionCallback = createTransactionCallback(contextHubTransaction);
    try {
      this.mService.loadNanoAppOnHub(paramContextHubInfo.getId(), iContextHubTransactionCallback, paramNanoAppBinary);
      return contextHubTransaction;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ContextHubTransaction<List<NanoAppState>> queryNanoApps(ContextHubInfo paramContextHubInfo) {
    Objects.requireNonNull(paramContextHubInfo, "ContextHubInfo cannot be null");
    ContextHubTransaction<List<NanoAppState>> contextHubTransaction = new ContextHubTransaction(4);
    IContextHubTransactionCallback iContextHubTransactionCallback = createQueryCallback(contextHubTransaction);
    try {
      this.mService.queryNanoApps(paramContextHubInfo.getId(), iContextHubTransactionCallback);
      return contextHubTransaction;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public int registerCallback(Callback paramCallback) {
    return registerCallback(paramCallback, null);
  }
  
  @Deprecated
  public int registerCallback(Callback paramCallback, Handler paramHandler) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCallback : Landroid/hardware/location/ContextHubManager$Callback;
    //   6: ifnull -> 22
    //   9: ldc 'ContextHubManager'
    //   11: ldc_w 'Max number of callbacks reached!'
    //   14: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   17: pop
    //   18: aload_0
    //   19: monitorexit
    //   20: iconst_m1
    //   21: ireturn
    //   22: aload_0
    //   23: aload_1
    //   24: putfield mCallback : Landroid/hardware/location/ContextHubManager$Callback;
    //   27: aload_2
    //   28: ifnonnull -> 46
    //   31: new android/os/Handler
    //   34: astore_1
    //   35: aload_1
    //   36: aload_0
    //   37: getfield mMainLooper : Landroid/os/Looper;
    //   40: invokespecial <init> : (Landroid/os/Looper;)V
    //   43: goto -> 48
    //   46: aload_2
    //   47: astore_1
    //   48: aload_0
    //   49: aload_1
    //   50: putfield mCallbackHandler : Landroid/os/Handler;
    //   53: aload_0
    //   54: monitorexit
    //   55: iconst_0
    //   56: ireturn
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	57	finally
    //   22	27	57	finally
    //   31	43	57	finally
    //   48	55	57	finally
    //   58	60	57	finally
  }
  
  @Deprecated
  public int registerCallback(ICallback paramICallback) {
    if (this.mLocalCallback != null) {
      Log.w("ContextHubManager", "Max number of local callbacks reached!");
      return -1;
    } 
    this.mLocalCallback = paramICallback;
    return 0;
  }
  
  @Deprecated
  public int sendMessage(int paramInt1, int paramInt2, ContextHubMessage paramContextHubMessage) {
    try {
      return this.mService.sendMessage(paramInt1, paramInt2, paramContextHubMessage);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public int unloadNanoApp(int paramInt) {
    try {
      return this.mService.unloadNanoApp(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ContextHubTransaction<Void> unloadNanoApp(ContextHubInfo paramContextHubInfo, long paramLong) {
    Objects.requireNonNull(paramContextHubInfo, "ContextHubInfo cannot be null");
    ContextHubTransaction<Void> contextHubTransaction = new ContextHubTransaction(1);
    IContextHubTransactionCallback iContextHubTransactionCallback = createTransactionCallback(contextHubTransaction);
    try {
      this.mService.unloadNanoAppFromHub(paramContextHubInfo.getId(), iContextHubTransactionCallback, paramLong);
      return contextHubTransaction;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public int unregisterCallback(Callback paramCallback) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: aload_0
    //   4: getfield mCallback : Landroid/hardware/location/ContextHubManager$Callback;
    //   7: if_acmpeq -> 23
    //   10: ldc 'ContextHubManager'
    //   12: ldc_w 'Cannot recognize callback!'
    //   15: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   18: pop
    //   19: aload_0
    //   20: monitorexit
    //   21: iconst_m1
    //   22: ireturn
    //   23: aload_0
    //   24: aconst_null
    //   25: putfield mCallback : Landroid/hardware/location/ContextHubManager$Callback;
    //   28: aload_0
    //   29: aconst_null
    //   30: putfield mCallbackHandler : Landroid/os/Handler;
    //   33: aload_0
    //   34: monitorexit
    //   35: iconst_0
    //   36: ireturn
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	37	finally
    //   23	35	37	finally
    //   38	40	37	finally
  }
  
  @Deprecated
  public int unregisterCallback(ICallback paramICallback) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: aload_0
    //   4: getfield mLocalCallback : Landroid/hardware/location/ContextHubManager$ICallback;
    //   7: if_acmpeq -> 23
    //   10: ldc 'ContextHubManager'
    //   12: ldc_w 'Cannot recognize local callback!'
    //   15: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   18: pop
    //   19: aload_0
    //   20: monitorexit
    //   21: iconst_m1
    //   22: ireturn
    //   23: aload_0
    //   24: aconst_null
    //   25: putfield mLocalCallback : Landroid/hardware/location/ContextHubManager$ICallback;
    //   28: aload_0
    //   29: monitorexit
    //   30: iconst_0
    //   31: ireturn
    //   32: astore_1
    //   33: aload_0
    //   34: monitorexit
    //   35: aload_1
    //   36: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	32	finally
    //   23	28	32	finally
  }
  
  @Deprecated
  public static abstract class Callback {
    public abstract void onMessageReceipt(int param1Int1, int param1Int2, ContextHubMessage param1ContextHubMessage);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Event {}
  
  @Deprecated
  public static interface ICallback {
    void onMessageReceipt(int param1Int1, int param1Int2, ContextHubMessage param1ContextHubMessage);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */