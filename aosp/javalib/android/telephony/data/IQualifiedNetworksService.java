package android.telephony.data;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IQualifiedNetworksService extends IInterface {
  void createNetworkAvailabilityProvider(int paramInt, IQualifiedNetworksServiceCallback paramIQualifiedNetworksServiceCallback) throws RemoteException;
  
  void removeNetworkAvailabilityProvider(int paramInt) throws RemoteException;
  
  public static class Default implements IQualifiedNetworksService {
    public IBinder asBinder() {
      return null;
    }
    
    public void createNetworkAvailabilityProvider(int param1Int, IQualifiedNetworksServiceCallback param1IQualifiedNetworksServiceCallback) throws RemoteException {}
    
    public void removeNetworkAvailabilityProvider(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IQualifiedNetworksService {
    private static final String DESCRIPTOR = "android.telephony.data.IQualifiedNetworksService";
    
    static final int TRANSACTION_createNetworkAvailabilityProvider = 1;
    
    static final int TRANSACTION_removeNetworkAvailabilityProvider = 2;
    
    public Stub() {
      attachInterface(this, "android.telephony.data.IQualifiedNetworksService");
    }
    
    public static IQualifiedNetworksService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.telephony.data.IQualifiedNetworksService");
      return (iInterface != null && iInterface instanceof IQualifiedNetworksService) ? (IQualifiedNetworksService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IQualifiedNetworksService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "removeNetworkAvailabilityProvider") : "createNetworkAvailabilityProvider";
    }
    
    public static boolean setDefaultImpl(IQualifiedNetworksService param1IQualifiedNetworksService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IQualifiedNetworksService != null) {
          Proxy.sDefaultImpl = param1IQualifiedNetworksService;
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
          param1Parcel2.writeString("android.telephony.data.IQualifiedNetworksService");
          return true;
        } 
        param1Parcel1.enforceInterface("android.telephony.data.IQualifiedNetworksService");
        removeNetworkAvailabilityProvider(param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.telephony.data.IQualifiedNetworksService");
      createNetworkAvailabilityProvider(param1Parcel1.readInt(), IQualifiedNetworksServiceCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
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
  
  private static class Proxy implements IQualifiedNetworksService {
    public static IQualifiedNetworksService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void createNetworkAvailabilityProvider(int param1Int, IQualifiedNetworksServiceCallback param1IQualifiedNetworksServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IQualifiedNetworksService");
        parcel.writeInt(param1Int);
        if (param1IQualifiedNetworksServiceCallback != null) {
          iBinder = param1IQualifiedNetworksServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IQualifiedNetworksService.Stub.getDefaultImpl() != null) {
          IQualifiedNetworksService.Stub.getDefaultImpl().createNetworkAvailabilityProvider(param1Int, param1IQualifiedNetworksServiceCallback);
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
    
    public void removeNetworkAvailabilityProvider(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IQualifiedNetworksService");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IQualifiedNetworksService.Stub.getDefaultImpl() != null) {
          IQualifiedNetworksService.Stub.getDefaultImpl().removeNetworkAvailabilityProvider(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IQualifiedNetworksService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */