package android.app.role;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;

class Proxy implements IRoleController {
  public static IRoleController sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.role.IRoleController";
  }
  
  public void grantDefaultRoles(RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.role.IRoleController");
      if (paramRemoteCallback != null) {
        parcel.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
        IRoleController.Stub.getDefaultImpl().grantDefaultRoles(paramRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void isApplicationQualifiedForRole(String paramString1, String paramString2, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.role.IRoleController");
      parcel.writeString(paramString1);
      parcel.writeString(paramString2);
      if (paramRemoteCallback != null) {
        parcel.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(5, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
        IRoleController.Stub.getDefaultImpl().isApplicationQualifiedForRole(paramString1, paramString2, paramRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void isApplicationVisibleForRole(String paramString1, String paramString2, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.role.IRoleController");
      parcel.writeString(paramString1);
      parcel.writeString(paramString2);
      if (paramRemoteCallback != null) {
        parcel.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
        IRoleController.Stub.getDefaultImpl().isApplicationVisibleForRole(paramString1, paramString2, paramRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void isRoleVisible(String paramString, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.role.IRoleController");
      parcel.writeString(paramString);
      if (paramRemoteCallback != null) {
        parcel.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(7, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
        IRoleController.Stub.getDefaultImpl().isRoleVisible(paramString, paramRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAddRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.role.IRoleController");
      parcel.writeString(paramString1);
      parcel.writeString(paramString2);
      parcel.writeInt(paramInt);
      if (paramRemoteCallback != null) {
        parcel.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
        IRoleController.Stub.getDefaultImpl().onAddRoleHolder(paramString1, paramString2, paramInt, paramRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onClearRoleHolders(String paramString, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.role.IRoleController");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt);
      if (paramRemoteCallback != null) {
        parcel.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(4, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
        IRoleController.Stub.getDefaultImpl().onClearRoleHolders(paramString, paramInt, paramRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onRemoveRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.role.IRoleController");
      parcel.writeString(paramString1);
      parcel.writeString(paramString2);
      parcel.writeInt(paramInt);
      if (paramRemoteCallback != null) {
        parcel.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel, null, 1) && IRoleController.Stub.getDefaultImpl() != null) {
        IRoleController.Stub.getDefaultImpl().onRemoveRoleHolder(paramString1, paramString2, paramInt, paramRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/IRoleController$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */