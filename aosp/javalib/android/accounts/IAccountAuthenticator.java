package android.accounts;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAccountAuthenticator extends IInterface {
  void addAccount(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString1, String paramString2, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException;
  
  void addAccountFromCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, Bundle paramBundle) throws RemoteException;
  
  void confirmCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, Bundle paramBundle) throws RemoteException;
  
  void editProperties(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString) throws RemoteException;
  
  void finishSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString, Bundle paramBundle) throws RemoteException;
  
  void getAccountCredentialsForCloning(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount) throws RemoteException;
  
  void getAccountRemovalAllowed(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount) throws RemoteException;
  
  void getAuthToken(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException;
  
  void getAuthTokenLabel(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString) throws RemoteException;
  
  void hasFeatures(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String[] paramArrayOfString) throws RemoteException;
  
  void isCredentialsUpdateSuggested(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString) throws RemoteException;
  
  void startAddAccountSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, String paramString1, String paramString2, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException;
  
  void startUpdateCredentialsSession(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException;
  
  void updateCredentials(IAccountAuthenticatorResponse paramIAccountAuthenticatorResponse, Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException;
  
  public static class Default implements IAccountAuthenticator {
    public void addAccount(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String1, String param1String2, String[] param1ArrayOfString, Bundle param1Bundle) throws RemoteException {}
    
    public void addAccountFromCredentials(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, Bundle param1Bundle) throws RemoteException {}
    
    public IBinder asBinder() {
      return null;
    }
    
    public void confirmCredentials(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, Bundle param1Bundle) throws RemoteException {}
    
    public void editProperties(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String) throws RemoteException {}
    
    public void finishSession(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String, Bundle param1Bundle) throws RemoteException {}
    
    public void getAccountCredentialsForCloning(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account) throws RemoteException {}
    
    public void getAccountRemovalAllowed(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account) throws RemoteException {}
    
    public void getAuthToken(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String, Bundle param1Bundle) throws RemoteException {}
    
    public void getAuthTokenLabel(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String) throws RemoteException {}
    
    public void hasFeatures(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String[] param1ArrayOfString) throws RemoteException {}
    
    public void isCredentialsUpdateSuggested(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String) throws RemoteException {}
    
    public void startAddAccountSession(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String1, String param1String2, String[] param1ArrayOfString, Bundle param1Bundle) throws RemoteException {}
    
    public void startUpdateCredentialsSession(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String, Bundle param1Bundle) throws RemoteException {}
    
    public void updateCredentials(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String, Bundle param1Bundle) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAccountAuthenticator {
    private static final String DESCRIPTOR = "android.accounts.IAccountAuthenticator";
    
    static final int TRANSACTION_addAccount = 1;
    
    static final int TRANSACTION_addAccountFromCredentials = 10;
    
    static final int TRANSACTION_confirmCredentials = 2;
    
    static final int TRANSACTION_editProperties = 6;
    
    static final int TRANSACTION_finishSession = 13;
    
    static final int TRANSACTION_getAccountCredentialsForCloning = 9;
    
    static final int TRANSACTION_getAccountRemovalAllowed = 8;
    
    static final int TRANSACTION_getAuthToken = 3;
    
    static final int TRANSACTION_getAuthTokenLabel = 4;
    
    static final int TRANSACTION_hasFeatures = 7;
    
    static final int TRANSACTION_isCredentialsUpdateSuggested = 14;
    
    static final int TRANSACTION_startAddAccountSession = 11;
    
    static final int TRANSACTION_startUpdateCredentialsSession = 12;
    
    static final int TRANSACTION_updateCredentials = 5;
    
    public Stub() {
      attachInterface(this, "android.accounts.IAccountAuthenticator");
    }
    
    public static IAccountAuthenticator asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.accounts.IAccountAuthenticator");
      return (iInterface != null && iInterface instanceof IAccountAuthenticator) ? (IAccountAuthenticator)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAccountAuthenticator getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 14:
          return "isCredentialsUpdateSuggested";
        case 13:
          return "finishSession";
        case 12:
          return "startUpdateCredentialsSession";
        case 11:
          return "startAddAccountSession";
        case 10:
          return "addAccountFromCredentials";
        case 9:
          return "getAccountCredentialsForCloning";
        case 8:
          return "getAccountRemovalAllowed";
        case 7:
          return "hasFeatures";
        case 6:
          return "editProperties";
        case 5:
          return "updateCredentials";
        case 4:
          return "getAuthTokenLabel";
        case 3:
          return "getAuthToken";
        case 2:
          return "confirmCredentials";
        case 1:
          break;
      } 
      return "addAccount";
    }
    
    public static boolean setDefaultImpl(IAccountAuthenticator param1IAccountAuthenticator) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAccountAuthenticator != null) {
          Proxy.sDefaultImpl = param1IAccountAuthenticator;
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
      IAccountAuthenticatorResponse iAccountAuthenticatorResponse;
      if (param1Int1 != 1598968902) {
        IAccountAuthenticatorResponse iAccountAuthenticatorResponse1;
        String[] arrayOfString1;
        IAccountAuthenticatorResponse iAccountAuthenticatorResponse3;
        String str1;
        IAccountAuthenticatorResponse iAccountAuthenticatorResponse2;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 14:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            iAccountAuthenticatorResponse3 = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              Account account = (Account)Account.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            isCredentialsUpdateSuggested(iAccountAuthenticatorResponse3, (Account)param1Parcel2, param1Parcel1.readString());
            return true;
          case 13:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            iAccountAuthenticatorResponse1 = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
            str1 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            finishSession(iAccountAuthenticatorResponse1, str1, (Bundle)param1Parcel1);
            return true;
          case 12:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              Account account = (Account)Account.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iAccountAuthenticatorResponse1 = null;
            } 
            str2 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            startUpdateCredentialsSession(iAccountAuthenticatorResponse2, (Account)iAccountAuthenticatorResponse1, str2, (Bundle)param1Parcel1);
            return true;
          case 11:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
            str2 = param1Parcel1.readString();
            str3 = param1Parcel1.readString();
            arrayOfString1 = param1Parcel1.createStringArray();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            startAddAccountSession(iAccountAuthenticatorResponse2, str2, str3, arrayOfString1, (Bundle)param1Parcel1);
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              Account account = (Account)Account.CREATOR.createFromParcel(param1Parcel1);
            } else {
              arrayOfString1 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            addAccountFromCredentials(iAccountAuthenticatorResponse2, (Account)arrayOfString1, (Bundle)param1Parcel1);
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            iAccountAuthenticatorResponse = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              Account account = (Account)Account.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            getAccountCredentialsForCloning(iAccountAuthenticatorResponse, (Account)param1Parcel1);
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            iAccountAuthenticatorResponse = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              Account account = (Account)Account.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            getAccountRemovalAllowed(iAccountAuthenticatorResponse, (Account)param1Parcel1);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              Account account = (Account)Account.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iAccountAuthenticatorResponse = null;
            } 
            hasFeatures(iAccountAuthenticatorResponse2, (Account)iAccountAuthenticatorResponse, param1Parcel1.createStringArray());
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            editProperties(IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString());
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              Account account = (Account)Account.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iAccountAuthenticatorResponse = null;
            } 
            str2 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            updateCredentials(iAccountAuthenticatorResponse2, (Account)iAccountAuthenticatorResponse, str2, (Bundle)param1Parcel1);
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            getAuthTokenLabel(IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString());
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              Account account = (Account)Account.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iAccountAuthenticatorResponse = null;
            } 
            str2 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            getAuthToken(iAccountAuthenticatorResponse2, (Account)iAccountAuthenticatorResponse, str2, (Bundle)param1Parcel1);
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
            iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              Account account = (Account)Account.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iAccountAuthenticatorResponse = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            confirmCredentials(iAccountAuthenticatorResponse2, (Account)iAccountAuthenticatorResponse, (Bundle)param1Parcel1);
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.accounts.IAccountAuthenticator");
        iAccountAuthenticatorResponse = IAccountAuthenticatorResponse.Stub.asInterface(param1Parcel1.readStrongBinder());
        String str3 = param1Parcel1.readString();
        String str2 = param1Parcel1.readString();
        String[] arrayOfString2 = param1Parcel1.createStringArray();
        if (param1Parcel1.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        addAccount(iAccountAuthenticatorResponse, str3, str2, arrayOfString2, (Bundle)param1Parcel1);
        return true;
      } 
      iAccountAuthenticatorResponse.writeString("android.accounts.IAccountAuthenticator");
      return true;
    }
    
    private static class Proxy implements IAccountAuthenticator {
      public static IAccountAuthenticator sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public void addAccount(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, String param2String1, String param2String2, String[] param2ArrayOfString, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          parcel.writeString(param2String1);
          parcel.writeString(param2String2);
          parcel.writeStringArray(param2ArrayOfString);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().addAccount(param2IAccountAuthenticatorResponse, param2String1, param2String2, param2ArrayOfString, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void addAccountFromCredentials(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, Account param2Account, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
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
          if (!this.mRemote.transact(10, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().addAccountFromCredentials(param2IAccountAuthenticatorResponse, param2Account, param2Bundle);
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
      
      public void confirmCredentials(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, Account param2Account, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
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
          if (!this.mRemote.transact(2, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().confirmCredentials(param2IAccountAuthenticatorResponse, param2Account, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void editProperties(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          parcel.writeString(param2String);
          if (!this.mRemote.transact(6, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().editProperties(param2IAccountAuthenticatorResponse, param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void finishSession(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, String param2String, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          parcel.writeString(param2String);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(13, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().finishSession(param2IAccountAuthenticatorResponse, param2String, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void getAccountCredentialsForCloning(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, Account param2Account) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (param2Account != null) {
            parcel.writeInt(1);
            param2Account.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(9, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().getAccountCredentialsForCloning(param2IAccountAuthenticatorResponse, param2Account);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void getAccountRemovalAllowed(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, Account param2Account) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (param2Account != null) {
            parcel.writeInt(1);
            param2Account.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(8, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().getAccountRemovalAllowed(param2IAccountAuthenticatorResponse, param2Account);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void getAuthToken(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, Account param2Account, String param2String, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (param2Account != null) {
            parcel.writeInt(1);
            param2Account.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeString(param2String);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(3, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().getAuthToken(param2IAccountAuthenticatorResponse, param2Account, param2String, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void getAuthTokenLabel(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          parcel.writeString(param2String);
          if (!this.mRemote.transact(4, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().getAuthTokenLabel(param2IAccountAuthenticatorResponse, param2String);
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
      
      public void hasFeatures(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, Account param2Account, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (param2Account != null) {
            parcel.writeInt(1);
            param2Account.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(7, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().hasFeatures(param2IAccountAuthenticatorResponse, param2Account, param2ArrayOfString);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void isCredentialsUpdateSuggested(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, Account param2Account, String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (param2Account != null) {
            parcel.writeInt(1);
            param2Account.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeString(param2String);
          if (!this.mRemote.transact(14, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().isCredentialsUpdateSuggested(param2IAccountAuthenticatorResponse, param2Account, param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void startAddAccountSession(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, String param2String1, String param2String2, String[] param2ArrayOfString, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          parcel.writeString(param2String1);
          parcel.writeString(param2String2);
          parcel.writeStringArray(param2ArrayOfString);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(11, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().startAddAccountSession(param2IAccountAuthenticatorResponse, param2String1, param2String2, param2ArrayOfString, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void startUpdateCredentialsSession(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, Account param2Account, String param2String, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (param2Account != null) {
            parcel.writeInt(1);
            param2Account.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeString(param2String);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(12, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().startUpdateCredentialsSession(param2IAccountAuthenticatorResponse, param2Account, param2String, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void updateCredentials(IAccountAuthenticatorResponse param2IAccountAuthenticatorResponse, Account param2Account, String param2String, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
          if (param2IAccountAuthenticatorResponse != null) {
            iBinder = param2IAccountAuthenticatorResponse.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (param2Account != null) {
            parcel.writeInt(1);
            param2Account.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeString(param2String);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(5, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
            IAccountAuthenticator.Stub.getDefaultImpl().updateCredentials(param2IAccountAuthenticatorResponse, param2Account, param2String, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IAccountAuthenticator {
    public static IAccountAuthenticator sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public void addAccount(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String1, String param1String2, String[] param1ArrayOfString, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeString(param1String1);
        parcel.writeString(param1String2);
        parcel.writeStringArray(param1ArrayOfString);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().addAccount(param1IAccountAuthenticatorResponse, param1String1, param1String2, param1ArrayOfString, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void addAccountFromCredentials(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
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
        if (!this.mRemote.transact(10, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().addAccountFromCredentials(param1IAccountAuthenticatorResponse, param1Account, param1Bundle);
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
    
    public void confirmCredentials(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
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
        if (!this.mRemote.transact(2, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().confirmCredentials(param1IAccountAuthenticatorResponse, param1Account, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void editProperties(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(6, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().editProperties(param1IAccountAuthenticatorResponse, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void finishSession(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeString(param1String);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(13, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().finishSession(param1IAccountAuthenticatorResponse, param1String, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void getAccountCredentialsForCloning(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (param1Account != null) {
          parcel.writeInt(1);
          param1Account.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().getAccountCredentialsForCloning(param1IAccountAuthenticatorResponse, param1Account);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void getAccountRemovalAllowed(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (param1Account != null) {
          parcel.writeInt(1);
          param1Account.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().getAccountRemovalAllowed(param1IAccountAuthenticatorResponse, param1Account);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void getAuthToken(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (param1Account != null) {
          parcel.writeInt(1);
          param1Account.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param1String);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().getAuthToken(param1IAccountAuthenticatorResponse, param1Account, param1String, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void getAuthTokenLabel(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(4, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().getAuthTokenLabel(param1IAccountAuthenticatorResponse, param1String);
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
    
    public void hasFeatures(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (param1Account != null) {
          parcel.writeInt(1);
          param1Account.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(7, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().hasFeatures(param1IAccountAuthenticatorResponse, param1Account, param1ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void isCredentialsUpdateSuggested(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (param1Account != null) {
          parcel.writeInt(1);
          param1Account.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param1String);
        if (!this.mRemote.transact(14, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().isCredentialsUpdateSuggested(param1IAccountAuthenticatorResponse, param1Account, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void startAddAccountSession(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, String param1String1, String param1String2, String[] param1ArrayOfString, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeString(param1String1);
        parcel.writeString(param1String2);
        parcel.writeStringArray(param1ArrayOfString);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().startAddAccountSession(param1IAccountAuthenticatorResponse, param1String1, param1String2, param1ArrayOfString, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void startUpdateCredentialsSession(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (param1Account != null) {
          parcel.writeInt(1);
          param1Account.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param1String);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().startUpdateCredentialsSession(param1IAccountAuthenticatorResponse, param1Account, param1String, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void updateCredentials(IAccountAuthenticatorResponse param1IAccountAuthenticatorResponse, Account param1Account, String param1String, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accounts.IAccountAuthenticator");
        if (param1IAccountAuthenticatorResponse != null) {
          iBinder = param1IAccountAuthenticatorResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (param1Account != null) {
          parcel.writeInt(1);
          param1Account.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param1String);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel, null, 1) && IAccountAuthenticator.Stub.getDefaultImpl() != null) {
          IAccountAuthenticator.Stub.getDefaultImpl().updateCredentials(param1IAccountAuthenticatorResponse, param1Account, param1String, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountAuthenticator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */