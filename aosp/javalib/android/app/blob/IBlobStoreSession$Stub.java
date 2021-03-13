package android.app.blob;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBlobStoreSession {
  private static final String DESCRIPTOR = "android.app.blob.IBlobStoreSession";
  
  static final int TRANSACTION_abandon = 11;
  
  static final int TRANSACTION_allowPackageAccess = 3;
  
  static final int TRANSACTION_allowPublicAccess = 5;
  
  static final int TRANSACTION_allowSameSignatureAccess = 4;
  
  static final int TRANSACTION_close = 10;
  
  static final int TRANSACTION_commit = 12;
  
  static final int TRANSACTION_getSize = 9;
  
  static final int TRANSACTION_isPackageAccessAllowed = 6;
  
  static final int TRANSACTION_isPublicAccessAllowed = 8;
  
  static final int TRANSACTION_isSameSignatureAccessAllowed = 7;
  
  static final int TRANSACTION_openRead = 2;
  
  static final int TRANSACTION_openWrite = 1;
  
  public Stub() {
    attachInterface(this, "android.app.blob.IBlobStoreSession");
  }
  
  public static IBlobStoreSession asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.blob.IBlobStoreSession");
    return (iInterface != null && iInterface instanceof IBlobStoreSession) ? (IBlobStoreSession)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBlobStoreSession getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 12:
        return "commit";
      case 11:
        return "abandon";
      case 10:
        return "close";
      case 9:
        return "getSize";
      case 8:
        return "isPublicAccessAllowed";
      case 7:
        return "isSameSignatureAccessAllowed";
      case 6:
        return "isPackageAccessAllowed";
      case 5:
        return "allowPublicAccess";
      case 4:
        return "allowSameSignatureAccess";
      case 3:
        return "allowPackageAccess";
      case 2:
        return "openRead";
      case 1:
        break;
    } 
    return "openWrite";
  }
  
  public static boolean setDefaultImpl(IBlobStoreSession paramIBlobStoreSession) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBlobStoreSession != null) {
        Proxy.sDefaultImpl = paramIBlobStoreSession;
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
    if (paramInt1 != 1598968902) {
      boolean bool;
      long l;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 12:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreSession");
          commit(IBlobCommitCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 11:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreSession");
          abandon();
          paramParcel2.writeNoException();
          return true;
        case 10:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreSession");
          close();
          paramParcel2.writeNoException();
          return true;
        case 9:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreSession");
          l = getSize();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 8:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreSession");
          bool = isPublicAccessAllowed();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreSession");
          bool = isSameSignatureAccessAllowed();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 6:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreSession");
          bool = isPackageAccessAllowed(paramParcel1.readString(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreSession");
          allowPublicAccess();
          paramParcel2.writeNoException();
          return true;
        case 4:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreSession");
          allowSameSignatureAccess();
          paramParcel2.writeNoException();
          return true;
        case 3:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreSession");
          allowPackageAccess(paramParcel1.readString(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 2:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreSession");
          parcelFileDescriptor = openRead();
          paramParcel2.writeNoException();
          if (parcelFileDescriptor != null) {
            paramParcel2.writeInt(1);
            parcelFileDescriptor.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 1:
          break;
      } 
      parcelFileDescriptor.enforceInterface("android.app.blob.IBlobStoreSession");
      ParcelFileDescriptor parcelFileDescriptor = openWrite(parcelFileDescriptor.readLong(), parcelFileDescriptor.readLong());
      paramParcel2.writeNoException();
      if (parcelFileDescriptor != null) {
        paramParcel2.writeInt(1);
        parcelFileDescriptor.writeToParcel(paramParcel2, 1);
      } else {
        paramParcel2.writeInt(0);
      } 
      return true;
    } 
    paramParcel2.writeString("android.app.blob.IBlobStoreSession");
    return true;
  }
  
  private static class Proxy implements IBlobStoreSession {
    public static IBlobStoreSession sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void abandon() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
          IBlobStoreSession.Stub.getDefaultImpl().abandon();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void allowPackageAccess(String param2String, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        parcel1.writeString(param2String);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
          IBlobStoreSession.Stub.getDefaultImpl().allowPackageAccess(param2String, param2ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void allowPublicAccess() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
          IBlobStoreSession.Stub.getDefaultImpl().allowPublicAccess();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void allowSameSignatureAccess() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
          IBlobStoreSession.Stub.getDefaultImpl().allowSameSignatureAccess();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void close() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
          IBlobStoreSession.Stub.getDefaultImpl().close();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void commit(IBlobCommitCallback param2IBlobCommitCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        if (param2IBlobCommitCallback != null) {
          iBinder = param2IBlobCommitCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
          IBlobStoreSession.Stub.getDefaultImpl().commit(param2IBlobCommitCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.blob.IBlobStoreSession";
    }
    
    public long getSize() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null)
          return IBlobStoreSession.Stub.getDefaultImpl().getSize(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isPackageAccessAllowed(String param2String, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        parcel1.writeString(param2String);
        parcel1.writeByteArray(param2ArrayOfbyte);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
          bool = IBlobStoreSession.Stub.getDefaultImpl().isPackageAccessAllowed(param2String, param2ArrayOfbyte);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isPublicAccessAllowed() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
          bool = IBlobStoreSession.Stub.getDefaultImpl().isPublicAccessAllowed();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isSameSignatureAccessAllowed() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
          bool = IBlobStoreSession.Stub.getDefaultImpl().isSameSignatureAccessAllowed();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor openRead() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParcelFileDescriptor parcelFileDescriptor;
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
          parcelFileDescriptor = IBlobStoreSession.Stub.getDefaultImpl().openRead();
          return parcelFileDescriptor;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          parcelFileDescriptor = null;
        } 
        return parcelFileDescriptor;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor openWrite(long param2Long1, long param2Long2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParcelFileDescriptor parcelFileDescriptor;
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
        parcel1.writeLong(param2Long1);
        parcel1.writeLong(param2Long2);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
          parcelFileDescriptor = IBlobStoreSession.Stub.getDefaultImpl().openWrite(param2Long1, param2Long2);
          return parcelFileDescriptor;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          parcelFileDescriptor = null;
        } 
        return parcelFileDescriptor;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/IBlobStoreSession$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */