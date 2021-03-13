package android.graphics;

import java.io.File;
import java.io.IOException;

class FileSource extends ImageDecoder.Source {
  private final File mFile;
  
  FileSource(File paramFile) {
    this.mFile = paramFile;
  }
  
  public ImageDecoder createImageDecoder(boolean paramBoolean) throws IOException {
    return ImageDecoder.access$600(this.mFile, paramBoolean, this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$FileSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */