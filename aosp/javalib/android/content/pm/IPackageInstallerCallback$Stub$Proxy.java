package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPackageInstallerCallback {
  public static IPackageInstallerCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPackageInstallerCallback";
  }
  
  public void onSessionActiveChanged(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
      parcel.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(3, parcel, null, 1) && IPackageInstallerCallback.Stub.getDefaultImpl() != null) {
        IPackageInstallerCallback.Stub.getDefaultImpl().onSessionActiveChanged(paramInt, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSessionBadgingChanged(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IPackageInstallerCallback.Stub.getDefaultImpl() != null) {
        IPackageInstallerCallback.Stub.getDefaultImpl().onSessionBadgingChanged(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSessionCreated(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IPackageInstallerCallback.Stub.getDefaultImpl() != null) {
        IPackageInstallerCallback.Stub.getDefaultImpl().onSessionCreated(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSessionFinished(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
      parcel.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(5, parcel, null, 1) && IPackageInstallerCallback.Stub.getDefaultImpl() != null) {
        IPackageInstallerCallback.Stub.getDefaultImpl().onSessionFinished(paramInt, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSessionProgressChanged(int paramInt, float paramFloat) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageInstallerCallback");
      parcel.writeInt(paramInt);
      parcel.writeFloat(paramFloat);
      if (!this.mRemote.transact(4, parcel, null, 1) && IPackageInstallerCallback.Stub.getDefaultImpl() != null) {
        IPackageInstallerCallback.Stub.getDefaultImpl().onSessionProgressChanged(paramInt, paramFloat);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallerCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */