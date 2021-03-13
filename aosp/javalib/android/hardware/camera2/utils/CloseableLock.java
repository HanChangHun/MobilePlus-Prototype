package android.hardware.camera2.utils;

import android.util.Log;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CloseableLock implements AutoCloseable {
  private static final boolean VERBOSE = false;
  
  private final String TAG = "CloseableLock";
  
  private volatile boolean mClosed = false;
  
  private final Condition mCondition;
  
  private boolean mExclusive = false;
  
  private final ReentrantLock mLock;
  
  private final ThreadLocal<Integer> mLockCount;
  
  private final String mName;
  
  private int mSharedLocks = 0;
  
  public CloseableLock() {
    ReentrantLock reentrantLock = new ReentrantLock();
    this.mLock = reentrantLock;
    this.mCondition = reentrantLock.newCondition();
    this.mLockCount = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
          return Integer.valueOf(0);
        }
      };
    this.mName = "";
  }
  
  public CloseableLock(String paramString) {
    ReentrantLock reentrantLock = new ReentrantLock();
    this.mLock = reentrantLock;
    this.mCondition = reentrantLock.newCondition();
    this.mLockCount = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
          return Integer.valueOf(0);
        }
      };
    this.mName = paramString;
  }
  
  private void log(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CloseableLock[");
    stringBuilder.append(this.mName);
    stringBuilder.append("]");
    Log.v(stringBuilder.toString(), paramString);
  }
  
  public ScopedLock acquireExclusiveLock() {
    try {
      this.mLock.lock();
      boolean bool = this.mClosed;
      if (bool)
        return null; 
      int i = ((Integer)this.mLockCount.get()).intValue();
      if (this.mExclusive || i <= 0) {
        while (i == 0 && (this.mExclusive || this.mSharedLocks > 0)) {
          this.mCondition.awaitUninterruptibly();
          bool = this.mClosed;
          if (bool)
            return null; 
        } 
        this.mExclusive = true;
        i = ((Integer)this.mLockCount.get()).intValue();
        this.mLockCount.set(Integer.valueOf(i + 1));
        return new ScopedLock();
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Cannot acquire exclusive lock while holding shared lock");
      throw illegalStateException;
    } finally {
      this.mLock.unlock();
    } 
  }
  
  public ScopedLock acquireLock() {
    try {
      this.mLock.lock();
      boolean bool = this.mClosed;
      if (bool)
        return null; 
      int i = ((Integer)this.mLockCount.get()).intValue();
      if (!this.mExclusive || i <= 0) {
        while (this.mExclusive) {
          this.mCondition.awaitUninterruptibly();
          bool = this.mClosed;
          if (bool)
            return null; 
        } 
        this.mSharedLocks++;
        i = ((Integer)this.mLockCount.get()).intValue();
        this.mLockCount.set(Integer.valueOf(i + 1));
        return new ScopedLock();
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Cannot acquire shared lock while holding exclusive lock");
      throw illegalStateException;
    } finally {
      this.mLock.unlock();
    } 
  }
  
  public void close() {
    if (this.mClosed)
      return; 
    if (acquireExclusiveLock() == null)
      return; 
    if (((Integer)this.mLockCount.get()).intValue() == 1)
      try {
        this.mLock.lock();
        this.mClosed = true;
        this.mExclusive = false;
        this.mSharedLocks = 0;
        this.mLockCount.remove();
        this.mCondition.signalAll();
        return;
      } finally {
        this.mLock.unlock();
      }  
    throw new IllegalStateException("Cannot close while one or more acquired locks are being held by this thread; release all other locks first");
  }
  
  public void releaseLock() {
    if (((Integer)this.mLockCount.get()).intValue() > 0)
      try {
        this.mLock.lock();
        if (!this.mClosed) {
          if (!this.mExclusive) {
            this.mSharedLocks--;
          } else if (this.mSharedLocks != 0) {
            AssertionError assertionError = new AssertionError();
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("Too many shared locks ");
            stringBuilder.append(this.mSharedLocks);
            this(stringBuilder.toString());
            throw assertionError;
          } 
          int i = ((Integer)this.mLockCount.get()).intValue() - 1;
          this.mLockCount.set(Integer.valueOf(i));
          if (i == 0 && this.mExclusive) {
            this.mExclusive = false;
            this.mCondition.signalAll();
          } else if (i == 0 && this.mSharedLocks == 0) {
            this.mCondition.signalAll();
          } 
          return;
        } 
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Do not release after the lock has been closed");
        throw illegalStateException;
      } finally {
        this.mLock.unlock();
      }  
    throw new IllegalStateException("Cannot release lock that was not acquired by this thread");
  }
  
  public class ScopedLock implements AutoCloseable {
    private ScopedLock() {}
    
    public void close() {
      CloseableLock.this.releaseLock();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/CloseableLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */