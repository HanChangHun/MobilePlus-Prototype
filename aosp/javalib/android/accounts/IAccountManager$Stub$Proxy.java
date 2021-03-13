package android.accounts;

import android.content.IntentSender;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.Map;

class Proxy implements IAccountManager {
  public static IAccountManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public boolean accountAuthenticated(Account paramAccount) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        bool = IAccountManager.Stub.getDefaultImpl().accountAuthenticated(paramAccount);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addAccount(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          try {
            boolean bool;
            parcel1.writeStringArray(paramArrayOfString);
            if (paramBoolean) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel1.writeInt(bool);
            if (paramBundle != null) {
              parcel1.writeInt(1);
              paramBundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
                IAccountManager.Stub.getDefaultImpl().addAccount(paramIAccountManagerResponse, paramString1, paramString2, paramArrayOfString, paramBoolean, paramBundle);
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
    throw paramIAccountManagerResponse;
  }
  
  public void addAccountAsUser(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean, Bundle paramBundle, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          try {
            boolean bool;
            parcel1.writeStringArray(paramArrayOfString);
            if (paramBoolean) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel1.writeInt(bool);
            if (paramBundle != null) {
              parcel1.writeInt(1);
              paramBundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeInt(paramInt);
              if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
                IAccountManager.Stub.getDefaultImpl().addAccountAsUser(paramIAccountManagerResponse, paramString1, paramString2, paramArrayOfString, paramBoolean, paramBundle, paramInt);
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
    throw paramIAccountManagerResponse;
  }
  
  public boolean addAccountExplicitly(Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        bool = IAccountManager.Stub.getDefaultImpl().addAccountExplicitly(paramAccount, paramString, paramBundle);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean addAccountExplicitlyWithVisibility(Account paramAccount, String paramString, Bundle paramBundle, Map paramMap) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeMap(paramMap);
      if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        bool = IAccountManager.Stub.getDefaultImpl().addAccountExplicitlyWithVisibility(paramAccount, paramString, paramBundle, paramMap);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addSharedAccountsFromParentUser(int paramInt1, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().addSharedAccountsFromParentUser(paramInt1, paramInt2, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void clearPassword(Account paramAccount) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().clearPassword(paramAccount);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void confirmCredentialsAsUser(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, Bundle paramBundle, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().confirmCredentialsAsUser(paramIAccountManagerResponse, paramAccount, paramBundle, paramBoolean, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void copyAccountToUser(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().copyAccountToUser(paramIAccountManagerResponse, paramAccount, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IntentSender createRequestAccountAccessIntentSenderAsUser(Account paramAccount, String paramString, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().createRequestAccountAccessIntentSenderAsUser(paramAccount, paramString, paramUserHandle); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        IntentSender intentSender = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel2);
      } else {
        paramAccount = null;
      } 
      return (IntentSender)paramAccount;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void editProperties(IAccountManagerResponse paramIAccountManagerResponse, String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      boolean bool;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().editProperties(paramIAccountManagerResponse, paramString, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void finishSessionAsUser(IAccountManagerResponse paramIAccountManagerResponse, Bundle paramBundle1, boolean paramBoolean, Bundle paramBundle2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      boolean bool;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramBundle1 != null) {
        parcel1.writeInt(1);
        paramBundle1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (paramBundle2 != null) {
        parcel1.writeInt(1);
        paramBundle2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().finishSessionAsUser(paramIAccountManagerResponse, paramBundle1, paramBoolean, paramBundle2, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void getAccountByTypeAndFeatures(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String[] paramArrayOfString, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().getAccountByTypeAndFeatures(paramIAccountManagerResponse, paramString1, paramArrayOfString, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getAccountVisibility(Account paramAccount, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().getAccountVisibility(paramAccount, paramString); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Map getAccountsAndVisibilityForPackage(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().getAccountsAndVisibilityForPackage(paramString1, paramString2); 
      parcel2.readException();
      return parcel2.readHashMap(getClass().getClassLoader());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Account[] getAccountsAsUser(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().getAccountsAsUser(paramString1, paramInt, paramString2); 
      parcel2.readException();
      return (Account[])parcel2.createTypedArray(Account.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void getAccountsByFeatures(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String[] paramArrayOfString, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().getAccountsByFeatures(paramIAccountManagerResponse, paramString1, paramArrayOfString, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Account[] getAccountsByTypeForPackage(String paramString1, String paramString2, String paramString3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().getAccountsByTypeForPackage(paramString1, paramString2, paramString3); 
      parcel2.readException();
      return (Account[])parcel2.createTypedArray(Account.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Account[] getAccountsForPackage(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().getAccountsForPackage(paramString1, paramInt, paramString2); 
      parcel2.readException();
      return (Account[])parcel2.createTypedArray(Account.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void getAuthToken(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String paramString, boolean paramBoolean1, boolean paramBoolean2, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        boolean bool;
        parcel1.writeString(paramString);
        if (paramBoolean1) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (paramBoolean2) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (paramBundle != null) {
          parcel1.writeInt(1);
          paramBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
            IAccountManager.Stub.getDefaultImpl().getAuthToken(paramIAccountManagerResponse, paramAccount, paramString, paramBoolean1, paramBoolean2, paramBundle);
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
    parcel2.recycle();
    parcel1.recycle();
    throw paramIAccountManagerResponse;
  }
  
  public void getAuthTokenLabel(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().getAuthTokenLabel(paramIAccountManagerResponse, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public AuthenticatorDescription[] getAuthenticatorTypes(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().getAuthenticatorTypes(paramInt); 
      parcel2.readException();
      return (AuthenticatorDescription[])parcel2.createTypedArray(AuthenticatorDescription.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.accounts.IAccountManager";
  }
  
  public Map getPackagesAndVisibilityForAccount(Account paramAccount) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().getPackagesAndVisibilityForAccount(paramAccount); 
      parcel2.readException();
      return parcel2.readHashMap(getClass().getClassLoader());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getPassword(Account paramAccount) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().getPassword(paramAccount); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getPreviousName(Account paramAccount) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().getPreviousName(paramAccount); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getUserData(Account paramAccount, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().getUserData(paramAccount, paramString); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasAccountAccess(Account paramAccount, String paramString, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        bool = IAccountManager.Stub.getDefaultImpl().hasAccountAccess(paramAccount, paramString, paramUserHandle);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void hasFeatures(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String[] paramArrayOfString, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().hasFeatures(paramIAccountManagerResponse, paramAccount, paramArrayOfString, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void invalidateAuthToken(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().invalidateAuthToken(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void isCredentialsUpdateSuggested(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().isCredentialsUpdateSuggested(paramIAccountManagerResponse, paramAccount, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onAccountAccessed(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().onAccountAccessed(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String peekAuthToken(Account paramAccount, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
        return IAccountManager.Stub.getDefaultImpl().peekAuthToken(paramAccount, paramString); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerAccountListener(String[] paramArrayOfString, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().registerAccountListener(paramArrayOfString, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeAccountAsUser(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().removeAccountAsUser(paramIAccountManagerResponse, paramAccount, paramBoolean, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean removeAccountExplicitly(Account paramAccount) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        bool = IAccountManager.Stub.getDefaultImpl().removeAccountExplicitly(paramAccount);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void renameAccount(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().renameAccount(paramIAccountManagerResponse, paramAccount, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setAccountVisibility(Account paramAccount, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        bool = IAccountManager.Stub.getDefaultImpl().setAccountVisibility(paramAccount, paramString, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAuthToken(Account paramAccount, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().setAuthToken(paramAccount, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPassword(Account paramAccount, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().setPassword(paramAccount, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setUserData(Account paramAccount, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().setUserData(paramAccount, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean someUserHasAccount(Account paramAccount) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        bool = IAccountManager.Stub.getDefaultImpl().someUserHasAccount(paramAccount);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startAddAccountSession(IAccountManagerResponse paramIAccountManagerResponse, String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          try {
            boolean bool;
            parcel1.writeStringArray(paramArrayOfString);
            if (paramBoolean) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel1.writeInt(bool);
            if (paramBundle != null) {
              parcel1.writeInt(1);
              paramBundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
                IAccountManager.Stub.getDefaultImpl().startAddAccountSession(paramIAccountManagerResponse, paramString1, paramString2, paramArrayOfString, paramBoolean, paramBundle);
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
    throw paramIAccountManagerResponse;
  }
  
  public void startUpdateCredentialsSession(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String paramString, boolean paramBoolean, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      boolean bool;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().startUpdateCredentialsSession(paramIAccountManagerResponse, paramAccount, paramString, paramBoolean, paramBundle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterAccountListener(String[] paramArrayOfString, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().unregisterAccountListener(paramArrayOfString, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateAppPermission(Account paramAccount, String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().updateAppPermission(paramAccount, paramString, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateCredentials(IAccountManagerResponse paramIAccountManagerResponse, Account paramAccount, String paramString, boolean paramBoolean, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      boolean bool;
      parcel1.writeInterfaceToken("android.accounts.IAccountManager");
      if (paramIAccountManagerResponse != null) {
        iBinder = paramIAccountManagerResponse.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
        IAccountManager.Stub.getDefaultImpl().updateCredentials(paramIAccountManagerResponse, paramAccount, paramString, paramBoolean, paramBundle);
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


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */