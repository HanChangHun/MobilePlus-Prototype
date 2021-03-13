package android.app;

import android.content.pm.InstantAppRequestInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IInstantAppResolver {
  private static final String DESCRIPTOR = "android.app.IInstantAppResolver";
  
  static final int TRANSACTION_getInstantAppIntentFilterList = 2;
  
  static final int TRANSACTION_getInstantAppResolveInfoList = 1;
  
  public Stub() {
    attachInterface(this, "android.app.IInstantAppResolver");
  }
  
  public static IInstantAppResolver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IInstantAppResolver");
    return (iInterface != null && iInterface instanceof IInstantAppResolver) ? (IInstantAppResolver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IInstantAppResolver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "getInstantAppIntentFilterList") : "getInstantAppResolveInfoList";
  }
  
  public static boolean setDefaultImpl(IInstantAppResolver paramIInstantAppResolver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIInstantAppResolver != null) {
        Proxy.sDefaultImpl = paramIInstantAppResolver;
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
        paramParcel2.writeString("android.app.IInstantAppResolver");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.IInstantAppResolver");
      if (paramParcel1.readInt() != 0) {
        InstantAppRequestInfo instantAppRequestInfo = (InstantAppRequestInfo)InstantAppRequestInfo.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel2 = null;
      } 
      getInstantAppIntentFilterList((InstantAppRequestInfo)paramParcel2, IRemoteCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IInstantAppResolver");
    if (paramParcel1.readInt() != 0) {
      InstantAppRequestInfo instantAppRequestInfo = (InstantAppRequestInfo)InstantAppRequestInfo.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    getInstantAppResolveInfoList((InstantAppRequestInfo)paramParcel2, paramParcel1.readInt(), IRemoteCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
    return true;
  }
  
  private static class Proxy implements IInstantAppResolver {
    public static IInstantAppResolver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void getInstantAppIntentFilterList(InstantAppRequestInfo param2InstantAppRequestInfo, IRemoteCallback param2IRemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IInstantAppResolver");
        if (param2InstantAppRequestInfo != null) {
          parcel.writeInt(1);
          param2InstantAppRequestInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2IRemoteCallback != null) {
          iBinder = param2IRemoteCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel, null, 1) && IInstantAppResolver.Stub.getDefaultImpl() != null) {
          IInstantAppResolver.Stub.getDefaultImpl().getInstantAppIntentFilterList(param2InstantAppRequestInfo, param2IRemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void getInstantAppResolveInfoList(InstantAppRequestInfo param2InstantAppRequestInfo, int param2Int, IRemoteCallback param2IRemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IInstantAppResolver");
        if (param2InstantAppRequestInfo != null) {
          parcel.writeInt(1);
          param2InstantAppRequestInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param2Int);
        if (param2IRemoteCallback != null) {
          iBinder = param2IRemoteCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IInstantAppResolver.Stub.getDefaultImpl() != null) {
          IInstantAppResolver.Stub.getDefaultImpl().getInstantAppResolveInfoList(param2InstantAppRequestInfo, param2Int, param2IRemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IInstantAppResolver";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IInstantAppResolver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */