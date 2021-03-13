package android.telephony.data;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IQualifiedNetworksServiceCallback {
  public static IQualifiedNetworksServiceCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.telephony.data.IQualifiedNetworksServiceCallback";
  }
  
  public void onQualifiedNetworkTypesChanged(int paramInt, int[] paramArrayOfint) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.telephony.data.IQualifiedNetworksServiceCallback");
      parcel.writeInt(paramInt);
      parcel.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(1, parcel, null, 1) && IQualifiedNetworksServiceCallback.Stub.getDefaultImpl() != null) {
        IQualifiedNetworksServiceCallback.Stub.getDefaultImpl().onQualifiedNetworkTypesChanged(paramInt, paramArrayOfint);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IQualifiedNetworksServiceCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */