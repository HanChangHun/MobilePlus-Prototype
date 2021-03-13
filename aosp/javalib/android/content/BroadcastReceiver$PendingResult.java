package android.content;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.QueuedWork;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class PendingResult {
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
  
  public PendingResult(int paramInt1, String paramString, Bundle paramBundle, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, IBinder paramIBinder, int paramInt3, int paramInt4) {
    this.mResultCode = paramInt1;
    this.mResultData = paramString;
    this.mResultExtras = paramBundle;
    this.mType = paramInt2;
    this.mOrderedHint = paramBoolean1;
    this.mInitialStickyHint = paramBoolean2;
    this.mToken = paramIBinder;
    this.mSendingUser = paramInt3;
    this.mFlags = paramInt4;
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
  
  public final Bundle getResultExtras(boolean paramBoolean) {
    Bundle bundle1 = this.mResultExtras;
    if (!paramBoolean)
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
  
  public void sendFinished(IActivityManager paramIActivityManager) {
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
  
  public void setExtrasClassLoader(ClassLoader paramClassLoader) {
    Bundle bundle = this.mResultExtras;
    if (bundle != null)
      bundle.setClassLoader(paramClassLoader); 
  }
  
  public final void setResult(int paramInt, String paramString, Bundle paramBundle) {
    checkSynchronousHint();
    this.mResultCode = paramInt;
    this.mResultData = paramString;
    this.mResultExtras = paramBundle;
  }
  
  public final void setResultCode(int paramInt) {
    checkSynchronousHint();
    this.mResultCode = paramInt;
  }
  
  public final void setResultData(String paramString) {
    checkSynchronousHint();
    this.mResultData = paramString;
  }
  
  public final void setResultExtras(Bundle paramBundle) {
    checkSynchronousHint();
    this.mResultExtras = paramBundle;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/BroadcastReceiver$PendingResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */