package android.app;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IActivityController {
  public static IActivityController sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public boolean activityResuming(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityController");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
        bool = IActivityController.Stub.getDefaultImpl().activityResuming(paramString);
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
  
  public boolean activityStarting(Intent paramIntent, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityController");
      boolean bool = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
        bool = IActivityController.Stub.getDefaultImpl().activityStarting(paramIntent, paramString);
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
  
  public boolean appCrashed(String paramString1, int paramInt, String paramString2, String paramString3, long paramLong, String paramString4) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityController");
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeInt(paramInt);
          try {
            parcel1.writeString(paramString2);
            try {
              parcel1.writeString(paramString3);
              parcel1.writeLong(paramLong);
              try {
                parcel1.writeString(paramString4);
                IBinder iBinder = this.mRemote;
                boolean bool = false;
                if (!iBinder.transact(3, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
                  bool = IActivityController.Stub.getDefaultImpl().appCrashed(paramString1, paramInt, paramString2, paramString3, paramLong, paramString4);
                  parcel2.recycle();
                  parcel1.recycle();
                  return bool;
                } 
                parcel2.readException();
                paramInt = parcel2.readInt();
                if (paramInt != 0)
                  bool = true; 
                parcel2.recycle();
                parcel1.recycle();
                return bool;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public int appEarlyNotResponding(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityController");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
        paramInt = IActivityController.Stub.getDefaultImpl().appEarlyNotResponding(paramString1, paramInt, paramString2);
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
  
  public int appNotResponding(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityController");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
        paramInt = IActivityController.Stub.getDefaultImpl().appNotResponding(paramString1, paramInt, paramString2);
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
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IActivityController";
  }
  
  public int systemNotResponding(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityController");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null)
        return IActivityController.Stub.getDefaultImpl().systemNotResponding(paramString); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityController$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */