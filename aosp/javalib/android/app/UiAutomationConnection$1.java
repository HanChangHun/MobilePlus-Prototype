package android.app;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import libcore.io.IoUtils;

class null implements Runnable {
  public void run() {
    try {
      if (writeToProcess != null)
        writeToProcess.join(); 
      if (readFromProcess != null)
        readFromProcess.join(); 
    } catch (InterruptedException interruptedException) {
      Log.e("UiAutomationConnection", "At least one of the threads was interrupted");
    } 
    IoUtils.closeQuietly((AutoCloseable)sink);
    IoUtils.closeQuietly((AutoCloseable)source);
    process.destroy();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/UiAutomationConnection$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */