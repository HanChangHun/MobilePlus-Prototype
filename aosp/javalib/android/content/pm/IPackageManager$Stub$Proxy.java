package android.content.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.dex.IArtManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.List;
import java.util.Map;

class Proxy implements IPackageManager {
  public static IPackageManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public boolean activitySupportsIntent(ComponentName paramComponentName, Intent paramIntent, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().activitySupportsIntent(paramComponentName, paramIntent, paramString);
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
  
  public void addCrossProfileIntentFilter(IntentFilter paramIntentFilter, String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntentFilter != null) {
        parcel1.writeInt(1);
        paramIntentFilter.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(61, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().addCrossProfileIntentFilter(paramIntentFilter, paramString, paramInt1, paramInt2, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean addPermission(PermissionInfo paramPermissionInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      boolean bool = true;
      if (paramPermissionInfo != null) {
        parcel1.writeInt(1);
        paramPermissionInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(186, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().addPermission(paramPermissionInfo);
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
  
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      boolean bool = true;
      if (paramPermissionInfo != null) {
        parcel1.writeInt(1);
        paramPermissionInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(187, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().addPermissionAsync(paramPermissionInfo);
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
  
  public void addPersistentPreferredActivity(IntentFilter paramIntentFilter, ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntentFilter != null) {
        parcel1.writeInt(1);
        paramIntentFilter.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().addPersistentPreferredActivity(paramIntentFilter, paramComponentName, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt1, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntentFilter != null) {
        parcel1.writeInt(1);
        paramIntentFilter.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeTypedArray((Parcelable[])paramArrayOfComponentName, 0);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().addPreferredActivity(paramIntentFilter, paramInt1, paramArrayOfComponentName, paramComponentName, paramInt2);
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
  
  public boolean canForwardTo(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      boolean bool = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().canForwardTo(paramIntent, paramString, paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean canRequestPackageInstalls(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(157, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().canRequestPackageInstalls(paramString, paramInt);
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
  
  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramArrayOfString = IPackageManager.Stub.getDefaultImpl().canonicalToCurrentPackageNames(paramArrayOfString);
        return paramArrayOfString;
      } 
      parcel2.readException();
      paramArrayOfString = parcel2.createStringArray();
      return paramArrayOfString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void checkPackageStartable(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().checkPackageStartable(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int checkPermission(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(189, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt = IPackageManager.Stub.getDefaultImpl().checkPermission(paramString1, paramString2, paramInt);
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
  
  public int checkSignatures(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().checkSignatures(paramString1, paramString2); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int checkUidPermission(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(191, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt = IPackageManager.Stub.getDefaultImpl().checkUidPermission(paramString, paramInt);
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
  
  public int checkUidSignatures(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt1 = IPackageManager.Stub.getDefaultImpl().checkUidSignatures(paramInt1, paramInt2);
        return paramInt1;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      return paramInt1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearApplicationProfileData(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(90, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().clearApplicationProfileData(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearApplicationUserData(String paramString, IPackageDataObserver paramIPackageDataObserver, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (paramIPackageDataObserver != null) {
        iBinder = paramIPackageDataObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(89, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().clearApplicationUserData(paramString, paramIPackageDataObserver, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearCrossProfileIntentFilters(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(62, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().clearCrossProfileIntentFilters(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearPackagePersistentPreferredActivities(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().clearPackagePersistentPreferredActivities(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearPackagePreferredActivities(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().clearPackagePreferredActivities(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean compileLayouts(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(106, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().compileLayouts(paramString);
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
  
  public String[] currentToCanonicalPackageNames(String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramArrayOfString = IPackageManager.Stub.getDefaultImpl().currentToCanonicalPackageNames(paramArrayOfString);
        return paramArrayOfString;
      } 
      parcel2.readException();
      paramArrayOfString = parcel2.createStringArray();
      return paramArrayOfString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deleteApplicationCacheFiles(String paramString, IPackageDataObserver paramIPackageDataObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (paramIPackageDataObserver != null) {
        iBinder = paramIPackageDataObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(87, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().deleteApplicationCacheFiles(paramString, paramIPackageDataObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deleteApplicationCacheFilesAsUser(String paramString, int paramInt, IPackageDataObserver paramIPackageDataObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramIPackageDataObserver != null) {
        iBinder = paramIPackageDataObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(88, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().deleteApplicationCacheFilesAsUser(paramString, paramInt, paramIPackageDataObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deleteExistingPackageAsUser(VersionedPackage paramVersionedPackage, IPackageDeleteObserver2 paramIPackageDeleteObserver2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramVersionedPackage != null) {
        parcel1.writeInt(1);
        paramVersionedPackage.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIPackageDeleteObserver2 != null) {
        iBinder = paramIPackageDeleteObserver2.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().deleteExistingPackageAsUser(paramVersionedPackage, paramIPackageDeleteObserver2, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deletePackageAsUser(String paramString, int paramInt1, IPackageDeleteObserver paramIPackageDeleteObserver, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      if (paramIPackageDeleteObserver != null) {
        iBinder = paramIPackageDeleteObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().deletePackageAsUser(paramString, paramInt1, paramIPackageDeleteObserver, paramInt2, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deletePackageVersioned(VersionedPackage paramVersionedPackage, IPackageDeleteObserver2 paramIPackageDeleteObserver2, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramVersionedPackage != null) {
        parcel1.writeInt(1);
        paramVersionedPackage.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIPackageDeleteObserver2 != null) {
        iBinder = paramIPackageDeleteObserver2.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().deletePackageVersioned(paramVersionedPackage, paramIPackageDeleteObserver2, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deletePreloadsFileCache() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(158, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().deletePreloadsFileCache();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void dumpProfiles(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(107, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().dumpProfiles(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void enterSafeMode() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(95, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().enterSafeMode();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(120, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().extendVerificationTimeout(paramInt1, paramInt2, paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ResolveInfo findPersistentPreferredActivity(Intent paramIntent, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().findPersistentPreferredActivity(paramIntent, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ResolveInfo resolveInfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramIntent = null;
      } 
      return (ResolveInfo)paramIntent;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void finishPackageInstall(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().finishPackageInstall(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void flushPackageRestrictionsAsUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(83, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().flushPackageRestrictionsAsUser(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void forceDexOpt(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(108, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().forceDexOpt(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void freeStorage(String paramString, long paramLong, int paramInt, IntentSender paramIntentSender) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeLong(paramLong);
      parcel1.writeInt(paramInt);
      if (paramIntentSender != null) {
        parcel1.writeInt(1);
        paramIntentSender.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(86, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().freeStorage(paramString, paramLong, paramInt, paramIntentSender);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void freeStorageAndNotify(String paramString, long paramLong, int paramInt, IPackageDataObserver paramIPackageDataObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeLong(paramLong);
      parcel1.writeInt(paramInt);
      if (paramIPackageDataObserver != null) {
        iBinder = paramIPackageDataObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(85, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().freeStorageAndNotify(paramString, paramLong, paramInt, paramIPackageDataObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getActivityInfo(paramComponentName, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ActivityInfo activityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (ActivityInfo)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getAllIntentFilters(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(125, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getAllIntentFilters(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getAllPackages() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getAllPackages(); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] getAppOpPermissionPackages(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(184, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getAppOpPermissionPackages(paramString); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getAppPredictionServicePackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(172, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getAppPredictionServicePackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getApplicationEnabledSetting(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(81, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt = IPackageManager.Stub.getDefaultImpl().getApplicationEnabledSetting(paramString, paramInt);
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
  
  public boolean getApplicationHiddenSettingAsUser(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(132, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().getApplicationHiddenSettingAsUser(paramString, paramInt);
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
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getApplicationInfo(paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ApplicationInfo)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IArtManager getArtManager() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(163, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getArtManager(); 
      parcel2.readException();
      return IArtManager.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getAttentionServicePackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(170, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getAttentionServicePackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean getBlockUninstallForUser(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(137, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().getBlockUninstallForUser(paramString, paramInt);
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
  
  public ChangedPackages getChangedPackages(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ChangedPackages changedPackages;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(152, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        changedPackages = IPackageManager.Stub.getDefaultImpl().getChangedPackages(paramInt1, paramInt2);
        return changedPackages;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        changedPackages = (ChangedPackages)ChangedPackages.CREATOR.createFromParcel(parcel2);
      } else {
        changedPackages = null;
      } 
      return changedPackages;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getComponentEnabledSetting(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(79, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt = IPackageManager.Stub.getDefaultImpl().getComponentEnabledSetting(paramComponentName, paramInt);
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
  
  public String getContentCaptureServicePackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(176, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getContentCaptureServicePackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getDeclaredSharedLibraries(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(156, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getDeclaredSharedLibraries(paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public byte[] getDefaultAppsBackup(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getDefaultAppsBackup(paramInt); 
      parcel2.readException();
      return parcel2.createByteArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getDefaultTextClassifierPackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(168, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getDefaultTextClassifierPackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getFlagsForUid(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt = IPackageManager.Stub.getDefaultImpl().getFlagsForUid(paramInt);
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
  
  public CharSequence getHarmfulAppWarning(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(165, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getHarmfulAppWarning(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getHomeActivities(List<ResolveInfo> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName1;
      ComponentName componentName2;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        componentName1 = IPackageManager.Stub.getDefaultImpl().getHomeActivities(paramList);
        return componentName1;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        componentName2 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
      } else {
        componentName2 = null;
      } 
      parcel2.readTypedList((List)componentName1, ResolveInfo.CREATOR);
      return componentName2;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getIncidentReportApproverPackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(175, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getIncidentReportApproverPackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getInstallLocation() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(117, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getInstallLocation(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getInstallReason(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(154, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt = IPackageManager.Stub.getDefaultImpl().getInstallReason(paramString, paramInt);
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
  
  public InstallSourceInfo getInstallSourceInfo(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getInstallSourceInfo(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        InstallSourceInfo installSourceInfo = (InstallSourceInfo)InstallSourceInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (InstallSourceInfo)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getInstalledApplications(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = IPackageManager.Stub.getDefaultImpl().getInstalledApplications(paramInt1, paramInt2);
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ModuleInfo> getInstalledModules(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(179, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getInstalledModules(paramInt); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ModuleInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getInstalledPackages(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = IPackageManager.Stub.getDefaultImpl().getInstalledPackages(paramInt1, paramInt2);
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInstallerPackageName(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(50, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramString = IPackageManager.Stub.getDefaultImpl().getInstallerPackageName(paramString);
        return paramString;
      } 
      parcel2.readException();
      paramString = parcel2.readString();
      return paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInstantAppAndroidId(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(162, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramString = IPackageManager.Stub.getDefaultImpl().getInstantAppAndroidId(paramString, paramInt);
        return paramString;
      } 
      parcel2.readException();
      paramString = parcel2.readString();
      return paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public byte[] getInstantAppCookie(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(144, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getInstantAppCookie(paramString, paramInt); 
      parcel2.readException();
      return parcel2.createByteArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bitmap getInstantAppIcon(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(146, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getInstantAppIcon(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (Bitmap)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getInstantAppInstallerComponent() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(161, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        componentName = IPackageManager.Stub.getDefaultImpl().getInstantAppInstallerComponent();
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
  
  public ComponentName getInstantAppResolverComponent() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(159, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        componentName = IPackageManager.Stub.getDefaultImpl().getInstantAppResolverComponent();
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
  
  public ComponentName getInstantAppResolverSettingsComponent() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(160, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        componentName = IPackageManager.Stub.getDefaultImpl().getInstantAppResolverSettingsComponent();
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
  
  public ParceledListSlice getInstantApps(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(143, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = IPackageManager.Stub.getDefaultImpl().getInstantApps(paramInt);
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getInstrumentationInfo(paramComponentName, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        InstrumentationInfo instrumentationInfo = (InstrumentationInfo)InstrumentationInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (InstrumentationInfo)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public byte[] getIntentFilterVerificationBackup(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(72, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getIntentFilterVerificationBackup(paramInt); 
      parcel2.readException();
      return parcel2.createByteArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getIntentFilterVerifications(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(124, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getIntentFilterVerifications(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getIntentVerificationStatus(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(122, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt = IPackageManager.Stub.getDefaultImpl().getIntentVerificationStatus(paramString, paramInt);
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
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPackageManager";
  }
  
  public KeySet getKeySetByAlias(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(138, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getKeySetByAlias(paramString1, paramString2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        KeySet keySet = (KeySet)KeySet.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (KeySet)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ResolveInfo getLastChosenActivity(Intent paramIntent, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getLastChosenActivity(paramIntent, paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ResolveInfo resolveInfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramIntent = null;
      } 
      return (ResolveInfo)paramIntent;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getMimeGroup(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(193, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getMimeGroup(paramString1, paramString2); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ModuleInfo getModuleInfo(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(180, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getModuleInfo(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ModuleInfo moduleInfo = (ModuleInfo)ModuleInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ModuleInfo)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getMoveStatus(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(111, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt = IPackageManager.Stub.getDefaultImpl().getMoveStatus(paramInt);
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
  
  public String getNameForUid(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getNameForUid(paramInt); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] getNamesForUids(int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getNamesForUids(paramArrayOfint); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int[] getPackageGids(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getPackageGids(paramString, paramInt1, paramInt2); 
      parcel2.readException();
      return parcel2.createIntArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getPackageInfo(paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        PackageInfo packageInfo = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (PackageInfo)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public PackageInfo getPackageInfoVersioned(VersionedPackage paramVersionedPackage, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramVersionedPackage != null) {
        parcel1.writeInt(1);
        paramVersionedPackage.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getPackageInfoVersioned(paramVersionedPackage, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        PackageInfo packageInfo = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramVersionedPackage = null;
      } 
      return (PackageInfo)paramVersionedPackage;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IPackageInstaller getPackageInstaller() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(135, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getPackageInstaller(); 
      parcel2.readException();
      return IPackageInstaller.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void getPackageSizeInfo(String paramString, int paramInt, IPackageStatsObserver paramIPackageStatsObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramIPackageStatsObserver != null) {
        iBinder = paramIPackageStatsObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(91, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().getPackageSizeInfo(paramString, paramInt, paramIPackageStatsObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPackageUid(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt1 = IPackageManager.Stub.getDefaultImpl().getPackageUid(paramString, paramInt1, paramInt2);
        return paramInt1;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      return paramInt1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] getPackagesForUid(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getPackagesForUid(paramInt); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getPackagesHoldingPermissions(paramArrayOfString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramArrayOfString = null;
      } 
      return (ParceledListSlice)paramArrayOfString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getPermissionControllerPackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(142, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getPermissionControllerPackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(185, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getPermissionGroupInfo(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        PermissionGroupInfo permissionGroupInfo = (PermissionGroupInfo)PermissionGroupInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (PermissionGroupInfo)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getPersistentApplications(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = IPackageManager.Stub.getDefaultImpl().getPersistentApplications(paramInt);
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(58, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getPreferredActivities(paramList, paramList1, paramString); 
      parcel2.readException();
      int i = parcel2.readInt();
      parcel2.readTypedList(paramList, IntentFilter.CREATOR);
      parcel2.readTypedList(paramList1, ComponentName.CREATOR);
      return i;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public byte[] getPreferredActivityBackup(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getPreferredActivityBackup(paramInt); 
      parcel2.readException();
      return parcel2.createByteArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPrivateFlagsForUid(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt = IPackageManager.Stub.getDefaultImpl().getPrivateFlagsForUid(paramInt);
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
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getProviderInfo(paramComponentName, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ProviderInfo providerInfo = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (ProviderInfo)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getReceiverInfo(paramComponentName, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ActivityInfo activityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (ActivityInfo)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getRuntimePermissionsVersion(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(181, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt = IPackageManager.Stub.getDefaultImpl().getRuntimePermissionsVersion(paramInt);
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
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getServiceInfo(paramComponentName, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ServiceInfo serviceInfo = (ServiceInfo)ServiceInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (ServiceInfo)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getServicesSystemSharedLibraryPackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(150, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getServicesSystemSharedLibraryPackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getSetupWizardPackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(174, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getSetupWizardPackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getSharedLibraries(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(155, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getSharedLibraries(paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getSharedSystemSharedLibraryPackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(151, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getSharedSystemSharedLibraryPackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public KeySet getSigningKeySet(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(139, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getSigningKeySet(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        KeySet keySet = (KeySet)KeySet.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (KeySet)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bundle getSuspendedPackageAppExtras(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getSuspendedPackageAppExtras(paramString, paramInt); 
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
  
  public ParceledListSlice getSystemAvailableFeatures() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(93, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = IPackageManager.Stub.getDefaultImpl().getSystemAvailableFeatures();
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getSystemCaptionsServicePackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(173, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getSystemCaptionsServicePackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] getSystemSharedLibraryNames() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(92, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getSystemSharedLibraryNames(); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getSystemTextClassifierPackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(169, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getSystemTextClassifierPackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getUidForSharedUser(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getUidForSharedUser(paramString); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] getUnsuspendablePackagesForUser(String[] paramArrayOfString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramArrayOfString = IPackageManager.Stub.getDefaultImpl().getUnsuspendablePackagesForUser(paramArrayOfString, paramInt);
        return paramArrayOfString;
      } 
      parcel2.readException();
      paramArrayOfString = parcel2.createStringArray();
      return paramArrayOfString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public VerifierDeviceIdentity getVerifierDeviceIdentity() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      VerifierDeviceIdentity verifierDeviceIdentity;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(126, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        verifierDeviceIdentity = IPackageManager.Stub.getDefaultImpl().getVerifierDeviceIdentity();
        return verifierDeviceIdentity;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        verifierDeviceIdentity = (VerifierDeviceIdentity)VerifierDeviceIdentity.CREATOR.createFromParcel(parcel2);
      } else {
        verifierDeviceIdentity = null;
      } 
      return verifierDeviceIdentity;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getWellbeingPackageName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(171, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().getWellbeingPackageName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void grantImplicitAccess(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(195, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().grantImplicitAccess(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void grantRuntimePermission(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(190, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().grantRuntimePermission(paramString1, paramString2, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasSigningCertificate(String paramString, byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeByteArray(paramArrayOfbyte);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(166, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().hasSigningCertificate(paramString, paramArrayOfbyte, paramInt);
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
  
  public boolean hasSystemFeature(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(94, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().hasSystemFeature(paramString, paramInt);
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
  
  public boolean hasSystemUidErrors() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(98, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().hasSystemUidErrors();
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
  
  public boolean hasUidSigningCertificate(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeByteArray(paramArrayOfbyte);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(167, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().hasUidSigningCertificate(paramInt1, paramArrayOfbyte, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int installExistingPackageAsUser(String paramString, int paramInt1, int paramInt2, int paramInt3, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(118, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramInt1 = IPackageManager.Stub.getDefaultImpl().installExistingPackageAsUser(paramString, paramInt1, paramInt2, paramInt3, paramList);
        return paramInt1;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      return paramInt1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isAutoRevokeWhitelisted(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(194, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isAutoRevokeWhitelisted(paramString);
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
  
  public boolean isDeviceUpgrading() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(129, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isDeviceUpgrading();
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
  
  public boolean isFirstBoot() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(127, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isFirstBoot();
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
  
  public boolean isInstantApp(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(147, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isInstantApp(paramString, paramInt);
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
  
  public boolean isOnlyCoreApps() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(128, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isOnlyCoreApps();
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
  
  public boolean isPackageAvailable(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isPackageAvailable(paramString, paramInt);
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
  
  public boolean isPackageDeviceAdminOnAnyUser(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(153, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isPackageDeviceAdminOnAnyUser(paramString);
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
  
  public boolean isPackageSignedByKeySet(String paramString, KeySet paramKeySet) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      boolean bool = true;
      if (paramKeySet != null) {
        parcel1.writeInt(1);
        paramKeySet.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(140, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isPackageSignedByKeySet(paramString, paramKeySet);
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
  
  public boolean isPackageSignedByKeySetExactly(String paramString, KeySet paramKeySet) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      boolean bool = true;
      if (paramKeySet != null) {
        parcel1.writeInt(1);
        paramKeySet.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(141, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isPackageSignedByKeySetExactly(paramString, paramKeySet);
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
  
  public boolean isPackageStateProtected(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(177, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isPackageStateProtected(paramString, paramInt);
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
  
  public boolean isPackageSuspendedForUser(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(66, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isPackageSuspendedForUser(paramString, paramInt);
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
  
  public boolean isProtectedBroadcast(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(15, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isProtectedBroadcast(paramString);
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
  
  public boolean isSafeMode() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(96, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isSafeMode();
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
  
  public boolean isStorageLow() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(130, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isStorageLow();
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
  
  public boolean isUidPrivileged(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(25, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().isUidPrivileged(paramInt);
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
  
  public void logAppProcessStartIfNeeded(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(82, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().logAppProcessStartIfNeeded(paramString1, paramInt1, paramString2, paramString3, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int movePackage(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(114, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().movePackage(paramString1, paramString2); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int movePrimaryStorage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(115, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().movePrimaryStorage(paramString); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyDexLoad(String paramString1, Map<String, String> paramMap, String paramString2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel.writeString(paramString1);
      if (paramMap == null) {
        parcel.writeInt(-1);
      } else {
        parcel.writeInt(paramMap.size());
        _$$Lambda$IPackageManager$Stub$Proxy$X2I1qlX4SiKMZSjDTNzS_nTibbo _$$Lambda$IPackageManager$Stub$Proxy$X2I1qlX4SiKMZSjDTNzS_nTibbo = new _$$Lambda$IPackageManager$Stub$Proxy$X2I1qlX4SiKMZSjDTNzS_nTibbo();
        this(parcel);
        paramMap.forEach(_$$Lambda$IPackageManager$Stub$Proxy$X2I1qlX4SiKMZSjDTNzS_nTibbo);
      } 
      parcel.writeString(paramString2);
      if (!this.mRemote.transact(102, parcel, null, 1) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().notifyDexLoad(paramString1, paramMap, paramString2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void notifyPackageUse(String paramString, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(101, parcel, null, 1) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().notifyPackageUse(paramString, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void notifyPackagesReplacedReceived(String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(183, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().notifyPackagesReplacedReceived(paramArrayOfString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void overrideLabelAndIcon(ComponentName paramComponentName, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().overrideLabelAndIcon(paramComponentName, paramString, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean performDexOptMode(String paramString1, boolean paramBoolean1, String paramString2, boolean paramBoolean2, boolean paramBoolean3, String paramString3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      try {
        int i;
        parcel1.writeString(paramString1);
        boolean bool = true;
        if (paramBoolean1) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        try {
          parcel1.writeString(paramString2);
          if (paramBoolean2) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          if (paramBoolean3) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          try {
            parcel1.writeString(paramString3);
            try {
              if (!this.mRemote.transact(104, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
                paramBoolean1 = IPackageManager.Stub.getDefaultImpl().performDexOptMode(paramString1, paramBoolean1, paramString2, paramBoolean2, paramBoolean3, paramString3);
                parcel2.recycle();
                parcel1.recycle();
                return paramBoolean1;
              } 
              parcel2.readException();
              i = parcel2.readInt();
              if (i != 0) {
                paramBoolean1 = bool;
              } else {
                paramBoolean1 = false;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return paramBoolean1;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public boolean performDexOptSecondary(String paramString1, String paramString2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      boolean bool = true;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(105, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IPackageManager.Stub.getDefaultImpl().performDexOptSecondary(paramString1, paramString2, paramBoolean);
        return paramBoolean;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0) {
        paramBoolean = bool;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void performFstrimIfNeeded() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(99, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().performFstrimIfNeeded();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice queryContentProviders(String paramString1, int paramInt1, int paramInt2, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().queryContentProviders(paramString1, paramInt1, paramInt2, paramString2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (ParceledListSlice)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice queryInstrumentation(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().queryInstrumentation(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice queryIntentActivities(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().queryIntentActivities(paramIntent, paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramIntent = null;
      } 
      return (ParceledListSlice)paramIntent;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, String[] paramArrayOfString, Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeTypedArray((Parcelable[])paramArrayOfIntent, 0);
        try {
          parcel1.writeStringArray(paramArrayOfString);
          if (paramIntent != null) {
            parcel1.writeInt(1);
            paramIntent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeString(paramString);
            parcel1.writeInt(paramInt1);
            parcel1.writeInt(paramInt2);
            if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
              ParceledListSlice parceledListSlice = IPackageManager.Stub.getDefaultImpl().queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramArrayOfString, paramIntent, paramString, paramInt1, paramInt2);
              parcel2.recycle();
              parcel1.recycle();
              return parceledListSlice;
            } 
            parcel2.readException();
            if (parcel2.readInt() != 0) {
              ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
            } else {
              paramComponentName = null;
            } 
            parcel2.recycle();
            parcel1.recycle();
            return (ParceledListSlice)paramComponentName;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramComponentName;
  }
  
  public ParceledListSlice queryIntentContentProviders(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().queryIntentContentProviders(paramIntent, paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramIntent = null;
      } 
      return (ParceledListSlice)paramIntent;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice queryIntentReceivers(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().queryIntentReceivers(paramIntent, paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramIntent = null;
      } 
      return (ParceledListSlice)paramIntent;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice queryIntentServices(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().queryIntentServices(paramIntent, paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramIntent = null;
      } 
      return (ParceledListSlice)paramIntent;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void querySyncProviders(List<String> paramList, List<ProviderInfo> paramList1) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeStringList(paramList);
      parcel1.writeTypedList(paramList1);
      if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().querySyncProviders(paramList, paramList1);
        return;
      } 
      parcel2.readException();
      parcel2.readStringList(paramList);
      parcel2.readTypedList(paramList1, ProviderInfo.CREATOR);
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reconcileSecondaryDexFiles(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(110, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().reconcileSecondaryDexFiles(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerDexModule(String paramString1, String paramString2, boolean paramBoolean, IDexModuleRegisterCallback paramIDexModuleRegisterCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      IBinder iBinder;
      parcel.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel.writeString(paramString1);
      parcel.writeString(paramString2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramIDexModuleRegisterCallback != null) {
        iBinder = paramIDexModuleRegisterCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(103, parcel, null, 1) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().registerDexModule(paramString1, paramString2, paramBoolean, paramIDexModuleRegisterCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void registerMoveCallback(IPackageMoveObserver paramIPackageMoveObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIPackageMoveObserver != null) {
        iBinder = paramIPackageMoveObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(112, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().registerMoveCallback(paramIPackageMoveObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removePermission(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(188, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().removePermission(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void replacePreferredActivity(IntentFilter paramIntentFilter, int paramInt1, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntentFilter != null) {
        parcel1.writeInt(1);
        paramIntentFilter.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeTypedArray((Parcelable[])paramArrayOfComponentName, 0);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().replacePreferredActivity(paramIntentFilter, paramInt1, paramArrayOfComponentName, paramComponentName, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void resetApplicationPreferences(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().resetApplicationPreferences(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().resolveContentProvider(paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ProviderInfo providerInfo = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ProviderInfo)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().resolveIntent(paramIntent, paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ResolveInfo resolveInfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramIntent = null;
      } 
      return (ResolveInfo)paramIntent;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null)
        return IPackageManager.Stub.getDefaultImpl().resolveService(paramIntent, paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ResolveInfo resolveInfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramIntent = null;
      } 
      return (ResolveInfo)paramIntent;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void restoreDefaultApps(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeByteArray(paramArrayOfbyte);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(71, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().restoreDefaultApps(paramArrayOfbyte, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void restoreIntentFilterVerification(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeByteArray(paramArrayOfbyte);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().restoreIntentFilterVerification(paramArrayOfbyte, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void restoreLabelAndIcon(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(77, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().restoreLabelAndIcon(paramComponentName, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void restorePreferredActivities(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeByteArray(paramArrayOfbyte);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().restorePreferredActivities(paramArrayOfbyte, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean runBackgroundDexoptJob(List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeStringList(paramList);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(109, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().runBackgroundDexoptJob(paramList);
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
  
  public void sendDeviceCustomizationReadyBroadcast() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(178, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().sendDeviceCustomizationReadyBroadcast();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setApplicationCategoryHint(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().setApplicationCategoryHint(paramString1, paramInt, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setApplicationEnabledSetting(String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(80, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().setApplicationEnabledSetting(paramString1, paramInt1, paramInt2, paramInt3, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setApplicationHiddenSettingAsUser(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      boolean bool1 = true;
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(131, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IPackageManager.Stub.getDefaultImpl().setApplicationHiddenSettingAsUser(paramString, paramBoolean, paramInt);
        return paramBoolean;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setBlockUninstallForUser(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      boolean bool1 = true;
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(136, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IPackageManager.Stub.getDefaultImpl().setBlockUninstallForUser(paramString, paramBoolean, paramInt);
        return paramBoolean;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(78, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] setDistractingPackageRestrictionsAsUser(String[] paramArrayOfString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(63, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramArrayOfString = IPackageManager.Stub.getDefaultImpl().setDistractingPackageRestrictionsAsUser(paramArrayOfString, paramInt1, paramInt2);
        return paramArrayOfString;
      } 
      parcel2.readException();
      paramArrayOfString = parcel2.createStringArray();
      return paramArrayOfString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setHarmfulAppWarning(String paramString, CharSequence paramCharSequence, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (paramCharSequence != null) {
        parcel1.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(164, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().setHarmfulAppWarning(paramString, paramCharSequence, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setHomeActivity(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().setHomeActivity(paramComponentName, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setInstallLocation(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(116, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().setInstallLocation(paramInt);
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
  
  public void setInstallerPackageName(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().setInstallerPackageName(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setInstantAppCookie(String paramString, byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeByteArray(paramArrayOfbyte);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(145, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().setInstantAppCookie(paramString, paramArrayOfbyte, paramInt);
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
  
  public void setLastChosenActivity(Intent paramIntent, String paramString, int paramInt1, IntentFilter paramIntentFilter, int paramInt2, ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeString(paramString);
        try {
          parcel1.writeInt(paramInt1);
          if (paramIntentFilter != null) {
            parcel1.writeInt(1);
            paramIntentFilter.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeInt(paramInt2);
            if (paramComponentName != null) {
              parcel1.writeInt(1);
              paramComponentName.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
              IPackageManager.Stub.getDefaultImpl().setLastChosenActivity(paramIntent, paramString, paramInt1, paramIntentFilter, paramInt2, paramComponentName);
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
    parcel2.recycle();
    parcel1.recycle();
    throw paramIntent;
  }
  
  public void setMimeGroup(String paramString1, String paramString2, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(192, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().setMimeGroup(paramString1, paramString2, paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPackageStoppedState(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(84, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().setPackageStoppedState(paramString, paramBoolean, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] setPackagesSuspendedAsUser(String[] paramArrayOfString, boolean paramBoolean, PersistableBundle paramPersistableBundle1, PersistableBundle paramPersistableBundle2, SuspendDialogInfo paramSuspendDialogInfo, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      try {
        boolean bool;
        parcel1.writeStringArray(paramArrayOfString);
        if (paramBoolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (paramPersistableBundle1 != null) {
          parcel1.writeInt(1);
          paramPersistableBundle1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramPersistableBundle2 != null) {
          parcel1.writeInt(1);
          paramPersistableBundle2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramSuspendDialogInfo != null) {
          parcel1.writeInt(1);
          paramSuspendDialogInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(paramString);
          parcel1.writeInt(paramInt);
          if (!this.mRemote.transact(64, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
            paramArrayOfString = IPackageManager.Stub.getDefaultImpl().setPackagesSuspendedAsUser(paramArrayOfString, paramBoolean, paramPersistableBundle1, paramPersistableBundle2, paramSuspendDialogInfo, paramString, paramInt);
            parcel2.recycle();
            parcel1.recycle();
            return paramArrayOfString;
          } 
          parcel2.readException();
          paramArrayOfString = parcel2.createStringArray();
          parcel2.recycle();
          parcel1.recycle();
          return paramArrayOfString;
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramArrayOfString;
  }
  
  public boolean setRequiredForSystemUser(String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      boolean bool = true;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(148, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IPackageManager.Stub.getDefaultImpl().setRequiredForSystemUser(paramString, paramBoolean);
        return paramBoolean;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0) {
        paramBoolean = bool;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setRuntimePermissionsVersion(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(182, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().setRuntimePermissionsVersion(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSystemAppHiddenUntilInstalled(String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(133, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().setSystemAppHiddenUntilInstalled(paramString, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setSystemAppInstallState(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      boolean bool1 = true;
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(134, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IPackageManager.Stub.getDefaultImpl().setSystemAppInstallState(paramString, paramBoolean, paramInt);
        return paramBoolean;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setUpdateAvailable(String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(149, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().setUpdateAvailable(paramString, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void systemReady() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(97, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().systemReady();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterMoveCallback(IPackageMoveObserver paramIPackageMoveObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (paramIPackageMoveObserver != null) {
        iBinder = paramIPackageMoveObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(113, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().unregisterMoveCallback(paramIPackageMoveObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean updateIntentVerificationStatus(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(123, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        bool = IPackageManager.Stub.getDefaultImpl().updateIntentVerificationStatus(paramString, paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updatePackagesIfNeeded() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      if (!this.mRemote.transact(100, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().updatePackagesIfNeeded();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void verifyIntentFilter(int paramInt1, int paramInt2, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(121, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().verifyIntentFilter(paramInt1, paramInt2, paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void verifyPendingInstall(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(119, parcel1, parcel2, 0) && IPackageManager.Stub.getDefaultImpl() != null) {
        IPackageManager.Stub.getDefaultImpl().verifyPendingInstall(paramInt1, paramInt2);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */