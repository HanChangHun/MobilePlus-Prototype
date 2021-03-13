package android.graphics;

import android.content.res.AssetFileDescriptor;
import java.io.IOException;
import java.util.concurrent.Callable;

class CallableSource extends ImageDecoder.Source {
  private final Callable<AssetFileDescriptor> mCallable;
  
  CallableSource(Callable<AssetFileDescriptor> paramCallable) {
    this.mCallable = paramCallable;
  }
  
  public ImageDecoder createImageDecoder(boolean paramBoolean) throws IOException {
    try {
      AssetFileDescriptor assetFileDescriptor = this.mCallable.call();
      return ImageDecoder.access$400(assetFileDescriptor, paramBoolean, this);
    } catch (Exception exception) {
      if (exception instanceof IOException)
        throw (IOException)exception; 
      throw new IOException(exception);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$CallableSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */