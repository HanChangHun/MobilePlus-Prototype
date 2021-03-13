package android.content;

import android.accounts.Account;
import android.database.IContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IContentService {
  public static IContentService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addPeriodicSync(Account paramAccount, String paramString, Bundle paramBundle, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
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
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().addPeriodicSync(paramAccount, paramString, paramBundle, paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addStatusChangeListener(int paramInt, ISyncStatusObserver paramISyncStatusObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.IContentService");
      parcel1.writeInt(paramInt);
      if (paramISyncStatusObserver != null) {
        iBinder = paramISyncStatusObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().addStatusChangeListener(paramInt, paramISyncStatusObserver);
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
  
  public void cancelRequest(SyncRequest paramSyncRequest) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramSyncRequest != null) {
        parcel1.writeInt(1);
        paramSyncRequest.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().cancelRequest(paramSyncRequest);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelSync(Account paramAccount, String paramString, ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().cancelSync(paramAccount, paramString, paramComponentName);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelSyncAsUser(Account paramAccount, String paramString, ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().cancelSyncAsUser(paramAccount, paramString, paramComponentName, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bundle getCache(String paramString, Uri paramUri, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      parcel1.writeString(paramString);
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
        return IContentService.Stub.getDefaultImpl().getCache(paramString, paramUri, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (Bundle)paramString;
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
  
  public List<SyncInfo> getCurrentSyncsAsUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
        return IContentService.Stub.getDefaultImpl().getCurrentSyncsAsUser(paramInt); 
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
  
  public int getIsSyncable(Account paramAccount, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
        return IContentService.Stub.getDefaultImpl().getIsSyncable(paramAccount, paramString); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getIsSyncableAsUser(Account paramAccount, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        paramInt = IContentService.Stub.getDefaultImpl().getIsSyncableAsUser(paramAccount, paramString, paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
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
  
  public boolean getMasterSyncAutomaticallyAsUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(24, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        bool = IContentService.Stub.getDefaultImpl().getMasterSyncAutomaticallyAsUser(paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<PeriodicSync> getPeriodicSyncs(Account paramAccount, String paramString, ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
        return IContentService.Stub.getDefaultImpl().getPeriodicSyncs(paramAccount, paramString, paramComponentName); 
      parcel2.readException();
      return parcel2.createTypedArrayList(PeriodicSync.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] getSyncAdapterPackagesForAuthorityAsUser(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
        return IContentService.Stub.getDefaultImpl().getSyncAdapterPackagesForAuthorityAsUser(paramString, paramInt); 
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
  
  public SyncAdapterType[] getSyncAdapterTypesAsUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
        return IContentService.Stub.getDefaultImpl().getSyncAdapterTypesAsUser(paramInt); 
      parcel2.readException();
      return (SyncAdapterType[])parcel2.createTypedArray(SyncAdapterType.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean getSyncAutomatically(Account paramAccount, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        bool = IContentService.Stub.getDefaultImpl().getSyncAutomatically(paramAccount, paramString);
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
  
  public boolean getSyncAutomaticallyAsUser(Account paramAccount, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        bool = IContentService.Stub.getDefaultImpl().getSyncAutomaticallyAsUser(paramAccount, paramString, paramInt);
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
  
  public SyncStatusInfo getSyncStatus(Account paramAccount, String paramString, ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
        return IContentService.Stub.getDefaultImpl().getSyncStatus(paramAccount, paramString, paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        SyncStatusInfo syncStatusInfo = (SyncStatusInfo)SyncStatusInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramAccount = null;
      } 
      return (SyncStatusInfo)paramAccount;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public SyncStatusInfo getSyncStatusAsUser(Account paramAccount, String paramString, ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null)
        return IContentService.Stub.getDefaultImpl().getSyncStatusAsUser(paramAccount, paramString, paramComponentName, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        SyncStatusInfo syncStatusInfo = (SyncStatusInfo)SyncStatusInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramAccount = null;
      } 
      return (SyncStatusInfo)paramAccount;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isSyncActive(Account paramAccount, String paramString, ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        bool = IContentService.Stub.getDefaultImpl().isSyncActive(paramAccount, paramString, paramComponentName);
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
  
  public boolean isSyncPending(Account paramAccount, String paramString, ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        bool = IContentService.Stub.getDefaultImpl().isSyncPending(paramAccount, paramString, paramComponentName);
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
  
  public boolean isSyncPendingAsUser(Account paramAccount, String paramString, ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        bool = IContentService.Stub.getDefaultImpl().isSyncPendingAsUser(paramAccount, paramString, paramComponentName, paramInt);
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
  
  public void notifyChange(Uri[] paramArrayOfUri, IContentObserver paramIContentObserver, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      try {
        IBinder iBinder;
        boolean bool;
        parcel1.writeTypedArray((Parcelable[])paramArrayOfUri, 0);
        if (paramIContentObserver != null) {
          iBinder = paramIContentObserver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (paramBoolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        try {
          parcel1.writeInt(paramInt1);
          try {
            parcel1.writeInt(paramInt2);
            try {
              parcel1.writeInt(paramInt3);
              try {
                parcel1.writeString(paramString);
                if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
                  IContentService.Stub.getDefaultImpl().notifyChange(paramArrayOfUri, paramIContentObserver, paramBoolean, paramInt1, paramInt2, paramInt3, paramString);
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
    throw paramArrayOfUri;
  }
  
  public void onDbCorruption(String paramString1, String paramString2, String paramString3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().onDbCorruption(paramString1, paramString2, paramString3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void putCache(String paramString, Uri paramUri, Bundle paramBundle, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      parcel1.writeString(paramString);
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().putCache(paramString, paramUri, paramBundle, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerContentObserver(Uri paramUri, boolean paramBoolean, IContentObserver paramIContentObserver, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.IContentService");
      boolean bool = true;
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (paramIContentObserver != null) {
        iBinder = paramIContentObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().registerContentObserver(paramUri, paramBoolean, paramIContentObserver, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removePeriodicSync(Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
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
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().removePeriodicSync(paramAccount, paramString, paramBundle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeStatusChangeListener(ISyncStatusObserver paramISyncStatusObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramISyncStatusObserver != null) {
        iBinder = paramISyncStatusObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().removeStatusChangeListener(paramISyncStatusObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestSync(Account paramAccount, String paramString1, Bundle paramBundle, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().requestSync(paramAccount, paramString1, paramBundle, paramString2);
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
  
  public void setIsSyncable(Account paramAccount, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().setIsSyncable(paramAccount, paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setIsSyncableAsUser(Account paramAccount, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().setIsSyncableAsUser(paramAccount, paramString, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setMasterSyncAutomatically(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().setMasterSyncAutomatically(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setMasterSyncAutomaticallyAsUser(boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().setMasterSyncAutomaticallyAsUser(paramBoolean, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSyncAutomatically(Account paramAccount, String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().setSyncAutomatically(paramAccount, paramString, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSyncAutomaticallyAsUser(Account paramAccount, String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      boolean bool = true;
      if (paramAccount != null) {
        parcel1.writeInt(1);
        paramAccount.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().setSyncAutomaticallyAsUser(paramAccount, paramString, paramBoolean, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sync(SyncRequest paramSyncRequest, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramSyncRequest != null) {
        parcel1.writeInt(1);
        paramSyncRequest.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().sync(paramSyncRequest, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void syncAsUser(SyncRequest paramSyncRequest, int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramSyncRequest != null) {
        parcel1.writeInt(1);
        paramSyncRequest.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().syncAsUser(paramSyncRequest, paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterContentObserver(IContentObserver paramIContentObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.IContentService");
      if (paramIContentObserver != null) {
        iBinder = paramIContentObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IContentService.Stub.getDefaultImpl() != null) {
        IContentService.Stub.getDefaultImpl().unregisterContentObserver(paramIContentObserver);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/IContentService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */