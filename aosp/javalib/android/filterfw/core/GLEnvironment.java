package android.filterfw.core;

import android.graphics.SurfaceTexture;
import android.media.MediaRecorder;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;

public class GLEnvironment {
  private int glEnvId;
  
  private boolean mManageContext = true;
  
  static {
    System.loadLibrary("filterfw");
  }
  
  public GLEnvironment() {
    nativeAllocate();
  }
  
  private GLEnvironment(NativeAllocatorTag paramNativeAllocatorTag) {}
  
  public static boolean isAnyContextActive() {
    return nativeIsAnyContextActive();
  }
  
  private native boolean nativeActivate();
  
  private native boolean nativeActivateSurfaceId(int paramInt);
  
  private native int nativeAddSurface(Surface paramSurface);
  
  private native int nativeAddSurfaceFromMediaRecorder(MediaRecorder paramMediaRecorder);
  
  private native int nativeAddSurfaceWidthHeight(Surface paramSurface, int paramInt1, int paramInt2);
  
  private native boolean nativeAllocate();
  
  private native boolean nativeDeactivate();
  
  private native boolean nativeDeallocate();
  
  private native boolean nativeDisconnectSurfaceMediaSource(MediaRecorder paramMediaRecorder);
  
  private native boolean nativeInitWithCurrentContext();
  
  private native boolean nativeInitWithNewContext();
  
  private native boolean nativeIsActive();
  
  private static native boolean nativeIsAnyContextActive();
  
  private native boolean nativeIsContextActive();
  
  private native boolean nativeRemoveSurfaceId(int paramInt);
  
  private native boolean nativeSetSurfaceTimestamp(long paramLong);
  
  private native boolean nativeSwapBuffers();
  
  public void activate() {
    if (Looper.myLooper() != null && Looper.myLooper().equals(Looper.getMainLooper()))
      Log.e("FilterFramework", "Activating GL context in UI thread!"); 
    if (!this.mManageContext || nativeActivate())
      return; 
    throw new RuntimeException("Could not activate GLEnvironment!");
  }
  
  public void activateSurfaceWithId(int paramInt) {
    if (nativeActivateSurfaceId(paramInt))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not activate surface ");
    stringBuilder.append(paramInt);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void deactivate() {
    if (!this.mManageContext || nativeDeactivate())
      return; 
    throw new RuntimeException("Could not deactivate GLEnvironment!");
  }
  
  protected void finalize() throws Throwable {
    tearDown();
  }
  
  public void initWithCurrentContext() {
    this.mManageContext = false;
    if (nativeInitWithCurrentContext())
      return; 
    throw new RuntimeException("Could not initialize GLEnvironment with current context!");
  }
  
  public void initWithNewContext() {
    this.mManageContext = true;
    if (nativeInitWithNewContext())
      return; 
    throw new RuntimeException("Could not initialize GLEnvironment with new context!");
  }
  
  public boolean isActive() {
    return nativeIsActive();
  }
  
  public boolean isContextActive() {
    return nativeIsContextActive();
  }
  
  public int registerSurface(Surface paramSurface) {
    int i = nativeAddSurface(paramSurface);
    if (i >= 0)
      return i; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error registering surface ");
    stringBuilder.append(paramSurface);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public int registerSurfaceFromMediaRecorder(MediaRecorder paramMediaRecorder) {
    int i = nativeAddSurfaceFromMediaRecorder(paramMediaRecorder);
    if (i >= 0)
      return i; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error registering surface from MediaRecorder");
    stringBuilder.append(paramMediaRecorder);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public int registerSurfaceTexture(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2) {
    Surface surface = new Surface(paramSurfaceTexture);
    paramInt1 = nativeAddSurfaceWidthHeight(surface, paramInt1, paramInt2);
    surface.release();
    if (paramInt1 >= 0)
      return paramInt1; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error registering surfaceTexture ");
    stringBuilder.append(paramSurfaceTexture);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setSurfaceTimestamp(long paramLong) {
    if (nativeSetSurfaceTimestamp(paramLong))
      return; 
    throw new RuntimeException("Could not set timestamp for current surface!");
  }
  
  public void swapBuffers() {
    if (nativeSwapBuffers())
      return; 
    throw new RuntimeException("Error swapping EGL buffers!");
  }
  
  public void tearDown() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield glEnvId : I
    //   6: iconst_m1
    //   7: if_icmpeq -> 20
    //   10: aload_0
    //   11: invokespecial nativeDeallocate : ()Z
    //   14: pop
    //   15: aload_0
    //   16: iconst_m1
    //   17: putfield glEnvId : I
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	23	finally
  }
  
  public void unregisterSurfaceId(int paramInt) {
    if (nativeRemoveSurfaceId(paramInt))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not unregister surface ");
    stringBuilder.append(paramInt);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/GLEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */