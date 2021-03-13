package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;
import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;

public class FixedRotationFilter extends Filter {
  private ShaderProgram mProgram = null;
  
  @GenerateFieldPort(hasDefault = true, name = "rotation")
  private int mRotation = 0;
  
  public FixedRotationFilter(String paramString) {
    super(paramString);
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void process(FilterContext paramFilterContext) {
    Quad quad;
    Frame frame2 = pullInput("image");
    if (this.mRotation == 0) {
      pushOutput("image", frame2);
      return;
    } 
    FrameFormat frameFormat = frame2.getFormat();
    if (this.mProgram == null)
      this.mProgram = ShaderProgram.createIdentity(paramFilterContext); 
    MutableFrameFormat mutableFrameFormat = frameFormat.mutableCopy();
    int i = frameFormat.getWidth();
    int j = frameFormat.getHeight();
    Point point2 = new Point(0.0F, 0.0F);
    Point point3 = new Point(1.0F, 0.0F);
    Point point1 = new Point(0.0F, 1.0F);
    Point point4 = new Point(1.0F, 1.0F);
    int k = Math.round(this.mRotation / 90.0F) % 4;
    if (k != 1) {
      if (k != 2) {
        if (k != 3) {
          quad = new Quad(point2, point3, point1, point4);
        } else {
          quad = new Quad(point3, point4, point2, (Point)quad);
          mutableFrameFormat.setDimensions(j, i);
        } 
      } else {
        quad = new Quad(point4, (Point)quad, point3, point2);
      } 
    } else {
      quad = new Quad((Point)quad, point2, point4, point3);
      mutableFrameFormat.setDimensions(j, i);
    } 
    Frame frame1 = paramFilterContext.getFrameManager().newFrame((FrameFormat)mutableFrameFormat);
    this.mProgram.setSourceRegion(quad);
    this.mProgram.process(frame2, frame1);
    pushOutput("image", frame1);
    frame1.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3, 3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/FixedRotationFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */