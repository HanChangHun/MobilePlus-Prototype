package android.app;

import android.content.Context;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.util.Log;
import android.view.Display;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ColorManagementProxy {
  private final Set<ColorSpace> mSupportedColorSpaces = new HashSet<>();
  
  public ColorManagementProxy(Context paramContext) {
    Display display = paramContext.getDisplayNoVerify();
    if (display != null)
      this.mSupportedColorSpaces.addAll(Arrays.asList(display.getSupportedWideColorGamut())); 
  }
  
  void doColorManagement(ImageDecoder paramImageDecoder, ImageDecoder.ImageInfo paramImageInfo) {
    if (!isSupportedColorSpace(paramImageInfo.getColorSpace())) {
      paramImageDecoder.setTargetColorSpace(ColorSpace.get(ColorSpace.Named.SRGB));
      String str = WallpaperManager.access$000();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Not supported color space: ");
      stringBuilder.append(paramImageInfo.getColorSpace());
      Log.w(str, stringBuilder.toString());
    } 
  }
  
  public Set<ColorSpace> getSupportedColorSpaces() {
    return this.mSupportedColorSpaces;
  }
  
  boolean isSupportedColorSpace(ColorSpace paramColorSpace) {
    boolean bool;
    if (paramColorSpace != null && (paramColorSpace == ColorSpace.get(ColorSpace.Named.SRGB) || getSupportedColorSpaces().contains(paramColorSpace))) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WallpaperManager$ColorManagementProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */