package android.graphics;

import android.view.animation.AnimationUtils;
import java.util.concurrent.Executor;

public final class FrameRenderRequest {
  private FrameInfo mFrameInfo = new FrameInfo();
  
  private boolean mWaitForPresent;
  
  private FrameRenderRequest() {}
  
  private void reset() {
    this.mWaitForPresent = false;
    HardwareRenderer.access$000(HardwareRenderer.this).setVsyncTime(AnimationUtils.currentAnimationTimeMillis() * 1000000L);
  }
  
  public FrameRenderRequest setFrameCommitCallback(Executor paramExecutor, Runnable paramRunnable) {
    HardwareRenderer.this.setFrameCompleteCallback(new _$$Lambda$HardwareRenderer$FrameRenderRequest$dejdYejpuxp3nc7eP6FZ2zBu778(paramExecutor, paramRunnable));
    return this;
  }
  
  public void setFrameInfo(FrameInfo paramFrameInfo) {
    System.arraycopy(paramFrameInfo.frameInfo, 0, this.mFrameInfo.frameInfo, 0, paramFrameInfo.frameInfo.length);
  }
  
  public FrameRenderRequest setVsyncTime(long paramLong) {
    this.mFrameInfo.setVsync(paramLong, paramLong);
    this.mFrameInfo.addFlags(4L);
    return this;
  }
  
  public FrameRenderRequest setWaitForPresent(boolean paramBoolean) {
    this.mWaitForPresent = paramBoolean;
    return this;
  }
  
  public int syncAndDraw() {
    int i = HardwareRenderer.this.syncAndDrawFrame(this.mFrameInfo);
    if (this.mWaitForPresent && (i & 0x8) == 0)
      HardwareRenderer.this.fence(); 
    return i;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/HardwareRenderer$FrameRenderRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */