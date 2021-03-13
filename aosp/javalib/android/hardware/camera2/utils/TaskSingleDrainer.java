package android.hardware.camera2.utils;

import java.util.concurrent.Executor;

public class TaskSingleDrainer {
  private final Object mSingleTask = new Object();
  
  private final TaskDrainer<Object> mTaskDrainer;
  
  public TaskSingleDrainer(Executor paramExecutor, TaskDrainer.DrainListener paramDrainListener) {
    this.mTaskDrainer = new TaskDrainer(paramExecutor, paramDrainListener);
  }
  
  public TaskSingleDrainer(Executor paramExecutor, TaskDrainer.DrainListener paramDrainListener, String paramString) {
    this.mTaskDrainer = new TaskDrainer(paramExecutor, paramDrainListener, paramString);
  }
  
  public void beginDrain() {
    this.mTaskDrainer.beginDrain();
  }
  
  public void taskFinished() {
    this.mTaskDrainer.taskFinished(this.mSingleTask);
  }
  
  public void taskStarted() {
    this.mTaskDrainer.taskStarted(this.mSingleTask);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/TaskSingleDrainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */