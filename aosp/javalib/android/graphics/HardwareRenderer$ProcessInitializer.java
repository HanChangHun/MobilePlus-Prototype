package android.graphics;

import android.app.ActivityManager;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.view.IGraphicsStats;
import android.view.IGraphicsStatsCallback;

class ProcessInitializer {
  static ProcessInitializer sInstance = new ProcessInitializer();
  
  private IGraphicsStatsCallback mGraphicsStatsCallback = (IGraphicsStatsCallback)new IGraphicsStatsCallback.Stub() {
      public void onRotateGraphicsStatsBuffer() throws RemoteException {
        HardwareRenderer.ProcessInitializer.this.rotateBuffer();
      }
    };
  
  private IGraphicsStats mGraphicsStatsService;
  
  private boolean mInitialized = false;
  
  private String mPackageName;
  
  private void initGraphicsStats() {
    if (this.mPackageName == null)
      return; 
    try {
      IBinder iBinder = ServiceManager.getService("graphicsstats");
      if (iBinder == null)
        return; 
      this.mGraphicsStatsService = IGraphicsStats.Stub.asInterface(iBinder);
    } finally {
      Exception exception = null;
    } 
  }
  
  private void initSched(long paramLong) {
    try {
      int i = HardwareRenderer.access$500(paramLong);
      ActivityManager.getService().setRenderThread(i);
    } finally {
      Exception exception = null;
    } 
  }
  
  private void requestBuffer() {
    try {
      ParcelFileDescriptor parcelFileDescriptor = this.mGraphicsStatsService.requestBufferForProcess(this.mPackageName, this.mGraphicsStatsCallback);
      HardwareRenderer.access$700(parcelFileDescriptor.getFd());
      parcelFileDescriptor.close();
    } finally {
      Exception exception = null;
    } 
  }
  
  private void rotateBuffer() {
    HardwareRenderer.access$600();
    requestBuffer();
  }
  
  void init(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mInitialized : Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifeq -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield mInitialized : Z
    //   19: aload_0
    //   20: lload_1
    //   21: invokespecial initSched : (J)V
    //   24: aload_0
    //   25: invokespecial initGraphicsStats : ()V
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: astore #4
    //   33: aload_0
    //   34: monitorexit
    //   35: aload #4
    //   37: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	31	finally
    //   14	28	31	finally
  }
  
  void setPackageName(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mInitialized : Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifeq -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: aload_1
    //   16: putfield mPackageName : Ljava/lang/String;
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
    //   14	19	22	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/HardwareRenderer$ProcessInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */