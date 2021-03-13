package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPackageManagerNative {
  private static final String DESCRIPTOR = "android.content.pm.IPackageManagerNative";
  
  static final int TRANSACTION_getAllPackages = 8;
  
  static final int TRANSACTION_getInstallerForPackage = 2;
  
  static final int TRANSACTION_getLocationFlags = 5;
  
  static final int TRANSACTION_getModuleMetadataPackageName = 7;
  
  static final int TRANSACTION_getNamesForUids = 1;
  
  static final int TRANSACTION_getTargetSdkVersionForPackage = 6;
  
  static final int TRANSACTION_getVersionCodeForPackage = 3;
  
  static final int TRANSACTION_isAudioPlaybackCaptureAllowed = 4;
  
  static final int TRANSACTION_registerPackageChangeObserver = 9;
  
  static final int TRANSACTION_unregisterPackageChangeObserver = 10;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageManagerNative");
  }
  
  public static IPackageManagerNative asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageManagerNative");
    return (iInterface != null && iInterface instanceof IPackageManagerNative) ? (IPackageManagerNative)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageManagerNative getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 10:
        return "unregisterPackageChangeObserver";
      case 9:
        return "registerPackageChangeObserver";
      case 8:
        return "getAllPackages";
      case 7:
        return "getModuleMetadataPackageName";
      case 6:
        return "getTargetSdkVersionForPackage";
      case 5:
        return "getLocationFlags";
      case 4:
        return "isAudioPlaybackCaptureAllowed";
      case 3:
        return "getVersionCodeForPackage";
      case 2:
        return "getInstallerForPackage";
      case 1:
        break;
    } 
    return "getNamesForUids";
  }
  
  public static boolean setDefaultImpl(IPackageManagerNative paramIPackageManagerNative) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageManagerNative != null) {
        Proxy.sDefaultImpl = paramIPackageManagerNative;
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
      String[] arrayOfString2;
      String str2;
      boolean[] arrayOfBoolean;
      String str1;
      long l;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 10:
          paramParcel1.enforceInterface("android.content.pm.IPackageManagerNative");
          unregisterPackageChangeObserver(IPackageChangeObserver.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 9:
          paramParcel1.enforceInterface("android.content.pm.IPackageManagerNative");
          registerPackageChangeObserver(IPackageChangeObserver.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 8:
          paramParcel1.enforceInterface("android.content.pm.IPackageManagerNative");
          arrayOfString2 = getAllPackages();
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString2);
          return true;
        case 7:
          arrayOfString2.enforceInterface("android.content.pm.IPackageManagerNative");
          str2 = getModuleMetadataPackageName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 6:
          str2.enforceInterface("android.content.pm.IPackageManagerNative");
          paramInt1 = getTargetSdkVersionForPackage(str2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 5:
          str2.enforceInterface("android.content.pm.IPackageManagerNative");
          paramInt1 = getLocationFlags(str2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 4:
          str2.enforceInterface("android.content.pm.IPackageManagerNative");
          arrayOfBoolean = isAudioPlaybackCaptureAllowed(str2.createStringArray());
          paramParcel2.writeNoException();
          paramParcel2.writeBooleanArray(arrayOfBoolean);
          return true;
        case 3:
          arrayOfBoolean.enforceInterface("android.content.pm.IPackageManagerNative");
          l = getVersionCodeForPackage(arrayOfBoolean.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 2:
          arrayOfBoolean.enforceInterface("android.content.pm.IPackageManagerNative");
          str1 = getInstallerForPackage(arrayOfBoolean.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 1:
          break;
      } 
      str1.enforceInterface("android.content.pm.IPackageManagerNative");
      String[] arrayOfString1 = getNamesForUids(str1.createIntArray());
      paramParcel2.writeNoException();
      paramParcel2.writeStringArray(arrayOfString1);
      return true;
    } 
    paramParcel2.writeString("android.content.pm.IPackageManagerNative");
    return true;
  }
  
  private static class Proxy implements IPackageManagerNative {
    public static IPackageManagerNative sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
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
    
    public String getInstallerForPackage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null) {
          param2String = IPackageManagerNative.Stub.getDefaultImpl().getInstallerForPackage(param2String);
          return param2String;
        } 
        parcel2.readException();
        param2String = parcel2.readString();
        return param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageManagerNative";
    }
    
    public int getLocationFlags(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
          return IPackageManagerNative.Stub.getDefaultImpl().getLocationFlags(param2String); 
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
    
    public String[] getNamesForUids(int[] param2ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
          return IPackageManagerNative.Stub.getDefaultImpl().getNamesForUids(param2ArrayOfint); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getTargetSdkVersionForPackage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
          return IPackageManagerNative.Stub.getDefaultImpl().getTargetSdkVersionForPackage(param2String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getVersionCodeForPackage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
          return IPackageManagerNative.Stub.getDefaultImpl().getVersionCodeForPackage(param2String); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean[] isAudioPlaybackCaptureAllowed(String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null)
          return IPackageManagerNative.Stub.getDefaultImpl().isAudioPlaybackCaptureAllowed(param2ArrayOfString); 
        parcel2.readException();
        return parcel2.createBooleanArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerPackageChangeObserver(IPackageChangeObserver param2IPackageChangeObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
        if (param2IPackageChangeObserver != null) {
          iBinder = param2IPackageChangeObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null) {
          IPackageManagerNative.Stub.getDefaultImpl().registerPackageChangeObserver(param2IPackageChangeObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterPackageChangeObserver(IPackageChangeObserver param2IPackageChangeObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageManagerNative");
        if (param2IPackageChangeObserver != null) {
          iBinder = param2IPackageChangeObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IPackageManagerNative.Stub.getDefaultImpl() != null) {
          IPackageManagerNative.Stub.getDefaultImpl().unregisterPackageChangeObserver(param2IPackageChangeObserver);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageManagerNative$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */