package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GLFrame;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.Program;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;

public class ResizeFilter extends Filter {
  @GenerateFieldPort(hasDefault = true, name = "generateMipMap")
  private boolean mGenerateMipMap = false;
  
  private int mInputChannels;
  
  @GenerateFieldPort(hasDefault = true, name = "keepAspectRatio")
  private boolean mKeepAspectRatio = false;
  
  private FrameFormat mLastFormat = null;
  
  @GenerateFieldPort(name = "oheight")
  private int mOHeight;
  
  @GenerateFieldPort(name = "owidth")
  private int mOWidth;
  
  private MutableFrameFormat mOutputFormat;
  
  private Program mProgram;
  
  public ResizeFilter(String paramString) {
    super(paramString);
  }
  
  protected void createProgram(FilterContext paramFilterContext, FrameFormat paramFrameFormat) {
    FrameFormat frameFormat = this.mLastFormat;
    if (frameFormat != null && frameFormat.getTarget() == paramFrameFormat.getTarget())
      return; 
    this.mLastFormat = paramFrameFormat;
    int i = paramFrameFormat.getTarget();
    if (i != 2) {
      if (i == 3) {
        this.mProgram = (Program)ShaderProgram.createIdentity(paramFilterContext);
        return;
      } 
      throw new RuntimeException("ResizeFilter could not create suitable program!");
    } 
    throw new RuntimeException("Native ResizeFilter not implemented yet!");
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame1 = pullInput("image");
    createProgram(paramFilterContext, frame1.getFormat());
    MutableFrameFormat mutableFrameFormat = frame1.getFormat().mutableCopy();
    if (this.mKeepAspectRatio) {
      FrameFormat frameFormat = frame1.getFormat();
      this.mOHeight = this.mOWidth * frameFormat.getHeight() / frameFormat.getWidth();
    } 
    mutableFrameFormat.setDimensions(this.mOWidth, this.mOHeight);
    Frame frame2 = paramFilterContext.getFrameManager().newFrame((FrameFormat)mutableFrameFormat);
    if (this.mGenerateMipMap) {
      GLFrame gLFrame = (GLFrame)paramFilterContext.getFrameManager().newFrame(frame1.getFormat());
      gLFrame.setTextureParameter(10241, 9985);
      gLFrame.setDataFromFrame(frame1);
      gLFrame.generateMipMap();
      this.mProgram.process((Frame)gLFrame, frame2);
      gLFrame.release();
    } else {
      this.mProgram.process(frame1, frame2);
    } 
    pushOutput("image", frame2);
    frame2.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/ResizeFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */