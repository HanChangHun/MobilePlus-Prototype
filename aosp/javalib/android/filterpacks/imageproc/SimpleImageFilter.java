package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.Program;
import android.filterfw.format.ImageFormat;
import java.lang.reflect.Field;

public abstract class SimpleImageFilter extends Filter {
  protected int mCurrentTarget = 0;
  
  protected String mParameterName;
  
  protected Program mProgram;
  
  public SimpleImageFilter(String paramString1, String paramString2) {
    super(paramString1);
    this.mParameterName = paramString2;
  }
  
  protected abstract Program getNativeProgram(FilterContext paramFilterContext);
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  protected abstract Program getShaderProgram(FilterContext paramFilterContext);
  
  public void process(FilterContext paramFilterContext) {
    Frame frame1 = pullInput("image");
    FrameFormat frameFormat = frame1.getFormat();
    Frame frame2 = paramFilterContext.getFrameManager().newFrame(frameFormat);
    updateProgramWithTarget(frameFormat.getTarget(), paramFilterContext);
    this.mProgram.process(frame1, frame2);
    pushOutput("image", frame2);
    frame2.release();
  }
  
  public void setupPorts() {
    if (this.mParameterName != null)
      try {
        Field field = SimpleImageFilter.class.getDeclaredField("mProgram");
        addProgramPort(this.mParameterName, this.mParameterName, field, float.class, false);
      } catch (NoSuchFieldException noSuchFieldException) {
        throw new RuntimeException("Internal Error: mProgram field not found!");
      }  
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addOutputBasedOnInput("image", "image");
  }
  
  protected void updateProgramWithTarget(int paramInt, FilterContext paramFilterContext) {
    if (paramInt != this.mCurrentTarget) {
      if (paramInt != 2) {
        if (paramInt != 3) {
          this.mProgram = null;
        } else {
          this.mProgram = getShaderProgram(paramFilterContext);
        } 
      } else {
        this.mProgram = getNativeProgram(paramFilterContext);
      } 
      Program program = this.mProgram;
      if (program != null) {
        initProgramInputs(program, paramFilterContext);
        this.mCurrentTarget = paramInt;
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not create a program for image filter ");
        stringBuilder.append(this);
        stringBuilder.append("!");
        throw new RuntimeException(stringBuilder.toString());
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/SimpleImageFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */