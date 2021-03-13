package android.hardware.display;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IVirtualDisplayCallback {
  private static final String DESCRIPTOR = "android.hardware.display.IVirtualDisplayCallback";
  
  static final int TRANSACTION_onPaused = 1;
  
  static final int TRANSACTION_onResumed = 2;
  
  static final int TRANSACTION_onStopped = 3;
  
  public Stub() {
    attachInterface(this, "android.hardware.display.IVirtualDisplayCallback");
  }
  
  public static IVirtualDisplayCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.display.IVirtualDisplayCallback");
    return (iInterface != null && iInterface instanceof IVirtualDisplayCallback) ? (IVirtualDisplayCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IVirtualDisplayCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "onStopped") : "onResumed") : "onPaused";
  }
  
  public static boolean setDefaultImpl(IVirtualDisplayCallback paramIVirtualDisplayCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIVirtualDisplayCallback != null) {
        Proxy.sDefaultImpl = paramIVirtualDisplayCallback;
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
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.hardware.display.IVirtualDisplayCallback");
          return true;
        } 
        paramParcel1.enforceInterface("android.hardware.display.IVirtualDisplayCallback");
        onStopped();
        return true;
      } 
      paramParcel1.enforceInterface("android.hardware.display.IVirtualDisplayCallback");
      onResumed();
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.display.IVirtualDisplayCallback");
    onPaused();
    return true;
  }
  
  private static class Proxy implements IVirtualDisplayCallback {
    public static IVirtualDisplayCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.display.IVirtualDisplayCallback";
    }
    
    public void onPaused() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
        if (!this.mRemote.transact(1, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
          IVirtualDisplayCallback.Stub.getDefaultImpl().onPaused();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onResumed() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
        if (!this.mRemote.transact(2, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
          IVirtualDisplayCallback.Stub.getDefaultImpl().onResumed();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onStopped() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
        if (!this.mRemote.transact(3, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
          IVirtualDisplayCallback.Stub.getDefaultImpl().onStopped();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IVirtualDisplayCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */