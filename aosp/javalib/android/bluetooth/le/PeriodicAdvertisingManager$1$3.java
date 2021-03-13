package android.bluetooth.le;

class null implements Runnable {
  public void run() {
    callback.onSyncLost(syncHandle);
    this.this$1.this$0.mCallbackWrappers.remove(callback);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/PeriodicAdvertisingManager$1$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */