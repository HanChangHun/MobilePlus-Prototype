package android.content;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.Iterator;
import java.util.function.BiConsumer;

class ISyncAdapterImpl extends ISyncAdapter.Stub {
  private ISyncAdapterImpl() {}
  
  public void cancelSync(ISyncContext paramISyncContext) {
    AbstractThreadedSyncAdapter.SyncThread syncThread = null;
    try {
      synchronized (AbstractThreadedSyncAdapter.access$300(AbstractThreadedSyncAdapter.this)) {
        AbstractThreadedSyncAdapter.SyncThread syncThread1;
        Iterator<AbstractThreadedSyncAdapter.SyncThread> iterator = AbstractThreadedSyncAdapter.access$400(AbstractThreadedSyncAdapter.this).values().iterator();
        while (true) {
          syncThread1 = syncThread;
          if (iterator.hasNext()) {
            syncThread1 = iterator.next();
            if (AbstractThreadedSyncAdapter.SyncThread.access$800(syncThread1).getSyncContextBinder() == paramISyncContext.asBinder())
              break; 
            continue;
          } 
          break;
        } 
        if (syncThread1 != null) {
          if (AbstractThreadedSyncAdapter.access$100()) {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("cancelSync() ");
            stringBuilder.append(AbstractThreadedSyncAdapter.SyncThread.access$900(syncThread1));
            stringBuilder.append(" ");
            stringBuilder.append(AbstractThreadedSyncAdapter.SyncThread.access$1000(syncThread1));
            Log.d("SyncAdapter", stringBuilder.toString());
          } 
          if (AbstractThreadedSyncAdapter.access$1100(AbstractThreadedSyncAdapter.this)) {
            AbstractThreadedSyncAdapter.this.onSyncCanceled(syncThread1);
          } else {
            AbstractThreadedSyncAdapter.this.onSyncCanceled();
          } 
        } else if (AbstractThreadedSyncAdapter.access$100()) {
          Log.w("SyncAdapter", "cancelSync() unknown context");
        } 
        if (AbstractThreadedSyncAdapter.access$100())
          Log.d("SyncAdapter", "cancelSync() finishing"); 
        return;
      } 
    } catch (RuntimeException|Error runtimeException) {
      if (AbstractThreadedSyncAdapter.access$100())
        Log.d("SyncAdapter", "cancelSync() caught exception", runtimeException); 
      throw runtimeException;
    } finally {}
    if (AbstractThreadedSyncAdapter.access$100())
      Log.d("SyncAdapter", "cancelSync() finishing"); 
    throw paramISyncContext;
  }
  
  public void onUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback paramISyncAdapterUnsyncableAccountCallback) {
    Handler.getMain().sendMessage(PooledLambda.obtainMessage((BiConsumer)_$$Lambda$AbstractThreadedSyncAdapter$ISyncAdapterImpl$L6ZtOCe8gjKwJj0908ytPlrD8Rc.INSTANCE, AbstractThreadedSyncAdapter.this, paramISyncAdapterUnsyncableAccountCallback));
  }
  
  public void startSync(ISyncContext paramISyncContext, String paramString, Account paramAccount, Bundle paramBundle) {
    if (AbstractThreadedSyncAdapter.access$100()) {
      if (paramBundle != null)
        paramBundle.size(); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("startSync() start ");
      stringBuilder.append(paramString);
      stringBuilder.append(" ");
      stringBuilder.append(paramAccount);
      stringBuilder.append(" ");
      stringBuilder.append(paramBundle);
      Log.d("SyncAdapter", stringBuilder.toString());
    } 
    try {
      SyncContext syncContext = new SyncContext();
      try {
        this(paramISyncContext);
        Account account = AbstractThreadedSyncAdapter.access$200(AbstractThreadedSyncAdapter.this, paramAccount);
        synchronized (AbstractThreadedSyncAdapter.access$300(AbstractThreadedSyncAdapter.this)) {
          boolean bool;
          if (!AbstractThreadedSyncAdapter.access$400(AbstractThreadedSyncAdapter.this).containsKey(account)) {
            SyncResult syncResult;
            if (AbstractThreadedSyncAdapter.access$500(AbstractThreadedSyncAdapter.this) && paramBundle != null) {
              boolean bool1 = paramBundle.getBoolean("initialize", false);
              if (bool1)
                try {
                  if (ContentResolver.getIsSyncable(paramAccount, paramString) < 0)
                    ContentResolver.setIsSyncable(paramAccount, paramString, 1); 
                  SyncResult syncResult1 = new SyncResult();
                  return;
                } finally {
                  syncResult = new SyncResult();
                  this();
                  syncContext.onFinished(syncResult);
                }  
            } 
            AbstractThreadedSyncAdapter.SyncThread syncThread = new AbstractThreadedSyncAdapter.SyncThread();
            AbstractThreadedSyncAdapter abstractThreadedSyncAdapter = AbstractThreadedSyncAdapter.this;
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("SyncAdapterThread-");
            stringBuilder.append(AbstractThreadedSyncAdapter.access$600(AbstractThreadedSyncAdapter.this).incrementAndGet());
            this(stringBuilder.toString(), syncContext, paramString, (Account)syncResult, paramBundle);
            AbstractThreadedSyncAdapter.access$400(AbstractThreadedSyncAdapter.this).put(account, syncThread);
            syncThread.start();
            bool = false;
          } else {
            if (AbstractThreadedSyncAdapter.access$100())
              Log.d("SyncAdapter", "  alreadyInProgress"); 
            bool = true;
          } 
          if (bool)
            syncContext.onFinished(SyncResult.ALREADY_IN_PROGRESS); 
          if (AbstractThreadedSyncAdapter.access$100())
            Log.d("SyncAdapter", "startSync() finishing"); 
          return;
        } 
      } catch (RuntimeException|Error runtimeException) {
      
      } finally {}
    } catch (RuntimeException|Error runtimeException) {
    
    } finally {}
    if (AbstractThreadedSyncAdapter.access$100())
      Log.d("SyncAdapter", "startSync() caught exception", (Throwable)paramISyncContext); 
    throw paramISyncContext;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/AbstractThreadedSyncAdapter$ISyncAdapterImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */