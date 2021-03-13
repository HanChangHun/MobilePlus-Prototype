package android.app.slice;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISliceManager {
  private static final String DESCRIPTOR = "android.app.slice.ISliceManager";
  
  static final int TRANSACTION_applyRestore = 7;
  
  static final int TRANSACTION_checkSlicePermission = 10;
  
  static final int TRANSACTION_getBackupPayload = 6;
  
  static final int TRANSACTION_getPinnedSlices = 5;
  
  static final int TRANSACTION_getPinnedSpecs = 4;
  
  static final int TRANSACTION_grantPermissionFromUser = 11;
  
  static final int TRANSACTION_grantSlicePermission = 8;
  
  static final int TRANSACTION_hasSliceAccess = 3;
  
  static final int TRANSACTION_pinSlice = 1;
  
  static final int TRANSACTION_revokeSlicePermission = 9;
  
  static final int TRANSACTION_unpinSlice = 2;
  
  public Stub() {
    attachInterface(this, "android.app.slice.ISliceManager");
  }
  
  public static ISliceManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.slice.ISliceManager");
    return (iInterface != null && iInterface instanceof ISliceManager) ? (ISliceManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISliceManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 11:
        return "grantPermissionFromUser";
      case 10:
        return "checkSlicePermission";
      case 9:
        return "revokeSlicePermission";
      case 8:
        return "grantSlicePermission";
      case 7:
        return "applyRestore";
      case 6:
        return "getBackupPayload";
      case 5:
        return "getPinnedSlices";
      case 4:
        return "getPinnedSpecs";
      case 3:
        return "hasSliceAccess";
      case 2:
        return "unpinSlice";
      case 1:
        break;
    } 
    return "pinSlice";
  }
  
  public static boolean setDefaultImpl(ISliceManager paramISliceManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISliceManager != null) {
        Proxy.sDefaultImpl = paramISliceManager;
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
      byte[] arrayOfByte;
      Uri[] arrayOfUri;
      SliceSpec[] arrayOfSliceSpec;
      Uri uri;
      String str1;
      String str3;
      boolean bool1;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 11:
          paramParcel1.enforceInterface("android.app.slice.ISliceManager");
          if (paramParcel1.readInt() != 0) {
            uri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          } else {
            uri = null;
          } 
          str2 = paramParcel1.readString();
          str3 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          grantPermissionFromUser(uri, str2, str3, bool1);
          paramParcel2.writeNoException();
          return true;
        case 10:
          paramParcel1.enforceInterface("android.app.slice.ISliceManager");
          if (paramParcel1.readInt() != 0) {
            uri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          } else {
            uri = null;
          } 
          paramInt1 = checkSlicePermission(uri, paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createStringArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 9:
          paramParcel1.enforceInterface("android.app.slice.ISliceManager");
          str2 = paramParcel1.readString();
          str1 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            Uri uri1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          revokeSlicePermission(str2, str1, (Uri)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 8:
          paramParcel1.enforceInterface("android.app.slice.ISliceManager");
          str2 = paramParcel1.readString();
          str1 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            Uri uri1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          grantSlicePermission(str2, str1, (Uri)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 7:
          paramParcel1.enforceInterface("android.app.slice.ISliceManager");
          applyRestore(paramParcel1.createByteArray(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 6:
          paramParcel1.enforceInterface("android.app.slice.ISliceManager");
          arrayOfByte = getBackupPayload(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeByteArray(arrayOfByte);
          return true;
        case 5:
          arrayOfByte.enforceInterface("android.app.slice.ISliceManager");
          arrayOfUri = getPinnedSlices(arrayOfByte.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfUri, 1);
          return true;
        case 4:
          arrayOfUri.enforceInterface("android.app.slice.ISliceManager");
          if (arrayOfUri.readInt() != 0) {
            Uri uri1 = (Uri)Uri.CREATOR.createFromParcel((Parcel)arrayOfUri);
          } else {
            str1 = null;
          } 
          arrayOfSliceSpec = getPinnedSpecs((Uri)str1, arrayOfUri.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfSliceSpec, 1);
          return true;
        case 3:
          arrayOfSliceSpec.enforceInterface("android.app.slice.ISliceManager");
          bool = hasSliceAccess(arrayOfSliceSpec.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 2:
          arrayOfSliceSpec.enforceInterface("android.app.slice.ISliceManager");
          str2 = arrayOfSliceSpec.readString();
          if (arrayOfSliceSpec.readInt() != 0) {
            Uri uri1 = (Uri)Uri.CREATOR.createFromParcel((Parcel)arrayOfSliceSpec);
          } else {
            str1 = null;
          } 
          unpinSlice(str2, (Uri)str1, arrayOfSliceSpec.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      arrayOfSliceSpec.enforceInterface("android.app.slice.ISliceManager");
      String str2 = arrayOfSliceSpec.readString();
      if (arrayOfSliceSpec.readInt() != 0) {
        Uri uri1 = (Uri)Uri.CREATOR.createFromParcel((Parcel)arrayOfSliceSpec);
      } else {
        str1 = null;
      } 
      pinSlice(str2, (Uri)str1, (SliceSpec[])arrayOfSliceSpec.createTypedArray(SliceSpec.CREATOR), arrayOfSliceSpec.readStrongBinder());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.app.slice.ISliceManager");
    return true;
  }
  
  private static class Proxy implements ISliceManager {
    public static ISliceManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void applyRestore(byte[] param2ArrayOfbyte, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
        parcel1.writeByteArray(param2ArrayOfbyte);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
          ISliceManager.Stub.getDefaultImpl().applyRestore(param2ArrayOfbyte, param2Int);
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
    
    public int checkSlicePermission(Uri param2Uri, String param2String1, String param2String2, int param2Int1, int param2Int2, String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
        if (param2Uri != null) {
          parcel1.writeInt(1);
          param2Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeString(param2String2);
            try {
              parcel1.writeInt(param2Int1);
              try {
                parcel1.writeInt(param2Int2);
                try {
                  parcel1.writeStringArray(param2ArrayOfString);
                  if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
                    param2Int1 = ISliceManager.Stub.getDefaultImpl().checkSlicePermission(param2Uri, param2String1, param2String2, param2Int1, param2Int2, param2ArrayOfString);
                    parcel2.recycle();
                    parcel1.recycle();
                    return param2Int1;
                  } 
                  parcel2.readException();
                  param2Int1 = parcel2.readInt();
                  parcel2.recycle();
                  parcel1.recycle();
                  return param2Int1;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2Uri;
    }
    
    public byte[] getBackupPayload(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null)
          return ISliceManager.Stub.getDefaultImpl().getBackupPayload(param2Int); 
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
    
    public Uri[] getPinnedSlices(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null)
          return ISliceManager.Stub.getDefaultImpl().getPinnedSlices(param2String); 
        parcel2.readException();
        return (Uri[])parcel2.createTypedArray(Uri.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public SliceSpec[] getPinnedSpecs(Uri param2Uri, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
        if (param2Uri != null) {
          parcel1.writeInt(1);
          param2Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null)
          return ISliceManager.Stub.getDefaultImpl().getPinnedSpecs(param2Uri, param2String); 
        parcel2.readException();
        return (SliceSpec[])parcel2.createTypedArray(SliceSpec.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void grantPermissionFromUser(Uri param2Uri, String param2String1, String param2String2, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
        boolean bool = true;
        if (param2Uri != null) {
          parcel1.writeInt(1);
          param2Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
          ISliceManager.Stub.getDefaultImpl().grantPermissionFromUser(param2Uri, param2String1, param2String2, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void grantSlicePermission(String param2String1, String param2String2, Uri param2Uri) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (param2Uri != null) {
          parcel1.writeInt(1);
          param2Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
          ISliceManager.Stub.getDefaultImpl().grantSlicePermission(param2String1, param2String2, param2Uri);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasSliceAccess(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(3, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
          bool = ISliceManager.Stub.getDefaultImpl().hasSliceAccess(param2String);
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
    
    public void pinSlice(String param2String, Uri param2Uri, SliceSpec[] param2ArrayOfSliceSpec, IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
        parcel1.writeString(param2String);
        if (param2Uri != null) {
          parcel1.writeInt(1);
          param2Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeTypedArray((Parcelable[])param2ArrayOfSliceSpec, 0);
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
          ISliceManager.Stub.getDefaultImpl().pinSlice(param2String, param2Uri, param2ArrayOfSliceSpec, param2IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void revokeSlicePermission(String param2String1, String param2String2, Uri param2Uri) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (param2Uri != null) {
          parcel1.writeInt(1);
          param2Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
          ISliceManager.Stub.getDefaultImpl().revokeSlicePermission(param2String1, param2String2, param2Uri);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unpinSlice(String param2String, Uri param2Uri, IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.slice.ISliceManager");
        parcel1.writeString(param2String);
        if (param2Uri != null) {
          parcel1.writeInt(1);
          param2Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISliceManager.Stub.getDefaultImpl() != null) {
          ISliceManager.Stub.getDefaultImpl().unpinSlice(param2String, param2Uri, param2IBinder);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/ISliceManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */