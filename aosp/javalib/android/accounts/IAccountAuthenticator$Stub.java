package android.accounts;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IAccountAuthenticator {
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
  
  public static IAccountAuthenticator asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.accounts.IAccountAuthenticator");
    return (iInterface != null && iInterface instanceof IAccountAuthenticator) ? (IAccountAuthenticator)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAccountAuthenticator getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(IAccountAuthenticator paramIAccountAuthenticator) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAccountAuthenticator != null) {
        Proxy.sDefaultImpl = paramIAccountAuthenticator;
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
    IAccountAuthenticatorResponse iAccountAuthenticatorResponse;
    if (paramInt1 != 1598968902) {
      IAccountAuthenticatorResponse iAccountAuthenticatorResponse1;
      String[] arrayOfString1;
      IAccountAuthenticatorResponse iAccountAuthenticatorResponse3;
      String str1;
      IAccountAuthenticatorResponse iAccountAuthenticatorResponse2;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 14:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          iAccountAuthenticatorResponse3 = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          isCredentialsUpdateSuggested(iAccountAuthenticatorResponse3, (Account)paramParcel2, paramParcel1.readString());
          return true;
        case 13:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          iAccountAuthenticatorResponse1 = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
          str1 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          finishSession(iAccountAuthenticatorResponse1, str1, (Bundle)paramParcel1);
          return true;
        case 12:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel(paramParcel1);
          } else {
            iAccountAuthenticatorResponse1 = null;
          } 
          str2 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          startUpdateCredentialsSession(iAccountAuthenticatorResponse2, (Account)iAccountAuthenticatorResponse1, str2, (Bundle)paramParcel1);
          return true;
        case 11:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
          str2 = paramParcel1.readString();
          str3 = paramParcel1.readString();
          arrayOfString1 = paramParcel1.createStringArray();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          startAddAccountSession(iAccountAuthenticatorResponse2, str2, str3, arrayOfString1, (Bundle)paramParcel1);
          return true;
        case 10:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel(paramParcel1);
          } else {
            arrayOfString1 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          addAccountFromCredentials(iAccountAuthenticatorResponse2, (Account)arrayOfString1, (Bundle)paramParcel1);
          return true;
        case 9:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          iAccountAuthenticatorResponse = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          getAccountCredentialsForCloning(iAccountAuthenticatorResponse, (Account)paramParcel1);
          return true;
        case 8:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          iAccountAuthenticatorResponse = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          getAccountRemovalAllowed(iAccountAuthenticatorResponse, (Account)paramParcel1);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel(paramParcel1);
          } else {
            iAccountAuthenticatorResponse = null;
          } 
          hasFeatures(iAccountAuthenticatorResponse2, (Account)iAccountAuthenticatorResponse, paramParcel1.createStringArray());
          return true;
        case 6:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          editProperties(IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString());
          return true;
        case 5:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel(paramParcel1);
          } else {
            iAccountAuthenticatorResponse = null;
          } 
          str2 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          updateCredentials(iAccountAuthenticatorResponse2, (Account)iAccountAuthenticatorResponse, str2, (Bundle)paramParcel1);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          getAuthTokenLabel(IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString());
          return true;
        case 3:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel(paramParcel1);
          } else {
            iAccountAuthenticatorResponse = null;
          } 
          str2 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          getAuthToken(iAccountAuthenticatorResponse2, (Account)iAccountAuthenticatorResponse, str2, (Bundle)paramParcel1);
          return true;
        case 2:
          paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
          iAccountAuthenticatorResponse2 = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel(paramParcel1);
          } else {
            iAccountAuthenticatorResponse = null;
          } 
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          confirmCredentials(iAccountAuthenticatorResponse2, (Account)iAccountAuthenticatorResponse, (Bundle)paramParcel1);
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.accounts.IAccountAuthenticator");
      iAccountAuthenticatorResponse = IAccountAuthenticatorResponse.Stub.asInterface(paramParcel1.readStrongBinder());
      String str3 = paramParcel1.readString();
      String str2 = paramParcel1.readString();
      String[] arrayOfString2 = paramParcel1.createStringArray();
      if (paramParcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      addAccount(iAccountAuthenticatorResponse, str3, str2, arrayOfString2, (Bundle)paramParcel1);
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


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountAuthenticator$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */