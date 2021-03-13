package android.companion;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.infra.AndroidFuture;

class Proxy implements ICompanionDeviceDiscoveryService {
  public static ICompanionDeviceDiscoveryService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.companion.ICompanionDeviceDiscoveryService";
  }
  
  public void startDiscovery(AssociationRequest paramAssociationRequest, String paramString, IFindDeviceCallback paramIFindDeviceCallback, AndroidFuture<Association> paramAndroidFuture) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.companion.ICompanionDeviceDiscoveryService");
      if (paramAssociationRequest != null) {
        parcel1.writeInt(1);
        paramAssociationRequest.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramIFindDeviceCallback != null) {
        iBinder = paramIFindDeviceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramAndroidFuture != null) {
        parcel1.writeInt(1);
        paramAndroidFuture.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICompanionDeviceDiscoveryService.Stub.getDefaultImpl() != null) {
        ICompanionDeviceDiscoveryService.Stub.getDefaultImpl().startDiscovery(paramAssociationRequest, paramString, paramIFindDeviceCallback, paramAndroidFuture);
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


/* Location:              /home/chun/Desktop/temp/!/android/companion/ICompanionDeviceDiscoveryService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */