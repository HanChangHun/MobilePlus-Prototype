package android.hardware.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IContextHubCallback {
  public static IContextHubCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.location.IContextHubCallback";
  }
  
  public void onMessageReceipt(int paramInt1, int paramInt2, ContextHubMessage paramContextHubMessage) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IContextHubCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (paramContextHubMessage != null) {
        parcel.writeInt(1);
        paramContextHubMessage.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubCallback.Stub.getDefaultImpl() != null) {
        IContextHubCallback.Stub.getDefaultImpl().onMessageReceipt(paramInt1, paramInt2, paramContextHubMessage);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */