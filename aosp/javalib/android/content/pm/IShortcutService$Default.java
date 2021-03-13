package android.content.pm;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IShortcutService {
  public boolean addDynamicShortcuts(String paramString, ParceledListSlice paramParceledListSlice, int paramInt) throws RemoteException {
    return false;
  }
  
  public void applyRestore(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public Intent createShortcutResultIntent(String paramString, ShortcutInfo paramShortcutInfo, int paramInt) throws RemoteException {
    return null;
  }
  
  public void disableShortcuts(String paramString, List paramList, CharSequence paramCharSequence, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void enableShortcuts(String paramString, List paramList, int paramInt) throws RemoteException {}
  
  public byte[] getBackupPayload(int paramInt) throws RemoteException {
    return null;
  }
  
  public int getIconMaxDimensions(String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public int getMaxShortcutCountPerActivity(String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public long getRateLimitResetTime(String paramString, int paramInt) throws RemoteException {
    return 0L;
  }
  
  public int getRemainingCallCount(String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public ParceledListSlice getShareTargets(String paramString, IntentFilter paramIntentFilter, int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getShortcuts(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public boolean hasShareTargets(String paramString1, String paramString2, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isRequestPinItemSupported(int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public void onApplicationActive(String paramString, int paramInt) throws RemoteException {}
  
  public void pushDynamicShortcut(String paramString, ShortcutInfo paramShortcutInfo, int paramInt) throws RemoteException {}
  
  public void removeAllDynamicShortcuts(String paramString, int paramInt) throws RemoteException {}
  
  public void removeDynamicShortcuts(String paramString, List paramList, int paramInt) throws RemoteException {}
  
  public void removeLongLivedShortcuts(String paramString, List paramList, int paramInt) throws RemoteException {}
  
  public void reportShortcutUsed(String paramString1, String paramString2, int paramInt) throws RemoteException {}
  
  public boolean requestPinShortcut(String paramString, ShortcutInfo paramShortcutInfo, IntentSender paramIntentSender, int paramInt) throws RemoteException {
    return false;
  }
  
  public void resetThrottling() throws RemoteException {}
  
  public boolean setDynamicShortcuts(String paramString, ParceledListSlice paramParceledListSlice, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean updateShortcuts(String paramString, ParceledListSlice paramParceledListSlice, int paramInt) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IShortcutService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */