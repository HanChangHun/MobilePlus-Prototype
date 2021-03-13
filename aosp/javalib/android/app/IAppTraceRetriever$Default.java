package android.app;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public class Default implements IAppTraceRetriever {
  public IBinder asBinder() {
    return null;
  }
  
  public ParcelFileDescriptor getTraceFileDescriptor(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAppTraceRetriever$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */