package android.graphics.drawable;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

class LoadDrawableTask implements Runnable {
  final Context mContext;
  
  final Message mMessage;
  
  public LoadDrawableTask(Context paramContext, Handler paramHandler, final Icon.OnDrawableLoadedListener listener) {
    this.mContext = paramContext;
    this.mMessage = Message.obtain(paramHandler, new Runnable() {
          public void run() {
            listener.onDrawableLoaded((Drawable)Icon.LoadDrawableTask.this.mMessage.obj);
          }
        });
  }
  
  public LoadDrawableTask(Context paramContext, Message paramMessage) {
    this.mContext = paramContext;
    this.mMessage = paramMessage;
  }
  
  public void run() {
    this.mMessage.obj = Icon.this.loadDrawable(this.mContext);
    this.mMessage.sendToTarget();
  }
  
  public void runAsync() {
    AsyncTask.THREAD_POOL_EXECUTOR.execute(this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/Icon$LoadDrawableTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */