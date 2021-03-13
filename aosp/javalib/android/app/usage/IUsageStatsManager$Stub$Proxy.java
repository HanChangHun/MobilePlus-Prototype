package android.app.usage;

import android.app.PendingIntent;
import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IUsageStatsManager {
  public static IUsageStatsManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
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
  
  public int getAppStandbyBucket(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        paramInt = IUsageStatsManager.Stub.getDefaultImpl().getAppStandbyBucket(paramString1, paramString2, paramInt);
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
  
  public ParceledListSlice getAppStandbyBuckets(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null)
        return IUsageStatsManager.Stub.getDefaultImpl().getAppStandbyBuckets(paramString, paramInt); 
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
  
  public boolean isAppInactive(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(9, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        bool = IUsageStatsManager.Stub.getDefaultImpl().isAppInactive(paramString1, paramInt, paramString2);
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
  
  public ParceledListSlice queryConfigurationStats(int paramInt, long paramLong1, long paramLong2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      try {
        parcel1.writeInt(paramInt);
        try {
          parcel1.writeLong(paramLong1);
          try {
            parcel1.writeLong(paramLong2);
            try {
              parcel1.writeString(paramString);
              if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                ParceledListSlice parceledListSlice = IUsageStatsManager.Stub.getDefaultImpl().queryConfigurationStats(paramInt, paramLong1, paramLong2, paramString);
                parcel2.recycle();
                parcel1.recycle();
                return parceledListSlice;
              } 
              parcel2.readException();
              if (parcel2.readInt() != 0) {
                ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
              } else {
                paramString = null;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return (ParceledListSlice)paramString;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString;
  }
  
  public ParceledListSlice queryEventStats(int paramInt, long paramLong1, long paramLong2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      try {
        parcel1.writeInt(paramInt);
        try {
          parcel1.writeLong(paramLong1);
          try {
            parcel1.writeLong(paramLong2);
            try {
              parcel1.writeString(paramString);
              if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                ParceledListSlice parceledListSlice = IUsageStatsManager.Stub.getDefaultImpl().queryEventStats(paramInt, paramLong1, paramLong2, paramString);
                parcel2.recycle();
                parcel1.recycle();
                return parceledListSlice;
              } 
              parcel2.readException();
              if (parcel2.readInt() != 0) {
                ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
              } else {
                paramString = null;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return (ParceledListSlice)paramString;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString;
  }
  
  public UsageEvents queryEvents(long paramLong1, long paramLong2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeLong(paramLong1);
      parcel1.writeLong(paramLong2);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null)
        return IUsageStatsManager.Stub.getDefaultImpl().queryEvents(paramLong1, paramLong2, paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        UsageEvents usageEvents = (UsageEvents)UsageEvents.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (UsageEvents)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public UsageEvents queryEventsForPackage(long paramLong1, long paramLong2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeLong(paramLong1);
      parcel1.writeLong(paramLong2);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null)
        return IUsageStatsManager.Stub.getDefaultImpl().queryEventsForPackage(paramLong1, paramLong2, paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        UsageEvents usageEvents = (UsageEvents)UsageEvents.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (UsageEvents)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public UsageEvents queryEventsForPackageForUser(long paramLong1, long paramLong2, int paramInt, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      try {
        parcel1.writeLong(paramLong1);
        try {
          parcel1.writeLong(paramLong2);
          try {
            parcel1.writeInt(paramInt);
            parcel1.writeString(paramString1);
            parcel1.writeString(paramString2);
            if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
              UsageEvents usageEvents = IUsageStatsManager.Stub.getDefaultImpl().queryEventsForPackageForUser(paramLong1, paramLong2, paramInt, paramString1, paramString2);
              parcel2.recycle();
              parcel1.recycle();
              return usageEvents;
            } 
            parcel2.readException();
            if (parcel2.readInt() != 0) {
              UsageEvents usageEvents = (UsageEvents)UsageEvents.CREATOR.createFromParcel(parcel2);
            } else {
              paramString1 = null;
            } 
            parcel2.recycle();
            parcel1.recycle();
            return (UsageEvents)paramString1;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public UsageEvents queryEventsForUser(long paramLong1, long paramLong2, int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      try {
        parcel1.writeLong(paramLong1);
        try {
          parcel1.writeLong(paramLong2);
          try {
            parcel1.writeInt(paramInt);
            try {
              parcel1.writeString(paramString);
              if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                UsageEvents usageEvents = IUsageStatsManager.Stub.getDefaultImpl().queryEventsForUser(paramLong1, paramLong2, paramInt, paramString);
                parcel2.recycle();
                parcel1.recycle();
                return usageEvents;
              } 
              parcel2.readException();
              if (parcel2.readInt() != 0) {
                UsageEvents usageEvents = (UsageEvents)UsageEvents.CREATOR.createFromParcel(parcel2);
              } else {
                paramString = null;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return (UsageEvents)paramString;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString;
  }
  
  public ParceledListSlice queryUsageStats(int paramInt, long paramLong1, long paramLong2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      try {
        parcel1.writeInt(paramInt);
        try {
          parcel1.writeLong(paramLong1);
          try {
            parcel1.writeLong(paramLong2);
            try {
              parcel1.writeString(paramString);
              if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                ParceledListSlice parceledListSlice = IUsageStatsManager.Stub.getDefaultImpl().queryUsageStats(paramInt, paramLong1, paramLong2, paramString);
                parcel2.recycle();
                parcel1.recycle();
                return parceledListSlice;
              } 
              parcel2.readException();
              if (parcel2.readInt() != 0) {
                ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
              } else {
                paramString = null;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return (ParceledListSlice)paramString;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString;
  }
  
  public void registerAppUsageLimitObserver(int paramInt, String[] paramArrayOfString, long paramLong1, long paramLong2, PendingIntent paramPendingIntent, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      try {
        parcel1.writeInt(paramInt);
        try {
          parcel1.writeStringArray(paramArrayOfString);
          parcel1.writeLong(paramLong1);
          parcel1.writeLong(paramLong2);
          if (paramPendingIntent != null) {
            parcel1.writeInt(1);
            paramPendingIntent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeString(paramString);
            if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
              IUsageStatsManager.Stub.getDefaultImpl().registerAppUsageLimitObserver(paramInt, paramArrayOfString, paramLong1, paramLong2, paramPendingIntent, paramString);
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
    throw paramArrayOfString;
  }
  
  public void registerAppUsageObserver(int paramInt, String[] paramArrayOfString, long paramLong, PendingIntent paramPendingIntent, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      try {
        parcel1.writeInt(paramInt);
        try {
          parcel1.writeStringArray(paramArrayOfString);
          try {
            parcel1.writeLong(paramLong);
            if (paramPendingIntent != null) {
              parcel1.writeInt(1);
              paramPendingIntent.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeString(paramString);
              if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
                IUsageStatsManager.Stub.getDefaultImpl().registerAppUsageObserver(paramInt, paramArrayOfString, paramLong, paramPendingIntent, paramString);
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
    throw paramArrayOfString;
  }
  
  public void registerUsageSessionObserver(int paramInt, String[] paramArrayOfString, long paramLong1, long paramLong2, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      try {
        parcel1.writeInt(paramInt);
        parcel1.writeStringArray(paramArrayOfString);
        parcel1.writeLong(paramLong1);
        parcel1.writeLong(paramLong2);
        if (paramPendingIntent1 != null) {
          parcel1.writeInt(1);
          paramPendingIntent1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramPendingIntent2 != null) {
          parcel1.writeInt(1);
          paramPendingIntent2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(paramString);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
          IUsageStatsManager.Stub.getDefaultImpl().registerUsageSessionObserver(paramInt, paramArrayOfString, paramLong1, paramLong2, paramPendingIntent1, paramPendingIntent2, paramString);
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
    throw paramArrayOfString;
  }
  
  public void reportChooserSelection(String paramString1, int paramInt, String paramString2, String[] paramArrayOfString, String paramString3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeString(paramString3);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        IUsageStatsManager.Stub.getDefaultImpl().reportChooserSelection(paramString1, paramInt, paramString2, paramArrayOfString, paramString3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportPastUsageStart(IBinder paramIBinder, String paramString1, long paramLong, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString1);
      parcel1.writeLong(paramLong);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        IUsageStatsManager.Stub.getDefaultImpl().reportPastUsageStart(paramIBinder, paramString1, paramLong, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportUsageStart(IBinder paramIBinder, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        IUsageStatsManager.Stub.getDefaultImpl().reportUsageStart(paramIBinder, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportUsageStop(IBinder paramIBinder, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        IUsageStatsManager.Stub.getDefaultImpl().reportUsageStop(paramIBinder, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAppInactive(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        IUsageStatsManager.Stub.getDefaultImpl().setAppInactive(paramString, paramBoolean, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAppStandbyBucket(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        IUsageStatsManager.Stub.getDefaultImpl().setAppStandbyBucket(paramString, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAppStandbyBuckets(ParceledListSlice paramParceledListSlice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        IUsageStatsManager.Stub.getDefaultImpl().setAppStandbyBuckets(paramParceledListSlice, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterAppUsageLimitObserver(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        IUsageStatsManager.Stub.getDefaultImpl().unregisterAppUsageLimitObserver(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterAppUsageObserver(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        IUsageStatsManager.Stub.getDefaultImpl().unregisterAppUsageObserver(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterUsageSessionObserver(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IUsageStatsManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IUsageStatsManager.Stub.getDefaultImpl() != null) {
        IUsageStatsManager.Stub.getDefaultImpl().unregisterUsageSessionObserver(paramInt, paramString);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/IUsageStatsManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */