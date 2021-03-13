package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IAlarmCompleteListener {
  private static final String DESCRIPTOR = "android.app.IAlarmCompleteListener";
  
  static final int TRANSACTION_alarmComplete = 1;
  
  public Stub() {
    attachInterface(this, "android.app.IAlarmCompleteListener");
  }
  
  public static IAlarmCompleteListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IAlarmCompleteListener");
    return (iInterface != null && iInterface instanceof IAlarmCompleteListener) ? (IAlarmCompleteListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAlarmCompleteListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "alarmComplete";
  }
  
  public static boolean setDefaultImpl(IAlarmCompleteListener paramIAlarmCompleteListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAlarmCompleteListener != null) {
        Proxy.sDefaultImpl = paramIAlarmCompleteListener;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.app.IAlarmCompleteListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IAlarmCompleteListener");
    alarmComplete(paramParcel1.readStrongBinder());
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IAlarmCompleteListener {
    public static IAlarmCompleteListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void alarmComplete(IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IAlarmCompleteListener");
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAlarmCompleteListener.Stub.getDefaultImpl() != null) {
          IAlarmCompleteListener.Stub.getDefaultImpl().alarmComplete(param2IBinder);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAlarmCompleteListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */