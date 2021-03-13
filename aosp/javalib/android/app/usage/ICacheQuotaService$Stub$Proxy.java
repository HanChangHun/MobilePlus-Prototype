package android.app.usage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import java.util.List;

class Proxy implements ICacheQuotaService {
  public static ICacheQuotaService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void computeCacheQuotaHints(RemoteCallback paramRemoteCallback, List<CacheQuotaHint> paramList) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.usage.ICacheQuotaService");
      if (paramRemoteCallback != null) {
        parcel.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeTypedList(paramList);
      if (!this.mRemote.transact(1, parcel, null, 1) && ICacheQuotaService.Stub.getDefaultImpl() != null) {
        ICacheQuotaService.Stub.getDefaultImpl().computeCacheQuotaHints(paramRemoteCallback, paramList);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/ICacheQuotaService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */