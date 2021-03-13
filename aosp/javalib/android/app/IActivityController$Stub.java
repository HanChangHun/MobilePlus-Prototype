package android.app;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IActivityController {
  private static final String DESCRIPTOR = "android.app.IActivityController";
  
  static final int TRANSACTION_activityResuming = 2;
  
  static final int TRANSACTION_activityStarting = 1;
  
  static final int TRANSACTION_appCrashed = 3;
  
  static final int TRANSACTION_appEarlyNotResponding = 4;
  
  static final int TRANSACTION_appNotResponding = 5;
  
  static final int TRANSACTION_systemNotResponding = 6;
  
  public Stub() {
    attachInterface(this, "android.app.IActivityController");
  }
  
  public static IActivityController asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IActivityController");
    return (iInterface != null && iInterface instanceof IActivityController) ? (IActivityController)iInterface : new Proxy(paramIBinder);
  }
  
  public static IActivityController getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 6:
        return "systemNotResponding";
      case 5:
        return "appNotResponding";
      case 4:
        return "appEarlyNotResponding";
      case 3:
        return "appCrashed";
      case 2:
        return "activityResuming";
      case 1:
        break;
    } 
    return "activityStarting";
  }
  
  public static boolean setDefaultImpl(IActivityController paramIActivityController) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIActivityController != null) {
        Proxy.sDefaultImpl = paramIActivityController;
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
      Intent intent;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 6:
          paramParcel1.enforceInterface("android.app.IActivityController");
          paramInt1 = systemNotResponding(paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.IActivityController");
          paramInt1 = appNotResponding(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.app.IActivityController");
          paramInt1 = appEarlyNotResponding(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 3:
          paramParcel1.enforceInterface("android.app.IActivityController");
          bool = appCrashed(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readLong(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 2:
          paramParcel1.enforceInterface("android.app.IActivityController");
          bool = activityResuming(paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.app.IActivityController");
      if (paramParcel1.readInt() != 0) {
        intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
      } else {
        intent = null;
      } 
      boolean bool = activityStarting(intent, paramParcel1.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeInt(bool);
      return true;
    } 
    paramParcel2.writeString("android.app.IActivityController");
    return true;
  }
  
  private static class Proxy implements IActivityController {
    public static IActivityController sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public boolean activityResuming(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
          bool = IActivityController.Stub.getDefaultImpl().activityResuming(param2String);
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
    
    public boolean activityStarting(Intent param2Intent, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        boolean bool = true;
        if (param2Intent != null) {
          parcel1.writeInt(1);
          param2Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
          bool = IActivityController.Stub.getDefaultImpl().activityStarting(param2Intent, param2String);
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
    
    public boolean appCrashed(String param2String1, int param2Int, String param2String2, String param2String3, long param2Long, String param2String4) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeInt(param2Int);
            try {
              parcel1.writeString(param2String2);
              try {
                parcel1.writeString(param2String3);
                parcel1.writeLong(param2Long);
                try {
                  parcel1.writeString(param2String4);
                  IBinder iBinder = this.mRemote;
                  boolean bool = false;
                  if (!iBinder.transact(3, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
                    bool = IActivityController.Stub.getDefaultImpl().appCrashed(param2String1, param2Int, param2String2, param2String3, param2Long, param2String4);
                    parcel2.recycle();
                    parcel1.recycle();
                    return bool;
                  } 
                  parcel2.readException();
                  param2Int = parcel2.readInt();
                  if (param2Int != 0)
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
      throw param2String1;
    }
    
    public int appEarlyNotResponding(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
          param2Int = IActivityController.Stub.getDefaultImpl().appEarlyNotResponding(param2String1, param2Int, param2String2);
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
    
    public int appNotResponding(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
          param2Int = IActivityController.Stub.getDefaultImpl().appNotResponding(param2String1, param2Int, param2String2);
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
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IActivityController";
    }
    
    public int systemNotResponding(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null)
          return IActivityController.Stub.getDefaultImpl().systemNotResponding(param2String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityController$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */