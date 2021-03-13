package android.content.pm;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;

class Proxy implements IOnAppsChangedListener {
  public static IOnAppsChangedListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IOnAppsChangedListener";
  }
  
  public void onPackageAdded(UserHandle paramUserHandle, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
      if (paramUserHandle != null) {
        parcel.writeInt(1);
        paramUserHandle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeString(paramString);
      if (!this.mRemote.transact(2, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
        IOnAppsChangedListener.Stub.getDefaultImpl().onPackageAdded(paramUserHandle, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPackageChanged(UserHandle paramUserHandle, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
      if (paramUserHandle != null) {
        parcel.writeInt(1);
        paramUserHandle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeString(paramString);
      if (!this.mRemote.transact(3, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
        IOnAppsChangedListener.Stub.getDefaultImpl().onPackageChanged(paramUserHandle, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPackageRemoved(UserHandle paramUserHandle, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
      if (paramUserHandle != null) {
        parcel.writeInt(1);
        paramUserHandle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeString(paramString);
      if (!this.mRemote.transact(1, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
        IOnAppsChangedListener.Stub.getDefaultImpl().onPackageRemoved(paramUserHandle, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPackagesAvailable(UserHandle paramUserHandle, String[] paramArrayOfString, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
      boolean bool = false;
      if (paramUserHandle != null) {
        parcel.writeInt(1);
        paramUserHandle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStringArray(paramArrayOfString);
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(4, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
        IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesAvailable(paramUserHandle, paramArrayOfString, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPackagesSuspended(UserHandle paramUserHandle, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
      if (paramUserHandle != null) {
        parcel.writeInt(1);
        paramUserHandle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStringArray(paramArrayOfString);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
        IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesSuspended(paramUserHandle, paramArrayOfString, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPackagesUnavailable(UserHandle paramUserHandle, String[] paramArrayOfString, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
      boolean bool = false;
      if (paramUserHandle != null) {
        parcel.writeInt(1);
        paramUserHandle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStringArray(paramArrayOfString);
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(5, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
        IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesUnavailable(paramUserHandle, paramArrayOfString, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPackagesUnsuspended(UserHandle paramUserHandle, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
      if (paramUserHandle != null) {
        parcel.writeInt(1);
        paramUserHandle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(7, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
        IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesUnsuspended(paramUserHandle, paramArrayOfString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onShortcutChanged(UserHandle paramUserHandle, String paramString, ParceledListSlice paramParceledListSlice) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
      if (paramUserHandle != null) {
        parcel.writeInt(1);
        paramUserHandle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeString(paramString);
      if (paramParceledListSlice != null) {
        parcel.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(8, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
        IOnAppsChangedListener.Stub.getDefaultImpl().onShortcutChanged(paramUserHandle, paramString, paramParceledListSlice);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IOnAppsChangedListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */