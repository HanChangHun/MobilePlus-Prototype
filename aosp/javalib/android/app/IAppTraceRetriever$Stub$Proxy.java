package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

class Proxy implements IAppTraceRetriever {
  public static IAppTraceRetriever sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IAppTraceRetriever";
  }
  
  public ParcelFileDescriptor getTraceFileDescriptor(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IAppTraceRetriever");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAppTraceRetriever.Stub.getDefaultImpl() != null)
        return IAppTraceRetriever.Stub.getDefaultImpl().getTraceFileDescriptor(paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParcelFileDescriptor)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAppTraceRetriever$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */