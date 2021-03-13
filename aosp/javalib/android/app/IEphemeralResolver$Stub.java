package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IEphemeralResolver {
  private static final String DESCRIPTOR = "android.app.IEphemeralResolver";
  
  static final int TRANSACTION_getEphemeralIntentFilterList = 2;
  
  static final int TRANSACTION_getEphemeralResolveInfoList = 1;
  
  public Stub() {
    attachInterface(this, "android.app.IEphemeralResolver");
  }
  
  public static IEphemeralResolver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IEphemeralResolver");
    return (iInterface != null && iInterface instanceof IEphemeralResolver) ? (IEphemeralResolver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IEphemeralResolver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "getEphemeralIntentFilterList") : "getEphemeralResolveInfoList";
  }
  
  public static boolean setDefaultImpl(IEphemeralResolver paramIEphemeralResolver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIEphemeralResolver != null) {
        Proxy.sDefaultImpl = paramIEphemeralResolver;
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
        paramParcel2.writeString("android.app.IEphemeralResolver");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.IEphemeralResolver");
      getEphemeralIntentFilterList(IRemoteCallback.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IEphemeralResolver");
    getEphemeralResolveInfoList(IRemoteCallback.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.createIntArray(), paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IEphemeralResolver {
    public static IEphemeralResolver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void getEphemeralIntentFilterList(IRemoteCallback param2IRemoteCallback, String param2String, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IEphemeralResolver");
        if (param2IRemoteCallback != null) {
          iBinder = param2IRemoteCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeString(param2String);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IEphemeralResolver.Stub.getDefaultImpl() != null) {
          IEphemeralResolver.Stub.getDefaultImpl().getEphemeralIntentFilterList(param2IRemoteCallback, param2String, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void getEphemeralResolveInfoList(IRemoteCallback param2IRemoteCallback, int[] param2ArrayOfint, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IEphemeralResolver");
        if (param2IRemoteCallback != null) {
          iBinder = param2IRemoteCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeIntArray(param2ArrayOfint);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IEphemeralResolver.Stub.getDefaultImpl() != null) {
          IEphemeralResolver.Stub.getDefaultImpl().getEphemeralResolveInfoList(param2IRemoteCallback, param2ArrayOfint, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IEphemeralResolver";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IEphemeralResolver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */