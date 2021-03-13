package android.graphics;

import android.content.res.Resources;
import java.io.IOException;

public abstract class Source {
  private Source() {}
  
  final int computeDstDensity() {
    Resources resources = getResources();
    return (resources == null) ? Bitmap.getDefaultDensity() : (resources.getDisplayMetrics()).densityDpi;
  }
  
  abstract ImageDecoder createImageDecoder(boolean paramBoolean) throws IOException;
  
  int getDensity() {
    return 0;
  }
  
  Resources getResources() {
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$Source.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */