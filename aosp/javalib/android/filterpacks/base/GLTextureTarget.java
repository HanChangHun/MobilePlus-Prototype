package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.format.ImageFormat;

public class GLTextureTarget extends Filter {
  @GenerateFieldPort(name = "texId")
  private int mTexId;
  
  public GLTextureTarget(String paramString) {
    super(paramString);
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame2 = pullInput("frame");
    MutableFrameFormat mutableFrameFormat = ImageFormat.create(frame2.getFormat().getWidth(), frame2.getFormat().getHeight(), 3, 3);
    Frame frame1 = paramFilterContext.getFrameManager().newBoundFrame((FrameFormat)mutableFrameFormat, 100, this.mTexId);
    frame1.setDataFromFrame(frame2);
    frame1.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("frame", (FrameFormat)ImageFormat.create(3));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/GLTextureTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */