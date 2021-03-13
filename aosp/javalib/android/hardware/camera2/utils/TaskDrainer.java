package android.hardware.camera2.utils;

import com.android.internal.util.Preconditions;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

public class TaskDrainer<T> {
  private static final String TAG = "TaskDrainer";
  
  private final boolean DEBUG = false;
  
  private boolean mDrainFinished = false;
  
  private boolean mDraining = false;
  
  private final Set<T> mEarlyFinishedTaskSet = new HashSet<>();
  
  private final Executor mExecutor;
  
  private final DrainListener mListener;
  
  private final Object mLock = new Object();
  
  private final String mName;
  
  private final Set<T> mTaskSet = new HashSet<>();
  
  public TaskDrainer(Executor paramExecutor, DrainListener paramDrainListener) {
    this.mExecutor = (Executor)Preconditions.checkNotNull(paramExecutor, "executor must not be null");
    this.mListener = (DrainListener)Preconditions.checkNotNull(paramDrainListener, "listener must not be null");
    this.mName = null;
  }
  
  public TaskDrainer(Executor paramExecutor, DrainListener paramDrainListener, String paramString) {
    this.mExecutor = (Executor)Preconditions.checkNotNull(paramExecutor, "executor must not be null");
    this.mListener = (DrainListener)Preconditions.checkNotNull(paramDrainListener, "listener must not be null");
    this.mName = paramString;
  }
  
  private void checkIfDrainFinished() {
    if (this.mTaskSet.isEmpty() && this.mDraining && !this.mDrainFinished) {
      this.mDrainFinished = true;
      postDrained();
    } 
  }
  
  private void postDrained() {
    this.mExecutor.execute(new _$$Lambda$TaskDrainer$Jb53sDskEXp_qIjiikQeCRx0wJs(this));
  }
  
  public void beginDrain() {
    synchronized (this.mLock) {
      if (!this.mDraining) {
        this.mDraining = true;
        checkIfDrainFinished();
      } 
      return;
    } 
  }
  
  public void taskFinished(T paramT) {
    synchronized (this.mLock) {
      if (this.mTaskSet.remove(paramT) || this.mEarlyFinishedTaskSet.add(paramT)) {
        checkIfDrainFinished();
        return;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Task ");
      stringBuilder.append(paramT);
      stringBuilder.append(" was already finished");
      this(stringBuilder.toString());
      throw illegalStateException;
    } 
  }
  
  public void taskStarted(T paramT) {
    synchronized (this.mLock) {
      if (!this.mDraining) {
        if (this.mEarlyFinishedTaskSet.remove(paramT) || this.mTaskSet.add(paramT))
          return; 
        IllegalStateException illegalStateException1 = new IllegalStateException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Task ");
        stringBuilder.append(paramT);
        stringBuilder.append(" was already started");
        this(stringBuilder.toString());
        throw illegalStateException1;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Can't start more tasks after draining has begun");
      throw illegalStateException;
    } 
  }
  
  public static interface DrainListener {
    void onDrained();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/TaskDrainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */