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

public interface IBlobStoreManager extends IInterface {
  void abandonSession(long paramLong, String paramString) throws RemoteException;
  
  void acquireLease(BlobHandle paramBlobHandle, int paramInt, CharSequence paramCharSequence, long paramLong, String paramString) throws RemoteException;
  
  long createSession(BlobHandle paramBlobHandle, String paramString) throws RemoteException;
  
  void deleteBlob(long paramLong) throws RemoteException;
  
  LeaseInfo getLeaseInfo(BlobHandle paramBlobHandle, String paramString) throws RemoteException;
  
  List<BlobHandle> getLeasedBlobs(String paramString) throws RemoteException;
  
  long getRemainingLeaseQuotaBytes(String paramString) throws RemoteException;
  
  ParcelFileDescriptor openBlob(BlobHandle paramBlobHandle, String paramString) throws RemoteException;
  
  IBlobStoreSession openSession(long paramLong, String paramString) throws RemoteException;
  
  List<BlobInfo> queryBlobsForUser(int paramInt) throws RemoteException;
  
  void releaseLease(BlobHandle paramBlobHandle, String paramString) throws RemoteException;
  
  void waitForIdle(RemoteCallback paramRemoteCallback) throws RemoteException;
  
  public static class Default implements IBlobStoreManager {
    public void abandonSession(long param1Long, String param1String) throws RemoteException {}
    
    public void acquireLease(BlobHandle param1BlobHandle, int param1Int, CharSequence param1CharSequence, long param1Long, String param1String) throws RemoteException {}
    
    public IBinder asBinder() {
      return null;
    }
    
    public long createSession(BlobHandle param1BlobHandle, String param1String) throws RemoteException {
      return 0L;
    }
    
    public void deleteBlob(long param1Long) throws RemoteException {}
    
    public LeaseInfo getLeaseInfo(BlobHandle param1BlobHandle, String param1String) throws RemoteException {
      return null;
    }
    
    public List<BlobHandle> getLeasedBlobs(String param1String) throws RemoteException {
      return null;
    }
    
    public long getRemainingLeaseQuotaBytes(String param1String) throws RemoteException {
      return 0L;
    }
    
    public ParcelFileDescriptor openBlob(BlobHandle param1BlobHandle, String param1String) throws RemoteException {
      return null;
    }
    
    public IBlobStoreSession openSession(long param1Long, String param1String) throws RemoteException {
      return null;
    }
    
    public List<BlobInfo> queryBlobsForUser(int param1Int) throws RemoteException {
      return null;
    }
    
    public void releaseLease(BlobHandle param1BlobHandle, String param1String) throws RemoteException {}
    
    public void waitForIdle(RemoteCallback param1RemoteCallback) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBlobStoreManager {
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
    
    public static IBlobStoreManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.blob.IBlobStoreManager");
      return (iInterface != null && iInterface instanceof IBlobStoreManager) ? (IBlobStoreManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBlobStoreManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IBlobStoreManager param1IBlobStoreManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBlobStoreManager != null) {
          Proxy.sDefaultImpl = param1IBlobStoreManager;
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
      if (param1Int1 != 1598968902) {
        LeaseInfo leaseInfo;
        List<BlobHandle> list;
        ParcelFileDescriptor parcelFileDescriptor;
        IBlobStoreSession iBlobStoreSession;
        BlobHandle blobHandle;
        CharSequence charSequence;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 12:
            param1Parcel1.enforceInterface("android.app.blob.IBlobStoreManager");
            if (param1Parcel1.readInt() != 0) {
              blobHandle = (BlobHandle)BlobHandle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              blobHandle = null;
            } 
            leaseInfo = getLeaseInfo(blobHandle, param1Parcel1.readString());
            param1Parcel2.writeNoException();
            if (leaseInfo != null) {
              param1Parcel2.writeInt(1);
              leaseInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 11:
            leaseInfo.enforceInterface("android.app.blob.IBlobStoreManager");
            list = getLeasedBlobs(leaseInfo.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 10:
            list.enforceInterface("android.app.blob.IBlobStoreManager");
            deleteBlob(list.readLong());
            param1Parcel2.writeNoException();
            return true;
          case 9:
            list.enforceInterface("android.app.blob.IBlobStoreManager");
            list = (List)queryBlobsForUser(list.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 8:
            list.enforceInterface("android.app.blob.IBlobStoreManager");
            if (list.readInt() != 0) {
              RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            waitForIdle((RemoteCallback)list);
            param1Parcel2.writeNoException();
            return true;
          case 7:
            list.enforceInterface("android.app.blob.IBlobStoreManager");
            l = getRemainingLeaseQuotaBytes(list.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeLong(l);
            return true;
          case 6:
            list.enforceInterface("android.app.blob.IBlobStoreManager");
            if (list.readInt() != 0) {
              blobHandle = (BlobHandle)BlobHandle.CREATOR.createFromParcel((Parcel)list);
            } else {
              blobHandle = null;
            } 
            releaseLease(blobHandle, list.readString());
            param1Parcel2.writeNoException();
            return true;
          case 5:
            list.enforceInterface("android.app.blob.IBlobStoreManager");
            if (list.readInt() != 0) {
              blobHandle = (BlobHandle)BlobHandle.CREATOR.createFromParcel((Parcel)list);
            } else {
              blobHandle = null;
            } 
            param1Int1 = list.readInt();
            if (list.readInt() != 0) {
              charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)list);
            } else {
              charSequence = null;
            } 
            acquireLease(blobHandle, param1Int1, charSequence, list.readLong(), list.readString());
            param1Parcel2.writeNoException();
            return true;
          case 4:
            list.enforceInterface("android.app.blob.IBlobStoreManager");
            abandonSession(list.readLong(), list.readString());
            param1Parcel2.writeNoException();
            return true;
          case 3:
            list.enforceInterface("android.app.blob.IBlobStoreManager");
            if (list.readInt() != 0) {
              blobHandle = (BlobHandle)BlobHandle.CREATOR.createFromParcel((Parcel)list);
            } else {
              blobHandle = null;
            } 
            parcelFileDescriptor = openBlob(blobHandle, list.readString());
            param1Parcel2.writeNoException();
            if (parcelFileDescriptor != null) {
              param1Parcel2.writeInt(1);
              parcelFileDescriptor.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 2:
            parcelFileDescriptor.enforceInterface("android.app.blob.IBlobStoreManager");
            iBlobStoreSession = openSession(parcelFileDescriptor.readLong(), parcelFileDescriptor.readString());
            param1Parcel2.writeNoException();
            if (iBlobStoreSession != null) {
              IBinder iBinder = iBlobStoreSession.asBinder();
            } else {
              iBlobStoreSession = null;
            } 
            param1Parcel2.writeStrongBinder((IBinder)iBlobStoreSession);
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
        param1Parcel2.writeNoException();
        param1Parcel2.writeLong(l);
        return true;
      } 
      param1Parcel2.writeString("android.app.blob.IBlobStoreManager");
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
  
  private static class Proxy implements IBlobStoreManager {
    public static IBlobStoreManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public void abandonSession(long param1Long, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeLong(param1Long);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
          IBlobStoreManager.Stub.getDefaultImpl().abandonSession(param1Long, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void acquireLease(BlobHandle param1BlobHandle, int param1Int, CharSequence param1CharSequence, long param1Long, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param1BlobHandle != null) {
          parcel1.writeInt(1);
          param1BlobHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeInt(param1Int);
          if (param1CharSequence != null) {
            parcel1.writeInt(1);
            TextUtils.writeToParcel(param1CharSequence, parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeLong(param1Long);
            try {
              parcel1.writeString(param1String);
              if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
                IBlobStoreManager.Stub.getDefaultImpl().acquireLease(param1BlobHandle, param1Int, param1CharSequence, param1Long, param1String);
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
      throw param1BlobHandle;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public long createSession(BlobHandle param1BlobHandle, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param1BlobHandle != null) {
          parcel1.writeInt(1);
          param1BlobHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().createSession(param1BlobHandle, param1String); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteBlob(long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeLong(param1Long);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
          IBlobStoreManager.Stub.getDefaultImpl().deleteBlob(param1Long);
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
    
    public LeaseInfo getLeaseInfo(BlobHandle param1BlobHandle, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param1BlobHandle != null) {
          parcel1.writeInt(1);
          param1BlobHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().getLeaseInfo(param1BlobHandle, param1String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          LeaseInfo leaseInfo = (LeaseInfo)LeaseInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param1BlobHandle = null;
        } 
        return (LeaseInfo)param1BlobHandle;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BlobHandle> getLeasedBlobs(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().getLeasedBlobs(param1String); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BlobHandle.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getRemainingLeaseQuotaBytes(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().getRemainingLeaseQuotaBytes(param1String); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor openBlob(BlobHandle param1BlobHandle, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param1BlobHandle != null) {
          parcel1.writeInt(1);
          param1BlobHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().openBlob(param1BlobHandle, param1String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param1BlobHandle = null;
        } 
        return (ParcelFileDescriptor)param1BlobHandle;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBlobStoreSession openSession(long param1Long, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeLong(param1Long);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().openSession(param1Long, param1String); 
        parcel2.readException();
        return IBlobStoreSession.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BlobInfo> queryBlobsForUser(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null)
          return IBlobStoreManager.Stub.getDefaultImpl().queryBlobsForUser(param1Int); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BlobInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void releaseLease(BlobHandle param1BlobHandle, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param1BlobHandle != null) {
          parcel1.writeInt(1);
          param1BlobHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
          IBlobStoreManager.Stub.getDefaultImpl().releaseLease(param1BlobHandle, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void waitForIdle(RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.blob.IBlobStoreManager");
        if (param1RemoteCallback != null) {
          parcel1.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBlobStoreManager.Stub.getDefaultImpl() != null) {
          IBlobStoreManager.Stub.getDefaultImpl().waitForIdle(param1RemoteCallback);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/IBlobStoreManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */