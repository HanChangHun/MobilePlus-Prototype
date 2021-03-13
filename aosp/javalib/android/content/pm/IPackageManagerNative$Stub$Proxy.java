package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPackageManagerNative {
  public static IPackageManagerNative sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String[] getAllPackages() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
        return IPackageManagerNative.Stub.getDefaultImpl().getAllPackages(); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInstallerForPackage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null) {
        paramString = IPackageManagerNative.Stub.getDefaultImpl().getInstallerForPackage(paramString);
        return paramString;
      } 
      parcel2.readException();
      paramString = parcel2.readString();
      return paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPackageManagerNative";
  }
  
  public int getLocationFlags(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
        return IPackageManagerNative.Stub.getDefaultImpl().getLocationFlags(paramString); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getModuleMetadataPackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
        return IPackageManagerNative.Stub.getDefaultImpl().getModuleMetadataPackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] getNamesForUids(int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
        return IPackageManagerNative.Stub.getDefaultImpl().getNamesForUids(paramArrayOfint); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getTargetSdkVersionForPackage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
        return IPackageManagerNative.Stub.getDefaultImpl().getTargetSdkVersionForPackage(paramString); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getVersionCodeForPackage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
        return IPackageManagerNative.Stub.getDefaultImpl().getVersionCodeForPackage(paramString); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean[] isAudioPlaybackCaptureAllowed(String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
        return IPackageManagerNative.Stub.getDefaultImpl().isAudioPlaybackCaptureAllowed(paramArrayOfString); 
      parcel2.readException();
      return parcel2.createBooleanArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerPackageChangeObserver(IPackageChangeObserver paramIPackageChangeObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
      if (paramIPackageChangeObserver != null) {
        iBinder = paramIPackageChangeObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null) {
        IPackageManagerNative.Stub.getDefaultImpl().registerPackageChangeObserver(paramIPackageChangeObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterPackageChangeObserver(IPackageChangeObserver paramIPackageChangeObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
      if (paramIPackageChangeObserver != null) {
        iBinder = paramIPackageChangeObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null) {
        IPackageManagerNative.Stub.getDefaultImpl().unregisterPackageChangeObserver(paramIPackageChangeObserver);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageManagerNative$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */