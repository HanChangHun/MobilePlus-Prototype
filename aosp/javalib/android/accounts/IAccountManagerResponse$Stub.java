package android.accounts;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IAccountManagerResponse {
  private static final String DESCRIPTOR = "android.accounts.IAccountManagerResponse";
  
  static final int TRANSACTION_onError = 2;
  
  static final int TRANSACTION_onResult = 1;
  
  public Stub() {
    attachInterface(this, "android.accounts.IAccountManagerResponse");
  }
  
  public static IAccountManagerResponse asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.accounts.IAccountManagerResponse");
    return (iInterface != null && iInterface instanceof IAccountManagerResponse) ? (IAccountManagerResponse)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAccountManagerResponse getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onError") : "onResult";
  }
  
  public static boolean setDefaultImpl(IAccountManagerResponse paramIAccountManagerResponse) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAccountManagerResponse != null) {
        Proxy.sDefaultImpl = paramIAccountManagerResponse;
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
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.accounts.IAccountManagerResponse");
        return true;
      } 
      paramParcel1.enforceInterface("android.accounts.IAccountManagerResponse");
      onError(paramParcel1.readInt(), paramParcel1.readString());
      return true;
    } 
    paramParcel1.enforceInterface("android.accounts.IAccountManagerResponse");
    if (paramParcel1.readInt() != 0) {
      Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onResult((Bundle)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IAccountManagerResponse {
    public static IAccountManagerResponse sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.accounts.IAccountManagerResponse";
    }
    
    public void onError(int param2Int, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accounts.IAccountManagerResponse");
        parcel.writeInt(param2Int);
        parcel.writeString(param2String);
        if (!this.mRemote.transact(2, parcel, null, 1) && IAccountManagerResponse.Stub.getDefaultImpl() != null) {
          IAccountManagerResponse.Stub.getDefaultImpl().onError(param2Int, param2String);
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
        parcel.writeInterfaceToken("android.accounts.IAccountManagerResponse");
        if (param2Bundle != null) {
          parcel.writeInt(1);
          param2Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IAccountManagerResponse.Stub.getDefaultImpl() != null) {
          IAccountManagerResponse.Stub.getDefaultImpl().onResult(param2Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountManagerResponse$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */