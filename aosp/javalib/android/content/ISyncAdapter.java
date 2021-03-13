package android.content;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISyncAdapter extends IInterface {
  void cancelSync(ISyncContext paramISyncContext) throws RemoteException;
  
  void onUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback paramISyncAdapterUnsyncableAccountCallback) throws RemoteException;
  
  void startSync(ISyncContext paramISyncContext, String paramString, Account paramAccount, Bundle paramBundle) throws RemoteException;
  
  public static class Default implements ISyncAdapter {
    public IBinder asBinder() {
      return null;
    }
    
    public void cancelSync(ISyncContext param1ISyncContext) throws RemoteException {}
    
    public void onUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback param1ISyncAdapterUnsyncableAccountCallback) throws RemoteException {}
    
    public void startSync(ISyncContext param1ISyncContext, String param1String, Account param1Account, Bundle param1Bundle) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ISyncAdapter {
    private static final String DESCRIPTOR = "android.content.ISyncAdapter";
    
    static final int TRANSACTION_cancelSync = 3;
    
    static final int TRANSACTION_onUnsyncableAccount = 1;
    
    static final int TRANSACTION_startSync = 2;
    
    public Stub() {
      attachInterface(this, "android.content.ISyncAdapter");
    }
    
    public static ISyncAdapter asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.ISyncAdapter");
      return (iInterface != null && iInterface instanceof ISyncAdapter) ? (ISyncAdapter)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISyncAdapter getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? null : "cancelSync") : "startSync") : "onUnsyncableAccount";
    }
    
    public static boolean setDefaultImpl(ISyncAdapter param1ISyncAdapter) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISyncAdapter != null) {
          Proxy.sDefaultImpl = param1ISyncAdapter;
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
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 1598968902)
              return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
            param1Parcel2.writeString("android.content.ISyncAdapter");
            return true;
          } 
          param1Parcel1.enforceInterface("android.content.ISyncAdapter");
          cancelSync(ISyncContext.Stub.asInterface(param1Parcel1.readStrongBinder()));
          return true;
        } 
        param1Parcel1.enforceInterface("android.content.ISyncAdapter");
        ISyncContext iSyncContext = ISyncContext.Stub.asInterface(param1Parcel1.readStrongBinder());
        String str = param1Parcel1.readString();
        if (param1Parcel1.readInt() != 0) {
          Account account = (Account)Account.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel2 = null;
        } 
        if (param1Parcel1.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        startSync(iSyncContext, str, (Account)param1Parcel2, (Bundle)param1Parcel1);
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.ISyncAdapter");
      onUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
      return true;
    }
    
    private static class Proxy implements ISyncAdapter {
      public static ISyncAdapter sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void cancelSync(ISyncContext param2ISyncContext) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.content.ISyncAdapter");
          if (param2ISyncContext != null) {
            iBinder = param2ISyncContext.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(3, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
            ISyncAdapter.Stub.getDefaultImpl().cancelSync(param2ISyncContext);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.ISyncAdapter";
      }
      
      public void onUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback param2ISyncAdapterUnsyncableAccountCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.content.ISyncAdapter");
          if (param2ISyncAdapterUnsyncableAccountCallback != null) {
            iBinder = param2ISyncAdapterUnsyncableAccountCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(1, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
            ISyncAdapter.Stub.getDefaultImpl().onUnsyncableAccount(param2ISyncAdapterUnsyncableAccountCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void startSync(ISyncContext param2ISyncContext, String param2String, Account param2Account, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.content.ISyncAdapter");
          if (param2ISyncContext != null) {
            iBinder = param2ISyncContext.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          parcel.writeString(param2String);
          if (param2Account != null) {
            parcel.writeInt(1);
            param2Account.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
            ISyncAdapter.Stub.getDefaultImpl().startSync(param2ISyncContext, param2String, param2Account, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ISyncAdapter {
    public static ISyncAdapter sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void cancelSync(ISyncContext param1ISyncContext) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.content.ISyncAdapter");
        if (param1ISyncContext != null) {
          iBinder = param1ISyncContext.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(3, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
          ISyncAdapter.Stub.getDefaultImpl().cancelSync(param1ISyncContext);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.ISyncAdapter";
    }
    
    public void onUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback param1ISyncAdapterUnsyncableAccountCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.content.ISyncAdapter");
        if (param1ISyncAdapterUnsyncableAccountCallback != null) {
          iBinder = param1ISyncAdapterUnsyncableAccountCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
          ISyncAdapter.Stub.getDefaultImpl().onUnsyncableAccount(param1ISyncAdapterUnsyncableAccountCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void startSync(ISyncContext param1ISyncContext, String param1String, Account param1Account, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.content.ISyncAdapter");
        if (param1ISyncContext != null) {
          iBinder = param1ISyncContext.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeString(param1String);
        if (param1Account != null) {
          parcel.writeInt(1);
          param1Account.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
          ISyncAdapter.Stub.getDefaultImpl().startSync(param1ISyncContext, param1String, param1Account, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */