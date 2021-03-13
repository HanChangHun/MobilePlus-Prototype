package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IAlarmListener {
  public static IAlarmListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void doAlarm(IAlarmCompleteListener paramIAlarmCompleteListener) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IAlarmListener");
      if (paramIAlarmCompleteListener != null) {
        iBinder = paramIAlarmCompleteListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && IAlarmListener.Stub.getDefaultImpl() != null) {
        IAlarmListener.Stub.getDefaultImpl().doAlarm(paramIAlarmCompleteListener);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IAlarmListener";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAlarmListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */