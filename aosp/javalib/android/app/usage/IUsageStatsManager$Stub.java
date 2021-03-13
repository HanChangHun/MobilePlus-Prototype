package android.app.usage;

import android.app.PendingIntent;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IUsageStatsManager {
  private static final String DESCRIPTOR = "android.app.usage.IUsageStatsManager";
  
  static final int TRANSACTION_forceUsageSourceSettingRead = 26;
  
  static final int TRANSACTION_getAppStandbyBucket = 12;
  
  static final int TRANSACTION_getAppStandbyBuckets = 14;
  
  static final int TRANSACTION_getUsageSource = 25;
  
  static final int TRANSACTION_isAppInactive = 9;
  
  static final int TRANSACTION_onCarrierPrivilegedAppsChanged = 10;
  
  static final int TRANSACTION_queryConfigurationStats = 2;
  
  static final int TRANSACTION_queryEventStats = 3;
  
  static final int TRANSACTION_queryEvents = 4;
  
  static final int TRANSACTION_queryEventsForPackage = 5;
  
  static final int TRANSACTION_queryEventsForPackageForUser = 7;
  
  static final int TRANSACTION_queryEventsForUser = 6;
  
  static final int TRANSACTION_queryUsageStats = 1;
  
  static final int TRANSACTION_registerAppUsageLimitObserver = 20;
  
  static final int TRANSACTION_registerAppUsageObserver = 16;
  
  static final int TRANSACTION_registerUsageSessionObserver = 18;
  
  static final int TRANSACTION_reportChooserSelection = 11;
  
  static final int TRANSACTION_reportPastUsageStart = 23;
  
  static final int TRANSACTION_reportUsageStart = 22;
  
  static final int TRANSACTION_reportUsageStop = 24;
  
  static final int TRANSACTION_setAppInactive = 8;
  
  static final int TRANSACTION_setAppStandbyBucket = 13;
  
  static final int TRANSACTION_setAppStandbyBuckets = 15;
  
  static final int TRANSACTION_unregisterAppUsageLimitObserver = 21;
  
  static final int TRANSACTION_unregisterAppUsageObserver = 17;
  
  static final int TRANSACTION_unregisterUsageSessionObserver = 19;
  
  public Stub() {
    attachInterface(this, "android.app.usage.IUsageStatsManager");
  }
  
  public static IUsageStatsManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.usage.IUsageStatsManager");
    return (iInterface != null && iInterface instanceof IUsageStatsManager) ? (IUsageStatsManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IUsageStatsManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 26:
        return "forceUsageSourceSettingRead";
      case 25:
        return "getUsageSource";
      case 24:
        return "reportUsageStop";
      case 23:
        return "reportPastUsageStart";
      case 22:
        return "reportUsageStart";
      case 21:
        return "unregisterAppUsageLimitObserver";
      case 20:
        return "registerAppUsageLimitObserver";
      case 19:
        return "unregisterUsageSessionObserver";
      case 18:
        return "registerUsageSessionObserver";
      case 17:
        return "unregisterAppUsageObserver";
      case 16:
        return "registerAppUsageObserver";
      case 15:
        return "setAppStandbyBuckets";
      case 14:
        return "getAppStandbyBuckets";
      case 13:
        return "setAppStandbyBucket";
      case 12:
        return "getAppStandbyBucket";
      case 11:
        return "reportChooserSelection";
      case 10:
        return "onCarrierPrivilegedAppsChanged";
      case 9:
        return "isAppInactive";
      case 8:
        return "setAppInactive";
      case 7:
        return "queryEventsForPackageForUser";
      case 6:
        return "queryEventsForUser";
      case 5:
        return "queryEventsForPackage";
      case 4:
        return "queryEvents";
      case 3:
        return "queryEventStats";
      case 2:
        return "queryConfigurationStats";
      case 1:
        break;
    } 
    return "queryUsageStats";
  }
  
  public static boolean setDefaultImpl(IUsageStatsManager paramIUsageStatsManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIUsageStatsManager != null) {
        Proxy.sDefaultImpl = paramIUsageStatsManager;
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
      boolean bool;
      ParceledListSlice parceledListSlice2;
      UsageEvents usageEvents;
      String[] arrayOfString1;
      long l1;
      long l2;
      PendingIntent pendingIntent;
      String str;
      String[] arrayOfString2;
      boolean bool1 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 26:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          forceUsageSourceSettingRead();
          paramParcel2.writeNoException();
          return true;
        case 25:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          paramInt1 = getUsageSource();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 24:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          reportUsageStop(paramParcel1.readStrongBinder(), paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 23:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          reportPastUsageStart(paramParcel1.readStrongBinder(), paramParcel1.readString(), paramParcel1.readLong(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 22:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          reportUsageStart(paramParcel1.readStrongBinder(), paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 21:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          unregisterAppUsageLimitObserver(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 20:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          paramInt1 = paramParcel1.readInt();
          arrayOfString1 = paramParcel1.createStringArray();
          l1 = paramParcel1.readLong();
          l2 = paramParcel1.readLong();
          if (paramParcel1.readInt() != 0) {
            pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
          } else {
            pendingIntent = null;
          } 
          registerAppUsageLimitObserver(paramInt1, arrayOfString1, l1, l2, pendingIntent, paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 19:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          unregisterUsageSessionObserver(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 18:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          paramInt1 = paramParcel1.readInt();
          arrayOfString2 = paramParcel1.createStringArray();
          l2 = paramParcel1.readLong();
          l1 = paramParcel1.readLong();
          if (paramParcel1.readInt() != 0) {
            pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
          } else {
            pendingIntent = null;
          } 
          if (paramParcel1.readInt() != 0) {
            PendingIntent pendingIntent1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
          } else {
            arrayOfString1 = null;
          } 
          registerUsageSessionObserver(paramInt1, arrayOfString2, l2, l1, pendingIntent, (PendingIntent)arrayOfString1, paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 17:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          unregisterAppUsageObserver(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 16:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          paramInt1 = paramParcel1.readInt();
          arrayOfString1 = paramParcel1.createStringArray();
          l2 = paramParcel1.readLong();
          if (paramParcel1.readInt() != 0) {
            pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
          } else {
            pendingIntent = null;
          } 
          registerAppUsageObserver(paramInt1, arrayOfString1, l2, pendingIntent, paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 15:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          if (paramParcel1.readInt() != 0) {
            ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(paramParcel1);
          } else {
            pendingIntent = null;
          } 
          setAppStandbyBuckets((ParceledListSlice)pendingIntent, paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 14:
          paramParcel1.enforceInterface("android.app.usage.IUsageStatsManager");
          parceledListSlice2 = getAppStandbyBuckets(paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice2 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 13:
          parceledListSlice2.enforceInterface("android.app.usage.IUsageStatsManager");
          setAppStandbyBucket(parceledListSlice2.readString(), parceledListSlice2.readInt(), parceledListSlice2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 12:
          parceledListSlice2.enforceInterface("android.app.usage.IUsageStatsManager");
          paramInt1 = getAppStandbyBucket(parceledListSlice2.readString(), parceledListSlice2.readString(), parceledListSlice2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 11:
          parceledListSlice2.enforceInterface("android.app.usage.IUsageStatsManager");
          reportChooserSelection(parceledListSlice2.readString(), parceledListSlice2.readInt(), parceledListSlice2.readString(), parceledListSlice2.createStringArray(), parceledListSlice2.readString());
          paramParcel2.writeNoException();
          return true;
        case 10:
          parceledListSlice2.enforceInterface("android.app.usage.IUsageStatsManager");
          onCarrierPrivilegedAppsChanged();
          paramParcel2.writeNoException();
          return true;
        case 9:
          parceledListSlice2.enforceInterface("android.app.usage.IUsageStatsManager");
          bool = isAppInactive(parceledListSlice2.readString(), parceledListSlice2.readInt(), parceledListSlice2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 8:
          parceledListSlice2.enforceInterface("android.app.usage.IUsageStatsManager");
          str = parceledListSlice2.readString();
          if (parceledListSlice2.readInt() != 0)
            bool1 = true; 
          setAppInactive(str, bool1, parceledListSlice2.readInt());
          paramParcel2.writeNoException();
          return true;
        case 7:
          parceledListSlice2.enforceInterface("android.app.usage.IUsageStatsManager");
          usageEvents = queryEventsForPackageForUser(parceledListSlice2.readLong(), parceledListSlice2.readLong(), parceledListSlice2.readInt(), parceledListSlice2.readString(), parceledListSlice2.readString());
          paramParcel2.writeNoException();
          if (usageEvents != null) {
            paramParcel2.writeInt(1);
            usageEvents.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 6:
          usageEvents.enforceInterface("android.app.usage.IUsageStatsManager");
          usageEvents = queryEventsForUser(usageEvents.readLong(), usageEvents.readLong(), usageEvents.readInt(), usageEvents.readString());
          paramParcel2.writeNoException();
          if (usageEvents != null) {
            paramParcel2.writeInt(1);
            usageEvents.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 5:
          usageEvents.enforceInterface("android.app.usage.IUsageStatsManager");
          usageEvents = queryEventsForPackage(usageEvents.readLong(), usageEvents.readLong(), usageEvents.readString());
          paramParcel2.writeNoException();
          if (usageEvents != null) {
            paramParcel2.writeInt(1);
            usageEvents.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 4:
          usageEvents.enforceInterface("android.app.usage.IUsageStatsManager");
          usageEvents = queryEvents(usageEvents.readLong(), usageEvents.readLong(), usageEvents.readString());
          paramParcel2.writeNoException();
          if (usageEvents != null) {
            paramParcel2.writeInt(1);
            usageEvents.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 3:
          usageEvents.enforceInterface("android.app.usage.IUsageStatsManager");
          parceledListSlice1 = queryEventStats(usageEvents.readInt(), usageEvents.readLong(), usageEvents.readLong(), usageEvents.readString());
          paramParcel2.writeNoException();
          if (parceledListSlice1 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 2:
          parceledListSlice1.enforceInterface("android.app.usage.IUsageStatsManager");
          parceledListSlice1 = queryConfigurationStats(parceledListSlice1.readInt(), parceledListSlice1.readLong(), parceledListSlice1.readLong(), parceledListSlice1.readString());
          paramParcel2.writeNoException();
          if (parceledListSlice1 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 1:
          break;
      } 
      parceledListSlice1.enforceInterface("android.app.usage.IUsageStatsManager");
      ParceledListSlice parceledListSlice1 = queryUsageStats(parceledListSlice1.readInt(), parceledListSlice1.readLong(), parceledListSlice1.readLong(), parceledListSlice1.readString());
      paramParcel2.writeNoException();
      if (parceledListSlice1 != null) {
        paramParcel2.writeInt(1);
        parceledListSlice1.writeToParcel(paramParcel2, 1);
      } else {
        paramParcel2.writeInt(0);
      } 
      return true;
    } 
    paramParcel2.writeString("android.app.usage.IUsageStatsManager");
    return true;
  }
  
  private static class Proxy implements IUsageStatsManager {
    public static IUsageStatsManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void forceUsageSourceSettingRead() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().forceUsageSourceSettingRead();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getAppStandbyBucket(String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          param2Int = IUsageStatsManager.Stub.getDefaultImpl().getAppStandbyBucket(param2String1, param2String2, param2Int);
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
    
    public ParceledListSlice getAppStandbyBuckets(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null)
          return IUsageStatsManager.Stub.getDefaultImpl().getAppStandbyBuckets(param2String, param2Int); 
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
    
    public String getInterfaceDescriptor() {
      return "android.app.usage.IUsageStatsManager";
    }
    
    public int getUsageSource() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null)
          return IUsageStatsManager.Stub.getDefaultImpl().getUsageSource(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAppInactive(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(9, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          bool = IUsageStatsManager.Stub.getDefaultImpl().isAppInactive(param2String1, param2Int, param2String2);
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
    
    public void onCarrierPrivilegedAppsChanged() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().onCarrierPrivilegedAppsChanged();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice queryConfigurationStats(int param2Int, long param2Long1, long param2Long2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        try {
          parcel1.writeInt(param2Int);
          try {
            parcel1.writeLong(param2Long1);
            try {
              parcel1.writeLong(param2Long2);
              try {
                parcel1.writeString(param2String);
                if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                  ParceledListSlice parceledListSlice = IUsageStatsManager.Stub.getDefaultImpl().queryConfigurationStats(param2Int, param2Long1, param2Long2, param2String);
                  parcel2.recycle();
                  parcel1.recycle();
                  return parceledListSlice;
                } 
                parcel2.readException();
                if (parcel2.readInt() != 0) {
                  ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
                } else {
                  param2String = null;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return (ParceledListSlice)param2String;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public ParceledListSlice queryEventStats(int param2Int, long param2Long1, long param2Long2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        try {
          parcel1.writeInt(param2Int);
          try {
            parcel1.writeLong(param2Long1);
            try {
              parcel1.writeLong(param2Long2);
              try {
                parcel1.writeString(param2String);
                if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                  ParceledListSlice parceledListSlice = IUsageStatsManager.Stub.getDefaultImpl().queryEventStats(param2Int, param2Long1, param2Long2, param2String);
                  parcel2.recycle();
                  parcel1.recycle();
                  return parceledListSlice;
                } 
                parcel2.readException();
                if (parcel2.readInt() != 0) {
                  ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
                } else {
                  param2String = null;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return (ParceledListSlice)param2String;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public UsageEvents queryEvents(long param2Long1, long param2Long2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeLong(param2Long1);
        parcel1.writeLong(param2Long2);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null)
          return IUsageStatsManager.Stub.getDefaultImpl().queryEvents(param2Long1, param2Long2, param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          UsageEvents usageEvents = (UsageEvents)UsageEvents.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (UsageEvents)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public UsageEvents queryEventsForPackage(long param2Long1, long param2Long2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeLong(param2Long1);
        parcel1.writeLong(param2Long2);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null)
          return IUsageStatsManager.Stub.getDefaultImpl().queryEventsForPackage(param2Long1, param2Long2, param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          UsageEvents usageEvents = (UsageEvents)UsageEvents.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (UsageEvents)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public UsageEvents queryEventsForPackageForUser(long param2Long1, long param2Long2, int param2Int, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        try {
          parcel1.writeLong(param2Long1);
          try {
            parcel1.writeLong(param2Long2);
            try {
              parcel1.writeInt(param2Int);
              parcel1.writeString(param2String1);
              parcel1.writeString(param2String2);
              if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                UsageEvents usageEvents = IUsageStatsManager.Stub.getDefaultImpl().queryEventsForPackageForUser(param2Long1, param2Long2, param2Int, param2String1, param2String2);
                parcel2.recycle();
                parcel1.recycle();
                return usageEvents;
              } 
              parcel2.readException();
              if (parcel2.readInt() != 0) {
                UsageEvents usageEvents = (UsageEvents)UsageEvents.CREATOR.createFromParcel(parcel2);
              } else {
                param2String1 = null;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return (UsageEvents)param2String1;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String1;
    }
    
    public UsageEvents queryEventsForUser(long param2Long1, long param2Long2, int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        try {
          parcel1.writeLong(param2Long1);
          try {
            parcel1.writeLong(param2Long2);
            try {
              parcel1.writeInt(param2Int);
              try {
                parcel1.writeString(param2String);
                if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                  UsageEvents usageEvents = IUsageStatsManager.Stub.getDefaultImpl().queryEventsForUser(param2Long1, param2Long2, param2Int, param2String);
                  parcel2.recycle();
                  parcel1.recycle();
                  return usageEvents;
                } 
                parcel2.readException();
                if (parcel2.readInt() != 0) {
                  UsageEvents usageEvents = (UsageEvents)UsageEvents.CREATOR.createFromParcel(parcel2);
                } else {
                  param2String = null;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return (UsageEvents)param2String;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public ParceledListSlice queryUsageStats(int param2Int, long param2Long1, long param2Long2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        try {
          parcel1.writeInt(param2Int);
          try {
            parcel1.writeLong(param2Long1);
            try {
              parcel1.writeLong(param2Long2);
              try {
                parcel1.writeString(param2String);
                if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                  ParceledListSlice parceledListSlice = IUsageStatsManager.Stub.getDefaultImpl().queryUsageStats(param2Int, param2Long1, param2Long2, param2String);
                  parcel2.recycle();
                  parcel1.recycle();
                  return parceledListSlice;
                } 
                parcel2.readException();
                if (parcel2.readInt() != 0) {
                  ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
                } else {
                  param2String = null;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return (ParceledListSlice)param2String;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public void registerAppUsageLimitObserver(int param2Int, String[] param2ArrayOfString, long param2Long1, long param2Long2, PendingIntent param2PendingIntent, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        try {
          parcel1.writeInt(param2Int);
          try {
            parcel1.writeStringArray(param2ArrayOfString);
            parcel1.writeLong(param2Long1);
            parcel1.writeLong(param2Long2);
            if (param2PendingIntent != null) {
              parcel1.writeInt(1);
              param2PendingIntent.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeString(param2String);
              if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                IUsageStatsManager.Stub.getDefaultImpl().registerAppUsageLimitObserver(param2Int, param2ArrayOfString, param2Long1, param2Long2, param2PendingIntent, param2String);
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
      throw param2ArrayOfString;
    }
    
    public void registerAppUsageObserver(int param2Int, String[] param2ArrayOfString, long param2Long, PendingIntent param2PendingIntent, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        try {
          parcel1.writeInt(param2Int);
          try {
            parcel1.writeStringArray(param2ArrayOfString);
            try {
              parcel1.writeLong(param2Long);
              if (param2PendingIntent != null) {
                parcel1.writeInt(1);
                param2PendingIntent.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeString(param2String);
                if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                  IUsageStatsManager.Stub.getDefaultImpl().registerAppUsageObserver(param2Int, param2ArrayOfString, param2Long, param2PendingIntent, param2String);
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
      throw param2ArrayOfString;
    }
    
    public void registerUsageSessionObserver(int param2Int, String[] param2ArrayOfString, long param2Long1, long param2Long2, PendingIntent param2PendingIntent1, PendingIntent param2PendingIntent2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        try {
          parcel1.writeInt(param2Int);
          parcel1.writeStringArray(param2ArrayOfString);
          parcel1.writeLong(param2Long1);
          parcel1.writeLong(param2Long2);
          if (param2PendingIntent1 != null) {
            parcel1.writeInt(1);
            param2PendingIntent1.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2PendingIntent2 != null) {
            parcel1.writeInt(1);
            param2PendingIntent2.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
            IUsageStatsManager.Stub.getDefaultImpl().registerUsageSessionObserver(param2Int, param2ArrayOfString, param2Long1, param2Long2, param2PendingIntent1, param2PendingIntent2, param2String);
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
      throw param2ArrayOfString;
    }
    
    public void reportChooserSelection(String param2String1, int param2Int, String param2String2, String[] param2ArrayOfString, String param2String3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        parcel1.writeStringArray(param2ArrayOfString);
        parcel1.writeString(param2String3);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().reportChooserSelection(param2String1, param2Int, param2String2, param2ArrayOfString, param2String3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportPastUsageStart(IBinder param2IBinder, String param2String1, long param2Long, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeString(param2String1);
        parcel1.writeLong(param2Long);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().reportPastUsageStart(param2IBinder, param2String1, param2Long, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportUsageStart(IBinder param2IBinder, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().reportUsageStart(param2IBinder, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportUsageStop(IBinder param2IBinder, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().reportUsageStop(param2IBinder, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAppInactive(String param2String, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().setAppInactive(param2String, param2Boolean, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAppStandbyBucket(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().setAppStandbyBucket(param2String, param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAppStandbyBuckets(ParceledListSlice param2ParceledListSlice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        if (param2ParceledListSlice != null) {
          parcel1.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().setAppStandbyBuckets(param2ParceledListSlice, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterAppUsageLimitObserver(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().unregisterAppUsageLimitObserver(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterAppUsageObserver(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().unregisterAppUsageObserver(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterUsageSessionObserver(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().unregisterUsageSessionObserver(param2Int, param2String);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/IUsageStatsManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */