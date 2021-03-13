package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.Program;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;
import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;

public class StraightenFilter extends Filter {
  private static final float DEGREE_TO_RADIAN = 0.017453292F;
  
  @GenerateFieldPort(hasDefault = true, name = "angle")
  private float mAngle = 0.0F;
  
  private int mHeight = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "maxAngle")
  private float mMaxAngle = 45.0F;
  
  private Program mProgram;
  
  private int mTarget = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  private int mWidth = 0;
  
  public StraightenFilter(String paramString) {
    super(paramString);
  }
  
  private void updateParameters() {
    float f1 = (float)Math.cos((this.mAngle * 0.017453292F));
    float f2 = (float)Math.sin((this.mAngle * 0.017453292F));
    float f3 = this.mMaxAngle;
    if (f3 > 0.0F) {
      float f = f3;
      if (f3 > 90.0F)
        f = 90.0F; 
      this.mMaxAngle = f;
      f3 = -f1;
      int i = this.mWidth;
      f = i;
      int j = this.mHeight;
      Point point1 = new Point(f3 * f + j * f2, -f2 * i - j * f1);
      i = this.mWidth;
      f = i;
      j = this.mHeight;
      Point point2 = new Point(f * f1 + j * f2, i * f2 - j * f1);
      f3 = -f1;
      j = this.mWidth;
      f = j;
      i = this.mHeight;
      Point point3 = new Point(f3 * f - i * f2, -f2 * j + i * f1);
      j = this.mWidth;
      f = j;
      i = this.mHeight;
      Point point4 = new Point(f * f1 - i * f2, j * f2 + i * f1);
      f3 = Math.max(Math.abs(point1.x), Math.abs(point2.x));
      f = Math.max(Math.abs(point1.y), Math.abs(point2.y));
      f = Math.min(this.mWidth / f3, this.mHeight / f) * 0.5F;
      point1.set(point1.x * f / this.mWidth + 0.5F, point1.y * f / this.mHeight + 0.5F);
      point2.set(point2.x * f / this.mWidth + 0.5F, point2.y * f / this.mHeight + 0.5F);
      point3.set(point3.x * f / this.mWidth + 0.5F, point3.y * f / this.mHeight + 0.5F);
      point4.set(point4.x * f / this.mWidth + 0.5F, point4.y * f / this.mHeight + 0.5F);
      Quad quad = new Quad(point1, point2, point3, point4);
      ((ShaderProgram)this.mProgram).setSourceRegion(quad);
      return;
    } 
    throw new RuntimeException("Max angle is out of range (0-180).");
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (this.mProgram != null)
      updateParameters(); 
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
    Frame frame2 = pullInput("image");
    FrameFormat frameFormat = frame2.getFormat();
    if (this.mProgram == null || frameFormat.getTarget() != this.mTarget)
      initProgram(paramFilterContext, frameFormat.getTarget()); 
    if (frameFormat.getWidth() != this.mWidth || frameFormat.getHeight() != this.mHeight) {
      this.mWidth = frameFormat.getWidth();
      this.mHeight = frameFormat.getHeight();
      updateParameters();
    } 
    Frame frame1 = paramFilterContext.getFrameManager().newFrame(frameFormat);
    this.mProgram.process(frame2, frame1);
    pushOutput("image", frame1);
    frame1.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/StraightenFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */