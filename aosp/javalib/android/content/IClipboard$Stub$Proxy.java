package android.content;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IClipboard {
  public static IClipboard sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener paramIOnPrimaryClipChangedListener, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.IClipboard");
      if (paramIOnPrimaryClipChangedListener != null) {
        iBinder = paramIOnPrimaryClipChangedListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
        IClipboard.Stub.getDefaultImpl().addPrimaryClipChangedListener(paramIOnPrimaryClipChangedListener, paramString, paramInt);
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
  
  public void clearPrimaryClip(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IClipboard");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
        IClipboard.Stub.getDefaultImpl().clearPrimaryClip(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.IClipboard";
  }
  
  public ClipData getPrimaryClip(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IClipboard");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null)
        return IClipboard.Stub.getDefaultImpl().getPrimaryClip(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ClipData clipData = (ClipData)ClipData.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ClipData)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ClipDescription getPrimaryClipDescription(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IClipboard");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null)
        return IClipboard.Stub.getDefaultImpl().getPrimaryClipDescription(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ClipDescription clipDescription = (ClipDescription)ClipDescription.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ClipDescription)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasClipboardText(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IClipboard");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(8, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
        bool = IClipboard.Stub.getDefaultImpl().hasClipboardText(paramString, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasPrimaryClip(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IClipboard");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(5, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
        bool = IClipboard.Stub.getDefaultImpl().hasPrimaryClip(paramString, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener paramIOnPrimaryClipChangedListener, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.IClipboard");
      if (paramIOnPrimaryClipChangedListener != null) {
        iBinder = paramIOnPrimaryClipChangedListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
        IClipboard.Stub.getDefaultImpl().removePrimaryClipChangedListener(paramIOnPrimaryClipChangedListener, paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPrimaryClip(ClipData paramClipData, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IClipboard");
      if (paramClipData != null) {
        parcel1.writeInt(1);
        paramClipData.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
        IClipboard.Stub.getDefaultImpl().setPrimaryClip(paramClipData, paramString, paramInt);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/IClipboard$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */