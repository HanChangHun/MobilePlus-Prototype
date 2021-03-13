package android.app.trust;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ITrustListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onTrustChanged(boolean paramBoolean, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onTrustError(CharSequence paramCharSequence) throws RemoteException {}
  
  public void onTrustManagedChanged(boolean paramBoolean, int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/ITrustListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */