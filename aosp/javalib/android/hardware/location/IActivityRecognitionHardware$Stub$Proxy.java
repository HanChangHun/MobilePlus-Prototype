package android.hardware.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IActivityRecognitionHardware {
  public static IActivityRecognitionHardware sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public boolean disableActivityEvent(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(6, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
        bool = IActivityRecognitionHardware.Stub.getDefaultImpl().disableActivityEvent(paramString, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean enableActivityEvent(String paramString, int paramInt, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      parcel1.writeLong(paramLong);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(5, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
        bool = IActivityRecognitionHardware.Stub.getDefaultImpl().enableActivityEvent(paramString, paramInt, paramLong);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
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
  
  public boolean isActivitySupported(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
        bool = IActivityRecognitionHardware.Stub.getDefaultImpl().isActivitySupported(paramString);
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
  
  public boolean registerSink(IActivityRecognitionHardwareSink paramIActivityRecognitionHardwareSink) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
      if (paramIActivityRecognitionHardwareSink != null) {
        iBinder = paramIActivityRecognitionHardwareSink.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(3, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
        bool = IActivityRecognitionHardware.Stub.getDefaultImpl().registerSink(paramIActivityRecognitionHardwareSink);
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
  
  public boolean unregisterSink(IActivityRecognitionHardwareSink paramIActivityRecognitionHardwareSink) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardware");
      if (paramIActivityRecognitionHardwareSink != null) {
        iBinder = paramIActivityRecognitionHardwareSink.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(4, parcel1, parcel2, 0) && IActivityRecognitionHardware.Stub.getDefaultImpl() != null) {
        bool = IActivityRecognitionHardware.Stub.getDefaultImpl().unregisterSink(paramIActivityRecognitionHardwareSink);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardware$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */