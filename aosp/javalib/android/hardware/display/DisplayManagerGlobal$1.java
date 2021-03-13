package android.hardware.display;

import android.app.PropertyInvalidatedCache;
import android.os.RemoteException;
import android.view.DisplayInfo;

class null extends PropertyInvalidatedCache<Integer, DisplayInfo> {
  null(int paramInt, String paramString) {
    super(paramInt, paramString);
  }
  
  protected DisplayInfo recompute(Integer paramInteger) {
    try {
      return DisplayManagerGlobal.access$000(DisplayManagerGlobal.this).getDisplayInfo(paramInteger.intValue());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayManagerGlobal$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */