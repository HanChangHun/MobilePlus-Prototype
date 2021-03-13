package android.app;

import android.os.ParcelFileDescriptor;
import libcore.io.IoUtils;

class null implements Runnable {
  public void run() {
    try {
      ActivityThread.ApplicationThread.access$500(ActivityThread.ApplicationThread.this, dup, args, true);
      return;
    } finally {
      IoUtils.closeQuietly((AutoCloseable)dup);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$ApplicationThread$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */