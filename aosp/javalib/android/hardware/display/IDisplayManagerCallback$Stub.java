package android.hardware.display;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IDisplayManagerCallback {
  private static final String DESCRIPTOR = "android.hardware.display.IDisplayManagerCallback";
  
  static final int TRANSACTION_onDisplayEvent = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.display.IDisplayManagerCallback");
  }
  
  public static IDisplayManagerCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.display.IDisplayManagerCallback");
    return (iInterface != null && iInterface instanceof IDisplayManagerCallback) ? (IDisplayManagerCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IDisplayManagerCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onDisplayEvent";
  }
  
  public static boolean setDefaultImpl(IDisplayManagerCallback paramIDisplayManagerCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIDisplayManagerCallback != null) {
        Proxy.sDefaultImpl = paramIDisplayManagerCallback;
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
      paramParcel2.writeString("android.hardware.display.IDisplayManagerCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.display.IDisplayManagerCallback");
    onDisplayEvent(paramParcel1.readInt(), paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IDisplayManagerCallback {
    public static IDisplayManagerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.display.IDisplayManagerCallback";
    }
    
    public void onDisplayEvent(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.display.IDisplayManagerCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IDisplayManagerCallback.Stub.getDefaultImpl() != null) {
          IDisplayManagerCallback.Stub.getDefaultImpl().onDisplayEvent(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IDisplayManagerCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */