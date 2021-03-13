package android.content;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IIntentSender {
  public IBinder asBinder() {
    return null;
  }
  
  public void send(int paramInt, Intent paramIntent, String paramString1, IBinder paramIBinder, IIntentReceiver paramIIntentReceiver, String paramString2, Bundle paramBundle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IIntentSender$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */