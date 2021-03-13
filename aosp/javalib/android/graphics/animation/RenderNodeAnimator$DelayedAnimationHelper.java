package android.graphics.animation;

import android.view.Choreographer;
import java.util.ArrayList;

class DelayedAnimationHelper implements Runnable {
  private boolean mCallbackScheduled;
  
  private final Choreographer mChoreographer = Choreographer.getInstance();
  
  private ArrayList<RenderNodeAnimator> mDelayedAnims = new ArrayList<>();
  
  private void scheduleCallback() {
    if (!this.mCallbackScheduled) {
      this.mCallbackScheduled = true;
      this.mChoreographer.postCallback(1, this, null);
    } 
  }
  
  public void addDelayedAnimation(RenderNodeAnimator paramRenderNodeAnimator) {
    this.mDelayedAnims.add(paramRenderNodeAnimator);
    scheduleCallback();
  }
  
  public void removeDelayedAnimation(RenderNodeAnimator paramRenderNodeAnimator) {
    this.mDelayedAnims.remove(paramRenderNodeAnimator);
  }
  
  public void run() {
    long l = this.mChoreographer.getFrameTime();
    this.mCallbackScheduled = false;
    int i = 0;
    byte b = 0;
    while (b < this.mDelayedAnims.size()) {
      RenderNodeAnimator renderNodeAnimator = this.mDelayedAnims.get(b);
      int j = i;
      if (!RenderNodeAnimator.access$000(renderNodeAnimator, l)) {
        if (i != b)
          this.mDelayedAnims.set(i, renderNodeAnimator); 
        j = i + 1;
      } 
      b++;
      i = j;
    } 
    while (this.mDelayedAnims.size() > i) {
      ArrayList<RenderNodeAnimator> arrayList = this.mDelayedAnims;
      arrayList.remove(arrayList.size() - 1);
    } 
    if (this.mDelayedAnims.size() > 0)
      scheduleCallback(); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/animation/RenderNodeAnimator$DelayedAnimationHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */