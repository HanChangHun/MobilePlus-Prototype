package android.hardware.iris;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IIrisService {
  private static final String DESCRIPTOR = "android.hardware.iris.IIrisService";
  
  static final int TRANSACTION_initConfiguredStrength = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.iris.IIrisService");
  }
  
  public static IIrisService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.iris.IIrisService");
    return (iInterface != null && iInterface instanceof IIrisService) ? (IIrisService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IIrisService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "initConfiguredStrength";
  }
  
  public static boolean setDefaultImpl(IIrisService paramIIrisService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIIrisService != null) {
        Proxy.sDefaultImpl = paramIIrisService;
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
      paramParcel2.writeString("android.hardware.iris.IIrisService");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.iris.IIrisService");
    initConfiguredStrength(paramParcel1.readInt());
    paramParcel2.writeNoException();
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/iris/IIrisService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */