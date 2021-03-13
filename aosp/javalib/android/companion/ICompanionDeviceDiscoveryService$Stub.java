package android.companion;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.infra.AndroidFuture;

public abstract class Stub extends Binder implements ICompanionDeviceDiscoveryService {
  private static final String DESCRIPTOR = "android.companion.ICompanionDeviceDiscoveryService";
  
  static final int TRANSACTION_startDiscovery = 1;
  
  public Stub() {
    attachInterface(this, "android.companion.ICompanionDeviceDiscoveryService");
  }
  
  public static ICompanionDeviceDiscoveryService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.companion.ICompanionDeviceDiscoveryService");
    return (iInterface != null && iInterface instanceof ICompanionDeviceDiscoveryService) ? (ICompanionDeviceDiscoveryService)iInterface : new Proxy(paramIBinder);
  }
  
  public static ICompanionDeviceDiscoveryService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "startDiscovery";
  }
  
  public static boolean setDefaultImpl(ICompanionDeviceDiscoveryService paramICompanionDeviceDiscoveryService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramICompanionDeviceDiscoveryService != null) {
        Proxy.sDefaultImpl = paramICompanionDeviceDiscoveryService;
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
    AssociationRequest associationRequest;
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.companion.ICompanionDeviceDiscoveryService");
      return true;
    } 
    paramParcel1.enforceInterface("android.companion.ICompanionDeviceDiscoveryService");
    if (paramParcel1.readInt() != 0) {
      associationRequest = (AssociationRequest)AssociationRequest.CREATOR.createFromParcel(paramParcel1);
    } else {
      associationRequest = null;
    } 
    String str = paramParcel1.readString();
    IFindDeviceCallback iFindDeviceCallback = IFindDeviceCallback.Stub.asInterface(paramParcel1.readStrongBinder());
    if (paramParcel1.readInt() != 0) {
      AndroidFuture androidFuture = (AndroidFuture)AndroidFuture.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    startDiscovery(associationRequest, str, iFindDeviceCallback, (AndroidFuture<Association>)paramParcel1);
    paramParcel2.writeNoException();
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


/* Location:              /home/chun/Desktop/temp/!/android/companion/ICompanionDeviceDiscoveryService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */