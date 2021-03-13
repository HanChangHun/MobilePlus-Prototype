package android.app;

import android.content.pm.ParceledListSlice;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IUriGrantsManager extends IInterface {
  void clearGrantedUriPermissions(String paramString, int paramInt) throws RemoteException;
  
  ParceledListSlice getGrantedUriPermissions(String paramString, int paramInt) throws RemoteException;
  
  ParceledListSlice getUriPermissions(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException;
  
  void grantUriPermissionFromOwner(IBinder paramIBinder, int paramInt1, String paramString, Uri paramUri, int paramInt2, int paramInt3, int paramInt4) throws RemoteException;
  
  void releasePersistableUriPermission(Uri paramUri, int paramInt1, String paramString, int paramInt2) throws RemoteException;
  
  void takePersistableUriPermission(Uri paramUri, int paramInt1, String paramString, int paramInt2) throws RemoteException;
  
  public static class Default implements IUriGrantsManager {
    public IBinder asBinder() {
      return null;
    }
    
    public void clearGrantedUriPermissions(String param1String, int param1Int) throws RemoteException {}
    
    public ParceledListSlice getGrantedUriPermissions(String param1String, int param1Int) throws RemoteException {
      return null;
    }
    
    public ParceledListSlice getUriPermissions(String param1String, boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {
      return null;
    }
    
    public void grantUriPermissionFromOwner(IBinder param1IBinder, int param1Int1, String param1String, Uri param1Uri, int param1Int2, int param1Int3, int param1Int4) throws RemoteException {}
    
    public void releasePersistableUriPermission(Uri param1Uri, int param1Int1, String param1String, int param1Int2) throws RemoteException {}
    
    public void takePersistableUriPermission(Uri param1Uri, int param1Int1, String param1String, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IUriGrantsManager {
    private static final String DESCRIPTOR = "android.app.IUriGrantsManager";
    
    static final int TRANSACTION_clearGrantedUriPermissions = 5;
    
    static final int TRANSACTION_getGrantedUriPermissions = 4;
    
    static final int TRANSACTION_getUriPermissions = 6;
    
    static final int TRANSACTION_grantUriPermissionFromOwner = 3;
    
    static final int TRANSACTION_releasePersistableUriPermission = 2;
    
    static final int TRANSACTION_takePersistableUriPermission = 1;
    
    public Stub() {
      attachInterface(this, "android.app.IUriGrantsManager");
    }
    
    public static IUriGrantsManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IUriGrantsManager");
      return (iInterface != null && iInterface instanceof IUriGrantsManager) ? (IUriGrantsManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IUriGrantsManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 6:
          return "getUriPermissions";
        case 5:
          return "clearGrantedUriPermissions";
        case 4:
          return "getGrantedUriPermissions";
        case 3:
          return "grantUriPermissionFromOwner";
        case 2:
          return "releasePersistableUriPermission";
        case 1:
          break;
      } 
      return "takePersistableUriPermission";
    }
    
    public static boolean setDefaultImpl(IUriGrantsManager param1IUriGrantsManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IUriGrantsManager != null) {
          Proxy.sDefaultImpl = param1IUriGrantsManager;
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
        ParceledListSlice parceledListSlice;
        String str1;
        boolean bool1;
        boolean bool2;
        IBinder iBinder;
        String str2;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 6:
            param1Parcel1.enforceInterface("android.app.IUriGrantsManager");
            str1 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            parceledListSlice = getUriPermissions(str1, bool1, bool2);
            param1Parcel2.writeNoException();
            if (parceledListSlice != null) {
              param1Parcel2.writeInt(1);
              parceledListSlice.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 5:
            parceledListSlice.enforceInterface("android.app.IUriGrantsManager");
            clearGrantedUriPermissions(parceledListSlice.readString(), parceledListSlice.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 4:
            parceledListSlice.enforceInterface("android.app.IUriGrantsManager");
            parceledListSlice = getGrantedUriPermissions(parceledListSlice.readString(), parceledListSlice.readInt());
            param1Parcel2.writeNoException();
            if (parceledListSlice != null) {
              param1Parcel2.writeInt(1);
              parceledListSlice.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 3:
            parceledListSlice.enforceInterface("android.app.IUriGrantsManager");
            iBinder = parceledListSlice.readStrongBinder();
            param1Int1 = parceledListSlice.readInt();
            str2 = parceledListSlice.readString();
            if (parceledListSlice.readInt() != 0) {
              Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)parceledListSlice);
            } else {
              str1 = null;
            } 
            grantUriPermissionFromOwner(iBinder, param1Int1, str2, (Uri)str1, parceledListSlice.readInt(), parceledListSlice.readInt(), parceledListSlice.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 2:
            parceledListSlice.enforceInterface("android.app.IUriGrantsManager");
            if (parceledListSlice.readInt() != 0) {
              Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)parceledListSlice);
            } else {
              str1 = null;
            } 
            releasePersistableUriPermission((Uri)str1, parceledListSlice.readInt(), parceledListSlice.readString(), parceledListSlice.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        parceledListSlice.enforceInterface("android.app.IUriGrantsManager");
        if (parceledListSlice.readInt() != 0) {
          Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)parceledListSlice);
        } else {
          str1 = null;
        } 
        takePersistableUriPermission((Uri)str1, parceledListSlice.readInt(), parceledListSlice.readString(), parceledListSlice.readInt());
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.app.IUriGrantsManager");
      return true;
    }
    
    private static class Proxy implements IUriGrantsManager {
      public static IUriGrantsManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void clearGrantedUriPermissions(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
            IUriGrantsManager.Stub.getDefaultImpl().clearGrantedUriPermissions(param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ParceledListSlice getGrantedUriPermissions(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null)
            return IUriGrantsManager.Stub.getDefaultImpl().getGrantedUriPermissions(param2String, param2Int); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (ParceledListSlice)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IUriGrantsManager";
      }
      
      public ParceledListSlice getUriPermissions(String param2String, boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool2;
          parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
          parcel1.writeString(param2String);
          boolean bool1 = true;
          if (param2Boolean1) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (param2Boolean2) {
            bool2 = bool1;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null)
            return IUriGrantsManager.Stub.getDefaultImpl().getUriPermissions(param2String, param2Boolean1, param2Boolean2); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (ParceledListSlice)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void grantUriPermissionFromOwner(IBinder param2IBinder, int param2Int1, String param2String, Uri param2Uri, int param2Int2, int param2Int3, int param2Int4) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
          try {
            parcel1.writeStrongBinder(param2IBinder);
            try {
              parcel1.writeInt(param2Int1);
              try {
                parcel1.writeString(param2String);
                if (param2Uri != null) {
                  parcel1.writeInt(1);
                  param2Uri.writeToParcel(parcel1, 0);
                } else {
                  parcel1.writeInt(0);
                } 
                try {
                  parcel1.writeInt(param2Int2);
                  parcel1.writeInt(param2Int3);
                  parcel1.writeInt(param2Int4);
                  if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
                    IUriGrantsManager.Stub.getDefaultImpl().grantUriPermissionFromOwner(param2IBinder, param2Int1, param2String, param2Uri, param2Int2, param2Int3, param2Int4);
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
        throw param2IBinder;
      }
      
      public void releasePersistableUriPermission(Uri param2Uri, int param2Int1, String param2String, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
          if (param2Uri != null) {
            parcel1.writeInt(1);
            param2Uri.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int1);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
            IUriGrantsManager.Stub.getDefaultImpl().releasePersistableUriPermission(param2Uri, param2Int1, param2String, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void takePersistableUriPermission(Uri param2Uri, int param2Int1, String param2String, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
          if (param2Uri != null) {
            parcel1.writeInt(1);
            param2Uri.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int1);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
            IUriGrantsManager.Stub.getDefaultImpl().takePersistableUriPermission(param2Uri, param2Int1, param2String, param2Int2);
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
  
  private static class Proxy implements IUriGrantsManager {
    public static IUriGrantsManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void clearGrantedUriPermissions(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
          IUriGrantsManager.Stub.getDefaultImpl().clearGrantedUriPermissions(param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getGrantedUriPermissions(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null)
          return IUriGrantsManager.Stub.getDefaultImpl().getGrantedUriPermissions(param1String, param1Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (ParceledListSlice)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IUriGrantsManager";
    }
    
    public ParceledListSlice getUriPermissions(String param1String, boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
        parcel1.writeString(param1String);
        boolean bool1 = true;
        if (param1Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param1Boolean2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null)
          return IUriGrantsManager.Stub.getDefaultImpl().getUriPermissions(param1String, param1Boolean1, param1Boolean2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (ParceledListSlice)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void grantUriPermissionFromOwner(IBinder param1IBinder, int param1Int1, String param1String, Uri param1Uri, int param1Int2, int param1Int3, int param1Int4) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
        try {
          parcel1.writeStrongBinder(param1IBinder);
          try {
            parcel1.writeInt(param1Int1);
            try {
              parcel1.writeString(param1String);
              if (param1Uri != null) {
                parcel1.writeInt(1);
                param1Uri.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeInt(param1Int2);
                parcel1.writeInt(param1Int3);
                parcel1.writeInt(param1Int4);
                if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
                  IUriGrantsManager.Stub.getDefaultImpl().grantUriPermissionFromOwner(param1IBinder, param1Int1, param1String, param1Uri, param1Int2, param1Int3, param1Int4);
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
      throw param1IBinder;
    }
    
    public void releasePersistableUriPermission(Uri param1Uri, int param1Int1, String param1String, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
        if (param1Uri != null) {
          parcel1.writeInt(1);
          param1Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int1);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
          IUriGrantsManager.Stub.getDefaultImpl().releasePersistableUriPermission(param1Uri, param1Int1, param1String, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void takePersistableUriPermission(Uri param1Uri, int param1Int1, String param1String, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUriGrantsManager");
        if (param1Uri != null) {
          parcel1.writeInt(1);
          param1Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int1);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IUriGrantsManager.Stub.getDefaultImpl() != null) {
          IUriGrantsManager.Stub.getDefaultImpl().takePersistableUriPermission(param1Uri, param1Int1, param1String, param1Int2);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IUriGrantsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */