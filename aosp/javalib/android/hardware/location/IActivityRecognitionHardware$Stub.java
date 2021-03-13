package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IActivityRecognitionHardware {
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
  
  public static IActivityRecognitionHardware asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardware");
    return (iInterface != null && iInterface instanceof IActivityRecognitionHardware) ? (IActivityRecognitionHardware)iInterface : new Proxy(paramIBinder);
  }
  
  public static IActivityRecognitionHardware getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(IActivityRecognitionHardware paramIActivityRecognitionHardware) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIActivityRecognitionHardware != null) {
        Proxy.sDefaultImpl = paramIActivityRecognitionHardware;
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
      boolean bool;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 7:
          paramParcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
          bool = flush();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 6:
          paramParcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
          bool = disableActivityEvent(paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 5:
          paramParcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
          bool = enableActivityEvent(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readLong());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
          bool = unregisterSink(IActivityRecognitionHardwareSink.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 3:
          paramParcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
          bool = registerSink(IActivityRecognitionHardwareSink.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 2:
          paramParcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
          bool = isActivitySupported(paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardware");
      String[] arrayOfString = getSupportedActivities();
      paramParcel2.writeNoException();
      paramParcel2.writeStringArray(arrayOfString);
      return true;
    } 
    paramParcel2.writeString("android.hardware.location.IActivityRecognitionHardware");
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardware$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */