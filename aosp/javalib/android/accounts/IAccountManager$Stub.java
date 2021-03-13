package android.accounts;

import android.content.IntentSender;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.Map;

public abstract class Stub extends Binder implements IAccountManager {
  private static final String DESCRIPTOR = "android.accounts.IAccountManager";
  
  static final int TRANSACTION_accountAuthenticated = 27;
  
  static final int TRANSACTION_addAccount = 22;
  
  static final int TRANSACTION_addAccountAsUser = 23;
  
  static final int TRANSACTION_addAccountExplicitly = 10;
  
  static final int TRANSACTION_addAccountExplicitlyWithVisibility = 38;
  
  static final int TRANSACTION_addSharedAccountsFromParentUser = 29;
  
  static final int TRANSACTION_clearPassword = 18;
  
  static final int TRANSACTION_confirmCredentialsAsUser = 26;
  
  static final int TRANSACTION_copyAccountToUser = 13;
  
  static final int TRANSACTION_createRequestAccountAccessIntentSenderAsUser = 45;
  
  static final int TRANSACTION_editProperties = 25;
  
  static final int TRANSACTION_finishSessionAsUser = 34;
  
  static final int TRANSACTION_getAccountByTypeAndFeatures = 8;
  
  static final int TRANSACTION_getAccountVisibility = 40;
  
  static final int TRANSACTION_getAccountsAndVisibilityForPackage = 41;
  
  static final int TRANSACTION_getAccountsAsUser = 6;
  
  static final int TRANSACTION_getAccountsByFeatures = 9;
  
  static final int TRANSACTION_getAccountsByTypeForPackage = 5;
  
  static final int TRANSACTION_getAccountsForPackage = 4;
  
  static final int TRANSACTION_getAuthToken = 21;
  
  static final int TRANSACTION_getAuthTokenLabel = 28;
  
  static final int TRANSACTION_getAuthenticatorTypes = 3;
  
  static final int TRANSACTION_getPackagesAndVisibilityForAccount = 37;
  
  static final int TRANSACTION_getPassword = 1;
  
  static final int TRANSACTION_getPreviousName = 31;
  
  static final int TRANSACTION_getUserData = 2;
  
  static final int TRANSACTION_hasAccountAccess = 44;
  
  static final int TRANSACTION_hasFeatures = 7;
  
  static final int TRANSACTION_invalidateAuthToken = 14;
  
  static final int TRANSACTION_isCredentialsUpdateSuggested = 36;
  
  static final int TRANSACTION_onAccountAccessed = 46;
  
  static final int TRANSACTION_peekAuthToken = 15;
  
  static final int TRANSACTION_registerAccountListener = 42;
  
  static final int TRANSACTION_removeAccountAsUser = 11;
  
  static final int TRANSACTION_removeAccountExplicitly = 12;
  
  static final int TRANSACTION_renameAccount = 30;
  
  static final int TRANSACTION_setAccountVisibility = 39;
  
  static final int TRANSACTION_setAuthToken = 16;
  
  static final int TRANSACTION_setPassword = 17;
  
  static final int TRANSACTION_setUserData = 19;
  
  static final int TRANSACTION_someUserHasAccount = 35;
  
  static final int TRANSACTION_startAddAccountSession = 32;
  
  static final int TRANSACTION_startUpdateCredentialsSession = 33;
  
  static final int TRANSACTION_unregisterAccountListener = 43;
  
  static final int TRANSACTION_updateAppPermission = 20;
  
  static final int TRANSACTION_updateCredentials = 24;
  
  public Stub() {
    attachInterface(this, "android.accounts.IAccountManager");
  }
  
  public static IAccountManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.accounts.IAccountManager");
    return (iInterface != null && iInterface instanceof IAccountManager) ? (IAccountManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAccountManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 46:
        return "onAccountAccessed";
      case 45:
        return "createRequestAccountAccessIntentSenderAsUser";
      case 44:
        return "hasAccountAccess";
      case 43:
        return "unregisterAccountListener";
      case 42:
        return "registerAccountListener";
      case 41:
        return "getAccountsAndVisibilityForPackage";
      case 40:
        return "getAccountVisibility";
      case 39:
        return "setAccountVisibility";
      case 38:
        return "addAccountExplicitlyWithVisibility";
      case 37:
        return "getPackagesAndVisibilityForAccount";
      case 36:
        return "isCredentialsUpdateSuggested";
      case 35:
        return "someUserHasAccount";
      case 34:
        return "finishSessionAsUser";
      case 33:
        return "startUpdateCredentialsSession";
      case 32:
        return "startAddAccountSession";
      case 31:
        return "getPreviousName";
      case 30:
        return "renameAccount";
      case 29:
        return "addSharedAccountsFromParentUser";
      case 28:
        return "getAuthTokenLabel";
      case 27:
        return "accountAuthenticated";
      case 26:
        return "confirmCredentialsAsUser";
      case 25:
        return "editProperties";
      case 24:
        return "updateCredentials";
      case 23:
        return "addAccountAsUser";
      case 22:
        return "addAccount";
      case 21:
        return "getAuthToken";
      case 20:
        return "updateAppPermission";
      case 19:
        return "setUserData";
      case 18:
        return "clearPassword";
      case 17:
        return "setPassword";
      case 16:
        return "setAuthToken";
      case 15:
        return "peekAuthToken";
      case 14:
        return "invalidateAuthToken";
      case 13:
        return "copyAccountToUser";
      case 12:
        return "removeAccountExplicitly";
      case 11:
        return "removeAccountAsUser";
      case 10:
        return "addAccountExplicitly";
      case 9:
        return "getAccountsByFeatures";
      case 8:
        return "getAccountByTypeAndFeatures";
      case 7:
        return "hasFeatures";
      case 6:
        return "getAccountsAsUser";
      case 5:
        return "getAccountsByTypeForPackage";
      case 4:
        return "getAccountsForPackage";
      case 3:
        return "getAuthenticatorTypes";
      case 2:
        return "getUserData";
      case 1:
        break;
    } 
    return "getPassword";
  }
  
  public static boolean setDefaultImpl(IAccountManager paramIAccountManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAccountManager != null) {
        Proxy.sDefaultImpl = paramIAccountManager;
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
    if (paramInt1 != 1598968902) {
      boolean bool3;
      int j;
      boolean bool2;
      int i;
      boolean bool1;
      IntentSender intentSender;
      Map map;
      String str2;
      Account[] arrayOfAccount;
      AuthenticatorDescription[] arrayOfAuthenticatorDescription;
      Account account;
      IAccountManagerResponse iAccountManagerResponse1;
      String str3;
      String str9;
      IAccountManagerResponse iAccountManagerResponse7;
      String str8;
      IAccountManagerResponse iAccountManagerResponse6;
      String str7;
      IAccountManagerResponse iAccountManagerResponse5;
      String[] arrayOfString1;
      String str6;
      IAccountManagerResponse iAccountManagerResponse4;
      String str5;
      IAccountManagerResponse iAccountManagerResponse3;
      String str4;
      IAccountManagerResponse iAccountManagerResponse2;
      String str13;
      IAccountManagerResponse iAccountManagerResponse9;
      String str12;
      IAccountManagerResponse iAccountManagerResponse8;
      String str11;
      String[] arrayOfString2;
      String str10;
      String[] arrayOfString3;
      String str14;
      IAccountManagerResponse iAccountManagerResponse10;
      IAccountManagerResponse iAccountManagerResponse11;
      boolean bool4 = false;
      boolean bool5 = false;
      boolean bool6 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 46:
          paramParcel1.enforceInterface("android.accounts.IAccountManager");
          onAccountAccessed(paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 45:
          paramParcel1.enforceInterface("android.accounts.IAccountManager");
          if (paramParcel1.readInt() != 0) {
            account = (Account)Account.CREATOR.createFromParcel(paramParcel1);
          } else {
            account = null;
          } 
          str9 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          intentSender = createRequestAccountAccessIntentSenderAsUser(account, str9, (UserHandle)paramParcel1);
          paramParcel2.writeNoException();
          if (intentSender != null) {
            paramParcel2.writeInt(1);
            intentSender.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 44:
          intentSender.enforceInterface("android.accounts.IAccountManager");
          if (intentSender.readInt() != 0) {
            account = (Account)Account.CREATOR.createFromParcel((Parcel)intentSender);
          } else {
            account = null;
          } 
          str9 = intentSender.readString();
          if (intentSender.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)intentSender);
          } else {
            intentSender = null;
          } 
          bool3 = hasAccountAccess(account, str9, (UserHandle)intentSender);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 43:
          intentSender.enforceInterface("android.accounts.IAccountManager");
          unregisterAccountListener(intentSender.createStringArray(), intentSender.readString());
          paramParcel2.writeNoException();
          return true;
        case 42:
          intentSender.enforceInterface("android.accounts.IAccountManager");
          registerAccountListener(intentSender.createStringArray(), intentSender.readString());
          paramParcel2.writeNoException();
          return true;
        case 41:
          intentSender.enforceInterface("android.accounts.IAccountManager");
          map = getAccountsAndVisibilityForPackage(intentSender.readString(), intentSender.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeMap(map);
          return true;
        case 40:
          map.enforceInterface("android.accounts.IAccountManager");
          if (map.readInt() != 0) {
            account = (Account)Account.CREATOR.createFromParcel((Parcel)map);
          } else {
            account = null;
          } 
          j = getAccountVisibility(account, map.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 39:
          map.enforceInterface("android.accounts.IAccountManager");
          if (map.readInt() != 0) {
            account = (Account)Account.CREATOR.createFromParcel((Parcel)map);
          } else {
            account = null;
          } 
          bool2 = setAccountVisibility(account, map.readString(), map.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 38:
          map.enforceInterface("android.accounts.IAccountManager");
          if (map.readInt() != 0) {
            account = (Account)Account.CREATOR.createFromParcel((Parcel)map);
          } else {
            account = null;
          } 
          str13 = map.readString();
          if (map.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)map);
          } else {
            str9 = null;
          } 
          bool2 = addAccountExplicitlyWithVisibility(account, str13, (Bundle)str9, map.readHashMap(getClass().getClassLoader()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 37:
          map.enforceInterface("android.accounts.IAccountManager");
          if (map.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)map);
          } else {
            map = null;
          } 
          map = getPackagesAndVisibilityForAccount((Account)map);
          paramParcel2.writeNoException();
          paramParcel2.writeMap(map);
          return true;
        case 36:
          map.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse7 = IAccountManagerResponse.Stub.asInterface(map.readStrongBinder());
          if (map.readInt() != 0) {
            account = (Account)Account.CREATOR.createFromParcel((Parcel)map);
          } else {
            account = null;
          } 
          isCredentialsUpdateSuggested(iAccountManagerResponse7, account, map.readString());
          paramParcel2.writeNoException();
          return true;
        case 35:
          map.enforceInterface("android.accounts.IAccountManager");
          if (map.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)map);
          } else {
            map = null;
          } 
          bool2 = someUserHasAccount((Account)map);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 34:
          map.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse9 = IAccountManagerResponse.Stub.asInterface(map.readStrongBinder());
          if (map.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)map);
          } else {
            account = null;
          } 
          if (map.readInt() != 0) {
            bool6 = true;
          } else {
            bool6 = false;
          } 
          if (map.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)map);
          } else {
            iAccountManagerResponse7 = null;
          } 
          finishSessionAsUser(iAccountManagerResponse9, (Bundle)account, bool6, (Bundle)iAccountManagerResponse7, map.readInt());
          paramParcel2.writeNoException();
          return true;
        case 33:
          map.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse7 = IAccountManagerResponse.Stub.asInterface(map.readStrongBinder());
          if (map.readInt() != 0) {
            account = (Account)Account.CREATOR.createFromParcel((Parcel)map);
          } else {
            account = null;
          } 
          str12 = map.readString();
          if (map.readInt() != 0) {
            bool6 = true;
          } else {
            bool6 = false;
          } 
          if (map.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)map);
          } else {
            map = null;
          } 
          startUpdateCredentialsSession(iAccountManagerResponse7, account, str12, bool6, (Bundle)map);
          paramParcel2.writeNoException();
          return true;
        case 32:
          map.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse1 = IAccountManagerResponse.Stub.asInterface(map.readStrongBinder());
          str8 = map.readString();
          str12 = map.readString();
          arrayOfString3 = map.createStringArray();
          if (map.readInt() != 0) {
            bool6 = true;
          } else {
            bool6 = false;
          } 
          if (map.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)map);
          } else {
            map = null;
          } 
          startAddAccountSession(iAccountManagerResponse1, str8, str12, arrayOfString3, bool6, (Bundle)map);
          paramParcel2.writeNoException();
          return true;
        case 31:
          map.enforceInterface("android.accounts.IAccountManager");
          if (map.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)map);
          } else {
            map = null;
          } 
          str2 = getPreviousName((Account)map);
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 30:
          str2.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse6 = IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder());
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            iAccountManagerResponse1 = null;
          } 
          renameAccount(iAccountManagerResponse6, (Account)iAccountManagerResponse1, str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 29:
          str2.enforceInterface("android.accounts.IAccountManager");
          addSharedAccountsFromParentUser(str2.readInt(), str2.readInt(), str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 28:
          str2.enforceInterface("android.accounts.IAccountManager");
          getAuthTokenLabel(IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder()), str2.readString(), str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 27:
          str2.enforceInterface("android.accounts.IAccountManager");
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str2 = null;
          } 
          bool2 = accountAuthenticated((Account)str2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 26:
          str2.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse8 = IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder());
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            iAccountManagerResponse1 = null;
          } 
          if (str2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str2);
          } else {
            iAccountManagerResponse6 = null;
          } 
          if (str2.readInt() != 0) {
            bool6 = true;
          } else {
            bool6 = false;
          } 
          confirmCredentialsAsUser(iAccountManagerResponse8, (Account)iAccountManagerResponse1, (Bundle)iAccountManagerResponse6, bool6, str2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 25:
          str2.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse1 = IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder());
          str7 = str2.readString();
          if (str2.readInt() != 0)
            bool6 = true; 
          editProperties(iAccountManagerResponse1, str7, bool6);
          paramParcel2.writeNoException();
          return true;
        case 24:
          str2.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse5 = IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder());
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            iAccountManagerResponse1 = null;
          } 
          str11 = str2.readString();
          if (str2.readInt() != 0) {
            bool6 = true;
          } else {
            bool6 = false;
          } 
          if (str2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str2 = null;
          } 
          updateCredentials(iAccountManagerResponse5, (Account)iAccountManagerResponse1, str11, bool6, (Bundle)str2);
          paramParcel2.writeNoException();
          return true;
        case 23:
          str2.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse11 = IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder());
          str14 = str2.readString();
          str11 = str2.readString();
          arrayOfString1 = str2.createStringArray();
          if (str2.readInt() != 0) {
            bool6 = true;
          } else {
            bool6 = false;
          } 
          if (str2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str2);
          } else {
            iAccountManagerResponse1 = null;
          } 
          addAccountAsUser(iAccountManagerResponse11, str14, str11, arrayOfString1, bool6, (Bundle)iAccountManagerResponse1, str2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 22:
          str2.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse10 = IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder());
          str6 = str2.readString();
          str3 = str2.readString();
          arrayOfString2 = str2.createStringArray();
          if (str2.readInt() != 0) {
            bool6 = true;
          } else {
            bool6 = false;
          } 
          if (str2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str2 = null;
          } 
          addAccount(iAccountManagerResponse10, str6, str3, arrayOfString2, bool6, (Bundle)str2);
          paramParcel2.writeNoException();
          return true;
        case 21:
          str2.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse4 = IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder());
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str3 = null;
          } 
          str10 = str2.readString();
          if (str2.readInt() != 0) {
            bool6 = true;
          } else {
            bool6 = false;
          } 
          if (str2.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          if (str2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str2 = null;
          } 
          getAuthToken(iAccountManagerResponse4, (Account)str3, str10, bool6, bool4, (Bundle)str2);
          paramParcel2.writeNoException();
          return true;
        case 20:
          str2.enforceInterface("android.accounts.IAccountManager");
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str3 = null;
          } 
          str5 = str2.readString();
          i = str2.readInt();
          bool6 = bool4;
          if (str2.readInt() != 0)
            bool6 = true; 
          updateAppPermission((Account)str3, str5, i, bool6);
          paramParcel2.writeNoException();
          return true;
        case 19:
          str2.enforceInterface("android.accounts.IAccountManager");
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str3 = null;
          } 
          setUserData((Account)str3, str2.readString(), str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 18:
          str2.enforceInterface("android.accounts.IAccountManager");
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str2 = null;
          } 
          clearPassword((Account)str2);
          paramParcel2.writeNoException();
          return true;
        case 17:
          str2.enforceInterface("android.accounts.IAccountManager");
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str3 = null;
          } 
          setPassword((Account)str3, str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 16:
          str2.enforceInterface("android.accounts.IAccountManager");
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str3 = null;
          } 
          setAuthToken((Account)str3, str2.readString(), str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 15:
          str2.enforceInterface("android.accounts.IAccountManager");
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str3 = null;
          } 
          str2 = peekAuthToken((Account)str3, str2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 14:
          str2.enforceInterface("android.accounts.IAccountManager");
          invalidateAuthToken(str2.readString(), str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 13:
          str2.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse3 = IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder());
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str3 = null;
          } 
          copyAccountToUser(iAccountManagerResponse3, (Account)str3, str2.readInt(), str2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 12:
          str2.enforceInterface("android.accounts.IAccountManager");
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str2 = null;
          } 
          bool1 = removeAccountExplicitly((Account)str2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 11:
          str2.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse3 = IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder());
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str3 = null;
          } 
          bool6 = bool5;
          if (str2.readInt() != 0)
            bool6 = true; 
          removeAccountAsUser(iAccountManagerResponse3, (Account)str3, bool6, str2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 10:
          str2.enforceInterface("android.accounts.IAccountManager");
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str3 = null;
          } 
          str4 = str2.readString();
          if (str2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str2 = null;
          } 
          bool1 = addAccountExplicitly((Account)str3, str4, (Bundle)str2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 9:
          str2.enforceInterface("android.accounts.IAccountManager");
          getAccountsByFeatures(IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder()), str2.readString(), str2.createStringArray(), str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 8:
          str2.enforceInterface("android.accounts.IAccountManager");
          getAccountByTypeAndFeatures(IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder()), str2.readString(), str2.createStringArray(), str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 7:
          str2.enforceInterface("android.accounts.IAccountManager");
          iAccountManagerResponse2 = IAccountManagerResponse.Stub.asInterface(str2.readStrongBinder());
          if (str2.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str2);
          } else {
            str3 = null;
          } 
          hasFeatures(iAccountManagerResponse2, (Account)str3, str2.createStringArray(), str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 6:
          str2.enforceInterface("android.accounts.IAccountManager");
          arrayOfAccount = getAccountsAsUser(str2.readString(), str2.readInt(), str2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfAccount, 1);
          return true;
        case 5:
          arrayOfAccount.enforceInterface("android.accounts.IAccountManager");
          arrayOfAccount = getAccountsByTypeForPackage(arrayOfAccount.readString(), arrayOfAccount.readString(), arrayOfAccount.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfAccount, 1);
          return true;
        case 4:
          arrayOfAccount.enforceInterface("android.accounts.IAccountManager");
          arrayOfAccount = getAccountsForPackage(arrayOfAccount.readString(), arrayOfAccount.readInt(), arrayOfAccount.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfAccount, 1);
          return true;
        case 3:
          arrayOfAccount.enforceInterface("android.accounts.IAccountManager");
          arrayOfAuthenticatorDescription = getAuthenticatorTypes(arrayOfAccount.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfAuthenticatorDescription, 1);
          return true;
        case 2:
          arrayOfAuthenticatorDescription.enforceInterface("android.accounts.IAccountManager");
          if (arrayOfAuthenticatorDescription.readInt() != 0) {
            Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)arrayOfAuthenticatorDescription);
          } else {
            str3 = null;
          } 
          str1 = getUserData((Account)str3, arrayOfAuthenticatorDescription.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 1:
          break;
      } 
      str1.enforceInterface("android.accounts.IAccountManager");
      if (str1.readInt() != 0) {
        Account account1 = (Account)Account.CREATOR.createFromParcel((Parcel)str1);
      } else {
        str1 = null;
      } 
      String str1 = getPassword((Account)str1);
      paramParcel2.writeNoException();
      paramParcel2.writeString(str1);
      return true;
    } 
    paramParcel2.writeString("android.accounts.IAccountManager");
    return true;
  }
  
  private static class Proxy implements IAccountManager {
    public static IAccountManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public boolean accountAuthenticated(Account param2Account) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          bool = IAccountManager.Stub.getDefaultImpl().accountAuthenticated(param2Account);
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
    
    public void addAccount(IAccountManagerResponse param2IAccountManagerResponse, String param2String1, String param2String2, String[] param2ArrayOfString, boolean param2Boolean, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeString(param2String2);
            try {
              boolean bool;
              parcel1.writeStringArray(param2ArrayOfString);
              if (param2Boolean) {
                bool = true;
              } else {
                bool = false;
              } 
              parcel1.writeInt(bool);
              if (param2Bundle != null) {
                parcel1.writeInt(1);
                param2Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
                  IAccountManager.Stub.getDefaultImpl().addAccount(param2IAccountManagerResponse, param2String1, param2String2, param2ArrayOfString, param2Boolean, param2Bundle);
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
      throw param2IAccountManagerResponse;
    }
    
    public void addAccountAsUser(IAccountManagerResponse param2IAccountManagerResponse, String param2String1, String param2String2, String[] param2ArrayOfString, boolean param2Boolean, Bundle param2Bundle, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeString(param2String2);
            try {
              boolean bool;
              parcel1.writeStringArray(param2ArrayOfString);
              if (param2Boolean) {
                bool = true;
              } else {
                bool = false;
              } 
              parcel1.writeInt(bool);
              if (param2Bundle != null) {
                parcel1.writeInt(1);
                param2Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeInt(param2Int);
                if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
                  IAccountManager.Stub.getDefaultImpl().addAccountAsUser(param2IAccountManagerResponse, param2String1, param2String2, param2ArrayOfString, param2Boolean, param2Bundle, param2Int);
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
      throw param2IAccountManagerResponse;
    }
    
    public boolean addAccountExplicitly(Account param2Account, String param2String, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          bool = IAccountManager.Stub.getDefaultImpl().addAccountExplicitly(param2Account, param2String, param2Bundle);
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
    
    public boolean addAccountExplicitlyWithVisibility(Account param2Account, String param2String, Bundle param2Bundle, Map param2Map) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeMap(param2Map);
        if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          bool = IAccountManager.Stub.getDefaultImpl().addAccountExplicitlyWithVisibility(param2Account, param2String, param2Bundle, param2Map);
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
    
    public void addSharedAccountsFromParentUser(int param2Int1, int param2Int2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().addSharedAccountsFromParentUser(param2Int1, param2Int2, param2String);
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
    
    public void clearPassword(Account param2Account) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().clearPassword(param2Account);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void confirmCredentialsAsUser(IAccountManagerResponse param2IAccountManagerResponse, Account param2Account, Bundle param2Bundle, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().confirmCredentialsAsUser(param2IAccountManagerResponse, param2Account, param2Bundle, param2Boolean, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void copyAccountToUser(IAccountManagerResponse param2IAccountManagerResponse, Account param2Account, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().copyAccountToUser(param2IAccountManagerResponse, param2Account, param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IntentSender createRequestAccountAccessIntentSenderAsUser(Account param2Account, String param2String, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().createRequestAccountAccessIntentSenderAsUser(param2Account, param2String, param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          IntentSender intentSender = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel2);
        } else {
          param2Account = null;
        } 
        return (IntentSender)param2Account;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void editProperties(IAccountManagerResponse param2IAccountManagerResponse, String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        boolean bool;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().editProperties(param2IAccountManagerResponse, param2String, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void finishSessionAsUser(IAccountManagerResponse param2IAccountManagerResponse, Bundle param2Bundle1, boolean param2Boolean, Bundle param2Bundle2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        boolean bool;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Bundle1 != null) {
          parcel1.writeInt(1);
          param2Bundle1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (param2Bundle2 != null) {
          parcel1.writeInt(1);
          param2Bundle2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().finishSessionAsUser(param2IAccountManagerResponse, param2Bundle1, param2Boolean, param2Bundle2, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void getAccountByTypeAndFeatures(IAccountManagerResponse param2IAccountManagerResponse, String param2String1, String[] param2ArrayOfString, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String1);
        parcel1.writeStringArray(param2ArrayOfString);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().getAccountByTypeAndFeatures(param2IAccountManagerResponse, param2String1, param2ArrayOfString, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getAccountVisibility(Account param2Account, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().getAccountVisibility(param2Account, param2String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Map getAccountsAndVisibilityForPackage(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().getAccountsAndVisibilityForPackage(param2String1, param2String2); 
        parcel2.readException();
        return parcel2.readHashMap(getClass().getClassLoader());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Account[] getAccountsAsUser(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().getAccountsAsUser(param2String1, param2Int, param2String2); 
        parcel2.readException();
        return (Account[])parcel2.createTypedArray(Account.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void getAccountsByFeatures(IAccountManagerResponse param2IAccountManagerResponse, String param2String1, String[] param2ArrayOfString, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String1);
        parcel1.writeStringArray(param2ArrayOfString);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().getAccountsByFeatures(param2IAccountManagerResponse, param2String1, param2ArrayOfString, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Account[] getAccountsByTypeForPackage(String param2String1, String param2String2, String param2String3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeString(param2String3);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().getAccountsByTypeForPackage(param2String1, param2String2, param2String3); 
        parcel2.readException();
        return (Account[])parcel2.createTypedArray(Account.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Account[] getAccountsForPackage(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().getAccountsForPackage(param2String1, param2Int, param2String2); 
        parcel2.readException();
        return (Account[])parcel2.createTypedArray(Account.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void getAuthToken(IAccountManagerResponse param2IAccountManagerResponse, Account param2Account, String param2String, boolean param2Boolean1, boolean param2Boolean2, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          boolean bool;
          parcel1.writeString(param2String);
          if (param2Boolean1) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (param2Boolean2) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
              IAccountManager.Stub.getDefaultImpl().getAuthToken(param2IAccountManagerResponse, param2Account, param2String, param2Boolean1, param2Boolean2, param2Bundle);
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
      throw param2IAccountManagerResponse;
    }
    
    public void getAuthTokenLabel(IAccountManagerResponse param2IAccountManagerResponse, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().getAuthTokenLabel(param2IAccountManagerResponse, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public AuthenticatorDescription[] getAuthenticatorTypes(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().getAuthenticatorTypes(param2Int); 
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
    
    public Map getPackagesAndVisibilityForAccount(Account param2Account) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().getPackagesAndVisibilityForAccount(param2Account); 
        parcel2.readException();
        return parcel2.readHashMap(getClass().getClassLoader());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getPassword(Account param2Account) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().getPassword(param2Account); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getPreviousName(Account param2Account) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().getPreviousName(param2Account); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getUserData(Account param2Account, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().getUserData(param2Account, param2String); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasAccountAccess(Account param2Account, String param2String, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          bool = IAccountManager.Stub.getDefaultImpl().hasAccountAccess(param2Account, param2String, param2UserHandle);
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
    
    public void hasFeatures(IAccountManagerResponse param2IAccountManagerResponse, Account param2Account, String[] param2ArrayOfString, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStringArray(param2ArrayOfString);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().hasFeatures(param2IAccountManagerResponse, param2Account, param2ArrayOfString, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void invalidateAuthToken(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().invalidateAuthToken(param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void isCredentialsUpdateSuggested(IAccountManagerResponse param2IAccountManagerResponse, Account param2Account, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().isCredentialsUpdateSuggested(param2IAccountManagerResponse, param2Account, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onAccountAccessed(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().onAccountAccessed(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String peekAuthToken(Account param2Account, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null)
          return IAccountManager.Stub.getDefaultImpl().peekAuthToken(param2Account, param2String); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerAccountListener(String[] param2ArrayOfString, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        parcel1.writeStringArray(param2ArrayOfString);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().registerAccountListener(param2ArrayOfString, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeAccountAsUser(IAccountManagerResponse param2IAccountManagerResponse, Account param2Account, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().removeAccountAsUser(param2IAccountManagerResponse, param2Account, param2Boolean, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeAccountExplicitly(Account param2Account) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          bool = IAccountManager.Stub.getDefaultImpl().removeAccountExplicitly(param2Account);
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
    
    public void renameAccount(IAccountManagerResponse param2IAccountManagerResponse, Account param2Account, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().renameAccount(param2IAccountManagerResponse, param2Account, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setAccountVisibility(Account param2Account, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          bool = IAccountManager.Stub.getDefaultImpl().setAccountVisibility(param2Account, param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAuthToken(Account param2Account, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().setAuthToken(param2Account, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPassword(Account param2Account, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().setPassword(param2Account, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setUserData(Account param2Account, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().setUserData(param2Account, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean someUserHasAccount(Account param2Account) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          bool = IAccountManager.Stub.getDefaultImpl().someUserHasAccount(param2Account);
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
    
    public void startAddAccountSession(IAccountManagerResponse param2IAccountManagerResponse, String param2String1, String param2String2, String[] param2ArrayOfString, boolean param2Boolean, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeString(param2String2);
            try {
              boolean bool;
              parcel1.writeStringArray(param2ArrayOfString);
              if (param2Boolean) {
                bool = true;
              } else {
                bool = false;
              } 
              parcel1.writeInt(bool);
              if (param2Bundle != null) {
                parcel1.writeInt(1);
                param2Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
                  IAccountManager.Stub.getDefaultImpl().startAddAccountSession(param2IAccountManagerResponse, param2String1, param2String2, param2ArrayOfString, param2Boolean, param2Bundle);
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
      throw param2IAccountManagerResponse;
    }
    
    public void startUpdateCredentialsSession(IAccountManagerResponse param2IAccountManagerResponse, Account param2Account, String param2String, boolean param2Boolean, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        boolean bool;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().startUpdateCredentialsSession(param2IAccountManagerResponse, param2Account, param2String, param2Boolean, param2Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterAccountListener(String[] param2ArrayOfString, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        parcel1.writeStringArray(param2ArrayOfString);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().unregisterAccountListener(param2ArrayOfString, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateAppPermission(Account param2Account, String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().updateAppPermission(param2Account, param2String, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateCredentials(IAccountManagerResponse param2IAccountManagerResponse, Account param2Account, String param2String, boolean param2Boolean, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        boolean bool;
        parcel1.writeInterfaceToken("android.accounts.IAccountManager");
        if (param2IAccountManagerResponse != null) {
          iBinder = param2IAccountManagerResponse.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IAccountManager.Stub.getDefaultImpl() != null) {
          IAccountManager.Stub.getDefaultImpl().updateCredentials(param2IAccountManagerResponse, param2Account, param2String, param2Boolean, param2Bundle);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */