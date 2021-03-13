package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.ArrayMap;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

final class ServiceDispatcher {
  private final ArrayMap<ComponentName, ConnectionInfo> mActiveConnections = new ArrayMap();
  
  private final Executor mActivityExecutor;
  
  private final Handler mActivityThread;
  
  private final ServiceConnection mConnection;
  
  private final Context mContext;
  
  private final int mFlags;
  
  private boolean mForgotten;
  
  private final InnerConnection mIServiceConnection = new InnerConnection(this);
  
  private final ServiceConnectionLeaked mLocation;
  
  private RuntimeException mUnbindLocation;
  
  ServiceDispatcher(ServiceConnection paramServiceConnection, Context paramContext, Handler paramHandler, int paramInt) {
    this.mConnection = paramServiceConnection;
    this.mContext = paramContext;
    this.mActivityThread = paramHandler;
    this.mActivityExecutor = null;
    ServiceConnectionLeaked serviceConnectionLeaked = new ServiceConnectionLeaked(null);
    this.mLocation = serviceConnectionLeaked;
    serviceConnectionLeaked.fillInStackTrace();
    this.mFlags = paramInt;
  }
  
  ServiceDispatcher(ServiceConnection paramServiceConnection, Context paramContext, Executor paramExecutor, int paramInt) {
    this.mConnection = paramServiceConnection;
    this.mContext = paramContext;
    this.mActivityThread = null;
    this.mActivityExecutor = paramExecutor;
    ServiceConnectionLeaked serviceConnectionLeaked = new ServiceConnectionLeaked(null);
    this.mLocation = serviceConnectionLeaked;
    serviceConnectionLeaked.fillInStackTrace();
    this.mFlags = paramInt;
  }
  
  public void connected(ComponentName paramComponentName, IBinder paramIBinder, boolean paramBoolean) {
    Executor executor = this.mActivityExecutor;
    if (executor != null) {
      executor.execute(new RunConnection(paramComponentName, paramIBinder, 0, paramBoolean));
    } else {
      Handler handler = this.mActivityThread;
      if (handler != null) {
        handler.post(new RunConnection(paramComponentName, paramIBinder, 0, paramBoolean));
      } else {
        doConnected(paramComponentName, paramIBinder, paramBoolean);
      } 
    } 
  }
  
  public void death(ComponentName paramComponentName, IBinder paramIBinder) {
    Executor executor = this.mActivityExecutor;
    if (executor != null) {
      executor.execute(new RunConnection(paramComponentName, paramIBinder, 1, false));
    } else {
      Handler handler = this.mActivityThread;
      if (handler != null) {
        handler.post(new RunConnection(paramComponentName, paramIBinder, 1, false));
      } else {
        doDeath(paramComponentName, paramIBinder);
      } 
    } 
  }
  
  public void doConnected(ComponentName paramComponentName, IBinder paramIBinder, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mForgotten : Z
    //   6: ifeq -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: getfield mActiveConnections : Landroid/util/ArrayMap;
    //   16: aload_1
    //   17: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   20: checkcast android/app/LoadedApk$ServiceDispatcher$ConnectionInfo
    //   23: astore #4
    //   25: aload #4
    //   27: ifnull -> 42
    //   30: aload #4
    //   32: getfield binder : Landroid/os/IBinder;
    //   35: aload_2
    //   36: if_acmpne -> 42
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_2
    //   43: ifnull -> 122
    //   46: new android/app/LoadedApk$ServiceDispatcher$ConnectionInfo
    //   49: astore #5
    //   51: aload #5
    //   53: aconst_null
    //   54: invokespecial <init> : (Landroid/app/LoadedApk$1;)V
    //   57: aload #5
    //   59: aload_2
    //   60: putfield binder : Landroid/os/IBinder;
    //   63: new android/app/LoadedApk$ServiceDispatcher$DeathMonitor
    //   66: astore #6
    //   68: aload #6
    //   70: aload_0
    //   71: aload_1
    //   72: aload_2
    //   73: invokespecial <init> : (Landroid/app/LoadedApk$ServiceDispatcher;Landroid/content/ComponentName;Landroid/os/IBinder;)V
    //   76: aload #5
    //   78: aload #6
    //   80: putfield deathMonitor : Landroid/os/IBinder$DeathRecipient;
    //   83: aload_2
    //   84: aload #5
    //   86: getfield deathMonitor : Landroid/os/IBinder$DeathRecipient;
    //   89: iconst_0
    //   90: invokeinterface linkToDeath : (Landroid/os/IBinder$DeathRecipient;I)V
    //   95: aload_0
    //   96: getfield mActiveConnections : Landroid/util/ArrayMap;
    //   99: aload_1
    //   100: aload #5
    //   102: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   105: pop
    //   106: goto -> 131
    //   109: astore_2
    //   110: aload_0
    //   111: getfield mActiveConnections : Landroid/util/ArrayMap;
    //   114: aload_1
    //   115: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   118: pop
    //   119: aload_0
    //   120: monitorexit
    //   121: return
    //   122: aload_0
    //   123: getfield mActiveConnections : Landroid/util/ArrayMap;
    //   126: aload_1
    //   127: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   130: pop
    //   131: aload #4
    //   133: ifnull -> 153
    //   136: aload #4
    //   138: getfield binder : Landroid/os/IBinder;
    //   141: aload #4
    //   143: getfield deathMonitor : Landroid/os/IBinder$DeathRecipient;
    //   146: iconst_0
    //   147: invokeinterface unlinkToDeath : (Landroid/os/IBinder$DeathRecipient;I)Z
    //   152: pop
    //   153: aload_0
    //   154: monitorexit
    //   155: aload #4
    //   157: ifnull -> 170
    //   160: aload_0
    //   161: getfield mConnection : Landroid/content/ServiceConnection;
    //   164: aload_1
    //   165: invokeinterface onServiceDisconnected : (Landroid/content/ComponentName;)V
    //   170: iload_3
    //   171: ifeq -> 184
    //   174: aload_0
    //   175: getfield mConnection : Landroid/content/ServiceConnection;
    //   178: aload_1
    //   179: invokeinterface onBindingDied : (Landroid/content/ComponentName;)V
    //   184: aload_2
    //   185: ifnull -> 202
    //   188: aload_0
    //   189: getfield mConnection : Landroid/content/ServiceConnection;
    //   192: aload_1
    //   193: aload_2
    //   194: invokeinterface onServiceConnected : (Landroid/content/ComponentName;Landroid/os/IBinder;)V
    //   199: goto -> 212
    //   202: aload_0
    //   203: getfield mConnection : Landroid/content/ServiceConnection;
    //   206: aload_1
    //   207: invokeinterface onNullBinding : (Landroid/content/ComponentName;)V
    //   212: return
    //   213: astore_1
    //   214: aload_0
    //   215: monitorexit
    //   216: aload_1
    //   217: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	213	finally
    //   12	25	213	finally
    //   30	41	213	finally
    //   46	83	213	finally
    //   83	106	109	android/os/RemoteException
    //   83	106	213	finally
    //   110	121	213	finally
    //   122	131	213	finally
    //   136	153	213	finally
    //   153	155	213	finally
    //   214	216	213	finally
  }
  
  public void doDeath(ComponentName paramComponentName, IBinder paramIBinder) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mActiveConnections : Landroid/util/ArrayMap;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast android/app/LoadedApk$ServiceDispatcher$ConnectionInfo
    //   13: astore_3
    //   14: aload_3
    //   15: ifnull -> 66
    //   18: aload_3
    //   19: getfield binder : Landroid/os/IBinder;
    //   22: aload_2
    //   23: if_acmpeq -> 29
    //   26: goto -> 66
    //   29: aload_0
    //   30: getfield mActiveConnections : Landroid/util/ArrayMap;
    //   33: aload_1
    //   34: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   37: pop
    //   38: aload_3
    //   39: getfield binder : Landroid/os/IBinder;
    //   42: aload_3
    //   43: getfield deathMonitor : Landroid/os/IBinder$DeathRecipient;
    //   46: iconst_0
    //   47: invokeinterface unlinkToDeath : (Landroid/os/IBinder$DeathRecipient;I)Z
    //   52: pop
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_0
    //   56: getfield mConnection : Landroid/content/ServiceConnection;
    //   59: aload_1
    //   60: invokeinterface onServiceDisconnected : (Landroid/content/ComponentName;)V
    //   65: return
    //   66: aload_0
    //   67: monitorexit
    //   68: return
    //   69: astore_1
    //   70: aload_0
    //   71: monitorexit
    //   72: aload_1
    //   73: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	69	finally
    //   18	26	69	finally
    //   29	53	69	finally
    //   53	55	69	finally
    //   66	68	69	finally
    //   70	72	69	finally
  }
  
  void doForget() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore_1
    //   4: iload_1
    //   5: aload_0
    //   6: getfield mActiveConnections : Landroid/util/ArrayMap;
    //   9: invokevirtual size : ()I
    //   12: if_icmpge -> 48
    //   15: aload_0
    //   16: getfield mActiveConnections : Landroid/util/ArrayMap;
    //   19: iload_1
    //   20: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   23: checkcast android/app/LoadedApk$ServiceDispatcher$ConnectionInfo
    //   26: astore_2
    //   27: aload_2
    //   28: getfield binder : Landroid/os/IBinder;
    //   31: aload_2
    //   32: getfield deathMonitor : Landroid/os/IBinder$DeathRecipient;
    //   35: iconst_0
    //   36: invokeinterface unlinkToDeath : (Landroid/os/IBinder$DeathRecipient;I)Z
    //   41: pop
    //   42: iinc #1, 1
    //   45: goto -> 4
    //   48: aload_0
    //   49: getfield mActiveConnections : Landroid/util/ArrayMap;
    //   52: invokevirtual clear : ()V
    //   55: aload_0
    //   56: iconst_1
    //   57: putfield mForgotten : Z
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    //   63: astore_2
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_2
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   4	42	63	finally
    //   48	62	63	finally
    //   64	66	63	finally
  }
  
  int getFlags() {
    return this.mFlags;
  }
  
  IServiceConnection getIServiceConnection() {
    return this.mIServiceConnection;
  }
  
  ServiceConnectionLeaked getLocation() {
    return this.mLocation;
  }
  
  ServiceConnection getServiceConnection() {
    return this.mConnection;
  }
  
  RuntimeException getUnbindLocation() {
    return this.mUnbindLocation;
  }
  
  void setUnbindLocation(RuntimeException paramRuntimeException) {
    this.mUnbindLocation = paramRuntimeException;
  }
  
  void validate(Context paramContext, Handler paramHandler, Executor paramExecutor) {
    StringBuilder stringBuilder1;
    if (this.mContext == paramContext) {
      if (this.mActivityThread == paramHandler) {
        if (this.mActivityExecutor == paramExecutor)
          return; 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ServiceConnection ");
        stringBuilder.append(this.mConnection);
        stringBuilder.append(" registered with differing executor (was ");
        stringBuilder.append(this.mActivityExecutor);
        stringBuilder.append(" now ");
        stringBuilder.append(paramExecutor);
        stringBuilder.append(")");
        throw new RuntimeException(stringBuilder.toString());
      } 
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("ServiceConnection ");
      stringBuilder1.append(this.mConnection);
      stringBuilder1.append(" registered with differing handler (was ");
      stringBuilder1.append(this.mActivityThread);
      stringBuilder1.append(" now ");
      stringBuilder1.append(paramHandler);
      stringBuilder1.append(")");
      throw new RuntimeException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("ServiceConnection ");
    stringBuilder2.append(this.mConnection);
    stringBuilder2.append(" registered with differing Context (was ");
    stringBuilder2.append(this.mContext);
    stringBuilder2.append(" now ");
    stringBuilder2.append(stringBuilder1);
    stringBuilder2.append(")");
    throw new RuntimeException(stringBuilder2.toString());
  }
  
  private static class ConnectionInfo {
    IBinder binder;
    
    IBinder.DeathRecipient deathMonitor;
    
    private ConnectionInfo() {}
  }
  
  private final class DeathMonitor implements IBinder.DeathRecipient {
    final ComponentName mName;
    
    final IBinder mService;
    
    DeathMonitor(ComponentName param2ComponentName, IBinder param2IBinder) {
      this.mName = param2ComponentName;
      this.mService = param2IBinder;
    }
    
    public void binderDied() {
      LoadedApk.ServiceDispatcher.this.death(this.mName, this.mService);
    }
  }
  
  private static class InnerConnection extends IServiceConnection.Stub {
    final WeakReference<LoadedApk.ServiceDispatcher> mDispatcher;
    
    InnerConnection(LoadedApk.ServiceDispatcher param2ServiceDispatcher) {
      this.mDispatcher = new WeakReference<>(param2ServiceDispatcher);
    }
    
    public void connected(ComponentName param2ComponentName, IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
      LoadedApk.ServiceDispatcher serviceDispatcher = this.mDispatcher.get();
      if (serviceDispatcher != null)
        serviceDispatcher.connected(param2ComponentName, param2IBinder, param2Boolean); 
    }
  }
  
  private final class RunConnection implements Runnable {
    final int mCommand;
    
    final boolean mDead;
    
    final ComponentName mName;
    
    final IBinder mService;
    
    RunConnection(ComponentName param2ComponentName, IBinder param2IBinder, int param2Int, boolean param2Boolean) {
      this.mName = param2ComponentName;
      this.mService = param2IBinder;
      this.mCommand = param2Int;
      this.mDead = param2Boolean;
    }
    
    public void run() {
      int i = this.mCommand;
      if (i == 0) {
        LoadedApk.ServiceDispatcher.this.doConnected(this.mName, this.mService, this.mDead);
      } else if (i == 1) {
        LoadedApk.ServiceDispatcher.this.doDeath(this.mName, this.mService);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoadedApk$ServiceDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */