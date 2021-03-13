package android.content;

import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.lang.ref.WeakReference;

public abstract class AsyncQueryHandler extends Handler {
  private static final int EVENT_ARG_DELETE = 4;
  
  private static final int EVENT_ARG_INSERT = 2;
  
  private static final int EVENT_ARG_QUERY = 1;
  
  private static final int EVENT_ARG_UPDATE = 3;
  
  private static final String TAG = "AsyncQuery";
  
  private static final boolean localLOGV = false;
  
  private static Looper sLooper = null;
  
  final WeakReference<ContentResolver> mResolver;
  
  private Handler mWorkerThreadHandler;
  
  public AsyncQueryHandler(ContentResolver paramContentResolver) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_0
    //   5: new java/lang/ref/WeakReference
    //   8: dup
    //   9: aload_1
    //   10: invokespecial <init> : (Ljava/lang/Object;)V
    //   13: putfield mResolver : Ljava/lang/ref/WeakReference;
    //   16: ldc android/content/AsyncQueryHandler
    //   18: monitorenter
    //   19: getstatic android/content/AsyncQueryHandler.sLooper : Landroid/os/Looper;
    //   22: ifnonnull -> 46
    //   25: new android/os/HandlerThread
    //   28: astore_1
    //   29: aload_1
    //   30: ldc 'AsyncQueryWorker'
    //   32: invokespecial <init> : (Ljava/lang/String;)V
    //   35: aload_1
    //   36: invokevirtual start : ()V
    //   39: aload_1
    //   40: invokevirtual getLooper : ()Landroid/os/Looper;
    //   43: putstatic android/content/AsyncQueryHandler.sLooper : Landroid/os/Looper;
    //   46: ldc android/content/AsyncQueryHandler
    //   48: monitorexit
    //   49: aload_0
    //   50: aload_0
    //   51: getstatic android/content/AsyncQueryHandler.sLooper : Landroid/os/Looper;
    //   54: invokevirtual createHandler : (Landroid/os/Looper;)Landroid/os/Handler;
    //   57: putfield mWorkerThreadHandler : Landroid/os/Handler;
    //   60: return
    //   61: astore_1
    //   62: ldc android/content/AsyncQueryHandler
    //   64: monitorexit
    //   65: aload_1
    //   66: athrow
    // Exception table:
    //   from	to	target	type
    //   19	46	61	finally
    //   46	49	61	finally
    //   62	65	61	finally
  }
  
  public final void cancelOperation(int paramInt) {
    this.mWorkerThreadHandler.removeMessages(paramInt);
  }
  
  protected Handler createHandler(Looper paramLooper) {
    return new WorkerHandler(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    WorkerArgs workerArgs = (WorkerArgs)paramMessage.obj;
    int i = paramMessage.what;
    int j = paramMessage.arg1;
    if (j != 1) {
      if (j != 2) {
        if (j != 3) {
          if (j == 4)
            onDeleteComplete(i, workerArgs.cookie, ((Integer)workerArgs.result).intValue()); 
        } else {
          onUpdateComplete(i, workerArgs.cookie, ((Integer)workerArgs.result).intValue());
        } 
      } else {
        onInsertComplete(i, workerArgs.cookie, (Uri)workerArgs.result);
      } 
    } else {
      onQueryComplete(i, workerArgs.cookie, (Cursor)workerArgs.result);
    } 
  }
  
  protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2) {}
  
  protected void onInsertComplete(int paramInt, Object paramObject, Uri paramUri) {}
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor) {}
  
  protected void onUpdateComplete(int paramInt1, Object paramObject, int paramInt2) {}
  
  public final void startDelete(int paramInt, Object paramObject, Uri paramUri, String paramString, String[] paramArrayOfString) {
    Message message = this.mWorkerThreadHandler.obtainMessage(paramInt);
    message.arg1 = 4;
    WorkerArgs workerArgs = new WorkerArgs();
    workerArgs.handler = this;
    workerArgs.uri = paramUri;
    workerArgs.cookie = paramObject;
    workerArgs.selection = paramString;
    workerArgs.selectionArgs = paramArrayOfString;
    message.obj = workerArgs;
    this.mWorkerThreadHandler.sendMessage(message);
  }
  
  public final void startInsert(int paramInt, Object paramObject, Uri paramUri, ContentValues paramContentValues) {
    Message message = this.mWorkerThreadHandler.obtainMessage(paramInt);
    message.arg1 = 2;
    WorkerArgs workerArgs = new WorkerArgs();
    workerArgs.handler = this;
    workerArgs.uri = paramUri;
    workerArgs.cookie = paramObject;
    workerArgs.values = paramContentValues;
    message.obj = workerArgs;
    this.mWorkerThreadHandler.sendMessage(message);
  }
  
  public void startQuery(int paramInt, Object paramObject, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2) {
    Message message = this.mWorkerThreadHandler.obtainMessage(paramInt);
    message.arg1 = 1;
    WorkerArgs workerArgs = new WorkerArgs();
    workerArgs.handler = this;
    workerArgs.uri = paramUri;
    workerArgs.projection = paramArrayOfString1;
    workerArgs.selection = paramString1;
    workerArgs.selectionArgs = paramArrayOfString2;
    workerArgs.orderBy = paramString2;
    workerArgs.cookie = paramObject;
    message.obj = workerArgs;
    this.mWorkerThreadHandler.sendMessage(message);
  }
  
  public final void startUpdate(int paramInt, Object paramObject, Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString) {
    Message message = this.mWorkerThreadHandler.obtainMessage(paramInt);
    message.arg1 = 3;
    WorkerArgs workerArgs = new WorkerArgs();
    workerArgs.handler = this;
    workerArgs.uri = paramUri;
    workerArgs.cookie = paramObject;
    workerArgs.values = paramContentValues;
    workerArgs.selection = paramString;
    workerArgs.selectionArgs = paramArrayOfString;
    message.obj = workerArgs;
    this.mWorkerThreadHandler.sendMessage(message);
  }
  
  protected static final class WorkerArgs {
    public Object cookie;
    
    public Handler handler;
    
    public String orderBy;
    
    public String[] projection;
    
    public Object result;
    
    public String selection;
    
    public String[] selectionArgs;
    
    public Uri uri;
    
    public ContentValues values;
  }
  
  protected class WorkerHandler extends Handler {
    public WorkerHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      ContentResolver contentResolver = AsyncQueryHandler.this.mResolver.get();
      if (contentResolver == null)
        return; 
      AsyncQueryHandler.WorkerArgs workerArgs = (AsyncQueryHandler.WorkerArgs)param1Message.obj;
      int i = param1Message.what;
      int j = param1Message.arg1;
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
      message.arg1 = param1Message.arg1;
      message.sendToTarget();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/AsyncQueryHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */