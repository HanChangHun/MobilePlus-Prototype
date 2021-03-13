package android.app;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IAppTask {
  private static final String DESCRIPTOR = "android.app.IAppTask";
  
  static final int TRANSACTION_finishAndRemoveTask = 1;
  
  static final int TRANSACTION_getTaskInfo = 2;
  
  static final int TRANSACTION_moveToFront = 3;
  
  static final int TRANSACTION_setExcludeFromRecents = 5;
  
  static final int TRANSACTION_startActivity = 4;
  
  public Stub() {
    attachInterface(this, "android.app.IAppTask");
  }
  
  public static IAppTask asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IAppTask");
    return (iInterface != null && iInterface instanceof IAppTask) ? (IAppTask)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAppTask getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "setExcludeFromRecents") : "startActivity") : "moveToFront") : "getTaskInfo") : "finishAndRemoveTask";
  }
  
  public static boolean setDefaultImpl(IAppTask paramIAppTask) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAppTask != null) {
        Proxy.sDefaultImpl = paramIAppTask;
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
    ActivityManager.RecentTaskInfo recentTaskInfo;
    if (paramInt1 != 1) {
      boolean bool = false;
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          Intent intent;
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.app.IAppTask");
              return true;
            } 
            paramParcel1.enforceInterface("android.app.IAppTask");
            if (paramParcel1.readInt() != 0)
              bool = true; 
            setExcludeFromRecents(bool);
            paramParcel2.writeNoException();
            return true;
          } 
          paramParcel1.enforceInterface("android.app.IAppTask");
          IBinder iBinder = paramParcel1.readStrongBinder();
          String str1 = paramParcel1.readString();
          String str2 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
          } else {
            intent = null;
          } 
          String str3 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          paramInt1 = startActivity(iBinder, str1, str2, intent, str3, (Bundle)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        } 
        paramParcel1.enforceInterface("android.app.IAppTask");
        moveToFront(IApplicationThread.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      } 
      paramParcel1.enforceInterface("android.app.IAppTask");
      recentTaskInfo = getTaskInfo();
      paramParcel2.writeNoException();
      if (recentTaskInfo != null) {
        paramParcel2.writeInt(1);
        recentTaskInfo.writeToParcel(paramParcel2, 1);
      } else {
        paramParcel2.writeInt(0);
      } 
      return true;
    } 
    recentTaskInfo.enforceInterface("android.app.IAppTask");
    finishAndRemoveTask();
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IAppTask {
    public static IAppTask sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void finishAndRemoveTask() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IAppTask");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAppTask.Stub.getDefaultImpl() != null) {
          IAppTask.Stub.getDefaultImpl().finishAndRemoveTask();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IAppTask";
    }
    
    public ActivityManager.RecentTaskInfo getTaskInfo() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ActivityManager.RecentTaskInfo recentTaskInfo;
        parcel1.writeInterfaceToken("android.app.IAppTask");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IAppTask.Stub.getDefaultImpl() != null) {
          recentTaskInfo = IAppTask.Stub.getDefaultImpl().getTaskInfo();
          return recentTaskInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          recentTaskInfo = (ActivityManager.RecentTaskInfo)ActivityManager.RecentTaskInfo.CREATOR.createFromParcel(parcel2);
        } else {
          recentTaskInfo = null;
        } 
        return recentTaskInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void moveToFront(IApplicationThread param2IApplicationThread, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IAppTask");
        if (param2IApplicationThread != null) {
          iBinder = param2IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAppTask.Stub.getDefaultImpl() != null) {
          IAppTask.Stub.getDefaultImpl().moveToFront(param2IApplicationThread, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setExcludeFromRecents(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IAppTask");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAppTask.Stub.getDefaultImpl() != null) {
          IAppTask.Stub.getDefaultImpl().setExcludeFromRecents(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int startActivity(IBinder param2IBinder, String param2String1, String param2String2, Intent param2Intent, String param2String3, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IAppTask");
        try {
          parcel1.writeStrongBinder(param2IBinder);
          try {
            parcel1.writeString(param2String1);
            try {
              parcel1.writeString(param2String2);
              if (param2Intent != null) {
                parcel1.writeInt(1);
                param2Intent.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeString(param2String3);
                if (param2Bundle != null) {
                  parcel1.writeInt(1);
                  param2Bundle.writeToParcel(parcel1, 0);
                } else {
                  parcel1.writeInt(0);
                } 
                if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAppTask.Stub.getDefaultImpl() != null) {
                  int j = IAppTask.Stub.getDefaultImpl().startActivity(param2IBinder, param2String1, param2String2, param2Intent, param2String3, param2Bundle);
                  parcel2.recycle();
                  parcel1.recycle();
                  return j;
                } 
                parcel2.readException();
                int i = parcel2.readInt();
                parcel2.recycle();
                parcel1.recycle();
                return i;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2IBinder;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAppTask$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */