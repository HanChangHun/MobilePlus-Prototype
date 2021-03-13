package android.hardware.input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ITabletModeChangedListener {
  private static final String DESCRIPTOR = "android.hardware.input.ITabletModeChangedListener";
  
  static final int TRANSACTION_onTabletModeChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.input.ITabletModeChangedListener");
  }
  
  public static ITabletModeChangedListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.input.ITabletModeChangedListener");
    return (iInterface != null && iInterface instanceof ITabletModeChangedListener) ? (ITabletModeChangedListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static ITabletModeChangedListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onTabletModeChanged";
  }
  
  public static boolean setDefaultImpl(ITabletModeChangedListener paramITabletModeChangedListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramITabletModeChangedListener != null) {
        Proxy.sDefaultImpl = paramITabletModeChangedListener;
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
    boolean bool;
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.input.ITabletModeChangedListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.input.ITabletModeChangedListener");
    long l = paramParcel1.readLong();
    if (paramParcel1.readInt() != 0) {
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/ITabletModeChangedListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */