package android.filterfw.core;

import android.os.ConditionVariable;

class null implements Runnable {
  public void run() {
    filterToSchedule.unsetStatus(4);
    conditionToWake.open();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/SyncRunner$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */