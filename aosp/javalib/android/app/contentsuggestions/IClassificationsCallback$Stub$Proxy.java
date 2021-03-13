package android.app.contentsuggestions;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IClassificationsCallback {
  public static IClassificationsCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.contentsuggestions.IClassificationsCallback";
  }
  
  public void onContentClassificationsAvailable(int paramInt, List<ContentClassification> paramList) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.contentsuggestions.IClassificationsCallback");
      parcel.writeInt(paramInt);
      parcel.writeTypedList(paramList);
      if (!this.mRemote.transact(1, parcel, null, 1) && IClassificationsCallback.Stub.getDefaultImpl() != null) {
        IClassificationsCallback.Stub.getDefaultImpl().onContentClassificationsAvailable(paramInt, paramList);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/IClassificationsCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */