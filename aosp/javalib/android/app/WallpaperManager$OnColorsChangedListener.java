package android.app;

public interface OnColorsChangedListener {
  void onColorsChanged(WallpaperColors paramWallpaperColors, int paramInt);
  
  default void onColorsChanged(WallpaperColors paramWallpaperColors, int paramInt1, int paramInt2) {
    onColorsChanged(paramWallpaperColors, paramInt1);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WallpaperManager$OnColorsChangedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */