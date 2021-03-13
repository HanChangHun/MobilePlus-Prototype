package android.filterfw.core;

public class OutputPort extends FilterPort {
  protected InputPort mBasePort;
  
  protected InputPort mTargetPort;
  
  public OutputPort(Filter paramFilter, String paramString) {
    super(paramFilter, paramString);
  }
  
  public void clear() {
    InputPort inputPort = this.mTargetPort;
    if (inputPort != null)
      inputPort.clear(); 
  }
  
  public void close() {
    super.close();
    InputPort inputPort = this.mTargetPort;
    if (inputPort != null && inputPort.isOpen())
      this.mTargetPort.close(); 
  }
  
  public void connectTo(InputPort paramInputPort) {
    if (this.mTargetPort == null) {
      this.mTargetPort = paramInputPort;
      paramInputPort.setSourcePort(this);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this);
    stringBuilder.append(" already connected to ");
    stringBuilder.append(this.mTargetPort);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public boolean filterMustClose() {
    boolean bool;
    if (!isOpen() && isBlocking()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public InputPort getBasePort() {
    return this.mBasePort;
  }
  
  public Filter getTargetFilter() {
    Filter filter;
    InputPort inputPort = this.mTargetPort;
    if (inputPort == null) {
      inputPort = null;
    } else {
      filter = inputPort.getFilter();
    } 
    return filter;
  }
  
  public InputPort getTargetPort() {
    return this.mTargetPort;
  }
  
  public boolean hasFrame() {
    boolean bool;
    InputPort inputPort = this.mTargetPort;
    if (inputPort == null) {
      bool = false;
    } else {
      bool = inputPort.hasFrame();
    } 
    return bool;
  }
  
  public boolean isConnected() {
    boolean bool;
    if (this.mTargetPort != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isReady() {
    boolean bool;
    if ((isOpen() && this.mTargetPort.acceptsFrame()) || !isBlocking()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void open() {
    super.open();
    InputPort inputPort = this.mTargetPort;
    if (inputPort != null && !inputPort.isOpen())
      this.mTargetPort.open(); 
  }
  
  public Frame pullFrame() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Cannot pull frame on ");
    stringBuilder.append(this);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void pushFrame(Frame paramFrame) {
    InputPort inputPort = this.mTargetPort;
    if (inputPort != null) {
      inputPort.pushFrame(paramFrame);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Attempting to push frame on unconnected port: ");
    stringBuilder.append(this);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setBasePort(InputPort paramInputPort) {
    this.mBasePort = paramInputPort;
  }
  
  public void setFrame(Frame paramFrame) {
    assertPortIsOpen();
    InputPort inputPort = this.mTargetPort;
    if (inputPort != null) {
      inputPort.setFrame(paramFrame);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Attempting to set frame on unconnected port: ");
    stringBuilder.append(this);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("output ");
    stringBuilder.append(super.toString());
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/OutputPort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */