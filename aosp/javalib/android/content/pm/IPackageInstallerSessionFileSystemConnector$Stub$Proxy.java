package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

class Proxy implements IPackageInstallerSessionFileSystemConnector {
  public static IPackageInstallerSessionFileSystemConnector sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPackageInstallerSessionFileSystemConnector";
  }
  
  public void writeData(String paramString, long paramLong1, long paramLong2, ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSessionFileSystemConnector");
      try {
        parcel1.writeString(paramString);
        try {
          parcel1.writeLong(paramLong1);
          try {
            parcel1.writeLong(paramLong2);
            if (paramParcelFileDescriptor != null) {
              parcel1.writeInt(1);
              paramParcelFileDescriptor.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageInstallerSessionFileSystemConnector.Stub.getDefaultImpl() != null) {
              IPackageInstallerSessionFileSystemConnector.Stub.getDefaultImpl().writeData(paramString, paramLong1, paramLong2, paramParcelFileDescriptor);
              parcel2.recycle();
              parcel1.recycle();
              return;
            } 
            parcel2.readException();
            parcel2.recycle();
            parcel1.recycle();
            return;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallerSessionFileSystemConnector$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */