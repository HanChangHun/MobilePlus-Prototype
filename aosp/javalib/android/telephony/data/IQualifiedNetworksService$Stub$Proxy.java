package android.telephony.data;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IQualifiedNetworksService {
  public static IQualifiedNetworksService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void createNetworkAvailabilityProvider(int paramInt, IQualifiedNetworksServiceCallback paramIQualifiedNetworksServiceCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.telephony.data.IQualifiedNetworksService");
      parcel.writeInt(paramInt);
      if (paramIQualifiedNetworksServiceCallback != null) {
        iBinder = paramIQualifiedNetworksServiceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && IQualifiedNetworksService.Stub.getDefaultImpl() != null) {
        IQualifiedNetworksService.Stub.getDefaultImpl().createNetworkAvailabilityProvider(paramInt, paramIQualifiedNetworksServiceCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.telephony.data.IQualifiedNetworksService";
  }
  
  public void removeNetworkAvailabilityProvider(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.telephony.data.IQualifiedNetworksService");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IQualifiedNetworksService.Stub.getDefaultImpl() != null) {
        IQualifiedNetworksService.Stub.getDefaultImpl().removeNetworkAvailabilityProvider(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IQualifiedNetworksService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */