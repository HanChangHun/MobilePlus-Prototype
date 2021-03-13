package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.GenerateFinalPort;
import android.filterfw.format.ObjectFormat;

public class ObjectSource extends Filter {
  private Frame mFrame;
  
  @GenerateFieldPort(name = "object")
  private Object mObject;
  
  @GenerateFinalPort(hasDefault = true, name = "format")
  private FrameFormat mOutputFormat = FrameFormat.unspecified();
  
  @GenerateFieldPort(hasDefault = true, name = "repeatFrame")
  boolean mRepeatFrame = false;
  
  public ObjectSource(String paramString) {
    super(paramString);
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (paramString.equals("object")) {
      Frame frame = this.mFrame;
      if (frame != null) {
        frame.release();
        this.mFrame = null;
      } 
    } 
  }
  
  public void process(FilterContext paramFilterContext) {
    if (this.mFrame == null) {
      Object object = this.mObject;
      if (object != null) {
        object = ObjectFormat.fromObject(object, 1);
        Frame frame = paramFilterContext.getFrameManager().newFrame((FrameFormat)object);
        this.mFrame = frame;
        frame.setObjectValue(this.mObject);
        this.mFrame.setTimestamp(-1L);
      } else {
        throw new NullPointerException("ObjectSource producing frame with no object set!");
      } 
    } 
    pushOutput("frame", this.mFrame);
    if (!this.mRepeatFrame)
      closeOutputPort("frame"); 
  }
  
  public void setupPorts() {
    addOutputPort("frame", this.mOutputFormat);
  }
  
  public void tearDown(FilterContext paramFilterContext) {
    this.mFrame.release();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/ObjectSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */