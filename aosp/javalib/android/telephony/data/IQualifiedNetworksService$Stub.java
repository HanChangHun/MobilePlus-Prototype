package android.telephony.data;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IQualifiedNetworksService {
  private static final String DESCRIPTOR = "android.telephony.data.IQualifiedNetworksService";
  
  static final int TRANSACTION_createNetworkAvailabilityProvider = 1;
  
  static final int TRANSACTION_removeNetworkAvailabilityProvider = 2;
  
  public Stub() {
    attachInterface(this, "android.telephony.data.IQualifiedNetworksService");
  }
  
  public static IQualifiedNetworksService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.telephony.data.IQualifiedNetworksService");
    return (iInterface != null && iInterface instanceof IQualifiedNetworksService) ? (IQualifiedNetworksService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IQualifiedNetworksService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "removeNetworkAvailabilityProvider") : "createNetworkAvailabilityProvider";
  }
  
  public static boolean setDefaultImpl(IQualifiedNetworksService paramIQualifiedNetworksService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIQualifiedNetworksService != null) {
        Proxy.sDefaultImpl = paramIQualifiedNetworksService;
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
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.telephony.data.IQualifiedNetworksService");
        return true;
      } 
      paramParcel1.enforceInterface("android.telephony.data.IQualifiedNetworksService");
      removeNetworkAvailabilityProvider(paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.telephony.data.IQualifiedNetworksService");
    createNetworkAvailabilityProvider(paramParcel1.readInt(), IQualifiedNetworksServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
    return true;
  }
  
  private static class Proxy implements IQualifiedNetworksService {
    public static IQualifiedNetworksService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void createNetworkAvailabilityProvider(int param2Int, IQualifiedNetworksServiceCallback param2IQualifiedNetworksServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IQualifiedNetworksService");
        parcel.writeInt(param2Int);
        if (param2IQualifiedNetworksServiceCallback != null) {
          iBinder = param2IQualifiedNetworksServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IQualifiedNetworksService.Stub.getDefaultImpl() != null) {
          IQualifiedNetworksService.Stub.getDefaultImpl().createNetworkAvailabilityProvider(param2Int, param2IQualifiedNetworksServiceCallback);
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
    
    public void removeNetworkAvailabilityProvider(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IQualifiedNetworksService");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IQualifiedNetworksService.Stub.getDefaultImpl() != null) {
          IQualifiedNetworksService.Stub.getDefaultImpl().removeNetworkAvailabilityProvider(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IQualifiedNetworksService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */