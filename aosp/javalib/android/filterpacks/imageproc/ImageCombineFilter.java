package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.Program;
import android.filterfw.format.ImageFormat;
import java.lang.reflect.Field;

public abstract class ImageCombineFilter extends Filter {
  protected int mCurrentTarget = 0;
  
  protected String[] mInputNames;
  
  protected String mOutputName;
  
  protected String mParameterName;
  
  protected Program mProgram;
  
  public ImageCombineFilter(String paramString1, String[] paramArrayOfString, String paramString2, String paramString3) {
    super(paramString1);
    this.mInputNames = paramArrayOfString;
    this.mOutputName = paramString2;
    this.mParameterName = paramString3;
  }
  
  private void assertAllInputTargetsMatch() {
    String[] arrayOfString = this.mInputNames;
    byte b = 0;
    int i = getInputFormat(arrayOfString[0]).getTarget();
    arrayOfString = this.mInputNames;
    int j = arrayOfString.length;
    while (b < j) {
      if (i == getInputFormat(arrayOfString[b]).getTarget()) {
        b++;
        continue;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Type mismatch of input formats in filter ");
      stringBuilder.append(this);
      stringBuilder.append(". All input frames must have the same target!");
      throw new RuntimeException(stringBuilder.toString());
    } 
  }
  
  protected abstract Program getNativeProgram(FilterContext paramFilterContext);
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  protected abstract Program getShaderProgram(FilterContext paramFilterContext);
  
  public void process(FilterContext paramFilterContext) {
    byte b1 = 0;
    String[] arrayOfString = this.mInputNames;
    Frame[] arrayOfFrame = new Frame[arrayOfString.length];
    int i = arrayOfString.length;
    byte b2 = 0;
    while (b2 < i) {
      arrayOfFrame[b1] = pullInput(arrayOfString[b2]);
      b2++;
      b1++;
    } 
    Frame frame = paramFilterContext.getFrameManager().newFrame(arrayOfFrame[0].getFormat());
    updateProgramWithTarget(arrayOfFrame[0].getFormat().getTarget(), paramFilterContext);
    this.mProgram.process(arrayOfFrame, frame);
    pushOutput(this.mOutputName, frame);
    frame.release();
  }
  
  public void setupPorts() {
    if (this.mParameterName != null)
      try {
        Field field = ImageCombineFilter.class.getDeclaredField("mProgram");
        addProgramPort(this.mParameterName, this.mParameterName, field, float.class, false);
      } catch (NoSuchFieldException noSuchFieldException) {
        throw new RuntimeException("Internal Error: mProgram field not found!");
      }  
    String[] arrayOfString = this.mInputNames;
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++)
      addMaskedInputPort(arrayOfString[b], (FrameFormat)ImageFormat.create(3)); 
    addOutputBasedOnInput(this.mOutputName, this.mInputNames[0]);
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


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/ImageCombineFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */