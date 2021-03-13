package android.hardware.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IContextHubTransactionCallback {
  public static IContextHubTransactionCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.location.IContextHubTransactionCallback";
  }
  
  public void onQueryResponse(int paramInt, List<NanoAppState> paramList) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IContextHubTransactionCallback");
      parcel.writeInt(paramInt);
      parcel.writeTypedList(paramList);
      if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubTransactionCallback.Stub.getDefaultImpl() != null) {
        IContextHubTransactionCallback.Stub.getDefaultImpl().onQueryResponse(paramInt, paramList);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTransactionComplete(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IContextHubTransactionCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IContextHubTransactionCallback.Stub.getDefaultImpl() != null) {
        IContextHubTransactionCallback.Stub.getDefaultImpl().onTransactionComplete(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubTransactionCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */