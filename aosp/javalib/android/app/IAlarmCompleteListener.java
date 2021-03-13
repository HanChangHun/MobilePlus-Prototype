package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAlarmCompleteListener extends IInterface {
  void alarmComplete(IBinder paramIBinder) throws RemoteException;
  
  public static class Default implements IAlarmCompleteListener {
    public void alarmComplete(IBinder param1IBinder) throws RemoteException {}
    
    public IBinder asBinder() {
      return null;
    }
  }
  
  public static abstract class Stub extends Binder implements IAlarmCompleteListener {
    private static final String DESCRIPTOR = "android.app.IAlarmCompleteListener";
    
    static final int TRANSACTION_alarmComplete = 1;
    
    public Stub() {
      attachInterface(this, "android.app.IAlarmCompleteListener");
    }
    
    public static IAlarmCompleteListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IAlarmCompleteListener");
      return (iInterface != null && iInterface instanceof IAlarmCompleteListener) ? (IAlarmCompleteListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAlarmCompleteListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "alarmComplete";
    }
    
    public static boolean setDefaultImpl(IAlarmCompleteListener param1IAlarmCompleteListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAlarmCompleteListener != null) {
          Proxy.sDefaultImpl = param1IAlarmCompleteListener;
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
        param1Parcel2.writeString("android.app.IAlarmCompleteListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IAlarmCompleteListener");
      alarmComplete(param1Parcel1.readStrongBinder());
      param1Parcel2.writeNoException();
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
  
  private static class Proxy implements IAlarmCompleteListener {
    public static IAlarmCompleteListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public void alarmComplete(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IAlarmCompleteListener");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAlarmCompleteListener.Stub.getDefaultImpl() != null) {
          IAlarmCompleteListener.Stub.getDefaultImpl().alarmComplete(param1IBinder);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IAlarmCompleteListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */