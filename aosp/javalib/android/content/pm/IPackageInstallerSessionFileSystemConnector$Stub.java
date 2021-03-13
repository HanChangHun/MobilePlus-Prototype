package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPackageInstallerSessionFileSystemConnector {
  private static final String DESCRIPTOR = "android.content.pm.IPackageInstallerSessionFileSystemConnector";
  
  static final int TRANSACTION_writeData = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageInstallerSessionFileSystemConnector");
  }
  
  public static IPackageInstallerSessionFileSystemConnector asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageInstallerSessionFileSystemConnector");
    return (iInterface != null && iInterface instanceof IPackageInstallerSessionFileSystemConnector) ? (IPackageInstallerSessionFileSystemConnector)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageInstallerSessionFileSystemConnector getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "writeData";
  }
  
  public static boolean setDefaultImpl(IPackageInstallerSessionFileSystemConnector paramIPackageInstallerSessionFileSystemConnector) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageInstallerSessionFileSystemConnector != null) {
        Proxy.sDefaultImpl = paramIPackageInstallerSessionFileSystemConnector;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.content.pm.IPackageInstallerSessionFileSystemConnector");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IPackageInstallerSessionFileSystemConnector");
    String str = paramParcel1.readString();
    long l1 = paramParcel1.readLong();
    long l2 = paramParcel1.readLong();
    if (paramParcel1.readInt() != 0) {
      ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    writeData(str, l1, l2, (ParcelFileDescriptor)paramParcel1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IPackageInstallerSessionFileSystemConnector {
    public static IPackageInstallerSessionFileSystemConnector sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageInstallerSessionFileSystemConnector";
    }
    
    public void writeData(String param2String, long param2Long1, long param2Long2, ParcelFileDescriptor param2ParcelFileDescriptor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSessionFileSystemConnector");
        try {
          parcel1.writeString(param2String);
          try {
            parcel1.writeLong(param2Long1);
            try {
              parcel1.writeLong(param2Long2);
              if (param2ParcelFileDescriptor != null) {
                parcel1.writeInt(1);
                param2ParcelFileDescriptor.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageInstallerSessionFileSystemConnector.Stub.getDefaultImpl() != null) {
                IPackageInstallerSessionFileSystemConnector.Stub.getDefaultImpl().writeData(param2String, param2Long1, param2Long2, param2ParcelFileDescriptor);
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
      throw param2String;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallerSessionFileSystemConnector$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */