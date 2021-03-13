package android.content;

import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class WorkerHandler extends Handler {
  public WorkerHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    ContentResolver contentResolver = AsyncQueryHandler.this.mResolver.get();
    if (contentResolver == null)
      return; 
    AsyncQueryHandler.WorkerArgs workerArgs = (AsyncQueryHandler.WorkerArgs)paramMessage.obj;
    int i = paramMessage.what;
    int j = paramMessage.arg1;
    if (j != 1) {
      if (j != 2) {
        if (j != 3) {
          if (j == 4)
            workerArgs.result = Integer.valueOf(contentResolver.delete(workerArgs.uri, workerArgs.selection, workerArgs.selectionArgs)); 
        } else {
          workerArgs.result = Integer.valueOf(contentResolver.update(workerArgs.uri, workerArgs.values, workerArgs.selection, workerArgs.selectionArgs));
        } 
      } else {
        workerArgs.result = contentResolver.insert(workerArgs.uri, workerArgs.values);
      } 
    } else {
      try {
        Cursor cursor = contentResolver.query(workerArgs.uri, workerArgs.projection, workerArgs.selection, workerArgs.selectionArgs, workerArgs.orderBy);
        if (cursor != null)
          cursor.getCount(); 
      } catch (Exception exception) {
        Log.w("AsyncQuery", "Exception thrown during handling EVENT_ARG_QUERY", exception);
        exception = null;
      } 
      workerArgs.result = exception;
    } 
    Message message = workerArgs.handler.obtainMessage(i);
    message.obj = workerArgs;
    message.arg1 = paramMessage.arg1;
    message.sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/AsyncQueryHandler$WorkerHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */