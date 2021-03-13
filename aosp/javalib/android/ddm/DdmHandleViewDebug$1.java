package android.ddm;

import android.view.View;
import android.view.ViewDebug;

class null implements Runnable {
  public void run() {
    ViewDebug.outputDisplayList(rootView, targetView);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/ddm/DdmHandleViewDebug$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */