package android.app;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IActivityController extends IInterface {
  boolean activityResuming(String paramString) throws RemoteException;
  
  boolean activityStarting(Intent paramIntent, String paramString) throws RemoteException;
  
  boolean appCrashed(String paramString1, int paramInt, String paramString2, String paramString3, long paramLong, String paramString4) throws RemoteException;
  
  int appEarlyNotResponding(String paramString1, int paramInt, String paramString2) throws RemoteException;
  
  int appNotResponding(String paramString1, int paramInt, String paramString2) throws RemoteException;
  
  int systemNotResponding(String paramString) throws RemoteException;
  
  public static class Default implements IActivityController {
    public boolean activityResuming(String param1String) throws RemoteException {
      return false;
    }
    
    public boolean activityStarting(Intent param1Intent, String param1String) throws RemoteException {
      return false;
    }
    
    public boolean appCrashed(String param1String1, int param1Int, String param1String2, String param1String3, long param1Long, String param1String4) throws RemoteException {
      return false;
    }
    
    public int appEarlyNotResponding(String param1String1, int param1Int, String param1String2) throws RemoteException {
      return 0;
    }
    
    public int appNotResponding(String param1String1, int param1Int, String param1String2) throws RemoteException {
      return 0;
    }
    
    public IBinder asBinder() {
      return null;
    }
    
    public int systemNotResponding(String param1String) throws RemoteException {
      return 0;
    }
  }
  
  public static abstract class Stub extends Binder implements IActivityController {
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
    
    public static IActivityController asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IActivityController");
      return (iInterface != null && iInterface instanceof IActivityController) ? (IActivityController)iInterface : new Proxy(param1IBinder);
    }
    
    public static IActivityController getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IActivityController param1IActivityController) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IActivityController != null) {
          Proxy.sDefaultImpl = param1IActivityController;
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
        Intent intent;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 6:
            param1Parcel1.enforceInterface("android.app.IActivityController");
            param1Int1 = systemNotResponding(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.app.IActivityController");
            param1Int1 = appNotResponding(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.app.IActivityController");
            param1Int1 = appEarlyNotResponding(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.app.IActivityController");
            bool = appCrashed(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readString(), param1Parcel1.readString(), param1Parcel1.readLong(), param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.app.IActivityController");
            bool = activityResuming(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.app.IActivityController");
        if (param1Parcel1.readInt() != 0) {
          intent = (Intent)Intent.CREATOR.createFromParcel(param1Parcel1);
        } else {
          intent = null;
        } 
        boolean bool = activityStarting(intent, param1Parcel1.readString());
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(bool);
        return true;
      } 
      param1Parcel2.writeString("android.app.IActivityController");
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
  
  private static class Proxy implements IActivityController {
    public static IActivityController sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public boolean activityResuming(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
          bool = IActivityController.Stub.getDefaultImpl().activityResuming(param1String);
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
    
    public boolean activityStarting(Intent param1Intent, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        boolean bool = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
          bool = IActivityController.Stub.getDefaultImpl().activityStarting(param1Intent, param1String);
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
    
    public boolean appCrashed(String param1String1, int param1Int, String param1String2, String param1String3, long param1Long, String param1String4) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        try {
          parcel1.writeString(param1String1);
          try {
            parcel1.writeInt(param1Int);
            try {
              parcel1.writeString(param1String2);
              try {
                parcel1.writeString(param1String3);
                parcel1.writeLong(param1Long);
                try {
                  parcel1.writeString(param1String4);
                  IBinder iBinder = this.mRemote;
                  boolean bool = false;
                  if (!iBinder.transact(3, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
                    bool = IActivityController.Stub.getDefaultImpl().appCrashed(param1String1, param1Int, param1String2, param1String3, param1Long, param1String4);
                    parcel2.recycle();
                    parcel1.recycle();
                    return bool;
                  } 
                  parcel2.readException();
                  param1Int = parcel2.readInt();
                  if (param1Int != 0)
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
      throw param1String1;
    }
    
    public int appEarlyNotResponding(String param1String1, int param1Int, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        parcel1.writeString(param1String1);
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
          param1Int = IActivityController.Stub.getDefaultImpl().appEarlyNotResponding(param1String1, param1Int, param1String2);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int appNotResponding(String param1String1, int param1Int, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        parcel1.writeString(param1String1);
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null) {
          param1Int = IActivityController.Stub.getDefaultImpl().appNotResponding(param1String1, param1Int, param1String2);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
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
    
    public int systemNotResponding(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityController");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IActivityController.Stub.getDefaultImpl() != null)
          return IActivityController.Stub.getDefaultImpl().systemNotResponding(param1String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */