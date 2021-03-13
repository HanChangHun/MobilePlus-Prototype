package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IActivityRecognitionHardwareWatcher {
  private static final String DESCRIPTOR = "android.hardware.location.IActivityRecognitionHardwareWatcher";
  
  static final int TRANSACTION_onInstanceChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.location.IActivityRecognitionHardwareWatcher");
  }
  
  public static IActivityRecognitionHardwareWatcher asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardwareWatcher");
    return (iInterface != null && iInterface instanceof IActivityRecognitionHardwareWatcher) ? (IActivityRecognitionHardwareWatcher)iInterface : new Proxy(paramIBinder);
  }
  
  public static IActivityRecognitionHardwareWatcher getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onInstanceChanged";
  }
  
  public static boolean setDefaultImpl(IActivityRecognitionHardwareWatcher paramIActivityRecognitionHardwareWatcher) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIActivityRecognitionHardwareWatcher != null) {
        Proxy.sDefaultImpl = paramIActivityRecognitionHardwareWatcher;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.location.IActivityRecognitionHardwareWatcher");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardwareWatcher");
    onInstanceChanged(IActivityRecognitionHardware.Stub.asInterface(paramParcel1.readStrongBinder()));
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IActivityRecognitionHardwareWatcher {
    public static IActivityRecognitionHardwareWatcher sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IActivityRecognitionHardwareWatcher";
    }
    
    public void onInstanceChanged(IActivityRecognitionHardware param2IActivityRecognitionHardware) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareWatcher");
        if (param2IActivityRecognitionHardware != null) {
          iBinder = param2IActivityRecognitionHardware.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityRecognitionHardwareWatcher.Stub.getDefaultImpl() != null) {
          IActivityRecognitionHardwareWatcher.Stub.getDefaultImpl().onInstanceChanged(param2IActivityRecognitionHardware);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardwareWatcher$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */