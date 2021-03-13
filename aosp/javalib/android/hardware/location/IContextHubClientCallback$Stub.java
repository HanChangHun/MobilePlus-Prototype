package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IContextHubClientCallback {
  private static final String DESCRIPTOR = "android.hardware.location.IContextHubClientCallback";
  
  static final int TRANSACTION_onHubReset = 2;
  
  static final int TRANSACTION_onMessageFromNanoApp = 1;
  
  static final int TRANSACTION_onNanoAppAborted = 3;
  
  static final int TRANSACTION_onNanoAppDisabled = 7;
  
  static final int TRANSACTION_onNanoAppEnabled = 6;
  
  static final int TRANSACTION_onNanoAppLoaded = 4;
  
  static final int TRANSACTION_onNanoAppUnloaded = 5;
  
  public Stub() {
    attachInterface(this, "android.hardware.location.IContextHubClientCallback");
  }
  
  public static IContextHubClientCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IContextHubClientCallback");
    return (iInterface != null && iInterface instanceof IContextHubClientCallback) ? (IContextHubClientCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IContextHubClientCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 7:
        return "onNanoAppDisabled";
      case 6:
        return "onNanoAppEnabled";
      case 5:
        return "onNanoAppUnloaded";
      case 4:
        return "onNanoAppLoaded";
      case 3:
        return "onNanoAppAborted";
      case 2:
        return "onHubReset";
      case 1:
        break;
    } 
    return "onMessageFromNanoApp";
  }
  
  public static boolean setDefaultImpl(IContextHubClientCallback paramIContextHubClientCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIContextHubClientCallback != null) {
        Proxy.sDefaultImpl = paramIContextHubClientCallback;
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
    if (paramInt1 != 1598968902) {
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 7:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
          onNanoAppDisabled(paramParcel1.readLong());
          return true;
        case 6:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
          onNanoAppEnabled(paramParcel1.readLong());
          return true;
        case 5:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
          onNanoAppUnloaded(paramParcel1.readLong());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
          onNanoAppLoaded(paramParcel1.readLong());
          return true;
        case 3:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
          onNanoAppAborted(paramParcel1.readLong(), paramParcel1.readInt());
          return true;
        case 2:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
          onHubReset();
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
      if (paramParcel1.readInt() != 0) {
        NanoAppMessage nanoAppMessage = (NanoAppMessage)NanoAppMessage.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      onMessageFromNanoApp((NanoAppMessage)paramParcel1);
      return true;
    } 
    paramParcel2.writeString("android.hardware.location.IContextHubClientCallback");
    return true;
  }
  
  private static class Proxy implements IContextHubClientCallback {
    public static IContextHubClientCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IContextHubClientCallback";
    }
    
    public void onHubReset() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        if (!this.mRemote.transact(2, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onHubReset();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onMessageFromNanoApp(NanoAppMessage param2NanoAppMessage) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        if (param2NanoAppMessage != null) {
          parcel.writeInt(1);
          param2NanoAppMessage.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onMessageFromNanoApp(param2NanoAppMessage);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNanoAppAborted(long param2Long, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppAborted(param2Long, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNanoAppDisabled(long param2Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        parcel.writeLong(param2Long);
        if (!this.mRemote.transact(7, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppDisabled(param2Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNanoAppEnabled(long param2Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        parcel.writeLong(param2Long);
        if (!this.mRemote.transact(6, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppEnabled(param2Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNanoAppLoaded(long param2Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        parcel.writeLong(param2Long);
        if (!this.mRemote.transact(4, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppLoaded(param2Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNanoAppUnloaded(long param2Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        parcel.writeLong(param2Long);
        if (!this.mRemote.transact(5, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppUnloaded(param2Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubClientCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */