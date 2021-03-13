package android.content;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISyncServiceAdapter extends IInterface {
  void cancelSync(ISyncContext paramISyncContext) throws RemoteException;
  
  void startSync(ISyncContext paramISyncContext, Bundle paramBundle) throws RemoteException;
  
  public static class Default implements ISyncServiceAdapter {
    public IBinder asBinder() {
      return null;
    }
    
    public void cancelSync(ISyncContext param1ISyncContext) throws RemoteException {}
    
    public void startSync(ISyncContext param1ISyncContext, Bundle param1Bundle) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ISyncServiceAdapter {
    private static final String DESCRIPTOR = "android.content.ISyncServiceAdapter";
    
    static final int TRANSACTION_cancelSync = 2;
    
    static final int TRANSACTION_startSync = 1;
    
    public Stub() {
      attachInterface(this, "android.content.ISyncServiceAdapter");
    }
    
    public static ISyncServiceAdapter asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.ISyncServiceAdapter");
      return (iInterface != null && iInterface instanceof ISyncServiceAdapter) ? (ISyncServiceAdapter)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISyncServiceAdapter getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "cancelSync") : "startSync";
    }
    
    public static boolean setDefaultImpl(ISyncServiceAdapter param1ISyncServiceAdapter) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISyncServiceAdapter != null) {
          Proxy.sDefaultImpl = param1ISyncServiceAdapter;
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
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.content.ISyncServiceAdapter");
          return true;
        } 
        param1Parcel1.enforceInterface("android.content.ISyncServiceAdapter");
        cancelSync(ISyncContext.Stub.asInterface(param1Parcel1.readStrongBinder()));
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.ISyncServiceAdapter");
      ISyncContext iSyncContext = ISyncContext.Stub.asInterface(param1Parcel1.readStrongBinder());
      if (param1Parcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      startSync(iSyncContext, (Bundle)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements ISyncServiceAdapter {
      public static ISyncServiceAdapter sDefaultImpl;
      
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
          parcel.writeInterfaceToken("android.content.ISyncServiceAdapter");
          if (param2ISyncContext != null) {
            iBinder = param2ISyncContext.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(2, parcel, null, 1) && ISyncServiceAdapter.Stub.getDefaultImpl() != null) {
            ISyncServiceAdapter.Stub.getDefaultImpl().cancelSync(param2ISyncContext);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.ISyncServiceAdapter";
      }
      
      public void startSync(ISyncContext param2ISyncContext, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.content.ISyncServiceAdapter");
          if (param2ISyncContext != null) {
            iBinder = param2ISyncContext.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && ISyncServiceAdapter.Stub.getDefaultImpl() != null) {
            ISyncServiceAdapter.Stub.getDefaultImpl().startSync(param2ISyncContext, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ISyncServiceAdapter {
    public static ISyncServiceAdapter sDefaultImpl;
    
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
        parcel.writeInterfaceToken("android.content.ISyncServiceAdapter");
        if (param1ISyncContext != null) {
          iBinder = param1ISyncContext.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel, null, 1) && ISyncServiceAdapter.Stub.getDefaultImpl() != null) {
          ISyncServiceAdapter.Stub.getDefaultImpl().cancelSync(param1ISyncContext);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.ISyncServiceAdapter";
    }
    
    public void startSync(ISyncContext param1ISyncContext, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.content.ISyncServiceAdapter");
        if (param1ISyncContext != null) {
          iBinder = param1ISyncContext.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && ISyncServiceAdapter.Stub.getDefaultImpl() != null) {
          ISyncServiceAdapter.Stub.getDefaultImpl().startSync(param1ISyncContext, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncServiceAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */