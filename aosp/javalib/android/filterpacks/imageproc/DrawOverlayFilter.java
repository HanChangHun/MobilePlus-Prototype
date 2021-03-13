package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;
import android.filterfw.format.ObjectFormat;
import android.filterfw.geometry.Quad;

public class DrawOverlayFilter extends Filter {
  private ShaderProgram mProgram;
  
  public DrawOverlayFilter(String paramString) {
    super(paramString);
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void prepare(FilterContext paramFilterContext) {
    this.mProgram = ShaderProgram.createIdentity(paramFilterContext);
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame2 = pullInput("source");
    Frame frame3 = pullInput("overlay");
    Quad quad = ((Quad)pullInput("box").getObjectValue()).translated(1.0F, 1.0F).scaled(2.0F);
    this.mProgram.setTargetRegion(quad);
    Frame frame1 = paramFilterContext.getFrameManager().newFrame(frame2.getFormat());
    frame1.setDataFromFrame(frame2);
    this.mProgram.process(frame3, frame1);
    pushOutput("image", frame1);
    frame1.release();
  }
  
  public void setupPorts() {
    MutableFrameFormat mutableFrameFormat = ImageFormat.create(3, 3);
    addMaskedInputPort("source", (FrameFormat)mutableFrameFormat);
    addMaskedInputPort("overlay", (FrameFormat)mutableFrameFormat);
    addMaskedInputPort("box", (FrameFormat)ObjectFormat.fromClass(Quad.class, 1));
    addOutputBasedOnInput("image", "source");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/DrawOverlayFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */