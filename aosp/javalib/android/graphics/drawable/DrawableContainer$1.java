package android.graphics.drawable;

class null implements Runnable {
  public void run() {
    DrawableContainer.this.animate(true);
    DrawableContainer.this.invalidateSelf();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/DrawableContainer$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */