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

public class CropRectFilter extends Filter {
  private int mHeight = 0;
  
  @GenerateFieldPort(name = "height")
  private int mOutputHeight;
  
  @GenerateFieldPort(name = "width")
  private int mOutputWidth;
  
  private Program mProgram;
  
  private int mTarget = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  private int mWidth = 0;
  
  @GenerateFieldPort(name = "xorigin")
  private int mXorigin;
  
  @GenerateFieldPort(name = "yorigin")
  private int mYorigin;
  
  public CropRectFilter(String paramString) {
    super(paramString);
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (this.mProgram != null)
      updateSourceRect(this.mWidth, this.mHeight); 
  }
  
  public void initProgram(FilterContext paramFilterContext, int paramInt) {
    if (paramInt == 3) {
      ShaderProgram shaderProgram = ShaderProgram.createIdentity(paramFilterContext);
      shaderProgram.setMaximumTileSize(this.mTileSize);
      this.mProgram = (Program)shaderProgram;
      this.mTarget = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Filter Sharpen does not support frames of target ");
    stringBuilder.append(paramInt);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame1 = pullInput("image");
    FrameFormat frameFormat = frame1.getFormat();
    MutableFrameFormat mutableFrameFormat = ImageFormat.create(this.mOutputWidth, this.mOutputHeight, 3, 3);
    Frame frame2 = paramFilterContext.getFrameManager().newFrame((FrameFormat)mutableFrameFormat);
    if (this.mProgram == null || frameFormat.getTarget() != this.mTarget)
      initProgram(paramFilterContext, frameFormat.getTarget()); 
    if (frameFormat.getWidth() != this.mWidth || frameFormat.getHeight() != this.mHeight)
      updateSourceRect(frameFormat.getWidth(), frameFormat.getHeight()); 
    this.mProgram.process(frame1, frame2);
    pushOutput("image", frame2);
    frame2.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addOutputBasedOnInput("image", "image");
  }
  
  void updateSourceRect(int paramInt1, int paramInt2) {
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    ((ShaderProgram)this.mProgram).setSourceRect(this.mXorigin / paramInt1, this.mYorigin / paramInt2, this.mOutputWidth / paramInt1, this.mOutputHeight / paramInt2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/CropRectFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */