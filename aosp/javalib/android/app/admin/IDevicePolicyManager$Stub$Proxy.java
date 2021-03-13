package android.app.admin;

import android.app.IApplicationThread;
import android.app.IServiceConnection;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.IPackageDataObserver;
import android.content.pm.ParceledListSlice;
import android.content.pm.StringParceledListSlice;
import android.graphics.Bitmap;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.UserHandle;
import android.security.keymaster.KeymasterCertificateChain;
import android.security.keystore.ParcelableKeyGenParameterSpec;
import android.telephony.data.ApnSetting;
import android.text.TextUtils;
import java.util.List;

class Proxy implements IDevicePolicyManager {
  public static IDevicePolicyManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addCrossProfileIntentFilter(ComponentName paramComponentName, IntentFilter paramIntentFilter, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIntentFilter != null) {
        parcel1.writeInt(1);
        paramIntentFilter.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(124, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().addCrossProfileIntentFilter(paramComponentName, paramIntentFilter, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean addCrossProfileWidgetProvider(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(185, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().addCrossProfileWidgetProvider(paramComponentName, paramString);
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
  
  public int addOverrideApn(ComponentName paramComponentName, ApnSetting paramApnSetting) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramApnSetting != null) {
        parcel1.writeInt(1);
        paramApnSetting.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(279, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().addOverrideApn(paramComponentName, paramApnSetting); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addPersistentPreferredActivity(ComponentName paramComponentName1, IntentFilter paramIntentFilter, ComponentName paramComponentName2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName1 != null) {
        parcel1.writeInt(1);
        paramComponentName1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIntentFilter != null) {
        parcel1.writeInt(1);
        paramIntentFilter.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramComponentName2 != null) {
        parcel1.writeInt(1);
        paramComponentName2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(112, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().addPersistentPreferredActivity(paramComponentName1, paramIntentFilter, paramComponentName2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean approveCaCert(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      boolean bool1 = true;
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(94, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IDevicePolicyManager.Stub.getDefaultImpl().approveCaCert(paramString, paramInt, paramBoolean);
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
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public boolean bindDeviceAdminServiceAsUser(ComponentName paramComponentName, IApplicationThread paramIApplicationThread, IBinder paramIBinder, Intent paramIntent, IServiceConnection paramIServiceConnection, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder2;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      IBinder iBinder1 = null;
      if (paramIApplicationThread != null) {
        iBinder2 = paramIApplicationThread.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      try {
        parcel1.writeStrongBinder(paramIBinder);
        if (paramIntent != null) {
          parcel1.writeInt(1);
          paramIntent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        iBinder2 = iBinder1;
        if (paramIServiceConnection != null)
          iBinder2 = paramIServiceConnection.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        try {
          parcel1.writeInt(paramInt1);
          try {
            parcel1.writeInt(paramInt2);
            if (!this.mRemote.transact(255, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
              bool = IDevicePolicyManager.Stub.getDefaultImpl().bindDeviceAdminServiceAsUser(paramComponentName, paramIApplicationThread, paramIBinder, paramIntent, paramIServiceConnection, paramInt1, paramInt2);
              parcel2.recycle();
              parcel1.recycle();
              return bool;
            } 
            parcel2.readException();
            paramInt1 = parcel2.readInt();
            if (paramInt1 == 0)
              bool = false; 
            parcel2.recycle();
            parcel1.recycle();
            return bool;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramComponentName;
  }
  
  public boolean canProfileOwnerResetPasswordWhenLocked(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(311, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().canProfileOwnerResetPasswordWhenLocked(paramInt);
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
  
  public boolean checkDeviceIdentifierAccess(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(86, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().checkDeviceIdentifierAccess(paramString, paramInt1, paramInt2);
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
  
  public int checkProvisioningPreCondition(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(211, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().checkProvisioningPreCondition(paramString1, paramString2); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void choosePrivateKeyAlias(int paramInt, Uri paramUri, String paramString, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(100, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().choosePrivateKeyAlias(paramInt, paramUri, paramString, paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearApplicationUserData(ComponentName paramComponentName, String paramString, IPackageDataObserver paramIPackageDataObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramIPackageDataObserver != null) {
        iBinder = paramIPackageDataObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(267, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().clearApplicationUserData(paramComponentName, paramString, paramIPackageDataObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearCrossProfileIntentFilters(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(125, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().clearCrossProfileIntentFilters(paramComponentName);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearDeviceOwner(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().clearDeviceOwner(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearPackagePersistentPreferredActivities(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(113, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().clearPackagePersistentPreferredActivities(paramComponentName, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearProfileOwner(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(83, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().clearProfileOwner(paramComponentName);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean clearResetPasswordToken(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(262, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().clearResetPasswordToken(paramComponentName);
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
  
  public void clearSystemUpdatePolicyFreezePeriodRecord() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(200, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().clearSystemUpdatePolicyFreezePeriodRecord();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Intent createAdminSupportIntent(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(137, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().createAdminSupportIntent(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (Intent)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public UserHandle createAndManageUser(ComponentName paramComponentName1, String paramString, ComponentName paramComponentName2, PersistableBundle paramPersistableBundle, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName1 != null) {
        parcel1.writeInt(1);
        paramComponentName1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramComponentName2 != null) {
        parcel1.writeInt(1);
        paramComponentName2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramPersistableBundle != null) {
        parcel1.writeInt(1);
        paramPersistableBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(140, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().createAndManageUser(paramComponentName1, paramString, paramComponentName2, paramPersistableBundle, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName1 = null;
      } 
      return (UserHandle)paramComponentName1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void enableSystemApp(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(147, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().enableSystemApp(paramComponentName, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int enableSystemAppWithIntent(ComponentName paramComponentName, String paramString, Intent paramIntent) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(148, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().enableSystemAppWithIntent(paramComponentName, paramString, paramIntent); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void enforceCanManageCaCerts(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(93, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().enforceCanManageCaCerts(paramComponentName, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long forceNetworkLogs() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(242, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().forceNetworkLogs(); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void forceRemoveActiveAdmin(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(61, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().forceRemoveActiveAdmin(paramComponentName, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long forceSecurityLogs() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(243, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().forceSecurityLogs(); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void forceUpdateUserSetupComplete() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(249, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().forceUpdateUserSetupComplete();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean generateKeyPair(ComponentName paramComponentName, String paramString1, String paramString2, ParcelableKeyGenParameterSpec paramParcelableKeyGenParameterSpec, int paramInt, KeymasterCertificateChain paramKeymasterCertificateChain) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          if (paramParcelableKeyGenParameterSpec != null) {
            parcel1.writeInt(1);
            paramParcelableKeyGenParameterSpec.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeInt(paramInt);
            try {
              if (!this.mRemote.transact(98, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                bool = IDevicePolicyManager.Stub.getDefaultImpl().generateKeyPair(paramComponentName, paramString1, paramString2, paramParcelableKeyGenParameterSpec, paramInt, paramKeymasterCertificateChain);
                parcel2.recycle();
                parcel1.recycle();
                return bool;
              } 
              parcel2.readException();
              if (parcel2.readInt() == 0)
                bool = false; 
              paramInt = parcel2.readInt();
              if (paramInt != 0)
                try {
                  paramKeymasterCertificateChain.readFromParcel(parcel2);
                } finally {} 
              parcel2.recycle();
              parcel1.recycle();
              return bool;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramComponentName;
  }
  
  public String[] getAccountTypesWithManagementDisabled() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(151, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getAccountTypesWithManagementDisabled(); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] getAccountTypesWithManagementDisabledAsUser(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(152, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getAccountTypesWithManagementDisabledAsUser(paramInt, paramBoolean); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ComponentName> getActiveAdmins(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getActiveAdmins(paramInt); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ComponentName.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getAffiliationIds(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(236, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getAffiliationIds(paramComponentName); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getAllCrossProfilePackages() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(297, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getAllCrossProfilePackages(); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getAlwaysOnVpnLockdownWhitelist(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(111, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getAlwaysOnVpnLockdownWhitelist(paramComponentName); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getAlwaysOnVpnPackage(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(107, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getAlwaysOnVpnPackage(paramComponentName); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getAlwaysOnVpnPackageForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(108, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getAlwaysOnVpnPackageForUser(paramInt); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bundle getApplicationRestrictions(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(116, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getApplicationRestrictions(paramComponentName, paramString1, paramString2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (Bundle)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getApplicationRestrictionsManagingPackage(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(118, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getApplicationRestrictionsManagingPackage(paramComponentName); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean getAutoTimeEnabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(191, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getAutoTimeEnabled(paramComponentName);
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
  
  public boolean getAutoTimeRequired() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(189, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getAutoTimeRequired();
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
  
  public boolean getAutoTimeZoneEnabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(193, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getAutoTimeZoneEnabled(paramComponentName);
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
  
  public List<UserHandle> getBindDeviceAdminTargetUsers(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(256, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getBindDeviceAdminTargetUsers(paramComponentName); 
      parcel2.readException();
      return parcel2.createTypedArrayList(UserHandle.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean getBluetoothContactSharingDisabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(181, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getBluetoothContactSharingDisabled(paramComponentName);
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
  
  public boolean getBluetoothContactSharingDisabledForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(182, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getBluetoothContactSharingDisabledForUser(paramInt);
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
  
  public boolean getCameraDisabled(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool1 = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(50, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IDevicePolicyManager.Stub.getDefaultImpl().getCameraDisabled(paramComponentName, paramInt, paramBoolean);
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
  
  public String getCertInstallerPackage(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(105, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getCertInstallerPackage(paramComponentName); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getCrossProfileCalendarPackages(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(292, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileCalendarPackages(paramComponentName); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getCrossProfileCalendarPackagesForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(294, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileCalendarPackagesForUser(paramInt); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean getCrossProfileCallerIdDisabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(174, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileCallerIdDisabled(paramComponentName);
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
  
  public boolean getCrossProfileCallerIdDisabledForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(175, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileCallerIdDisabledForUser(paramInt);
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
  
  public boolean getCrossProfileContactsSearchDisabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(177, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileContactsSearchDisabled(paramComponentName);
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
  
  public boolean getCrossProfileContactsSearchDisabledForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(178, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileContactsSearchDisabledForUser(paramInt);
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
  
  public List<String> getCrossProfilePackages(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(296, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfilePackages(paramComponentName); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getCrossProfileWidgetProviders(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(187, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getCrossProfileWidgetProviders(paramComponentName); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getCurrentFailedPasswordAttempts(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getCurrentFailedPasswordAttempts(paramInt, paramBoolean);
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
  
  public List<String> getDefaultCrossProfilePackages() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(298, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getDefaultCrossProfilePackages(); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getDelegatePackages(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(103, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getDelegatePackages(paramComponentName, paramString); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getDelegatedScopes(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(102, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getDelegatedScopes(paramComponentName, paramString); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getDeviceOwnerComponent(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(71, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        componentName = IDevicePolicyManager.Stub.getDefaultImpl().getDeviceOwnerComponent(paramBoolean);
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
  
  public CharSequence getDeviceOwnerLockScreenInfo() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      CharSequence charSequence;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(88, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        charSequence = IDevicePolicyManager.Stub.getDefaultImpl().getDeviceOwnerLockScreenInfo();
        return charSequence;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        charSequence = null;
      } 
      return charSequence;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getDeviceOwnerName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getDeviceOwnerName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public CharSequence getDeviceOwnerOrganizationName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      CharSequence charSequence;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(231, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        charSequence = IDevicePolicyManager.Stub.getDefaultImpl().getDeviceOwnerOrganizationName();
        return charSequence;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        charSequence = null;
      } 
      return charSequence;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getDeviceOwnerUserId() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getDeviceOwnerUserId(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getDisallowedSystemApps(ComponentName paramComponentName, int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(270, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getDisallowedSystemApps(paramComponentName, paramInt, paramString); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean getDoNotAskCredentialsOnBoot() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(203, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getDoNotAskCredentialsOnBoot();
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
  
  public CharSequence getEndUserSessionMessage(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(276, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getEndUserSessionMessage(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (CharSequence)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public FactoryResetProtectionPolicy getFactoryResetProtectionPolicy(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getFactoryResetProtectionPolicy(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        FactoryResetProtectionPolicy factoryResetProtectionPolicy = (FactoryResetProtectionPolicy)FactoryResetProtectionPolicy.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (FactoryResetProtectionPolicy)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean getForceEphemeralUsers(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(195, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getForceEphemeralUsers(paramComponentName);
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
  
  public String getGlobalPrivateDnsHost(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(288, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getGlobalPrivateDnsHost(paramComponentName); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getGlobalPrivateDnsMode(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(287, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getGlobalPrivateDnsMode(paramComponentName); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getGlobalProxyAdmin(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        componentName = IDevicePolicyManager.Stub.getDefaultImpl().getGlobalProxyAdmin(paramInt);
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
  
  public String getInterfaceDescriptor() {
    return "android.app.admin.IDevicePolicyManager";
  }
  
  public List<String> getKeepUninstalledPackages(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(213, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getKeepUninstalledPackages(paramComponentName, paramString); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getKeyguardDisabledFeatures(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getKeyguardDisabledFeatures(paramComponentName, paramInt, paramBoolean);
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
  
  public long getLastBugReportRequestTime() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(259, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getLastBugReportRequestTime(); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getLastNetworkLogRetrievalTime() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(260, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getLastNetworkLogRetrievalTime(); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getLastSecurityLogRetrievalTime() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(258, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getLastSecurityLogRetrievalTime(); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getLockTaskFeatures(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(159, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getLockTaskFeatures(paramComponentName); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] getLockTaskPackages(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(156, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getLockTaskPackages(paramComponentName); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public CharSequence getLongSupportMessage(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(221, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getLongSupportMessage(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (CharSequence)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public CharSequence getLongSupportMessageForUser(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(223, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getLongSupportMessageForUser(paramComponentName, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (CharSequence)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getManagedProfileMaximumTimeOff(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(309, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getManagedProfileMaximumTimeOff(paramComponentName); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getMaximumFailedPasswordsForWipe(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getMaximumFailedPasswordsForWipe(paramComponentName, paramInt, paramBoolean);
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
  
  public long getMaximumTimeToLock(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getMaximumTimeToLock(paramComponentName, paramInt, paramBoolean); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getMeteredDataDisabledPackages(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(278, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getMeteredDataDisabledPackages(paramComponentName); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getOrganizationColor(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(227, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getOrganizationColor(paramComponentName); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getOrganizationColorForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(228, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getOrganizationColorForUser(paramInt);
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
  
  public CharSequence getOrganizationName(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(230, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getOrganizationName(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (CharSequence)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public CharSequence getOrganizationNameForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      CharSequence charSequence;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(232, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        charSequence = IDevicePolicyManager.Stub.getDefaultImpl().getOrganizationNameForUser(paramInt);
        return charSequence;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        charSequence = null;
      } 
      return charSequence;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ApnSetting> getOverrideApns(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(282, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getOverrideApns(paramComponentName); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ApnSetting.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public StringParceledListSlice getOwnerInstalledCaCerts(UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(266, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getOwnerInstalledCaCerts(paramUserHandle); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        StringParceledListSlice stringParceledListSlice = (StringParceledListSlice)StringParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramUserHandle = null;
      } 
      return (StringParceledListSlice)paramUserHandle;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPasswordComplexity(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        i = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordComplexity(paramBoolean);
        return i;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      return i;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getPasswordExpiration(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getPasswordExpiration(paramComponentName, paramInt, paramBoolean); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getPasswordExpirationTimeout(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getPasswordExpirationTimeout(paramComponentName, paramInt, paramBoolean); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPasswordHistoryLength(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordHistoryLength(paramComponentName, paramInt, paramBoolean);
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
  
  public int getPasswordMinimumLength(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumLength(paramComponentName, paramInt, paramBoolean);
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
  
  public int getPasswordMinimumLetters(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumLetters(paramComponentName, paramInt, paramBoolean);
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
  
  public int getPasswordMinimumLowerCase(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumLowerCase(paramComponentName, paramInt, paramBoolean);
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
  
  public PasswordMetrics getPasswordMinimumMetrics(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      PasswordMetrics passwordMetrics;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        passwordMetrics = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumMetrics(paramInt);
        return passwordMetrics;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        passwordMetrics = (PasswordMetrics)PasswordMetrics.CREATOR.createFromParcel(parcel2);
      } else {
        passwordMetrics = null;
      } 
      return passwordMetrics;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPasswordMinimumNonLetter(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumNonLetter(paramComponentName, paramInt, paramBoolean);
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
  
  public int getPasswordMinimumNumeric(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumNumeric(paramComponentName, paramInt, paramBoolean);
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
  
  public int getPasswordMinimumSymbols(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumSymbols(paramComponentName, paramInt, paramBoolean);
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
  
  public int getPasswordMinimumUpperCase(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordMinimumUpperCase(paramComponentName, paramInt, paramBoolean);
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
  
  public int getPasswordQuality(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getPasswordQuality(paramComponentName, paramInt, paramBoolean);
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
  
  public SystemUpdateInfo getPendingSystemUpdate(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(205, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getPendingSystemUpdate(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        SystemUpdateInfo systemUpdateInfo = (SystemUpdateInfo)SystemUpdateInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (SystemUpdateInfo)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPermissionGrantState(ComponentName paramComponentName, String paramString1, String paramString2, String paramString3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      if (!this.mRemote.transact(209, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getPermissionGrantState(paramComponentName, paramString1, paramString2, paramString3); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPermissionPolicy(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(207, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getPermissionPolicy(paramComponentName); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List getPermittedAccessibilityServices(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(127, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getPermittedAccessibilityServices(paramComponentName); 
      parcel2.readException();
      return parcel2.readArrayList(getClass().getClassLoader());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List getPermittedAccessibilityServicesForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(128, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getPermittedAccessibilityServicesForUser(paramInt); 
      parcel2.readException();
      return parcel2.readArrayList(getClass().getClassLoader());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getPermittedCrossProfileNotificationListeners(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(135, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getPermittedCrossProfileNotificationListeners(paramComponentName); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List getPermittedInputMethods(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(131, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getPermittedInputMethods(paramComponentName); 
      parcel2.readException();
      return parcel2.readArrayList(getClass().getClassLoader());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List getPermittedInputMethodsForCurrentUser() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(132, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getPermittedInputMethodsForCurrentUser(); 
      parcel2.readException();
      return parcel2.readArrayList(getClass().getClassLoader());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPersonalAppsSuspendedReasons(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(307, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getPersonalAppsSuspendedReasons(paramComponentName); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getProfileOwner(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(78, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        componentName = IDevicePolicyManager.Stub.getDefaultImpl().getProfileOwner(paramInt);
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
  
  public ComponentName getProfileOwnerAsUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(77, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        componentName = IDevicePolicyManager.Stub.getDefaultImpl().getProfileOwnerAsUser(paramInt);
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
  
  public String getProfileOwnerName(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(80, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getProfileOwnerName(paramInt); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getProfileOwnerOrDeviceOwnerSupervisionComponent(UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(79, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getProfileOwnerOrDeviceOwnerSupervisionComponent(paramUserHandle); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
      } else {
        paramUserHandle = null;
      } 
      return (ComponentName)paramUserHandle;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getProfileWithMinimumFailedPasswordsForWipe(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getProfileWithMinimumFailedPasswordsForWipe(paramInt, paramBoolean);
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
  
  public void getRemoveWarning(ComponentName paramComponentName, RemoteCallback paramRemoteCallback, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramRemoteCallback != null) {
        parcel1.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().getRemoveWarning(paramComponentName, paramRemoteCallback, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getRequiredStrongAuthTimeout(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getRequiredStrongAuthTimeout(paramComponentName, paramInt, paramBoolean); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getRestrictionsProvider(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(121, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        componentName = IDevicePolicyManager.Stub.getDefaultImpl().getRestrictionsProvider(paramInt);
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
  
  public boolean getScreenCaptureDisabled(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool1 = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IDevicePolicyManager.Stub.getDefaultImpl().getScreenCaptureDisabled(paramComponentName, paramInt, paramBoolean);
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
  
  public List<UserHandle> getSecondaryUsers(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(146, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getSecondaryUsers(paramComponentName); 
      parcel2.readException();
      return parcel2.createTypedArrayList(UserHandle.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public CharSequence getShortSupportMessage(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(219, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getShortSupportMessage(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (CharSequence)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public CharSequence getShortSupportMessageForUser(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(222, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getShortSupportMessageForUser(paramComponentName, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (CharSequence)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public CharSequence getStartUserSessionMessage(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(275, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getStartUserSessionMessage(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (CharSequence)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean getStorageEncryption(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().getStorageEncryption(paramComponentName, paramInt);
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
  
  public int getStorageEncryptionStatus(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().getStorageEncryptionStatus(paramString, paramInt);
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
  
  public SystemUpdatePolicy getSystemUpdatePolicy() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      SystemUpdatePolicy systemUpdatePolicy;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(199, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        systemUpdatePolicy = IDevicePolicyManager.Stub.getDefaultImpl().getSystemUpdatePolicy();
        return systemUpdatePolicy;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        systemUpdatePolicy = (SystemUpdatePolicy)SystemUpdatePolicy.CREATOR.createFromParcel(parcel2);
      } else {
        systemUpdatePolicy = null;
      } 
      return systemUpdatePolicy;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public PersistableBundle getTransferOwnershipBundle() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      PersistableBundle persistableBundle;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(272, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        persistableBundle = IDevicePolicyManager.Stub.getDefaultImpl().getTransferOwnershipBundle();
        return persistableBundle;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(parcel2);
      } else {
        persistableBundle = null;
      } 
      return persistableBundle;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<PersistableBundle> getTrustAgentConfiguration(ComponentName paramComponentName1, ComponentName paramComponentName2, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName1 != null) {
        parcel1.writeInt(1);
        paramComponentName1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramComponentName2 != null) {
        parcel1.writeInt(1);
        paramComponentName2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(184, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getTrustAgentConfiguration(paramComponentName1, paramComponentName2, paramInt, paramBoolean); 
      parcel2.readException();
      return parcel2.createTypedArrayList(PersistableBundle.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getUserControlDisabledPackages(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(304, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getUserControlDisabledPackages(paramComponentName); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getUserProvisioningState() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(233, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getUserProvisioningState(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bundle getUserRestrictions(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(123, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getUserRestrictions(paramComponentName, paramBoolean); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (Bundle)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getWifiMacAddress(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(216, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().getWifiMacAddress(paramComponentName); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasDeviceOwner() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(72, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().hasDeviceOwner();
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
  
  public boolean hasGrantedPolicy(ComponentName paramComponentName, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(62, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().hasGrantedPolicy(paramComponentName, paramInt1, paramInt2);
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
  
  public boolean hasLockdownAdminConfiguredNetworks(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(164, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().hasLockdownAdminConfiguredNetworks(paramComponentName);
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
  
  public boolean hasUserSetupCompleted() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(84, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().hasUserSetupCompleted();
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
  
  public boolean installCaCert(ComponentName paramComponentName, String paramString, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(91, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().installCaCert(paramComponentName, paramString, paramArrayOfbyte);
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
  
  public boolean installExistingPackage(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(149, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().installExistingPackage(paramComponentName, paramString1, paramString2);
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
  
  public boolean installKeyPair(ComponentName paramComponentName, String paramString1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, String paramString2, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeByteArray(paramArrayOfbyte1);
          try {
            parcel1.writeByteArray(paramArrayOfbyte2);
            parcel1.writeByteArray(paramArrayOfbyte3);
            parcel1.writeString(paramString2);
            if (paramBoolean1) {
              i = 1;
            } else {
              i = 0;
            } 
            parcel1.writeInt(i);
            if (paramBoolean2) {
              i = 1;
            } else {
              i = 0;
            } 
            parcel1.writeInt(i);
            if (!this.mRemote.transact(96, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
              paramBoolean1 = IDevicePolicyManager.Stub.getDefaultImpl().installKeyPair(paramComponentName, paramString1, paramArrayOfbyte1, paramArrayOfbyte2, paramArrayOfbyte3, paramString2, paramBoolean1, paramBoolean2);
              parcel2.recycle();
              parcel1.recycle();
              return paramBoolean1;
            } 
            parcel2.readException();
            int i = parcel2.readInt();
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
    parcel2.recycle();
    parcel1.recycle();
    throw paramComponentName;
  }
  
  public void installUpdateFromFile(ComponentName paramComponentName, ParcelFileDescriptor paramParcelFileDescriptor, StartInstallingUpdateCallback paramStartInstallingUpdateCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramParcelFileDescriptor != null) {
        parcel1.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramStartInstallingUpdateCallback != null) {
        iBinder = paramStartInstallingUpdateCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(290, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().installUpdateFromFile(paramComponentName, paramParcelFileDescriptor, paramStartInstallingUpdateCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isAccessibilityServicePermittedByAdmin(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(129, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isAccessibilityServicePermittedByAdmin(paramComponentName, paramString, paramInt);
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
  
  public boolean isActivePasswordSufficient(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      boolean bool1 = true;
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IDevicePolicyManager.Stub.getDefaultImpl().isActivePasswordSufficient(paramInt, paramBoolean);
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
  
  public boolean isAdminActive(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isAdminActive(paramComponentName, paramInt);
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
  
  public boolean isAffiliatedUser() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(237, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isAffiliatedUser();
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
  
  public boolean isAlwaysOnVpnLockdownEnabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(109, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isAlwaysOnVpnLockdownEnabled(paramComponentName);
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
  
  public boolean isAlwaysOnVpnLockdownEnabledForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(110, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isAlwaysOnVpnLockdownEnabledForUser(paramInt);
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
  
  public boolean isApplicationHidden(ComponentName paramComponentName, String paramString1, String paramString2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(139, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IDevicePolicyManager.Stub.getDefaultImpl().isApplicationHidden(paramComponentName, paramString1, paramString2, paramBoolean);
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
  
  public boolean isBackupServiceEnabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(251, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isBackupServiceEnabled(paramComponentName);
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
  
  public boolean isCaCertApproved(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(95, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isCaCertApproved(paramString, paramInt);
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
  
  public boolean isCallerApplicationRestrictionsManagingPackage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(119, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isCallerApplicationRestrictionsManagingPackage(paramString);
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
  
  public boolean isCommonCriteriaModeEnabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(306, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isCommonCriteriaModeEnabled(paramComponentName);
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
  
  public boolean isCurrentInputMethodSetByOwner() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(265, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isCurrentInputMethodSetByOwner();
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
  
  public boolean isDeviceProvisioned() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(246, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isDeviceProvisioned();
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
  
  public boolean isDeviceProvisioningConfigApplied() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(247, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isDeviceProvisioningConfigApplied();
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
  
  public boolean isEphemeralUser(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(257, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isEphemeralUser(paramComponentName);
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
  
  public boolean isFactoryResetProtectionPolicySupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(41, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isFactoryResetProtectionPolicySupported();
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
  
  public boolean isInputMethodPermittedByAdmin(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(133, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isInputMethodPermittedByAdmin(paramComponentName, paramString, paramInt);
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
  
  public boolean isLockTaskPermitted(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(157, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isLockTaskPermitted(paramString);
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
  
  public boolean isLogoutEnabled() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(269, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isLogoutEnabled();
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
  
  public boolean isManagedKiosk() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(299, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isManagedKiosk();
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
  
  public boolean isManagedProfile(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(214, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isManagedProfile(paramComponentName);
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
  
  public boolean isMasterVolumeMuted(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(169, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isMasterVolumeMuted(paramComponentName);
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
  
  public boolean isMeteredDataDisabledPackageForUser(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(285, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isMeteredDataDisabledPackageForUser(paramComponentName, paramString, paramInt);
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
  
  public boolean isNetworkLoggingEnabled(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(253, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isNetworkLoggingEnabled(paramComponentName, paramString);
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
  
  public boolean isNotificationListenerServicePermitted(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(136, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isNotificationListenerServicePermitted(paramString, paramInt);
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
  
  public boolean isOrganizationOwnedDeviceWithManagedProfile() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(85, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isOrganizationOwnedDeviceWithManagedProfile();
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
  
  public boolean isOverrideApnEnabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(284, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isOverrideApnEnabled(paramComponentName);
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
  
  public boolean isPackageAllowedToAccessCalendarForUser(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(293, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isPackageAllowedToAccessCalendarForUser(paramString, paramInt);
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
  
  public boolean isPackageSuspended(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(90, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isPackageSuspended(paramComponentName, paramString1, paramString2);
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
  
  public boolean isPasswordSufficientAfterProfileUnification(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(25, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isPasswordSufficientAfterProfileUnification(paramInt1, paramInt2);
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
  
  public boolean isProfileActivePasswordSufficientForParent(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(24, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isProfileActivePasswordSufficientForParent(paramInt);
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
  
  public boolean isProvisioningAllowed(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(210, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isProvisioningAllowed(paramString1, paramString2);
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
  
  public boolean isRemovingAdmin(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(196, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isRemovingAdmin(paramComponentName, paramInt);
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
  
  public boolean isResetPasswordTokenActive(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(263, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isResetPasswordTokenActive(paramComponentName);
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
  
  public boolean isSecondaryLockscreenEnabled(UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(154, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isSecondaryLockscreenEnabled(paramUserHandle);
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
  
  public boolean isSecurityLoggingEnabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(239, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isSecurityLoggingEnabled(paramComponentName);
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
  
  public boolean isSeparateProfileChallengeAllowed(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(224, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isSeparateProfileChallengeAllowed(paramInt);
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
  
  public boolean isSystemOnlyUser(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(215, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isSystemOnlyUser(paramComponentName);
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
  
  public boolean isUnattendedManagedKiosk() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(300, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isUnattendedManagedKiosk();
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
  
  public boolean isUninstallBlocked(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(172, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isUninstallBlocked(paramComponentName, paramString);
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
  
  public boolean isUninstallInQueue(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(244, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isUninstallInQueue(paramString);
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
  
  public boolean isUsingUnifiedPassword(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().isUsingUnifiedPassword(paramComponentName);
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
  
  public void lockNow(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().lockNow(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int logoutUser(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(145, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().logoutUser(paramComponentName); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void markProfileOwnerOnOrganizationOwnedDevice(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(289, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().markProfileOwnerOnOrganizationOwnedDevice(paramComponentName, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyLockTaskModeChanged(boolean paramBoolean, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(170, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().notifyLockTaskModeChanged(paramBoolean, paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyPendingSystemUpdate(SystemUpdateInfo paramSystemUpdateInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramSystemUpdateInfo != null) {
        parcel1.writeInt(1);
        paramSystemUpdateInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(204, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().notifyPendingSystemUpdate(paramSystemUpdateInfo);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean packageHasActiveAdmins(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(58, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().packageHasActiveAdmins(paramString, paramInt);
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
  
  public void reboot(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(217, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().reboot(paramComponentName);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeActiveAdmin(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().removeActiveAdmin(paramComponentName, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean removeCrossProfileWidgetProvider(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(186, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().removeCrossProfileWidgetProvider(paramComponentName, paramString);
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
  
  public boolean removeKeyPair(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(97, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().removeKeyPair(paramComponentName, paramString1, paramString2);
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
  
  public boolean removeOverrideApn(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(281, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().removeOverrideApn(paramComponentName, paramInt);
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
  
  public boolean removeUser(ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(141, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().removeUser(paramComponentName, paramUserHandle);
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
  
  public void reportFailedBiometricAttempt(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(66, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().reportFailedBiometricAttempt(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportFailedPasswordAttempt(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(64, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().reportFailedPasswordAttempt(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportKeyguardDismissed(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().reportKeyguardDismissed(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportKeyguardSecured(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().reportKeyguardSecured(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportPasswordChanged(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(63, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().reportPasswordChanged(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportSuccessfulBiometricAttempt(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().reportSuccessfulBiometricAttempt(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportSuccessfulPasswordAttempt(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().reportSuccessfulPasswordAttempt(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean requestBugreport(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().requestBugreport(paramComponentName);
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
  
  public boolean resetPassword(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(32, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().resetPassword(paramString, paramInt);
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
  
  public boolean resetPasswordWithToken(ComponentName paramComponentName, String paramString, byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeByteArray(paramArrayOfbyte);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(264, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().resetPasswordWithToken(paramComponentName, paramString, paramArrayOfbyte, paramInt);
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
  
  public List<NetworkEvent> retrieveNetworkLogs(ComponentName paramComponentName, String paramString, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(254, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().retrieveNetworkLogs(paramComponentName, paramString, paramLong); 
      parcel2.readException();
      return parcel2.createTypedArrayList(NetworkEvent.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice retrievePreRebootSecurityLogs(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(241, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().retrievePreRebootSecurityLogs(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (ParceledListSlice)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice retrieveSecurityLogs(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(240, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().retrieveSecurityLogs(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (ParceledListSlice)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAccountManagementDisabled(ComponentName paramComponentName, String paramString, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool1 = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean2) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(150, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setAccountManagementDisabled(paramComponentName, paramString, paramBoolean1, paramBoolean2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setActiveAdmin(ComponentName paramComponentName, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setActiveAdmin(paramComponentName, paramBoolean, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAffiliationIds(ComponentName paramComponentName, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(235, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setAffiliationIds(paramComponentName, paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setAlwaysOnVpnPackage(ComponentName paramComponentName, String paramString, boolean paramBoolean, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(106, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IDevicePolicyManager.Stub.getDefaultImpl().setAlwaysOnVpnPackage(paramComponentName, paramString, paramBoolean, paramList);
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
  
  public boolean setApplicationHidden(ComponentName paramComponentName, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeString(paramString1);
        try {
          int i;
          parcel1.writeString(paramString2);
          if (paramBoolean1) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          if (paramBoolean2) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          try {
            if (!this.mRemote.transact(138, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
              paramBoolean1 = IDevicePolicyManager.Stub.getDefaultImpl().setApplicationHidden(paramComponentName, paramString1, paramString2, paramBoolean1, paramBoolean2);
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
    parcel2.recycle();
    parcel1.recycle();
    throw paramComponentName;
  }
  
  public void setApplicationRestrictions(ComponentName paramComponentName, String paramString1, String paramString2, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(115, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setApplicationRestrictions(paramComponentName, paramString1, paramString2, paramBundle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setApplicationRestrictionsManagingPackage(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(117, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().setApplicationRestrictionsManagingPackage(paramComponentName, paramString);
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
  
  public void setAutoTimeEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(190, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setAutoTimeEnabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAutoTimeRequired(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(188, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setAutoTimeRequired(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAutoTimeZoneEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(192, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setAutoTimeZoneEnabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setBackupServiceEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(250, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setBackupServiceEnabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setBluetoothContactSharingDisabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(180, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setBluetoothContactSharingDisabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setCameraDisabled(ComponentName paramComponentName, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool1 = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean2) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setCameraDisabled(paramComponentName, paramBoolean1, paramBoolean2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setCertInstallerPackage(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(104, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setCertInstallerPackage(paramComponentName, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setCommonCriteriaModeEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(305, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setCommonCriteriaModeEnabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setConfiguredNetworksLockdownState(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(163, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setConfiguredNetworksLockdownState(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setCrossProfileCalendarPackages(ComponentName paramComponentName, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(291, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setCrossProfileCalendarPackages(paramComponentName, paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setCrossProfileCallerIdDisabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(173, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setCrossProfileCallerIdDisabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setCrossProfileContactsSearchDisabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(176, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setCrossProfileContactsSearchDisabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setCrossProfilePackages(ComponentName paramComponentName, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(295, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setCrossProfilePackages(paramComponentName, paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setDefaultSmsApplication(ComponentName paramComponentName, String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(114, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setDefaultSmsApplication(paramComponentName, paramString, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setDelegatedScopes(ComponentName paramComponentName, String paramString, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(101, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setDelegatedScopes(paramComponentName, paramString, paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setDeviceOwner(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().setDeviceOwner(paramComponentName, paramString, paramInt);
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
  
  public void setDeviceOwnerLockScreenInfo(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramCharSequence != null) {
        parcel1.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(87, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setDeviceOwnerLockScreenInfo(paramComponentName, paramCharSequence);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setDeviceProvisioningConfigApplied() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (!this.mRemote.transact(248, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setDeviceProvisioningConfigApplied();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setEndUserSessionMessage(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramCharSequence != null) {
        parcel1.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(274, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setEndUserSessionMessage(paramComponentName, paramCharSequence);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setFactoryResetProtectionPolicy(ComponentName paramComponentName, FactoryResetProtectionPolicy paramFactoryResetProtectionPolicy) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramFactoryResetProtectionPolicy != null) {
        parcel1.writeInt(1);
        paramFactoryResetProtectionPolicy.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setFactoryResetProtectionPolicy(paramComponentName, paramFactoryResetProtectionPolicy);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setForceEphemeralUsers(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(194, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setForceEphemeralUsers(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int setGlobalPrivateDns(ComponentName paramComponentName, int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(286, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramInt = IDevicePolicyManager.Stub.getDefaultImpl().setGlobalPrivateDns(paramComponentName, paramInt, paramString);
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
  
  public ComponentName setGlobalProxy(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramComponentName = IDevicePolicyManager.Stub.getDefaultImpl().setGlobalProxy(paramComponentName, paramString1, paramString2);
        return paramComponentName;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        paramComponentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setGlobalSetting(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(160, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setGlobalSetting(paramComponentName, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setKeepUninstalledPackages(ComponentName paramComponentName, String paramString, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(212, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setKeepUninstalledPackages(paramComponentName, paramString, paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setKeyGrantForApp(ComponentName paramComponentName, String paramString1, String paramString2, String paramString3, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          try {
            int i;
            parcel1.writeString(paramString3);
            if (paramBoolean) {
              i = 1;
            } else {
              i = 0;
            } 
            parcel1.writeInt(i);
            try {
              if (!this.mRemote.transact(302, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                paramBoolean = IDevicePolicyManager.Stub.getDefaultImpl().setKeyGrantForApp(paramComponentName, paramString1, paramString2, paramString3, paramBoolean);
                parcel2.recycle();
                parcel1.recycle();
                return paramBoolean;
              } 
              parcel2.readException();
              i = parcel2.readInt();
              if (i != 0) {
                paramBoolean = bool;
              } else {
                paramBoolean = false;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return paramBoolean;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramComponentName;
  }
  
  public boolean setKeyPairCertificate(ComponentName paramComponentName, String paramString1, String paramString2, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          try {
            parcel1.writeByteArray(paramArrayOfbyte1);
            try {
              int i;
              parcel1.writeByteArray(paramArrayOfbyte2);
              if (paramBoolean) {
                i = 1;
              } else {
                i = 0;
              } 
              parcel1.writeInt(i);
              try {
                if (!this.mRemote.transact(99, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                  paramBoolean = IDevicePolicyManager.Stub.getDefaultImpl().setKeyPairCertificate(paramComponentName, paramString1, paramString2, paramArrayOfbyte1, paramArrayOfbyte2, paramBoolean);
                  parcel2.recycle();
                  parcel1.recycle();
                  return paramBoolean;
                } 
                parcel2.readException();
                i = parcel2.readInt();
                if (i != 0) {
                  paramBoolean = bool;
                } else {
                  paramBoolean = false;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return paramBoolean;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramComponentName;
  }
  
  public boolean setKeyguardDisabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(201, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IDevicePolicyManager.Stub.getDefaultImpl().setKeyguardDisabled(paramComponentName, paramBoolean);
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
  
  public void setKeyguardDisabledFeatures(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setKeyguardDisabledFeatures(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setLocationEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(165, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setLocationEnabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setLockTaskFeatures(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(158, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setLockTaskFeatures(paramComponentName, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setLockTaskPackages(ComponentName paramComponentName, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(155, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setLockTaskPackages(paramComponentName, paramArrayOfString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setLogoutEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(268, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setLogoutEnabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setLongSupportMessage(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramCharSequence != null) {
        parcel1.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(220, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setLongSupportMessage(paramComponentName, paramCharSequence);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setManagedProfileMaximumTimeOff(ComponentName paramComponentName, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(310, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setManagedProfileMaximumTimeOff(paramComponentName, paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setMasterVolumeMuted(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(168, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setMasterVolumeMuted(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setMaximumFailedPasswordsForWipe(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setMaximumFailedPasswordsForWipe(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setMaximumTimeToLock(ComponentName paramComponentName, long paramLong, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeLong(paramLong);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setMaximumTimeToLock(paramComponentName, paramLong, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> setMeteredDataDisabledPackages(ComponentName paramComponentName, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(277, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().setMeteredDataDisabledPackages(paramComponentName, paramList); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNetworkLoggingEnabled(ComponentName paramComponentName, String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(252, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setNetworkLoggingEnabled(paramComponentName, paramString, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setOrganizationColor(ComponentName paramComponentName, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(225, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setOrganizationColor(paramComponentName, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setOrganizationColorForUser(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(226, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setOrganizationColorForUser(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setOrganizationName(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramCharSequence != null) {
        parcel1.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(229, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setOrganizationName(paramComponentName, paramCharSequence);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setOverrideApnsEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(283, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setOverrideApnsEnabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] setPackagesSuspended(ComponentName paramComponentName, String paramString, String[] paramArrayOfString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeStringArray(paramArrayOfString);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(89, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().setPackagesSuspended(paramComponentName, paramString, paramArrayOfString, paramBoolean); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPasswordExpirationTimeout(ComponentName paramComponentName, long paramLong, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeLong(paramLong);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPasswordExpirationTimeout(paramComponentName, paramLong, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPasswordHistoryLength(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPasswordHistoryLength(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPasswordMinimumLength(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumLength(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPasswordMinimumLetters(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumLetters(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPasswordMinimumLowerCase(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumLowerCase(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPasswordMinimumNonLetter(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumNonLetter(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPasswordMinimumNumeric(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumNumeric(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPasswordMinimumSymbols(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumSymbols(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPasswordMinimumUpperCase(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPasswordMinimumUpperCase(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPasswordQuality(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPasswordQuality(paramComponentName, paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPermissionGrantState(ComponentName paramComponentName, String paramString1, String paramString2, String paramString3, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          try {
            parcel1.writeString(paramString3);
            try {
              parcel1.writeInt(paramInt);
              if (paramRemoteCallback != null) {
                parcel1.writeInt(1);
                paramRemoteCallback.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (!this.mRemote.transact(208, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
                IDevicePolicyManager.Stub.getDefaultImpl().setPermissionGrantState(paramComponentName, paramString1, paramString2, paramString3, paramInt, paramRemoteCallback);
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
    throw paramComponentName;
  }
  
  public void setPermissionPolicy(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(206, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPermissionPolicy(paramComponentName, paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setPermittedAccessibilityServices(ComponentName paramComponentName, List paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeList(paramList);
      if (!this.mRemote.transact(126, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().setPermittedAccessibilityServices(paramComponentName, paramList);
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
  
  public boolean setPermittedCrossProfileNotificationListeners(ComponentName paramComponentName, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(134, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().setPermittedCrossProfileNotificationListeners(paramComponentName, paramList);
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
  
  public boolean setPermittedInputMethods(ComponentName paramComponentName, List paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeList(paramList);
      if (!this.mRemote.transact(130, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().setPermittedInputMethods(paramComponentName, paramList);
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
  
  public void setPersonalAppsSuspended(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(308, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setPersonalAppsSuspended(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setProfileEnabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(81, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setProfileEnabled(paramComponentName);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setProfileName(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(82, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setProfileName(paramComponentName, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setProfileOwner(ComponentName paramComponentName, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().setProfileOwner(paramComponentName, paramString, paramInt);
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
  
  public void setRecommendedGlobalProxy(ComponentName paramComponentName, ProxyInfo paramProxyInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramProxyInfo != null) {
        parcel1.writeInt(1);
        paramProxyInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setRecommendedGlobalProxy(paramComponentName, paramProxyInfo);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setRequiredStrongAuthTimeout(ComponentName paramComponentName, long paramLong, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeLong(paramLong);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setRequiredStrongAuthTimeout(paramComponentName, paramLong, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setResetPasswordToken(ComponentName paramComponentName, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(261, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().setResetPasswordToken(paramComponentName, paramArrayOfbyte);
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
  
  public void setRestrictionsProvider(ComponentName paramComponentName1, ComponentName paramComponentName2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName1 != null) {
        parcel1.writeInt(1);
        paramComponentName1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramComponentName2 != null) {
        parcel1.writeInt(1);
        paramComponentName2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(120, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setRestrictionsProvider(paramComponentName1, paramComponentName2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setScreenCaptureDisabled(ComponentName paramComponentName, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool1 = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean2) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setScreenCaptureDisabled(paramComponentName, paramBoolean1, paramBoolean2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSecondaryLockscreenEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(153, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setSecondaryLockscreenEnabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSecureSetting(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(162, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setSecureSetting(paramComponentName, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSecurityLoggingEnabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(238, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setSecurityLoggingEnabled(paramComponentName, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setShortSupportMessage(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramCharSequence != null) {
        parcel1.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(218, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setShortSupportMessage(paramComponentName, paramCharSequence);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setStartUserSessionMessage(ComponentName paramComponentName, CharSequence paramCharSequence) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramCharSequence != null) {
        parcel1.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(273, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setStartUserSessionMessage(paramComponentName, paramCharSequence);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setStatusBarDisabled(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(202, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IDevicePolicyManager.Stub.getDefaultImpl().setStatusBarDisabled(paramComponentName, paramBoolean);
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
  
  public int setStorageEncryption(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      int i = 1;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        i = 0; 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        i = IDevicePolicyManager.Stub.getDefaultImpl().setStorageEncryption(paramComponentName, paramBoolean);
        return i;
      } 
      parcel2.readException();
      i = parcel2.readInt();
      return i;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSystemSetting(ComponentName paramComponentName, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(161, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setSystemSetting(paramComponentName, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSystemUpdatePolicy(ComponentName paramComponentName, SystemUpdatePolicy paramSystemUpdatePolicy) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramSystemUpdatePolicy != null) {
        parcel1.writeInt(1);
        paramSystemUpdatePolicy.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(198, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setSystemUpdatePolicy(paramComponentName, paramSystemUpdatePolicy);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setTime(ComponentName paramComponentName, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(166, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().setTime(paramComponentName, paramLong);
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
  
  public boolean setTimeZone(ComponentName paramComponentName, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(167, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().setTimeZone(paramComponentName, paramString);
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
  
  public void setTrustAgentConfiguration(ComponentName paramComponentName1, ComponentName paramComponentName2, PersistableBundle paramPersistableBundle, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName1 != null) {
        parcel1.writeInt(1);
        paramComponentName1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramComponentName2 != null) {
        parcel1.writeInt(1);
        paramComponentName2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramPersistableBundle != null) {
        parcel1.writeInt(1);
        paramPersistableBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(183, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setTrustAgentConfiguration(paramComponentName1, paramComponentName2, paramPersistableBundle, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setUninstallBlocked(ComponentName paramComponentName, String paramString1, String paramString2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(171, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setUninstallBlocked(paramComponentName, paramString1, paramString2, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setUserControlDisabledPackages(ComponentName paramComponentName, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(303, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setUserControlDisabledPackages(paramComponentName, paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setUserIcon(ComponentName paramComponentName, Bitmap paramBitmap) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBitmap != null) {
        parcel1.writeInt(1);
        paramBitmap.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(197, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setUserIcon(paramComponentName, paramBitmap);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setUserProvisioningState(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(234, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setUserProvisioningState(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setUserRestriction(ComponentName paramComponentName, String paramString, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool1 = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean2) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(122, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().setUserRestriction(paramComponentName, paramString, paramBoolean1, paramBoolean2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startManagedQuickContact(String paramString, long paramLong1, boolean paramBoolean, long paramLong2, Intent paramIntent) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      try {
        parcel1.writeString(paramString);
        try {
          boolean bool;
          parcel1.writeLong(paramLong1);
          if (paramBoolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          parcel1.writeLong(paramLong2);
          if (paramIntent != null) {
            parcel1.writeInt(1);
            paramIntent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            if (!this.mRemote.transact(179, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
              IDevicePolicyManager.Stub.getDefaultImpl().startManagedQuickContact(paramString, paramLong1, paramBoolean, paramLong2, paramIntent);
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
    throw paramString;
  }
  
  public int startUserInBackground(ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(143, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().startUserInBackground(paramComponentName, paramUserHandle); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean startViewCalendarEventInManagedProfile(String paramString, long paramLong1, long paramLong2, long paramLong3, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      try {
        parcel1.writeString(paramString);
        try {
          boolean bool2;
          parcel1.writeLong(paramLong1);
          parcel1.writeLong(paramLong2);
          parcel1.writeLong(paramLong3);
          boolean bool1 = true;
          if (paramBoolean) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          parcel1.writeInt(paramInt);
          if (!this.mRemote.transact(301, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
            paramBoolean = IDevicePolicyManager.Stub.getDefaultImpl().startViewCalendarEventInManagedProfile(paramString, paramLong1, paramLong2, paramLong3, paramBoolean, paramInt);
            parcel2.recycle();
            parcel1.recycle();
            return paramBoolean;
          } 
          parcel2.readException();
          paramInt = parcel2.readInt();
          if (paramInt != 0) {
            paramBoolean = bool1;
          } else {
            paramBoolean = false;
          } 
          parcel2.recycle();
          parcel1.recycle();
          return paramBoolean;
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString;
  }
  
  public int stopUser(ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(144, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null)
        return IDevicePolicyManager.Stub.getDefaultImpl().stopUser(paramComponentName, paramUserHandle); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean switchUser(ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(142, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().switchUser(paramComponentName, paramUserHandle);
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
  
  public void transferOwnership(ComponentName paramComponentName1, ComponentName paramComponentName2, PersistableBundle paramPersistableBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName1 != null) {
        parcel1.writeInt(1);
        paramComponentName1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramComponentName2 != null) {
        parcel1.writeInt(1);
        paramComponentName2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramPersistableBundle != null) {
        parcel1.writeInt(1);
        paramPersistableBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(271, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().transferOwnership(paramComponentName1, paramComponentName2, paramPersistableBundle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void uninstallCaCerts(ComponentName paramComponentName, String paramString, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(92, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().uninstallCaCerts(paramComponentName, paramString, paramArrayOfString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void uninstallPackageWithActiveAdmins(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(245, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().uninstallPackageWithActiveAdmins(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean updateOverrideApn(ComponentName paramComponentName, int paramInt, ApnSetting paramApnSetting) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (paramApnSetting != null) {
        parcel1.writeInt(1);
        paramApnSetting.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(280, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        bool = IDevicePolicyManager.Stub.getDefaultImpl().updateOverrideApn(paramComponentName, paramInt, paramApnSetting);
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
  
  public void wipeDataWithReason(int paramInt, String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IDevicePolicyManager.Stub.getDefaultImpl() != null) {
        IDevicePolicyManager.Stub.getDefaultImpl().wipeDataWithReason(paramInt, paramString, paramBoolean);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IDevicePolicyManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */