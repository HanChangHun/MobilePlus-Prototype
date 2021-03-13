package android.app;

import android.os.Process;
import android.util.Log;

final class InstrumentationThread extends Thread {
  public InstrumentationThread(String paramString) {
    super(paramString);
  }
  
  public void run() {
    try {
      Process.setThreadPriority(-8);
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Exception setting priority of instrumentation thread ");
      stringBuilder.append(Process.myTid());
      Log.w("Instrumentation", stringBuilder.toString(), runtimeException);
    } 
    if (Instrumentation.access$100(Instrumentation.this))
      Instrumentation.this.startPerformanceSnapshot(); 
    Instrumentation.this.onStart();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Instrumentation$InstrumentationThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */