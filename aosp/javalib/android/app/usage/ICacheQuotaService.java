package android.app.usage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import java.util.List;

public interface ICacheQuotaService extends IInterface {
  void computeCacheQuotaHints(RemoteCallback paramRemoteCallback, List<CacheQuotaHint> paramList) throws RemoteException;
  
  public static class Default implements ICacheQuotaService {
    public IBinder asBinder() {
      return null;
    }
    
    public void computeCacheQuotaHints(RemoteCallback param1RemoteCallback, List<CacheQuotaHint> param1List) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICacheQuotaService {
    private static final String DESCRIPTOR = "android.app.usage.ICacheQuotaService";
    
    static final int TRANSACTION_computeCacheQuotaHints = 1;
    
    public Stub() {
      attachInterface(this, "android.app.usage.ICacheQuotaService");
    }
    
    public static ICacheQuotaService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.usage.ICacheQuotaService");
      return (iInterface != null && iInterface instanceof ICacheQuotaService) ? (ICacheQuotaService)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICacheQuotaService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "computeCacheQuotaHints";
    }
    
    public static boolean setDefaultImpl(ICacheQuotaService param1ICacheQuotaService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICacheQuotaService != null) {
          Proxy.sDefaultImpl = param1ICacheQuotaService;
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
        param1Parcel2.writeString("android.app.usage.ICacheQuotaService");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.usage.ICacheQuotaService");
      if (param1Parcel1.readInt() != 0) {
        RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      computeCacheQuotaHints((RemoteCallback)param1Parcel2, param1Parcel1.createTypedArrayList(CacheQuotaHint.CREATOR));
      return true;
    }
    
    private static class Proxy implements ICacheQuotaService {
      public static ICacheQuotaService sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void computeCacheQuotaHints(RemoteCallback param2RemoteCallback, List<CacheQuotaHint> param2List) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.usage.ICacheQuotaService");
          if (param2RemoteCallback != null) {
            parcel.writeInt(1);
            param2RemoteCallback.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeTypedList(param2List);
          if (!this.mRemote.transact(1, parcel, null, 1) && ICacheQuotaService.Stub.getDefaultImpl() != null) {
            ICacheQuotaService.Stub.getDefaultImpl().computeCacheQuotaHints(param2RemoteCallback, param2List);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.usage.ICacheQuotaService";
      }
    }
  }
  
  private static class Proxy implements ICacheQuotaService {
    public static ICacheQuotaService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void computeCacheQuotaHints(RemoteCallback param1RemoteCallback, List<CacheQuotaHint> param1List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.usage.ICacheQuotaService");
        if (param1RemoteCallback != null) {
          parcel.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeTypedList(param1List);
        if (!this.mRemote.transact(1, parcel, null, 1) && ICacheQuotaService.Stub.getDefaultImpl() != null) {
          ICacheQuotaService.Stub.getDefaultImpl().computeCacheQuotaHints(param1RemoteCallback, param1List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.usage.ICacheQuotaService";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/ICacheQuotaService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */