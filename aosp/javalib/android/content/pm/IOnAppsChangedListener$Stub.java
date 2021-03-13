package android.content.pm;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;

public abstract class Stub extends Binder implements IOnAppsChangedListener {
  private static final String DESCRIPTOR = "android.content.pm.IOnAppsChangedListener";
  
  static final int TRANSACTION_onPackageAdded = 2;
  
  static final int TRANSACTION_onPackageChanged = 3;
  
  static final int TRANSACTION_onPackageRemoved = 1;
  
  static final int TRANSACTION_onPackagesAvailable = 4;
  
  static final int TRANSACTION_onPackagesSuspended = 6;
  
  static final int TRANSACTION_onPackagesUnavailable = 5;
  
  static final int TRANSACTION_onPackagesUnsuspended = 7;
  
  static final int TRANSACTION_onShortcutChanged = 8;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IOnAppsChangedListener");
  }
  
  public static IOnAppsChangedListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IOnAppsChangedListener");
    return (iInterface != null && iInterface instanceof IOnAppsChangedListener) ? (IOnAppsChangedListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IOnAppsChangedListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 8:
        return "onShortcutChanged";
      case 7:
        return "onPackagesUnsuspended";
      case 6:
        return "onPackagesSuspended";
      case 5:
        return "onPackagesUnavailable";
      case 4:
        return "onPackagesAvailable";
      case 3:
        return "onPackageChanged";
      case 2:
        return "onPackageAdded";
      case 1:
        break;
    } 
    return "onPackageRemoved";
  }
  
  public static boolean setDefaultImpl(IOnAppsChangedListener paramIOnAppsChangedListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIOnAppsChangedListener != null) {
        Proxy.sDefaultImpl = paramIOnAppsChangedListener;
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
      String str;
      String[] arrayOfString;
      boolean bool1 = false;
      boolean bool2 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 8:
          paramParcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
          if (paramParcel1.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          str = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onShortcutChanged((UserHandle)paramParcel2, str, (ParceledListSlice)paramParcel1);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
          if (paramParcel1.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          onPackagesUnsuspended((UserHandle)paramParcel2, paramParcel1.createStringArray());
          return true;
        case 6:
          paramParcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
          if (paramParcel1.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          arrayOfString = paramParcel1.createStringArray();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onPackagesSuspended((UserHandle)paramParcel2, arrayOfString, (Bundle)paramParcel1);
          return true;
        case 5:
          paramParcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
          if (paramParcel1.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          arrayOfString = paramParcel1.createStringArray();
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          onPackagesUnavailable((UserHandle)paramParcel2, arrayOfString, bool2);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
          if (paramParcel1.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          arrayOfString = paramParcel1.createStringArray();
          bool2 = bool1;
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          onPackagesAvailable((UserHandle)paramParcel2, arrayOfString, bool2);
          return true;
        case 3:
          paramParcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
          if (paramParcel1.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          onPackageChanged((UserHandle)paramParcel2, paramParcel1.readString());
          return true;
        case 2:
          paramParcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
          if (paramParcel1.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          onPackageAdded((UserHandle)paramParcel2, paramParcel1.readString());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
      if (paramParcel1.readInt() != 0) {
        UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel2 = null;
      } 
      onPackageRemoved((UserHandle)paramParcel2, paramParcel1.readString());
      return true;
    } 
    paramParcel2.writeString("android.content.pm.IOnAppsChangedListener");
    return true;
  }
  
  private static class Proxy implements IOnAppsChangedListener {
    public static IOnAppsChangedListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IOnAppsChangedListener";
    }
    
    public void onPackageAdded(UserHandle param2UserHandle, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param2UserHandle != null) {
          parcel.writeInt(1);
          param2UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param2String);
        if (!this.mRemote.transact(2, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackageAdded(param2UserHandle, param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackageChanged(UserHandle param2UserHandle, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param2UserHandle != null) {
          parcel.writeInt(1);
          param2UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param2String);
        if (!this.mRemote.transact(3, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackageChanged(param2UserHandle, param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackageRemoved(UserHandle param2UserHandle, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param2UserHandle != null) {
          parcel.writeInt(1);
          param2UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param2String);
        if (!this.mRemote.transact(1, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackageRemoved(param2UserHandle, param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackagesAvailable(UserHandle param2UserHandle, String[] param2ArrayOfString, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        boolean bool = false;
        if (param2UserHandle != null) {
          parcel.writeInt(1);
          param2UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param2ArrayOfString);
        if (param2Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(4, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesAvailable(param2UserHandle, param2ArrayOfString, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackagesSuspended(UserHandle param2UserHandle, String[] param2ArrayOfString, Bundle param2Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param2UserHandle != null) {
          parcel.writeInt(1);
          param2UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param2ArrayOfString);
        if (param2Bundle != null) {
          parcel.writeInt(1);
          param2Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesSuspended(param2UserHandle, param2ArrayOfString, param2Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackagesUnavailable(UserHandle param2UserHandle, String[] param2ArrayOfString, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        boolean bool = false;
        if (param2UserHandle != null) {
          parcel.writeInt(1);
          param2UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param2ArrayOfString);
        if (param2Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(5, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesUnavailable(param2UserHandle, param2ArrayOfString, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackagesUnsuspended(UserHandle param2UserHandle, String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param2UserHandle != null) {
          parcel.writeInt(1);
          param2UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(7, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesUnsuspended(param2UserHandle, param2ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onShortcutChanged(UserHandle param2UserHandle, String param2String, ParceledListSlice param2ParceledListSlice) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param2UserHandle != null) {
          parcel.writeInt(1);
          param2UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param2String);
        if (param2ParceledListSlice != null) {
          parcel.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onShortcutChanged(param2UserHandle, param2String, param2ParceledListSlice);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IOnAppsChangedListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */