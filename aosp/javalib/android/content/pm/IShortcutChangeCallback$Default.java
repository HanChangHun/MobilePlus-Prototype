package android.content.pm;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;

public class Default implements IShortcutChangeCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onShortcutsAddedOrUpdated(String paramString, List<ShortcutInfo> paramList, UserHandle paramUserHandle) throws RemoteException {}
  
  public void onShortcutsRemoved(String paramString, List<ShortcutInfo> paramList, UserHandle paramUserHandle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IShortcutChangeCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */