package android.hardware.display;

import android.view.Display;
import android.view.Surface;

public final class VirtualDisplay {
  private final Display mDisplay;
  
  private final DisplayManagerGlobal mGlobal;
  
  private Surface mSurface;
  
  private IVirtualDisplayCallback mToken;
  
  VirtualDisplay(DisplayManagerGlobal paramDisplayManagerGlobal, Display paramDisplay, IVirtualDisplayCallback paramIVirtualDisplayCallback, Surface paramSurface) {
    this.mGlobal = paramDisplayManagerGlobal;
    this.mDisplay = paramDisplay;
    this.mToken = paramIVirtualDisplayCallback;
    this.mSurface = paramSurface;
  }
  
  public Display getDisplay() {
    return this.mDisplay;
  }
  
  public Surface getSurface() {
    return this.mSurface;
  }
  
  public void release() {
    IVirtualDisplayCallback iVirtualDisplayCallback = this.mToken;
    if (iVirtualDisplayCallback != null) {
      this.mGlobal.releaseVirtualDisplay(iVirtualDisplayCallback);
      this.mToken = null;
    } 
  }
  
  public void resize(int paramInt1, int paramInt2, int paramInt3) {
    this.mGlobal.resizeVirtualDisplay(this.mToken, paramInt1, paramInt2, paramInt3);
  }
  
  public void setDisplayState(boolean paramBoolean) {
    IVirtualDisplayCallback iVirtualDisplayCallback = this.mToken;
    if (iVirtualDisplayCallback != null)
      this.mGlobal.setVirtualDisplayState(iVirtualDisplayCallback, paramBoolean); 
  }
  
  public void setSurface(Surface paramSurface) {
    if (this.mSurface != paramSurface) {
      this.mGlobal.setVirtualDisplaySurface(this.mToken, paramSurface);
      this.mSurface = paramSurface;
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("VirtualDisplay{display=");
    stringBuilder.append(this.mDisplay);
    stringBuilder.append(", token=");
    stringBuilder.append(this.mToken);
    stringBuilder.append(", surface=");
    stringBuilder.append(this.mSurface);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public static abstract class Callback {
    public void onPaused() {}
    
    public void onResumed() {}
    
    public void onStopped() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/VirtualDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */