package android.graphics;

import android.app.ActivityManager;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.view.IGraphicsStats;
import android.view.IGraphicsStatsCallback;
import android.view.NativeVectorDrawableAnimator;
import android.view.Surface;
import android.view.TextureLayer;
import android.view.animation.AnimationUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
import sun.misc.Cleaner;

public class HardwareRenderer {
  private static final String CACHE_PATH_SHADERS = "com.android.opengl.shaders_cache";
  
  private static final String CACHE_PATH_SKIASHADERS = "com.android.skia.shaders_cache";
  
  public static final int FLAG_DUMP_ALL = 1;
  
  public static final int FLAG_DUMP_FRAMESTATS = 1;
  
  public static final int FLAG_DUMP_RESET = 2;
  
  private static final String LOG_TAG = "HardwareRenderer";
  
  public static final int SYNC_CONTEXT_IS_STOPPED = 4;
  
  public static final int SYNC_FRAME_DROPPED = 8;
  
  public static final int SYNC_LOST_SURFACE_REWARD_IF_FOUND = 2;
  
  public static final int SYNC_OK = 0;
  
  public static final int SYNC_REDRAW_REQUESTED = 1;
  
  private boolean mForceDark = false;
  
  private boolean mIsWideGamut = false;
  
  private final long mNativeProxy;
  
  private boolean mOpaque = true;
  
  private FrameRenderRequest mRenderRequest = new FrameRenderRequest();
  
  protected RenderNode mRootNode;
  
  public HardwareRenderer() {
    RenderNode renderNode = RenderNode.adopt(nCreateRootRenderNode());
    this.mRootNode = renderNode;
    renderNode.setClipToBounds(false);
    long l = nCreateProxy(true ^ this.mOpaque, this.mIsWideGamut, this.mRootNode.mNativeRenderNode);
    this.mNativeProxy = l;
    if (l != 0L) {
      Cleaner.create(this, new DestroyContextRunnable(l));
      ProcessInitializer.sInstance.init(this.mNativeProxy);
      return;
    } 
    throw new OutOfMemoryError("Unable to create hardware renderer");
  }
  
  public static int copySurfaceInto(Surface paramSurface, Rect paramRect, Bitmap paramBitmap) {
    return (paramRect == null) ? nCopySurfaceInto(paramSurface, 0, 0, 0, 0, paramBitmap.getNativeInstance()) : nCopySurfaceInto(paramSurface, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom, paramBitmap.getNativeInstance());
  }
  
  public static Bitmap createHardwareBitmap(RenderNode paramRenderNode, int paramInt1, int paramInt2) {
    return nCreateHardwareBitmap(paramRenderNode.mNativeRenderNode, paramInt1, paramInt2);
  }
  
  public static native void disableVsync();
  
  public static void invokeFunctor(long paramLong, boolean paramBoolean) {
    nInvokeFunctor(paramLong, paramBoolean);
  }
  
  static void invokePictureCapturedCallback(long paramLong, PictureCapturedCallback paramPictureCapturedCallback) {
    paramPictureCapturedCallback.onPictureCaptured(new Picture(paramLong));
  }
  
  private static native void nAddObserver(long paramLong1, long paramLong2);
  
  private static native void nAddRenderNode(long paramLong1, long paramLong2, boolean paramBoolean);
  
  private static native void nAllocateBuffers(long paramLong);
  
  private static native void nBuildLayer(long paramLong1, long paramLong2);
  
  private static native void nCancelLayerUpdate(long paramLong1, long paramLong2);
  
  private static native boolean nCopyLayerInto(long paramLong1, long paramLong2, long paramLong3);
  
  private static native int nCopySurfaceInto(Surface paramSurface, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
  
  private static native Bitmap nCreateHardwareBitmap(long paramLong, int paramInt1, int paramInt2);
  
  private static native long nCreateProxy(boolean paramBoolean1, boolean paramBoolean2, long paramLong);
  
  private static native long nCreateRootRenderNode();
  
  private static native long nCreateTextureLayer(long paramLong);
  
  private static native void nDeleteProxy(long paramLong);
  
  private static native void nDestroy(long paramLong1, long paramLong2);
  
  private static native void nDestroyHardwareResources(long paramLong);
  
  private static native void nDetachSurfaceTexture(long paramLong1, long paramLong2);
  
  private static native void nDrawRenderNode(long paramLong1, long paramLong2);
  
  private static native void nDumpProfileInfo(long paramLong, FileDescriptor paramFileDescriptor, int paramInt);
  
  private static native void nFence(long paramLong);
  
  private static native int nGetRenderThreadTid(long paramLong);
  
  private static native void nHackySetRTAnimationsEnabled(boolean paramBoolean);
  
  private static native void nInvokeFunctor(long paramLong, boolean paramBoolean);
  
  private static native boolean nLoadSystemProperties(long paramLong);
  
  private static native void nNotifyFramePending(long paramLong);
  
  private static native void nOverrideProperty(String paramString1, String paramString2);
  
  private static native boolean nPause(long paramLong);
  
  private static native void nPushLayerUpdate(long paramLong1, long paramLong2);
  
  private static native void nRegisterAnimatingRenderNode(long paramLong1, long paramLong2);
  
  private static native void nRegisterVectorDrawableAnimator(long paramLong1, long paramLong2);
  
  private static native void nRemoveObserver(long paramLong1, long paramLong2);
  
  private static native void nRemoveRenderNode(long paramLong1, long paramLong2);
  
  private static native void nRotateProcessStatsBuffer();
  
  private static native void nSetContentDrawBounds(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  private static native void nSetContextPriority(int paramInt);
  
  private static native void nSetDebuggingEnabled(boolean paramBoolean);
  
  private static native void nSetForceDark(long paramLong, boolean paramBoolean);
  
  private static native void nSetFrameCallback(long paramLong, FrameDrawingCallback paramFrameDrawingCallback);
  
  private static native void nSetFrameCompleteCallback(long paramLong, FrameCompleteCallback paramFrameCompleteCallback);
  
  private static native void nSetHighContrastText(boolean paramBoolean);
  
  private static native void nSetIsolatedProcess(boolean paramBoolean);
  
  private static native void nSetLightAlpha(long paramLong, float paramFloat1, float paramFloat2);
  
  private static native void nSetLightGeometry(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  private static native void nSetName(long paramLong, String paramString);
  
  private static native void nSetOpaque(long paramLong, boolean paramBoolean);
  
  private static native void nSetPictureCaptureCallback(long paramLong, PictureCapturedCallback paramPictureCapturedCallback);
  
  private static native void nSetProcessStatsBuffer(int paramInt);
  
  private static native void nSetStopped(long paramLong, boolean paramBoolean);
  
  private static native void nSetSurface(long paramLong, Surface paramSurface, boolean paramBoolean);
  
  private static native void nSetWideGamut(long paramLong, boolean paramBoolean);
  
  private static native void nStopDrawing(long paramLong);
  
  private static native int nSyncAndDrawFrame(long paramLong, long[] paramArrayOflong, int paramInt);
  
  private static native void nTrimMemory(int paramInt);
  
  public static void overrideProperty(String paramString1, String paramString2) {
    if (paramString1 != null && paramString2 != null) {
      nOverrideProperty(paramString1, paramString2);
      return;
    } 
    throw new IllegalArgumentException("name and value must be non-null");
  }
  
  public static native void preload();
  
  public static void setContextPriority(int paramInt) {
    nSetContextPriority(paramInt);
  }
  
  public static void setDebuggingEnabled(boolean paramBoolean) {
    nSetDebuggingEnabled(paramBoolean);
  }
  
  public static void setFPSDivisor(int paramInt) {
    boolean bool = true;
    if (paramInt > 1)
      bool = false; 
    nHackySetRTAnimationsEnabled(bool);
  }
  
  public static void setHighContrastText(boolean paramBoolean) {
    nSetHighContrastText(paramBoolean);
  }
  
  public static void setIsolatedProcess(boolean paramBoolean) {
    nSetIsolatedProcess(paramBoolean);
  }
  
  public static void setPackageName(String paramString) {
    ProcessInitializer.sInstance.setPackageName(paramString);
  }
  
  public static void setupDiskCache(File paramFile) {
    setupShadersDiskCache((new File(paramFile, "com.android.opengl.shaders_cache")).getAbsolutePath(), (new File(paramFile, "com.android.skia.shaders_cache")).getAbsolutePath());
  }
  
  protected static native void setupShadersDiskCache(String paramString1, String paramString2);
  
  public static void trimMemory(int paramInt) {
    nTrimMemory(paramInt);
  }
  
  private static void validateAlpha(float paramFloat, String paramString) {
    if (paramFloat >= 0.0F && paramFloat <= 1.0F)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(" must be a valid alpha, ");
    stringBuilder.append(paramFloat);
    stringBuilder.append(" is not in the range of 0.0f to 1.0f");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static void validateFinite(float paramFloat, String paramString) {
    if (Float.isFinite(paramFloat))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(" must be finite, given=");
    stringBuilder.append(paramFloat);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static void validatePositive(float paramFloat, String paramString) {
    if (Float.isFinite(paramFloat) && paramFloat >= 0.0F)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(" must be a finite positive, given=");
    stringBuilder.append(paramFloat);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void addObserver(HardwareRendererObserver paramHardwareRendererObserver) {
    nAddObserver(this.mNativeProxy, paramHardwareRendererObserver.getNativeInstance());
  }
  
  public void addRenderNode(RenderNode paramRenderNode, boolean paramBoolean) {
    nAddRenderNode(this.mNativeProxy, paramRenderNode.mNativeRenderNode, paramBoolean);
  }
  
  public void allocateBuffers() {
    nAllocateBuffers(this.mNativeProxy);
  }
  
  public void buildLayer(RenderNode paramRenderNode) {
    if (paramRenderNode.hasDisplayList())
      nBuildLayer(this.mNativeProxy, paramRenderNode.mNativeRenderNode); 
  }
  
  public void clearContent() {
    nDestroyHardwareResources(this.mNativeProxy);
  }
  
  public boolean copyLayerInto(TextureLayer paramTextureLayer, Bitmap paramBitmap) {
    return nCopyLayerInto(this.mNativeProxy, paramTextureLayer.getDeferredLayerUpdater(), paramBitmap.getNativeInstance());
  }
  
  public FrameRenderRequest createRenderRequest() {
    this.mRenderRequest.reset();
    return this.mRenderRequest;
  }
  
  public TextureLayer createTextureLayer() {
    return TextureLayer.adoptTextureLayer(this, nCreateTextureLayer(this.mNativeProxy));
  }
  
  public void destroy() {
    nDestroy(this.mNativeProxy, this.mRootNode.mNativeRenderNode);
  }
  
  public void detachSurfaceTexture(long paramLong) {
    nDetachSurfaceTexture(this.mNativeProxy, paramLong);
  }
  
  public void drawRenderNode(RenderNode paramRenderNode) {
    nDrawRenderNode(this.mNativeProxy, paramRenderNode.mNativeRenderNode);
  }
  
  public void dumpProfileInfo(FileDescriptor paramFileDescriptor, int paramInt) {
    nDumpProfileInfo(this.mNativeProxy, paramFileDescriptor, paramInt);
  }
  
  public void fence() {
    nFence(this.mNativeProxy);
  }
  
  public boolean isOpaque() {
    return this.mOpaque;
  }
  
  public boolean isWideGamut() {
    return this.mIsWideGamut;
  }
  
  public boolean loadSystemProperties() {
    return nLoadSystemProperties(this.mNativeProxy);
  }
  
  public void notifyFramePending() {
    nNotifyFramePending(this.mNativeProxy);
  }
  
  public void onLayerDestroyed(TextureLayer paramTextureLayer) {
    nCancelLayerUpdate(this.mNativeProxy, paramTextureLayer.getDeferredLayerUpdater());
  }
  
  public boolean pause() {
    return nPause(this.mNativeProxy);
  }
  
  public void pushLayerUpdate(TextureLayer paramTextureLayer) {
    nPushLayerUpdate(this.mNativeProxy, paramTextureLayer.getDeferredLayerUpdater());
  }
  
  public void registerAnimatingRenderNode(RenderNode paramRenderNode) {
    nRegisterAnimatingRenderNode(this.mRootNode.mNativeRenderNode, paramRenderNode.mNativeRenderNode);
  }
  
  public void registerVectorDrawableAnimator(NativeVectorDrawableAnimator paramNativeVectorDrawableAnimator) {
    nRegisterVectorDrawableAnimator(this.mRootNode.mNativeRenderNode, paramNativeVectorDrawableAnimator.getAnimatorNativePtr());
  }
  
  public void removeObserver(HardwareRendererObserver paramHardwareRendererObserver) {
    nRemoveObserver(this.mNativeProxy, paramHardwareRendererObserver.getNativeInstance());
  }
  
  public void removeRenderNode(RenderNode paramRenderNode) {
    nRemoveRenderNode(this.mNativeProxy, paramRenderNode.mNativeRenderNode);
  }
  
  public void setContentDrawBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    nSetContentDrawBounds(this.mNativeProxy, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setContentRoot(RenderNode paramRenderNode) {
    RecordingCanvas recordingCanvas = this.mRootNode.beginRecording();
    if (paramRenderNode != null)
      recordingCanvas.drawRenderNode(paramRenderNode); 
    this.mRootNode.endRecording();
  }
  
  public boolean setForceDark(boolean paramBoolean) {
    if (this.mForceDark != paramBoolean) {
      this.mForceDark = paramBoolean;
      nSetForceDark(this.mNativeProxy, paramBoolean);
      return true;
    } 
    return false;
  }
  
  public void setFrameCallback(FrameDrawingCallback paramFrameDrawingCallback) {
    nSetFrameCallback(this.mNativeProxy, paramFrameDrawingCallback);
  }
  
  public void setFrameCompleteCallback(FrameCompleteCallback paramFrameCompleteCallback) {
    nSetFrameCompleteCallback(this.mNativeProxy, paramFrameCompleteCallback);
  }
  
  public void setLightSourceAlpha(float paramFloat1, float paramFloat2) {
    validateAlpha(paramFloat1, "ambientShadowAlpha");
    validateAlpha(paramFloat2, "spotShadowAlpha");
    nSetLightAlpha(this.mNativeProxy, paramFloat1, paramFloat2);
  }
  
  public void setLightSourceGeometry(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    validateFinite(paramFloat1, "lightX");
    validateFinite(paramFloat2, "lightY");
    validatePositive(paramFloat3, "lightZ");
    validatePositive(paramFloat4, "lightRadius");
    nSetLightGeometry(this.mNativeProxy, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public void setName(String paramString) {
    nSetName(this.mNativeProxy, paramString);
  }
  
  public void setOpaque(boolean paramBoolean) {
    if (this.mOpaque != paramBoolean) {
      this.mOpaque = paramBoolean;
      nSetOpaque(this.mNativeProxy, paramBoolean);
    } 
  }
  
  public void setPictureCaptureCallback(PictureCapturedCallback paramPictureCapturedCallback) {
    nSetPictureCaptureCallback(this.mNativeProxy, paramPictureCapturedCallback);
  }
  
  public void setStopped(boolean paramBoolean) {
    nSetStopped(this.mNativeProxy, paramBoolean);
  }
  
  public void setSurface(Surface paramSurface) {
    setSurface(paramSurface, false);
  }
  
  public void setSurface(Surface paramSurface, boolean paramBoolean) {
    if (paramSurface == null || paramSurface.isValid()) {
      nSetSurface(this.mNativeProxy, paramSurface, paramBoolean);
      return;
    } 
    throw new IllegalArgumentException("Surface is invalid. surface.isValid() == false.");
  }
  
  public void setWideGamut(boolean paramBoolean) {
    this.mIsWideGamut = paramBoolean;
    nSetWideGamut(this.mNativeProxy, paramBoolean);
  }
  
  public void start() {
    nSetStopped(this.mNativeProxy, false);
  }
  
  public void stop() {
    nSetStopped(this.mNativeProxy, true);
  }
  
  public void stopDrawing() {
    nStopDrawing(this.mNativeProxy);
  }
  
  public int syncAndDrawFrame(FrameInfo paramFrameInfo) {
    return nSyncAndDrawFrame(this.mNativeProxy, paramFrameInfo.frameInfo, paramFrameInfo.frameInfo.length);
  }
  
  private static final class DestroyContextRunnable implements Runnable {
    private final long mNativeInstance;
    
    DestroyContextRunnable(long param1Long) {
      this.mNativeInstance = param1Long;
    }
    
    public void run() {
      HardwareRenderer.nDeleteProxy(this.mNativeInstance);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface DumpFlags {}
  
  public static interface FrameCompleteCallback {
    void onFrameComplete(long param1Long);
  }
  
  public static interface FrameDrawingCallback {
    void onFrameDraw(long param1Long);
  }
  
  public final class FrameRenderRequest {
    private FrameInfo mFrameInfo = new FrameInfo();
    
    private boolean mWaitForPresent;
    
    private FrameRenderRequest() {}
    
    private void reset() {
      this.mWaitForPresent = false;
      HardwareRenderer.this.mRenderRequest.setVsyncTime(AnimationUtils.currentAnimationTimeMillis() * 1000000L);
    }
    
    public FrameRenderRequest setFrameCommitCallback(Executor param1Executor, Runnable param1Runnable) {
      HardwareRenderer.this.setFrameCompleteCallback(new _$$Lambda$HardwareRenderer$FrameRenderRequest$dejdYejpuxp3nc7eP6FZ2zBu778(param1Executor, param1Runnable));
      return this;
    }
    
    public void setFrameInfo(FrameInfo param1FrameInfo) {
      System.arraycopy(param1FrameInfo.frameInfo, 0, this.mFrameInfo.frameInfo, 0, param1FrameInfo.frameInfo.length);
    }
    
    public FrameRenderRequest setVsyncTime(long param1Long) {
      this.mFrameInfo.setVsync(param1Long, param1Long);
      this.mFrameInfo.addFlags(4L);
      return this;
    }
    
    public FrameRenderRequest setWaitForPresent(boolean param1Boolean) {
      this.mWaitForPresent = param1Boolean;
      return this;
    }
    
    public int syncAndDraw() {
      int i = HardwareRenderer.this.syncAndDrawFrame(this.mFrameInfo);
      if (this.mWaitForPresent && (i & 0x8) == 0)
        HardwareRenderer.this.fence(); 
      return i;
    }
  }
  
  public static interface PictureCapturedCallback {
    void onPictureCaptured(Picture param1Picture);
  }
  
  private static class ProcessInitializer {
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
    
    private void initSched(long param1Long) {
      try {
        int i = HardwareRenderer.nGetRenderThreadTid(param1Long);
        ActivityManager.getService().setRenderThread(i);
      } finally {
        Exception exception = null;
      } 
    }
    
    private void requestBuffer() {
      try {
        ParcelFileDescriptor parcelFileDescriptor = this.mGraphicsStatsService.requestBufferForProcess(this.mPackageName, this.mGraphicsStatsCallback);
        HardwareRenderer.nSetProcessStatsBuffer(parcelFileDescriptor.getFd());
        parcelFileDescriptor.close();
      } finally {
        Exception exception = null;
      } 
    }
    
    private void rotateBuffer() {
      HardwareRenderer.nRotateProcessStatsBuffer();
      requestBuffer();
    }
    
    void init(long param1Long) {
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
    
    void setPackageName(String param1String) {
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
  
  class null extends IGraphicsStatsCallback.Stub {
    public void onRotateGraphicsStatsBuffer() throws RemoteException {
      this.this$0.rotateBuffer();
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SyncAndDrawResult {}
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/HardwareRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */