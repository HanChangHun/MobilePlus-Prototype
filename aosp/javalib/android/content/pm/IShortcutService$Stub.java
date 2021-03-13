package android.content.pm;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public abstract class Stub extends Binder implements IShortcutService {
  private static final String DESCRIPTOR = "android.content.pm.IShortcutService";
  
  static final int TRANSACTION_addDynamicShortcuts = 2;
  
  static final int TRANSACTION_applyRestore = 18;
  
  static final int TRANSACTION_createShortcutResultIntent = 7;
  
  static final int TRANSACTION_disableShortcuts = 8;
  
  static final int TRANSACTION_enableShortcuts = 9;
  
  static final int TRANSACTION_getBackupPayload = 17;
  
  static final int TRANSACTION_getIconMaxDimensions = 13;
  
  static final int TRANSACTION_getMaxShortcutCountPerActivity = 10;
  
  static final int TRANSACTION_getRateLimitResetTime = 12;
  
  static final int TRANSACTION_getRemainingCallCount = 11;
  
  static final int TRANSACTION_getShareTargets = 20;
  
  static final int TRANSACTION_getShortcuts = 23;
  
  static final int TRANSACTION_hasShareTargets = 21;
  
  static final int TRANSACTION_isRequestPinItemSupported = 19;
  
  static final int TRANSACTION_onApplicationActive = 16;
  
  static final int TRANSACTION_pushDynamicShortcut = 24;
  
  static final int TRANSACTION_removeAllDynamicShortcuts = 4;
  
  static final int TRANSACTION_removeDynamicShortcuts = 3;
  
  static final int TRANSACTION_removeLongLivedShortcuts = 22;
  
  static final int TRANSACTION_reportShortcutUsed = 14;
  
  static final int TRANSACTION_requestPinShortcut = 6;
  
  static final int TRANSACTION_resetThrottling = 15;
  
  static final int TRANSACTION_setDynamicShortcuts = 1;
  
  static final int TRANSACTION_updateShortcuts = 5;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IShortcutService");
  }
  
  public static IShortcutService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IShortcutService");
    return (iInterface != null && iInterface instanceof IShortcutService) ? (IShortcutService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IShortcutService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 24:
        return "pushDynamicShortcut";
      case 23:
        return "getShortcuts";
      case 22:
        return "removeLongLivedShortcuts";
      case 21:
        return "hasShareTargets";
      case 20:
        return "getShareTargets";
      case 19:
        return "isRequestPinItemSupported";
      case 18:
        return "applyRestore";
      case 17:
        return "getBackupPayload";
      case 16:
        return "onApplicationActive";
      case 15:
        return "resetThrottling";
      case 14:
        return "reportShortcutUsed";
      case 13:
        return "getIconMaxDimensions";
      case 12:
        return "getRateLimitResetTime";
      case 11:
        return "getRemainingCallCount";
      case 10:
        return "getMaxShortcutCountPerActivity";
      case 9:
        return "enableShortcuts";
      case 8:
        return "disableShortcuts";
      case 7:
        return "createShortcutResultIntent";
      case 6:
        return "requestPinShortcut";
      case 5:
        return "updateShortcuts";
      case 4:
        return "removeAllDynamicShortcuts";
      case 3:
        return "removeDynamicShortcuts";
      case 2:
        return "addDynamicShortcuts";
      case 1:
        break;
    } 
    return "setDynamicShortcuts";
  }
  
  public static boolean setDefaultImpl(IShortcutService paramIShortcutService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIShortcutService != null) {
        Proxy.sDefaultImpl = paramIShortcutService;
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
      ParceledListSlice parceledListSlice;
      byte[] arrayOfByte;
      Intent intent;
      String str2;
      ArrayList arrayList;
      ShortcutInfo shortcutInfo;
      long l;
      String str3;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 24:
          paramParcel1.enforceInterface("android.content.pm.IShortcutService");
          str2 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            shortcutInfo = (ShortcutInfo)ShortcutInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            shortcutInfo = null;
          } 
          pushDynamicShortcut(str2, shortcutInfo, paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 23:
          paramParcel1.enforceInterface("android.content.pm.IShortcutService");
          parceledListSlice = getShortcuts(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 22:
          parceledListSlice.enforceInterface("android.content.pm.IShortcutService");
          removeLongLivedShortcuts(parceledListSlice.readString(), parceledListSlice.readArrayList(getClass().getClassLoader()), parceledListSlice.readInt());
          paramParcel2.writeNoException();
          return true;
        case 21:
          parceledListSlice.enforceInterface("android.content.pm.IShortcutService");
          bool2 = hasShareTargets(parceledListSlice.readString(), parceledListSlice.readString(), parceledListSlice.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 20:
          parceledListSlice.enforceInterface("android.content.pm.IShortcutService");
          str2 = parceledListSlice.readString();
          if (parceledListSlice.readInt() != 0) {
            IntentFilter intentFilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel((Parcel)parceledListSlice);
          } else {
            shortcutInfo = null;
          } 
          parceledListSlice = getShareTargets(str2, (IntentFilter)shortcutInfo, parceledListSlice.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 19:
          parceledListSlice.enforceInterface("android.content.pm.IShortcutService");
          bool2 = isRequestPinItemSupported(parceledListSlice.readInt(), parceledListSlice.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 18:
          parceledListSlice.enforceInterface("android.content.pm.IShortcutService");
          applyRestore(parceledListSlice.createByteArray(), parceledListSlice.readInt());
          paramParcel2.writeNoException();
          return true;
        case 17:
          parceledListSlice.enforceInterface("android.content.pm.IShortcutService");
          arrayOfByte = getBackupPayload(parceledListSlice.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeByteArray(arrayOfByte);
          return true;
        case 16:
          arrayOfByte.enforceInterface("android.content.pm.IShortcutService");
          onApplicationActive(arrayOfByte.readString(), arrayOfByte.readInt());
          paramParcel2.writeNoException();
          return true;
        case 15:
          arrayOfByte.enforceInterface("android.content.pm.IShortcutService");
          resetThrottling();
          paramParcel2.writeNoException();
          return true;
        case 14:
          arrayOfByte.enforceInterface("android.content.pm.IShortcutService");
          reportShortcutUsed(arrayOfByte.readString(), arrayOfByte.readString(), arrayOfByte.readInt());
          paramParcel2.writeNoException();
          return true;
        case 13:
          arrayOfByte.enforceInterface("android.content.pm.IShortcutService");
          i = getIconMaxDimensions(arrayOfByte.readString(), arrayOfByte.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 12:
          arrayOfByte.enforceInterface("android.content.pm.IShortcutService");
          l = getRateLimitResetTime(arrayOfByte.readString(), arrayOfByte.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 11:
          arrayOfByte.enforceInterface("android.content.pm.IShortcutService");
          i = getRemainingCallCount(arrayOfByte.readString(), arrayOfByte.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 10:
          arrayOfByte.enforceInterface("android.content.pm.IShortcutService");
          i = getMaxShortcutCountPerActivity(arrayOfByte.readString(), arrayOfByte.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 9:
          arrayOfByte.enforceInterface("android.content.pm.IShortcutService");
          enableShortcuts(arrayOfByte.readString(), arrayOfByte.readArrayList(getClass().getClassLoader()), arrayOfByte.readInt());
          paramParcel2.writeNoException();
          return true;
        case 8:
          arrayOfByte.enforceInterface("android.content.pm.IShortcutService");
          str3 = arrayOfByte.readString();
          arrayList = arrayOfByte.readArrayList(getClass().getClassLoader());
          if (arrayOfByte.readInt() != 0) {
            CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)arrayOfByte);
          } else {
            shortcutInfo = null;
          } 
          disableShortcuts(str3, arrayList, (CharSequence)shortcutInfo, arrayOfByte.readInt(), arrayOfByte.readInt());
          paramParcel2.writeNoException();
          return true;
        case 7:
          arrayOfByte.enforceInterface("android.content.pm.IShortcutService");
          str1 = arrayOfByte.readString();
          if (arrayOfByte.readInt() != 0) {
            shortcutInfo = (ShortcutInfo)ShortcutInfo.CREATOR.createFromParcel((Parcel)arrayOfByte);
          } else {
            shortcutInfo = null;
          } 
          intent = createShortcutResultIntent(str1, shortcutInfo, arrayOfByte.readInt());
          paramParcel2.writeNoException();
          if (intent != null) {
            paramParcel2.writeInt(1);
            intent.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 6:
          intent.enforceInterface("android.content.pm.IShortcutService");
          str3 = intent.readString();
          if (intent.readInt() != 0) {
            shortcutInfo = (ShortcutInfo)ShortcutInfo.CREATOR.createFromParcel((Parcel)intent);
          } else {
            shortcutInfo = null;
          } 
          if (intent.readInt() != 0) {
            IntentSender intentSender = (IntentSender)IntentSender.CREATOR.createFromParcel((Parcel)intent);
          } else {
            str1 = null;
          } 
          bool1 = requestPinShortcut(str3, shortcutInfo, (IntentSender)str1, intent.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 5:
          intent.enforceInterface("android.content.pm.IShortcutService");
          str1 = intent.readString();
          if (intent.readInt() != 0) {
            ParceledListSlice parceledListSlice1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel((Parcel)intent);
          } else {
            shortcutInfo = null;
          } 
          bool1 = updateShortcuts(str1, (ParceledListSlice)shortcutInfo, intent.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 4:
          intent.enforceInterface("android.content.pm.IShortcutService");
          removeAllDynamicShortcuts(intent.readString(), intent.readInt());
          paramParcel2.writeNoException();
          return true;
        case 3:
          intent.enforceInterface("android.content.pm.IShortcutService");
          removeDynamicShortcuts(intent.readString(), intent.readArrayList(getClass().getClassLoader()), intent.readInt());
          paramParcel2.writeNoException();
          return true;
        case 2:
          intent.enforceInterface("android.content.pm.IShortcutService");
          str1 = intent.readString();
          if (intent.readInt() != 0) {
            ParceledListSlice parceledListSlice1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel((Parcel)intent);
          } else {
            shortcutInfo = null;
          } 
          bool1 = addDynamicShortcuts(str1, (ParceledListSlice)shortcutInfo, intent.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 1:
          break;
      } 
      intent.enforceInterface("android.content.pm.IShortcutService");
      String str1 = intent.readString();
      if (intent.readInt() != 0) {
        ParceledListSlice parceledListSlice1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel((Parcel)intent);
      } else {
        shortcutInfo = null;
      } 
      boolean bool1 = setDynamicShortcuts(str1, (ParceledListSlice)shortcutInfo, intent.readInt());
      paramParcel2.writeNoException();
      paramParcel2.writeInt(bool1);
      return true;
    } 
    paramParcel2.writeString("android.content.pm.IShortcutService");
    return true;
  }
  
  private static class Proxy implements IShortcutService {
    public static IShortcutService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public boolean addDynamicShortcuts(String param2String, ParceledListSlice param2ParceledListSlice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2ParceledListSlice != null) {
          parcel1.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          bool = IShortcutService.Stub.getDefaultImpl().addDynamicShortcuts(param2String, param2ParceledListSlice, param2Int);
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
    
    public void applyRestore(byte[] param2ArrayOfbyte, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeByteArray(param2ArrayOfbyte);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          IShortcutService.Stub.getDefaultImpl().applyRestore(param2ArrayOfbyte, param2Int);
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
    
    public Intent createShortcutResultIntent(String param2String, ShortcutInfo param2ShortcutInfo, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        if (param2ShortcutInfo != null) {
          parcel1.writeInt(1);
          param2ShortcutInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null)
          return IShortcutService.Stub.getDefaultImpl().createShortcutResultIntent(param2String, param2ShortcutInfo, param2Int); 
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
    
    public void disableShortcuts(String param2String, List param2List, CharSequence param2CharSequence, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        parcel1.writeList(param2List);
        if (param2CharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          IShortcutService.Stub.getDefaultImpl().disableShortcuts(param2String, param2List, param2CharSequence, param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enableShortcuts(String param2String, List param2List, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        parcel1.writeList(param2List);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          IShortcutService.Stub.getDefaultImpl().enableShortcuts(param2String, param2List, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public byte[] getBackupPayload(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null)
          return IShortcutService.Stub.getDefaultImpl().getBackupPayload(param2Int); 
        parcel2.readException();
        return parcel2.createByteArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getIconMaxDimensions(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          param2Int = IShortcutService.Stub.getDefaultImpl().getIconMaxDimensions(param2String, param2Int);
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
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IShortcutService";
    }
    
    public int getMaxShortcutCountPerActivity(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          param2Int = IShortcutService.Stub.getDefaultImpl().getMaxShortcutCountPerActivity(param2String, param2Int);
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
    
    public long getRateLimitResetTime(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null)
          return IShortcutService.Stub.getDefaultImpl().getRateLimitResetTime(param2String, param2Int); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getRemainingCallCount(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          param2Int = IShortcutService.Stub.getDefaultImpl().getRemainingCallCount(param2String, param2Int);
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
    
    public ParceledListSlice getShareTargets(String param2String, IntentFilter param2IntentFilter, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        if (param2IntentFilter != null) {
          parcel1.writeInt(1);
          param2IntentFilter.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null)
          return IShortcutService.Stub.getDefaultImpl().getShareTargets(param2String, param2IntentFilter, param2Int); 
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
    
    public ParceledListSlice getShortcuts(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null)
          return IShortcutService.Stub.getDefaultImpl().getShortcuts(param2String, param2Int1, param2Int2); 
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
    
    public boolean hasShareTargets(String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(21, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          bool = IShortcutService.Stub.getDefaultImpl().hasShareTargets(param2String1, param2String2, param2Int);
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
    
    public boolean isRequestPinItemSupported(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(19, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          bool = IShortcutService.Stub.getDefaultImpl().isRequestPinItemSupported(param2Int1, param2Int2);
          return bool;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        if (param2Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onApplicationActive(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          IShortcutService.Stub.getDefaultImpl().onApplicationActive(param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void pushDynamicShortcut(String param2String, ShortcutInfo param2ShortcutInfo, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        if (param2ShortcutInfo != null) {
          parcel1.writeInt(1);
          param2ShortcutInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          IShortcutService.Stub.getDefaultImpl().pushDynamicShortcut(param2String, param2ShortcutInfo, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeAllDynamicShortcuts(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          IShortcutService.Stub.getDefaultImpl().removeAllDynamicShortcuts(param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeDynamicShortcuts(String param2String, List param2List, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        parcel1.writeList(param2List);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          IShortcutService.Stub.getDefaultImpl().removeDynamicShortcuts(param2String, param2List, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeLongLivedShortcuts(String param2String, List param2List, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        parcel1.writeList(param2List);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          IShortcutService.Stub.getDefaultImpl().removeLongLivedShortcuts(param2String, param2List, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportShortcutUsed(String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          IShortcutService.Stub.getDefaultImpl().reportShortcutUsed(param2String1, param2String2, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean requestPinShortcut(String param2String, ShortcutInfo param2ShortcutInfo, IntentSender param2IntentSender, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2ShortcutInfo != null) {
          parcel1.writeInt(1);
          param2ShortcutInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IntentSender != null) {
          parcel1.writeInt(1);
          param2IntentSender.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          bool = IShortcutService.Stub.getDefaultImpl().requestPinShortcut(param2String, param2ShortcutInfo, param2IntentSender, param2Int);
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
    
    public void resetThrottling() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          IShortcutService.Stub.getDefaultImpl().resetThrottling();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setDynamicShortcuts(String param2String, ParceledListSlice param2ParceledListSlice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2ParceledListSlice != null) {
          parcel1.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          bool = IShortcutService.Stub.getDefaultImpl().setDynamicShortcuts(param2String, param2ParceledListSlice, param2Int);
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
    
    public boolean updateShortcuts(String param2String, ParceledListSlice param2ParceledListSlice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2ParceledListSlice != null) {
          parcel1.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
          bool = IShortcutService.Stub.getDefaultImpl().updateShortcuts(param2String, param2ParceledListSlice, param2Int);
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
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IShortcutService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */