package android.app.backup;

import android.app.QueuedWork;
import java.util.concurrent.CountDownLatch;

class SharedPrefsSynchronizer implements Runnable {
  public final CountDownLatch mLatch = new CountDownLatch(1);
  
  public void run() {
    QueuedWork.waitToFinish();
    this.mLatch.countDown();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupAgent$SharedPrefsSynchronizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */