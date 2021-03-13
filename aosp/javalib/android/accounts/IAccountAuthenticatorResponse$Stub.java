package android.accounts;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IAccountAuthenticatorResponse {
  private static final String DESCRIPTOR = "android.accounts.IAccountAuthenticatorResponse";
  
  static final int TRANSACTION_onError = 3;
  
  static final int TRANSACTION_onRequestContinued = 2;
  
  static final int TRANSACTION_onResult = 1;
  
  public Stub() {
    attachInterface(this, "android.accounts.IAccountAuthenticatorResponse");
  }
  
  public static IAccountAuthenticatorResponse asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.accounts.IAccountAuthenticatorResponse");
    return (iInterface != null && iInterface instanceof IAccountAuthenticatorResponse) ? (IAccountAuthenticatorResponse)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAccountAuthenticatorResponse getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "onError") : "onRequestContinued") : "onResult";
  }
  
  public static boolean setDefaultImpl(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAccountAuthenticatorResponse != null) {
        Proxy.sDefaultImpl = paramIAccountAuthenticatorResponse;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.accounts.IAccountAuthenticatorResponse");
          return true;
        } 
        paramParcel1.enforceInterface("android.accounts.IAccountAuthenticatorResponse");
        onError(paramParcel1.readInt(), paramParcel1.readString());
        return true;
      } 
      paramParcel1.enforceInterface("android.accounts.IAccountAuthenticatorResponse");
      onRequestContinued();
      return true;
    } 
    paramParcel1.enforceInterface("android.accounts.IAccountAuthenticatorResponse");
    if (paramParcel1.readInt() != 0) {
      Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onResult((Bundle)paramParcel1);
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


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountAuthenticatorResponse$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */