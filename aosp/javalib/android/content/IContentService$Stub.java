package android.content;

import android.accounts.Account;
import android.database.IContentObserver;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IContentService {
  private static final String DESCRIPTOR = "android.content.IContentService";
  
  static final int TRANSACTION_addPeriodicSync = 15;
  
  static final int TRANSACTION_addStatusChangeListener = 35;
  
  static final int TRANSACTION_cancelRequest = 9;
  
  static final int TRANSACTION_cancelSync = 7;
  
  static final int TRANSACTION_cancelSyncAsUser = 8;
  
  static final int TRANSACTION_getCache = 38;
  
  static final int TRANSACTION_getCurrentSyncs = 25;
  
  static final int TRANSACTION_getCurrentSyncsAsUser = 26;
  
  static final int TRANSACTION_getIsSyncable = 17;
  
  static final int TRANSACTION_getIsSyncableAsUser = 18;
  
  static final int TRANSACTION_getMasterSyncAutomatically = 23;
  
  static final int TRANSACTION_getMasterSyncAutomaticallyAsUser = 24;
  
  static final int TRANSACTION_getPeriodicSyncs = 14;
  
  static final int TRANSACTION_getSyncAdapterPackagesForAuthorityAsUser = 29;
  
  static final int TRANSACTION_getSyncAdapterTypes = 27;
  
  static final int TRANSACTION_getSyncAdapterTypesAsUser = 28;
  
  static final int TRANSACTION_getSyncAutomatically = 10;
  
  static final int TRANSACTION_getSyncAutomaticallyAsUser = 11;
  
  static final int TRANSACTION_getSyncStatus = 31;
  
  static final int TRANSACTION_getSyncStatusAsUser = 32;
  
  static final int TRANSACTION_isSyncActive = 30;
  
  static final int TRANSACTION_isSyncPending = 33;
  
  static final int TRANSACTION_isSyncPendingAsUser = 34;
  
  static final int TRANSACTION_notifyChange = 3;
  
  static final int TRANSACTION_onDbCorruption = 40;
  
  static final int TRANSACTION_putCache = 37;
  
  static final int TRANSACTION_registerContentObserver = 2;
  
  static final int TRANSACTION_removePeriodicSync = 16;
  
  static final int TRANSACTION_removeStatusChangeListener = 36;
  
  static final int TRANSACTION_requestSync = 4;
  
  static final int TRANSACTION_resetTodayStats = 39;
  
  static final int TRANSACTION_setIsSyncable = 19;
  
  static final int TRANSACTION_setIsSyncableAsUser = 20;
  
  static final int TRANSACTION_setMasterSyncAutomatically = 21;
  
  static final int TRANSACTION_setMasterSyncAutomaticallyAsUser = 22;
  
  static final int TRANSACTION_setSyncAutomatically = 12;
  
  static final int TRANSACTION_setSyncAutomaticallyAsUser = 13;
  
  static final int TRANSACTION_sync = 5;
  
  static final int TRANSACTION_syncAsUser = 6;
  
  static final int TRANSACTION_unregisterContentObserver = 1;
  
  public Stub() {
    attachInterface(this, "android.content.IContentService");
  }
  
  public static IContentService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.IContentService");
    return (iInterface != null && iInterface instanceof IContentService) ? (IContentService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IContentService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 40:
        return "onDbCorruption";
      case 39:
        return "resetTodayStats";
      case 38:
        return "getCache";
      case 37:
        return "putCache";
      case 36:
        return "removeStatusChangeListener";
      case 35:
        return "addStatusChangeListener";
      case 34:
        return "isSyncPendingAsUser";
      case 33:
        return "isSyncPending";
      case 32:
        return "getSyncStatusAsUser";
      case 31:
        return "getSyncStatus";
      case 30:
        return "isSyncActive";
      case 29:
        return "getSyncAdapterPackagesForAuthorityAsUser";
      case 28:
        return "getSyncAdapterTypesAsUser";
      case 27:
        return "getSyncAdapterTypes";
      case 26:
        return "getCurrentSyncsAsUser";
      case 25:
        return "getCurrentSyncs";
      case 24:
        return "getMasterSyncAutomaticallyAsUser";
      case 23:
        return "getMasterSyncAutomatically";
      case 22:
        return "setMasterSyncAutomaticallyAsUser";
      case 21:
        return "setMasterSyncAutomatically";
      case 20:
        return "setIsSyncableAsUser";
      case 19:
        return "setIsSyncable";
      case 18:
        return "getIsSyncableAsUser";
      case 17:
        return "getIsSyncable";
      case 16:
        return "removePeriodicSync";
      case 15:
        return "addPeriodicSync";
      case 14:
        return "getPeriodicSyncs";
      case 13:
        return "setSyncAutomaticallyAsUser";
      case 12:
        return "setSyncAutomatically";
      case 11:
        return "getSyncAutomaticallyAsUser";
      case 10:
        return "getSyncAutomatically";
      case 9:
        return "cancelRequest";
      case 8:
        return "cancelSyncAsUser";
      case 7:
        return "cancelSync";
      case 6:
        return "syncAsUser";
      case 5:
        return "sync";
      case 4:
        return "requestSync";
      case 3:
        return "notifyChange";
      case 2:
        return "registerContentObserver";
      case 1:
        break;
    } 
    return "unregisterContentObserver";
  }
  
  public static boolean setDefaultImpl(IContentService paramIContentService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIContentService != null) {
        Proxy.sDefaultImpl = paramIContentService;
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
      int i;
      boolean bool1;
      Bundle bundle;
      SyncStatusInfo syncStatusInfo;
      String[] arrayOfString;
      SyncAdapterType[] arrayOfSyncAdapterType;
      List<SyncInfo> list;
      String str1;
      IContentObserver iContentObserver;
      Uri uri;
      Uri[] arrayOfUri;
      String str2;
      boolean bool3 = false;
      boolean bool4 = false;
      boolean bool5 = false;
      boolean bool6 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 40:
          paramParcel1.enforceInterface("android.content.IContentService");
          onDbCorruption(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 39:
          paramParcel1.enforceInterface("android.content.IContentService");
          resetTodayStats();
          paramParcel2.writeNoException();
          return true;
        case 38:
          paramParcel1.enforceInterface("android.content.IContentService");
          str1 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            uri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          } else {
            uri = null;
          } 
          bundle = getCache(str1, uri, paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (bundle != null) {
            paramParcel2.writeInt(1);
            bundle.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 37:
          bundle.enforceInterface("android.content.IContentService");
          str2 = bundle.readString();
          if (bundle.readInt() != 0) {
            uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            uri = null;
          } 
          if (bundle.readInt() != 0) {
            Bundle bundle1 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str1 = null;
          } 
          putCache(str2, uri, (Bundle)str1, bundle.readInt());
          paramParcel2.writeNoException();
          return true;
        case 36:
          bundle.enforceInterface("android.content.IContentService");
          removeStatusChangeListener(ISyncStatusObserver.Stub.asInterface(bundle.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 35:
          bundle.enforceInterface("android.content.IContentService");
          addStatusChangeListener(bundle.readInt(), ISyncStatusObserver.Stub.asInterface(bundle.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 34:
          bundle.enforceInterface("android.content.IContentService");
          if (bundle.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            uri = null;
          } 
          str2 = bundle.readString();
          if (bundle.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str1 = null;
          } 
          bool2 = isSyncPendingAsUser((Account)uri, str2, (ComponentName)str1, bundle.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 33:
          bundle.enforceInterface("android.content.IContentService");
          if (bundle.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            uri = null;
          } 
          str1 = bundle.readString();
          if (bundle.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            bundle = null;
          } 
          bool2 = isSyncPending((Account)uri, str1, (ComponentName)bundle);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 32:
          bundle.enforceInterface("android.content.IContentService");
          if (bundle.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            uri = null;
          } 
          str2 = bundle.readString();
          if (bundle.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str1 = null;
          } 
          syncStatusInfo = getSyncStatusAsUser((Account)uri, str2, (ComponentName)str1, bundle.readInt());
          paramParcel2.writeNoException();
          if (syncStatusInfo != null) {
            paramParcel2.writeInt(1);
            syncStatusInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 31:
          syncStatusInfo.enforceInterface("android.content.IContentService");
          if (syncStatusInfo.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)syncStatusInfo);
          } else {
            uri = null;
          } 
          str1 = syncStatusInfo.readString();
          if (syncStatusInfo.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)syncStatusInfo);
          } else {
            syncStatusInfo = null;
          } 
          syncStatusInfo = getSyncStatus((Account)uri, str1, (ComponentName)syncStatusInfo);
          paramParcel2.writeNoException();
          if (syncStatusInfo != null) {
            paramParcel2.writeInt(1);
            syncStatusInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 30:
          syncStatusInfo.enforceInterface("android.content.IContentService");
          if (syncStatusInfo.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)syncStatusInfo);
          } else {
            uri = null;
          } 
          str1 = syncStatusInfo.readString();
          if (syncStatusInfo.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)syncStatusInfo);
          } else {
            syncStatusInfo = null;
          } 
          bool2 = isSyncActive((Account)uri, str1, (ComponentName)syncStatusInfo);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 29:
          syncStatusInfo.enforceInterface("android.content.IContentService");
          arrayOfString = getSyncAdapterPackagesForAuthorityAsUser(syncStatusInfo.readString(), syncStatusInfo.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString);
          return true;
        case 28:
          arrayOfString.enforceInterface("android.content.IContentService");
          arrayOfSyncAdapterType = getSyncAdapterTypesAsUser(arrayOfString.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfSyncAdapterType, 1);
          return true;
        case 27:
          arrayOfSyncAdapterType.enforceInterface("android.content.IContentService");
          arrayOfSyncAdapterType = getSyncAdapterTypes();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfSyncAdapterType, 1);
          return true;
        case 26:
          arrayOfSyncAdapterType.enforceInterface("android.content.IContentService");
          list = getCurrentSyncsAsUser(arrayOfSyncAdapterType.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 25:
          list.enforceInterface("android.content.IContentService");
          list = getCurrentSyncs();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 24:
          list.enforceInterface("android.content.IContentService");
          bool2 = getMasterSyncAutomaticallyAsUser(list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 23:
          list.enforceInterface("android.content.IContentService");
          bool2 = getMasterSyncAutomatically();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 22:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0)
            bool6 = true; 
          setMasterSyncAutomaticallyAsUser(bool6, list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 21:
          list.enforceInterface("android.content.IContentService");
          bool6 = bool3;
          if (list.readInt() != 0)
            bool6 = true; 
          setMasterSyncAutomatically(bool6);
          paramParcel2.writeNoException();
          return true;
        case 20:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          setIsSyncableAsUser((Account)uri, list.readString(), list.readInt(), list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 19:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          setIsSyncable((Account)uri, list.readString(), list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 18:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          i = getIsSyncableAsUser((Account)uri, list.readString(), list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 17:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          i = getIsSyncable((Account)uri, list.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 16:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          str1 = list.readString();
          if (list.readInt() != 0) {
            Bundle bundle1 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          removePeriodicSync((Account)uri, str1, (Bundle)list);
          paramParcel2.writeNoException();
          return true;
        case 15:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          str2 = list.readString();
          if (list.readInt() != 0) {
            Bundle bundle1 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)list);
          } else {
            str1 = null;
          } 
          addPeriodicSync((Account)uri, str2, (Bundle)str1, list.readLong());
          paramParcel2.writeNoException();
          return true;
        case 14:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          str1 = list.readString();
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          list = (List)getPeriodicSyncs((Account)uri, str1, (ComponentName)list);
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 13:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          str1 = list.readString();
          bool6 = bool4;
          if (list.readInt() != 0)
            bool6 = true; 
          setSyncAutomaticallyAsUser((Account)uri, str1, bool6, list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 12:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          str1 = list.readString();
          bool6 = bool5;
          if (list.readInt() != 0)
            bool6 = true; 
          setSyncAutomatically((Account)uri, str1, bool6);
          paramParcel2.writeNoException();
          return true;
        case 11:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          bool1 = getSyncAutomaticallyAsUser((Account)uri, list.readString(), list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 10:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          bool1 = getSyncAutomatically((Account)uri, list.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 9:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            SyncRequest syncRequest = (SyncRequest)SyncRequest.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          cancelRequest((SyncRequest)list);
          paramParcel2.writeNoException();
          return true;
        case 8:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          str2 = list.readString();
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            str1 = null;
          } 
          cancelSyncAsUser((Account)uri, str2, (ComponentName)str1, list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 7:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          str1 = list.readString();
          if (list.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          cancelSync((Account)uri, str1, (ComponentName)list);
          paramParcel2.writeNoException();
          return true;
        case 6:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            SyncRequest syncRequest = (SyncRequest)SyncRequest.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          syncAsUser((SyncRequest)uri, list.readInt(), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 5:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            SyncRequest syncRequest = (SyncRequest)SyncRequest.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          sync((SyncRequest)uri, list.readString());
          paramParcel2.writeNoException();
          return true;
        case 4:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Account account = (Account)Account.CREATOR.createFromParcel((Parcel)list);
          } else {
            uri = null;
          } 
          str2 = list.readString();
          if (list.readInt() != 0) {
            Bundle bundle1 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)list);
          } else {
            str1 = null;
          } 
          requestSync((Account)uri, str2, (Bundle)str1, list.readString());
          paramParcel2.writeNoException();
          return true;
        case 3:
          list.enforceInterface("android.content.IContentService");
          arrayOfUri = (Uri[])list.createTypedArray(Uri.CREATOR);
          iContentObserver = IContentObserver.Stub.asInterface(list.readStrongBinder());
          if (list.readInt() != 0) {
            bool6 = true;
          } else {
            bool6 = false;
          } 
          notifyChange(arrayOfUri, iContentObserver, bool6, list.readInt(), list.readInt(), list.readInt(), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 2:
          list.enforceInterface("android.content.IContentService");
          if (list.readInt() != 0) {
            Uri uri1 = (Uri)Uri.CREATOR.createFromParcel((Parcel)list);
          } else {
            arrayOfUri = null;
          } 
          if (list.readInt() != 0) {
            bool6 = true;
          } else {
            bool6 = false;
          } 
          registerContentObserver((Uri)arrayOfUri, bool6, IContentObserver.Stub.asInterface(list.readStrongBinder()), list.readInt(), list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      list.enforceInterface("android.content.IContentService");
      unregisterContentObserver(IContentObserver.Stub.asInterface(list.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.content.IContentService");
    return true;
  }
  
  private static class Proxy implements IContentService {
    public static IContentService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void addPeriodicSync(Account param2Account, String param2String, Bundle param2Bundle, long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
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
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().addPeriodicSync(param2Account, param2String, param2Bundle, param2Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addStatusChangeListener(int param2Int, ISyncStatusObserver param2ISyncStatusObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.IContentService");
        parcel1.writeInt(param2Int);
        if (param2ISyncStatusObserver != null) {
          iBinder = param2ISyncStatusObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().addStatusChangeListener(param2Int, param2ISyncStatusObserver);
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
    
    public void cancelRequest(SyncRequest param2SyncRequest) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2SyncRequest != null) {
          parcel1.writeInt(1);
          param2SyncRequest.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().cancelRequest(param2SyncRequest);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelSync(Account param2Account, String param2String, ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().cancelSync(param2Account, param2String, param2ComponentName);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelSyncAsUser(Account param2Account, String param2String, ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().cancelSyncAsUser(param2Account, param2String, param2ComponentName, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bundle getCache(String param2String, Uri param2Uri, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        parcel1.writeString(param2String);
        if (param2Uri != null) {
          parcel1.writeInt(1);
          param2Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
          return IContentService.Stub.getDefaultImpl().getCache(param2String, param2Uri, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (Bundle)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<SyncInfo> getCurrentSyncs() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
          return IContentService.Stub.getDefaultImpl().getCurrentSyncs(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(SyncInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<SyncInfo> getCurrentSyncsAsUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
          return IContentService.Stub.getDefaultImpl().getCurrentSyncsAsUser(param2Int); 
        parcel2.readException();
        return parcel2.createTypedArrayList(SyncInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.IContentService";
    }
    
    public int getIsSyncable(Account param2Account, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
          return IContentService.Stub.getDefaultImpl().getIsSyncable(param2Account, param2String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getIsSyncableAsUser(Account param2Account, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          param2Int = IContentService.Stub.getDefaultImpl().getIsSyncableAsUser(param2Account, param2String, param2Int);
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
    
    public boolean getMasterSyncAutomatically() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(23, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          bool = IContentService.Stub.getDefaultImpl().getMasterSyncAutomatically();
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
    
    public boolean getMasterSyncAutomaticallyAsUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(24, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          bool = IContentService.Stub.getDefaultImpl().getMasterSyncAutomaticallyAsUser(param2Int);
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
    
    public List<PeriodicSync> getPeriodicSyncs(Account param2Account, String param2String, ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
          return IContentService.Stub.getDefaultImpl().getPeriodicSyncs(param2Account, param2String, param2ComponentName); 
        parcel2.readException();
        return parcel2.createTypedArrayList(PeriodicSync.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] getSyncAdapterPackagesForAuthorityAsUser(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
          return IContentService.Stub.getDefaultImpl().getSyncAdapterPackagesForAuthorityAsUser(param2String, param2Int); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public SyncAdapterType[] getSyncAdapterTypes() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
          return IContentService.Stub.getDefaultImpl().getSyncAdapterTypes(); 
        parcel2.readException();
        return (SyncAdapterType[])parcel2.createTypedArray(SyncAdapterType.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public SyncAdapterType[] getSyncAdapterTypesAsUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
          return IContentService.Stub.getDefaultImpl().getSyncAdapterTypesAsUser(param2Int); 
        parcel2.readException();
        return (SyncAdapterType[])parcel2.createTypedArray(SyncAdapterType.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getSyncAutomatically(Account param2Account, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          bool = IContentService.Stub.getDefaultImpl().getSyncAutomatically(param2Account, param2String);
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
    
    public boolean getSyncAutomaticallyAsUser(Account param2Account, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          bool = IContentService.Stub.getDefaultImpl().getSyncAutomaticallyAsUser(param2Account, param2String, param2Int);
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
    
    public SyncStatusInfo getSyncStatus(Account param2Account, String param2String, ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
          return IContentService.Stub.getDefaultImpl().getSyncStatus(param2Account, param2String, param2ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          SyncStatusInfo syncStatusInfo = (SyncStatusInfo)SyncStatusInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2Account = null;
        } 
        return (SyncStatusInfo)param2Account;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public SyncStatusInfo getSyncStatusAsUser(Account param2Account, String param2String, ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
          return IContentService.Stub.getDefaultImpl().getSyncStatusAsUser(param2Account, param2String, param2ComponentName, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          SyncStatusInfo syncStatusInfo = (SyncStatusInfo)SyncStatusInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2Account = null;
        } 
        return (SyncStatusInfo)param2Account;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isSyncActive(Account param2Account, String param2String, ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          bool = IContentService.Stub.getDefaultImpl().isSyncActive(param2Account, param2String, param2ComponentName);
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
    
    public boolean isSyncPending(Account param2Account, String param2String, ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          bool = IContentService.Stub.getDefaultImpl().isSyncPending(param2Account, param2String, param2ComponentName);
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
    
    public boolean isSyncPendingAsUser(Account param2Account, String param2String, ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          bool = IContentService.Stub.getDefaultImpl().isSyncPendingAsUser(param2Account, param2String, param2ComponentName, param2Int);
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
    
    public void notifyChange(Uri[] param2ArrayOfUri, IContentObserver param2IContentObserver, boolean param2Boolean, int param2Int1, int param2Int2, int param2Int3, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        try {
          IBinder iBinder;
          boolean bool;
          parcel1.writeTypedArray((Parcelable[])param2ArrayOfUri, 0);
          if (param2IContentObserver != null) {
            iBinder = param2IContentObserver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeInt(param2Int2);
              try {
                parcel1.writeInt(param2Int3);
                try {
                  parcel1.writeString(param2String);
                  if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
                    IContentService.Stub.getDefaultImpl().notifyChange(param2ArrayOfUri, param2IContentObserver, param2Boolean, param2Int1, param2Int2, param2Int3, param2String);
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
      throw param2ArrayOfUri;
    }
    
    public void onDbCorruption(String param2String1, String param2String2, String param2String3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeString(param2String3);
        if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().onDbCorruption(param2String1, param2String2, param2String3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void putCache(String param2String, Uri param2Uri, Bundle param2Bundle, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        parcel1.writeString(param2String);
        if (param2Uri != null) {
          parcel1.writeInt(1);
          param2Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().putCache(param2String, param2Uri, param2Bundle, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerContentObserver(Uri param2Uri, boolean param2Boolean, IContentObserver param2IContentObserver, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.IContentService");
        boolean bool = true;
        if (param2Uri != null) {
          parcel1.writeInt(1);
          param2Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (param2IContentObserver != null) {
          iBinder = param2IContentObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().registerContentObserver(param2Uri, param2Boolean, param2IContentObserver, param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removePeriodicSync(Account param2Account, String param2String, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
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
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().removePeriodicSync(param2Account, param2String, param2Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeStatusChangeListener(ISyncStatusObserver param2ISyncStatusObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2ISyncStatusObserver != null) {
          iBinder = param2ISyncStatusObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().removeStatusChangeListener(param2ISyncStatusObserver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestSync(Account param2Account, String param2String1, Bundle param2Bundle, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String1);
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().requestSync(param2Account, param2String1, param2Bundle, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resetTodayStats() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().resetTodayStats();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setIsSyncable(Account param2Account, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().setIsSyncable(param2Account, param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setIsSyncableAsUser(Account param2Account, String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().setIsSyncableAsUser(param2Account, param2String, param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setMasterSyncAutomatically(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().setMasterSyncAutomatically(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setMasterSyncAutomaticallyAsUser(boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().setMasterSyncAutomaticallyAsUser(param2Boolean, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSyncAutomatically(Account param2Account, String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().setSyncAutomatically(param2Account, param2String, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSyncAutomaticallyAsUser(Account param2Account, String param2String, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        boolean bool = true;
        if (param2Account != null) {
          parcel1.writeInt(1);
          param2Account.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().setSyncAutomaticallyAsUser(param2Account, param2String, param2Boolean, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sync(SyncRequest param2SyncRequest, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2SyncRequest != null) {
          parcel1.writeInt(1);
          param2SyncRequest.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().sync(param2SyncRequest, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void syncAsUser(SyncRequest param2SyncRequest, int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2SyncRequest != null) {
          parcel1.writeInt(1);
          param2SyncRequest.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().syncAsUser(param2SyncRequest, param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterContentObserver(IContentObserver param2IContentObserver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.IContentService");
        if (param2IContentObserver != null) {
          iBinder = param2IContentObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
          IContentService.Stub.getDefaultImpl().unregisterContentObserver(param2IContentObserver);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/IContentService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */