package android.app;

class null implements Runnable {
  public void run() {
    try {
      mcr.writtenToDiskLatch.await();
    } catch (InterruptedException interruptedException) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SharedPreferencesImpl$EditorImpl$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */