package android.graphics.drawable;

public interface Callback {
  void invalidateDrawable(Drawable paramDrawable);
  
  void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong);
  
  void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable);
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/Drawable$Callback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */