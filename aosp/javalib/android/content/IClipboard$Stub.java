package android.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IClipboard {
  private static final String DESCRIPTOR = "android.content.IClipboard";
  
  static final int TRANSACTION_addPrimaryClipChangedListener = 6;
  
  static final int TRANSACTION_clearPrimaryClip = 2;
  
  static final int TRANSACTION_getPrimaryClip = 3;
  
  static final int TRANSACTION_getPrimaryClipDescription = 4;
  
  static final int TRANSACTION_hasClipboardText = 8;
  
  static final int TRANSACTION_hasPrimaryClip = 5;
  
  static final int TRANSACTION_removePrimaryClipChangedListener = 7;
  
  static final int TRANSACTION_setPrimaryClip = 1;
  
  public Stub() {
    attachInterface(this, "android.content.IClipboard");
  }
  
  public static IClipboard asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.IClipboard");
    return (iInterface != null && iInterface instanceof IClipboard) ? (IClipboard)iInterface : new Proxy(paramIBinder);
  }
  
  public static IClipboard getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 8:
        return "hasClipboardText";
      case 7:
        return "removePrimaryClipChangedListener";
      case 6:
        return "addPrimaryClipChangedListener";
      case 5:
        return "hasPrimaryClip";
      case 4:
        return "getPrimaryClipDescription";
      case 3:
        return "getPrimaryClip";
      case 2:
        return "clearPrimaryClip";
      case 1:
        break;
    } 
    return "setPrimaryClip";
  }
  
  public static boolean setDefaultImpl(IClipboard paramIClipboard) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIClipboard != null) {
        Proxy.sDefaultImpl = paramIClipboard;
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
      boolean bool;
      ClipDescription clipDescription;
      ClipData clipData1;
      ClipData clipData2;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 8:
          paramParcel1.enforceInterface("android.content.IClipboard");
          bool = hasClipboardText(paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.content.IClipboard");
          removePrimaryClipChangedListener(IOnPrimaryClipChangedListener.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 6:
          paramParcel1.enforceInterface("android.content.IClipboard");
          addPrimaryClipChangedListener(IOnPrimaryClipChangedListener.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.content.IClipboard");
          bool = hasPrimaryClip(paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.content.IClipboard");
          clipDescription = getPrimaryClipDescription(paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (clipDescription != null) {
            paramParcel2.writeInt(1);
            clipDescription.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 3:
          clipDescription.enforceInterface("android.content.IClipboard");
          clipData1 = getPrimaryClip(clipDescription.readString(), clipDescription.readInt());
          paramParcel2.writeNoException();
          if (clipData1 != null) {
            paramParcel2.writeInt(1);
            clipData1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 2:
          clipData1.enforceInterface("android.content.IClipboard");
          clearPrimaryClip(clipData1.readString(), clipData1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      clipData1.enforceInterface("android.content.IClipboard");
      if (clipData1.readInt() != 0) {
        clipData2 = (ClipData)ClipData.CREATOR.createFromParcel((Parcel)clipData1);
      } else {
        clipData2 = null;
      } 
      setPrimaryClip(clipData2, clipData1.readString(), clipData1.readInt());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.content.IClipboard");
    return true;
  }
  
  private static class Proxy implements IClipboard {
    public static IClipboard sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener param2IOnPrimaryClipChangedListener, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.IClipboard");
        if (param2IOnPrimaryClipChangedListener != null) {
          iBinder = param2IOnPrimaryClipChangedListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          IClipboard.Stub.getDefaultImpl().addPrimaryClipChangedListener(param2IOnPrimaryClipChangedListener, param2String, param2Int);
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
    
    public void clearPrimaryClip(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          IClipboard.Stub.getDefaultImpl().clearPrimaryClip(param2String, param2Int);
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
    
    public ClipData getPrimaryClip(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null)
          return IClipboard.Stub.getDefaultImpl().getPrimaryClip(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ClipData clipData = (ClipData)ClipData.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ClipData)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ClipDescription getPrimaryClipDescription(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null)
          return IClipboard.Stub.getDefaultImpl().getPrimaryClipDescription(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ClipDescription clipDescription = (ClipDescription)ClipDescription.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ClipDescription)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasClipboardText(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          bool = IClipboard.Stub.getDefaultImpl().hasClipboardText(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasPrimaryClip(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(5, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          bool = IClipboard.Stub.getDefaultImpl().hasPrimaryClip(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener param2IOnPrimaryClipChangedListener, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.IClipboard");
        if (param2IOnPrimaryClipChangedListener != null) {
          iBinder = param2IOnPrimaryClipChangedListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          IClipboard.Stub.getDefaultImpl().removePrimaryClipChangedListener(param2IOnPrimaryClipChangedListener, param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPrimaryClip(ClipData param2ClipData, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        if (param2ClipData != null) {
          parcel1.writeInt(1);
          param2ClipData.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          IClipboard.Stub.getDefaultImpl().setPrimaryClip(param2ClipData, param2String, param2Int);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IClipboard$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */