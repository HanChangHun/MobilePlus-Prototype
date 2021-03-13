package android.app.backup;

class FailRunnable implements Runnable {
  private String mMessage;
  
  FailRunnable(String paramString) {
    this.mMessage = paramString;
  }
  
  public void run() {
    throw new IllegalStateException(this.mMessage);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupAgent$FailRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */