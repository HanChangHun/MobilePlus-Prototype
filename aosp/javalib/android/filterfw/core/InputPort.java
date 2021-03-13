package android.filterfw.core;

public abstract class InputPort extends FilterPort {
  protected OutputPort mSourcePort;
  
  public InputPort(Filter paramFilter, String paramString) {
    super(paramFilter, paramString);
  }
  
  public boolean acceptsFrame() {
    return hasFrame() ^ true;
  }
  
  public void close() {
    OutputPort outputPort = this.mSourcePort;
    if (outputPort != null && outputPort.isOpen())
      this.mSourcePort.close(); 
    super.close();
  }
  
  public boolean filterMustClose() {
    boolean bool;
    if (!isOpen() && isBlocking() && !hasFrame()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Filter getSourceFilter() {
    Filter filter;
    OutputPort outputPort = this.mSourcePort;
    if (outputPort == null) {
      outputPort = null;
    } else {
      filter = outputPort.getFilter();
    } 
    return filter;
  }
  
  public FrameFormat getSourceFormat() {
    FrameFormat frameFormat;
    OutputPort outputPort = this.mSourcePort;
    if (outputPort != null) {
      frameFormat = outputPort.getPortFormat();
    } else {
      frameFormat = getPortFormat();
    } 
    return frameFormat;
  }
  
  public OutputPort getSourcePort() {
    return this.mSourcePort;
  }
  
  public Object getTarget() {
    return null;
  }
  
  public boolean isConnected() {
    boolean bool;
    if (this.mSourcePort != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isReady() {
    return (hasFrame() || !isBlocking());
  }
  
  public void open() {
    super.open();
    OutputPort outputPort = this.mSourcePort;
    if (outputPort != null && !outputPort.isOpen())
      this.mSourcePort.open(); 
  }
  
  public void setSourcePort(OutputPort paramOutputPort) {
    if (this.mSourcePort == null) {
      this.mSourcePort = paramOutputPort;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this);
    stringBuilder.append(" already connected to ");
    stringBuilder.append(this.mSourcePort);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public abstract void transfer(FilterContext paramFilterContext);
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/InputPort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */