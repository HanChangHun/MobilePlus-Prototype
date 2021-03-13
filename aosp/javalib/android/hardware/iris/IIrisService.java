package android.hardware.iris;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IIrisService extends IInterface {
  void initConfiguredStrength(int paramInt) throws RemoteException;
  
  public static class Default implements IIrisService {
    public IBinder asBinder() {
      return null;
    }
    
    public void initConfiguredStrength(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IIrisService {
    private static final String DESCRIPTOR = "android.hardware.iris.IIrisService";
    
    static final int TRANSACTION_initConfiguredStrength = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.iris.IIrisService");
    }
    
    public static IIrisService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.iris.IIrisService");
      return (iInterface != null && iInterface instanceof IIrisService) ? (IIrisService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IIrisService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "initConfiguredStrength";
    }
    
    public static boolean setDefaultImpl(IIrisService param1IIrisService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IIrisService != null) {
          Proxy.sDefaultImpl = param1IIrisService;
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
        param1Parcel2.writeString("android.hardware.iris.IIrisService");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.iris.IIrisService");
      initConfiguredStrength(param1Parcel1.readInt());
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements IIrisService {
      public static IIrisService sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.iris.IIrisService";
      }
      
      public void initConfiguredStrength(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.iris.IIrisService");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IIrisService.Stub.getDefaultImpl() != null) {
            IIrisService.Stub.getDefaultImpl().initConfiguredStrength(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IIrisService {
    public static IIrisService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.iris.IIrisService";
    }
    
    public void initConfiguredStrength(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.iris.IIrisService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IIrisService.Stub.getDefaultImpl() != null) {
          IIrisService.Stub.getDefaultImpl().initConfiguredStrength(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/iris/IIrisService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */