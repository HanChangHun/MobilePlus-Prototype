package android.graphics.drawable;

class BlockInvalidateCallback implements Drawable.Callback {
  private Drawable.Callback mCallback;
  
  private BlockInvalidateCallback() {}
  
  public void invalidateDrawable(Drawable paramDrawable) {}
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong) {
    Drawable.Callback callback = this.mCallback;
    if (callback != null)
      callback.scheduleDrawable(paramDrawable, paramRunnable, paramLong); 
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable) {
    Drawable.Callback callback = this.mCallback;
    if (callback != null)
      callback.unscheduleDrawable(paramDrawable, paramRunnable); 
  }
  
  public Drawable.Callback unwrap() {
    Drawable.Callback callback = this.mCallback;
    this.mCallback = null;
    return callback;
  }
  
  public BlockInvalidateCallback wrap(Drawable.Callback paramCallback) {
    this.mCallback = paramCallback;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/DrawableContainer$BlockInvalidateCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */