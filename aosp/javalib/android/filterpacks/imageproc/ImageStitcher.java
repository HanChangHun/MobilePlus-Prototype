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

public class ImageStitcher extends Filter {
  private int mImageHeight;
  
  private int mImageWidth;
  
  private int mInputHeight;
  
  private int mInputWidth;
  
  private Frame mOutputFrame;
  
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
  
  public ImageStitcher(String paramString) {
    super(paramString);
  }
  
  private FrameFormat calcOutputFormatForInput(FrameFormat paramFrameFormat) {
    MutableFrameFormat mutableFrameFormat = paramFrameFormat.mutableCopy();
    this.mInputWidth = paramFrameFormat.getWidth();
    int i = paramFrameFormat.getHeight();
    this.mInputHeight = i;
    int j = this.mInputWidth;
    int k = this.mPadSize;
    j -= k * 2;
    this.mSliceWidth = j;
    k = i - k * 2;
    this.mSliceHeight = k;
    i = j * this.mXSlices;
    this.mImageWidth = i;
    j = k * this.mYSlices;
    this.mImageHeight = j;
    mutableFrameFormat.setDimensions(i, j);
    return (FrameFormat)mutableFrameFormat;
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame = pullInput("image");
    FrameFormat frameFormat = frame.getFormat();
    if (this.mSliceIndex == 0) {
      this.mOutputFrame = paramFilterContext.getFrameManager().newFrame(calcOutputFormatForInput(frameFormat));
    } else if (frameFormat.getWidth() != this.mInputWidth || frameFormat.getHeight() != this.mInputHeight) {
      throw new RuntimeException("Image size should not change.");
    } 
    if (this.mProgram == null)
      this.mProgram = (Program)ShaderProgram.createIdentity(paramFilterContext); 
    int i = this.mPadSize;
    float f1 = i / this.mInputWidth;
    float f2 = i / this.mInputHeight;
    int j = this.mSliceIndex;
    int k = this.mXSlices;
    i = this.mSliceWidth;
    int m = j % k * i;
    j = j / k * this.mSliceHeight;
    float f3 = Math.min(i, this.mImageWidth - m);
    float f4 = Math.min(this.mSliceHeight, this.mImageHeight - j);
    ((ShaderProgram)this.mProgram).setSourceRect(f1, f2, f3 / this.mInputWidth, f4 / this.mInputHeight);
    ShaderProgram shaderProgram = (ShaderProgram)this.mProgram;
    f1 = m;
    i = this.mImageWidth;
    f2 = f1 / i;
    f1 = j;
    m = this.mImageHeight;
    shaderProgram.setTargetRect(f2, f1 / m, f3 / i, f4 / m);
    this.mProgram.process(frame, this.mOutputFrame);
    i = this.mSliceIndex + 1;
    this.mSliceIndex = i;
    if (i == this.mXSlices * this.mYSlices) {
      pushOutput("image", this.mOutputFrame);
      this.mOutputFrame.release();
      this.mSliceIndex = 0;
    } 
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3, 3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/ImageStitcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */