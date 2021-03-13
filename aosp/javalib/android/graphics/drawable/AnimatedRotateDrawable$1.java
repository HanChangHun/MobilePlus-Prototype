package android.graphics.drawable;

class null implements Runnable {
  public void run() {
    AnimatedRotateDrawable animatedRotateDrawable = AnimatedRotateDrawable.this;
    AnimatedRotateDrawable.access$216(animatedRotateDrawable, AnimatedRotateDrawable.access$300(animatedRotateDrawable));
    if (AnimatedRotateDrawable.access$200(AnimatedRotateDrawable.this) > 360.0F - AnimatedRotateDrawable.access$300(AnimatedRotateDrawable.this))
      AnimatedRotateDrawable.access$202(AnimatedRotateDrawable.this, 0.0F); 
    AnimatedRotateDrawable.this.invalidateSelf();
    AnimatedRotateDrawable.access$400(AnimatedRotateDrawable.this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedRotateDrawable$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */