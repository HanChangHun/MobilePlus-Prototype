package android.content;

import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.QueuedWork;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;

public abstract class BroadcastReceiver {
  private boolean mDebugUnregister;
  
  private PendingResult mPendingResult;
  
  public final void abortBroadcast() {
    checkSynchronousHint();
    this.mPendingResult.mAbortBroadcast = true;
  }
  
  void checkSynchronousHint() {
    PendingResult pendingResult = this.mPendingResult;
    if (pendingResult != null) {
      if (pendingResult.mOrderedHint || this.mPendingResult.mInitialStickyHint)
        return; 
      RuntimeException runtimeException = new RuntimeException("BroadcastReceiver trying to return result during a non-ordered broadcast");
      runtimeException.fillInStackTrace();
      Log.e("BroadcastReceiver", runtimeException.getMessage(), runtimeException);
      return;
    } 
    throw new IllegalStateException("Call while result is not pending");
  }
  
  public final void clearAbortBroadcast() {
    PendingResult pendingResult = this.mPendingResult;
    if (pendingResult != null)
      pendingResult.mAbortBroadcast = false; 
  }
  
  public final boolean getAbortBroadcast() {
    boolean bool;
    PendingResult pendingResult = this.mPendingResult;
    if (pendingResult != null) {
      bool = pendingResult.mAbortBroadcast;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean getDebugUnregister() {
    return this.mDebugUnregister;
  }
  
  public final PendingResult getPendingResult() {
    return this.mPendingResult;
  }
  
  public final int getResultCode() {
    boolean bool;
    PendingResult pendingResult = this.mPendingResult;
    if (pendingResult != null) {
      bool = pendingResult.mResultCode;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final String getResultData() {
    PendingResult pendingResult = this.mPendingResult;
    if (pendingResult != null) {
      String str = pendingResult.mResultData;
    } else {
      pendingResult = null;
    } 
    return (String)pendingResult;
  }
  
  public final Bundle getResultExtras(boolean paramBoolean) {
    PendingResult pendingResult = this.mPendingResult;
    if (pendingResult == null)
      return null; 
    Bundle bundle2 = pendingResult.mResultExtras;
    if (!paramBoolean)
      return bundle2; 
    Bundle bundle1 = bundle2;
    if (bundle2 == null) {
      PendingResult pendingResult1 = this.mPendingResult;
      bundle2 = new Bundle();
      bundle1 = bundle2;
      pendingResult1.mResultExtras = bundle2;
    } 
    return bundle1;
  }
  
  @SystemApi
  public final UserHandle getSendingUser() {
    return UserHandle.of(getSendingUserId());
  }
  
  public int getSendingUserId() {
    return this.mPendingResult.mSendingUser;
  }
  
  public final PendingResult goAsync() {
    PendingResult pendingResult = this.mPendingResult;
    this.mPendingResult = null;
    return pendingResult;
  }
  
  public final boolean isInitialStickyBroadcast() {
    boolean bool;
    PendingResult pendingResult = this.mPendingResult;
    if (pendingResult != null) {
      bool = pendingResult.mInitialStickyHint;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isOrderedBroadcast() {
    boolean bool;
    PendingResult pendingResult = this.mPendingResult;
    if (pendingResult != null) {
      bool = pendingResult.mOrderedHint;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public abstract void onReceive(Context paramContext, Intent paramIntent);
  
  public IBinder peekService(Context paramContext, Intent paramIntent) {
    IActivityManager iActivityManager = ActivityManager.getService();
    RemoteException remoteException2 = null;
    try {
      paramIntent.prepareToLeaveProcess(paramContext);
      IBinder iBinder = iActivityManager.peekService(paramIntent, paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver()), paramContext.getOpPackageName());
    } catch (RemoteException remoteException1) {
      remoteException1 = remoteException2;
    } 
    return (IBinder)remoteException1;
  }
  
  public final void setDebugUnregister(boolean paramBoolean) {
    this.mDebugUnregister = paramBoolean;
  }
  
  public final void setOrderedHint(boolean paramBoolean) {}
  
  public final void setPendingResult(PendingResult paramPendingResult) {
    this.mPendingResult = paramPendingResult;
  }
  
  public final void setResult(int paramInt, String paramString, Bundle paramBundle) {
    checkSynchronousHint();
    this.mPendingResult.mResultCode = paramInt;
    this.mPendingResult.mResultData = paramString;
    this.mPendingResult.mResultExtras = paramBundle;
  }
  
  public final void setResultCode(int paramInt) {
    checkSynchronousHint();
    this.mPendingResult.mResultCode = paramInt;
  }
  
  public final void setResultData(String paramString) {
    checkSynchronousHint();
    this.mPendingResult.mResultData = paramString;
  }
  
  public final void setResultExtras(Bundle paramBundle) {
    checkSynchronousHint();
    this.mPendingResult.mResultExtras = paramBundle;
  }
  
  public static class PendingResult {
    public static final int TYPE_COMPONENT = 0;
    
    public static final int TYPE_REGISTERED = 1;
    
    public static final int TYPE_UNREGISTERED = 2;
    
    boolean mAbortBroadcast;
    
    boolean mFinished;
    
    final int mFlags;
    
    final boolean mInitialStickyHint;
    
    final boolean mOrderedHint;
    
    int mResultCode;
    
    String mResultData;
    
    Bundle mResultExtras;
    
    final int mSendingUser;
    
    final IBinder mToken;
    
    final int mType;
    
    public PendingResult(int param1Int1, String param1String, Bundle param1Bundle, int param1Int2, boolean param1Boolean1, boolean param1Boolean2, IBinder param1IBinder, int param1Int3, int param1Int4) {
      this.mResultCode = param1Int1;
      this.mResultData = param1String;
      this.mResultExtras = param1Bundle;
      this.mType = param1Int2;
      this.mOrderedHint = param1Boolean1;
      this.mInitialStickyHint = param1Boolean2;
      this.mToken = param1IBinder;
      this.mSendingUser = param1Int3;
      this.mFlags = param1Int4;
    }
    
    public final void abortBroadcast() {
      checkSynchronousHint();
      this.mAbortBroadcast = true;
    }
    
    void checkSynchronousHint() {
      if (this.mOrderedHint || this.mInitialStickyHint)
        return; 
      RuntimeException runtimeException = new RuntimeException("BroadcastReceiver trying to return result during a non-ordered broadcast");
      runtimeException.fillInStackTrace();
      Log.e("BroadcastReceiver", runtimeException.getMessage(), runtimeException);
    }
    
    public final void clearAbortBroadcast() {
      this.mAbortBroadcast = false;
    }
    
    public final void finish() {
      int i = this.mType;
      if (i == 0) {
        final IActivityManager mgr = ActivityManager.getService();
        if (QueuedWork.hasPendingWork()) {
          QueuedWork.queue(new Runnable() {
                public void run() {
                  BroadcastReceiver.PendingResult.this.sendFinished(mgr);
                }
              },  false);
        } else {
          sendFinished(iActivityManager);
        } 
      } else if (this.mOrderedHint && i != 2) {
        sendFinished(ActivityManager.getService());
      } 
    }
    
    public final boolean getAbortBroadcast() {
      return this.mAbortBroadcast;
    }
    
    public final int getResultCode() {
      return this.mResultCode;
    }
    
    public final String getResultData() {
      return this.mResultData;
    }
    
    public final Bundle getResultExtras(boolean param1Boolean) {
      Bundle bundle1 = this.mResultExtras;
      if (!param1Boolean)
        return bundle1; 
      Bundle bundle2 = bundle1;
      if (bundle1 == null) {
        bundle1 = new Bundle();
        bundle2 = bundle1;
        this.mResultExtras = bundle1;
      } 
      return bundle2;
    }
    
    public int getSendingUserId() {
      return this.mSendingUser;
    }
    
    public void sendFinished(IActivityManager param1IActivityManager) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mFinished : Z
      //   6: ifne -> 95
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield mFinished : Z
      //   14: aload_0
      //   15: getfield mResultExtras : Landroid/os/Bundle;
      //   18: ifnull -> 30
      //   21: aload_0
      //   22: getfield mResultExtras : Landroid/os/Bundle;
      //   25: iconst_0
      //   26: invokevirtual setAllowFds : (Z)Z
      //   29: pop
      //   30: aload_0
      //   31: getfield mOrderedHint : Z
      //   34: ifeq -> 70
      //   37: aload_1
      //   38: aload_0
      //   39: getfield mToken : Landroid/os/IBinder;
      //   42: aload_0
      //   43: getfield mResultCode : I
      //   46: aload_0
      //   47: getfield mResultData : Ljava/lang/String;
      //   50: aload_0
      //   51: getfield mResultExtras : Landroid/os/Bundle;
      //   54: aload_0
      //   55: getfield mAbortBroadcast : Z
      //   58: aload_0
      //   59: getfield mFlags : I
      //   62: invokeinterface finishReceiver : (Landroid/os/IBinder;ILjava/lang/String;Landroid/os/Bundle;ZI)V
      //   67: goto -> 88
      //   70: aload_1
      //   71: aload_0
      //   72: getfield mToken : Landroid/os/IBinder;
      //   75: iconst_0
      //   76: aconst_null
      //   77: aconst_null
      //   78: iconst_0
      //   79: aload_0
      //   80: getfield mFlags : I
      //   83: invokeinterface finishReceiver : (Landroid/os/IBinder;ILjava/lang/String;Landroid/os/Bundle;ZI)V
      //   88: goto -> 92
      //   91: astore_1
      //   92: aload_0
      //   93: monitorexit
      //   94: return
      //   95: new java/lang/IllegalStateException
      //   98: astore_1
      //   99: aload_1
      //   100: ldc 'Broadcast already finished'
      //   102: invokespecial <init> : (Ljava/lang/String;)V
      //   105: aload_1
      //   106: athrow
      //   107: astore_1
      //   108: aload_0
      //   109: monitorexit
      //   110: aload_1
      //   111: athrow
      // Exception table:
      //   from	to	target	type
      //   2	14	107	finally
      //   14	30	91	android/os/RemoteException
      //   14	30	107	finally
      //   30	67	91	android/os/RemoteException
      //   30	67	107	finally
      //   70	88	91	android/os/RemoteException
      //   70	88	107	finally
      //   92	94	107	finally
      //   95	107	107	finally
      //   108	110	107	finally
    }
    
    public void setExtrasClassLoader(ClassLoader param1ClassLoader) {
      Bundle bundle = this.mResultExtras;
      if (bundle != null)
        bundle.setClassLoader(param1ClassLoader); 
    }
    
    public final void setResult(int param1Int, String param1String, Bundle param1Bundle) {
      checkSynchronousHint();
      this.mResultCode = param1Int;
      this.mResultData = param1String;
      this.mResultExtras = param1Bundle;
    }
    
    public final void setResultCode(int param1Int) {
      checkSynchronousHint();
      this.mResultCode = param1Int;
    }
    
    public final void setResultData(String param1String) {
      checkSynchronousHint();
      this.mResultData = param1String;
    }
    
    public final void setResultExtras(Bundle param1Bundle) {
      checkSynchronousHint();
      this.mResultExtras = param1Bundle;
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$0.sendFinished(mgr);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/BroadcastReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */