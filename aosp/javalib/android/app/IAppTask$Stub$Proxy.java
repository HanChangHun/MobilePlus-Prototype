package android.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IAppTask {
  public static IAppTask sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
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
  
  public void moveToFront(IApplicationThread paramIApplicationThread, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IAppTask");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAppTask.Stub.getDefaultImpl() != null) {
        IAppTask.Stub.getDefaultImpl().moveToFront(paramIApplicationThread, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setExcludeFromRecents(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IAppTask");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAppTask.Stub.getDefaultImpl() != null) {
        IAppTask.Stub.getDefaultImpl().setExcludeFromRecents(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int startActivity(IBinder paramIBinder, String paramString1, String paramString2, Intent paramIntent, String paramString3, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IAppTask");
      try {
        parcel1.writeStrongBinder(paramIBinder);
        try {
          parcel1.writeString(paramString1);
          try {
            parcel1.writeString(paramString2);
            if (paramIntent != null) {
              parcel1.writeInt(1);
              paramIntent.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeString(paramString3);
              if (paramBundle != null) {
                parcel1.writeInt(1);
                paramBundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAppTask.Stub.getDefaultImpl() != null) {
                int j = IAppTask.Stub.getDefaultImpl().startActivity(paramIBinder, paramString1, paramString2, paramIntent, paramString3, paramBundle);
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
    throw paramIBinder;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAppTask$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */