package android.graphics;

final class DestroyContextRunnable implements Runnable {
  private final long mNativeInstance;
  
  DestroyContextRunnable(long paramLong) {
    this.mNativeInstance = paramLong;
  }
  
  public void run() {
    HardwareRenderer.access$300(this.mNativeInstance);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/HardwareRenderer$DestroyContextRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */