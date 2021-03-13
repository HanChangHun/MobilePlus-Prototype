package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.format.ImageFormat;
import android.graphics.Bitmap;
import java.io.OutputStream;

public class ImageEncoder extends Filter {
  @GenerateFieldPort(name = "stream")
  private OutputStream mOutputStream;
  
  @GenerateFieldPort(hasDefault = true, name = "quality")
  private int mQuality = 80;
  
  public ImageEncoder(String paramString) {
    super(paramString);
  }
  
  public void process(FilterContext paramFilterContext) {
    pullInput("image").getBitmap().compress(Bitmap.CompressFormat.JPEG, this.mQuality, this.mOutputStream);
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3, 0));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/ImageEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */