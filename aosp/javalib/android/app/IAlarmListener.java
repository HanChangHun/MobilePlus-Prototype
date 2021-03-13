package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAlarmListener extends IInterface {
  void doAlarm(IAlarmCompleteListener paramIAlarmCompleteListener) throws RemoteException;
  
  public static class Default implements IAlarmListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void doAlarm(IAlarmCompleteListener param1IAlarmCompleteListener) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAlarmListener {
    private static final String DESCRIPTOR = "android.app.IAlarmListener";
    
    static final int TRANSACTION_doAlarm = 1;
    
    public Stub() {
      attachInterface(this, "android.app.IAlarmListener");
    }
    
    public static IAlarmListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IAlarmListener");
      return (iInterface != null && iInterface instanceof IAlarmListener) ? (IAlarmListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAlarmListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "doAlarm";
    }
    
    public static boolean setDefaultImpl(IAlarmListener param1IAlarmListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAlarmListener != null) {
          Proxy.sDefaultImpl = param1IAlarmListener;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.app.IAlarmListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IAlarmListener");
      doAlarm(IAlarmCompleteListener.Stub.asInterface(param1Parcel1.readStrongBinder()));
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
  
  private static class Proxy implements IAlarmListener {
    public static IAlarmListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void doAlarm(IAlarmCompleteListener param1IAlarmCompleteListener) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IAlarmListener");
        if (param1IAlarmCompleteListener != null) {
          iBinder = param1IAlarmCompleteListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IAlarmListener.Stub.getDefaultImpl() != null) {
          IAlarmListener.Stub.getDefaultImpl().doAlarm(param1IAlarmCompleteListener);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IAlarmListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */