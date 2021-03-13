package android.app.blob;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

class Proxy implements IBlobStoreSession {
  public static IBlobStoreSession sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
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
  
  public void allowPackageAccess(String paramString, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
      parcel1.writeString(paramString);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
        IBlobStoreSession.Stub.getDefaultImpl().allowPackageAccess(paramString, paramArrayOfbyte);
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
  
  public void commit(IBlobCommitCallback paramIBlobCommitCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
      if (paramIBlobCommitCallback != null) {
        iBinder = paramIBlobCommitCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
        IBlobStoreSession.Stub.getDefaultImpl().commit(paramIBlobCommitCallback);
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
  
  public boolean isPackageAccessAllowed(String paramString, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
      parcel1.writeString(paramString);
      parcel1.writeByteArray(paramArrayOfbyte);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(6, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
        bool = IBlobStoreSession.Stub.getDefaultImpl().isPackageAccessAllowed(paramString, paramArrayOfbyte);
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
  
  public ParcelFileDescriptor openWrite(long paramLong1, long paramLong2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParcelFileDescriptor parcelFileDescriptor;
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreSession");
      parcel1.writeLong(paramLong1);
      parcel1.writeLong(paramLong2);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBlobStoreSession.Stub.getDefaultImpl() != null) {
        parcelFileDescriptor = IBlobStoreSession.Stub.getDefaultImpl().openWrite(paramLong1, paramLong2);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/IBlobStoreSession$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */