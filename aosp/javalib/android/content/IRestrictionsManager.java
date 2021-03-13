package android.content;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;

public interface IRestrictionsManager extends IInterface {
  Intent createLocalApprovalIntent() throws RemoteException;
  
  Bundle getApplicationRestrictions(String paramString) throws RemoteException;
  
  boolean hasRestrictionsProvider() throws RemoteException;
  
  void notifyPermissionResponse(String paramString, PersistableBundle paramPersistableBundle) throws RemoteException;
  
  void requestPermission(String paramString1, String paramString2, String paramString3, PersistableBundle paramPersistableBundle) throws RemoteException;
  
  public static class Default implements IRestrictionsManager {
    public IBinder asBinder() {
      return null;
    }
    
    public Intent createLocalApprovalIntent() throws RemoteException {
      return null;
    }
    
    public Bundle getApplicationRestrictions(String param1String) throws RemoteException {
      return null;
    }
    
    public boolean hasRestrictionsProvider() throws RemoteException {
      return false;
    }
    
    public void notifyPermissionResponse(String param1String, PersistableBundle param1PersistableBundle) throws RemoteException {}
    
    public void requestPermission(String param1String1, String param1String2, String param1String3, PersistableBundle param1PersistableBundle) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IRestrictionsManager {
    private static final String DESCRIPTOR = "android.content.IRestrictionsManager";
    
    static final int TRANSACTION_createLocalApprovalIntent = 5;
    
    static final int TRANSACTION_getApplicationRestrictions = 1;
    
    static final int TRANSACTION_hasRestrictionsProvider = 2;
    
    static final int TRANSACTION_notifyPermissionResponse = 4;
    
    static final int TRANSACTION_requestPermission = 3;
    
    public Stub() {
      attachInterface(this, "android.content.IRestrictionsManager");
    }
    
    public static IRestrictionsManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.IRestrictionsManager");
      return (iInterface != null && iInterface instanceof IRestrictionsManager) ? (IRestrictionsManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IRestrictionsManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? ((param1Int != 5) ? null : "createLocalApprovalIntent") : "notifyPermissionResponse") : "requestPermission") : "hasRestrictionsProvider") : "getApplicationRestrictions";
    }
    
    public static boolean setDefaultImpl(IRestrictionsManager param1IRestrictionsManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IRestrictionsManager != null) {
          Proxy.sDefaultImpl = param1IRestrictionsManager;
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
      Intent intent;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                if (param1Int1 != 1598968902)
                  return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
                param1Parcel2.writeString("android.content.IRestrictionsManager");
                return true;
              } 
              param1Parcel1.enforceInterface("android.content.IRestrictionsManager");
              intent = createLocalApprovalIntent();
              param1Parcel2.writeNoException();
              if (intent != null) {
                param1Parcel2.writeInt(1);
                intent.writeToParcel(param1Parcel2, 1);
              } else {
                param1Parcel2.writeInt(0);
              } 
              return true;
            } 
            intent.enforceInterface("android.content.IRestrictionsManager");
            String str = intent.readString();
            if (intent.readInt() != 0) {
              PersistableBundle persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel((Parcel)intent);
            } else {
              intent = null;
            } 
            notifyPermissionResponse(str, (PersistableBundle)intent);
            param1Parcel2.writeNoException();
            return true;
          } 
          intent.enforceInterface("android.content.IRestrictionsManager");
          String str2 = intent.readString();
          String str3 = intent.readString();
          String str1 = intent.readString();
          if (intent.readInt() != 0) {
            PersistableBundle persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel((Parcel)intent);
          } else {
            intent = null;
          } 
          requestPermission(str2, str3, str1, (PersistableBundle)intent);
          param1Parcel2.writeNoException();
          return true;
        } 
        intent.enforceInterface("android.content.IRestrictionsManager");
        boolean bool = hasRestrictionsProvider();
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(bool);
        return true;
      } 
      intent.enforceInterface("android.content.IRestrictionsManager");
      Bundle bundle = getApplicationRestrictions(intent.readString());
      param1Parcel2.writeNoException();
      if (bundle != null) {
        param1Parcel2.writeInt(1);
        bundle.writeToParcel(param1Parcel2, 1);
      } else {
        param1Parcel2.writeInt(0);
      } 
      return true;
    }
    
    private static class Proxy implements IRestrictionsManager {
      public static IRestrictionsManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public Intent createLocalApprovalIntent() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          Intent intent;
          parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
            intent = IRestrictionsManager.Stub.getDefaultImpl().createLocalApprovalIntent();
            return intent;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
          } else {
            intent = null;
          } 
          return intent;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Bundle getApplicationRestrictions(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null)
            return IRestrictionsManager.Stub.getDefaultImpl().getApplicationRestrictions(param2String); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (Bundle)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.IRestrictionsManager";
      }
      
      public boolean hasRestrictionsProvider() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(2, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
            bool = IRestrictionsManager.Stub.getDefaultImpl().hasRestrictionsProvider();
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
      
      public void notifyPermissionResponse(String param2String, PersistableBundle param2PersistableBundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
          parcel1.writeString(param2String);
          if (param2PersistableBundle != null) {
            parcel1.writeInt(1);
            param2PersistableBundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
            IRestrictionsManager.Stub.getDefaultImpl().notifyPermissionResponse(param2String, param2PersistableBundle);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestPermission(String param2String1, String param2String2, String param2String3, PersistableBundle param2PersistableBundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          parcel1.writeString(param2String3);
          if (param2PersistableBundle != null) {
            parcel1.writeInt(1);
            param2PersistableBundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
            IRestrictionsManager.Stub.getDefaultImpl().requestPermission(param2String1, param2String2, param2String3, param2PersistableBundle);
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
  
  private static class Proxy implements IRestrictionsManager {
    public static IRestrictionsManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public Intent createLocalApprovalIntent() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Intent intent;
        parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
          intent = IRestrictionsManager.Stub.getDefaultImpl().createLocalApprovalIntent();
          return intent;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
        } else {
          intent = null;
        } 
        return intent;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bundle getApplicationRestrictions(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null)
          return IRestrictionsManager.Stub.getDefaultImpl().getApplicationRestrictions(param1String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (Bundle)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.IRestrictionsManager";
    }
    
    public boolean hasRestrictionsProvider() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
          bool = IRestrictionsManager.Stub.getDefaultImpl().hasRestrictionsProvider();
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
    
    public void notifyPermissionResponse(String param1String, PersistableBundle param1PersistableBundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
        parcel1.writeString(param1String);
        if (param1PersistableBundle != null) {
          parcel1.writeInt(1);
          param1PersistableBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
          IRestrictionsManager.Stub.getDefaultImpl().notifyPermissionResponse(param1String, param1PersistableBundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestPermission(String param1String1, String param1String2, String param1String3, PersistableBundle param1PersistableBundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        parcel1.writeString(param1String3);
        if (param1PersistableBundle != null) {
          parcel1.writeInt(1);
          param1PersistableBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
          IRestrictionsManager.Stub.getDefaultImpl().requestPermission(param1String1, param1String2, param1String3, param1PersistableBundle);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/IRestrictionsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */