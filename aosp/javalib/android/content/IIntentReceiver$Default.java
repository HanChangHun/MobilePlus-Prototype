package android.content;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IIntentReceiver {
  public IBinder asBinder() {
    return null;
  }
  
  public void performReceive(Intent paramIntent, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IIntentReceiver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */