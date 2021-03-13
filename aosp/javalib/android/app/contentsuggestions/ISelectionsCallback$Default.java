package android.app.contentsuggestions;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements ISelectionsCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onContentSelectionsAvailable(int paramInt, List<ContentSelection> paramList) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ISelectionsCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */