package android.app;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;

final class Args extends BroadcastReceiver.PendingResult {
  private Intent mCurIntent;
  
  private boolean mDispatched;
  
  private final boolean mOrdered;
  
  private boolean mRunCalled;
  
  public Args(Intent paramIntent, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) {
    super(paramInt1, paramString, paramBundle, b, paramBoolean1, paramBoolean2, paramReceiverDispatcher.mIIntentReceiver.asBinder(), paramInt2, paramIntent.getFlags());
    byte b;
    this.mCurIntent = paramIntent;
    this.mOrdered = paramBoolean1;
  }
  
  public final Runnable getRunnable() {
    return new _$$Lambda$LoadedApk$ReceiverDispatcher$Args$_BumDX2UKsnxLVrE6UJsJZkotuA(this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoadedApk$ReceiverDispatcher$Args.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */