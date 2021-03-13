package android.app;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

@Deprecated
public abstract class IntentService extends Service {
  private String mName;
  
  private boolean mRedelivery;
  
  private volatile ServiceHandler mServiceHandler;
  
  private volatile Looper mServiceLooper;
  
  public IntentService(String paramString) {
    this.mName = paramString;
  }
  
  public IBinder onBind(Intent paramIntent) {
    return null;
  }
  
  public void onCreate() {
    super.onCreate();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("IntentService[");
    stringBuilder.append(this.mName);
    stringBuilder.append("]");
    HandlerThread handlerThread = new HandlerThread(stringBuilder.toString());
    handlerThread.start();
    this.mServiceLooper = handlerThread.getLooper();
    this.mServiceHandler = new ServiceHandler(this.mServiceLooper);
  }
  
  public void onDestroy() {
    this.mServiceLooper.quit();
  }
  
  protected abstract void onHandleIntent(Intent paramIntent);
  
  public void onStart(Intent paramIntent, int paramInt) {
    Message message = this.mServiceHandler.obtainMessage();
    message.arg1 = paramInt;
    message.obj = paramIntent;
    this.mServiceHandler.sendMessage(message);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    onStart(paramIntent, paramInt2);
    if (this.mRedelivery) {
      paramInt1 = 3;
    } else {
      paramInt1 = 2;
    } 
    return paramInt1;
  }
  
  public void setIntentRedelivery(boolean paramBoolean) {
    this.mRedelivery = paramBoolean;
  }
  
  private final class ServiceHandler extends Handler {
    public ServiceHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      IntentService.this.onHandleIntent((Intent)param1Message.obj);
      IntentService.this.stopSelf(param1Message.arg1);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IntentService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */