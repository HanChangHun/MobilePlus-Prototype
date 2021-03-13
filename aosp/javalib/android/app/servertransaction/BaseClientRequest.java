package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;

public interface BaseClientRequest extends ObjectPoolItem {
  void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions);
  
  default void postExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {}
  
  default void preExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/BaseClientRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */