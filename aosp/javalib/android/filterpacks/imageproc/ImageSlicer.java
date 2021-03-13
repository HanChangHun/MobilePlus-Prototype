package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.Program;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;

public class ImageSlicer extends Filter {
  private int mInputHeight;
  
  private int mInputWidth;
  
  private Frame mOriginalFrame;
  
  private int mOutputHeight;
  
  private int mOutputWidth;
  
  @GenerateFieldPort(name = "padSize")
  private int mPadSize;
  
  private Program mProgram;
  
  private int mSliceHeight;
  
  private int mSliceIndex = 0;
  
  private int mSliceWidth;
  
  @GenerateFieldPort(name = "xSlices")
  private int mXSlices;
  
  @GenerateFieldPort(name = "ySlices")
  private int mYSlices;
  
  public ImageSlicer(String paramString) {
    super(paramString);
  }
  
  private void calcOutputFormatForInput(Frame paramFrame) {
    this.mInputWidth = paramFrame.getFormat().getWidth();
    int i = paramFrame.getFormat().getHeight();
    this.mInputHeight = i;
    int j = this.mInputWidth;
    int k = this.mXSlices;
    k = (j + k - 1) / k;
    this.mSliceWidth = k;
    j = this.mYSlices;
    i = (i + j - 1) / j;
    this.mSliceHeight = i;
    j = this.mPadSize;
    this.mOutputWidth = k + j * 2;
    this.mOutputHeight = i + j * 2;
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void process(FilterContext paramFilterContext) {
    if (this.mSliceIndex == 0) {
      Frame frame1 = pullInput("image");
      this.mOriginalFrame = frame1;
      calcOutputFormatForInput(frame1);
    } 
    MutableFrameFormat mutableFrameFormat = this.mOriginalFrame.getFormat().mutableCopy();
    mutableFrameFormat.setDimensions(this.mOutputWidth, this.mOutputHeight);
    Frame frame = paramFilterContext.getFrameManager().newFrame((FrameFormat)mutableFrameFormat);
    if (this.mProgram == null)
      this.mProgram = (Program)ShaderProgram.createIdentity(paramFilterContext); 
    int i = this.mSliceIndex;
    int j = this.mXSlices;
    int k = i / j;
    int m = this.mSliceWidth;
    int n = this.mPadSize;
    float f1 = (m * i % j - n);
    i = this.mInputWidth;
    f1 /= i;
    float f2 = (this.mSliceHeight * k - n);
    n = this.mInputHeight;
    f2 /= n;
    ((ShaderProgram)this.mProgram).setSourceRect(f1, f2, this.mOutputWidth / i, this.mOutputHeight / n);
    this.mProgram.process(this.mOriginalFrame, frame);
    n = this.mSliceIndex + 1;
    this.mSliceIndex = n;
    if (n == this.mXSlices * this.mYSlices) {
      this.mSliceIndex = 0;
      this.mOriginalFrame.release();
      setWaitsOnInputPort("image", true);
    } else {
      this.mOriginalFrame.retain();
      setWaitsOnInputPort("image", false);
    } 
    pushOutput("image", frame);
    frame.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3, 3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/ImageSlicer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */