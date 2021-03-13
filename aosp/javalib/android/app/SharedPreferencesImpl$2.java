package android.app;

class null implements Runnable {
  public void run() {
    synchronized (SharedPreferencesImpl.access$800(SharedPreferencesImpl.this)) {
      SharedPreferencesImpl.access$900(SharedPreferencesImpl.this, mcr, isFromSyncCommit);
      synchronized (SharedPreferencesImpl.access$200(SharedPreferencesImpl.this)) {
        SharedPreferencesImpl.access$310(SharedPreferencesImpl.this);
        Runnable runnable = postWriteRunnable;
        if (runnable != null)
          runnable.run(); 
        return;
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SharedPreferencesImpl$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */