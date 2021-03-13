package android.app.role;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IRoleManager {
  public static IRoleManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addOnRoleHoldersChangedListenerAsUser(IOnRoleHoldersChangedListener paramIOnRoleHoldersChangedListener, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      if (paramIOnRoleHoldersChangedListener != null) {
        iBinder = paramIOnRoleHoldersChangedListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
        IRoleManager.Stub.getDefaultImpl().addOnRoleHoldersChangedListenerAsUser(paramIOnRoleHoldersChangedListener, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addRoleHolderAsUser(String paramString1, String paramString2, int paramInt1, int paramInt2, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramRemoteCallback != null) {
        parcel1.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
        IRoleManager.Stub.getDefaultImpl().addRoleHolderAsUser(paramString1, paramString2, paramInt1, paramInt2, paramRemoteCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean addRoleHolderFromController(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(10, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
        bool = IRoleManager.Stub.getDefaultImpl().addRoleHolderFromController(paramString1, paramString2);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void clearRoleHoldersAsUser(String paramString, int paramInt1, int paramInt2, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramRemoteCallback != null) {
        parcel1.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
        IRoleManager.Stub.getDefaultImpl().clearRoleHoldersAsUser(paramString, paramInt1, paramInt2, paramRemoteCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getDefaultSmsPackage(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null)
        return IRoleManager.Stub.getDefaultImpl().getDefaultSmsPackage(paramInt); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getHeldRolesFromController(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null)
        return IRoleManager.Stub.getDefaultImpl().getHeldRolesFromController(paramString); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.role.IRoleManager";
  }
  
  public List<String> getRoleHoldersAsUser(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null)
        return IRoleManager.Stub.getDefaultImpl().getRoleHoldersAsUser(paramString, paramInt); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isRoleAvailable(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(1, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
        bool = IRoleManager.Stub.getDefaultImpl().isRoleAvailable(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isRoleHeld(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
        bool = IRoleManager.Stub.getDefaultImpl().isRoleHeld(paramString1, paramString2);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeOnRoleHoldersChangedListenerAsUser(IOnRoleHoldersChangedListener paramIOnRoleHoldersChangedListener, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      if (paramIOnRoleHoldersChangedListener != null) {
        iBinder = paramIOnRoleHoldersChangedListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
        IRoleManager.Stub.getDefaultImpl().removeOnRoleHoldersChangedListenerAsUser(paramIOnRoleHoldersChangedListener, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeRoleHolderAsUser(String paramString1, String paramString2, int paramInt1, int paramInt2, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramRemoteCallback != null) {
        parcel1.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
        IRoleManager.Stub.getDefaultImpl().removeRoleHolderAsUser(paramString1, paramString2, paramInt1, paramInt2, paramRemoteCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean removeRoleHolderFromController(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(11, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
        bool = IRoleManager.Stub.getDefaultImpl().removeRoleHolderFromController(paramString1, paramString2);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setRoleNamesFromController(List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.role.IRoleManager");
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IRoleManager.Stub.getDefaultImpl() != null) {
        IRoleManager.Stub.getDefaultImpl().setRoleNamesFromController(paramList);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/role/IRoleManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */