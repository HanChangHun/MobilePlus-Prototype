package android.app.usage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements ICacheQuotaService {
  private static final String DESCRIPTOR = "android.app.usage.ICacheQuotaService";
  
  static final int TRANSACTION_computeCacheQuotaHints = 1;
  
  public Stub() {
    attachInterface(this, "android.app.usage.ICacheQuotaService");
  }
  
  public static ICacheQuotaService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.usage.ICacheQuotaService");
    return (iInterface != null && iInterface instanceof ICacheQuotaService) ? (ICacheQuotaService)iInterface : new Proxy(paramIBinder);
  }
  
  public static ICacheQuotaService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "computeCacheQuotaHints";
  }
  
  public static boolean setDefaultImpl(ICacheQuotaService paramICacheQuotaService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramICacheQuotaService != null) {
        Proxy.sDefaultImpl = paramICacheQuotaService;
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
      paramParcel2.writeString("android.app.usage.ICacheQuotaService");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.usage.ICacheQuotaService");
    if (paramParcel1.readInt() != 0) {
      RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    computeCacheQuotaHints((RemoteCallback)paramParcel2, paramParcel1.createTypedArrayList(CacheQuotaHint.CREATOR));
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


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/ICacheQuotaService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */