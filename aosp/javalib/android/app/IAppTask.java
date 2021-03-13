package android.app;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAppTask extends IInterface {
  void finishAndRemoveTask() throws RemoteException;
  
  ActivityManager.RecentTaskInfo getTaskInfo() throws RemoteException;
  
  void moveToFront(IApplicationThread paramIApplicationThread, String paramString) throws RemoteException;
  
  void setExcludeFromRecents(boolean paramBoolean) throws RemoteException;
  
  int startActivity(IBinder paramIBinder, String paramString1, String paramString2, Intent paramIntent, String paramString3, Bundle paramBundle) throws RemoteException;
  
  public static class Default implements IAppTask {
    public IBinder asBinder() {
      return null;
    }
    
    public void finishAndRemoveTask() throws RemoteException {}
    
    public ActivityManager.RecentTaskInfo getTaskInfo() throws RemoteException {
      return null;
    }
    
    public void moveToFront(IApplicationThread param1IApplicationThread, String param1String) throws RemoteException {}
    
    public void setExcludeFromRecents(boolean param1Boolean) throws RemoteException {}
    
    public int startActivity(IBinder param1IBinder, String param1String1, String param1String2, Intent param1Intent, String param1String3, Bundle param1Bundle) throws RemoteException {
      return 0;
    }
  }
  
  public static abstract class Stub extends Binder implements IAppTask {
    private static final String DESCRIPTOR = "android.app.IAppTask";
    
    static final int TRANSACTION_finishAndRemoveTask = 1;
    
    static final int TRANSACTION_getTaskInfo = 2;
    
    static final int TRANSACTION_moveToFront = 3;
    
    static final int TRANSACTION_setExcludeFromRecents = 5;
    
    static final int TRANSACTION_startActivity = 4;
    
    public Stub() {
      attachInterface(this, "android.app.IAppTask");
    }
    
    public static IAppTask asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IAppTask");
      return (iInterface != null && iInterface instanceof IAppTask) ? (IAppTask)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAppTask getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? ((param1Int != 5) ? null : "setExcludeFromRecents") : "startActivity") : "moveToFront") : "getTaskInfo") : "finishAndRemoveTask";
    }
    
    public static boolean setDefaultImpl(IAppTask param1IAppTask) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAppTask != null) {
          Proxy.sDefaultImpl = param1IAppTask;
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
      ActivityManager.RecentTaskInfo recentTaskInfo;
      if (param1Int1 != 1) {
        boolean bool = false;
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            Intent intent;
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                if (param1Int1 != 1598968902)
                  return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
                param1Parcel2.writeString("android.app.IAppTask");
                return true;
              } 
              param1Parcel1.enforceInterface("android.app.IAppTask");
              if (param1Parcel1.readInt() != 0)
                bool = true; 
              setExcludeFromRecents(bool);
              param1Parcel2.writeNoException();
              return true;
            } 
            param1Parcel1.enforceInterface("android.app.IAppTask");
            IBinder iBinder = param1Parcel1.readStrongBinder();
            String str1 = param1Parcel1.readString();
            String str2 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              intent = (Intent)Intent.CREATOR.createFromParcel(param1Parcel1);
            } else {
              intent = null;
            } 
            String str3 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            param1Int1 = startActivity(iBinder, str1, str2, intent, str3, (Bundle)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          } 
          param1Parcel1.enforceInterface("android.app.IAppTask");
          moveToFront(IApplicationThread.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString());
          param1Parcel2.writeNoException();
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.IAppTask");
        recentTaskInfo = getTaskInfo();
        param1Parcel2.writeNoException();
        if (recentTaskInfo != null) {
          param1Parcel2.writeInt(1);
          recentTaskInfo.writeToParcel(param1Parcel2, 1);
        } else {
          param1Parcel2.writeInt(0);
        } 
        return true;
      } 
      recentTaskInfo.enforceInterface("android.app.IAppTask");
      finishAndRemoveTask();
      param1Parcel2.writeNoException();
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
  
  private static class Proxy implements IAppTask {
    public static IAppTask sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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
    
    public void moveToFront(IApplicationThread param1IApplicationThread, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IAppTask");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAppTask.Stub.getDefaultImpl() != null) {
          IAppTask.Stub.getDefaultImpl().moveToFront(param1IApplicationThread, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setExcludeFromRecents(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IAppTask");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAppTask.Stub.getDefaultImpl() != null) {
          IAppTask.Stub.getDefaultImpl().setExcludeFromRecents(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int startActivity(IBinder param1IBinder, String param1String1, String param1String2, Intent param1Intent, String param1String3, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IAppTask");
        try {
          parcel1.writeStrongBinder(param1IBinder);
          try {
            parcel1.writeString(param1String1);
            try {
              parcel1.writeString(param1String2);
              if (param1Intent != null) {
                parcel1.writeInt(1);
                param1Intent.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeString(param1String3);
                if (param1Bundle != null) {
                  parcel1.writeInt(1);
                  param1Bundle.writeToParcel(parcel1, 0);
                } else {
                  parcel1.writeInt(0);
                } 
                if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAppTask.Stub.getDefaultImpl() != null) {
                  int j = IAppTask.Stub.getDefaultImpl().startActivity(param1IBinder, param1String1, param1String2, param1Intent, param1String3, param1Bundle);
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
      throw param1IBinder;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAppTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */