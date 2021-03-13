package android.app;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

public final class AutoCorker {
  public static final int DEFAULT_AUTO_CORK_DELAY_MS = 2000;
  
  private final int mAutoCorkDelayMs;
  
  private Handler mHandler;
  
  private final Object mLock = new Object();
  
  private final String mPropertyName;
  
  private long mUncorkDeadlineMs = -1L;
  
  public AutoCorker(String paramString) {
    this(paramString, 2000);
  }
  
  public AutoCorker(String paramString, int paramInt) {
    this.mPropertyName = paramString;
    this.mAutoCorkDelayMs = paramInt;
  }
  
  private Handler getHandlerLocked() {
    if (this.mHandler == null)
      this.mHandler = new Handler(Looper.getMainLooper()) {
          public void handleMessage(Message param2Message) {
            PropertyInvalidatedCache.AutoCorker.this.handleMessage(param2Message);
          }
        }; 
    return this.mHandler;
  }
  
  private void handleMessage(Message paramMessage) {
    synchronized (this.mLock) {
      if (this.mUncorkDeadlineMs < 0L)
        return; 
      long l = SystemClock.uptimeMillis();
      if (this.mUncorkDeadlineMs > l) {
        this.mUncorkDeadlineMs = this.mAutoCorkDelayMs + l;
        getHandlerLocked().sendEmptyMessageAtTime(0, this.mUncorkDeadlineMs);
        return;
      } 
      this.mUncorkDeadlineMs = -1L;
      PropertyInvalidatedCache.uncorkInvalidations(this.mPropertyName);
      return;
    } 
  }
  
  public void autoCork() {
    if (Looper.getMainLooper() == null) {
      PropertyInvalidatedCache.invalidateCache(this.mPropertyName);
      return;
    } 
    synchronized (this.mLock) {
      boolean bool;
      if (this.mUncorkDeadlineMs >= 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mUncorkDeadlineMs = SystemClock.uptimeMillis() + this.mAutoCorkDelayMs;
      if (!bool) {
        getHandlerLocked().sendEmptyMessageAtTime(0, this.mUncorkDeadlineMs);
        PropertyInvalidatedCache.corkInvalidations(this.mPropertyName);
      } 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PropertyInvalidatedCache$AutoCorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */