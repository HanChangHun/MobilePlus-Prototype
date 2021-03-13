package android.app.prediction;

import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPredictionCallback {
  public static IPredictionCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.prediction.IPredictionCallback";
  }
  
  public void onResult(ParceledListSlice paramParceledListSlice) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.prediction.IPredictionCallback");
      if (paramParceledListSlice != null) {
        parcel.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IPredictionCallback.Stub.getDefaultImpl() != null) {
        IPredictionCallback.Stub.getDefaultImpl().onResult(paramParceledListSlice);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/IPredictionCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */