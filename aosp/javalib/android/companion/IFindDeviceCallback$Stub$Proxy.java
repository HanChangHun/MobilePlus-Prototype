package android.companion;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;

class Proxy implements IFindDeviceCallback {
  public static IFindDeviceCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.companion.IFindDeviceCallback";
  }
  
  public void onFailure(CharSequence paramCharSequence) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.companion.IFindDeviceCallback");
      if (paramCharSequence != null) {
        parcel.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && IFindDeviceCallback.Stub.getDefaultImpl() != null) {
        IFindDeviceCallback.Stub.getDefaultImpl().onFailure(paramCharSequence);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSuccess(PendingIntent paramPendingIntent) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.companion.IFindDeviceCallback");
      if (paramPendingIntent != null) {
        parcel.writeInt(1);
        paramPendingIntent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IFindDeviceCallback.Stub.getDefaultImpl() != null) {
        IFindDeviceCallback.Stub.getDefaultImpl().onSuccess(paramPendingIntent);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/IFindDeviceCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */