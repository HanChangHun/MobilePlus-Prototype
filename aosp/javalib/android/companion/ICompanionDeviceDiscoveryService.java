package android.companion;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.infra.AndroidFuture;

public interface ICompanionDeviceDiscoveryService extends IInterface {
  void startDiscovery(AssociationRequest paramAssociationRequest, String paramString, IFindDeviceCallback paramIFindDeviceCallback, AndroidFuture<Association> paramAndroidFuture) throws RemoteException;
  
  public static class Default implements ICompanionDeviceDiscoveryService {
    public IBinder asBinder() {
      return null;
    }
    
    public void startDiscovery(AssociationRequest param1AssociationRequest, String param1String, IFindDeviceCallback param1IFindDeviceCallback, AndroidFuture<Association> param1AndroidFuture) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICompanionDeviceDiscoveryService {
    private static final String DESCRIPTOR = "android.companion.ICompanionDeviceDiscoveryService";
    
    static final int TRANSACTION_startDiscovery = 1;
    
    public Stub() {
      attachInterface(this, "android.companion.ICompanionDeviceDiscoveryService");
    }
    
    public static ICompanionDeviceDiscoveryService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.companion.ICompanionDeviceDiscoveryService");
      return (iInterface != null && iInterface instanceof ICompanionDeviceDiscoveryService) ? (ICompanionDeviceDiscoveryService)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICompanionDeviceDiscoveryService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "startDiscovery";
    }
    
    public static boolean setDefaultImpl(ICompanionDeviceDiscoveryService param1ICompanionDeviceDiscoveryService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICompanionDeviceDiscoveryService != null) {
          Proxy.sDefaultImpl = param1ICompanionDeviceDiscoveryService;
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
      AssociationRequest associationRequest;
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.companion.ICompanionDeviceDiscoveryService");
        return true;
      } 
      param1Parcel1.enforceInterface("android.companion.ICompanionDeviceDiscoveryService");
      if (param1Parcel1.readInt() != 0) {
        associationRequest = (AssociationRequest)AssociationRequest.CREATOR.createFromParcel(param1Parcel1);
      } else {
        associationRequest = null;
      } 
      String str = param1Parcel1.readString();
      IFindDeviceCallback iFindDeviceCallback = IFindDeviceCallback.Stub.asInterface(param1Parcel1.readStrongBinder());
      if (param1Parcel1.readInt() != 0) {
        AndroidFuture androidFuture = (AndroidFuture)AndroidFuture.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      startDiscovery(associationRequest, str, iFindDeviceCallback, (AndroidFuture<Association>)param1Parcel1);
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements ICompanionDeviceDiscoveryService {
      public static ICompanionDeviceDiscoveryService sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.companion.ICompanionDeviceDiscoveryService";
      }
      
      public void startDiscovery(AssociationRequest param2AssociationRequest, String param2String, IFindDeviceCallback param2IFindDeviceCallback, AndroidFuture<Association> param2AndroidFuture) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.companion.ICompanionDeviceDiscoveryService");
          if (param2AssociationRequest != null) {
            parcel1.writeInt(1);
            param2AssociationRequest.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String);
          if (param2IFindDeviceCallback != null) {
            iBinder = param2IFindDeviceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2AndroidFuture != null) {
            parcel1.writeInt(1);
            param2AndroidFuture.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICompanionDeviceDiscoveryService.Stub.getDefaultImpl() != null) {
            ICompanionDeviceDiscoveryService.Stub.getDefaultImpl().startDiscovery(param2AssociationRequest, param2String, param2IFindDeviceCallback, param2AndroidFuture);
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
  
  private static class Proxy implements ICompanionDeviceDiscoveryService {
    public static ICompanionDeviceDiscoveryService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.companion.ICompanionDeviceDiscoveryService";
    }
    
    public void startDiscovery(AssociationRequest param1AssociationRequest, String param1String, IFindDeviceCallback param1IFindDeviceCallback, AndroidFuture<Association> param1AndroidFuture) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.companion.ICompanionDeviceDiscoveryService");
        if (param1AssociationRequest != null) {
          parcel1.writeInt(1);
          param1AssociationRequest.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (param1IFindDeviceCallback != null) {
          iBinder = param1IFindDeviceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1AndroidFuture != null) {
          parcel1.writeInt(1);
          param1AndroidFuture.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICompanionDeviceDiscoveryService.Stub.getDefaultImpl() != null) {
          ICompanionDeviceDiscoveryService.Stub.getDefaultImpl().startDiscovery(param1AssociationRequest, param1String, param1IFindDeviceCallback, param1AndroidFuture);
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


/* Location:              /home/chun/Desktop/temp/!/android/companion/ICompanionDeviceDiscoveryService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */