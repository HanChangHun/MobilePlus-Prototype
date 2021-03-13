package android.content.pm;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IDexModuleRegisterCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onDexModuleRegistered(String paramString1, boolean paramBoolean, String paramString2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDexModuleRegisterCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */