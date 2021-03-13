package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDexModuleRegisterCallback extends IInterface {
  void onDexModuleRegistered(String paramString1, boolean paramBoolean, String paramString2) throws RemoteException;
  
  public static class Default implements IDexModuleRegisterCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onDexModuleRegistered(String param1String1, boolean param1Boolean, String param1String2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IDexModuleRegisterCallback {
    private static final String DESCRIPTOR = "android.content.pm.IDexModuleRegisterCallback";
    
    static final int TRANSACTION_onDexModuleRegistered = 1;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IDexModuleRegisterCallback");
    }
    
    public static IDexModuleRegisterCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IDexModuleRegisterCallback");
      return (iInterface != null && iInterface instanceof IDexModuleRegisterCallback) ? (IDexModuleRegisterCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IDexModuleRegisterCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onDexModuleRegistered";
    }
    
    public static boolean setDefaultImpl(IDexModuleRegisterCallback param1IDexModuleRegisterCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IDexModuleRegisterCallback != null) {
          Proxy.sDefaultImpl = param1IDexModuleRegisterCallback;
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
      boolean bool;
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.content.pm.IDexModuleRegisterCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.IDexModuleRegisterCallback");
      String str = param1Parcel1.readString();
      if (param1Parcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      onDexModuleRegistered(str, bool, param1Parcel1.readString());
      return true;
    }
    
    private static class Proxy implements IDexModuleRegisterCallback {
      public static IDexModuleRegisterCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.pm.IDexModuleRegisterCallback";
      }
      
      public void onDexModuleRegistered(String param2String1, boolean param2Boolean, String param2String2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.content.pm.IDexModuleRegisterCallback");
          parcel.writeString(param2String1);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          parcel.writeString(param2String2);
          if (!this.mRemote.transact(1, parcel, null, 1) && IDexModuleRegisterCallback.Stub.getDefaultImpl() != null) {
            IDexModuleRegisterCallback.Stub.getDefaultImpl().onDexModuleRegistered(param2String1, param2Boolean, param2String2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IDexModuleRegisterCallback {
    public static IDexModuleRegisterCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IDexModuleRegisterCallback";
    }
    
    public void onDexModuleRegistered(String param1String1, boolean param1Boolean, String param1String2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.content.pm.IDexModuleRegisterCallback");
        parcel.writeString(param1String1);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeString(param1String2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IDexModuleRegisterCallback.Stub.getDefaultImpl() != null) {
          IDexModuleRegisterCallback.Stub.getDefaultImpl().onDexModuleRegistered(param1String1, param1Boolean, param1String2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDexModuleRegisterCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */