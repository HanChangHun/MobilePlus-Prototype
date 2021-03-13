package android.content.pm;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;

public interface IOnAppsChangedListener extends IInterface {
  void onPackageAdded(UserHandle paramUserHandle, String paramString) throws RemoteException;
  
  void onPackageChanged(UserHandle paramUserHandle, String paramString) throws RemoteException;
  
  void onPackageRemoved(UserHandle paramUserHandle, String paramString) throws RemoteException;
  
  void onPackagesAvailable(UserHandle paramUserHandle, String[] paramArrayOfString, boolean paramBoolean) throws RemoteException;
  
  void onPackagesSuspended(UserHandle paramUserHandle, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException;
  
  void onPackagesUnavailable(UserHandle paramUserHandle, String[] paramArrayOfString, boolean paramBoolean) throws RemoteException;
  
  void onPackagesUnsuspended(UserHandle paramUserHandle, String[] paramArrayOfString) throws RemoteException;
  
  void onShortcutChanged(UserHandle paramUserHandle, String paramString, ParceledListSlice paramParceledListSlice) throws RemoteException;
  
  public static class Default implements IOnAppsChangedListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onPackageAdded(UserHandle param1UserHandle, String param1String) throws RemoteException {}
    
    public void onPackageChanged(UserHandle param1UserHandle, String param1String) throws RemoteException {}
    
    public void onPackageRemoved(UserHandle param1UserHandle, String param1String) throws RemoteException {}
    
    public void onPackagesAvailable(UserHandle param1UserHandle, String[] param1ArrayOfString, boolean param1Boolean) throws RemoteException {}
    
    public void onPackagesSuspended(UserHandle param1UserHandle, String[] param1ArrayOfString, Bundle param1Bundle) throws RemoteException {}
    
    public void onPackagesUnavailable(UserHandle param1UserHandle, String[] param1ArrayOfString, boolean param1Boolean) throws RemoteException {}
    
    public void onPackagesUnsuspended(UserHandle param1UserHandle, String[] param1ArrayOfString) throws RemoteException {}
    
    public void onShortcutChanged(UserHandle param1UserHandle, String param1String, ParceledListSlice param1ParceledListSlice) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IOnAppsChangedListener {
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
    
    public static IOnAppsChangedListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IOnAppsChangedListener");
      return (iInterface != null && iInterface instanceof IOnAppsChangedListener) ? (IOnAppsChangedListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IOnAppsChangedListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IOnAppsChangedListener param1IOnAppsChangedListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IOnAppsChangedListener != null) {
          Proxy.sDefaultImpl = param1IOnAppsChangedListener;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1598968902) {
        String str;
        String[] arrayOfString;
        boolean bool1 = false;
        boolean bool2 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 8:
            param1Parcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
            if (param1Parcel1.readInt() != 0) {
              UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            str = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onShortcutChanged((UserHandle)param1Parcel2, str, (ParceledListSlice)param1Parcel1);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
            if (param1Parcel1.readInt() != 0) {
              UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            onPackagesUnsuspended((UserHandle)param1Parcel2, param1Parcel1.createStringArray());
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
            if (param1Parcel1.readInt() != 0) {
              UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            arrayOfString = param1Parcel1.createStringArray();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onPackagesSuspended((UserHandle)param1Parcel2, arrayOfString, (Bundle)param1Parcel1);
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
            if (param1Parcel1.readInt() != 0) {
              UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            arrayOfString = param1Parcel1.createStringArray();
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            onPackagesUnavailable((UserHandle)param1Parcel2, arrayOfString, bool2);
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
            if (param1Parcel1.readInt() != 0) {
              UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            arrayOfString = param1Parcel1.createStringArray();
            bool2 = bool1;
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            onPackagesAvailable((UserHandle)param1Parcel2, arrayOfString, bool2);
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
            if (param1Parcel1.readInt() != 0) {
              UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            onPackageChanged((UserHandle)param1Parcel2, param1Parcel1.readString());
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
            if (param1Parcel1.readInt() != 0) {
              UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            onPackageAdded((UserHandle)param1Parcel2, param1Parcel1.readString());
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.content.pm.IOnAppsChangedListener");
        if (param1Parcel1.readInt() != 0) {
          UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel2 = null;
        } 
        onPackageRemoved((UserHandle)param1Parcel2, param1Parcel1.readString());
        return true;
      } 
      param1Parcel2.writeString("android.content.pm.IOnAppsChangedListener");
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
  
  private static class Proxy implements IOnAppsChangedListener {
    public static IOnAppsChangedListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IOnAppsChangedListener";
    }
    
    public void onPackageAdded(UserHandle param1UserHandle, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param1UserHandle != null) {
          parcel.writeInt(1);
          param1UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param1String);
        if (!this.mRemote.transact(2, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackageAdded(param1UserHandle, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackageChanged(UserHandle param1UserHandle, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param1UserHandle != null) {
          parcel.writeInt(1);
          param1UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param1String);
        if (!this.mRemote.transact(3, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackageChanged(param1UserHandle, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackageRemoved(UserHandle param1UserHandle, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param1UserHandle != null) {
          parcel.writeInt(1);
          param1UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param1String);
        if (!this.mRemote.transact(1, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackageRemoved(param1UserHandle, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackagesAvailable(UserHandle param1UserHandle, String[] param1ArrayOfString, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        boolean bool = false;
        if (param1UserHandle != null) {
          parcel.writeInt(1);
          param1UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param1ArrayOfString);
        if (param1Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(4, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesAvailable(param1UserHandle, param1ArrayOfString, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackagesSuspended(UserHandle param1UserHandle, String[] param1ArrayOfString, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param1UserHandle != null) {
          parcel.writeInt(1);
          param1UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param1ArrayOfString);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesSuspended(param1UserHandle, param1ArrayOfString, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackagesUnavailable(UserHandle param1UserHandle, String[] param1ArrayOfString, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        boolean bool = false;
        if (param1UserHandle != null) {
          parcel.writeInt(1);
          param1UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param1ArrayOfString);
        if (param1Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(5, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesUnavailable(param1UserHandle, param1ArrayOfString, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPackagesUnsuspended(UserHandle param1UserHandle, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param1UserHandle != null) {
          parcel.writeInt(1);
          param1UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(7, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onPackagesUnsuspended(param1UserHandle, param1ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onShortcutChanged(UserHandle param1UserHandle, String param1String, ParceledListSlice param1ParceledListSlice) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IOnAppsChangedListener");
        if (param1UserHandle != null) {
          parcel.writeInt(1);
          param1UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param1String);
        if (param1ParceledListSlice != null) {
          parcel.writeInt(1);
          param1ParceledListSlice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel, null, 1) && IOnAppsChangedListener.Stub.getDefaultImpl() != null) {
          IOnAppsChangedListener.Stub.getDefaultImpl().onShortcutChanged(param1UserHandle, param1String, param1ParceledListSlice);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IOnAppsChangedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */