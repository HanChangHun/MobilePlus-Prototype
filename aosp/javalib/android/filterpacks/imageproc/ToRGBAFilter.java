package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.NativeProgram;
import android.filterfw.core.Program;

public class ToRGBAFilter extends Filter {
  private int mInputBPP;
  
  private FrameFormat mLastFormat = null;
  
  private Program mProgram;
  
  public ToRGBAFilter(String paramString) {
    super(paramString);
  }
  
  public void createProgram(FilterContext paramFilterContext, FrameFormat paramFrameFormat) {
    this.mInputBPP = paramFrameFormat.getBytesPerSample();
    FrameFormat frameFormat = this.mLastFormat;
    if (frameFormat != null && frameFormat.getBytesPerSample() == this.mInputBPP)
      return; 
    this.mLastFormat = paramFrameFormat;
    int i = this.mInputBPP;
    if (i != 1) {
      if (i == 3) {
        this.mProgram = (Program)new NativeProgram("filterpack_imageproc", "rgb_to_rgba");
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported BytesPerPixel: ");
        stringBuilder.append(this.mInputBPP);
        stringBuilder.append("!");
        throw new RuntimeException(stringBuilder.toString());
      } 
    } else {
      this.mProgram = (Program)new NativeProgram("filterpack_imageproc", "gray_to_rgba");
    } 
  }
  
  public FrameFormat getConvertedFormat(FrameFormat paramFrameFormat) {
    MutableFrameFormat mutableFrameFormat = paramFrameFormat.mutableCopy();
    mutableFrameFormat.setMetaValue("colorspace", Integer.valueOf(3));
    mutableFrameFormat.setBytesPerSample(4);
    return (FrameFormat)mutableFrameFormat;
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return getConvertedFormat(paramFrameFormat);
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame2 = pullInput("image");
    createProgram(paramFilterContext, frame2.getFormat());
    Frame frame1 = paramFilterContext.getFrameManager().newFrame(getConvertedFormat(frame2.getFormat()));
    this.mProgram.process(frame2, frame1);
    pushOutput("image", frame1);
    frame1.release();
  }
  
  public void setupPorts() {
    MutableFrameFormat mutableFrameFormat = new MutableFrameFormat(2, 2);
    mutableFrameFormat.setDimensionCount(2);
    addMaskedInputPort("image", (FrameFormat)mutableFrameFormat);
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/ToRGBAFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */