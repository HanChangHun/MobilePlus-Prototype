package android.app;

class ContextMenuRunnable implements Runnable {
  private final Activity activity;
  
  private final int flags;
  
  private final int identifier;
  
  boolean returnValue;
  
  public ContextMenuRunnable(Activity paramActivity, int paramInt1, int paramInt2) {
    this.activity = paramActivity;
    this.identifier = paramInt1;
    this.flags = paramInt2;
  }
  
  public void run() {
    this.returnValue = this.activity.getWindow().performContextMenuIdentifierAction(this.identifier, this.flags);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Instrumentation$1ContextMenuRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */