package android.app;

import android.content.pm.ParceledListSlice;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IUriGrantsManager {
  public static IUriGrantsManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void clearGrantedUriPermissions(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
        IUriGrantsManager.Stub.getDefaultImpl().clearGrantedUriPermissions(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getGrantedUriPermissions(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null)
        return IUriGrantsManager.Stub.getDefaultImpl().getGrantedUriPermissions(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IUriGrantsManager";
  }
  
  public ParceledListSlice getUriPermissions(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
      parcel1.writeString(paramString);
      boolean bool1 = true;
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean2) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null)
        return IUriGrantsManager.Stub.getDefaultImpl().getUriPermissions(paramString, paramBoolean1, paramBoolean2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void grantUriPermissionFromOwner(IBinder paramIBinder, int paramInt1, String paramString, Uri paramUri, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
      try {
        parcel1.writeStrongBinder(paramIBinder);
        try {
          parcel1.writeInt(paramInt1);
          try {
            parcel1.writeString(paramString);
            if (paramUri != null) {
              parcel1.writeInt(1);
              paramUri.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeInt(paramInt2);
              parcel1.writeInt(paramInt3);
              parcel1.writeInt(paramInt4);
              if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
                IUriGrantsManager.Stub.getDefaultImpl().grantUriPermissionFromOwner(paramIBinder, paramInt1, paramString, paramUri, paramInt2, paramInt3, paramInt4);
                parcel2.recycle();
                parcel1.recycle();
                return;
              } 
              parcel2.readException();
              parcel2.recycle();
              parcel1.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIBinder;
  }
  
  public void releasePersistableUriPermission(Uri paramUri, int paramInt1, String paramString, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
        IUriGrantsManager.Stub.getDefaultImpl().releasePersistableUriPermission(paramUri, paramInt1, paramString, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void takePersistableUriPermission(Uri paramUri, int paramInt1, String paramString, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
        IUriGrantsManager.Stub.getDefaultImpl().takePersistableUriPermission(paramUri, paramInt1, paramString, paramInt2);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IUriGrantsManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */