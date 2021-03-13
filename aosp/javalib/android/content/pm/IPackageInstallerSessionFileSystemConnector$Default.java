package android.content.pm;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public class Default implements IPackageInstallerSessionFileSystemConnector {
  public IBinder asBinder() {
    return null;
  }
  
  public void writeData(String paramString, long paramLong1, long paramLong2, ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallerSessionFileSystemConnector$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */