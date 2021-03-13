package android.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IIntentReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Trace;
import android.util.Log;
import java.lang.ref.WeakReference;

final class ReceiverDispatcher {
  final Handler mActivityThread;
  
  final Context mContext;
  
  boolean mForgotten;
  
  final IIntentReceiver.Stub mIIntentReceiver;
  
  final Instrumentation mInstrumentation;
  
  final IntentReceiverLeaked mLocation;
  
  final BroadcastReceiver mReceiver;
  
  final boolean mRegistered;
  
  RuntimeException mUnregisterLocation;
  
  ReceiverDispatcher(BroadcastReceiver paramBroadcastReceiver, Context paramContext, Handler paramHandler, Instrumentation paramInstrumentation, boolean paramBoolean) {
    if (paramHandler != null) {
      this.mIIntentReceiver = new InnerReceiver(this, paramBoolean ^ true);
      this.mReceiver = paramBroadcastReceiver;
      this.mContext = paramContext;
      this.mActivityThread = paramHandler;
      this.mInstrumentation = paramInstrumentation;
      this.mRegistered = paramBoolean;
      IntentReceiverLeaked intentReceiverLeaked = new IntentReceiverLeaked(null);
      this.mLocation = intentReceiverLeaked;
      intentReceiverLeaked.fillInStackTrace();
      return;
    } 
    throw new NullPointerException("Handler must not be null");
  }
  
  IIntentReceiver getIIntentReceiver() {
    return (IIntentReceiver)this.mIIntentReceiver;
  }
  
  BroadcastReceiver getIntentReceiver() {
    return this.mReceiver;
  }
  
  IntentReceiverLeaked getLocation() {
    return this.mLocation;
  }
  
  RuntimeException getUnregisterLocation() {
    return this.mUnregisterLocation;
  }
  
  public void performReceive(Intent paramIntent, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) {
    Args args = new Args(paramIntent, paramInt1, paramString, paramBundle, paramBoolean1, paramBoolean2, paramInt2);
    if (paramIntent == null)
      Log.wtf("LoadedApk", "Null intent received"); 
    if ((paramIntent == null || !this.mActivityThread.post(args.getRunnable())) && this.mRegistered && paramBoolean1)
      args.sendFinished(ActivityManager.getService()); 
  }
  
  void setUnregisterLocation(RuntimeException paramRuntimeException) {
    this.mUnregisterLocation = paramRuntimeException;
  }
  
  void validate(Context paramContext, Handler paramHandler) {
    StringBuilder stringBuilder1;
    if (this.mContext == paramContext) {
      if (this.mActivityThread == paramHandler)
        return; 
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Receiver ");
      stringBuilder1.append(this.mReceiver);
      stringBuilder1.append(" registered with differing handler (was ");
      stringBuilder1.append(this.mActivityThread);
      stringBuilder1.append(" now ");
      stringBuilder1.append(paramHandler);
      stringBuilder1.append(")");
      throw new IllegalStateException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Receiver ");
    stringBuilder2.append(this.mReceiver);
    stringBuilder2.append(" registered with differing Context (was ");
    stringBuilder2.append(this.mContext);
    stringBuilder2.append(" now ");
    stringBuilder2.append(stringBuilder1);
    stringBuilder2.append(")");
    throw new IllegalStateException(stringBuilder2.toString());
  }
  
  final class Args extends BroadcastReceiver.PendingResult {
    private Intent mCurIntent;
    
    private boolean mDispatched;
    
    private final boolean mOrdered;
    
    private boolean mRunCalled;
    
    public Args(Intent param2Intent, int param2Int1, String param2String, Bundle param2Bundle, boolean param2Boolean1, boolean param2Boolean2, int param2Int2) {
      super(param2Int1, param2String, param2Bundle, b, param2Boolean1, param2Boolean2, LoadedApk.ReceiverDispatcher.this.mIIntentReceiver.asBinder(), param2Int2, param2Intent.getFlags());
      byte b;
      this.mCurIntent = param2Intent;
      this.mOrdered = param2Boolean1;
    }
    
    public final Runnable getRunnable() {
      return new _$$Lambda$LoadedApk$ReceiverDispatcher$Args$_BumDX2UKsnxLVrE6UJsJZkotuA(this);
    }
  }
  
  static final class InnerReceiver extends IIntentReceiver.Stub {
    final WeakReference<LoadedApk.ReceiverDispatcher> mDispatcher;
    
    final LoadedApk.ReceiverDispatcher mStrongRef;
    
    InnerReceiver(LoadedApk.ReceiverDispatcher param2ReceiverDispatcher, boolean param2Boolean) {
      this.mDispatcher = new WeakReference<>(param2ReceiverDispatcher);
      if (!param2Boolean)
        param2ReceiverDispatcher = null; 
      this.mStrongRef = param2ReceiverDispatcher;
    }
    
    public void performReceive(Intent param2Intent, int param2Int1, String param2String, Bundle param2Bundle, boolean param2Boolean1, boolean param2Boolean2, int param2Int2) {
      LoadedApk.ReceiverDispatcher receiverDispatcher;
      if (param2Intent == null) {
        Log.wtf("LoadedApk", "Null intent received");
        receiverDispatcher = null;
      } else {
        receiverDispatcher = this.mDispatcher.get();
      } 
      if (receiverDispatcher != null) {
        receiverDispatcher.performReceive(param2Intent, param2Int1, param2String, param2Bundle, param2Boolean1, param2Boolean2, param2Int2);
      } else {
        IActivityManager iActivityManager = ActivityManager.getService();
        if (param2Bundle != null)
          try {
            param2Bundle.setAllowFds(false);
            iActivityManager.finishReceiver((IBinder)this, param2Int1, param2String, param2Bundle, false, param2Intent.getFlags());
          } catch (RemoteException remoteException) {
            throw remoteException.rethrowFromSystemServer();
          }  
        iActivityManager.finishReceiver((IBinder)this, param2Int1, param2String, param2Bundle, false, remoteException.getFlags());
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoadedApk$ReceiverDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */