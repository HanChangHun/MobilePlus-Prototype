package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IPackageInstallerSessionFileSystemConnector extends IInterface {
  void writeData(String paramString, long paramLong1, long paramLong2, ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException;
  
  public static class Default implements IPackageInstallerSessionFileSystemConnector {
    public IBinder asBinder() {
      return null;
    }
    
    public void writeData(String param1String, long param1Long1, long param1Long2, ParcelFileDescriptor param1ParcelFileDescriptor) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IPackageInstallerSessionFileSystemConnector {
    private static final String DESCRIPTOR = "android.content.pm.IPackageInstallerSessionFileSystemConnector";
    
    static final int TRANSACTION_writeData = 1;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IPackageInstallerSessionFileSystemConnector");
    }
    
    public static IPackageInstallerSessionFileSystemConnector asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IPackageInstallerSessionFileSystemConnector");
      return (iInterface != null && iInterface instanceof IPackageInstallerSessionFileSystemConnector) ? (IPackageInstallerSessionFileSystemConnector)iInterface : new Proxy(param1IBinder);
    }
    
    public static IPackageInstallerSessionFileSystemConnector getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "writeData";
    }
    
    public static boolean setDefaultImpl(IPackageInstallerSessionFileSystemConnector param1IPackageInstallerSessionFileSystemConnector) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IPackageInstallerSessionFileSystemConnector != null) {
          Proxy.sDefaultImpl = param1IPackageInstallerSessionFileSystemConnector;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.content.pm.IPackageInstallerSessionFileSystemConnector");
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.IPackageInstallerSessionFileSystemConnector");
      String str = param1Parcel1.readString();
      long l1 = param1Parcel1.readLong();
      long l2 = param1Parcel1.readLong();
      if (param1Parcel1.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      writeData(str, l1, l2, (ParcelFileDescriptor)param1Parcel1);
      param1Parcel2.writeNoException();
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
  
  private static class Proxy implements IPackageInstallerSessionFileSystemConnector {
    public static IPackageInstallerSessionFileSystemConnector sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageInstallerSessionFileSystemConnector";
    }
    
    public void writeData(String param1String, long param1Long1, long param1Long2, ParcelFileDescriptor param1ParcelFileDescriptor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSessionFileSystemConnector");
        try {
          parcel1.writeString(param1String);
          try {
            parcel1.writeLong(param1Long1);
            try {
              parcel1.writeLong(param1Long2);
              if (param1ParcelFileDescriptor != null) {
                parcel1.writeInt(1);
                param1ParcelFileDescriptor.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageInstallerSessionFileSystemConnector.Stub.getDefaultImpl() != null) {
                IPackageInstallerSessionFileSystemConnector.Stub.getDefaultImpl().writeData(param1String, param1Long1, param1Long2, param1ParcelFileDescriptor);
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
      throw param1String;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallerSessionFileSystemConnector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */