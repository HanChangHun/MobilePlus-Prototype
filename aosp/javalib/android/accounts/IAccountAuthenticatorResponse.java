package android.accounts;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAccountAuthenticatorResponse extends IInterface {
  void onError(int paramInt, String paramString) throws RemoteException;
  
  void onRequestContinued() throws RemoteException;
  
  void onResult(Bundle paramBundle) throws RemoteException;
  
  public static class Default implements IAccountAuthenticatorResponse {
    public IBinder asBinder() {
      return null;
    }
    
    public void onError(int param1Int, String param1String) throws RemoteException {}
    
    public void onRequestContinued() throws RemoteException {}
    
    public void onResult(Bundle param1Bundle) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAccountAuthenticatorResponse {
    private static final String DESCRIPTOR = "android.accounts.IAccountAuthenticatorResponse";
    
    static final int TRANSACTION_onError = 3;
    
    static final int TRANSACTION_onRequestContinued = 2;
    
    static final int TRANSACTION_onResult = 1;
    
    public Stub() {
      attachInterface(this, "android.accounts.IAccountAuthenticatorResponse");
    }
    
    public static IAccountAuthenticatorResponse asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.accounts.IAccountAuthenticatorResponse");
      return (iInterface != null && iInterface instanceof IAccountAuthenticatorResponse) ? (IAccountAuthenticatorResponse)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAccountAuthenticatorResponse getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? null : "onError") : "onRequestContinued") : "onResult";
    }
    
    public static boolean setDefaultImpl(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAccountAuthenticatorResponse != null) {
          Proxy.sDefaultImpl = param1IAccountAuthenticatorResponse;
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
            param1Parcel2.writeString("android.accounts.IAccountAuthenticatorResponse");
            return true;
          } 
          param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticatorResponse");
          onError(param1Parcel1.readInt(), param1Parcel1.readString());
          return true;
        } 
        param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticatorResponse");
        onRequestContinued();
        return true;
      } 
      param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticatorResponse");
      if (param1Parcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onResult((Bundle)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements IAccountAuthenticatorResponse {
      public static IAccountAuthenticatorResponse sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.accounts.IAccountAuthenticatorResponse";
      }
      
      public void onError(int param2Int, String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticatorResponse");
          parcel.writeInt(param2Int);
          parcel.writeString(param2String);
          if (!this.mRemote.transact(3, parcel, null, 1) && IAccountAuthenticatorResponse.Stub.getDefaultImpl() != null) {
            IAccountAuthenticatorResponse.Stub.getDefaultImpl().onError(param2Int, param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onRequestContinued() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticatorResponse");
          if (!this.mRemote.transact(2, parcel, null, 1) && IAccountAuthenticatorResponse.Stub.getDefaultImpl() != null) {
            IAccountAuthenticatorResponse.Stub.getDefaultImpl().onRequestContinued();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onResult(Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticatorResponse");
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IAccountAuthenticatorResponse.Stub.getDefaultImpl() != null) {
            IAccountAuthenticatorResponse.Stub.getDefaultImpl().onResult(param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IAccountAuthenticatorResponse {
    public static IAccountAuthenticatorResponse sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.accounts.IAccountAuthenticatorResponse";
    }
    
    public void onError(int param1Int, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticatorResponse");
        parcel.writeInt(param1Int);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(3, parcel, null, 1) && IAccountAuthenticatorResponse.Stub.getDefaultImpl() != null) {
          IAccountAuthenticatorResponse.Stub.getDefaultImpl().onError(param1Int, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRequestContinued() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticatorResponse");
        if (!this.mRemote.transact(2, parcel, null, 1) && IAccountAuthenticatorResponse.Stub.getDefaultImpl() != null) {
          IAccountAuthenticatorResponse.Stub.getDefaultImpl().onRequestContinued();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onResult(Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticatorResponse");
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IAccountAuthenticatorResponse.Stub.getDefaultImpl() != null) {
          IAccountAuthenticatorResponse.Stub.getDefaultImpl().onResult(param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountAuthenticatorResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */