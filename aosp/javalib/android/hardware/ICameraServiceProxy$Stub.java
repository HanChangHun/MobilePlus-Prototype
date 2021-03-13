package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ICameraServiceProxy {
  private static final String DESCRIPTOR = "android.hardware.ICameraServiceProxy";
  
  static final int TRANSACTION_notifyCameraState = 2;
  
  static final int TRANSACTION_pingForUserUpdate = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.ICameraServiceProxy");
  }
  
  public static ICameraServiceProxy asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.ICameraServiceProxy");
    return (iInterface != null && iInterface instanceof ICameraServiceProxy) ? (ICameraServiceProxy)iInterface : new Proxy(paramIBinder);
  }
  
  public static ICameraServiceProxy getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "notifyCameraState") : "pingForUserUpdate";
  }
  
  public static boolean setDefaultImpl(ICameraServiceProxy paramICameraServiceProxy) {
    if (Proxy.sDefaultImpl == null) {
      if (paramICameraServiceProxy != null) {
        Proxy.sDefaultImpl = paramICameraServiceProxy;
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
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.hardware.ICameraServiceProxy");
        return true;
      } 
      paramParcel1.enforceInterface("android.hardware.ICameraServiceProxy");
      notifyCameraState(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.ICameraServiceProxy");
    pingForUserUpdate();
    return true;
  }
  
  private static class Proxy implements ICameraServiceProxy {
    public static ICameraServiceProxy sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ICameraServiceProxy";
    }
    
    public void notifyCameraState(String param2String1, int param2Int1, int param2Int2, String param2String2, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceProxy");
        parcel.writeString(param2String1);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeString(param2String2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(2, parcel, null, 1) && ICameraServiceProxy.Stub.getDefaultImpl() != null) {
          ICameraServiceProxy.Stub.getDefaultImpl().notifyCameraState(param2String1, param2Int1, param2Int2, param2String2, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void pingForUserUpdate() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceProxy");
        if (!this.mRemote.transact(1, parcel, null, 1) && ICameraServiceProxy.Stub.getDefaultImpl() != null) {
          ICameraServiceProxy.Stub.getDefaultImpl().pingForUserUpdate();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraServiceProxy$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */