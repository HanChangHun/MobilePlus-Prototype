package android.app.blob;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.List;

public abstract class Stub extends Binder implements IBlobStoreManager {
  private static final String DESCRIPTOR = "android.app.blob.IBlobStoreManager";
  
  static final int TRANSACTION_abandonSession = 4;
  
  static final int TRANSACTION_acquireLease = 5;
  
  static final int TRANSACTION_createSession = 1;
  
  static final int TRANSACTION_deleteBlob = 10;
  
  static final int TRANSACTION_getLeaseInfo = 12;
  
  static final int TRANSACTION_getLeasedBlobs = 11;
  
  static final int TRANSACTION_getRemainingLeaseQuotaBytes = 7;
  
  static final int TRANSACTION_openBlob = 3;
  
  static final int TRANSACTION_openSession = 2;
  
  static final int TRANSACTION_queryBlobsForUser = 9;
  
  static final int TRANSACTION_releaseLease = 6;
  
  static final int TRANSACTION_waitForIdle = 8;
  
  public Stub() {
    attachInterface(this, "android.app.blob.IBlobStoreManager");
  }
  
  public static IBlobStoreManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.blob.IBlobStoreManager");
    return (iInterface != null && iInterface instanceof IBlobStoreManager) ? (IBlobStoreManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBlobStoreManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 12:
        return "getLeaseInfo";
      case 11:
        return "getLeasedBlobs";
      case 10:
        return "deleteBlob";
      case 9:
        return "queryBlobsForUser";
      case 8:
        return "waitForIdle";
      case 7:
        return "getRemainingLeaseQuotaBytes";
      case 6:
        return "releaseLease";
      case 5:
        return "acquireLease";
      case 4:
        return "abandonSession";
      case 3:
        return "openBlob";
      case 2:
        return "openSession";
      case 1:
        break;
    } 
    return "createSession";
  }
  
  public static boolean setDefaultImpl(IBlobStoreManager paramIBlobStoreManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBlobStoreManager != null) {
        Proxy.sDefaultImpl = paramIBlobStoreManager;
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
      LeaseInfo leaseInfo;
      List<BlobHandle> list;
      ParcelFileDescriptor parcelFileDescriptor;
      IBlobStoreSession iBlobStoreSession;
      BlobHandle blobHandle;
      CharSequence charSequence;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 12:
          paramParcel1.enforceInterface("android.app.blob.IBlobStoreManager");
          if (paramParcel1.readInt() != 0) {
            blobHandle = (BlobHandle)BlobHandle.CREATOR.createFromParcel(paramParcel1);
          } else {
            blobHandle = null;
          } 
          leaseInfo = getLeaseInfo(blobHandle, paramParcel1.readString());
          paramParcel2.writeNoException();
          if (leaseInfo != null) {
            paramParcel2.writeInt(1);
            leaseInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 11:
          leaseInfo.enforceInterface("android.app.blob.IBlobStoreManager");
          list = getLeasedBlobs(leaseInfo.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 10:
          list.enforceInterface("android.app.blob.IBlobStoreManager");
          deleteBlob(list.readLong());
          paramParcel2.writeNoException();
          return true;
        case 9:
          list.enforceInterface("android.app.blob.IBlobStoreManager");
          list = (List)queryBlobsForUser(list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 8:
          list.enforceInterface("android.app.blob.IBlobStoreManager");
          if (list.readInt() != 0) {
            RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          waitForIdle((RemoteCallback)list);
          paramParcel2.writeNoException();
          return true;
        case 7:
          list.enforceInterface("android.app.blob.IBlobStoreManager");
          l = getRemainingLeaseQuotaBytes(list.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 6:
          list.enforceInterface("android.app.blob.IBlobStoreManager");
          if (list.readInt() != 0) {
            blobHandle = (BlobHandle)BlobHandle.CREATOR.createFromParcel((Parcel)list);
          } else {
            blobHandle = null;
          } 
          releaseLease(blobHandle, list.readString());
          paramParcel2.writeNoException();
          return true;
        case 5:
          list.enforceInterface("android.app.blob.IBlobStoreManager");
          if (list.readInt() != 0) {
            blobHandle = (BlobHandle)BlobHandle.CREATOR.createFromParcel((Parcel)list);
          } else {
            blobHandle = null;
          } 
          paramInt1 = list.readInt();
          if (list.readInt() != 0) {
            charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)list);
          } else {
            charSequence = null;
          } 
          acquireLease(blobHandle, paramInt1, charSequence, list.readLong(), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 4:
          list.enforceInterface("android.app.blob.IBlobStoreManager");
          abandonSession(list.readLong(), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 3:
          list.enforceInterface("android.app.blob.IBlobStoreManager");
          if (list.readInt() != 0) {
            blobHandle = (BlobHandle)BlobHandle.CREATOR.createFromParcel((Parcel)list);
          } else {
            blobHandle = null;
          } 
          parcelFileDescriptor = openBlob(blobHandle, list.readString());
          paramParcel2.writeNoException();
          if (parcelFileDescriptor != null) {
            paramParcel2.writeInt(1);
            parcelFileDescriptor.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 2:
          parcelFileDescriptor.enforceInterface("android.app.blob.IBlobStoreManager");
          iBlobStoreSession = openSession(parcelFileDescriptor.readLong(), parcelFileDescriptor.readString());
          paramParcel2.writeNoException();
          if (iBlobStoreSession != null) {
            IBinder iBinder = iBlobStoreSession.asBinder();
          } else {
            iBlobStoreSession = null;
          } 
          paramParcel2.writeStrongBinder((IBinder)iBlobStoreSession);
          return true;
        case 1:
          break;
      } 
      iBlobStoreSession.enforceInterface("android.app.blob.IBlobStoreManager");
      if (iBlobStoreSession.readInt() != 0) {
        blobHandle = (BlobHandle)BlobHandle.CREATOR.createFromParcel((Parcel)iBlobStoreSession);
      } else {
        blobHandle = null;
      } 
      long l = createSession(blobHandle, iBlobStoreSession.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeLong(l);
      return true;
    } 
    paramParcel2.writeString("android.app.blob.IBlobStoreManager");
    return true;
  }
  
  private static class Proxy implements IBlobStoreManager {
    public static IBlobStoreManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void abandonSession(long param2Long, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeLong(param2Long);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
          IBlobStoreManager.Stub.getDefaultImpl().abandonSession(param2Long, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void acquireLease(BlobHandle param2BlobHandle, int param2Int, CharSequence param2CharSequence, long param2Long, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param2BlobHandle != null) {
          parcel1.writeInt(1);
          param2BlobHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeInt(param2Int);
          if (param2CharSequence != null) {
            parcel1.writeInt(1);
            TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeLong(param2Long);
            try {
              parcel1.writeString(param2String);
              if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
                IBlobStoreManager.Stub.getDefaultImpl().acquireLease(param2BlobHandle, param2Int, param2CharSequence, param2Long, param2String);
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
      throw param2BlobHandle;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public long createSession(BlobHandle param2BlobHandle, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param2BlobHandle != null) {
          parcel1.writeInt(1);
          param2BlobHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().createSession(param2BlobHandle, param2String); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteBlob(long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
          IBlobStoreManager.Stub.getDefaultImpl().deleteBlob(param2Long);
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
    
    public LeaseInfo getLeaseInfo(BlobHandle param2BlobHandle, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param2BlobHandle != null) {
          parcel1.writeInt(1);
          param2BlobHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().getLeaseInfo(param2BlobHandle, param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          LeaseInfo leaseInfo = (LeaseInfo)LeaseInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2BlobHandle = null;
        } 
        return (LeaseInfo)param2BlobHandle;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BlobHandle> getLeasedBlobs(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().getLeasedBlobs(param2String); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BlobHandle.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getRemainingLeaseQuotaBytes(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().getRemainingLeaseQuotaBytes(param2String); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor openBlob(BlobHandle param2BlobHandle, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param2BlobHandle != null) {
          parcel1.writeInt(1);
          param2BlobHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().openBlob(param2BlobHandle, param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param2BlobHandle = null;
        } 
        return (ParcelFileDescriptor)param2BlobHandle;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBlobStoreSession openSession(long param2Long, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeLong(param2Long);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().openSession(param2Long, param2String); 
        parcel2.readException();
        return IBlobStoreSession.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BlobInfo> queryBlobsForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().queryBlobsForUser(param2Int); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BlobInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void releaseLease(BlobHandle param2BlobHandle, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param2BlobHandle != null) {
          parcel1.writeInt(1);
          param2BlobHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
          IBlobStoreManager.Stub.getDefaultImpl().releaseLease(param2BlobHandle, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void waitForIdle(RemoteCallback param2RemoteCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param2RemoteCallback != null) {
          parcel1.writeInt(1);
          param2RemoteCallback.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
          IBlobStoreManager.Stub.getDefaultImpl().waitForIdle(param2RemoteCallback);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/IBlobStoreManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */