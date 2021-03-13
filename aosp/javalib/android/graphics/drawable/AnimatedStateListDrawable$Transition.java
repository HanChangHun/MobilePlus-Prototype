package android.graphics.drawable;

abstract class Transition {
  private Transition() {}
  
  public boolean canReverse() {
    return false;
  }
  
  public void reverse() {}
  
  public abstract void start();
  
  public abstract void stop();
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedStateListDrawable$Transition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */