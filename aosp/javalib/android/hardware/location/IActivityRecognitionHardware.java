package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IActivityRecognitionHardware extends IInterface {
  boolean disableActivityEvent(String paramString, int paramInt) throws RemoteException;
  
  boolean enableActivityEvent(String paramString, int paramInt, long paramLong) throws RemoteException;
  
  boolean flush() throws RemoteException;
  
  String[] getSupportedActivities() throws RemoteException;
  
  boolean isActivitySupported(String paramString) throws RemoteException;
  
  boolean registerSink(IActivityRecognitionHardwareSink paramIActivityRecognitionHardwareSink) throws RemoteException;
  
  boolean unregisterSink(IActivityRecognitionHardwareSink paramIActivityRecognitionHardwareSink) throws RemoteException;
  
  public static class Default implements IActivityRecognitionHardware {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean disableActivityEvent(String param1String, int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean enableActivityEvent(String param1String, int param1Int, long param1Long) throws RemoteException {
      return false;
    }
    
    public boolean flush() throws RemoteException {
      return false;
    }
    
    public String[] getSupportedActivities() throws RemoteException {
      return null;
    }
    
    public boolean isActivitySupported(String param1String) throws RemoteException {
      return false;
    }
    
    public boolean registerSink(IActivityRecognitionHardwareSink param1IActivityRecognitionHardwareSink) throws RemoteException {
      return false;
    }
    
    public boolean unregisterSink(IActivityRecognitionHardwareSink param1IActivityRecognitionHardwareSink) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IActivityRecognitionHardware {
    private static final String DESCRIPTOR = "android.hardware.location.IActivityRecognitionHardware";
    
    static final int TRANSACTION_disableActivityEvent = 6;
    
    static final int TRANSACTION_enableActivityEvent = 5;
    
    static final int TRANSACTION_flush = 7;
    
    static final int TRANSACTION_getSupportedActivities = 1;
    
    static final int TRANSACTION_isActivitySupported = 2;
    
    static final int TRANSACTION_registerSink = 3;
    
    static final int TRANSACTION_unregisterSink = 4;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IActivityRecognitionHardware");
    }
    
    public static IActivityRecognitionHardware asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardware");
      return (iInterface != null && iInterface instanceof IActivityRecognitionHardware) ? (IActivityRecognitionHardware)iInterface : new Proxy(param1IBinder);
    }
    
    public static IActivityRecognitionHardware getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 7:
          return "flush";
        case 6:
          return "disableActivityEvent";
        case 5:
          return "enableActivityEvent";
        case 4:
          return "unregisterSink";
        case 3:
          return "registerSink";
        case 2:
          return "isActivitySupported";
        case 1:
          break;
      } 
      return "getSupportedActivities";
    }
    
    public static boolean setDefaultImpl(IActivityRecognitionHardware param1IActivityRecognitionHardware) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IActivityRecognitionHardware != null) {
          Proxy.sDefaultImpl = param1IActivityRecognitionHardware;
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
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 7:
            param1Parcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
            bool = flush();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
            bool = disableActivityEvent(param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
            bool = enableActivityEvent(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readLong());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
            bool = unregisterSink(IActivityRecognitionHardwareSink.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
            bool = registerSink(IActivityRecognitionHardwareSink.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
            bool = isActivitySupported(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
        String[] arrayOfString = getSupportedActivities();
        param1Parcel2.writeNoException();
        param1Parcel2.writeStringArray(arrayOfString);
        return true;
      } 
      param1Parcel2.writeString("android.hardware.location.IActivityRecognitionHardware");
      return true;
    }
    
    private static class Proxy implements IActivityRecognitionHardware {
      public static IActivityRecognitionHardware sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public boolean disableActivityEvent(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(6, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
            bool = IActivityRecognitionHardware.Stub.getDefaultImpl().disableActivityEvent(param2String, param2Int);
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
      
      public boolean enableActivityEvent(String param2String, int param2Int, long param2Long) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          parcel1.writeLong(param2Long);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(5, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
            bool = IActivityRecognitionHardware.Stub.getDefaultImpl().enableActivityEvent(param2String, param2Int, param2Long);
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
      
      public boolean flush() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(7, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
            bool = IActivityRecognitionHardware.Stub.getDefaultImpl().flush();
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
      
      public String getInterfaceDescriptor() {
        return "android.hardware.location.IActivityRecognitionHardware";
      }
      
      public String[] getSupportedActivities() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null)
            return IActivityRecognitionHardware.Stub.getDefaultImpl().getSupportedActivities(); 
          parcel2.readException();
          return parcel2.createStringArray();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isActivitySupported(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(2, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
            bool = IActivityRecognitionHardware.Stub.getDefaultImpl().isActivitySupported(param2String);
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
      
      public boolean registerSink(IActivityRecognitionHardwareSink param2IActivityRecognitionHardwareSink) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
          if (param2IActivityRecognitionHardwareSink != null) {
            iBinder = param2IActivityRecognitionHardwareSink.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(3, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
            bool = IActivityRecognitionHardware.Stub.getDefaultImpl().registerSink(param2IActivityRecognitionHardwareSink);
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
      
      public boolean unregisterSink(IActivityRecognitionHardwareSink param2IActivityRecognitionHardwareSink) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
          if (param2IActivityRecognitionHardwareSink != null) {
            iBinder = param2IActivityRecognitionHardwareSink.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(4, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
            bool = IActivityRecognitionHardware.Stub.getDefaultImpl().unregisterSink(param2IActivityRecognitionHardwareSink);
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
    }
  }
  
  private static class Proxy implements IActivityRecognitionHardware {
    public static IActivityRecognitionHardware sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean disableActivityEvent(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
          bool = IActivityRecognitionHardware.Stub.getDefaultImpl().disableActivityEvent(param1String, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean enableActivityEvent(String param1String, int param1Int, long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        parcel1.writeLong(param1Long);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(5, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
          bool = IActivityRecognitionHardware.Stub.getDefaultImpl().enableActivityEvent(param1String, param1Int, param1Long);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean flush() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
          bool = IActivityRecognitionHardware.Stub.getDefaultImpl().flush();
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
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IActivityRecognitionHardware";
    }
    
    public String[] getSupportedActivities() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null)
          return IActivityRecognitionHardware.Stub.getDefaultImpl().getSupportedActivities(); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isActivitySupported(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
          bool = IActivityRecognitionHardware.Stub.getDefaultImpl().isActivitySupported(param1String);
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
    
    public boolean registerSink(IActivityRecognitionHardwareSink param1IActivityRecognitionHardwareSink) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
        if (param1IActivityRecognitionHardwareSink != null) {
          iBinder = param1IActivityRecognitionHardwareSink.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(3, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
          bool = IActivityRecognitionHardware.Stub.getDefaultImpl().registerSink(param1IActivityRecognitionHardwareSink);
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
    
    public boolean unregisterSink(IActivityRecognitionHardwareSink param1IActivityRecognitionHardwareSink) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
        if (param1IActivityRecognitionHardwareSink != null) {
          iBinder = param1IActivityRecognitionHardwareSink.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(4, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
          bool = IActivityRecognitionHardware.Stub.getDefaultImpl().unregisterSink(param1IActivityRecognitionHardwareSink);
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
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardware.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */