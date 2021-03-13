package android.app;

import android.hardware.display.DisplayManager;

class null implements DisplayManager.DisplayListener {
  public void onDisplayAdded(int paramInt) {}
  
  public void onDisplayChanged(int paramInt) {
    if (paramInt == Presentation.access$000(Presentation.this).getDisplayId())
      Presentation.access$200(Presentation.this); 
  }
  
  public void onDisplayRemoved(int paramInt) {
    if (paramInt == Presentation.access$000(Presentation.this).getDisplayId())
      Presentation.access$100(Presentation.this); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Presentation$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */