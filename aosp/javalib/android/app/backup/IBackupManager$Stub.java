package android.app.backup;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import java.util.List;

public abstract class Stub extends Binder implements IBackupManager {
  private static final String DESCRIPTOR = "android.app.backup.IBackupManager";
  
  static final int TRANSACTION_acknowledgeFullBackupOrRestore = 26;
  
  static final int TRANSACTION_acknowledgeFullBackupOrRestoreForUser = 25;
  
  static final int TRANSACTION_adbBackup = 22;
  
  static final int TRANSACTION_adbRestore = 24;
  
  static final int TRANSACTION_agentConnected = 7;
  
  static final int TRANSACTION_agentConnectedForUser = 6;
  
  static final int TRANSACTION_agentDisconnected = 9;
  
  static final int TRANSACTION_agentDisconnectedForUser = 8;
  
  static final int TRANSACTION_backupNow = 21;
  
  static final int TRANSACTION_backupNowForUser = 20;
  
  static final int TRANSACTION_beginRestoreSessionForUser = 45;
  
  static final int TRANSACTION_cancelBackups = 57;
  
  static final int TRANSACTION_cancelBackupsForUser = 56;
  
  static final int TRANSACTION_clearBackupData = 4;
  
  static final int TRANSACTION_clearBackupDataForUser = 3;
  
  static final int TRANSACTION_dataChanged = 2;
  
  static final int TRANSACTION_dataChangedForUser = 1;
  
  static final int TRANSACTION_excludeKeysFromRestore = 60;
  
  static final int TRANSACTION_filterAppsEligibleForBackupForUser = 53;
  
  static final int TRANSACTION_fullTransportBackupForUser = 23;
  
  static final int TRANSACTION_getAvailableRestoreTokenForUser = 51;
  
  static final int TRANSACTION_getConfigurationIntent = 39;
  
  static final int TRANSACTION_getConfigurationIntentForUser = 38;
  
  static final int TRANSACTION_getCurrentTransport = 29;
  
  static final int TRANSACTION_getCurrentTransportComponentForUser = 30;
  
  static final int TRANSACTION_getCurrentTransportForUser = 28;
  
  static final int TRANSACTION_getDataManagementIntent = 43;
  
  static final int TRANSACTION_getDataManagementIntentForUser = 42;
  
  static final int TRANSACTION_getDataManagementLabelForUser = 44;
  
  static final int TRANSACTION_getDestinationString = 41;
  
  static final int TRANSACTION_getDestinationStringForUser = 40;
  
  static final int TRANSACTION_getTransportWhitelist = 34;
  
  static final int TRANSACTION_getUserForAncestralSerialNumber = 58;
  
  static final int TRANSACTION_hasBackupPassword = 19;
  
  static final int TRANSACTION_initializeTransportsForUser = 5;
  
  static final int TRANSACTION_isAppEligibleForBackupForUser = 52;
  
  static final int TRANSACTION_isBackupEnabled = 17;
  
  static final int TRANSACTION_isBackupEnabledForUser = 16;
  
  static final int TRANSACTION_isBackupServiceActive = 49;
  
  static final int TRANSACTION_isUserReadyForBackup = 50;
  
  static final int TRANSACTION_listAllTransportComponentsForUser = 33;
  
  static final int TRANSACTION_listAllTransports = 32;
  
  static final int TRANSACTION_listAllTransportsForUser = 31;
  
  static final int TRANSACTION_opComplete = 47;
  
  static final int TRANSACTION_opCompleteForUser = 46;
  
  static final int TRANSACTION_requestBackup = 55;
  
  static final int TRANSACTION_requestBackupForUser = 54;
  
  static final int TRANSACTION_restoreAtInstall = 11;
  
  static final int TRANSACTION_restoreAtInstallForUser = 10;
  
  static final int TRANSACTION_selectBackupTransport = 36;
  
  static final int TRANSACTION_selectBackupTransportAsyncForUser = 37;
  
  static final int TRANSACTION_selectBackupTransportForUser = 35;
  
  static final int TRANSACTION_setAncestralSerialNumber = 59;
  
  static final int TRANSACTION_setAutoRestore = 15;
  
  static final int TRANSACTION_setAutoRestoreForUser = 14;
  
  static final int TRANSACTION_setBackupEnabled = 13;
  
  static final int TRANSACTION_setBackupEnabledForUser = 12;
  
  static final int TRANSACTION_setBackupPassword = 18;
  
  static final int TRANSACTION_setBackupServiceActive = 48;
  
  static final int TRANSACTION_updateTransportAttributesForUser = 27;
  
  public Stub() {
    attachInterface(this, "android.app.backup.IBackupManager");
  }
  
  public static IBackupManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.backup.IBackupManager");
    return (iInterface != null && iInterface instanceof IBackupManager) ? (IBackupManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBackupManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 60:
        return "excludeKeysFromRestore";
      case 59:
        return "setAncestralSerialNumber";
      case 58:
        return "getUserForAncestralSerialNumber";
      case 57:
        return "cancelBackups";
      case 56:
        return "cancelBackupsForUser";
      case 55:
        return "requestBackup";
      case 54:
        return "requestBackupForUser";
      case 53:
        return "filterAppsEligibleForBackupForUser";
      case 52:
        return "isAppEligibleForBackupForUser";
      case 51:
        return "getAvailableRestoreTokenForUser";
      case 50:
        return "isUserReadyForBackup";
      case 49:
        return "isBackupServiceActive";
      case 48:
        return "setBackupServiceActive";
      case 47:
        return "opComplete";
      case 46:
        return "opCompleteForUser";
      case 45:
        return "beginRestoreSessionForUser";
      case 44:
        return "getDataManagementLabelForUser";
      case 43:
        return "getDataManagementIntent";
      case 42:
        return "getDataManagementIntentForUser";
      case 41:
        return "getDestinationString";
      case 40:
        return "getDestinationStringForUser";
      case 39:
        return "getConfigurationIntent";
      case 38:
        return "getConfigurationIntentForUser";
      case 37:
        return "selectBackupTransportAsyncForUser";
      case 36:
        return "selectBackupTransport";
      case 35:
        return "selectBackupTransportForUser";
      case 34:
        return "getTransportWhitelist";
      case 33:
        return "listAllTransportComponentsForUser";
      case 32:
        return "listAllTransports";
      case 31:
        return "listAllTransportsForUser";
      case 30:
        return "getCurrentTransportComponentForUser";
      case 29:
        return "getCurrentTransport";
      case 28:
        return "getCurrentTransportForUser";
      case 27:
        return "updateTransportAttributesForUser";
      case 26:
        return "acknowledgeFullBackupOrRestore";
      case 25:
        return "acknowledgeFullBackupOrRestoreForUser";
      case 24:
        return "adbRestore";
      case 23:
        return "fullTransportBackupForUser";
      case 22:
        return "adbBackup";
      case 21:
        return "backupNow";
      case 20:
        return "backupNowForUser";
      case 19:
        return "hasBackupPassword";
      case 18:
        return "setBackupPassword";
      case 17:
        return "isBackupEnabled";
      case 16:
        return "isBackupEnabledForUser";
      case 15:
        return "setAutoRestore";
      case 14:
        return "setAutoRestoreForUser";
      case 13:
        return "setBackupEnabled";
      case 12:
        return "setBackupEnabledForUser";
      case 11:
        return "restoreAtInstall";
      case 10:
        return "restoreAtInstallForUser";
      case 9:
        return "agentDisconnected";
      case 8:
        return "agentDisconnectedForUser";
      case 7:
        return "agentConnected";
      case 6:
        return "agentConnectedForUser";
      case 5:
        return "initializeTransportsForUser";
      case 4:
        return "clearBackupData";
      case 3:
        return "clearBackupDataForUser";
      case 2:
        return "dataChanged";
      case 1:
        break;
    } 
    return "dataChangedForUser";
  }
  
  public static boolean setDefaultImpl(IBackupManager paramIBackupManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBackupManager != null) {
        Proxy.sDefaultImpl = paramIBackupManager;
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
      boolean bool2;
      int j;
      boolean bool1;
      int i;
      UserHandle userHandle;
      String[] arrayOfString3;
      IRestoreSession iRestoreSession;
      CharSequence charSequence;
      Intent intent2;
      String str3;
      Intent intent1;
      String str2;
      String[] arrayOfString2;
      ComponentName[] arrayOfComponentName;
      String[] arrayOfString1;
      ComponentName componentName1;
      String str1;
      long l;
      ComponentName componentName2;
      String str4;
      Intent intent3;
      String str5;
      Intent intent4;
      boolean bool5;
      boolean bool6;
      boolean bool7;
      boolean bool8;
      boolean bool9;
      boolean bool10;
      boolean bool3 = false;
      boolean bool4 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 60:
          paramParcel1.enforceInterface("android.app.backup.IBackupManager");
          excludeKeysFromRestore(paramParcel1.readString(), paramParcel1.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 59:
          paramParcel1.enforceInterface("android.app.backup.IBackupManager");
          setAncestralSerialNumber(paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        case 58:
          paramParcel1.enforceInterface("android.app.backup.IBackupManager");
          userHandle = getUserForAncestralSerialNumber(paramParcel1.readLong());
          paramParcel2.writeNoException();
          if (userHandle != null) {
            paramParcel2.writeInt(1);
            userHandle.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 57:
          userHandle.enforceInterface("android.app.backup.IBackupManager");
          cancelBackups();
          paramParcel2.writeNoException();
          return true;
        case 56:
          userHandle.enforceInterface("android.app.backup.IBackupManager");
          cancelBackupsForUser(userHandle.readInt());
          paramParcel2.writeNoException();
          return true;
        case 55:
          userHandle.enforceInterface("android.app.backup.IBackupManager");
          paramInt1 = requestBackup(userHandle.createStringArray(), IBackupObserver.Stub.asInterface(userHandle.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(userHandle.readStrongBinder()), userHandle.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 54:
          userHandle.enforceInterface("android.app.backup.IBackupManager");
          paramInt1 = requestBackupForUser(userHandle.readInt(), userHandle.createStringArray(), IBackupObserver.Stub.asInterface(userHandle.readStrongBinder()), IBackupManagerMonitor.Stub.asInterface(userHandle.readStrongBinder()), userHandle.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 53:
          userHandle.enforceInterface("android.app.backup.IBackupManager");
          arrayOfString3 = filterAppsEligibleForBackupForUser(userHandle.readInt(), userHandle.createStringArray());
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString3);
          return true;
        case 52:
          arrayOfString3.enforceInterface("android.app.backup.IBackupManager");
          bool2 = isAppEligibleForBackupForUser(arrayOfString3.readInt(), arrayOfString3.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 51:
          arrayOfString3.enforceInterface("android.app.backup.IBackupManager");
          l = getAvailableRestoreTokenForUser(arrayOfString3.readInt(), arrayOfString3.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 50:
          arrayOfString3.enforceInterface("android.app.backup.IBackupManager");
          bool2 = isUserReadyForBackup(arrayOfString3.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 49:
          arrayOfString3.enforceInterface("android.app.backup.IBackupManager");
          bool2 = isBackupServiceActive(arrayOfString3.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 48:
          arrayOfString3.enforceInterface("android.app.backup.IBackupManager");
          j = arrayOfString3.readInt();
          if (arrayOfString3.readInt() != 0)
            bool4 = true; 
          setBackupServiceActive(j, bool4);
          paramParcel2.writeNoException();
          return true;
        case 47:
          arrayOfString3.enforceInterface("android.app.backup.IBackupManager");
          opComplete(arrayOfString3.readInt(), arrayOfString3.readLong());
          paramParcel2.writeNoException();
          return true;
        case 46:
          arrayOfString3.enforceInterface("android.app.backup.IBackupManager");
          opCompleteForUser(arrayOfString3.readInt(), arrayOfString3.readInt(), arrayOfString3.readLong());
          paramParcel2.writeNoException();
          return true;
        case 45:
          arrayOfString3.enforceInterface("android.app.backup.IBackupManager");
          iRestoreSession = beginRestoreSessionForUser(arrayOfString3.readInt(), arrayOfString3.readString(), arrayOfString3.readString());
          paramParcel2.writeNoException();
          if (iRestoreSession != null) {
            IBinder iBinder = iRestoreSession.asBinder();
          } else {
            iRestoreSession = null;
          } 
          paramParcel2.writeStrongBinder((IBinder)iRestoreSession);
          return true;
        case 44:
          iRestoreSession.enforceInterface("android.app.backup.IBackupManager");
          charSequence = getDataManagementLabelForUser(iRestoreSession.readInt(), iRestoreSession.readString());
          paramParcel2.writeNoException();
          if (charSequence != null) {
            paramParcel2.writeInt(1);
            TextUtils.writeToParcel(charSequence, paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 43:
          charSequence.enforceInterface("android.app.backup.IBackupManager");
          intent2 = getDataManagementIntent(charSequence.readString());
          paramParcel2.writeNoException();
          if (intent2 != null) {
            paramParcel2.writeInt(1);
            intent2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 42:
          intent2.enforceInterface("android.app.backup.IBackupManager");
          intent2 = getDataManagementIntentForUser(intent2.readInt(), intent2.readString());
          paramParcel2.writeNoException();
          if (intent2 != null) {
            paramParcel2.writeInt(1);
            intent2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 41:
          intent2.enforceInterface("android.app.backup.IBackupManager");
          str3 = getDestinationString(intent2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str3);
          return true;
        case 40:
          str3.enforceInterface("android.app.backup.IBackupManager");
          str3 = getDestinationStringForUser(str3.readInt(), str3.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str3);
          return true;
        case 39:
          str3.enforceInterface("android.app.backup.IBackupManager");
          intent1 = getConfigurationIntent(str3.readString());
          paramParcel2.writeNoException();
          if (intent1 != null) {
            paramParcel2.writeInt(1);
            intent1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 38:
          intent1.enforceInterface("android.app.backup.IBackupManager");
          intent1 = getConfigurationIntentForUser(intent1.readInt(), intent1.readString());
          paramParcel2.writeNoException();
          if (intent1 != null) {
            paramParcel2.writeInt(1);
            intent1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 37:
          intent1.enforceInterface("android.app.backup.IBackupManager");
          j = intent1.readInt();
          if (intent1.readInt() != 0) {
            componentName2 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)intent1);
          } else {
            componentName2 = null;
          } 
          selectBackupTransportAsyncForUser(j, componentName2, ISelectBackupTransportCallback.Stub.asInterface(intent1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 36:
          intent1.enforceInterface("android.app.backup.IBackupManager");
          str2 = selectBackupTransport(intent1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 35:
          str2.enforceInterface("android.app.backup.IBackupManager");
          str2 = selectBackupTransportForUser(str2.readInt(), str2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 34:
          str2.enforceInterface("android.app.backup.IBackupManager");
          arrayOfString2 = getTransportWhitelist();
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString2);
          return true;
        case 33:
          arrayOfString2.enforceInterface("android.app.backup.IBackupManager");
          arrayOfComponentName = listAllTransportComponentsForUser(arrayOfString2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfComponentName, 1);
          return true;
        case 32:
          arrayOfComponentName.enforceInterface("android.app.backup.IBackupManager");
          arrayOfString1 = listAllTransports();
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString1);
          return true;
        case 31:
          arrayOfString1.enforceInterface("android.app.backup.IBackupManager");
          arrayOfString1 = listAllTransportsForUser(arrayOfString1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString1);
          return true;
        case 30:
          arrayOfString1.enforceInterface("android.app.backup.IBackupManager");
          componentName1 = getCurrentTransportComponentForUser(arrayOfString1.readInt());
          paramParcel2.writeNoException();
          if (componentName1 != null) {
            paramParcel2.writeInt(1);
            componentName1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 29:
          componentName1.enforceInterface("android.app.backup.IBackupManager");
          str1 = getCurrentTransport();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 28:
          str1.enforceInterface("android.app.backup.IBackupManager");
          str1 = getCurrentTransportForUser(str1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 27:
          str1.enforceInterface("android.app.backup.IBackupManager");
          j = str1.readInt();
          if (str1.readInt() != 0) {
            componentName2 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)str1);
          } else {
            componentName2 = null;
          } 
          str4 = str1.readString();
          if (str1.readInt() != 0) {
            intent3 = (Intent)Intent.CREATOR.createFromParcel((Parcel)str1);
          } else {
            intent3 = null;
          } 
          str5 = str1.readString();
          if (str1.readInt() != 0) {
            intent4 = (Intent)Intent.CREATOR.createFromParcel((Parcel)str1);
          } else {
            intent4 = null;
          } 
          if (str1.readInt() != 0) {
            CharSequence charSequence1 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)str1);
          } else {
            str1 = null;
          } 
          updateTransportAttributesForUser(j, componentName2, str4, intent3, str5, intent4, str1);
          paramParcel2.writeNoException();
          return true;
        case 26:
          str1.enforceInterface("android.app.backup.IBackupManager");
          j = str1.readInt();
          if (str1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          acknowledgeFullBackupOrRestore(j, bool4, str1.readString(), str1.readString(), IFullBackupRestoreObserver.Stub.asInterface(str1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 25:
          str1.enforceInterface("android.app.backup.IBackupManager");
          j = str1.readInt();
          paramInt2 = str1.readInt();
          if (str1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          acknowledgeFullBackupOrRestoreForUser(j, paramInt2, bool4, str1.readString(), str1.readString(), IFullBackupRestoreObserver.Stub.asInterface(str1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 24:
          str1.enforceInterface("android.app.backup.IBackupManager");
          j = str1.readInt();
          if (str1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel((Parcel)str1);
          } else {
            str1 = null;
          } 
          adbRestore(j, (ParcelFileDescriptor)str1);
          paramParcel2.writeNoException();
          return true;
        case 23:
          str1.enforceInterface("android.app.backup.IBackupManager");
          fullTransportBackupForUser(str1.readInt(), str1.createStringArray());
          paramParcel2.writeNoException();
          return true;
        case 22:
          str1.enforceInterface("android.app.backup.IBackupManager");
          j = str1.readInt();
          if (str1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel((Parcel)str1);
          } else {
            componentName2 = null;
          } 
          if (str1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          if (str1.readInt() != 0) {
            bool5 = true;
          } else {
            bool5 = false;
          } 
          if (str1.readInt() != 0) {
            bool6 = true;
          } else {
            bool6 = false;
          } 
          if (str1.readInt() != 0) {
            bool7 = true;
          } else {
            bool7 = false;
          } 
          if (str1.readInt() != 0) {
            bool8 = true;
          } else {
            bool8 = false;
          } 
          if (str1.readInt() != 0) {
            bool9 = true;
          } else {
            bool9 = false;
          } 
          if (str1.readInt() != 0) {
            bool10 = true;
          } else {
            bool10 = false;
          } 
          if (str1.readInt() != 0)
            bool3 = true; 
          adbBackup(j, (ParcelFileDescriptor)componentName2, bool4, bool5, bool6, bool7, bool8, bool9, bool10, bool3, str1.createStringArray());
          paramParcel2.writeNoException();
          return true;
        case 21:
          str1.enforceInterface("android.app.backup.IBackupManager");
          backupNow();
          paramParcel2.writeNoException();
          return true;
        case 20:
          str1.enforceInterface("android.app.backup.IBackupManager");
          backupNowForUser(str1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 19:
          str1.enforceInterface("android.app.backup.IBackupManager");
          bool1 = hasBackupPassword();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 18:
          str1.enforceInterface("android.app.backup.IBackupManager");
          bool1 = setBackupPassword(str1.readString(), str1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 17:
          str1.enforceInterface("android.app.backup.IBackupManager");
          bool1 = isBackupEnabled();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 16:
          str1.enforceInterface("android.app.backup.IBackupManager");
          bool1 = isBackupEnabledForUser(str1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 15:
          str1.enforceInterface("android.app.backup.IBackupManager");
          if (str1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          setAutoRestore(bool4);
          paramParcel2.writeNoException();
          return true;
        case 14:
          str1.enforceInterface("android.app.backup.IBackupManager");
          i = str1.readInt();
          if (str1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          setAutoRestoreForUser(i, bool4);
          paramParcel2.writeNoException();
          return true;
        case 13:
          str1.enforceInterface("android.app.backup.IBackupManager");
          if (str1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          setBackupEnabled(bool4);
          paramParcel2.writeNoException();
          return true;
        case 12:
          str1.enforceInterface("android.app.backup.IBackupManager");
          i = str1.readInt();
          if (str1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          setBackupEnabledForUser(i, bool4);
          paramParcel2.writeNoException();
          return true;
        case 11:
          str1.enforceInterface("android.app.backup.IBackupManager");
          restoreAtInstall(str1.readString(), str1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 10:
          str1.enforceInterface("android.app.backup.IBackupManager");
          restoreAtInstallForUser(str1.readInt(), str1.readString(), str1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 9:
          str1.enforceInterface("android.app.backup.IBackupManager");
          agentDisconnected(str1.readString());
          paramParcel2.writeNoException();
          return true;
        case 8:
          str1.enforceInterface("android.app.backup.IBackupManager");
          agentDisconnectedForUser(str1.readInt(), str1.readString());
          paramParcel2.writeNoException();
          return true;
        case 7:
          str1.enforceInterface("android.app.backup.IBackupManager");
          agentConnected(str1.readString(), str1.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        case 6:
          str1.enforceInterface("android.app.backup.IBackupManager");
          agentConnectedForUser(str1.readInt(), str1.readString(), str1.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        case 5:
          str1.enforceInterface("android.app.backup.IBackupManager");
          initializeTransportsForUser(str1.readInt(), str1.createStringArray(), IBackupObserver.Stub.asInterface(str1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 4:
          str1.enforceInterface("android.app.backup.IBackupManager");
          clearBackupData(str1.readString(), str1.readString());
          paramParcel2.writeNoException();
          return true;
        case 3:
          str1.enforceInterface("android.app.backup.IBackupManager");
          clearBackupDataForUser(str1.readInt(), str1.readString(), str1.readString());
          paramParcel2.writeNoException();
          return true;
        case 2:
          str1.enforceInterface("android.app.backup.IBackupManager");
          dataChanged(str1.readString());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      str1.enforceInterface("android.app.backup.IBackupManager");
      dataChangedForUser(str1.readInt(), str1.readString());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.app.backup.IBackupManager");
    return true;
  }
  
  private static class Proxy implements IBackupManager {
    public static IBackupManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void acknowledgeFullBackupOrRestore(int param2Int, boolean param2Boolean, String param2String1, String param2String2, IFullBackupRestoreObserver param2IFullBackupRestoreObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (param2IFullBackupRestoreObserver != null) {
          iBinder = param2IFullBackupRestoreObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().acknowledgeFullBackupOrRestore(param2Int, param2Boolean, param2String1, param2String2, param2IFullBackupRestoreObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void acknowledgeFullBackupOrRestoreForUser(int param2Int1, int param2Int2, boolean param2Boolean, String param2String1, String param2String2, IFullBackupRestoreObserver param2IFullBackupRestoreObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        try {
          parcel1.writeInt(param2Int1);
          try {
            boolean bool;
            parcel1.writeInt(param2Int2);
            if (param2Boolean) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel1.writeInt(bool);
            try {
              parcel1.writeString(param2String1);
              try {
                IBinder iBinder;
                parcel1.writeString(param2String2);
                if (param2IFullBackupRestoreObserver != null) {
                  iBinder = param2IFullBackupRestoreObserver.asBinder();
                } else {
                  iBinder = null;
                } 
                parcel1.writeStrongBinder(iBinder);
                try {
                  if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
                    IBackupManager.Stub.getDefaultImpl().acknowledgeFullBackupOrRestoreForUser(param2Int1, param2Int2, param2Boolean, param2String1, param2String2, param2IFullBackupRestoreObserver);
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
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String1;
    }
    
    public void adbBackup(int param2Int, ParcelFileDescriptor param2ParcelFileDescriptor, boolean param2Boolean1, boolean param2Boolean2, boolean param2Boolean3, boolean param2Boolean4, boolean param2Boolean5, boolean param2Boolean6, boolean param2Boolean7, boolean param2Boolean8, String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        boolean bool1 = true;
        if (param2ParcelFileDescriptor != null) {
          parcel1.writeInt(1);
          param2ParcelFileDescriptor.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean2) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean3) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean4) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean5) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean6) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean7) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean8) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().adbBackup(param2Int, param2ParcelFileDescriptor, param2Boolean1, param2Boolean2, param2Boolean3, param2Boolean4, param2Boolean5, param2Boolean6, param2Boolean7, param2Boolean8, param2ArrayOfString);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void adbRestore(int param2Int, ParcelFileDescriptor param2ParcelFileDescriptor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (param2ParcelFileDescriptor != null) {
          parcel1.writeInt(1);
          param2ParcelFileDescriptor.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().adbRestore(param2Int, param2ParcelFileDescriptor);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void agentConnected(String param2String, IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeString(param2String);
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().agentConnected(param2String, param2IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void agentConnectedForUser(int param2Int, String param2String, IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().agentConnectedForUser(param2Int, param2String, param2IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void agentDisconnected(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().agentDisconnected(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void agentDisconnectedForUser(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().agentDisconnectedForUser(param2Int, param2String);
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
    
    public void backupNow() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().backupNow();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void backupNowForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().backupNowForUser(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IRestoreSession beginRestoreSessionForUser(int param2Int, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().beginRestoreSessionForUser(param2Int, param2String1, param2String2); 
        parcel2.readException();
        return IRestoreSession.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelBackups() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().cancelBackups();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelBackupsForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().cancelBackupsForUser(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearBackupData(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().clearBackupData(param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearBackupDataForUser(int param2Int, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().clearBackupDataForUser(param2Int, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void dataChanged(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().dataChanged(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void dataChangedForUser(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().dataChangedForUser(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void excludeKeysFromRestore(String param2String, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeString(param2String);
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().excludeKeysFromRestore(param2String, param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] filterAppsEligibleForBackupForUser(int param2Int, String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          param2ArrayOfString = IBackupManager.Stub.getDefaultImpl().filterAppsEligibleForBackupForUser(param2Int, param2ArrayOfString);
          return param2ArrayOfString;
        } 
        parcel2.readException();
        param2ArrayOfString = parcel2.createStringArray();
        return param2ArrayOfString;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void fullTransportBackupForUser(int param2Int, String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().fullTransportBackupForUser(param2Int, param2ArrayOfString);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getAvailableRestoreTokenForUser(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().getAvailableRestoreTokenForUser(param2Int, param2String); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Intent getConfigurationIntent(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().getConfigurationIntent(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (Intent)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Intent getConfigurationIntentForUser(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().getConfigurationIntentForUser(param2Int, param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (Intent)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getCurrentTransport() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().getCurrentTransport(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getCurrentTransportComponentForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          componentName = IBackupManager.Stub.getDefaultImpl().getCurrentTransportComponentForUser(param2Int);
          return componentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName = null;
        } 
        return componentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getCurrentTransportForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().getCurrentTransportForUser(param2Int); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Intent getDataManagementIntent(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().getDataManagementIntent(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (Intent)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Intent getDataManagementIntentForUser(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().getDataManagementIntentForUser(param2Int, param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (Intent)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CharSequence getDataManagementLabelForUser(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().getDataManagementLabelForUser(param2Int, param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getDestinationString(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          param2String = IBackupManager.Stub.getDefaultImpl().getDestinationString(param2String);
          return param2String;
        } 
        parcel2.readException();
        param2String = parcel2.readString();
        return param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getDestinationStringForUser(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          param2String = IBackupManager.Stub.getDefaultImpl().getDestinationStringForUser(param2Int, param2String);
          return param2String;
        } 
        parcel2.readException();
        param2String = parcel2.readString();
        return param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.backup.IBackupManager";
    }
    
    public String[] getTransportWhitelist() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().getTransportWhitelist(); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public UserHandle getUserForAncestralSerialNumber(long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        UserHandle userHandle;
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(58, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          userHandle = IBackupManager.Stub.getDefaultImpl().getUserForAncestralSerialNumber(param2Long);
          return userHandle;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel2);
        } else {
          userHandle = null;
        } 
        return userHandle;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasBackupPassword() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(19, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          bool = IBackupManager.Stub.getDefaultImpl().hasBackupPassword();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void initializeTransportsForUser(int param2Int, String[] param2ArrayOfString, IBackupObserver param2IBackupObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeStringArray(param2ArrayOfString);
        if (param2IBackupObserver != null) {
          iBinder = param2IBackupObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().initializeTransportsForUser(param2Int, param2ArrayOfString, param2IBackupObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAppEligibleForBackupForUser(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(52, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          bool = IBackupManager.Stub.getDefaultImpl().isAppEligibleForBackupForUser(param2Int, param2String);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isBackupEnabled() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(17, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          bool = IBackupManager.Stub.getDefaultImpl().isBackupEnabled();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isBackupEnabledForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(16, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          bool = IBackupManager.Stub.getDefaultImpl().isBackupEnabledForUser(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isBackupServiceActive(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(49, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          bool = IBackupManager.Stub.getDefaultImpl().isBackupServiceActive(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isUserReadyForBackup(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(50, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          bool = IBackupManager.Stub.getDefaultImpl().isUserReadyForBackup(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName[] listAllTransportComponentsForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().listAllTransportComponentsForUser(param2Int); 
        parcel2.readException();
        return (ComponentName[])parcel2.createTypedArray(ComponentName.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] listAllTransports() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().listAllTransports(); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] listAllTransportsForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
          return IBackupManager.Stub.getDefaultImpl().listAllTransportsForUser(param2Int); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void opComplete(int param2Int, long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().opComplete(param2Int, param2Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void opCompleteForUser(int param2Int1, int param2Int2, long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().opCompleteForUser(param2Int1, param2Int2, param2Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int requestBackup(String[] param2ArrayOfString, IBackupObserver param2IBackupObserver, IBackupManagerMonitor param2IBackupManagerMonitor, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeStringArray(param2ArrayOfString);
        IBinder iBinder1 = null;
        if (param2IBackupObserver != null) {
          iBinder2 = param2IBackupObserver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param2IBackupManagerMonitor != null)
          iBinder2 = param2IBackupManagerMonitor.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          param2Int = IBackupManager.Stub.getDefaultImpl().requestBackup(param2ArrayOfString, param2IBackupObserver, param2IBackupManagerMonitor, param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int requestBackupForUser(int param2Int1, String[] param2ArrayOfString, IBackupObserver param2IBackupObserver, IBackupManagerMonitor param2IBackupManagerMonitor, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeStringArray(param2ArrayOfString);
        IBinder iBinder1 = null;
        if (param2IBackupObserver != null) {
          iBinder2 = param2IBackupObserver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param2IBackupManagerMonitor != null)
          iBinder2 = param2IBackupManagerMonitor.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          param2Int1 = IBackupManager.Stub.getDefaultImpl().requestBackupForUser(param2Int1, param2ArrayOfString, param2IBackupObserver, param2IBackupManagerMonitor, param2Int2);
          return param2Int1;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        return param2Int1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void restoreAtInstall(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().restoreAtInstall(param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void restoreAtInstallForUser(int param2Int1, String param2String, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().restoreAtInstallForUser(param2Int1, param2String, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String selectBackupTransport(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          param2String = IBackupManager.Stub.getDefaultImpl().selectBackupTransport(param2String);
          return param2String;
        } 
        parcel2.readException();
        param2String = parcel2.readString();
        return param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void selectBackupTransportAsyncForUser(int param2Int, ComponentName param2ComponentName, ISelectBackupTransportCallback param2ISelectBackupTransportCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ISelectBackupTransportCallback != null) {
          iBinder = param2ISelectBackupTransportCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().selectBackupTransportAsyncForUser(param2Int, param2ComponentName, param2ISelectBackupTransportCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String selectBackupTransportForUser(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          param2String = IBackupManager.Stub.getDefaultImpl().selectBackupTransportForUser(param2Int, param2String);
          return param2String;
        } 
        parcel2.readException();
        param2String = parcel2.readString();
        return param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAncestralSerialNumber(long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().setAncestralSerialNumber(param2Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAutoRestore(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().setAutoRestore(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAutoRestoreForUser(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().setAutoRestoreForUser(param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setBackupEnabled(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().setBackupEnabled(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setBackupEnabledForUser(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().setBackupEnabledForUser(param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setBackupPassword(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(18, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          bool = IBackupManager.Stub.getDefaultImpl().setBackupPassword(param2String1, param2String2);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setBackupServiceActive(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().setBackupServiceActive(param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateTransportAttributesForUser(int param2Int, ComponentName param2ComponentName, String param2String1, Intent param2Intent1, String param2String2, Intent param2Intent2, CharSequence param2CharSequence) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
        try {
          parcel1.writeInt(param2Int);
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String1);
          if (param2Intent1 != null) {
            parcel1.writeInt(1);
            param2Intent1.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String2);
          if (param2Intent2 != null) {
            parcel1.writeInt(1);
            param2Intent2.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2CharSequence != null) {
            parcel1.writeInt(1);
            TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
            IBackupManager.Stub.getDefaultImpl().updateTransportAttributesForUser(param2Int, param2ComponentName, param2String1, param2Intent1, param2String2, param2Intent2, param2CharSequence);
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
      parcel2.recycle();
      parcel1.recycle();
      throw param2ComponentName;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */