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
import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;

public class RotateFilter extends Filter {
  @GenerateFieldPort(name = "angle")
  private int mAngle;
  
  private int mHeight = 0;
  
  private int mOutputHeight;
  
  private int mOutputWidth;
  
  private Program mProgram;
  
  private int mTarget = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  private int mWidth = 0;
  
  public RotateFilter(String paramString) {
    super(paramString);
  }
  
  private void updateParameters() {
    int i = this.mAngle;
    if (i % 90 == 0) {
      float f2;
      float f1 = -1.0F;
      if (i % 180 == 0) {
        float f = 0.0F;
        if (i % 360 == 0)
          f1 = 1.0F; 
        f2 = f1;
        f1 = f;
      } else {
        if ((i + 90) % 360 != 0)
          f1 = 1.0F; 
        this.mOutputWidth = this.mHeight;
        this.mOutputHeight = this.mWidth;
        f2 = 0.0F;
      } 
      Quad quad = new Quad(new Point((-f2 + f1 + 1.0F) * 0.5F, (-f1 - f2 + 1.0F) * 0.5F), new Point((f2 + f1 + 1.0F) * 0.5F, (f1 - f2 + 1.0F) * 0.5F), new Point((-f2 - f1 + 1.0F) * 0.5F, (-f1 + f2 + 1.0F) * 0.5F), new Point((f2 - f1 + 1.0F) * 0.5F, (f1 + f2 + 1.0F) * 0.5F));
      ((ShaderProgram)this.mProgram).setTargetRegion(quad);
      return;
    } 
    throw new RuntimeException("degree has to be multiply of 90.");
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (this.mProgram != null)
      updateParameters(); 
  }
  
  public void initProgram(FilterContext paramFilterContext, int paramInt) {
    if (paramInt == 3) {
      ShaderProgram shaderProgram = ShaderProgram.createIdentity(paramFilterContext);
      shaderProgram.setMaximumTileSize(this.mTileSize);
      shaderProgram.setClearsOutput(true);
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
    Frame frame2 = pullInput("image");
    FrameFormat frameFormat = frame2.getFormat();
    if (this.mProgram == null || frameFormat.getTarget() != this.mTarget)
      initProgram(paramFilterContext, frameFormat.getTarget()); 
    if (frameFormat.getWidth() != this.mWidth || frameFormat.getHeight() != this.mHeight) {
      this.mWidth = frameFormat.getWidth();
      int i = frameFormat.getHeight();
      this.mHeight = i;
      this.mOutputWidth = this.mWidth;
      this.mOutputHeight = i;
      updateParameters();
    } 
    MutableFrameFormat mutableFrameFormat = ImageFormat.create(this.mOutputWidth, this.mOutputHeight, 3, 3);
    Frame frame1 = paramFilterContext.getFrameManager().newFrame((FrameFormat)mutableFrameFormat);
    this.mProgram.process(frame2, frame1);
    pushOutput("image", frame1);
    frame1.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/RotateFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */