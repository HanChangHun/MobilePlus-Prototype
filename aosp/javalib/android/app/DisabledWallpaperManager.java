package android.app;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.io.InputStream;

final class DisabledWallpaperManager extends WallpaperManager {
  private static final boolean DEBUG = false;
  
  private static final String TAG = DisabledWallpaperManager.class.getSimpleName();
  
  private static DisabledWallpaperManager sInstance;
  
  static DisabledWallpaperManager getInstance() {
    if (sInstance == null)
      sInstance = new DisabledWallpaperManager(); 
    return sInstance;
  }
  
  private static <T> T unsupported() {
    return null;
  }
  
  private static boolean unsupportedBoolean() {
    return false;
  }
  
  public void addOnColorsChangedListener(WallpaperManager.OnColorsChangedListener paramOnColorsChangedListener, Handler paramHandler) {
    unsupported();
  }
  
  public void addOnColorsChangedListener(WallpaperManager.OnColorsChangedListener paramOnColorsChangedListener, Handler paramHandler, int paramInt) {
    unsupported();
  }
  
  public void clear() throws IOException {
    unsupported();
  }
  
  public void clear(int paramInt) throws IOException {
    unsupported();
  }
  
  public void clearWallpaper() {
    unsupported();
  }
  
  public void clearWallpaper(int paramInt1, int paramInt2) {
    unsupported();
  }
  
  public void clearWallpaperOffsets(IBinder paramIBinder) {
    unsupported();
  }
  
  public void forgetLoadedWallpaper() {
    unsupported();
  }
  
  public Bitmap getBitmap() {
    return unsupported();
  }
  
  public Bitmap getBitmap(boolean paramBoolean) {
    return unsupported();
  }
  
  public Bitmap getBitmapAsUser(int paramInt, boolean paramBoolean) {
    return unsupported();
  }
  
  public Drawable getBuiltInDrawable() {
    return unsupported();
  }
  
  public Drawable getBuiltInDrawable(int paramInt) {
    return unsupported();
  }
  
  public Drawable getBuiltInDrawable(int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat1, float paramFloat2) {
    return unsupported();
  }
  
  public Drawable getBuiltInDrawable(int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat1, float paramFloat2, int paramInt3) {
    return unsupported();
  }
  
  public Intent getCropAndSetWallpaperIntent(Uri paramUri) {
    return unsupported();
  }
  
  public int getDesiredMinimumHeight() {
    return ((Integer)unsupported()).intValue();
  }
  
  public int getDesiredMinimumWidth() {
    return ((Integer)unsupported()).intValue();
  }
  
  public Drawable getDrawable() {
    return unsupported();
  }
  
  public Drawable getFastDrawable() {
    return unsupported();
  }
  
  public WallpaperColors getWallpaperColors(int paramInt) {
    return unsupported();
  }
  
  public WallpaperColors getWallpaperColors(int paramInt1, int paramInt2) {
    return unsupported();
  }
  
  public ParcelFileDescriptor getWallpaperFile(int paramInt) {
    return unsupported();
  }
  
  public ParcelFileDescriptor getWallpaperFile(int paramInt1, int paramInt2) {
    return unsupported();
  }
  
  public int getWallpaperId(int paramInt) {
    return ((Integer)unsupported()).intValue();
  }
  
  public int getWallpaperIdForUser(int paramInt1, int paramInt2) {
    return ((Integer)unsupported()).intValue();
  }
  
  public WallpaperInfo getWallpaperInfo() {
    return unsupported();
  }
  
  public WallpaperInfo getWallpaperInfo(int paramInt) {
    return unsupported();
  }
  
  public boolean hasResourceWallpaper(int paramInt) {
    return unsupportedBoolean();
  }
  
  public boolean isSetWallpaperAllowed() {
    return false;
  }
  
  public boolean isWallpaperBackupEligible(int paramInt) {
    return unsupportedBoolean();
  }
  
  public boolean isWallpaperSupported() {
    return false;
  }
  
  public Drawable peekDrawable() {
    return unsupported();
  }
  
  public Drawable peekFastDrawable() {
    return unsupported();
  }
  
  public void removeOnColorsChangedListener(WallpaperManager.OnColorsChangedListener paramOnColorsChangedListener) {
    unsupported();
  }
  
  public void removeOnColorsChangedListener(WallpaperManager.OnColorsChangedListener paramOnColorsChangedListener, int paramInt) {
    unsupported();
  }
  
  public void sendWallpaperCommand(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle) {
    unsupported();
  }
  
  public int setBitmap(Bitmap paramBitmap, Rect paramRect, boolean paramBoolean) throws IOException {
    return ((Integer)unsupported()).intValue();
  }
  
  public int setBitmap(Bitmap paramBitmap, Rect paramRect, boolean paramBoolean, int paramInt) throws IOException {
    return ((Integer)unsupported()).intValue();
  }
  
  public int setBitmap(Bitmap paramBitmap, Rect paramRect, boolean paramBoolean, int paramInt1, int paramInt2) throws IOException {
    return ((Integer)unsupported()).intValue();
  }
  
  public void setBitmap(Bitmap paramBitmap) throws IOException {
    unsupported();
  }
  
  public void setDisplayOffset(IBinder paramIBinder, int paramInt1, int paramInt2) {
    unsupported();
  }
  
  public void setDisplayPadding(Rect paramRect) {
    unsupported();
  }
  
  public int setResource(int paramInt1, int paramInt2) throws IOException {
    return ((Integer)unsupported()).intValue();
  }
  
  public void setResource(int paramInt) throws IOException {
    unsupported();
  }
  
  public int setStream(InputStream paramInputStream, Rect paramRect, boolean paramBoolean) throws IOException {
    return ((Integer)unsupported()).intValue();
  }
  
  public int setStream(InputStream paramInputStream, Rect paramRect, boolean paramBoolean, int paramInt) throws IOException {
    return ((Integer)unsupported()).intValue();
  }
  
  public void setStream(InputStream paramInputStream) throws IOException {
    unsupported();
  }
  
  public boolean setWallpaperComponent(ComponentName paramComponentName) {
    return unsupportedBoolean();
  }
  
  public boolean setWallpaperComponent(ComponentName paramComponentName, int paramInt) {
    return unsupportedBoolean();
  }
  
  public void setWallpaperOffsetSteps(float paramFloat1, float paramFloat2) {
    unsupported();
  }
  
  public void setWallpaperOffsets(IBinder paramIBinder, float paramFloat1, float paramFloat2) {
    unsupported();
  }
  
  public void suggestDesiredDimensions(int paramInt1, int paramInt2) {
    unsupported();
  }
  
  public boolean wallpaperSupportsWcg(int paramInt) {
    return unsupportedBoolean();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DisabledWallpaperManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */