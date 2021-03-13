package android.hardware.camera2.legacy;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.MessageQueue;

public class RequestHandlerThread extends HandlerThread {
  public static final int MSG_POKE_IDLE_HANDLER = -1;
  
  private Handler.Callback mCallback;
  
  private volatile Handler mHandler;
  
  private final ConditionVariable mIdle = new ConditionVariable(true);
  
  private final MessageQueue.IdleHandler mIdleHandler = new MessageQueue.IdleHandler() {
      public boolean queueIdle() {
        RequestHandlerThread.this.mIdle.open();
        return false;
      }
    };
  
  private final ConditionVariable mStarted = new ConditionVariable(false);
  
  public RequestHandlerThread(String paramString, Handler.Callback paramCallback) {
    super(paramString, 10);
    this.mCallback = paramCallback;
  }
  
  public Handler getHandler() {
    return this.mHandler;
  }
  
  public boolean hasAnyMessages(int[] paramArrayOfint) {
    synchronized (this.mHandler.getLooper().getQueue()) {
      int i = paramArrayOfint.length;
      for (byte b = 0; b < i; b++) {
        int j = paramArrayOfint[b];
        if (this.mHandler.hasMessages(j))
          return true; 
      } 
      return false;
    } 
  }
  
  protected void onLooperPrepared() {
    this.mHandler = new Handler(getLooper(), this.mCallback);
    this.mStarted.open();
  }
  
  public void removeMessages(int[] paramArrayOfint) {
    synchronized (this.mHandler.getLooper().getQueue()) {
      int i = paramArrayOfint.length;
      for (byte b = 0; b < i; b++) {
        int j = paramArrayOfint[b];
        this.mHandler.removeMessages(j);
      } 
      return;
    } 
  }
  
  public Handler waitAndGetHandler() {
    waitUntilStarted();
    return getHandler();
  }
  
  public void waitUntilIdle() {
    Handler handler = waitAndGetHandler();
    MessageQueue messageQueue = handler.getLooper().getQueue();
    if (messageQueue.isIdle())
      return; 
    this.mIdle.close();
    messageQueue.addIdleHandler(this.mIdleHandler);
    handler.sendEmptyMessage(-1);
    if (messageQueue.isIdle())
      return; 
    this.mIdle.block();
  }
  
  public void waitUntilStarted() {
    this.mStarted.block();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestHandlerThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */