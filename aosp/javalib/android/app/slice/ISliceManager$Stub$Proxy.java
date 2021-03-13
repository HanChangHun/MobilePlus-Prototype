package android.app.slice;

import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

class Proxy implements ISliceManager {
  public static ISliceManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void applyRestore(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
      parcel1.writeByteArray(paramArrayOfbyte);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
        ISliceManager.Stub.getDefaultImpl().applyRestore(paramArrayOfbyte, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public int checkSlicePermission(Uri paramUri, String paramString1, String paramString2, int paramInt1, int paramInt2, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          try {
            parcel1.writeInt(paramInt1);
            try {
              parcel1.writeInt(paramInt2);
              try {
                parcel1.writeStringArray(paramArrayOfString);
                if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
                  paramInt1 = ISliceManager.Stub.getDefaultImpl().checkSlicePermission(paramUri, paramString1, paramString2, paramInt1, paramInt2, paramArrayOfString);
                  parcel2.recycle();
                  parcel1.recycle();
                  return paramInt1;
                } 
                parcel2.readException();
                paramInt1 = parcel2.readInt();
                parcel2.recycle();
                parcel1.recycle();
                return paramInt1;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramUri;
  }
  
  public byte[] getBackupPayload(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null)
        return ISliceManager.Stub.getDefaultImpl().getBackupPayload(paramInt); 
      parcel2.readException();
      return parcel2.createByteArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.slice.ISliceManager";
  }
  
  public Uri[] getPinnedSlices(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null)
        return ISliceManager.Stub.getDefaultImpl().getPinnedSlices(paramString); 
      parcel2.readException();
      return (Uri[])parcel2.createTypedArray(Uri.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public SliceSpec[] getPinnedSpecs(Uri paramUri, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null)
        return ISliceManager.Stub.getDefaultImpl().getPinnedSpecs(paramUri, paramString); 
      parcel2.readException();
      return (SliceSpec[])parcel2.createTypedArray(SliceSpec.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void grantPermissionFromUser(Uri paramUri, String paramString1, String paramString2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
      boolean bool = true;
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
        ISliceManager.Stub.getDefaultImpl().grantPermissionFromUser(paramUri, paramString1, paramString2, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void grantSlicePermission(String paramString1, String paramString2, Uri paramUri) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
        ISliceManager.Stub.getDefaultImpl().grantSlicePermission(paramString1, paramString2, paramUri);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasSliceAccess(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(3, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
        bool = ISliceManager.Stub.getDefaultImpl().hasSliceAccess(paramString);
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
  
  public void pinSlice(String paramString, Uri paramUri, SliceSpec[] paramArrayOfSliceSpec, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
      parcel1.writeString(paramString);
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeTypedArray((Parcelable[])paramArrayOfSliceSpec, 0);
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
        ISliceManager.Stub.getDefaultImpl().pinSlice(paramString, paramUri, paramArrayOfSliceSpec, paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void revokeSlicePermission(String paramString1, String paramString2, Uri paramUri) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
        ISliceManager.Stub.getDefaultImpl().revokeSlicePermission(paramString1, paramString2, paramUri);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unpinSlice(String paramString, Uri paramUri, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
      parcel1.writeString(paramString);
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
        ISliceManager.Stub.getDefaultImpl().unpinSlice(paramString, paramUri, paramIBinder);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/ISliceManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */