package android.graphics.drawable;

class null implements Drawable.Callback {
  public void invalidateDrawable(Drawable paramDrawable) {
    AnimatedVectorDrawable.this.invalidateSelf();
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong) {
    AnimatedVectorDrawable.this.scheduleSelf(paramRunnable, paramLong);
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable) {
    AnimatedVectorDrawable.this.unscheduleSelf(paramRunnable);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedVectorDrawable$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */