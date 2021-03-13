package android.graphics;

import android.util.Size;

public class ImageInfo {
  private ImageDecoder mDecoder;
  
  private final Size mSize;
  
  private ImageInfo(ImageDecoder paramImageDecoder) {
    this.mSize = new Size(ImageDecoder.access$700(paramImageDecoder), ImageDecoder.access$800(paramImageDecoder));
    this.mDecoder = paramImageDecoder;
  }
  
  public ColorSpace getColorSpace() {
    return ImageDecoder.access$1100(this.mDecoder);
  }
  
  public String getMimeType() {
    return ImageDecoder.access$900(this.mDecoder);
  }
  
  public Size getSize() {
    return this.mSize;
  }
  
  public boolean isAnimated() {
    return ImageDecoder.access$1000(this.mDecoder);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$ImageInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */