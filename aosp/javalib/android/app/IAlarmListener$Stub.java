package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IAlarmListener {
  private static final String DESCRIPTOR = "android.app.IAlarmListener";
  
  static final int TRANSACTION_doAlarm = 1;
  
  public Stub() {
    attachInterface(this, "android.app.IAlarmListener");
  }
  
  public static IAlarmListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IAlarmListener");
    return (iInterface != null && iInterface instanceof IAlarmListener) ? (IAlarmListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAlarmListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "doAlarm";
  }
  
  public static boolean setDefaultImpl(IAlarmListener paramIAlarmListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAlarmListener != null) {
        Proxy.sDefaultImpl = paramIAlarmListener;
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
      paramParcel2.writeString("android.app.IAlarmListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IAlarmListener");
    doAlarm(IAlarmCompleteListener.Stub.asInterface(paramParcel1.readStrongBinder()));
    return true;
  }
  
  private static class Proxy implements IAlarmListener {
    public static IAlarmListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void doAlarm(IAlarmCompleteListener param2IAlarmCompleteListener) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IAlarmListener");
        if (param2IAlarmCompleteListener != null) {
          iBinder = param2IAlarmCompleteListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IAlarmListener.Stub.getDefaultImpl() != null) {
          IAlarmListener.Stub.getDefaultImpl().doAlarm(param2IAlarmCompleteListener);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAlarmListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */