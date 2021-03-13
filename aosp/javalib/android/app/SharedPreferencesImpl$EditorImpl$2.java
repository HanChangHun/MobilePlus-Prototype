package android.app;

class null implements Runnable {
  public void run() {
    awaitCommit.run();
    QueuedWork.removeFinisher(awaitCommit);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SharedPreferencesImpl$EditorImpl$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */