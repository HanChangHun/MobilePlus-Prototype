package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.WorkSource;

class Proxy implements IAlarmManager {
  public static IAlarmManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
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
  
  public AlarmManager.AlarmClockInfo getNextAlarmClock(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      AlarmManager.AlarmClockInfo alarmClockInfo;
      parcel1.writeInterfaceToken("android.app.IAlarmManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
        alarmClockInfo = IAlarmManager.Stub.getDefaultImpl().getNextAlarmClock(paramInt);
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
  
  public void remove(PendingIntent paramPendingIntent, IAlarmListener paramIAlarmListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IAlarmManager");
      if (paramPendingIntent != null) {
        parcel1.writeInt(1);
        paramPendingIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIAlarmListener != null) {
        iBinder = paramIAlarmListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
        IAlarmManager.Stub.getDefaultImpl().remove(paramPendingIntent, paramIAlarmListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void set(String paramString1, int paramInt1, long paramLong1, long paramLong2, long paramLong3, int paramInt2, PendingIntent paramPendingIntent, IAlarmListener paramIAlarmListener, String paramString2, WorkSource paramWorkSource, AlarmManager.AlarmClockInfo paramAlarmClockInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IAlarmManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt1);
      parcel1.writeLong(paramLong1);
      parcel1.writeLong(paramLong2);
      parcel1.writeLong(paramLong3);
      parcel1.writeInt(paramInt2);
      if (paramPendingIntent != null) {
        try {
          parcel1.writeInt(1);
          paramPendingIntent.writeToParcel(parcel1, 0);
        } finally {}
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIAlarmListener != null) {
        iBinder = paramIAlarmListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString2);
      if (paramWorkSource != null) {
        parcel1.writeInt(1);
        paramWorkSource.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramAlarmClockInfo != null) {
        parcel1.writeInt(1);
        paramAlarmClockInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
        IAlarmManager iAlarmManager = IAlarmManager.Stub.getDefaultImpl();
        try {
          iAlarmManager.set(paramString1, paramInt1, paramLong1, paramLong2, paramLong3, paramInt2, paramPendingIntent, paramIAlarmListener, paramString2, paramWorkSource, paramAlarmClockInfo);
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
    throw paramString1;
  }
  
  public boolean setTime(long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IAlarmManager");
      parcel1.writeLong(paramLong);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
        bool = IAlarmManager.Stub.getDefaultImpl().setTime(paramLong);
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
  
  public void setTimeZone(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IAlarmManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAlarmManager.Stub.getDefaultImpl() != null) {
        IAlarmManager.Stub.getDefaultImpl().setTimeZone(paramString);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IAlarmManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */