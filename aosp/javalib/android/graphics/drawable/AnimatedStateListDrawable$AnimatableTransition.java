package android.graphics.drawable;

class AnimatableTransition extends AnimatedStateListDrawable.Transition {
  private final Animatable mA;
  
  public AnimatableTransition(Animatable paramAnimatable) {
    this.mA = paramAnimatable;
  }
  
  public void start() {
    this.mA.start();
  }
  
  public void stop() {
    this.mA.stop();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedStateListDrawable$AnimatableTransition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */