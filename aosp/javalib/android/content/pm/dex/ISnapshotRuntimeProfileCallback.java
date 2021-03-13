package android.content.pm.dex;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface ISnapshotRuntimeProfileCallback extends IInterface {
  void onError(int paramInt) throws RemoteException;
  
  void onSuccess(ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException;
  
  public static class Default implements ISnapshotRuntimeProfileCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onError(int param1Int) throws RemoteException {}
    
    public void onSuccess(ParcelFileDescriptor param1ParcelFileDescriptor) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ISnapshotRuntimeProfileCallback {
    private static final String DESCRIPTOR = "android.content.pm.dex.ISnapshotRuntimeProfileCallback";
    
    static final int TRANSACTION_onError = 2;
    
    static final int TRANSACTION_onSuccess = 1;
    
    public Stub() {
      attachInterface(this, "android.content.pm.dex.ISnapshotRuntimeProfileCallback");
    }
    
    public static ISnapshotRuntimeProfileCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
      return (iInterface != null && iInterface instanceof ISnapshotRuntimeProfileCallback) ? (ISnapshotRuntimeProfileCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISnapshotRuntimeProfileCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onError") : "onSuccess";
    }
    
    public static boolean setDefaultImpl(ISnapshotRuntimeProfileCallback param1ISnapshotRuntimeProfileCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISnapshotRuntimeProfileCallback != null) {
          Proxy.sDefaultImpl = param1ISnapshotRuntimeProfileCallback;
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
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
          return true;
        } 
        param1Parcel1.enforceInterface("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
        onError(param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
      if (param1Parcel1.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onSuccess((ParcelFileDescriptor)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements ISnapshotRuntimeProfileCallback {
      public static ISnapshotRuntimeProfileCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.pm.dex.ISnapshotRuntimeProfileCallback";
      }
      
      public void onError(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel, null, 1) && ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl() != null) {
            ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl().onError(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onSuccess(ParcelFileDescriptor param2ParcelFileDescriptor) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
          if (param2ParcelFileDescriptor != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl() != null) {
            ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl().onSuccess(param2ParcelFileDescriptor);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ISnapshotRuntimeProfileCallback {
    public static ISnapshotRuntimeProfileCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.dex.ISnapshotRuntimeProfileCallback";
    }
    
    public void onError(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl() != null) {
          ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl().onError(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSuccess(ParcelFileDescriptor param1ParcelFileDescriptor) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl() != null) {
          ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl().onSuccess(param1ParcelFileDescriptor);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/ISnapshotRuntimeProfileCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */