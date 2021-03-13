package android.app.blob;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.List;

class Proxy implements IBlobStoreManager {
  public static IBlobStoreManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void abandonSession(long paramLong, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      parcel1.writeLong(paramLong);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
        IBlobStoreManager.Stub.getDefaultImpl().abandonSession(paramLong, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void acquireLease(BlobHandle paramBlobHandle, int paramInt, CharSequence paramCharSequence, long paramLong, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      if (paramBlobHandle != null) {
        parcel1.writeInt(1);
        paramBlobHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeInt(paramInt);
        if (paramCharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeLong(paramLong);
          try {
            parcel1.writeString(paramString);
            if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
              IBlobStoreManager.Stub.getDefaultImpl().acquireLease(paramBlobHandle, paramInt, paramCharSequence, paramLong, paramString);
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
    throw paramBlobHandle;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public long createSession(BlobHandle paramBlobHandle, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      if (paramBlobHandle != null) {
        parcel1.writeInt(1);
        paramBlobHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
        return IBlobStoreManager.Stub.getDefaultImpl().createSession(paramBlobHandle, paramString); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deleteBlob(long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
        IBlobStoreManager.Stub.getDefaultImpl().deleteBlob(paramLong);
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
    return "android.app.blob.IBlobStoreManager";
  }
  
  public LeaseInfo getLeaseInfo(BlobHandle paramBlobHandle, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      if (paramBlobHandle != null) {
        parcel1.writeInt(1);
        paramBlobHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
        return IBlobStoreManager.Stub.getDefaultImpl().getLeaseInfo(paramBlobHandle, paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        LeaseInfo leaseInfo = (LeaseInfo)LeaseInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramBlobHandle = null;
      } 
      return (LeaseInfo)paramBlobHandle;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<BlobHandle> getLeasedBlobs(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
        return IBlobStoreManager.Stub.getDefaultImpl().getLeasedBlobs(paramString); 
      parcel2.readException();
      return parcel2.createTypedArrayList(BlobHandle.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getRemainingLeaseQuotaBytes(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
        return IBlobStoreManager.Stub.getDefaultImpl().getRemainingLeaseQuotaBytes(paramString); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParcelFileDescriptor openBlob(BlobHandle paramBlobHandle, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      if (paramBlobHandle != null) {
        parcel1.writeInt(1);
        paramBlobHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
        return IBlobStoreManager.Stub.getDefaultImpl().openBlob(paramBlobHandle, paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
      } else {
        paramBlobHandle = null;
      } 
      return (ParcelFileDescriptor)paramBlobHandle;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBlobStoreSession openSession(long paramLong, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      parcel1.writeLong(paramLong);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
        return IBlobStoreManager.Stub.getDefaultImpl().openSession(paramLong, paramString); 
      parcel2.readException();
      return IBlobStoreSession.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<BlobInfo> queryBlobsForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
        return IBlobStoreManager.Stub.getDefaultImpl().queryBlobsForUser(paramInt); 
      parcel2.readException();
      return parcel2.createTypedArrayList(BlobInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void releaseLease(BlobHandle paramBlobHandle, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      if (paramBlobHandle != null) {
        parcel1.writeInt(1);
        paramBlobHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
        IBlobStoreManager.Stub.getDefaultImpl().releaseLease(paramBlobHandle, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void waitForIdle(RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
      if (paramRemoteCallback != null) {
        parcel1.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
        IBlobStoreManager.Stub.getDefaultImpl().waitForIdle(paramRemoteCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/IBlobStoreManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */