package android.app;

import android.util.EventLog;

public class EventLogTags {
  public static final int WM_ADD_TO_STOPPING = 30066;
  
  public static final int WM_ON_ACTIVITY_RESULT_CALLED = 30062;
  
  public static final int WM_ON_CREATE_CALLED = 30057;
  
  public static final int WM_ON_DESTROY_CALLED = 30060;
  
  public static final int WM_ON_PAUSED_CALLED = 30021;
  
  public static final int WM_ON_RESTART_CALLED = 30058;
  
  public static final int WM_ON_RESUME_CALLED = 30022;
  
  public static final int WM_ON_START_CALLED = 30059;
  
  public static final int WM_ON_STOP_CALLED = 30049;
  
  public static final int WM_ON_TOP_RESUMED_GAINED_CALLED = 30064;
  
  public static final int WM_ON_TOP_RESUMED_LOST_CALLED = 30065;
  
  public static final int WM_STOP_ACTIVITY = 30048;
  
  public static void writeWmAddToStopping(int paramInt1, int paramInt2, String paramString1, String paramString2) {
    EventLog.writeEvent(30066, new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramString1, paramString2 });
  }
  
  public static void writeWmOnActivityResultCalled(int paramInt, String paramString1, String paramString2) {
    EventLog.writeEvent(30062, new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
  }
  
  public static void writeWmOnCreateCalled(int paramInt, String paramString1, String paramString2) {
    EventLog.writeEvent(30057, new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
  }
  
  public static void writeWmOnDestroyCalled(int paramInt, String paramString1, String paramString2) {
    EventLog.writeEvent(30060, new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
  }
  
  public static void writeWmOnPausedCalled(int paramInt, String paramString1, String paramString2) {
    EventLog.writeEvent(30021, new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
  }
  
  public static void writeWmOnRestartCalled(int paramInt, String paramString1, String paramString2) {
    EventLog.writeEvent(30058, new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
  }
  
  public static void writeWmOnResumeCalled(int paramInt, String paramString1, String paramString2) {
    EventLog.writeEvent(30022, new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
  }
  
  public static void writeWmOnStartCalled(int paramInt, String paramString1, String paramString2) {
    EventLog.writeEvent(30059, new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
  }
  
  public static void writeWmOnStopCalled(int paramInt, String paramString1, String paramString2) {
    EventLog.writeEvent(30049, new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
  }
  
  public static void writeWmOnTopResumedGainedCalled(int paramInt, String paramString1, String paramString2) {
    EventLog.writeEvent(30064, new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
  }
  
  public static void writeWmOnTopResumedLostCalled(int paramInt, String paramString1, String paramString2) {
    EventLog.writeEvent(30065, new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
  }
  
  public static void writeWmStopActivity(int paramInt1, int paramInt2, String paramString) {
    EventLog.writeEvent(30048, new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramString });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/EventLogTags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */