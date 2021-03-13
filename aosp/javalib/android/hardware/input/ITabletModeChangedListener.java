package android.hardware.input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITabletModeChangedListener extends IInterface {
  void onTabletModeChanged(long paramLong, boolean paramBoolean) throws RemoteException;
  
  public static class Default implements ITabletModeChangedListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onTabletModeChanged(long param1Long, boolean param1Boolean) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ITabletModeChangedListener {
    private static final String DESCRIPTOR = "android.hardware.input.ITabletModeChangedListener";
    
    static final int TRANSACTION_onTabletModeChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.input.ITabletModeChangedListener");
    }
    
    public static ITabletModeChangedListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.input.ITabletModeChangedListener");
      return (iInterface != null && iInterface instanceof ITabletModeChangedListener) ? (ITabletModeChangedListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static ITabletModeChangedListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onTabletModeChanged";
    }
    
    public static boolean setDefaultImpl(ITabletModeChangedListener param1ITabletModeChangedListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ITabletModeChangedListener != null) {
          Proxy.sDefaultImpl = param1ITabletModeChangedListener;
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
      boolean bool;
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.hardware.input.ITabletModeChangedListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.input.ITabletModeChangedListener");
      long l = param1Parcel1.readLong();
      if (param1Parcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      onTabletModeChanged(l, bool);
      return true;
    }
    
    private static class Proxy implements ITabletModeChangedListener {
      public static ITabletModeChangedListener sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.input.ITabletModeChangedListener";
      }
      
      public void onTabletModeChanged(long param2Long, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.hardware.input.ITabletModeChangedListener");
          parcel.writeLong(param2Long);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(1, parcel, null, 1) && ITabletModeChangedListener.Stub.getDefaultImpl() != null) {
            ITabletModeChangedListener.Stub.getDefaultImpl().onTabletModeChanged(param2Long, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ITabletModeChangedListener {
    public static ITabletModeChangedListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.input.ITabletModeChangedListener";
    }
    
    public void onTabletModeChanged(long param1Long, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.input.ITabletModeChangedListener");
        parcel.writeLong(param1Long);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && ITabletModeChangedListener.Stub.getDefaultImpl() != null) {
          ITabletModeChangedListener.Stub.getDefaultImpl().onTabletModeChanged(param1Long, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/ITabletModeChangedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */