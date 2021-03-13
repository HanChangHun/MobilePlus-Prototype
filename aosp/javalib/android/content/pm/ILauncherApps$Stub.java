package android.content.pm;

import android.app.IApplicationThread;
import android.content.ComponentName;
import android.content.IntentSender;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.ArrayList;
import java.util.List;

public abstract class Stub extends Binder implements ILauncherApps {
  private static final String DESCRIPTOR = "android.content.pm.ILauncherApps";
  
  static final int TRANSACTION_addOnAppsChangedListener = 1;
  
  static final int TRANSACTION_cacheShortcuts = 26;
  
  static final int TRANSACTION_getAllSessions = 23;
  
  static final int TRANSACTION_getAppUsageLimit = 12;
  
  static final int TRANSACTION_getApplicationInfo = 11;
  
  static final int TRANSACTION_getLauncherActivities = 3;
  
  static final int TRANSACTION_getShortcutConfigActivities = 20;
  
  static final int TRANSACTION_getShortcutConfigActivityIntent = 21;
  
  static final int TRANSACTION_getShortcutIconFd = 17;
  
  static final int TRANSACTION_getShortcutIconResId = 16;
  
  static final int TRANSACTION_getShortcutIconUri = 28;
  
  static final int TRANSACTION_getShortcuts = 13;
  
  static final int TRANSACTION_getSuspendedPackageLauncherExtras = 9;
  
  static final int TRANSACTION_hasShortcutHostPermission = 18;
  
  static final int TRANSACTION_isActivityEnabled = 10;
  
  static final int TRANSACTION_isPackageEnabled = 8;
  
  static final int TRANSACTION_pinShortcuts = 14;
  
  static final int TRANSACTION_registerPackageInstallerCallback = 22;
  
  static final int TRANSACTION_registerShortcutChangeCallback = 24;
  
  static final int TRANSACTION_removeOnAppsChangedListener = 2;
  
  static final int TRANSACTION_resolveActivity = 4;
  
  static final int TRANSACTION_shouldHideFromSuggestions = 19;
  
  static final int TRANSACTION_showAppDetailsAsUser = 7;
  
  static final int TRANSACTION_startActivityAsUser = 6;
  
  static final int TRANSACTION_startSessionDetailsActivityAsUser = 5;
  
  static final int TRANSACTION_startShortcut = 15;
  
  static final int TRANSACTION_uncacheShortcuts = 27;
  
  static final int TRANSACTION_unregisterShortcutChangeCallback = 25;
  
  public Stub() {
    attachInterface(this, "android.content.pm.ILauncherApps");
  }
  
  public static ILauncherApps asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.ILauncherApps");
    return (iInterface != null && iInterface instanceof ILauncherApps) ? (ILauncherApps)iInterface : new Proxy(paramIBinder);
  }
  
  public static ILauncherApps getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 28:
        return "getShortcutIconUri";
      case 27:
        return "uncacheShortcuts";
      case 26:
        return "cacheShortcuts";
      case 25:
        return "unregisterShortcutChangeCallback";
      case 24:
        return "registerShortcutChangeCallback";
      case 23:
        return "getAllSessions";
      case 22:
        return "registerPackageInstallerCallback";
      case 21:
        return "getShortcutConfigActivityIntent";
      case 20:
        return "getShortcutConfigActivities";
      case 19:
        return "shouldHideFromSuggestions";
      case 18:
        return "hasShortcutHostPermission";
      case 17:
        return "getShortcutIconFd";
      case 16:
        return "getShortcutIconResId";
      case 15:
        return "startShortcut";
      case 14:
        return "pinShortcuts";
      case 13:
        return "getShortcuts";
      case 12:
        return "getAppUsageLimit";
      case 11:
        return "getApplicationInfo";
      case 10:
        return "isActivityEnabled";
      case 9:
        return "getSuspendedPackageLauncherExtras";
      case 8:
        return "isPackageEnabled";
      case 7:
        return "showAppDetailsAsUser";
      case 6:
        return "startActivityAsUser";
      case 5:
        return "startSessionDetailsActivityAsUser";
      case 4:
        return "resolveActivity";
      case 3:
        return "getLauncherActivities";
      case 2:
        return "removeOnAppsChangedListener";
      case 1:
        break;
    } 
    return "addOnAppsChangedListener";
  }
  
  public static boolean setDefaultImpl(ILauncherApps paramILauncherApps) {
    if (Proxy.sDefaultImpl == null) {
      if (paramILauncherApps != null) {
        Proxy.sDefaultImpl = paramILauncherApps;
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
      String str1;
      ParceledListSlice parceledListSlice4;
      IntentSender intentSender;
      ParceledListSlice parceledListSlice3;
      ParcelFileDescriptor parcelFileDescriptor;
      ParceledListSlice parceledListSlice2;
      LauncherApps.AppUsageLimit appUsageLimit;
      ApplicationInfo applicationInfo;
      Bundle bundle;
      ActivityInfo activityInfo;
      ParceledListSlice parceledListSlice1;
      String str2;
      String str4;
      IApplicationThread iApplicationThread1;
      String str3;
      ArrayList<String> arrayList1;
      String str5;
      UserHandle userHandle;
      String str7;
      ArrayList<String> arrayList2;
      String str6;
      String str9;
      IApplicationThread iApplicationThread3;
      String str8;
      IApplicationThread iApplicationThread2;
      String str10;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 28:
          paramParcel1.enforceInterface("android.content.pm.ILauncherApps");
          str1 = getShortcutIconUri(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 27:
          str1.enforceInterface("android.content.pm.ILauncherApps");
          str2 = str1.readString();
          str4 = str1.readString();
          arrayList1 = str1.createStringArrayList();
          if (str1.readInt() != 0) {
            userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)str1);
          } else {
            userHandle = null;
          } 
          uncacheShortcuts(str2, str4, arrayList1, userHandle, str1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 26:
          str1.enforceInterface("android.content.pm.ILauncherApps");
          str2 = str1.readString();
          str4 = str1.readString();
          arrayList1 = str1.createStringArrayList();
          if (str1.readInt() != 0) {
            userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)str1);
          } else {
            userHandle = null;
          } 
          cacheShortcuts(str2, str4, arrayList1, userHandle, str1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 25:
          str1.enforceInterface("android.content.pm.ILauncherApps");
          unregisterShortcutChangeCallback(str1.readString(), IShortcutChangeCallback.Stub.asInterface(str1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 24:
          str1.enforceInterface("android.content.pm.ILauncherApps");
          str2 = str1.readString();
          if (str1.readInt() != 0) {
            ShortcutQueryWrapper shortcutQueryWrapper = (ShortcutQueryWrapper)ShortcutQueryWrapper.CREATOR.createFromParcel((Parcel)str1);
          } else {
            userHandle = null;
          } 
          registerShortcutChangeCallback(str2, (ShortcutQueryWrapper)userHandle, IShortcutChangeCallback.Stub.asInterface(str1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 23:
          str1.enforceInterface("android.content.pm.ILauncherApps");
          parceledListSlice4 = getAllSessions(str1.readString());
          paramParcel2.writeNoException();
          if (parceledListSlice4 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice4.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 22:
          parceledListSlice4.enforceInterface("android.content.pm.ILauncherApps");
          registerPackageInstallerCallback(parceledListSlice4.readString(), IPackageInstallerCallback.Stub.asInterface(parceledListSlice4.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 21:
          parceledListSlice4.enforceInterface("android.content.pm.ILauncherApps");
          str2 = parceledListSlice4.readString();
          if (parceledListSlice4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parceledListSlice4);
          } else {
            userHandle = null;
          } 
          if (parceledListSlice4.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)parceledListSlice4);
          } else {
            parceledListSlice4 = null;
          } 
          intentSender = getShortcutConfigActivityIntent(str2, (ComponentName)userHandle, (UserHandle)parceledListSlice4);
          paramParcel2.writeNoException();
          if (intentSender != null) {
            paramParcel2.writeInt(1);
            intentSender.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 20:
          intentSender.enforceInterface("android.content.pm.ILauncherApps");
          str7 = intentSender.readString();
          str2 = intentSender.readString();
          if (intentSender.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)intentSender);
          } else {
            intentSender = null;
          } 
          parceledListSlice3 = getShortcutConfigActivities(str7, str2, (UserHandle)intentSender);
          paramParcel2.writeNoException();
          if (parceledListSlice3 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice3.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 19:
          parceledListSlice3.enforceInterface("android.content.pm.ILauncherApps");
          str7 = parceledListSlice3.readString();
          if (parceledListSlice3.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)parceledListSlice3);
          } else {
            parceledListSlice3 = null;
          } 
          bool3 = shouldHideFromSuggestions(str7, (UserHandle)parceledListSlice3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 18:
          parceledListSlice3.enforceInterface("android.content.pm.ILauncherApps");
          bool3 = hasShortcutHostPermission(parceledListSlice3.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 17:
          parceledListSlice3.enforceInterface("android.content.pm.ILauncherApps");
          parcelFileDescriptor = getShortcutIconFd(parceledListSlice3.readString(), parceledListSlice3.readString(), parceledListSlice3.readString(), parceledListSlice3.readInt());
          paramParcel2.writeNoException();
          if (parcelFileDescriptor != null) {
            paramParcel2.writeInt(1);
            parcelFileDescriptor.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 16:
          parcelFileDescriptor.enforceInterface("android.content.pm.ILauncherApps");
          j = getShortcutIconResId(parcelFileDescriptor.readString(), parcelFileDescriptor.readString(), parcelFileDescriptor.readString(), parcelFileDescriptor.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 15:
          parcelFileDescriptor.enforceInterface("android.content.pm.ILauncherApps");
          str5 = parcelFileDescriptor.readString();
          str9 = parcelFileDescriptor.readString();
          str4 = parcelFileDescriptor.readString();
          str10 = parcelFileDescriptor.readString();
          if (parcelFileDescriptor.readInt() != 0) {
            Rect rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
          } else {
            str7 = null;
          } 
          if (parcelFileDescriptor.readInt() != 0) {
            Bundle bundle1 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
          } else {
            str2 = null;
          } 
          bool2 = startShortcut(str5, str9, str4, str10, (Rect)str7, (Bundle)str2, parcelFileDescriptor.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 14:
          parcelFileDescriptor.enforceInterface("android.content.pm.ILauncherApps");
          str2 = parcelFileDescriptor.readString();
          str5 = parcelFileDescriptor.readString();
          arrayList2 = parcelFileDescriptor.createStringArrayList();
          if (parcelFileDescriptor.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
          } else {
            parcelFileDescriptor = null;
          } 
          pinShortcuts(str2, str5, arrayList2, (UserHandle)parcelFileDescriptor);
          paramParcel2.writeNoException();
          return true;
        case 13:
          parcelFileDescriptor.enforceInterface("android.content.pm.ILauncherApps");
          str2 = parcelFileDescriptor.readString();
          if (parcelFileDescriptor.readInt() != 0) {
            ShortcutQueryWrapper shortcutQueryWrapper = (ShortcutQueryWrapper)ShortcutQueryWrapper.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
          } else {
            arrayList2 = null;
          } 
          if (parcelFileDescriptor.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
          } else {
            parcelFileDescriptor = null;
          } 
          parceledListSlice2 = getShortcuts(str2, (ShortcutQueryWrapper)arrayList2, (UserHandle)parcelFileDescriptor);
          paramParcel2.writeNoException();
          if (parceledListSlice2 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 12:
          parceledListSlice2.enforceInterface("android.content.pm.ILauncherApps");
          str2 = parceledListSlice2.readString();
          str6 = parceledListSlice2.readString();
          if (parceledListSlice2.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)parceledListSlice2);
          } else {
            parceledListSlice2 = null;
          } 
          appUsageLimit = getAppUsageLimit(str2, str6, (UserHandle)parceledListSlice2);
          paramParcel2.writeNoException();
          if (appUsageLimit != null) {
            paramParcel2.writeInt(1);
            appUsageLimit.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 11:
          appUsageLimit.enforceInterface("android.content.pm.ILauncherApps");
          str6 = appUsageLimit.readString();
          str2 = appUsageLimit.readString();
          i = appUsageLimit.readInt();
          if (appUsageLimit.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)appUsageLimit);
          } else {
            appUsageLimit = null;
          } 
          applicationInfo = getApplicationInfo(str6, str2, i, (UserHandle)appUsageLimit);
          paramParcel2.writeNoException();
          if (applicationInfo != null) {
            paramParcel2.writeInt(1);
            applicationInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 10:
          applicationInfo.enforceInterface("android.content.pm.ILauncherApps");
          str2 = applicationInfo.readString();
          if (applicationInfo.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)applicationInfo);
          } else {
            str6 = null;
          } 
          if (applicationInfo.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)applicationInfo);
          } else {
            applicationInfo = null;
          } 
          bool1 = isActivityEnabled(str2, (ComponentName)str6, (UserHandle)applicationInfo);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 9:
          applicationInfo.enforceInterface("android.content.pm.ILauncherApps");
          str6 = applicationInfo.readString();
          if (applicationInfo.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)applicationInfo);
          } else {
            applicationInfo = null;
          } 
          bundle = getSuspendedPackageLauncherExtras(str6, (UserHandle)applicationInfo);
          paramParcel2.writeNoException();
          if (bundle != null) {
            paramParcel2.writeInt(1);
            bundle.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 8:
          bundle.enforceInterface("android.content.pm.ILauncherApps");
          str2 = bundle.readString();
          str6 = bundle.readString();
          if (bundle.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            bundle = null;
          } 
          bool1 = isPackageEnabled(str2, str6, (UserHandle)bundle);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 7:
          bundle.enforceInterface("android.content.pm.ILauncherApps");
          iApplicationThread3 = IApplicationThread.Stub.asInterface(bundle.readStrongBinder());
          str4 = bundle.readString();
          str10 = bundle.readString();
          if (bundle.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str6 = null;
          } 
          if (bundle.readInt() != 0) {
            Rect rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str2 = null;
          } 
          if (bundle.readInt() != 0) {
            Bundle bundle1 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str5 = null;
          } 
          if (bundle.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            bundle = null;
          } 
          showAppDetailsAsUser(iApplicationThread3, str4, str10, (ComponentName)str6, (Rect)str2, (Bundle)str5, (UserHandle)bundle);
          paramParcel2.writeNoException();
          return true;
        case 6:
          bundle.enforceInterface("android.content.pm.ILauncherApps");
          iApplicationThread1 = IApplicationThread.Stub.asInterface(bundle.readStrongBinder());
          str8 = bundle.readString();
          str10 = bundle.readString();
          if (bundle.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str6 = null;
          } 
          if (bundle.readInt() != 0) {
            Rect rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str2 = null;
          } 
          if (bundle.readInt() != 0) {
            Bundle bundle1 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str5 = null;
          } 
          if (bundle.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            bundle = null;
          } 
          startActivityAsUser(iApplicationThread1, str8, str10, (ComponentName)str6, (Rect)str2, (Bundle)str5, (UserHandle)bundle);
          paramParcel2.writeNoException();
          return true;
        case 5:
          bundle.enforceInterface("android.content.pm.ILauncherApps");
          iApplicationThread2 = IApplicationThread.Stub.asInterface(bundle.readStrongBinder());
          str3 = bundle.readString();
          str10 = bundle.readString();
          if (bundle.readInt() != 0) {
            PackageInstaller.SessionInfo sessionInfo = (PackageInstaller.SessionInfo)PackageInstaller.SessionInfo.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str6 = null;
          } 
          if (bundle.readInt() != 0) {
            Rect rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str2 = null;
          } 
          if (bundle.readInt() != 0) {
            Bundle bundle1 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str5 = null;
          } 
          if (bundle.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            bundle = null;
          } 
          startSessionDetailsActivityAsUser(iApplicationThread2, str3, str10, (PackageInstaller.SessionInfo)str6, (Rect)str2, (Bundle)str5, (UserHandle)bundle);
          paramParcel2.writeNoException();
          return true;
        case 4:
          bundle.enforceInterface("android.content.pm.ILauncherApps");
          str2 = bundle.readString();
          if (bundle.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            str6 = null;
          } 
          if (bundle.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)bundle);
          } else {
            bundle = null;
          } 
          activityInfo = resolveActivity(str2, (ComponentName)str6, (UserHandle)bundle);
          paramParcel2.writeNoException();
          if (activityInfo != null) {
            paramParcel2.writeInt(1);
            activityInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 3:
          activityInfo.enforceInterface("android.content.pm.ILauncherApps");
          str6 = activityInfo.readString();
          str2 = activityInfo.readString();
          if (activityInfo.readInt() != 0) {
            UserHandle userHandle1 = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)activityInfo);
          } else {
            activityInfo = null;
          } 
          parceledListSlice1 = getLauncherActivities(str6, str2, (UserHandle)activityInfo);
          paramParcel2.writeNoException();
          if (parceledListSlice1 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 2:
          parceledListSlice1.enforceInterface("android.content.pm.ILauncherApps");
          removeOnAppsChangedListener(IOnAppsChangedListener.Stub.asInterface(parceledListSlice1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      parceledListSlice1.enforceInterface("android.content.pm.ILauncherApps");
      addOnAppsChangedListener(parceledListSlice1.readString(), IOnAppsChangedListener.Stub.asInterface(parceledListSlice1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.content.pm.ILauncherApps");
    return true;
  }
  
  private static class Proxy implements ILauncherApps {
    public static ILauncherApps sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void addOnAppsChangedListener(String param2String, IOnAppsChangedListener param2IOnAppsChangedListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        if (param2IOnAppsChangedListener != null) {
          iBinder = param2IOnAppsChangedListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          ILauncherApps.Stub.getDefaultImpl().addOnAppsChangedListener(param2String, param2IOnAppsChangedListener);
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
    
    public void cacheShortcuts(String param2String1, String param2String2, List<String> param2List, UserHandle param2UserHandle, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeStringList(param2List);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          ILauncherApps.Stub.getDefaultImpl().cacheShortcuts(param2String1, param2String2, param2List, param2UserHandle, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getAllSessions(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
          return ILauncherApps.Stub.getDefaultImpl().getAllSessions(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public LauncherApps.AppUsageLimit getAppUsageLimit(String param2String1, String param2String2, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
          return ILauncherApps.Stub.getDefaultImpl().getAppUsageLimit(param2String1, param2String2, param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          LauncherApps.AppUsageLimit appUsageLimit = (LauncherApps.AppUsageLimit)LauncherApps.AppUsageLimit.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (LauncherApps.AppUsageLimit)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ApplicationInfo getApplicationInfo(String param2String1, String param2String2, int param2Int, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
          return ILauncherApps.Stub.getDefaultImpl().getApplicationInfo(param2String1, param2String2, param2Int, param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (ApplicationInfo)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.ILauncherApps";
    }
    
    public ParceledListSlice getLauncherActivities(String param2String1, String param2String2, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
          return ILauncherApps.Stub.getDefaultImpl().getLauncherActivities(param2String1, param2String2, param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (ParceledListSlice)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getShortcutConfigActivities(String param2String1, String param2String2, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
          return ILauncherApps.Stub.getDefaultImpl().getShortcutConfigActivities(param2String1, param2String2, param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (ParceledListSlice)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IntentSender getShortcutConfigActivityIntent(String param2String, ComponentName param2ComponentName, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
          return ILauncherApps.Stub.getDefaultImpl().getShortcutConfigActivityIntent(param2String, param2ComponentName, param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          IntentSender intentSender = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (IntentSender)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor getShortcutIconFd(String param2String1, String param2String2, String param2String3, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeString(param2String3);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
          return ILauncherApps.Stub.getDefaultImpl().getShortcutIconFd(param2String1, param2String2, param2String3, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (ParcelFileDescriptor)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getShortcutIconResId(String param2String1, String param2String2, String param2String3, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeString(param2String3);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          param2Int = ILauncherApps.Stub.getDefaultImpl().getShortcutIconResId(param2String1, param2String2, param2String3, param2Int);
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
    
    public String getShortcutIconUri(String param2String1, String param2String2, String param2String3, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeString(param2String3);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          param2String1 = ILauncherApps.Stub.getDefaultImpl().getShortcutIconUri(param2String1, param2String2, param2String3, param2Int);
          return param2String1;
        } 
        parcel2.readException();
        param2String1 = parcel2.readString();
        return param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getShortcuts(String param2String, ShortcutQueryWrapper param2ShortcutQueryWrapper, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        if (param2ShortcutQueryWrapper != null) {
          parcel1.writeInt(1);
          param2ShortcutQueryWrapper.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
          return ILauncherApps.Stub.getDefaultImpl().getShortcuts(param2String, param2ShortcutQueryWrapper, param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bundle getSuspendedPackageLauncherExtras(String param2String, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
          return ILauncherApps.Stub.getDefaultImpl().getSuspendedPackageLauncherExtras(param2String, param2UserHandle); 
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
    
    public boolean hasShortcutHostPermission(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(18, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          bool = ILauncherApps.Stub.getDefaultImpl().hasShortcutHostPermission(param2String);
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
    
    public boolean isActivityEnabled(String param2String, ComponentName param2ComponentName, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          bool = ILauncherApps.Stub.getDefaultImpl().isActivityEnabled(param2String, param2ComponentName, param2UserHandle);
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
    
    public boolean isPackageEnabled(String param2String1, String param2String2, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        boolean bool = true;
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          bool = ILauncherApps.Stub.getDefaultImpl().isPackageEnabled(param2String1, param2String2, param2UserHandle);
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
    
    public void pinShortcuts(String param2String1, String param2String2, List<String> param2List, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeStringList(param2List);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          ILauncherApps.Stub.getDefaultImpl().pinShortcuts(param2String1, param2String2, param2List, param2UserHandle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerPackageInstallerCallback(String param2String, IPackageInstallerCallback param2IPackageInstallerCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        if (param2IPackageInstallerCallback != null) {
          iBinder = param2IPackageInstallerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          ILauncherApps.Stub.getDefaultImpl().registerPackageInstallerCallback(param2String, param2IPackageInstallerCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerShortcutChangeCallback(String param2String, ShortcutQueryWrapper param2ShortcutQueryWrapper, IShortcutChangeCallback param2IShortcutChangeCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        if (param2ShortcutQueryWrapper != null) {
          parcel1.writeInt(1);
          param2ShortcutQueryWrapper.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IShortcutChangeCallback != null) {
          iBinder = param2IShortcutChangeCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          ILauncherApps.Stub.getDefaultImpl().registerShortcutChangeCallback(param2String, param2ShortcutQueryWrapper, param2IShortcutChangeCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeOnAppsChangedListener(IOnAppsChangedListener param2IOnAppsChangedListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        if (param2IOnAppsChangedListener != null) {
          iBinder = param2IOnAppsChangedListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          ILauncherApps.Stub.getDefaultImpl().removeOnAppsChangedListener(param2IOnAppsChangedListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ActivityInfo resolveActivity(String param2String, ComponentName param2ComponentName, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null)
          return ILauncherApps.Stub.getDefaultImpl().resolveActivity(param2String, param2ComponentName, param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ActivityInfo activityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ActivityInfo)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean shouldHideFromSuggestions(String param2String, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          bool = ILauncherApps.Stub.getDefaultImpl().shouldHideFromSuggestions(param2String, param2UserHandle);
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
    
    public void showAppDetailsAsUser(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, ComponentName param2ComponentName, Rect param2Rect, Bundle param2Bundle, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        if (param2IApplicationThread != null) {
          iBinder = param2IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        try {
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Rect != null) {
            parcel1.writeInt(1);
            param2Rect.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2UserHandle != null) {
            parcel1.writeInt(1);
            param2UserHandle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
            ILauncherApps.Stub.getDefaultImpl().showAppDetailsAsUser(param2IApplicationThread, param2String1, param2String2, param2ComponentName, param2Rect, param2Bundle, param2UserHandle);
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
      throw param2IApplicationThread;
    }
    
    public void startActivityAsUser(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, ComponentName param2ComponentName, Rect param2Rect, Bundle param2Bundle, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        if (param2IApplicationThread != null) {
          iBinder = param2IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        try {
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Rect != null) {
            parcel1.writeInt(1);
            param2Rect.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2UserHandle != null) {
            parcel1.writeInt(1);
            param2UserHandle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
            ILauncherApps.Stub.getDefaultImpl().startActivityAsUser(param2IApplicationThread, param2String1, param2String2, param2ComponentName, param2Rect, param2Bundle, param2UserHandle);
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
      throw param2IApplicationThread;
    }
    
    public void startSessionDetailsActivityAsUser(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, PackageInstaller.SessionInfo param2SessionInfo, Rect param2Rect, Bundle param2Bundle, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        if (param2IApplicationThread != null) {
          iBinder = param2IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        try {
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (param2SessionInfo != null) {
            parcel1.writeInt(1);
            param2SessionInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Rect != null) {
            parcel1.writeInt(1);
            param2Rect.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2UserHandle != null) {
            parcel1.writeInt(1);
            param2UserHandle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
            ILauncherApps.Stub.getDefaultImpl().startSessionDetailsActivityAsUser(param2IApplicationThread, param2String1, param2String2, param2SessionInfo, param2Rect, param2Bundle, param2UserHandle);
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
      throw param2IApplicationThread;
    }
    
    public boolean startShortcut(String param2String1, String param2String2, String param2String3, String param2String4, Rect param2Rect, Bundle param2Bundle, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeString(param2String2);
            try {
              parcel1.writeString(param2String3);
              parcel1.writeString(param2String4);
              boolean bool = true;
              if (param2Rect != null) {
                parcel1.writeInt(1);
                param2Rect.writeToParcel(parcel1, 0);
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
              if (!this.mRemote.transact(15, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
                bool = ILauncherApps.Stub.getDefaultImpl().startShortcut(param2String1, param2String2, param2String3, param2String4, param2Rect, param2Bundle, param2Int);
                parcel2.recycle();
                parcel1.recycle();
                return bool;
              } 
              parcel2.readException();
              param2Int = parcel2.readInt();
              if (param2Int == 0)
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
      throw param2String1;
    }
    
    public void uncacheShortcuts(String param2String1, String param2String2, List<String> param2List, UserHandle param2UserHandle, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeStringList(param2List);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          ILauncherApps.Stub.getDefaultImpl().uncacheShortcuts(param2String1, param2String2, param2List, param2UserHandle, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterShortcutChangeCallback(String param2String, IShortcutChangeCallback param2IShortcutChangeCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.ILauncherApps");
        parcel1.writeString(param2String);
        if (param2IShortcutChangeCallback != null) {
          iBinder = param2IShortcutChangeCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && ILauncherApps.Stub.getDefaultImpl() != null) {
          ILauncherApps.Stub.getDefaultImpl().unregisterShortcutChangeCallback(param2String, param2IShortcutChangeCallback);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ILauncherApps$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */