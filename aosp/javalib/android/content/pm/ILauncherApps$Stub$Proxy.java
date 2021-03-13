package android.content.pm;

import android.app.IApplicationThread;
import android.content.ComponentName;
import android.content.IntentSender;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;

class Proxy implements ILauncherApps {
  public static ILauncherApps sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addOnAppsChangedListener(String paramString, IOnAppsChangedListener paramIOnAppsChangedListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
      if (paramIOnAppsChangedListener != null) {
        iBinder = paramIOnAppsChangedListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        ILauncherApps.Stub.getDefaultImpl().addOnAppsChangedListener(paramString, paramIOnAppsChangedListener);
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
  
  public void cacheShortcuts(String paramString1, String paramString2, List<String> paramList, UserHandle paramUserHandle, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeStringList(paramList);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        ILauncherApps.Stub.getDefaultImpl().cacheShortcuts(paramString1, paramString2, paramList, paramUserHandle, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getAllSessions(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
        return ILauncherApps.Stub.getDefaultImpl().getAllSessions(paramString); 
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
  
  public LauncherApps.AppUsageLimit getAppUsageLimit(String paramString1, String paramString2, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
        return ILauncherApps.Stub.getDefaultImpl().getAppUsageLimit(paramString1, paramString2, paramUserHandle); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        LauncherApps.AppUsageLimit appUsageLimit = (LauncherApps.AppUsageLimit)LauncherApps.AppUsageLimit.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (LauncherApps.AppUsageLimit)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ApplicationInfo getApplicationInfo(String paramString1, String paramString2, int paramInt, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
        return ILauncherApps.Stub.getDefaultImpl().getApplicationInfo(paramString1, paramString2, paramInt, paramUserHandle); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (ApplicationInfo)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.ILauncherApps";
  }
  
  public ParceledListSlice getLauncherActivities(String paramString1, String paramString2, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
        return ILauncherApps.Stub.getDefaultImpl().getLauncherActivities(paramString1, paramString2, paramUserHandle); 
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
  
  public ParceledListSlice getShortcutConfigActivities(String paramString1, String paramString2, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
        return ILauncherApps.Stub.getDefaultImpl().getShortcutConfigActivities(paramString1, paramString2, paramUserHandle); 
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
  
  public IntentSender getShortcutConfigActivityIntent(String paramString, ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
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
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
        return ILauncherApps.Stub.getDefaultImpl().getShortcutConfigActivityIntent(paramString, paramComponentName, paramUserHandle); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        IntentSender intentSender = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (IntentSender)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParcelFileDescriptor getShortcutIconFd(String paramString1, String paramString2, String paramString3, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
        return ILauncherApps.Stub.getDefaultImpl().getShortcutIconFd(paramString1, paramString2, paramString3, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (ParcelFileDescriptor)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getShortcutIconResId(String paramString1, String paramString2, String paramString3, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        paramInt = ILauncherApps.Stub.getDefaultImpl().getShortcutIconResId(paramString1, paramString2, paramString3, paramInt);
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
  
  public String getShortcutIconUri(String paramString1, String paramString2, String paramString3, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        paramString1 = ILauncherApps.Stub.getDefaultImpl().getShortcutIconUri(paramString1, paramString2, paramString3, paramInt);
        return paramString1;
      } 
      parcel2.readException();
      paramString1 = parcel2.readString();
      return paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getShortcuts(String paramString, ShortcutQueryWrapper paramShortcutQueryWrapper, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
      if (paramShortcutQueryWrapper != null) {
        parcel1.writeInt(1);
        paramShortcutQueryWrapper.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
        return ILauncherApps.Stub.getDefaultImpl().getShortcuts(paramString, paramShortcutQueryWrapper, paramUserHandle); 
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
  
  public Bundle getSuspendedPackageLauncherExtras(String paramString, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
        return ILauncherApps.Stub.getDefaultImpl().getSuspendedPackageLauncherExtras(paramString, paramUserHandle); 
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
  
  public boolean hasShortcutHostPermission(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(18, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        bool = ILauncherApps.Stub.getDefaultImpl().hasShortcutHostPermission(paramString);
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
  
  public boolean isActivityEnabled(String paramString, ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
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
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        bool = ILauncherApps.Stub.getDefaultImpl().isActivityEnabled(paramString, paramComponentName, paramUserHandle);
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
  
  public boolean isPackageEnabled(String paramString1, String paramString2, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      boolean bool = true;
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        bool = ILauncherApps.Stub.getDefaultImpl().isPackageEnabled(paramString1, paramString2, paramUserHandle);
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
  
  public void pinShortcuts(String paramString1, String paramString2, List<String> paramList, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeStringList(paramList);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        ILauncherApps.Stub.getDefaultImpl().pinShortcuts(paramString1, paramString2, paramList, paramUserHandle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerPackageInstallerCallback(String paramString, IPackageInstallerCallback paramIPackageInstallerCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
      if (paramIPackageInstallerCallback != null) {
        iBinder = paramIPackageInstallerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        ILauncherApps.Stub.getDefaultImpl().registerPackageInstallerCallback(paramString, paramIPackageInstallerCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerShortcutChangeCallback(String paramString, ShortcutQueryWrapper paramShortcutQueryWrapper, IShortcutChangeCallback paramIShortcutChangeCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
      if (paramShortcutQueryWrapper != null) {
        parcel1.writeInt(1);
        paramShortcutQueryWrapper.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIShortcutChangeCallback != null) {
        iBinder = paramIShortcutChangeCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        ILauncherApps.Stub.getDefaultImpl().registerShortcutChangeCallback(paramString, paramShortcutQueryWrapper, paramIShortcutChangeCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeOnAppsChangedListener(IOnAppsChangedListener paramIOnAppsChangedListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      if (paramIOnAppsChangedListener != null) {
        iBinder = paramIOnAppsChangedListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        ILauncherApps.Stub.getDefaultImpl().removeOnAppsChangedListener(paramIOnAppsChangedListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ActivityInfo resolveActivity(String paramString, ComponentName paramComponentName, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
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
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
        return ILauncherApps.Stub.getDefaultImpl().resolveActivity(paramString, paramComponentName, paramUserHandle); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ActivityInfo activityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ActivityInfo)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean shouldHideFromSuggestions(String paramString, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
      boolean bool = true;
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        bool = ILauncherApps.Stub.getDefaultImpl().shouldHideFromSuggestions(paramString, paramUserHandle);
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
  
  public void showAppDetailsAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, ComponentName paramComponentName, Rect paramRect, Bundle paramBundle, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      try {
        parcel1.writeString(paramString1);
        parcel1.writeString(paramString2);
        if (paramComponentName != null) {
          parcel1.writeInt(1);
          paramComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramRect != null) {
          parcel1.writeInt(1);
          paramRect.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramBundle != null) {
          parcel1.writeInt(1);
          paramBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramUserHandle != null) {
          parcel1.writeInt(1);
          paramUserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          ILauncherApps.Stub.getDefaultImpl().showAppDetailsAsUser(paramIApplicationThread, paramString1, paramString2, paramComponentName, paramRect, paramBundle, paramUserHandle);
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
    throw paramIApplicationThread;
  }
  
  public void startActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, ComponentName paramComponentName, Rect paramRect, Bundle paramBundle, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      try {
        parcel1.writeString(paramString1);
        parcel1.writeString(paramString2);
        if (paramComponentName != null) {
          parcel1.writeInt(1);
          paramComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramRect != null) {
          parcel1.writeInt(1);
          paramRect.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramBundle != null) {
          parcel1.writeInt(1);
          paramBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramUserHandle != null) {
          parcel1.writeInt(1);
          paramUserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          ILauncherApps.Stub.getDefaultImpl().startActivityAsUser(paramIApplicationThread, paramString1, paramString2, paramComponentName, paramRect, paramBundle, paramUserHandle);
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
    throw paramIApplicationThread;
  }
  
  public void startSessionDetailsActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, PackageInstaller.SessionInfo paramSessionInfo, Rect paramRect, Bundle paramBundle, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      try {
        parcel1.writeString(paramString1);
        parcel1.writeString(paramString2);
        if (paramSessionInfo != null) {
          parcel1.writeInt(1);
          paramSessionInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramRect != null) {
          parcel1.writeInt(1);
          paramRect.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramBundle != null) {
          parcel1.writeInt(1);
          paramBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramUserHandle != null) {
          parcel1.writeInt(1);
          paramUserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          ILauncherApps.Stub.getDefaultImpl().startSessionDetailsActivityAsUser(paramIApplicationThread, paramString1, paramString2, paramSessionInfo, paramRect, paramBundle, paramUserHandle);
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
    throw paramIApplicationThread;
  }
  
  public boolean startShortcut(String paramString1, String paramString2, String paramString3, String paramString4, Rect paramRect, Bundle paramBundle, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          try {
            parcel1.writeString(paramString3);
            parcel1.writeString(paramString4);
            boolean bool = true;
            if (paramRect != null) {
              parcel1.writeInt(1);
              paramRect.writeToParcel(parcel1, 0);
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
            if (!this.mRemote.transact(15, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
              bool = ILauncherApps.Stub.getDefaultImpl().startShortcut(paramString1, paramString2, paramString3, paramString4, paramRect, paramBundle, paramInt);
              parcel2.recycle();
              parcel1.recycle();
              return bool;
            } 
            parcel2.readException();
            paramInt = parcel2.readInt();
            if (paramInt == 0)
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
    throw paramString1;
  }
  
  public void uncacheShortcuts(String paramString1, String paramString2, List<String> paramList, UserHandle paramUserHandle, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeStringList(paramList);
      if (paramUserHandle != null) {
        parcel1.writeInt(1);
        paramUserHandle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        ILauncherApps.Stub.getDefaultImpl().uncacheShortcuts(paramString1, paramString2, paramList, paramUserHandle, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterShortcutChangeCallback(String paramString, IShortcutChangeCallback paramIShortcutChangeCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
      parcel1.writeString(paramString);
      if (paramIShortcutChangeCallback != null) {
        iBinder = paramIShortcutChangeCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(25, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
        ILauncherApps.Stub.getDefaultImpl().unregisterShortcutChangeCallback(paramString, paramIShortcutChangeCallback);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ILauncherApps$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */