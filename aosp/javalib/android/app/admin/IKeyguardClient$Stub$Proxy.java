package android.app.admin;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IKeyguardClient {
  public static IKeyguardClient sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.admin.IKeyguardClient";
  }
  
  public void onCreateKeyguardSurface(IBinder paramIBinder, IKeyguardCallback paramIKeyguardCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.admin.IKeyguardClient");
      parcel.writeStrongBinder(paramIBinder);
      if (paramIKeyguardCallback != null) {
        iBinder = paramIKeyguardCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && IKeyguardClient.Stub.getDefaultImpl() != null) {
        IKeyguardClient.Stub.getDefaultImpl().onCreateKeyguardSurface(paramIBinder, paramIKeyguardCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IKeyguardClient$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */