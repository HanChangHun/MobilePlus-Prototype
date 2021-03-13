package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPackageInstallerCallback {
  private static final String DESCRIPTOR = "android.content.pm.IPackageInstallerCallback";
  
  static final int TRANSACTION_onSessionActiveChanged = 3;
  
  static final int TRANSACTION_onSessionBadgingChanged = 2;
  
  static final int TRANSACTION_onSessionCreated = 1;
  
  static final int TRANSACTION_onSessionFinished = 5;
  
  static final int TRANSACTION_onSessionProgressChanged = 4;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageInstallerCallback");
  }
  
  public static IPackageInstallerCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageInstallerCallback");
    return (iInterface != null && iInterface instanceof IPackageInstallerCallback) ? (IPackageInstallerCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageInstallerCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "onSessionFinished") : "onSessionProgressChanged") : "onSessionActiveChanged") : "onSessionBadgingChanged") : "onSessionCreated";
  }
  
  public static boolean setDefaultImpl(IPackageInstallerCallback paramIPackageInstallerCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageInstallerCallback != null) {
        Proxy.sDefaultImpl = paramIPackageInstallerCallback;
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
        boolean bool1 = false;
        boolean bool2 = false;
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.content.pm.IPackageInstallerCallback");
              return true;
            } 
            paramParcel1.enforceInterface("android.content.pm.IPackageInstallerCallback");
            paramInt1 = paramParcel1.readInt();
            if (paramParcel1.readInt() != 0)
              bool2 = true; 
            onSessionFinished(paramInt1, bool2);
            return true;
          } 
          paramParcel1.enforceInterface("android.content.pm.IPackageInstallerCallback");
          onSessionProgressChanged(paramParcel1.readInt(), paramParcel1.readFloat());
          return true;
        } 
        paramParcel1.enforceInterface("android.content.pm.IPackageInstallerCallback");
        paramInt1 = paramParcel1.readInt();
        bool2 = bool1;
        if (paramParcel1.readInt() != 0)
          bool2 = true; 
        onSessionActiveChanged(paramInt1, bool2);
        return true;
      } 
      paramParcel1.enforceInterface("android.content.pm.IPackageInstallerCallback");
      onSessionBadgingChanged(paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IPackageInstallerCallback");
    onSessionCreated(paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IPackageInstallerCallback {
    public static IPackageInstallerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageInstallerCallback";
    }
    
    public void onSessionActiveChanged(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
        parcel.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(3, parcel, null, 1) && IPackageInstallerCallback.Stub.getDefaultImpl() != null) {
          IPackageInstallerCallback.Stub.getDefaultImpl().onSessionActiveChanged(param2Int, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSessionBadgingChanged(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IPackageInstallerCallback.Stub.getDefaultImpl() != null) {
          IPackageInstallerCallback.Stub.getDefaultImpl().onSessionBadgingChanged(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSessionCreated(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IPackageInstallerCallback.Stub.getDefaultImpl() != null) {
          IPackageInstallerCallback.Stub.getDefaultImpl().onSessionCreated(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSessionFinished(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
        parcel.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(5, parcel, null, 1) && IPackageInstallerCallback.Stub.getDefaultImpl() != null) {
          IPackageInstallerCallback.Stub.getDefaultImpl().onSessionFinished(param2Int, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSessionProgressChanged(int param2Int, float param2Float) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
        parcel.writeInt(param2Int);
        parcel.writeFloat(param2Float);
        if (!this.mRemote.transact(4, parcel, null, 1) && IPackageInstallerCallback.Stub.getDefaultImpl() != null) {
          IPackageInstallerCallback.Stub.getDefaultImpl().onSessionProgressChanged(param2Int, param2Float);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallerCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */