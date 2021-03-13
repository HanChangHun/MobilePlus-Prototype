package android.content;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IClipboard {
  public void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener paramIOnPrimaryClipChangedListener, String paramString, int paramInt) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void clearPrimaryClip(String paramString, int paramInt) throws RemoteException {}
  
  public ClipData getPrimaryClip(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public ClipDescription getPrimaryClipDescription(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public boolean hasClipboardText(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean hasPrimaryClip(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener paramIOnPrimaryClipChangedListener, String paramString, int paramInt) throws RemoteException {}
  
  public void setPrimaryClip(ClipData paramClipData, String paramString, int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IClipboard$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */