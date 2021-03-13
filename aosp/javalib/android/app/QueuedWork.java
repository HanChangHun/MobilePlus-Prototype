package android.app;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import com.android.internal.util.ExponentiallyBucketedHistogram;
import java.util.LinkedList;

public class QueuedWork {
  private static final boolean DEBUG = false;
  
  private static final long DELAY = 100L;
  
  private static final String LOG_TAG = QueuedWork.class.getSimpleName();
  
  private static final long MAX_WAIT_TIME_MILLIS = 512L;
  
  private static int mNumWaits;
  
  private static final ExponentiallyBucketedHistogram mWaitTimes;
  
  private static boolean sCanDelay;
  
  private static final LinkedList<Runnable> sFinishers;
  
  private static Handler sHandler;
  
  private static final Object sLock = new Object();
  
  private static Object sProcessingWork = new Object();
  
  private static final LinkedList<Runnable> sWork;
  
  static {
    sFinishers = new LinkedList<>();
    sHandler = null;
    sWork = new LinkedList<>();
    sCanDelay = true;
    mWaitTimes = new ExponentiallyBucketedHistogram(16);
    mNumWaits = 0;
  }
  
  public static void addFinisher(Runnable paramRunnable) {
    synchronized (sLock) {
      sFinishers.add(paramRunnable);
      return;
    } 
  }
  
  private static Handler getHandler() {
    synchronized (sLock) {
      if (sHandler == null) {
        HandlerThread handlerThread = new HandlerThread();
        this("queued-work-looper", -2);
        handlerThread.start();
        QueuedWorkHandler queuedWorkHandler = new QueuedWorkHandler();
        this(handlerThread.getLooper());
        sHandler = queuedWorkHandler;
      } 
      return sHandler;
    } 
  }
  
  public static boolean hasPendingWork() {
    synchronized (sLock) {
      boolean bool;
      if (!sWork.isEmpty()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  private static void processPendingWork() {
    synchronized (sProcessingWork) {
      synchronized (sLock) {
        LinkedList linkedList = (LinkedList)sWork.clone();
        sWork.clear();
        getHandler().removeMessages(1);
        if (linkedList.size() > 0) {
          null = linkedList.iterator();
          while (null.hasNext())
            ((Runnable)null.next()).run(); 
        } 
        return;
      } 
    } 
  }
  
  public static void queue(Runnable paramRunnable, boolean paramBoolean) {
    Handler handler = getHandler();
    synchronized (sLock) {
      sWork.add(paramRunnable);
      if (paramBoolean && sCanDelay) {
        handler.sendEmptyMessageDelayed(1, 100L);
      } else {
        handler.sendEmptyMessage(1);
      } 
      return;
    } 
  }
  
  public static void removeFinisher(Runnable paramRunnable) {
    synchronized (sLock) {
      sFinishers.remove(paramRunnable);
      return;
    } 
  }
  
  public static void waitToFinish() {
    long l = System.currentTimeMillis();
    null = getHandler();
    synchronized (sLock) {
      if (null.hasMessages(1))
        null.removeMessages(1); 
      sCanDelay = false;
      StrictMode.ThreadPolicy threadPolicy = StrictMode.allowThreadDiskWrites();
      try {
        processPendingWork();
        StrictMode.setThreadPolicy(threadPolicy);
      } finally {
        StrictMode.setThreadPolicy(threadPolicy);
      } 
    } 
  }
  
  private static class QueuedWorkHandler extends Handler {
    static final int MSG_RUN = 1;
    
    QueuedWorkHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      if (param1Message.what == 1)
        QueuedWork.processPendingWork(); 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/QueuedWork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */