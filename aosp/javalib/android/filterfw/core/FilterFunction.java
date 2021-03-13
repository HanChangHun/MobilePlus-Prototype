package android.filterfw.core;

import java.util.Map;

public class FilterFunction {
  private Filter mFilter;
  
  private FilterContext mFilterContext;
  
  private boolean mFilterIsSetup = false;
  
  private FrameHolderPort[] mResultHolders;
  
  public FilterFunction(FilterContext paramFilterContext, Filter paramFilter) {
    this.mFilterContext = paramFilterContext;
    this.mFilter = paramFilter;
  }
  
  private void connectFilterOutputs() {
    byte b = 0;
    this.mResultHolders = new FrameHolderPort[this.mFilter.getNumberOfOutputs()];
    for (OutputPort outputPort : this.mFilter.getOutputPorts()) {
      this.mResultHolders[b] = new FrameHolderPort();
      outputPort.connectTo(this.mResultHolders[b]);
      b++;
    } 
  }
  
  public void close() {
    this.mFilter.performClose(this.mFilterContext);
  }
  
  public Frame execute(KeyValueMap paramKeyValueMap) {
    int i = this.mFilter.getNumberOfOutputs();
    if (i <= 1) {
      Frame frame;
      if (!this.mFilterIsSetup) {
        connectFilterOutputs();
        this.mFilterIsSetup = true;
      } 
      boolean bool1 = false;
      GLEnvironment gLEnvironment = this.mFilterContext.getGLEnvironment();
      boolean bool2 = bool1;
      if (gLEnvironment != null) {
        bool2 = bool1;
        if (!gLEnvironment.isActive()) {
          gLEnvironment.activate();
          bool2 = true;
        } 
      } 
      for (Map.Entry<String, Object> entry : paramKeyValueMap.entrySet()) {
        if (entry.getValue() instanceof Frame) {
          this.mFilter.pushInputFrame((String)entry.getKey(), (Frame)entry.getValue());
          continue;
        } 
        this.mFilter.pushInputValue((String)entry.getKey(), entry.getValue());
      } 
      if (this.mFilter.getStatus() != 3)
        this.mFilter.openOutputs(); 
      this.mFilter.performProcess(this.mFilterContext);
      KeyValueMap keyValueMap = null;
      paramKeyValueMap = keyValueMap;
      if (i == 1) {
        paramKeyValueMap = keyValueMap;
        if (this.mResultHolders[0].hasFrame())
          frame = this.mResultHolders[0].pullFrame(); 
      } 
      if (bool2)
        gLEnvironment.deactivate(); 
      return frame;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Calling execute on filter ");
    stringBuilder.append(this.mFilter);
    stringBuilder.append(" with multiple outputs! Use executeMulti() instead!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public Frame executeWithArgList(Object... paramVarArgs) {
    return execute(KeyValueMap.fromKeyValues(paramVarArgs));
  }
  
  public FilterContext getContext() {
    return this.mFilterContext;
  }
  
  public Filter getFilter() {
    return this.mFilter;
  }
  
  public void setInputFrame(String paramString, Frame paramFrame) {
    this.mFilter.setInputFrame(paramString, paramFrame);
  }
  
  public void setInputValue(String paramString, Object paramObject) {
    this.mFilter.setInputValue(paramString, paramObject);
  }
  
  public void tearDown() {
    this.mFilter.performTearDown(this.mFilterContext);
    this.mFilter = null;
  }
  
  public String toString() {
    return this.mFilter.getName();
  }
  
  private class FrameHolderPort extends StreamPort {
    public FrameHolderPort() {
      super(null, "holder");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/FilterFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */