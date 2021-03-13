package android.debug;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IAdbTransport {
  private static final String DESCRIPTOR = "android.debug.IAdbTransport";
  
  static final int TRANSACTION_onAdbEnabled = 1;
  
  public Stub() {
    attachInterface(this, "android.debug.IAdbTransport");
  }
  
  public static IAdbTransport asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.debug.IAdbTransport");
    return (iInterface != null && iInterface instanceof IAdbTransport) ? (IAdbTransport)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAdbTransport getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onAdbEnabled";
  }
  
  public static boolean setDefaultImpl(IAdbTransport paramIAdbTransport) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAdbTransport != null) {
        Proxy.sDefaultImpl = paramIAdbTransport;
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
      paramParcel2.writeString("android.debug.IAdbTransport");
      return true;
    } 
    paramParcel1.enforceInterface("android.debug.IAdbTransport");
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onAdbEnabled(bool, paramParcel1.readByte());
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IAdbTransport {
    public static IAdbTransport sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.debug.IAdbTransport";
    }
    
    public void onAdbEnabled(boolean param2Boolean, byte param2Byte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.debug.IAdbTransport");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeByte(param2Byte);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAdbTransport.Stub.getDefaultImpl() != null) {
          IAdbTransport.Stub.getDefaultImpl().onAdbEnabled(param2Boolean, param2Byte);
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


/* Location:              /home/chun/Desktop/temp/!/android/debug/IAdbTransport$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */