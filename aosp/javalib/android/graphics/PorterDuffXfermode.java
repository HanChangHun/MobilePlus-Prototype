package android.graphics;

public class PorterDuffXfermode extends Xfermode {
  public PorterDuffXfermode(PorterDuff.Mode paramMode) {
    this.porterDuffMode = paramMode.nativeInt;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PorterDuffXfermode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */