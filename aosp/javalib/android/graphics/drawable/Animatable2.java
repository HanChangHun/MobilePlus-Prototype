package android.graphics.drawable;

public interface Animatable2 extends Animatable {
  void clearAnimationCallbacks();
  
  void registerAnimationCallback(AnimationCallback paramAnimationCallback);
  
  boolean unregisterAnimationCallback(AnimationCallback paramAnimationCallback);
  
  public static abstract class AnimationCallback {
    public void onAnimationEnd(Drawable param1Drawable) {}
    
    public void onAnimationStart(Drawable param1Drawable) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/Animatable2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */