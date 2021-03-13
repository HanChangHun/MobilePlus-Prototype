package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IAlarmCompleteListener {
  public static IAlarmCompleteListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void alarmComplete(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IAlarmCompleteListener");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAlarmCompleteListener.Stub.getDefaultImpl() != null) {
        IAlarmCompleteListener.Stub.getDefaultImpl().alarmComplete(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IAlarmCompleteListener";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAlarmCompleteListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */