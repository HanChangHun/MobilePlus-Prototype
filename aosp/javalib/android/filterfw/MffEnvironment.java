package android.filterfw;

import android.filterfw.core.CachedFrameManager;
import android.filterfw.core.FilterContext;
import android.filterfw.core.FrameManager;
import android.filterfw.core.GLEnvironment;

public class MffEnvironment {
  private FilterContext mContext;
  
  protected MffEnvironment(FrameManager paramFrameManager) {
    CachedFrameManager cachedFrameManager;
    FrameManager frameManager = paramFrameManager;
    if (paramFrameManager == null)
      cachedFrameManager = new CachedFrameManager(); 
    FilterContext filterContext = new FilterContext();
    this.mContext = filterContext;
    filterContext.setFrameManager((FrameManager)cachedFrameManager);
  }
  
  public void activateGLEnvironment() {
    if (this.mContext.getGLEnvironment() != null) {
      this.mContext.getGLEnvironment().activate();
      return;
    } 
    throw new NullPointerException("No GLEnvironment in place to activate!");
  }
  
  public void createGLEnvironment() {
    GLEnvironment gLEnvironment = new GLEnvironment();
    gLEnvironment.initWithNewContext();
    setGLEnvironment(gLEnvironment);
  }
  
  public void deactivateGLEnvironment() {
    if (this.mContext.getGLEnvironment() != null) {
      this.mContext.getGLEnvironment().deactivate();
      return;
    } 
    throw new NullPointerException("No GLEnvironment in place to deactivate!");
  }
  
  public FilterContext getContext() {
    return this.mContext;
  }
  
  public void setGLEnvironment(GLEnvironment paramGLEnvironment) {
    this.mContext.initGLEnvironment(paramGLEnvironment);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/MffEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */