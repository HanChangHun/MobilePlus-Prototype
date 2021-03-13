package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.WorkSource;

public interface IAlarmManager extends IInterface {
  long currentNetworkTimeMillis() throws RemoteException;
  
  AlarmManager.AlarmClockInfo getNextAlarmClock(int paramInt) throws RemoteException;
  
  long getNextWakeFromIdleTime() throws RemoteException;
  
  void remove(PendingIntent paramPendingIntent, IAlarmListener paramIAlarmListener) throws RemoteException;
  
  void set(String paramString1, int paramInt1, long paramLong1, long paramLong2, long paramLong3, int paramInt2, PendingIntent paramPendingIntent, IAlarmListener paramIAlarmListener, String paramString2, WorkSource paramWorkSource, AlarmManager.AlarmClockInfo paramAlarmClockInfo) throws RemoteException;
  
  boolean setTime(long paramLong) throws RemoteException;
  
  void setTimeZone(String paramString) throws RemoteException;
  
  public static class Default implements IAlarmManager {
    public IBinder asBinder() {
      return null;
    }
    
    public long currentNetworkTimeMillis() throws RemoteException {
      return 0L;
    }
    
    public AlarmManager.AlarmClockInfo getNextAlarmClock(int param1Int) throws RemoteException {
      return null;
    }
    
    public long getNextWakeFromIdleTime() throws RemoteException {
      return 0L;
    }
    
    public void remove(PendingIntent param1PendingIntent, IAlarmListener param1IAlarmListener) throws RemoteException {}
    
    public void set(String param1String1, int param1Int1, long param1Long1, long param1Long2, long param1Long3, int param1Int2, PendingIntent param1PendingIntent, IAlarmListener param1IAlarmListener, String param1String2, WorkSource param1WorkSource, AlarmManager.AlarmClockInfo param1AlarmClockInfo) throws RemoteException {}
    
    public boolean setTime(long param1Long) throws RemoteException {
      return false;
    }
    
    public void setTimeZone(String param1String) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAlarmManager {
    private static final String DESCRIPTOR = "android.app.IAlarmManager";
    
    static final int TRANSACTION_currentNetworkTimeMillis = 7;
    
    static final int TRANSACTION_getNextAlarmClock = 6;
    
    static final int TRANSACTION_getNextWakeFromIdleTime = 5;
    
    static final int TRANSACTION_remove = 4;
    
    static final int TRANSACTION_set = 1;
    
    static final int TRANSACTION_setTime = 2;
    
    static final int TRANSACTION_setTimeZone = 3;
    
    public Stub() {
      attachInterface(this, "android.app.IAlarmManager");
    }
    
    public static IAlarmManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IAlarmManager");
      return (iInterface != null && iInterface instanceof IAlarmManager) ? (IAlarmManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAlarmManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 7:
          return "currentNetworkTimeMillis";
        case 6:
          return "getNextAlarmClock";
        case 5:
          return "getNextWakeFromIdleTime";
        case 4:
          return "remove";
        case 3:
          return "setTimeZone";
        case 2:
          return "setTime";
        case 1:
          break;
      } 
      return "set";
    }
    
    public static boolean setDefaultImpl(IAlarmManager param1IAlarmManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAlarmManager != null) {
          Proxy.sDefaultImpl = param1IAlarmManager;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1598968902) {
        boolean bool;
        AlarmManager.AlarmClockInfo alarmClockInfo;
        PendingIntent pendingIntent;
        WorkSource workSource;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 7:
            param1Parcel1.enforceInterface("android.app.IAlarmManager");
            l1 = currentNetworkTimeMillis();
            param1Parcel2.writeNoException();
            param1Parcel2.writeLong(l1);
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.app.IAlarmManager");
            alarmClockInfo = getNextAlarmClock(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            if (alarmClockInfo != null) {
              param1Parcel2.writeInt(1);
              alarmClockInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 5:
            alarmClockInfo.enforceInterface("android.app.IAlarmManager");
            l1 = getNextWakeFromIdleTime();
            param1Parcel2.writeNoException();
            param1Parcel2.writeLong(l1);
            return true;
          case 4:
            alarmClockInfo.enforceInterface("android.app.IAlarmManager");
            if (alarmClockInfo.readInt() != 0) {
              pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)alarmClockInfo);
            } else {
              pendingIntent = null;
            } 
            remove(pendingIntent, IAlarmListener.Stub.asInterface(alarmClockInfo.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 3:
            alarmClockInfo.enforceInterface("android.app.IAlarmManager");
            setTimeZone(alarmClockInfo.readString());
            param1Parcel2.writeNoException();
            return true;
          case 2:
            alarmClockInfo.enforceInterface("android.app.IAlarmManager");
            bool = setTime(alarmClockInfo.readLong());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 1:
            break;
        } 
        alarmClockInfo.enforceInterface("android.app.IAlarmManager");
        String str1 = alarmClockInfo.readString();
        int i = alarmClockInfo.readInt();
        long l1 = alarmClockInfo.readLong();
        long l2 = alarmClockInfo.readLong();
        long l3 = alarmClockInfo.readLong();
        param1Int2 = alarmClockInfo.readInt();
        if (alarmClockInfo.readInt() != 0) {
          pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)alarmClockInfo);
        } else {
          pendingIntent = null;
        } 
        IAlarmListener iAlarmListener = IAlarmListener.Stub.asInterface(alarmClockInfo.readStrongBinder());
        String str2 = alarmClockInfo.readString();
        if (alarmClockInfo.readInt() != 0) {
          workSource = (WorkSource)WorkSource.CREATOR.createFromParcel((Parcel)alarmClockInfo);
        } else {
          workSource = null;
        } 
        if (alarmClockInfo.readInt() != 0) {
          alarmClockInfo = (AlarmManager.AlarmClockInfo)AlarmManager.AlarmClockInfo.CREATOR.createFromParcel((Parcel)alarmClockInfo);
        } else {
          alarmClockInfo = null;
        } 
        set(str1, i, l1, l2, l3, param1Int2, pendingIntent, iAlarmListener, str2, workSource, alarmClockInfo);
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.app.IAlarmManager");
      return true;
    }
    
    private static class Proxy implements IAlarmManager {
      public static IAlarmManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public long currentNetworkTimeMillis() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IAlarmManager");
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null)
            return IAlarmManager.Stub.getDefaultImpl().currentNetworkTimeMillis(); 
          parcel2.readException();
          return parcel2.readLong();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IAlarmManager";
      }
      
      public AlarmManager.AlarmClockInfo getNextAlarmClock(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          AlarmManager.AlarmClockInfo alarmClockInfo;
          parcel1.writeInterfaceToken("android.app.IAlarmManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
            alarmClockInfo = IAlarmManager.Stub.getDefaultImpl().getNextAlarmClock(param2Int);
            return alarmClockInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            alarmClockInfo = (AlarmManager.AlarmClockInfo)AlarmManager.AlarmClockInfo.CREATOR.createFromParcel(parcel2);
          } else {
            alarmClockInfo = null;
          } 
          return alarmClockInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public long getNextWakeFromIdleTime() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IAlarmManager");
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null)
            return IAlarmManager.Stub.getDefaultImpl().getNextWakeFromIdleTime(); 
          parcel2.readException();
          return parcel2.readLong();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void remove(PendingIntent param2PendingIntent, IAlarmListener param2IAlarmListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IAlarmManager");
          if (param2PendingIntent != null) {
            parcel1.writeInt(1);
            param2PendingIntent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2IAlarmListener != null) {
            iBinder = param2IAlarmListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
            IAlarmManager.Stub.getDefaultImpl().remove(param2PendingIntent, param2IAlarmListener);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void set(String param2String1, int param2Int1, long param2Long1, long param2Long2, long param2Long3, int param2Int2, PendingIntent param2PendingIntent, IAlarmListener param2IAlarmListener, String param2String2, WorkSource param2WorkSource, AlarmManager.AlarmClockInfo param2AlarmClockInfo) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IAlarmManager");
          parcel1.writeString(param2String1);
          parcel1.writeInt(param2Int1);
          parcel1.writeLong(param2Long1);
          parcel1.writeLong(param2Long2);
          parcel1.writeLong(param2Long3);
          parcel1.writeInt(param2Int2);
          if (param2PendingIntent != null) {
            try {
              parcel1.writeInt(1);
              param2PendingIntent.writeToParcel(parcel1, 0);
            } finally {}
          } else {
            parcel1.writeInt(0);
          } 
          if (param2IAlarmListener != null) {
            iBinder = param2IAlarmListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String2);
          if (param2WorkSource != null) {
            parcel1.writeInt(1);
            param2WorkSource.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2AlarmClockInfo != null) {
            parcel1.writeInt(1);
            param2AlarmClockInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
            IAlarmManager iAlarmManager = IAlarmManager.Stub.getDefaultImpl();
            try {
              iAlarmManager.set(param2String1, param2Int1, param2Long1, param2Long2, param2Long3, param2Int2, param2PendingIntent, param2IAlarmListener, param2String2, param2WorkSource, param2AlarmClockInfo);
              parcel2.recycle();
              parcel1.recycle();
              return;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            parcel.recycle();
            parcel1.recycle();
            return;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2String1;
      }
      
      public boolean setTime(long param2Long) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IAlarmManager");
          parcel1.writeLong(param2Long);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(2, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
            bool = IAlarmManager.Stub.getDefaultImpl().setTime(param2Long);
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
      
      public void setTimeZone(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IAlarmManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
            IAlarmManager.Stub.getDefaultImpl().setTimeZone(param2String);
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
  
  private static class Proxy implements IAlarmManager {
    public static IAlarmManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public long currentNetworkTimeMillis() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IAlarmManager");
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null)
          return IAlarmManager.Stub.getDefaultImpl().currentNetworkTimeMillis(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IAlarmManager";
    }
    
    public AlarmManager.AlarmClockInfo getNextAlarmClock(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        AlarmManager.AlarmClockInfo alarmClockInfo;
        parcel1.writeInterfaceToken("android.app.IAlarmManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
          alarmClockInfo = IAlarmManager.Stub.getDefaultImpl().getNextAlarmClock(param1Int);
          return alarmClockInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          alarmClockInfo = (AlarmManager.AlarmClockInfo)AlarmManager.AlarmClockInfo.CREATOR.createFromParcel(parcel2);
        } else {
          alarmClockInfo = null;
        } 
        return alarmClockInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getNextWakeFromIdleTime() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IAlarmManager");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null)
          return IAlarmManager.Stub.getDefaultImpl().getNextWakeFromIdleTime(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void remove(PendingIntent param1PendingIntent, IAlarmListener param1IAlarmListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IAlarmManager");
        if (param1PendingIntent != null) {
          parcel1.writeInt(1);
          param1PendingIntent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1IAlarmListener != null) {
          iBinder = param1IAlarmListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
          IAlarmManager.Stub.getDefaultImpl().remove(param1PendingIntent, param1IAlarmListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void set(String param1String1, int param1Int1, long param1Long1, long param1Long2, long param1Long3, int param1Int2, PendingIntent param1PendingIntent, IAlarmListener param1IAlarmListener, String param1String2, WorkSource param1WorkSource, AlarmManager.AlarmClockInfo param1AlarmClockInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IAlarmManager");
        parcel1.writeString(param1String1);
        parcel1.writeInt(param1Int1);
        parcel1.writeLong(param1Long1);
        parcel1.writeLong(param1Long2);
        parcel1.writeLong(param1Long3);
        parcel1.writeInt(param1Int2);
        if (param1PendingIntent != null) {
          try {
            parcel1.writeInt(1);
            param1PendingIntent.writeToParcel(parcel1, 0);
          } finally {}
        } else {
          parcel1.writeInt(0);
        } 
        if (param1IAlarmListener != null) {
          iBinder = param1IAlarmListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String2);
        if (param1WorkSource != null) {
          parcel1.writeInt(1);
          param1WorkSource.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1AlarmClockInfo != null) {
          parcel1.writeInt(1);
          param1AlarmClockInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
          IAlarmManager iAlarmManager = IAlarmManager.Stub.getDefaultImpl();
          try {
            iAlarmManager.set(param1String1, param1Int1, param1Long1, param1Long2, param1Long3, param1Int2, param1PendingIntent, param1IAlarmListener, param1String2, param1WorkSource, param1AlarmClockInfo);
            parcel2.recycle();
            parcel1.recycle();
            return;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          parcel.recycle();
          parcel1.recycle();
          return;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1String1;
    }
    
    public boolean setTime(long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IAlarmManager");
        parcel1.writeLong(param1Long);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
          bool = IAlarmManager.Stub.getDefaultImpl().setTime(param1Long);
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
    
    public void setTimeZone(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IAlarmManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
          IAlarmManager.Stub.getDefaultImpl().setTimeZone(param1String);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IAlarmManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */