package android.accounts;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IAccountAuthenticator {
  public static IAccountAuthenticator sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addAccount(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString1, String paramString2, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeString(paramString1);
      parcel.writeString(paramString2);
      parcel.writeStringArray(paramArrayOfString);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().addAccount(paramIAccountAuthenticatorResponse, paramString1, paramString2, paramArrayOfString, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void addAccountFromCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel.writeInt(1);
        paramAccount.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(10, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().addAccountFromCredentials(paramIAccountAuthenticatorResponse, paramAccount, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void confirmCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel.writeInt(1);
        paramAccount.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().confirmCredentials(paramIAccountAuthenticatorResponse, paramAccount, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void editProperties(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(6, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().editProperties(paramIAccountAuthenticatorResponse, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void finishSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeString(paramString);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(13, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().finishSession(paramIAccountAuthenticatorResponse, paramString, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void getAccountCredentialsForCloning(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel.writeInt(1);
        paramAccount.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().getAccountCredentialsForCloning(paramIAccountAuthenticatorResponse, paramAccount);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void getAccountRemovalAllowed(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel.writeInt(1);
        paramAccount.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(8, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().getAccountRemovalAllowed(paramIAccountAuthenticatorResponse, paramAccount);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void getAuthToken(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel.writeInt(1);
        paramAccount.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeString(paramString);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().getAuthToken(paramIAccountAuthenticatorResponse, paramAccount, paramString, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void getAuthTokenLabel(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(4, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().getAuthTokenLabel(paramIAccountAuthenticatorResponse, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.accounts.IAccountAuthenticator";
  }
  
  public void hasFeatures(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel.writeInt(1);
        paramAccount.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(7, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().hasFeatures(paramIAccountAuthenticatorResponse, paramAccount, paramArrayOfString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void isCredentialsUpdateSuggested(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel.writeInt(1);
        paramAccount.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeString(paramString);
      if (!this.mRemote.transact(14, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().isCredentialsUpdateSuggested(paramIAccountAuthenticatorResponse, paramAccount, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void startAddAccountSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString1, String paramString2, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeString(paramString1);
      parcel.writeString(paramString2);
      parcel.writeStringArray(paramArrayOfString);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(11, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().startAddAccountSession(paramIAccountAuthenticatorResponse, paramString1, paramString2, paramArrayOfString, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void startUpdateCredentialsSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel.writeInt(1);
        paramAccount.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeString(paramString);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(12, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().startUpdateCredentialsSession(paramIAccountAuthenticatorResponse, paramAccount, paramString, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void updateCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
      if (paramIAccountAuthenticatorResponse != null) {
        iBinder = paramIAccountAuthenticatorResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel.writeInt(1);
        paramAccount.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeString(paramString);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(5, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
        IAccountAuthenticator.Stub.getDefaultImpl().updateCredentials(paramIAccountAuthenticatorResponse, paramAccount, paramString, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountAuthenticator$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */