package android.graphics;

import android.view.NativeVectorDrawableAnimator;

public interface AnimationHost {
  boolean isAttached();
  
  void registerAnimatingRenderNode(RenderNode paramRenderNode);
  
  void registerVectorDrawableAnimator(NativeVectorDrawableAnimator paramNativeVectorDrawableAnimator);
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/RenderNode$AnimationHost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */