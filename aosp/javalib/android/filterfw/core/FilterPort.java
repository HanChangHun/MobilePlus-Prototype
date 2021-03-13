package android.filterfw.core;

import android.util.Log;

public abstract class FilterPort {
  private static final String TAG = "FilterPort";
  
  protected boolean mChecksType = false;
  
  protected Filter mFilter;
  
  protected boolean mIsBlocking = true;
  
  protected boolean mIsOpen = false;
  
  private boolean mLogVerbose;
  
  protected String mName;
  
  protected FrameFormat mPortFormat;
  
  public FilterPort(Filter paramFilter, String paramString) {
    this.mName = paramString;
    this.mFilter = paramFilter;
    this.mLogVerbose = Log.isLoggable("FilterPort", 2);
  }
  
  protected void assertPortIsOpen() {
    if (isOpen())
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Illegal operation on closed ");
    stringBuilder.append(this);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  protected void checkFrameManager(Frame paramFrame, FilterContext paramFilterContext) {
    if (paramFrame.getFrameManager() == null || paramFrame.getFrameManager() == paramFilterContext.getFrameManager())
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Frame ");
    stringBuilder.append(paramFrame);
    stringBuilder.append(" is managed by foreign FrameManager! ");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  protected void checkFrameType(Frame paramFrame, boolean paramBoolean) {
    if ((!this.mChecksType && !paramBoolean) || this.mPortFormat == null || paramFrame.getFormat().isCompatibleWith(this.mPortFormat))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Frame passed to ");
    stringBuilder.append(this);
    stringBuilder.append(" is of incorrect type! Expected ");
    stringBuilder.append(this.mPortFormat);
    stringBuilder.append(" but got ");
    stringBuilder.append(paramFrame.getFormat());
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public abstract void clear();
  
  public void close() {
    if (this.mIsOpen && this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Closing ");
      stringBuilder.append(this);
      Log.v("FilterPort", stringBuilder.toString());
    } 
    this.mIsOpen = false;
  }
  
  public abstract boolean filterMustClose();
  
  public Filter getFilter() {
    return this.mFilter;
  }
  
  public String getName() {
    return this.mName;
  }
  
  public FrameFormat getPortFormat() {
    return this.mPortFormat;
  }
  
  public abstract boolean hasFrame();
  
  public boolean isAttached() {
    boolean bool;
    if (this.mFilter != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isBlocking() {
    return this.mIsBlocking;
  }
  
  public boolean isOpen() {
    return this.mIsOpen;
  }
  
  public abstract boolean isReady();
  
  public void open() {
    if (!this.mIsOpen && this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Opening ");
      stringBuilder.append(this);
      Log.v("FilterPort", stringBuilder.toString());
    } 
    this.mIsOpen = true;
  }
  
  public abstract Frame pullFrame();
  
  public abstract void pushFrame(Frame paramFrame);
  
  public void setBlocking(boolean paramBoolean) {
    this.mIsBlocking = paramBoolean;
  }
  
  public void setChecksType(boolean paramBoolean) {
    this.mChecksType = paramBoolean;
  }
  
  public abstract void setFrame(Frame paramFrame);
  
  public void setPortFormat(FrameFormat paramFrameFormat) {
    this.mPortFormat = paramFrameFormat;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("port '");
    stringBuilder.append(this.mName);
    stringBuilder.append("' of ");
    stringBuilder.append(this.mFilter);
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/FilterPort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */