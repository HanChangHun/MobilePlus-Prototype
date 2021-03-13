package android.app.trust;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IStrongAuthTracker extends IInterface {
  void onIsNonStrongBiometricAllowedChanged(boolean paramBoolean, int paramInt) throws RemoteException;
  
  void onStrongAuthRequiredChanged(int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IStrongAuthTracker {
    public IBinder asBinder() {
      return null;
    }
    
    public void onIsNonStrongBiometricAllowedChanged(boolean param1Boolean, int param1Int) throws RemoteException {}
    
    public void onStrongAuthRequiredChanged(int param1Int1, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IStrongAuthTracker {
    private static final String DESCRIPTOR = "android.app.trust.IStrongAuthTracker";
    
    static final int TRANSACTION_onIsNonStrongBiometricAllowedChanged = 2;
    
    static final int TRANSACTION_onStrongAuthRequiredChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.app.trust.IStrongAuthTracker");
    }
    
    public static IStrongAuthTracker asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.trust.IStrongAuthTracker");
      return (iInterface != null && iInterface instanceof IStrongAuthTracker) ? (IStrongAuthTracker)iInterface : new Proxy(param1IBinder);
    }
    
    public static IStrongAuthTracker getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onIsNonStrongBiometricAllowedChanged") : "onStrongAuthRequiredChanged";
    }
    
    public static boolean setDefaultImpl(IStrongAuthTracker param1IStrongAuthTracker) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IStrongAuthTracker != null) {
          Proxy.sDefaultImpl = param1IStrongAuthTracker;
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
        boolean bool;
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.app.trust.IStrongAuthTracker");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.trust.IStrongAuthTracker");
        if (param1Parcel1.readInt() != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        onIsNonStrongBiometricAllowedChanged(bool, param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.trust.IStrongAuthTracker");
      onStrongAuthRequiredChanged(param1Parcel1.readInt(), param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements IStrongAuthTracker {
      public static IStrongAuthTracker sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.trust.IStrongAuthTracker";
      }
      
      public void onIsNonStrongBiometricAllowedChanged(boolean param2Boolean, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.app.trust.IStrongAuthTracker");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel, null, 1) && IStrongAuthTracker.Stub.getDefaultImpl() != null) {
            IStrongAuthTracker.Stub.getDefaultImpl().onIsNonStrongBiometricAllowedChanged(param2Boolean, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onStrongAuthRequiredChanged(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.trust.IStrongAuthTracker");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(1, parcel, null, 1) && IStrongAuthTracker.Stub.getDefaultImpl() != null) {
            IStrongAuthTracker.Stub.getDefaultImpl().onStrongAuthRequiredChanged(param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IStrongAuthTracker {
    public static IStrongAuthTracker sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.trust.IStrongAuthTracker";
    }
    
    public void onIsNonStrongBiometricAllowedChanged(boolean param1Boolean, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.trust.IStrongAuthTracker");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IStrongAuthTracker.Stub.getDefaultImpl() != null) {
          IStrongAuthTracker.Stub.getDefaultImpl().onIsNonStrongBiometricAllowedChanged(param1Boolean, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onStrongAuthRequiredChanged(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.trust.IStrongAuthTracker");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IStrongAuthTracker.Stub.getDefaultImpl() != null) {
          IStrongAuthTracker.Stub.getDefaultImpl().onStrongAuthRequiredChanged(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/IStrongAuthTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */