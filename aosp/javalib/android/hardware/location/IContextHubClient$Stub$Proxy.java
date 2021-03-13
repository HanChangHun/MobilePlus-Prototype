package android.hardware.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IContextHubClient {
  public static IContextHubClient sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void close() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubClient");
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IContextHubClient.Stub.getDefaultImpl() != null) {
        IContextHubClient.Stub.getDefaultImpl().close();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.location.IContextHubClient";
  }
  
  public int sendMessageToNanoApp(NanoAppMessage paramNanoAppMessage) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubClient");
      if (paramNanoAppMessage != null) {
        parcel1.writeInt(1);
        paramNanoAppMessage.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IContextHubClient.Stub.getDefaultImpl() != null)
        return IContextHubClient.Stub.getDefaultImpl().sendMessageToNanoApp(paramNanoAppMessage); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubClient$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */